/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelGroup50SP2TestData.java
 *
 * $Id: QuoteCancelGroup50SP2TestData.java,v 1.2 2009-11-21 09:57:26 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.QuoteCancelGroup;

/**
 * Test utility for QuoteCancelGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteCancelGroup50SP2TestData extends MsgTest {

    private static final QuoteCancelGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new QuoteCancelGroup50SP2TestData();
    }

    public static QuoteCancelGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteCancelGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
    }

    public void populate2(QuoteCancelGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
    }

    public void check(QuoteCancelGroup expected, QuoteCancelGroup actual) throws Exception {
        // Instrument
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0],
            actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1],
            actual.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[0],
            actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[1],
            actual.getInstrumentLegs()[1]);
    }
}
