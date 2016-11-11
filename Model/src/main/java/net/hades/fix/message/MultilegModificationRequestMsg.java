/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MultilegModificationRequestMsg.java
 *
 * $Id: MultilegModificationRequestMsg.java,v 1.1 2011-09-10 05:39:46 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegOrdGroup;
import net.hades.fix.message.group.PreAllocMLegGroup;
import net.hades.fix.message.group.StrategyParametersGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

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

import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.comp.TriggeringInstruction;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.MultiLegRptTypeReq;
import net.hades.fix.message.type.MultilegModel;
import net.hades.fix.message.type.MultilegPriceMethod;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * In the enhanced model – the multileg security is still defined as a product using the Security Definition
 * message. However, the instrument legs are elaborated on the order to provide clearing information per leg, such
 * as LegPositionEffect, LegCoveredOrUncovered, and within <NestedParties> information such as ClearingFirm
 * for the leg, etc.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MultilegModificationRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.OrigClOrdID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.ClOrdLinkID.getValue(),
        TagNum.TradeOriginationDate.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.DayBookingInst.getValue(),
        TagNum.BookingUnit.getValue(),
        TagNum.PreallocMethod.getValue(),
        TagNum.AllocID.getValue(),
        TagNum.NoAllocs.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.CashMargin.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.HandlInst.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.MatchIncrement.getValue(),
        TagNum.MaxPriceLevels.getValue(),
        TagNum.MaxFloor.getValue(),
        TagNum.ExDestination.getValue(),
        TagNum.ExDestinationIDSource.getValue(),
        TagNum.NoTradingSessions.getValue(),
        TagNum.ProcessCode.getValue(),
        TagNum.Side.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.PrevClosePx.getValue(),
        TagNum.SwapPoints.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.LocateReqd.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.MultilegModel.getValue(),
        TagNum.MultilegPriceMethod.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceProtectionScope.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.IOIID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.GTBookingInst.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.PreTradeAnonymity.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.ForexReq.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.Text.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.CoveredOrUncovered.getValue(),
        TagNum.MaxShow.getValue(),
        TagNum.TargetStrategy.getValue(),
        TagNum.NoStrategyParameters.getValue(),
        TagNum.TargetStrategyParameters.getValue(),
        TagNum.ParticipationRate.getValue(),
        TagNum.RiskFreeRate.getValue(),
        TagNum.CancellationRights.getValue(),
        TagNum.MoneyLaunderingStatus.getValue(),
        TagNum.RegistID.getValue(),
        TagNum.Designation.getValue(),
        TagNum.MultiLegRptTypeReq.getValue(),
        TagNum.NetMoney.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ClOrdID.getValue(),
        TagNum.Side.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.OrdType.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 37. Starting with 4.3 version.
     */
    protected String orderID;

    /**
     * TagNum = 11 REQUIRED. Starting with 4.3 version.
     */
    protected String origClOrdID;

    /**
     * TagNum = 11 REQUIRED. Starting with 4.3 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 526. Starting with 4.3 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 583. Starting with 4.3 version.
     */
    protected String clOrdLinkID;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * Starting with 5.0 version.
     */
    protected Date tradeOriginationDate;

    /**
     * TagNum = 75. Starting with 5.0 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 1. Starting with 4.3 version.
     */
    protected String account;

    /**
     * TagNum = 660. Starting with 4.4 version.
     */
    protected AcctIDSource acctIDSource;

    /**
     * TagNum = 581. Starting with 4.3 version.
     */
    protected AccountType accountType;

    /**
     * TagNum = 589. Starting with 4.3 version.
     */
    protected DayBookingInst dayBookingInst;

    /**
     * TagNum = 590. Starting with 4.3 version.
     */
    protected BookingUnit bookingUnit;

    /**
     * TagNum = 591. Starting with 4.3 version.
     */
    protected PreallocMethod preallocMethod;

    /**
     * TagNum = 70. Starting with 4.4 version.
     */
    protected String allocID;

    /**
     * TagNum = 78. Starting with 4.3 version.
     */
    protected Integer noAllocs;

    /**
     * Starting with 4.3 version.
     */
    protected PreAllocMLegGroup[] allocGroups;

    /**
     * TagNum = 63. Starting with 4.0 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.0 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 544. Starting with 4.3 version.
     */
    protected CashMargin cashMargin;

    /**
     * TagNum = 635. Starting with 4.3 version.
     */
    protected ClearingFeeIndicator clearingFeeIndicator;

    /**
     * TagNum = 21. Starting with 4.3 version.
     */
    protected HandlInst handlInst;

    /**
     * TagNum = 18. Starting with 4.3 version.
     */
    protected String execInst;

    /**
     * TagNum = 110. Starting with 4.3 version.
     */
    protected Double minQty;

    /**
     * TagNum = 1089. Starting with 5.0 version.
     */
    protected Double matchIncrement;

    /**
     * TagNum = 1090. Starting with 5.0 version.
     */
    protected Integer maxPriceLevels;

    /**
     * Starting with 5.0 version.
     */
    protected DisplayInstruction displayInstruction;

    /**
     * TagNum = 111. Starting with 4.3 version.
     */
    protected Double maxFloor;

    /**
     * TagNum = 100. Starting with 4.3 version.
     */
    protected String exDestination;

    /**
     * TagNum = 1133. Starting with 5.0 version.
     */
    protected ExDestinationIDSource exDestinationIDSource;

    /**
     * TagNum = 386. Starting with 4.3 version.
     */
    protected Integer noTradingSessions;

    /**
     * Starting with 4.3 version.
     */
    protected TradingSessionGroup[] tradingSessionGroups;

    /**
     * TagNum = 81. Starting with 4.3 version.
     */
    protected ProcessCode processCode;

    /**
     * TagNum = 54 REQUIRED. Starting with 4.0 version.
     */
    protected Side side;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;
    
    /**
     * TagNum = 140. Starting with 4.3 version.
     */
    protected Double prevClosePx;

    /**
     * TagNum = 1069. Starting with 5.0 version.
     */
    protected Double swapPoints;

    /**
     * TagNum = 555. Starting with 4.3 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.3 version.
     */
    protected LegOrdGroup[] legOrdGroups;

    /**
     * TagNum = 114. Starting with 4.3 version.
     */
    protected Boolean locateReqd;

    /**
     * TagNum = 60. Starting with 4.3 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 465. Starting with 4.3 version.
     */
    protected QuantityType quantityType;

    /**
     * TagNum = 854. Starting with 4.4 version.
     */
    protected QtyType qtyType;

    /**
     * Starting with 4.3 version.
     */
    protected OrderQtyData orderQtyData;

    /**
     * TagNum = 40. Starting with 4.3 version.
     */
    protected OrdType ordType;
    
    /**
     * TagNum = 1377. Starting with 5.0SP1 version.
     */
    protected MultilegModel multilegModel;
    
    /**
     * TagNum = 1378. Starting with 5.0SP1 version.
     */
    protected MultilegPriceMethod multilegPriceMethod;

    /**
     * TagNum = 423. Starting with 4.3 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 44. Starting with 4.3 version.
     */
    protected Double price;

    /**
     * TagNum = 1092. Starting with 5.0 version.
     */
    protected PriceProtectionScope priceProtectionScope;

    /**
     * TagNum = 99. Starting with 4.3 version.
     */
    protected Double stopPx;

    /**
     * Starting with 5.0 version.
     */
    protected TriggeringInstruction triggeringInstruction;
    
    /**
     * TagNum = 15. Starting with 4.3 version.
     */
    protected Currency currency;

    /**
     * TagNum = 376. Starting with 4.3 version.
     */
    protected String complianceID;

    /**
     * TagNum = 377. Starting with 4.3 version.
     */
    protected Boolean solicitedFlag;

    /**
     * TagNum = 23. Starting with 4.3 version.
     */
    protected String IOIID;

    /**
     * TagNum = 117. Starting with 4.3 version.
     */
    protected String quoteID;

    /**
     * TagNum = 59. Starting with 4.3 version.
     */
    protected TimeInForce timeInForce;

    /**
     * TagNum = 168. Starting with 4.3 version.
     */
    protected Date effectiveTime;

    /**
     * TagNum = 432. Starting with 4.3 version.
     */
    protected Date expireDate;

    /**
     * TagNum = 126. Starting with 4.3 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 427. Starting with 4.3 version.
     */
    protected GTBookingInst GTBookingInst;

    /**
     * Starting with 4.3 version.
     */
    protected CommissionData commissionData;

    /**
     * TagNum = 528. Starting with 4.3 version.
     */
    protected OrderCapacity orderCapacity;

    /**
     * TagNum = 529. Starting with 4.3 version.
     */
    protected String orderRestrictions;

    /**
     * TagNum = 1091. Starting with 5.0 version.
     */
    protected Boolean preTradeAnonymity;

    /**
     * TagNum = 582. Starting with 4.3 version.
     */
    protected CustOrderCapacity custOrderCapacity;

    /**
     * TagNum = 121. Starting with 4.3 version.
     */
    protected Boolean forexReq;

    /**
     * TagNum = 120. Starting with 4.3 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 775. Starting with 4.4 version.
     */
    protected BookingType bookingType;

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
     * TagNum = 77. Starting with 4.3 version.
     */
    protected PositionEffect positionEffect;

    /**
     * TagNum = 203. Starting with 4.3 version.
     */
    protected CoveredOrUncovered coveredOrUncovered;

    /**
     * TagNum = 210. Starting with 4.3 version.
     */
    protected Double maxShow;

    /**
     * Starting with 4.4 version.
     */
    protected PegInstructions pegInstructions;

    /**
     * Starting with 4.4 version.
     */
    protected DiscretionInstructions discretionInstructions;

    /**
     * TagNum = 847. Starting with 4.4 version.
     */
    protected Integer targetStrategy;

    /**
     * TagNum = 957. Starting with 5.0 version.
     */
    protected Integer noStrategyParameters;

    /**
     * Starting with 5.0 version.
     */
    protected StrategyParametersGroup[] strategyParametersGroups;

    /**
     * TagNum = 848. Starting with 4.4 version.
     */
    protected String targetStrategyParameters;

    /**
     * TagNum = 849. Starting with 4.4 version.
     */
    protected Double participationRate;
    
    /**
     * TagNum = 1190. Starting with 5.0SP1 version.
     */
    protected Double riskFreeRate;

    /**
     * TagNum = 211. Starting with 4.3 version.
     */
    protected Double pegOffsetValue;

    /**
     * TagNum = 388. Starting with 4.3 version.
     */
    protected DiscretionInst discretionInst;

    /**
     * TagNum = 389. Starting with 4.3 version.
     */
    protected Double discretionOffsetValue;

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
     * TagNum = 494. Starting with 4.3 version.
     */
    protected String designation;

    /**
     * TagNum = 563. Starting with 4.3 version.
     */
    protected MultiLegRptTypeReq multiLegRptTypeReq;

    /**
     * TagNum = 118. Starting with 4.3 version.
     */
    protected Double netMoney;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MultilegModificationRequestMsg() {
        super();
    }

    public MultilegModificationRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MultilegModificationRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.MultilegModificationRequest.getValue(), beginString);
    }

    public MultilegModificationRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.MultilegModificationRequest.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.OrigClOrdID, required = true)
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    /**
     * Message field setter.
     * @param origClOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrigClOrdID, required = true)
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID, required = true)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID, required = true)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    /**
     * Message field setter.
     * @param secondaryClOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdLinkID)
    public String getClOrdLinkID() {
        return clOrdLinkID;
    }

    /**
     * Message field setter.
     * @param clOrdLinkID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdLinkID)
    public void setClOrdLinkID(String clOrdLinkID) {
        this.clOrdLinkID = clOrdLinkID;
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeOriginationDate)
    public Date getTradeOriginationDate() {
        return tradeOriginationDate;
    }

    /**
     * Message field setter.
     * @param tradeOriginationDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeOriginationDate)
    public void setTradeOriginationDate(Date tradeOriginationDate) {
        this.tradeOriginationDate = tradeOriginationDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.0")
    @TagNumRef(tagNum = TagNum.Account)
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    /**
     * Message field setter.
     * @param acctIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.AccountType)
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Message field setter.
     * @param accountType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.AccountType)
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.DayBookingInst)
    public DayBookingInst getDayBookingInst() {
        return dayBookingInst;
    }

    /**
     * Message field setter.
     * @param dayBookingInst field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.DayBookingInst)
    public void setDayBookingInst(DayBookingInst dayBookingInst) {
        this.dayBookingInst = dayBookingInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BookingUnit)
    public BookingUnit getBookingUnit() {
        return bookingUnit;
    }

    /**
     * Message field setter.
     * @param bookingUnit field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.BookingUnit)
    public void setBookingUnit(BookingUnit bookingUnit) {
        this.bookingUnit = bookingUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PreallocMethod)
    public PreallocMethod getPreallocMethod() {
        return preallocMethod;
    }

    /**
     * Message field setter.
     * @param preallocMethod field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PreallocMethod)
    public void setPreallocMethod(PreallocMethod preallocMethod) {
        this.preallocMethod = preallocMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AllocID)
    public String getAllocID() {
        return allocID;
    }

    /**
     * Message field setter.
     * @param allocID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AllocID)
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public Integer getNoAllocs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link PreAllocMLegGroup} groups. It will also create an array
     * of {@link PreAllocMLegGroup} objects and set the <code>allocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>allocGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public void setNoAllocs(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PreAllocMLegGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public PreAllocMLegGroup[] getAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link PreAllocMLegGroup} object to the existing array of <code>allocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * Note: If the <code>setNoAllocs</code> method has been called there will already be a number of objects in the
     * <code>allocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public PreAllocMLegGroup addAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link PreAllocMLegGroup} object from the existing array of <code>allocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public PreAllocMLegGroup deleteAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PreAllocMLegGroup} objects from the <code>allocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashMargin)
    public CashMargin getCashMargin() {
        return cashMargin;
    }

    /**
     * Message field setter.
     * @param cashMargin field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashMargin)
    public void setCashMargin(CashMargin cashMargin) {
        this.cashMargin = cashMargin;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    /**
     * Message field setter.
     * @param clearingFeeIndicator field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public HandlInst getHandlInst() {
        return handlInst;
    }

    /**
     * Message field setter.
     * @param handlInst field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public void setHandlInst(HandlInst handlInst) {
        this.handlInst = handlInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        return execInst;
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MinQty)
    public Double getMinQty() {
        return minQty;
    }

    /**
     * Message field setter.
     * @param minQty field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MinQty)
    public void setMinQty(Double minQty) {
        this.minQty = minQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchIncrement)
    public Double getMatchIncrement() {
        return matchIncrement;
    }

    /**
     * Message field setter.
     * @param matchIncrement field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchIncrement)
    public void setMatchIncrement(Double matchIncrement) {
        this.matchIncrement = matchIncrement;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MaxPriceLevels)
    public Integer getMaxPriceLevels() {
        return maxPriceLevels;
    }

    /**
     * Message field setter.
     * @param maxPriceLevels field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MaxPriceLevels)
    public void setMaxPriceLevels(Integer maxPriceLevels) {
        this.maxPriceLevels = maxPriceLevels;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public DisplayInstruction getDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DisplayInstruction component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0")
    public void setDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DisplayInstruction component to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public Double getMaxFloor() {
        return maxFloor;
    }

    /**
     * Message field setter.
     * @param maxFloor field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public void setMaxFloor(Double maxFloor) {
        this.maxFloor = maxFloor;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public String getExDestination() {
        return exDestination;
    }

    /**
     * Message field setter.
     * @param exDestination field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public void setExDestination(String exDestination) {
        this.exDestination = exDestination;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ExDestinationIDSource)
    public ExDestinationIDSource getExDestinationIDSource() {
        return exDestinationIDSource;
    }

    /**
     * Message field setter.
     * @param exDestinationIDSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ExDestinationIDSource)
    public void setExDestinationIDSource(ExDestinationIDSource exDestinationIDSource) {
        this.exDestinationIDSource = exDestinationIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoTradingSessions)
    public Integer getNoTradingSessions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TradingSessionGroup} groups. It will also create an array
     * of {@link TradingSessionGroup} objects and set the <code>tradingSessionGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>tradingSessionGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoTradingSessions)
    public void setNoTradingSessions(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TradingSessionGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public TradingSessionGroup[] getTradingSessionGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TradingSessionGroup} object to the existing array of <code>tradingSessionGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTradingSessions</code> field to the proper value.<br/>
     * Note: If the <code>setNoTradingSessions</code> method has been called there will already be a number of objects in the
     * <code>tradingSessionGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public TradingSessionGroup addTradingSessionGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TradingSessionGroup} object from the existing array of <code>tradingSessionGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTradingSessions</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public TradingSessionGroup deleteTradingSessionGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TradingSessionGroup} objects from the <code>tradingSessionGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTradingSessions</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearTradingSessionGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public ProcessCode getProcessCode() {
        return processCode;
    }

     /**
     * Message field setter.
     * @param processCode field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public void setProcessCode(ProcessCode processCode) {
        this.processCode = processCode;
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
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public Double getPrevClosePx() {
        return prevClosePx;
    }

    /**
     * Message field setter.
     * @param prevClosePx field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SwapPoints)
    public Double getSwapPoints() {
        return swapPoints;
    }

    /**
     * Message field setter.
     * @param swapPoints field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SwapPoints)
    public void setSwapPoints(Double swapPoints) {
        this.swapPoints = swapPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link LegOrdGroup} components. It will also create an array
     * of {@link LegOrdGroup} objects and set the <code>legOrdGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>legOrdGroups</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link LegOrdGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public LegOrdGroup[] getLegOrdGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link LegOrdGroup} object to the existing array of <code>legOrdGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>legOrdGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public LegOrdGroup addLegOrdGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link LegOrdGroup} object from the existing array of <code>legOrdGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public LegOrdGroup deleteLegOrdGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link LegOrdGroup} objects from the <code>legOrdGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearLegOrdGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public Boolean getLocateReqd() {
        return locateReqd;
    }

    /**
     * Message field setter.
     * @param locateReqd field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public void setLocateReqd(Boolean locateReqd) {
        this.locateReqd = locateReqd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }
    
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.QuantityType)
    public QuantityType getQuantityType() {
        return quantityType;
    }

    /**
     * Message field setter.
     * @param quantityType field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.QuantityType)
    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QtyType)
    public QtyType getQtyType() {
        return qtyType;
    }

    /**
     * Message field setter.
     * @param qtyType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QtyType)
    public void setQtyType(QtyType qtyType) {
        this.qtyType = qtyType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public OrderQtyData getOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the OrderQtyData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearOrderQtyData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrdType)
    public OrdType getOrdType() {
        return ordType;
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MultilegModel)
    public MultilegModel getMultilegModel() {
        return multilegModel;
    }

    /**
     * Message field setter.
     * @param multilegModel field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MultilegModel)
    public void setMultilegModel(MultilegModel multilegModel) {
        this.multilegModel = multilegModel;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MultilegPriceMethod)
    public MultilegPriceMethod getMultilegPriceMethod() {
        return multilegPriceMethod;
    }

    /**
     * Message field setter.
     * @param multilegPriceMethod field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MultilegPriceMethod)
    public void setMultilegPriceMethod(MultilegPriceMethod multilegPriceMethod) {
        this.multilegPriceMethod = multilegPriceMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price)
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PriceProtectionScope)
    public PriceProtectionScope getPriceProtectionScope() {
        return priceProtectionScope;
    }

    /**
     * Message field setter.
     * @param priceProtectionScope field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PriceProtectionScope)
    public void setPriceProtectionScope(PriceProtectionScope priceProtectionScope) {
        this.priceProtectionScope = priceProtectionScope;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.StopPx)
    public Double getStopPx() {
        return stopPx;
    }

    /**
     * Message field setter.
     * @param stopPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.StopPx)
    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public TriggeringInstruction getTriggeringInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TriggeringInstruction component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0")
    public void setTriggeringInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TriggeringInstruction component to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearTriggeringInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @TagNumRef(tagNum=TagNum.ComplianceID)
    public String getComplianceID() {
        return complianceID;
    }

    /**
     * Message field setter.
     * @param complianceID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ComplianceID)
    public void setComplianceID(String complianceID) {
        this.complianceID = complianceID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SolicitedFlag)
    public Boolean getSolicitedFlag() {
        return solicitedFlag;
    }

    /**
     * Message field setter.
     * @param solicitedFlag field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SolicitedFlag)
    public void setSolicitedFlag(Boolean solicitedFlag) {
        this.solicitedFlag = solicitedFlag;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.IOIID)
    public String getIOIID() {
        return IOIID;
    }

    /**
     * Message field setter.
     * @param IOIID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.IOIID)
    public void setIOIID(String IOIID) {
        this.IOIID = IOIID;
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public String getQuoteID() {
        return quoteID;
    }

    /**
     * Message field setter.
     * @param quoteID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    /**
     * Message field setter.
     * @param timeInForce field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Message field setter.
     * @param effectiveTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Message field setter.
     * @param expireDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.GTBookingInst)
    public GTBookingInst getGTBookingInst() {
        return GTBookingInst;
    }

    /**
     * Message field setter.
     * @param GTBookingInst field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.GTBookingInst)
    public void setGTBookingInst(GTBookingInst GTBookingInst) {
        this.GTBookingInst = GTBookingInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public CommissionData getCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the CommissionData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the CommissionData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    /**
     * Message field setter.
     * @param orderCapacity field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    /**
     * Message field setter.
     * @param orderRestrictions field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PreTradeAnonymity)
    public Boolean getPreTradeAnonymity() {
        return preTradeAnonymity;
    }

    /**
     * Message field setter.
     * @param preTradeAnonymity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PreTradeAnonymity)
    public void setPreTradeAnonymity(Boolean preTradeAnonymity) {
        this.preTradeAnonymity = preTradeAnonymity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CustOrderCapacity)
    public CustOrderCapacity getCustOrderCapacity() {
        return custOrderCapacity;
    }

    /**
     * Message field setter.
     * @param custOrderCapacity field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CustOrderCapacity)
    public void setCustOrderCapacity(CustOrderCapacity custOrderCapacity) {
        this.custOrderCapacity = custOrderCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public Boolean getForexReq() {
        return forexReq;
    }

    /**
     * Message field setter.
     * @param forexReq field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public void setForexReq(Boolean forexReq) {
        this.forexReq = forexReq;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.BookingType)
    public BookingType getBookingType() {
        return bookingType;
    }

    /**
     * Message field setter.
     * @param bookingType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.BookingType)
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
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
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public PositionEffect getPositionEffect() {
        return positionEffect;
    }

    /**
     * Message field setter.
     * @param positionEffect field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public void setPositionEffect(PositionEffect positionEffect) {
        this.positionEffect = positionEffect;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CoveredOrUncovered)
    public CoveredOrUncovered getCoveredOrUncovered() {
        return coveredOrUncovered;
    }

    /**
     * Message field setter.
     * @param coveredOrUncovered field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CoveredOrUncovered)
    public void setCoveredOrUncovered(CoveredOrUncovered coveredOrUncovered) {
        this.coveredOrUncovered = coveredOrUncovered;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public Double getMaxShow() {
        return maxShow;
    }

    /**
     * Message field setter.
     * @param maxShow field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public void setMaxShow(Double maxShow) {
        this.maxShow = maxShow;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public PegInstructions getPegInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the PegInstructions component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setPegInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the PegInstructions component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearPegInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public DiscretionInstructions getDiscretionInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the PegInstructions component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setDiscretionInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the PegInstructions component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearDiscretionInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategy)
    public Integer getTargetStrategy() {
        return targetStrategy;
    }

    /**
     * Message field setter.
     * @param targetStrategy field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategy)
    public void setTargetStrategy(Integer targetStrategy) {
        this.targetStrategy = targetStrategy;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoStrategyParameters)
    public Integer getNoStrategyParameters() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link StrategyParametersGroup} groups. It will also create an array
     * of {@link StrategyParametersGroup} objects and set the <code>strategyParametersGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>strategyParametersGroups</code> array they will be discarded.<br/>
     * @param noStrategyParameters field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoStrategyParameters)
    public void setNoStrategyParameters(Integer noStrategyParameters) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link StrategyParametersGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0")
    public StrategyParametersGroup[] getStrategyParametersGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link StrategyParametersGroup} object to the existing array of <code>strategyParametersGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noStrategyParameters</code> field to the proper value.<br/>
     * Note: If the <code>setNoStrategyParameters</code> method has been called there will already be a number of objects in the
     * <code>strategyParametersGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public StrategyParametersGroup addStrategyParametersGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link StrategyParametersGroup} object from the existing array of <code>strategyParametersGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noStrategyParameters</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public StrategyParametersGroup deleteStrategyParametersGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link StrategyParametersGroup} objects from the <code>strategyParametersGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noStrategyParameters</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearStrategyParametersGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategyParameters)
    public String getTargetStrategyParameters() {
        return targetStrategyParameters;
    }

    /**
     * Message field setter.
     * @param targetStrategyParameters field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategyParameters)
    public void setTargetStrategyParameters(String targetStrategyParameters) {
        this.targetStrategyParameters = targetStrategyParameters;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ParticipationRate)
    public Double getParticipationRate() {
        return participationRate;
    }

    /**
     * Message field setter.
     * @param participationRate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ParticipationRate)
    public void setParticipationRate(Double participationRate) {
        this.participationRate = participationRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RiskFreeRate)
    public Double getRiskFreeRate() {
        return riskFreeRate;
    }

    /**
     * Message field setter.
     * @param riskFreeRate field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.RiskFreeRate)
    public void setRiskFreeRate(Double riskFreeRate) {
        this.riskFreeRate = riskFreeRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    /**
     * Message field setter.
     * @param pegOffsetValue field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public void setPegOffsetValue(Double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionInst)
    public DiscretionInst getDiscretionInst() {
        return discretionInst;
    }

    /**
     * Message field setter.
     * @param discretionInst field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionInst)
    public void setDiscretionInst(DiscretionInst discretionInst) {
        this.discretionInst = discretionInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionOffsetValue)
    public Double getDiscretionOffsetValue() {
        return discretionOffsetValue;
    }

    /**
     * Message field setter.
     * @param discretionOffsetValue field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionOffsetValue)
    public void setDiscretionOffsetValue(Double discretionOffsetValue) {
        this.discretionOffsetValue = discretionOffsetValue;
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Designation)
    public String getDesignation() {
        return designation;
    }

    /**
     * Message field setter.
     * @param designation field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Designation)
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MultiLegRptTypeReq)
    public MultiLegRptTypeReq getMultiLegRptTypeReq() {
        return multiLegRptTypeReq;
    }

    /**
     * Message field setter.
     * @param multiLegRptTypeReq field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MultiLegRptTypeReq)
    public void setMultiLegRptTypeReq(MultiLegRptTypeReq multiLegRptTypeReq) {
        this.multiLegRptTypeReq = multiLegRptTypeReq;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public Double getNetMoney() {
        return netMoney;
    }

    /**
     * Message field setter.
     * @param netMoney field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (origClOrdID == null || origClOrdID.trim().isEmpty()) {
            errorMsg.append(" [OrigClOrdID]");
            hasMissingTag = true;
        }
        if (clOrdID == null || clOrdID.trim().isEmpty()) {
            errorMsg.append(" [ClOrdID]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (legOrdGroups == null || legOrdGroups.length == 0) {
            errorMsg.append(" [LegOrdGroups]");
            hasMissingTag = true;
        }
        if (orderQtyData == null || orderQtyData.getOrderQty() == null) {
            errorMsg.append(" [OrderQty]");
            hasMissingTag = true;
        }
        if (ordType == null) {
            errorMsg.append(" [OrdType]");
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
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.OrigClOrdID, origClOrdID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.ClOrdLinkID, clOrdLinkID);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encodeDate(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);           
            TagEncoder.encode(bao, TagNum.Account, account);
            if (acctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (dayBookingInst != null) {
                TagEncoder.encode(bao, TagNum.DayBookingInst, dayBookingInst.getValue());
            }
            if (bookingUnit != null) {
                TagEncoder.encode(bao, TagNum.BookingUnit, bookingUnit.getValue());
            }
            if (preallocMethod != null) {
                TagEncoder.encode(bao, TagNum.PreallocMethod, preallocMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocID, allocID);
            if (noAllocs != null && noAllocs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocGroups != null && allocGroups.length == noAllocs.intValue()) {
                    for (PreAllocMLegGroup allocGroup : allocGroups) {
                        bao.write(allocGroup.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "PreAllocMLegGroups field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoAllocs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            if (cashMargin != null) {
                TagEncoder.encode(bao, TagNum.CashMargin, cashMargin.getValue());
            }
            if (clearingFeeIndicator != null) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (handlInst != null) {
                TagEncoder.encode(bao, TagNum.HandlInst, handlInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            TagEncoder.encode(bao, TagNum.MinQty, minQty);
            TagEncoder.encode(bao, TagNum.MatchIncrement, matchIncrement);
            TagEncoder.encode(bao, TagNum.MaxPriceLevels, maxPriceLevels);
            if (displayInstruction != null) {
                bao.write(displayInstruction.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.MaxFloor, maxFloor);
            TagEncoder.encode(bao, TagNum.ExDestination, exDestination);
            if (exDestinationIDSource != null) {
                TagEncoder.encode(bao, TagNum.ExDestinationIDSource, exDestinationIDSource.getValue());
            }
            if (noTradingSessions != null && noTradingSessions.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoTradingSessions, noTradingSessions);
                if (tradingSessionGroups != null && tradingSessionGroups.length == noTradingSessions.intValue()) {
                    for (int i = 0; i < noTradingSessions.intValue(); i++) {
                        if (tradingSessionGroups[i] != null) {
                            bao.write(tradingSessionGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TradingSessionsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTradingSessions.getValue(), error);
                }
            }
            if (processCode != null) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
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
            TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            TagEncoder.encode(bao, TagNum.SwapPoints, swapPoints);
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (legOrdGroups != null && legOrdGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (legOrdGroups[i] != null) {
                            bao.write(legOrdGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "LegOrdGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.LocateReqd, locateReqd);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (quantityType != null) {
                TagEncoder.encode(bao, TagNum.QuantityType, quantityType.getValue());
            }
            if (qtyType != null) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (multilegModel != null) {
                TagEncoder.encode(bao, TagNum.MultilegModel, multilegModel.getValue());
            }
            if (multilegPriceMethod != null) {
                TagEncoder.encode(bao, TagNum.MultilegPriceMethod, multilegPriceMethod.getValue());
            }
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Price, price);
            if (priceProtectionScope != null) {
                TagEncoder.encode(bao, TagNum.PriceProtectionScope, priceProtectionScope.getValue());
            }
            TagEncoder.encode(bao, TagNum.StopPx, stopPx);
            if (triggeringInstruction != null) {
                bao.write(triggeringInstruction.encode(MsgSecureType.ALL_FIELDS));
            }
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.ComplianceID, complianceID);
            TagEncoder.encode(bao, TagNum.SolicitedFlag, solicitedFlag);
            TagEncoder.encode(bao, TagNum.IOIID, IOIID);
            TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            if (timeInForce != null) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            if (GTBookingInst != null) {
                TagEncoder.encode(bao, TagNum.GTBookingInst, GTBookingInst.getValue());
            }
            if (commissionData != null) {
                bao.write(commissionData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (orderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            TagEncoder.encode(bao, TagNum.PreTradeAnonymity, preTradeAnonymity);
            if (custOrderCapacity != null) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.ForexReq, forexReq);
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (bookingType != null) {
                TagEncoder.encode(bao, TagNum.BookingType, bookingType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (positionEffect != null) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            if (coveredOrUncovered != null) {
                TagEncoder.encode(bao, TagNum.CoveredOrUncovered, coveredOrUncovered.getValue());
            }
            TagEncoder.encode(bao, TagNum.MaxShow, maxShow);
            if (pegInstructions != null) {
                bao.write(pegInstructions.encode(MsgSecureType.ALL_FIELDS));
            }
            if (discretionInstructions != null) {
                bao.write(discretionInstructions.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.TargetStrategy, targetStrategy);
            if (noStrategyParameters != null) {
                TagEncoder.encode(bao, TagNum.NoStrategyParameters, noStrategyParameters);
                if (strategyParametersGroups != null && strategyParametersGroups.length == noStrategyParameters.intValue()) {
                    for (int i = 0; i < noStrategyParameters.intValue(); i++) {
                        if (strategyParametersGroups[i] != null) {
                            bao.write(strategyParametersGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "StrategyParametersGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoStrategyParameters.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.TargetStrategyParameters, targetStrategyParameters);
            TagEncoder.encode(bao, TagNum.RiskFreeRate, riskFreeRate);
            TagEncoder.encode(bao, TagNum.ParticipationRate, participationRate);
            TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            if (discretionInst != null) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
            if (cancellationRights != null) {
                TagEncoder.encode(bao, TagNum.CancellationRights, cancellationRights.getValue());
            }
            if (moneyLaunderingStatus != null) {
                TagEncoder.encode(bao, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.RegistID, registID);
            TagEncoder.encode(bao, TagNum.Designation, designation);
            if (multiLegRptTypeReq != null) {
                TagEncoder.encode(bao, TagNum.MultiLegRptTypeReq, multiLegRptTypeReq.getValue());
            }
            TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
             
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
            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

            case OrigClOrdID:
                origClOrdID = new String(tag.value, sessionCharset);
                break;

            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

            case ClOrdLinkID:
                clOrdLinkID = new String(tag.value, sessionCharset);
                break;

            case TradeOriginationDate:
                tradeOriginationDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case AcctIDSource:
                acctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AccountType:
                accountType = AccountType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DayBookingInst:
                dayBookingInst = DayBookingInst.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case BookingUnit:
                bookingUnit = BookingUnit.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case PreallocMethod:
                preallocMethod = PreallocMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case AllocID:
                allocID = new String(tag.value, sessionCharset);
                break;

            case NoAllocs:
                noAllocs = new Integer(new String(tag.value, sessionCharset));
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case CashMargin:
                cashMargin = CashMargin.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ClearingFeeIndicator:
                clearingFeeIndicator = ClearingFeeIndicator.valueFor(new String(tag.value, sessionCharset));
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

            case MatchIncrement:
                matchIncrement = new Double(new String(tag.value, sessionCharset));
                break;

            case MaxPriceLevels:
                maxPriceLevels = new Integer(new String(tag.value, sessionCharset));
                break;

            case MaxFloor:
                maxFloor = new Double(new String(tag.value, sessionCharset));
                break;

            case ExDestination:
                exDestination = new String(tag.value, sessionCharset);
                break;

            case ExDestinationIDSource:
                exDestinationIDSource = ExDestinationIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case NoTradingSessions:
                noTradingSessions = new Integer(new String(tag.value, sessionCharset));
                break;

            case ProcessCode:
                processCode = ProcessCode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case PrevClosePx:
                prevClosePx = new Double(new String(tag.value, sessionCharset));
                break;

            case SwapPoints:
                swapPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case LocateReqd:
                locateReqd = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case QuantityType:
                quantityType = QuantityType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QtyType:
                qtyType = QtyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MultilegModel:
                multilegModel = MultilegModel.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MultilegPriceMethod:
                multilegPriceMethod = MultilegPriceMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceProtectionScope:
                priceProtectionScope = PriceProtectionScope.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case StopPx:
                stopPx = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case ComplianceID:
                complianceID = new String(tag.value, sessionCharset);
                break;

            case SolicitedFlag:
                solicitedFlag = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case IOIID:
                IOIID = new String(tag.value, sessionCharset);
                break;

           case QuoteID:
                quoteID = new String(tag.value, sessionCharset);
                break;

            case TimeInForce:
                timeInForce = TimeInForce.valueFor(new String(tag.value, sessionCharset));
                break;

            case EffectiveTime:
                effectiveTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExpireDate:
                expireDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case GTBookingInst:
                GTBookingInst = GTBookingInst.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrderCapacity:
                orderCapacity = OrderCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case OrderRestrictions:
                orderRestrictions = new String(tag.value, getSessionCharset());
                break;

            case PreTradeAnonymity:
                preTradeAnonymity = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case CustOrderCapacity:
                custOrderCapacity = CustOrderCapacity.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ForexReq:
                forexReq = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case BookingType:
                bookingType = BookingType.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;
                
            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case PositionEffect:
                positionEffect = PositionEffect.valueFor(new String(tag.value, getSessionCharset()));
                break;

            case CoveredOrUncovered:
                coveredOrUncovered = CoveredOrUncovered.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case MaxShow:
                maxShow = new Double(new String(tag.value, sessionCharset));
                break;

            case TargetStrategy:
                targetStrategy = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoStrategyParameters:
                noStrategyParameters = new Integer(new String(tag.value, sessionCharset));
                break;

            case TargetStrategyParameters:
                targetStrategyParameters = new String(tag.value, sessionCharset);
                break;

            case ParticipationRate:
                participationRate = new Double(new String(tag.value, sessionCharset));
                break;

            case RiskFreeRate:
                riskFreeRate = new Double(new String(tag.value, sessionCharset));
                break;

            case PegOffsetValue:
                pegOffsetValue = new Double(new String(tag.value, sessionCharset));
                break;

            case DiscretionInst:
                discretionInst = DiscretionInst.valueFor(new String(tag.value, sessionCharset));
                break;

            case DiscretionOffsetValue:
                discretionOffsetValue = new Double(new String(tag.value, sessionCharset));
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

            case Designation:
                designation = new String(tag.value, sessionCharset);
                break;

            case MultiLegRptTypeReq:
                multiLegRptTypeReq = MultiLegRptTypeReq.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NetMoney:
                netMoney = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [MultilegModificationRequestMsg] fields.";
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
        StringBuilder b = new StringBuilder("{MultilegModificationRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.OrigClOrdID, origClOrdID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.ClOrdLinkID, clOrdLinkID);
        printTagValue(b, parties);
        printDateTagValue(b, TagNum.TradeOriginationDate, tradeOriginationDate);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.DayBookingInst, dayBookingInst);
        printTagValue(b, TagNum.BookingUnit, bookingUnit);
        printTagValue(b, TagNum.PreallocMethod, preallocMethod);
        printTagValue(b, TagNum.AllocID, allocID);
        printTagValue(b, TagNum.NoAllocs, noAllocs);
        printTagValue(b, allocGroups);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.CashMargin, cashMargin);
        printTagValue(b, TagNum.ClearingFeeIndicator, clearingFeeIndicator);
        printTagValue(b, TagNum.HandlInst, handlInst);
        printTagValue(b, TagNum.ExecInst, execInst);
        printTagValue(b, TagNum.MinQty, minQty);
        printTagValue(b, TagNum.MatchIncrement, matchIncrement);
        printTagValue(b, TagNum.MaxPriceLevels, maxPriceLevels);
        printTagValue(b, displayInstruction);
        printTagValue(b, TagNum.MaxFloor, maxFloor);
        printTagValue(b, TagNum.ExDestination, exDestination);
        printTagValue(b, TagNum.ExDestinationIDSource, exDestinationIDSource);
        printTagValue(b, TagNum.NoTradingSessions, noTradingSessions);
        printTagValue(b, tradingSessionGroups);
        printTagValue(b, TagNum.ProcessCode, processCode);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, instrument);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.PrevClosePx, prevClosePx);
        printTagValue(b, TagNum.SwapPoints, swapPoints);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, legOrdGroups);
        printTagValue(b, TagNum.LocateReqd, locateReqd);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.QuantityType, quantityType);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, orderQtyData);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.MultilegModel, multilegModel);
        printTagValue(b, TagNum.MultilegPriceMethod, multilegPriceMethod);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.PriceProtectionScope, priceProtectionScope);
        printTagValue(b, TagNum.StopPx, stopPx);
        printTagValue(b, triggeringInstruction);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.ComplianceID, complianceID);
        printTagValue(b, TagNum.SolicitedFlag, solicitedFlag);
        printTagValue(b, TagNum.IOIID, IOIID);
        printTagValue(b, TagNum.QuoteID, quoteID);
        printTagValue(b, TagNum.TimeInForce, timeInForce);
        printUTCDateTimeTagValue(b, TagNum.EffectiveTime, effectiveTime);
        printDateTagValue(b, TagNum.ExpireDate, expireDate);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printTagValue(b, TagNum.GTBookingInst, GTBookingInst);
        printTagValue(b, commissionData);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.OrderRestrictions, orderRestrictions);
        printTagValue(b, TagNum.PreTradeAnonymity, preTradeAnonymity);
        printTagValue(b, TagNum.CustOrderCapacity, custOrderCapacity);
        printTagValue(b, TagNum.ForexReq, forexReq);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.BookingType, bookingType);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.PositionEffect, positionEffect);
        printTagValue(b, TagNum.CoveredOrUncovered, coveredOrUncovered);
        printTagValue(b, TagNum.MaxShow, maxShow);
        printTagValue(b, pegInstructions);
        printTagValue(b, discretionInstructions);
        printTagValue(b, TagNum.TargetStrategy, targetStrategy);
        printTagValue(b, TagNum.NoStrategyParameters, noStrategyParameters);
        printTagValue(b, strategyParametersGroups);
        printTagValue(b, TagNum.TargetStrategyParameters, targetStrategyParameters);
        printTagValue(b, TagNum.ParticipationRate, participationRate);
        printTagValue(b, TagNum.RiskFreeRate, riskFreeRate);
        printTagValue(b, TagNum.PegOffsetValue, pegOffsetValue);
        printTagValue(b, TagNum.DiscretionInst, discretionInst);
        printTagValue(b, TagNum.DiscretionOffsetValue, discretionOffsetValue);
        printTagValue(b, TagNum.CancellationRights, cancellationRights);
        printTagValue(b, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus);
        printTagValue(b, TagNum.RegistID, registID);
        printTagValue(b, TagNum.Designation, designation);
        printTagValue(b, TagNum.MultiLegRptTypeReq, multiLegRptTypeReq);
        printTagValue(b, TagNum.NetMoney, netMoney);       
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
