/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosAmtGroup44TestData.java
 *
 * $Id: PosAmtGroup44TestData.java,v 1.1 2011-10-25 08:29:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.type.PosAmtType;

/**
 * Test utility for PosAmtGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class PosAmtGroup44TestData extends MsgTest {

    private static final PosAmtGroup44TestData INSTANCE;

    static {
        INSTANCE = new PosAmtGroup44TestData();
    }

    public static PosAmtGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PosAmtGroup grp) throws UnsupportedEncodingException {
        grp.setPosAmtType(PosAmtType.CashAmount);
        grp.setPosAmt(23.5d);
    }

    public void populate2(PosAmtGroup grp) throws UnsupportedEncodingException {
        grp.setPosAmtType(PosAmtType.CashResidualAmount);
        grp.setPosAmt(12.5d);
    }

    public void check(PosAmtGroup expected, PosAmtGroup actual) throws Exception {
        assertEquals(expected.getPosAmtType(), actual.getPosAmtType());
        assertEquals(expected.getPosAmt(), actual.getPosAmt());
    }
}
