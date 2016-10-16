/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListRequestMsg.java
 *
 * $Id: DerivativeSecurityListRequestMsg.java,v 1.2 2011-09-27 08:57:26 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.DerivativeInstrument;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityListRequestType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Derivative Security List Request message is used to return a list of securities from the counterparty that
 * match criteria provided on the request.
 * Subscription for security status can be optionally specified by including the {@ling TagNum#SubscriptionRequestType} [263]
 * field on the message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeSecurityListRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityReqID.getValue(),
        TagNum.SecurityListRequestType.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.MarketSegmentID.getValue(),
        TagNum.SecuritySubType.getValue(),
        TagNum.Currency.getValue(),
        TagNum.Text.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.SubscriptionRequestType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityReqID.getValue(),
        TagNum.SecurityListRequestType.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 330 REQUIRED. Starting with 4.3 version.
     */
    protected String securityReqID;

    /**
     * TagNum = 321 REQUIRED. Starting with 4.3 version.
     */
    protected SecurityListRequestType securityListRequestType;

    /**
     * TagNum = 1301. Starting with 5.0SP1 version.
     */
    protected String marketID;

    /**
     * TagNum = 1300. Starting with 5.0SP1 version.
     */
    protected String marketSegmentID;

    /**
     * Starting with 4.3 version.
     */
    protected UnderlyingInstrument underlyingInstrument;

    /**
     * Starting with 5.0SP1 version.
     */
    protected DerivativeInstrument derivativeInstrument;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentExtension instrumentExtension;

    /**
     * TagNum = 762. Starting with 4.4 version.
     */
    protected String securitySubType;

    /**
     * TagNum = 15. Starting with 4.3 version.
     */
    protected Currency currency;

    /**
     * TagNum = 58. Starting with 4.3 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.3 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.3 version.
     */
    protected byte[] encodedText;

    /**
     * TagNum = 336. Starting with 4.3 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 263. Starting with 4.3 version.
     */
    protected SubscriptionRequestType subscriptionRequestType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityListRequestMsg() {
        super();
    }

    public DerivativeSecurityListRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public DerivativeSecurityListRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.DerivativeSecurityListRequest.getValue(), beginString);
    }

    public DerivativeSecurityListRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.DerivativeSecurityListRequest.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityReqID, required=true)
    public String getSecurityReqID() {
        return securityReqID;
    }

    /**
     * Message field setter.
     * @param securityReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityReqID, required=true)
    public void setSecurityReqID(String securityReqID) {
        this.securityReqID = securityReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityListRequestType, required=true)
    public SecurityListRequestType getSecurityListRequestType() {
        return securityListRequestType;
    }

    /**
     * Message field setter.
     * @param securityListRequestType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityListRequestType, required=true)
    public void setSecurityListRequestType(SecurityListRequestType securityListRequestType) {
        this.securityListRequestType = securityListRequestType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public String getMarketID() {
        return marketID;
    }

    /**
     * Message field setter.
     * @param marketID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    /**
     * Message field setter.
     * @param marketSegmentID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public UnderlyingInstrument getUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void setUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field clear.
     */
    @FIXVersion(introduced="4.3")
    public void clearUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeInstrument getDerivativeInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setDerivativeInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field clear.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearDerivativeInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySubType)
    public String getSecuritySubType() {
        return securitySubType;
    }

    /**
     * Message field setter.
     * @param securitySubType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySubType)
    public void setSecuritySubType(String securitySubType) {
        this.securitySubType = securitySubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType)
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    /**
     * Message field setter.
     * @param subscriptionRequestType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType)
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (securityReqID == null || securityReqID.trim().isEmpty()) {
            errorMsg.append(" [SecurityReqID]");
            hasMissingTag = true;
        }
        if (securityListRequestType == null) {
            errorMsg.append(" [SecurityListRequestType]");
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
            TagEncoder.encode(bao, TagNum.SecurityReqID, securityReqID);
            if (securityListRequestType != null) {
                TagEncoder.encode(bao, TagNum.SecurityListRequestType, securityListRequestType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            if (derivativeInstrument != null) {
                bao.write(derivativeInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.SecuritySubType, securitySubType);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (subscriptionRequestType != null) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
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
            case SecurityReqID:
                securityReqID = new String(tag.value, sessionCharset);
                break;

            case SecurityListRequestType:
                securityListRequestType = SecurityListRequestType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MarketID:
                marketID = new String(tag.value, sessionCharset);
                break;

            case MarketSegmentID:
                marketSegmentID = new String(tag.value, sessionCharset);
                break;

            case SecuritySubType:
                securitySubType = new String(tag.value, sessionCharset);
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case SubscriptionRequestType:
                subscriptionRequestType = SubscriptionRequestType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeSecurityListRequestMsg] fields.";
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
        StringBuilder b = new StringBuilder("{DerivativeSecurityListRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.SecurityReqID, securityReqID);
        printTagValue(b, TagNum.SecurityListRequestType, securityListRequestType);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, derivativeInstrument);
        printTagValue(b, underlyingInstrument);
        printTagValue(b, TagNum.SecuritySubType, securitySubType);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.SubscriptionRequestType, subscriptionRequestType);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
