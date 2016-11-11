/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionGroup42TestData.java
 *
 * $Id: TradingSessionGroup42TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for TradingSessionGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class TradingSessionGroup42TestData extends MsgTest {

    private static final TradingSessionGroup42TestData INSTANCE;

    static {
        INSTANCE = new TradingSessionGroup42TestData();
    }

    public static TradingSessionGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TradingSessionGroup group) throws UnsupportedEncodingException {
        group.setTradingSessionID(TradingSessionID.AfterHours.getValue());
    }

    public void populate2(TradingSessionGroup group) throws UnsupportedEncodingException {
        group.setTradingSessionID(TradingSessionID.Day.getValue());
    }

    public void check(TradingSessionGroup expected, TradingSessionGroup actual) throws Exception {
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
    }
}
