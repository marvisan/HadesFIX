/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsg50SP2TestData.java
 *
 * $Id: MassQuoteMsg50SP2TestData.java,v 1.1 2009-07-21 08:58:49 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.QuoteSetGroup50SP2TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteType;

/**
 * Test utility for MassQuoteMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MassQuoteMsg50SP2TestData extends MsgTest {

    private static final MassQuoteMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new MassQuoteMsg50SP2TestData();
    }

    public static MassQuoteMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MassQuoteMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteType(QuoteType.Tradeable);
        // Parties
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());

        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        msg.setAccount("837463783");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setDefBidSize(new Double(23.55));
        msg.setDefOfferSize(new Double(24.55));
        // QuoteSetGroup
        msg.setNoQuoteSets(new Integer(2));
        QuoteSetGroup50SP2TestData.getInstance().populate1(msg.getQuoteSetGroups()[0]);
        QuoteSetGroup50SP2TestData.getInstance().populate2(msg.getQuoteSetGroups()[1]);
    }

    public void check(MassQuoteMsg expected, MassQuoteMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        // Parties
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getDefBidSize().doubleValue(), actual.getDefBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDefOfferSize().doubleValue(), actual.getDefOfferSize().doubleValue(), 0.001);
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getNoQuoteSets().intValue());
        QuoteSetGroup50SP2TestData.getInstance().check(expected.getQuoteSetGroups()[0], actual.getQuoteSetGroups()[0]);
        QuoteSetGroup50SP2TestData.getInstance().check(expected.getQuoteSetGroups()[1], actual.getQuoteSetGroups()[1]);
    }
}
