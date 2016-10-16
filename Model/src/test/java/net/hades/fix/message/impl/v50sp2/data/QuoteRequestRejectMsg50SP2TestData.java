/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg50SP2TestData.java
 *
 * $Id: QuoteRequestRejectMsg50SP2TestData.java,v 1.1 2009-07-06 03:19:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.comp.impl.v50sp2.RootParties50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.QuoteRequestRejectGroup50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.QuoteRequestRejectReason;
import net.hades.fix.message.type.RespondentType;

/**
 * Test utility for QuoteRequestRejectMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestRejectMsg50SP2TestData extends MsgTest {

    private static final QuoteRequestRejectMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestRejectMsg50SP2TestData();
    }

    public static QuoteRequestRejectMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(QuoteRequestRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setQuoteReqID("X162773883");
        msg.setRfqReqID("SSS87766555");
        msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.ExchangeClosed.getValue());
        msg.setPrivateQuote(Boolean.TRUE);
        msg.setRespondentType(RespondentType.AllMarketMakers);
        msg.setPreTradeAnonymity(Boolean.TRUE);
        // RootParties
        msg.setRootParties();
        RootParties50SP2TestData.getInstance().populate(msg.getRootParties());
        // QuoteRelatedSymbolGroup
        msg.setNoRelatedSym(new Integer(2));
        QuoteRequestRejectGroup50SP2TestData.getInstance().populate1(msg.getQuoteRequestRejectGroups()[0]);
        QuoteRequestRejectGroup50SP2TestData.getInstance().populate2(msg.getQuoteRequestRejectGroups()[1]);

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
        assertEquals(expected.getPrivateQuote(), actual.getPrivateQuote());
        assertEquals(expected.getRespondentType(), actual.getRespondentType());
        assertEquals(expected.getPreTradeAnonymity(), actual.getPreTradeAnonymity());
        // RootParties check
        RootParties50SP2TestData.getInstance().check(expected.getRootParties(), actual.getRootParties());
        // QuoteRelatedSymbolGroup check
        QuoteRequestRejectGroup50SP2TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[0],
            actual.getQuoteRequestRejectGroups()[0]);
        QuoteRequestRejectGroup50SP2TestData.getInstance().check(expected.getQuoteRequestRejectGroups()[1],
            actual.getQuoteRequestRejectGroups()[1]);

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
