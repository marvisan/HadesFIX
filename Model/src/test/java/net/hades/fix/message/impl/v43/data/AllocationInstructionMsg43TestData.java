/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionMsg43TestData.java
 *
 * $Id: AllocationInstructionMsg43TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationInstructionMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.group.impl.v43.AllocGroup43TestData;
import net.hades.fix.message.group.impl.v43.ExecAllocGroup43TestData;
import net.hades.fix.message.group.impl.v43.OrderAllocGroup43TestData;
import net.hades.fix.message.type.AllocLinkType;
import net.hades.fix.message.type.AllocTransType;
import net.hades.fix.message.type.AllocType;
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
public class AllocationInstructionMsg43TestData extends MsgTest {

    private static final AllocationInstructionMsg43TestData INSTANCE;

    static {
        INSTANCE = new AllocationInstructionMsg43TestData();
    }

    public static AllocationInstructionMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AllocationInstructionMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setAllocID("ALLOC775555");
        msg.setAllocTransType(AllocTransType.Calculated);
        msg.setAllocType(AllocType.Accept);
        msg.setRefAllocID("REF112233");
        msg.setAllocLinkID("LK223344");
        msg.setAllocLinkType(AllocLinkType.FXNetting);
        msg.setBookingRefID("BKREF2222");

        msg.setNoOrders(2);
        OrderAllocGroup43TestData.getInstance().populate1(msg.getOrderAllocGroups()[0]);
        OrderAllocGroup43TestData.getInstance().populate2(msg.getOrderAllocGroups()[1]);

        msg.setNoExecs(2);
        ExecAllocGroup43TestData.getInstance().populate1(msg.getExecAllocGroups()[0]);
        ExecAllocGroup43TestData.getInstance().populate2(msg.getExecAllocGroups()[1]);

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setSide(Side.Buy);
        msg.setQuantity(23.00d);
        msg.setLastMkt("MKT111");
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTradeOriginationDate(cal.getTime());
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setPriceType(PriceType.Percentage);
        msg.setAvgPx(12.2d);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setAvgPxPrecision(2);

        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 11, 13, 14, 22);
        msg.setSettlDate(cal.getTime());
        msg.setGrossTradeAmt(43.22d);
        msg.setConcession(55.44d);
        msg.setTotalTakedown(12.77d);
        msg.setNetMoney(22.33d);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setNumDaysInterest(2);
        msg.setAccruedInterestRate(12.5D);
        msg.setTotalAccruedInterestAmt(43.22d);
        msg.setLegalConfirm(Boolean.TRUE);

        msg.setNoAllocs(2);
        AllocGroup43TestData.getInstance().populate1(msg.getAllocGroups()[0]);
        AllocGroup43TestData.getInstance().populate2(msg.getAllocGroups()[1]);
    }

    public void check(AllocationInstructionMsg expected, AllocationInstructionMsg actual) throws Exception {
        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getAllocTransType(), actual.getAllocTransType());
        assertEquals(expected.getAllocType(), actual.getAllocType());
        assertEquals(expected.getRefAllocID(), actual.getRefAllocID());
        assertEquals(expected.getAllocLinkID(), actual.getAllocLinkID());
        assertEquals(expected.getAllocLinkType(), actual.getAllocLinkType());
        assertEquals(expected.getBookingRefID(), actual.getBookingRefID());

        assertEquals(expected.getNoOrders(), actual.getNoOrders());
        OrderAllocGroup43TestData.getInstance().check(expected.getOrderAllocGroups()[0], actual.getOrderAllocGroups()[0]);
        OrderAllocGroup43TestData.getInstance().check(expected.getOrderAllocGroups()[1], actual.getOrderAllocGroups()[1]);

        assertEquals(expected.getNoExecs(), actual.getNoExecs());
        ExecAllocGroup43TestData.getInstance().check(expected.getExecAllocGroups()[0], actual.getExecAllocGroups()[0]);
        ExecAllocGroup43TestData.getInstance().check(expected.getExecAllocGroups()[1], actual.getExecAllocGroups()[1]);

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getAvgPxPrecision(), actual.getAvgPxPrecision());

        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
        assertEquals(expected.getConcession(), actual.getConcession());
        assertEquals(expected.getTotalTakedown(), actual.getTotalTakedown());
        assertEquals(expected.getNetMoney(), actual.getNetMoney());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getNumDaysInterest(), actual.getNumDaysInterest());
        assertEquals(expected.getAccruedInterestRate(), actual.getAccruedInterestRate());
        assertEquals(expected.getTotalAccruedInterestAmt(), actual.getTotalAccruedInterestAmt());
        assertEquals(expected.getLegalConfirm(), actual.getLegalConfirm());

        assertEquals(expected.getNoAllocs(), actual.getNoAllocs());
        AllocGroup43TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        AllocGroup43TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);
    }
}
