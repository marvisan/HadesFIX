/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionInstructions44TestData.java
 *
 * $Id: DiscretionInstructions44TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.DiscretionLimitType;
import net.hades.fix.message.type.DiscretionMoveType;
import net.hades.fix.message.type.DiscretionOffsetType;
import net.hades.fix.message.type.DiscretionRoundDirection;
import net.hades.fix.message.type.DiscretionScope;

/**
 * Test utility for DiscretionInstructions44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/03/2009, 7:52:01 PM
 */
public class DiscretionInstructions44TestData extends MsgTest {

    private static final DiscretionInstructions44TestData INSTANCE;

    static {
        INSTANCE = new DiscretionInstructions44TestData();
    }

    public static DiscretionInstructions44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DiscretionInstructions component) {
        component.setDiscretionInst(DiscretionInst.RelatedToMarketPrice);
        component.setDiscretionOffsetValue(34.55d);
        component.setDiscretionMoveType(DiscretionMoveType.Floating);
        component.setDiscretionOffsetType(DiscretionOffsetType.BasisPoints);
        component.setDiscretionLimitType(DiscretionLimitType.OrBetter);
        component.setDiscretionRoundDirection(DiscretionRoundDirection.MoreAggressive);
        component.setDiscretionScope(DiscretionScope.National);
    }

    public void check(DiscretionInstructions expected, DiscretionInstructions actual) throws Exception {
        assertEquals(expected.getDiscretionInst(), actual.getDiscretionInst());
        assertEquals(expected.getDiscretionOffsetValue(), actual.getDiscretionOffsetValue());
        assertEquals(expected.getDiscretionMoveType(), actual.getDiscretionMoveType());
        assertEquals(expected.getDiscretionOffsetType(), actual.getDiscretionOffsetType());
        assertEquals(expected.getDiscretionLimitType(), actual.getDiscretionLimitType());
        assertEquals(expected.getDiscretionRoundDirection(), actual.getDiscretionRoundDirection());
        assertEquals(expected.getDiscretionScope(), actual.getDiscretionScope());
    }
}
