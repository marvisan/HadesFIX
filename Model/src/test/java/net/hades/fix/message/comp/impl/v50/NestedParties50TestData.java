/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties50TestData.java
 *
 * $Id: NestedParties50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.group.impl.v50.NestedPartyGroup50TestData;

/**
 * Test utility for NestedParties50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class NestedParties50TestData extends MsgTest {

    private static final NestedParties50TestData INSTANCE;

    static {
        INSTANCE = new NestedParties50TestData();
    }

    public static NestedParties50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix50.component.NestedParties msg) throws Exception {
        msg.setInt(quickfix.field.NoNestedPartyIDs.FIELD, 2);
        quickfix.fix50.component.NestedParties.NoNestedPartyIDs grp1 = new quickfix.fix50.component.NestedParties.NoNestedPartyIDs();
        NestedPartyGroup50TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix50.component.NestedParties.NoNestedPartyIDs grp2 = new quickfix.fix50.component.NestedParties.NoNestedPartyIDs();
        NestedPartyGroup50TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(NestedParties component) {
        component.setNoNestedPartyIDs(new Integer(2));
        NestedPartyGroup50TestData.getInstance().populate1(component.getNestedPartyIDGroups()[0]);
        NestedPartyGroup50TestData.getInstance().populate2(component.getNestedPartyIDGroups()[1]);
    }

    public void check(NestedParties expected, quickfix.fix50.component.NestedParties actual) throws Exception {
        assertEquals(expected.getNoNestedPartyIDs().intValue(), actual.getInt(quickfix.field.NoNestedPartyIDs.FIELD));
        for (int i = 0; i < expected.getNoNestedPartyIDs().intValue(); i++) {
            quickfix.fix50.component.NestedParties.NoNestedPartyIDs grp = new quickfix.fix50.component.NestedParties.NoNestedPartyIDs();
            actual.getGroup(i + 1, grp);
            NestedPartyGroup50TestData.getInstance().check(expected.getNestedPartyIDGroups()[i], grp);
        }
    }

    public void check(quickfix.fix50.component.NestedParties expected, NestedParties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoNestedPartyIDs.FIELD), actual.getNoNestedPartyIDs().intValue());
        for (int i = 0; i < actual.getNoNestedPartyIDs().intValue(); i++) {
            quickfix.fix50.component.NestedParties.NoNestedPartyIDs grp = new quickfix.fix50.component.NestedParties.NoNestedPartyIDs();
            expected.getGroup(i + 1, grp);
            NestedPartyGroup50TestData.getInstance().check(grp, actual.getNestedPartyIDGroups()[i]);
        }
    }

    public void check(NestedParties expected, NestedParties actual) throws Exception {
        assertEquals(expected.getNoNestedPartyIDs().intValue(), actual.getNoNestedPartyIDs().intValue());
        for (int i = 0; i < expected.getNoNestedPartyIDs().intValue(); i++) {
            NestedPartyGroup50TestData.getInstance().check(expected.getNestedPartyIDGroups()[i], actual.getNestedPartyIDGroups()[i]);
        }
    }
}
