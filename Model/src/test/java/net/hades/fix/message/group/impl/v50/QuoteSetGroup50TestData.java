/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetGroup50TestData.java
 *
 * $Id: QuoteSetGroup50TestData.java,v 1.1 2009-07-21 08:58:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.group.QuoteSetGroup;

/**
 * Test utility for QuoteSetGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteSetGroup50TestData extends MsgTest {

    private static final QuoteSetGroup50TestData INSTANCE;

    static {
        INSTANCE = new QuoteSetGroup50TestData();
    }

    public static QuoteSetGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteSetGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("FFF46466");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setQuoteSetValidUntilTime(new Date());
        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setLastFragment(Boolean.FALSE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryGroup50TestData.getInstance().populate1(msg.getQuoteEntryGroups()[0]);
        QuoteEntryGroup50TestData.getInstance().populate2(msg.getQuoteEntryGroups()[1]);
    }

    public void populate2(QuoteSetGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("GGG878w7847");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstrument());

        msg.setQuoteSetValidUntilTime(new Date());
        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setLastFragment(Boolean.TRUE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryGroup50TestData.getInstance().populate1(msg.getQuoteEntryGroups()[0]);
        QuoteEntryGroup50TestData.getInstance().populate2(msg.getQuoteEntryGroups()[1]);
    }

    public void check(QuoteSetGroup expected, QuoteSetGroup actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertUTCTimestampEquals(expected.getQuoteSetValidUntilTime(), actual.getQuoteSetValidUntilTime(), false);
        assertEquals(actual.getTotNoQuoteEntries(), expected.getTotNoQuoteEntries());
        assertEquals(expected.getLastFragment().booleanValue(), actual.getLastFragment().booleanValue());

        // QuoteEntryGroup check
        QuoteEntryGroup50TestData.getInstance().check(expected.getQuoteEntryGroups()[0], actual.getQuoteEntryGroups()[0]);
        QuoteEntryGroup50TestData.getInstance().check(expected.getQuoteEntryGroups()[1], actual.getQuoteEntryGroups()[1]);
    }
}
