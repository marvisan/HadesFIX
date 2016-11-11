/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegSecListGroup43TestData.java
 *
 * $Id: InstrmtLegSecListGroup43TestData.java,v 1.2 2011-10-29 09:42:16 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.InstrumentLeg43TestData;
import net.hades.fix.message.group.InstrmtLegSecListGroup;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for InstrmtLegSecListGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtLegSecListGroup43TestData extends MsgTest {

    private static final InstrmtLegSecListGroup43TestData INSTANCE;

    static {
        INSTANCE = new InstrmtLegSecListGroup43TestData();
    }

    public static InstrmtLegSecListGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtLegSecListGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg43TestData.getInstance().populate1(grp.getInstrumentLeg());

        grp.setLegCurrency(Currency.CanadianDollar);
    }

    public void populate2(InstrmtLegSecListGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg43TestData.getInstance().populate2(grp.getInstrumentLeg());

        grp.setLegCurrency(Currency.AustralianDollar);
    }

    public void check(InstrmtLegSecListGroup expected, InstrmtLegSecListGroup actual) throws Exception {
        InstrumentLeg43TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegCurrency(), actual.getLegCurrency());
    }
}
