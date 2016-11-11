/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmationMsg50SP2TestData.java
 *
 * $Id: ConfirmationMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ConfirmationMsg;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v44.SettlInstructionsData44TestData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.impl.v44.CpctyConfGroup44TestData;
import net.hades.fix.message.group.impl.v44.MiscFeeGroup44TestData;
import net.hades.fix.message.group.impl.v44.OrderAllocGroup44TestData;
import net.hades.fix.message.group.impl.v50.TrdRegTimestamps50TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocAccountType;
import net.hades.fix.message.type.ConfirmStatus;
import net.hades.fix.message.type.ConfirmTransType;
import net.hades.fix.message.type.ConfirmType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for ConfirmationMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ConfirmationMsg50SP2TestData extends MsgTest {

    private static final ConfirmationMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new ConfirmationMsg50SP2TestData();
    }

    public static ConfirmationMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ConfirmationMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setConfirmID("CONF_88888");
        msg.setConfirmRefID("CONF_REF_1111");
        msg.setConfirmReqID("CONF_REQ_5555");
        msg.setConfirmTransType(ConfirmTransType.Replace);
        msg.setConfirmType(ConfirmType.Confirmation);
        msg.setCopyMsgIndicator(Boolean.TRUE);
        msg.setLegalConfirm(Boolean.TRUE);
        msg.setConfirmStatus(ConfirmStatus.Confirmed);
        
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());

        msg.setNoOrders(2);
        OrderAllocGroup44TestData.getInstance().populate1(msg.getOrderAllocGroups()[0]);
        OrderAllocGroup44TestData.getInstance().populate2(msg.getOrderAllocGroups()[1]);

        msg.setAllocID("ALLOC775555");
        msg.setSecondaryAllocID("SECALL2222");
        msg.setIndividualAllocID("IND_ALLOC_0000");
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        
        msg.setNoTrdRegTimestamps(2);
        TrdRegTimestamps50TestData.getInstance().populate1(msg.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().populate2(msg.getTrdRegTimestampsGroups()[1]);
        
        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());

        msg.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(msg.getInstrumentExtension());

        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());

        msg.setAllocQty(24.33d);
        msg.setQtyType(QtyType.Units);
        msg.setSide(Side.Buy);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setLastMkt("MKT111");
        
        msg.setNoCapacities(2);
        CpctyConfGroup44TestData.getInstance().populate1(msg.getCpctyConfGroups()[0]);
        CpctyConfGroup44TestData.getInstance().populate2(msg.getCpctyConfGroups()[1]);

        msg.setAllocAccount("72634637632");
        msg.setAllocAcctIDSource(AcctIDSource.SID);
        msg.setAllocAccountType(AllocAccountType.FloorTrader);
        msg.setAvgPx(12.2d);
        msg.setAvgPxPrecision(2);
        msg.setPriceType(PriceType.Percentage);
        msg.setAvgParPx(43.22d);
        
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        
        msg.setReportedPx(53.23);
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setProcessCode(ProcessCode.Regular);
        msg.setGrossTradeAmt(43.22d);
        msg.setNumDaysInterest(2);
        cal.set(2011, 3, 12, 22, 33, 15);
        msg.setExDate(cal.getTime());
        msg.setAccruedInterestRate(12.33d);
        msg.setAccruedInterestAmt(456.33d);
        msg.setInterestAtMaturity(54.77d);
        msg.setEndAccruedInterestAmt(66.22d);
        msg.setStartCash(33.88d);
        msg.setEndCash(55.88d);
        msg.setConcession(55.44d);
        msg.setTotalTakedown(12.77d);
        msg.setNetMoney(22.33d);
        msg.setMaturityNetMoney(34.22d);
        msg.setSettlCurrAmt(24.66d);
        msg.setSettlCurrency(Currency.AustralianDollar);
        msg.setSettlCurrFxRate(1.11d);
        msg.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 11, 13, 14, 22);
        msg.setSettlDate(cal.getTime());
        
        msg.setSettlInstructionsData();
        SettlInstructionsData44TestData.getInstance().populate(msg.getSettlInstructionsData());

        msg.setCommissionData();
        CommissionData43TestData.getInstance().populate(msg.getCommissionData());
       
        msg.setSharedCommission(42.12d);

        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());

        msg.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(msg.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate1(msg.getMiscFeeGroups()[1]);
    }

    public void check(ConfirmationMsg expected, ConfirmationMsg actual) throws Exception {
        assertEquals(expected.getConfirmID(), actual.getConfirmID());
        assertEquals(expected.getConfirmRefID(), actual.getConfirmRefID());
        assertEquals(expected.getConfirmReqID(), actual.getConfirmReqID());
        assertEquals(expected.getConfirmTransType(), actual.getConfirmTransType());
        assertEquals(expected.getConfirmType(), actual.getConfirmType());
        assertEquals(expected.getCopyMsgIndicator(), actual.getCopyMsgIndicator());
        assertEquals(expected.getLegalConfirm(), actual.getLegalConfirm());
        assertEquals(expected.getConfirmStatus(), actual.getConfirmStatus());
        
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getNoOrders(), actual.getNoOrders());
        OrderAllocGroup44TestData.getInstance().check(expected.getOrderAllocGroups()[0], actual.getOrderAllocGroups()[0]);
        OrderAllocGroup44TestData.getInstance().check(expected.getOrderAllocGroups()[1], actual.getOrderAllocGroups()[1]);

        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getSecondaryAllocID(), actual.getSecondaryAllocID());
        assertEquals(expected.getIndividualAllocID(), actual.getIndividualAllocID());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        
        assertEquals(expected.getNoTrdRegTimestamps(), actual.getNoTrdRegTimestamps());
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[0], actual.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[1], actual.getTrdRegTimestampsGroups()[1]);
              
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getQtyType(), actual.getQtyType());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        
        assertEquals(expected.getNoCapacities(), actual.getNoCapacities());
        CpctyConfGroup44TestData.getInstance().check(expected.getCpctyConfGroups()[0], actual.getCpctyConfGroups()[0]);
        CpctyConfGroup44TestData.getInstance().check(expected.getCpctyConfGroups()[1], actual.getCpctyConfGroups()[1]);

        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocAccountType(), actual.getAllocAccountType());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getAvgPxPrecision(), actual.getAvgPxPrecision());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getAvgParPx(), actual.getAvgParPx());
        
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getReportedPx(), actual.getReportedPx());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
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
        assertEquals(expected.getMaturityNetMoney(), actual.getMaturityNetMoney());
        assertEquals(expected.getSettlCurrAmt(), actual.getSettlCurrAmt());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getSettlCurrFxRate(), actual.getSettlCurrFxRate());
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        
        SettlInstructionsData44TestData.getInstance().check(expected.getSettlInstructionsData(), actual.getSettlInstructionsData());
                
        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());
        
        assertEquals(expected.getSharedCommission(), actual.getSharedCommission());
        
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);
    }
}
