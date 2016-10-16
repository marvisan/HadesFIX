/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg43TestData.java
 *
 * $Id: QuoteRequestMsg43TestData.java,v 1.1 2009-07-06 03:19:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.group.impl.v43.QuoteRequestGroup43TestData;

/**
 * Test utility for QuoteRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestMsg43TestData extends MsgTest {

    private static final QuoteRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestMsg43TestData();
    }

    public static QuoteRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.QuoteRequest msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.RFQReqID.FIELD, "TTT5676555");
        // QuoteRelatedSymbolGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 2);
        quickfix.fix43.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix43.QuoteRequest.NoRelatedSym();
        QuoteRequestGroup43TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);
        quickfix.fix43.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix43.QuoteRequest.NoRelatedSym();
        QuoteRequestGroup43TestData.getInstance().populate2(grprelsym2);
        msg.addGroup(grprelsym2);

        msg.setString(quickfix.field.Text.FIELD, "Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedText.FIELD, new String(text, DEFAULT_CHARACTER_SET));
    }

    public void populate(QuoteRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteReqID("X162773883");
        msg.setRfqReqID("SSS87766555");
        // QuoteRelatedSymbolGroup
        msg.setNoRelatedSym(new Integer(2));
        QuoteRequestGroup43TestData.getInstance().populate1(msg.getQuoteRelatedSymbols()[0]);
        QuoteRequestGroup43TestData.getInstance().populate2(msg.getQuoteRelatedSymbols()[1]);

        msg.setText("Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedTextLen(new Integer(8));
        msg.setEncodedText(text);
    }

    public void check(quickfix.fix43.QuoteRequest expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getString(quickfix.field.RFQReqID.FIELD), actual.getRfqReqID());
        // QuoteRelatedSymbolGroup check
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSym().intValue());
        quickfix.fix43.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix43.QuoteRequest.NoRelatedSym();
        expected.getGroup(1, grprelsym1);
        QuoteRequestGroup43TestData.getInstance().check(grprelsym1, actual.getQuoteRelatedSymbols()[0]);
        quickfix.fix43.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix43.QuoteRequest.NoRelatedSym();
        expected.getGroup(2, grprelsym2);
        QuoteRequestGroup43TestData.getInstance().check(grprelsym2, actual.getQuoteRelatedSymbols()[1]);

        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedText());
    }

    public void check(QuoteRequestMsg expected, quickfix.fix43.QuoteRequest actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getRfqReqID(), actual.getString(quickfix.field.RFQReqID.FIELD));
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        // QuoteRelatedSymbolGroup check
        quickfix.fix43.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix43.QuoteRequest.NoRelatedSym();
        actual.getGroup(1, grprelsym1);
        QuoteRequestGroup43TestData.getInstance().check(expected.getQuoteRelatedSymbols()[0], grprelsym1);
        quickfix.fix43.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix43.QuoteRequest.NoRelatedSym();
        actual.getGroup(2, grprelsym2);
        QuoteRequestGroup43TestData.getInstance().check(expected.getQuoteRelatedSymbols()[1], grprelsym2);

        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET));
    }

    public void check(QuoteRequestMsg expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        // QuoteRelatedSymbolGroup check
        QuoteRequestGroup43TestData.getInstance().check(expected.getQuoteRelatedSymbols()[0], actual.getQuoteRelatedSymbols()[0]);
        QuoteRequestGroup43TestData.getInstance().check(expected.getQuoteRelatedSymbols()[1], actual.getQuoteRelatedSymbols()[1]);

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
