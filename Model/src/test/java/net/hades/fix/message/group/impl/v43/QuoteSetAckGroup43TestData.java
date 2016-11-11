/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetAckGroup43TestData.java
 *
 * $Id: QuoteSetAckGroup43TestData.java,v 1.1 2009-07-22 10:38:37 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.UnderlyingInstrument43TestData;
import net.hades.fix.message.group.QuoteSetAckGroup;

/**
 * Test utility for QuoteSetAckGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteSetAckGroup43TestData extends MsgTest {

    private static final QuoteSetAckGroup43TestData INSTANCE;

    static {
        INSTANCE = new QuoteSetAckGroup43TestData();
    }

    public static QuoteSetAckGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "ZZ23433");
        // UnderlyingInstrument
        quickfix.fix43.component.UnderlyingInstrument instr = new quickfix.fix43.component.UnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate1(instr);
        msg.set(instr);

        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 3);
        
        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup43TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup43TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate2(quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "AAA34353");
        // UnderlyingInstrument
        quickfix.fix43.component.UnderlyingInstrument instr = new quickfix.fix43.component.UnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate2(instr);
        msg.set(instr);

        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 4);

        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup43TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup43TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate1(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("FFF46466");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup43TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup43TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void populate2(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("GGG878w7847");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument43TestData.getInstance().populate2(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup43TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup43TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void check(QuoteSetAckGroup expected, quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getString(quickfix.field.QuoteSetID.FIELD));
        // UnderlyingInstrument
        UnderlyingInstrument43TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getTotNoQuoteEntries().intValue(), actual.getInt(quickfix.field.TotNoQuoteEntries.FIELD));

        // QuoteEntryGroup check
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(1, grp1);
        QuoteEntryAckGroup43TestData.getInstance().check(expected.getQuoteEntryAckGroups()[0], grp1);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(2, grp2);
        QuoteEntryAckGroup43TestData.getInstance().check(expected.getQuoteEntryAckGroups()[1], grp2);
    }

    public void check(quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets expected, QuoteSetAckGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteSetID.FIELD), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument43TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(actual.getTotNoQuoteEntries().intValue(), expected.getInt(quickfix.field.TotNoQuoteEntries.FIELD));

        // QuoteEntryGroup check
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(1, grp1);
        QuoteEntryAckGroup43TestData.getInstance().check(grp1, actual.getQuoteEntryAckGroups()[0]);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(2, grp2);
        QuoteEntryAckGroup43TestData.getInstance().check(grp2, actual.getQuoteEntryAckGroups()[1]);
    }

    public void check(QuoteSetAckGroup expected, QuoteSetAckGroup actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument43TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        assertEquals(actual.getTotNoQuoteEntries(), expected.getTotNoQuoteEntries());

        // QuoteEntryGroup check
        QuoteEntryAckGroup43TestData.getInstance().check(expected.getQuoteEntryAckGroups()[0], actual.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup43TestData.getInstance().check(expected.getQuoteEntryAckGroups()[1], actual.getQuoteEntryAckGroups()[1]);
    }
}
