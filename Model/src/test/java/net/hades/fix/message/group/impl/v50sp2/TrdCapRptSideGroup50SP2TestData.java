/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdCapRptSideGroup50SP2TestData.java
 *
 * $Id: TrdCapRptSideGroup50SP2TestData.java,v 1.1 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.TradeReportOrderDetail50SP2TestData;
import net.hades.fix.message.group.TrdCapRptSideGroup;
import net.hades.fix.message.group.impl.v43.ClrInstGroup43TestData;
import net.hades.fix.message.group.impl.v43.ContAmtGroup43TestData;
import net.hades.fix.message.group.impl.v44.MiscFeeGroup44TestData;
import net.hades.fix.message.group.impl.v50.SideTrdRegTimestamps50TestData;
import net.hades.fix.message.group.impl.v50.TradeAllocGroup50TestData;
import net.hades.fix.message.group.impl.v50sp1.SettlDetailsGroup50SP1TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.FillLiquidityInd;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrderCategory;
import net.hades.fix.message.type.OrderDelayUnit;
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
 * Test utility for TrdCapRptSideGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class TrdCapRptSideGroup50SP2TestData extends MsgTest {

    private static final TrdCapRptSideGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new TrdCapRptSideGroup50SP2TestData();
    }

    public static TrdCapRptSideGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdCapRptSideGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setSide(Side.Buy);
        grp.setSideExecID("EXEC_87766");
        grp.setOrderDelay(3);
        grp.setOrderDelayUnit(OrderDelayUnit.Days);
        grp.setOrderID("ORD_1111");
        grp.setSideLastQty(34);
        grp.setSideTradeReportID("SIDE_TRD_RPT_2222");
        grp.setSideFillStationCd("ST1");
        grp.setSideReasonCd("REASON_1");
        grp.setRptSeq(4);
        grp.setSideTrdSubTyp(TrdSubType.RejectForSubmittingSide);
        
        grp.setParties();
        Parties50SP2TestData.getInstance().populate(grp.getParties());
        
        grp.setAccount("2537836464");
        grp.setAcctIDSource(AcctIDSource.SID);
        grp.setAccountType(AccountType.HouseTrader);
        grp.setProcessCode(ProcessCode.Regular);
        grp.setOddLot(Boolean.FALSE);
        
        grp.setNoClearingInstructions(2);
        ClrInstGroup43TestData.getInstance().populate1(grp.getClrInstGroups()[0]);
        ClrInstGroup43TestData.getInstance().populate2(grp.getClrInstGroups()[1]);
        
        grp.setTradeInputSource("TRD_INP_SRC");
        grp.setTradeInputDevice("TRD_INP_DEV");
        grp.setComplianceID("COMPL_33333");
        grp.setSolicitedFlag(Boolean.TRUE);
        grp.setCustOrderCapacity(CustOrderCapacity.AllOther);
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
        grp.setSettlCurrFxRate(25.6d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        grp.setPositionEffect(PositionEffect.Close);
        grp.setText("Some text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
        grp.setSideMultiLegReportingType(MultiLegReportingType.MultiLegSecurity);
        
        grp.setNoContAmts(2);
        ContAmtGroup43TestData.getInstance().populate1(grp.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().populate2(grp.getContAmtGroups()[1]);
        
        grp.setStipulations();
        Stipulations50TestData.getInstance().populate(grp.getStipulations());
        
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
        
        grp.setNoSideTrdRegTS(2);
        SideTrdRegTimestamps50TestData.getInstance().populate1(grp.getSideTrdRegTimestampsGroups()[0]);
        SideTrdRegTimestamps50TestData.getInstance().populate2(grp.getSideTrdRegTimestampsGroups()[1]);
        
        grp.setNoSettlDetails(2);
        SettlDetailsGroup50SP1TestData.getInstance().populate1(grp.getSettlDetailsGroups()[0]);
        SettlDetailsGroup50SP1TestData.getInstance().populate2(grp.getSettlDetailsGroups()[1]);
        
        grp.setSideGrossTradeAmt(66.22d);
        grp.setAggressorIndicator(Boolean.FALSE);
        grp.setExchangeSpecialInstructions("EXC_RULE_1");
        grp.setOrderCategory(OrderCategory.Order);
        grp.setSideLiquidityInd(FillLiquidityInd.AddedLiquidity);
        
        grp.setTradeReportOrderDetail();
        TradeReportOrderDetail50SP2TestData.getInstance().populate(grp.getTradeReportOrderDetail());
    }

    public void populate2(TrdCapRptSideGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setSide(Side.Sell);
        grp.setSideExecID("EXEC_87777");
        grp.setOrderDelay(2);
        grp.setOrderDelayUnit(OrderDelayUnit.Hours);
        grp.setOrderID("ORD_2222");
        grp.setSideLastQty(36);
        grp.setSideTradeReportID("SIDE_TRD_RPT_3333");
        grp.setSideFillStationCd("ST2");
        grp.setSideReasonCd("REASON_2");
        grp.setRptSeq(4);
        grp.setSideTrdSubTyp(TrdSubType.AdvisoryForContraSide);
        
        grp.setParties();
        Parties50SP2TestData.getInstance().populate(grp.getParties());
        
        grp.setAccount("7546354222");
        grp.setAcctIDSource(AcctIDSource.BIC);
        grp.setAccountType(AccountType.CustomerSide);
        grp.setProcessCode(ProcessCode.SoftDollar);
        grp.setOddLot(Boolean.TRUE);
        
        grp.setNoClearingInstructions(2);
        ClrInstGroup43TestData.getInstance().populate1(grp.getClrInstGroups()[0]);
        ClrInstGroup43TestData.getInstance().populate2(grp.getClrInstGroups()[1]);
        
        grp.setTradeInputSource("TRD_INP_SRC1");
        grp.setTradeInputDevice("TRD_INP_DEV1");
        grp.setComplianceID("COMPL_77777");
        grp.setSolicitedFlag(Boolean.FALSE);
        grp.setCustOrderCapacity(CustOrderCapacity.ClearingFirmTrading);
        grp.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Opening.getValue());
        grp.setTimeBracket("BRKT2");
        
        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());
        
        grp.setNumDaysInterest(2);
        cal.set(2011, 10, 11, 8, 12, 22);
        grp.setExDate(cal.getTime());
        grp.setAccruedInterestRate(13.6d);
        grp.setAccruedInterestAmt(57.3d);
        grp.setInterestAtMaturity(35.77d);
        grp.setEndAccruedInterestAmt(36.88d);
        grp.setStartCash(29.55d);
        grp.setEndCash(39.66d);
        grp.setConcession(2.2d);
        grp.setTotalTakedown(34.6d);
        grp.setNetMoney(568.9d);
        grp.setSettlCurrAmt(450.46d);
        grp.setSettlCurrFxRate(25.9d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Divide);
        grp.setPositionEffect(PositionEffect.Open);
        grp.setText("Some text 2");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 34, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 200, (byte) 253};
        grp.setEncodedText(encText);
        grp.setSideMultiLegReportingType(MultiLegReportingType.SingleSecurity);
        
        grp.setNoContAmts(2);
        ContAmtGroup43TestData.getInstance().populate1(grp.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().populate2(grp.getContAmtGroups()[1]);
        
        grp.setStipulations();
        Stipulations50TestData.getInstance().populate(grp.getStipulations());
        
        grp.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);
        
        grp.setExchangeRule("RULE2");
        grp.setTradeAllocIndicator(TradeAllocIndicator.AllocNotRequired);
        grp.setPreallocMethod(PreallocMethod.DiscussFirst);
        grp.setAllocID("ALLOC_2");
        
        grp.setNoAllocs(2);
        TradeAllocGroup50TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        TradeAllocGroup50TestData.getInstance().populate2(grp.getAllocGroups()[1]);
        
        grp.setNoSideTrdRegTS(2);
        SideTrdRegTimestamps50TestData.getInstance().populate1(grp.getSideTrdRegTimestampsGroups()[0]);
        SideTrdRegTimestamps50TestData.getInstance().populate2(grp.getSideTrdRegTimestampsGroups()[1]);
               
        grp.setNoSettlDetails(2);
        SettlDetailsGroup50SP1TestData.getInstance().populate1(grp.getSettlDetailsGroups()[0]);
        SettlDetailsGroup50SP1TestData.getInstance().populate2(grp.getSettlDetailsGroups()[1]);

        grp.setSideGrossTradeAmt(66.55d);
        grp.setAggressorIndicator(Boolean.TRUE);
        grp.setExchangeSpecialInstructions("EXC_RULE_2");
        grp.setOrderCategory(OrderCategory.Quote);
        grp.setSideLiquidityInd(FillLiquidityInd.Auction);
        
        grp.setTradeReportOrderDetail();
        TradeReportOrderDetail50SP2TestData.getInstance().populate(grp.getTradeReportOrderDetail());
    }

    public void check(TrdCapRptSideGroup expected, TrdCapRptSideGroup actual) throws Exception {
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getSideExecID(), actual.getSideExecID());
        assertEquals(expected.getOrderDelay(), actual.getOrderDelay());
        assertEquals(expected.getOrderDelayUnit(), actual.getOrderDelayUnit());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSideLastQty(), actual.getSideLastQty());
        assertEquals(expected.getSideTradeReportID(), actual.getSideTradeReportID());
        assertEquals(expected.getSideFillStationCd(), actual.getSideFillStationCd());
        assertEquals(expected.getSideReasonCd(), actual.getSideReasonCd());
        assertEquals(expected.getRptSeq(), actual.getRptSeq());
        assertEquals(expected.getSideTrdSubTyp(), actual.getSideTrdSubTyp());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
                
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getOddLot(), actual.getOddLot());
        
        assertEquals(expected.getNoClearingInstructions(), actual.getNoClearingInstructions());
        ClrInstGroup43TestData.getInstance().check(expected.getClrInstGroups()[0], actual.getClrInstGroups()[0]);
        ClrInstGroup43TestData.getInstance().check(expected.getClrInstGroups()[1], actual.getClrInstGroups()[1]);
        
        assertEquals(expected.getTradeInputSource(), actual.getTradeInputSource());
        assertEquals(expected.getTradeInputDevice(), actual.getTradeInputDevice());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getCustOrderCapacity(), actual.getCustOrderCapacity());
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
        assertEquals(expected.getSettlCurrFxRate(), actual.getSettlCurrFxRate());
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getSideMultiLegReportingType(), actual.getSideMultiLegReportingType());
        
        assertEquals(expected.getNoContAmts(), actual.getNoContAmts());
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[0], actual.getContAmtGroups()[0]);
        ContAmtGroup43TestData.getInstance().check(expected.getContAmtGroups()[1], actual.getContAmtGroups()[1]);
        
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        
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
        
        assertEquals(expected.getNoSideTrdRegTS(), actual.getNoSideTrdRegTS());
        SideTrdRegTimestamps50TestData.getInstance().check(expected.getSideTrdRegTimestampsGroups()[0], actual.getSideTrdRegTimestampsGroups()[0]);
        SideTrdRegTimestamps50TestData.getInstance().check(expected.getSideTrdRegTimestampsGroups()[1], actual.getSideTrdRegTimestampsGroups()[1]);
        
        assertEquals(expected.getNoSettlDetails(), actual.getNoSettlDetails());
        SettlDetailsGroup50SP1TestData.getInstance().check(expected.getSettlDetailsGroups()[0], actual.getSettlDetailsGroups()[0]);
        SettlDetailsGroup50SP1TestData.getInstance().check(expected.getSettlDetailsGroups()[1], actual.getSettlDetailsGroups()[1]);
        
        assertEquals(expected.getSideGrossTradeAmt(), actual.getSideGrossTradeAmt());
        assertEquals(expected.getAggressorIndicator(), actual.getAggressorIndicator());
        assertEquals(expected.getExchangeSpecialInstructions(), actual.getExchangeSpecialInstructions());
        assertEquals(expected.getOrderCategory(), actual.getOrderCategory());
        assertEquals(expected.getSideLiquidityInd(), actual.getSideLiquidityInd());
        
        TradeReportOrderDetail50SP2TestData.getInstance().check(expected.getTradeReportOrderDetail(), actual.getTradeReportOrderDetail());
    }
}
