/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusRequestMsg44TestData.java
 *
 * $Id: QuoteStatusRequestMsg44TestData.java,v 1.1 2009-07-06 03:19:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteStatusRequestMsg;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteStatusRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteStatusRequestMsg44TestData extends MsgTest {

    private static final QuoteStatusRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new QuoteStatusRequestMsg44TestData();
    }

    public static QuoteStatusRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.QuoteStatusRequest msg) throws Exception {
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteStatusReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "Q63774663");
        // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix44.component.FinancingDetails findet = new quickfix.fix44.component.FinancingDetails();
        FinancingDetails44TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(ui1);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu1 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu1.set(ui1);
        grpu1.set(ui1.getUnderlyingStipulations());
        msg.addGroup(grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(ui2);
        quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings grpu2 = new quickfix.fix44.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu2.set(ui2);
        grpu2.set(ui2.getUnderlyingStipulations());
        msg.addGroup(grpu2);
        // Parties
        quickfix.fix44.component.Parties parties = new quickfix.fix44.component.Parties();
        Parties44TestData.getInstance().populate(parties);
        msg.set(parties);

        msg.setString(quickfix.field.Account.FIELD, "54543464");
        msg.setInt(quickfix.field.AcctIDSource.FIELD, quickfix.field.AcctIDSource.BIC);
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.AfterHours.getValue());
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, TradingSessionSubID.Closing.getValue());
        msg.setChar(quickfix.field.SubscriptionRequestType.FIELD, quickfix.field.SubscriptionRequestType.SNAPSHOT);
    }

    public void populate(QuoteStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteStatusReqID("AAA564567");
        msg.setQuoteID("X162773883");
                // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        // Parties
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());

        msg.setAccount("54543464");
        msg.setAcctIDSource(AcctIDSource.BIC);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID("X637478466");
        msg.setTradingSessionSubID("FCD636744");
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(quickfix.fix44.QuoteStatusRequest expected, QuoteStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteStatusReqID.FIELD), actual.getQuoteStatusReqID());
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getInt(quickfix.field.NoUnderlyings.FIELD), actual.getNoUnderlyings().intValue());
        quickfix.fix44.QuoteStatusRequest.NoUnderlyings grpu1 = new quickfix.fix44.QuoteStatusRequest.NoUnderlyings();
        expected.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument uinst1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(uinst1);
        quickfix.fix44.component.UnderlyingStipulations unstip1 = new quickfix.fix44.component.UnderlyingStipulations();
        grpu1.get(unstip1);
        uinst1.set(unstip1);
        UnderlyingInstrument44TestData.getInstance().check(uinst1, actual.getUnderlyingInstruments()[0]);
        quickfix.fix44.QuoteStatusRequest.NoUnderlyings grpu2 = new quickfix.fix44.QuoteStatusRequest.NoUnderlyings();
        expected.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument uinst2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(uinst2);
        quickfix.fix44.component.UnderlyingStipulations unstip2 = new quickfix.fix44.component.UnderlyingStipulations();
        grpu2.get(unstip2);
        uinst2.set(unstip2);
        UnderlyingInstrument44TestData.getInstance().check(uinst2, actual.getUnderlyingInstruments()[1]);
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AcctIDSource.FIELD), actual.getAcctIDSource().getValue());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        assertEquals(expected.getChar(quickfix.field.SubscriptionRequestType.FIELD), actual.getSubscriptionRequestType().getValue());
    }

    public void check(QuoteStatusRequestMsg expected, quickfix.fix44.QuoteStatusRequest actual) throws Exception {
        assertEquals(expected.getQuoteStatusReqID(), actual.getString(quickfix.field.QuoteStatusReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getInt(quickfix.field.NoUnderlyings.FIELD));
        quickfix.fix44.QuoteStatusRequest.NoUnderlyings grpu1 = new quickfix.fix44.QuoteStatusRequest.NoUnderlyings();
        actual.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument uinst1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(uinst1);
        quickfix.fix44.component.UnderlyingStipulations unstip1 = new quickfix.fix44.component.UnderlyingStipulations();
        grpu1.get(unstip1);
        uinst1.set(unstip1);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], uinst1);
        quickfix.fix44.QuoteStatusRequest.NoUnderlyings grpu2 = new quickfix.fix44.QuoteStatusRequest.NoUnderlyings();
        actual.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument uinst2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(uinst2);
        quickfix.fix44.component.UnderlyingStipulations unstip2 = new quickfix.fix44.component.UnderlyingStipulations();
        grpu2.get(unstip2);
        uinst2.set(unstip2);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], uinst2);
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAcctIDSource().getValue(), actual.getInt(quickfix.field.AcctIDSource.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getChar(quickfix.field.SubscriptionRequestType.FIELD));
    }

    public void check(QuoteStatusRequestMsg expected, QuoteStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getQuoteStatusReqID(), actual.getQuoteStatusReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        // InstrumentLeg
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
    }
}
