/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetAckGroup50SP1TestData.java
 *
 * $Id: QuoteSetAckGroup50SP1TestData.java,v 1.1 2009-07-24 02:16:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.QuoteSetAckGroup;

/**
 * Test utility for QuoteSetAckGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteSetAckGroup50SP1TestData extends MsgTest {

    private static final QuoteSetAckGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new QuoteSetAckGroup50SP1TestData();
    }

    public static QuoteSetAckGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("FFF46466");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setTotNoCxldQuotes(new Integer(3));
        msg.setTotNoAccQuotes(new Integer(10));
        msg.setTotNoRejQuotes(new Integer(4));
        msg.setLastFragment(Boolean.FALSE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup50SP1TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup50SP1TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void populate2(QuoteSetAckGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteSetID("GGG878w7847");
        // UnderlyingInstrument
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstrument());

        msg.setTotNoQuoteEntries(new Integer(3));
        msg.setTotNoCxldQuotes(new Integer(2));
        msg.setTotNoAccQuotes(new Integer(9));
        msg.setTotNoRejQuotes(new Integer(5));
        msg.setLastFragment(Boolean.TRUE);

        // QuoteEntryGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteEntryAckGroup50SP1TestData.getInstance().populate1(msg.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup50SP1TestData.getInstance().populate2(msg.getQuoteEntryAckGroups()[1]);
    }

    public void check(QuoteSetAckGroup expected, QuoteSetAckGroup actual) throws Exception {
        assertEquals(expected.getQuoteSetID(), actual.getQuoteSetID());
        // UnderlyingInstrument
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        assertEquals(actual.getTotNoQuoteEntries(), expected.getTotNoQuoteEntries());
        assertEquals(actual.getTotNoCxldQuotes(), expected.getTotNoCxldQuotes());
        assertEquals(actual.getTotNoAccQuotes(), expected.getTotNoAccQuotes());
        assertEquals(actual.getTotNoRejQuotes(), expected.getTotNoRejQuotes());
        assertEquals(actual.getLastFragment().booleanValue(), expected.getLastFragment().booleanValue());

        // QuoteEntryGroup check
        QuoteEntryAckGroup50SP1TestData.getInstance().check(expected.getQuoteEntryAckGroups()[0], actual.getQuoteEntryAckGroups()[0]);
        QuoteEntryAckGroup50SP1TestData.getInstance().check(expected.getQuoteEntryAckGroups()[1], actual.getQuoteEntryAckGroups()[1]);
    }
}
