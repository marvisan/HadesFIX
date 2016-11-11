/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartySubIDGroup50TestData.java
 *
 * $Id: PartySubGroup50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PartySubGroup;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for PartySubIDGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class PartySubGroup50TestData extends MsgTest {

    private static final PartySubGroup50TestData INSTANCE;

    static {
        INSTANCE = new PartySubGroup50TestData();
    }

    public static PartySubGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PartySubGroup component) {
        component.setPartySubID("party sub ID 1");
        component.setPartySubIDType(PartySubIDType.BIC);
    }

    public void populate2(PartySubGroup component) {
        component.setPartySubID("party sub ID 2");
        component.setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
    }

    public void populate3(PartySubGroup component) {
        component.setPartySubID("party sub ID 3");
        component.setPartySubIDType(PartySubIDType.ContactName);
    }

    public void populate4(PartySubGroup component) {
        component.setPartySubID("party sub ID 4");
        component.setPartySubIDType(PartySubIDType.FaxNumber);
    }

    public void check(PartySubGroup expected, PartySubGroup actual) throws Exception {
        assertEquals(expected.getPartySubID(), actual.getPartySubID());
        assertEquals(expected.getPartySubIDType().getValue(), actual.getPartySubIDType().getValue());
    }
}
