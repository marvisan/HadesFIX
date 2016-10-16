/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MsgUtil.java
 *
 * $Id: MsgUtil.java,v 1.15 2011-03-07 09:08:09 vrotaru Exp $
 */
package net.hades.fix.message.util;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.crypt.Crypter;
import net.hades.fix.message.impl.v50.HeaderT11_50;
import net.hades.fix.message.impl.v50sp2.HeaderT11_50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.UnsupportedCrypterException;
import net.hades.fix.message.impl.v40.Header40;
import net.hades.fix.message.impl.v41.Header41;
import net.hades.fix.message.impl.v42.Header42;
import net.hades.fix.message.impl.v43.Header43;
import net.hades.fix.message.impl.v44.Header44;
import net.hades.fix.message.impl.v50sp1.HeaderT11_50SP1;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;

/**
 * Utility class used to manipulate FIX messages.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.15 $
 */
public class MsgUtil {

    private static final Logger LOGGER = Logger.getLogger(MsgUtil.class.getName());

    /**
     * Calculates the FIX message checksum for the given message
     * body as a String.
     * @param msgBodyStr FIX message body
     * @return checksum
     * @throws BadFormatMsgException bad format message
     */
    public static int calculateChecksum(String msgBodyStr) throws BadFormatMsgException {
        byte[] msgBody;
        try {
            msgBody = msgBodyStr.getBytes(FIXMsg.DEFAULT_CHARACTER_SET);
        } catch (UnsupportedEncodingException ex) {
            String error = "Could not encode given byte buffer as UTF-8.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error);
        }
        return calculateChecksum(msgBody);
    }
    
    /**
     * Calculates the FIX message checksum for the given message
     * body as a char array.
     * @param body FIX message body
     * @return checksum
     */
    public static int calculateChecksum(byte[] body) {
        int sum = 0;
        for (byte b : body) {
            sum += (int) b;
        }

        return sum % 256;
    }
  
    /**
     * Gets the next tag. Assumes that the  buffer position is located at the beginning of the
     * tag to retrieve. The given FIX message buffer pointer is advanced to the tag value.
     * @param message IX message wrapped in a ByteBuffer
     * @return next tag
     */
    public static int getNextTagNum(ByteBuffer message) {
        StringBuilder builder = new StringBuilder(10);
        int b;
        message.mark();
        while (message.hasRemaining()) {
            if ((b = message.get()) == ((int) '=')) {
                break;
            } else {
                builder.append((char) b);
            }
        }
        
        return Integer.parseInt(builder.toString());
    }
    
