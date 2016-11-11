/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FillExecGroup50SP1TestData.java
 *
 * $Id: FillExecGroup50SP1TestData.java,v 1.1 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.NestedParties450SP1TestData;
import net.hades.fix.message.group.FillExecGroup;

/**
 * Test utility for FillExecGroup50SP group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class FillExecGroup50SP1TestData extends MsgTest {

    private static final FillExecGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new FillExecGroup50SP1TestData();
    }

    public static FillExecGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(FillExecGroup grp) throws UnsupportedEncodingException {
        grp.setFillExecID("EXEC1");
        grp.setFillPx(45.50d);
        grp.setFillQty(10.0d);

        grp.setNestedParties4();
        NestedParties450SP1TestData.getInstance().populate(grp.getNestedParties4());
    }

    public void populate2(FillExecGroup grp) throws UnsupportedEncodingException {
        grp.setFillExecID("EXEC2");
        grp.setFillPx(85.50d);
        grp.setFillQty(20.0d);

        grp.setNestedParties4();
        NestedParties450SP1TestData.getInstance().populate(grp.getNestedParties4());
    }

    public void check(FillExecGroup expected, FillExecGroup actual) throws Exception {
        assertEquals(expected.getFillExecID(), actual.getFillExecID());
        assertEquals(expected.getFillPx(), actual.getFillPx());
        assertEquals(expected.getFillQty(), actual.getFillQty());

        NestedParties450SP1TestData.getInstance().check(expected.getNestedParties4(), actual.getNestedParties4());
    }
}
