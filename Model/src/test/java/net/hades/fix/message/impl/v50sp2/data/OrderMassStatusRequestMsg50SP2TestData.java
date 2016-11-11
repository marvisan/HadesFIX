/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassStatusRequestMsg50SP2TestData.java
 *
 * $Id: OrderMassStatusRequestMsg50SP2TestData.java,v 1.1 2011-05-09 08:21:14 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderMassStatusRequestMsg;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.TargetParties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.MassStatusReqType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for OrderMassStatusRequestMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2011, 12:08:30 PM
 */
public class OrderMassStatusRequestMsg50SP2TestData extends MsgTest {

    private static final OrderMassStatusRequestMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new OrderMassStatusRequestMsg50SP2TestData();
    }

    public static OrderMassStatusRequestMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderMassStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setMassStatusReqID("AAA564567");
        msg.setMassStatusReqType(MassStatusReqType.AllOrdersStatus);
        
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
                
        msg.setTargetParties();
        TargetParties50SP2TestData.getInstance().populate(msg.getTargetParties());

        msg.setAccount("87765433");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());

        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setSide(Side.Buy);
    }

    public void check(OrderMassStatusRequestMsg expected, OrderMassStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getMassStatusReqID(), actual.getMassStatusReqID());
        assertEquals(expected.getMassStatusReqType(), actual.getMassStatusReqType());
        
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());
                        
        TargetParties50SP2TestData.getInstance().check(expected.getTargetParties(), actual.getTargetParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());

        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getSide(), actual.getSide());
    }
}
