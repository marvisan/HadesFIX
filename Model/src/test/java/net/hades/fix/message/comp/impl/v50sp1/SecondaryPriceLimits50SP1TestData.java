/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecondaryPriceLimits50SP1TestData.java
 *
 * $Id: SecondaryPriceLimits50SP1TestData.java,v 1.1 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.SecondaryPriceLimits;
import net.hades.fix.message.type.PriceLimitType;

/**
 * Test utility for SecondaryPriceLimits component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class SecondaryPriceLimits50SP1TestData extends MsgTest {

    private static final SecondaryPriceLimits50SP1TestData INSTANCE;

    static {
        INSTANCE = new SecondaryPriceLimits50SP1TestData();
    }

    public static SecondaryPriceLimits50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecondaryPriceLimits comp) {
        comp.setSecondaryPriceLimitType(PriceLimitType.Percentage);
        comp.setSecondaryLowLimitPrice(34.56d);
        comp.setSecondaryHighLimitPrice(66.98d);
        comp.setSecondaryTradingReferencePrice(55.88d);
    }

    public void check(SecondaryPriceLimits expected, SecondaryPriceLimits actual) {
        assertEquals(expected.getSecondaryPriceLimitType(), actual.getSecondaryPriceLimitType());
        assertEquals(expected.getSecondaryLowLimitPrice(), actual.getSecondaryLowLimitPrice());
        assertEquals(expected.getSecondaryHighLimitPrice(), actual.getSecondaryHighLimitPrice());
        assertEquals(expected.getSecondaryTradingReferencePrice(), actual.getSecondaryTradingReferencePrice());
    }
}
