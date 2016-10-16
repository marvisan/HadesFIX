/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionMsg44TestData.java
 *
 * $Id: AllocationInstructionMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationInstructionMsg;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.comp.impl.v44.YieldData44TestData;
import net.hades.fix.message.group.impl.v44.AllocGroup44TestData;
import net.hades.fix.message.group.impl.v44.ExecAllocGroup44TestData;
import net.hades.fix.message.group.impl.v44.OrderAllocGroup44TestData;
import net.hades.fix.message.type.AllocCancReplaceReason;
import net.hades.fix.message.type.AllocIntermedReqType;
import net.hades.fix.message.type.AllocLinkType;
import net.hades.fix.message.type.AllocNoOrdersType;
import net.hades.fix.message.type.AllocTransType;
import net.hades.fix.message.type.AllocType;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for AllocationInstructionMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class AllocationInstructionMsg44TestData extends MsgTest {

    private static final AllocationInstructionMsg44TestData INSTANCE;

    static {
        INSTANCE = new AllocationInstructionMsg44TestData();
    }

    public static AllocationInstructionMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AllocationInstructionMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setAllocID("ALLOC775555");
        msg.setAllocTransType(AllocTransType.Calculated);
        msg.setAllocType(AllocType.Calculated);
        msg.setSecondaryAllocID("SECALL2222");
        msg.setRefAllocID("REF112233");
        msg.setAllocCancReplaceReason(AllocCancReplaceReason.OrigDetailsIncomplete);
        msg.setAllocIntermedReqType(AllocIntermedReqType.BlockLevelReject);
        msg.setAllocLinkID("LK223344");
        msg.setAllocLinkType(AllocLinkType.FXNetting);
        msg.setBookingRefID("BKREF2222");
        msg.setAllocNoOrdersType(AllocNoOrdersType.NotSpecified);

        msg.setNoOrders(2);
        OrderAllocGroup44TestData.getInstance().populate1(msg.getOrderAllocGroups()[0]);
        OrderAllocGroup44TestData.getInstance().populate2(msg.getOrderAllocGroups()[1]);

        msg.setNoExecs(2);
        ExecAllocGroup44TestData.getInstance().populate1(msg.getExecAllocGroups()[0]);
        ExecAllocGroup44TestData.getInstance().populate2(msg.getExecAllocGroups()[1]);

        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());

        msg.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(msg.getInstrumentExtension());

        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setSide(Side.Buy);
        msg.setQuantity(23.00d);
        msg.setLastMkt("MKT111");
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTradeOriginationDate(cal.getTime());
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setPriceType(PriceType.Percentage);
        msg.setAvgPx(12.2d);
        msg.setAvgParPx(43.22d);

        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setAvgPxPrecision(2);

        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());

        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 11, 13, 14, 22);
        msg.setSettlDate(cal.getTime());
        msg.setBookingType(BookingType.RegularBooking);
        msg.setGrossTradeAmt(43.22d);
        msg.setConcession(55.44d);
        msg.setTotalTakedown(12.77d);
        msg.setNetMoney(22.33d);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setAutoAcceptIndicator(Boolean.FALSE);
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setNumDaysInterest(2);
        msg.setAccruedInterestRate(12.5D);
        msg.setAccruedInterestAmt(25.77d);
        msg.setTotalAccruedInterestAmt(43.22d);
        msg.setInterestAtMaturity(54.77d);
        msg.setEndAccruedInterestAmt(66.22d);
        msg.setStartCash(33.88d);
        msg.setEndCash(55.88d);
        msg.setLegalConfirm(Boolean.TRUE);

        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());

        msg.setYieldData();
        YieldData44TestData.getInstance().populate(msg.getYieldData());

        msg.setTotNoAllocs(2);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoAllocs(2);
        AllocGroup44TestData.getInstance().populate1(msg.getAllocGroups()[0]);
        AllocGroup44TestData.getInstance().populate2(msg.getAllocGroups()[1]);
    }

    public void check(AllocationInstructionMsg expected, AllocationInstructionMsg actual) throws Exception {
        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getAllocTransType(), actual.getAllocTransType());
        assertEquals(expected.getAllocType(), actual.getAllocType());
        assertEquals(expected.getSecondaryAllocID(), actual.getSecondaryAllocID());
        assertEquals(expected.getRefAllocID(), actual.getRefAllocID());
        assertEquals(expected.getAllocCancReplaceReason(), actual.getAllocCancReplaceReason());
        assertEquals(expected.getAllocIntermedReqType(), actual.getAllocIntermedReqType());
        assertEquals(expected.getAllocLinkID(), actual.getAllocLinkID());
        assertEquals(expected.getAllocLinkType(), actual.getAllocLinkType());
        assertEquals(expected.getBookingRefID(), actual.getBookingRefID());
        assertEquals(expected.getAllocNoOrdersType(), actual.getAllocNoOrdersType());

        assertEquals(expected.getNoOrders(), actual.getNoOrders());
        OrderAllocGroup44TestData.getInstance().check(expected.getOrderAllocGroups()[0], actual.getOrderAllocGroups()[0]);
        OrderAllocGroup44TestData.getInstance().check(expected.getOrderAllocGroups()[1], actual.getOrderAllocGroups()[1]);

        assertEquals(expected.getNoExecs(), actual.getNoExecs());
        ExecAllocGroup44TestData.getInstance().check(expected.getExecAllocGroups()[0], actual.getExecAllocGroups()[0]);
        ExecAllocGroup44TestData.getInstance().check(expected.getExecAllocGroups()[1], actual.getExecAllocGroups()[1]);

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getAvgParPx(), actual.getAvgParPx());

        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getAvgPxPrecision(), actual.getAvgPxPrecision());

        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getBookingType(), actual.getBookingType());
        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
        assertEquals(expected.getConcession(), actual.getConcession());
        assertEquals(expected.getTotalTakedown(), actual.getTotalTakedown());
        assertEquals(expected.getNetMoney(), actual.getNetMoney());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getAutoAcceptIndicator(), actual.getAutoAcceptIndicator());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getNumDaysInterest(), actual.getNumDaysInterest());
        assertEquals(expected.getAccruedInterestRate(), actual.getAccruedInterestRate());
        assertEquals(expected.getAccruedInterestAmt(), actual.getAccruedInterestAmt());
        assertEquals(expected.getTotalAccruedInterestAmt(), actual.getTotalAccruedInterestAmt());
        assertEquals(expected.getInterestAtMaturity(), actual.getInterestAtMaturity());
        assertEquals(expected.getEndAccruedInterestAmt(), actual.getEndAccruedInterestAmt());
        assertEquals(expected.getStartCash(), actual.getStartCash());
        assertEquals(expected.getEndCash(), actual.getEndCash());
        assertEquals(expected.getLegalConfirm(), actual.getLegalConfirm());

        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getTotNoAllocs(), actual.getTotNoAllocs());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());

        assertEquals(expected.getNoAllocs(), actual.getNoAllocs());
        AllocGroup44TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        AllocGroup44TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);
    }
}
