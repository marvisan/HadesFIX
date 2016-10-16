/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosAmtGroup50TestData.java
 *
 * $Id: PosAmtGroup50TestData.java,v 1.2 2011-10-29 09:42:29 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PosAmtType;

/**
 * Test utility for PosAmtGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class PosAmtGroup50TestData extends MsgTest {

    private static final PosAmtGroup50TestData INSTANCE;

    static {
        INSTANCE = new PosAmtGroup50TestData();
    }

    public static PosAmtGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PosAmtGroup grp) throws UnsupportedEncodingException {
        grp.setPosAmtType(PosAmtType.CashAmount);
        grp.setPosAmt(23.5d);
        grp.setPositionCurrency(Currency.CanadianDollar);
    }

    public void populate2(PosAmtGroup grp) throws UnsupportedEncodingException {
        grp.setPosAmtType(PosAmtType.CashResidualAmount);
        grp.setPosAmt(12.5d);
        grp.setPositionCurrency(Currency.AustralianDollar);
    }

    public void check(PosAmtGroup expected, PosAmtGroup actual) throws Exception {
        assertEquals(expected.getPosAmtType(), actual.getPosAmtType());
        assertEquals(expected.getPosAmt(), actual.getPosAmt());
        assertEquals(expected.getPositionCurrency(), actual.getPositionCurrency());
    }
}
