/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartyGroup50SP1TestData.java
 *
 * $Id: InstrumentPartyGroup50SP1TestData.java,v 1.1 2009-07-06 03:19:11 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.InstrumentPartyGroup;
import net.hades.fix.message.group.InstrumentPartySubIDGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for InstrumentPartySubGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class InstrumentPartyGroup50SP1TestData extends MsgTest {

    private static final InstrumentPartyGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new InstrumentPartyGroup50SP1TestData();
    }

    public static InstrumentPartyGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrumentPartyGroup component) {
        component.setInstrumentPartyID("ID 1");
        component.setInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setNoInstrumentPartySubIDs(new Integer(2));
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubID("sub id 1");
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubIDType(PartySubIDType.System);
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubID("sub id 2");
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubIDType(PartySubIDType.Application);
    }

    public void populate2(InstrumentPartyGroup component) {
        component.setInstrumentPartyID("ID 2");
        component.setInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setNoInstrumentPartySubIDs(new Integer(2));
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubID("sub id 3");
        component.getInstrumentPartySubIDGroups()[0].setInstrumentPartySubIDType(PartySubIDType.EmailAddress);
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubID("sub id 4");
        component.getInstrumentPartySubIDGroups()[1].setInstrumentPartySubIDType(PartySubIDType.ContactName);
    }

    public void check(InstrumentPartySubIDGroup expected, InstrumentPartySubIDGroup actual) throws Exception {
        assertEquals(expected.getInstrumentPartySubID(), actual.getInstrumentPartySubID());
        assertEquals(expected.getInstrumentPartySubIDType().getValue(), actual.getInstrumentPartySubIDType().getValue());
    }

    public void check(InstrumentPartyGroup expected, InstrumentPartyGroup actual) throws Exception {
        assertEquals(expected.getInstrumentPartyID(), actual.getInstrumentPartyID());
        assertEquals(expected.getInstrumentPartyIDSource().getValue(), actual.getInstrumentPartyIDSource().getValue());
        assertEquals(expected.getInstrumentPartyRole().getValue(), actual.getInstrumentPartyRole().getValue());
        for (int i = 0; i < expected.getNoInstrumentPartySubIDs().intValue(); i++) {
            assertEquals(expected.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID(),
                actual.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID());
            assertEquals(expected.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue(),
                actual.getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue());
        }
    }
}
