/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecListGroup50TestData.java
 *
 * $Id: SecListGroup50TestData.java,v 1.2 2011-10-29 09:42:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExpirationCycle;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecListGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class SecListGroup50TestData extends MsgTest {

    private static final SecListGroup50TestData INSTANCE;

    static {
        INSTANCE = new SecListGroup50TestData();
    }

    public static SecListGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SecListGroup grp) throws UnsupportedEncodingException {
        // Instrument
        grp.setInstrument();
        Instrument50TestData.getInstance().populate(grp.getInstrument());

        grp.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(grp.getInstrumentExtension());

        grp.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(grp.getFinancingDetails());

        grp.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(grp.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(grp.getUnderlyingInstruments()[1]);

        grp.setCurrency(Currency.AustralianDollar);

        grp.setStipulations();
        Stipulations50TestData.getInstance().populate(grp.getStipulations());

        grp.setNoLegs(2);
        InstrmtLegSecListGroup50TestData.getInstance().populate1(grp.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup50TestData.getInstance().populate2(grp.getInstrmtLegSecListGroups()[1]);

        grp.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(grp.getSpreadOrBenchmarkCurveData());

        grp.setYieldData();
        YieldData50TestData.getInstance().populate(grp.getYieldData());

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
        Instrument50TestData.getInstance().populate(grp.getInstrument());

        grp.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(grp.getInstrumentExtension());

        grp.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(grp.getFinancingDetails());

        grp.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(grp.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(grp.getUnderlyingInstruments()[1]);

        grp.setCurrency(Currency.UnitedStatesDollar);

        grp.setStipulations();
        Stipulations50TestData.getInstance().populate(grp.getStipulations());

        grp.setNoLegs(2);
        InstrmtLegSecListGroup50TestData.getInstance().populate1(grp.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup50TestData.getInstance().populate2(grp.getInstrmtLegSecListGroups()[1]);

        grp.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(grp.getSpreadOrBenchmarkCurveData());

        grp.setYieldData();
        YieldData50TestData.getInstance().populate(grp.getYieldData());

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
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getCurrency(), actual.getCurrency());

        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrmtLegSecListGroup50TestData.getInstance().check(expected.getInstrmtLegSecListGroups()[0], actual.getInstrmtLegSecListGroups()[0]);
        InstrmtLegSecListGroup50TestData.getInstance().check(expected.getInstrmtLegSecListGroups()[1], actual.getInstrmtLegSecListGroups()[1]);

        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

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
