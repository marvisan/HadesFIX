/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtStrikePriceGroup50SP1TestData.java
 *
 * $Id: InstrmtStrikePriceGroup50SP1TestData.java,v 1.2 2011-10-29 09:42:24 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.InstrmtStrikePriceGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.Side;

/**
 * Test utility for InstrmtStrikePriceGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtStrikePriceGroup50SP1TestData extends MsgTest {

    private static final InstrmtStrikePriceGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new InstrmtStrikePriceGroup50SP1TestData();
    }

    public static InstrmtStrikePriceGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtStrikePriceGroup msg) throws UnsupportedEncodingException {
        msg.setInstrument();
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(44.5);
        msg.setClOrdID("CLIO-7363644");
        msg.setSecondaryClOrdID("SECORD-735353");
        msg.setSide(Side.Buy);
        msg.setPrice(46.77);
        msg.setCurrency(Currency.AustralianDollar);
        msg.setText("Some text 1");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void populate2(InstrmtStrikePriceGroup msg) throws UnsupportedEncodingException {
        msg.setInstrument();
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(44.1);
        msg.setClOrdID("CLIO-7363655");
        msg.setSecondaryClOrdID("SECORD-735388");
        msg.setSide(Side.Sell);
        msg.setPrice(46.21);
        msg.setCurrency(Currency.CanadianDollar);
        msg.setText("Some text 2");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 16, (byte) 33, (byte) 50, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        msg.setEncodedText(encText);
    }

    public void check(InstrmtStrikePriceGroup expected, InstrmtStrikePriceGroup actual) throws Exception {
        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getPrevClosePx(), actual.getPrevClosePx());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
