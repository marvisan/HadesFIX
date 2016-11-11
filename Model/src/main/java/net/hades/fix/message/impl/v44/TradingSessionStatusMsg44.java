/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusMsg44.java
 *
 * $Id: TradingSessionStatusMsg44.java,v 1.1 2011-04-23 00:19:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.TradingSessionStatusMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradSesMethod;
import net.hades.fix.message.type.TradSesMode;
import net.hades.fix.message.type.TradSesStatus;
import net.hades.fix.message.type.TradSesStatusRejReason;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

/**
 * FIX version 4.4 TradingSessionStatusMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="TrdgSesStat")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TradingSessionStatusMsg44 extends TradingSessionStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionStatusMsg44() {
        super();
    }

    public TradingSessionStatusMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradingSessionStatusMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradingSessionStatusMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        TradingSessionStatusMsg44 fixml = (TradingSessionStatusMsg44) fragment;
        if (fixml.getTradSesReqID() != null) {
            tradSesReqID = fixml.getTradSesReqID();
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getTradSesMethod() != null) {
            tradSesMethod = fixml.getTradSesMethod();
        }
        if (fixml.getTradSesMode() != null) {
            tradSesMode = fixml.getTradSesMode();
        }
        if (fixml.getUnsolicitedIndicator() != null) {
            unsolicitedIndicator = fixml.getUnsolicitedIndicator();
        }
        if (fixml.getTradSesStatus() != null) {
            tradSesStatus = fixml.getTradSesStatus();
        }
        if (fixml.getTradSesStatusRejReason() != null) {
            tradSesStatusRejReason = fixml.getTradSesStatusRejReason();
        }
        if (fixml.getTradSesStartTime() != null) {
            tradSesStartTime = fixml.getTradSesStartTime();
        }
        if (fixml.getTradSesOpenTime() != null) {
            tradSesOpenTime = fixml.getTradSesOpenTime();
        }
        if (fixml.getTradSesPreCloseTime() != null) {
            tradSesPreCloseTime = fixml.getTradSesPreCloseTime();
        }
        if (fixml.getTradSesCloseTime() != null) {
            tradSesCloseTime = fixml.getTradSesCloseTime();
        }
        if (fixml.getTradSesEndTime() != null) {
            tradSesEndTime = fixml.getTradSesEndTime();
        }
        if (fixml.getTotalVolumeTraded() != null) {
            totalVolumeTraded = fixml.getTotalVolumeTraded();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "ReqID")
    @Override
    public String getTradSesReqID() {
        return tradSesReqID;
    }

    @Override
    public void setTradSesReqID(String tradSesReqID) {
        this.tradSesReqID = tradSesReqID;
    }

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @XmlAttribute(name = "Method")
    @Override
    public TradSesMethod getTradSesMethod() {
        return tradSesMethod;
    }

    @Override
    public void setTradSesMethod(TradSesMethod tradSesMethod) {
        this.tradSesMethod = tradSesMethod;
    }

    @XmlAttribute(name = "Mode")
    @Override
    public TradSesMode getTradSesMode() {
        return tradSesMode;
    }

    @Override
    public void setTradSesMode(TradSesMode tradSesMode) {
        this.tradSesMode = tradSesMode;
    }

    @XmlAttribute(name = "Unsol")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getUnsolicitedIndicator() {
        return unsolicitedIndicator;
    }

    @Override
    public void setUnsolicitedIndicator(Boolean unsolicitedIndicator) {
        this.unsolicitedIndicator = unsolicitedIndicator;
    }

    @XmlAttribute(name = "Stat")
    @Override
    public TradSesStatus getTradSesStatus() {
        return tradSesStatus;
    }

    @Override
    public void setTradSesStatus(TradSesStatus tradSesStatus) {
        this.tradSesStatus = tradSesStatus;
    }

    @XmlAttribute(name = "StatRejRsn")
    @Override
    public TradSesStatusRejReason getTradSesStatusRejReason() {
        return tradSesStatusRejReason;
    }

    @Override
    public void setTradSesStatusRejReason(TradSesStatusRejReason tradSesStatusRejReason) {
        this.tradSesStatusRejReason = tradSesStatusRejReason;
    }

    @XmlAttribute(name = "StartTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTradSesStartTime() {
        return tradSesStartTime;
    }

    @Override
    public void setTradSesStartTime(Date tradSesStartTime) {
        this.tradSesStartTime = tradSesStartTime;
    }

    @XmlAttribute(name = "OpenTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTradSesOpenTime() {
        return tradSesOpenTime;
    }

    @Override
    public void setTradSesOpenTime(Date tradSesOpenTime) {
        this.tradSesOpenTime = tradSesOpenTime;
    }

    @XmlAttribute(name = "PreClsTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTradSesPreCloseTime() {
        return tradSesPreCloseTime;
    }

    @Override
    public void setTradSesPreCloseTime(Date tradSesPreCloseTime) {
        this.tradSesPreCloseTime = tradSesPreCloseTime;
    }

    @XmlAttribute(name = "ClsTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTradSesCloseTime() {
        return tradSesCloseTime;
    }

    @Override
    public void setTradSesCloseTime(Date tradSesCloseTime) {
        this.tradSesCloseTime = tradSesCloseTime;
    }

    @XmlAttribute(name = "EndTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTradSesEndTime() {
        return tradSesEndTime;
    }

    @Override
    public void setTradSesEndTime(Date tradSesEndTime) {
        this.tradSesEndTime = tradSesEndTime;
    }

    @XmlAttribute(name = "TotVolTrdd")
    @Override
    public Double getTotalVolumeTraded() {
        return totalVolumeTraded;
    }

    @Override
    public void setTotalVolumeTraded(Double totalVolumeTraded) {
        this.totalVolumeTraded = totalVolumeTraded;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.TradSesReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesReqID, tradSesReqID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (tradSesMethod != null && MsgUtil.isTagInList(TagNum.TradSesMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesMethod, tradSesMethod.getValue());
            }
            if (tradSesMode != null && MsgUtil.isTagInList(TagNum.TradSesMode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesMode, tradSesMode.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.UnsolicitedIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnsolicitedIndicator, unsolicitedIndicator);
            }
            if (tradSesStatus != null && MsgUtil.isTagInList(TagNum.TradSesStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesStatus, tradSesStatus.getValue());
            }
            if (tradSesStatusRejReason != null && MsgUtil.isTagInList(TagNum.TradSesStatusRejReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradSesStatusRejReason, tradSesStatusRejReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradSesStartTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesStartTime, tradSesStartTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesOpenTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesOpenTime, tradSesOpenTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesPreCloseTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesPreCloseTime, tradSesPreCloseTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesCloseTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesCloseTime, tradSesCloseTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradSesEndTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TradSesEndTime, tradSesEndTime);
            }
            if (MsgUtil.isTagInList(TagNum.TotalVolumeTraded, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalVolumeTraded, totalVolumeTraded);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradingSessionStatusMsg] message version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
