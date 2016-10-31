/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MiscFeeGroup44TestData.java
 *
 * $Id: MiscFeeGroup44TestData.java,v 1.2 2011-10-29 09:42:11 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MiscFeeBasis;
import net.hades.fix.message.type.MiscFeeType;

/**
 * Test utility for MiscFeeGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class MiscFeeGroup44TestData extends MsgTest {

    private static final MiscFeeGroup44TestData INSTANCE;

    static {
        INSTANCE = new MiscFeeGroup44TestData();
    }

    public static MiscFeeGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MiscFeeGroup grp) throws UnsupportedEncodingException {
        grp.setMiscFeeAmt(34.77d);
        grp.setMiscFeeCurr(Currency.CanadianDollar);
        grp.setMiscFeeType(MiscFeeType.Conversion);
        grp.setMiscFeeBasis(MiscFeeBasis.Percentage);
    }

    public void populate2(MiscFeeGroup grp) throws UnsupportedEncodingException {
        grp.setMiscFeeAmt(22.55d);
        grp.setMiscFeeCurr(Currency.UnitedStatesDollar);
        grp.setMiscFeeType(MiscFeeType.ExchangeFees);
        grp.setMiscFeeBasis(MiscFeeBasis.Absolute);
    }

    public void check(MiscFeeGroup expected, MiscFeeGroup actual) throws Exception {
        assertEquals(expected.getMiscFeeAmt(), actual.getMiscFeeAmt());
        assertEquals(expected.getMiscFeeCurr(), actual.getMiscFeeCurr());
        assertEquals(expected.getMiscFeeType(), actual.getMiscFeeType());
        assertEquals(expected.getMiscFeeBasis(), actual.getMiscFeeBasis());
    }
}
