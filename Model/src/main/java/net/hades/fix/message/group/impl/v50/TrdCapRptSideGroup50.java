/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdCapRptSideGroup50.java
 *
 * $Id: TrdCapRptSideGroup50.java,v 1.2 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.impl.v50.Stipulations50;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.ClrInstGroup;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.TradeAllocGroup;
import net.hades.fix.message.group.TrdCapRptSideGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

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

import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.impl.v50.CommissionData50;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ContAmtGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.SideTrdRegTimestampsGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TradeAllocIndicator;
import net.hades.fix.message.type.TrdSubType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0 implementation of TrdCapRptSideGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="RptSide")
@XmlType(propOrder = {"partyIDGroups", "clrInstGroups", "commissionData", "contAmtGroups", "stipulationsGroups", "miscFeeGroups",
    "allocGroups", "sideTrdRegTimestampsGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class TrdCapRptSideGroup50 extends TrdCapRptSideGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V50 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Side.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.ExecRefID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.ListID.getValue(),
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
        TagNum.LotType.getValue(),
        TagNum.OddLot.getValue(),
        TagNum.NoClearingInstructions.getValue(),
        TagNum.TradeInputSource.getValue(),
        TagNum.TradeInputDevice.getValue(),
        TagNum.OrderInputDevice.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.TransBkdTime.getValue(),
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
        TagNum.SettlCurrency.getValue(),
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
        TagNum.SideGrossTradeAmt.getValue(),
        TagNum.AggressorIndicator.getValue(),
        TagNum.ExchangeSpecialInstructions.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();
    protected static final Set<Integer> CLR_INST_GROUP_TAGS = new ClrInstGroup50().getFragmentAllTags();
    protected static final Set<Integer> COMMISSION_DATA_COMP_TAGS = new CommissionData50().getFragmentAllTags();
    protected static final Set<Integer> CONT_AMT_GROUP_TAGS = new ContAmtGroup50().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50().getFragmentAllTags();
    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup50().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_GROUP_TAGS = new TradeAllocGroup50().getFragmentAllTags();
    protected static final Set<Integer> SIDE_TRD_REG_TS_GROUP_TAGS = new SideTrdRegTimestampsGroup50().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V50);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(CLR_INST_GROUP_TAGS);
        ALL_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        ALL_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        ALL_TAGS.addAll(ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(SIDE_TRD_REG_TS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(CLR_INST_GROUP_TAGS);
        START_COMP_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(SIDE_TRD_REG_TS_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TrdCapRptSideGroup50() {
    }
    
    public TrdCapRptSideGroup50(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "OrdID2")
    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }
            
    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "ExecRefID")
    @Override
    public String getExecRefID() {
        return execRefID;
    }

    @Override
    public void setExecRefID(String execRefID) {
        this.execRefID = execRefID;
    }

    @XmlAttribute(name = "ClOrdID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @XmlAttribute(name = "ListID")
    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @XmlAttribute(name = "SideQty")
    @Override
    public Integer getSideLastQty() {
        return sideLastQty;
    }

    @Override
    public void setSideLastQty(Integer sideLastQty) {
        this.sideLastQty = sideLastQty;
    }

    @XmlAttribute(name = "RptID")
    @Override
    public String getSideTradeReportID() {
        return sideTradeReportID;
    }

    @Override
    public void setSideTradeReportID(String sideTradeReportID) {
        this.sideTradeReportID = sideTradeReportID;
    }

    @XmlAttribute(name = "FillStationCd")
    @Override
    public String getSideFillStationCd() {
        return sideFillStationCd;
    }

    @Override
    public void setSideFillStationCd(String sideFillStationCd) {
        this.sideFillStationCd = sideFillStationCd;
    }

    @XmlAttribute(name = "RsnCD")
    @Override
    public String getSideReasonCd() {
        return sideReasonCd;
    }

    @Override
    public void setSideReasonCd(String sideReasonCd) {
        this.sideReasonCd = sideReasonCd;
    }

    @XmlAttribute(name = "RptSeq")
    @Override
    public Integer getRptSeq() {
        return rptSeq;
    }

    @Override
    public void setRptSeq(Integer rptSeq) {
        this.rptSeq = rptSeq;
    }

    @XmlAttribute(name = "TrdSubTyp")
    @Override
    public TrdSubType getSideTrdSubTyp() {
        return sideTrdSubTyp;
    }

    @Override
    public void setSideTrdSubTyp(TrdSubType sideTrdSubTyp) {
        this.sideTrdSubTyp = sideTrdSubTyp;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties50) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlAttribute(name = "Acct")
    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @XmlAttribute(name = "AcctIDSrc")
    @Override
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    @Override
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    @XmlAttribute(name = "AcctTyp")
    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @XmlAttribute(name = "ProcCode")
    @Override
    public ProcessCode getProcessCode() {
        return processCode;
    }

    @Override
    public void setProcessCode(ProcessCode processCode) {
        this.processCode = processCode;
    }

    @XmlAttribute(name = "LtTyp")
    @Override
    public LotType getLotType() {
        return lotType;
    }

    @Override
    public void setLotType(LotType lotType) {
        this.lotType = lotType;
    }

    @XmlAttribute(name = "OddLot")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getOddLot() {
        return oddLot;
    }

    @Override
    public void setOddLot(Boolean oddLot) {
        this.oddLot = oddLot;
    }

    @Override
    public Integer getNoClearingInstructions() {
        return noClearingInstructions;
    }

    @Override
    public void setNoClearingInstructions(Integer noClearingInstructions) {
        this.noClearingInstructions = noClearingInstructions;
        if (noClearingInstructions != null) {
            clrInstGroups = new ClrInstGroup[noClearingInstructions.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < clrInstGroups.length; i++) {
                clrInstGroups[i] = new ClrInstGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public ClrInstGroup[] getClrInstGroups() {
        return clrInstGroups;
    }

    public void setClrInstGroups(ClrInstGroup[] clrInstGroups) {
        this.clrInstGroups = clrInstGroups;
        if (clrInstGroups != null) {
            noClearingInstructions = new Integer(clrInstGroups.length);
        }
    }

    @Override
    public ClrInstGroup addClrInstGroup() {
        ClrInstGroup group = new ClrInstGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ClrInstGroup> groups = new ArrayList<ClrInstGroup>();
        if (clrInstGroups != null && clrInstGroups.length > 0) {
            groups = new ArrayList<ClrInstGroup>(Arrays.asList(clrInstGroups));
        }
        groups.add(group);
        clrInstGroups = groups.toArray(new ClrInstGroup[groups.size()]);
        noClearingInstructions = new Integer(clrInstGroups.length);

        return group;
    }

    @Override
    public ClrInstGroup deleteClrInstGroup(int index) {
        ClrInstGroup result = null;
        if (clrInstGroups != null && clrInstGroups.length > 0 && clrInstGroups.length > index) {
            List<ClrInstGroup> groups = new ArrayList<ClrInstGroup>(Arrays.asList(clrInstGroups));
            result = groups.remove(index);
            clrInstGroups = groups.toArray(new ClrInstGroup[groups.size()]);
            if (clrInstGroups.length > 0) {
                noClearingInstructions = new Integer(clrInstGroups.length);
            } else {
                clrInstGroups = null;
                noClearingInstructions = null;
            }
        }

        return result;
    }

    @Override
    public int clearClrInstGroups() {
        int result = 0;
        if (clrInstGroups != null && clrInstGroups.length > 0) {
            result = clrInstGroups.length;
            clrInstGroups = null;
            noClearingInstructions = null;
        }

        return result;
    }

    @XmlAttribute(name = "InptSrc")
    @Override
    public String getTradeInputSource() {
        return tradeInputSource;
    }

    @Override
    public void setTradeInputSource(String tradeInputSource) {
        this.tradeInputSource = tradeInputSource;
    }

    @XmlAttribute(name = "InptDev")
    @Override
    public String getTradeInputDevice() {
        return tradeInputDevice;
    }

    @Override
    public void setTradeInputDevice(String tradeInputDevice) {
        this.tradeInputDevice = tradeInputDevice;
    }

    @XmlAttribute(name = "OrdInptDev")
    @Override
    public String getOrderInputDevice() {
        return orderInputDevice;
    }

    @Override
    public void setOrderInputDevice(String orderInputDevice) {
        this.orderInputDevice = orderInputDevice;
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

    @XmlAttribute(name = "ComplianceID")
    @Override
    public String getComplianceID() {
        return complianceID;
    }

    @Override
    public void setComplianceID(String complianceID) {
        this.complianceID = complianceID;
    }

    @XmlAttribute(name = "SolFlag")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getSolicitedFlag() {
        return solicitedFlag;
    }

    @Override
    public void setSolicitedFlag(Boolean solicitedFlag) {
        this.solicitedFlag = solicitedFlag;
    }

    @XmlAttribute(name = "Cpcty")
    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    @XmlAttribute(name = "Rstctions")
    @Override
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    @Override
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    @XmlAttribute(name = "CustCpcty")
    @Override
    public CustOrderCapacity getCustOrderCapacity() {
        return custOrderCapacity;
    }

    @Override
    public void setCustOrderCapacity(CustOrderCapacity custOrderCapacity) {
        this.custOrderCapacity = custOrderCapacity;
    }

    @XmlAttribute(name = "OrdTyp")
    @Override
    public OrdType getOrdType() {
        return ordType;
    }

    @Override
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    @XmlAttribute(name = "ExecInst")
    @Override
    public String getExecInst() {
        return execInst;
    }

    @Override
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    @XmlAttribute(name = "TransBkdTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    @Override
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
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

    @XmlAttribute(name = "TmBkt")
    @Override
    public String getTimeBracket() {
        return timeBracket;
    }

    @Override
    public void setTimeBracket(String timeBracket) {
        this.timeBracket = timeBracket;
    }

    @XmlElementRef
    @Override
    public CommissionData getCommissionData() {
        return commissionData;
    }

    public void setCommissionData(CommissionData commissionData) {
        this.commissionData = commissionData;
    }

    @Override
    public void setCommissionData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.commissionData = new CommissionData50(context);
    }

    @Override
    public void clearCommissionData() {
        commissionData = null;
    }

    @XmlAttribute(name = "NumDaysInt")
    @Override
    public Integer getNumDaysInterest() {
        return numDaysInterest;
    }

    @Override
    public void setNumDaysInterest(Integer numDaysInterest) {
        this.numDaysInterest = numDaysInterest;
    }

    @XmlAttribute(name = "ExDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getExDate() {
        return exDate;
    }

    @Override
    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    @XmlAttribute(name = "AcrdIntRt")
    @Override
    public Double getAccruedInterestRate() {
        return accruedInterestRate;
    }

    @Override
    public void setAccruedInterestRate(Double accruedInterestRate) {
        this.accruedInterestRate = accruedInterestRate;
    }

    @XmlAttribute(name = "AcrdIntAmt")
    @Override
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    @Override
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    @XmlAttribute(name = "IntAtMat")
    @Override
    public Double getInterestAtMaturity() {
        return interestAtMaturity;
    }

    @Override
    public void setInterestAtMaturity(Double interestAtMaturity) {
        this.interestAtMaturity = interestAtMaturity;
    }

    @XmlAttribute(name = "EndAcrdIntAmt")
    @Override
    public Double getEndAccruedInterestAmt() {
        return endAccruedInterestAmt;
    }

    @Override
    public void setEndAccruedInterestAmt(Double endAccruedInterestAmt) {
        this.endAccruedInterestAmt = endAccruedInterestAmt;
    }

    @XmlAttribute(name = "StartCsh")
    @Override
    public Double getStartCash() {
        return startCash;
    }

    @Override
    public void setStartCash(Double startCash) {
        this.startCash = startCash;
    }

    @XmlAttribute(name = "EndCsh")
    @Override
    public Double getEndCash() {
        return endCash;
    }

    @Override
    public void setEndCash(Double endCash) {
        this.endCash = endCash;
    }

    @XmlAttribute(name = "Concession")
    @Override
    public Double getConcession() {
        return concession;
    }

    @Override
    public void setConcession(Double concession) {
        this.concession = concession;
    }

    @XmlAttribute(name = "TotTakedown")
    @Override
    public Double getTotalTakedown() {
        return totalTakedown;
    }

    @Override
    public void setTotalTakedown(Double totalTakedown) {
        this.totalTakedown = totalTakedown;
    }

    @XmlAttribute(name = "NetMny")
    @Override
    public Double getNetMoney() {
        return netMoney;
    }

    @Override
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }

    @XmlAttribute(name = "SettlCurrAmt")
    @Override
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    @Override
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
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

    @XmlAttribute(name = "SettlCurrFxRt")
    @Override
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    @Override
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    @XmlAttribute(name = "SettlCurrFxRtCalc")
    @Override
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    @Override
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    @XmlAttribute(name = "PosEfct")
    @Override
    public PositionEffect getPositionEffect() {
        return positionEffect;
    }

    @Override
    public void setPositionEffect(PositionEffect positionEffect) {
        this.positionEffect = positionEffect;
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

    @XmlAttribute(name = "MLegRptTyp")
    @Override
    public MultiLegReportingType getSideMultiLegReportingType() {
        return sideMultiLegReportingType;
    }

    @Override
    public void setSideMultiLegReportingType(MultiLegReportingType sideMultiLegReportingType) {
        this.sideMultiLegReportingType = sideMultiLegReportingType;
    }

    @Override
    public Integer getNoContAmts() {
        return noContAmts;
    }

    @Override
    public void setNoContAmts(Integer noContAmts) {
        this.noContAmts = noContAmts;
        if (noContAmts != null) {
            contAmtGroups = new ContAmtGroup[noContAmts.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < contAmtGroups.length; i++) {
                contAmtGroups[i] = new ContAmtGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public ContAmtGroup[] getContAmtGroups() {
        return contAmtGroups;
    }

    public void setContAmtGroups(ContAmtGroup[] contAmtGroups) {
        this.contAmtGroups = contAmtGroups;
        if (contAmtGroups != null) {
            noContAmts = new Integer(contAmtGroups.length);
        }
    }

    @Override
    public ContAmtGroup addContAmtGroup() {
        ContAmtGroup group = new ContAmtGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<ContAmtGroup> groups = new ArrayList<ContAmtGroup>();
        if (contAmtGroups != null && contAmtGroups.length > 0) {
            groups = new ArrayList<ContAmtGroup>(Arrays.asList(contAmtGroups));
        }
        groups.add(group);
        contAmtGroups = groups.toArray(new ContAmtGroup[groups.size()]);
        noContAmts = new Integer(contAmtGroups.length);

        return group;
    }

    @Override
    public ContAmtGroup deleteContAmtGroup(int index) {
        ContAmtGroup result = null;
        if (contAmtGroups != null && contAmtGroups.length > 0 && contAmtGroups.length > index) {
            List<ContAmtGroup> groups = new ArrayList<ContAmtGroup>(Arrays.asList(contAmtGroups));
            result = groups.remove(index);
            contAmtGroups = groups.toArray(new ContAmtGroup[groups.size()]);
            if (contAmtGroups.length > 0) {
                noContAmts = new Integer(contAmtGroups.length);
            } else {
                contAmtGroups = null;
                noContAmts = null;
            }
        }

        return result;
    }

    @Override
    public int clearContAmtGroups() {
        int result = 0;
        if (contAmtGroups != null && contAmtGroups.length > 0) {
            result = contAmtGroups.length;
            contAmtGroups = null;
            noContAmts = null;
        }

        return result;
    }

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.stipulations = new Stipulations50(context);
    }

    @Override
    public void clearStipulations() {
         this.stipulations = null;
    }

    public void setStipulations(Stipulations stipulations) {
        this.stipulations = stipulations;
    }

    @XmlElementRef
    public StipulationsGroup[] getStipulationsGroups() {
        return stipulations == null ? null : stipulations.getStipulationsGroups();
    }

    public void setStipulationsGroups(StipulationsGroup[] stipulationsGroups) {
        if (stipulationsGroups != null) {
            if (stipulations == null) {
                setStipulations();
            }
            ((Stipulations50) stipulations).setStipulationsGroups(stipulationsGroups);
        }
    }

    @Override
    public Integer getNoMiscFees() {
        return noMiscFees;
    }

    @Override
    public void setNoMiscFees(Integer noMiscFees) {
        this.noMiscFees = noMiscFees;
        if (noMiscFees != null) {
            miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < miscFeeGroups.length; i++) {
                miscFeeGroups[i] = new MiscFeeGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public MiscFeeGroup[] getMiscFeeGroups() {
        return miscFeeGroups;
    }

    public void setMiscFeeGroups(MiscFeeGroup[] miscFeeGroups) {
        this.miscFeeGroups = miscFeeGroups;
        if (miscFeeGroups != null) {
            noMiscFees = new Integer(miscFeeGroups.length);
        }
    }

    @Override
    public MiscFeeGroup addMiscFeeGroup() {
        MiscFeeGroup group = new MiscFeeGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MiscFeeGroup> groups = new ArrayList<MiscFeeGroup>();
        if (miscFeeGroups != null && miscFeeGroups.length > 0) {
            groups = new ArrayList<MiscFeeGroup>(Arrays.asList(miscFeeGroups));
        }
        groups.add(group);
        miscFeeGroups = groups.toArray(new MiscFeeGroup[groups.size()]);
        noMiscFees = new Integer(miscFeeGroups.length);

        return group;
    }

    @Override
    public MiscFeeGroup deleteMiscFeeGroup(int index) {
        MiscFeeGroup result = null;
        if (miscFeeGroups != null && miscFeeGroups.length > 0 && miscFeeGroups.length > index) {
            List<MiscFeeGroup> groups = new ArrayList<MiscFeeGroup>(Arrays.asList(miscFeeGroups));
            result = groups.remove(index);
            miscFeeGroups = groups.toArray(new MiscFeeGroup[groups.size()]);
            if (miscFeeGroups.length > 0) {
                noMiscFees = new Integer(miscFeeGroups.length);
            } else {
                miscFeeGroups = null;
                noMiscFees = null;
            }
        }

        return result;
    }

    @Override
    public int clearMiscFeeGroups() {
        int result = 0;
        if (miscFeeGroups != null && miscFeeGroups.length > 0) {
            result = miscFeeGroups.length;
            miscFeeGroups = null;
            noMiscFees = null;
        }

        return result;
    }

    @XmlAttribute(name = "ExchRule")
    @Override
    public String getExchangeRule() {
        return exchangeRule;
    }

    @Override
    public void setExchangeRule(String exchangeRule) {
        this.exchangeRule = exchangeRule;
    }

    @XmlAttribute(name = "AllocInd")
    @Override
    public TradeAllocIndicator getTradeAllocIndicator() {
        return tradeAllocIndicator;
    }

    @Override
    public void setTradeAllocIndicator(TradeAllocIndicator tradeAllocIndicator) {
        this.tradeAllocIndicator = tradeAllocIndicator;
    }

    @XmlAttribute(name = "AllocID")
    @Override
    public String getAllocID() {
        return allocID;
    }

    @Override
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    @XmlAttribute(name = "PreallocMeth")
    @Override
    public PreallocMethod getPreallocMethod() {
        return preallocMethod;
    }

    @Override
    public void setPreallocMethod(PreallocMethod preallocMethod) {
        this.preallocMethod = preallocMethod;
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
                allocGroups[i] = new TradeAllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        TradeAllocGroup group = new TradeAllocGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
    
    @Override
    public Integer getNoSideTrdRegTS() {
        return noSideTrdRegTS;
    }

    @Override
    public void setNoSideTrdRegTS(Integer noSideTrdRegTS) {
        this.noSideTrdRegTS = noSideTrdRegTS;
        if (noSideTrdRegTS != null) {
            sideTrdRegTimestampsGroups = new SideTrdRegTimestampsGroup[noSideTrdRegTS.intValue()];
            for (int i = 0; i < sideTrdRegTimestampsGroups.length; i++) {
                sideTrdRegTimestampsGroups[i] = new SideTrdRegTimestampsGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SideTrdRegTimestampsGroup[] getSideTrdRegTimestampsGroups() {
        return sideTrdRegTimestampsGroups;
    }

    public void setSideTrdRegTimestampsGroups(SideTrdRegTimestampsGroup[] sideTrdRegTimestampsGroups) {
        this.sideTrdRegTimestampsGroups = sideTrdRegTimestampsGroups;
        if (sideTrdRegTimestampsGroups != null) {
            noSideTrdRegTS = new Integer(sideTrdRegTimestampsGroups.length);
        }
    }
    @Override
    public SideTrdRegTimestampsGroup addSideTrdRegTimestampsGroup() {
        SideTrdRegTimestampsGroup group = new SideTrdRegTimestampsGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SideTrdRegTimestampsGroup> groups = new ArrayList<SideTrdRegTimestampsGroup>();
        if (sideTrdRegTimestampsGroups != null && sideTrdRegTimestampsGroups.length > 0) {
            groups = new ArrayList<SideTrdRegTimestampsGroup>(Arrays.asList(sideTrdRegTimestampsGroups));
        }
        groups.add(group);
        sideTrdRegTimestampsGroups = groups.toArray(new SideTrdRegTimestampsGroup[groups.size()]);
        noSideTrdRegTS = new Integer(sideTrdRegTimestampsGroups.length);

        return group;
    }

    @Override
    public SideTrdRegTimestampsGroup deleteSideTrdRegTimestampsGroup(int index) {
        SideTrdRegTimestampsGroup result = null;
        if (sideTrdRegTimestampsGroups != null && sideTrdRegTimestampsGroups.length > 0 && sideTrdRegTimestampsGroups.length > index) {
            List<SideTrdRegTimestampsGroup> groups = new ArrayList<SideTrdRegTimestampsGroup>(Arrays.asList(sideTrdRegTimestampsGroups));
            result = groups.remove(index);
            sideTrdRegTimestampsGroups = groups.toArray(new SideTrdRegTimestampsGroup[groups.size()]);
            if (sideTrdRegTimestampsGroups.length > 0) {
                noSideTrdRegTS = new Integer(sideTrdRegTimestampsGroups.length);
            } else {
                sideTrdRegTimestampsGroups = null;
                noSideTrdRegTS = null;
            }
        }

        return result;
    }

    @Override
    public int clearSideTrdRegTimestampsGroups() {
        int result = 0;
        if (sideTrdRegTimestampsGroups != null && sideTrdRegTimestampsGroups.length > 0) {
            result = sideTrdRegTimestampsGroups.length;
            sideTrdRegTimestampsGroups = null;
            noSideTrdRegTS = null;
        }

        return result;
    }

    @XmlAttribute(name = "SideGrossTradeAmt")
    @Override
    public Double getSideGrossTradeAmt() {
        return sideGrossTradeAmt;
    }

    @Override
    public void setSideGrossTradeAmt(Double sideGrossTradeAmt) {
        this.sideGrossTradeAmt = sideGrossTradeAmt;
    }

    @XmlAttribute(name = "AgrsrInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getAggressorIndicator() {
        return aggressorIndicator;
    }

    @Override
    public void setAggressorIndicator(Boolean aggressorIndicator) {
        this.aggressorIndicator = aggressorIndicator;
    }

    @XmlAttribute(name = "ExchSpeclInstr")
    @Override
    public String getExchangeSpecialInstructions() {
        return exchangeSpecialInstructions;
    }

    @Override
    public void setExchangeSpecialInstructions(String exchangeSpecialInstructions) {
        this.exchangeSpecialInstructions = exchangeSpecialInstructions;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
            
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50(context);
            }
            parties.decode(tag, message);
        }
        if (CLR_INST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noClearingInstructions != null && noClearingInstructions.intValue() > 0) {
                message.reset();
                clrInstGroups = new ClrInstGroup[noClearingInstructions.intValue()];
                for (int i = 0; i < noClearingInstructions.intValue(); i++) {
                    ClrInstGroup component = new ClrInstGroup50(context);
                    component.decode(message);
                    clrInstGroups[i] = component;
                }
            }
        }
        if (COMMISSION_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (commissionData == null) {
                commissionData = new CommissionData50(context);
            }
            commissionData.decode(tag, message);
        }
        if (CONT_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noContAmts != null && noContAmts.intValue() > 0) {
                message.reset();
                contAmtGroups = new ContAmtGroup[noContAmts.intValue()];
                for (int i = 0; i < noContAmts.intValue(); i++) {
                    ContAmtGroup component = new ContAmtGroup50(context);
                    component.decode(message);
                    contAmtGroups[i] = component;
                }
            }
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50(context);
            }
            stipulations.decode(tag, message);
        }
        if (MISC_FEE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMiscFees != null && noMiscFees.intValue() > 0) {
                message.reset();
                miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
                for (int i = 0; i < noMiscFees.intValue(); i++) {
                    MiscFeeGroup component = new MiscFeeGroup50(context);
                    component.decode(message);
                    miscFeeGroups[i] = component;
                }
            }
        }      
        if (ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAllocs != null && noAllocs.intValue() > 0) {
                message.reset();
                allocGroups = new TradeAllocGroup[noAllocs.intValue()];
                for (int i = 0; i < noAllocs.intValue(); i++) {
                    TradeAllocGroup group = new TradeAllocGroup50(context);
                    group.decode(message);
                    allocGroups[i] = group;
                }
            }
        }
        if (SIDE_TRD_REG_TS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSideTrdRegTS != null && noSideTrdRegTS.intValue() > 0) {
                message.reset();
                sideTrdRegTimestampsGroups = new SideTrdRegTimestampsGroup[noSideTrdRegTS.intValue()];
                for (int i = 0; i < noSideTrdRegTS.intValue(); i++) {
                    SideTrdRegTimestampsGroup group = new SideTrdRegTimestampsGroup50(context);
                    group.decode(message);
                    sideTrdRegTimestampsGroups[i] = group;
                }
            }
        }
    }
    
    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TrdCapRptSideGroup] group version [5.0].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
