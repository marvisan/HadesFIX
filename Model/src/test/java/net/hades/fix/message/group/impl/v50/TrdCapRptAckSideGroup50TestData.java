/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdCapRptAckSideGroup50TestData.java
 *
 * $Id: TrdCapRptAckSideGroup50TestData.java,v 1.2 2011-10-29 09:42:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.group.TrdCapRptAckSideGroup;
import net.hades.fix.message.group.impl.v43.ClrInstGroup43TestData;
import net.hades.fix.message.group.impl.v43.ContAmtGroup43TestData;
import net.hades.fix.message.group.impl.v44.MiscFeeGroup44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderRestrictions;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradeAllocIndicator;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;
import net.hades.fix.message.type.TrdSubType;

/**
 * Test utility for TrdCapRptAckSideGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class TrdCapRptAckSideGroup50TestData extends MsgTest {

    private static final TrdCapRptAckSideGroup50TestData INSTANCE;

    static {
        INSTANCE = new TrdCapRptAckSideGroup50TestData();
    }

    public static TrdCapRptAckSideGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdCapRptAckSideGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setSide(Side.Buy);
        grp.setOrderID("ORD_1111");
        grp.setSecondaryOrderID("SEC_ORD_8888");
        grp.setClOrdID("CLI_ORD_9999");
        grp.setSecondaryClOrdID("SEC_CLI_ORD_6666");
        grp.setListID("LIST_1111");
       
        grp.setParties();
        Parties50TestData.getInstance().populate(grp.getParties());
        
        grp.setAccount("2537836464");
        grp.setAcctIDSource(AcctIDSource.SID);
        grp.setAccountType(AccountType.HouseTrader);
        grp.setProcessCode(ProcessCode.Regular);
        grp.setLotType(LotType.BlockLot);
        grp.setOddLot(Boolean.FALSE);
                
        grp.setNoClearingInstructions(2);
        ClrInstGroup43TestData.getInstance().populate1(grp.getClrInstGroups()[0]);
        ClrInstGroup43TestData.getInstance().populate2(grp.getClrInstGroups()[1]);
        
        grp.setTradeInputDevice("TRD_INP_DEV");
        grp.setTradeInputSource("TRD_INP_SRC");
        grp.setOrderInputDevice("DEV1");
        grp.setCurrency(Currency.UnitedStatesDollar);
        grp.setComplianceID("COMPL_33333");
        grp.setSolicitedFlag(Boolean.TRUE);
        grp.setOrderCapacity(OrderCapacity.Proprietary);
        grp.setOrderRestrictions(OrderRestrictions.CompetingMarketMaker.getValue());
        grp.setCustOrderCapacity(CustOrderCapacity.AllOther);
        grp.setOrdType(OrdType.Stop);
        grp.setExecInst(ExecInst.CallFirst.getValue());
        cal.set(2011, 10, 11, 10, 23, 34);
        grp.setTransBkdTime(cal.getTime());
        grp.setTradingSessionID(TradingSessionID.Day.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        grp.setTimeBracket("BRKT1");
               
        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setNumDaysInterest(3);
        cal.set(2011, 10, 11, 11, 12, 22);
        grp.setExDate(cal.getTime());
        grp.setAccruedInterestRate(13.5d);
        grp.setAccruedInterestAmt(57.9d);
        grp.setInterestAtMaturity(34.77d);
        grp.setEndAccruedInterestAmt(35.88d);
        grp.setStartCash(30.55d);
        grp.setEndCash(38.66d);
        grp.setConcession(2.5d);
        grp.setTotalTakedown(34.5d);
        grp.setNetMoney(568.8d);
        grp.setSettlCurrAmt(450.45d);
        grp.setSettlCurrency(Currency.CanadianDollar);
        grp.setSettlCurrFxRate(25.6d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        grp.setPositionEffect(PositionEffect.Close);
        grp.setSideMultiLegReportingType(MultiLegReportingType.MultiLegSecurity);
        
        grp.setNoContAmts(2);
        ContAmtGroup43TestData.getInstance().populate1(grp.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().populate2(grp.getContAmtGroups()[1]);
                
        grp.setStipulations();
        Stipulations44TestData.getInstance().populate(grp.getStipulations());
        
        grp.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);
        
        grp.setExchangeRule("RULE1");
        grp.setTradeAllocIndicator(TradeAllocIndicator.AllocRequired);
        grp.setPreallocMethod(PreallocMethod.ProRata);
        grp.setAllocID("ALLOC_1");
         
        grp.setNoAllocs(2);
        TradeAllocGroup50TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        TradeAllocGroup50TestData.getInstance().populate2(grp.getAllocGroups()[1]);

        grp.setSideGrossTradeAmt(66.22d);
        grp.setAggressorIndicator(Boolean.FALSE);
        grp.setSideLastQty(34);
        grp.setSideTradeReportID("SIDE_TRD_RPT_2222");
        grp.setSideFillStationCd("ST1");
        grp.setSideReasonCd("REASON_1");
        grp.setRptSeq(4);
        grp.setSideTrdSubTyp(TrdSubType.RejectForSubmittingSide);

        grp.setNoSideTrdRegTS(2);
        SideTrdRegTimestamps50TestData.getInstance().populate1(grp.getSideTrdRegTimestampsGroups()[0]);
        SideTrdRegTimestamps50TestData.getInstance().populate2(grp.getSideTrdRegTimestampsGroups()[1]);
    }

    public void populate2(TrdCapRptAckSideGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setSide(Side.Buy);
        grp.setOrderID("ORD_1112");
        grp.setSecondaryOrderID("SEC_ORD_8889");
        grp.setClOrdID("CLI_ORD_9990");
        grp.setSecondaryClOrdID("SEC_CLI_ORD_6667");
        grp.setListID("LIST_1112");
       
        grp.setParties();
        Parties50TestData.getInstance().populate(grp.getParties());
        
        grp.setAccount("2537836465");
        grp.setAcctIDSource(AcctIDSource.SID);
        grp.setAccountType(AccountType.HouseTrader);
        grp.setProcessCode(ProcessCode.Regular);
        grp.setLotType(LotType.BlockLot);
        grp.setOddLot(Boolean.FALSE);
                
        grp.setNoClearingInstructions(2);
        ClrInstGroup43TestData.getInstance().populate1(grp.getClrInstGroups()[0]);
        ClrInstGroup43TestData.getInstance().populate2(grp.getClrInstGroups()[1]);
        
        grp.setTradeInputDevice("TRD_INP_DEV1");
        grp.setTradeInputSource("TRD_INP_SRC1");
        grp.setOrderInputDevice("DEV2");
        grp.setCurrency(Currency.AustralianDollar);
        grp.setComplianceID("COMPL_33334");
        grp.setSolicitedFlag(Boolean.FALSE);
        grp.setOrderCapacity(OrderCapacity.Agency);
        grp.setOrderRestrictions(OrderRestrictions.ProgramTrade.getValue());
        grp.setCustOrderCapacity(CustOrderCapacity.ClearingFirmTrading);
        grp.setOrdType(OrdType.ForexSwap);
        grp.setExecInst(ExecInst.Held.getValue());
        cal.set(2011, 10, 12, 10, 25, 34);
        grp.setTransBkdTime(cal.getTime());
        grp.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Opening.getValue());
        grp.setTimeBracket("BRKT2");
               
        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setNumDaysInterest(3);
        cal.set(2011, 4, 11, 1, 12, 22);
        grp.setExDate(cal.getTime());
        grp.setAccruedInterestRate(14.5d);
        grp.setAccruedInterestAmt(58.9d);
        grp.setInterestAtMaturity(31.77d);
        grp.setEndAccruedInterestAmt(32.88d);
        grp.setStartCash(32.55d);
        grp.setEndCash(39.66d);
        grp.setConcession(21.5d);
        grp.setTotalTakedown(35.5d);
        grp.setNetMoney(58.8d);
        grp.setSettlCurrAmt(452.45d);
        grp.setSettlCurrency(Currency.UnitedStatesDollar);
        grp.setSettlCurrFxRate(28.6d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Divide);
        grp.setPositionEffect(PositionEffect.Open);
        grp.setSideMultiLegReportingType(MultiLegReportingType.SingleSecurity);
        
        grp.setNoContAmts(2);
        ContAmtGroup43TestData.getInstance().populate1(grp.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().populate2(grp.getContAmtGroups()[1]);
                
        grp.setStipulations();
        Stipulations44TestData.getInstance().populate(grp.getStipulations());
        
        grp.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);
        
        grp.setExchangeRule("RULE2");
        grp.setTradeAllocIndicator(TradeAllocIndicator.AllocGiveupExecutor);
        grp.setPreallocMethod(PreallocMethod.ProRata);
        grp.setAllocID("ALLOC_2");
         
        grp.setNoAllocs(2);
        TradeAllocGroup50TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        TradeAllocGroup50TestData.getInstance().populate2(grp.getAllocGroups()[1]);

        grp.setSideGrossTradeAmt(67.22d);
        grp.setAggressorIndicator(Boolean.TRUE);
        grp.setSideLastQty(36);
        grp.setSideTradeReportID("SIDE_TRD_RPT_2223");
        grp.setSideFillStationCd("ST2");
        grp.setSideReasonCd("REASON_2");
        grp.setRptSeq(6);
        grp.setSideTrdSubTyp(TrdSubType.Adjustment);

        grp.setNoSideTrdRegTS(2);
        SideTrdRegTimestamps50TestData.getInstance().populate1(grp.getSideTrdRegTimestampsGroups()[0]);
        SideTrdRegTimestamps50TestData.getInstance().populate2(grp.getSideTrdRegTimestampsGroups()[1]);
    }

    public void check(TrdCapRptAckSideGroup expected, TrdCapRptAckSideGroup actual) throws Exception {
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getListID(), actual.getListID());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getLotType(), actual.getLotType());
        assertEquals(expected.getOddLot(), actual.getOddLot());
        
        assertEquals(expected.getNoClearingInstructions(), actual.getNoClearingInstructions());
        ClrInstGroup43TestData.getInstance().check(expected.getClrInstGroups()[0], actual.getClrInstGroups()[0]);
        ClrInstGroup43TestData.getInstance().check(expected.getClrInstGroups()[1], actual.getClrInstGroups()[1]);
        
        assertEquals(expected.getTradeInputDevice(), actual.getTradeInputDevice());
        assertEquals(expected.getTradeInputSource(), actual.getTradeInputSource());
        assertEquals(expected.getOrderInputDevice(), actual.getOrderInputDevice());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getCustOrderCapacity(), actual.getCustOrderCapacity());
        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertUTCTimestampEquals(expected.getTransBkdTime(), actual.getTransBkdTime(), false);
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getTimeBracket(), actual.getTimeBracket());
        
        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());
        
        assertEquals(expected.getNumDaysInterest(), actual.getNumDaysInterest());
        assertDateEquals(expected.getExDate(), actual.getExDate());
        assertEquals(expected.getAccruedInterestRate(), actual.getAccruedInterestRate());
        assertEquals(expected.getAccruedInterestAmt(), actual.getAccruedInterestAmt());
        assertEquals(expected.getInterestAtMaturity(), actual.getInterestAtMaturity());
        assertEquals(expected.getEndAccruedInterestAmt(), actual.getEndAccruedInterestAmt());
        assertEquals(expected.getStartCash(), actual.getStartCash());
        assertEquals(expected.getEndCash(), actual.getEndCash());
        assertEquals(expected.getConcession(), actual.getConcession());
        assertEquals(expected.getTotalTakedown(), actual.getTotalTakedown());
        assertEquals(expected.getNetMoney(), actual.getNetMoney());
        assertEquals(expected.getSettlCurrAmt(), actual.getSettlCurrAmt());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getSettlCurrFxRate(), actual.getSettlCurrFxRate());
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getSideMultiLegReportingType(), actual.getSideMultiLegReportingType());
                
        assertEquals(expected.getNoContAmts(), actual.getNoContAmts());
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[0], actual.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[1], actual.getContAmtGroups()[1]);
        
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
       
        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);
        
        assertEquals(expected.getExchangeRule(), actual.getExchangeRule());
        assertEquals(expected.getTradeAllocIndicator(), actual.getTradeAllocIndicator());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());
        assertEquals(expected.getAllocID(), actual.getAllocID());
        
        assertEquals(expected.getNoAllocs(), actual.getNoAllocs());
        TradeAllocGroup50TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        TradeAllocGroup50TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);

        assertEquals(expected.getSideGrossTradeAmt(), actual.getSideGrossTradeAmt());
        assertEquals(expected.getAggressorIndicator(), actual.getAggressorIndicator());
        assertEquals(expected.getSideLastQty(), actual.getSideLastQty());
        assertEquals(expected.getSideTradeReportID(), actual.getSideTradeReportID());
        assertEquals(expected.getSideFillStationCd(), actual.getSideFillStationCd());
        assertEquals(expected.getSideReasonCd(), actual.getSideReasonCd());
        assertEquals(expected.getRptSeq(), actual.getRptSeq());
        assertEquals(expected.getSideTrdSubTyp(), actual.getSideTrdSubTyp());

        assertEquals(expected.getNoSideTrdRegTS(), actual.getNoSideTrdRegTS());
        SideTrdRegTimestamps50TestData.getInstance().check(expected.getSideTrdRegTimestampsGroups()[0], actual.getSideTrdRegTimestampsGroups()[0]);
        SideTrdRegTimestamps50TestData.getInstance().check(expected.getSideTrdRegTimestampsGroups()[1], actual.getSideTrdRegTimestampsGroups()[1]);
    }
}
