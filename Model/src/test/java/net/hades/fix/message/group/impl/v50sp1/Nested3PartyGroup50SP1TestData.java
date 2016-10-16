/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested3PartyGroup50SP1TestData.java
 *
 * $Id: Nested3PartyGroup50SP1TestData.java,v 1.1 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.Nested3PartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test utility for Nested3PartyGroup50SP1 component class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 8:34:33 AM
 */
public class Nested3PartyGroup50SP1TestData extends MsgTest {

    private static final Nested3PartyGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new Nested3PartyGroup50SP1TestData();
    }

    public static Nested3PartyGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(Nested3PartyGroup component) {
        component.setNested3PartyID("ID 1");
        component.setNested3PartyIDSource(PartyIDSource.BIC);
        component.setNested3PartyRole(PartyRole.Agent);
        component.setNoNested3PartySubIDs(new Integer(2));
        component.getNstd3PtysSubGroups()[0].setNested3PartySubID("sub id 1");
        component.getNstd3PtysSubGroups()[0].setNested3PartySubIDType(PartySubIDType.System);
        component.getNstd3PtysSubGroups()[1].setNested3PartySubID("sub id 2");
        component.getNstd3PtysSubGroups()[1].setNested3PartySubIDType(PartySubIDType.Application);
    }

    public void populate2(Nested3PartyGroup component) {
        component.setNested3PartyID("ID 2");
        component.setNested3PartyIDSource(PartyIDSource.NASD);
        component.setNested3PartyRole(PartyRole.ExecutingSystem);
        component.setNoNested3PartySubIDs(new Integer(2));
        component.getNstd3PtysSubGroups()[0].setNested3PartySubID("sub id 3");
        component.getNstd3PtysSubGroups()[0].setNested3PartySubIDType(PartySubIDType.Person);
        component.getNstd3PtysSubGroups()[1].setNested3PartySubID("sub id 4");
        component.getNstd3PtysSubGroups()[1].setNested3PartySubIDType(PartySubIDType.ContactName);
    }

    public void check(Nested3PartyGroup expected, Nested3PartyGroup actual) throws Exception {
        assertEquals(expected.getNested3PartyID(), actual.getNested3PartyID());
        assertEquals(expected.getNested3PartyIDSource().getValue(), actual.getNested3PartyIDSource().getValue());
        assertEquals(expected.getNested3PartyRole().getValue(), actual.getNested3PartyRole().getValue());
        for (int i = 0; i < expected.getNoNested3PartySubIDs().intValue(); i++) {
            assertEquals(expected.getNstd3PtysSubGroups()[i].getNested3PartySubID(), actual.getNstd3PtysSubGroups()[i].getNested3PartySubID());
            assertEquals(expected.getNstd3PtysSubGroups()[i].getNested3PartySubIDType().getValue(),
                actual.getNstd3PtysSubGroups()[i].getNested3PartySubIDType().getValue());
        }
    }
}
