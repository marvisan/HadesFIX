/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecAllocGroup40TestData.java
 *
 * $Id: ExecAllocGroup40TestData.java,v 1.1 2011-02-16 11:24:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v40;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.ExecAllocGroup;

/**
 * Test utility for ExecAllocGroup40 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class ExecAllocGroup40TestData extends MsgTest {

    private static final ExecAllocGroup40TestData INSTANCE;

    static {
        INSTANCE = new ExecAllocGroup40TestData();
    }

    public static ExecAllocGroup40TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(ExecAllocGroup grp) throws UnsupportedEncodingException {
        grp.setLastQty(22.0d);
        grp.setExecID("EXEC384744");
        grp.setLastPx(33.44d);
        grp.setLastMkt("MKT1");
    }

    public void populate2(ExecAllocGroup grp) throws UnsupportedEncodingException {
        grp.setLastQty(23.0d);
        grp.setExecID("EXEC384755");
        grp.setLastPx(33.55d);
        grp.setLastMkt("MKT2");
    }

    public void check(ExecAllocGroup expected, ExecAllocGroup actual) throws Exception {
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
    }
}
