/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDReqGroup50SP2TestData.java
 *
 * $Id: MDreqGroup50SP2TestData.java,v 1.5 2010-01-14 09:07:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.MDReqGroup;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for MDreqGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDReqGroup50SP2TestData extends MsgTest {

    private static final MDReqGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new MDReqGroup50SP2TestData();
    }

    public static MDReqGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        msg.setNoLegs(new Integer(2));
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        msg.setQuoteType(QuoteType.Tradeable);
        msg.setSettlType(SettlType.Cash.getValue());
        msg.setSettlDate(new Date());
        msg.setMdEntrySize(new Double(2.35));
        msg.setMdStreamID("RTR446363552");
    }

    public void populate2(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        msg.setNoLegs(2);
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        msg.setSettlDate(new Date());
        msg.setMdEntrySize(new Double(2.55));
        msg.setMdStreamID("XCX446363552");
    }

    public void check(MDReqGroup expected, MDReqGroup actual) throws Exception {
        // Instrument
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // UnderlyingInstrument
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0],
            actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1],
            actual.getUnderlyingInstruments()[1]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0],
            actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1],
            actual.getUnderlyingInstruments()[1]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[0],
            actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[1],
            actual.getInstrumentLegs()[1]);
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getMdEntrySize(), actual.getMdEntrySize());
        assertEquals(expected.getMdStreamID(), actual.getMdStreamID());
    }
}
