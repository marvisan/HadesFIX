/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MiscFeeGroup40TestData.java
 *
 * $Id: MiscFeeGroup40TestData.java,v 1.2 2011-10-29 09:42:33 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v40;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MiscFeeType;

/**
 * Test utility for MiscFeeGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class MiscFeeGroup40TestData extends MsgTest {

    private static final MiscFeeGroup40TestData INSTANCE;

    static {
        INSTANCE = new MiscFeeGroup40TestData();
    }

    public static MiscFeeGroup40TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MiscFeeGroup grp) throws UnsupportedEncodingException {
        grp.setMiscFeeAmt(34.77d);
        grp.setMiscFeeCurr(Currency.CanadianDollar);
        grp.setMiscFeeType(MiscFeeType.Conversion);
    }

    public void populate2(MiscFeeGroup grp) throws UnsupportedEncodingException {
        grp.setMiscFeeAmt(22.55d);
        grp.setMiscFeeCurr(Currency.UnitedStatesDollar);
        grp.setMiscFeeType(MiscFeeType.ExchangeFees);
    }

    public void check(MiscFeeGroup expected, MiscFeeGroup actual) throws Exception {
        assertEquals(expected.getMiscFeeAmt(), actual.getMiscFeeAmt());
        assertEquals(expected.getMiscFeeCurr(), actual.getMiscFeeCurr());
        assertEquals(expected.getMiscFeeType(), actual.getMiscFeeType());
    }
}
