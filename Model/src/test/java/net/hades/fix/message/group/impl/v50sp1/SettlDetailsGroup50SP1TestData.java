/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlDetailsGroup50SP1TestData.java
 *
 * $Id: SettlDetailsGroup50SP1TestData.java,v 1.1 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.SettlDetailsGroup;
import net.hades.fix.message.type.SettlObligSource;

/**
 * Test utility for SettlDetailsGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class SettlDetailsGroup50SP1TestData extends MsgTest {

    private static final SettlDetailsGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new SettlDetailsGroup50SP1TestData();
    }

    public static SettlDetailsGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SettlDetailsGroup grp) throws UnsupportedEncodingException {
        grp.setSettlObligSource(SettlObligSource.Investor);

        grp.setNoSettlPartyIDs(2);
        SettlPartyGroup50SP1TestData.getInstance().populate1(grp.getSettlPartyGroups()[0]);
        SettlPartyGroup50SP1TestData.getInstance().populate2(grp.getSettlPartyGroups()[1]);
    }

    public void populate2(SettlDetailsGroup grp) throws UnsupportedEncodingException {
        grp.setSettlObligSource(SettlObligSource.InstructionsOfBroker);

        grp.setNoSettlPartyIDs(2);
        SettlPartyGroup50SP1TestData.getInstance().populate1(grp.getSettlPartyGroups()[0]);
        SettlPartyGroup50SP1TestData.getInstance().populate2(grp.getSettlPartyGroups()[1]);
    }

    public void check(SettlDetailsGroup expected, SettlDetailsGroup actual) throws Exception {
        assertEquals(expected.getSettlObligSource(), actual.getSettlObligSource());

        assertEquals(expected.getNoSettlPartyIDs(), actual.getNoSettlPartyIDs());
        SettlPartyGroup50SP1TestData.getInstance().check(expected.getSettlPartyGroups()[0], actual.getSettlPartyGroups()[0]);
        SettlPartyGroup50SP1TestData.getInstance().check(expected.getSettlPartyGroups()[1], actual.getSettlPartyGroups()[1]);
    }
}
