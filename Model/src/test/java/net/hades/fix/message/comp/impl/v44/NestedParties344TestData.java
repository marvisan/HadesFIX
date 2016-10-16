/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties344TestData.java
 *
 * $Id: NestedParties344TestData.java,v 1.1 2011-09-09 08:05:25 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.NestedParties3;
import net.hades.fix.message.group.impl.v50sp1.Nested3PartyGroup50SP1TestData;

/**
 * Test utility for NestedParties3 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class NestedParties344TestData extends MsgTest {

    private static final NestedParties344TestData INSTANCE;

    static {
        INSTANCE = new NestedParties344TestData();
    }

    public static NestedParties344TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NestedParties3 component) {
        component.setNoNested3PartyIDs(new Integer(2));
        Nested3PartyGroup50SP1TestData.getInstance().populate1(component.getNested3PartyIDGroups()[0]);
        Nested3PartyGroup50SP1TestData.getInstance().populate2(component.getNested3PartyIDGroups()[1]);
    }

    public void check(NestedParties3 expected, NestedParties3 actual) throws Exception {
        assertEquals(expected.getNoNested3PartyIDs().intValue(), actual.getNoNested3PartyIDs().intValue());
        for (int i = 0; i < expected.getNoNested3PartyIDs().intValue(); i++) {
            Nested3PartyGroup50SP1TestData.getInstance().check(expected.getNested3PartyIDGroups()[i], actual.getNested3PartyIDGroups()[i]);
        }
    }
}
