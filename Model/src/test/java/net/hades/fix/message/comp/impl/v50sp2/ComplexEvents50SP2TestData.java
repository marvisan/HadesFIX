/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEvents50SP2TestData.java
 *
 * $Id: ComplexEvents50SP2TestData.java,v 1.2 2011-09-28 08:10:21 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import java.util.Date;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.ComplexEvents;
import net.hades.fix.message.type.ComplexEventCondition;
import net.hades.fix.message.type.ComplexEventPriceBoundaryMethod;
import net.hades.fix.message.type.ComplexEventPriceTimeType;
import net.hades.fix.message.type.ComplexEventType;

/**
 * Test utility for ComplexEvents50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class ComplexEvents50SP2TestData extends MsgTest {

    private static final ComplexEvents50SP2TestData INSTANCE;

    static {
        INSTANCE = new ComplexEvents50SP2TestData();
    }

    public static ComplexEvents50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ComplexEvents comp) {
        comp.setComplexEventType(ComplexEventType.Capped);
        comp.setComplexOptPayoutAmount(new Double(12.55));
        comp.setComplexEventPrice(new Double(123.55));
        comp.setComplexEventPriceBoundaryMethod(ComplexEventPriceBoundaryMethod.EqualTo);
        comp.setComplexEventPriceBoundaryPrecision(new Double(1.55));
        comp.setComplexEventPriceTimeType(ComplexEventPriceTimeType.Expiration);
        comp.setComplexEventCondition(ComplexEventCondition.And);
        comp.setComplexEventDates();
        comp.getComplexEventDates().setNoComplexEventDates(new Integer(2));
        comp.getComplexEventDates().getComplexEventDateGroups()[0].setComplexEventStartDate(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[0].setComplexEventEndDate(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[0].setComplexEventTimes();
        comp.getComplexEventDates().getComplexEventDateGroups()[0].getComplexEventTimes().setNoComplexEventTimes(new Integer(2));
        comp.getComplexEventDates().getComplexEventDateGroups()[0].getComplexEventTimes().getComplexEventTimeGroups()[0].setComplexEventStartTime(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[0].getComplexEventTimes().getComplexEventTimeGroups()[0].setComplexEventEndTime(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[0].getComplexEventTimes().getComplexEventTimeGroups()[1].setComplexEventStartTime(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[0].getComplexEventTimes().getComplexEventTimeGroups()[1].setComplexEventEndTime(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[1].setComplexEventStartDate(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[1].setComplexEventEndDate(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[1].setComplexEventTimes();
        comp.getComplexEventDates().getComplexEventDateGroups()[1].getComplexEventTimes().setNoComplexEventTimes(new Integer(2));
        comp.getComplexEventDates().getComplexEventDateGroups()[1].getComplexEventTimes().getComplexEventTimeGroups()[0].setComplexEventStartTime(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[1].getComplexEventTimes().getComplexEventTimeGroups()[0].setComplexEventEndTime(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[1].getComplexEventTimes().getComplexEventTimeGroups()[1].setComplexEventStartTime(new Date());
        comp.getComplexEventDates().getComplexEventDateGroups()[1].getComplexEventTimes().getComplexEventTimeGroups()[1].setComplexEventEndTime(new Date());
    }

    public void check(ComplexEvents expected, ComplexEvents actual) {
        assertEquals(expected.getComplexEventType(), actual.getComplexEventType());
        assertEquals(expected.getComplexOptPayoutAmount(), actual.getComplexOptPayoutAmount());
        assertEquals(expected.getComplexEventPrice().doubleValue(), actual.getComplexEventPrice().doubleValue(), 0.001);
        assertEquals(expected.getComplexEventPriceBoundaryMethod(), actual.getComplexEventPriceBoundaryMethod());
        assertEquals(expected.getComplexEventPriceBoundaryPrecision().doubleValue(), actual.getComplexEventPriceBoundaryPrecision().doubleValue(), 0.001);
        assertEquals(expected.getComplexEventPriceTimeType(), actual.getComplexEventPriceTimeType());
        assertEquals(expected.getComplexEventCondition(), actual.getComplexEventCondition());
        assertNotNull(actual.getComplexEventDates());
        assertEquals(expected.getComplexEventDates().getNoComplexEventDates(),
            actual.getComplexEventDates().getNoComplexEventDates());
        for (int i = 0; i < actual.getComplexEventDates().getNoComplexEventDates().intValue(); i++) {
            assertUTCTimestampEquals(expected.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventStartDate(),
                actual.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventStartDate(), false);
            assertUTCTimestampEquals(expected.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventEndDate(),
                actual.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventEndDate(), false);
            assertNotNull(actual.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes());
            assertEquals(expected.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes().getNoComplexEventTimes(),
                actual.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes().getNoComplexEventTimes());
            for (int j = 0; j < actual.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes().getNoComplexEventTimes().intValue(); j++) {
                assertUTCTimeEquals(expected.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes().getComplexEventTimeGroups()[j].getComplexEventStartTime(),
                    actual.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes().getComplexEventTimeGroups()[j].getComplexEventStartTime(),
                    false);
                assertUTCTimeEquals(expected.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes().getComplexEventTimeGroups()[j].getComplexEventEndTime(),
                    actual.getComplexEventDates().getComplexEventDateGroups()[i].getComplexEventTimes().getComplexEventTimeGroups()[j].getComplexEventEndTime(),
                    false);
            }
        }
    }
}
