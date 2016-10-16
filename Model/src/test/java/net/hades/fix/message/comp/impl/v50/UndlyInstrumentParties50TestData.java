/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentParties50TestData.java
 *
 * $Id: UndlyInstrumentParties50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.UndlyInstrumentParties;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for UndlyInstrumentParties50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 15/04/2009, 9:54:37 AM
 */
public class UndlyInstrumentParties50TestData extends MsgTest {

    private static final UndlyInstrumentParties50TestData INSTANCE;

    static {
        INSTANCE = new UndlyInstrumentParties50TestData();
    }

    public static UndlyInstrumentParties50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix50.component.UndlyInstrumentParties msg) throws Exception {
        msg.setInt(quickfix.field.NoUndlyInstrumentParties.FIELD, 2);
        quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties undlyip1 =
            new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties();
        undlyip1.setString(quickfix.field.UndlyInstrumentPartyID.FIELD, "IN PAR 1");
        undlyip1.setChar(quickfix.field.UndlyInstrumentPartyIDSource.FIELD, PartyIDSource.AustralianTaxFileNumber.getValue());
        undlyip1.setInt(quickfix.field.UndlyInstrumentPartyRole.FIELD, PartyRole.AssetManager.getValue());
        quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs undlyips11 =
            new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs();
        undlyips11.setString(quickfix.field.UndlyInstrumentPartySubID.FIELD, "SUB ID 11");
        undlyips11.setInt(quickfix.field.UndlyInstrumentPartySubIDType.FIELD, PartySubIDType.BIC.getValue());
        undlyip1.addGroup(undlyips11);
        quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs undlyips12 =
            new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs();
        undlyips12.setString(quickfix.field.UndlyInstrumentPartySubID.FIELD, "SUB ID 12");
        undlyips12.setInt(quickfix.field.UndlyInstrumentPartySubIDType.FIELD, PartySubIDType.ContactName.getValue());
        undlyip1.addGroup(undlyips12);
        msg.addGroup(undlyip1);
        quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties undlyip2 =
            new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties();
        undlyip2.setString(quickfix.field.UndlyInstrumentPartyID.FIELD, "IN PAR 2");
        undlyip2.setChar(quickfix.field.UndlyInstrumentPartyIDSource.FIELD, PartyIDSource.CSD.getValue());
        undlyip2.setInt(quickfix.field.UndlyInstrumentPartyRole.FIELD, PartyRole.Beneficiary.getValue());
        quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs undlyips21 =
            new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs();
        undlyips21.setString(quickfix.field.UndlyInstrumentPartySubID.FIELD, "SUB ID 2");
        undlyips21.setInt(quickfix.field.UndlyInstrumentPartySubIDType.FIELD, PartySubIDType.CashAccountName.getValue());
        undlyip2.addGroup(undlyips21);
        quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs undlyips22 =
            new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs();
        undlyips22.setString(quickfix.field.UndlyInstrumentPartySubID.FIELD, "SUB ID 2");
        undlyips22.setInt(quickfix.field.UndlyInstrumentPartySubIDType.FIELD, PartySubIDType.CashAccountName.getValue());
        undlyip2.addGroup(undlyips22);
        msg.addGroup(undlyip2);
    }

    public void populate(UndlyInstrumentParties comp) {
        comp.setNoUndlyInstrumentParties(new Integer(1));
        comp.getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyID("IN PAR 1");
        comp.getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        comp.getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyRole(PartyRole.Agent);
        comp.getUndlyInstrumentPartyIDGroups()[0].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        comp.getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("SUB ID 11");
        comp.getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        comp.getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("SUB ID 12");
        comp.getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.CashAccountName);
//        comp.getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyID("IN PAR 2");
//        comp.getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyIDSource(PartyIDSource.ChineseInvestorID);
//        comp.getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyRole(PartyRole.AllocationEntity);
//        comp.getUndlyInstrumentPartyIDGroups()[1].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
//        comp.getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("SUB ID 21");
//        comp.getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.ContactName);
//        comp.getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("SUB ID 22");
//        comp.getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.BIC);
    }

    public void check(UndlyInstrumentParties expected, quickfix.fix50.component.UndlyInstrumentParties actual) throws Exception {
        assertEquals(expected.getNoUndlyInstrumentParties().intValue(), actual.getInt(quickfix.field.NoUndlyInstrumentParties.FIELD));
        for (int i = 0; i < expected.getNoUndlyInstrumentParties().intValue(); i++) {
            quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties undlyip =
                new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties();
            actual.getGroup(i + 1, undlyip);
            assertEquals(expected.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyID(),
                undlyip.getString(quickfix.field.UndlyInstrumentPartyID.FIELD));
            assertEquals(expected.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyIDSource().getValue(),
                undlyip.getChar(quickfix.field.UndlyInstrumentPartyIDSource.FIELD));
            assertEquals(expected.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyRole().getValue(),
                undlyip.getInt(quickfix.field.UndlyInstrumentPartyRole.FIELD));
            for (int j = 0; j < expected.getNoUndlyInstrumentParties().intValue(); j++) {
                quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs undlyips =
                    new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs();
                undlyip.getGroup(j + 1, undlyips);
                assertEquals(expected.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubID(),
                    undlyips.getString(quickfix.field.UndlyInstrumentPartySubID.FIELD));
                assertEquals(expected.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubIDType().getValue(),
                    undlyips.getInt(quickfix.field.UndlyInstrumentPartySubIDType.FIELD));
            }
        }
    }

    public void check(quickfix.fix50.component.UndlyInstrumentParties expected, UndlyInstrumentParties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoUndlyInstrumentParties.FIELD), actual.getNoUndlyInstrumentParties().intValue());
        for (int i = 0; i < actual.getNoUndlyInstrumentParties().intValue(); i++) {
            quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties undlyip =
                new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties();
            expected.getGroup(i + 1, undlyip);
            assertEquals(undlyip.getString(quickfix.field.UndlyInstrumentPartyID.FIELD),
                actual.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyID());
            assertEquals(undlyip.getChar(quickfix.field.UndlyInstrumentPartyIDSource.FIELD),
                actual.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyIDSource().getValue());
            assertEquals(undlyip.getInt(quickfix.field.UndlyInstrumentPartyRole.FIELD),
                actual.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyRole().getValue());
            for (int j = 0; j < actual.getNoUndlyInstrumentParties().intValue(); j++) {
                quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs undlyips =
                    new quickfix.fix50.component.UndlyInstrumentParties.NoUndlyInstrumentParties.NoUndlyInstrumentPartySubIDs();
                undlyip.getGroup(j + 1, undlyips);
                assertEquals(undlyips.getString(quickfix.field.UndlyInstrumentPartySubID.FIELD),
                    actual.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubID());
                assertEquals(undlyips.getInt(quickfix.field.UndlyInstrumentPartySubIDType.FIELD),
                    actual.getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubIDType().getValue());
            }
        }
    }
}
