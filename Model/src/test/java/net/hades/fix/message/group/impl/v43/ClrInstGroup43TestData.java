/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ClrInstGroup43TestData.java
 *
 * $Id: ClrInstGroup43TestData.java,v 1.1 2011-10-25 08:29:23 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.ClrInstGroup;
import net.hades.fix.message.type.ClearingInstruction;

/**
 * Test utility for ClrInstGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class ClrInstGroup43TestData extends MsgTest {

    private static final ClrInstGroup43TestData INSTANCE;

    static {
        INSTANCE = new ClrInstGroup43TestData();
    }

    public static ClrInstGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(ClrInstGroup grp) throws UnsupportedEncodingException {
        grp.setClearingInstruction(ClearingInstruction.ExClearing);
    }

    public void populate2(ClrInstGroup grp) throws UnsupportedEncodingException {
        grp.setClearingInstruction(ClearingInstruction.CustomerTrade);
    }

    public void check(ClrInstGroup expected, ClrInstGroup actual) throws Exception {
        assertEquals(expected.getClearingInstruction(), actual.getClearingInstruction());
    }
}
