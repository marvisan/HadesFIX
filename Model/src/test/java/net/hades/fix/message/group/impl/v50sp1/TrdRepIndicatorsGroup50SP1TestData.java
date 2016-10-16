/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdRepIndicatorsGroup50SP1TestData.java
 *
 * $Id: TrdRepIndicatorsGroup50SP1TestData.java,v 1.1 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.TrdRepIndicatorsGroup;
import net.hades.fix.message.type.PartyRole;

/**
 * Test utility for TrdRepIndicatorsGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class TrdRepIndicatorsGroup50SP1TestData extends MsgTest {

    private static final TrdRepIndicatorsGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new TrdRepIndicatorsGroup50SP1TestData();
    }

    public static TrdRepIndicatorsGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TrdRepIndicatorsGroup grp) throws UnsupportedEncodingException {
        grp.setTrdRepPartyRole(PartyRole.InterestedParty);
        grp.setTrdRepIndicator(Boolean.TRUE);
    }

    public void populate2(TrdRepIndicatorsGroup grp) throws UnsupportedEncodingException {
        grp.setTrdRepPartyRole(PartyRole.Agent);
        grp.setTrdRepIndicator(Boolean.FALSE);
    }

    public void check(TrdRepIndicatorsGroup expected, TrdRepIndicatorsGroup actual) throws Exception {
        assertEquals(expected.getTrdRepPartyRole(), actual.getTrdRepPartyRole());
        assertEquals(expected.getTrdRepIndicator(), actual.getTrdRepIndicator());
    }
}
