/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderCancelRequestMsg43TestData.java
 *
 * $Id: CrossOrderCancelRequestMsg43TestData.java,v 1.1 2011-05-21 23:53:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CrossOrderCancelRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.group.impl.v43.SideCrossOrdCxlGroup43TestData;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;

/**
 * Test utility for CrossOrderCancelRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/05/2011, 12:08:30 PM
 */
public class CrossOrderCancelRequestMsg43TestData extends MsgTest {

    private static final CrossOrderCancelRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new CrossOrderCancelRequestMsg43TestData();
    }

    public static CrossOrderCancelRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(CrossOrderCancelRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setOrderID("ORD7765544");
        msg.setCrossID("AAA564567");
        msg.setOrigCrossID("ORIG_887733");
        msg.setCrossType(CrossType.CrossAON);
        msg.setCrossPrioritization(CrossPrioritization.BuySidePrioritized);
        
        msg.setNoSides(2);
        SideCrossOrdCxlGroup43TestData.getInstance().populate1(msg.getSideCrossOrdCxlGroups()[0]);
        SideCrossOrdCxlGroup43TestData.getInstance().populate2(msg.getSideCrossOrdCxlGroups()[1]);
        
        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());
    }

    public void check(CrossOrderCancelRequestMsg expected, CrossOrderCancelRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getCrossID(), actual.getCrossID());
        assertEquals(expected.getOrigCrossID(), actual.getOrigCrossID());
        assertEquals(expected.getCrossType(), actual.getCrossType());
        assertEquals(expected.getCrossPrioritization(), actual.getCrossPrioritization());
        
        assertEquals(expected.getNoSides(), actual.getNoSides());
        SideCrossOrdCxlGroup43TestData.getInstance().check(expected.getSideCrossOrdCxlGroups()[0], actual.getSideCrossOrdCxlGroups()[0]);
        SideCrossOrdCxlGroup43TestData.getInstance().check(expected.getSideCrossOrdCxlGroups()[1], actual.getSideCrossOrdCxlGroups()[1]);
        
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
    }
}
