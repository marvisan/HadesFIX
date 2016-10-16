/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionGroup43TestData.java
 *
 * $Id: TradingSessionGroup43TestData.java,v 1.1 2010-12-12 09:13:11 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for TradingSessionGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class TradingSessionGroup43TestData extends MsgTest {

    private static final TradingSessionGroup43TestData INSTANCE;

    static {
        INSTANCE = new TradingSessionGroup43TestData();
    }

    public static TradingSessionGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TradingSessionGroup group) throws UnsupportedEncodingException {
        group.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        group.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
    }

    public void populate2(TradingSessionGroup group) throws UnsupportedEncodingException {
        group.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        group.setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
    }

    public void check(TradingSessionGroup expected, TradingSessionGroup actual) throws Exception {
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
    }
}
