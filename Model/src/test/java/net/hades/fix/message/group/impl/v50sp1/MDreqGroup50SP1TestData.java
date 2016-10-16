/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDreqGroup50SP1TestData.java
 *
 * $Id: MDreqGroup50SP1TestData.java,v 1.2 2011-10-29 09:42:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.MDReqGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for MDreqGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDreqGroup50SP1TestData extends MsgTest {

    private static final MDreqGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new MDreqGroup50SP1TestData();
    }

    public static MDreqGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP1TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setQuoteType(QuoteType.Tradeable);
        msg.setSettlType(SettlType.Cash.getValue());
        msg.setSettlDate(new Date());
        msg.setMdEntrySize(new Double(2.35));
    }

    public void populate2(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP1TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setCurrency(Currency.AustralianDollar);
        msg.setQuoteType(QuoteType.Counter);
        msg.setSettlType(SettlType.Future.getValue());
        msg.setSettlDate(new Date());
        msg.setMdEntrySize(new Double(2.55));
    }

    public void check(MDReqGroup expected, MDReqGroup actual) throws Exception {
        // Instrument
        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // UnderlyingInstrument
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getQuoteType(), actual.getQuoteType());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getMdEntrySize(), actual.getMdEntrySize());
    }
}
