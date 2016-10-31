/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidReqComponentGroup44TestData.java
 *
 * $Id: BidReqComponentGroup44TestData.java,v 1.1 2011-04-14 11:44:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.BidReqComponentGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.NetGrossInd;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for BidReqComponentGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class BidReqComponentGroup44TestData extends MsgTest {

    private static final BidReqComponentGroup44TestData INSTANCE;

    static {
        INSTANCE = new BidReqComponentGroup44TestData();
    }

    public static BidReqComponentGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(BidReqComponentGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setListID("LIST_ID_6677");
        grp.setSide(Side.Buy);
        grp.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        grp.setNetGrossInd(NetGrossInd.Gross);
        grp.setSettlType(SettlType.NextDay.getValue());
        cal.set(2011, 3, 14, 12, 15, 33);
        grp.setSettlDate(cal.getTime());
        grp.setAccount("28736354");
        grp.setAcctIDSource(AcctIDSource.SID);
    }

    public void populate2(BidReqComponentGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setListID("LIST_ID_8899");
        grp.setSide(Side.Sell);
        grp.setTradingSessionID(TradingSessionID.Evening.getValue());
        grp.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        grp.setNetGrossInd(NetGrossInd.Net);
        grp.setSettlType(SettlType.Cash.getValue());
        cal.set(2011, 3, 14, 2, 30, 33);
        grp.setSettlDate(cal.getTime());
        grp.setAccount("253254534");
        grp.setAcctIDSource(AcctIDSource.BIC);
    }

    public void check(BidReqComponentGroup expected, BidReqComponentGroup actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getNetGrossInd(), actual.getNetGrossInd());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
    }
}
