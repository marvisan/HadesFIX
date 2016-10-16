/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRespComponentGroup44TestData.java
 *
 * $Id: BidRespComponentGroup44TestData.java,v 1.1 2011-04-14 11:44:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.group.BidRespComponentGroup;
import net.hades.fix.message.type.Country;
import net.hades.fix.message.type.NetGrossInd;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for BidRespComponentGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class BidRespComponentGroup44TestData extends MsgTest {

    private static final BidRespComponentGroup44TestData INSTANCE;

    static {
        INSTANCE = new BidRespComponentGroup44TestData();
    }

    public static BidRespComponentGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(BidRespComponentGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setListID("LIST_ID_6677");
        grp.setCountry(Country.Canada);
        grp.setSide(Side.Buy);
        grp.setPrice(35.5);
        grp.setPriceType(PriceType.Percentage);
        grp.setFairValue(34.2);
        grp.setNetGrossInd(NetGrossInd.Gross);
        grp.setSettlType(SettlType.NextDay.getValue());
        cal.set(2011, 3, 14, 12, 15, 33);
        grp.setSettlDate(cal.getTime());
        grp.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Opening.getValue());
        grp.setText("Some text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void populate2(BidRespComponentGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();

        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setListID("LIST_ID_8899");
        grp.setCountry(Country.USA);
        grp.setSide(Side.Sell);
        grp.setPrice(35.8);
        grp.setPriceType(PriceType.FixedAmount);
        grp.setFairValue(34.6);
        grp.setNetGrossInd(NetGrossInd.Net);
        grp.setSettlType(SettlType.Cash.getValue());
        cal.set(2011, 3, 15, 22, 15, 33);
        grp.setSettlDate(cal.getTime());
        grp.setTradingSessionID(TradingSessionID.Day.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        grp.setText("Some text 2");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 44, (byte) 11, (byte) 25,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void check(BidRespComponentGroup expected, BidRespComponentGroup actual) throws Exception {

        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());

        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getNetGrossInd(), actual.getNetGrossInd());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
