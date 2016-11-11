/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartyGroup50SP2TestData.java
 *
 * $Id: InstrumentPartyGroup50SP2TestData.java,v 1.2 2011-09-28 08:10:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.InstrumentPartyGroup;
import net.hades.fix.message.group.InstrumentPartySubIDGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for InstrumentPartySubGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class InstrumentPartyGroup50SP2TestData extends MsgTest {

    private static final InstrumentPartyGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new InstrumentPartyGroup50SP2TestData();
    }

    public static InstrumentPartyGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrumentPartyGroup component) {
        component.setInstrumentPartyID("ID 1");
        component.setInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setInstrumentPtysSubGrp();
        component.getInstrumentPtysSubGrp().setNoInstrumentPartySubIDs(new Integer(2));
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[0].setInstrumentPartySubID("sub id 1");
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[0].setInstrumentPartySubIDType(PartySubIDType.System);
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[1].setInstrumentPartySubID("sub id 2");
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[1].setInstrumentPartySubIDType(PartySubIDType.Application);
    }

    public void populate2(InstrumentPartyGroup component) {
        component.setInstrumentPartyID("ID 2");
        component.setInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setInstrumentPtysSubGrp();
        component.getInstrumentPtysSubGrp().setNoInstrumentPartySubIDs(new Integer(2));
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[0].setInstrumentPartySubID("sub id 3");
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[0].setInstrumentPartySubIDType(PartySubIDType.EmailAddress);
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[1].setInstrumentPartySubID("sub id 4");
        component.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[1].setInstrumentPartySubIDType(PartySubIDType.ContactName);
    }

    public void check(InstrumentPartySubIDGroup expected, InstrumentPartySubIDGroup actual) throws Exception {
        assertEquals(expected.getInstrumentPartySubID(), actual.getInstrumentPartySubID());
        assertEquals(expected.getInstrumentPartySubIDType().getValue(), actual.getInstrumentPartySubIDType().getValue());
    }

    public void check(InstrumentPartyGroup expected, InstrumentPartyGroup actual) {
        assertEquals(expected.getInstrumentPartyID(), actual.getInstrumentPartyID());
        assertEquals(expected.getInstrumentPartyIDSource().getValue(), actual.getInstrumentPartyIDSource().getValue());
        assertEquals(expected.getInstrumentPartyRole().getValue(), actual.getInstrumentPartyRole().getValue());
        for (int i = 0; i < expected.getInstrumentPtysSubGrp().getNoInstrumentPartySubIDs().intValue(); i++) {
            assertEquals(expected.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID(),
                actual.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[i].getInstrumentPartySubID());
            assertEquals(expected.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue(),
                actual.getInstrumentPtysSubGrp().getInstrumentPartySubIDGroups()[i].getInstrumentPartySubIDType().getValue());
        }
    }
}
