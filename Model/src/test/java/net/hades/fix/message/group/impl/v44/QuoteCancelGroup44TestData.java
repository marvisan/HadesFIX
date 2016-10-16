/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelGroup44TestData.java
 *
 * $Id: QuoteCancelGroup44TestData.java,v 1.1 2009-07-06 03:19:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.QuoteCancelGroup;

/**
 * Test utility for QuoteCancelGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteCancelGroup44TestData extends MsgTest {

    private static final QuoteCancelGroup44TestData INSTANCE;

    static {
        INSTANCE = new QuoteCancelGroup44TestData();
    }

    public static QuoteCancelGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.QuoteCancel.NoQuoteEntries msg) throws Exception {
        // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix44.component.FinancingDetails findet = new quickfix.fix44.component.FinancingDetails();
        FinancingDetails44TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(ui1);
        quickfix.fix44.QuoteCancel.NoQuoteEntries.NoUnderlyings grpu1 = new quickfix.fix44.QuoteCancel.NoQuoteEntries.NoUnderlyings();
        grpu1.set(ui1);
        grpu1.set(ui1.getUnderlyingStipulations());
        msg.addGroup(grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(ui2);
        quickfix.fix44.QuoteCancel.NoQuoteEntries.NoUnderlyings grpu2 = new quickfix.fix44.QuoteCancel.NoQuoteEntries.NoUnderlyings();
        grpu2.set(ui2);
        grpu2.set(ui2.getUnderlyingStipulations());
        msg.addGroup(grpu2);
        // InstrumentLeg
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix44.component.InstrumentLeg il1 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(il1);
        quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs grpi1 = new quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs();
        msg.addGroup(grpi1);
        quickfix.fix44.component.InstrumentLeg il2 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate2(il2);
        quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs grpi2 = new quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs();
        msg.addGroup(grpi2);
    }

    public void populate2(quickfix.fix44.QuoteCancel.NoQuoteEntries msg) throws Exception {
        // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix44.component.FinancingDetails findet = new quickfix.fix44.component.FinancingDetails();
        FinancingDetails44TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(ui1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu1.set(ui1);
        grpu1.set(ui1.getUnderlyingStipulations());
        msg.addGroup(grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(ui2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu2.set(ui2);
        grpu2.set(ui2.getUnderlyingStipulations());
        msg.addGroup(grpu2);
        // InstrumentLeg
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix44.component.InstrumentLeg il1 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(il1);
        quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs grpi1 = new quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs();
        msg.addGroup(grpi1);
        quickfix.fix44.component.InstrumentLeg il2 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate2(il2);
        quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs grpi2 = new quickfix.fix44.QuoteCancel.NoQuoteEntries.NoLegs();
        msg.addGroup(grpi2);
    }

    public void populate1(QuoteCancelGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
    }

    public void populate2(QuoteCancelGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
    }

    public void check(QuoteCancelGroup expected, quickfix.fix44.QuoteCancel.NoQuoteEntries actual) throws Exception {
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
    }

    public void check(quickfix.fix44.QuoteCancel.NoQuoteEntries expected, QuoteCancelGroup actual) throws Exception {
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
    }

    public void check(QuoteCancelGroup expected, QuoteCancelGroup actual) throws Exception {
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
    }
}
