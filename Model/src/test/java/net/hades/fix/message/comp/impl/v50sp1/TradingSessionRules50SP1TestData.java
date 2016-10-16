/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionRules50SP1TestData.java
 *
 * $Id: TradingSessionRules50SP1TestData.java,v 1.3 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.TradingSessionRules;
import net.hades.fix.message.type.ExecInstValue;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.MarketDepth;
import net.hades.fix.message.type.MatchType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for TradingSessionRules component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class TradingSessionRules50SP1TestData extends MsgTest {

    private static final TradingSessionRules50SP1TestData INSTANCE;

    static {
        INSTANCE = new TradingSessionRules50SP1TestData();
    }

    public static TradingSessionRules50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradingSessionRules comp) {
        comp.setNoOrdTypeRules(2);
        comp.getOrdTypeRuleGroups()[0].setOrdType(OrdType.Limit);
        comp.getOrdTypeRuleGroups()[1].setOrdType(OrdType.Stop);

        comp.setNoTimeInForceRules(2);
        comp.getTimeInForceRuleGroups()[0].setTimeInForce(TimeInForce.Opening);
        comp.getTimeInForceRuleGroups()[1].setTimeInForce(TimeInForce.Day);

        comp.setNoExecInstRules(2);
        comp.getExecInstRuleGroups()[0].setExecInstValue(ExecInstValue.AON);
        comp.getExecInstRuleGroups()[1].setExecInstValue(ExecInstValue.StrictScale);

        comp.setNoMatchRules(2);
        comp.getMatchRuleGroups()[0].setMatchAlgorithm("EXACT");
        comp.getMatchRuleGroups()[0].setMatchType(MatchType.AutoMatch);
        comp.getMatchRuleGroups()[1].setMatchAlgorithm("START");
        comp.getMatchRuleGroups()[1].setMatchType(MatchType.CallAuction);

        comp.setNoMDFeedTypes(2);
        comp.getMDFeedTypeGroups()[0].setMDFeedType("feed type 1");
        comp.getMDFeedTypeGroups()[0].setMarketDepth(MarketDepth.FullBookDepth);
        comp.getMDFeedTypeGroups()[0].setMDBookType(MDBookType.TopOfBook);
        comp.getMDFeedTypeGroups()[1].setMDFeedType("feed type 2");
        comp.getMDFeedTypeGroups()[1].setMarketDepth(MarketDepth.TopOfBook);
        comp.getMDFeedTypeGroups()[1].setMDBookType(MDBookType.PriceDepth);
    }

    public void check(TradingSessionRules expected, TradingSessionRules actual) {
        assertEquals(expected.getNoOrdTypeRules(), actual.getNoOrdTypeRules());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getOrdTypeRuleGroups()[i].getOrdType(), actual.getOrdTypeRuleGroups()[i].getOrdType());
        }

        assertEquals(expected.getNoTimeInForceRules(), actual.getNoTimeInForceRules());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getTimeInForceRuleGroups()[i].getTimeInForce(), actual.getTimeInForceRuleGroups()[i].getTimeInForce());
        }

        assertEquals(expected.getNoExecInstRules(), actual.getNoExecInstRules());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getExecInstRuleGroups()[i].getExecInstValue(), actual.getExecInstRuleGroups()[i].getExecInstValue());
        }

        assertEquals(expected.getNoMatchRules(), actual.getNoMatchRules());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getMatchRuleGroups()[i].getMatchAlgorithm(), actual.getMatchRuleGroups()[i].getMatchAlgorithm());
            assertEquals(expected.getMatchRuleGroups()[i].getMatchType(), actual.getMatchRuleGroups()[i].getMatchType());
        }

        assertEquals(expected.getNoMDFeedTypes(), actual.getNoMDFeedTypes());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getMDFeedTypeGroups()[i].getMDFeedType(), actual.getMDFeedTypeGroups()[i].getMDFeedType());
            assertEquals(expected.getMDFeedTypeGroups()[i].getMarketDepth(), actual.getMDFeedTypeGroups()[i].getMarketDepth());
            assertEquals(expected.getMDFeedTypeGroups()[i].getMDBookType(), actual.getMDFeedTypeGroups()[i].getMDBookType());
        }
    }
}
