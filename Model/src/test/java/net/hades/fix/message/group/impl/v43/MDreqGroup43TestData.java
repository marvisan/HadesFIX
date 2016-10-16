/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDreqGroup43TestData.java
 *
 * $Id: MDreqGroup43TestData.java,v 1.1 2009-08-05 11:19:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.group.MDReqGroup;

/**
 * Test utility for MDreqGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDreqGroup43TestData extends MsgTest {

    private static final MDreqGroup43TestData INSTANCE;

    static {
        INSTANCE = new MDreqGroup43TestData();
    }

    public static MDreqGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.MarketDataRequest.NoRelatedSym msg) throws Exception {
       // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);
    }

    public void populate2(quickfix.fix43.MarketDataRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);
    }

    public void populate1(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());
    }

    public void populate2(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());
    }

    public void check(MDReqGroup expected, quickfix.fix43.MarketDataRequest.NoRelatedSym actual) throws Exception {
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(quickfix.fix43.MarketDataRequest.NoRelatedSym expected, MDReqGroup actual) throws Exception {
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(MDReqGroup expected, MDReqGroup actual) throws Exception {
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }
}
