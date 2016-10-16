/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PegInstructions44TestData.java
 *
 * $Id: PegInstructions44TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.type.PegLimitType;
import net.hades.fix.message.type.PegMoveType;
import net.hades.fix.message.type.PegOffsetType;
import net.hades.fix.message.type.PegRoundDirection;
import net.hades.fix.message.type.PegScope;

/**
 * Test utility for PegInstructions44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/03/2009, 7:52:01 PM
 */
public class PegInstructions44TestData extends MsgTest {

    private static final PegInstructions44TestData INSTANCE;

    static {
        INSTANCE = new PegInstructions44TestData();
    }

    public static PegInstructions44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(PegInstructions component) {
        component.setPegOffsetValue(22.55d);
        component.setPegMoveType(PegMoveType.Floating);
        component.setPegOffsetType(PegOffsetType.PriceTier);
        component.setPegLimitType(PegLimitType.Strict);
        component.setPegRoundDirection(PegRoundDirection.MoreAggressive);
        component.setPegScope(PegScope.Local);
    }

    public void check(PegInstructions expected, PegInstructions actual) throws Exception {
        assertEquals(expected.getPegOffsetValue(), actual.getPegOffsetValue());
        assertEquals(expected.getPegMoveType(), actual.getPegMoveType());
        assertEquals(expected.getPegOffsetType(), actual.getPegOffsetType());
        assertEquals(expected.getPegLimitType(), actual.getPegLimitType());
        assertEquals(expected.getPegRoundDirection(), actual.getPegRoundDirection());
        assertEquals(expected.getPegScope(), actual.getPegScope());
    }
}
