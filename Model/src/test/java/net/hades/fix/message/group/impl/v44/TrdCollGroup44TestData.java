/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdCollGroup44TestData.java
 *
 * $Id: TrdCollGroup44TestData.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.TrdCollGroup;

/**
 * Test utility for TrdCollGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/12/2011, 9:12:46 AM
 */
public class TrdCollGroup44TestData extends MsgTest {

    private static final TrdCollGroup44TestData INSTANCE;

    static {
        INSTANCE = new TrdCollGroup44TestData();
    }

    public static TrdCollGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdCollGroup grp) throws UnsupportedEncodingException {
        grp.setTradeReportID("TRD_REP_5555");
        grp.setSecondaryTradeReportID("SEC_TRD_REP_6666");
    }

    public void populate2(TrdCollGroup grp) throws UnsupportedEncodingException {
        grp.setTradeReportID("TRD_REP_7777");
        grp.setSecondaryTradeReportID("SEC_TRD_REP_8888");
    }

    public void check(TrdCollGroup expected, TrdCollGroup actual) throws Exception {
        assertEquals(expected.getTradeReportID(), actual.getTradeReportID());
        assertEquals(expected.getSecondaryTradeReportID(), actual.getSecondaryTradeReportID());
    }
}
