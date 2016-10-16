/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg44TestData.java
 *
 * $Id: QuoteRequestMsg44TestData.java,v 1.1 2009-07-06 03:19:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.group.impl.v44.QuoteRequestGroup44TestData;
import net.hades.fix.message.type.OrderCapacity;

/**
 * Test utility for QuoteRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestMsg44TestData extends MsgTest {

    private static final QuoteRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestMsg44TestData();
    }

    public static QuoteRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.QuoteRequest msg) throws Exception {
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.RFQReqID.FIELD, "TTT5676555");
        msg.setString(quickfix.field.ClOrdID.FIELD, "AAA5676555");
        msg.setChar(quickfix.field.OrderCapacity.FIELD, quickfix.field.OrderCapacity.AGENCY);
        // QuoteRelatedSymbolGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 2);
        quickfix.fix44.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix44.QuoteRequest.NoRelatedSym();
        QuoteRequestGroup44TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);
        quickfix.fix44.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix44.QuoteRequest.NoRelatedSym();
        QuoteRequestGroup44TestData.getInstance().populate2(grprelsym2);
        msg.addGroup(grprelsym2);

        msg.setString(quickfix.field.Text.FIELD, "Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedText.FIELD, new String(text, DEFAULT_CHARACTER_SET));
    }

    public void populate(QuoteRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteReqID("X162773883");
        msg.setRfqReqID("SSS87766555");
        msg.setClOrdID("AAAA676555");
        msg.setOrderCapacity(OrderCapacity.Agency);
        // QuoteRelatedSymbolGroup
        msg.setNoRelatedSym(new Integer(2));
        QuoteRequestGroup44TestData.getInstance().populate1(msg.getQuoteRelatedSymbols()[0]);
        QuoteRequestGroup44TestData.getInstance().populate2(msg.getQuoteRelatedSymbols()[1]);

        msg.setText("Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedTextLen(new Integer(8));
        msg.setEncodedText(text);
    }

    public void check(quickfix.fix44.QuoteRequest expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getString(quickfix.field.RFQReqID.FIELD), actual.getRfqReqID());
        assertEquals(expected.getString(quickfix.field.ClOrdID.FIELD), actual.getClOrdID());
        assertEquals(expected.getChar(quickfix.field.OrderCapacity.FIELD), actual.getOrderCapacity().getValue());
        // QuoteRelatedSymbolGroup check
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSym().intValue());
        quickfix.fix44.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix44.QuoteRequest.NoRelatedSym();
        expected.getGroup(1, grprelsym1);
        QuoteRequestGroup44TestData.getInstance().check(grprelsym1, actual.getQuoteRelatedSymbols()[0]);
        quickfix.fix44.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix44.QuoteRequest.NoRelatedSym();
        expected.getGroup(2, grprelsym2);
        QuoteRequestGroup44TestData.getInstance().check(grprelsym2, actual.getQuoteRelatedSymbols()[1]);

        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedText());
    }

    public void check(QuoteRequestMsg expected, quickfix.fix44.QuoteRequest actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getRfqReqID(), actual.getString(quickfix.field.RFQReqID.FIELD));
        assertEquals(expected.getClOrdID(), actual.getString(quickfix.field.ClOrdID.FIELD));
        assertEquals(expected.getOrderCapacity().getValue(), actual.getChar(quickfix.field.OrderCapacity.FIELD));
        // QuoteRelatedSymbolGroup check
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        quickfix.fix44.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix44.QuoteRequest.NoRelatedSym();
        actual.getGroup(1, grprelsym1);
        QuoteRequestGroup44TestData.getInstance().check(expected.getQuoteRelatedSymbols()[0], grprelsym1);
        quickfix.fix44.QuoteRequest.NoRelatedSym grprelsym2 = new quickfix.fix44.QuoteRequest.NoRelatedSym();
        actual.getGroup(2, grprelsym2);
        QuoteRequestGroup44TestData.getInstance().check(expected.getQuoteRelatedSymbols()[1], grprelsym2);

        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET));
    }

    public void check(QuoteRequestMsg expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrderCapacity().getValue(), actual.getOrderCapacity().getValue());
        // QuoteRelatedSymbolGroup check
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        QuoteRequestGroup44TestData.getInstance().check(expected.getQuoteRelatedSymbols()[0], actual.getQuoteRelatedSymbols()[0]);
        QuoteRequestGroup44TestData.getInstance().check(expected.getQuoteRelatedSymbols()[1], actual.getQuoteRelatedSymbols()[1]);

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
