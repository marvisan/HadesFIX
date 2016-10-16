/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NotAffectedOrdGroup50SP1TestData.java
 *
 * $Id: NotAffectedOrdGroup50SP1TestData.java,v 1.1 2011-05-07 06:58:53 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.NotAffectedOrdGroup;

/**
 * Test utility for NotAffectedOrdGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/05/2011, 9:12:46 AM
 */
public class NotAffectedOrdGroup50SP1TestData extends MsgTest {

    private static final NotAffectedOrdGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new NotAffectedOrdGroup50SP1TestData();
    }

    public static NotAffectedOrdGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(NotAffectedOrdGroup grp) throws UnsupportedEncodingException {
        grp.setNotAffOrigClOrdID("CL00998877");
        grp.setNotAffectedOrderID("ORD_554433");
    }

    public void populate2(NotAffectedOrdGroup grp) throws UnsupportedEncodingException {
        grp.setNotAffOrigClOrdID("CLIORD_332211");
        grp.setNotAffectedOrderID("ORD_998877");
    }

    public void check(NotAffectedOrdGroup expected, NotAffectedOrdGroup actual) throws Exception {
        assertEquals(expected.getNotAffOrigClOrdID(), actual.getNotAffOrigClOrdID());
        assertEquals(expected.getNotAffectedOrderID(), actual.getNotAffectedOrderID());
    }
}
