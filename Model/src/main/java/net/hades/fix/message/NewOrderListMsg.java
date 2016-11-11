/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsg.java
 *
 * $Id: NewOrderListMsg.java,v 1.4 2011-05-12 09:26:09 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderListGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.ContingencyType;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustomerOrFirm;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.ListExecInstType;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.ProgRptReqs;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The NewOrderList Message can be used in one of two ways depending on which market conventions are being
 * followed.<br/>
 * In the “Non disclosed” convention the New Order - List message is sent after the bidding process has been
 * completed, by telephone or electronically. The New Order - List message enumerates the stocks, quantities,
 * direction for the trade and may contain pre-allocation information.
 * This message may also be used as the first message for the transmission of a program trade where the bidding
 * process has been done by means other than FIX. In this scenario the messages may either be used as a staging
 * process, in which case the broker will start execution once either a ListExecute is received or for immediate
 * execution, in which case the orders will be executed on receipt.<br/>
 * In the “Disclosed” convention the New Order - List message is sent before the bidding process is started, by
 * telephone or electronically. The New Order - List message enumerates the stocks and quantities from the
 * bidding process, and may contain pre-allocation information. The direction of the trade is disclosed after the
 * bidding process is completed.
 * Where multiple waves of a program trade are submitted by an institution or retail intermediaries, as a series of
 * separate lists, to a broker ClOrdLinkID may be used to link the orders together.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 29/01/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class NewOrderListMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.BidID.getValue(),
        TagNum.ClientBidID.getValue(),
        TagNum.ProgRptReqs.getValue(),
        TagNum.BidType.getValue(),
        TagNum.ProgPeriodInterval.getValue(),
        TagNum.CancellationRights.getValue(),
        TagNum.MoneyLaunderingStatus.getValue(),
        TagNum.RegistID.getValue(),
        TagNum.ListExecInstType.getValue(),
        TagNum.ListExecInst.getValue(),
        TagNum.ContingencyType.getValue(),
        TagNum.AllowableOneSidednessPct.getValue(),
        TagNum.AllowableOneSidednessValue.getValue(),
        TagNum.AllowableOneSidednessCurr.getValue(),
        TagNum.TotNoOrders.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoOrders.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedListExecInstLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.BidType.getValue(),
        TagNum.TotNoOrders.getValue(),
        TagNum.NoOrders.getValue()
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
     * TagNum = 390. Starting with 4.2 version.
     */
    protected String bidID;

    /**
     * TagNum = 391. Starting with 4.2 version.
     */
    protected String clientBidID;

    /**
     * TagNum = 414. Starting with 4.2 version.
     */
    protected ProgRptReqs progRptReqs;

    /**
     * TagNum = 394 REQUIRED. Starting with 4.2 version.
     */
    protected BidType bidType;

    /**
     * TagNum = 415. Starting with 4.2 version.
     */
    protected Integer progPeriodInterval;

    /**
     * TagNum = 480. Starting with 4.3 version.
     */
    protected CancellationRights cancellationRights;

    /**
     * TagNum = 481. Starting with 4.3 version.
     */
    protected MoneyLaunderingStatus moneyLaunderingStatus;

    /**
     * TagNum = 513. Starting with 4.3 version.
     */
    protected String registID;

    /**
     * TagNum = 433. Starting with 4.2 version.
     */
    protected ListExecInstType listExecInstType;

    /**
     * TagNum = 69. Starting with 4.0 version.
     */
    protected String listExecInst;

    /**
     * TagNum = 1385. Starting with 5.0SP1 version.
     */
    protected ContingencyType contingencyType;

    /**
     * TagNum = 352. Starting with 4.2 version.
     */
    protected Integer encodedListExecInstLen;

    /**
     * TagNum = 353. Starting with 4.2 version.
     */
    protected byte[] encodedListExecInst;

    /**
     * TagNum = 765. Starting with 4.4 version.
     */
    protected Double allowableOneSidednessPct;

    /**
     * TagNum = 766. Starting with 4.4 version.
     */
    protected Double allowableOneSidednessValue;

    /**
     * TagNum = 767. Starting with 4.4 version.
     */
    protected Currency allowableOneSidednessCurr;

    /**
     * TagNum = 68 REQUIRED. Starting with 4.0 version.
     */
    protected Integer totNoOrders;

    /**
     * TagNum = 893. Starting with 4.4 version.
     */
    protected Boolean lastFragment;
    
    /**
     * Starting with 5.0 version.
     */
    protected RootParties rootParties;

    /**
     * TagNum = 68 REQUIRED. Starting with 4.2 version.
     */
    protected Integer noOrders;

    /**
     * Starting with 4.2 version.
     */
    protected OrderListGroup[] orderListGroups;

    /**
     * TagNum = 105. Starting with 4.0 version.
     */
    protected String waveNo;

    /**
     * TagNum = 67. Starting with 4.0 version.
     */
    protected Integer listSeqNo;

    /**
     * TagNum = 11 REQUIRED. Starting with 4.0 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 109. Starting with 4.0 version.
     */
    protected String clientID;

    /**
     * TagNum = 76. Starting with 4.0 version.
     */
    protected String execBroker;

    /**
     * TagNum = 1. Starting with 4.0 version.
     */
    protected String account;

    /**
     * TagNum = 63. Starting with 4.0 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.0 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 21. Starting with 4.0 version.
     */
    protected HandlInst handlInst;

    /**
     * TagNum = 18. Starting with 4.0 version.
     */
    protected String execInst;

    /**
     * TagNum = 110. Starting with 4.0 version.
     */
    protected Double minQty;

    /**
     * TagNum = 111. Starting with 4.0 version.
     */
    protected Double maxFloor;

    /**
     * TagNum = 100. Starting with 4.0 version.
     */
    protected String exDestination;

    /**
     * TagNum = 81. Starting with 4.0 version.
     */
    protected ProcessCode processCode;

    /**
     * TagNum = 55. Starting with 4.0 version.
     */
    protected String symbol;

    /**
     * TagNum = 65. Starting with 4.0 version.
     */
    protected String symbolSfx;

    /**
     * TagNum = 48. Starting with 4.0 version.
     */
    protected String securityID;

    /**
     * TagNum = 22. Starting with 4.0 version.
     */
    protected String securityIDSource;

    /**
     * TagNum = 167. Starting with 4.1 version.
     */
    protected String securityType;

    /**
     * TagNum = 200. Starting with 4.1 version.
     */
    protected String maturityMonthYear;

    /**
     * TagNum = 205. Starting with 4.1 version.
     */
    protected Integer maturityDay;

    /**
     * TagNum = 201. Starting with 4.1 version.
     */
    protected PutOrCall putOrCall;

    /**
     * TagNum = 202. Starting with 4.1 version.
     */
    protected Double strikePrice;

    /**
     * TagNum = 206. Starting with 4.1 version.
     */
    protected Character optAttribute;

    /**
     * TagNum = 207. Starting with 4.1 version.
     */
    protected String securityExchange;

    /**
     * TagNum = 106. Starting with 4.0 version.
     */
    protected String issuer;

    /**
     * TagNum = 107. Starting with 4.0 version.
     */
    protected String securityDesc;

    /**
     * TagNum = 140. Starting with 4.0 version.
     */
    protected Double prevClosePx;

    /**
     * TagNum = 54 REQUIRED. Starting with 4.0 version.
     */
    protected Side side;

    /**
     * TagNum = 114. Starting with 4.0 version.
     */
    protected Boolean locateReqd;

    /**
     * TagNum = 38. Starting with 4.0 version.
     */
    protected Double orderQty;

    /**
     * TagNum = 40. Starting with 4.0 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 44. Starting with 4.0 version.
     */
    protected Double price;

    /**
     * TagNum = 99. Starting with 4.0 version.
     */
    protected Double stopPx;

    /**
     * TagNum = 211. Starting with 4.1 version.
     */
    protected Double pegOffsetValue;

    /**
     * TagNum = 15. Starting with 4.0 version.
     */
    protected Currency currency;

    /**
     * TagNum = 59. Starting with 4.0 version.
     */
    protected TimeInForce timeInForce;

    /**
     * TagNum = 126. Starting with 4.0 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 12. Starting with 4.0 version.
     */
    protected Double commission;

    /**
     * TagNum = 13. Starting with 4.0 version.
     */
    protected CommType commType;
    
    /**
     * TagNum = 47. Starting with 4.0 version.
     */
    protected Rule80A rule80A;

    /**
     * TagNum = 121. Starting with 4.0 version.
     */
    protected Boolean forexReq;

    /**
     * TagNum = 120. Starting with 4.0 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 58. Starting with 4.0 version.
     */
    protected String text;

    /**
     * TagNum = 193. Starting with 4.1 version.
     */
    protected Date settlDate2;

    /**
     * TagNum = 192. Starting with 4.1 version.
     */
    protected Double orderQty2;

    /**
     * TagNum = 77. Starting with 4.1 version.
     */
    protected PositionEffect positionEffect;

    /**
     * TagNum = 203. Starting with 4.1 version.
     */
    protected CoveredOrUncovered coveredOrUncovered;

    /**
     * TagNum = 204. Starting with 4.1 version.
     */
    protected CustomerOrFirm customerOrFirm;

    /**
     * TagNum = 210. Starting with 4.1 version.
     */
    protected Double maxShow;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewOrderListMsg() {
        super();
    }

    public NewOrderListMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public NewOrderListMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.NewOrderList.getValue(), beginString);
    }

    public NewOrderListMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.NewOrderList.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.ListID)
    public String getListID() {
        return listID;
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListID)
    public void setListID(String listID) {
        this.listID = listID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidID)
    public String getBidID() {
        return bidID;
    }

    /**
     * Message field setter.
     * @param bidID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidID)
    public void setBidID(String bidID) {
        this.bidID = bidID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClientBidID)
    public String getClientBidID() {
        return clientBidID;
    }

    /**
     * Message field setter.
     * @param clientBidID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClientBidID)
    public void setClientBidID(String clientBidID) {
        this.clientBidID = clientBidID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgRptReqs)
    public ProgRptReqs getProgRptReqs() {
        return progRptReqs;
    }

    /**
     * Message field setter.
     * @param progRptReqs field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgRptReqs)
    public void setProgRptReqs(ProgRptReqs progRptReqs) {
        this.progRptReqs = progRptReqs;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidType)
    public BidType getBidType() {
        return bidType;
    }

    /**
     * Message field setter.
     * @param bidType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidType)
    public void setBidType(BidType bidType) {
        this.bidType = bidType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgPeriodInterval)
    public Integer getProgPeriodInterval() {
        return progPeriodInterval;
    }

    /**
     * Message field setter.
     * @param progPeriodInterval field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgPeriodInterval)
    public void setProgPeriodInterval(Integer progPeriodInterval) {
        this.progPeriodInterval = progPeriodInterval;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CancellationRights)
    public CancellationRights getCancellationRights() {
        return cancellationRights;
    }

    /**
     * Message field setter.
     * @param cancellationRights field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CancellationRights)
    public void setCancellationRights(CancellationRights cancellationRights) {
        this.cancellationRights = cancellationRights;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MoneyLaunderingStatus)
    public MoneyLaunderingStatus getMoneyLaunderingStatus() {
        return moneyLaunderingStatus;
    }

    /**
     * Message field setter.
     * @param moneyLaunderingStatus field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MoneyLaunderingStatus)
    public void setMoneyLaunderingStatus(MoneyLaunderingStatus moneyLaunderingStatus) {
        this.moneyLaunderingStatus = moneyLaunderingStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistID)
    public String getRegistID() {
        return registID;
    }

    /**
     * Message field setter.
     * @param registID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistID)
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListExecInstType)
    public ListExecInstType getListExecInstType() {
        return listExecInstType;
    }

    /**
     * Message field setter.
     * @param listExecInstType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListExecInstType)
    public void setListExecInstType(ListExecInstType listExecInstType) {
        this.listExecInstType = listExecInstType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListExecInst)
    public String getListExecInst() {
        return listExecInst;
    }

    /**
     * Message field setter.
     * @param listExecInst field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListExecInst)
    public void setListExecInst(String listExecInst) {
        this.listExecInst = listExecInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ContingencyType)
    public ContingencyType getContingencyType() {
        return contingencyType;
    }

    /**
     * Message field setter.
     * @param contingencyType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ContingencyType)
    public void setContingencyType(ContingencyType contingencyType) {
        this.contingencyType = contingencyType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListExecInstLen)
    public Integer getEncodedListExecInstLen() {
        return encodedListExecInstLen;
    }

    /**
     * Message field setter.
     * @param encodedListExecInstLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListExecInstLen)
    public void setEncodedListExecInstLen(Integer encodedListExecInstLen) {
        this.encodedListExecInstLen = encodedListExecInstLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListExecInst)
    public byte[] getEncodedListExecInst() {
        return encodedListExecInst;
    }

    /**
     * Message field setter.
     * @param encodedListExecInst field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListExecInst)
    public void setEncodedListExecInst(byte[] encodedListExecInst) {
        this.encodedListExecInst = encodedListExecInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllowableOneSidednessPct)
    public Double getAllowableOneSidednessPct() {
        return allowableOneSidednessPct;
    }

    /**
     * Message field setter.
     * @param allowableOneSidednessPct field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllowableOneSidednessPct)
    public void setAllowableOneSidednessPct(Double allowableOneSidednessPct) {
        this.allowableOneSidednessPct = allowableOneSidednessPct;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllowableOneSidednessValue)
    public Double getAllowableOneSidednessValue() {
        return allowableOneSidednessValue;
    }

    /**
     * Message field setter.
     * @param allowableOneSidednessValue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllowableOneSidednessValue)
    public void setAllowableOneSidednessValue(Double allowableOneSidednessValue) {
        this.allowableOneSidednessValue = allowableOneSidednessValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllowableOneSidednessCurr)
    public Currency getAllowableOneSidednessCurr() {
        return allowableOneSidednessCurr;
    }

    /**
     * Message field setter.
     * @param allowableOneSidednessCurr field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllowableOneSidednessCurr)
    public void setAllowableOneSidednessCurr(Currency allowableOneSidednessCurr) {
        this.allowableOneSidednessCurr = allowableOneSidednessCurr;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TotNoOrders, required = true)
    public Integer getTotNoOrders() {
        return totNoOrders;
    }

    /**
     * Message field setter.
     * @param totNoOrders field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TotNoOrders, required = true)
    public void setTotNoOrders(Integer totNoOrders) {
        this.totNoOrders = totNoOrders;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public Boolean getLastFragment() {
        return lastFragment;
    }

    /**
     * Message field setter.
     * @param lastFragment field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public RootParties getRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to the proper implementation.
     */
    @FIXVersion(introduced="5.0")
    public void setRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public Integer getNoOrders() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link OrderListGroup} groups. It will also create an array
     * of {@link OrderListGroup} objects and set the <code>orderListGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>orderListGroups</code> array they will be discarded.<br/>
     * @param noOrders field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public void setNoOrders(Integer noOrders) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link OrderListGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public OrderListGroup[] getOrderListGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link OrderListGroup} object to the existing array of <code>orderListGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * Note: If the <code>setNoOrders</code> method has been called there will already be a number of objects in the
     * <code>orderListGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public OrderListGroup addOrderListGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link OrderListGroup} object from the existing array of <code>orderListGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public OrderListGroup deleteOrderListGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link OrderListGroup} objects from the <code>orderListGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noOrders</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearOrderListGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public String getWaveNo() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param waveNo field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public void setWaveNo(String waveNo) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ListSeqNo)
    public Integer getListSeqNo() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param listSeqNo field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ListSeqNo)
    public void setListSeqNo(Integer listSeqNo) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
            
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.ClientID)
    public String getClientID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param clientID field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.ClientID)
    public void setClientID(String clientID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public String getExecBroker() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param execBroker field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public void setExecBroker(String execBroker) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Account)
    public void setAccount(String account) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public HandlInst getHandlInst() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param handlInst field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public void setHandlInst(HandlInst handlInst) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.MinQty)
    public Double getMinQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param minQty field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.MinQty)
    public void setMinQty(Double minQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public Double getMaxFloor() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maxFloor field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public void setMaxFloor(Double maxFloor) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public String getExDestination() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param exDestination field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public void setExDestination(String exDestination) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public ProcessCode getProcessCode() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Message field setter.
     * @param processCode field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public void setProcessCode(ProcessCode processCode) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
    public String getSymbol() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
    public void setSymbol(String symbol) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public String getSymbolSfx() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param symbolSfx field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public void setSymbolSfx(String symbolSfx) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public String getSecurityID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityID field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public void setSecurityID(String securityID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public String getSecurityIDSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityIDSource field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public void setSecurityIDSource(String securityIDSource) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public String getMaturityMonthYear() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maturityMonthYear field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public void setMaturityMonthYear(String maturityMonthYear) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public Integer getMaturityDay() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maturityDay field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public void setMaturityDay(Integer maturityDay) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public PutOrCall getPutOrCall() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param putOrCall field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public void setPutOrCall(PutOrCall putOrCall) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public Double getStrikePrice() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param strikePrice field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public void setStrikePrice(Double strikePrice) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public Character getOptAttribute() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param optAttribute field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public void setOptAttribute(Character optAttribute) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public String getSecurityExchange() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityExchange field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public void setSecurityExchange(String securityExchange) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Issuer)
    public String getIssuer() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param issuer field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Issuer)
    public void setIssuer(String issuer) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public String getSecurityDesc() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityDesc field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public void setSecurityDesc(String securityDesc) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public Double getPrevClosePx() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param prevClosePx field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public void setPrevClosePx(Double prevClosePx) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Side, required = true)
    public Side getSide() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.Side, required = true)
    public void setSide(Side side) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public Boolean getLocateReqd() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param locateReqd field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public void setLocateReqd(Boolean locateReqd) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.OrderQty)
    public Double getOrderQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param orderQty field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.OrderQty, required = true)
    public void setOrderQty(Double orderQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.OrdType, required = true)
    public OrdType getOrdType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.OrdType, required = true)
    public void setOrdType(OrdType ordType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.Price)
    public void setPrice(Double price) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.StopPx)
    public Double getStopPx() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param stopPx field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.StopPx)
    public void setStopPx(Double stopPx) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public Double getPegOffsetValue() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param pegOffsetValue field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public void setPegOffsetValue(Double pegOffsetValue) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public TimeInForce getTimeInForce() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param timeInForce field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public void setTimeInForce(TimeInForce timeInForce) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.Commission)
    public Double getCommission() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param commission field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.Commission)
    public void setCommission(Double commission) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.CommType)
    public CommType getCommType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param commType field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.CommType)
    public void setCommType(CommType commType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.Rule80A)
    public Rule80A getRule80A() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param rule80A field value
     */
    @FIXVersion(introduced="4.0", retired="4.2")
    @TagNumRef(tagNum=TagNum.Rule80A)
    public void setRule80A(Rule80A rule80A) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public Boolean getForexReq() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param forexReq field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public void setForexReq(Boolean forexReq) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlDate2)
    public Date getSettlDate2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlDate2 field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.SettlDate2)
    public void setSettlDate2(Date settlDate2) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.OrderQty2)
    public Double getOrderQty2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param orderQty2 field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.OrderQty2)
    public void setOrderQty2(Double orderQty2) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public PositionEffect getPositionEffect() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param positionEffect field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public void setPositionEffect(PositionEffect positionEffect) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.CoveredOrUncovered)
    public CoveredOrUncovered getCoveredOrUncovered() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param coveredOrUncovered field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.CoveredOrUncovered)
    public void setCoveredOrUncovered(CoveredOrUncovered coveredOrUncovered) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.2")
    @TagNumRef(tagNum=TagNum.CustomerOrFirm)
    public CustomerOrFirm getCustomerOrFirm() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param customerOrFirm field value
     */
    @FIXVersion(introduced="4.1", retired="4.2")
    @TagNumRef(tagNum=TagNum.CustomerOrFirm)
    public void setCustomerOrFirm(CustomerOrFirm customerOrFirm) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public Double getMaxShow() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maxShow field value
     */
    @FIXVersion(introduced="4.1", retired = "4.2")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public void setMaxShow(Double maxShow) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
        if (bidType == null) {
            errorMsg.append(" [BidType]");
            hasMissingTag = true;
        }
        if (totNoOrders == null) {
            errorMsg.append(" [TotNoOrders]");
            hasMissingTag = true;
        }
        if (noOrders == null) {
            errorMsg.append(" [NoOrders]");
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
            TagEncoder.encode(bao, TagNum.BidID, bidID);
            TagEncoder.encode(bao, TagNum.ClientBidID, clientBidID);
            if (progRptReqs != null) {
                TagEncoder.encode(bao, TagNum.ProgRptReqs, progRptReqs.getValue());
            }
            if (bidType != null) {
                TagEncoder.encode(bao, TagNum.BidType, bidType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ProgPeriodInterval, progPeriodInterval);
            if (cancellationRights != null) {
                TagEncoder.encode(bao, TagNum.CancellationRights, cancellationRights.getValue());
            }
            if (moneyLaunderingStatus != null) {
                TagEncoder.encode(bao, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.RegistID, registID);
            if (listExecInstType != null) {
                TagEncoder.encode(bao, TagNum.ListExecInstType, listExecInstType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ListExecInst, listExecInst);
            if (contingencyType != null) {
                TagEncoder.encode(bao, TagNum.ContingencyType, contingencyType.getValue());
            }
            if (encodedListExecInstLen != null && encodedListExecInstLen.intValue() > 0) {
                if (encodedListExecInst != null && encodedListExecInst.length > 0) {
                    encodedListExecInstLen = new Integer(encodedListExecInst.length);
                    TagEncoder.encode(bao, TagNum.EncodedListExecInstLen, encodedListExecInstLen);
                    TagEncoder.encode(bao, TagNum.EncodedListExecInst, encodedListExecInst);
                }
            }
            TagEncoder.encode(bao, TagNum.AllowableOneSidednessPct, allowableOneSidednessPct);
            TagEncoder.encode(bao, TagNum.AllowableOneSidednessValue, allowableOneSidednessValue);
            if (allowableOneSidednessCurr != null) {
                TagEncoder.encode(bao, TagNum.AllowableOneSidednessCurr, allowableOneSidednessCurr.getValue());
            }
            TagEncoder.encode(bao, TagNum.TotNoOrders, totNoOrders);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (rootParties != null) {
                bao.write(rootParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noOrders != null) {
                TagEncoder.encode(bao, TagNum.NoOrders, noOrders);
                if (orderListGroups != null && orderListGroups.length == noOrders.intValue()) {
                    for (int i = 0; i < noOrders.intValue(); i++) {
                        if (orderListGroups[i] != null) {
                            bao.write(orderListGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "OrderListGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoOrders.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.WaveNo, waveNo);
            TagEncoder.encode(bao, TagNum.ListSeqNo, listSeqNo);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.ClientID, clientID);
            TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            TagEncoder.encode(bao, TagNum.Account, account);
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            if (handlInst != null) {
                TagEncoder.encode(bao, TagNum.HandlInst, handlInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            TagEncoder.encode(bao, TagNum.MinQty, minQty);
            TagEncoder.encode(bao, TagNum.MaxFloor, maxFloor);
            TagEncoder.encode(bao, TagNum.ExDestination, exDestination);
            if (processCode != null) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            TagEncoder.encode(bao, TagNum.Symbol, symbol);
            TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx);
            TagEncoder.encode(bao, TagNum.SecurityID, securityID);
            TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource);
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear);
            TagEncoder.encode(bao, TagNum.MaturityDay, maturityDay);
            if (putOrCall != null) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            TagEncoder.encode(bao, TagNum.Issuer, issuer);
            TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            TagEncoder.encode(bao, TagNum.LocateReqd, locateReqd);
            TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Price, price);
            TagEncoder.encode(bao, TagNum.StopPx, stopPx);
            TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (timeInForce != null) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            TagEncoder.encode(bao, TagNum.Commission, commission);
            if (commType != null) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            if (rule80A != null) {
                TagEncoder.encode(bao, TagNum.Rule80A, rule80A.getValue());
            }
            TagEncoder.encode(bao, TagNum.ForexReq, forexReq);
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.Text, text);
            TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            if (positionEffect != null) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            if (coveredOrUncovered != null) {
                TagEncoder.encode(bao, TagNum.CoveredOrUncovered, coveredOrUncovered.getValue());
            }
            if (customerOrFirm != null) {
                TagEncoder.encode(bao, TagNum.CustomerOrFirm, customerOrFirm.getValue());
            }
            TagEncoder.encode(bao, TagNum.MaxShow, maxShow);
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

            case BidID:
                bidID = new String(tag.value, sessionCharset);
                break;

            case ClientBidID:
                clientBidID = new String(tag.value, sessionCharset);
                break;

            case ProgRptReqs:
                progRptReqs = ProgRptReqs.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case BidType:
                bidType = BidType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ProgPeriodInterval:
                progPeriodInterval = new Integer(new String(tag.value, sessionCharset));
                break;

            case CancellationRights:
                cancellationRights = CancellationRights.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MoneyLaunderingStatus:
                moneyLaunderingStatus = MoneyLaunderingStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case RegistID:
                registID = new String(tag.value, sessionCharset);
                break;

            case ListExecInstType:
                listExecInstType = ListExecInstType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ListExecInst:
                listExecInst = new String(tag.value, sessionCharset);
                break;

            case ContingencyType:
                contingencyType = ContingencyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case EncodedListExecInstLen:
                encodedListExecInstLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case AllowableOneSidednessPct:
                allowableOneSidednessPct = new Double(new String(tag.value, sessionCharset));
                break;

            case AllowableOneSidednessValue:
                allowableOneSidednessValue = new Double(new String(tag.value, sessionCharset));
                break;

            case AllowableOneSidednessCurr:
                allowableOneSidednessCurr = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case TotNoOrders:
                totNoOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoOrders:
                noOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case WaveNo:
                waveNo = new String(tag.value, sessionCharset);
                break;

            case ListSeqNo:
                listSeqNo = new Integer(new String(tag.value, getSessionCharset()));
                break;
       
            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case ClientID:
                clientID = new String(tag.value, sessionCharset);
                break;

            case ExecBroker:
                execBroker = new String(tag.value, sessionCharset);
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case HandlInst:
                handlInst = HandlInst.valueFor(new String(tag.value, sessionCharset));
                break;

            case ExecInst:
                execInst = new String(tag.value, sessionCharset);
                break;

            case MinQty:
                minQty = new Double(new String(tag.value, sessionCharset));
                break;

            case MaxFloor:
                maxFloor = new Double(new String(tag.value, sessionCharset));
                break;

            case ExDestination:
                exDestination = new String(tag.value, sessionCharset);
                break;

            case ProcessCode:
                processCode = ProcessCode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Symbol:
                symbol = new String(tag.value, sessionCharset);
                break;

            case SymbolSfx:
                symbolSfx = new String(tag.value, sessionCharset);
                break;

            case SecurityID:
                securityID = new String(tag.value, sessionCharset);
                break;

            case SecurityIDSource:
                securityIDSource = new String(tag.value, sessionCharset);
                break;

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case MaturityMonthYear:
                maturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case MaturityDay:
                maturityDay = new Integer(new String(tag.value, sessionCharset));
                break;

            case PutOrCall:
                putOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StrikePrice:
                strikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case OptAttribute:
                optAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SecurityExchange:
                securityExchange = new String(tag.value, sessionCharset);
                break;

            case Issuer:
                issuer = new String(tag.value, sessionCharset);
                break;

            case SecurityDesc:
                securityDesc = new String(tag.value, sessionCharset);
                break;

            case PrevClosePx:
                prevClosePx = new Double(new String(tag.value, sessionCharset));
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case LocateReqd:
                locateReqd = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case OrderQty:
                orderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case StopPx:
                stopPx = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case TimeInForce:
                timeInForce = TimeInForce.valueFor(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Commission:
                commission = new Double(new String(tag.value, sessionCharset));
                break;

            case CommType:
                commType = CommType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Rule80A:
                rule80A = Rule80A.valueFor(new String(tag.value, sessionCharset));
                break;

            case ForexReq:
                forexReq = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;
                
            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case SettlDate2:
                settlDate2 = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrderQty2:
                orderQty2 = new Double(new String(tag.value, sessionCharset));
                break;

            case PositionEffect:
                positionEffect = PositionEffect.valueFor(new String(tag.value, getSessionCharset()));
                break;

            case CoveredOrUncovered:
                coveredOrUncovered = CoveredOrUncovered.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case CustomerOrFirm:
                customerOrFirm = CustomerOrFirm.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case MaxShow:
                maxShow = new Double(new String(tag.value, sessionCharset));
                break;

            case PegOffsetValue:
                pegOffsetValue = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [NewOrderListMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedListExecInstLen.getValue()) {
            try {
                encodedListExecInstLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedListExecInstLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedListExecInstLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedListExecInstLen.intValue());
            encodedListExecInst = dataTag.value;
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
        StringBuilder b = new StringBuilder("{NewOrderListMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, TagNum.BidID, bidID);
        printTagValue(b, TagNum.ClientBidID, clientBidID);
        printTagValue(b, TagNum.ProgRptReqs, progRptReqs);
        printTagValue(b, TagNum.BidType, bidType);
        printTagValue(b, TagNum.ProgPeriodInterval, progPeriodInterval);
        printTagValue(b, TagNum.CancellationRights, cancellationRights);
        printTagValue(b, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus);
        printTagValue(b, TagNum.RegistID, registID);
        printTagValue(b, TagNum.ListExecInstType, listExecInstType);
        printTagValue(b, TagNum.ListExecInst, listExecInst);
        printTagValue(b, TagNum.ContingencyType, contingencyType);
        printTagValue(b, TagNum.EncodedListExecInstLen, encodedListExecInstLen);
        printTagValue(b, TagNum.EncodedListExecInst, encodedListExecInst);
        printTagValue(b, TagNum.AllowableOneSidednessPct, allowableOneSidednessPct);
        printTagValue(b, TagNum.AllowableOneSidednessValue, allowableOneSidednessValue);
        printTagValue(b, TagNum.AllowableOneSidednessCurr, allowableOneSidednessCurr);
        printTagValue(b, TagNum.TotNoOrders, totNoOrders);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, rootParties);
        printTagValue(b, TagNum.NoOrders, noOrders);
        printTagValue(b, orderListGroups);
        printTagValue(b, TagNum.WaveNo, waveNo);
        printTagValue(b, TagNum.ListSeqNo, listSeqNo); 
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.ClientID, clientID);
        printTagValue(b, TagNum.ExecBroker, execBroker);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.HandlInst, handlInst);
        printTagValue(b, TagNum.ExecInst, execInst);
        printTagValue(b, TagNum.MinQty, minQty);
        printTagValue(b, TagNum.ExDestination, exDestination);
        printTagValue(b, TagNum.ProcessCode, processCode);
        printTagValue(b, TagNum.Symbol, symbol);
        printTagValue(b, TagNum.SymbolSfx, symbolSfx);
        printTagValue(b, TagNum.SecurityID, securityID);
        printTagValue(b, TagNum.SecurityIDSource, securityIDSource);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.MaturityMonthYear, maturityMonthYear);
        printTagValue(b, TagNum.MaturityDay, maturityDay);
        printTagValue(b, TagNum.PutOrCall, putOrCall);
        printTagValue(b, TagNum.StrikePrice, strikePrice);
        printTagValue(b, TagNum.OptAttribute, optAttribute);
        printTagValue(b, TagNum.SecurityExchange, securityExchange);
        printTagValue(b, TagNum.Issuer, issuer);
        printTagValue(b, TagNum.SecurityDesc, securityDesc);
        printTagValue(b, TagNum.PrevClosePx, prevClosePx);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.LocateReqd, locateReqd);
        printTagValue(b, TagNum.OrderQty, orderQty);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.StopPx, stopPx);
        printTagValue(b, TagNum.PegOffsetValue, pegOffsetValue);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.TimeInForce, timeInForce);
        printTagValue(b, TagNum.Commission, commission);
        printTagValue(b, TagNum.CommType, commType);
        printTagValue(b, TagNum.Rule80A, rule80A);
        printTagValue(b, TagNum.ForexReq, forexReq);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.Text, text);
        printDateTagValue(b, TagNum.SettlDate2, settlDate2);
        printTagValue(b, TagNum.OrderQty2, orderQty2);
        printTagValue(b, TagNum.PositionEffect, positionEffect);
        printTagValue(b, TagNum.CoveredOrUncovered, coveredOrUncovered);
        printTagValue(b, TagNum.CustomerOrFirm, customerOrFirm);
        printTagValue(b, TagNum.MaxShow, maxShow);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
