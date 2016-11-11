/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndInstrmtCollGroup50SP1TestData.java
 *
 * $Id: UndInstrmtCollGroup50SP1TestData.java,v 1.2 2011-10-29 09:42:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.UndInstrmtCollGroup;
import net.hades.fix.message.type.CollAction;

/**
 * Test utility for UndInstrmtCollGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 17/12/2011, 10:08:38 AM
 */
public class UndInstrmtCollGroup50SP1TestData extends MsgTest {

    private static final UndInstrmtCollGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new UndInstrmtCollGroup50SP1TestData();
    }

    public static UndInstrmtCollGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UndInstrmtCollGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setCollAction(CollAction.Retain);
    }

    public void populate2(UndInstrmtCollGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setCollAction(CollAction.Add);
    }

    public void check(UndInstrmtCollGroup expected, UndInstrmtCollGroup actual) throws Exception {
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getCollAction(), actual.getCollAction());
    }
}
