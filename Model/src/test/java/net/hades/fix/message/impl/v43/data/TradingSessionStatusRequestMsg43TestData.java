/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusRequestMsg43TestData.java
 *
 * $Id: TradingSessionStatusRequestMsg43TestData.java,v 1.1 2011-04-22 01:59:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradingSessionStatusRequestMsg;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradSesMethod;
import net.hades.fix.message.type.TradSesMode;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for TradingSessionStatusRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradingSessionStatusRequestMsg43TestData extends MsgTest {

    private static final TradingSessionStatusRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new TradingSessionStatusRequestMsg43TestData();
    }

    public static TradingSessionStatusRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradingSessionStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setTradSesReqID("REQ_11111");
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setTradSesMethod(TradSesMethod.OpenOutcry);
        msg.setTradSesMode(TradSesMode.Production);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        
    }

    public void check(TradingSessionStatusRequestMsg expected, TradingSessionStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getTradSesReqID(), actual.getTradSesReqID());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getTradSesMethod(), actual.getTradSesMethod());
        assertEquals(expected.getTradSesMode(), actual.getTradSesMode());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
    }
}
