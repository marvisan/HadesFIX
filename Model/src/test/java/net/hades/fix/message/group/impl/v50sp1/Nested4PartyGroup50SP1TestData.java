/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested4PartyGroup50SP1TestData.java
 *
 * $Id: Nested4PartyGroup50SP1TestData.java,v 1.1 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.Nested4PartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for Nested4PartyGroup50SP1 component class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 8:34:33 AM
 */
public class Nested4PartyGroup50SP1TestData extends MsgTest {

    private static final Nested4PartyGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new Nested4PartyGroup50SP1TestData();
    }

    public static Nested4PartyGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(Nested4PartyGroup component) {
        component.setNested4PartyID("ID 1");
        component.setNested4PartyIDSource(PartyIDSource.BIC);
        component.setNested4PartyRole(PartyRole.Agent);
        component.setNoNested4PartySubIDs(new Integer(2));
        component.getNstd4PtysSubGroups()[0].setNested4PartySubID("sub id 1");
        component.getNstd4PtysSubGroups()[0].setNested4PartySubIDType(PartySubIDType.System);
        component.getNstd4PtysSubGroups()[1].setNested4PartySubID("sub id 2");
        component.getNstd4PtysSubGroups()[1].setNested4PartySubIDType(PartySubIDType.Application);
    }

    public void populate2(Nested4PartyGroup component) {
        component.setNested4PartyID("ID 2");
        component.setNested4PartyIDSource(PartyIDSource.NASD);
        component.setNested4PartyRole(PartyRole.ExecutingSystem);
        component.setNoNested4PartySubIDs(new Integer(2));
        component.getNstd4PtysSubGroups()[0].setNested4PartySubID("sub id 3");
        component.getNstd4PtysSubGroups()[0].setNested4PartySubIDType(PartySubIDType.Person);
        component.getNstd4PtysSubGroups()[1].setNested4PartySubID("sub id 4");
        component.getNstd4PtysSubGroups()[1].setNested4PartySubIDType(PartySubIDType.ContactName);
    }

    public void check(Nested4PartyGroup expected, Nested4PartyGroup actual) throws Exception {
        assertEquals(expected.getNested4PartyID(), actual.getNested4PartyID());
        assertEquals(expected.getNested4PartyIDSource().getValue(), actual.getNested4PartyIDSource().getValue());
        assertEquals(expected.getNested4PartyRole().getValue(), actual.getNested4PartyRole().getValue());
        for (int i = 0; i < expected.getNoNested4PartySubIDs().intValue(); i++) {
            assertEquals(expected.getNstd4PtysSubGroups()[i].getNested4PartySubID(), actual.getNstd4PtysSubGroups()[i].getNested4PartySubID());
            assertEquals(expected.getNstd4PtysSubGroups()[i].getNested4PartySubIDType().getValue(),
                actual.getNstd4PtysSubGroups()[i].getNested4PartySubIDType().getValue());
        }
    }
}
