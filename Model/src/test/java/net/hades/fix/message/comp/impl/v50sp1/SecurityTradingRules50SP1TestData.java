/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTradingRules50SP1TestData.java
 *
 * $Id: SecurityTradingRules50SP1TestData.java,v 1.2 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.SecurityTradingRules;
import net.hades.fix.message.type.InstrAttribType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityTradingRules component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class SecurityTradingRules50SP1TestData extends MsgTest {

    private static final SecurityTradingRules50SP1TestData INSTANCE;

    static {
        INSTANCE = new SecurityTradingRules50SP1TestData();
    }

    public static SecurityTradingRules50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SecurityTradingRules comp) {
        comp.setBaseTradingRules();
        BaseTradingRule50SP1TestData.getInstance().populate(comp.getBaseTradingRules());

        comp.setNoTradingSessionRules(2);
        comp.getTradingSessionRuleGroups()[0].setTradingSessionID(TradingSessionID.HalfDay.getValue());
        comp.getTradingSessionRuleGroups()[0].setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
        comp.getTradingSessionRuleGroups()[0].setTradingSessionRules();
        TradingSessionRules50SP1TestData.getInstance().populate(comp.getTradingSessionRuleGroups()[0].getTradingSessionRules());
        comp.getTradingSessionRuleGroups()[1].setTradingSessionID(TradingSessionID.Evening.getValue());
        comp.getTradingSessionRuleGroups()[1].setTradingSessionSubID(TradingSessionSubID.Quiescent.getValue());
        comp.getTradingSessionRuleGroups()[1].setTradingSessionRules();
        TradingSessionRules50SP1TestData.getInstance().populate(comp.getTradingSessionRuleGroups()[1].getTradingSessionRules());

        comp.setNoNestedInstrAttrib(2);
        comp.getNestedInstrmtAttribGroups()[0].setNestedInstrAttribType(InstrAttribType.Flat);
        comp.getNestedInstrmtAttribGroups()[0].setNestedInstrAttribValue("DRSFFF");
        comp.getNestedInstrmtAttribGroups()[1].setNestedInstrAttribType(InstrAttribType.InDefault);
        comp.getNestedInstrmtAttribGroups()[1].setNestedInstrAttribValue("KJHSIO");
    }

    public void populate2(SecurityTradingRules comp) {
        comp.setBaseTradingRules();
        BaseTradingRule50SP1TestData.getInstance().populate(comp.getBaseTradingRules());

        comp.setNoTradingSessionRules(2);
        comp.getTradingSessionRuleGroups()[0].setTradingSessionID(TradingSessionID.Day.getValue());
        comp.getTradingSessionRuleGroups()[0].setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        comp.getTradingSessionRuleGroups()[0].setTradingSessionRules();
        TradingSessionRules50SP1TestData.getInstance().populate(comp.getTradingSessionRuleGroups()[0].getTradingSessionRules());
        comp.getTradingSessionRuleGroups()[1].setTradingSessionID(TradingSessionID.Afternoon.getValue());
        comp.getTradingSessionRuleGroups()[1].setTradingSessionSubID(TradingSessionSubID.Opening.getValue());
        comp.getTradingSessionRuleGroups()[1].setTradingSessionRules();
        TradingSessionRules50SP1TestData.getInstance().populate(comp.getTradingSessionRuleGroups()[1].getTradingSessionRules());

        comp.setNoNestedInstrAttrib(2);
        comp.getNestedInstrmtAttribGroups()[0].setNestedInstrAttribType(InstrAttribType.InterestBearing);
        comp.getNestedInstrmtAttribGroups()[0].setNestedInstrAttribValue("GSFDDS");
        comp.getNestedInstrmtAttribGroups()[1].setNestedInstrAttribType(InstrAttribType.Callable);
        comp.getNestedInstrmtAttribGroups()[1].setNestedInstrAttribValue("JDHDGD");
    }

    public void check(SecurityTradingRules expected, SecurityTradingRules actual) {
        BaseTradingRule50SP1TestData.getInstance().check(expected.getBaseTradingRules(),
                actual.getBaseTradingRules());

        assertEquals(expected.getNoTradingSessionRules(), actual.getNoTradingSessionRules());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getTradingSessionRuleGroups()[i].getTradingSessionID(),
                    actual.getTradingSessionRuleGroups()[i].getTradingSessionID());
            assertEquals(expected.getTradingSessionRuleGroups()[i].getTradingSessionSubID(),
                    actual.getTradingSessionRuleGroups()[i].getTradingSessionSubID());
            TradingSessionRules50SP1TestData.getInstance().check(expected.getTradingSessionRuleGroups()[i].getTradingSessionRules(),
                    actual.getTradingSessionRuleGroups()[i].getTradingSessionRules());
        }

        assertEquals(expected.getNoNestedInstrAttrib(), actual.getNoNestedInstrAttrib());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getNestedInstrmtAttribGroups()[i].getNestedInstrAttribType(),
                    actual.getNestedInstrmtAttribGroups()[i].getNestedInstrAttribType());
            assertEquals(expected.getNestedInstrmtAttribGroups()[i].getNestedInstrAttribValue(),
                    actual.getNestedInstrmtAttribGroups()[i].getNestedInstrAttribValue());
        }
    }
}
