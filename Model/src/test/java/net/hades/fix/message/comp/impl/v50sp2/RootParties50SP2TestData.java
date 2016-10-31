/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootParties50SP2TestData.java
 *
 * $Id: RootParties50SP2TestData.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for RootParties50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class RootParties50SP2TestData extends MsgTest {

    private static final RootParties50SP2TestData INSTANCE;

    static {
        INSTANCE = new RootParties50SP2TestData();
    }

    public static RootParties50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RootParties component) {
        component.setNoRootPartyIDs(new Integer(2));
        component.getRootPartyIDGroups()[0].setRootPartyID("party ID 1");
        component.getRootPartyIDGroups()[0].setRootPartyIDSource(PartyIDSource.BIC);
        component.getRootPartyIDGroups()[0].setRootPartyRole(PartyRole.Agent);
        component.getRootPartyIDGroups()[0].setRootSubParties();
        component.getRootPartyIDGroups()[0].getRootSubParties().setNoRootPartySubIDs(new Integer(2));
        component.getRootPartyIDGroups()[0].getRootSubParties().getRootPartySubGroups()[0].setRootPartySubID("party sub ID 11");
        component.getRootPartyIDGroups()[0].getRootSubParties().getRootPartySubGroups()[0].setRootPartySubIDType(PartySubIDType.BIC);
        component.getRootPartyIDGroups()[0].getRootSubParties().getRootPartySubGroups()[1].setRootPartySubID("party sub ID 12");
        component.getRootPartyIDGroups()[0].getRootSubParties().getRootPartySubGroups()[1].setRootPartySubIDType(PartySubIDType.CSDParticipantMemberCode);

        component.getRootPartyIDGroups()[1].setRootPartyID("party ID 2");
        component.getRootPartyIDGroups()[1].setRootPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        component.getRootPartyIDGroups()[1].setRootPartyRole(PartyRole.AllocationEntity);
        component.getRootPartyIDGroups()[1].setRootSubParties();
        component.getRootPartyIDGroups()[1].getRootSubParties().setNoRootPartySubIDs(new Integer(2));
        component.getRootPartyIDGroups()[1].getRootSubParties().getRootPartySubGroups()[0].setRootPartySubID("party sub ID 21");
        component.getRootPartyIDGroups()[1].getRootSubParties().getRootPartySubGroups()[0].setRootPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        component.getRootPartyIDGroups()[1].getRootSubParties().getRootPartySubGroups()[1].setRootPartySubID("party sub ID 22");
        component.getRootPartyIDGroups()[1].getRootSubParties().getRootPartySubGroups()[1].setRootPartySubIDType(PartySubIDType.CashAccountName);
    }

    public void check(RootParties expected, RootParties actual) throws Exception {
        assertEquals(actual.getNoRootPartyIDs().intValue(), expected.getNoRootPartyIDs().intValue());
        for (int i = 0; i < expected.getNoRootPartyIDs().intValue(); i++) {
            assertEquals(expected.getRootPartyIDGroups()[i].getRootPartyID(), actual.getRootPartyIDGroups()[i].getRootPartyID());
            assertEquals(expected.getRootPartyIDGroups()[i].getRootPartyIDSource().getValue(), actual.getRootPartyIDGroups()[i].getRootPartyIDSource().getValue());
            assertEquals(expected.getRootPartyIDGroups()[i].getRootPartyRole().getValue(), actual.getRootPartyIDGroups()[i].getRootPartyRole().getValue());
            assertNotNull(actual.getRootPartyIDGroups()[i].getRootSubParties());
            assertEquals(expected.getRootPartyIDGroups()[i].getRootSubParties().getNoRootPartySubIDs().intValue(),
                actual.getRootPartyIDGroups()[i].getRootSubParties().getNoRootPartySubIDs().intValue());
            for (int j = 0; j < actual.getRootPartyIDGroups()[i].getRootSubParties().getNoRootPartySubIDs().intValue(); j++) {
                assertEquals(expected.getRootPartyIDGroups()[i].getRootSubParties().getRootPartySubGroups()[j].getRootPartySubID(),
                    actual.getRootPartyIDGroups()[i].getRootSubParties().getRootPartySubGroups()[j].getRootPartySubID());
                assertEquals(expected.getRootPartyIDGroups()[i].getRootSubParties().getRootPartySubGroups()[j].getRootPartySubIDType().getValue(),
                    actual.getRootPartyIDGroups()[i].getRootSubParties().getRootPartySubGroups()[j].getRootPartySubIDType().getValue());
            }
        }
    }
}
