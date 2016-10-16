/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusRequestMsg50SP1TestData.java
 *
 * $Id: SecurityStatusRequestMsg50SP1TestData.java,v 1.2 2011-10-29 09:42:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityStatusRequestMsg;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityStatusRequestMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityStatusRequestMsg50SP1TestData extends MsgTest {

    private static final SecurityStatusRequestMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new SecurityStatusRequestMsg50SP1TestData();
    }

    public static SecurityStatusRequestMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setSecurityStatusReqID("REQ_11111");

        msg.setInstrument();
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());

        msg.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(msg.getInstrumentExtension());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(2);
        InstrumentLeg50SP1TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setCurrency(Currency.AustralianDollar);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setMarketID("NYSE");
        msg.setMarketSegmentID("COMM");
    }

    public void check(SecurityStatusRequestMsg expected, SecurityStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getSecurityStatusReqID(), actual.getSecurityStatusReqID());

        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getMarketID(), actual.getMarketID());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());
    }
}
