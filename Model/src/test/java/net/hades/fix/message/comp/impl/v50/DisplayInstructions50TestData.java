/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DisplayInstructions50TestData.java
 *
 * $Id: DisplayInstructions50TestData.java,v 1.2 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.type.DisplayMethod;
import net.hades.fix.message.type.DisplayWhen;

/**
 * Test utility for DisplayInstructions50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/03/2009, 3:30:53 PM
 */
public class DisplayInstructions50TestData extends MsgTest {

    private static final DisplayInstructions50TestData INSTANCE;

    static {
        INSTANCE = new DisplayInstructions50TestData();
    }

    public static DisplayInstructions50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DisplayInstruction comp) {
        comp.setDisplayQty(34.55d);
        comp.setSecondaryDisplayQty(44.44d);
        comp.setDisplayWhen(DisplayWhen.AfterEachFill);
        comp.setDisplayMethod(DisplayMethod.Initial);
        comp.setDisplayLowQty(33.55d);
        comp.setDisplayHighQty(36.77d);
        comp.setDisplayMinIncr(4.5d);
        comp.setRefreshQty(3.3d);
    }

    public void check(DisplayInstruction expected, DisplayInstruction actual) {
        assertEquals(expected.getDisplayQty(), actual.getDisplayQty());
        assertEquals(expected.getSecondaryDisplayQty(), actual.getSecondaryDisplayQty());
        assertEquals(expected.getDisplayWhen(), actual.getDisplayWhen());
        assertEquals(expected.getDisplayMethod(), actual.getDisplayMethod());
        assertEquals(expected.getDisplayLowQty(), actual.getDisplayLowQty());
        assertEquals(expected.getDisplayHighQty(), actual.getDisplayHighQty());
        assertEquals(expected.getDisplayMinIncr(), actual.getDisplayMinIncr());
        assertEquals(expected.getRefreshQty(), actual.getRefreshQty());
    }
}
