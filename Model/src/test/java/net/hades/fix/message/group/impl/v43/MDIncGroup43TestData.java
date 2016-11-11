/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDIncGroup43TestData.java
 *
 * $Id: MDIncGroup43TestData.java,v 1.2 2011-10-29 09:42:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
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
 * Test utility for MDIncGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDIncGroup43TestData extends MsgTest {

    private static final MDIncGroup43TestData INSTANCE;

    static {
        INSTANCE = new MDIncGroup43TestData();
    }

    public static MDIncGroup43TestData getInstance() {
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
        Instrument43TestData.getInstance().populate(msg.getInstrument());
        
        msg.setFinancialStatus("fin stat 1");
        msg.setCorporateAction("corp act");
        msg.setMdEntryPx(new Double(22.22));
        msg.setCurrency(Currency.AustralianDollar);
        msg.setMdEntrySize(new Double(2.22));
        msg.setMdEntryDate(new Date());
        msg.setMdEntryTime(new Date());
        msg.setTickDirection(TickDirection.MinusTick);
        msg.setMdMkt("Market 1");
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setQuoteCondition(QuoteCondition.AdditionalInfo);
        msg.setTradeCondition(TradeCondition.Adjusted);
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
        msg.setScope("Any");
        msg.setTotalVolumeTraded(23.55d);
        msg.setTotalVolumeTradedDate(new Date());
        msg.setTotalVolumeTradedTime(new Date());
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
        msg.setMdEntryType(MDEntryType.CashRate);
        msg.setMdEntryID("entry 2");
        msg.setMdEntryRefID("entry ref 2");
        // Instrument
        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setFinancialStatus("fin stat 2");
        msg.setCorporateAction("corp act 1");
        msg.setMdEntryPx(new Double(22.33));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setMdEntrySize(new Double(2.44));
        msg.setMdEntryDate(new Date());
        msg.setMdEntryTime(new Date());
        msg.setTickDirection(TickDirection.ZeroMinusTick);
        msg.setMdMkt("Market 2");
        msg.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
        msg.setQuoteCondition(QuoteCondition.BetterPrices);
        msg.setTradeCondition(TradeCondition.Bunched);
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
        msg.setScope("Other");
        msg.setTotalVolumeTraded(23.55d);
        msg.setTotalVolumeTradedDate(new Date());
        msg.setTotalVolumeTradedTime(new Date());
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
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

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
        assertEquals(expected.getTotalVolumeTraded().doubleValue(), actual.getTotalVolumeTraded().doubleValue(), 0.001);
        assertDateEquals(expected.getTotalVolumeTradedDate(), actual.getTotalVolumeTradedDate());
        assertUTCTimeEquals(expected.getTotalVolumeTradedTime(), actual.getTotalVolumeTradedTime(), false);
        assertEquals(expected.getNetChgPrevDay().doubleValue(), actual.getNetChgPrevDay().doubleValue(), 0.001);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
