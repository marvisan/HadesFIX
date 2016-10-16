/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusMsg.java
 *
 * $Id: TradingSessionStatusMsg.java,v 1.2 2011-04-28 10:07:44 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TradSesMethod;
import net.hades.fix.message.util.BooleanConverter;
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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradSesEvent;
import net.hades.fix.message.type.TradSesMode;
import net.hades.fix.message.type.TradSesStatus;
import net.hades.fix.message.type.TradSesStatusRejReason;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Trading Session Status provides information on the status of a market. With the move to multiple sessions
 * occurring for a given trading party (morning and evening sessions for instance) there is a need to be able to
 * provide information on what product is trading on what market.<br/>
 * The Trading Session Status can provide an optional repeating group of securities that are available for trading
 * during that session.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TradingSessionStatusMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradSesReqID.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.MarketSegmentID.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.TradSesMethod.getValue(),
        TagNum.TradSesMode.getValue(),
        TagNum.UnsolicitedIndicator.getValue(),
        TagNum.TradSesStatus.getValue(),
        TagNum.TradSesEvent.getValue(),
        TagNum.TradSesStatusRejReason.getValue(),
        TagNum.TradSesStartTime.getValue(),
        TagNum.TradSesOpenTime.getValue(),
        TagNum.TradSesPreCloseTime.getValue(),
        TagNum.TradSesCloseTime.getValue(),
        TagNum.TradSesEndTime.getValue(),
        TagNum.TotalVolumeTraded.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradingSessionID.getValue(),
        TagNum.TradSesStatus.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 5.0SP1 version.
     */
    protected ApplicationSequenceControl applicationSequenceControl;

    /**
     * TagNum = 335. Starting with 4.2 version.
     */
    protected String tradSesReqID;

    /**
     * TagNum = 1301. Starting with 5.0SP1 version.
     */
    protected String marketID;

    /**
     * TagNum = 1300. Starting with 5.0SP1 version.
     */
    protected String marketSegmentID;

    /**
     * TagNum = 336 REQUIRED. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 338. Starting with 4.2 version.
     */
    protected TradSesMethod tradSesMethod;

    /**
     * TagNum = 339. Starting with 4.2 version.
     */
    protected TradSesMode tradSesMode;

    /**
     * TagNum = 325. Starting with 4.2 version.
     */
    protected Boolean unsolicitedIndicator;

    /**
     * TagNum = 340 REQUIRED. Starting with 4.2 version.
     */
    protected TradSesStatus tradSesStatus;

    /**
     * TagNum = 1368. Starting with 5.0SP1 version.
     */
    protected TradSesEvent tradSesEvent;

    /**
     * TagNum = 567. Starting with 4.3 version.
     */
    protected TradSesStatusRejReason tradSesStatusRejReason;

    /**
     * TagNum = 341. Starting with 4.2 version.
     */
    protected Date tradSesStartTime;

    /**
     * TagNum = 342. Starting with 4.2 version.
     */
    protected Date tradSesOpenTime;

    /**
     * TagNum = 343. Starting with 4.2 version.
     */
    protected Date tradSesPreCloseTime;

    /**
     * TagNum = 344. Starting with 4.2 version.
     */
    protected Date tradSesCloseTime;

    /**
     * TagNum = 345. Starting with 4.2 version.
     */
    protected Date tradSesEndTime;

    /**
     * TagNum = 387. Starting with 4.2 version.
     */
    protected Double totalVolumeTraded;

    /**
     * TagNum = 58. Starting with 4.2 version.
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

    /**
     * Starting with 5.0 version.
     */
    protected Instrument instrument;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionStatusMsg() {
        super();
    }

    public TradingSessionStatusMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public TradingSessionStatusMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.TradingSessionStatus.getValue(), beginString);
    }

    public TradingSessionStatusMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.TradingSessionStatus.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="5.0SP1")
    public ApplicationSequenceControl getApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ApplicationSequenceControl component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Clear the ApplicationSequenceControl component.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesReqID)
    public String getTradSesReqID() {
        return tradSesReqID;
    }

    /**
     * Message field setter.
     * @param tradSesReqID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesReqID)
    public void setTradSesReqID(String tradSesReqID) {
        this.tradSesReqID = tradSesReqID;
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradingSessionID, required=true)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradingSessionID, required=true)
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesMethod)
    public TradSesMethod getTradSesMethod() {
        return tradSesMethod;
    }

    /**
     * Message field setter.
     * @param tradSesMethod field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesMethod)
    public void setTradSesMethod(TradSesMethod tradSesMethod) {
        this.tradSesMethod = tradSesMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesMode)
    public TradSesMode getTradSesMode() {
        return tradSesMode;
    }

    /**
     * Message field setter.
     * @param tradSesMode field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesMode)
    public void setTradSesMode(TradSesMode tradSesMode) {
        this.tradSesMode = tradSesMode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.UnsolicitedIndicator)
    public Boolean getUnsolicitedIndicator() {
        return unsolicitedIndicator;
    }

    /**
     * Message field setter.
     * @param unsolicitedIndicator field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.UnsolicitedIndicator)
    public void setUnsolicitedIndicator(Boolean unsolicitedIndicator) {
        this.unsolicitedIndicator = unsolicitedIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesStatus, required=true)
    public TradSesStatus getTradSesStatus() {
        return tradSesStatus;
    }

    /**
     * Message field setter.
     * @param tradSesStatus field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesStatus, required=true)
    public void setTradSesStatus(TradSesStatus tradSesStatus) {
        this.tradSesStatus = tradSesStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradSesEvent)
    public TradSesEvent getTradSesEvent() {
        return tradSesEvent;
    }

    /**
     * Message field setter.
     * @param tradSesEvent field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TradSesEvent)
    public void setTradSesEvent(TradSesEvent tradSesEvent) {
        this.tradSesEvent = tradSesEvent;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradSesStatusRejReason)
    public TradSesStatusRejReason getTradSesStatusRejReason() {
        return tradSesStatusRejReason;
    }

    /**
     * Message field setter.
     * @param tradSesStatusRejReason field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradSesStatusRejReason)
    public void setTradSesStatusRejReason(TradSesStatusRejReason tradSesStatusRejReason) {
        this.tradSesStatusRejReason = tradSesStatusRejReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesStartTime)
    public Date getTradSesStartTime() {
        return tradSesStartTime;
    }

    /**
     * Message field setter.
     * @param tradSesStartTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesStartTime)
    public void setTradSesStartTime(Date tradSesStartTime) {
        this.tradSesStartTime = tradSesStartTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesOpenTime)
    public Date getTradSesOpenTime() {
        return tradSesOpenTime;
    }

    /**
     * Message field setter.
     * @param tradSesOpenTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesOpenTime)
    public void setTradSesOpenTime(Date tradSesOpenTime) {
        this.tradSesOpenTime = tradSesOpenTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesPreCloseTime)
    public Date getTradSesPreCloseTime() {
        return tradSesPreCloseTime;
    }

    /**
     * Message field setter.
     * @param tradSesPreCloseTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesPreCloseTime)
    public void setTradSesPreCloseTime(Date tradSesPreCloseTime) {
        this.tradSesPreCloseTime = tradSesPreCloseTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesCloseTime)
    public Date getTradSesCloseTime() {
        return tradSesCloseTime;
    }

    /**
     * Message field setter.
     * @param tradSesCloseTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesCloseTime)
    public void setTradSesCloseTime(Date tradSesCloseTime) {
        this.tradSesCloseTime = tradSesCloseTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesEndTime)
    public Date getTradSesEndTime() {
        return tradSesEndTime;
    }

    /**
     * Message field setter.
     * @param tradSesEndTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradSesEndTime)
    public void setTradSesEndTime(Date tradSesEndTime) {
        this.tradSesEndTime = tradSesEndTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotalVolumeTraded)
    public Double getTotalVolumeTraded() {
        return totalVolumeTraded;
    }

    /**
     * Message field setter.
     * @param totalVolumeTraded field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotalVolumeTraded)
    public void setTotalVolumeTraded(Double totalVolumeTraded) {
        this.totalVolumeTraded = totalVolumeTraded;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.2")
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="5.0")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="5.0")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (tradingSessionID == null || tradingSessionID.trim().isEmpty()) {
            errorMsg.append(" [TradingSessionID]");
            hasMissingTag = true;
        }
        if (tradSesStatus == null) {
            errorMsg.append(" [TradSesStatus]");
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
            if (applicationSequenceControl != null) {
                bao.write(applicationSequenceControl.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.TradSesReqID, tradSesReqID);
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (tradSesMethod != null) {
                TagEncoder.encode(bao, TagNum.TradSesMethod, tradSesMethod.getValue());
            }
            if (tradSesMode != null) {
                TagEncoder.encode(bao, TagNum.TradSesMode, tradSesMode.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
            if (tradSesStatus != null) {
                TagEncoder.encode(bao, TagNum.TradSesStatus, tradSesStatus.getValue());
            }
            if (tradSesEvent != null) {
                TagEncoder.encode(bao, TagNum.TradSesEvent, tradSesEvent.getValue());
            }
            if (tradSesStatusRejReason != null) {
                TagEncoder.encode(bao, TagNum.TradSesStatusRejReason, tradSesStatusRejReason.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesStartTime, tradSesStartTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesOpenTime, tradSesOpenTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesPreCloseTime, tradSesPreCloseTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesCloseTime, tradSesCloseTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesEndTime, tradSesEndTime);
            TagEncoder.encode(bao, TagNum.TotalVolumeTraded, totalVolumeTraded);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
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
            case TradSesReqID:
                tradSesReqID = new String(tag.value, sessionCharset);
                break;

            case MarketID:
                marketID = new String(tag.value, sessionCharset);
                break;

            case MarketSegmentID:
                marketSegmentID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case TradSesMethod:
                tradSesMethod = TradSesMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradSesMode:
                tradSesMode = TradSesMode.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnsolicitedIndicator:
                unsolicitedIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case TradSesStatus:
                tradSesStatus = TradSesStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradSesEvent:
                tradSesEvent = TradSesEvent.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradSesStatusRejReason:
                tradSesStatusRejReason = TradSesStatusRejReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradSesStartTime:
                tradSesStartTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradSesOpenTime:
                tradSesOpenTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradSesPreCloseTime:
                tradSesPreCloseTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradSesCloseTime:
                tradSesCloseTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradSesEndTime:
                tradSesEndTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TotalVolumeTraded:
                totalVolumeTraded = new Double(new String(tag.value, getSessionCharset()));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TradingSessionStatusMsg] fields.";
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

    private Instrument getSafeInstrument() {
        if (getInstrument() == null) {
            setInstrument();
        }

        return getInstrument();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{TradingSessionStatusMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.TradSesReqID, tradSesReqID);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.TradSesMethod, tradSesMethod);
        printTagValue(b, TagNum.TradSesMode, tradSesMode);
        printTagValue(b, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
        printTagValue(b, TagNum.TradSesStatus, tradSesStatus);
        printTagValue(b, TagNum.TradSesEvent, tradSesEvent);
        printTagValue(b, TagNum.TradSesStatusRejReason, tradSesStatusRejReason);
        printUTCDateTimeTagValue(b, TagNum.TradSesStartTime, tradSesStartTime);
        printUTCDateTimeTagValue(b, TagNum.TradSesOpenTime, tradSesOpenTime);
        printUTCDateTimeTagValue(b, TagNum.TradSesPreCloseTime, tradSesPreCloseTime);
        printUTCDateTimeTagValue(b, TagNum.TradSesCloseTime, tradSesCloseTime);
        printUTCDateTimeTagValue(b, TagNum.TradSesEndTime, tradSesEndTime);
        printTagValue(b, TagNum.TotalVolumeTraded, totalVolumeTraded);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, instrument);        
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
