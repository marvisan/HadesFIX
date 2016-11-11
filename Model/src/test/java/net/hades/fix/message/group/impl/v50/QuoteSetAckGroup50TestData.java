/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetAckGroup50TestData.java
 *
 * $Id: QuoteSetAckGroup50TestData.java,v 1.1 2009-07-24 02:16:28 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.group.QuoteSetAckGroup;

/**
 * Test utility for QuoteSetAckGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteSetAckGroup50TestData extends MsgTest {

    private static final QuoteSetAckGroup50TestData INSTANCE;

    static {
        INSTANCE = new QuoteSetAckGroup50TestData();
    }

    public static QuoteSetAckGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("FFF46466");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setLastFragment(Boolean.FALSE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup50TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup50TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void populate2(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("GGG878w7847");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setLastFragment(Boolean.TRUE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup50TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup50TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void check(QuoteSetAckGroup expected, QuoteSetAckGroup actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        assertEquals(actual.getTotNoQuoteEntries(), expected.getTotNoQuoteEntries());
        assertEquals(actual.getLastFragment().booleanValue(), expected.getLastFragment().booleanValue());

        // QuoteEntryGroup check
        QuoteEntryAckGroup50TestData.getInstance().check(expected.getQuoteEntryAckGroups()[0], actual.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup50TestData.getInstance().check(expected.getQuoteEntryAckGroups()[1], actual.getQuoteEntryAckGroups()[1]);
    }
}
