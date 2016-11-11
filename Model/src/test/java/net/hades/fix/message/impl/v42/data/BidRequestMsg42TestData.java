/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRequestMsg42TestData.java
 *
 * $Id: BidRequestMsg42TestData.java,v 1.3 2011-10-29 09:42:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.BidRequestMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v42.BidReqComponentGroup42TestData;
import net.hades.fix.message.group.impl.v42.BidReqDescriptorGroup42TestData;
import net.hades.fix.message.type.BasisPxType;
import net.hades.fix.message.type.BidRequestTransType;
import net.hades.fix.message.type.BidTradeType;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IncTaxInd;
import net.hades.fix.message.type.LiquidityIndType;
import net.hades.fix.message.type.ProgRptReqs;

/**
 * Test utility for BidRequestMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class BidRequestMsg42TestData extends MsgTest {

    private static final BidRequestMsg42TestData INSTANCE;

    static {
        INSTANCE = new BidRequestMsg42TestData();
    }

    public static BidRequestMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(BidRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setBidID("BIDID5555722");
        msg.setClientBidID("CLIBID7364844");
        msg.setBidRequestTransType(BidRequestTransType.Cancel);
        msg.setListName("main list");
        msg.setTotNoRelatedSym(3);
        msg.setBidType(BidType.Disclosed);
        msg.setNumTickets(2);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setSideValue1(22.44);
        msg.setSideValue2(25.88);

        msg.setNoBidDescriptors(2);
        BidReqDescriptorGroup42TestData.getInstance().populate1(msg.getBidDescriptorGroups()[0]);
        BidReqDescriptorGroup42TestData.getInstance().populate2(msg.getBidDescriptorGroups()[1]);

        msg.setNoBidComponents(2);
        BidReqComponentGroup42TestData.getInstance().populate1(msg.getBidComponentGroups()[0]);
        BidReqComponentGroup42TestData.getInstance().populate2(msg.getBidComponentGroups()[1]);

        msg.setLiquidityIndType(LiquidityIndType.NormalMarketSize);
        msg.setWtAverageLiquidity(23.55);
        msg.setExchangeForPhysical(Boolean.FALSE);
        msg.setOutMainCntryUIndex(44.55);
        msg.setCrossPercent(44.55);
        msg.setProgRptReqs(ProgRptReqs.BuySide);
        msg.setProgPeriodInterval(3);
        msg.setIncTaxInd(IncTaxInd.Net);
        msg.setForexReq(Boolean.TRUE);
        msg.setNumBidders(3);
        cal.set(2011, 5, 12, 12, 15, 33);
        msg.setTradeDate(cal.getTime());
        msg.setBidTradeType(BidTradeType.RiskTrade);
        msg.setBasisPxType(BasisPxType.ClosingPrice);
        cal.set(2011, 5, 12, 13, 23, 40);
        msg.setStrikeTime(cal.getTime());
        msg.setText("Some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void check(BidRequestMsg expected, BidRequestMsg actual) throws Exception {
        assertEquals(expected.getBidID(), actual.getBidID());
        assertEquals(expected.getClientBidID(), actual.getClientBidID());
        assertEquals(expected.getBidRequestTransType(), actual.getBidRequestTransType());
        assertEquals(expected.getListName(), actual.getListName());
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
        assertEquals(expected.getBidType(), actual.getBidType());
        assertEquals(expected.getNumTickets(), actual.getNumTickets());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSideValue1(), actual.getSideValue1());
        assertEquals(expected.getSideValue2(), actual.getSideValue2());
        
        assertEquals(expected.getNoBidDescriptors(), actual.getNoBidDescriptors());
        BidReqDescriptorGroup42TestData.getInstance().check(expected.getBidDescriptorGroups()[0], actual.getBidDescriptorGroups()[0]);
        BidReqDescriptorGroup42TestData.getInstance().check(expected.getBidDescriptorGroups()[1], actual.getBidDescriptorGroups()[1]);

        assertEquals(expected.getNoBidComponents(), actual.getNoBidComponents());
        BidReqComponentGroup42TestData.getInstance().check(expected.getBidComponentGroups()[0], actual.getBidComponentGroups()[0]);
        BidReqComponentGroup42TestData.getInstance().check(expected.getBidComponentGroups()[1], actual.getBidComponentGroups()[1]);

        assertEquals(expected.getLiquidityIndType(), actual.getLiquidityIndType());
        assertEquals(expected.getWtAverageLiquidity(), actual.getWtAverageLiquidity());
        assertEquals(expected.getExchangeForPhysical(), actual.getExchangeForPhysical());
        assertEquals(expected.getOutMainCntryUIndex(), actual.getOutMainCntryUIndex());
        assertEquals(expected.getCrossPercent(), actual.getCrossPercent());
        assertEquals(expected.getProgRptReqs(), actual.getProgRptReqs());
        assertEquals(expected.getProgPeriodInterval(), actual.getProgPeriodInterval());
        assertEquals(expected.getIncTaxInd(), actual.getIncTaxInd());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getNumBidders(), actual.getNumBidders());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getBidTradeType(), actual.getBidTradeType());
        assertEquals(expected.getBasisPxType(), actual.getBasisPxType());
        assertUTCTimestampEquals(expected.getStrikeTime(), actual.getStrikeTime(), false);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
