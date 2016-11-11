/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.crypt.Crypter;
import net.hades.fix.message.exception.*;
import net.hades.fix.message.struct.ProcessingState;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.*;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.codec.util.FixmlCodecUtil;

/**
 * Generic FIX message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class FIXMsg extends FIXFragment implements Message {

    private static final long serialVersionUID = 1L;

    public static final byte SOH = 1;
    
    /**
     * FIX message field separator.
     */
    public static final String SOH_OCTAL = "\01";
    
    public static final String DEFAULT_CHARACTER_SET = "ISO-8859-1";

    private static final String SEND_MSG_PRINT_LINE = ">>>";

    private static final String RCVD_MSG_PRINT_LINE = "<<<";

    private int priority;
    
    private long orderSequence; 

    /**
     * FIX message header data.
     */
    protected Header header;

    /**
     * FIX mesage trailer data.
     */
    protected Trailer trailer;
    
    /**
     * Raw FIX message as received from the transport wrapped in a
     * ByteBuffer for easy processing.
     */
    protected ByteBuffer message;
    
    /**
     * Map containing the unknown tags found in the message header together
     * with their values. These tags are most likely custom tags.
     */
    protected Map<Integer, String> customTags;

    /**
     * Flag indicating that the body of the FIX message has been decoded and as a result the
     * message attributes are populated.
     */
    protected boolean decoded;
    
    /** 
     * Holds the state of the processing for header and body. Used to optimize
     * the message decoding.
     */
    private ProcessingState processingState;

    /**
     * If set the begin string for messages using FIXT will be set to message version.
     */
    private boolean useNonStdBeginString;

    public FIXMsg() {
        customTags = new HashMap<>();
        priority = Message.PRIORITY_NORMAL;
        encryptionRequired = false;
        setSessionData();
    }

    public FIXMsg(Header header) throws InvalidMsgException {
        this();
        this.header = header;
        header.setValidateRequired(validateRequired);
        setTrailer(header.getBeginString());
    }

    /**
     * Creates a new message from the given FIX message. It checks the message
     * checksum and creates a message token list.
     * @param header FIX message decoded header
     * @param rawMsg FIX message received
     * @throws InvalidMsgException message decode error
     * @throws BadFormatMsgException message contains recoverable missing tags or data
     */
    public FIXMsg(Header header, final ByteBuffer rawMsg)
    throws InvalidMsgException, BadFormatMsgException {
        this(header);
        processingState = new ProcessingState();
        this.message = rawMsg;
    }

    /**
     * Creates a new empty FIX message fot the given FIX version.
     * @param msgType type of message to create
     * @param beginString FIX version
     * @throws InvalidMsgException message format error
     */
    public FIXMsg(String msgType, BeginString beginString) throws InvalidMsgException {
        this();
        setHeader(msgType, beginString);
        header.setValidateRequired(validateRequired);
        setTrailer(beginString);
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Message version [{0}] type [{1}].", new Object[] { beginString.getValue(), msgType });
        }
    }

    /**
     * Creates a new empty FIX message fot the given FIX version and application version.
     * @param msgType type of message to create
     * @param beginString FIX version
     * @param applVerID app version
     * @throws InvalidMsgException message format error
     */
    public FIXMsg(String msgType, BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        this();
        setHeader(msgType, beginString, applVerID);
        header.setValidateRequired(validateRequired);
        setTrailer(beginString);
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Message version [{0}] type [{1}].", new Object[] { beginString.getValue(), msgType });
        }
    }
    /**
     * Encode a message that has already the required values set.
     * @return encoded message
     * @throws TagNotPresentException data for a required tag is missing
     * @throws BadFormatMsgException the message values cannot be encoded
     */
    public byte[] encode() throws TagNotPresentException, BadFormatMsgException {
        byte[] verFragment = encodeVersion();
        byte[] headerFragment;
        byte[] headerFragmentSecured = new byte[0];
        if (encryptionRequired) {
            headerFragmentSecured = header.encode(MsgSecureType.SECURED_FIELDS);
            headerFragment = header.encode(MsgSecureType.UNSECURED_FIELDS);
        } else {
            headerFragment = header.encode(MsgSecureType.ALL_FIELDS);
        }
        // Call the specific message body check
        byte[] bodyStdFragment;
        byte[] bodyStdFragmentSecured = new byte[0];
        if (encryptionRequired) {
            bodyStdFragmentSecured = encodeFragmentSecured(true);
            bodyStdFragment = encodeFragmentSecured(false);
        } else {
            bodyStdFragment = encodeFragmentAll();
        }
        byte[] bodyCustomFragment = encodeCustom();
        byte[] bodyLenFragment;
        byte[] encryptedFragment = new byte[0];
        byte[] signature = trailer.encodeSignature();
        if (encryptionRequired) {
            byte[] bodyFragmentToEncrypt = new byte[headerFragmentSecured.length + bodyStdFragmentSecured.length + bodyCustomFragment.length];
            System.arraycopy(headerFragmentSecured, 0, bodyFragmentToEncrypt, 0, headerFragmentSecured.length);
            System.arraycopy(bodyStdFragmentSecured, 0, bodyFragmentToEncrypt, headerFragmentSecured.length, bodyStdFragmentSecured.length);
            System.arraycopy(bodyCustomFragment, 0, bodyFragmentToEncrypt, headerFragmentSecured.length + bodyStdFragmentSecured.length, 
                    bodyCustomFragment.length);
            byte[] encryptedData = new byte[0];
            try {
                encryptedData = encryptSecureData(bodyFragmentToEncrypt);
            } catch (UnsupportedCrypterException ex) {
                throw new BadFormatMsgException("Encryption method not supported.", ex);
            }
            encryptedFragment = encodeSecuredData(encryptedData);
            bodyLenFragment = encodeBodyLength(headerFragment.length + bodyStdFragment.length + encryptedFragment.length + signature.length);
        } else {
            bodyLenFragment = encodeBodyLength(headerFragment.length + bodyStdFragment.length + bodyCustomFragment.length + signature.length);
        }
        byte[] bodyChksumFragment;
        if (encryptionRequired) {
            bodyChksumFragment = new byte[verFragment.length + bodyLenFragment.length + headerFragment.length + bodyStdFragment.length + 
                encryptedFragment.length + signature.length];
            System.arraycopy(verFragment, 
                0,
                bodyChksumFragment,
                0,
                verFragment.length);
            System.arraycopy(bodyLenFragment,
                0,
                bodyChksumFragment, verFragment.length,
                bodyLenFragment.length);
            System.arraycopy(headerFragment, 
                0,
                bodyChksumFragment,
                verFragment.length + bodyLenFragment.length,
                headerFragment.length);
            System.arraycopy(encryptedFragment,
                0,
                bodyChksumFragment,
                verFragment.length + bodyLenFragment.length + headerFragment.length,
                encryptedFragment.length);
            System.arraycopy(bodyStdFragment,
                0,
                bodyChksumFragment, verFragment.length + bodyLenFragment.length + headerFragment.length + encryptedFragment.length,
                bodyStdFragment.length);
            System.arraycopy(signature,
                0,
                bodyChksumFragment, verFragment.length + bodyLenFragment.length + headerFragment.length + encryptedFragment.length + bodyStdFragment.length,
                signature.length);
        } else {
            bodyChksumFragment = new byte[verFragment.length + bodyLenFragment.length + headerFragment.length + bodyStdFragment.length + 
                bodyCustomFragment.length + signature.length];
            System.arraycopy(verFragment, 0, bodyChksumFragment, 0, verFragment.length);
            System.arraycopy(bodyLenFragment, 0, bodyChksumFragment, verFragment.length, bodyLenFragment.length);
            System.arraycopy(headerFragment, 0, bodyChksumFragment, verFragment.length + bodyLenFragment.length, headerFragment.length);
            System.arraycopy(bodyStdFragment, 0, bodyChksumFragment, verFragment.length + bodyLenFragment.length + headerFragment.length,
                    bodyStdFragment.length);
            System.arraycopy(bodyCustomFragment, 0, bodyChksumFragment, verFragment.length + bodyLenFragment.length +
                    headerFragment.length + bodyStdFragment.length, bodyCustomFragment.length);
            System.arraycopy(signature, 0, bodyChksumFragment, verFragment.length + bodyLenFragment.length +
                    headerFragment.length + bodyStdFragment.length + bodyCustomFragment.length, signature.length);
        }
        byte[] trailerFragment = trailer.encodeFragment(bodyChksumFragment);
        byte[] msg = new byte[bodyChksumFragment.length + trailerFragment.length];
        System.arraycopy(bodyChksumFragment, 0, msg, 0, bodyChksumFragment.length);
        System.arraycopy(trailerFragment, 0, msg, bodyChksumFragment.length, trailerFragment.length);

        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest(SEND_MSG_PRINT_LINE);
            LOGGER.finest(toString());
        }
        
        return msg;
    }
    
    /**
     * Decodes a message received as a char static array.
     * @throws InvalidMsgException received message is invalid
     * @throws BadFormatMsgException recoverable message error
     */
    public void decode() throws InvalidMsgException, BadFormatMsgException {
        
        try {
            while (message.hasRemaining()) {
                message.mark();
                Tag tag = MsgUtil.getNextTag(message);
                if (!processingState.HeaderProcessed && MsgUtil.isTagInList(tag.tagNum, header.getFragmentAllTags())) {
                    message = header.decode(tag, message);
                } else if (!processingState.BodyProcessed && MsgUtil.isTagInList(tag.tagNum, getFragmentAllTags())) {
                    message = decode(tag, message);
                    processingState.HeaderProcessed = true;
                } else if (MsgUtil.isTagInList(tag.tagNum, trailer.getFragmentAllTags())) {
                    message = trailer.decode(tag, message);
                    processingState.BodyProcessed = true;
                } else {
                    customTags.put(tag.tagNum, new String(tag.value));
                }
            }
            if (validateRequired) {
                validateRequiredTags();
            }
            decoded = true;
        } catch (TagNotPresentException ex) {
            throw new BadFormatMsgException(SessionRejectReason.RequiredTagMissing, getHeader().getMsgType(),
                    TagNum.MsgType.getValue(), ex.getMessage());
        }

        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest(RCVD_MSG_PRINT_LINE);
            LOGGER.finest(toString());
        }

    }

    /**
     * Creates a FIXML string representation of the current object.
     * @return FIXML string data
     * @throws net.hades.fix.message.exception.FixmlEncodingException error in marshaling data to FIXML format
     */
    public String toFixml() throws FixmlEncodingException {
        return FixmlCodecUtil.getInstance().getCodec().marshall(this);
    }

    /**
     * Populates current object with the data from a FIXML string.
     * @param fixml FIXML string data
     * @throws net.hades.fix.message.exception.FixmlDecodingException error in un-marshaling data from FIXML format
     */
    public void fromFixml(String fixml) throws FixmlDecodingException {
        FixmlCodecUtil.getInstance().getCodec().unmarshall(fixml, this);
    }
    
    /**
     * Gets a custom tag value as a byte array.
     * @param tagNum tag number as a string
     * @return custom tag value or null if the tag is not set
     */
    public String getCustomTag(String tagNum) {
        if (tagNum == null || tagNum.isEmpty()) {
            throw new RuntimeException("Custom tag number cannot be null or empty.");
        }
        return customTags.get(Integer.valueOf(tagNum));
    }
    
    /**
     * Adds a custom tag to the message.
     * @param tagNum tag number
     * @param tagValue stringfied value of the tag value
     */
    public void addCustomTag(String tagNum, String tagValue) {
        if (tagNum == null || tagNum.isEmpty()) {
            throw new RuntimeException("Custom tag number cannot be null or empty.");
        }
        customTags.put(Integer.valueOf(tagNum), tagValue);
    }
    
    /**
     * Removes a tag from the custom tag map.
     * @param tagNum tag number to be removed
     */
    public void removeCustomTag(String tagNum) {
        if (tagNum == null || tagNum.isEmpty()) {
            throw new RuntimeException("Custom tag number cannot be null or empty.");
        }
        customTags.remove(Integer.valueOf(tagNum));
    }

    /**
     * Remove all the custom tags from the map.
     */
    public void clearCustomTags() {
        customTags.clear();
    }

    /**
     * Returns true if this message body has been decoded.
     * @return true if body decoded, otherwise false
     */
    public boolean isDecoded() {
        return decoded;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Used by the engine to order the incoming messages in the priority queue
     * @return 
     */
    @Override
    public long getOrderSequence() {
	return orderSequence;
    }

    @Override
    public void setOrderSequence(long order) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getRawMessage() {
        return message.array();
    }


    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in release [" + getHeader().getBeginString().getValue() + "].";
    }

    protected String getNotPresentTagMessage(Tag tag) {
        StringBuilder msg = new StringBuilder("Tag value [");
        msg.append(tag.tagNum).append("] not present in [" );
        String msgTypeStr = header.getMsgType();
        MsgType msgType = MsgType.valueFor(header.getMsgType());
        if (msgType != null) {
            msgTypeStr = msgType.toString();
        }
        msg.append(msgTypeStr)
            .append("] transport version [")
            .append(header.getBeginString().getValue())
            .append( "]");
        try {
            if (header.getApplVerID() != null) {
                msg.append(" appl version [").append(header.getApplVerID().getValue()).append("]");
            }
        } catch (UnsupportedOperationException e) {
            // cannot call this on this message version - OK
        }
        msg.append(".");
        
        return msg.toString();
    }
    
    private byte[] encodeVersion() throws BadFormatMsgException {
        
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            bao.write(TagNum.BeginString.asBytes());
            bao.write("=".getBytes(sessionCharset));
            bao.write(getHeader().getBeginString().getValue().getBytes(sessionCharset));
            bao.write(SOH);
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }
        
        return result;
    }
    
    private byte[] encodeBodyLength(int bodyLength) throws BadFormatMsgException {
        
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            bao.write(TagNum.BodyLength.asBytes());
            bao.write("=".getBytes(sessionCharset));
            bao.write(String.valueOf(bodyLength).getBytes(sessionCharset));
            bao.write(SOH);
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }
        
        return result;
    }
    
    private byte[] encodeSecuredData(byte[] encryptedData) throws BadFormatMsgException {
        
        byte[] result = new byte[0];
        if (encryptedData != null && encryptedData.length > 0) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            try {
                bao.write(TagNum.SecureDataLen.asBytes());
                bao.write("=".getBytes(sessionCharset));
                bao.write(String.valueOf(encryptedData.length).getBytes(sessionCharset));
                bao.write(SOH);
                bao.write(TagNum.SecureData.asBytes());
                bao.write("=".getBytes(sessionCharset));
                bao.write(encryptedData);
                bao.write(SOH);
                result = bao.toByteArray();
            } catch (IOException ex) {
                String error = "Error writing to the byte array.";
                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(error, ex);
            }
        }
        
        return result;
    }

    private void setHeader(String msgType, BeginString beginString) throws InvalidMsgException {
        FragmentContext context = new FragmentContext(sessionCharset, sessionCharset, encryptionRequired, crypter);
        header = MsgUtil.getHeader(msgType, beginString, context, useNonStdBeginString);
    }
    
    private void setHeader(String msgType, BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {

        FragmentContext context = new FragmentContext(sessionCharset, sessionCharset, encryptionRequired, crypter);
        switch (beginString) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
            case FIX_4_3:
            case FIX_4_4:
                header = MsgUtil.getHeader(msgType, beginString, context, useNonStdBeginString);
                break;
                
            case FIXT_1_1:
                header = MsgUtil.getFIXTHeader(msgType, applVerID, context, useNonStdBeginString);
                break;
                
            default:
                throw new InvalidMsgException("FIX messages with version [" + beginString.getValue() +
                        "] are not yet supported.");
        }
    }

    private void setTrailer(BeginString beginString) throws InvalidMsgException {
        trailer = new Trailer(this);
    }
    
    private byte[] encodeCustom() throws BadFormatMsgException {
        
        byte[] result = new byte[0];
        if (customTags != null && customTags.size() > 0) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            try {
                for(Integer tagNum : customTags.keySet()) {
                    if (tagNum != null && customTags.get(tagNum) != null) {
                        bao.write(tagNum.toString().getBytes(sessionCharset));
                        bao.write("=".getBytes(sessionCharset));
                        bao.write(customTags.get(tagNum).getBytes(sessionCharset));
                        bao.write(SOH);
                    }
                }
                result = bao.toByteArray();
            } catch (IOException ex) {
                String error = "Error writing to the byte array.";
                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(error, ex);
            }
        }
        
        return result;
    }
    
    private void setSessionData() {
        SessionContext context = ThreadData.getSessionContext();
        if (context == null) {
            LOGGER.warning("Session context data has not been set by the current FIX session.");
            sessionCharset = Charset.forName(DEFAULT_CHARACTER_SET);
            validateRequired = false;
        } else {
            if (!(this instanceof LogonMsg)) {
                Object crypterObj = context.getValue(SessionContextKey.CRYPTER);
                if (crypterObj == null) {
                    LOGGER.finest("Session encryption not set.");
                } else {
                    encryptionRequired = true;
                    crypter = (Crypter) crypterObj;
                }
            }
            if (context.getValue(SessionContextKey.SESSION_CHARACTER_SET) == null) {
                sessionCharset = Charset.forName(DEFAULT_CHARACTER_SET);
                LOGGER.finest("Session character set not set. Using default [" + DEFAULT_CHARACTER_SET + "].");
            } else {
                sessionCharset = (Charset) context.getValue(SessionContextKey.SESSION_CHARACTER_SET);
            }
            if (context.getValue(SessionContextKey.VALIDATE_REQUIRED) == null) {
                validateRequired = false;
            } else {
                validateRequired = ((Boolean) context.getValue(SessionContextKey.VALIDATE_REQUIRED));
            }
            LOGGER.log(Level.FINEST, "Session FIX required tag validation set to {0}.", validateRequired);
            if (context.getValue(SessionContextKey.USE_NON_STD_BEGIN_STRING) != null) {
                useNonStdBeginString = ((Boolean) context.getValue(SessionContextKey.USE_NON_STD_BEGIN_STRING));
            }
        }
    }
    
    private byte[] encryptSecureData(byte[] dataToSecure) throws UnsupportedCrypterException {
        
        byte[] result = null;
        if (crypter == null) {
            String error = "No crypter setup in session context. Should not attempt to encrypt this message.";
            LOGGER.severe(error);
            throw new UnsupportedCrypterException(error);
        } else {
            result = crypter.encrypt(dataToSecure);
        }

        return result;
    }

}
