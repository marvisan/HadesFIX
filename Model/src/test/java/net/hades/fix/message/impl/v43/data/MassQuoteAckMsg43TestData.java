/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg43TestData.java
 *
 * $Id: MassQuoteAckMsg43TestData.java,v 1.1 2009-07-22 10:38:20 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.group.impl.v43.QuoteSetAckGroup43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.QuoteRejectReason;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for MassQuoteAckMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MassQuoteAckMsg43TestData extends MsgTest {

    private static final MassQuoteAckMsg43TestData INSTANCE;

    static {
        INSTANCE = new MassQuoteAckMsg43TestData();
    }

    public static MassQuoteAckMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.MassQuoteAcknowledgement msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteStatus.FIELD, quickfix.field.QuoteStatus.ACCEPTED);
        msg.setInt(quickfix.field.QuoteRejectReason.FIELD, quickfix.field.QuoteRejectReason.DUPLICATE_QUOTE);
        msg.setInt(quickfix.field.QuoteResponseLevel.FIELD, quickfix.field.QuoteResponseLevel.ACKNOWLEDGE_EACH_QUOTE_MESSAGES);
        msg.setInt(quickfix.field.QuoteType.FIELD, quickfix.field.QuoteType.INDICATIVE);
        // Parties
        quickfix.fix43.component.Parties part = new quickfix.fix43.component.Parties();
        Parties43TestData.getInstance().populate(part);
        msg.set(part);

        msg.setString(quickfix.field.Account.FIELD, "353453454");
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.AfterHours.getValue());
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, TradingSessionSubID.Intraday.getValue());
        msg.setString(quickfix.field.Text.FIELD, "Text test");
        // QuoteSetGroup
        msg.setInt(quickfix.field.NoQuoteSets.FIELD, 2);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets();
        QuoteSetAckGroup43TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets();
        QuoteSetAckGroup43TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(MassQuoteAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteStatus(QuoteStatus.Accepted);
        msg.setQuoteRejectReason(QuoteRejectReason.DuplicateQuote.getValue());
        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        msg.setQuoteType(QuoteType.Tradeable);
        // Parties
        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setAccount("837463783");
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setText("Text test");
        // QuoteSetGroup
        msg.setNoQuoteSets(new Integer(2));
        QuoteSetAckGroup43TestData.getInstance().populate1(msg.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup43TestData.getInstance().populate2(msg.getQuoteSetAckGroups()[1]);
    }

    public void check(quickfix.fix43.MassQuoteAcknowledgement expected, MassQuoteAckMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getInt(quickfix.field.QuoteStatus.FIELD), actual.getQuoteStatus().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteRejectReason.FIELD), actual.getQuoteRejectReason().intValue());
        assertEquals(expected.getInt(quickfix.field.QuoteResponseLevel.FIELD), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        // QuoteSetGroup
        assertEquals(expected.getInt(quickfix.field.NoQuoteSets.FIELD), actual.getNoQuoteSets().intValue());
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets();
        expected.getGroup(1, grp1);
        QuoteSetAckGroup43TestData.getInstance().check(grp1, actual.getQuoteSetAckGroups()[0]);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets();
        expected.getGroup(2, grp2);
        QuoteSetAckGroup43TestData.getInstance().check(grp2, actual.getQuoteSetAckGroups()[1]);
    }

    public void check(MassQuoteAckMsg expected, quickfix.fix43.MassQuoteAcknowledgement actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteStatus().getValue(), actual.getInt(quickfix.field.QuoteStatus.FIELD));
        assertEquals(expected.getQuoteRejectReason().intValue(), actual.getInt(quickfix.field.QuoteRejectReason.FIELD));
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getInt(quickfix.field.QuoteResponseLevel.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        // Parties
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getInt(quickfix.field.NoQuoteSets.FIELD));
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets grp1 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets();
        actual.getGroup(1, grp1);
        QuoteSetAckGroup43TestData.getInstance().check(expected.getQuoteSetAckGroups()[0], grp1);
        quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets grp2 = new quickfix.fix43.MassQuoteAcknowledgement.NoQuoteSets();
        actual.getGroup(2, grp2);
        QuoteSetAckGroup43TestData.getInstance().check(expected.getQuoteSetAckGroups()[1], grp2);
    }

    public void check(MassQuoteAckMsg expected, MassQuoteAckMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteStatus().getValue(), actual.getQuoteStatus().getValue());
        assertEquals(expected.getQuoteRejectReason().intValue(), actual.getQuoteRejectReason().intValue());
        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        // Parties
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getText(), actual.getText());
        // QuoteSetGroup
        assertEquals(expected.getNoQuoteSets().intValue(), actual.getNoQuoteSets().intValue());
        QuoteSetAckGroup43TestData.getInstance().check(expected.getQuoteSetAckGroups()[0], actual.getQuoteSetAckGroups()[0]);
        QuoteSetAckGroup43TestData.getInstance().check(expected.getQuoteSetAckGroups()[1], actual.getQuoteSetAckGroups()[1]);
    }
}
