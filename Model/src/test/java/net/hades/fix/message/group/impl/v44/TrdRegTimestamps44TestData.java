/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRegTimestamps44TestData.java
 *
 * $Id: TrdRegTimestamps44TestData.java,v 1.1 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
import net.hades.fix.message.type.TrdRegTimestampType;

/**
 * Test utility for TrdRegTimestamps44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class TrdRegTimestamps44TestData extends MsgTest {

    private static final TrdRegTimestamps44TestData INSTANCE;

    static {
        INSTANCE = new TrdRegTimestamps44TestData();
    }

    public static TrdRegTimestamps44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdRegTimestampsGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        cal.set(2101, 5, 12, 15, 15, 15);
        group.setTrdRegTimestamp(cal.getTime());
        group.setTrdRegTimestampType(TrdRegTimestampType.ExecutionTime);
        group.setTrdRegTimestampOrigin("exchange");
    }

    public void populate2(TrdRegTimestampsGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        cal.set(2101, 5, 12, 16, 16, 16);
        group.setTrdRegTimestamp(cal.getTime());
        group.setTrdRegTimestampType(TrdRegTimestampType.BrokerExecution);
        group.setTrdRegTimestampOrigin("other exchange");
    }

    public void check(TrdRegTimestampsGroup expected, TrdRegTimestampsGroup actual) throws Exception {
        assertUTCTimestampEquals(expected.getTrdRegTimestamp(), actual.getTrdRegTimestamp(), false);
        assertEquals(expected.getTrdRegTimestampType(), actual.getTrdRegTimestampType());
        assertEquals(expected.getTrdRegTimestampOrigin(), actual.getTrdRegTimestampOrigin());
    }
}
