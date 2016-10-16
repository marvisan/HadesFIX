/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContAmtGroup43TestData.java
 *
 * $Id: ContAmtGroup43TestData.java,v 1.2 2011-10-29 09:42:16 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.ContAmtGroup;
import net.hades.fix.message.type.ContAmtType;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for ContAmtGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class ContAmtGroup43TestData extends MsgTest {

    private static final ContAmtGroup43TestData INSTANCE;

    static {
        INSTANCE = new ContAmtGroup43TestData();
    }

    public static ContAmtGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(ContAmtGroup grp) throws UnsupportedEncodingException {
        grp.setContAmtType(ContAmtType.DiscountAmount);
        grp.setContAmtValue(33.77d);
        grp.setContAmtCurr(Currency.AustralianDollar);
    }

    public void populate2(ContAmtGroup grp) throws UnsupportedEncodingException {
        grp.setContAmtType(ContAmtType.CommissionPercent);
        grp.setContAmtValue(33.88);
        grp.setContAmtCurr(Currency.UnitedStatesDollar);
    }

    public void check(ContAmtGroup expected, ContAmtGroup actual) throws Exception {
        assertEquals(expected.getContAmtType(), actual.getContAmtType());
        assertEquals(expected.getContAmtValue(), actual.getContAmtValue());
        assertEquals(expected.getContAmtCurr(), actual.getContAmtCurr());
    }
}
