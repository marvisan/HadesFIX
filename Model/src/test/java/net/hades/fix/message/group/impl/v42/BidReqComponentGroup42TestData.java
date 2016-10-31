/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidReqComponentGroup42TestData.java
 *
 * $Id: BidReqComponentGroup42TestData.java,v 1.1 2011-04-14 11:44:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.BidReqComponentGroup;
import net.hades.fix.message.type.NetGrossInd;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for BidReqComponentGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class BidReqComponentGroup42TestData extends MsgTest {

    private static final BidReqComponentGroup42TestData INSTANCE;

    static {
        INSTANCE = new BidReqComponentGroup42TestData();
    }

    public static BidReqComponentGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(BidReqComponentGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setListID("LIST_ID_6677");
        grp.setSide(Side.Buy);
        grp.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        grp.setNetGrossInd(NetGrossInd.Gross);
        grp.setSettlType(SettlType.BrokenDate.getValue());
        cal.set(2011, 3, 14, 12, 15, 33);
        grp.setSettlDate(cal.getTime());
        grp.setAccount("28736354");
    }

    public void populate2(BidReqComponentGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setListID("LIST_ID_8899");
        grp.setSide(Side.Sell);
        grp.setTradingSessionID(TradingSessionID.Evening.getValue());
        grp.setNetGrossInd(NetGrossInd.Net);
        grp.setSettlType(SettlType.FXSpotNextDay.getValue());
        cal.set(2011, 3, 14, 2, 30, 33);
        grp.setSettlDate(cal.getTime());
        grp.setAccount("253254534");
    }

    public void check(BidReqComponentGroup expected, BidReqComponentGroup actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getNetGrossInd(), actual.getNetGrossInd());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getAccount(), actual.getAccount());
    }
}
