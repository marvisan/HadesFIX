/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportAckMsg50SP2.java
 *
 * $Id: TradeCaptureReportMsg44.java,v 1.2 2011-10-25 08:29:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.TradeCaptureReportAckMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.impl.v50sp2.RootParties50SP2;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.group.TrdCapRptAckSideGroup;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.group.TrdRepIndicatorsGroup;
import net.hades.fix.message.group.impl.v50sp2.PosAmtGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TrdCapRptAckSideGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TrdInstrmtLegGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TrdRegTimestampsGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TrdRepIndicatorsGroup50SP2;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.AsOfIndicator;
import net.hades.fix.message.type.AvgPxIndicator;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.ShortSaleReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeHandlingInstr;
import net.hades.fix.message.type.TradePublishIndicator;
import net.hades.fix.message.type.TradeReportTransType;
import net.hades.fix.message.type.TradeReportType;
import net.hades.fix.message.type.TrdRptStatus;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.type.VenueType;

/**
 * FIX version 5.0SP2 TradeCaptureReportAckMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="TrdCaptRptAck")
@XmlType(propOrder = {"header", "rootPartyIDGroups", "instrument", "underlyingInstruments", "trdRepIndicatorsGroups",
    "trdInstrmtLegGroups", "trdRegTimestampsGroups", "posAmtGroups", "trdCapRptAckSideGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class TradeCaptureReportAckMsg50SP2 extends TradeCaptureReportAckMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> ROOT_PARTIES_COMP_TAGS = new RootParties50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRD_REP_INDICATORS_GROUP_TAGS = new TrdRepIndicatorsGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRD_INSTRMT_LEG__GROUP_TAGS = new TrdInstrmtLegGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRD_REG_TSTAMP_GROUP_TAGS = new TrdRegTimestampsGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> POS_AMT_GROUP_TAGS = new PosAmtGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRD_CAP_RPT_ACK_SIDE_GROUP_TAGS = new TrdCapRptAckSideGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(ROOT_PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(TRD_REP_INDICATORS_GROUP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(TRD_INSTRMT_LEG__GROUP_TAGS);
        ALL_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        ALL_TAGS.addAll(POS_AMT_GROUP_TAGS);
        ALL_TAGS.addAll(TRD_CAP_RPT_ACK_SIDE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(ROOT_PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(TRD_REP_INDICATORS_GROUP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(TRD_INSTRMT_LEG__GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_REG_TSTAMP_GROUP_TAGS);
        START_COMP_TAGS.addAll(POS_AMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRD_CAP_RPT_ACK_SIDE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportAckMsg50SP2() {
        super();
    }

    public TradeCaptureReportAckMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public TradeCaptureReportAckMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public TradeCaptureReportAckMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        TradeCaptureReportAckMsg50SP2 fixml = (TradeCaptureReportAckMsg50SP2) fragment;
        if (fixml.getTradeReportID() != null) {
            tradeReportID = fixml.getTradeReportID();
        }
        if (fixml.getTradeID() != null) {
            tradeID = fixml.getTradeID();
        }
        if (fixml.getSecondaryTradeID() != null) {
            secondaryTradeID = fixml.getSecondaryTradeID();
        }
        if (fixml.getFirmTradeID() != null) {
            firmTradeID = fixml.getFirmTradeID();
        }
        if (fixml.getSecondaryFirmTradeID() != null) {
            secondaryFirmTradeID = fixml.getSecondaryFirmTradeID();
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
        if (fixml.getTradeHandlingInstr() != null) {
            tradeHandlingInstr = fixml.getTradeHandlingInstr();
        }
        if (fixml.getOrigTradeHandlingInstr() != null) {
            origTradeHandlingInstr = fixml.getOrigTradeHandlingInstr();
        }
        if (fixml.getOrigTradeDate() != null) {
            origTradeDate = fixml.getOrigTradeDate();
        }
        if (fixml.getOrigTradeID() != null) {
            origTradeID = fixml.getOrigTradeID();
        }
        if (fixml.getOrigSecondaryTradeID() != null) {
            origSecondaryTradeID = fixml.getOrigSecondaryTradeID();
        }
        if (fixml.getTransferReason() != null) {
            transferReason = fixml.getTransferReason();
        }
        if (fixml.getRootParties() != null) {
            rootParties = fixml.getRootParties();
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
        if (fixml.getExecRestatementReason() != null) {
            execRestatementReason = fixml.getExecRestatementReason();
        }
        if (fixml.getPreviouslyReported() != null) {
            previouslyReported = fixml.getPreviouslyReported();
        }
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getUnderlyingTradingSessionID() != null) {
            underlyingTradingSessionID = fixml.getUnderlyingTradingSessionID();
        }
        if (fixml.getUnderlyingTradingSessionSubID() != null) {
            underlyingTradingSessionSubID = fixml.getUnderlyingTradingSessionSubID();
        }
        if (fixml.getSettlSessID() != null) {
            settlSessID = fixml.getSettlSessID();
        }
        if (fixml.getSettlSessSubID() != null) {
            settlSessSubID = fixml.getSettlSessSubID();
        }
        if (fixml.getQtyType() != null) {
            qtyType = fixml.getQtyType();
        }
        if (fixml.getLastQty() != null) {
            lastQty = fixml.getLastQty();
        }
        if (fixml.getLastPx() != null) {
            lastPx = fixml.getLastPx();
        }
        if (fixml.getVenueType() != null) {
            venueType = fixml.getVenueType();
        }
        if (fixml.getMarketSegmentID() != null) {
            marketSegmentID = fixml.getMarketSegmentID();
        }
        if (fixml.getMarketID() != null) {
            marketID = fixml.getMarketID();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getLastParPx() != null) {
            lastParPx = fixml.getLastParPx();
        }
        if (fixml.getCalculatedCcyLastQty() != null) {
            calculatedCcyLastQty = fixml.getCalculatedCcyLastQty();
        }
        if (fixml.getLastSwapPoints() != null) {
            lastSwapPoints = fixml.getLastSwapPoints();
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getSettlCurrency() != null) {
            settlCurrency = fixml.getSettlCurrency();
        }
        if (fixml.getLastSpotRate() != null) {
            lastSpotRate = fixml.getLastSpotRate();
        }
        if (fixml.getLastForwardPoints() != null) {
            lastForwardPoints = fixml.getLastForwardPoints();
        }
        if (fixml.getLastMkt() != null) {
            lastMkt = fixml.getLastMkt();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getClearingBusinessDate() != null) {
            clearingBusinessDate = fixml.getClearingBusinessDate();
        }
        if (fixml.getAvgPx() != null) {
            avgPx = fixml.getAvgPx();
        }
        if (fixml.getAvgPxIndicator() != null) {
            avgPxIndicator = fixml.getAvgPxIndicator();
        }
        if (fixml.getMultilegReportingType() != null) {
            multilegReportingType = fixml.getMultilegReportingType();
        }
        if (fixml.getTradeLegRefID() != null) {
            tradeLegRefID = fixml.getTradeLegRefID();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getSettlType() != null) {
            settlType = fixml.getSettlType();
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getMatchStatus() != null) {
            matchStatus = fixml.getMatchStatus();
        }
        if (fixml.getMatchType() != null) {
            matchType = fixml.getMatchType();
        }
        if (fixml.getCopyMsgIndicator() != null) {
            copyMsgIndicator = fixml.getCopyMsgIndicator();
        }
        if (fixml.getTrdRepIndicatorsGroups() != null) {
            setTrdRepIndicatorsGroups(fixml.getTrdRepIndicatorsGroups());
        }
        if (fixml.getTradePublishIndicator() != null) {
            tradePublishIndicator = fixml.getTradePublishIndicator();
        }
        if (fixml.getShortSaleReason() != null) {
            shortSaleReason = fixml.getShortSaleReason();
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
        if (fixml.getAsOfIndicator() != null) {
            asOfIndicator = fixml.getAsOfIndicator();
        }
        if (fixml.getClearingFeeIndicator() != null) {
            clearingFeeIndicator = fixml.getClearingFeeIndicator();
        }
        if (fixml.getPosAmtGroups() != null && fixml.getPosAmtGroups().length > 0) {
            setPosAmtGroups(fixml.getPosAmtGroups());
        }
        if (fixml.getTierCode() != null) {
            tierCode = fixml.getTierCode();
        }
        if (fixml.getMessageEventSource() != null) {
            messageEventSource = fixml.getMessageEventSource();
        }
        if (fixml.getLastUpdateTime() != null) {
            lastUpdateTime = fixml.getLastUpdateTime();
        }
        if (fixml.getRndPx() != null) {
            rndPx = fixml.getRndPx();
        }
        if (fixml.getTrdCapRptAckSideGroups() != null && fixml.getTrdCapRptAckSideGroups().length > 0) {
            setTrdCapRptAckSideGroups(fixml.getTrdCapRptAckSideGroups());
        }
        if (fixml.getRptSys() != null) {
            rptSys = fixml.getRptSys();
        }
        if (fixml.getGrossTradeAmt() != null) {
            grossTradeAmt = fixml.getGrossTradeAmt();
        }
        if (fixml.getSettlDate() != null) {
            settlDate = fixml.getSettlDate();
        }
        if (fixml.getFeeMultiplier() != null) {
            feeMultiplier = fixml.getFeeMultiplier();
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

    @XmlAttribute(name = "TrdID")
    @Override
    public String getTradeID() {
        return tradeID;
    }

    @Override
    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    @XmlAttribute(name = "TrdID2")
    @Override
    public String getSecondaryTradeID() {
        return secondaryTradeID;
    }

    @Override
    public void setSecondaryTradeID(String secondaryTradeID) {
        this.secondaryTradeID = secondaryTradeID;
    }

    @XmlAttribute(name = "FirmTrdID")
    @Override
    public String getFirmTradeID() {
        return firmTradeID;
    }

    @Override
    public void setFirmTradeID(String firmTradeID) {
        this.firmTradeID = firmTradeID;
    }

    @XmlAttribute(name = "FirmTrdID2")
    @Override
    public String getSecondaryFirmTradeID() {
        return secondaryFirmTradeID;
    }

    @Override
    public void setSecondaryFirmTradeID(String secondaryFirmTradeID) {
        this.secondaryFirmTradeID = secondaryFirmTradeID;
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

    @XmlAttribute(name = "TrdHandlInst")
    @Override
    public TradeHandlingInstr getTradeHandlingInstr() {
        return tradeHandlingInstr;
    }

    @Override
    public void setTradeHandlingInstr(TradeHandlingInstr tradeHandlingInstr) {
        this.tradeHandlingInstr = tradeHandlingInstr;
    }

    @XmlAttribute(name = "OrigTrdHandlInst")
    @Override
    public TradeHandlingInstr getOrigTradeHandlingInstr() {
        return origTradeHandlingInstr;
    }

    @Override
    public void setOrigTradeHandlingInstr(TradeHandlingInstr origTradeHandlingInstr) {
        this.origTradeHandlingInstr = origTradeHandlingInstr;
    }

    @XmlAttribute(name = "OrigTrdDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getOrigTradeDate() {
        return origTradeDate;
    }

    @Override
    public void setOrigTradeDate(Date origTradeDate) {
        this.origTradeDate = origTradeDate;
    }

    @XmlAttribute(name = "OrigTrdID")
    @Override
    public String getOrigTradeID() {
        return origTradeID;
    }

    @Override
    public void setOrigTradeID(String origTradeID) {
        this.origTradeID = origTradeID;
    }

    @XmlAttribute(name = "OrignTrdID2")
    @Override
    public String getOrigSecondaryTradeID() {
        return origSecondaryTradeID;
    }

    @Override
    public void setOrigSecondaryTradeID(String origSecondaryTradeID) {
        this.origSecondaryTradeID = origSecondaryTradeID;
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

    @Override
    public RootParties getRootParties() {
        return rootParties;
    }

    @Override
    public void setRootParties() {
        this.rootParties = new RootParties50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearRootParties() {
        this.rootParties = null;
    }

    @XmlElementRef
    public RootPartyGroup[] getRootPartyIDGroups() {
        return rootParties == null ? null : rootParties.getRootPartyIDGroups();
    }

    public void setRootPartyIDGroups(RootPartyGroup[] rootPartyIDGroups) {
        if (rootPartyIDGroups != null) {
            if (rootParties == null) {
                setRootParties();
            }
            ((RootParties50SP2) rootParties).setRootPartyIDGroups(rootPartyIDGroups);
        }
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

    @XmlAttribute(name = "RejRsn")
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

    @XmlAttribute(name = "MtchID")
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

    @XmlAttribute(name = "ExecRstmtRsn")
    @Override
    public ExecRestatementReason getExecRestatementReason() {
        return execRestatementReason;
    }

    @Override
    public void setExecRestatementReason(ExecRestatementReason execRestatementReason) {
        this.execRestatementReason = execRestatementReason;
    }

    @XmlAttribute(name = "PrevlyRpted")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPreviouslyReported() {
        return previouslyReported;
    }

    @Override
    public void setPreviouslyReported(Boolean previouslyReported) {
        this.previouslyReported = previouslyReported;
    }

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @XmlAttribute(name = "UndSesID")
    @Override
    public String getUnderlyingTradingSessionID() {
        return underlyingTradingSessionID;
    }

    @Override
    public void setUnderlyingTradingSessionID(String underlyingTradingSessionID) {
        this.underlyingTradingSessionID = underlyingTradingSessionID;
    }

    @XmlAttribute(name = "UndSesSub")
    @Override
    public String getUnderlyingTradingSessionSubID() {
        return underlyingTradingSessionSubID;
    }

    @Override
    public void setUnderlyingTradingSessionSubID(String underlyingTradingSessionSubID) {
        this.underlyingTradingSessionSubID = underlyingTradingSessionSubID;
    }

    @XmlAttribute(name = "SetSesID")
    @Override
    public String getSettlSessID() {
        return settlSessID;
    }

    @Override
    public void setSettlSessID(String settlSessID) {
        this.settlSessID = settlSessID;
    }

    @XmlAttribute(name = "SetSesSub")
    @Override
    public String getSettlSessSubID() {
        return settlSessSubID;
    }

    @Override
    public void setSettlSessSubID(String settlSessSubID) {
        this.settlSessSubID = settlSessSubID;
    }

    @XmlAttribute(name = "QtyTyp")
    @Override
    public QtyType getQtyType() {
        return qtyType;
    }

    @Override
    public void setQtyType(QtyType qtyType) {
        this.qtyType = qtyType;
    }

    @XmlAttribute(name = "LastQty")
    @Override
    public Double getLastQty() {
        return lastQty;
    }

    @Override
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    @XmlAttribute(name = "LastPx")
    @Override
    public Double getLastPx() {
        return lastPx;
    }

    @Override
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    @XmlAttribute(name = "VenuTyp")
    @Override
    public VenueType getVenueType() {
        return venueType;
    }

    @Override
    public void setVenueType(VenueType venueType) {
        this.venueType = venueType;
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

    @XmlAttribute(name = "MktID")
    @Override
    public String getMarketID() {
        return marketID;
    }

    @Override
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrument = new Instrument50SP2(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    
    @XmlAttribute(name = "LastParPx")
    @Override
    public Double getLastParPx() {
        return lastParPx;
    }

    @Override
    public void setLastParPx(Double lastParPx) {
        this.lastParPx = lastParPx;
    }
    
    @XmlAttribute(name = "CalcCcyLastQty")
    @Override
    public Double getCalculatedCcyLastQty() {
        return calculatedCcyLastQty;
    }

    @Override
    public void setCalculatedCcyLastQty(Double calculatedCcyLastQty) {
        this.calculatedCcyLastQty = calculatedCcyLastQty;
    }

    @XmlAttribute(name = "LastSwapPnts")
    @Override
    public Double getLastSwapPoints() {
        return lastSwapPoints;
    }

    @Override
    public void setLastSwapPoints(Double lastSwapPoints) {
        this.lastSwapPoints = lastSwapPoints;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "SettlCcy")
    @Override
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    @Override
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    @XmlAttribute(name = "LastSpotRt")
    @Override
    public Double getLastSpotRate() {
        return lastSpotRate;
    }

    @Override
    public void setLastSpotRate(Double lastSpotRate) {
        this.lastSpotRate = lastSpotRate;
    }

    @XmlAttribute(name = "LastFwdPnts")
    @Override
    public Double getLastForwardPoints() {
        return lastForwardPoints;
    }

    @Override
    public void setLastForwardPoints(Double lastForwardPoints) {
        this.lastForwardPoints = lastForwardPoints;
    }

    @XmlAttribute(name = "LastMkt")
    @Override
    public String getLastMkt() {
        return lastMkt;
    }

    @Override
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
    }

    @XmlAttribute(name = "TrdDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @XmlAttribute(name = "BizDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    @Override
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    @XmlAttribute(name = "AvgPx")
    @Override
    public Double getAvgPx() {
        return avgPx;
    }

    @Override
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    @XmlAttribute(name = "AvgPxInd")
    @Override
    public AvgPxIndicator getAvgPxIndicator() {
        return avgPxIndicator;
    }

    @Override
    public void setAvgPxIndicator(AvgPxIndicator avgPxIndicator) {
        this.avgPxIndicator = avgPxIndicator;
    }

    @XmlAttribute(name = "MLegRptTyp")
    @Override
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    @Override
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    @XmlAttribute(name = "TrdLegRefID")
    @Override
    public String getTradeLegRefID() {
        return tradeLegRefID;
    }

    @Override
    public void setTradeLegRefID(String tradeLegRefID) {
        this.tradeLegRefID = tradeLegRefID;
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

    @XmlAttribute(name = "SettlTyp")
    @Override
    public String getSettlType() {
        return settlType;
    }

    @Override
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    @Override
    public Integer getNoUnderlyings() {
        return noUnderlyings;
    }

    @Override
    public void setNoUnderlyings(Integer noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
        if (noUnderlyings != null) {
            underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        return underlyingInstruments;
    }
    
    public void setUnderlyingInstruments(UnderlyingInstrument[] underlyingInstruments) {
        this.underlyingInstruments = underlyingInstruments;
        if (underlyingInstruments != null) {
            noUnderlyings = new Integer(underlyingInstruments.length);
        }
    }

    @Override
    public UnderlyingInstrument addUnderlyingInstrument() {
        UnderlyingInstrument group = new UnderlyingInstrument50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>();
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
        }
        groups.add(group);
        underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
        noUnderlyings = new Integer(underlyingInstruments.length);

        return group;
    }

    @Override
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        UnderlyingInstrument result = null;
        if (underlyingInstruments != null && underlyingInstruments.length > 0 && underlyingInstruments.length > index) {
            List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
            result = groups.remove(index);
            underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
            if (underlyingInstruments.length > 0) {
                noUnderlyings = new Integer(underlyingInstruments.length);
            } else {
                underlyingInstruments = null;
                noUnderlyings = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingInstruments() {
        int result = 0;
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            result = underlyingInstruments.length;
            underlyingInstruments = null;
            noUnderlyings = null;
        }

        return result;
    }
    
    @XmlAttribute(name = "MtchStat")
    @Override
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    @Override
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    @XmlAttribute(name = "MtchTyp")
    @Override
    public MatchType getMatchType() {
        return matchType;
    }

    @Override
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    @XmlAttribute(name = "CopyMsgInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getCopyMsgIndicator() {
        return copyMsgIndicator;
    }

    @Override
    public void setCopyMsgIndicator(Boolean copyMsgIndicator) {
        this.copyMsgIndicator = copyMsgIndicator;
    }

    @Override
    public Integer getNoTrdRepIndicators() {
        return noTrdRepIndicators;
    }

    @Override
    public void setNoTrdRepIndicators(Integer noTrdRepIndicators) {
        this.noTrdRepIndicators = noTrdRepIndicators;
        if (noTrdRepIndicators != null) {
            trdRepIndicatorsGroups = new TrdRepIndicatorsGroup[noTrdRepIndicators.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < trdRepIndicatorsGroups.length; i++) {
                trdRepIndicatorsGroups[i] = new TrdRepIndicatorsGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdRepIndicatorsGroup[] getTrdRepIndicatorsGroups() {
        return trdRepIndicatorsGroups;
    }

    public void setTrdRepIndicatorsGroups(TrdRepIndicatorsGroup[] trdRepIndicatorsGroups) {
        this.trdRepIndicatorsGroups = trdRepIndicatorsGroups;
        if (trdRepIndicatorsGroups != null) {
            noTrdRepIndicators = new Integer(trdRepIndicatorsGroups.length);
        }
    }

    @Override
    public TrdRepIndicatorsGroup addTrdRepIndicatorsGroup() {
        TrdRepIndicatorsGroup group = new TrdRepIndicatorsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TrdRepIndicatorsGroup> groups = new ArrayList<TrdRepIndicatorsGroup>();
        if (trdRepIndicatorsGroups != null && trdRepIndicatorsGroups.length > 0) {
            groups = new ArrayList<TrdRepIndicatorsGroup>(Arrays.asList(trdRepIndicatorsGroups));
        }
        groups.add(group);
        trdRepIndicatorsGroups = groups.toArray(new TrdRepIndicatorsGroup[groups.size()]);
        noTrdRepIndicators = new Integer(trdRepIndicatorsGroups.length);

        return group;
    }

    @Override
    public TrdRepIndicatorsGroup deleteTrdRepIndicatorsGroup(int index) {
        TrdRepIndicatorsGroup result = null;
        if (trdRepIndicatorsGroups != null && trdRepIndicatorsGroups.length > 0 && trdRepIndicatorsGroups.length > index) {
            List<TrdRepIndicatorsGroup> groups = new ArrayList<TrdRepIndicatorsGroup>(Arrays.asList(trdRepIndicatorsGroups));
            result = groups.remove(index);
            trdRepIndicatorsGroups = groups.toArray(new TrdRepIndicatorsGroup[groups.size()]);
            if (trdRepIndicatorsGroups.length > 0) {
                noTrdRepIndicators = new Integer(trdRepIndicatorsGroups.length);
            } else {
                trdRepIndicatorsGroups = null;
                noTrdRepIndicators = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdRepIndicatorsGroups() {
        int result = 0;
        if (trdRepIndicatorsGroups != null && trdRepIndicatorsGroups.length > 0) {
            result = trdRepIndicatorsGroups.length;
            trdRepIndicatorsGroups = null;
            noTrdRepIndicators = null;
        }

        return result;
    }

    @XmlAttribute(name = "TrdPubInd")
    @Override
    public TradePublishIndicator getTradePublishIndicator() {
        return tradePublishIndicator;
    }

    @Override
    public void setTradePublishIndicator(TradePublishIndicator tradePublishIndicator) {
        this.tradePublishIndicator = tradePublishIndicator;
    }

    @XmlAttribute(name = "ShrtSaleRsn")
    @Override
    public ShortSaleReason getShortSaleReason() {
        return shortSaleReason;
    }

    @Override
    public void setShortSaleReason(ShortSaleReason shortSaleReason) {
        this.shortSaleReason = shortSaleReason;
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < trdInstrmtLegGroups.length; i++) {
                trdInstrmtLegGroups[i] = new TrdInstrmtLegGroup50SP2(context);
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
        TrdInstrmtLegGroup group = new TrdInstrmtLegGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
                trdRegTimestampsGroups[i] = new TrdRegTimestampsGroup50SP2(context);
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
        TrdRegTimestampsGroup group = new TrdRegTimestampsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "AsOfInd")
    @Override
    public AsOfIndicator getAsOfIndicator() {
        return asOfIndicator;
    }

    @Override
    public void setAsOfIndicator(AsOfIndicator asOfIndicator) {
        this.asOfIndicator = asOfIndicator;
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
    public Integer getNoPosAmt() {
        return noPosAmt;
    }

    @Override
    public void setNoPosAmt(Integer noPosAmt) {
        this.noPosAmt = noPosAmt;
        if (noPosAmt != null) {
            posAmtGroups = new PosAmtGroup[noPosAmt.intValue()];
            for (int i = 0; i < posAmtGroups.length; i++) {
                posAmtGroups[i] = new PosAmtGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public PosAmtGroup[] getPosAmtGroups() {
        return posAmtGroups;
    }

    public void setPosAmtGroups(PosAmtGroup[] posAmtGroups) {
        this.posAmtGroups = posAmtGroups;
        if (posAmtGroups != null) {
            noPosAmt = new Integer(posAmtGroups.length);
        }
    }

    @Override
    public PosAmtGroup addPosAmtGroup() {
        PosAmtGroup group = new PosAmtGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<PosAmtGroup> groups = new ArrayList<PosAmtGroup>();
        if (posAmtGroups != null && posAmtGroups.length > 0) {
            groups = new ArrayList<PosAmtGroup>(Arrays.asList(posAmtGroups));
        }
        groups.add(group);
        posAmtGroups = groups.toArray(new PosAmtGroup[groups.size()]);
        noPosAmt = new Integer(posAmtGroups.length);

        return group;
    }

    @Override
    public PosAmtGroup deletePosAmtGroup(int index) {
        PosAmtGroup result = null;
        if (posAmtGroups != null && posAmtGroups.length > 0 && posAmtGroups.length > index) {
            List<PosAmtGroup> groups = new ArrayList<PosAmtGroup>(Arrays.asList(posAmtGroups));
            result = groups.remove(index);
            posAmtGroups = groups.toArray(new PosAmtGroup[groups.size()]);
            if (posAmtGroups.length > 0) {
                noPosAmt = new Integer(posAmtGroups.length);
            } else {
                posAmtGroups = null;
                noPosAmt = null;
            }
        }

        return result;
    }

    @Override
    public int clearPosAmtGroups() {
        int result = 0;
        if (posAmtGroups != null && posAmtGroups.length > 0) {
            result = posAmtGroups.length;
            posAmtGroups = null;
            noPosAmt = null;
        }

        return result;
    }

    @XmlAttribute(name = "TierCD")
    @Override
    public String getTierCode() {
        return tierCode;
    }

    @Override
    public void setTierCode(String tierCode) {
        this.tierCode = tierCode;
    }

    @XmlAttribute(name = "MsgEvtSrc")
    @Override
    public String getMessageEventSource() {
        return messageEventSource;
    }

    @Override
    public void setMessageEventSource(String messageEventSource) {
        this.messageEventSource = messageEventSource;
    }

    @XmlAttribute(name = "LastUpdateTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @XmlAttribute(name = "RndPx")
    @Override
    public Double getRndPx() {
        return rndPx;
    }

    @Override
    public void setRndPx(Double rndPx) {
        this.rndPx = rndPx;
    }

    @Override
    public Integer getNoSides() {
        return noSides;
    }

    @Override
    public void setNoSides(Integer noSides) {
        this.noSides = noSides;
        if (noSides != null) {
            trdCapRptAckSideGroups = new TrdCapRptAckSideGroup[noSides.intValue()];
            for (int i = 0; i < trdCapRptAckSideGroups.length; i++) {
                trdCapRptAckSideGroups[i] = new TrdCapRptAckSideGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public TrdCapRptAckSideGroup[] getTrdCapRptAckSideGroups() {
        return trdCapRptAckSideGroups;
    }

    public void setTrdCapRptAckSideGroups(TrdCapRptAckSideGroup[] sideCrossOrdModGroups) {
        this.trdCapRptAckSideGroups = sideCrossOrdModGroups;
        if (sideCrossOrdModGroups != null) {
            noSides = new Integer(sideCrossOrdModGroups.length);
        }
    }

    @Override
    public TrdCapRptAckSideGroup addTrdCapRptAckSideGroup() {
        TrdCapRptAckSideGroup group = new TrdCapRptAckSideGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TrdCapRptAckSideGroup> groups = new ArrayList<TrdCapRptAckSideGroup>();
        if (trdCapRptAckSideGroups != null && trdCapRptAckSideGroups.length > 0) {
            groups = new ArrayList<TrdCapRptAckSideGroup>(Arrays.asList(trdCapRptAckSideGroups));
        }
        groups.add(group);
        trdCapRptAckSideGroups = groups.toArray(new TrdCapRptAckSideGroup[groups.size()]);
        noSides = new Integer(trdCapRptAckSideGroups.length);

        return group;
    }

    @Override
    public TrdCapRptAckSideGroup deleteTrdCapRptAckSideGroup(int index) {
        TrdCapRptAckSideGroup result = null;
        if (trdCapRptAckSideGroups != null && trdCapRptAckSideGroups.length > 0 && trdCapRptAckSideGroups.length > index) {
            List<TrdCapRptAckSideGroup> groups = new ArrayList<TrdCapRptAckSideGroup>(Arrays.asList(trdCapRptAckSideGroups));
            result = groups.remove(index);
            trdCapRptAckSideGroups = groups.toArray(new TrdCapRptAckSideGroup[groups.size()]);
            if (trdCapRptAckSideGroups.length > 0) {
                noSides = new Integer(trdCapRptAckSideGroups.length);
            } else {
                trdCapRptAckSideGroups = null;
                noSides = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdCapRptAckSideGroups() {
        int result = 0;
        if (trdCapRptAckSideGroups != null && trdCapRptAckSideGroups.length > 0) {
            result = trdCapRptAckSideGroups.length;
            trdCapRptAckSideGroups = null;
            noSides = null;
        }

        return result;
    }
    
    @XmlAttribute(name = "RptSys")
    @Override
    public String getRptSys() {
        return rptSys;
    }

    @Override
    public void setRptSys(String rptSys) {
        this.rptSys = rptSys;
    }

    @XmlAttribute(name = "GrossTrdAmt")
    @Override
    public Double getGrossTradeAmt() {
        return grossTradeAmt;
    }

    @Override
    public void setGrossTradeAmt(Double grossTradeAmt) {
        this.grossTradeAmt = grossTradeAmt;
    }

    @XmlAttribute(name = "SettlDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getSettlDate() {
        return settlDate;
    }

    @Override
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    @XmlAttribute(name = "FeeMult")
    @Override
    public Double getFeeMultiplier() {
        return feeMultiplier;
    }

    @Override
    public void setFeeMultiplier(Double feeMultiplier) {
        this.feeMultiplier = feeMultiplier;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (ROOT_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (rootParties == null) {
                rootParties = new RootParties50SP2(context);
            }
            rootParties.decode(tag, message);
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP2(context);
            }
            instrument.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument50SP2(context);
                    component.decode(message);
                    underlyingInstruments[i] = component;
                }
            }
        }
        if (TRD_REP_INDICATORS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTrdRepIndicators != null && noTrdRepIndicators.intValue() > 0) {
                message.reset();
                trdRepIndicatorsGroups = new TrdRepIndicatorsGroup[noTrdRepIndicators.intValue()];
                for (int i = 0; i < noTrdRepIndicators.intValue(); i++) {
                    TrdRepIndicatorsGroup component = new TrdRepIndicatorsGroup50SP2(context);
                    component.decode(message);
                    trdRepIndicatorsGroups[i] = component;
                }
            }
        }
        if (TRD_INSTRMT_LEG__GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                trdInstrmtLegGroups = new TrdInstrmtLegGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    TrdInstrmtLegGroup group = new TrdInstrmtLegGroup50SP2(context);
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
                    TrdRegTimestampsGroup group = new TrdRegTimestampsGroup50SP2(context);
                    group.decode(message);
                    trdRegTimestampsGroups[i] = group;
                }
            }
        }
        if (POS_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPosAmt != null && noPosAmt.intValue() > 0) {
                message.reset();
                posAmtGroups = new PosAmtGroup[noPosAmt.intValue()];
                for (int i = 0; i < noPosAmt.intValue(); i++) {
                    PosAmtGroup group = new PosAmtGroup50SP2(context);
                    group.decode(message);
                    posAmtGroups[i] = group;
                }
            }
        }
        if (TRD_CAP_RPT_ACK_SIDE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSides != null && noSides.intValue() > 0) {
                message.reset();
                trdCapRptAckSideGroups = new TrdCapRptAckSideGroup[noSides.intValue()];
                for (int i = 0; i < noSides.intValue(); i++) {
                    TrdCapRptAckSideGroup component = new TrdCapRptAckSideGroup50SP2(context);
                    component.decode(message);
                    trdCapRptAckSideGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradeCaptureReportAckMsg] message version [5.0SP2].";
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
