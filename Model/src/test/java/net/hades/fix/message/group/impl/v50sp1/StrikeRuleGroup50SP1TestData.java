/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * StrikeRuleGroup50SP1TestData.java
 *
 * $Id: StrikeRuleGroup50SP1TestData.java,v 1.2 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;


import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.StrikeRuleGroup;
import net.hades.fix.message.type.StrikeExerciseStyle;

/**
 * Test utility for StrikeRuleGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class StrikeRuleGroup50SP1TestData extends MsgTest {

    private static final StrikeRuleGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new StrikeRuleGroup50SP1TestData();
    }

    public static StrikeRuleGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(StrikeRuleGroup grp) {
        grp.setStrikeRuleID("ID887766");
        grp.setStartStrikePxRange(20.50);
        grp.setEndStrikePxRange(30.50);
        grp.setStrikeIncrement(3.5);
        grp.setStrikeExerciseStyle(StrikeExerciseStyle.European);
        
        grp.setNoMaturityRules(2);
        MaturityRuleGroup50SP1TestData.getInstance().populate1(grp.getMaturityRuleGroups()[0]);
        MaturityRuleGroup50SP1TestData.getInstance().populate2(grp.getMaturityRuleGroups()[1]);
    }

    public void populate2(StrikeRuleGroup grp) {
        grp.setStrikeRuleID("ID889966");
        grp.setStartStrikePxRange(40.50);
        grp.setEndStrikePxRange(90.50);
        grp.setStrikeIncrement(8.5);
        grp.setStrikeExerciseStyle(StrikeExerciseStyle.American);

        grp.setNoMaturityRules(2);
        MaturityRuleGroup50SP1TestData.getInstance().populate1(grp.getMaturityRuleGroups()[0]);
        MaturityRuleGroup50SP1TestData.getInstance().populate2(grp.getMaturityRuleGroups()[1]);
    }

    public void check(StrikeRuleGroup expected, StrikeRuleGroup actual) {
        assertEquals(expected.getStrikeRuleID(), actual.getStrikeRuleID());
        assertEquals(expected.getStartStrikePxRange(), actual.getStartStrikePxRange());
        assertEquals(expected.getEndStrikePxRange(), actual.getEndStrikePxRange());
        assertEquals(expected.getStrikeIncrement(), actual.getStrikeIncrement());
        assertEquals(expected.getStrikeExerciseStyle(), actual.getStrikeExerciseStyle());

        assertEquals(expected.getNoMaturityRules(), actual.getNoMaturityRules());
        MaturityRuleGroup50SP1TestData.getInstance().check(expected.getMaturityRuleGroups()[0], actual.getMaturityRuleGroups()[0]);
        MaturityRuleGroup50SP1TestData.getInstance().check(expected.getMaturityRuleGroups()[1], actual.getMaturityRuleGroups()[1]);
    }
}
