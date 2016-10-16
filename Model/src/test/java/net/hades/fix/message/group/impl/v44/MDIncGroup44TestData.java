/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDIncGroup44TestData.java
 *
 * $Id: MDIncGroup44TestData.java,v 1.2 2011-10-29 09:42:09 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.MDIncGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DeleteReason;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDUpdateAction;
import net.hades.fix.message.type.OpenCloseSettlFlag;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for MDIncGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDIncGroup44TestData extends MsgTest {

    private static final MDIncGroup44TestData INSTANCE;

    static {
        INSTANCE = new MDIncGroup44TestData();
    }

    public static MDIncGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MDIncGroup msg) throws UnsupportedEncodingException {
        msg.setMdUpdateAction(MDUpdateAction.Change);
        msg.setDeleteReason(DeleteReason.Cancellation);
        msg.setMdEntryType(MDEntryType.Bid);
        msg.setMdEntryID("entry 1");
        msg.setMdEntryRefID("entry ref 1");
        // Instrument
        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        
        msg.setFinancialStatus("1");
        msg.setCorporateAction("A");
        msg.setMdEntryPx(new Double(22.22));
        msg.setCurrency(Currency.AustralianDollar);
        msg.setMdEntrySize(new Double(2.22));
        msg.setMdEntryDate(new Date());
        msg.setMdEntryTime(new Date());
        msg.setTickDirection(TickDirection.MinusTick);
        msg.setMdMkt("Market 1");
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setQuoteCondition(QuoteCondition.Open);
        msg.setTradeCondition(TradeCondition.CashMarket);
        msg.setMdEntryOriginator("Orig 1");
        msg.setLocationID("Location 1");
        msg.setDeskID("Desk 1");
        msg.setOpenCloseSettlFlag(String.valueOf(OpenCloseSettlFlag.DailyOpen.getValue()));
        msg.setTimeInForce(TimeInForce.AtTheClosing);
        msg.setExpireDate(new Date());
        msg.setExpireTime(new Date());
        msg.setMinQty(new Double(30.5));
        msg.setExecInst(ExecInst.DNI.getValue());
        msg.setSellerDays(new Integer(2));
        msg.setOrderID("SS73947441");
        msg.setQuoteEntryID("DD83748394");
        msg.setMdEntryBuyer("FSDA55555");
        msg.setMdEntrySeller("XX7394733");
        msg.setNumberOfOrders(new Integer(23));
        msg.setMdEntryPositionNo(new Integer(22));
        msg.setScope("1");
        msg.setPriceDelta(12.34);
        msg.setNetChgPrevDay(22.2d);
        msg.setText("Some text 1");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void populate2(MDIncGroup msg) throws UnsupportedEncodingException {
        msg.setMdUpdateAction(MDUpdateAction.Delete);
        msg.setDeleteReason(DeleteReason.Error);
        msg.setMdEntryType(MDEntryType.Offer);
        msg.setMdEntryID("entry 2");
        msg.setMdEntryRefID("entry ref 2");
        // Instrument
        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setFinancialStatus("2");
        msg.setCorporateAction("B");
        msg.setMdEntryPx(new Double(22.33));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setMdEntrySize(new Double(2.44));
        msg.setMdEntryDate(new Date());
        msg.setMdEntryTime(new Date());
        msg.setTickDirection(TickDirection.ZeroMinusTick);
        msg.setMdMkt("Market 2");
        msg.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
        msg.setQuoteCondition(QuoteCondition.Closed);
        msg.setTradeCondition(TradeCondition.AveragePriceTrade);
        msg.setMdEntryOriginator("Orig 2");
        msg.setLocationID("Location 2");
        msg.setDeskID("Desk 2");
        msg.setOpenCloseSettlFlag(String.valueOf(OpenCloseSettlFlag.ExpectedEntry.getValue()));
        msg.setTimeInForce(TimeInForce.FillOrKill);
        msg.setExpireDate(new Date());
        msg.setExpireTime(new Date());
        msg.setMinQty(new Double(30.8));
        msg.setExecInst(ExecInst.DNI.getValue());
        msg.setSellerDays(new Integer(2));
        msg.setOrderID("SS73947443");
        msg.setQuoteEntryID("DD83748395");
        msg.setMdEntryBuyer("FSDA55555");
        msg.setMdEntrySeller("XX7394737");
        msg.setNumberOfOrders(new Integer(20));
        msg.setMdEntryPositionNo(new Integer(20));
        msg.setScope("2");
        msg.setTotalVolumeTraded(23.55d);
        msg.setTotalVolumeTradedDate(new Date());
        msg.setTotalVolumeTradedTime(new Date());
        msg.setPriceDelta(12.67);
        msg.setNetChgPrevDay(42.2d);
        msg.setText("Some text 2");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 16, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void check(MDIncGroup expected, MDIncGroup actual) throws Exception {
        assertEquals(expected.getMdUpdateAction(), actual.getMdUpdateAction());
        assertEquals(expected.getDeleteReason(), actual.getDeleteReason());
        assertEquals(expected.getMdEntryType(), actual.getMdEntryType());
        assertEquals(expected.getMdEntryID(), actual.getMdEntryID());
        assertEquals(expected.getMdEntryRefID(), actual.getMdEntryRefID());
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // UnderlyingInstrument
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
        assertEquals(expected.getMdEntryPx(), actual.getMdEntryPx());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getMdEntrySize(), actual.getMdEntrySize());
        assertDateEquals(expected.getMdEntryDate(), actual.getMdEntryDate());
        assertUTCTimeEquals(expected.getMdEntryTime(), actual.getMdEntryTime(), false);
        assertEquals(expected.getTickDirection(), actual.getTickDirection());
        assertEquals(expected.getMdMkt(), actual.getMdMkt());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getQuoteCondition(), actual.getQuoteCondition());
        assertEquals(expected.getTradeCondition(), actual.getTradeCondition());
        assertEquals(expected.getMdEntryOriginator(), actual.getMdEntryOriginator());
        assertEquals(expected.getLocationID(), actual.getLocationID());
        assertEquals(expected.getDeskID(), actual.getDeskID());
        assertEquals(expected.getOpenCloseSettlFlag(), actual.getOpenCloseSettlFlag());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertUTCTimeEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getMinQty(), actual.getMinQty(), 0.01);
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getSellerDays(), actual.getSellerDays());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getQuoteEntryID(), actual.getQuoteEntryID());
        assertEquals(expected.getMdEntrySeller(), actual.getMdEntrySeller());
        assertEquals(expected.getNumberOfOrders(), actual.getNumberOfOrders());
        assertEquals(expected.getMdEntryPositionNo(), actual.getMdEntryPositionNo());
        assertEquals(expected.getScope(), actual.getScope());
        assertEquals(expected.getPriceDelta().doubleValue(), actual.getPriceDelta().doubleValue(), 0.001);
        assertEquals(expected.getNetChgPrevDay().doubleValue(), actual.getNetChgPrevDay().doubleValue(), 0.001);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
