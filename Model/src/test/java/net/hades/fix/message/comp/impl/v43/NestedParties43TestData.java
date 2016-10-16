/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties43TestData.java
 *
 * $Id: NestedParties43TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.group.impl.v43.NestedPartyGroup43TestData;

/**
 * Test utility for NestedParties43 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class NestedParties43TestData extends MsgTest {

    private static final NestedParties43TestData INSTANCE;

    static {
        INSTANCE = new NestedParties43TestData();
    }

    public static NestedParties43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NestedParties component) {
        component.setNoNestedPartyIDs(new Integer(2));
        NestedPartyGroup43TestData.getInstance().populate1(component.getNestedPartyIDGroups()[0]);
        NestedPartyGroup43TestData.getInstance().populate2(component.getNestedPartyIDGroups()[1]);
    }

    public void check(NestedParties expected, NestedParties actual) throws Exception {
        assertEquals(expected.getNoNestedPartyIDs().intValue(), actual.getNoNestedPartyIDs().intValue());
        for (int i = 0; i < expected.getNoNestedPartyIDs().intValue(); i++) {
            NestedPartyGroup43TestData.getInstance().check(expected.getNestedPartyIDGroups()[i], actual.getNestedPartyIDGroups()[i]);
        }
    }
}
