/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg43TestData.java
 *
 * $Id: QuoteCancelMsg43TestData.java,v 1.1 2009-07-06 03:19:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteCancelMsg;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.group.impl.v43.QuoteCancelGroup43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.QuoteCancelType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteCancelMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteCancelMsg43TestData extends MsgTest {

    private static final QuoteCancelMsg43TestData INSTANCE;

    static {
        INSTANCE = new QuoteCancelMsg43TestData();
    }

    public static QuoteCancelMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.QuoteCancel msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteCancelType.FIELD, quickfix.field.QuoteCancelType.CANCEL_ALL_QUOTES);
        msg.setInt(quickfix.field.QuoteResponseLevel.FIELD, quickfix.field.QuoteResponseLevel.NO_ACKNOWLEDGEMENT);
        // Parties
        quickfix.fix43.component.Parties parties = new quickfix.fix43.component.Parties();
        Parties43TestData.getInstance().populate(parties);
        msg.set(parties);

        msg.setString(quickfix.field.Account.FIELD, "8438438563");
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.AfterHours.getValue());
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, TradingSessionSubID.Closing.getValue());
        
        // QuoteCancelGroup
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 2);
        quickfix.fix43.QuoteCancel.NoQuoteEntries qe1 = new quickfix.fix43.QuoteCancel.NoQuoteEntries();
        QuoteCancelGroup43TestData.getInstance().populate1(qe1);
        msg.addGroup(qe1);
        quickfix.fix43.QuoteCancel.NoQuoteEntries qe2 = new quickfix.fix43.QuoteCancel.NoQuoteEntries();
        QuoteCancelGroup43TestData.getInstance().populate2(qe2);
        msg.addGroup(qe2);
    }

    public void populate(QuoteCancelMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteCancelType(new Integer(QuoteCancelType.CancelAllQuotes.getValue()));
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        // Parties
        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setAccount("743358393859");
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        // QuoteCancelGroup
        msg.setNoQuoteEntries(new Integer(2));
        QuoteCancelGroup43TestData.getInstance().populate1(msg.getQuoteCancelEntries()[0]);
        QuoteCancelGroup43TestData.getInstance().populate2(msg.getQuoteCancelEntries()[1]);
    }

    public void check(quickfix.fix43.QuoteCancel expected, QuoteCancelMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteCancelType.FIELD), actual.getQuoteCancelType().intValue());
        assertEquals(expected.getInt(quickfix.field.QuoteResponseLevel.FIELD), actual.getQuoteResponseLevel().getValue());
        // Parties check
        quickfix.fix43.component.Parties parties = new quickfix.fix43.component.Parties();
        expected.get(parties);
        Parties43TestData.getInstance().check(parties, actual.getParties());

        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        

        // QuoteCancelGroup check
        quickfix.fix43.QuoteCancel.NoQuoteEntries qe1 = new quickfix.fix43.QuoteCancel.NoQuoteEntries();
        expected.getGroup(1, qe1);
        quickfix.fix43.QuoteCancel.NoQuoteEntries qe2 = new quickfix.fix43.QuoteCancel.NoQuoteEntries();
        expected.getGroup(2, qe2);
        QuoteCancelGroup43TestData.getInstance().check(qe1, actual.getQuoteCancelEntries()[0]);
        QuoteCancelGroup43TestData.getInstance().check(qe2, actual.getQuoteCancelEntries()[1]);
    }

    public void check(QuoteCancelMsg expected, quickfix.fix43.QuoteCancel actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteCancelType().intValue(), actual.getInt(quickfix.field.QuoteCancelType.FIELD));
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getInt(quickfix.field.QuoteResponseLevel.FIELD));
        // Parties check
        quickfix.fix43.component.Parties parties = new quickfix.fix43.component.Parties();
        actual.get(parties);
        Parties43TestData.getInstance().check(expected.getParties(), parties);

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        // QuoteCancelGroup check
        quickfix.fix43.QuoteCancel.NoQuoteEntries qe1 = new quickfix.fix43.QuoteCancel.NoQuoteEntries();
        actual.getGroup(1, qe1);
        quickfix.fix43.QuoteCancel.NoQuoteEntries qe2 = new quickfix.fix43.QuoteCancel.NoQuoteEntries();
        actual.getGroup(2, qe2);
        QuoteCancelGroup43TestData.getInstance().check(expected.getQuoteCancelEntries()[0], qe1);
        QuoteCancelGroup43TestData.getInstance().check(expected.getQuoteCancelEntries()[1], qe2);
     }

    public void check(QuoteCancelMsg expected, QuoteCancelMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteCancelType().intValue(), actual.getQuoteCancelType().intValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        // Parties check
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // QuoteCancelGroup check
        QuoteCancelGroup43TestData.getInstance().check(expected.getQuoteCancelEntries()[0], actual.getQuoteCancelEntries()[0]);
        QuoteCancelGroup43TestData.getInstance().check(expected.getQuoteCancelEntries()[1], actual.getQuoteCancelEntries()[1]);
    }
}
