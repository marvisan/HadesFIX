/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecListGroup44TestData.java
 *
 * $Id: SecListGroup44TestData.java,v 1.2 2011-10-29 09:42:09 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExpirationCycle;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecListGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class SecListGroup44TestData extends MsgTest {

    private static final SecListGroup44TestData INSTANCE;

    static {
        INSTANCE = new SecListGroup44TestData();
    }

    public static SecListGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SecListGroup grp) throws UnsupportedEncodingException {
        // Instrument
        grp.setInstrument();
        Instrument44TestData.getInstance().populate(grp.getInstrument());

        grp.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(grp.getInstrumentExtension());

        grp.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(grp.getFinancingDetails());

        grp.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(grp.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(grp.getUnderlyingInstruments()[1]);

        grp.setCurrency(Currency.AustralianDollar);

        grp.setStipulations();
        Stipulations44TestData.getInstance().populate(grp.getStipulations());

        grp.setNoLegs(2);
        InstrmtLegSecListGroup44TestData.getInstance().populate1(grp.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup44TestData.getInstance().populate2(grp.getInstrmtLegSecListGroups()[1]);

        grp.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(grp.getSpreadOrBenchmarkCurveData());

        grp.setYieldData();
        YieldData44TestData.getInstance().populate(grp.getYieldData());

        grp.setRoundLot(34.6);
        grp.setMinTradeVol(45000.0d);
        grp.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        grp.setExpirationCycle(ExpirationCycle.ExpireOnTradingSessionClose);
        grp.setText("Some text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void populate2(SecListGroup grp) throws UnsupportedEncodingException {
        // Instrument
        grp.setInstrument();
        Instrument44TestData.getInstance().populate(grp.getInstrument());

        grp.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(grp.getInstrumentExtension());

        grp.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(grp.getFinancingDetails());

        grp.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(grp.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(grp.getUnderlyingInstruments()[1]);

        grp.setCurrency(Currency.UnitedStatesDollar);

        grp.setStipulations();
        Stipulations44TestData.getInstance().populate(grp.getStipulations());

        grp.setNoLegs(2);
        InstrmtLegSecListGroup44TestData.getInstance().populate1(grp.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup44TestData.getInstance().populate2(grp.getInstrmtLegSecListGroups()[1]);

        grp.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(grp.getSpreadOrBenchmarkCurveData());

        grp.setYieldData();
        YieldData44TestData.getInstance().populate(grp.getYieldData());

        grp.setRoundLot(37.6);
        grp.setMinTradeVol(95000.0d);
        grp.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Opening.getValue());
        grp.setExpirationCycle(ExpirationCycle.ExpireOnTradingSessionOpen);
        grp.setText("Some text 2");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 16, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void check(SecListGroup expected, SecListGroup actual) throws Exception {
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getCurrency(), actual.getCurrency());

        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrmtLegSecListGroup44TestData.getInstance().check(expected.getInstrmtLegSecListGroups()[0], actual.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup44TestData.getInstance().check(expected.getInstrmtLegSecListGroups()[1], actual.getInstrmtLegSecListGroups()[1]);

        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getRoundLot(), actual.getRoundLot());
        assertEquals(expected.getMinTradeVol(), actual.getMinTradeVol());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getExpirationCycle(), actual.getExpirationCycle());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
