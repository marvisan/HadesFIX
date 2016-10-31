/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties50TestData.java
 *
 * $Id: Parties50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.group.impl.v50.PartyGroup50TestData;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for Parties50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class Parties50TestData extends MsgTest {

    private static final Parties50TestData INSTANCE;

    static {
        INSTANCE = new Parties50TestData();
    }

    public static Parties50TestData getInstance() {
        return INSTANCE;
    }

    public void check(Parties expected, Parties actual) throws Exception {
        assertEquals(expected.getNoPartyIDs().intValue(), actual.getNoPartyIDs().intValue());
        PartyGroup50TestData.getInstance().check(expected.getPartyIDGroups()[0], actual.getPartyIDGroups()[0]);
        PartyGroup50TestData.getInstance().check(expected.getPartyIDGroups()[1], actual.getPartyIDGroups()[1]);
    }
    
    public void populate(Parties component) {
        component.setNoPartyIDs(new Integer(2));
        component.getPartyIDGroups()[0].setNoPartySubIDs(new Integer(2));
        component.getPartyIDGroups()[0].setPartyID("party ID 1");
        component.getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.BIC);
        component.getPartyIDGroups()[0].setPartyRole(PartyRole.Agent);
        component.getPartyIDGroups()[0].setNoPartySubIDs(new Integer(2));
        component.getPartyIDGroups()[0].getPartySubIDGroups()[0].setPartySubID("party sub ID 11");
        component.getPartyIDGroups()[0].getPartySubIDGroups()[0].setPartySubIDType(PartySubIDType.BIC);
        component.getPartyIDGroups()[0].getPartySubIDGroups()[1].setPartySubID("party sub ID 12");
        component.getPartyIDGroups()[0].getPartySubIDGroups()[1].setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);

        component.getPartyIDGroups()[1].setPartyID("party ID 2");
        component.getPartyIDGroups()[1].setPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        component.getPartyIDGroups()[1].setPartyRole(PartyRole.AllocationEntity);
        component.getPartyIDGroups()[1].setNoPartySubIDs(new Integer(2));
        component.getPartyIDGroups()[1].getPartySubIDGroups()[0].setPartySubID("party sub ID 21");
        component.getPartyIDGroups()[1].getPartySubIDGroups()[0].setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        component.getPartyIDGroups()[1].getPartySubIDGroups()[1].setPartySubID("party sub ID 22");
        component.getPartyIDGroups()[1].getPartySubIDGroups()[1].setPartySubIDType(PartySubIDType.CashAccountName);
    }

    public void populate(quickfix.fix50.component.Parties component) {
        component.setInt(quickfix.field.NoPartyIDs.FIELD, 2);
        quickfix.fix50.component.Parties.NoPartyIDs part1 = new quickfix.fix50.component.Parties.NoPartyIDs();
        part1.setString(quickfix.field.PartyID.FIELD, "party id 1");
        part1.setChar(quickfix.field.PartyIDSource.FIELD, quickfix.field.PartyIDSource.CSD_PARTICIPANT_MEMBER_CODE);
        part1.setInt(quickfix.field.PartyRole.FIELD, quickfix.field.PartyRole.AGENT);
        part1.setInt(quickfix.field.NoPartySubIDs.FIELD, 2);
        quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs partsub11 = new quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub11.setString(quickfix.field.PartySubID.FIELD, "party subid 11");
        partsub11.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part1.addGroup(partsub11);
        quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs partsub12 = new quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub12.setString(quickfix.field.PartySubID.FIELD, "party subid 12");
        partsub12.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part1.addGroup(partsub12);
        component.addGroup(part1);

        quickfix.fix50.component.Parties.NoPartyIDs part2 = new quickfix.fix50.component.Parties.NoPartyIDs();
        part2.setString(quickfix.field.PartyID.FIELD, "party id 2");
        part2.setChar(quickfix.field.PartyIDSource.FIELD, quickfix.field.PartyIDSource.BIC);
        part2.setInt(quickfix.field.PartyRole.FIELD, quickfix.field.PartyRole.ASSET_MANAGER);
        part2.setInt(quickfix.field.NoPartySubIDs.FIELD, 2);
        quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs partsub21 = new quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub21.setString(quickfix.field.PartySubID.FIELD, "party subid 21");
        partsub21.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.EMAIL_ADDRESS);
        part2.addGroup(partsub21);
        quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs partsub22 = new quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub22.setString(quickfix.field.PartySubID.FIELD, "party subid 22");
        partsub22.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.FUND_ACCOUNT_NAME);
        part2.addGroup(partsub22);
        component.addGroup(part2);
    }

    public void check(quickfix.fix50.component.Parties expected, Parties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoPartyIDs.FIELD), actual.getNoPartyIDs().intValue());
        for (int i = 0; i < actual.getNoPartyIDs().intValue(); i++) {
            quickfix.fix50.component.Parties.NoPartyIDs part = new quickfix.fix50.component.Parties.NoPartyIDs();
            expected.getGroup(i + 1, part);
            assertEquals(part.getString(quickfix.field.PartyID.FIELD), actual.getPartyIDGroups()[i].getPartyID());
            assertEquals(part.getChar(quickfix.field.PartyIDSource.FIELD), actual.getPartyIDGroups()[i].getPartyIDSource().getValue());
            assertEquals(part.getInt(quickfix.field.PartyRole.FIELD), actual.getPartyIDGroups()[i].getPartyRole().getValue());
            assertEquals(part.getInt(quickfix.field.NoPartySubIDs.FIELD), actual.getPartyIDGroups()[i].getNoPartySubIDs().intValue());
            for (int j = 0; j < part.getNoPartySubIDs().getValue(); j++) {
                quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs partsub = new quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs();
                part.getGroup(j + 1, partsub);
                assertEquals(partsub.getString(quickfix.field.PartySubID.FIELD), actual.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubID());
                assertEquals(partsub.getInt(quickfix.field.PartySubIDType.FIELD), actual.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubIDType().getValue());
            }
        }
    }

    public void check(Parties expected, quickfix.fix50.component.Parties actual) throws Exception {
        assertEquals(actual.getInt(quickfix.field.NoPartyIDs.FIELD), expected.getNoPartyIDs().intValue());
        for (int i = 0; i < expected.getNoPartyIDs().intValue(); i++) {
            quickfix.fix50.component.Parties.NoPartyIDs part = new quickfix.fix50.component.Parties.NoPartyIDs();
            actual.getGroup(i + 1, part);
            assertEquals(part.getString(quickfix.field.PartyID.FIELD), expected.getPartyIDGroups()[i].getPartyID());
            assertEquals(part.getChar(quickfix.field.PartyIDSource.FIELD), expected.getPartyIDGroups()[i].getPartyIDSource().getValue());
            assertEquals(part.getInt(quickfix.field.PartyRole.FIELD), expected.getPartyIDGroups()[i].getPartyRole().getValue());
            assertEquals(part.getInt(quickfix.field.NoPartySubIDs.FIELD), expected.getPartyIDGroups()[i].getNoPartySubIDs().intValue());
            for (int j = 0; j < expected.getNoPartyIDs().intValue(); j++) {
                quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs partsub = new quickfix.fix50.component.Parties.NoPartyIDs.NoPartySubIDs();
                part.getGroup(j + 1, partsub);
                assertEquals(partsub.getString(quickfix.field.PartySubID.FIELD), expected.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubID());
                assertEquals(partsub.getInt(quickfix.field.PartySubIDType.FIELD), expected.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubIDType().getValue());
            }
        }
    }
}
