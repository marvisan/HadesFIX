/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartyGroup50SP2TestData.java
 *
 * $Id: PartyGroup50SP2TestData.java,v 1.2 2009-11-21 09:57:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for PartyIDGroup50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class PartyGroup50SP2TestData extends MsgTest {

    private static final PartyGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new PartyGroup50SP2TestData();
    }

    public static PartyGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PartyGroup component) {
        component.setPartyID("party ID 1");
        component.setPartyIDSource(PartyIDSource.BIC);
        component.setPartyRole(PartyRole.Agent);
        component.setNoPartySubIDs(new Integer(2));
        component.getPartySubIDGroups()[0].setPartySubID("party sub ID 11");
        component.getPartySubIDGroups()[0].setPartySubIDType(PartySubIDType.BIC);
        component.getPartySubIDGroups()[1].setPartySubID("party sub ID 12");
        component.getPartySubIDGroups()[1].setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
    }

    public void populate2(PartyGroup component) {
        component.setPartyID("party ID 2");
        component.setPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        component.setPartyRole(PartyRole.AllocationEntity);
        component.setNoPartySubIDs(new Integer(2));
        component.getPartySubIDGroups()[0].setPartySubID("party sub ID 21");
        component.getPartySubIDGroups()[0].setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        component.getPartySubIDGroups()[1].setPartySubID("party sub ID 22");
        component.getPartySubIDGroups()[1].setPartySubIDType(PartySubIDType.CashAccountName);
    }

    public void check(PartyGroup expected, PartyGroup actual) throws Exception {
        assertEquals(expected.getPartyID(), actual.getPartyID());
        assertEquals(expected.getPartyIDSource().getValue(), actual.getPartyIDSource().getValue());
        assertEquals(expected.getPartyRole(), actual.getPartyRole());
        assertEquals(expected.getNoPartySubIDs().intValue(), actual.getNoPartySubIDs().intValue());
        for (int i = 0; i < actual.getNoPartySubIDs().intValue(); i++) {
            assertEquals(expected.getPartySubIDGroups()[0].getPartySubID(),
                actual.getPartySubIDGroups()[0].getPartySubID());
            assertEquals(expected.getPartySubIDGroups()[0].getPartySubIDType(),
                actual.getPartySubIDGroups()[0].getPartySubIDType());
        }
    }
}
