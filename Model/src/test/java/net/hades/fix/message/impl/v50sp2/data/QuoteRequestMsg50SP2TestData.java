/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg50SP2TestData.java
 *
 * $Id: QuoteRequestMsg50SP2TestData.java,v 1.1 2009-07-06 03:19:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.comp.impl.v50sp2.RootParties50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.QuoteRequestGroup50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderRestrictions;
import net.hades.fix.message.type.RespondentType;

/**
 * Test utility for QuoteRequestMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestMsg50SP2TestData extends MsgTest {

    private static final QuoteRequestMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestMsg50SP2TestData();
    }

    public static QuoteRequestMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(QuoteRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setQuoteReqID("X162773883");
        msg.setRfqReqID("SSS87766555");
        msg.setClOrdID("AAAA676555");
        msg.setBookingType(BookingType.ContractForDifference);
        msg.setOrderCapacity(OrderCapacity.Agency);
        msg.setOrderRestrictions(OrderRestrictions.Algorithmic.getValue());
        msg.setPrivateQuote(Boolean.FALSE);
        msg.setRespondentType(RespondentType.AllMarketParticipants);
        msg.setPreTradeAnonymity(Boolean.TRUE);
        // RootParties
        msg.setRootParties();
        RootParties50SP2TestData.getInstance().populate(msg.getRootParties());
        // QuoteRelatedSymbolGroup
        msg.setNoRelatedSym(new Integer(2));
        QuoteRequestGroup50SP2TestData.getInstance().populate1(msg.getQuoteRelatedSymbols()[0]);
        QuoteRequestGroup50SP2TestData.getInstance().populate2(msg.getQuoteRelatedSymbols()[1]);

        msg.setText("Some text");
        byte[] text = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedTextLen(new Integer(8));
        msg.setEncodedText(text);
    }

    public void check(QuoteRequestMsg expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getBookingType(), actual.getBookingType());
        assertEquals(expected.getOrderCapacity().getValue(), actual.getOrderCapacity().getValue());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getPrivateQuote().booleanValue(), actual.getPrivateQuote().booleanValue());
        assertEquals(expected.getRespondentType().getValue(), actual.getRespondentType().getValue());
        assertEquals(expected.getPreTradeAnonymity().booleanValue(), actual.getPreTradeAnonymity().booleanValue());
        // RootParties check
        RootParties50SP2TestData.getInstance().check(expected.getRootParties(), actual.getRootParties());
        // QuoteRelatedSymbolGroup check
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        QuoteRequestGroup50SP2TestData.getInstance().check(expected.getQuoteRelatedSymbols()[0], actual.getQuoteRelatedSymbols()[0]);
        QuoteRequestGroup50SP2TestData.getInstance().check(expected.getQuoteRelatedSymbols()[1], actual.getQuoteRelatedSymbols()[1]);

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
