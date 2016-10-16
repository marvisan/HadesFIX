/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties244TestData.java
 *
 * $Id: NestedParties244TestData.java,v 1.1 2011-09-09 08:05:25 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.NestedParties2;
import net.hades.fix.message.group.impl.v50sp1.Nested2PartyGroup50SP1TestData;

/**
 * Test utility for NestedParties2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class NestedParties244TestData extends MsgTest {

    private static final NestedParties244TestData INSTANCE;

    static {
        INSTANCE = new NestedParties244TestData();
    }

    public static NestedParties244TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NestedParties2 component) {
        component.setNoNested2PartyIDs(new Integer(2));
        Nested2PartyGroup50SP1TestData.getInstance().populate1(component.getNested2PartyIDGroups()[0]);
        Nested2PartyGroup50SP1TestData.getInstance().populate2(component.getNested2PartyIDGroups()[1]);
    }

    public void check(NestedParties2 expected, NestedParties2 actual) throws Exception {
        assertEquals(expected.getNoNested2PartyIDs().intValue(), actual.getNoNested2PartyIDs().intValue());
        for (int i = 0; i < expected.getNoNested2PartyIDs().intValue(); i++) {
            Nested2PartyGroup50SP1TestData.getInstance().check(expected.getNested2PartyIDGroups()[i], actual.getNested2PartyIDGroups()[i]);
        }
    }
}
