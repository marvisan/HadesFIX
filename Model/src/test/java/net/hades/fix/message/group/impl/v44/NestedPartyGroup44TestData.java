/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup44TestData.java
 *
 * $Id: NestedPartyGroup44TestData.java,v 1.1 2009-07-06 03:19:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for NestedPartyGroup44 component class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 8:34:33 AM
 */
public class NestedPartyGroup44TestData extends MsgTest {

    private static final NestedPartyGroup44TestData INSTANCE;

    static {
        INSTANCE = new NestedPartyGroup44TestData();
    }

    public static NestedPartyGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix44.component.NestedParties.NoNestedPartyIDs msg) throws Exception {
        msg.setString(quickfix.field.NestedPartyID.FIELD, "ID 1");
        msg.setChar(quickfix.field.NestedPartyIDSource.FIELD, '1');
        msg.setInt(quickfix.field.NestedPartyRole.FIELD, 2);
        msg.setInt(quickfix.field.NoNestedPartySubIDs.FIELD, 2);
        quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs grp1 = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs();
        grp1.setString(quickfix.field.NestedPartySubID.FIELD, "SUB ID 1");
        grp1.setInt(quickfix.field.NestedPartySubIDType.FIELD, 2);
        msg.addGroup(grp1);
        quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs grp2 = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs();
        grp2.setString(quickfix.field.NestedPartySubID.FIELD, "SUB ID 2");
        grp2.setInt(quickfix.field.NestedPartySubIDType.FIELD, 1);
        msg.addGroup(grp2);
    }

    public void populate2(quickfix.fix44.component.NestedParties.NoNestedPartyIDs msg) throws Exception {
        msg.setString(quickfix.field.NestedPartyID.FIELD, "ID 2");
        msg.setChar(quickfix.field.NestedPartyIDSource.FIELD, '2');
        msg.setInt(quickfix.field.NestedPartyRole.FIELD, 2);
        msg.setInt(quickfix.field.NoNestedPartySubIDs.FIELD, 2);
        quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs grp1 = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs();
        grp1.setString(quickfix.field.NestedPartySubID.FIELD, "SUB ID 3");
        grp1.setInt(quickfix.field.NestedPartySubIDType.FIELD, 3);
        msg.addGroup(grp1);
        quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs grp2 = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs();
        grp2.setString(quickfix.field.NestedPartySubID.FIELD, "SUB ID 4");
        grp2.setInt(quickfix.field.NestedPartySubIDType.FIELD, 4);
        msg.addGroup(grp2);
    }

    public void populate1(NestedPartyGroup component) {
        component.setNestedPartyID("ID 1");
        component.setNestedPartyIDSource(PartyIDSource.CSD);
        component.setNestedPartyRole(PartyRole.AllocationEntity);
        component.setNoNestedPartySubIDs(new Integer(2));
        component.getNstdPtysSubGroups()[0].setNestedPartySubID("sub id 1");
        component.getNstdPtysSubGroups()[0].setNestedPartySubIDType(PartySubIDType.CashAccountNumber);
        component.getNstdPtysSubGroups()[1].setNestedPartySubID("sub id 2");
        component.getNstdPtysSubGroups()[1].setNestedPartySubIDType(PartySubIDType.Application);
    }

    public void populate2(NestedPartyGroup component) {
        component.setNestedPartyID("ID 2");
        component.setNestedPartyIDSource(PartyIDSource.CSD);
        component.setNestedPartyRole(PartyRole.AllocationEntity);
        component.setNoNestedPartySubIDs(new Integer(2));
        component.getNstdPtysSubGroups()[0].setNestedPartySubID("sub id 3");
        component.getNstdPtysSubGroups()[0].setNestedPartySubIDType(PartySubIDType.CurrencyDeliveryIdentifier);
        component.getNstdPtysSubGroups()[1].setNestedPartySubID("sub id 4");
        component.getNstdPtysSubGroups()[1].setNestedPartySubIDType(PartySubIDType.ContactName);
    }

    public void check(NestedPartyGroup expected, quickfix.fix44.component.NestedParties.NoNestedPartyIDs actual) throws Exception {
        assertEquals(expected.getNestedPartyID(), actual.getString(quickfix.field.NestedPartyID.FIELD));
        assertEquals(expected.getNestedPartyIDSource().getValue(), actual.getChar(quickfix.field.NestedPartyIDSource.FIELD));
        assertEquals(expected.getNestedPartyRole().getValue(), actual.getInt(quickfix.field.NestedPartyRole.FIELD));
        for (int i = 0; i < expected.getNoNestedPartySubIDs().intValue(); i++) {
            quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs grp = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs();
            actual.getGroup(i + 1, grp);
            assertEquals(expected.getNstdPtysSubGroups()[i].getNestedPartySubID(), grp.getString(quickfix.field.NestedPartySubID.FIELD));
            assertEquals(expected.getNstdPtysSubGroups()[i].getNestedPartySubIDType().getValue(), grp.getInt(quickfix.field.NestedPartySubIDType.FIELD));
        }
    }

    public void check(quickfix.fix44.component.NestedParties.NoNestedPartyIDs expected, NestedPartyGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.NestedPartyID.FIELD), actual.getNestedPartyID());
        assertEquals(expected.getChar(quickfix.field.NestedPartyIDSource.FIELD), actual.getNestedPartyIDSource().getValue());
        assertEquals(expected.getInt(quickfix.field.NestedPartyRole.FIELD), actual.getNestedPartyRole().getValue());
        for (int i = 0; i < actual.getNoNestedPartySubIDs().intValue(); i++) {
            quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs grp = new quickfix.fix44.component.NestedParties.NoNestedPartyIDs.NoNestedPartySubIDs();
            expected.getGroup(i + 1, grp);
            assertEquals(grp.getString(quickfix.field.NestedPartySubID.FIELD), actual.getNstdPtysSubGroups()[i].getNestedPartySubID());
            assertEquals(grp.getInt(quickfix.field.NestedPartySubIDType.FIELD), actual.getNstdPtysSubGroups()[i].getNestedPartySubIDType().getValue());
        }
    }

    public void check(NestedPartyGroup expected, NestedPartyGroup actual) throws Exception {
        assertEquals(expected.getNestedPartyID(), actual.getNestedPartyID());
        assertEquals(expected.getNestedPartyIDSource().getValue(), actual.getNestedPartyIDSource().getValue());
        assertEquals(expected.getNestedPartyRole().getValue(), actual.getNestedPartyRole().getValue());
        for (int i = 0; i < expected.getNoNestedPartySubIDs().intValue(); i++) {
            assertEquals(expected.getNstdPtysSubGroups()[i].getNestedPartySubID(), actual.getNstdPtysSubGroups()[i].getNestedPartySubID());
            assertEquals(expected.getNstdPtysSubGroups()[i].getNestedPartySubIDType().getValue(), actual.getNstdPtysSubGroups()[i].getNestedPartySubIDType().getValue());
        }
    }
}
