/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDreqGroup44TestData.java
 *
 * $Id: MDreqGroup44TestData.java,v 1.1 2009-08-05 11:19:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.MDReqGroup;

/**
 * Test utility for MDreqGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MDreqGroup44TestData extends MsgTest {

    private static final MDreqGroup44TestData INSTANCE;

    static {
        INSTANCE = new MDreqGroup44TestData();
    }

    public static MDreqGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.MarketDataRequest.NoRelatedSym msg) throws Exception {
       // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
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

    public void populate2(quickfix.fix44.MarketDataRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
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

    public void populate1(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
    }

    public void populate2(MDReqGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
    }

    public void check(MDReqGroup expected, quickfix.fix44.MarketDataRequest.NoRelatedSym actual) throws Exception {
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(quickfix.fix44.MarketDataRequest.NoRelatedSym expected, MDReqGroup actual) throws Exception {
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(MDReqGroup expected, MDReqGroup actual) throws Exception {
        // Instrument
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // UnderlyingInstrument
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
    }
}
