/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusRequestMsg43TestData.java
 *
 * $Id: QuoteStatusRequestMsg43TestData.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteStatusRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteStatusRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteStatusRequestMsg43TestData extends MsgTest {

    private static final QuoteStatusRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new QuoteStatusRequestMsg43TestData();
    }

    public static QuoteStatusRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.QuoteStatusRequest msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteStatusReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "Q63774663");
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);
        // Parties
        quickfix.fix43.component.Parties parties = new quickfix.fix43.component.Parties();
        Parties43TestData.getInstance().populate(parties);
        msg.set(parties);

        msg.setString(quickfix.field.Account.FIELD, "54543464");
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.AfterHours.getValue());
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, TradingSessionSubID.Closing.getValue());
        msg.setChar(quickfix.field.SubscriptionRequestType.FIELD, quickfix.field.SubscriptionRequestType.SNAPSHOT);
    }

    public void populate(QuoteStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteStatusReqID("AAA564567");
        msg.setQuoteID("X162773883");
       // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());
        // Parties
        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        msg.setAccount("54543464");
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID("X637478466");
        msg.setTradingSessionSubID("FCD636744");
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(quickfix.fix43.QuoteStatusRequest expected, QuoteStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteStatusReqID.FIELD), actual.getQuoteStatusReqID());
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Parties check
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        assertEquals(expected.getChar(quickfix.field.SubscriptionRequestType.FIELD), actual.getSubscriptionRequestType().getValue());
    }

    public void check(QuoteStatusRequestMsg expected, quickfix.fix43.QuoteStatusRequest actual) throws Exception {
        assertEquals(expected.getQuoteStatusReqID(), actual.getString(quickfix.field.QuoteStatusReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Parties check
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getChar(quickfix.field.SubscriptionRequestType.FIELD));
    }

    public void check(QuoteStatusRequestMsg expected, QuoteStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getQuoteStatusReqID(), actual.getQuoteStatusReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Parties check
        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
    }
}
