/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup50SP2TestData.java
 *
 * $Id: NestedPartyGroup50SP2TestData.java,v 1.2 2009-11-21 09:57:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for NestedPartyGroup50SP2 component class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/04/2009, 8:34:33 AM
 */
public class NestedPartyGroup50SP2TestData extends MsgTest {

    private static final NestedPartyGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new NestedPartyGroup50SP2TestData();
    }

    public static NestedPartyGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(NestedPartyGroup component) {
        component.setNestedPartyID("ID 1");
        component.setNestedPartyIDSource(PartyIDSource.CSD);
        component.setNestedPartyRole(PartyRole.AllocationEntity);
        component.setNoNestedPartySubIDs(new Integer(2));
        component.getNstdPtysSubGroups()[0].setNestedPartySubID("sub id 1");
        component.getNstdPtysSubGroups()[0].setNestedPartySubIDType(PartySubIDType.CashAccountNumber);
        component.getNstdPtysSubGroups()[1].setNestedPartySubID("sub id 2");
        component.getNstdPtysSubGroups()[1].setNestedPartySubIDType(PartySubIDType.Application);
    }

    public void populate2(NestedPartyGroup component) {
        component.setNestedPartyID("ID 2");
        component.setNestedPartyIDSource(PartyIDSource.CSD);
        component.setNestedPartyRole(PartyRole.AllocationEntity);
        component.setNoNestedPartySubIDs(new Integer(2));
        component.getNstdPtysSubGroups()[0].setNestedPartySubID("sub id 3");
        component.getNstdPtysSubGroups()[0].setNestedPartySubIDType(PartySubIDType.CurrencyDeliveryIdentifier);
        component.getNstdPtysSubGroups()[1].setNestedPartySubID("sub id 4");
        component.getNstdPtysSubGroups()[1].setNestedPartySubIDType(PartySubIDType.ContactName);
    }

    public void check(NestedPartyGroup expected, NestedPartyGroup actual) throws Exception {
        assertEquals(expected.getNestedPartyID(), actual.getNestedPartyID());
        assertEquals(expected.getNestedPartyIDSource().getValue(), actual.getNestedPartyIDSource().getValue());
        assertEquals(expected.getNestedPartyRole().getValue(), actual.getNestedPartyRole().getValue());
        for (int i = 0; i < actual.getNoNestedPartySubIDs().intValue(); i++) {
            assertEquals(expected.getNstdPtysSubGroups()[i].getNestedPartySubID(),
                actual.getNstdPtysSubGroups()[i].getNestedPartySubID());
            assertEquals(expected.getNstdPtysSubGroups()[i].getNestedPartySubIDType().getValue(),
                actual.getNstdPtysSubGroups()[i].getNestedPartySubIDType().getValue());
        }
    }
}
