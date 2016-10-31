/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TargetParties50SP2TestData.java
 *
 * $Id: TargetParties50SP2TestData.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

/**
 * Test utility for TargetParties50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class TargetParties50SP2TestData extends MsgTest {

    private static final TargetParties50SP2TestData INSTANCE;

    static {
        INSTANCE = new TargetParties50SP2TestData();
    }

    public static TargetParties50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TargetParties component) {
        component.setNoTargetPartyIDs(new Integer(2));
        component.getTargetPartyGroups()[0].setTargetPartyID("TARG PARTY 1");
        component.getTargetPartyGroups()[0].setTargetPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        component.getTargetPartyGroups()[0].setTargetPartyRole(PartyRole.Agent);
        component.getTargetPartyGroups()[1].setTargetPartyID("TARG PARTY 2");
        component.getTargetPartyGroups()[1].setTargetPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        component.getTargetPartyGroups()[1].setTargetPartyRole(PartyRole.AssetManager);
    }

    public void check(TargetParties expected, TargetParties actual) throws Exception {
        assertEquals(expected.getNoTargetPartyIDs().intValue(), actual.getNoTargetPartyIDs().intValue());
        for (int i = 0; i < actual.getNoTargetPartyIDs().intValue(); i++) {
            assertEquals(expected.getTargetPartyGroups()[i].getTargetPartyID(), actual.getTargetPartyGroups()[i].getTargetPartyID());
            assertEquals(expected.getTargetPartyGroups()[i].getTargetPartyIDSource(), actual.getTargetPartyGroups()[i].getTargetPartyIDSource());
            assertEquals(expected.getTargetPartyGroups()[i].getTargetPartyRole(), actual.getTargetPartyGroups()[i].getTargetPartyRole());
        }
    }
}
