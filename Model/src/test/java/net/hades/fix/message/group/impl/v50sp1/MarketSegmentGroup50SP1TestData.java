/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketSegmentGroup50SP1TestData.java
 *
 * $Id: MarketSegmentGroup50SP1TestData.java,v 1.3 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;


import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.SecurityTradingRules50SP1TestData;
import net.hades.fix.message.group.MarketSegmentGroup;

/**
 * Test utility for MarketSegmentGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class MarketSegmentGroup50SP1TestData extends MsgTest {

    private static final MarketSegmentGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new MarketSegmentGroup50SP1TestData();
    }

    public static MarketSegmentGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(MarketSegmentGroup grp) {
        grp.setMarketID("CBOT");
        grp.setMarketSegmentID("SEG 1");

        grp.setSecurityTradingRules();
        SecurityTradingRules50SP1TestData.getInstance().populate1(grp.getSecurityTradingRules());

        grp.setNoStrikeRules(2);
        StrikeRuleGroup50SP1TestData.getInstance().populate1(grp.getStrikeRuleGroups()[0]);
        StrikeRuleGroup50SP1TestData.getInstance().populate2(grp.getStrikeRuleGroups()[1]);
    }

    public void populate2(MarketSegmentGroup grp) {
        grp.setMarketID("NYSE");
        grp.setMarketSegmentID("SEG 2");

        grp.setSecurityTradingRules();
        SecurityTradingRules50SP1TestData.getInstance().populate2(grp.getSecurityTradingRules());

        grp.setNoStrikeRules(2);
        StrikeRuleGroup50SP1TestData.getInstance().populate1(grp.getStrikeRuleGroups()[0]);
        StrikeRuleGroup50SP1TestData.getInstance().populate2(grp.getStrikeRuleGroups()[1]);
    }

    public void check(MarketSegmentGroup expected, MarketSegmentGroup actual) {
        assertEquals(expected.getMarketID(), actual.getMarketID());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());

        SecurityTradingRules50SP1TestData.getInstance().check(expected.getSecurityTradingRules(), actual.getSecurityTradingRules());

        assertEquals(expected.getNoStrikeRules(), actual.getNoStrikeRules());
        StrikeRuleGroup50SP1TestData.getInstance().check(expected.getStrikeRuleGroups()[0], expected.getStrikeRuleGroups()[0]);
        StrikeRuleGroup50SP1TestData.getInstance().check(expected.getStrikeRuleGroups()[1], expected.getStrikeRuleGroups()[1]);
    }
}
