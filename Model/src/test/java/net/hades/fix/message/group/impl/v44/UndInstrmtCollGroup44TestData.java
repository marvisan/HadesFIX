/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndInstrmtCollGroup44TestData.java
 *
 * $Id: UndInstrmtCollGroup44TestData.java,v 1.2 2011-10-29 09:42:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.UndInstrmtCollGroup;
import net.hades.fix.message.type.CollAction;

/**
 * Test utility for UndInstrmtCollGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 17/12/2011, 10:08:38 AM
 */
public class UndInstrmtCollGroup44TestData extends MsgTest {

    private static final UndInstrmtCollGroup44TestData INSTANCE;

    static {
        INSTANCE = new UndInstrmtCollGroup44TestData();
    }

    public static UndInstrmtCollGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UndInstrmtCollGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setCollAction(CollAction.Retain);
    }

    public void populate2(UndInstrmtCollGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setCollAction(CollAction.Add);
    }

    public void check(UndInstrmtCollGroup expected, UndInstrmtCollGroup actual) throws Exception {
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getCollAction(), actual.getCollAction());
    }
}
