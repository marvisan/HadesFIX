/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestMsg.java
 *
 * $Id: TradeCaptureReportRequestMsg.java,v 1.2 2011-10-21 10:31:03 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradeHandlingInstr;

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
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.TrdCapDtGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SecondaryTrdType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradeRequestType;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.type.TrdType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Trade Capture Report Request can be used to:<br/>
 * • Request one or more trade capture reports based upon selection criteria provided on the trade capture report request<br/>
 * • Subscribe for trade capture reports based upon selection criteria provided on the trade capture report request.<br/>
 * The following criteria can be specified on the Trade Capture Report Request:<br/>
 * • All trades matching specified trade identification: TradeReportID, SecondaryTradeReportID<br/>
 * • All trades matching specified trade types: TrdType, TrdSubType, TransferReason, SecondaryTrdType,TradeLinkID<br/>
 * • All trades matching the order identification information: OrderId, ClOrdID, ExecID<br/>
 * • Trades that have specified MatchStatus<br/>
 * • All trades for the party defined in the component block - Parties<br/>
 * • This can be a trader id, firm, broker id, clearing firm<br/>
 * • All trades for a specific instrument, specified using the component block <Instrument>, the component block - UnderlyingInstrument>
 * and/or the component block InstrumentLeg.<br/>
 * • All unreported trades – Executions that have not been sent<br/>
 * • All unmatched trades – Trades that have not been matched<br/>
 * • All trades matching specific date and trading session criteria<br/>
 * • Trades entered via a specific TradeInputSource<br/>
 * • Trades entered via a specific TradeInputDevice<br/>
 * • All Advisories<br/>
 * Each field in the Trade Capture Report Request (other than TradeRequestID and SubscriptionRequestType) identify
 * filters - trade reports that satisfy all Specified filters will be returned. Note that the filters are combined using an
 * implied "and" - a trade report must satisfy every specified filter to be returned.
 * The optional date or time range-specific filter criteria (within NoDates repeating group) can be used in one of two modes:<br/>
 * • "Since" a time period. NoDates=1 with first TradeDate (and optional TransactTime) indicating the "since" 
 * (greater than or equal to operation) point in time.<br/>
 * • "Between" time periods. NoDates=2 with first TradeDate (and optional TransactTime) indicating the "beginning" 
 * (greater than or equal to operation) point in time and the second TradeDate (and optional TransactTime) indicating 
 * the "ending" (less than or equal to operation) point in time. Trade Capture Report messages are the normal return 
 * type to a Trade Capture Report Request.<br/>
 * The response to a Trade Capture Report Request can be:<br/>
 * • One or more Trade Capture Reports<br/>
 * • A Trade Capture Report Request Ack followed by one or more Trade Capture Reports in two specific cases:<br/>
 * • When the Trade Capture Reports are being delivered out of band (such as a file transfer),<br/>
 * • When there is a processing delay between the time of the request and when the reports will be sent (for instance in a 
 * distributed trading environment where trades are distributed across multiple trading systems).<br/>
 * • A Trade Capture Report Ack only<br/>
 * • When no trades are found that match the selection criteria specified on the Trade Capture Report Request<br/>
 * • When the Trade Capture Report Request was deemed invalid for business reasons by the counterparty
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TradeCaptureReportRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradeRequestID.getValue(),
        TagNum.TradeID.getValue(),
        TagNum.SecondaryTradeID.getValue(),
        TagNum.FirmTradeID.getValue(),
        TagNum.SecondaryFirmTradeID.getValue(),
        TagNum.TradeRequestType.getValue(),
        TagNum.SubscriptionRequestType.getValue(),
        TagNum.TradeReportID.getValue(),
        TagNum.SecondaryTradeReportID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.MatchStatus.getValue(),
        TagNum.TrdType.getValue(),
        TagNum.TrdSubType.getValue(),
        TagNum.TradeHandlingInstr.getValue(),
        TagNum.TransferReason.getValue(),
        TagNum.SecondaryTrdType.getValue(),
        TagNum.TradeLinkID.getValue(),
        TagNum.TrdMatchID.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoDates.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.TimeBracket.getValue(),
        TagNum.Side.getValue(),
        TagNum.MultiLegReportingType.getValue(),
        TagNum.TradeInputSource.getValue(),
        TagNum.TradeInputDevice.getValue(),
        TagNum.ResponseTransportType.getValue(),
        TagNum.ResponseDestination.getValue(),
        TagNum.Text.getValue(),
        TagNum.MessageEventSource.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradeRequestID.getValue(),
        TagNum.TradeRequestType.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 568 REQUIRED. Starting with 4.3 version.
     */
    protected String tradeRequestID;
    
    /**
     * TagNum = 1003. Starting with 5.0 version.
     */
    protected String tradeID;
    
    /**
     * TagNum = 1040. Starting with 5.0 version.
     */
    protected String secondaryTradeID;
    
    /**
     * TagNum = 1041. Starting with 5.0 version.
     */
    protected String firmTradeID;
    
    /**
     * TagNum = 1042. Starting with 5.0 version.
     */
    protected String secondaryFirmTradeID;
    
    /**
     * TagNum = 569 REQUIRED. Starting with 4.3 version.
     */
    protected TradeRequestType tradeRequestType;

    /**
     * TagNum = 263. Starting with 4.3 version.
     */
    protected SubscriptionRequestType subscriptionRequestType;
    
    /**
     * TagNum = 571. Starting with 4.4 version.
     */
    protected String tradeReportID;
    
    /**
     * TagNum = 818. Starting with 4.4 version.
     */
    protected String secondaryTradeReportID;
    
    /**
     * TagNum = 17. Starting with 4.3 version.
     */
    protected String execID;

    /**
     * TagNum = 150. Starting with 4.4 version.
     */
    protected ExecType execType;
    
     /**
     * TagNum = 37. Starting with 4.3 version.
     */
    protected String orderID;

    /**
     * TagNum = 11. Starting with 4.3 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 573. Starting with 4.3 version.
     */
    protected MatchStatus matchStatus;

    /**
     * TagNum = 828. Starting with 4.4 version.
     */
    protected TrdType trdType;

    /**
     * TagNum = 829. Starting with 4.4 version.
     */
    protected TrdSubType trdSubType;

    /**
     * TagNum = 1123. Starting with 5.0 version.
     */
    protected TradeHandlingInstr tradeHandlingInstr;

    /**
     * TagNum = 830. Starting with 4.4 version.
     */
    protected String transferReason;

    /**
     * TagNum = 855. Starting with 4.4 version.
     */
    protected SecondaryTrdType secondaryTrdType;

    /**
     * TagNum = 820. Starting with 4.4 version.
     */
    protected String tradeLinkID;

    /**
     * TagNum = 880. Starting with 4.4 version.
     */
    protected String trdMatchID;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;
            
    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentExtension instrumentExtension;

    /**
     * Starting with 4.4 version.
     */
    protected FinancingDetails financingDetails;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;
    
    /**
     * TagNum = 580. Starting with 4.3 version.
     */
    protected Integer noDates;
    
    /**
     * Starting with 4.3 version.
     */
    protected TrdCapDtGroup[] trdCapDtGroups;

    /**
     * TagNum = 715. Starting with 4.4 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 336. Starting with 4.4 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.4 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 943. Starting with 4.4 version.
     */
    protected String timeBracket;

    /**
     * TagNum = 54. Starting with 4.3 version.
     */
    protected Side side;

    /**
     * TagNum = 442. Starting with 4.4 version.
     */
    protected MultiLegReportingType multilegReportingType;

    /**
     * TagNum = 578. Starting with 4.3 version.
     */
    protected String tradeInputSource;

    /**
     * TagNum = 579. Starting with 4.3 version.
     */
    protected String tradeInputDevice;

    /**
     * TagNum = 725. Starting with 4.4 version.
     */
    protected ResponseTransportType responseTransportType;

    /**
     * TagNum = 726. Starting with 4.4 version.
     */
    protected String responseDestination;
    
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
     * TagNum = 1011. Starting with 5.0 version.
     */
    protected String messageEventSource;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportRequestMsg() {
        super();
    }

    public TradeCaptureReportRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public TradeCaptureReportRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.TradeCaptureReportRequest.getValue(), beginString);
    }

    public TradeCaptureReportRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.TradeCaptureReportRequest.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.TradeRequestID, required=true)
    public String getTradeRequestID() {
        return tradeRequestID;
    }

    /**
     * Message field setter.
     * @param tradeRequestID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeRequestID, required=true)
    public void setTradeRequestID(String tradeRequestID) {
        this.tradeRequestID = tradeRequestID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeID)
    public String getTradeID() {
        return tradeID;
    }

    /**
     * Message field setter.
     * @param tradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeID)
    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryTradeID)
    public String getSecondaryTradeID() {
        return secondaryTradeID;
    }

    /**
     * Message field setter.
     * @param secondaryTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryTradeID)
    public void setSecondaryTradeID(String secondaryTradeID) {
        this.secondaryTradeID = secondaryTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirmTradeID)
    public String getFirmTradeID() {
        return firmTradeID;
    }

    /**
     * Message field setter.
     * @param firmTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirmTradeID)
    public void setFirmTradeID(String firmTradeID) {
        this.firmTradeID = firmTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryFirmTradeID)
    public String getSecondaryFirmTradeID() {
        return secondaryFirmTradeID;
    }

    /**
     * Message field setter.
     * @param secondaryFirmTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryFirmTradeID)
    public void setSecondaryFirmTradeID(String secondaryFirmTradeID) {
        this.secondaryFirmTradeID = secondaryFirmTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeRequestType, required=true)
    public TradeRequestType getTradeRequestType() {
        return tradeRequestType;
    }

    /**
     * Message field setter.
     * @param tradeRequestType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeRequestType, required=true)
    public void setTradeRequestType(TradeRequestType tradeRequestType) {
        this.tradeRequestType = tradeRequestType;
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportID)
    public String getTradeReportID() {
        return tradeReportID;
    }

    /**
     * Message field setter.
     * @param tradeReportID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeReportID)
    public void setTradeReportID(String tradeReportID) {
        this.tradeReportID = tradeReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTradeReportID)
    public String getSecondaryTradeReportID() {
        return secondaryTradeReportID;
    }

    /**
     * Message field setter.
     * @param secondaryTradeReportID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTradeReportID)
    public void setSecondaryTradeReportID(String secondaryTradeReportID) {
        this.secondaryTradeReportID = secondaryTradeReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecID)
    public String getExecID() {
        return execID;
    }

    /**
     * Message field setter.
     * @param execID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecID)
    public void setExecID(String execID) {
        this.execID = execID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExecType)
    public ExecType getExecType() {
        return execType;
    }

    /**
     * Message field setter.
     * @param execType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExecType)
    public void setExecType(ExecType execType) {
        this.execType = execType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    /**
     * Message field setter.
     * @param matchStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdType)
    public TrdType getTrdType() {
        return trdType;
    }

    /**
     * Message field setter.
     * @param trdType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdType)
    public void setTrdType(TrdType trdType) {
        this.trdType = trdType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdSubType)
    public TrdSubType getTrdSubType() {
        return trdSubType;
    }

    /**
     * Message field setter.
     * @param trdSubType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdSubType)
    public void setTrdSubType(TrdSubType trdSubType) {
        this.trdSubType = trdSubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeHandlingInstr)
    public TradeHandlingInstr getTradeHandlingInstr() {
        return tradeHandlingInstr;
    }

    /**
     * Message field setter.
     * @param tradeHandlingInstr field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeHandlingInstr)
    public void setTradeHandlingInstr(TradeHandlingInstr tradeHandlingInstr) {
        this.tradeHandlingInstr = tradeHandlingInstr;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransferReason)
    public String getTransferReason() {
        return transferReason;
    }

    /**
     * Message field setter.
     * @param transferReason field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransferReason)
    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTrdType)
    public SecondaryTrdType getSecondaryTrdType() {
        return secondaryTrdType;
    }

    /**
     * Message field setter.
     * @param secondaryTrdType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryTrdType)
    public void setSecondaryTrdType(SecondaryTrdType secondaryTrdType) {
        this.secondaryTrdType = secondaryTrdType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeLinkID)
    public String getTradeLinkID() {
        return tradeLinkID;
    }

    /**
     * Message field setter.
     * @param tradeLinkID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeLinkID)
    public void setTradeLinkID(String tradeLinkID) {
        this.tradeLinkID = tradeLinkID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdMatchID)
    public String getTrdMatchID() {
        return trdMatchID;
    }

    /**
     * Message field setter.
     * @param trdMatchID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TrdMatchID)
    public void setTrdMatchID(String trdMatchID) {
        this.trdMatchID = trdMatchID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentExtension getInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void setInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void clearInstrumentExtension() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public FinancingDetails getFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the FinancingDetails component class to the proper implementation.
     */
    @FIXVersion(introduced = "4.4")
    public void setFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the FinancingDetails component to null.
     */
    @FIXVersion(introduced = "4.4")
    public void clearFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public Integer getNoUnderlyings() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingInstrument} components. It will also create an array
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public void setNoUnderlyings(Integer noUnderlyings) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UnderlyingInstrument} object to the existing array of <code>underlyingInstruments</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyings</code> method has been called there will already be a number of objects in the
     * <code>underlyingInstruments</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument addUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingInstrument} object from the existing array of <code>underlyingInstruments</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingInstrument} objects from the <code>underlyingInstruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyings</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrumentLeg} components. It will also create an array
     * of {@link InstrumentLeg} objects and set the <code>instrumentLegs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrumentLegs</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrumentLeg} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public InstrumentLeg[] getInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrumentLeg} object to the existing array of <code>instrumentLegs</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>instrumentLegs</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg addInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrumentLeg} object from the existing array of <code>instrumentLegs</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg deleteInstrumentLeg(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentLeg} objects from the <code>instrumentLegs</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoDates)
    public Integer getNoDates() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * This method sets the number of {@link TrdCapDtGroup} components. It will also create an array
     * of {@link TrdCapDtGroup} objects and set the <code>trdCapDtGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdCapDtGroups</code> array they will be discarded.<br/>
     * @param noDates field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoDates)
    public void setNoDates(Integer noDates) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdCapDtGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public TrdCapDtGroup[] getTrdCapDtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link TrdCapDtGroup} object to the existing array of <code>trdCapDtGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noDates</code> field to the proper value.<br/>
     * Note: If the <code>setNoDates</code> method has been called there will already be a number of objects in the
     * <code>trdCapDtGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.3")
    public TrdCapDtGroup addTrdCapDtGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method deletes a {@link TrdCapDtGroup} object from the existing array of <code>trdCapDtGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDates</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.3")
    public TrdCapDtGroup deleteTrdCapDtGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Deletes all the {@link TrdCapDtGroup} objects from the <code>trdCapDtGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDates</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3")
    public int clearTrdCapDtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TimeBracket)
    public String getTimeBracket() {
        return timeBracket;
    }

    /**
     * Message field setter.
     * @param timeBracket field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TimeBracket)
    public void setTimeBracket(String timeBracket) {
        this.timeBracket = timeBracket;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Side, required=true)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Side, required=true)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    /**
     * Message field setter.
     * @param multilegReportingType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeInputSource)
    public String getTradeInputSource() {
        return tradeInputSource;
    }

    /**
     * Message field setter.
     * @param tradeInputSource field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeInputSource)
    public void setTradeInputSource(String tradeInputSource) {
        this.tradeInputSource = tradeInputSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeInputDevice)
    public String getTradeInputDevice() {
        return tradeInputDevice;
    }

    /**
     * Message field setter.
     * @param tradeInputDevice field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradeInputDevice)
    public void setTradeInputDevice(String tradeInputDevice) {
        this.tradeInputDevice = tradeInputDevice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseTransportType)
    public ResponseTransportType getResponseTransportType() {
        return responseTransportType;
    }

    /**
     * Message field setter.
     * @param responseTransportType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseTransportType)
    public void setResponseTransportType(ResponseTransportType responseTransportType) {
        this.responseTransportType = responseTransportType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseDestination)
    public String getResponseDestination() {
        return responseDestination;
    }

    /**
     * Message field setter.
     * @param responseDestination field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseDestination)
    public void setResponseDestination(String responseDestination) {
        this.responseDestination = responseDestination;
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MessageEventSource)
    public String getMessageEventSource() {
        return messageEventSource;
    }

    /**
     * Message field setter.
     * @param messageEventSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MessageEventSource)
    public void setMessageEventSource(String messageEventSource) {
        this.messageEventSource = messageEventSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (tradeRequestID == null || tradeRequestID.trim().isEmpty()) {
            errorMsg.append(" [TradeRequestID]");
            hasMissingTag = true;
        }
        if (tradeRequestType == null) {
            errorMsg.append(" [TradeRequestType]");
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
            TagEncoder.encode(bao, TagNum.TradeRequestID, tradeRequestID);
            TagEncoder.encode(bao, TagNum.TradeID, tradeID);
            TagEncoder.encode(bao, TagNum.SecondaryTradeID, secondaryTradeID);
            TagEncoder.encode(bao, TagNum.FirmTradeID, firmTradeID);
            TagEncoder.encode(bao, TagNum.SecondaryFirmTradeID, secondaryFirmTradeID);
            if (tradeRequestType != null) {
                TagEncoder.encode(bao, TagNum.TradeRequestType, tradeRequestType.getValue());
            }
            if (subscriptionRequestType != null) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeReportID, tradeReportID);
            TagEncoder.encode(bao, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
            TagEncoder.encode(bao, TagNum.ExecID, execID);
            if (execType != null) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            if (matchStatus != null) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (trdType != null) {
                TagEncoder.encode(bao, TagNum.TrdType, trdType.getValue());
            }
            if (trdSubType != null) {
                TagEncoder.encode(bao, TagNum.TrdSubType, trdSubType.getValue());
            }
            if (tradeHandlingInstr != null) {
                TagEncoder.encode(bao, TagNum.TradeHandlingInstr, tradeHandlingInstr.getValue());
            }
            TagEncoder.encode(bao, TagNum.TransferReason, transferReason);
            if (secondaryTrdType != null) {
                TagEncoder.encode(bao, TagNum.SecondaryTrdType, secondaryTrdType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeLinkID, tradeLinkID);
            TagEncoder.encode(bao, TagNum.TrdMatchID, trdMatchID);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (instrumentExtension != null) {
                bao.write(instrumentExtension.encode(MsgSecureType.ALL_FIELDS));
            }
            if (financingDetails != null) {
                bao.write(financingDetails.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noUnderlyings != null) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            if (noDates != null) {
                TagEncoder.encode(bao, TagNum.NoDates, noDates);
                if (trdCapDtGroups != null && trdCapDtGroups.length == noDates.intValue()) {
                    for (int i = 0; i < noDates.intValue(); i++) {
                        if (trdCapDtGroups[i] != null) {
                            bao.write(trdCapDtGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdCapDtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoDates.getValue(), error);
                }
            }
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encode(bao, TagNum.TimeBracket, timeBracket);
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (multilegReportingType != null) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeInputSource, tradeInputSource);
            TagEncoder.encode(bao, TagNum.TradeInputDevice, tradeInputDevice);
            if (responseTransportType != null) {
                TagEncoder.encode(bao, TagNum.ResponseTransportType, responseTransportType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ResponseDestination, responseDestination);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            TagEncoder.encode(bao, TagNum.MessageEventSource, messageEventSource);
 
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
            case TradeRequestID:
                tradeRequestID = new String(tag.value, sessionCharset);
                break;

            case TradeID:
                tradeID = new String(tag.value, sessionCharset);
                break;
                
            case SecondaryTradeID:
                secondaryTradeID = new String(tag.value, sessionCharset);
                break;
                
            case FirmTradeID:
                firmTradeID = new String(tag.value, sessionCharset);
                break;
                
            case SecondaryFirmTradeID:
                secondaryFirmTradeID = new String(tag.value, sessionCharset);
                break;

            case TradeRequestType:
                tradeRequestType = TradeRequestType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SubscriptionRequestType:
                subscriptionRequestType = SubscriptionRequestType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;
               
            case TradeReportID:
                tradeReportID = new String(tag.value, sessionCharset);
                break;
               
            case SecondaryTradeReportID:
                secondaryTradeReportID = new String(tag.value, sessionCharset);
                break;

            case ExecID:
                execID = new String(tag.value, sessionCharset);
                break;

            case ExecType:
                execType = ExecType.valueFor(new String(tag.value, sessionCharset));
                break;

            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;
                
            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case MatchStatus:
                matchStatus = MatchStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TrdType:
                trdType = TrdType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TrdSubType:
                trdSubType = TrdSubType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradeHandlingInstr:
                tradeHandlingInstr = TradeHandlingInstr.valueFor(new String(tag.value, sessionCharset));
                break;
                
            case TransferReason:
                transferReason = new String(tag.value, sessionCharset);
                break;

            case SecondaryTrdType:
                secondaryTrdType = SecondaryTrdType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;
                
            case TradeLinkID:
                tradeLinkID = new String(tag.value, sessionCharset);
                break;
                
            case TrdMatchID:
                trdMatchID = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case NoDates:
                noDates = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case TimeBracket:
                timeBracket = new String(tag.value, sessionCharset);
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case MultiLegReportingType:
                multilegReportingType = MultiLegReportingType.valueFor(new String(tag.value, sessionCharset));
                break;

            case TradeInputSource:
                tradeInputSource = new String(tag.value, sessionCharset);
                break;

            case TradeInputDevice:
                tradeInputDevice = new String(tag.value, sessionCharset);
                break;

            case ResponseTransportType:
                responseTransportType = ResponseTransportType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ResponseDestination:
                responseDestination = new String(tag.value, sessionCharset);
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case MessageEventSource:
                messageEventSource = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TradeCaptureReportRequestMsg] fields.";
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
        StringBuilder b = new StringBuilder("{TradeCaptureReportRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.TradeRequestID, tradeRequestID);
        printTagValue(b, TagNum.TradeID, tradeID);
        printTagValue(b, TagNum.SecondaryTradeID, secondaryTradeID);
        printTagValue(b, TagNum.FirmTradeID, firmTradeID);
        printTagValue(b, TagNum.SecondaryFirmTradeID, secondaryFirmTradeID);
        printTagValue(b, TagNum.TradeRequestType, tradeRequestType);
        printTagValue(b, TagNum.SubscriptionRequestType, subscriptionRequestType);
        printTagValue(b, TagNum.TradeReportID, tradeReportID);
        printTagValue(b, TagNum.SecondaryTradeReportID, secondaryTradeReportID);
        printTagValue(b, TagNum.ExecID, execID);
        printTagValue(b, TagNum.ExecType, execType);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.MatchStatus, matchStatus);
        printTagValue(b, TagNum.TrdType, trdType);
        printTagValue(b, TagNum.TrdSubType, trdSubType);
        printTagValue(b, TagNum.TradeHandlingInstr, tradeHandlingInstr);
        printTagValue(b, TagNum.TransferReason, transferReason);
        printTagValue(b, TagNum.SecondaryTrdType, secondaryTrdType);
        printTagValue(b, TagNum.TradeLinkID, tradeLinkID);
        printTagValue(b, TagNum.TrdMatchID, trdMatchID);
        printTagValue(b, parties);
        printTagValue(b, instrument);
        printTagValue(b, instrumentExtension);
        printTagValue(b, financingDetails);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.NoDates, noDates);
        printTagValue(b, trdCapDtGroups);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.TimeBracket, timeBracket);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.MultiLegReportingType, multilegReportingType);
        printTagValue(b, TagNum.TradeInputSource, tradeInputSource);
        printTagValue(b, TagNum.TradeInputDevice, tradeInputDevice);
        printTagValue(b, TagNum.ResponseTransportType, responseTransportType);
        printTagValue(b, TagNum.ResponseDestination, responseDestination);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.MessageEventSource, messageEventSource);        
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
