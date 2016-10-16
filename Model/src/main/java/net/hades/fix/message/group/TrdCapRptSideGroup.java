/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdCapRptSideGroup.java
 *
 * $Id: TrdCapRptSideGroup.java,v 1.2 2011-10-25 08:29:20 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.Stipulations;
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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.TradeReportOrderDetail;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.FillLiquidityInd;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderCategory;
import net.hades.fix.message.type.OrderDelayUnit;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradeAllocIndicator;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Trade capture report sides group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TrdCapRptSideGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Side.getValue(),
        TagNum.SideExecID.getValue(),
        TagNum.OrderDelay.getValue(),
        TagNum.OrderDelayUnit.getValue(),
        TagNum.SideLastQty.getValue(),
        TagNum.SideTradeReportID.getValue(),
        TagNum.SideFillStationCd.getValue(),
        TagNum.SideReasonCd.getValue(),
        TagNum.RptSeq.getValue(),
        TagNum.SideTrdSubTyp.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.ProcessCode.getValue(),
        TagNum.OddLot.getValue(),
        TagNum.NoClearingInstructions.getValue(),
        TagNum.TradeInputSource.getValue(),
        TagNum.TradeInputDevice.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.TimeBracket.getValue(),
        TagNum.NumDaysInterest.getValue(),
        TagNum.ExDate.getValue(),
        TagNum.AccruedInterestRate.getValue(),
        TagNum.AccruedInterestAmt.getValue(),
        TagNum.InterestAtMaturity.getValue(),
        TagNum.EndAccruedInterestAmt.getValue(),
        TagNum.StartCash.getValue(),
        TagNum.EndCash.getValue(),
        TagNum.Concession.getValue(),
        TagNum.TotalTakedown.getValue(),
        TagNum.NetMoney.getValue(),
        TagNum.SettlCurrAmt.getValue(),
        TagNum.SettlCurrFxRate.getValue(),
        TagNum.SettlCurrFxRateCalc.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.Text.getValue(),
        TagNum.SideMultiLegReportingType.getValue(),
        TagNum.NoContAmts.getValue(),
        TagNum.NoMiscFees.getValue(),
        TagNum.ExchangeRule.getValue(),
        TagNum.TradeAllocIndicator.getValue(),
        TagNum.PreallocMethod.getValue(),
        TagNum.AllocID.getValue(),
        TagNum.NoAllocs.getValue(),
        TagNum.NoSideTrdRegTS.getValue(),
        TagNum.NoSettlDetails.getValue(),
        TagNum.SideGrossTradeAmt.getValue(),
        TagNum.AggressorIndicator.getValue(),
        TagNum.ExchangeSpecialInstructions.getValue(),
        TagNum.OrderCategory.getValue(),
        TagNum.SideLiquidityInd.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 54 REQUIRED. Starting with 4.3 version.
     */
    protected Side side;

    /**
     * TagNum = 1427. Starting with 5.0SP2 version.
     */
    protected String sideExecID;

    /**
     * TagNum = 1428. Starting with 5.0SP2 version.
     */
    protected Integer orderDelay;

    /**
     * TagNum = 1429. Starting with 5.0SP2 version.
     */
    protected OrderDelayUnit orderDelayUnit;

    /**
     * TagNum = 37. Starting with 4.3 version.
     */
    protected String orderID;

    /**
     * TagNum = 198. Starting with 4.3 version.
     */
    protected String secondaryOrderID;
            
    /**
     * TagNum = 11. Starting with 4.3 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 19. Starting with 5.0 version.
     */
    protected String execRefID;

    /**
     * TagNum = 526. Starting with 4.4 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 66. Starting with 5.0 version.
     */
    protected String listID;

    /**
     * TagNum = 1009. Starting with 5.0 version.
     */
    protected Integer sideLastQty;

    /**
     * TagNum = 1005. Starting with 5.0 version.
     */
    protected String sideTradeReportID;

    /**
     * TagNum = 1006. Starting with 5.0 version.
     */
    protected String sideFillStationCd;

    /**
     * TagNum = 1007. Starting with 5.0 version.
     */
    protected String sideReasonCd;

    /**
     * TagNum = 83. Starting with 5.0 version.
     */
    protected Integer rptSeq;

    /**
     * TagNum = 1008. Starting with 5.0 version.
     */
    protected TrdSubType sideTrdSubTyp;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

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
     * TagNum = 81. Starting with 4.3 version.
     */
    protected ProcessCode processCode;

    /**
     * TagNum = 1093. Starting with 5.0 version.
     */
    protected LotType lotType;

    /**
     * TagNum = 575. Starting with 4.3 version.
     */
    protected Boolean oddLot;

    /**
     * TagNum = 576. Starting with 4.3 version.
     */
    protected Integer noClearingInstructions;

    /**
     * Starting with 4.3 version.
     */
    protected ClrInstGroup[] clrInstGroups;

    /**
     * TagNum = 635. Starting with 4.3 version.
     */
    protected ClearingFeeIndicator clearingFeeIndicator;

    /**
     * TagNum = 578. Starting with 4.3 version.
     */
    protected String tradeInputSource;

    /**
     * TagNum = 579. Starting with 4.3 version.
     */
    protected String tradeInputDevice;

    /**
     * TagNum = 821. Starting with 4.4 version.
     */
    protected String orderInputDevice;

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
     * TagNum = 528. Starting with 4.3 version.
     */
    protected OrderCapacity orderCapacity;

    /**
     * TagNum = 529. Starting with 4.3 version.
     */
    protected String orderRestrictions;

    /**
     * TagNum = 582. Starting with 4.3 version.
     */
    protected CustOrderCapacity custOrderCapacity;

    /**
     * TagNum = 40. Starting with 4.4 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 18. Starting with 4.4 version.
     */
    protected String execInst;

    /**
     * TagNum = 483. Starting with 4.3 version.
     */
    protected Date transBkdTime;

    /**
     * TagNum = 336. Starting with 4.3 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 943. Starting with 4.4 version.
     */
    protected String timeBracket;

    /**
     * Starting with 4.3 version.
     */
    protected CommissionData commissionData;

    /**
     * TagNum = 381. Starting with 4.3 version.
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
     * TagNum = 238. Starting with 4.3 version.
     */
    protected Double concession;

    /**
     * TagNum = 237. Starting with 4.3 version.
     */
    protected Double totalTakedown;

    /**
     * TagNum = 118. Starting with 4.3 version.
     */
    protected Double netMoney;

    /**
     * TagNum = 119. Starting with 4.3 version.
     */
    protected Double settlCurrAmt;

    /**
     * TagNum = 120. Starting with 4.3 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 155. Starting with 4.3 version.
     */
    protected Double settlCurrFxRate;

    /**
     * TagNum = 156. Starting with 4.3 version.
     */
    protected SettlCurrFxRateCalc settlCurrFxRateCalc;

    /**
     * TagNum = 77. Starting with 4.3 version.
     */
    protected PositionEffect positionEffect;

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
     * TagNum = 752. Starting with 4.4 version.
     */
    protected MultiLegReportingType sideMultiLegReportingType;

    /**
     * TagNum = 442. Starting with 4.3 version.
     */
    protected MultiLegReportingType multilegReportingType;

    /**
     * TagNum = 518. Starting with 4.3 version.
     */
    protected Integer noContAmts;

    /**
     * Starting with 4.3 version.
     */
    protected ContAmtGroup[] contAmtGroups;

    /**
     * Starting with 4.4 version.
     */
    protected Stipulations stipulations;

    /**
     * TagNum = 555. Starting with 4.3 version.
     */
    protected Integer noMiscFees;

    /**
     * Starting with 4.3 version.
     */
    protected MiscFeeGroup[] miscFeeGroups;

    /**
     * TagNum = 825. Starting with 4.4 version.
     */
    protected String exchangeRule;

    /**
     * TagNum = 826. Starting with 4.4 version.
     */
    protected TradeAllocIndicator tradeAllocIndicator;

    /**
     * TagNum = 591. Starting with 4.3 4.
     */
    protected PreallocMethod preallocMethod;

    /**
     * TagNum = 70. Starting with 4.4 version.
     */
    protected String allocID;

    /**
     * TagNum = 78. Starting with 4.4 version.
     */
    protected Integer noAllocs;

    /**
     * Starting with 4.2 version.
     */
    protected TradeAllocGroup[] allocGroups;

    /**
     * TagNum = 1016. Starting with 4.4 version.
     */
    protected Integer noSideTrdRegTS;

    /**
     * Starting with 4.4 version.
     */
    protected SideTrdRegTimestampsGroup[] sideTrdRegTimestampsGroups;

    /**
     * TagNum = 1158. Starting with 5.0SP1 version.
     */
    protected Integer noSettlDetails;

    /**
     * Starting with 5.0SP1 version.
     */
    protected SettlDetailsGroup[] settlDetailsGroups;

    /**
     * TagNum = 1072. Starting with 4.4 version.
     */
    protected Double sideGrossTradeAmt;

    /**
     * TagNum = 1057. Starting with 4.4 version.
     */
    protected Boolean aggressorIndicator;

    /**
     * TagNum = 1139. Starting with 4.4 version.
     */
    protected String exchangeSpecialInstructions;

    /**
     * TagNum = 1115. Starting with 5.0SP2 version.
     */
    protected OrderCategory orderCategory;

    /**
     * TagNum = 1444. Starting with 5.0SP2 version.
     */
    protected FillLiquidityInd sideLiquidityInd;
 
    /**
     * Starting with 5.0SP2 version.
     */
    protected TradeReportOrderDetail tradeReportOrderDetail;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TrdCapRptSideGroup() {
    }
    
    public TrdCapRptSideGroup(FragmentContext context) {
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
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SideExecID)
    public String getSideExecID() {
        return sideExecID;
    }

    /**
     * Message field setter.
     * @param sideExecID sideExecID value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SideExecID)
    public void setSideExecID(String sideExecID) {
        this.sideExecID = sideExecID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderDelay)
    public Integer getOrderDelay() {
        return orderDelay;
    }

    /**
     * Message field setter.
     * @param orderDelay sideExecID value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderDelay)
    public void setOrderDelay(Integer orderDelay) {
        this.orderDelay = orderDelay;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderDelayUnit)
    public OrderDelayUnit getOrderDelayUnit() {
        return orderDelayUnit;
    }

    /**
     * Message field setter.
     * @param orderDelayUnit sideExecID value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderDelayUnit)
    public void setOrderDelayUnit(OrderDelayUnit orderDelayUnit) {
        this.orderDelayUnit = orderDelayUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return getSafeTradeReportOrderDetail().getOrderID();
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        getSafeTradeReportOrderDetail().setOrderID(orderID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public String getSecondaryOrderID() {
        return getSafeTradeReportOrderDetail().getSecondaryOrderID();
    }

    /**
     * Message field setter.
     * @param secondaryOrderID field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public void setSecondaryOrderID(String secondaryOrderID) {
        getSafeTradeReportOrderDetail().setSecondaryOrderID(secondaryOrderID);
    }
            
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return getSafeTradeReportOrderDetail().getClOrdID();
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        getSafeTradeReportOrderDetail().setClOrdID(clOrdID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExecRefID)
    public String getExecRefID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param execRefID field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExecRefID)
    public void setExecRefID(String execRefID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public String getSecondaryClOrdID() {
        return getSafeTradeReportOrderDetail().getSecondaryClOrdID();
    }

    /**
     * Message field setter.
     * @param secondaryClOrdID field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        getSafeTradeReportOrderDetail().setSecondaryClOrdID(secondaryClOrdID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ListID)
    public String getListID() {
        return getSafeTradeReportOrderDetail().getListID();
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ListID)
    public void setListID(String listID) {
        getSafeTradeReportOrderDetail().setListID(listID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideLastQty)
    public Integer getSideLastQty() {
        return sideLastQty;
    }

    /**
     * Message field setter.
     * @param sideLastQty field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideLastQty)
    public void setSideLastQty(Integer sideLastQty) {
        this.sideLastQty = sideLastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideTradeReportID)
    public String getSideTradeReportID() {
        return sideTradeReportID;
    }

    /**
     * Message field setter.
     * @param sideTradeReportID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideTradeReportID)
    public void setSideTradeReportID(String sideTradeReportID) {
        this.sideTradeReportID = sideTradeReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideFillStationCd)
    public String getSideFillStationCd() {
        return sideFillStationCd;
    }

    /**
     * Message field setter.
     * @param sideFillStationCd field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideFillStationCd)
    public void setSideFillStationCd(String sideFillStationCd) {
        this.sideFillStationCd = sideFillStationCd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideReasonCd)
    public String getSideReasonCd() {
        return sideReasonCd;
    }

    /**
     * Message field setter.
     * @param sideReasonCd field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideReasonCd)
    public void setSideReasonCd(String sideReasonCd) {
        this.sideReasonCd = sideReasonCd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RptSeq)
    public Integer getRptSeq() {
        return rptSeq;
    }

    /**
     * Message field setter.
     * @param rptSeq field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.RptSeq)
    public void setRptSeq(Integer rptSeq) {
        this.rptSeq = rptSeq;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideTrdSubTyp)
    public TrdSubType getSideTrdSubTyp() {
        return sideTrdSubTyp;
    }

    /**
     * Message field setter.
     * @param sideTrdSubTyp field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SideTrdSubTyp)
    public void setSideTrdSubTyp(TrdSubType sideTrdSubTyp) {
        this.sideTrdSubTyp = sideTrdSubTyp;
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
     * Sets the Parties component if used in this message to the proper implementation class.
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
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.3")
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
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.LotType)
    public LotType getLotType() {
        return getSafeTradeReportOrderDetail().getLotType();
    }

    /**
     * Message field setter.
     * @param lotType field value
     */
    @FIXVersion(introduced="5.0", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.LotType)
    public void setLotType(LotType lotType) {
        getSafeTradeReportOrderDetail().setLotType(lotType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OddLot)
    public Boolean getOddLot() {
        return oddLot;
    }

    /**
     * Message field setter.
     * @param oddLot field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.OddLot)
    public void setOddLot(Boolean oddLot) {
        this.oddLot = oddLot;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoClearingInstructions)
    public Integer getNoClearingInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link ClrInstGroup} groups. It will also create an array
     * of {@link ClrInstGroup} objects and set the <code>clrInstGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>clrInstGroups</code> array they will be discarded.<br/>
     * @param noClearingInstructions field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoClearingInstructions)
    public void setNoClearingInstructions(Integer noClearingInstructions) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link ClrInstGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public ClrInstGroup[] getClrInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ClrInstGroup} object to the existing array of <code>clrInstGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noClearingInstructions</code> field to the proper value.<br/>
     * Note: If the <code>setNoClearingInstructions</code> method has been called there will already be a number of objects in the
     * <code>clrInstGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public ClrInstGroup addClrInstGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link ClrInstGroup} object from the existing array of <code>clrInstGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noClearingInstructions</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public ClrInstGroup deleteClrInstGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link ClrInstGroup} objects from the <code>clrInstGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noClearingInstructions</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearClrInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public ClearingFeeIndicator getClearingFeeIndicator() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param clearingFeeIndicator field value
     */
    @FIXVersion(introduced="4.3", retired="5.0")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderInputDevice)
    public String getOrderInputDevice() {
        return getSafeTradeReportOrderDetail().getOrderInputDevice();
    }

    /**
     * Message field setter.
     * @param orderInputDevice field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderInputDevice)
    public void setOrderInputDevice(String orderInputDevice) {
        getSafeTradeReportOrderDetail().setOrderInputDevice(orderInputDevice);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public OrderCapacity getOrderCapacity() {
        return getSafeTradeReportOrderDetail().getOrderCapacity();
    }

    /**
     * Message field setter.
     * @param orderCapacity field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCapacity)
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        getSafeTradeReportOrderDetail().setOrderCapacity(orderCapacity);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public String getOrderRestrictions() {
        return getSafeTradeReportOrderDetail().getOrderRestrictions();
    }

    /**
     * Message field setter.
     * @param orderRestrictions field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderRestrictions)
    public void setOrderRestrictions(String orderRestrictions) {
        getSafeTradeReportOrderDetail().setOrderRestrictions(orderRestrictions);
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
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdType)
    public OrdType getOrdType() {
        return getSafeTradeReportOrderDetail().getOrdType();
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        getSafeTradeReportOrderDetail().setOrdType(ordType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        return getSafeTradeReportOrderDetail().getExecInst();
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="4.4", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        getSafeTradeReportOrderDetail().setExecInst(execInst);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public Date getTransBkdTime() {
        return getSafeTradeReportOrderDetail().getTransBkdTime();
    }

    /**
     * Message field setter.
     * @param transBkdTime field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public void setTransBkdTime(Date transBkdTime) {
        getSafeTradeReportOrderDetail().setTransBkdTime(transBkdTime);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.3")
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
    @FIXVersion(introduced="4.3", retired="5.0")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public Double getGrossTradeAmt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param grossTradeAmt field value
     */
    @FIXVersion(introduced="4.3", retired="5.0")
    @TagNumRef(tagNum=TagNum.GrossTradeAmt)
    public void setGrossTradeAmt(Double grossTradeAmt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public Double getNetMoney() {
        return netMoney;
    }

    /**
     * Message field setter.
     * @param netMoney field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrAmt)
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    /**
     * Message field setter.
     * @param settlCurrAmt field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrAmt)
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="4.3", retired="5.0SP1")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRate)
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    /**
     * Message field setter.
     * @param settlCurrFxRate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRate)
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    /**
     * Message field setter.
     * @param settlCurrFxRateCalc field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SideMultiLegReportingType)
    public MultiLegReportingType getSideMultiLegReportingType() {
        return sideMultiLegReportingType;
    }

    /**
     * Message field setter.
     * @param sideMultiLegReportingType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SideMultiLegReportingType)
    public void setSideMultiLegReportingType(MultiLegReportingType sideMultiLegReportingType) {
        this.sideMultiLegReportingType = sideMultiLegReportingType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public MultiLegReportingType getMultilegReportingType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param multilegReportingType field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.MultiLegReportingType)
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @FIXVersion(introduced="4.4")
    public Stipulations getStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoMiscFees)
    public void setNoMiscFees(Integer noMiscFees) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MiscFeeGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
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
    @FIXVersion(introduced="4.3")
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
    @FIXVersion(introduced="4.3")
    public MiscFeeGroup deleteMiscFeeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MiscFeeGroup} objects from the <code>miscFeeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMiscFees</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearMiscFeeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExchangeRule)
    public String getExchangeRule() {
        return exchangeRule;
    }

    /**
     * Message field setter.
     * @param exchangeRule field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExchangeRule)
    public void setExchangeRule(String exchangeRule) {
        this.exchangeRule = exchangeRule;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeAllocIndicator)
    public TradeAllocIndicator getTradeAllocIndicator() {
        return tradeAllocIndicator;
    }

    /**
     * Message field setter.
     * @param tradeAllocIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradeAllocIndicator)
    public void setTradeAllocIndicator(TradeAllocIndicator tradeAllocIndicator) {
        this.tradeAllocIndicator = tradeAllocIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PreallocMethod)
    public PreallocMethod getPreallocMethod() {
        return preallocMethod;
    }

    /**
     * Message field setter.
     * @param preallocMethod field value
     */
    @FIXVersion(introduced = "4.4")
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public Integer getNoAllocs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TradeAllocGroup} groups. It will also create an array
     * of {@link TradeAllocGroup} objects and set the <code>allocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>allocGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoAllocs)
    public void setNoAllocs(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TradeAllocGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public TradeAllocGroup[] getAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TradeAllocGroup} object to the existing array of <code>allocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * Note: If the <code>setNoAllocs</code> method has been called there will already be a number of objects in the
     * <code>allocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public TradeAllocGroup addAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TradeAllocGroup} object from the existing array of <code>allocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAllocs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public TradeAllocGroup deleteAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TradeAllocGroup} objects from the <code>allocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoSideTrdRegTS)
    public Integer getNoSideTrdRegTS() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SideTrdRegTimestampsGroup} groups. It will also create an array
     * of {@link SideTrdRegTimestampsGroup} objects and set the <code>sideTrdRegTimestampsGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>sideTrdRegTimestampsGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoSideTrdRegTS)
    public void setNoSideTrdRegTS(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SideTrdRegTimestampsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public SideTrdRegTimestampsGroup[] getSideTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link SideTrdRegTimestampsGroup} object to the existing array of <code>sideTrdRegTimestampsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSideTrdRegTS</code> field to the proper value.<br/>
     * Note: If the <code>setNoSideTrdRegTS</code> method has been called there will already be a number of objects in the
     * <code>sideTrdRegTimestampsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public SideTrdRegTimestampsGroup addSideTrdRegTimestampsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SideTrdRegTimestampsGroup} object from the existing array of <code>sideTrdRegTimestampsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSideTrdRegTS</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public SideTrdRegTimestampsGroup deleteSideTrdRegTimestampsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SideTrdRegTimestampsGroup} objects from the <code>sideTrdRegTimestampsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSideTrdRegTS</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearSideTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoSettlDetails)
    public Integer getNoSettlDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SettlDetailsGroup} groups. It will also create an array
     * of {@link SettlDetailsGroup} objects and set the <code>settlDetailsGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>settlDetailsGroups</code> array they will be discarded.<br/>
     * @param noSettlDetails field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.NoSettlDetails)
    public void setNoSettlDetails(Integer noSettlDetails) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SettlDetailsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP2")
    public SettlDetailsGroup[] getSettlDetailsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link SettlDetailsGroup} object to the existing array of <code>settlDetailsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSideTrdRegTS</code> field to the proper value.<br/>
     * Note: If the <code>setNoSettlDetails</code> method has been called there will already be a number of objects in the
     * <code>settlDetailsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP2")
    public SettlDetailsGroup addSettlDetailsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SettlDetailsGroup} object from the existing array of <code>settlDetailsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSettlDetails</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP2")
    public SettlDetailsGroup deleteSettlDetailsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SettlDetailsGroup} objects from the <code>settlDetailsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSettlDetails</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP2")
    public int clearSettlDetailsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SideGrossTradeAmt)
    public Double getSideGrossTradeAmt() {
        return sideGrossTradeAmt;
    }

    /**
     * Message field setter.
     * @param sideGrossTradeAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SideGrossTradeAmt)
    public void setSideGrossTradeAmt(Double sideGrossTradeAmt) {
        this.sideGrossTradeAmt = sideGrossTradeAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AggressorIndicator)
    public Boolean getAggressorIndicator() {
        return aggressorIndicator;
    }
    
    /**
     * Message field setter.
     * @param aggressorIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AggressorIndicator)
    public void setAggressorIndicator(Boolean aggressorIndicator) {
        this.aggressorIndicator = aggressorIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExchangeSpecialInstructions)
    public String getExchangeSpecialInstructions() {
        return exchangeSpecialInstructions;
    }

    /**
     * Message field setter.
     * @param exchangeSpecialInstructions field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExchangeSpecialInstructions)
    public void setExchangeSpecialInstructions(String exchangeSpecialInstructions) {
        this.exchangeSpecialInstructions = exchangeSpecialInstructions;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCategory)
    public OrderCategory getOrderCategory() {
        return orderCategory;
    }

    /**
     * Message field setter.
     * @param orderCategory field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.OrderCategory)
    public void setOrderCategory(OrderCategory orderCategory) {
        this.orderCategory = orderCategory;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SideLiquidityInd)
    public FillLiquidityInd getSideLiquidityInd() {
        return sideLiquidityInd;
    }

    /**
     * Message field setter.
     * @param sideLiquidityInd field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.SideLiquidityInd)
    public void setSideLiquidityInd(FillLiquidityInd sideLiquidityInd) {
        this.sideLiquidityInd = sideLiquidityInd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    public TradeReportOrderDetail getTradeReportOrderDetail() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Sets the Parties component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced="5.0SP2")
    public void setTradeReportOrderDetail() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="5.0SP2")
    public void clearTradeReportOrderDetail() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.Side.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
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
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            TagEncoder.encode(bao, TagNum.SideExecID, sideExecID);
            TagEncoder.encode(bao, TagNum.OrderDelay, orderDelay);
            if (orderDelayUnit != null) {
                TagEncoder.encode(bao, TagNum.OrderDelayUnit, orderDelayUnit.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.ExecRefID, execRefID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.ListID, listID);
            TagEncoder.encode(bao, TagNum.SideLastQty, sideLastQty);
            TagEncoder.encode(bao, TagNum.SideTradeReportID, sideTradeReportID);
            TagEncoder.encode(bao, TagNum.SideFillStationCd, sideFillStationCd);
            TagEncoder.encode(bao, TagNum.SideReasonCd, sideReasonCd);
            TagEncoder.encode(bao, TagNum.RptSeq, rptSeq);
            if (sideTrdSubTyp != null) {
                TagEncoder.encode(bao, TagNum.SideTrdSubTyp, sideTrdSubTyp.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (acctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (processCode != null) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            if (lotType != null) {
                TagEncoder.encode(bao, TagNum.LotType, lotType.getValue());
            }
            TagEncoder.encode(bao, TagNum.OddLot, oddLot);
            if (noClearingInstructions != null) {
                TagEncoder.encode(bao, TagNum.NoClearingInstructions, noClearingInstructions);
                if (clrInstGroups != null && clrInstGroups.length == noClearingInstructions.intValue()) {
                    for (int i = 0; i < noClearingInstructions.intValue(); i++) {
                        if (clrInstGroups[i] != null) {
                            bao.write(clrInstGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "ClrInstGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoClearingInstructions.getValue(), error);
                }
            }
            if (clearingFeeIndicator != null) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeInputSource, tradeInputSource);
            TagEncoder.encode(bao, TagNum.TradeInputDevice, tradeInputDevice);
            TagEncoder.encode(bao, TagNum.OrderInputDevice, orderInputDevice);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.ComplianceID, complianceID);
            TagEncoder.encode(bao, TagNum.SolicitedFlag, solicitedFlag);
            if (orderCapacity != null) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            if (custOrderCapacity != null) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransBkdTime, transBkdTime);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encode(bao, TagNum.TimeBracket, timeBracket);
            if (commissionData != null) {
                bao.write(commissionData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            TagEncoder.encode(bao, TagNum.NumDaysInterest, numDaysInterest);
            TagEncoder.encodeDate(bao, TagNum.ExDate, exDate);
            TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            TagEncoder.encode(bao, TagNum.InterestAtMaturity, interestAtMaturity);
            TagEncoder.encode(bao, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
            TagEncoder.encode(bao, TagNum.StartCash, startCash);
            TagEncoder.encode(bao, TagNum.EndCash, endCash);
            TagEncoder.encode(bao, TagNum.Concession, concession);
            TagEncoder.encode(bao, TagNum.TotalTakedown, totalTakedown);
            TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
            TagEncoder.encode(bao, TagNum.SettlCurrAmt, settlCurrAmt);
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.SettlCurrFxRate, settlCurrFxRate);
            if (settlCurrFxRateCalc != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            if (positionEffect != null) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (sideMultiLegReportingType != null) {
                TagEncoder.encode(bao, TagNum.SideMultiLegReportingType, sideMultiLegReportingType.getValue());
            }
            if (multilegReportingType != null) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoContAmts.getValue(), error);
                }
            }
            if (stipulations != null) {
                bao.write(stipulations.encode(MsgSecureType.ALL_FIELDS));
            }
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMiscFees.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.ExchangeRule, exchangeRule);
            if (tradeAllocIndicator != null) {
                TagEncoder.encode(bao, TagNum.TradeAllocIndicator, tradeAllocIndicator.getValue());
            }
            if (preallocMethod != null) {
                TagEncoder.encode(bao, TagNum.PreallocMethod, preallocMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocID, allocID);
            if (noAllocs != null && noAllocs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocGroups != null && allocGroups.length == noAllocs.intValue()) {
                    for (TradeAllocGroup allocGroup : allocGroups) {
                        bao.write(allocGroup.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "TradeAllocGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoAllocs.getValue(), error);
                }
            }
            if (noSideTrdRegTS != null && noSideTrdRegTS.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoSideTrdRegTS, noSideTrdRegTS);
                if (sideTrdRegTimestampsGroups != null && sideTrdRegTimestampsGroups.length == noSideTrdRegTS.intValue()) {
                    for (int i = 0; i < noSideTrdRegTS.intValue(); i++) {
                        if (sideTrdRegTimestampsGroups[i] != null) {
                            bao.write(sideTrdRegTimestampsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SideTrdRegTimestampsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoSideTrdRegTS.getValue(), error);
                }
            }
            if (noSettlDetails != null && noSettlDetails.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoSettlDetails, noSettlDetails);
                if (settlDetailsGroups != null && settlDetailsGroups.length == noSettlDetails.intValue()) {
                    for (int i = 0; i < noSettlDetails.intValue(); i++) {
                        if (settlDetailsGroups[i] != null) {
                            bao.write(settlDetailsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SettlDetailsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoSettlDetails.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.SideGrossTradeAmt, sideGrossTradeAmt);
            TagEncoder.encode(bao, TagNum.AggressorIndicator, aggressorIndicator);
            TagEncoder.encode(bao, TagNum.ExchangeSpecialInstructions, exchangeSpecialInstructions);
            if (orderCategory != null) {
                TagEncoder.encode(bao, TagNum.OrderCategory, orderCategory.getValue());
            }
            if (sideLiquidityInd != null) {
                TagEncoder.encode(bao, TagNum.SideLiquidityInd, sideLiquidityInd.getValue());
            }
            if (tradeReportOrderDetail != null) {
                bao.write(tradeReportOrderDetail.encode(MsgSecureType.ALL_FIELDS));
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
            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;
                
            case SideExecID:
                sideExecID = new String(tag.value, sessionCharset);
                break;

            case OrderDelay:
                orderDelay = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case OrderDelayUnit:
                orderDelayUnit = OrderDelayUnit.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

            case SecondaryOrderID:
                secondaryOrderID = new String(tag.value, sessionCharset);
                break;
                       
            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case ExecRefID:
                execRefID = new String(tag.value, sessionCharset);
                break;
 
            case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;

            case SideLastQty:
                sideLastQty = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case SideTradeReportID:
                sideTradeReportID = new String(tag.value, sessionCharset);
                break;

            case SideFillStationCd:
                sideFillStationCd = new String(tag.value, sessionCharset);
                break;

            case SideReasonCd:
                sideReasonCd = new String(tag.value, sessionCharset);
                break;

            case RptSeq:
                rptSeq = new Integer(new String(tag.value, sessionCharset));
                break;
                
            case SideTrdSubTyp:
                sideTrdSubTyp = TrdSubType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
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

            case ProcessCode:
                processCode = ProcessCode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;
              
            case LotType:
                lotType = LotType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;
               
            case OddLot:
                oddLot = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;
                
            case NoClearingInstructions:
                noClearingInstructions = new Integer(new String(tag.value, sessionCharset));
                break;

            case ClearingFeeIndicator:
                clearingFeeIndicator = ClearingFeeIndicator.valueFor(new String(tag.value, sessionCharset));
                break;

            case TradeInputSource:
                tradeInputSource = new String(tag.value, sessionCharset);
                break;

            case TradeInputDevice:
                tradeInputDevice = new String(tag.value, sessionCharset);
                break;

            case OrderInputDevice:
                orderInputDevice = new String(tag.value, sessionCharset);
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

            case OrderCapacity:
                orderCapacity = OrderCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case OrderRestrictions:
                orderRestrictions = new String(tag.value, getSessionCharset());
                break;

            case CustOrderCapacity:
                custOrderCapacity = CustOrderCapacity.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ExecInst:
                execInst = new String(tag.value, sessionCharset);
                break;

            case TransBkdTime:
                transBkdTime = DateConverter.parseString(new String(tag.value, sessionCharset));
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

            case SettlCurrFxRate:
                settlCurrFxRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrFxRateCalc:
                settlCurrFxRateCalc = SettlCurrFxRateCalc.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case PositionEffect:
                positionEffect = PositionEffect.valueFor(new String(tag.value, getSessionCharset()));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case SideMultiLegReportingType:
                sideMultiLegReportingType = MultiLegReportingType.valueFor(new String(tag.value, sessionCharset));
                break;

            case MultiLegReportingType:
                multilegReportingType = MultiLegReportingType.valueFor(new String(tag.value, sessionCharset));
                break;

            case NoContAmts:
                noContAmts = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoMiscFees:
                noMiscFees = new Integer(new String(tag.value, sessionCharset));
                break;
             
            case ExchangeRule:
                exchangeRule = new String(tag.value, sessionCharset);
                break;
            
            case TradeAllocIndicator:
                tradeAllocIndicator = TradeAllocIndicator.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
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

            case NoSideTrdRegTS:
                noSideTrdRegTS = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoSettlDetails:
                noSettlDetails = new Integer(new String(tag.value, sessionCharset));
                break;

            case SideGrossTradeAmt:
                sideGrossTradeAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case AggressorIndicator:
                aggressorIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;
                
           case ExchangeSpecialInstructions:
                exchangeSpecialInstructions = new String(tag.value, sessionCharset);
                break;
                
           case OrderCategory:
                orderCategory = OrderCategory.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;
                
           case SideLiquidityInd:
                sideLiquidityInd = FillLiquidityInd.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [TrdCapRptSideGroup] fields.";
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

    private TradeReportOrderDetail getSafeTradeReportOrderDetail() {
        if (getTradeReportOrderDetail() == null) {
            setTradeReportOrderDetail();
        }

        return getTradeReportOrderDetail();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{TrdCapRptSideGroup=");
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.SideExecID, sideExecID);
        printTagValue(b, TagNum.OrderDelay, orderDelay);
        printTagValue(b, TagNum.OrderDelayUnit, orderDelayUnit);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.SecondaryOrderID, secondaryOrderID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.ExecRefID, execRefID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, TagNum.SideLastQty, sideLastQty);
        printTagValue(b, TagNum.SideTradeReportID, sideTradeReportID);
        printTagValue(b, TagNum.SideFillStationCd, sideFillStationCd);
        printTagValue(b, TagNum.SideReasonCd, sideReasonCd);
        printTagValue(b, TagNum.RptSeq, rptSeq);
        printTagValue(b, TagNum.SideTrdSubTyp, sideTrdSubTyp);
        printTagValue(b, parties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.ProcessCode, processCode);
        printTagValue(b, TagNum.LotType, lotType);
        printTagValue(b, TagNum.OddLot, oddLot);
        printTagValue(b, TagNum.NoClearingInstructions, noClearingInstructions);
        printTagValue(b, clrInstGroups);
        printTagValue(b, TagNum.ClearingFeeIndicator, clearingFeeIndicator);
        printTagValue(b, TagNum.TradeInputSource, tradeInputSource);
        printTagValue(b, TagNum.TradeInputDevice, tradeInputDevice);
        printTagValue(b, TagNum.OrderInputDevice, orderInputDevice);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.ComplianceID, complianceID);
        printTagValue(b, TagNum.SolicitedFlag, solicitedFlag);
        printTagValue(b, TagNum.OrderCapacity, orderCapacity);
        printTagValue(b, TagNum.OrderRestrictions, orderRestrictions);
        printTagValue(b, TagNum.CustOrderCapacity, custOrderCapacity);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.ExecInst, execInst);
        printUTCDateTimeTagValue(b, TagNum.TransBkdTime, transBkdTime);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.TimeBracket, timeBracket);
        printTagValue(b, commissionData);
        printTagValue(b, TagNum.GrossTradeAmt, grossTradeAmt);
        printTagValue(b, TagNum.NumDaysInterest, numDaysInterest);
        printDateTagValue(b, TagNum.ExDate, exDate);
        printTagValue(b, TagNum.AccruedInterestRate, accruedInterestRate);
        printTagValue(b, TagNum.AccruedInterestAmt, accruedInterestAmt);
        printTagValue(b, TagNum.InterestAtMaturity, interestAtMaturity);
        printTagValue(b, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
        printTagValue(b, TagNum.StartCash, startCash);
        printTagValue(b, TagNum.EndCash, endCash);
        printTagValue(b, TagNum.SettlCurrFxRate, settlCurrFxRate);
        printTagValue(b, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc);
        printTagValue(b, TagNum.PositionEffect, positionEffect);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.SideMultiLegReportingType, sideMultiLegReportingType);
        printTagValue(b, TagNum.MultiLegReportingType, multilegReportingType);
        printTagValue(b, TagNum.NoContAmts, noContAmts);
        printTagValue(b, contAmtGroups);
        printTagValue(b, stipulations);
        printTagValue(b, TagNum.NoMiscFees, noMiscFees);
        printTagValue(b, miscFeeGroups);
        printTagValue(b, TagNum.ExchangeRule, exchangeRule);
        printTagValue(b, TagNum.TradeAllocIndicator, tradeAllocIndicator);
        printTagValue(b, TagNum.PreallocMethod, preallocMethod);
        printTagValue(b, TagNum.AllocID, allocID);
        printTagValue(b, TagNum.NoAllocs, noAllocs);
        printTagValue(b, allocGroups);
        printTagValue(b, TagNum.NoSideTrdRegTS, noSideTrdRegTS);
        printTagValue(b, sideTrdRegTimestampsGroups);
        printTagValue(b, TagNum.NoSettlDetails, noSettlDetails);
        printTagValue(b, settlDetailsGroups);
        printTagValue(b, TagNum.SideGrossTradeAmt, sideGrossTradeAmt);
        printTagValue(b, TagNum.AggressorIndicator, aggressorIndicator);
        printTagValue(b, TagNum.ExchangeSpecialInstructions, exchangeSpecialInstructions);
        printTagValue(b, TagNum.OrderCategory, orderCategory);
        printTagValue(b, TagNum.SideLiquidityInd, sideLiquidityInd);
        printTagValue(b, tradeReportOrderDetail);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
