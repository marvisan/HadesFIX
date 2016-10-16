/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegInstructions50TestData.java
 *
 * $Id: PegInstructions50TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.type.PegLimitType;
import net.hades.fix.message.type.PegMoveType;
import net.hades.fix.message.type.PegOffsetType;
import net.hades.fix.message.type.PegPriceType;
import net.hades.fix.message.type.PegRoundDirection;
import net.hades.fix.message.type.PegScope;

/**
 * Test utility for PegInstructions50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/03/2009, 7:52:01 PM
 */
public class PegInstructions50TestData extends MsgTest {

    private static final PegInstructions50TestData INSTANCE;

    static {
        INSTANCE = new PegInstructions50TestData();
    }

    public static PegInstructions50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(PegInstructions comp) {
        comp.setPegOffsetValue(22.55d);
        comp.setPegPriceType(PegPriceType.MidPricePeg);
        comp.setPegMoveType(PegMoveType.Floating);
        comp.setPegOffsetType(PegOffsetType.PriceTier);
        comp.setPegLimitType(PegLimitType.Strict);
        comp.setPegRoundDirection(PegRoundDirection.MoreAggressive);
        comp.setPegScope(PegScope.Local);
        comp.setPegSecurityIDSource("L");
        comp.setPegSecurityID("BHP.ASX");
        comp.setPegSymbol("BHP");
        comp.setPegSecurityDesc("BHP Shares");
    }

    public void check(PegInstructions expected, PegInstructions actual) throws Exception {
        assertEquals(expected.getPegOffsetValue(), actual.getPegOffsetValue());
        assertEquals(expected.getPegPriceType(), actual.getPegPriceType());
        assertEquals(expected.getPegMoveType(), actual.getPegMoveType());
        assertEquals(expected.getPegOffsetType(), actual.getPegOffsetType());
        assertEquals(expected.getPegLimitType(), actual.getPegLimitType());
        assertEquals(expected.getPegRoundDirection(), actual.getPegRoundDirection());
        assertEquals(expected.getPegScope(), actual.getPegScope());
        assertEquals(expected.getPegSecurityIDSource(), actual.getPegSecurityIDSource());
        assertEquals(expected.getPegSecurityID(), actual.getPegSecurityID());
        assertEquals(expected.getPegSymbol(), actual.getPegSymbol());
        assertEquals(expected.getPegSecurityDesc(), actual.getPegSecurityDesc());
    }
}
