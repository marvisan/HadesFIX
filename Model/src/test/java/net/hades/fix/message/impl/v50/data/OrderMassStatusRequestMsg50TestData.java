/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassStatusRequestMsg50TestData.java
 *
 * $Id: OrderMassStatusRequestMsg50TestData.java,v 1.1 2011-05-09 08:21:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderMassStatusRequestMsg;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.MassStatusReqType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for OrderMassStatusRequestMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderMassStatusRequestMsg50TestData extends MsgTest {

    private static final OrderMassStatusRequestMsg50TestData INSTANCE;

    static {
        INSTANCE = new OrderMassStatusRequestMsg50TestData();
    }

    public static OrderMassStatusRequestMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderMassStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setMassStatusReqID("AAA564567");
        msg.setMassStatusReqType(MassStatusReqType.AllOrdersStatus);
        
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        
        msg.setAccount("87765433");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());

        msg.setInstrument();
        Instrument50TestData.getInstance().populate(msg.getInstrument());
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setSide(Side.Buy);
    }

    public void check(OrderMassStatusRequestMsg expected, OrderMassStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getMassStatusReqID(), actual.getMassStatusReqID());
        assertEquals(expected.getMassStatusReqType(), actual.getMassStatusReqType());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());

        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getSide(), actual.getSide());
    }
}
