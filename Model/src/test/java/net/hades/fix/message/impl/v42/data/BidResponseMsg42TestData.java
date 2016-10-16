/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidResponseMsg42TestData.java
 *
 * $Id: BidResponseMsg42TestData.java,v 1.1 2011-04-14 11:44:48 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.BidResponseMsg;
import net.hades.fix.message.group.impl.v42.BidRespComponentGroup42TestData;
import net.hades.fix.message.MsgTest;

/**
 * Test utility for BidResponseMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class BidResponseMsg42TestData extends MsgTest {

    private static final BidResponseMsg42TestData INSTANCE;

    static {
        INSTANCE = new BidResponseMsg42TestData();
    }

    public static BidResponseMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate( BidResponseMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setBidID("BIDID5555722");
        msg.setClientBidID("CLIBID7364844");

        msg.setNoBidComponents(2);
        BidRespComponentGroup42TestData.getInstance().populate1(msg.getBidComponentGroups()[0]);
        BidRespComponentGroup42TestData.getInstance().populate2(msg.getBidComponentGroups()[1]);
    }

    public void check( BidResponseMsg expected,  BidResponseMsg actual) throws Exception {
        assertEquals(expected.getBidID(), actual.getBidID());
        assertEquals(expected.getClientBidID(), actual.getClientBidID());

        assertEquals(expected.getNoBidComponents(), actual.getNoBidComponents());
        BidRespComponentGroup42TestData.getInstance().check(expected.getBidComponentGroups()[0], actual.getBidComponentGroups()[0]);
        BidRespComponentGroup42TestData.getInstance().check(expected.getBidComponentGroups()[1], actual.getBidComponentGroups()[1]);
    }
}
