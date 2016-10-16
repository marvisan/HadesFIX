/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg.java
 *
 * $Id: ExecutionReportMsg.java,v 1.10 2011-10-21 10:31:02 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.PriorityIndicator;

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

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.TriggeringInstruction;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ContAmtGroup;
import net.hades.fix.message.group.ContraBrokerGroup;
import net.hades.fix.message.group.FillExecGroup;
import net.hades.fix.message.group.InstrmtLegExecGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.group.PreTradeAllocGroup;
import net.hades.fix.message.group.RateSourceGroup;
import net.hades.fix.message.group.StrategyParametersGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExecPriceType;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecTransType;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.LastCapacity;
import net.hades.fix.message.type.LastLiquidityInd;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderCategory;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.RateSource;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The execution report message is used to:
 * <ol>
 *  <li>confirm the receipt of an order
 *  <li>confirm changes to an existing order (i.e. accept cancel and replace requests)
 *  <li>relay order status information
 *  <li>relay fill information on working orders
 *  <li>relay fill information on tradeable or restricted tradeable quotes
 *  <li>reject orders
 *  <li>report post-trade fees calculations associated with a trade
 * </ol>
 * Each execution report contains two fields which are used to communicate both the current state of the
 * order as understood by the broker (OrdStatus) and the purpose of the message (ExecType).
 * In an execution report the OrdStatus is used to convey the current state of the order. If an order
 * simultaneously exists in more than one order state, the value with highest precedence is the value that
 * is reported in the OrdStatus field.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 21/12/2010, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ExecutionReportMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.OrigClOrdID.getValue(),
        TagNum.ClOrdLinkID.getValue(),
        TagNum.QuoteRespID.getValue(),
        TagNum.OrdStatusReqID.getValue(),
        TagNum.MassStatusReqID.getValue(),
        TagNum.HostCrossID.getValue(),
        TagNum.TotNumReports.getValue(),
        TagNum.LastRptRequested.getValue(),
        TagNum.TradeOriginationDate.getValue(),
        TagNum.NoContraBrokers.getValue(),
        TagNum.ListID.getValue(),
        TagNum.CrossID.getValue(),
        TagNum.OrigCrossID.getValue(),
        TagNum.CrossType.getValue(),
        TagNum.TrdMatchID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.ExecRefID.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.OrdStatus.getValue(),
        TagNum.WorkingIndicator.getValue(),
        TagNum.OrdRejReason.getValue(),
        TagNum.ExecRestatementReason.getValue(),
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
        TagNum.MatchType.getValue(),
        TagNum.OrderCategory.getValue(),
        TagNum.CashMargin.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.Side.getValue(),
        TagNum.QuantityType.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.LotType.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceProtectionScope.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.PeggedPrice.getValue(),
        TagNum.PeggedRefPrice.getValue(),
        TagNum.DiscretionPrice.getValue(),
        TagNum.TargetStrategy.getValue(),
        TagNum.NoStrategyParameters.getValue(),
        TagNum.TargetStrategyParameters.getValue(),
        TagNum.ParticipationRate.getValue(),
        TagNum.TargetStrategyPerformance.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.AggressorIndicator.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.PreTradeAnonymity.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.CalculatedCcyLastQty.getValue(),
        TagNum.LastSwapPoints.getValue(),
        TagNum.UnderlyingLastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.UnderlyingLastPx.getValue(),
        TagNum.LastParPx.getValue(),
        TagNum.LastSpotRate.getValue(),
        TagNum.LastForwardPoints.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.TimeBracket.getValue(),
        TagNum.LastCapacity.getValue(),
        TagNum.LeavesQty.getValue(),
        TagNum.CumQty.getValue(),
        TagNum.AvgPx.getValue(),
        TagNum.DayOrderQty.getValue(),
        TagNum.DayCumQty.getValue(),
        TagNum.DayAvgPx.getValue(),
        TagNum.TotNoFills.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoFills.getValue(),
        TagNum.GTBookingInst.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.ReportToExch.getValue(),
        TagNum.GrossTradeAmt.getValue(),
        TagNum.NumDaysInterest.getValue(),
        TagNum.ExDate.getValue(),
        TagNum.AccruedInterestRate.getValue(),
        TagNum.AccruedInterestAmt.getValue(),
        TagNum.InterestAtMaturity.getValue(),
        TagNum.EndAccruedInterestAmt.getValue(),
        TagNum.StartCash.getValue(),
        TagNum.EndCash.getValue(),
        TagNum.TradedFlatSwitch.getValue(),
        TagNum.BasisFeatureDate.getValue(),
        TagNum.BasisFeaturePrice.getValue(),
        TagNum.Concession.getValue(),
        TagNum.TotalTakedown.getValue(),
        TagNum.NetMoney.getValue(),
        TagNum.SettlCurrAmt.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.NoRateSources.getValue(),
        TagNum.SettlCurrFxRate.getValue(),
        TagNum.SettlCurrFxRateCalc.getValue(),
        TagNum.HandlInst.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.MatchIncrement.getValue(),
        TagNum.MaxPriceLevels.getValue(),
        TagNum.MaxFloor.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.MaxShow.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.Text.getValue(),
        TagNum.SettlDate2.getValue(),
        TagNum.OrderQty2.getValue(),
        TagNum.LastForwardPoints2.getValue(),
        TagNum.MultiLegReportingType.getValue(),
        TagNum.CancellationRights.getValue(),
        TagNum.MoneyLaunderingStatus.getValue(),
        TagNum.RegistID.getValue(),
        TagNum.Designation.getValue(),
        TagNum.TransBkdTime.getValue(),
        TagNum.ExecValuationPoint.getValue(),
        TagNum.ExecPriceType.getValue(),
        TagNum.ExecPriceAdjustment.getValue(),
        TagNum.PriorityIndicator.getValue(),
        TagNum.PriceImprovement.getValue(),
        TagNum.LastLiquidityInd.getValue(),
        TagNum.NoContAmts.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.CopyMsgIndicator.getValue(),
        TagNum.NoMiscFees.getValue(),
        TagNum.DividendYield.getValue(),
        TagNum.ManualOrderIndicator.getValue(),
        TagNum.CustDirectedOrder.getValue(),
        TagNum.ReceivedDeptID.getValue(),
        TagNum.CustOrderHandlingInst.getValue(),
        TagNum.OrderHandlingInstSource.getValue(),
        TagNum.NoTrdRegTimestamps.getValue(),
        TagNum.Volatility.getValue(),
        TagNum.TimeToExpiration.getValue(),
        TagNum.RiskFreeRate.getValue(),
        TagNum.PriceDelta.getValue()
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
     * Starting with 5.0SP1 version.
     */
    protected ApplicationSequenceControl applicationSequenceControl;

    /**
     * TagNum = 37 REQUIRED. Starting with 4.0 version.
     */
    protected String orderID;

    /**
     * TagNum = 198. Starting with 4.1 version.
     */
    protected String secondaryOrderID;

    /**
     * TagNum = 526. Starting with 4.3 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 527. Starting with 4.3 version.
     */
    protected String secondaryExecID;

    /**
     * TagNum = 11. Starting with 4.0 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 11. Starting with 4.1 version.
     */
    protected String origClOrdID;

    /**
     * TagNum = 583. Starting with 4.3 version.
     */
    protected String clOrdLinkID;

    /**
     * TagNum = 693. Starting with 4.4 version.
     */
    protected String quoteRespID;

    /**
     * TagNum = 790. Starting with 4.4 version.
     */
    protected String ordStatusReqID;

    /**
     * TagNum = 584. Starting with 4.4 version.
     */
    protected String massStatusReqID;

    /**
     * TagNum = 961. Starting with 5.0 version.
     */
    protected String hostCrossID;

    /**
     * TagNum = 911. Starting with 4.4 version.
     */
    protected Integer totNumReports;

    /**
     * TagNum = 912. Starting with 4.4 version.
     */
    protected Boolean lastRptRequested;
    
   /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * Starting with 4.3 version.
     */
    protected Date tradeOriginationDate;

    /**
     * TagNum = 109. Starting with 4.0 version.
     */
    protected String clientID;

    /**
     * TagNum = 76. Starting with 4.0 version.
     */
    protected String execBroker;

    /**
     * TagNum = 382. Starting with 4.2 version.
     */
    protected Integer noContraBrokers;

    /**
     * Starting with 4.2 version.
     */
    protected ContraBrokerGroup[] contraBrokers;

    /**
     * TagNum = 66. Starting with 4.0 version.
     */
    protected String listID;

    /**
     * TagNum = 548. Starting with 4.3 version.
     */
    protected String crossID;

    /**
     * TagNum = 551. Starting with 4.3 version.
     */
    protected String origCrossID;

    /**
     * TagNum = 549. Starting with 4.3 version.
     */
    protected CrossType crossType;

    /**
     * TagNum = 880. Starting with 5.0SP1 version.
     */
    protected String trdMatchID;

    /**
     * TagNum = 17 REQUIRED. Starting with 4.0 version.
     */
    protected String execID;

    /**
     * TagNum = 20. Starting with 4.0 REQUIRED version.
     */
    protected ExecTransType execTransType;

    /**
     * TagNum = 19. Starting with 4.0 version.
     */
    protected String execRefID;

    /**
     * TagNum = 150 REQUIRED. Starting with 4.1 version.
     */
    protected ExecType execType;

    /**
     * TagNum = 39 REQUIRED. Starting with 4.0 version.
     */
    protected OrdStatus ordStatus;

    /**
     * TagNum = 636. Starting with 4.3 version.
     */
    protected Boolean workingIndicator;

    /**
     * TagNum = 103. Starting with 4.0 version.
     */
    protected OrdRejReason ordRejReason;
    
    /**
     * TagNum = 378. Starting with 4.2 version.
     */
    protected ExecRestatementReason execRestatementReason;

    /**
     * TagNum = 1. Starting with 4.0 version.
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
     * TagNum = 70. Starting with 5.0SP1 version.
     */
    protected String allocID;

    /**
     * TagNum = 78. Starting with 5.0SP1 version.
     */
    protected Integer noAllocs;

    /**
     * Starting with 5.0SP1 version.
     */
    protected PreTradeAllocGroup[] allocGroups;

    /**
     * TagNum = 63. Starting with 4.0 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.0 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 574. Starting with 5.0 version.
     */
    protected MatchType matchType;

    /**
     * TagNum = 1115. Starting with 5.0 version.
     */
    protected OrderCategory orderCategory;

    /**
     * TagNum = 544. Starting with 4.3 version.
     */
    protected CashMargin cashMargin;

    /**
     * TagNum = 635. Starting with 4.3 version.
     */
    protected ClearingFeeIndicator clearingFeeIndicator;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

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
     * TagNum = 231. Starting with 4.2 version.
     */
    protected Double contractMultiplier;

    /**
     * TagNum = 223. Starting with 4.2 version.
     */
    protected Double couponRate;

    /**
     * TagNum = 207. Starting with 4.1 version.
     */
    protected String securityExchange;

    /**
     * TagNum = 106. Starting with 4.0 version.
     */
    protected String issuer;

    /**
     * TagNum = 348. Starting with 4.2 version.
     */
    protected Integer encodedIssuerLen;

    /**
     * TagNum = 349. Starting with 4.2 version.
     */
    protected byte[] encodedIssuer;

    /**
     * TagNum = 107. Starting with 4.0 version.
     */
    protected String securityDesc;

    /**
     * TagNum = 350. Starting with 4.2 version.
     */
    protected Integer encodedSecurityDescLen;

    /**
     * TagNum = 351. Starting with 4.2 version.
     */
    protected byte[] encodedSecurityDesc;

    /**
     * TagNum = 54 REQUIRED. Starting with 4.0 version.
     */
    protected Side side;
    
    /**
     * Starting with 4.3 version.
     */
    protected Stipulations stipulations;

    /**
     * TagNum = 465. Starting with 4.3 version.
     */
    protected QuantityType quantityType;

    /**
     * TagNum = 854. Starting with 4.3 version.
     */
    protected QtyType qtyType;

    /**
     * Starting with 4.3 version.
     */
    protected OrderQtyData orderQtyData;

    /**
     * TagNum = 38. Starting with 4.0 version.
     */
    protected Double orderQty;

    /**
     * TagNum = 152. Starting with 4.3 version.
     */
    protected Double cashOrderQty;

    /**
     * TagNum = 1093. Starting with 5.0 version.
     */
    protected LotType lotType;

    /**
     * TagNum = 40. Starting with 4.0 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 423. Starting with 4.3 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 44. Starting with 4.0 version.
     */
    protected Double price;

    /**
     * TagNum = 1092. Starting with 5.0 version.
     */
    protected PriceProtectionScope priceProtectionScope;

    /**
     * TagNum = 99. Starting with 4.0 version.
     */
    protected Double stopPx;

    /**
     * Starting with 5.0 version.
     */
    protected TriggeringInstruction triggeringInstruction;

    /**
     * Starting with 4.4 version.
     */
    protected PegInstructions pegInstructions;

    /**
     * Starting with 4.4 version.
     */
    protected DiscretionInstructions discretionInstructions;

    /**
     * TagNum = 839. Starting with 4.4 version.
     */
    protected Double peggedPrice;

    /**
     * TagNum = 1035. Starting with 5.0 version.
     */
    protected Double peggedRefPrice;

    /**
     * TagNum = 845. Starting with 4.4 version.
     */
    protected Double discretionPrice;

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
     * TagNum = 850. Starting with 4.4 version.
     */
    protected Double targetStrategyPerformance;

    /**
     * TagNum = 211. Starting with 4.1 version.
     */
    protected Double pegOffsetValue;

    /**
     * TagNum = 388. Starting with 4.2 version.
     */
    protected DiscretionInst discretionInst;

    /**
     * TagNum = 389. Starting with 4.2 version.
     */
    protected Double discretionOffsetValue;

    /**
     * TagNum = 15. Starting with 4.0 version.
     */
    protected Currency currency;

    /**
     * TagNum = 376. Starting with 4.2 version.
     */
    protected String complianceID;

    /**
     * TagNum = 377. Starting with 4.2 version.
     */
    protected Boolean solicitedFlag;

    /**
     * TagNum = 59. Starting with 4.0 version.
     */
    protected TimeInForce timeInForce;

    /**
     * TagNum = 168. Starting with 4.2 version.
     */
    protected Date effectiveTime;

    /**
     * TagNum = 432. Starting with 4.2 version.
     */
    protected Date expireDate;

    /**
     * TagNum = 126. Starting with 4.0 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 18. Starting with 4.0 version.
     */
    protected String execInst;

    /**
     * TagNum = 1057. Starting with 5.0 version.
     */
    protected Boolean aggressorIndicator;

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
     * TagNum = 47. Starting with 4.0 version.
     */
    protected Rule80A rule80A;

    /**
     * TagNum = 32. Starting with 4.0 version.
     */
    protected Double lastQty;

    /**
     * TagNum = 1056. Starting with 5.0 version.
     */
    protected Double calculatedCcyLastQty;

    /**
     * TagNum = 1071. Starting with 5.0 version.
     */
    protected Double lastSwapPoints;

    /**
     * TagNum = 652. Starting with 4.3 version.
     */
    protected Double underlyingLastQty;

    /**
     * TagNum = 31. Starting with 4.0 version.
     */
    protected Double lastPx;

    /**
     * TagNum = 651. Starting with 4.4 version.
     */
    protected Double underlyingLastPx;

    /**
     * TagNum = 669. Starting with 4.4 version.
     */
    protected Double lastParPx;

    /**
     * TagNum = 194. Starting with 4.1 version.
     */
    protected Double lastSpotRate;

    /**
     * TagNum = 195. Starting with 4.1 version.
     */
    protected Double lastForwardPoints;

    /**
     * TagNum = 30. Starting with 4.0 version.
     */
    protected String lastMkt;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 943. Starting with 5.0 version.
     */
    protected String timeBracket;

    /**
     * TagNum = 29. Starting with 4.0 version.
     */
    protected LastCapacity lastCapacity;

    /**
     * TagNum = 151. Starting with 4.1 version.
     */
    protected Double leavesQty;

    /**
     * TagNum = 14 REQUIRED. Starting with 4.0 version.
     */
    protected Double cumQty;

    /**
     * TagNum = 6 REQUIRED. Starting with 4.0 version.
     */
    protected Double avgPx;

    /**
     * TagNum = 424. Starting with 4.2 version.
     */
    protected Double dayOrderQty;

    /**
     * TagNum = 425. Starting with 4.2 version.
     */
    protected Double dayCumQty;

    /**
     * TagNum = 426. Starting with 4.2 version.
     */
    protected Double dayAvgPx;

    /**
     * TagNum = 1361. Starting with 5.0SP1 version.
     */
    protected Integer totNoFills;

    /**
     * TagNum = 893. Starting with 5.0SP1 version.
     */
    protected Boolean lastFragment;

    /**
     * TagNum = 1362. Starting with 5.0SP1 version.
     */
    protected Integer noFills;

    /**
     * Starting with 5.0SP1 version.
     */
    protected FillExecGroup[] fillExecGroups;

    /**
     * TagNum = 427. Starting with 4.2 version.
     */
    protected GTBookingInst GTBookingInst;

    /**
     * TagNum = 75. Starting with 4.0 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 60. Starting with 4.0 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 113. Starting with 4.0 version.
     */
    protected Boolean reportToExch;

    /**
     * Starting with 4.3 version.
     */
    protected CommissionData commissionData;

    /**
     * Starting with 4.3 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * Starting with 4.3 version.
     */
    protected YieldData yieldData;

    /**
     * TagNum = 12. Starting with 4.0 version.
     */
    protected Double commission;

    /**
     * TagNum = 13. Starting with 4.0 version.
     */
    protected CommType commType;

    /**
     * TagNum = 381. Starting with 4.2 version.
     */
    protected Double grossTradeAmt;

    /**
     * TagNum = 157. Starting with 4.3 version.
     */
    protected Integer numDaysInterest;

    /**
     * TagNum = 230. Starting with 4.3 version.
     */
    protected Date exDate;

    /**
     * TagNum = 158. Starting with 4.3 version.
     */
    protected Double accruedInterestRate;

    /**
     * TagNum = 159. Starting with 4.3 version.
     */
    protected Double accruedInterestAmt;

    /**
     * TagNum = 738. Starting with 4.4 version.
     */
    protected Double interestAtMaturity;

    /**
     * TagNum = 920. Starting with 4.4 version.
     */
    protected Double endAccruedInterestAmt;

    /**
     * TagNum = 921. Starting with 4.4 version.
     */
    protected Double startCash;

    /**
     * TagNum = 922. Starting with 4.4 version.
     */
    protected Double endCash;

    /**
     * TagNum = 258. Starting with 4.3 version.
     */
    protected Boolean tradedFlatSwitch;

    /**
     * TagNum = 259. Starting with 4.3 version.
     */
    protected Date basisFeatureDate;

    /**
     * TagNum = 260. Starting with 4.3 version.
     */
    protected Double basisFeaturePrice;

    /**
     * TagNum = 238. Starting with 4.3 version.
     */
    protected Double concession;

    /**
     * TagNum = 237. Starting with 4.3 version.
     */
    protected Double totalTakedown;

    /**
     * TagNum = 118. Starting with 4.0 version.
     */
    protected Double netMoney;

    /**
     * TagNum = 119. Starting with 4.0 version.
     */
    protected Double settlCurrAmt;

    /**
     * TagNum = 120. Starting with 4.0 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 555. Starting with 5.0SP2 version.
     */
    protected Integer noRateSources;

    /**
     * Starting with 5.0SP2 version.
     */
    protected RateSourceGroup[] rateSources;

    /**
     * TagNum = 155. Starting with 4.1 version.
     */
    protected Double settlCurrFxRate;

    /**
     * TagNum = 156. Starting with 4.1 version.
     */
    protected SettlCurrFxRateCalc settlCurrFxRateCalc;

    /**
     * TagNum = 21. Starting with 4.2 version.
     */
    protected HandlInst handlInst;

    /**
     * TagNum = 110. Starting with 4.2 version.
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
     * TagNum = 111. Starting with 4.2 version.
     */
    protected Double maxFloor;

    /**
     * TagNum = 77. Starting with 4.2 version.
     */
    protected PositionEffect positionEffect;

    /**
     * TagNum = 210. Starting with 4.2 version.
     */
    protected Double maxShow;

    /**
     * TagNum = 775. Starting with 4.4 version.
     */
    protected BookingType bookingType;

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

    /**
     * TagNum = 193. Starting with 4.2 version.
     */
    protected Date settlDate2;

    /**
     * TagNum = 192. Starting with 4.2 version.
     */
    protected Double orderQty2;

    /**
     * TagNum = 439. Starting with 4.2 version.
     */
    protected String clearingFirm;

    /**
     * TagNum = 440. Starting with 4.2 version.
     */
    protected String clearingAccount;

    /**
     * TagNum = 641. Starting with 4.4 version.
     */
    protected Double lastForwardPoints2;

    /**
     * TagNum = 442. Starting with 4.2 version.
     */
    protected MultiLegReportingType multilegReportingType;

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
     * TagNum = 483. Starting with 4.3 version.
     */
    protected Date transBkdTime;

    /**
     * TagNum = 515. Starting with 4.3 version.
     */
    protected Date execValuationPoint;

    /**
     * TagNum = 484. Starting with 4.3 version.
     */
    protected ExecPriceType execPriceType;

    /**
     * TagNum = 485. Starting with 4.3 version.
     */
    protected Double execPriceAdjustment;

    /**
     * TagNum = 638. Starting with 4.3 version.
     */
    protected PriorityIndicator priorityIndicator;

    /**
     * TagNum = 639. Starting with 4.3 version.
     */
    protected Double priceImprovement;

    /**
     * TagNum = 851. Starting with 4.4 version.
     */
    protected LastLiquidityInd lastLiquidityInd;

    /**
     * TagNum = 518. Starting with 4.3 version.
     */
    protected Integer noContAmts;

    /**
     * Starting with 4.3 version.
     */
    protected ContAmtGroup[] contAmtGroups;

    /**
     * TagNum = 555. Starting with 4.3 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.3 version.
     */
    protected InstrmtLegExecGroup[] instrmtLegExecGroups;

    /**
     * TagNum = 797. Starting with 4.4 version.
     */
    protected Boolean copyMsgIndicator;

    /**
     * TagNum = 555. Starting with 4.0 version.
     */
    protected Integer noMiscFees;

    /**
     * Starting with 4.0 version.
     */
    protected MiscFeeGroup[] miscFeeGroups;

    /**
     * TagNum = 1380. Starting with 5.0SP1 version.
     */
    protected Double dividendYield;

    /**
     * TagNum = 1028. Starting with 5.0 version.
     */
    protected Boolean manualOrderIndicator;

    /**
     * TagNum = 1029. Starting with 5.0 version.
     */
    protected Boolean custDirectedOrder;

    /**
     * TagNum = 1030. Starting with 5.0 version.
     */
    protected String receivedDeptID;

    /**
     * TagNum = 1031. Starting with 5.0 version.
     */
    protected String custOrderHandlingInst;

    /**
     * TagNum = 1032. Starting with 5.0 version.
     */
    protected Integer orderHandlingInstSource;

    /**
     * TagNum = 768. Starting with 5.0 version.
     */
    protected Integer noTrdRegTimestamps;

    /**
     * Starting with 5.0 version.
     */
    protected TrdRegTimestampsGroup[] trdRegTimestampsGroups;

    /**
     * TagNum = 1188. Starting with 5.0SP1 version.
     */
    protected Double volatility;

    /**
     * TagNum = 1189. Starting with 5.0SP1 version.
     */
    protected Double timeToExpiration;

    /**
     * TagNum = 1190. Starting with 5.0SP1 version.
     */
    protected Double riskFreeRate;

    /**
     * TagNum = 811. Starting with 5.0SP1 version.
     */
    protected Double priceDelta;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ExecutionReportMsg() {
        super();
    }

    public ExecutionReportMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ExecutionReportMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.ExecutionReport.getValue(), beginString);
    }

    public ExecutionReportMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.ExecutionReport.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    /**
     * Message field setter.
     * @param secondaryOrderID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
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
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public String getSecondaryExecID() {
        return secondaryExecID;
    }

    /**
     * Message field setter.
     * @param secondaryExecID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public void setSecondaryExecID(String secondaryExecID) {
        this.secondaryExecID = secondaryExecID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.OrigClOrdID)
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    /**
     * Message field setter.
     * @param origClOrdID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.OrigClOrdID)
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QuoteRespID)
    public String getQuoteRespID() {
        return quoteRespID;
    }

    /**
     * Message field setter.
     * @param quoteRespID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QuoteRespID)
    public void setQuoteRespID(String quoteRespID) {
        this.quoteRespID = quoteRespID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrdStatusReqID)
    public String getOrdStatusReqID() {
        return ordStatusReqID;
    }

    /**
     * Message field setter.
     * @param ordStatusReqID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrdStatusReqID)
    public void setOrdStatusReqID(String ordStatusReqID) {
        this.ordStatusReqID = ordStatusReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MassStatusReqID)
    public String getMassStatusReqID() {
        return massStatusReqID;
    }

    /**
     * Message field setter.
     * @param massStatusReqID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MassStatusReqID)
    public void setMassStatusReqID(String massStatusReqID) {
        this.massStatusReqID = massStatusReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.HostCrossID)
    public String getHostCrossID() {
        return hostCrossID;
    }

    /**
     * Message field setter.
     * @param hostCrossID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.HostCrossID)
    public void setHostCrossID(String hostCrossID) {
        this.hostCrossID = hostCrossID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumReports)
    public Integer getTotNumReports() {
        return totNumReports;
    }

    /**
     * Message field setter.
     * @param totNumReports field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumReports)
    public void setTotNumReports(Integer totNumReports) {
        this.totNumReports = totNumReports;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastRptRequested)
    public Boolean getLastRptRequested() {
        return lastRptRequested;
    }

    /**
     * Message field setter.
     * @param lastRptRequested field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastRptRequested)
    public void setLastRptRequested(Boolean lastRptRequested) {
        this.lastRptRequested = lastRptRequested;
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
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public String getClientID() {
        return clientID;
    }

    /**
     * Message field setter.
     * @param clientID field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public String getExecBroker() {
        return execBroker;
    }

    /**
     * Message field setter.
     * @param execBroker field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public void setExecBroker(String execBroker) {
        this.execBroker = execBroker;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoContraBrokers)
    public Integer getNoContraBrokers() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link ContraBrokerGroup} groups. It will also create an array
     * of {@link ContraBrokerGroup} objects and set the <code>contraBrokers</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>contraBrokers</code> array they will be discarded.<br/>
     * @param noContraBrokers field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoContraBrokers)
    public void setNoContraBrokers(Integer noContraBrokers) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    public ContraBrokerGroup[] getContraBrokers() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ContraBrokerGroup} object to the existing array of <code>contraBrokers</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noContraBrokers</code> field to the proper value.<br/>
     * Note: If the <code>setNoContraBrokers</code> method has been called there will already be a number of objects in the
     * <code>contraBrokers</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public ContraBrokerGroup addContraBroker() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link ContraBrokerGroup} object from the existing array of <code>contraBrokers</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noContraBrokers</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public ContraBrokerGroup deleteContraBroker(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link ContraBrokerGroup} objects from the <code>contraBrokers</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noContraBrokers</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearContraBrokers() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossID)
    public String getCrossID() {
        return crossID;
    }

    /**
     * Message field setter.
     * @param crossID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossID)
    public void setCrossID(String crossID) {
        this.crossID = crossID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrigCrossID)
    public String getOrigCrossID() {
        return origCrossID;
    }

    /**
     * Message field setter.
     * @param origCrossID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrigCrossID)
    public void setOrigCrossID(String origCrossID) {
        this.origCrossID = origCrossID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossType)
    public CrossType getCrossType() {
        return crossType;
    }

    /**
     * Message field setter.
     * @param crossType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossType)
    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TrdMatchID)
    public String getTrdMatchID() {
        return trdMatchID;
    }

    /**
     * Message field setter.
     * @param trdMatchID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TrdMatchID)
    public void setTrdMatchID(String trdMatchID) {
        this.trdMatchID = trdMatchID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecID)
    public String getExecID() {
        return execID;
    }

    /**
     * Message field setter.
     * @param execID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecID)
    public void setExecID(String execID) {
        this.execID = execID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.3")
    @TagNumRef(tagNum=TagNum.ExecTransType)
    public ExecTransType getExecTransType() {
        return execTransType;
    }

    /**
     * Message field setter.
     * @param execTransType field value
     */
    @FIXVersion(introduced="4.0", retired = "4.3")
    @TagNumRef(tagNum=TagNum.ExecTransType)
    public void setExecTransType(ExecTransType execTransType) {
        this.execTransType = execTransType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecRefID)
    public String getExecRefID() {
        return execRefID;
    }

    /**
     * Message field setter.
     * @param execRefID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecRefID)
    public void setExecRefID(String execRefID) {
        this.execRefID = execRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.ExecType)
    public ExecType getExecType() {
        return execType;
    }

    /**
     * Message field setter.
     * @param execType field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.ExecType)
    public void setExecType(ExecType execType) {
        this.execType = execType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    /**
     * Message field setter.
     * @param ordStatus field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdStatus)
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.WorkingIndicator)
    public Boolean getWorkingIndicator() {
        return workingIndicator;
    }

    /**
     * Message field setter.
     * @param workingIndicator field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.WorkingIndicator)
    public void setWorkingIndicator(Boolean workingIndicator) {
        this.workingIndicator = workingIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdRejReason)
    public OrdRejReason getOrdRejReason() {
        return ordRejReason;
    }

    /**
     * Message field setter.
     * @param ordRejReason field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdRejReason)
    public void setOrdRejReason(OrdRejReason ordRejReason) {
        this.ordRejReason = ordRejReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExecRestatementReason)
    public ExecRestatementReason getExecRestatementReason() {
        return execRestatementReason;
    }

    /**
     * Message field setter.
     * @param execRestatementReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExecRestatementReason)
    public void setExecRestatementReason(ExecRestatementReason execRestatementReason) {
        this.execRestatementReason = execRestatementReason;
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.AllocID)
    public String getAllocID() {
        return allocID;
    }

    /**
     * Message field setter.
     * @param allocID field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.AllocID)
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public Integer getNoAllocs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link PreTradeAllocGroup} groups. It will also create an array
     * of {@link PreTradeAllocGroup} objects and set the <code>allocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>allocGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public void setNoAllocs(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PreTradeAllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public PreTradeAllocGroup[] getAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link PreTradeAllocGroup} object to the existing array of <code>allocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * Note: If the <code>setNoAllocs</code> method has been called there will already be a number of objects in the
     * <code>allocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public PreTradeAllocGroup addAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link PreTradeAllocGroup} object from the existing array of <code>allocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public PreTradeAllocGroup deleteAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PreTradeAllocGroup} objects from the <code>allocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchType)
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * Message field setter.
     * @param matchType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchType)
    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrderCategory)
    public OrderCategory getOrderCategory() {
        return orderCategory;
    }

    /**
     * Message field setter.
     * @param orderCategory field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrderCategory)
    public void setOrderCategory(OrderCategory orderCategory) {
        this.orderCategory = orderCategory;
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
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required=true)
    public String getSymbol() {
        return getSafeInstrument().getSymbol();
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required=true)
    public void setSymbol(String symbol) {
        getSafeInstrument().setSymbol(symbol);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public String getSymbolSfx() {
        return getSafeInstrument().getSymbolSfx();
    }

    /**
     * Message field setter.
     * @param symbolSfx field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public void setSymbolSfx(String symbolSfx) {
        getSafeInstrument().setSymbolSfx(symbolSfx);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public String getSecurityID() {
        return getSafeInstrument().getSecurityID();
    }

    /**
     * Message field setter.
     * @param securityID field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public void setSecurityID(String securityID) {
        getSafeInstrument().setSecurityID(securityID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public String getSecurityIDSource() {
        return getSafeInstrument().getSecurityIDSource();
    }

    /**
     * Message field setter.
     * @param securityIDSource field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public void setSecurityIDSource(String securityIDSource) {
        getSafeInstrument().setSecurityIDSource(securityIDSource);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return getSafeInstrument().getSecurityType();
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        getSafeInstrument().setSecurityType(securityType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public String getMaturityMonthYear() {
        return getSafeInstrument().getMaturityMonthYear();
    }

    /**
     * Message field setter.
     * @param maturityMonthYear field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public void setMaturityMonthYear(String maturityMonthYear) {
        getSafeInstrument().setMaturityMonthYear(maturityMonthYear);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public Integer getMaturityDay() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maturityDay field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public void setMaturityDay(Integer maturityDay) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public PutOrCall getPutOrCall() {
        return getSafeInstrument().getPutOrCall();
    }

    /**
     * Message field setter.
     * @param putOrCall field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public void setPutOrCall(PutOrCall putOrCall) {
        getSafeInstrument().setPutOrCall(putOrCall);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public Double getStrikePrice() {
        return getSafeInstrument().getStrikePrice();
    }

    /**
     * Message field setter.
     * @param strikePrice field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public void setStrikePrice(Double strikePrice) {
        getSafeInstrument().setStrikePrice(strikePrice);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public Double getContractMultiplier() {
        return getSafeInstrument().getContractMultiplier();
    }

    /**
     * Message field setter.
     * @param contractMultiplier field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public void setContractMultiplier(Double contractMultiplier) {
        getSafeInstrument().setContractMultiplier(contractMultiplier);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public Double getCouponRate() {
        return getSafeInstrument().getCouponRate();
    }

    /**
     * Message field setter.
     * @param couponRate field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public void setCouponRate(Double couponRate) {
        getSafeInstrument().setCouponRate(couponRate);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public Character getOptAttribute() {
        return getSafeInstrument().getOptAttribute();
    }

    /**
     * Message field setter.
     * @param optAttribute field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public void setOptAttribute(Character optAttribute) {
        getSafeInstrument().setOptAttribute(optAttribute);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public String getSecurityExchange() {
        return getSafeInstrument().getSecurityExchange();
    }

    /**
     * Message field setter.
     * @param securityExchange field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public void setSecurityExchange(String securityExchange) {
        getSafeInstrument().setSecurityExchange(securityExchange);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public String getIssuer() {
        return getSafeInstrument().getIssuer();
    }

    /**
     * Message field setter.
     * @param issuer field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public void setIssuer(String issuer) {
        getSafeInstrument().setIssuer(issuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public Integer getEncodedIssuerLen() {
        return getSafeInstrument().getEncodedIssuerLen();
    }

    /**
     * Message field setter.
     * @param encodedIssuerLen field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        getSafeInstrument().setEncodedIssuerLen(encodedIssuerLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public byte[] getEncodedIssuer() {
        return getSafeInstrument().getEncodedIssuer();
    }

    /**
     * Message field setter.
     * @param encodedIssuer field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public void setEncodedIssuer(byte[] encodedIssuer) {
        getSafeInstrument().setEncodedIssuer(encodedIssuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public String getSecurityDesc() {
        return getSafeInstrument().getSecurityDesc();
    }

    /**
     * Message field setter.
     * @param securityDesc field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public void setSecurityDesc(String securityDesc) {
        getSafeInstrument().setSecurityDesc(securityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public Integer getEncodedSecurityDescLen() {
        return getSafeInstrument().getEncodedSecurityDescLen();
    }

    /**
     * Message field setter.
     * @param encodedSecurityDescLen field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        getSafeInstrument().setEncodedSecurityDescLen(encodedSecurityDescLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public byte[] getEncodedSecurityDesc() {
        return getSafeInstrument().getEncodedSecurityDesc();
    }

    /**
     * Message field setter.
     * @param encodedSecurityDesc field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        getSafeInstrument().setEncodedSecurityDesc(encodedSecurityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Side, required = true)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Side, required = true)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public Stipulations getStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.OrderQty)
    public Double getOrderQty() {
        return getSafeOrderQtyData().getOrderQty();
    }

    /**
     * Message field setter.
     * @param orderQty field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.OrderQty)
    public void setOrderQty(Double orderQty) {
        getSafeOrderQtyData().setOrderQty(orderQty);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CashOrderQty)
    public Double getCashOrderQty() {
        return getSafeOrderQtyData().getCashOrderQty();
    }

    /**
     * Message field setter.
     * @param cashOrderQty field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CashOrderQty)
    public void setCashOrderQty(Double cashOrderQty) {
        getSafeOrderQtyData().setCashOrderQty(cashOrderQty);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LotType)
    public LotType getLotType() {
        return lotType;
    }

    /**
     * Message field setter.
     * @param lotType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LotType)
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdType)
    public OrdType getOrdType() {
        return ordType;
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.0")
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.StopPx)
    public Double getStopPx() {
        return stopPx;
    }

    /**
     * Message field setter.
     * @param stopPx field value
     */
    @FIXVersion(introduced="4.0")
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
    @TagNumRef(tagNum=TagNum.PeggedPrice)
    public Double getPeggedPrice() {
        return peggedPrice;
    }

    /**
     * Message field setter.
     * @param peggedPrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PeggedPrice)
    public void setPeggedPrice(Double peggedPrice) {
        this.peggedPrice = peggedPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PeggedRefPrice)
    public Double getPeggedRefPrice() {
        return peggedRefPrice;
    }

    /**
     * Message field setter.
     * @param peggedRefPrice field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PeggedRefPrice)
    public void setPeggedRefPrice(Double peggedRefPrice) {
        this.peggedRefPrice = peggedRefPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionPrice)
    public Double getDiscretionPrice() {
        return discretionPrice;
    }

    /**
     * Message field setter.
     * @param discretionPrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionPrice)
    public void setDiscretionPrice(Double discretionPrice) {
        this.discretionPrice = discretionPrice;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategyPerformance)
    public Double getTargetStrategyPerformance() {
        return targetStrategyPerformance;
    }

    /**
     * Message field setter.
     * @param targetStrategyPerformance field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategyPerformance)
    public void setTargetStrategyPerformance(Double targetStrategyPerformance) {
        this.targetStrategyPerformance = targetStrategyPerformance;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    /**
     * Message field setter.
     * @param pegOffsetValue field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public void setPegOffsetValue(Double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionInst)
    public DiscretionInst getDiscretionInst() {
        return discretionInst;
    }

    /**
     * Message field setter.
     * @param discretionInst field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionInst)
    public void setDiscretionInst(DiscretionInst discretionInst) {
        this.discretionInst = discretionInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionOffsetValue)
    public Double getDiscretionOffsetValue() {
        return discretionOffsetValue;
    }

    /**
     * Message field setter.
     * @param discretionOffsetValue field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionOffsetValue)
    public void setDiscretionOffsetValue(Double discretionOffsetValue) {
        this.discretionOffsetValue = discretionOffsetValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ComplianceID)
    public String getComplianceID() {
        return complianceID;
    }

    /**
     * Message field setter.
     * @param complianceID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ComplianceID)
    public void setComplianceID(String complianceID) {
        this.complianceID = complianceID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SolicitedFlag)
    public Boolean getSolicitedFlag() {
        return solicitedFlag;
    }

    /**
     * Message field setter.
     * @param solicitedFlag field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SolicitedFlag)
    public void setSolicitedFlag(Boolean solicitedFlag) {
        this.solicitedFlag = solicitedFlag;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    /**
     * Message field setter.
     * @param timeInForce field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Message field setter.
     * @param effectiveTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Message field setter.
     * @param expireDate field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        return execInst;
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AggressorIndicator)
    public Boolean getAggressorIndicator() {
        return aggressorIndicator;
    }

    /**
     * Message field setter.
     * @param aggressorIndicator field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AggressorIndicator)
    public void setAggressorIndicator(Boolean aggressorIndicator) {
        this.aggressorIndicator = aggressorIndicator;
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
    @FIXVersion(introduced="4.0", retired="4.4")
    @TagNumRef(tagNum=TagNum.Rule80A)
    public Rule80A getRule80A() {
        return rule80A;
    }

    /**
     * Message field setter.
     * @param rule80A field value
     */
    @FIXVersion(introduced="4.0", retired="4.4")
    @TagNumRef(tagNum=TagNum.Rule80A)
    public void setRule80A(Rule80A rule80A) {
        this.rule80A = rule80A;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastQty)
    public Double getLastQty() {
        return lastQty;
    }

    /**
     * Message field setter.
     * @param lastQty field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastQty)
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CalculatedCcyLastQty)
    public Double getCalculatedCcyLastQty() {
        return calculatedCcyLastQty;
    }

    /**
     * Message field setter.
     * @param calculatedCcyLastQty field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CalculatedCcyLastQty)
    public void setCalculatedCcyLastQty(Double calculatedCcyLastQty) {
        this.calculatedCcyLastQty = calculatedCcyLastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastSwapPoints)
    public Double getLastSwapPoints() {
        return lastSwapPoints;
    }

    /**
     * Message field setter.
     * @param lastSwapPoints field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LastSwapPoints)
    public void setLastSwapPoints(Double lastSwapPoints) {
        this.lastSwapPoints = lastSwapPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingLastQty)
    public Double getUnderlyingLastQty() {
        return underlyingLastQty;
    }

    /**
     * Message field setter.
     * @param underlyingLastQty field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.UnderlyingLastQty)
    public void setUnderlyingLastQty(Double underlyingLastQty) {
        this.underlyingLastQty = underlyingLastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastPx)
    public Double getLastPx() {
        return lastPx;
    }

    /**
     * Message field setter.
     * @param lastPx field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastPx)
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingLastPx)
    public Double getUnderlyingLastPx() {
        return underlyingLastPx;
    }

    /**
     * Message field setter.
     * @param underlyingLastPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingLastPx)
    public void setUnderlyingLastPx(Double underlyingLastPx) {
        this.underlyingLastPx = underlyingLastPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastParPx)
    public Double getLastParPx() {
        return lastParPx;
    }

    /**
     * Message field setter.
     * @param lastParPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastParPx)
    public void setLastParPx(Double lastParPx) {
        this.lastParPx = lastParPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LastSpotRate)
    public Double getLastSpotRate() {
        return lastSpotRate;
    }

    /**
     * Message field setter.
     * @param lastSpotRate field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LastSpotRate)
    public void setLastSpotRate(Double lastSpotRate) {
        this.lastSpotRate = lastSpotRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LastForwardPoints)
    public Double getLastForwardPoints() {
        return lastForwardPoints;
    }

    /**
     * Message field setter.
     * @param lastForwardPoints field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LastForwardPoints)
    public void setLastForwardPoints(Double lastForwardPoints) {
        this.lastForwardPoints = lastForwardPoints;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public String getLastMkt() {
        return lastMkt;
    }

    /**
     * Message field setter.
     * @param lastMkt field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TimeBracket)
    public String getTimeBracket() {
        return timeBracket;
    }

    /**
     * Message field setter.
     * @param timeBracket field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TimeBracket)
    public void setTimeBracket(String timeBracket) {
        this.timeBracket = timeBracket;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastCapacity)
    public LastCapacity getLastCapacity() {
        return lastCapacity;
    }

    /**
     * Message field setter.
     * @param lastCapacity field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastCapacity)
    public void setLastCapacity(LastCapacity lastCapacity) {
        this.lastCapacity = lastCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LeavesQty)
    public Double getLeavesQty() {
        return leavesQty;
    }

    /**
     * Message field setter.
     * @param leavesQty field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LeavesQty)
    public void setLeavesQty(Double leavesQty) {
        this.leavesQty = leavesQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.CumQty, required = true)
    public Double getCumQty() {
        return cumQty;
    }

    /**
     * Message field setter.
     * @param cumQty field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.CumQty, required = true)
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AvgPx, required = true)
    public Double getAvgPx() {
        return avgPx;
    }

    /**
     * Message field setter.
     * @param avgPx field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AvgPx, required = true)
    public void setAvgPx(Double avgPx) {
        this.avgPx = avgPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DayOrderQty)
    public Double getDayOrderQty() {
        return dayOrderQty;
    }

    /**
     * Message field setter.
     * @param dayOrderQty field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DayOrderQty)
    public void setDayOrderQty(Double dayOrderQty) {
        this.dayOrderQty = dayOrderQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DayCumQty)
    public Double getDayCumQty() {
        return dayCumQty;
    }

    /**
     * Message field setter.
     * @param dayCumQty field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DayCumQty)
    public void setDayCumQty(Double dayCumQty) {
        this.dayCumQty = dayCumQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DayAvgPx)
    public Double getDayAvgPx() {
        return dayAvgPx;
    }

    /**
     * Message field setter.
     * @param dayAvgPx field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DayAvgPx)
    public void setDayAvgPx(Double dayAvgPx) {
        this.dayAvgPx = dayAvgPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoFills)
    public Integer getTotNoFills() {
        return totNoFills;
    }

    /**
     * Message field setter.
     * @param totNoFills field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TotNoFills)
    public void setTotNoFills(Integer totNoFills) {
        this.totNoFills = totNoFills;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public Boolean getLastFragment() {
        return lastFragment;
    }

    /**
     * Message field setter.
     * @param lastFragment field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoFills)
    public Integer getNoFills() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link FillExecGroup} groups. It will also create an array
     * of {@link FillExecGroup} objects and set the <code>fillExecGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>fillExecGroups</code> array they will be discarded.<br/>
     * @param noFills field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoFills)
    public void setNoFills(Integer noFills) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link FillExecGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public FillExecGroup[] getFillExecGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link FillExecGroup} object to the existing array of <code>FillExecGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noFills</code> field to the proper value.<br/>
     * Note: If the <code>setNoFills</code> method has been called there will already be a number of objects in the
     * <code>FillExecGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public FillExecGroup addFillExecGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link FillExecGroup} object from the existing array of <code>fillExecGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noFills</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public FillExecGroup deleteFillExecGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link FillExecGroup} objects from the <code>fillExecGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noFills</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearFillExecGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.GTBookingInst)
    public GTBookingInst getGTBookingInst() {
        return GTBookingInst;
    }

    /**
     * Message field setter.
     * @param GTBookingInst field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.GTBookingInst)
    public void setGTBookingInst(GTBookingInst GTBookingInst) {
        this.GTBookingInst = GTBookingInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ReportToExch)
    public Boolean getReportToExch() {
        return reportToExch;
    }

    /**
     * Message field setter.
     * @param reportToExch field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ReportToExch)
    public void setReportToExch(Boolean reportToExch) {
        this.reportToExch = reportToExch;
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
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public YieldData getYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the YieldData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public Double getCommission() {
        return getSafeCommissionData().getCommission();
    }

    /**
     * Message field setter.
     * @param commission field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public void setCommission(Double commission) {
        getSafeCommissionData().setCommission(commission);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public CommType getCommType() {
        return getSafeCommissionData().getCommType();
    }

    /**
     * Message field setter.
     * @param commType field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public void setCommType(CommType commType) {
        getSafeCommissionData().setCommType(commType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public Double getGrossTradeAmt() {
        return grossTradeAmt;
    }

    /**
     * Message field setter.
     * @param grossTradeAmt field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public void setGrossTradeAmt(Double grossTradeAmt) {
        this.grossTradeAmt = grossTradeAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NumDaysInterest)
    public Integer getNumDaysInterest() {
        return numDaysInterest;
    }

    /**
     * Message field setter.
     * @param numDaysInterest field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NumDaysInterest)
    public void setNumDaysInterest(Integer numDaysInterest) {
        this.numDaysInterest = numDaysInterest;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDate)
    public Date getExDate() {
        return exDate;
    }

    /**
     * Message field setter.
     * @param exDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDate)
    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public Double getAccruedInterestRate() {
        return accruedInterestRate;
    }

    /**
     * Message field setter.
     * @param accruedInterestRate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public void setAccruedInterestRate(Double accruedInterestRate) {
        this.accruedInterestRate = accruedInterestRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param accruedInterestAmt field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.InterestAtMaturity)
    public Double getInterestAtMaturity() {
        return interestAtMaturity;
    }

    /**
     * Message field setter.
     * @param interestAtMaturity field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.InterestAtMaturity)
    public void setInterestAtMaturity(Double interestAtMaturity) {
        this.interestAtMaturity = interestAtMaturity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndAccruedInterestAmt)
    public Double getEndAccruedInterestAmt() {
        return endAccruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param endAccruedInterestAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndAccruedInterestAmt)
    public void setEndAccruedInterestAmt(Double endAccruedInterestAmt) {
        this.endAccruedInterestAmt = endAccruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StartCash)
    public Double getStartCash() {
        return startCash;
    }

    /**
     * Message field setter.
     * @param startCash field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StartCash)
    public void setStartCash(Double startCash) {
        this.startCash = startCash;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndCash)
    public Double getEndCash() {
        return endCash;
    }

    /**
     * Message field setter.
     * @param endCash field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndCash)
    public void setEndCash(Double endCash) {
        this.endCash = endCash;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradedFlatSwitch)
    public Boolean getTradedFlatSwitch() {
        return tradedFlatSwitch;
    }

    /**
     * Message field setter.
     * @param tradedFlatSwitch field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradedFlatSwitch)
    public void setTradedFlatSwitch(Boolean tradedFlatSwitch) {
        this.tradedFlatSwitch = tradedFlatSwitch;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BasisFeatureDate)
    public Date getBasisFeatureDate() {
        return basisFeatureDate;
    }

    /**
     * Message field setter.
     * @param basisFeatureDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BasisFeatureDate)
    public void setBasisFeatureDate(Date basisFeatureDate) {
        this.basisFeatureDate = basisFeatureDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BasisFeaturePrice)
    public Double getBasisFeaturePrice() {
        return basisFeaturePrice;
    }

    /**
     * Message field setter.
     * @param basisFeaturePrice field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.BasisFeaturePrice)
    public void setBasisFeaturePrice(Double basisFeaturePrice) {
        this.basisFeaturePrice = basisFeaturePrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Concession)
    public Double getConcession() {
        return concession;
    }

    /**
     * Message field setter.
     * @param concession field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Concession)
    public void setConcession(Double concession) {
        this.concession = concession;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TotalTakedown)
    public Double getTotalTakedown() {
        return totalTakedown;
    }

    /**
     * Message field setter.
     * @param totalTakedown field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TotalTakedown)
    public void setTotalTakedown(Double totalTakedown) {
        this.totalTakedown = totalTakedown;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public Double getNetMoney() {
        return netMoney;
    }

    /**
     * Message field setter.
     * @param netMoney field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlCurrAmt)
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    /**
     * Message field setter.
     * @param settlCurrAmt field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlCurrAmt)
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRateSources)
    public Integer getNoRateSources() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link RateSource} groups. It will also create an array
     * of {@link RateSource} objects and set the <code>rateSources</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>rateSources</code> array they will be discarded.<br/>
     * @param noRateSources field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRateSources)
    public void setNoRateSources(Integer noRateSources) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RateSource} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP2")
    public RateSourceGroup[] getRateSources() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link RateSource} object to the existing array of <code>rateSources</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRateSources</code> field to the proper value.<br/>
     * Note: If the <code>setNoRateSources</code> method has been called there will already be a number of objects in the
     * <code>rateSources</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0SP2")
    public RateSourceGroup addRateSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RateSource} object from the existing array of <code>rateSources</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRateSources</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0SP2")
    public RateSourceGroup deleteRateSource(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link RateSource} objects from the <code>rateSources</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRateSources</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0SP2")
    public int clearRateSources() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRate)
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    /**
     * Message field setter.
     * @param settlCurrFxRate field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRate)
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    /**
     * Message field setter.
     * @param settlCurrFxRateCalc field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }
   
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public HandlInst getHandlInst() {
        return handlInst;
    }

    /**
     * Message field setter.
     * @param handlInst field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public void setHandlInst(HandlInst handlInst) {
        this.handlInst = handlInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MinQty)
    public Double getMinQty() {
        return minQty;
    }

    /**
     * Message field setter.
     * @param minQty field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public Double getMaxFloor() {
        return maxFloor;
    }

    /**
     * Message field setter.
     * @param maxFloor field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public void setMaxFloor(Double maxFloor) {
        this.maxFloor = maxFloor;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public PositionEffect getPositionEffect() {
        return positionEffect;
    }

    /**
     * Message field setter.
     * @param positionEffect field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.PositionEffect)
    public void setPositionEffect(PositionEffect positionEffect) {
        this.positionEffect = positionEffect;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public Double getMaxShow() {
        return maxShow;
    }

    /**
     * Message field setter.
     * @param maxShow field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public void setMaxShow(Double maxShow) {
        this.maxShow = maxShow;
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.0")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlDate2)
    public Date getSettlDate2() {
        return settlDate2;
    }

    /**
     * Message field setter.
     * @param settlDate2 field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlDate2)
    public void setSettlDate2(Date settlDate2) {
        this.settlDate2 = settlDate2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrderQty2)
    public Double getOrderQty2() {
        return orderQty2;
    }

    /**
     * Message field setter.
     * @param orderQty2 field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrderQty2)
    public void setOrderQty2(Double orderQty2) {
        this.orderQty2 = orderQty2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClearingFirm)
    public String getClearingFirm() {
        return clearingFirm;
    }

    /**
     * Message field setter.
     * @param clearingFirm field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClearingFirm)
    public void setClearingFirm(String clearingFirm) {
        this.clearingFirm = clearingFirm;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClearingAccount)
    public String getClearingAccount() {
        return clearingAccount;
    }

    /**
     * Message field setter.
     * @param clearingAccount field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClearingAccount)
    public void setClearingAccount(String clearingAccount) {
        this.clearingAccount = clearingAccount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastForwardPoints2)
    public Double getLastForwardPoints2() {
        return lastForwardPoints2;
    }

    /**
     * Message field setter.
     * @param lastForwardPoints2 field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastForwardPoints2)
    public void setLastForwardPoints2(Double lastForwardPoints2) {
        this.lastForwardPoints2 = lastForwardPoints2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    /**
     * Message field setter.
     * @param multilegReportingType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
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
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    /**
     * Message field setter.
     * @param transBkdTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecValuationPoint)
    public Date getExecValuationPoint() {
        return execValuationPoint;
    }

    /**
     * Message field setter.
     * @param execValuationPoint field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecValuationPoint)
    public void setExecValuationPoint(Date execValuationPoint) {
        this.execValuationPoint = execValuationPoint;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecPriceType)
    public ExecPriceType getExecPriceType() {
        return execPriceType;
    }

    /**
     * Message field setter.
     * @param execPriceType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecPriceType)
    public void setExecPriceType(ExecPriceType execPriceType) {
        this.execPriceType = execPriceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecPriceAdjustment)
    public Double getExecPriceAdjustment() {
        return execPriceAdjustment;
    }

    /**
     * Message field setter.
     * @param execPriceAdjustment field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecPriceAdjustment)
    public void setExecPriceAdjustment(Double execPriceAdjustment) {
        this.execPriceAdjustment = execPriceAdjustment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriorityIndicator)
    public PriorityIndicator getPriorityIndicator() {
        return priorityIndicator;
    }

    /**
     * Message field setter.
     * @param priorityIndicator field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriorityIndicator)
    public void setPriorityIndicator(PriorityIndicator priorityIndicator) {
        this.priorityIndicator = priorityIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceImprovement)
    public Double getPriceImprovement() {
        return priceImprovement;
    }

    /**
     * Message field setter.
     * @param priceImprovement field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceImprovement)
    public void setPriceImprovement(Double priceImprovement) {
        this.priceImprovement = priceImprovement;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastLiquidityInd)
    public LastLiquidityInd getLastLiquidityInd() {
        return lastLiquidityInd;
    }

    /**
     * Message field setter.
     * @param lastLiquidityInd field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastLiquidityInd)
    public void setLastLiquidityInd(LastLiquidityInd lastLiquidityInd) {
        this.lastLiquidityInd = lastLiquidityInd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoContAmts)
    public Integer getNoContAmts() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link ContAmtGroup} groups. It will also create an array
     * of {@link ContAmtGroup} objects and set the <code>contAmtGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>contAmtGroups</code> array they will be discarded.<br/>
     * @param noContAmts field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoContAmts)
    public void setNoContAmts(Integer noContAmts) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link ContAmtGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public ContAmtGroup[] getContAmtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ContAmtGroup} object to the existing array of <code>ContAmtGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noContAmts</code> field to the proper value.<br/>
     * Note: If the <code>setNoContAmts</code> method has been called there will already be a number of objects in the
     * <code>ContAmtGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public ContAmtGroup addContAmtGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link ContAmtGroup} object from the existing array of <code>ContAmtGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noContAmts</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public ContAmtGroup deleteContAmtGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link ContAmtGroup} objects from the <code>ContAmtGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noContAmts</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearContAmtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
     * This method sets the number of {@link InstrmtLegExecGroup} groups. It will also create an array
     * of {@link InstrmtLegExecGroup} objects and set the <code>instrmtLegExecGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrmtLegExecGroups</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrmtLegExecGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public InstrmtLegExecGroup[] getInstrmtLegExecGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrmtLegExecGroup} object to the existing array of <code>instrmtLegExecGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>instrmtLegExecGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public InstrmtLegExecGroup addInstrmtLegExecGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrmtLegExecGroup} object from the existing array of <code>instrmtLegExecGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public InstrmtLegExecGroup deleteInstrmtLegExecGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrmtLegExecGroup} objects from the <code>instrmtLegExecGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearInstrmtLegExecGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CopyMsgIndicator)
    public Boolean getCopyMsgIndicator() {
        return copyMsgIndicator;
    }

    /**
     * Message field setter.
     * @param copyMsgIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CopyMsgIndicator)
    public void setCopyMsgIndicator(Boolean copyMsgIndicator) {
        this.copyMsgIndicator = copyMsgIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NoMiscFees)
    public Integer getNoMiscFees() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MiscFeeGroup} groups. It will also create an array
     * of {@link MiscFeeGroup} objects and set the <code>miscFeeGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>miscFeeGroups</code> array they will be discarded.<br/>
     * @param noMiscFees field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NoMiscFees)
    public void setNoMiscFees(Integer noMiscFees) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MiscFeeGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.0")
    public MiscFeeGroup[] getMiscFeeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MiscFeeGroup} object to the existing array of <code>miscFeeGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMiscFees</code> field to the proper value.<br/>
     * Note: If the <code>setNoMiscFees</code> method has been called there will already be a number of objects in the
     * <code>MiscFeeGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.0")
    public MiscFeeGroup addMiscFeeGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MiscFeeGroup} object from the existing array of <code>miscFeeGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMiscFees</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.0")
    public MiscFeeGroup deleteMiscFeeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MiscFeeGroup} objects from the <code>miscFeeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMiscFees</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.0")
    public int clearMiscFeeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DividendYield)
    public Double getDividendYield() {
        return dividendYield;
    }

    /**
     * Message field setter.
     * @param dividendYield field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.DividendYield)
    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ManualOrderIndicator)
    public Boolean getManualOrderIndicator() {
        return manualOrderIndicator;
    }

    /**
     * Message field setter.
     * @param manualOrderIndicator field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ManualOrderIndicator)
    public void setManualOrderIndicator(Boolean manualOrderIndicator) {
        this.manualOrderIndicator = manualOrderIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CustDirectedOrder)
    public Boolean getCustDirectedOrder() {
        return custDirectedOrder;
    }

    /**
     * Message field setter.
     * @param custDirectedOrder field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CustDirectedOrder)
    public void setCustDirectedOrder(Boolean custDirectedOrder) {
        this.custDirectedOrder = custDirectedOrder;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ReceivedDeptID)
    public String getReceivedDeptID() {
        return receivedDeptID;
    }

    /**
     * Message field setter.
     * @param receivedDeptID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ReceivedDeptID)
    public void setReceivedDeptID(String receivedDeptID) {
        this.receivedDeptID = receivedDeptID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CustOrderHandlingInst)
    public String getCustOrderHandlingInst() {
        return custOrderHandlingInst;
    }

    /**
     * Message field setter.
     * @param custOrderHandlingInst field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CustOrderHandlingInst)
    public void setCustOrderHandlingInst(String custOrderHandlingInst) {
        this.custOrderHandlingInst = custOrderHandlingInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrderHandlingInstSource)
    public Integer getOrderHandlingInstSource() {
        return orderHandlingInstSource;
    }

    /**
     * Message field setter.
     * @param orderHandlingInstSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OrderHandlingInstSource)
    public void setOrderHandlingInstSource(Integer orderHandlingInstSource) {
        this.orderHandlingInstSource = orderHandlingInstSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoTrdRegTimestamps)
    public Integer getNoTrdRegTimestamps() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TrdRegTimestampsGroup} components. It will also create an array
     * of {@link TrdRegTimestampsGroup} objects and set the <code>trdRegTimestampsGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdRegTimestampsGroups</code> array they will be discarded.<br/>
     * @param noTrdRegTimestamps number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoTrdRegTimestamps)
    public void setNoTrdRegTimestamps(Integer noTrdRegTimestamps) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdRegTimestampsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0")
    public TrdRegTimestampsGroup[] getTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdRegTimestampsGroup} object to the existing array of <code>trdRegTimestampsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field to the proper value.<br/>
     * Note: If the <code>setNoTrdRegTimestamps</code> method has been called there will already be a number of objects in the
     * <code>trdRegTimestampsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0")
    public TrdRegTimestampsGroup addTrdRegTimestampsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdRegTimestampsGroup} object from the existing array of <code>trdRegTimestampsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0")
    public TrdRegTimestampsGroup deleteTrdRegTimestampsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdRegTimestampsGroup} objects from the <code>trdRegTimestampsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0")
    public int clearTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Volatility)
    public Double getVolatility() {
        return volatility;
    }

    /**
     * Message field setter.
     * @param volatility field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.Volatility)
    public void setVolatility(Double volatility) {
        this.volatility = volatility;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TimeToExpiration)
    public Double getTimeToExpiration() {
        return timeToExpiration;
    }

    /**
     * Message field setter.
     * @param timeToExpiration field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.TimeToExpiration)
    public void setTimeToExpiration(Double timeToExpiration) {
        this.timeToExpiration = timeToExpiration;
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
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PriceDelta)
    public Double getPriceDelta() {
        return priceDelta;
    }

    /**
     * Message field setter.
     * @param priceDelta field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.PriceDelta)
    public void setPriceDelta(Double priceDelta) {
        this.priceDelta = priceDelta;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
            hasMissingTag = true;
        }
        if (execID == null || execID.trim().isEmpty()) {
            errorMsg.append(" [ExecID]");
            hasMissingTag = true;
        }
        if (execType == null) {
            errorMsg.append(" [ExecType]");
            hasMissingTag = true;
        }
        if (ordStatus == null) {
            errorMsg.append(" [OrdStatus]");
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
        if (leavesQty == null) {
            errorMsg.append(" [LeavesQty]");
            hasMissingTag = true;
        }
        if (cumQty == null) {
            errorMsg.append(" [CumQty]");
            hasMissingTag = true;
        }
        if (avgPx == null) {
            errorMsg.append(" [AvgPx]");
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
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.OrigClOrdID, origClOrdID);
            TagEncoder.encode(bao, TagNum.ClOrdLinkID, clOrdLinkID);
            TagEncoder.encode(bao, TagNum.QuoteRespID, quoteRespID);
            TagEncoder.encode(bao, TagNum.OrdStatusReqID, ordStatusReqID);
            TagEncoder.encode(bao, TagNum.MassStatusReqID, massStatusReqID);
            TagEncoder.encode(bao, TagNum.HostCrossID, hostCrossID);
            TagEncoder.encode(bao, TagNum.TotNumReports, totNumReports);
            TagEncoder.encode(bao, TagNum.LastRptRequested, lastRptRequested);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encodeDate(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            TagEncoder.encode(bao, TagNum.ClientID, clientID);
            TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            if (noContraBrokers != null && noContraBrokers.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoContraBrokers, noContraBrokers);
                if (contraBrokers != null && contraBrokers.length == noContraBrokers.intValue()) {
                    for (ContraBrokerGroup contraBroker : contraBrokers) {
                        bao.write(contraBroker.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "ContraBrokerGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoContraBrokers.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.ListID, listID);
            TagEncoder.encode(bao, TagNum.CrossID, crossID);
            TagEncoder.encode(bao, TagNum.OrigCrossID, origCrossID);
            if (crossType != null) {
                TagEncoder.encode(bao, TagNum.CrossType, crossType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TrdMatchID, trdMatchID);
            TagEncoder.encode(bao, TagNum.ExecID, execID);
            if (execTransType != null) {
                TagEncoder.encode(bao, TagNum.ExecTransType, execTransType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ExecRefID, execRefID);
            if (execType != null) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            if (ordStatus != null) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.WorkingIndicator, workingIndicator);
            if (ordRejReason != null) {
                TagEncoder.encode(bao, TagNum.OrdRejReason, ordRejReason.getValue());
            }
            if (execRestatementReason != null) {
                TagEncoder.encode(bao, TagNum.ExecRestatementReason, execRestatementReason.getValue());
            }
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
                    for (PreTradeAllocGroup allocGroup : allocGroups) {
                        bao.write(allocGroup.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "PreTradeAllocGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoAllocs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            if (matchType != null) {
                TagEncoder.encode(bao, TagNum.MatchType, matchType.getValue());
            }
            if (orderCategory != null) {
                TagEncoder.encode(bao, TagNum.OrderCategory, orderCategory.getValue());
            }
            if (cashMargin != null) {
                TagEncoder.encode(bao, TagNum.CashMargin, cashMargin.getValue());
            }
            if (clearingFeeIndicator != null) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
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
            TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            TagEncoder.encode(bao, TagNum.Issuer, issuer);
            if (encodedIssuerLen != null && encodedIssuerLen.intValue() > 0) {
                if (encodedIssuer != null && encodedIssuer.length > 0) {
                    encodedIssuerLen = new Integer(encodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedIssuerLen, encodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedIssuer, encodedIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            if (encodedSecurityDescLen != null && encodedSecurityDescLen.intValue() > 0) {
                if (encodedSecurityDesc != null && encodedSecurityDesc.length > 0) {
                    encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
                }
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (stipulations != null) {
                bao.write(stipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            if (quantityType != null) {
                TagEncoder.encode(bao, TagNum.QuantityType, quantityType.getValue());
            }
            if (qtyType != null) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            TagEncoder.encode(bao, TagNum.CashOrderQty, cashOrderQty);
            if (lotType != null) {
                TagEncoder.encode(bao, TagNum.LotType, lotType.getValue());
            }
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
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
            if (pegInstructions != null) {
                bao.write(pegInstructions.encode(MsgSecureType.ALL_FIELDS));
            }
            if (discretionInstructions != null) {
                bao.write(discretionInstructions.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.PeggedPrice, peggedPrice);
            TagEncoder.encode(bao, TagNum.PeggedRefPrice, peggedRefPrice);
            TagEncoder.encode(bao, TagNum.DiscretionPrice, discretionPrice);
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
            TagEncoder.encode(bao, TagNum.ParticipationRate, participationRate);
            TagEncoder.encode(bao, TagNum.TargetStrategyPerformance, targetStrategyPerformance);
            TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            if (discretionInst != null) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.ComplianceID, complianceID);
            TagEncoder.encode(bao, TagNum.SolicitedFlag, solicitedFlag);
            if (timeInForce != null) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            TagEncoder.encode(bao, TagNum.AggressorIndicator, aggressorIndicator);
            if (orderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            TagEncoder.encode(bao, TagNum.PreTradeAnonymity, preTradeAnonymity);
            if (custOrderCapacity != null) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            if (rule80A != null) {
                TagEncoder.encode(bao, TagNum.Rule80A, rule80A.getValue());
            }
            TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            TagEncoder.encode(bao, TagNum.CalculatedCcyLastQty, calculatedCcyLastQty);
            TagEncoder.encode(bao, TagNum.LastSwapPoints, lastSwapPoints);
            TagEncoder.encode(bao, TagNum.UnderlyingLastQty, underlyingLastQty);
            TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            TagEncoder.encode(bao, TagNum.UnderlyingLastPx, underlyingLastPx);
            TagEncoder.encode(bao, TagNum.LastParPx, lastParPx);
            TagEncoder.encode(bao, TagNum.LastSpotRate, lastSpotRate);
            TagEncoder.encode(bao, TagNum.LastForwardPoints, lastForwardPoints);
            TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encode(bao, TagNum.TimeBracket, timeBracket);
            if (lastCapacity != null) {
                TagEncoder.encode(bao, TagNum.LastCapacity, lastCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.LeavesQty, leavesQty);
            TagEncoder.encode(bao, TagNum.CumQty, cumQty);
            TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            TagEncoder.encode(bao, TagNum.DayOrderQty, dayOrderQty);
            TagEncoder.encode(bao, TagNum.DayCumQty, dayCumQty);
            TagEncoder.encode(bao, TagNum.DayAvgPx, dayAvgPx);
            TagEncoder.encode(bao, TagNum.TotNoFills, totNoFills);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noFills != null) {
                TagEncoder.encode(bao, TagNum.NoFills, noFills);
                if (fillExecGroups != null && fillExecGroups.length == noFills.intValue()) {
                    for (int i = 0; i < noFills.intValue(); i++) {
                        if (fillExecGroups[i] != null) {
                            bao.write(fillExecGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "FillExecGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoFills.getValue(), error);
                }
            }
            if (GTBookingInst != null) {
                TagEncoder.encode(bao, TagNum.GTBookingInst, GTBookingInst.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.ReportToExch, reportToExch);
            if (commissionData != null) {
                bao.write(commissionData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Commission, commission);
            if (commType != null) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            TagEncoder.encode(bao, TagNum.NumDaysInterest, numDaysInterest);
            TagEncoder.encodeTimestamp(bao, TagNum.ExDate, exDate);
            TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            TagEncoder.encode(bao, TagNum.InterestAtMaturity, interestAtMaturity);
            TagEncoder.encode(bao, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
            TagEncoder.encode(bao, TagNum.StartCash, startCash);
            TagEncoder.encode(bao, TagNum.EndCash, endCash);
            TagEncoder.encode(bao, TagNum.TradedFlatSwitch, tradedFlatSwitch);
            TagEncoder.encodeDate(bao, TagNum.BasisFeatureDate, basisFeatureDate);
            TagEncoder.encode(bao, TagNum.BasisFeaturePrice, basisFeaturePrice);
            TagEncoder.encode(bao, TagNum.Concession, concession);
            TagEncoder.encode(bao, TagNum.TotalTakedown, totalTakedown);
            TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
            TagEncoder.encode(bao, TagNum.SettlCurrAmt, settlCurrAmt);
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (noRateSources != null && noRateSources.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoRateSources, noRateSources);
                if (rateSources != null && rateSources.length == noRateSources.intValue()) {
                    for (RateSourceGroup rateSource : rateSources) {
                        bao.write(rateSource.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "RateSourceGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoRateSources.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.SettlCurrFxRate, settlCurrFxRate);
            if (settlCurrFxRateCalc != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            if (handlInst != null) {
                TagEncoder.encode(bao, TagNum.HandlInst, handlInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.MinQty, minQty);
            TagEncoder.encode(bao, TagNum.MatchIncrement, matchIncrement);
            TagEncoder.encode(bao, TagNum.MaxPriceLevels, maxPriceLevels);
            if (displayInstruction != null) {
                bao.write(displayInstruction.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.MaxFloor, maxFloor);
            if (positionEffect != null) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            TagEncoder.encode(bao, TagNum.MaxShow, maxShow);
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
            TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            TagEncoder.encode(bao, TagNum.ClearingFirm, clearingFirm);
            TagEncoder.encode(bao, TagNum.ClearingAccount, clearingAccount);
            TagEncoder.encode(bao, TagNum.LastForwardPoints2, lastForwardPoints2);
            if (multilegReportingType != null) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
            }
            if (cancellationRights != null) {
                TagEncoder.encode(bao, TagNum.CancellationRights, cancellationRights.getValue());
            }
            if (moneyLaunderingStatus != null) {
                TagEncoder.encode(bao, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.RegistID, registID);
            TagEncoder.encode(bao, TagNum.Designation, designation);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransBkdTime, transBkdTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExecValuationPoint, execValuationPoint);
            if (execPriceType != null) {
                TagEncoder.encode(bao, TagNum.ExecPriceType, execPriceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ExecPriceAdjustment, execPriceAdjustment);
            if (priorityIndicator != null) {
                TagEncoder.encode(bao, TagNum.PriorityIndicator, priorityIndicator.getValue());
            }
            TagEncoder.encode(bao, TagNum.PriceImprovement, priceImprovement);
            if (lastLiquidityInd != null) {
                TagEncoder.encode(bao, TagNum.LastLiquidityInd, lastLiquidityInd.getValue());
            }
            if (noContAmts != null) {
                TagEncoder.encode(bao, TagNum.NoContAmts, noContAmts);
                if (contAmtGroups != null && contAmtGroups.length == noContAmts.intValue()) {
                    for (int i = 0; i < noContAmts.intValue(); i++) {
                        if (contAmtGroups[i] != null) {
                            bao.write(contAmtGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "ContAmtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoContAmts.getValue(), error);
                }
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrmtLegExecGroups != null && instrmtLegExecGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrmtLegExecGroups[i] != null) {
                            bao.write(instrmtLegExecGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrmtLegExecGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.CopyMsgIndicator, copyMsgIndicator);
            if (noMiscFees != null) {
                TagEncoder.encode(bao, TagNum.NoMiscFees, noMiscFees);
                if (miscFeeGroups != null && miscFeeGroups.length == noMiscFees.intValue()) {
                    for (int i = 0; i < noMiscFees.intValue(); i++) {
                        if (miscFeeGroups[i] != null) {
                            bao.write(miscFeeGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MiscFeeGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMiscFees.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.DividendYield, dividendYield);
            TagEncoder.encode(bao, TagNum.ManualOrderIndicator, manualOrderIndicator);
            TagEncoder.encode(bao, TagNum.CustDirectedOrder, custDirectedOrder);
            TagEncoder.encode(bao, TagNum.ReceivedDeptID, receivedDeptID);
            TagEncoder.encode(bao, TagNum.CustOrderHandlingInst, custOrderHandlingInst);
            TagEncoder.encode(bao, TagNum.OrderHandlingInstSource, orderHandlingInstSource);
            if (noTrdRegTimestamps != null) {
                TagEncoder.encode(bao, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
                if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length == noTrdRegTimestamps.intValue()) {
                    for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                        if (trdRegTimestampsGroups[i] != null) {
                            bao.write(trdRegTimestampsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdRegTimestampsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTrdRegTimestamps.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Volatility, volatility);
            TagEncoder.encode(bao, TagNum.TimeToExpiration, timeToExpiration);
            TagEncoder.encode(bao, TagNum.RiskFreeRate, riskFreeRate);
            TagEncoder.encode(bao, TagNum.PriceDelta, priceDelta);
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

           case SecondaryOrderID:
                secondaryOrderID = new String(tag.value, sessionCharset);
                break;

           case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

           case SecondaryExecID:
                secondaryExecID = new String(tag.value, sessionCharset);
                break;

            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case OrigClOrdID:
                origClOrdID = new String(tag.value, sessionCharset);
                break;

            case ClOrdLinkID:
                clOrdLinkID = new String(tag.value, sessionCharset);
                break;

            case QuoteRespID:
                quoteRespID = new String(tag.value, sessionCharset);
                break;

            case OrdStatusReqID:
                ordStatusReqID = new String(tag.value, sessionCharset);
                break;

            case MassStatusReqID:
                massStatusReqID = new String(tag.value, sessionCharset);
                break;

            case HostCrossID:
                hostCrossID = new String(tag.value, sessionCharset);
                break;

            case TotNumReports:
                totNumReports = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastRptRequested:
                lastRptRequested = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case TradeOriginationDate:
                tradeOriginationDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ClientID:
                clientID = new String(tag.value, sessionCharset);
                break;

            case ExecBroker:
                execBroker = new String(tag.value, sessionCharset);
                break;

            case NoContraBrokers:
                noContraBrokers = new Integer(new String(tag.value, sessionCharset));
                break;

            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;

            case CrossID:
                crossID = new String(tag.value, sessionCharset);
                break;

            case OrigCrossID:
                origCrossID = new String(tag.value, sessionCharset);
                break;

            case CrossType:
                crossType = CrossType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TrdMatchID:
                trdMatchID = new String(tag.value, sessionCharset);
                break;

            case ExecID:
                execID = new String(tag.value, sessionCharset);
                break;

            case ExecTransType:
                execTransType = ExecTransType.valueFor(new String(tag.value, sessionCharset));
                break;

            case ExecRefID:
                execRefID = new String(tag.value, sessionCharset);
                break;

            case ExecType:
                execType = ExecType.valueFor(new String(tag.value, sessionCharset));
                break;

            case OrdStatus:
                ordStatus = OrdStatus.valueFor(new String(tag.value, sessionCharset));
                break;

            case WorkingIndicator:
                workingIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case OrdRejReason:
                ordRejReason = OrdRejReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ExecRestatementReason:
                execRestatementReason = ExecRestatementReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
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

            case MatchType:
                matchType = MatchType.valueFor(new String(tag.value, sessionCharset));
                break;

            case OrderCategory:
                orderCategory = OrderCategory.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case CashMargin:
                cashMargin = CashMargin.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ClearingFeeIndicator:
                clearingFeeIndicator = ClearingFeeIndicator.valueFor(new String(tag.value, sessionCharset));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
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

            case ContractMultiplier:
                contractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case CouponRate:
                couponRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SecurityExchange:
                securityExchange = new String(tag.value, sessionCharset);
                break;

            case Issuer:
                issuer = new String(tag.value, sessionCharset);
                break;

            case EncodedIssuerLen:
                encodedIssuerLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case SecurityDesc:
                securityDesc = new String(tag.value, sessionCharset);
                break;

            case EncodedSecurityDescLen:
                encodedSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case QuantityType:
                quantityType = QuantityType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QtyType:
                qtyType = QtyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrderQty:
                orderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CashOrderQty:
                cashOrderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LotType:
                lotType = LotType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
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

            case PeggedPrice:
                peggedPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case PeggedRefPrice:
                peggedRefPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case DiscretionPrice:
                discretionPrice = new Double(new String(tag.value, sessionCharset));
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

            case TargetStrategyPerformance:
                targetStrategyPerformance = new Double(new String(tag.value, sessionCharset));
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

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case ComplianceID:
                complianceID = new String(tag.value, sessionCharset);
                break;

            case SolicitedFlag:
                solicitedFlag = BooleanConverter.parse(new String(tag.value, sessionCharset));
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

            case ExecInst:
                execInst = new String(tag.value, sessionCharset);
                break;

            case AggressorIndicator:
                aggressorIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
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

            case Rule80A:
                rule80A = Rule80A.valueFor(new String(tag.value, sessionCharset));
                break;

            case LastQty:
                lastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CalculatedCcyLastQty:
                calculatedCcyLastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LastSwapPoints:
                lastSwapPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingLastQty:
                underlyingLastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LastPx:
                lastPx = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingLastPx:
                underlyingLastPx = new Double(new String(tag.value, sessionCharset));
                break;

            case LastParPx:
                lastParPx = new Double(new String(tag.value, sessionCharset));
                break;

            case LastSpotRate:
                lastSpotRate = new Double(new String(tag.value, sessionCharset));
                break;

            case LastForwardPoints:
                lastForwardPoints = new Double(new String(tag.value, sessionCharset));
                break;

            case LastMkt:
                lastMkt = new String(tag.value, sessionCharset);
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

            case LastCapacity:
                lastCapacity = LastCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case LeavesQty:
                leavesQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CumQty:
                cumQty = new Double(new String(tag.value, sessionCharset));
                break;

            case AvgPx:
                avgPx = new Double(new String(tag.value, sessionCharset));
                break;

            case DayOrderQty:
                dayOrderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case DayCumQty:
                dayCumQty = new Double(new String(tag.value, sessionCharset));
                break;

            case DayAvgPx:
                dayAvgPx = new Double(new String(tag.value, sessionCharset));
                break;

            case TotNoFills:
                totNoFills = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoFills:
                noFills = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case GTBookingInst:
                GTBookingInst = GTBookingInst.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ReportToExch:
                reportToExch = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case Commission:
                commission = new Double(new String(tag.value, sessionCharset));
                break;

            case CommType:
                commType = CommType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case GrossTradeAmt:
                grossTradeAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case NumDaysInterest:
                numDaysInterest = new Integer(new String(tag.value, sessionCharset));
                break;

            case ExDate:
                exDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AccruedInterestRate:
                accruedInterestRate = new Double(new String(tag.value, sessionCharset));
                break;

            case AccruedInterestAmt:
                accruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case InterestAtMaturity:
                interestAtMaturity = new Double(new String(tag.value, sessionCharset));
                break;

            case EndAccruedInterestAmt:
                endAccruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case StartCash:
                startCash = new Double(new String(tag.value, sessionCharset));
                break;

            case EndCash:
                endCash = new Double(new String(tag.value, sessionCharset));
                break;

            case TradedFlatSwitch:
                tradedFlatSwitch = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case BasisFeatureDate:
                basisFeatureDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case BasisFeaturePrice:
                basisFeaturePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case Concession:
                concession = new Double(new String(tag.value, sessionCharset));
                break;

            case TotalTakedown:
                totalTakedown = new Double(new String(tag.value, sessionCharset));
                break;

            case NetMoney:
                netMoney = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrAmt:
                settlCurrAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case NoRateSources:
                noRateSources = new Integer(new String(tag.value, sessionCharset));
                break;

            case SettlCurrFxRate:
                settlCurrFxRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrFxRateCalc:
                settlCurrFxRateCalc = SettlCurrFxRateCalc.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case HandlInst:
                handlInst = HandlInst.valueFor(new String(tag.value, sessionCharset));
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

            case PositionEffect:
                positionEffect = PositionEffect.valueFor(new String(tag.value, getSessionCharset()));
                break;

            case MaxShow:
                maxShow = new Double(new String(tag.value, sessionCharset));
                break;

            case BookingType:
                bookingType = BookingType.valueFor(Integer.valueOf(new String(tag.value, getSessionCharset())));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case SettlDate2:
                settlDate2 = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrderQty2:
                orderQty2 = new Double(new String(tag.value, sessionCharset));
                break;

            case ClearingFirm:
                clearingFirm = new String(tag.value, sessionCharset);
                break;

            case ClearingAccount:
                clearingAccount = new String(tag.value, sessionCharset);
                break;

            case LastForwardPoints2:
                lastForwardPoints2 = new Double(new String(tag.value, sessionCharset));
                break;

            case MultiLegReportingType:
                multilegReportingType = MultiLegReportingType.valueFor(new String(tag.value, sessionCharset));
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

            case TransBkdTime:
                transBkdTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExecValuationPoint:
                execValuationPoint = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExecPriceType:
                execPriceType = ExecPriceType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ExecPriceAdjustment:
                execPriceAdjustment = new Double(new String(tag.value, sessionCharset));
                break;

            case PriorityIndicator:
                priorityIndicator = PriorityIndicator.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PriceImprovement:
                priceImprovement = new Double(new String(tag.value, sessionCharset));
                break;

            case LastLiquidityInd:
                lastLiquidityInd = LastLiquidityInd.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoContAmts:
                noContAmts = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
                break;

            case CopyMsgIndicator:
                copyMsgIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoMiscFees:
                noMiscFees = new Integer(new String(tag.value, sessionCharset));
                break;

            case DividendYield:
                dividendYield = new Double(new String(tag.value, sessionCharset));
                break;

            case ManualOrderIndicator:
                manualOrderIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case CustDirectedOrder:
                custDirectedOrder = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case ReceivedDeptID:
                receivedDeptID = new String(tag.value, sessionCharset);
                break;

            case CustOrderHandlingInst:
                custOrderHandlingInst = new String(tag.value, sessionCharset);
                break;

            case OrderHandlingInstSource:
                orderHandlingInstSource = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoTrdRegTimestamps:
                noTrdRegTimestamps = new Integer(new String(tag.value, sessionCharset));
                break;

            case Volatility:
                volatility = new Double(new String(tag.value, sessionCharset));
                break;

            case TimeToExpiration:
                timeToExpiration = new Double(new String(tag.value, sessionCharset));
                break;

            case RiskFreeRate:
                riskFreeRate = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceDelta:
                priceDelta = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ExecutionReportMsg] fields.";
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

    private OrderQtyData getSafeOrderQtyData() {
        if (getOrderQtyData() == null) {
            setOrderQtyData();
        }

        return getOrderQtyData();
    }

    private CommissionData getSafeCommissionData() {
        if (getCommissionData() == null) {
            setCommissionData();
        }

        return getCommissionData();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{ExecutionReportMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.SecondaryOrderID, secondaryOrderID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.SecondaryExecID, secondaryExecID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.OrigClOrdID, origClOrdID);
        printTagValue(b, TagNum.ClOrdLinkID, clOrdLinkID);
        printTagValue(b, TagNum.QuoteRespID, quoteRespID);
        printTagValue(b, TagNum.OrdStatusReqID, ordStatusReqID);
        printTagValue(b, TagNum.MassStatusReqID, massStatusReqID);
        printTagValue(b, TagNum.HostCrossID, hostCrossID);
        printTagValue(b, TagNum.TotNumReports, totNumReports);
        printTagValue(b, TagNum.LastRptRequested, lastRptRequested);
        printTagValue(b, parties);
        printDateTagValue(b, TagNum.TradeOriginationDate, tradeOriginationDate);
        printTagValue(b, TagNum.ClientID, clientID);
        printTagValue(b, TagNum.ExecBroker, execBroker);
        printTagValue(b, TagNum.NoContraBrokers, noContraBrokers);
        printTagValue(b, contraBrokers);
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, TagNum.CrossID, crossID);
        printTagValue(b, TagNum.OrigCrossID, origCrossID);
        printTagValue(b, TagNum.CrossType, crossType);
        printTagValue(b, TagNum.TrdMatchID, trdMatchID);
        printTagValue(b, TagNum.ExecID, execID);
        printTagValue(b, TagNum.ExecTransType, execID);
        printTagValue(b, TagNum.ExecRefID, execRefID);
        printTagValue(b, TagNum.ExecType, execType);
        printTagValue(b, TagNum.OrdStatus, ordStatus);
        printTagValue(b, TagNum.WorkingIndicator, workingIndicator);
        printTagValue(b, TagNum.OrdRejReason, ordRejReason);
        printTagValue(b, TagNum.ExecRestatementReason, execRestatementReason);
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
        printTagValue(b, TagNum.MatchType, matchType);
        printTagValue(b, TagNum.OrderCategory, orderCategory);
        printTagValue(b, TagNum.CashMargin, cashMargin);
        printTagValue(b, TagNum.ClearingFeeIndicator, clearingFeeIndicator);
        printTagValue(b, instrument);
        printTagValue(b, financingDetails);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.Symbol, symbol);
        printTagValue(b, TagNum.SymbolSfx, symbolSfx);
        printTagValue(b, TagNum.SecurityID, securityID);
        printTagValue(b, TagNum.SecurityIDSource, securityIDSource);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.MaturityMonthYear, maturityMonthYear);
        printTagValue(b, TagNum.MaturityDay, maturityDay);
        printTagValue(b, TagNum.PutOrCall, putOrCall);
        printTagValue(b, TagNum.StrikePrice, strikePrice);
        printTagValue(b, TagNum.ContractMultiplier, contractMultiplier);
        printTagValue(b, TagNum.CouponRate, couponRate);
        printTagValue(b, TagNum.OptAttribute, optAttribute);
        printTagValue(b, TagNum.SecurityExchange, securityExchange);
        printTagValue(b, TagNum.Issuer, issuer);
        printTagValue(b, TagNum.EncodedIssuerLen, encodedIssuerLen);
        printTagValue(b, TagNum.EncodedIssuer, encodedIssuer);
        printTagValue(b, TagNum.SecurityDesc, securityDesc);
        printTagValue(b, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, stipulations);
        printTagValue(b, TagNum.QuantityType, quantityType);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, orderQtyData);
        printTagValue(b, TagNum.OrderQty, orderQty);
        printTagValue(b, TagNum.CashOrderQty, cashOrderQty);
        printTagValue(b, TagNum.LotType, lotType);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.PriceProtectionScope, priceProtectionScope);
        printTagValue(b, TagNum.StopPx, stopPx);
        printTagValue(b, triggeringInstruction);
        printTagValue(b, pegInstructions);
        printTagValue(b, discretionInstructions);
        printTagValue(b, TagNum.PeggedPrice, peggedPrice);
        printTagValue(b, TagNum.PeggedRefPrice, peggedRefPrice);
        printTagValue(b, TagNum.DiscretionPrice, discretionPrice);
        printTagValue(b, TagNum.TargetStrategy, targetStrategy);
        printTagValue(b, TagNum.NoStrategyParameters, noStrategyParameters);
        printTagValue(b, strategyParametersGroups);
        printTagValue(b, TagNum.TargetStrategyParameters, targetStrategyParameters);
        printTagValue(b, TagNum.ParticipationRate, participationRate);
        printTagValue(b, TagNum.TargetStrategyPerformance, targetStrategyPerformance);
        printTagValue(b, TagNum.PegOffsetValue, pegOffsetValue);
        printTagValue(b, TagNum.DiscretionInst, discretionInst);
        printTagValue(b, TagNum.DiscretionOffsetValue, discretionOffsetValue);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.ComplianceID, complianceID);
        printTagValue(b, TagNum.SolicitedFlag, solicitedFlag);
        printTagValue(b, TagNum.TimeInForce, timeInForce);
        printUTCDateTimeTagValue(b, TagNum.EffectiveTime, effectiveTime);
        printDateTagValue(b, TagNum.ExpireDate, expireDate);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printTagValue(b, TagNum.ExecInst, execInst);
        printTagValue(b, TagNum.AggressorIndicator, aggressorIndicator);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.OrderRestrictions, orderRestrictions);
        printTagValue(b, TagNum.PreTradeAnonymity, preTradeAnonymity);
        printTagValue(b, TagNum.CustOrderCapacity, custOrderCapacity);
        printTagValue(b, TagNum.Rule80A, rule80A);
        printTagValue(b, TagNum.LastQty, lastQty);
        printTagValue(b, TagNum.CalculatedCcyLastQty, calculatedCcyLastQty);
        printTagValue(b, TagNum.LastSwapPoints, lastSwapPoints);
        printTagValue(b, TagNum.UnderlyingLastQty, underlyingLastQty);
        printTagValue(b, TagNum.LastPx, lastPx);
        printTagValue(b, TagNum.UnderlyingLastPx, underlyingLastPx);
        printTagValue(b, TagNum.LastParPx, lastParPx);
        printTagValue(b, TagNum.LastSpotRate, lastSpotRate);
        printTagValue(b, TagNum.LastForwardPoints, lastForwardPoints);
        printTagValue(b, TagNum.LastMkt, lastMkt);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.TimeBracket, timeBracket);
        printTagValue(b, TagNum.LastCapacity, lastCapacity);
        printTagValue(b, TagNum.LeavesQty, leavesQty);
        printTagValue(b, TagNum.CumQty, cumQty);
        printTagValue(b, TagNum.AvgPx, avgPx);
        printTagValue(b, TagNum.DayOrderQty, dayOrderQty);
        printTagValue(b, TagNum.DayCumQty, dayCumQty);
        printTagValue(b, TagNum.DayAvgPx, dayAvgPx);
        printTagValue(b, TagNum.TotNoFills, totNoFills);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoFills, noFills);
        printTagValue(b, fillExecGroups);
        printTagValue(b, TagNum.GTBookingInst, GTBookingInst);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.ReportToExch, reportToExch);
        printTagValue(b, commissionData);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, yieldData);
        printTagValue(b, TagNum.Commission, commission);
        printTagValue(b, TagNum.CommType, commType);
        printTagValue(b, TagNum.GrossTradeAmt, grossTradeAmt);
        printTagValue(b, TagNum.NumDaysInterest, numDaysInterest);
        printDateTagValue(b, TagNum.ExDate, exDate);
        printTagValue(b, TagNum.AccruedInterestRate, accruedInterestRate);
        printTagValue(b, TagNum.AccruedInterestAmt, accruedInterestAmt);
        printTagValue(b, TagNum.InterestAtMaturity, interestAtMaturity);
        printTagValue(b, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
        printTagValue(b, TagNum.StartCash, startCash);
        printTagValue(b, TagNum.EndCash, endCash);
        printTagValue(b, TagNum.TradedFlatSwitch, tradedFlatSwitch);
        printDateTagValue(b, TagNum.BasisFeatureDate, basisFeatureDate);
        printTagValue(b, TagNum.BasisFeaturePrice, basisFeaturePrice);
        printTagValue(b, TagNum.Concession, concession);
        printTagValue(b, TagNum.TotalTakedown, totalTakedown);
        printTagValue(b, TagNum.NetMoney, netMoney);
        printTagValue(b, TagNum.SettlCurrAmt, settlCurrAmt);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.NoRateSources, noRateSources);
        printTagValue(b, rateSources);
        printTagValue(b, TagNum.SettlCurrFxRate, settlCurrFxRate);
        printTagValue(b, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc);
        printTagValue(b, TagNum.HandlInst, handlInst);
        printTagValue(b, TagNum.MinQty, minQty);
        printTagValue(b, TagNum.MatchIncrement, matchIncrement);
        printTagValue(b, TagNum.MaxPriceLevels, maxPriceLevels);
        printTagValue(b, displayInstruction);
        printTagValue(b, TagNum.MaxFloor, maxFloor);
        printTagValue(b, TagNum.PositionEffect, positionEffect);
        printTagValue(b, TagNum.MaxShow, maxShow);
        printTagValue(b, TagNum.BookingType, bookingType);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printDateTagValue(b, TagNum.SettlDate2, settlDate2);
        printTagValue(b, TagNum.OrderQty2, orderQty2);
        printTagValue(b, TagNum.ClearingFirm, clearingFirm);
        printTagValue(b, TagNum.ClearingAccount, clearingAccount);
        printTagValue(b, TagNum.LastForwardPoints2, lastForwardPoints2);
        printTagValue(b, TagNum.MultiLegReportingType, multilegReportingType);
        printTagValue(b, TagNum.CancellationRights, cancellationRights);
        printTagValue(b, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus);
        printTagValue(b, TagNum.RegistID, registID);
        printTagValue(b, TagNum.Designation, designation);
        printUTCDateTimeTagValue(b, TagNum.TransBkdTime, transBkdTime);
        printUTCDateTimeTagValue(b, TagNum.ExecValuationPoint, execValuationPoint);
        printTagValue(b, TagNum.ExecPriceType, execPriceType);
        printTagValue(b, TagNum.ExecPriceAdjustment, execPriceAdjustment);
        printTagValue(b, TagNum.PriorityIndicator, priorityIndicator);
        printTagValue(b, TagNum.PriceImprovement, priceImprovement);
        printTagValue(b, TagNum.LastLiquidityInd, lastLiquidityInd);
        printTagValue(b, TagNum.NoContAmts, noContAmts);
        printTagValue(b, contAmtGroups);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrmtLegExecGroups);
        printTagValue(b, TagNum.CopyMsgIndicator, copyMsgIndicator);
        printTagValue(b, TagNum.NoMiscFees, noMiscFees);
        printTagValue(b, miscFeeGroups);
        printTagValue(b, TagNum.DividendYield, dividendYield);
        printTagValue(b, TagNum.ManualOrderIndicator, manualOrderIndicator);
        printTagValue(b, TagNum.CustDirectedOrder, custDirectedOrder);
        printTagValue(b, TagNum.ReceivedDeptID, receivedDeptID);
        printTagValue(b, TagNum.CustOrderHandlingInst, custOrderHandlingInst);
        printTagValue(b, TagNum.OrderHandlingInstSource, orderHandlingInstSource);
        printTagValue(b, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
        printTagValue(b, trdRegTimestampsGroups);
        printTagValue(b, TagNum.Volatility, volatility);
        printTagValue(b, TagNum.TimeToExpiration, timeToExpiration);
        printTagValue(b, TagNum.RiskFreeRate, riskFreeRate);
        printTagValue(b, TagNum.PriceDelta, priceDelta);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
