/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDReqGroup42TestData.java
 *
 * $Id: MDFullGroup42TestData.java,v 1.4 2011-10-29 09:42:27 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.MDFullGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.OpenCloseSettlFlag;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;

/**
 * Test utility for MDFullGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDFullGroup42TestData extends MsgTest {

    private static final MDFullGroup42TestData INSTANCE;

    static {
        INSTANCE = new MDFullGroup42TestData();
    }

    public static MDFullGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MDFullGroup msg) throws UnsupportedEncodingException {
        msg.setMdEntryType(MDEntryType.Bid);
        msg.setMdEntryPx(new Double(22.22));
        msg.setCurrency(Currency.AustralianDollar);
        msg.setMdEntrySize(new Double(10.5));
        msg.setMdEntryDate(new Date());
        msg.setMdEntryTime(new Date());
        msg.setTickDirection(TickDirection.MinusTick);
        msg.setMdMkt("Market 1");
        msg.setTradingSessionID("SS53466333");
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
        msg.setText("Some text 1");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void populate2(MDFullGroup msg) throws UnsupportedEncodingException {
        msg.setMdEntryType(MDEntryType.CashRate);
        msg.setMdEntryPx(new Double(44.22));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setMdEntrySize(new Double(13.5));
        msg.setMdEntryDate(new Date());
        msg.setMdEntryTime(new Date());
        msg.setTickDirection(TickDirection.PlusTick);
        msg.setMdMkt("Market 2");
        msg.setTradingSessionID("SS53466344");
        msg.setQuoteCondition(QuoteCondition.AdditionalInfo);
        msg.setTradeCondition(TradeCondition.Adjusted);
        msg.setMdEntryOriginator("Orig 2");
        msg.setLocationID("Location 2");
        msg.setDeskID("Desk 2");
        msg.setOpenCloseSettlFlag(String.valueOf(OpenCloseSettlFlag.DeliverySettlement.getValue()));
        msg.setTimeInForce(TimeInForce.Day);
        msg.setExpireDate(new Date());
        msg.setExpireTime(new Date());
        msg.setMinQty(new Double(30.88));
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setSellerDays(new Integer(3));
        msg.setOrderID("SS73947477");
        msg.setQuoteEntryID("DD83748377");
        msg.setMdEntryBuyer("FSDA55577");
        msg.setMdEntrySeller("XX7394777");
        msg.setNumberOfOrders(new Integer(12));
        msg.setMdEntryPositionNo(new Integer(55));
        msg.setText("Some text 2");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 14, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void check(MDFullGroup expected, MDFullGroup actual) throws Exception {
        assertEquals(expected.getMdEntryType(), actual.getMdEntryType());
        assertEquals(expected.getMdEntryPx(), actual.getMdEntryPx());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getMdEntrySize(), actual.getMdEntrySize());
        assertUTCDateEquals(expected.getMdEntryDate(), actual.getMdEntryDate());
        assertUTCTimeEquals(expected.getMdEntryTime(), actual.getMdEntryTime(), false);
        assertEquals(expected.getTickDirection(), actual.getTickDirection());
        assertEquals(expected.getMdMkt(), actual.getMdMkt());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
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
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertEquals(expected.getNumberOfOrders(), actual.getNumberOfOrders());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
