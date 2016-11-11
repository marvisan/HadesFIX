/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg50TestData.java
 *
 * $Id: QuoteCancelMsg50TestData.java,v 1.2 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteCancelMsg;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.group.impl.v50.QuoteCancelGroup50TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.QuoteCancelType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteCancelMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteCancelMsg50TestData extends MsgTest {

    private static final QuoteCancelMsg50TestData INSTANCE;

    static {
        INSTANCE = new QuoteCancelMsg50TestData();
    }

    public static QuoteCancelMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(QuoteCancelMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteCancelType(new Integer(QuoteCancelType.CancelAllQuotes.getValue()));
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        // Parties
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());

        msg.setAccount("743358393859");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        // QuoteCancelGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteCancelGroup50TestData.getInstance().populate1(msg.getQuoteCancelEntries()[0]);
        QuoteCancelGroup50TestData.getInstance().populate2(msg.getQuoteCancelEntries()[1]);
    }

    public void check(QuoteCancelMsg expected, QuoteCancelMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteCancelType().intValue(), actual.getQuoteCancelType().intValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        // Parties check
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // QuoteCancelGroup check
        QuoteCancelGroup50TestData.getInstance().check(expected.getQuoteCancelEntries()[0], actual.getQuoteCancelEntries()[0]);
        QuoteCancelGroup50TestData.getInstance().check(expected.getQuoteCancelEntries()[1], actual.getQuoteCancelEntries()[1]);
    }
}
