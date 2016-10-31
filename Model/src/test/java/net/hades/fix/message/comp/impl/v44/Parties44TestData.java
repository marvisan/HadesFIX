/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties44TestData.java
 *
 * $Id: Parties44TestData.java,v 1.2 2011-09-09 08:05:25 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for Parties44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class Parties44TestData extends MsgTest {

    private static final Parties44TestData INSTANCE;

    static {
        INSTANCE = new Parties44TestData();
    }

    public static Parties44TestData getInstance() {
        return INSTANCE;
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
        component.getPartyIDGroups()[1].setPartyRole(PartyRole.ClearingFirm);
        component.getPartyIDGroups()[1].setNoPartySubIDs(new Integer(2));
        component.getPartyIDGroups()[1].getPartySubIDGroups()[0].setPartySubID("party sub ID 21");
        component.getPartyIDGroups()[1].getPartySubIDGroups()[0].setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        component.getPartyIDGroups()[1].getPartySubIDGroups()[1].setPartySubID("party sub ID 22");
        component.getPartyIDGroups()[1].getPartySubIDGroups()[1].setPartySubIDType(PartySubIDType.CashAccountName);
    }

    public void populate(quickfix.fix44.component.Parties component) {
        component.setInt(quickfix.field.NoPartyIDs.FIELD, 2);
        quickfix.fix44.component.Parties.NoPartyIDs part1 = new quickfix.fix44.component.Parties.NoPartyIDs();
        part1.setString(quickfix.field.PartyID.FIELD, "party id 1");
        part1.setChar(quickfix.field.PartyIDSource.FIELD, quickfix.field.PartyIDSource.CSD_PARTICIPANT_MEMBER_CODE);
        part1.setInt(quickfix.field.PartyRole.FIELD, quickfix.field.PartyRole.AGENT);
        part1.setInt(quickfix.field.NoPartySubIDs.FIELD, 2);
        quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs partsub11 = new quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub11.setString(quickfix.field.PartySubID.FIELD, "party subid 11");
        partsub11.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part1.addGroup(partsub11);
        quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs partsub12 = new quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub12.setString(quickfix.field.PartySubID.FIELD, "party subid 12");
        partsub12.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.DEPARTMENT);
        part1.addGroup(partsub12);
        component.addGroup(part1);

        quickfix.fix44.component.Parties.NoPartyIDs part2 = new quickfix.fix44.component.Parties.NoPartyIDs();
        part2.setString(quickfix.field.PartyID.FIELD, "party id 2");
        part2.setChar(quickfix.field.PartyIDSource.FIELD, quickfix.field.PartyIDSource.BIC);
        part2.setInt(quickfix.field.PartyRole.FIELD, quickfix.field.PartyRole.CLEARING_FIRM);
        part2.setInt(quickfix.field.NoPartySubIDs.FIELD, 2);
        quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs partsub21 = new quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub21.setString(quickfix.field.PartySubID.FIELD, "party subid 21");
        partsub21.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.EMAIL_ADDRESS);
        part2.addGroup(partsub21);
        quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs partsub22 = new quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs();
        partsub22.setString(quickfix.field.PartySubID.FIELD, "party subid 22");
        partsub22.setInt(quickfix.field.PartySubIDType.FIELD, quickfix.field.PartySubIDType.FUND_ACCOUNT_NAME);
        part2.addGroup(partsub22);
        component.addGroup(part2);
    }

    public void check(quickfix.fix44.component.Parties expected, Parties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoPartyIDs.FIELD), actual.getNoPartyIDs().intValue());
        for (int i = 0; i < actual.getNoPartyIDs().intValue(); i++) {
            quickfix.fix44.component.Parties.NoPartyIDs part = new quickfix.fix44.component.Parties.NoPartyIDs();
            expected.getGroup(i + 1, part);
            assertEquals(part.getString(quickfix.field.PartyID.FIELD), actual.getPartyIDGroups()[i].getPartyID());
            assertEquals(part.getChar(quickfix.field.PartyIDSource.FIELD), actual.getPartyIDGroups()[i].getPartyIDSource().getValue());
            assertEquals(part.getInt(quickfix.field.PartyRole.FIELD), actual.getPartyIDGroups()[i].getPartyRole().getValue());
            assertEquals(part.getInt(quickfix.field.NoPartySubIDs.FIELD), actual.getPartyIDGroups()[i].getNoPartySubIDs().intValue());
            for (int j = 0; j < part.getNoPartySubIDs().getValue(); j++) {
                quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs partsub = new quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs();
                part.getGroup(j + 1, partsub);
                assertEquals(partsub.getString(quickfix.field.PartySubID.FIELD), actual.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubID());
                assertEquals(partsub.getInt(quickfix.field.PartySubIDType.FIELD), actual.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubIDType().getValue());
            }
        }
    }

    public void check(Parties expected, quickfix.fix44.component.Parties actual) throws Exception {
        assertEquals(actual.getInt(quickfix.field.NoPartyIDs.FIELD), expected.getNoPartyIDs().intValue());
        for (int i = 0; i < expected.getNoPartyIDs().intValue(); i++) {
            quickfix.fix44.component.Parties.NoPartyIDs part = new quickfix.fix44.component.Parties.NoPartyIDs();
            actual.getGroup(i + 1, part);
            assertEquals(part.getString(quickfix.field.PartyID.FIELD), expected.getPartyIDGroups()[i].getPartyID());
            assertEquals(part.getChar(quickfix.field.PartyIDSource.FIELD), expected.getPartyIDGroups()[i].getPartyIDSource().getValue());
            assertEquals(part.getInt(quickfix.field.PartyRole.FIELD), expected.getPartyIDGroups()[i].getPartyRole().getValue());
            assertEquals(part.getInt(quickfix.field.NoPartySubIDs.FIELD), expected.getPartyIDGroups()[i].getNoPartySubIDs().intValue());
            for (int j = 0; j < expected.getNoPartyIDs().intValue(); j++) {
                quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs partsub = new quickfix.fix44.component.Parties.NoPartyIDs.NoPartySubIDs();
                part.getGroup(j + 1, partsub);
                assertEquals(partsub.getString(quickfix.field.PartySubID.FIELD), expected.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubID());
                assertEquals(partsub.getInt(quickfix.field.PartySubIDType.FIELD), expected.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubIDType().getValue());
            }
        }
    }

    public void check(Parties expected, Parties actual) throws Exception {
        assertEquals(expected.getNoPartyIDs(), actual.getNoPartyIDs());
        for (int i = 0; i < actual.getNoPartyIDs(); i++) {
            assertEquals(expected.getPartyIDGroups()[i].getPartyID(), actual.getPartyIDGroups()[i].getPartyID());
            assertEquals(expected.getPartyIDGroups()[i].getPartyIDSource().getValue(), actual.getPartyIDGroups()[i].getPartyIDSource().getValue());
            assertEquals(expected.getPartyIDGroups()[i].getPartyRole().getValue(), actual.getPartyIDGroups()[i].getPartyRole().getValue());
            assertEquals(expected.getPartyIDGroups()[i].getNoPartySubIDs().intValue(), actual.getPartyIDGroups()[i].getNoPartySubIDs().intValue());
            for (int j = 0; j < expected.getPartyIDGroups()[i].getNoPartySubIDs().intValue(); j++) {
                assertEquals(expected.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubID(),
                    actual.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubID());
                assertEquals(expected.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubIDType().getValue(),
                    actual.getPartyIDGroups()[i].getPartySubIDGroups()[j].getPartySubIDType().getValue());
            }
        }
    }
}
