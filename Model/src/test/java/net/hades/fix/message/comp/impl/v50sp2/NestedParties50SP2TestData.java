/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties50SP2TestData.java
 *
 * $Id: NestedParties50SP2TestData.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.group.impl.v50sp2.NestedPartyGroup50SP2TestData;

/**
 * Test utility for NestedParties50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class NestedParties50SP2TestData extends MsgTest {

    private static final NestedParties50SP2TestData INSTANCE;

    static {
        INSTANCE = new NestedParties50SP2TestData();
    }

    public static NestedParties50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NestedParties component) {
        component.setNoNestedPartyIDs(new Integer(2));
        NestedPartyGroup50SP2TestData.getInstance().populate1(component.getNestedPartyIDGroups()[0]);
        NestedPartyGroup50SP2TestData.getInstance().populate2(component.getNestedPartyIDGroups()[1]);
    }

    public void check(NestedParties expected, NestedParties actual) throws Exception {
        assertEquals(expected.getNoNestedPartyIDs().intValue(), actual.getNoNestedPartyIDs().intValue());
        for (int i = 0; i < expected.getNoNestedPartyIDs().intValue(); i++) {
            NestedPartyGroup50SP2TestData.getInstance().check(expected.getNestedPartyIDGroups()[i], actual.getNestedPartyIDGroups()[i]);
        }
    }
}
