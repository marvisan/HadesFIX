/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootParties50TestData.java
 *
 * $Id: RootParties50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for RootParties50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class RootParties50TestData extends MsgTest {

    private static final RootParties50TestData INSTANCE;

    static {
        INSTANCE = new RootParties50TestData();
    }

    public static RootParties50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RootParties component) {
        component.setNoRootPartyIDs(new Integer(2));
        component.getRootPartyIDGroups()[0].setNoRootPartySubIDs(new Integer(2));
        component.getRootPartyIDGroups()[0].setRootPartyID("party ID 1");
        component.getRootPartyIDGroups()[0].setRootPartyIDSource(PartyIDSource.BIC);
        component.getRootPartyIDGroups()[0].setRootPartyRole(PartyRole.Agent);
        component.getRootPartyIDGroups()[0].setNoRootPartySubIDs(new Integer(2));
        component.getRootPartyIDGroups()[0].getRootPartySubGroups()[0].setRootPartySubID("party sub ID 11");
        component.getRootPartyIDGroups()[0].getRootPartySubGroups()[0].setRootPartySubIDType(PartySubIDType.BIC);
        component.getRootPartyIDGroups()[0].getRootPartySubGroups()[1].setRootPartySubID("party sub ID 12");
        component.getRootPartyIDGroups()[0].getRootPartySubGroups()[1].setRootPartySubIDType(PartySubIDType.CSDParticipantMemberCode);

        component.getRootPartyIDGroups()[1].setRootPartyID("party ID 2");
        component.getRootPartyIDGroups()[1].setRootPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        component.getRootPartyIDGroups()[1].setRootPartyRole(PartyRole.AllocationEntity);
        component.getRootPartyIDGroups()[1].setNoRootPartySubIDs(new Integer(2));
        component.getRootPartyIDGroups()[1].getRootPartySubGroups()[0].setRootPartySubID("party sub ID 21");
        component.getRootPartyIDGroups()[1].getRootPartySubGroups()[0].setRootPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        component.getRootPartyIDGroups()[1].getRootPartySubGroups()[1].setRootPartySubID("party sub ID 22");
        component.getRootPartyIDGroups()[1].getRootPartySubGroups()[1].setRootPartySubIDType(PartySubIDType.CashAccountName);
    }

    public void populate(quickfix.fix50.component.RootParties component) {
        component.setInt(quickfix.field.NoRootPartyIDs.FIELD, 2);
        quickfix.fix50.component.RootParties.NoRootPartyIDs part1 = new quickfix.fix50.component.RootParties.NoRootPartyIDs();
        part1.setString(quickfix.field.RootPartyID.FIELD, "party id 1");
        part1.setChar(quickfix.field.RootPartyIDSource.FIELD, quickfix.field.PartyIDSource.CSD_PARTICIPANT_MEMBER_CODE);
        part1.setInt(quickfix.field.RootPartyRole.FIELD, quickfix.field.PartyRole.AGENT);
        part1.setInt(quickfix.field.NoRootPartySubIDs.FIELD, 2);
        quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs partsub11 = new quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs();
        partsub11.setString(quickfix.field.RootPartySubID.FIELD, "party subid 11");
        partsub11.setInt(quickfix.field.RootPartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part1.addGroup(partsub11);
        quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs partsub12 = new quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs();
        partsub12.setString(quickfix.field.RootPartySubID.FIELD, "party subid 12");
        partsub12.setInt(quickfix.field.RootPartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part1.addGroup(partsub12);
        component.addGroup(part1);

        quickfix.fix50.component.RootParties.NoRootPartyIDs part2 = new quickfix.fix50.component.RootParties.NoRootPartyIDs();
        part2.setString(quickfix.field.RootPartyID.FIELD, "party id 2");
        part2.setChar(quickfix.field.RootPartyIDSource.FIELD, quickfix.field.PartyIDSource.GENERALLY_ACCEPTED_MARKET_PARTICIPANT_IDENTIFIER);
        part2.setInt(quickfix.field.RootPartyRole.FIELD, quickfix.field.PartyRole.ALLOCATION_ENTITY);
        part2.setInt(quickfix.field.NoRootPartySubIDs.FIELD, 2);
        quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs partsub21 = new quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs();
        partsub21.setString(quickfix.field.RootPartySubID.FIELD, "party subid 21");
        partsub21.setInt(quickfix.field.RootPartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part1.addGroup(partsub21);
        quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs partsub22 = new quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs();
        partsub22.setString(quickfix.field.RootPartySubID.FIELD, "party subid 22");
        partsub22.setInt(quickfix.field.RootPartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part2.addGroup(partsub22);
        component.addGroup(part2);
    }

    public void check(quickfix.fix50.component.RootParties expected, RootParties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoRootPartyIDs.FIELD), actual.getNoRootPartyIDs().intValue());
        for (int i = 0; i < actual.getNoRootPartyIDs().intValue(); i++) {
            quickfix.fix50.component.RootParties.NoRootPartyIDs part = new quickfix.fix50.component.RootParties.NoRootPartyIDs();
            expected.getGroup(i + 1, part);
            assertEquals(part.getString(quickfix.field.RootPartyID.FIELD), actual.getRootPartyIDGroups()[i].getRootPartyID());
            assertEquals(part.getChar(quickfix.field.RootPartyIDSource.FIELD), actual.getRootPartyIDGroups()[i].getRootPartyIDSource().getValue());
            assertEquals(part.getInt(quickfix.field.RootPartyRole.FIELD), actual.getRootPartyIDGroups()[i].getRootPartyRole().getValue());
            assertEquals(part.getInt(quickfix.field.NoRootPartySubIDs.FIELD), actual.getRootPartyIDGroups()[i].getNoRootPartySubIDs().intValue());
            for (int j = 0; j < part.getNoRootPartySubIDs().getValue(); j++) {
                quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs partsub = new quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs();
                part.getGroup(j + 1, partsub);
                assertEquals(partsub.getString(quickfix.field.RootPartySubID.FIELD), actual.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubID());
                assertEquals(partsub.getInt(quickfix.field.RootPartySubIDType.FIELD), actual.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubIDType().getValue());
            }
        }
    }

    public void check(RootParties expected, quickfix.fix50.component.RootParties actual) throws Exception {
        assertEquals(actual.getInt(quickfix.field.NoPartyIDs.FIELD), expected.getNoRootPartyIDs().intValue());
        for (int i = 0; i < expected.getNoRootPartyIDs().intValue(); i++) {
            quickfix.fix50.component.RootParties.NoRootPartyIDs part = new quickfix.fix50.component.RootParties.NoRootPartyIDs();
            actual.getGroup(i + 1, part);
            assertEquals(part.getString(quickfix.field.RootPartyID.FIELD), expected.getRootPartyIDGroups()[i].getRootPartyID());
            assertEquals(part.getChar(quickfix.field.RootPartyIDSource.FIELD), expected.getRootPartyIDGroups()[i].getRootPartyIDSource().getValue());
            assertEquals(part.getInt(quickfix.field.RootPartyRole.FIELD), expected.getRootPartyIDGroups()[i].getRootPartyRole().getValue());
            assertEquals(part.getInt(quickfix.field.NoRootPartySubIDs.FIELD), expected.getRootPartyIDGroups()[i].getNoRootPartySubIDs().intValue());
            for (int j = 0; j < expected.getNoRootPartyIDs().intValue(); j++) {
                quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs partsub = new quickfix.fix50.component.RootParties.NoRootPartyIDs.NoRootPartySubIDs();
                part.getGroup(j + 1, partsub);
                assertEquals(partsub.getString(quickfix.field.PartySubID.FIELD), expected.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubID());
                assertEquals(partsub.getInt(quickfix.field.PartySubIDType.FIELD), expected.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubIDType().getValue());
            }
        }
    }

    public void check(RootParties expected, RootParties actual) throws Exception {
        assertEquals(actual.getNoRootPartyIDs().intValue(), expected.getNoRootPartyIDs().intValue());
        for (int i = 0; i < expected.getNoRootPartyIDs().intValue(); i++) {
            assertEquals(expected.getRootPartyIDGroups()[i].getRootPartyID(), actual.getRootPartyIDGroups()[i].getRootPartyID());
            assertEquals(expected.getRootPartyIDGroups()[i].getRootPartyIDSource().getValue(), actual.getRootPartyIDGroups()[i].getRootPartyIDSource().getValue());
            assertEquals(expected.getRootPartyIDGroups()[i].getRootPartyRole().getValue(), actual.getRootPartyIDGroups()[i].getRootPartyRole().getValue());
            assertEquals(expected.getRootPartyIDGroups()[i].getNoRootPartySubIDs().intValue(), actual.getRootPartyIDGroups()[i].getNoRootPartySubIDs().intValue());
            for (int j = 0; j < expected.getNoRootPartyIDs().intValue(); j++) {
                assertEquals(expected.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubID(),
                    actual.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubID());
                assertEquals(expected.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubIDType().getValue(),
                    actual.getRootPartyIDGroups()[i].getRootPartySubGroups()[j].getRootPartySubIDType().getValue());
            }
        }
    }
}
