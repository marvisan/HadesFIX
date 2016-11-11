/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested2PartyGroup50SP1TestData.java
 *
 * $Id: Nested2PartyGroup50SP1TestData.java,v 1.1 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.Nested2PartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for Nested2PartyGroup50SP1 component class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 8:34:33 AM
 */
public class Nested2PartyGroup50SP1TestData extends MsgTest {

    private static final Nested2PartyGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new Nested2PartyGroup50SP1TestData();
    }

    public static Nested2PartyGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(Nested2PartyGroup component) {
        component.setNested2PartyID("ID 1");
        component.setNested2PartyIDSource(PartyIDSource.BIC);
        component.setNested2PartyRole(PartyRole.Agent);
        component.setNoNested2PartySubIDs(new Integer(2));
        component.getNstd2PtysSubGroups()[0].setNested2PartySubID("sub id 1");
        component.getNstd2PtysSubGroups()[0].setNested2PartySubIDType(PartySubIDType.System);
        component.getNstd2PtysSubGroups()[1].setNested2PartySubID("sub id 2");
        component.getNstd2PtysSubGroups()[1].setNested2PartySubIDType(PartySubIDType.Application);
    }

    public void populate2(Nested2PartyGroup component) {
        component.setNested2PartyID("ID 2");
        component.setNested2PartyIDSource(PartyIDSource.NASD);
        component.setNested2PartyRole(PartyRole.ExecutingSystem);
        component.setNoNested2PartySubIDs(new Integer(2));
        component.getNstd2PtysSubGroups()[0].setNested2PartySubID("sub id 3");
        component.getNstd2PtysSubGroups()[0].setNested2PartySubIDType(PartySubIDType.Person);
        component.getNstd2PtysSubGroups()[1].setNested2PartySubID("sub id 4");
        component.getNstd2PtysSubGroups()[1].setNested2PartySubIDType(PartySubIDType.ContactName);
    }

    public void check(Nested2PartyGroup expected, Nested2PartyGroup actual) throws Exception {
        assertEquals(expected.getNested2PartyID(), actual.getNested2PartyID());
        assertEquals(expected.getNested2PartyIDSource().getValue(), actual.getNested2PartyIDSource().getValue());
        assertEquals(expected.getNested2PartyRole().getValue(), actual.getNested2PartyRole().getValue());
        for (int i = 0; i < expected.getNoNested2PartySubIDs().intValue(); i++) {
            assertEquals(expected.getNstd2PtysSubGroups()[i].getNested2PartySubID(), actual.getNstd2PtysSubGroups()[i].getNested2PartySubID());
            assertEquals(expected.getNstd2PtysSubGroups()[i].getNested2PartySubIDType().getValue(),
                actual.getNstd2PtysSubGroups()[i].getNested2PartySubIDType().getValue());
        }
    }
}