    /**
     * Gets the next tag number/value pair in a Tag structure object.
     * Assumes that the buffer position is lcacted at the begining of the
     * tag to retrieve. The given FIX message buffer pointer is advanced to the next 
     * tag.
     * @param message FIX message wrapped in a ByteBuffer
     * @return tag number/value pair
     * @throws BadFormatMsgException received message is invalid
     */
    public static Tag getNextTag(ByteBuffer message) throws BadFormatMsgException {
        byte b;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        while (message.hasRemaining()) {
            if ((b = message.get()) == 1) {
                break;
            } else {
                bao.write(b);
            }
        }
        byte[] tagValue = bao.toByteArray();
        bao.reset();
        boolean found = false;
        int equalsIndx = 0;
        for (int i = 0; i < tagValue.length; i++) {
            if (tagValue[i] == '=') {
                found = true;
                equalsIndx = i;
                break;
            } else {
                bao.write(tagValue[i]);
            }
        }
        String tagNumStr;
        try {
            tagNumStr = bao.toString(FIXMsg.DEFAULT_CHARACTER_SET);
        } catch (UnsupportedEncodingException ex) {
            String error = "Could not encode given byte buffer as ISO-8859-1.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }
        bao.reset();
        for (int i = equalsIndx + 1; i < tagValue.length; i++) {
            bao.write(tagValue[i]);
        }
        if (!found || tagNumStr == null || tagNumStr.isEmpty()) {
            String error = "Wrong message tag format received or bad index position.";
            LOGGER.severe(error);
            throw new BadFormatMsgException(error);
        }

        return new Tag(Integer.parseInt(tagNumStr), bao.toByteArray());
    }
    
    /**
     * Gets the next tag number/value pair in a Tag structure object for a data
     * field of the given field.<br>
     * Assumes that the buffer position is loacted at the begining of the
     * tag to retrieve. The given FIX message buffer pointer is advanced to the next 
     * tag.
     * @param message FIX message wrapped in a ByteBuffer
     * @param length length of the binary data field
     * @return tag number/value pair
     * @throws BadFormatMsgException received message is invalid
     */
    public static Tag getNextTag(ByteBuffer message, int length) throws BadFormatMsgException {
        
        ByteBuffer buffer = ByteBuffer.allocate(length);
        int tagNum = getNextTagNum(message);
        for (int i = 0; i < length; i++) {
            buffer.put(message.get());
        }
        // eat the ending SOH
        int last = message.get();
        if (last != 1) {
            String error = "Last byte in the data field it is not a SOH character.";
            LOGGER.severe(error);
            throw new BadFormatMsgException(error);
        }

        return new Tag(tagNum, buffer.array());
    }

    /**
     * Insert a static byte array at the current position of the given message.
     * @param source to be inserted
     * @param target buffer to insert in
     * @return new buffer with the position set at the current position
     */
    public static ByteBuffer insertByteArray(byte[] source, ByteBuffer target) {
        
        int current = target.position();
        target.rewind();
        byte[] first = new byte[current];
        byte[] last = new byte[target.capacity() - current];
        target.get(first, 0, first.length);
        target.get(last, 0, last.length);
        byte[] all = new byte[first.length + source.length + last.length];
        System.arraycopy(first, 0, all, 0, first.length);
        System.arraycopy(source, 0, all, first.length, source.length);
        System.arraycopy(last, 0, all, first.length + source.length, last.length);
        target = ByteBuffer.wrap(all);
        target.position(current);
        
        return target;
    }
    
    /**
     * This method checks if the given tag is part of the given list.
     * @param tagNum tag to compare with
     * @param list list to search for
     * @return true if the tag is part of the list, false otherwise
     */
    public static boolean isTagInList(int tagNum, Set<Integer> list) {
        
        boolean result = false;
        if (list != null && list.size() > 0) {
            result = list.contains(tagNum);
        }
        
        return result;
    }

    /**
     * This method checks if the given tag is part of the given list.
     * @param tagNum tag to compare with
     * @param list list to search for
     * @param secured flag indicating the the result will not be negated
     * @return true if the tag is part of the list, false otherwise
     */
    public static boolean isTagInList(int tagNum, Set<Integer> list, boolean secured) {

        boolean result = false;
        if (list != null && list.size() > 0) {
            result = list.contains(tagNum);
        }

        return secured ? result : !result;
    }

    /**
     * This method checks if the given tag is part of the given list.
     * @param tagNum tag to compare with
     * @param list list to search for
     * @param secured flag indicating the the result will not be negated
     * @return true if the tag is part of the list, false otherwise
     */
    public static boolean isTagInList(TagNum tagNum, Set<Integer> list, boolean secured) {

        boolean result = false;
        if (list != null && list.size() > 0) {
            result = list.contains(tagNum.getValue());
        }

        return secured ? result : !result;
    }
    
    /**
     * Decrypts the given data with the crypter.
     * @param secureData data to be decrypted
     * @param crypter crypter used for decryption
     * @return decrypted data
     * @throws net.hades.fix.message.exception.UnsupportedCrypterException error in using the crypter
     */
    public static byte[] decryptSecureData(byte[] secureData, Crypter crypter) throws UnsupportedCrypterException {
        
        byte[] result;
        
        if (crypter == null) {
            String error = "No crypter setup in session context. Should not attempt to decrypt this message.";
            LOGGER.severe(error);
            throw new UnsupportedCrypterException(error);
        } else {
            result = crypter.decrypt(secureData);
        }
        
        return result;
    }

    /**
     * Returns the message application version ID.
     * @param beginString header begin version
     * @param applVerID application version present in message or session
     * @return application version ID
     * @throws InvalidMsgException invalid FIX message
     */
    public static ApplVerID getMsgFixVersion(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        ApplVerID result;
        if (applVerID != null) {
            result = applVerID;
        } else if (beginString != null && !beginString.equals(BeginString.FIXT_1_1)) {
            result = getApplVerFromBeginString(beginString);
        } else {
            String error = "Unable to determine application version for BeginString [" +
                (beginString == null ? null : beginString.getValue()) + "] and version [null].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }

        return result;
    }

    /**
     * Gets the ApplVerID for a given BeginString value.
     * @param beginString BeginString value
     * @return ApplVerID corresponding to the given parameter value
     * @throws InvalidMsgException error if the BeginString value is not valid
     */
    public static ApplVerID getApplVerFromBeginString(BeginString beginString) throws InvalidMsgException {
        ApplVerID result;
        switch (beginString) {
            case FIX_4_0:
                result = ApplVerID.FIX40;
                break;

            case FIX_4_1:
                result = ApplVerID.FIX41;
                break;

            case FIX_4_2:
                result = ApplVerID.FIX42;
                break;

            case FIX_4_3:
                result = ApplVerID.FIX43;
                break;

            case FIX_4_4:
                result = ApplVerID.FIX44;
                break;

            case FIX_5_0:
                result = ApplVerID.FIX50;
                break;

            case FIX_5_0SP1:
                result = ApplVerID.FIX50SP1;
                break;

            case FIX_5_0SP2:
                result = ApplVerID.FIX50SP2;
                break;

            default:
                String error = "Unable to determine application version for BeginString [" +
                    (beginString == null ? null : beginString.getValue()) + "] or version not supported";
                LOGGER.severe(error);
                throw new InvalidMsgException(error);
        }

        return result;
    }

    /**
     * Build the BeginString field based on the given string that can be a valid FIX string
     * or just an abbreviation (e.g. 4.3). If not recognized is set to FIXT.1.1
     * 
     * @param beginString string value
     * @return enum value
     */
    public static BeginString getBeginStringFromString(String beginString) {
        if (beginString == null || beginString.trim().isEmpty()) {
            throw new IllegalArgumentException("BeginString parameter cannot be null or empty.");
        }
        BeginString result;
        result = BeginString.valueFor(beginString);
        if (result == null) {
            if (beginString.equals("4.0")) {
                result = BeginString.FIX_4_0;
            } else if (beginString.equals("4.1")) {
                result = BeginString.FIX_4_1;
            } else if (beginString.equals("4.2")) {
                result = BeginString.FIX_4_2;
            } else if (beginString.equals("4.3")) {
                result = BeginString.FIX_4_3;
            } else if (beginString.equals("4.4")) {
                result = BeginString.FIX_4_4;
            } else if (beginString.equals("T1.1")) {
                result = BeginString.FIXT_1_1;
            } else if (beginString.equals("5.0")) {
                result = BeginString.FIX_5_0;
            } else if (beginString.equals("5.0SP1")) {
                result = BeginString.FIX_5_0SP1;
            } else if (beginString.equals("5.0SP2")) {
                result = BeginString.FIX_5_0SP2;
            } else {
                result = BeginString.FIXT_1_1;
            }
        }

        return result;
    }

    /**
     * Compare to BeginString values. The method return 0 if the version are equals, 
     * -1 if the comp version is lower than the version to compare to or +1 otherwise.
     * @param comp verion to compare
     * @param compTo version to compare to
     * @return -1,0 or 1
     */
    public static int compare(BeginString comp, BeginString compTo) {
        int result;
        BeginString[] values = BeginString.values();
        int idxCompTo = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(compTo)) {
                idxCompTo = i;
                break;
            }
        }
        int idxComp = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(comp)) {
                idxComp = i;
                break;
            }
        }
        if (idxCompTo == idxComp) {
            result = 0;
        } else if (idxCompTo < idxComp) {
            result = 1;
        } else {
            result = -1;
        }

        return result;
    }

    /**
     * Compare to ApplVerID values. The method return 0 if the version are equals,
     * -1 if the comp version is lower than the version to compare to or +1 otherwise.
     * @param comp verion to compare
     * @param compTo version to compare to
     * @return -1,0 or 1
     */
    public static int compare(ApplVerID comp, ApplVerID compTo) {
        int result;
        ApplVerID[] values = ApplVerID.values();
        int idxCompTo = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(compTo)) {
                idxCompTo = i;
                break;
            }
        }
        int idxComp = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(comp)) {
                idxComp = i;
                break;
            }
        }
        if (idxCompTo == idxComp) {
            result = 0;
        } else if (idxCompTo < idxComp) {
            result = 1;
        } else {
            result = -1;
        }

        return result;
    }


    public static Header getHeader(String msgType, BeginString beginString, FragmentContext context, boolean useNonStdBeginString)
            throws InvalidMsgException {

        Header header;
        switch (beginString) {
            case FIX_4_0:
                header = new Header40(msgType, context);
                break;

            case FIX_4_1:
                header = new Header41(msgType, context);
                break;

            case FIX_4_2:
                header = new Header42(msgType, context);
                break;

            case FIX_4_3:
                header = new Header43(msgType, context);
                break;

            case FIX_4_4:
                header = new Header44(msgType, context);
                break;

           case FIX_5_0:
                header = getFIXTHeader(msgType, ApplVerID.FIX50, context, useNonStdBeginString);
                break;

            case FIX_5_0SP1:
                header = getFIXTHeader(msgType, ApplVerID.FIX50SP1, context, useNonStdBeginString);
                break;

            case FIX_5_0SP2:
                header = getFIXTHeader(msgType, ApplVerID.FIX50SP2, context, useNonStdBeginString);
                break;

            default:
                throw new InvalidMsgException("FIX messages with version [" + beginString.getValue() +
                        "] are not yet supported.");
        }

        return header;
    }

    public static Header getFIXTHeader(String msgType, ApplVerID applVerID, FragmentContext context, boolean useNonStdBeginString)
            throws InvalidMsgException {

        Header header;
        switch (applVerID) {
            case FIX40:
                header = new Header40(msgType, context);
                break;

            case FIX41:
                header = new Header41(msgType, context);
                break;

            case FIX42:
                header = new Header42(msgType, context);
                break;

            case FIX43:
                header = new Header43(msgType, context);
                break;

            case FIX44:
                header = new Header44(msgType, context);
                break;

            case FIX50:
                header = new HeaderT11_50(msgType, context);
                if (useNonStdBeginString) {
                    header.setBeginString(BeginString.FIX_5_0);
                }
                break;

            case FIX50SP1:
                header = new HeaderT11_50SP1(msgType, context);
                if (useNonStdBeginString) {
                    header.setBeginString(BeginString.FIX_5_0SP1);
                }
                break;

            case FIX50SP2:
                header = new HeaderT11_50SP2(msgType, context);
                if (useNonStdBeginString) {
                    header.setBeginString(BeginString.FIX_5_0SP2);
                }
                break;

            default:
                throw new InvalidMsgException("FIX messages with version [" + applVerID.getValue() +
                        "] are not yet supported.");
        }

        return header;
    }

    public static Header getFIXTHeader(ApplVerID applVerID, FragmentContext context, boolean useNonStdBeginString)
            throws InvalidMsgException {

        Header header;
        switch (applVerID) {
            case FIX40:
                header = new Header40(context);
                break;

            case FIX41:
                header = new Header41(context);
                break;

            case FIX42:
                header = new Header42(context);
                break;

            case FIX43:
                header = new Header43(context);
                break;

            case FIX44:
                header = new Header44(context);
                break;

            case FIX50:
                header = new HeaderT11_50(context);
                if (useNonStdBeginString) {
                    header.setBeginString(BeginString.FIX_5_0);
                }
                break;

            case FIX50SP1:
                header = new HeaderT11_50SP1(context);
                if (useNonStdBeginString) {
                    header.setBeginString(BeginString.FIX_5_0SP1);
                }
                break;

            case FIX50SP2:
                header = new HeaderT11_50SP2(context);
                if (useNonStdBeginString) {
                    header.setBeginString(BeginString.FIX_5_0SP2);
                }
                break;

            default:
                throw new InvalidMsgException("FIX messages with version [" + applVerID.getValue() +
                        "] are not yet supported.");
        }

        return header;
    }

    public static String getPrintableRawFixMessage(byte[] rawMessage) {
        byte[] copyMessage = new byte[rawMessage.length];
        System.arraycopy(rawMessage, 0, copyMessage, 0, rawMessage.length);
        for (int i = 0; i < copyMessage.length; i++) {
            int byteValue = (short) copyMessage[i];
            if (byteValue < 32) {
                if (byteValue == 1) {
                    copyMessage[i] = (byte) 174;
                } else {
                    copyMessage[i] = (byte) 191;
                }
            } else if (byteValue > 126) {
                copyMessage[i] = (byte) 191;
            }
        }

        return new String(copyMessage, Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET));
    }
}
