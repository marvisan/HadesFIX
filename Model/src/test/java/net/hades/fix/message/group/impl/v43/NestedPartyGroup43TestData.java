/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup43TestData.java
 *
 * $Id: NestedPartyGroup43TestData.java,v 1.2 2011-01-12 11:33:57 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

/**
 * Test utility for NestedPartyGroup43 component class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/04/2009, 8:34:33 AM
 */
public class NestedPartyGroup43TestData extends MsgTest {

    private static final NestedPartyGroup43TestData INSTANCE;

    static {
        INSTANCE = new NestedPartyGroup43TestData();
    }

    public static NestedPartyGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(NestedPartyGroup group) {
        group.setNestedPartyID("ID 1");
        group.setNestedPartyIDSource(PartyIDSource.CSD);
        group.setNestedPartyRole(PartyRole.AllocationEntity);
        group.setNestedPartySubID("D");
    }

    public void populate2(NestedPartyGroup group) {
        group.setNestedPartyID("ID 2");
        group.setNestedPartyIDSource(PartyIDSource.CSD);
        group.setNestedPartyRole(PartyRole.AllocationEntity);
        group.setNestedPartySubID("A");
    }

    public void check(NestedPartyGroup expected, NestedPartyGroup actual) throws Exception {
        assertEquals(expected.getNestedPartyID(), actual.getNestedPartyID());
        assertEquals(expected.getNestedPartyIDSource().getValue(), actual.getNestedPartyIDSource().getValue());
        assertEquals(expected.getNestedPartyRole().getValue(), actual.getNestedPartyRole().getValue());
        assertEquals(expected.getNestedPartySubID(), actual.getNestedPartySubID());
    }
}
