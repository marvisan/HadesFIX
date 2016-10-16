/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg50SP2TestData.java
 *
 * $Id: QuoteCancelMsg50SP2TestData.java,v 1.1 2009-07-06 03:19:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteCancelMsg;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.TargetParties50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.QuoteCancelGroup50SP2TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.QuoteCancelType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteCancelMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteCancelMsg50SP2TestData extends MsgTest {

    private static final QuoteCancelMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new QuoteCancelMsg50SP2TestData();
    }

    public static QuoteCancelMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(QuoteCancelMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteMsgID("AS7777");
        msg.setQuoteCancelType(new Integer(QuoteCancelType.CancelSpecifiedQuote.getValue()));
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        // Parties
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
        // TargetParties
        msg.setTargetParties();
        TargetParties50SP2TestData.getInstance().populate(msg.getTargetParties());

        msg.setAccount("743358393859");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        // QuoteCancelGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteCancelGroup50SP2TestData.getInstance().populate1(msg.getQuoteCancelEntries()[0]);
        QuoteCancelGroup50SP2TestData.getInstance().populate2(msg.getQuoteCancelEntries()[1]);
    }

    public void check(QuoteCancelMsg expected, QuoteCancelMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteMsgID(), actual.getQuoteMsgID());
        assertEquals(expected.getQuoteCancelType().intValue(), actual.getQuoteCancelType().intValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        // Parties check
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());
        // TargetParties check
        TargetParties50SP2TestData.getInstance().check(expected.getTargetParties(), actual.getTargetParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // QuoteCancelGroup check
        QuoteCancelGroup50SP2TestData.getInstance().check(expected.getQuoteCancelEntries()[0], actual.getQuoteCancelEntries()[0]);
        QuoteCancelGroup50SP2TestData.getInstance().check(expected.getQuoteCancelEntries()[1], actual.getQuoteCancelEntries()[1]);
    }
}
