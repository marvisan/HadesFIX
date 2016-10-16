/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecListGroup43TestData.java
 *
 * $Id: SecListGroup43TestData.java,v 1.2 2011-10-29 09:42:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecListGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class SecListGroup43TestData extends MsgTest {

    private static final SecListGroup43TestData INSTANCE;

    static {
        INSTANCE = new SecListGroup43TestData();
    }

    public static SecListGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SecListGroup grp) throws UnsupportedEncodingException {
        // Instrument
        grp.setInstrument();
        Instrument43TestData.getInstance().populate(grp.getInstrument());
        
        grp.setCurrency(Currency.AustralianDollar);

        grp.setNoLegs(2);
        InstrmtLegSecListGroup43TestData.getInstance().populate1(grp.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup43TestData.getInstance().populate2(grp.getInstrmtLegSecListGroups()[1]);

        grp.setRoundLot(34.6);
        grp.setMinTradeVol(45000.0d);
        grp.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        grp.setText("Some text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void populate2(SecListGroup grp) throws UnsupportedEncodingException {
        // Instrument
        grp.setInstrument();
        Instrument43TestData.getInstance().populate(grp.getInstrument());

        grp.setCurrency(Currency.UnitedStatesDollar);

        grp.setNoLegs(2);
        InstrmtLegSecListGroup43TestData.getInstance().populate1(grp.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup43TestData.getInstance().populate2(grp.getInstrmtLegSecListGroups()[1]);

        grp.setRoundLot(37.6);
        grp.setMinTradeVol(95000.0d);
        grp.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Opening.getValue());
        grp.setText("Some text 2");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 16, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void check(SecListGroup expected, SecListGroup actual) throws Exception {
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrmtLegSecListGroup43TestData.getInstance().check(expected.getInstrmtLegSecListGroups()[0], actual.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup43TestData.getInstance().check(expected.getInstrmtLegSecListGroups()[1], actual.getInstrmtLegSecListGroups()[1]);

        assertEquals(expected.getRoundLot(), actual.getRoundLot());
        assertEquals(expected.getMinTradeVol(), actual.getMinTradeVol());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
