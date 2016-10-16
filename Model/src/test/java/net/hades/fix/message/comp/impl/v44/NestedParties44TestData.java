/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties44TestData.java
 *
 * $Id: NestedParties44TestData.java,v 1.1 2009-07-06 03:18:51 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.group.impl.v44.NestedPartyGroup44TestData;

/**
 * Test utility for NestedParties44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class NestedParties44TestData extends MsgTest {

    private static final NestedParties44TestData INSTANCE;

    static {
        INSTANCE = new NestedParties44TestData();
    }

    public static NestedParties44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.component.NestedParties msg) throws Exception {
        msg.setInt(quickfix.field.NoNestedPartyIDs.FIELD, 2);
        quickfix.fix44.component.NestedParties.NoNestedPartyIDs grp1 = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs();
        NestedPartyGroup44TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix44.component.NestedParties.NoNestedPartyIDs grp2 = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs();
        NestedPartyGroup44TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(NestedParties component) {
        component.setNoNestedPartyIDs(new Integer(2));
        NestedPartyGroup44TestData.getInstance().populate1(component.getNestedPartyIDGroups()[0]);
        NestedPartyGroup44TestData.getInstance().populate2(component.getNestedPartyIDGroups()[1]);
    }

    public void check(NestedParties expected, quickfix.fix44.component.NestedParties actual) throws Exception {
        assertEquals(expected.getNoNestedPartyIDs().intValue(), actual.getInt(quickfix.field.NoNestedPartyIDs.FIELD));
        for (int i = 0; i < expected.getNoNestedPartyIDs().intValue(); i++) {
            quickfix.fix44.component.NestedParties.NoNestedPartyIDs grp = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs();
            actual.getGroup(i + 1, grp);
            NestedPartyGroup44TestData.getInstance().check(expected.getNestedPartyIDGroups()[i], grp);
        }
    }

    public void check(quickfix.fix44.component.NestedParties expected, NestedParties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoNestedPartyIDs.FIELD), actual.getNoNestedPartyIDs().intValue());
        for (int i = 0; i < actual.getNoNestedPartyIDs().intValue(); i++) {
            quickfix.fix44.component.NestedParties.NoNestedPartyIDs grp = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs();
            expected.getGroup(i + 1, grp);
            NestedPartyGroup44TestData.getInstance().check(grp, actual.getNestedPartyIDGroups()[i]);
        }
    }

    public void check(NestedParties expected, NestedParties actual) throws Exception {
        assertEquals(expected.getNoNestedPartyIDs().intValue(), actual.getNoNestedPartyIDs().intValue());
        for (int i = 0; i < expected.getNoNestedPartyIDs().intValue(); i++) {
            NestedPartyGroup44TestData.getInstance().check(expected.getNestedPartyIDGroups()[i], actual.getNestedPartyIDGroups()[i]);
        }
    }
}
