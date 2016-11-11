/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AffectedOrdGroup43TestData.java
 *
 * $Id: AffectedOrdGroup43TestData.java,v 1.1 2011-05-07 06:58:56 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.AffectedOrdGroup;

/**
 * Test utility for AffectedOrdGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/05/2011, 9:12:46 AM
 */
public class AffectedOrdGroup43TestData extends MsgTest {

    private static final AffectedOrdGroup43TestData INSTANCE;

    static {
        INSTANCE = new AffectedOrdGroup43TestData();
    }

    public static AffectedOrdGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(AffectedOrdGroup grp) throws UnsupportedEncodingException {
        grp.setOrigClOrdID("CLIORD_776655");
        grp.setAffectedOrderID("ORD_554433");
        grp.setAffectedSecondaryOrderID("SECORD_556677");
    }

    public void populate2(AffectedOrdGroup grp) throws UnsupportedEncodingException {
        grp.setOrigClOrdID("CLIORD_332211");
        grp.setAffectedOrderID("ORD_998877");
        grp.setAffectedSecondaryOrderID("SECORD_887766");
    }

    public void check(AffectedOrdGroup expected, AffectedOrdGroup actual) throws Exception {
        assertEquals(expected.getAffectedSecondaryOrderID(), actual.getAffectedSecondaryOrderID());
        assertEquals(expected.getAffectedOrderID(), actual.getAffectedOrderID());
        assertEquals(expected.getAffectedSecondaryOrderID(), actual.getAffectedSecondaryOrderID());
    }
}
