/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties50SP2TestData.java
 *
 * $Id: Parties50SP2TestData.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.group.impl.v50sp2.PartyGroup50SP2TestData;

/**
 * Test utility for Parties50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class Parties50SP2TestData extends MsgTest {

    private static final Parties50SP2TestData INSTANCE;

    static {
        INSTANCE = new Parties50SP2TestData();
    }

    public static Parties50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void check(Parties expected, Parties actual) throws Exception {
        assertEquals(expected.getNoPartyIDs().intValue(), actual.getNoPartyIDs().intValue());
        for (int i = 0; i < actual.getNoPartyIDs().intValue(); i++) {
            PartyGroup50SP2TestData.getInstance().check(expected.getPartyIDGroups()[i], actual.getPartyIDGroups()[i]);
        }
    }
    
    public void populate(Parties component) {
        component.setNoPartyIDs(new Integer(2));
        PartyGroup50SP2TestData.getInstance().populate1(component.getPartyIDGroups()[0]);
        PartyGroup50SP2TestData.getInstance().populate2(component.getPartyIDGroups()[1]);
    }
}
