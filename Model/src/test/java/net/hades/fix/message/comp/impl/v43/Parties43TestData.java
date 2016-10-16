/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties43Test.java
 *
 * $Id: Parties43TestData.java,v 1.1 2009-07-06 03:19:16 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import static org.junit.Assert.*;

import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

/**
 * Test suite for Partis43 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/05/2009, 7:53:57 PM
 */
public class Parties43TestData {

    private static final Parties43TestData INSTANCE;

    static {
        INSTANCE = new Parties43TestData();
    }

    public static Parties43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.component.Parties msg) throws Exception {
        msg.setInt(quickfix.field.NoPartyIDs.FIELD, 2);
        quickfix.fix43.component.Parties.NoPartyIDs grp1 = new quickfix.fix43.component.Parties.NoPartyIDs();
        grp1.setString(quickfix.field.PartyID.FIELD, "party id 1");
        grp1.setChar(quickfix.field.PartyIDSource.FIELD, quickfix.field.PartyIDSource.BIC);
        grp1.setInt(quickfix.field.PartyRole.FIELD, quickfix.field.PartyRole.AGENT);
        grp1.setString(quickfix.field.PartySubID.FIELD, "party subid 1");
        msg.addGroup(grp1);
        quickfix.fix43.component.Parties.NoPartyIDs grp2 = new quickfix.fix43.component.Parties.NoPartyIDs();
        grp2.setString(quickfix.field.PartyID.FIELD, "party id 2");
        grp2.setChar(quickfix.field.PartyIDSource.FIELD, quickfix.field.PartyIDSource.DIRECTED_BROKER);
        grp2.setInt(quickfix.field.PartyRole.FIELD, quickfix.field.PartyRole.ALLOCATION_ENTITY);
        grp2.setString(quickfix.field.PartySubID.FIELD, "party subid 2");
        msg.addGroup(grp2);
    }

    public void populate(Parties component) {
        component.setNoPartyIDs(new Integer(2));
        component.getPartyIDGroups()[0].setPartyID("party id 1");
        component.getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        component.getPartyIDGroups()[0].setPartyRole(PartyRole.AssetManager);
        component.getPartyIDGroups()[0].setPartySubID("party subid 1");

        component.getPartyIDGroups()[1].setPartyID("party id 2");
        component.getPartyIDGroups()[1].setPartyIDSource(PartyIDSource.ISOCountryCode);
        component.getPartyIDGroups()[1].setPartyRole(PartyRole.Beneficiary);
        component.getPartyIDGroups()[1].setPartySubID("party subid 2");
    }

    public void check(Parties expected, quickfix.fix43.component.Parties actual) throws Exception {
        assertEquals(expected.getNoPartyIDs().intValue(), actual.getInt(quickfix.field.NoPartyIDs.FIELD));
        for (int i = 0; i < expected.getNoPartyIDs().intValue(); i++) {
            quickfix.fix43.component.Parties.NoPartyIDs grp = new quickfix.fix43.component.Parties.NoPartyIDs();
            actual.getGroup(i + 1, grp);
            assertEquals(expected.getPartyIDGroups()[i].getPartyID(), grp.getString(quickfix.field.PartyID.FIELD));
            assertEquals(expected.getPartyIDGroups()[i].getPartyIDSource().getValue(), grp.getChar(quickfix.field.PartyIDSource.FIELD));
            assertEquals(expected.getPartyIDGroups()[i].getPartyRole().getValue(), grp.getInt(quickfix.field.PartyRole.FIELD));
            assertEquals(expected.getPartyIDGroups()[i].getPartySubID(), grp.getString(quickfix.field.PartySubID.FIELD));
        }
    }

    public void check(quickfix.fix43.component.Parties expected, Parties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoPartyIDs.FIELD), actual.getNoPartyIDs().intValue());
        for (int i = 0; i < actual.getNoPartyIDs().intValue(); i++) {
            quickfix.fix43.component.Parties.NoPartyIDs grp = new quickfix.fix43.component.Parties.NoPartyIDs();
            expected.getGroup(i + 1, grp);
            assertEquals(grp.getString(quickfix.field.PartyID.FIELD), actual.getPartyIDGroups()[i].getPartyID());
            assertEquals(grp.getChar(quickfix.field.PartyIDSource.FIELD), actual.getPartyIDGroups()[i].getPartyIDSource().getValue());
            assertEquals(grp.getInt(quickfix.field.PartyRole.FIELD), actual.getPartyIDGroups()[i].getPartyRole().getValue());
            assertEquals(grp.getString(quickfix.field.PartySubID.FIELD), actual.getPartyIDGroups()[i].getPartySubID());
        }
    }

    public void check(Parties expected, Parties actual) throws Exception {
        assertEquals(expected.getNoPartyIDs().intValue(), actual.getNoPartyIDs().intValue());
        for (int i = 0; i < expected.getNoPartyIDs().intValue(); i++) {
            assertEquals(expected.getPartyIDGroups()[i].getPartyID(), actual.getPartyIDGroups()[i].getPartyID());
            assertEquals(expected.getPartyIDGroups()[i].getPartyIDSource().getValue(), actual.getPartyIDGroups()[i].getPartyIDSource().getValue());
            assertEquals(expected.getPartyIDGroups()[i].getPartyRole().getValue(), actual.getPartyIDGroups()[i].getPartyRole().getValue());
            assertEquals(expected.getPartyIDGroups()[i].getPartySubID(), actual.getPartyIDGroups()[i].getPartySubID());
        }
    }
}
