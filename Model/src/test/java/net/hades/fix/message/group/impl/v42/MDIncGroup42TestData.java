/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDIncGroup42TestData.java
 *
 * $Id: MDIncGroup42TestData.java,v 1.3 2011-10-29 09:42:26 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.MDIncGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DeleteReason;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDUpdateAction;
import net.hades.fix.message.type.OpenCloseSettlFlag;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.QuoteCondition;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.TickDirection;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradeCondition;

/**
 * Test utility for MDIncGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDIncGroup42TestData extends MsgTest {

    private static final MDIncGroup42TestData INSTANCE;

    static {
        INSTANCE = new MDIncGroup42TestData();
    }

    public static MDIncGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MDIncGroup msg) throws UnsupportedEncodingException {
        msg.setMdUpdateAction(MDUpdateAction.Change);
        msg.setDeleteReason(DeleteReason.Cancellation);
        msg.setMdEntryType(MDEntryType.Bid);
        msg.setMdEntryID("entry 1");
        msg.setMdEntryRefID("entry ref 1");
        msg.setSymbol("symbol");
        msg.setSymbolSfx("symbol sfx");
        msg.setSecurityID("sec ID");
        msg.setSecurityIDSource("sec ID source");
        msg.setSecurityType(SecurityType.AmendedRestated.getValue());
        msg.setMaturityMonthYear("082009");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(12.456));
        msg.setOptAttribute(new Character('Z'));
        msg.setContractMultiplier(new Double(23.44));
        msg.setCouponRate(new Double(12.999));
        msg.setSecurityExchange("NYSE");
        msg.setIssuer("ISSUER");
        msg.setEncodedIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedIssuer(encLegIssuer);
        msg.setSecurityDesc("sec desc");
        msg.setEncodedSecurityDescLen(new Integer(6));
        byte[] encEncSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedSecurityDesc(encEncSecDesc);
        msg.setFinancialStatus("fin stat 1");
        msg.setCorporateAction("corp act");
        msg.setMdEntryPx(new Double(22.22));
        msg.setCurrency(Currency.AustralianDollar);
        msg.setMdEntrySize(new Double(2.22));
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
        msg.setTotalVolumeTraded(23.55d);
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
        msg.setSymbol("symbol 1");
        msg.setSymbolSfx("symbol sfx 1");
        msg.setSecurityID("sec ID 1");
        msg.setSecurityIDSource("sec ID source 1");
        msg.setSecurityType(SecurityType.BankDepositoryNote.getValue());
        msg.setMaturityMonthYear("082010");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Put);
        msg.setStrikePrice(new Double(12.45));
        msg.setOptAttribute(new Character('X'));
        msg.setContractMultiplier(new Double(23.66));
        msg.setCouponRate(new Double(12.666));
        msg.setSecurityExchange("CBOT");
        msg.setIssuer("ISSUER 1");
        msg.setEncodedIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 12, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedIssuer(encLegIssuer);
        msg.setSecurityDesc("sec desc");
        msg.setEncodedSecurityDescLen(new Integer(6));
        byte[] encEncSecDesc = new byte[] {(byte) 29, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedSecurityDesc(encEncSecDesc);
        msg.setFinancialStatus("fin stat 2");
        msg.setCorporateAction("corp act 1");
        msg.setMdEntryPx(new Double(22.33));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setMdEntrySize(new Double(2.44));
        msg.setMdEntryDate(new Date());
        msg.setMdEntryTime(new Date());
        msg.setTickDirection(TickDirection.ZeroMinusTick);
        msg.setMdMkt("Market 2");
        msg.setTradingSessionID("SS53466344");
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
        msg.setTotalVolumeTraded(23.55d);
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
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().intValue(), actual.getMaturityDay().intValue());
        assertEquals(expected.getPutOrCall().getValue(), actual.getPutOrCall().getValue());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getOptAttribute().charValue());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
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
        assertEquals(expected.getTotalVolumeTraded().doubleValue(), actual.getTotalVolumeTraded().doubleValue(), 0.001);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
