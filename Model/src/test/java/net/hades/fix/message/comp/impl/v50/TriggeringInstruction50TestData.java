/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TriggeringInstruction50TestData.java
 *
 * $Id: TriggeringInstruction50TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.TriggeringInstruction;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TriggerAction;
import net.hades.fix.message.type.TriggerPriceDirection;
import net.hades.fix.message.type.TriggerPriceType;
import net.hades.fix.message.type.TriggerPriceTypeScope;
import net.hades.fix.message.type.TriggerType;

/**
 * Test utility for TriggeringInstruction50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 3:30:53 PM
 */
public class TriggeringInstruction50TestData extends MsgTest {

    private static final TriggeringInstruction50TestData INSTANCE;

    static {
        INSTANCE = new TriggeringInstruction50TestData();
    }

    public static TriggeringInstruction50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TriggeringInstruction comp) throws UnsupportedEncodingException {
        comp.setTriggerType(TriggerType.PriceMovement);
        comp.setTriggerAction(TriggerAction.Activate);
        comp.setTriggerPrice(34.55d);
        comp.setTriggerSymbol("BHP");
        comp.setTriggerSecurityID("BHP.ASX");
        comp.setTriggerSecurityIDSource("K");
        comp.setTriggerSecurityDesc("Shares");
        comp.setTriggerPriceType(TriggerPriceType.BestOffer);
        comp.setTriggerPriceTypeScope(TriggerPriceTypeScope.None);
        comp.setTriggerPriceDirection(TriggerPriceDirection.UP);
        comp.setTriggerNewPrice(44.55d);
        comp.setTriggerOrderType(OrdType.Market);
        comp.setTriggerNewQty(44.44d);
        comp.setTriggerTradingSessionID(TradingSessionID.AfterHours.getValue());
        comp.setTriggerTradingSessionSubID("2");
    }

    public void check(TriggeringInstruction expected, TriggeringInstruction actual) throws Exception {
        assertEquals(expected.getTriggerType(), actual.getTriggerType());
        assertEquals(expected.getTriggerAction(), actual.getTriggerAction());
        assertEquals(expected.getTriggerPrice(), actual.getTriggerPrice());
        assertEquals(expected.getTriggerSymbol(), actual.getTriggerSymbol());
        assertEquals(expected.getTriggerSecurityID(), actual.getTriggerSecurityID());
        assertEquals(expected.getTriggerSecurityIDSource(), actual.getTriggerSecurityIDSource());
        assertEquals(expected.getTriggerSecurityDesc(), actual.getTriggerSecurityDesc());
        assertEquals(expected.getTriggerPriceType(), actual.getTriggerPriceType());
        assertEquals(expected.getTriggerPriceTypeScope(), actual.getTriggerPriceTypeScope());
        assertEquals(expected.getTriggerPriceDirection(), actual.getTriggerPriceDirection());
        assertEquals(expected.getTriggerNewPrice(), actual.getTriggerNewPrice());
        assertEquals(expected.getTriggerOrderType(), actual.getTriggerOrderType());
        assertEquals(expected.getTriggerNewQty(), actual.getTriggerNewQty());
        assertEquals(expected.getTriggerTradingSessionID(), actual.getTriggerTradingSessionID());
        assertEquals(expected.getTriggerTradingSessionSubID(), actual.getTriggerTradingSessionSubID());
    }
}
