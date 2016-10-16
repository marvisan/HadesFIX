/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegAllocGroup50SP1TestData.java
 *
 * $Id: LegAllocGroup50SP1TestData.java,v 1.3 2011-10-29 09:42:24 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.NestedParties244TestData;
import net.hades.fix.message.group.LegAllocGroup;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for LegAllocGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class LegAllocGroup50SP1TestData extends MsgTest {

    private static final LegAllocGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new LegAllocGroup50SP1TestData();
    }

    public static LegAllocGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(LegAllocGroup grp) throws UnsupportedEncodingException {
        grp.setLegAllocAccount("22322444424");
        grp.setLegIndividualAllocID("IND4444");

        grp.setNestedParties2();
        NestedParties244TestData.getInstance().populate(grp.getNestedParties2());

        grp.setLegAllocQty(13.50d);
        grp.setLegAllocAcctIDSource("2");
        grp.setLegAllocSettlCurrency(Currency.UnitedStatesDollar);
    }

    public void populate2(LegAllocGroup grp) throws UnsupportedEncodingException {
        grp.setLegAllocAccount("2255454644");
        grp.setLegIndividualAllocID("IND3333");

        grp.setNestedParties2();
        NestedParties244TestData.getInstance().populate(grp.getNestedParties2());

        grp.setLegAllocQty(23.50d);
        grp.setLegAllocAcctIDSource("1");
        grp.setLegAllocSettlCurrency(Currency.AustralianDollar);
    }

    public void check(LegAllocGroup expected, LegAllocGroup actual) throws Exception {
        assertEquals(expected.getLegAllocAccount(), actual.getLegAllocAccount());
        assertEquals(expected.getLegIndividualAllocID(), actual.getLegIndividualAllocID());

        NestedParties244TestData.getInstance().check(expected.getNestedParties2(), actual.getNestedParties2());

        assertEquals(expected.getLegAllocQty(), actual.getLegAllocQty());
        assertEquals(expected.getLegAllocAcctIDSource(), actual.getLegAllocAcctIDSource());
        assertEquals(expected.getLegAllocSettlCurrency(), actual.getLegAllocSettlCurrency());
    }
}
