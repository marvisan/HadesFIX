/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartyGroup50TestData.java
 *
 * $Id: PartyGroup50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

/**
 * Test utility for PartyIDGroup50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class PartyGroup50TestData extends MsgTest {

    private static final PartyGroup50TestData INSTANCE;

    static {
        INSTANCE = new PartyGroup50TestData();
    }

    public static PartyGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PartyGroup component) {
        component.setNoPartySubIDs(new Integer(2));
        component.setPartyID("party ID 1");
        component.setPartyIDSource(PartyIDSource.BIC);
        component.setPartyRole(PartyRole.Agent);
        PartySubGroup50TestData.getInstance().populate1(component.getPartySubIDGroups()[0]);
        PartySubGroup50TestData.getInstance().populate2(component.getPartySubIDGroups()[1]);
    }

    public void populate2(PartyGroup component) {
        component.setNoPartySubIDs(new Integer(2));
        component.setPartyID("party ID 2");
        component.setPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        component.setPartyRole(PartyRole.AssetManager);
        PartySubGroup50TestData.getInstance().populate3(component.getPartySubIDGroups()[0]);
        PartySubGroup50TestData.getInstance().populate4(component.getPartySubIDGroups()[1]);
    }

    public void check(PartyGroup expected, PartyGroup actual) throws Exception {
        assertEquals(expected.getPartyID(), actual.getPartyID());
        assertEquals(expected.getPartyIDSource().getValue(), actual.getPartyIDSource().getValue());
        assertEquals(expected.getPartyRole(), actual.getPartyRole());
        assertEquals(expected.getNoPartySubIDs().intValue(), actual.getNoPartySubIDs().intValue());
        PartySubGroup50TestData.getInstance().check(expected.getPartySubIDGroups()[0], actual.getPartySubIDGroups()[0]);
        PartySubGroup50TestData.getInstance().check(expected.getPartySubIDGroups()[1], actual.getPartySubIDGroups()[1]);
    }
}
