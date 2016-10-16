/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg43TestData.java
 *
 * $Id: QuoteRequestRejectMsg43TestData.java,v 1.1 2009-07-06 03:19:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.group.impl.v43.QuoteRequestRejectGroup43TestData;
import net.hades.fix.message.type.QuoteRequestRejectReason;

/**
 * Test utility for QuoteRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestRejectMsg43TestData extends MsgTest {

    private static final QuoteRequestRejectMsg43TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestRejectMsg43TestData();
    }

    public static QuoteRequestRejectMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.QuoteRequestReject msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.RFQReqID.FIELD, "TTT5676555");
        msg.setInt(quickfix.field.QuoteRequestRejectReason.FIELD, quickfix.field.QuoteRequestRejectReason.EXCHANGE_CLOSED);
        // QuoteRelatedSymbolGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 2);
        quickfix.fix43.QuoteRequestReject.NoRelatedSym grprelsym1 = new quickfix.fix43.QuoteRequestReject.NoRelatedSym();
        QuoteRequestRejectGroup43TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);
        quickfix.fix43.QuoteRequestReject.NoRelatedSym grprelsym2 = new quickfix.fix43.QuoteRequestReject.NoRelatedSym();
        QuoteRequestRejectGroup43TestData.getInstance().populate2(grprelsym2);
        msg.addGroup(grprelsym2);

        msg.setString(quickfix.field.Text.FIELD, "Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedText.FIELD, new String(text, DEFAULT_CHARACTER_SET));
    }

    public void populate(QuoteRequestRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteReqID("X162773883");
        msg.setRfqReqID("SSS87766555");
        msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.InvalidPrice.getValue());
        // QuoteRelatedSymbolGroup
        msg.setNoRelatedSym(new Integer(2));
        QuoteRequestRejectGroup43TestData.getInstance().populate1(msg.getQuoteRequestRejectGroups()[0]);
        QuoteRequestRejectGroup43TestData.getInstance().populate2(msg.getQuoteRequestRejectGroups()[1]);

        msg.setText("Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedTextLen(new Integer(8));
        msg.setEncodedText(text);
    }

    public void check(quickfix.fix43.QuoteRequestReject expected, QuoteRequestRejectMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getString(quickfix.field.RFQReqID.FIELD), actual.getRfqReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteRequestRejectReason.FIELD), actual.getQuoteRequestRejectReason().intValue());
        // QuoteRelatedSymbolGroup check
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSym().intValue());
        quickfix.fix43.QuoteRequestReject.NoRelatedSym grprelsym1 = new quickfix.fix43.QuoteRequestReject.NoRelatedSym();
        expected.getGroup(1, grprelsym1);
        QuoteRequestRejectGroup43TestData.getInstance().check(grprelsym1, actual.getQuoteRequestRejectGroups()[0]);
        quickfix.fix43.QuoteRequestReject.NoRelatedSym grprelsym2 = new quickfix.fix43.QuoteRequestReject.NoRelatedSym();
        expected.getGroup(2, grprelsym2);
        QuoteRequestRejectGroup43TestData.getInstance().check(grprelsym2, actual.getQuoteRequestRejectGroups()[1]);

        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedText());
    }

    public void check(QuoteRequestRejectMsg expected, quickfix.fix43.QuoteRequestReject actual) throws Exception {
        assertEquals(actual.getString(quickfix.field.QuoteReqID.FIELD), expected.getQuoteReqID());
        assertEquals(actual.getString(quickfix.field.RFQReqID.FIELD), expected.getRfqReqID());
        assertEquals(actual.getInt(quickfix.field.QuoteRequestRejectReason.FIELD), expected.getQuoteRequestRejectReason().intValue());
        // QuoteRelatedSymbolGroup check
        assertEquals(actual.getInt(quickfix.field.NoRelatedSym.FIELD), expected.getNoRelatedSym().intValue());
        quickfix.fix43.QuoteRequestReject.NoRelatedSym grprelsym1 = new quickfix.fix43.QuoteRequestReject.NoRelatedSym();
        actual.getGroup(1, grprelsym1);
        QuoteRequestRejectGroup43TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[0], grprelsym1);
        quickfix.fix43.QuoteRequestReject.NoRelatedSym grprelsym2 = new quickfix.fix43.QuoteRequestReject.NoRelatedSym();
        actual.getGroup(2, grprelsym2);
        QuoteRequestRejectGroup43TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[1], grprelsym2);

        assertEquals(actual.getString(quickfix.field.Text.FIELD), expected.getText());
        assertEquals(actual.getInt(quickfix.field.EncodedTextLen.FIELD), expected.getEncodedTextLen().intValue());
        assertArrayEquals(actual.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), expected.getEncodedText());
    }

    public void check(QuoteRequestRejectMsg expected, QuoteRequestRejectMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getQuoteRequestRejectReason(), actual.getQuoteRequestRejectReason());
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        // QuoteRelatedSymbolGroup check
        QuoteRequestRejectGroup43TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[0],
            actual.getQuoteRequestRejectGroups()[0]);
        QuoteRequestRejectGroup43TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[1],
            actual.getQuoteRequestRejectGroups()[1]);

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
