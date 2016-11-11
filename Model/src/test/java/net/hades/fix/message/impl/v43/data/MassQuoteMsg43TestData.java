/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsg43TestData.java
 *
 * $Id: MassQuoteMsg43TestData.java,v 1.1 2009-07-21 08:58:48 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.group.impl.v43.QuoteSetGroup43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteType;

/**
 * Test utility for MassQuoteMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MassQuoteMsg43TestData extends MsgTest {

    private static final MassQuoteMsg43TestData INSTANCE;

    static {
        INSTANCE = new MassQuoteMsg43TestData();
    }

    public static MassQuoteMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.MassQuote msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteType.FIELD, quickfix.field.QuoteType.INDICATIVE);
        // Parties
        quickfix.fix43.component.Parties part = new quickfix.fix43.component.Parties();
        Parties43TestData.getInstance().populate(part);
        msg.set(part);

        msg.setInt(quickfix.field.QuoteResponseLevel.FIELD, quickfix.field.QuoteResponseLevel.ACKNOWLEDGE_EACH_QUOTE_MESSAGES);
        msg.setString(quickfix.field.Account.FIELD, "353453454");
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setDouble(quickfix.field.DefBidSize.FIELD, 12.456);
        msg.setDouble(quickfix.field.DefOfferSize.FIELD, 13.456);
        // QuoteSetGroup
        msg.setInt(quickfix.field.NoQuoteSets.FIELD, 2);
        quickfix.fix43.MassQuote.NoQuoteSets grp1 = new quickfix.fix43.MassQuote.NoQuoteSets();
        QuoteSetGroup43TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix43.MassQuote.NoQuoteSets grp2 = new quickfix.fix43.MassQuote.NoQuoteSets();
        QuoteSetGroup43TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(MassQuoteMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteType(QuoteType.Tradeable);
        // Parties
        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        msg.setAccount("837463783");
        msg.setAccountType(AccountType.HouseTrader);
        msg.setDefBidSize(new Double(23.55));
        msg.setDefOfferSize(new Double(24.55));
        // QuoteSetGroup
        msg.setNoQuoteSets(new Integer(2));
        QuoteSetGroup43TestData.getInstance().populate1(msg.getQuoteSetGroups()[0]);
        QuoteSetGroup43TestData.getInstance().populate2(msg.getQuoteSetGroups()[1]);
    }

    public void check(quickfix.fix43.MassQuote expected, MassQuoteMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteResponseLevel.FIELD), actual.getQuoteResponseLevel().getValue());
        // Parties
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getDouble(quickfix.field.DefBidSize.FIELD), actual.getDefBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.DefOfferSize.FIELD), actual.getDefOfferSize().doubleValue(), 0.001);
        // QuoteSetGroup
        assertEquals(expected.getInt(quickfix.field.NoQuoteSets.FIELD), actual.getNoQuoteSets().intValue());
        quickfix.fix43.MassQuote.NoQuoteSets grp1 = new quickfix.fix43.MassQuote.NoQuoteSets();
        expected.getGroup(1, grp1);
        QuoteSetGroup43TestData.getInstance().check(grp1, actual.getQuoteSetGroups()[0]);
        quickfix.fix43.MassQuote.NoQuoteSets grp2 = new quickfix.fix43.MassQuote.NoQuoteSets();
        expected.getGroup(2, grp2);
        QuoteSetGroup43TestData.getInstance().check(grp2, actual.getQuoteSetGroups()[1]);
    }

    public void check(MassQuoteMsg expected, quickfix.fix43.MassQuote actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getInt(quickfix.field.QuoteResponseLevel.FIELD));
        // Parties
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getDefBidSize().doubleValue(), actual.getDouble(quickfix.field.DefBidSize.FIELD), 0.001);
        assertEquals(expected.getDefOfferSize().doubleValue(), actual.getDouble(quickfix.field.DefOfferSize.FIELD), 0.001);
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getInt(quickfix.field.NoQuoteSets.FIELD));
        quickfix.fix43.MassQuote.NoQuoteSets grp1 = new quickfix.fix43.MassQuote.NoQuoteSets();
        actual.getGroup(1, grp1);
        QuoteSetGroup43TestData.getInstance().check(expected.getQuoteSetGroups()[0], grp1);
        quickfix.fix43.MassQuote.NoQuoteSets grp2 = new quickfix.fix43.MassQuote.NoQuoteSets();
        actual.getGroup(2, grp2);
        QuoteSetGroup43TestData.getInstance().check(expected.getQuoteSetGroups()[1], grp2);
    }

    public void check(MassQuoteMsg expected, MassQuoteMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        // Parties
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getDefBidSize().doubleValue(), actual.getDefBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDefOfferSize().doubleValue(), actual.getDefOfferSize().doubleValue(), 0.001);
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getNoQuoteSets().intValue());
        QuoteSetGroup43TestData.getInstance().check(expected.getQuoteSetGroups()[0], actual.getQuoteSetGroups()[0]);
        QuoteSetGroup43TestData.getInstance().check(expected.getQuoteSetGroups()[1], actual.getQuoteSetGroups()[1]);
    }
}
