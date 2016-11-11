/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetAckGroup44TestData.java
 *
 * $Id: QuoteSetAckGroup44TestData.java,v 1.1 2009-07-24 02:16:28 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.QuoteSetAckGroup;

/**
 * Test utility for QuoteSetAckGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteSetAckGroup44TestData extends MsgTest {

    private static final QuoteSetAckGroup44TestData INSTANCE;

    static {
        INSTANCE = new QuoteSetAckGroup44TestData();
    }

    public static QuoteSetAckGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "ZZ23433");
        // UnderlyingInstrument
        quickfix.fix44.component.UnderlyingInstrument instr = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(instr);
        msg.set(instr);

        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 3);
        msg.setBoolean(quickfix.field.LastFragment.FIELD, true);
        
        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup44TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup44TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate2(quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets msg) throws Exception {
        msg.setString(quickfix.field.QuoteSetID.FIELD, "AAA34353");
        // UnderlyingInstrument
        quickfix.fix44.component.UnderlyingInstrument instr = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(instr);
        msg.set(instr);

        msg.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 4);
        msg.setBoolean(quickfix.field.LastFragment.FIELD, false);

        // QuoteEntryGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup44TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        QuoteEntryAckGroup44TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate1(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("FFF46466");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setLastFragment(Boolean.FALSE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup44TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup44TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void populate2(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("GGG878w7847");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setLastFragment(Boolean.TRUE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup44TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup44TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void check(QuoteSetAckGroup expected, quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getString(quickfix.field.QuoteSetID.FIELD));
        // UnderlyingInstrument
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getTotNoQuoteEntries().intValue(), actual.getInt(quickfix.field.TotNoQuoteEntries.FIELD));
        assertEquals(expected.getLastFragment().booleanValue(), actual.getBoolean(quickfix.field.LastFragment.FIELD));

        // QuoteEntryGroup check
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(1, grp1);
        QuoteEntryAckGroup44TestData.getInstance().check(expected.getQuoteEntryAckGroups()[0], grp1);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        actual.getGroup(2, grp2);
        QuoteEntryAckGroup44TestData.getInstance().check(expected.getQuoteEntryAckGroups()[1], grp2);
    }

    public void check(quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets expected, QuoteSetAckGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteSetID.FIELD), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(actual.getTotNoQuoteEntries().intValue(), expected.getInt(quickfix.field.TotNoQuoteEntries.FIELD));
        assertEquals(actual.getLastFragment().booleanValue(), expected.getBoolean(quickfix.field.LastFragment.FIELD));

        // QuoteEntryGroup check
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp1 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(1, grp1);
        QuoteEntryAckGroup44TestData.getInstance().check(grp1, actual.getQuoteEntryAckGroups()[0]);
        quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries grp2 = new quickfix.fix44.MassQuoteAcknowledgement.NoQuoteSets.NoQuoteEntries();
        expected.getGroup(2, grp2);
        QuoteEntryAckGroup44TestData.getInstance().check(grp2, actual.getQuoteEntryAckGroups()[1]);
    }

    public void check(QuoteSetAckGroup expected, QuoteSetAckGroup actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        assertEquals(actual.getTotNoQuoteEntries(), expected.getTotNoQuoteEntries());
        assertEquals(actual.getLastFragment().booleanValue(), expected.getLastFragment().booleanValue());

        // QuoteEntryGroup check
        QuoteEntryAckGroup44TestData.getInstance().check(expected.getQuoteEntryAckGroups()[0], actual.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup44TestData.getInstance().check(expected.getQuoteEntryAckGroups()[1], actual.getQuoteEntryAckGroups()[1]);
    }
}
