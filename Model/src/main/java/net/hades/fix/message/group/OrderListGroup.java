/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderListGroup.java
 *
 * $Id: OrderListGroup.java,v 1.2 2011-02-02 10:03:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
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
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.comp.DisplayInstruction;
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
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.CustomerOrFirm;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.RefOrderIDSource;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.SideValueInd;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.util.TagEncoder;

/**
 * Order list group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class OrderListGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ClOrdID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.ListSeqNo.getValue(),
        TagNum.ClOrdLinkID.getValue(),
        TagNum.SettlInstMode.getValue(),
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
        TagNum.NoUnderlyings.getValue(),
        TagNum.PrevClosePx.getValue(),
        TagNum.Side.getValue(),
        TagNum.SideValueInd.getValue(),
        TagNum.LocateReqd.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceProtectionScope.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.IOIID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.RefOrderID.getValue(),
        TagNum.RefOrderIDSource.getValue(),
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
        TagNum.SettlDate2.getValue(),
        TagNum.OrderQty2.getValue(),
        TagNum.Price2.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.CoveredOrUncovered.getValue(),
        TagNum.MaxShow.getValue(),
        TagNum.TargetStrategy.getValue(),
        TagNum.NoStrategyParameters.getValue(),
        TagNum.TargetStrategyParameters.getValue(),
        TagNum.ParticipationRate.getValue(),
        TagNum.Designation.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 11 REQUIRED. Starting with 4.2 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 526. Starting with 4.3 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 67. Starting with 4.2 version.
     */
    protected Integer listSeqNo;

    /**
     * TagNum = 583. Starting with 5.0 version.
     */
    protected String clOrdLinkID;

    /**
     * TagNum = 583. Starting with 4.2 version.
     */
    protected SettlInstMode settlInstMode;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * Starting with 4.3 version.
     */
    protected Date tradeOriginationDate;

    /**
     * TagNum = 75. Starting with 4.4 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 109. Starting with 4.2 version.
     */
    protected String clientID;

    /**
     * TagNum = 76. Starting with 4.2 version.
     */
    protected String execBroker;

    /**
     * TagNum = 1. Starting with 4.2 version.
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
     * TagNum = 70. Starting with 4.4 version.
     */
    protected String allocID;

    /**
     * TagNum = 591. Starting with 4.3 version.
     */
    protected PreallocMethod preallocMethod;

    /**
     * TagNum = 78. Starting with 4.2 version.
     */
    protected Integer noAllocs;

    /**
     * Starting with 4.2 version.
     */
    protected PreTradeAllocGroup[] allocGroups;

    /**
     * TagNum = 63. Starting with 4.2 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.2 version.
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
     * TagNum = 21. Starting with 4.2 version.
     */
    protected HandlInst handlInst;

    /**
     * TagNum = 18. Starting with 4.2 version.
     */
    protected String execInst;

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
     * TagNum = 100. Starting with 4.2 version.
     */
    protected String exDestination;

    /**
     * TagNum = 1133. Starting with 5.0 version.
     */
    protected ExDestinationIDSource exDestinationIDSource;

    /**
     * TagNum = 386. Starting with 4.2 version.
     */
    protected Integer noTradingSessions;

    /**
     * Starting with 4.2 version.
     */
    protected TradingSessionGroup[] tradingSessionGroups;

    /**
     * TagNum = 81. Starting with 4.2 version.
     */
    protected ProcessCode processCode;

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
     * TagNum = 55. Starting with 4.2 version.
     */
    protected String symbol;

    /**
     * TagNum = 65. Starting with 4.2 version.
     */
    protected String symbolSfx;

    /**
     * TagNum = 48. Starting with 4.2 version.
     */
    protected String securityID;

    /**
     * TagNum = 22. Starting with 4.2 version.
     */
    protected String securityIDSource;

    /**
     * TagNum = 167. Starting with 4.2 version.
     */
    protected String securityType;

    /**
     * TagNum = 200. Starting with 4.2 version.
     */
    protected String maturityMonthYear;

    /**
     * TagNum = 205. Starting with 4.2 version.
     */
    protected Integer maturityDay;

    /**
     * TagNum = 201. Starting with 4.2 version.
     */
    protected PutOrCall putOrCall;

    /**
     * TagNum = 202. Starting with 4.2 version.
     */
    protected Double strikePrice;

    /**
     * TagNum = 206. Starting with 4.2 version.
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
     * TagNum = 207. Starting with 4.2 version.
     */
    protected String securityExchange;

    /**
     * TagNum = 106. Starting with 4.2 version.
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
     * TagNum = 107. Starting with 4.2 version.
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
     * TagNum = 140. Starting with 4.2 version.
     */
    protected Double prevClosePx;

    /**
     * TagNum = 54 REQUIRED. Starting with 4.2 version.
     */
    protected Side side;

    /**
     * TagNum = 401. Starting with 4.2 version.
     */
    protected SideValueInd sideValueInd;

    /**
     * TagNum = 114. Starting with 4.2 version.
     */
    protected Boolean locateReqd;

    /**
     * TagNum = 60. Starting with 4.2 version.
     */
    protected Date transactTime;

    /**
     * Starting with 4.3 version.
     */
    protected Stipulations stipulations;

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
     * TagNum = 38. Starting with 4.2 version.
     */
    protected Double orderQty;

    /**
     * TagNum = 152. Starting with 4.2 version.
     */
    protected Double cashOrderQty;

    /**
     * TagNum = 40. Starting with 4.2 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 423. Starting with 4.3 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 44. Starting with 4.2 version.
     */
    protected Double price;

    /**
     * TagNum = 1092. Starting with 5.0 version.
     */
    protected PriceProtectionScope priceProtectionScope;

    /**
     * TagNum = 99. Starting with 4.2 version.
     */
    protected Double stopPx;

    /**
     * Starting with 5.0 version.
     */
    protected TriggeringInstruction triggeringInstruction;

    /**
     * Starting with 4.3 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * Starting with 4.3 version.
     */
    protected YieldData yieldData;

    /**
     * TagNum = 15. Starting with 4.2 version.
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
     * TagNum = 23. Starting with 4.2 version.
     */
    protected String IOIID;

    /**
     * TagNum = 117. Starting with 4.2 version.
     */
    protected String quoteID;

    /**
     * TagNum = 1080. Starting with 5.0 version.
     */
    protected String refOrderID;

    /**
     * TagNum = 1081. Starting with 5.0 version.
     */
    protected RefOrderIDSource refOrderIDSource;

    /**
     * TagNum = 59. Starting with 4.2 version.
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
     * TagNum = 126. Starting with 4.2 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 427. Starting with 4.2 version.
     */
    protected GTBookingInst GTBookingInst;

    /**
     * Starting with 4.3 version.
     */
    protected CommissionData commissionData;

    /**
     * TagNum = 12. Starting with 4.2 version.
     */
    protected Double commission;

    /**
     * TagNum = 13. Starting with 4.2 version.
     */
    protected CommType commType;

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
     * TagNum = 47. Starting with 4.2 version.
     */
    protected Rule80A rule80A;

    /**
     * TagNum = 121. Starting with 4.2 version.
     */
    protected Boolean forexReq;

    /**
     * TagNum = 120. Starting with 4.2 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 775. Starting with 4.4 version.
     */
    protected BookingType bookingType;

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
     * TagNum = 193. Starting with 4.2 version.
     */
    protected Date settlDate2;

    /**
     * TagNum = 192. Starting with 4.2 version.
     */
    protected Double orderQty2;

    /**
     * TagNum = 640. Starting with 4.3 version.
     */
    protected Double price2;

    /**
     * TagNum = 77. Starting with 4.2 version.
     */
    protected PositionEffect positionEffect;

    /**
     * TagNum = 203. Starting with 4.2 version.
     */
    protected CoveredOrUncovered coveredOrUncovered;

    /**
     * TagNum = 204. Starting with 4.2 version.
     */
    protected CustomerOrFirm customerOrFirm;

    /**
     * TagNum = 210. Starting with 4.4 version.
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
     * TagNum = 211. Starting with 4.2 version.
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
     * TagNum = 494. Starting with 4.3 version.
     */
    protected String designation;

    /**
     * TagNum = 158. Starting with 4.3 version.
     */
    protected Double accruedInterestRate;

    /**
     * TagNum = 158. Starting with 4.3 version.
     */
    protected Double accruedInterestAmt;

    /**
     * TagNum = 118. Starting with 4.3 version.
     */
    protected Double netMoney;

    /**
     * TagNum = 439. Starting with 4.2 version.
     */
    protected String clearingFirm;

    /**
     * TagNum = 440. Starting with 4.2 version.
     */
    protected String clearingAccount;
 
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OrderListGroup() {
    }
    
    public OrderListGroup(FragmentContext context) {
        super(context);
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListSeqNo)
    public Integer getListSeqNo() {
        return listSeqNo;
    }

    /**
     * Message field setter.
     * @param listSeqNo field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListSeqNo)
    public void setListSeqNo(Integer listSeqNo) {
        this.listSeqNo = listSeqNo;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClOrdLinkID)
    public String getClOrdLinkID() {
        return clOrdLinkID;
    }

    /**
     * Message field setter.
     * @param clOrdLinkID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ClOrdLinkID)
    public void setClOrdLinkID(String clOrdLinkID) {
        this.clOrdLinkID = clOrdLinkID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlInstMode)
    public SettlInstMode getSettlInstMode() {
        return settlInstMode;
    }

    /**
     * Message field setter.
     * @param settlInstMode field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlInstMode)
    public void setSettlInstMode(SettlInstMode settlInstMode) {
        this.settlInstMode = settlInstMode;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public String getClientID() {
        return clientID;
    }

    /**
     * Message field setter.
     * @param clientID field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public String getExecBroker() {
        return execBroker;
    }

    /**
     * Message field setter.
     * @param execBroker field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public void setExecBroker(String execBroker) {
        this.execBroker = execBroker;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.2")
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
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public void setNoAllocs(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PreTradeAllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    public PreTradeAllocGroup deleteAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PreTradeAllocGroup} objects from the <code>allocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.2")
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
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        return execInst;
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        this.execInst = execInst;
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
    @TagNumRef(tagNum=TagNum.ExDestination)
    public String getExDestination() {
        return exDestination;
    }

    /**
     * Message field setter.
     * @param exDestination field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoTradingSessions)
    public void setNoTradingSessions(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TradingSessionGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    public TradingSessionGroup deleteTradingSessionGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TradingSessionGroup} objects from the <code>tradingSessionGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTradingSessions</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearTradingSessionGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public ProcessCode getProcessCode() {
        return processCode;
    }

     /**
     * Message field setter.
     * @param processCode field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public void setProcessCode(ProcessCode processCode) {
        this.processCode = processCode;
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
     * Sets the Instrument component to null.
     */
    @FIXVersion(introduced = "4.3")
    public void clearInstrument() {
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
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required=true)
    public String getSymbol() {
        return getSafeInstrument().getSymbol();
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required=true)
    public void setSymbol(String symbol) {
        getSafeInstrument().setSymbol(symbol);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public String getSymbolSfx() {
        return getSafeInstrument().getSymbolSfx();
    }

    /**
     * Message field setter.
     * @param symbolSfx field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public void setSymbolSfx(String symbolSfx) {
        getSafeInstrument().setSymbolSfx(symbolSfx);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public String getSecurityID() {
        return getSafeInstrument().getSecurityID();
    }

    /**
     * Message field setter.
     * @param securityID field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public void setSecurityID(String securityID) {
        getSafeInstrument().setSecurityID(securityID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public String getSecurityIDSource() {
        return getSafeInstrument().getSecurityIDSource();
    }

    /**
     * Message field setter.
     * @param securityIDSource field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public void setSecurityIDSource(String securityIDSource) {
        getSafeInstrument().setSecurityIDSource(securityIDSource);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return getSafeInstrument().getSecurityType();
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        getSafeInstrument().setSecurityType(securityType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public String getMaturityMonthYear() {
        return getSafeInstrument().getMaturityMonthYear();
    }

    /**
     * Message field setter.
     * @param maturityMonthYear field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public void setMaturityMonthYear(String maturityMonthYear) {
        getSafeInstrument().setMaturityMonthYear(maturityMonthYear);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public Integer getMaturityDay() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maturityDay field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public void setMaturityDay(Integer maturityDay) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public PutOrCall getPutOrCall() {
        return getSafeInstrument().getPutOrCall();
    }

    /**
     * Message field setter.
     * @param putOrCall field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public void setPutOrCall(PutOrCall putOrCall) {
        getSafeInstrument().setPutOrCall(putOrCall);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public Double getStrikePrice() {
        return getSafeInstrument().getStrikePrice();
    }

    /**
     * Message field setter.
     * @param strikePrice field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
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
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public Character getOptAttribute() {
        return getSafeInstrument().getOptAttribute();
    }

    /**
     * Message field setter.
     * @param optAttribute field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public void setOptAttribute(Character optAttribute) {
        getSafeInstrument().setOptAttribute(optAttribute);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public String getSecurityExchange() {
        return getSafeInstrument().getSecurityExchange();
    }

    /**
     * Message field setter.
     * @param securityExchange field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public void setSecurityExchange(String securityExchange) {
        getSafeInstrument().setSecurityExchange(securityExchange);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public String getIssuer() {
        return getSafeInstrument().getIssuer();
    }

    /**
     * Message field setter.
     * @param issuer field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
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
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public String getSecurityDesc() {
        return getSafeInstrument().getSecurityDesc();
    }

    /**
     * Message field setter.
     * @param securityDesc field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
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
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public Double getPrevClosePx() {
        return prevClosePx;
    }

    /**
     * Message field setter.
     * @param prevClosePx field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Side, required=true)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Side, required=true)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SideValueInd)
    public SideValueInd getSideValueInd() {
        return sideValueInd;
    }

    /**
     * Message field setter.
     * @param sideValueInd field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.SideValueInd)
    public void setSideValueInd(SideValueInd sideValueInd) {
        this.sideValueInd = sideValueInd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public Boolean getLocateReqd() {
        return locateReqd;
    }

    /**
     * Message field setter.
     * @param locateReqd field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public void setLocateReqd(Boolean locateReqd) {
        this.locateReqd = locateReqd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.OrderQty)
    public Double getOrderQty() {
        return getSafeOrderQtyData().getOrderQty();
    }

    /**
     * Message field setter.
     * @param orderQty field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OrdType)
    public OrdType getOrdType() {
        return ordType;
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.StopPx)
    public Double getStopPx() {
        return stopPx;
    }

    /**
     * Message field setter.
     * @param stopPx field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.IOIID)
    public String getIOIID() {
        return IOIID;
    }

    /**
     * Message field setter.
     * @param IOIID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.IOIID)
    public void setIOIID(String IOIID) {
        this.IOIID = IOIID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public String getQuoteID() {
        return quoteID;
    }

    /**
     * Message field setter.
     * @param quoteID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RefOrderID)
    public String getRefOrderID() {
        return refOrderID;
    }

    /**
     * Message field setter.
     * @param refOrderID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RefOrderID)
    public void setRefOrderID(String refOrderID) {
        this.refOrderID = refOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RefOrderIDSource)
    public RefOrderIDSource getRefOrderIDSource() {
        return refOrderIDSource;
    }

    /**
     * Message field setter.
     * @param refOrderIDSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RefOrderIDSource)
    public void setRefOrderIDSource(RefOrderIDSource refOrderIDSource) {
        this.refOrderIDSource = refOrderIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    /**
     * Message field setter.
     * @param timeInForce field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public Double getCommission() {
        return getSafeCommissionData().getCommission();
    }

    /**
     * Message field setter.
     * @param commission field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public void setCommission(Double commission) {
        getSafeCommissionData().setCommission(commission);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public CommType getCommType() {
        return getSafeCommissionData().getCommType();
    }

    /**
     * Message field setter.
     * @param commType field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public void setCommType(CommType commType) {
        getSafeCommissionData().setCommType(commType);
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
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.Rule80A)
    public Rule80A getRule80A() {
        return rule80A;
    }

    /**
     * Message field setter.
     * @param rule80A field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.Rule80A)
    public void setRule80A(Rule80A rule80A) {
        this.rule80A = rule80A;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public Boolean getForexReq() {
        return forexReq;
    }

    /**
     * Message field setter.
     * @param forexReq field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public void setForexReq(Boolean forexReq) {
        this.forexReq = forexReq;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="4.2")
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price2)
    public Double getPrice2() {
        return price2;
    }

    /**
     * Message field setter.
     * @param price2 field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price2)
    public void setPrice2(Double price2) {
        this.price2 = price2;
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
    @TagNumRef(tagNum=TagNum.CoveredOrUncovered)
    public CoveredOrUncovered getCoveredOrUncovered() {
        return coveredOrUncovered;
    }

    /**
     * Message field setter.
     * @param coveredOrUncovered field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.CoveredOrUncovered)
    public void setCoveredOrUncovered(CoveredOrUncovered coveredOrUncovered) {
        this.coveredOrUncovered = coveredOrUncovered;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CustomerOrFirm)
    public CustomerOrFirm getCustomerOrFirm() {
        return customerOrFirm;
    }

    /**
     * Message field setter.
     * @param customerOrFirm field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CustomerOrFirm)
    public void setCustomerOrFirm(CustomerOrFirm customerOrFirm) {
        this.customerOrFirm = customerOrFirm;
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
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    /**
     * Message field setter.
     * @param pegOffsetValue field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
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
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public Double getAccruedInterestRate() {
        return accruedInterestRate;
    }

    /**
     * Message field setter.
     * @param accruedInterestRate field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public void setAccruedInterestRate(Double accruedInterestRate) {
        this.accruedInterestRate = accruedInterestRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param accruedInterestAmt field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.ClOrdID.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (clOrdID == null || clOrdID.trim().isEmpty()) {
            errorMsg.append(" [ClOrdID]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null) {
            errorMsg.append(" [Instrument]");
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
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.ListSeqNo, listSeqNo);
            TagEncoder.encode(bao, TagNum.ClOrdLinkID, clOrdLinkID);
            if (settlInstMode != null) {
                TagEncoder.encode(bao, TagNum.SettlInstMode, settlInstMode.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encodeDate(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encode(bao, TagNum.ClientID, clientID);
            TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
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
            TagEncoder.encode(bao, TagNum.AllocID, allocID);
            if (preallocMethod != null) {
                TagEncoder.encode(bao, TagNum.PreallocMethod, preallocMethod.getValue());
            }
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoTradingSessions.getValue(), error);
                }
            }
            if (processCode != null) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyings.getValue(), error);
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
            TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (sideValueInd != null) {
                TagEncoder.encode(bao, TagNum.SideValueInd, sideValueInd.getValue());
            }
            TagEncoder.encode(bao, TagNum.LocateReqd, locateReqd);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
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
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.ComplianceID, complianceID);
            TagEncoder.encode(bao, TagNum.SolicitedFlag, solicitedFlag);
            TagEncoder.encode(bao, TagNum.IOIID, IOIID);
            TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            TagEncoder.encode(bao, TagNum.RefOrderID, refOrderID);
            if (refOrderIDSource != null) {
                TagEncoder.encode(bao, TagNum.RefOrderIDSource, refOrderIDSource.getValue());
            }
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
            TagEncoder.encode(bao, TagNum.Commission, commission);
            if (commType != null) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
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
            TagEncoder.encodeDate(bao, TagNum.SettlDate2, settlDate2);
            TagEncoder.encode(bao, TagNum.OrderQty2, orderQty2);
            TagEncoder.encode(bao, TagNum.Price2, price2);
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoStrategyParameters.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.TargetStrategyParameters, targetStrategyParameters);
            TagEncoder.encode(bao, TagNum.ParticipationRate, participationRate);
            TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            if (discretionInst != null) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
            TagEncoder.encode(bao, TagNum.Designation, designation);
            TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
            TagEncoder.encode(bao, TagNum.ClearingFirm, clearingFirm);
            TagEncoder.encode(bao, TagNum.ClearingAccount, clearingAccount);
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
            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

            case ListSeqNo:
                listSeqNo = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case ClOrdLinkID:
                clOrdLinkID = new String(tag.value, sessionCharset);
                break;

            case SettlInstMode:
                settlInstMode = SettlInstMode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case TradeOriginationDate:
                tradeOriginationDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
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

            case AllocID:
                allocID = new String(tag.value, sessionCharset);
                break;

            case PreallocMethod:
                preallocMethod = PreallocMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
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

            case PrevClosePx:
                prevClosePx = new Double(new String(tag.value, sessionCharset));
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case SideValueInd:
                sideValueInd = SideValueInd.valueFor((Integer.valueOf(new String(tag.value, sessionCharset))));
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

            case OrderQty:
                orderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CashOrderQty:
                cashOrderQty = new Double(new String(tag.value, sessionCharset));
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

            case RefOrderID:
                refOrderID = new String(tag.value, sessionCharset);
                break;

            case RefOrderIDSource:
                refOrderIDSource = RefOrderIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
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

            case Commission:
                commission = new Double(new String(tag.value, sessionCharset));
                break;

            case CommType:
                commType = CommType.valueFor(new String(tag.value, sessionCharset).charAt(0));
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

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case SettlDate2:
                settlDate2 = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrderQty2:
                orderQty2 = new Double(new String(tag.value, sessionCharset));
                break;

            case Price2:
                price2 = new Double(new String(tag.value, sessionCharset));
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

            case PegOffsetValue:
                pegOffsetValue = new Double(new String(tag.value, sessionCharset));
                break;

            case DiscretionInst:
                discretionInst = DiscretionInst.valueFor(new String(tag.value, sessionCharset));
                break;

            case DiscretionOffsetValue:
                discretionOffsetValue = new Double(new String(tag.value, sessionCharset));
                break;

            case Designation:
                designation = new String(tag.value, sessionCharset);
                break;

            case AccruedInterestRate:
                accruedInterestRate = new Double(new String(tag.value, sessionCharset));
                break;

            case AccruedInterestAmt:
                accruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case NetMoney:
                netMoney = new Double(new String(tag.value, sessionCharset));
                break;

            case ClearingFirm:
                clearingFirm = new String(tag.value, sessionCharset);
                break;

            case ClearingAccount:
                clearingAccount = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [OrderListGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }


        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    /// </editor-fold>
    
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
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{OrderListGroup=");
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.ListSeqNo, listSeqNo);
        printTagValue(b, TagNum.ClOrdLinkID, clOrdLinkID);
        printTagValue(b, TagNum.SettlInstMode, settlInstMode);
        printTagValue(b, parties);
        printDateTagValue(b, TagNum.TradeOriginationDate, tradeOriginationDate);
        printTagValue(b, TagNum.ClientID, clientID);
        printTagValue(b, TagNum.ExecBroker, execBroker);
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
        printTagValue(b, instrument);
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
        printTagValue(b, TagNum.PrevClosePx, prevClosePx);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.LocateReqd, locateReqd);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, stipulations);
        printTagValue(b, TagNum.QuantityType, quantityType);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, orderQtyData);
        printTagValue(b, TagNum.OrderQty, orderQty);
        printTagValue(b, TagNum.CashOrderQty, cashOrderQty);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.PriceProtectionScope, priceProtectionScope);
        printTagValue(b, TagNum.StopPx, stopPx);
        printTagValue(b, triggeringInstruction);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, yieldData);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.ComplianceID, complianceID);
        printTagValue(b, TagNum.SolicitedFlag, solicitedFlag);
        printTagValue(b, TagNum.IOIID, IOIID);
        printTagValue(b, TagNum.QuoteID, quoteID);
        printTagValue(b, TagNum.RefOrderID, refOrderID);
        printTagValue(b, TagNum.RefOrderIDSource, refOrderIDSource);
        printTagValue(b, TagNum.TimeInForce, timeInForce);
        printUTCDateTimeTagValue(b, TagNum.EffectiveTime, effectiveTime);
        printDateTagValue(b, TagNum.ExpireDate, expireDate);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printTagValue(b, TagNum.GTBookingInst, GTBookingInst);
        printTagValue(b, commissionData);
        printTagValue(b, TagNum.Commission, commission);
        printTagValue(b, TagNum.CommType, commType);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.OrderRestrictions, orderRestrictions);
        printTagValue(b, TagNum.PreTradeAnonymity, preTradeAnonymity);
        printTagValue(b, TagNum.CustOrderCapacity, custOrderCapacity);
        printTagValue(b, TagNum.Rule80A, rule80A);
        printTagValue(b, TagNum.ForexReq, forexReq);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.BookingType, bookingType);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printDateTagValue(b, TagNum.SettlDate2, settlDate2);
        printTagValue(b, TagNum.OrderQty2, orderQty2);
        printTagValue(b, TagNum.Price2, price2);
        printTagValue(b, TagNum.PositionEffect, positionEffect);
        printTagValue(b, TagNum.CoveredOrUncovered, coveredOrUncovered);
        printTagValue(b, TagNum.CustomerOrFirm, customerOrFirm);
        printTagValue(b, TagNum.MaxShow, maxShow);
        printTagValue(b, pegInstructions);
        printTagValue(b, discretionInstructions);
        printTagValue(b, TagNum.TargetStrategy, targetStrategy);
        printTagValue(b, TagNum.NoStrategyParameters, noStrategyParameters);
        printTagValue(b, strategyParametersGroups);
        printTagValue(b, TagNum.TargetStrategyParameters, targetStrategyParameters);
        printTagValue(b, TagNum.ParticipationRate, participationRate);
        printTagValue(b, TagNum.PegOffsetValue, pegOffsetValue);
        printTagValue(b, TagNum.DiscretionInst, discretionInst);
        printTagValue(b, TagNum.DiscretionOffsetValue, discretionOffsetValue);
        printTagValue(b, TagNum.Designation, designation);
        printTagValue(b, TagNum.AccruedInterestRate, accruedInterestRate);
        printTagValue(b, TagNum.AccruedInterestAmt, accruedInterestAmt);
        printTagValue(b, TagNum.NetMoney, netMoney);
        printTagValue(b, TagNum.ClearingFirm, clearingFirm);
        printTagValue(b, TagNum.ClearingAccount, clearingAccount);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
