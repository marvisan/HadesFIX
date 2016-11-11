/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecCollGroup44TestData.java
 *
 * $Id: ExecCollGroup44TestData.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.ExecCollGroup;

/**
 * Test utility for ExecCollGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/012/2011, 9:12:46 AM
 */
public class ExecCollGroup44TestData extends MsgTest {

    private static final ExecCollGroup44TestData INSTANCE;

    static {
        INSTANCE = new ExecCollGroup44TestData();
    }

    public static ExecCollGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(ExecCollGroup grp) throws UnsupportedEncodingException {
        grp.setExecID("EXEC_1111");
    }

    public void populate2(ExecCollGroup grp) throws UnsupportedEncodingException {
        grp.setExecID("EXEC_2222");
    }

    public void check(ExecCollGroup expected, ExecCollGroup actual) throws Exception {
        assertEquals(expected.getExecID(), actual.getExecID());
    }
}
