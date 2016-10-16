/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderCancelRequestMsg44TestData.java
 *
 * $Id: CrossOrderCancelRequestMsg44TestData.java,v 1.1 2011-05-21 23:53:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CrossOrderCancelRequestMsg;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.impl.v44.SideCrossOrdCxlGroup44TestData;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;

/**
 * Test utility for CrossOrderCancelRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/05/2011, 12:08:30 PM
 */
public class CrossOrderCancelRequestMsg44TestData extends MsgTest {

    private static final CrossOrderCancelRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new CrossOrderCancelRequestMsg44TestData();
    }

    public static CrossOrderCancelRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(CrossOrderCancelRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setOrderID("ORD7765544");
        msg.setCrossID("AAA564567");
        msg.setOrigCrossID("ORIG_887733");
        msg.setCrossType(CrossType.CrossAON);
        msg.setCrossPrioritization(CrossPrioritization.BuySidePrioritized);
        
        msg.setNoSides(2);
        SideCrossOrdCxlGroup44TestData.getInstance().populate1(msg.getSideCrossOrdCxlGroups()[0]);
        SideCrossOrdCxlGroup44TestData.getInstance().populate2(msg.getSideCrossOrdCxlGroups()[1]);
        
        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());
       
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
        
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

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
        SideCrossOrdCxlGroup44TestData.getInstance().check(expected.getSideCrossOrdCxlGroups()[0], actual.getSideCrossOrdCxlGroups()[0]);
        SideCrossOrdCxlGroup44TestData.getInstance().check(expected.getSideCrossOrdCxlGroups()[1], actual.getSideCrossOrdCxlGroups()[1]);
        
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
    }
}
