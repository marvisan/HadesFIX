/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SideTrdRegTimestamps50TestData.java
 *
 * $Id: SideTrdRegTimestamps50TestData.java,v 1.1 2011-10-25 08:29:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.SideTrdRegTimestampsGroup;
import net.hades.fix.message.type.TrdRegTimestampType;

/**
 * Test utility for TrdRegTimestamps50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class SideTrdRegTimestamps50TestData extends MsgTest {

    private static final SideTrdRegTimestamps50TestData INSTANCE;

    static {
        INSTANCE = new SideTrdRegTimestamps50TestData();
    }

    public static SideTrdRegTimestamps50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SideTrdRegTimestampsGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        cal.set(2101, 5, 12, 15, 15, 15);
        group.setSideTrdRegTimestamp(cal.getTime());
        group.setSideTrdRegTimestampType(TrdRegTimestampType.ExecutionTime);
        group.setSideTrdRegTimestampSrc("A");
    }

    public void populate2(SideTrdRegTimestampsGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        cal.set(2101, 5, 12, 16, 16, 16);
        group.setSideTrdRegTimestamp(cal.getTime());
        group.setSideTrdRegTimestampType(TrdRegTimestampType.BrokerExecution);
        group.setSideTrdRegTimestampSrc("B");
    }

    public void check(SideTrdRegTimestampsGroup expected, SideTrdRegTimestampsGroup actual) throws Exception {
        assertUTCTimestampEquals(expected.getSideTrdRegTimestamp(), actual.getSideTrdRegTimestamp(), false);
        assertEquals(expected.getSideTrdRegTimestampType(), actual.getSideTrdRegTimestampType());
        assertEquals(expected.getSideTrdRegTimestampSrc(), actual.getSideTrdRegTimestampSrc());
    }
}
