/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusMsg50SP1.java
 *
 * $Id: TradingSessionStatusMsg50SP1.java,v 1.1 2011-04-23 00:19:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.TradingSessionStatusMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.impl.v50sp1.ApplicationSequenceControl50SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TradSesEvent;
import net.hades.fix.message.type.TradSesMethod;
import net.hades.fix.message.type.TradSesMode;
import net.hades.fix.message.type.TradSesStatus;
import net.hades.fix.message.type.TradSesStatusRejReason;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * FIX version 5.0SP1 TradingSessionStatusMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="TrdgSesStat")
@XmlType(propOrder = {"header", "applicationSequenceControl", "instrument"})
@XmlAccessorType(XmlAccessType.NONE)
public class TradingSessionStatusMsg50SP1 extends TradingSessionStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> APPL_SEQ_CONTROL_COMP_TAGS = new ApplicationSequenceControl50SP1().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP1().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(APPL_SEQ_CONTROL_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionStatusMsg50SP1() {
        super();
    }

    public TradingSessionStatusMsg50SP1(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public TradingSessionStatusMsg50SP1(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public TradingSessionStatusMsg50SP1(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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
        TradingSessionStatusMsg50SP1 fixml = (TradingSessionStatusMsg50SP1) fragment;
        if (fixml.getApplicationSequenceControl() != null) {
            applicationSequenceControl = fixml.getApplicationSequenceControl();
        }
        if (fixml.getTradSesReqID() != null) {
            tradSesReqID = fixml.getTradSesReqID();
        }
        if (fixml.getMarketID() != null) {
            marketID = fixml.getMarketID();
        }
        if (fixml.getMarketSegmentID() != null) {
            marketSegmentID = fixml.getMarketSegmentID();
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
        if (fixml.getTradSesEvent() != null) {
            tradSesEvent = fixml.getTradSesEvent();
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
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
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

    @XmlElementRef
    @Override
    public ApplicationSequenceControl getApplicationSequenceControl() {
        return applicationSequenceControl;
    }

    @Override
    public void setApplicationSequenceControl() {
        this.applicationSequenceControl = new ApplicationSequenceControl50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setApplicationSequenceControl(ApplicationSequenceControl applicationSequenceControl) {
        this.applicationSequenceControl = applicationSequenceControl;
    }

    @Override
    public void clearApplicationSequenceControl() {
        this.applicationSequenceControl = null;
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

    @XmlAttribute(name = "MktID")
    @Override
    public String getMarketID() {
        return marketID;
    }

    @Override
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    @XmlAttribute(name = "MktSegID")
    @Override
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    @Override
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
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

    @XmlAttribute(name = "TradSesEvent")
    @Override
    public TradSesEvent getTradSesEvent() {
        return tradSesEvent;
    }

    @Override
    public void setTradSesEvent(TradSesEvent tradSesEvent) {
        this.tradSesEvent = tradSesEvent;
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

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrument = new Instrument50SP1(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP1(context);
            }
            instrument.decode(tag, message);
        }
        if (APPL_SEQ_CONTROL_COMP_TAGS.contains(tag.tagNum)) {
            if (applicationSequenceControl == null) {
                applicationSequenceControl = new ApplicationSequenceControl50SP1(context);
            }
            applicationSequenceControl.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradingSessionStatusMsg] message version [5.0SP1].";
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
