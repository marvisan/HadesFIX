/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsg42TestData.java
 *
 * $Id: MassQuoteMsg42TestData.java,v 1.1 2009-07-21 08:58:50 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v42.QuoteSetGroup42TestData;
import net.hades.fix.message.type.QuoteResponseLevel;

/**
 * Test utility for MassQuoteMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MassQuoteMsg42TestData extends MsgTest {

    private static final MassQuoteMsg42TestData INSTANCE;

    static {
        INSTANCE = new MassQuoteMsg42TestData();
    }

    public static MassQuoteMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix42.MassQuote msg) throws Exception {
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteResponseLevel.FIELD, quickfix.field.QuoteResponseLevel.ACKNOWLEDGE_EACH_QUOTE_MESSAGES);
        msg.setDouble(quickfix.field.DefBidSize.FIELD, 12.456);
        msg.setDouble(quickfix.field.DefOfferSize.FIELD, 13.456);
        // QuoteSetGroup
        msg.setInt(quickfix.field.NoQuoteSets.FIELD, 2);
        quickfix.fix42.MassQuote.NoQuoteSets grp1 = new quickfix.fix42.MassQuote.NoQuoteSets();
        QuoteSetGroup42TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix42.MassQuote.NoQuoteSets grp2 = new quickfix.fix42.MassQuote.NoQuoteSets();
        QuoteSetGroup42TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(MassQuoteMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        msg.setDefBidSize(new Double(23.55));
        msg.setDefOfferSize(new Double(24.55));
        // QuoteSetGroup
        msg.setNoQuoteSets(new Integer(2));
        QuoteSetGroup42TestData.getInstance().populate1(msg.getQuoteSetGroups()[0]);
        QuoteSetGroup42TestData.getInstance().populate2(msg.getQuoteSetGroups()[1]);
    }

    public void check(quickfix.fix42.MassQuote expected, MassQuoteMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteResponseLevel.FIELD), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getDouble(quickfix.field.DefBidSize.FIELD), actual.getDefBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.DefOfferSize.FIELD), actual.getDefOfferSize().doubleValue(), 0.001);
        // QuoteSetGroup
        assertEquals(expected.getInt(quickfix.field.NoQuoteSets.FIELD), actual.getNoQuoteSets().intValue());
        quickfix.fix42.MassQuote.NoQuoteSets grp1 = new quickfix.fix42.MassQuote.NoQuoteSets();
        expected.getGroup(1, grp1);
        QuoteSetGroup42TestData.getInstance().check(grp1, actual.getQuoteSetGroups()[0]);
        quickfix.fix42.MassQuote.NoQuoteSets grp2 = new quickfix.fix42.MassQuote.NoQuoteSets();
        expected.getGroup(2, grp2);
        QuoteSetGroup42TestData.getInstance().check(grp2, actual.getQuoteSetGroups()[1]);
    }

    public void check(MassQuoteMsg expected, quickfix.fix42.MassQuote actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getInt(quickfix.field.QuoteResponseLevel.FIELD));
        assertEquals(expected.getDefBidSize().doubleValue(), actual.getDouble(quickfix.field.DefBidSize.FIELD), 0.001);
        assertEquals(expected.getDefOfferSize().doubleValue(), actual.getDouble(quickfix.field.DefOfferSize.FIELD), 0.001);
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getInt(quickfix.field.NoQuoteSets.FIELD));
        quickfix.fix42.MassQuote.NoQuoteSets grp1 = new quickfix.fix42.MassQuote.NoQuoteSets();
        actual.getGroup(1, grp1);
        QuoteSetGroup42TestData.getInstance().check(expected.getQuoteSetGroups()[0], grp1);
        quickfix.fix42.MassQuote.NoQuoteSets grp2 = new quickfix.fix42.MassQuote.NoQuoteSets();
        actual.getGroup(2, grp2);
        QuoteSetGroup42TestData.getInstance().check(expected.getQuoteSetGroups()[1], grp2);
    }

    public void check(MassQuoteMsg expected, MassQuoteMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getDefBidSize().doubleValue(), actual.getDefBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDefOfferSize().doubleValue(), actual.getDefOfferSize().doubleValue(), 0.001);
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getNoQuoteSets().intValue());
        QuoteSetGroup42TestData.getInstance().check(expected.getQuoteSetGroups()[0], actual.getQuoteSetGroups()[0]);
        QuoteSetGroup42TestData.getInstance().check(expected.getQuoteSetGroups()[1], actual.getQuoteSetGroups()[1]);
    }
}
