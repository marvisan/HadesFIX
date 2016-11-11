/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidResponseMsg43TestData.java
 *
 * $Id: BidResponseMsg43TestData.java,v 1.1 2011-04-14 11:44:49 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.BidResponseMsg;
import net.hades.fix.message.group.impl.v43.BidRespComponentGroup43TestData;
import net.hades.fix.message.MsgTest;

/**
 * Test utility for BidResponseMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class BidResponseMsg43TestData extends MsgTest {

    private static final BidResponseMsg43TestData INSTANCE;

    static {
        INSTANCE = new BidResponseMsg43TestData();
    }

    public static BidResponseMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate( BidResponseMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setBidID("BIDID5555722");
        msg.setClientBidID("CLIBID7364844");

        msg.setNoBidComponents(2);
        BidRespComponentGroup43TestData.getInstance().populate1(msg.getBidComponentGroups()[0]);
        BidRespComponentGroup43TestData.getInstance().populate2(msg.getBidComponentGroups()[1]);
    }

    public void check( BidResponseMsg expected,  BidResponseMsg actual) throws Exception {
        assertEquals(expected.getBidID(), actual.getBidID());
        assertEquals(expected.getClientBidID(), actual.getClientBidID());

        assertEquals(expected.getNoBidComponents(), actual.getNoBidComponents());
        BidRespComponentGroup43TestData.getInstance().check(expected.getBidComponentGroups()[0], actual.getBidComponentGroups()[0]);
        BidRespComponentGroup43TestData.getInstance().check(expected.getBidComponentGroups()[1], actual.getBidComponentGroups()[1]);
    }
}
