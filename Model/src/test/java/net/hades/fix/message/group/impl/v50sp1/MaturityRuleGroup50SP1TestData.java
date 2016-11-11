/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MaturityRuleGroup50SP1TestData.java
 *
 * $Id: MaturityRuleGroup50SP1TestData.java,v 1.2 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.MaturityRuleGroup;
import net.hades.fix.message.type.MaturityMonthYearFormat;
import net.hades.fix.message.type.MaturityMonthYearIncrementUnits;

/**
 * Test utility for MaturityRule component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class MaturityRuleGroup50SP1TestData extends MsgTest {

    private static final MaturityRuleGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new MaturityRuleGroup50SP1TestData();
    }

    public static MaturityRuleGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MaturityRuleGroup grp) {
        grp.setMaturityRuleID("RULE-5544332");
        grp.setMaturityMonthYearFormat(MaturityMonthYearFormat.YearMonthDay);
        grp.setMaturityMonthYearIncrementUnits(MaturityMonthYearIncrementUnits.Months);
        grp.setStartMaturityMonthYear("112010");
        grp.setEndMaturityMonthYear("122011");
        grp.setMaturityMonthYearIncrement(1);
    }

    public void populate2(MaturityRuleGroup grp) {
        grp.setMaturityRuleID("RULE-5544356");
        grp.setMaturityMonthYearFormat(MaturityMonthYearFormat.YearMonth);
        grp.setMaturityMonthYearIncrementUnits(MaturityMonthYearIncrementUnits.Days);
        grp.setStartMaturityMonthYear("112011");
        grp.setEndMaturityMonthYear("122012");
        grp.setMaturityMonthYearIncrement(2);
    }

    public void check(MaturityRuleGroup expected, MaturityRuleGroup actual) {
        assertEquals(expected.getMaturityRuleID(), actual.getMaturityRuleID());
        assertEquals(expected.getMaturityMonthYearFormat(), actual.getMaturityMonthYearFormat());
        assertEquals(expected.getMaturityMonthYearIncrementUnits(), actual.getMaturityMonthYearIncrementUnits());
        assertEquals(expected.getStartMaturityMonthYear(), actual.getStartMaturityMonthYear());
        assertEquals(expected.getEndMaturityMonthYear(), actual.getEndMaturityMonthYear());
        assertEquals(expected.getMaturityMonthYearIncrement(), actual.getMaturityMonthYearIncrement());
    }
}
