/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg42TestData.java
 *
 * $Id: QuoteRequestMsg42TestData.java,v 1.1 2009-07-06 03:19:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.group.impl.v42.QuoteRelatedSymbolGroup42TestData;

/**
 * Test utility for QuoteRequestMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestMsg42TestData extends MsgTest {

    private static final QuoteRequestMsg42TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestMsg42TestData();
    }

    public static QuoteRequestMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix42.QuoteRequest msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        // QuoteRelatedSymbolGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 2);
        quickfix.fix42.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix42.QuoteRequest.NoRelatedSym();
        QuoteRelatedSymbolGroup42TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);
        quickfix.fix42.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix42.QuoteRequest.NoRelatedSym();
        QuoteRelatedSymbolGroup42TestData.getInstance().populate2(grprelsym2);
        msg.addGroup(grprelsym2);
    }

    public void populate(QuoteRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setQuoteReqID("X162773883");
        // QuoteRelatedSymbolGroup
        msg.setNoRelatedSym(new Integer(2));
        QuoteRelatedSymbolGroup42TestData.getInstance().populate1(msg.getQuoteRelatedSymbols()[0]);
        QuoteRelatedSymbolGroup42TestData.getInstance().populate2(msg.getQuoteRelatedSymbols()[1]);
    }

    public void check(quickfix.fix42.QuoteRequest expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        // QuoteRelatedSymbolGroup check
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSym().intValue());
        quickfix.fix42.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix42.QuoteRequest.NoRelatedSym();
        expected.getGroup(1, grprelsym1);
        QuoteRelatedSymbolGroup42TestData.getInstance().check(grprelsym1, actual.getQuoteRelatedSymbols()[0]);
        quickfix.fix42.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix42.QuoteRequest.NoRelatedSym();
        expected.getGroup(2, grprelsym2);
        QuoteRelatedSymbolGroup42TestData.getInstance().check(grprelsym2, actual.getQuoteRelatedSymbols()[1]);
    }

    public void check(QuoteRequestMsg expected, quickfix.fix42.QuoteRequest actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        // QuoteRelatedSymbolGroup check
        quickfix.fix42.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix42.QuoteRequest.NoRelatedSym();
        actual.getGroup(1, grprelsym1);
        QuoteRelatedSymbolGroup42TestData.getInstance().check(expected.getQuoteRelatedSymbols()[0], grprelsym1);
        quickfix.fix42.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix42.QuoteRequest.NoRelatedSym();
        actual.getGroup(2, grprelsym2);
        QuoteRelatedSymbolGroup42TestData.getInstance().check(expected.getQuoteRelatedSymbols()[1], grprelsym2);
    }

    public void check(QuoteRequestMsg expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        // QuoteRelatedSymbolGroup check
        QuoteRelatedSymbolGroup42TestData.getInstance().check(expected.getQuoteRelatedSymbols()[0], actual.getQuoteRelatedSymbols()[0]);
        QuoteRelatedSymbolGroup42TestData.getInstance().check(expected.getQuoteRelatedSymbols()[1], actual.getQuoteRelatedSymbols()[1]);
    }
}
