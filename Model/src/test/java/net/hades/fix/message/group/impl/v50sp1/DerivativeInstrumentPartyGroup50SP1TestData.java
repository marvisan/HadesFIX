/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrumentPartyGroup50SP1TestData.java
 *
 * $Id: DerivativeInstrumentPartyGroup50SP1TestData.java,v 1.1 2011-09-22 08:54:33 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.DerivativeInstrumentPartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for InstrumentPartySubGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class DerivativeInstrumentPartyGroup50SP1TestData extends MsgTest {

    private static final DerivativeInstrumentPartyGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new DerivativeInstrumentPartyGroup50SP1TestData();
    }

    public static DerivativeInstrumentPartyGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(DerivativeInstrumentPartyGroup component) {
        component.setDerivativeInstrumentPartyID("ID 1");
        component.setDerivativeInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setDerivativeInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setNoDerivativeInstrumentPartySubIDs(new Integer(2));
        component.getDerivativeInstrumentPartySubIDGroups()[0].setDerivativeInstrumentPartySubID("sub id 1");
        component.getDerivativeInstrumentPartySubIDGroups()[0].setDerivativeInstrumentPartySubIDType(PartySubIDType.System);
        component.getDerivativeInstrumentPartySubIDGroups()[1].setDerivativeInstrumentPartySubID("sub id 2");
        component.getDerivativeInstrumentPartySubIDGroups()[1].setDerivativeInstrumentPartySubIDType(PartySubIDType.Application);
    }

    public void populate2(DerivativeInstrumentPartyGroup component) {
        component.setDerivativeInstrumentPartyID("ID 2");
        component.setDerivativeInstrumentPartyIDSource(PartyIDSource.CSD);
        component.setDerivativeInstrumentPartyRole(PartyRole.AllocationEntity);
        component.setNoDerivativeInstrumentPartySubIDs(new Integer(2));
        component.getDerivativeInstrumentPartySubIDGroups()[0].setDerivativeInstrumentPartySubID("sub id 3");
        component.getDerivativeInstrumentPartySubIDGroups()[0].setDerivativeInstrumentPartySubIDType(PartySubIDType.EmailAddress);
        component.getDerivativeInstrumentPartySubIDGroups()[1].setDerivativeInstrumentPartySubID("sub id 4");
        component.getDerivativeInstrumentPartySubIDGroups()[1].setDerivativeInstrumentPartySubIDType(PartySubIDType.ContactName);
    }

    public void check(DerivativeInstrumentPartyGroup expected, DerivativeInstrumentPartyGroup actual) throws Exception {
        assertEquals(expected.getDerivativeInstrumentPartyID(), actual.getDerivativeInstrumentPartyID());
        assertEquals(expected.getDerivativeInstrumentPartyIDSource(), actual.getDerivativeInstrumentPartyIDSource());
        assertEquals(expected.getDerivativeInstrumentPartyRole().getValue(), actual.getDerivativeInstrumentPartyRole().getValue());
        for (int i = 0; i < expected.getNoDerivativeInstrumentPartySubIDs(); i++) {
            assertEquals(expected.getDerivativeInstrumentPartySubIDGroups()[i].getDerivativeInstrumentPartySubID(),
                actual.getDerivativeInstrumentPartySubIDGroups()[i].getDerivativeInstrumentPartySubID());
            assertEquals(expected.getDerivativeInstrumentPartySubIDGroups()[i].getDerivativeInstrumentPartySubIDType().getValue(),
                actual.getDerivativeInstrumentPartySubIDGroups()[i].getDerivativeInstrumentPartySubIDType().getValue());
        }
    }
}
