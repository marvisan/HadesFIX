/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SideCrossOrdCxlGroup50SP2TestData.java
 *
 * $Id: SideCrossOrdCxlGroup50SP2TestData.java,v 1.1 2011-05-21 23:53:23 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.group.SideCrossOrdCxlGroup;
import net.hades.fix.message.type.Side;

/**
 * Test utility for SideCrossOrdCxlGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/05/2011, 10:08:38 AM
 */
public class SideCrossOrdCxlGroup50SP2TestData extends MsgTest {

    private static final SideCrossOrdCxlGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new SideCrossOrdCxlGroup50SP2TestData();
    }

    public static SideCrossOrdCxlGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SideCrossOrdCxlGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSide(Side.Buy);
        grp.setOrigClOrdID("ORIG_334456");
        grp.setClOrdID("CLORD53773");
        grp.setSecondaryClOrdID("BBB363744");
        grp.setClOrdLinkID("LINK_998877");

        grp.setParties();
        Parties50SP2TestData.getInstance().populate(grp.getParties());

        cal.set(2010, 3, 14, 12, 13, 13);
        grp.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 15, 12, 13, 13);
        grp.setTradeDate(cal.getTime());

        grp.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(grp.getOrderQtyData());

        grp.setComplianceID("CPL_009900");
        grp.setText("text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
    }

    public void populate2(SideCrossOrdCxlGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSide(Side.Sell);
        grp.setOrigClOrdID("ORIG_334499");
        grp.setClOrdID("CLORD53788");
        grp.setSecondaryClOrdID("BBB363755");
        grp.setClOrdLinkID("LINK_334455");

        grp.setParties();
        Parties50SP2TestData.getInstance().populate(grp.getParties());

        cal.set(2010, 3, 16, 12, 13, 13);
        grp.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 17, 12, 13, 13);
        grp.setTradeDate(cal.getTime());

        grp.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(grp.getOrderQtyData());

        grp.setComplianceID("CPL_223388");
        grp.setText("text 2");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 44, (byte) 49, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
    }

    public void check(SideCrossOrdCxlGroup expected, SideCrossOrdCxlGroup actual) throws Exception {
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());

        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());

        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
