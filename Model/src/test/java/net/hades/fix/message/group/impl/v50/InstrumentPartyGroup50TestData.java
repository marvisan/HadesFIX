/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartySubIDGroup50TestData.java
 *
 * $Id: InstrumentPartyGroup50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.InstrumentPartyGroup;
import net.hades.fix.message.group.InstrumentPartySubIDGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for InstrumentPartySubGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class InstrumentPartyGroup50TestData extends MsgTest {

    private static final InstrumentPartyGroup50TestData INSTANCE;

    static {
        INSTANCE = new InstrumentPartyGroup50TestData();
    }

    public static InstrumentPartyGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix50.component.InstrumentParties.NoInstrumentParties msg) throws Exception {
        msg.setString(quickfix.field.InstrumentPartyID.FIELD, "ID 1");
        msg.setChar(quickfix.field.InstrumentPartyIDSource.FIELD, '1');
        msg.setInt(quickfix.field.InstrumentPartyRole.FIELD, 2);
        msg.setInt(quickfix.field.NoInstrumentPartySubIDs.FIELD, 2);
        quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs grp1 =
            new quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs();
        grp1.setString(quickfix.field.InstrumentPartySubID.FIELD, "SUB ID 1");
        grp1.setInt(quickfix.field.InstrumentPartySubIDType.FIELD, 2);
        msg.addGroup(grp1);
        quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs grp2 =
            new quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs();
        grp2.setString(quickfix.field.InstrumentPartySubID.FIELD, "SUB ID 2");
        grp2.setInt(quickfix.field.InstrumentPartySubIDType.FIELD, 1);
        msg.addGroup(grp2);
    }

    public void populate2(quickfix.fix50.component.InstrumentParties.NoInstrumentParties msg) throws Exception {
        msg.setString(quickfix.field.InstrumentPartyID.FIELD, "ID 2");
        msg.setChar(quickfix.field.InstrumentPartyIDSource.FIELD, '2');
        msg.setInt(quickfix.field.InstrumentPartyRole.FIELD, 2);
        msg.setInt(quickfix.field.NoInstrumentPartySubIDs.FIELD, 2);
        quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs grp1 =
            new quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs();
        grp1.setString(quickfix.field.InstrumentPartySubID.FIELD, "SUB ID 3");
        grp1.setInt(quickfix.field.InstrumentPartySubIDType.FIELD, 3);
        msg.addGroup(grp1);
        quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs grp2 =
            new quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs();
        grp2.setString(quickfix.field.InstrumentPartySubID.FIELD, "SUB ID 4");
        grp2.setInt(quickfix.field.InstrumentPartySubIDType.FIELD, 4);
        msg.addGroup(grp2);
    }

    public void populate1(InstrumentPartyGroup component) {
        component.setInstrumentPartyID("ID 1");
        component.setInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setNoInstrumentPartySubIDs(new Integer(2));
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubID("sub id 1");
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubIDType(PartySubIDType.System);
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubID("sub id 2");
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubIDType(PartySubIDType.Application);
    }

    public void populate2(InstrumentPartyGroup component) {
        component.setInstrumentPartyID("ID 2");
        component.setInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setNoInstrumentPartySubIDs(new Integer(2));
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubID("sub id 3");
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubIDType(PartySubIDType.EmailAddress);
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubID("sub id 4");
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubIDType(PartySubIDType.ContactName);
    }

    public void check(InstrumentPartySubIDGroup expected, InstrumentPartySubIDGroup actual) throws Exception {
        assertEquals(expected.getInstrumentPartySubID(), actual.getInstrumentPartySubID());
        assertEquals(expected.getInstrumentPartySubIDType().getValue(), actual.getInstrumentPartySubIDType().getValue());
    }

    public void check(InstrumentPartyGroup expected, quickfix.fix50.component.InstrumentParties.NoInstrumentParties actual) throws Exception {
        assertEquals(expected.getInstrumentPartyID(), actual.getString(quickfix.field.InstrumentPartyID.FIELD));
        assertEquals(expected.getInstrumentPartyIDSource().getValue(), actual.getChar(quickfix.field.InstrumentPartyIDSource.FIELD));
        assertEquals(expected.getInstrumentPartyRole().getValue(), actual.getInt(quickfix.field.InstrumentPartyRole.FIELD));
        for (int i = 0; i < expected.getNoInstrumentPartySubIDs().intValue(); i++) {
            quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs grp =
                new quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs();
            actual.getGroup(i + 1, grp);
            assertEquals(expected.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID(), grp.getString(quickfix.field.InstrumentPartySubID.FIELD));
            assertEquals(expected.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue(), grp.getInt(quickfix.field.InstrumentPartySubIDType.FIELD));
        }
    }

    public void check(quickfix.fix50.component.InstrumentParties.NoInstrumentParties expected, InstrumentPartyGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.InstrumentPartyID.FIELD), actual.getInstrumentPartyID());
        assertEquals(expected.getChar(quickfix.field.InstrumentPartyIDSource.FIELD), actual.getInstrumentPartyIDSource().getValue());
        assertEquals(expected.getInt(quickfix.field.InstrumentPartyRole.FIELD), actual.getInstrumentPartyRole().getValue());
        for (int i = 0; i < actual.getNoInstrumentPartySubIDs().intValue(); i++) {
            quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs grp =
                new quickfix.fix50.component.InstrumentParties.NoInstrumentParties.NoInstrumentPartySubIDs();
            expected.getGroup(i + 1, grp);
            assertEquals(grp.getString(quickfix.field.InstrumentPartySubID.FIELD), actual.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID());
            assertEquals(grp.getInt(quickfix.field.InstrumentPartySubIDType.FIELD), actual.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue());
        }
    }

    public void check(InstrumentPartyGroup expected, InstrumentPartyGroup actual) throws Exception {
        assertEquals(expected.getInstrumentPartyID(), actual.getInstrumentPartyID());
        assertEquals(expected.getInstrumentPartyIDSource().getValue(), actual.getInstrumentPartyIDSource().getValue());
        assertEquals(expected.getInstrumentPartyRole().getValue(), actual.getInstrumentPartyRole().getValue());
        for (int i = 0; i < expected.getNoInstrumentPartySubIDs().intValue(); i++) {
            assertEquals(expected.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID(),
                actual.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID());
            assertEquals(expected.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue(),
                actual.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue());
        }
    }
}
