/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdCapDtGroup43TestData.java
 *
 * $Id: TrdCapDtGroup43TestData.java,v 1.1 2011-10-08 08:43:05 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.TrdCapDtGroup;

/**
 * Test utility for TrdCapDtGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class TrdCapDtGroup43TestData extends MsgTest {

    private static final TrdCapDtGroup43TestData INSTANCE;

    static {
        INSTANCE = new TrdCapDtGroup43TestData();
    }

    public static TrdCapDtGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdCapDtGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        cal.set(2011, 10, 11, 10, 23, 34);
        grp.setTradeDate(cal.getTime());
        
        cal.set(2011, 10, 11, 11, 12, 22);
        grp.setTransactTime(cal.getTime());
    }

    public void populate2(TrdCapDtGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        cal.set(2011, 11, 11, 10, 23, 34);
        grp.setTradeDate(cal.getTime());
        
        cal.set(2011, 11, 11, 11, 12, 22);
        grp.setTransactTime(cal.getTime());
    }

    public void check(TrdCapDtGroup expected, TrdCapDtGroup actual) throws Exception {
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
    }
}
