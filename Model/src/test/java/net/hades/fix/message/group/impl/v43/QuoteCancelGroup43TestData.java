/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelGroup43TestData.java
 *
 * $Id: QuoteCancelGroup43TestData.java,v 1.1 2009-07-06 03:19:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.group.QuoteCancelGroup;

/**
 * Test utility for QuoteCancelGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteCancelGroup43TestData extends MsgTest {

    private static final QuoteCancelGroup43TestData INSTANCE;

    static {
        INSTANCE = new QuoteCancelGroup43TestData();
    }

    public static QuoteCancelGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.QuoteCancel.NoQuoteEntries msg) throws Exception {
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);
    }

    public void populate2(quickfix.fix43.QuoteCancel.NoQuoteEntries msg) throws Exception {
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);
    }

    public void populate1(QuoteCancelGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());
    }

    public void populate2(QuoteCancelGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());
    }

    public void check(QuoteCancelGroup expected, quickfix.fix43.QuoteCancel.NoQuoteEntries actual) throws Exception {
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(quickfix.fix43.QuoteCancel.NoQuoteEntries expected, QuoteCancelGroup actual) throws Exception {
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(QuoteCancelGroup expected, QuoteCancelGroup actual) throws Exception {
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }
}
