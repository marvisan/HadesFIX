/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties50TestData.java
 *
 * $Id: NestedParties450SP1TestData.java,v 1.1 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.NestedParties4;
import net.hades.fix.message.group.impl.v50sp1.Nested4PartyGroup50SP1TestData;

/**
 * Test utility for NestedParties50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class NestedParties450SP1TestData extends MsgTest {

    private static final NestedParties450SP1TestData INSTANCE;

    static {
        INSTANCE = new NestedParties450SP1TestData();
    }

    public static NestedParties450SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NestedParties4 component) {
        component.setNoNested4PartyIDs(new Integer(2));
        Nested4PartyGroup50SP1TestData.getInstance().populate1(component.getNested4PartyIDGroups()[0]);
        Nested4PartyGroup50SP1TestData.getInstance().populate2(component.getNested4PartyIDGroups()[1]);
    }

    public void check(NestedParties4 expected, NestedParties4 actual) throws Exception {
        assertEquals(expected.getNoNested4PartyIDs().intValue(), actual.getNoNested4PartyIDs().intValue());
        for (int i = 0; i < expected.getNoNested4PartyIDs().intValue(); i++) {
            Nested4PartyGroup50SP1TestData.getInstance().check(expected.getNested4PartyIDGroups()[i], actual.getNested4PartyIDGroups()[i]);
        }
    }
}
