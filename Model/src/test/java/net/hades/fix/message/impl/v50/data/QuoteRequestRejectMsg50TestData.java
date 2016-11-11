/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg50TestData.java
 *
 * $Id: QuoteRequestRejectMsg50TestData.java,v 1.2 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.group.impl.v50.QuoteRequestRejectGroup50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.QuoteRequestRejectReason;

/**
 * Test utility for QuoteRequestRejectMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestRejectMsg50TestData extends MsgTest {

    private static final QuoteRequestRejectMsg50TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestRejectMsg50TestData();
    }

    public static QuoteRequestRejectMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(QuoteRequestRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setQuoteReqID("X162773883");
        msg.setRfqReqID("SSS87766555");
        msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.ExchangeClosed.getValue());
        // QuoteRelatedSymbolGroup
        msg.setNoRelatedSym(new Integer(2));
        QuoteRequestRejectGroup50TestData.getInstance().populate1(msg.getQuoteRequestRejectGroups()[0]);
        QuoteRequestRejectGroup50TestData.getInstance().populate2(msg.getQuoteRequestRejectGroups()[1]);

        msg.setText("Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedTextLen(new Integer(8));
        msg.setEncodedText(text);
    }

    public void check(QuoteRequestRejectMsg expected, QuoteRequestRejectMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getQuoteRequestRejectReason(), actual.getQuoteRequestRejectReason());
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        // QuoteRelatedSymbolGroup check
        QuoteRequestRejectGroup50TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[0],
            actual.getQuoteRequestRejectGroups()[0]);
        QuoteRequestRejectGroup50TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[1],
            actual.getQuoteRequestRejectGroups()[1]);

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
