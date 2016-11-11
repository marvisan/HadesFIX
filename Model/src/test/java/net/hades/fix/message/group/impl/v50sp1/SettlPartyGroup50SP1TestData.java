/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlPartyGroup50SP1TestData.java
 *
 * $Id: SettlPartyGroup50SP1TestData.java,v 1.1 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.SettlPartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for SettlPartyGroup50SP1 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class SettlPartyGroup50SP1TestData extends MsgTest {

    private static final SettlPartyGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new SettlPartyGroup50SP1TestData();
    }

    public static SettlPartyGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SettlPartyGroup comp) {
        comp.setSettlPartyID("party ID 1");
        comp.setSettlPartyIDSource(PartyIDSource.BIC);
        comp.setSettlPartyRole(PartyRole.Agent);
        
        comp.setNoSettlPartySubIDs(2);
        comp.getSettlPartySubIDGroups()[0].setSettlPartySubID("SubId_1");
        comp.getSettlPartySubIDGroups()[0].setSettlPartySubIDType(PartySubIDType.PositionAccountType);
        comp.getSettlPartySubIDGroups()[1].setSettlPartySubID("SubId_2");
        comp.getSettlPartySubIDGroups()[1].setSettlPartySubIDType(PartySubIDType.Application);
    }

    public void populate2(SettlPartyGroup comp) {
        comp.setSettlPartyID("party ID 2");
        comp.setSettlPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        comp.setSettlPartyRole(PartyRole.AssetManager);
        
        comp.setNoSettlPartySubIDs(2);
        comp.getSettlPartySubIDGroups()[0].setSettlPartySubID("SubId_3");
        comp.getSettlPartySubIDGroups()[0].setSettlPartySubIDType(PartySubIDType.Firm);
        comp.getSettlPartySubIDGroups()[1].setSettlPartySubID("SubId_4");
        comp.getSettlPartySubIDGroups()[1].setSettlPartySubIDType(PartySubIDType.BIC);
    }

    public void check(SettlPartyGroup expected, SettlPartyGroup actual) throws Exception {
        assertEquals(expected.getSettlPartyID(), actual.getSettlPartyID());
        assertEquals(expected.getSettlPartyIDSource().getValue(), actual.getSettlPartyIDSource().getValue());
        assertEquals(expected.getSettlPartyRole(), actual.getSettlPartyRole());
        
        assertEquals(expected.getNoSettlPartySubIDs().intValue(), actual.getNoSettlPartySubIDs().intValue());
        assertEquals(expected.getSettlPartySubIDGroups()[0].getSettlPartySubID(), actual.getSettlPartySubIDGroups()[0].getSettlPartySubID());
        assertEquals(expected.getSettlPartySubIDGroups()[0].getSettlPartySubIDType(), actual.getSettlPartySubIDGroups()[0].getSettlPartySubIDType());
        assertEquals(expected.getSettlPartySubIDGroups()[1].getSettlPartySubID(), actual.getSettlPartySubIDGroups()[1].getSettlPartySubID());
        assertEquals(expected.getSettlPartySubIDGroups()[1].getSettlPartySubIDType(), actual.getSettlPartySubIDGroups()[1].getSettlPartySubIDType());
    }
}
