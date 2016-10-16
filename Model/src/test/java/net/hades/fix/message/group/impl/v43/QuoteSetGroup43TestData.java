/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetGroup43TestData.java
 *
 * $Id: QuoteSetGroup43TestData.java,v 1.2 2009-07-22 10:38:37 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.UnderlyingInstrument43TestData;
import net.hades.fix.message.group.QuoteSetGroup;

/**
 * Test utility for QuoteSetGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteSetGroup43TestData extends MsgTest {

    private static final QuoteSetGroup43TestData INSTANCE;

    static {
        INSTANCE = new QuoteSetGroup43TestData();
    }

    public static QuoteSetGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.MassQuote.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "ZZ23433");
        // UnderlyingInstrument
        quickfix.fix43.component.UnderlyingInstrument instr = new quickfix.fix43.component.UnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate1(instr);
        msg.set(instr);

        msg.setUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD, new Date());
        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 3);
        
        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup43TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup43TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate2(quickfix.fix43.MassQuote.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "AAA34353");
        // UnderlyingInstrument
        quickfix.fix43.component.UnderlyingInstrument instr = new quickfix.fix43.component.UnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate2(instr);
        msg.set(instr);

        msg.setUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD, new Date());
        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 4);

        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup43TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        QuoteEntryGroup43TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate1(QuoteSetGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("FFF46466");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setQuoteSetValidUntilTime(new Date());
        msg.setTotNoQuoteEntries(new Integer(3));

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryGroup43TestData.getInstance().populate1(msg.getQuoteEntryGroups()[0]);
        QuoteEntryGroup43TestData.getInstance().populate2(msg.getQuoteEntryGroups()[1]);
    }

    public void populate2(QuoteSetGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("GGG878w7847");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate2(msg.getUnderlyingInstrument());

        msg.setQuoteSetValidUntilTime(new Date());
        msg.setTotNoQuoteEntries(new Integer(3));

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryGroup43TestData.getInstance().populate1(msg.getQuoteEntryGroups()[0]);
        QuoteEntryGroup43TestData.getInstance().populate2(msg.getQuoteEntryGroups()[1]);
    }

    public void check(QuoteSetGroup expected, quickfix.fix43.MassQuote.NoQuoteSets actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getString(quickfix.field.QuoteSetID.FIELD));
        // UnderlyingInstrument
        UnderlyingInstrument43TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertUTCTimestampEquals(expected.getQuoteSetValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD), false);
        assertEquals(expected.getTotNoQuoteEntries().intValue(), actual.getInt(quickfix.field.TotNoQuoteEntries.FIELD));

        // QuoteEntryGroup check
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(1, grp1);
        QuoteEntryGroup43TestData.getInstance().check(expected.getQuoteEntryGroups()[0], grp1);
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(2, grp2);
        QuoteEntryGroup43TestData.getInstance().check(expected.getQuoteEntryGroups()[1], grp2);
    }

    public void check(quickfix.fix43.MassQuote.NoQuoteSets expected, QuoteSetGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteSetID.FIELD), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument43TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.QuoteSetValidUntilTime.FIELD), actual.getQuoteSetValidUntilTime(), false);
        assertEquals(actual.getTotNoQuoteEntries().intValue(), expected.getInt(quickfix.field.TotNoQuoteEntries.FIELD));

        // QuoteEntryGroup check
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(1, grp1);
        QuoteEntryGroup43TestData.getInstance().check(grp1, actual.getQuoteEntryGroups()[0]);
        quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(2, grp2);
        QuoteEntryGroup43TestData.getInstance().check(grp2, actual.getQuoteEntryGroups()[1]);
    }

    public void check(QuoteSetGroup expected, QuoteSetGroup actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument43TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertUTCTimestampEquals(expected.getQuoteSetValidUntilTime(), actual.getQuoteSetValidUntilTime(), false);
        assertEquals(actual.getTotNoQuoteEntries(), expected.getTotNoQuoteEntries());

        // QuoteEntryGroup check
        QuoteEntryGroup43TestData.getInstance().check(expected.getQuoteEntryGroups()[0], actual.getQuoteEntryGroups()[0]);
        QuoteEntryGroup43TestData.getInstance().check(expected.getQuoteEntryGroups()[1], actual.getQuoteEntryGroups()[1]);
    }
}
