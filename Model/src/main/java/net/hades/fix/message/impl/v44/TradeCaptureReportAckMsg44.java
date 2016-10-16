/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportAckMsg44.java
 *
 * $Id: TradeCaptureReportMsg44.java,v 1.2 2011-10-25 08:29:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.TradeCaptureReportAckMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.group.TradeAllocGroup;
import net.hades.fix.message.group.impl.v44.TradeAllocGroup44;
import net.hades.fix.message.group.impl.v44.TrdInstrmtLegGroup44;
import net.hades.fix.message.group.impl.v44.TrdRegTimestampsGroup44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import net.hades.fix.message.comp.impl.v44.Instrument44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TrdRptStatus;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 TradeCaptureReportAckMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="TrdCaptRptAck")
@XmlType(propOrder = {"header", "instrument", "trdRegTimestampsGroups", "trdInstrmtLegGroups", "allocGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class TradeCaptureReportAckMsg44 extends TradeCaptureReportAckMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V44 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradeReportID.getValue(),
        TagNum.TradeReportTransType.getValue(),
        TagNum.TradeReportType.getValue(),
        TagNum.TrdType.getValue(),
        TagNum.TrdSubType.getValue(),
        TagNum.SecondaryTrdType.getValue(),
        TagNum.TransferReason.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.TradeReportRefID.getValue(),
        TagNum.SecondaryTradeReportRefID.getValue(),
        TagNum.TrdRptStatus.getValue(),
        TagNum.TradeReportRejectReason.getValue(),
        TagNum.SecondaryTradeReportID.getValue(),
        TagNum.SubscriptionRequestType.getValue(),
        TagNum.TradeLinkID.getValue(),
        TagNum.TrdMatchID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoTrdRegTimestamps.getValue(),
        TagNum.ResponseTransportType.getValue(),
        TagNum.ResponseDestination.getValue(),
        TagNum.Text.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.NoAllocs.getValue()
    }));
    
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument44().getFragmentAllTags();
    protected static final Set<Integer> TRD_INSTRMT_LEG__GROUP_TAGS = new TrdInstrmtLegGroup44().getFragmentAllTags();
    protected static final Set<Integer> TRD_REG_TSTAMP_GROUP_TAGS = new TrdRegTimestampsGroup44().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_GROUP_TAGS = new TradeAllocGroup44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V44);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(TRD_INSTRMT_LEG__GROUP_TAGS);
        ALL_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        ALL_TAGS.addAll(ALLOC_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(TRD_INSTRMT_LEG__GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V44;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportAckMsg44() {
        super();
    }

    public TradeCaptureReportAckMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportAckMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportAckMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V44;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        TradeCaptureReportAckMsg44 fixml = (TradeCaptureReportAckMsg44) fragment;
        if (fixml.getTradeReportID() != null) {
            tradeReportID = fixml.getTradeReportID();
        }
        if (fixml.getTradeReportTransType() != null) {
            tradeReportTransType = fixml.getTradeReportTransType();
        }
        if (fixml.getTradeReportType() != null) {
            tradeReportType = fixml.getTradeReportType();
        }
        if (fixml.getTrdType() != null) {
            trdType = fixml.getTrdType();
        }
        if (fixml.getTrdSubType() != null) {
            trdSubType = fixml.getTrdSubType();
        }
        if (fixml.getSecondaryTrdType() != null) {
            secondaryTrdType = fixml.getSecondaryTrdType();
        }
        if (fixml.getTransferReason() != null) {
            transferReason = fixml.getTransferReason();
        }
        if (fixml.getExecType() != null) {
            execType = fixml.getExecType();
        }
        if (fixml.getTradeReportRefID() != null) {
            tradeReportRefID = fixml.getTradeReportRefID();
        }
        if (fixml.getSecondaryTradeReportRefID() != null) {
            secondaryTradeReportRefID = fixml.getSecondaryTradeReportRefID();
        }
        if (fixml.getTrdRptStatus() != null) {
            trdRptStatus = fixml.getTrdRptStatus();
        }
        if (fixml.getTradeReportRejectReason() != null) {
            tradeReportRejectReason = fixml.getTradeReportRejectReason();
        }
        if (fixml.getSecondaryTradeReportID() != null) {
            secondaryTradeReportID = fixml.getSecondaryTradeReportID();
        }
        if (fixml.getSubscriptionRequestType() != null) {
            subscriptionRequestType = fixml.getSubscriptionRequestType();
        }
        if (fixml.getTradeLinkID() != null) {
            tradeLinkID = fixml.getTradeLinkID();
        }
        if (fixml.getTrdMatchID() != null) {
            trdMatchID = fixml.getTrdMatchID();
        }
        if (fixml.getExecID() != null) {
            execID = fixml.getExecID();
        }
        if (fixml.getSecondaryExecID() != null) {
            secondaryExecID = fixml.getSecondaryExecID();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getTrdInstrmtLegGroups() != null && fixml.getTrdInstrmtLegGroups().length > 0) {
            setTrdInstrmtLegGroups(fixml.getTrdInstrmtLegGroups());
        }
        if (fixml.getTrdRegTimestampsGroups() != null && fixml.getTrdRegTimestampsGroups().length > 0) {
            setTrdRegTimestampsGroups(fixml.getTrdRegTimestampsGroups());
        }
        if (fixml.getResponseTransportType() != null) {
            responseTransportType = fixml.getResponseTransportType();
        }
        if (fixml.getResponseDestination() != null) {
            responseDestination = fixml.getResponseDestination();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getClearingFeeIndicator() != null) {
            clearingFeeIndicator = fixml.getClearingFeeIndicator();
        }
        if (fixml.getAllocGroups() != null && fixml.getAllocGroups().length > 0) {
            setAllocGroups(fixml.getAllocGroups());
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

    @XmlAttribute(name = "RptID")
    @Override
    public String getTradeReportID() {
        return tradeReportID;
    }

    @Override
    public void setTradeReportID(String tradeReportID) {
        this.tradeReportID = tradeReportID;
    }

    @XmlAttribute(name = "TransTyp")
    @Override
    public TradeReportTransType getTradeReportTransType() {
        return tradeReportTransType;
    }

    @Override
    public void setTradeReportTransType(TradeReportTransType tradeReportTransType) {
        this.tradeReportTransType = tradeReportTransType;
    }

    @XmlAttribute(name = "RptTyp")
    @Override
    public TradeReportType getTradeReportType() {
        return tradeReportType;
    }

    @Override
    public void setTradeReportType(TradeReportType tradeReportType) {
        this.tradeReportType = tradeReportType;
    }
          
    @XmlAttribute(name = "TrdTyp")
    @Override
    public TrdType getTrdType() {
        return trdType;
    }

    @Override
    public void setTrdType(TrdType trdType) {
        this.trdType = trdType;
    }

    @XmlAttribute(name = "TrdSubTyp")
    @Override
    public TrdSubType getTrdSubType() {
        return trdSubType;
    }

    @Override
    public void setTrdSubType(TrdSubType trdSubType) {
        this.trdSubType = trdSubType;
    }

    @XmlAttribute(name = "TrdTyp2")
    @Override
    public SecondaryTrdType getSecondaryTrdType() {
        return secondaryTrdType;
    }

    @Override
    public void setSecondaryTrdType(SecondaryTrdType secondaryTrdType) {
        this.secondaryTrdType = secondaryTrdType;
    }

    @XmlAttribute(name = "TrnsfrRsn")
    @Override
    public String getTransferReason() {
        return transferReason;
    }

    @Override
    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }
    
    @XmlAttribute(name = "ExecTyp")
    @Override
    public ExecType getExecType() {
        return execType;
    }

    @Override
    public void setExecType(ExecType execType) {
        this.execType = execType;
    } 
    
    @XmlAttribute(name = "RptRefID")
    @Override
    public String getTradeReportRefID() {
        return tradeReportRefID;
    }

    @Override
    public void setTradeReportRefID(String tradeReportRefID) {
        this.tradeReportRefID = tradeReportRefID;
    }

    @XmlAttribute(name = "RptRefID2")
    @Override
    public String getSecondaryTradeReportRefID() {
        return secondaryTradeReportRefID;
    }

    @Override
    public void setSecondaryTradeReportRefID(String secondaryTradeReportRefID) {
        this.secondaryTradeReportRefID = secondaryTradeReportRefID;
    }

    @XmlAttribute(name = "TrdRptStat")
    @Override
    public TrdRptStatus getTrdRptStatus() {
        return trdRptStatus;
    }

    @Override
    public void setTrdRptStatus(TrdRptStatus trdRptStatus) {
        this.trdRptStatus = trdRptStatus;
    }

    @XmlAttribute(name = "RptRejRsn")
    @Override
    public Integer getTradeReportRejectReason() {
        return tradeReportRejectReason;
    }

    @Override
    public void setTradeReportRejectReason(Integer tradeReportRejectReason) {
        this.tradeReportRejectReason = tradeReportRejectReason;
    }

    @XmlAttribute(name = "RptID2")
    @Override
    public String getSecondaryTradeReportID() {
        return secondaryTradeReportID;
    }

    @Override
    public void setSecondaryTradeReportID(String secondaryTradeReportID) {
        this.secondaryTradeReportID = secondaryTradeReportID;
    }

    @XmlAttribute(name = "SubReqTyp")
    @Override
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    @Override
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    @XmlAttribute(name = "LinkID")
    @Override
    public String getTradeLinkID() {
        return tradeLinkID;
    }

    @Override
    public void setTradeLinkID(String tradeLinkID) {
        this.tradeLinkID = tradeLinkID;
    }

    @XmlAttribute(name = "TrdMtchID")
    @Override
    public String getTrdMatchID() {
        return trdMatchID;
    }

    @Override
    public void setTrdMatchID(String trdMatchID) {
        this.trdMatchID = trdMatchID;
    }

    @XmlAttribute(name = "ExecID")
    @Override
    public String getExecID() {
        return execID;
    }

    @Override
    public void setExecID(String execID) {
        this.execID = execID;
    }

    @XmlAttribute(name = "ExecID2")
    @Override
    public String getSecondaryExecID() {
        return secondaryExecID;
    }

    @Override
    public void setSecondaryExecID(String secondaryExecID) {
        this.secondaryExecID = secondaryExecID;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument44(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            trdInstrmtLegGroups = new TrdInstrmtLegGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < trdInstrmtLegGroups.length; i++) {
                trdInstrmtLegGroups[i] = new TrdInstrmtLegGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdInstrmtLegGroup[] getTrdInstrmtLegGroups() {
        return trdInstrmtLegGroups;
    }

    public void setTrdInstrmtLegGroups(TrdInstrmtLegGroup[] instrumentLegs) {
        this.trdInstrmtLegGroups = instrumentLegs;
        if (instrumentLegs != null) {
            noLegs = new Integer(instrumentLegs.length);
        }
    }

    @Override
    public TrdInstrmtLegGroup addTrdInstrmtLegGroup() {
        TrdInstrmtLegGroup group = new TrdInstrmtLegGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<TrdInstrmtLegGroup> groups = new ArrayList<TrdInstrmtLegGroup>();
        if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length > 0) {
            groups = new ArrayList<TrdInstrmtLegGroup>(Arrays.asList(trdInstrmtLegGroups));
        }
        groups.add(group);
        trdInstrmtLegGroups = groups.toArray(new TrdInstrmtLegGroup[groups.size()]);
        noLegs = new Integer(trdInstrmtLegGroups.length);

        return group;
    }

    @Override
    public TrdInstrmtLegGroup deleteTrdInstrmtLegGroup(int index) {
        TrdInstrmtLegGroup result = null;
        if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length > 0 && trdInstrmtLegGroups.length > index) {
            List<TrdInstrmtLegGroup> groups = new ArrayList<TrdInstrmtLegGroup>(Arrays.asList(trdInstrmtLegGroups));
            result = groups.remove(index);
            trdInstrmtLegGroups = groups.toArray(new TrdInstrmtLegGroup[groups.size()]);
            if (trdInstrmtLegGroups.length > 0) {
                noLegs = new Integer(trdInstrmtLegGroups.length);
            } else {
                trdInstrmtLegGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdInstrmtLegGroups() {
        int result = 0;
        if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length > 0) {
            result = trdInstrmtLegGroups.length;
            trdInstrmtLegGroups = null;
            noLegs = null;
        }

        return result;
    }

    @Override
    public Integer getNoTrdRegTimestamps() {
        return noTrdRegTimestamps;
    }

    @Override
    public void setNoTrdRegTimestamps(Integer noTrdRegTimestamps) {
        this.noTrdRegTimestamps = noTrdRegTimestamps;
        if (noTrdRegTimestamps != null) {
            trdRegTimestampsGroups = new TrdRegTimestampsGroup[noTrdRegTimestamps.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < trdRegTimestampsGroups.length; i++) {
                trdRegTimestampsGroups[i] = new TrdRegTimestampsGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdRegTimestampsGroup[] getTrdRegTimestampsGroups() {
        return trdRegTimestampsGroups;
    }

    public void setTrdRegTimestampsGroups(TrdRegTimestampsGroup[] trdRegTimestampsGroups) {
        this.trdRegTimestampsGroups = trdRegTimestampsGroups;
        if (trdRegTimestampsGroups != null) {
            noTrdRegTimestamps = new Integer(trdRegTimestampsGroups.length);
        }
    }

    @Override
    public TrdRegTimestampsGroup addTrdRegTimestampsGroup() {
        TrdRegTimestampsGroup group = new TrdRegTimestampsGroup44(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TrdRegTimestampsGroup> groups = new ArrayList<TrdRegTimestampsGroup>();
        if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length > 0) {
            groups = new ArrayList<TrdRegTimestampsGroup>(Arrays.asList(trdRegTimestampsGroups));
        }
        groups.add(group);
        trdRegTimestampsGroups = groups.toArray(new TrdRegTimestampsGroup[groups.size()]);
        noTrdRegTimestamps = new Integer(trdRegTimestampsGroups.length);

        return group;
    }

    @Override
    public TrdRegTimestampsGroup deleteTrdRegTimestampsGroup(int index) {
        TrdRegTimestampsGroup result = null;
        if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length > 0 && trdRegTimestampsGroups.length > index) {
            List<TrdRegTimestampsGroup> groups = new ArrayList<TrdRegTimestampsGroup>(Arrays.asList(trdRegTimestampsGroups));
            result = groups.remove(index);
            trdRegTimestampsGroups = groups.toArray(new TrdRegTimestampsGroup[groups.size()]);
            if (trdRegTimestampsGroups.length > 0) {
                noTrdRegTimestamps = new Integer(trdRegTimestampsGroups.length);
            } else {
                trdRegTimestampsGroups = null;
                noTrdRegTimestamps = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdRegTimestampsGroups() {
        int result = 0;
        if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length > 0) {
            result = trdRegTimestampsGroups.length;
            trdRegTimestampsGroups = null;
            noTrdRegTimestamps = null;
        }

        return result;
    }

    @XmlAttribute(name = "RspTransportTyp")
    @Override
    public ResponseTransportType getResponseTransportType() {
        return responseTransportType;
    }

    @Override
    public void setResponseTransportType(ResponseTransportType responseTransportType) {
        this.responseTransportType = responseTransportType;
    }

    @XmlAttribute(name = "RspDest")
    @Override
    public String getResponseDestination() {
        return responseDestination;
    }

    @Override
    public void setResponseDestination(String responseDestination) {
        this.responseDestination = responseDestination;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
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

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "ClrFeeInd")
    @Override
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    @Override
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    @Override
    public Integer getNoAllocs() {
        return noAllocs;
    }

    @Override
    public void setNoAllocs(Integer noAllocs) {
        this.noAllocs = noAllocs;
        if (noAllocs != null) {
            allocGroups = new TradeAllocGroup[noAllocs.intValue()];
            for (int i = 0; i < allocGroups.length; i++) {
                allocGroups[i] = new TradeAllocGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public TradeAllocGroup[] getAllocGroups() {
        return allocGroups;
    }

    public void setAllocGroups(TradeAllocGroup[] allocGroups) {
        this.allocGroups = allocGroups;
        if (allocGroups != null) {
            noAllocs = new Integer(allocGroups.length);
        }
    }

    @Override
    public TradeAllocGroup addAllocGroup() {
        TradeAllocGroup group = new TradeAllocGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<TradeAllocGroup> groups = new ArrayList<TradeAllocGroup>();
        if (allocGroups != null && allocGroups.length > 0) {
            groups = new ArrayList<TradeAllocGroup>(Arrays.asList(allocGroups));
        }
        groups.add(group);
        allocGroups = groups.toArray(new TradeAllocGroup[groups.size()]);
        noAllocs = new Integer(allocGroups.length);

        return group;
    }

    @Override
    public TradeAllocGroup deleteAllocGroup(int index) {
        TradeAllocGroup result = null;
        if (allocGroups != null && allocGroups.length > 0 && allocGroups.length > index) {
            List<TradeAllocGroup> groups = new ArrayList<TradeAllocGroup>(Arrays.asList(allocGroups));
            result = groups.remove(index);
            allocGroups = groups.toArray(new TradeAllocGroup[groups.size()]);
            if (allocGroups.length > 0) {
                noAllocs = new Integer(allocGroups.length);
            } else {
                allocGroups = null;
                noAllocs = null;
            }
        }

        return result;
    }

    @Override
    public int clearAllocGroups() {
        int result = 0;
        if (allocGroups != null && allocGroups.length > 0) {
            result = allocGroups.length;
            allocGroups = null;
            noAllocs = null;
        }

        return result;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (tradeReportID == null || tradeReportID.isEmpty()) {
            errorMsg.append(" [TradeReportID]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (execType == null) {
            errorMsg.append(" [ExecType]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.TradeReportID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportID, tradeReportID);
            }
            if (tradeReportTransType != null && MsgUtil.isTagInList(TagNum.TradeReportTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportTransType, tradeReportTransType.getValue());
            }
            if (tradeReportType != null && MsgUtil.isTagInList(TagNum.TradeReportType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportType, tradeReportType.getValue());
            }
            if (trdType != null && MsgUtil.isTagInList(TagNum.TrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdType, trdType.getValue());
            }
            if (trdSubType != null && MsgUtil.isTagInList(TagNum.TrdSubType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdSubType, trdSubType.getValue());
            }
            if (secondaryTrdType != null && MsgUtil.isTagInList(TagNum.SecondaryTrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryTrdType, secondaryTrdType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TransferReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TransferReason, transferReason);
            }
            if (execType != null && MsgUtil.isTagInList(TagNum.ExecType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeReportRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportRefID, tradeReportRefID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryTradeReportRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryTradeReportRefID, secondaryTradeReportRefID);
            }
            if (trdRptStatus != null && MsgUtil.isTagInList(TagNum.TrdRptStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdRptStatus, trdRptStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeReportRejectReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportRejectReason, tradeReportRejectReason);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryTradeReportID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
            }
            if (subscriptionRequestType != null && MsgUtil.isTagInList(TagNum.SubscriptionRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeLinkID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeLinkID, tradeLinkID);
            }
            if (MsgUtil.isTagInList(TagNum.TrdMatchID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TrdMatchID, trdMatchID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (trdInstrmtLegGroups != null && trdInstrmtLegGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (trdInstrmtLegGroups[i] != null) {
                            bao.write(trdInstrmtLegGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TrdInstrmtLegGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            if (noTrdRegTimestamps != null && MsgUtil.isTagInList(TagNum.NoTrdRegTimestamps, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
                if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length == noTrdRegTimestamps.intValue()) {
                    for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                        if (trdRegTimestampsGroups[i] != null) {
                            bao.write(trdRegTimestampsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TrdRegTimestampsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTrdRegTimestamps.getValue(), error);
                }
            }
            if (responseTransportType != null && MsgUtil.isTagInList(TagNum.ResponseTransportType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ResponseTransportType, responseTransportType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ResponseDestination, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ResponseDestination, responseDestination);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text, sessionCharset);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (clearingFeeIndicator != null && MsgUtil.isTagInList(TagNum.ClearingFeeIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (noAllocs != null && MsgUtil.isTagInList(TagNum.NoAllocs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocGroups != null && allocGroups.length == noAllocs.intValue()) {
                    for (int i = 0; i < noAllocs.intValue(); i++) {
                        if (allocGroups[i] != null) {
                            bao.write(allocGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "AllocationGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoAllocs.getValue(), error);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument44(context);
            }
            instrument.decode(tag, message);
        }
        if (TRD_INSTRMT_LEG__GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                trdInstrmtLegGroups = new TrdInstrmtLegGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    TrdInstrmtLegGroup group = new TrdInstrmtLegGroup44(context);
                    group.decode(message);
                    trdInstrmtLegGroups[i] = group;
                }
            }
        }
        if (TRD_REG_TSTAMP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTrdRegTimestamps != null && noTrdRegTimestamps.intValue() > 0) {
                message.reset();
                trdRegTimestampsGroups = new TrdRegTimestampsGroup[noTrdRegTimestamps.intValue()];
                for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                    TrdRegTimestampsGroup group = new TrdRegTimestampsGroup44(context);
                    group.decode(message);
                    trdRegTimestampsGroups[i] = group;
                }
            }
        }
        if (ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAllocs != null && noAllocs.intValue() > 0) {
                message.reset();
                allocGroups = new TradeAllocGroup[noAllocs.intValue()];
                for (int i = 0; i < noAllocs.intValue(); i++) {
                    TradeAllocGroup group = new TradeAllocGroup44(context);
                    group.decode(message);
                    allocGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradeCaptureReportAckMsg] message version [4.4].";
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
