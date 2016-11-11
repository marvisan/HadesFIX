/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg50SP2TestData.java
 *
 * $Id: MassQuoteAckMsg50SP2TestData.java,v 1.1 2009-07-24 02:16:31 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.TargetParties50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.QuoteSetAckGroup50SP2TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.QuoteCancelType;
import net.hades.fix.message.type.QuoteRejectReason;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;

/**
 * Test utility for MassQuoteAckMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MassQuoteAckMsg50SP2TestData extends MsgTest {

    private static final MassQuoteAckMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new MassQuoteAckMsg50SP2TestData();
    }

    public static MassQuoteAckMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MassQuoteAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteStatus(QuoteStatus.Accepted);
        msg.setQuoteRejectReason(QuoteRejectReason.DuplicateQuote.getValue());
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        msg.setQuoteType(QuoteType.Tradeable);
        msg.setQuoteCancelType(QuoteCancelType.CancelAllQuotes.getValue());
        // Parties
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
        // TargetParties
        msg.setTargetParties();
        TargetParties50SP2TestData.getInstance().populate(msg.getTargetParties());

        msg.setAccount("837463783");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setText("Text test");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        // QuoteSetGroup
        msg.setNoQuoteSets(new Integer(2));
        QuoteSetAckGroup50SP2TestData.getInstance().populate1(msg.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup50SP2TestData.getInstance().populate2(msg.getQuoteSetAckGroups()[1]);
    }

    public void check(MassQuoteAckMsg expected, MassQuoteAckMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteStatus().getValue(), actual.getQuoteStatus().getValue());
        assertEquals(expected.getQuoteRejectReason().intValue(), actual.getQuoteRejectReason().intValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getQuoteCancelType(), actual.getQuoteCancelType());
        // Parties
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());
        // TargetParties check
        TargetParties50SP2TestData.getInstance().check(expected.getTargetParties(), actual.getTargetParties());
        
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getNoQuoteSets().intValue());
        QuoteSetAckGroup50SP2TestData.getInstance().check(expected.getQuoteSetAckGroups()[0], actual.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup50SP2TestData.getInstance().check(expected.getQuoteSetAckGroups()[1], actual.getQuoteSetAckGroups()[1]);
    }
}
