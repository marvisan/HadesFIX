/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListCancelRequestMsg.java
 *
 * $Id: ListCancelRequestMsg.java,v 1.2 2011-04-28 10:07:48 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The List Cancel Request message type is used by institutions wishing to cancel previously submitted lists either
 * before or during execution.<br/>
 * After the list has been staged with the broker, it can be canceled via the submission of the List Cancel message.
 * If the list has not yet been submitted for execution, the List Cancel message will instruct the broker not to
 * execute it, if the list is being executed, the List Cancel message should trigger the broker's system to generate
 * cancel requests for the remaining quantities of each order within the list. Individual orders within the list can
 * be canceled via the Order Cancel Request message.<br/>
 * The List Status message type is used by the recipient of the List Cancel Request to communicate the status of
 * the List Cancel Request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ListCancelRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.TradeOriginationDate.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.WaveNo.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.TransactTime.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 66 REQUIRED. Starting with 4.0 version.
     */
    protected String listID;

    /**
     * Starting with 5.0 version.
     */
    protected Parties parties;

    /**
     * TagNum = 60 REQUIRED. Starting with 4.2 version.
     */
    protected Date transactTime;

    /**
     * Starting with 4.3 version.
     */
    protected Date tradeOriginationDate;

    /**
     * TagNum = 75. Starting with 4.4 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 105. Starting with 4.0 version.
     */
    protected String waveNo;

    /**
     * TagNum = 58. Starting with 4.0 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.2 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.2 version.
     */
    protected byte[] encodedText;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ListCancelRequestMsg() {
        super();
    }

    public ListCancelRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ListCancelRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.ListCancelRequest.getValue(), beginString);
    }

    public ListCancelRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.ListCancelRequest.getValue(), beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListID, required = true)
    public String getListID() {
        return listID;
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListID, required = true)
    public void setListID(String listID) {
        this.listID = listID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime, required = true)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime, required = true)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeOriginationDate)
    public Date getTradeOriginationDate() {
        return tradeOriginationDate;
    }

    /**
     * Message field setter.
     * @param tradeOriginationDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeOriginationDate)
    public void setTradeOriginationDate(Date tradeOriginationDate) {
        this.tradeOriginationDate = tradeOriginationDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public String getWaveNo() {
        return waveNo;
    }

    /**
     * Message field setter.
     * @param waveNo field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (listID == null || listID.trim().isEmpty()) {
            errorMsg.append(" [ListID]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.ListID, listID);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encodeDate(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encode(bao, TagNum.WaveNo, waveNo);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }
    

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradeOriginationDate:
                tradeOriginationDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case WaveNo:
                waveNo = new String(tag.value, sessionCharset);
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ListCancelRequestMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{ListCancelRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, parties);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printDateTagValue(b, TagNum.TradeOriginationDate, tradeOriginationDate);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printTagValue(b, TagNum.WaveNo, waveNo);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
