/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusRequestMsg43TestData.java
 *
 * $Id: SecurityStatusRequestMsg43TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityStatusRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityStatusRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityStatusRequestMsg43TestData extends MsgTest {

    private static final SecurityStatusRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new SecurityStatusRequestMsg43TestData();
    }

    public static SecurityStatusRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setSecurityStatusReqID("REQ_11111");

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());
       
        msg.setCurrency(Currency.AustralianDollar);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
    }

    public void check(SecurityStatusRequestMsg expected, SecurityStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getSecurityStatusReqID(), actual.getSecurityStatusReqID());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
    }
}
