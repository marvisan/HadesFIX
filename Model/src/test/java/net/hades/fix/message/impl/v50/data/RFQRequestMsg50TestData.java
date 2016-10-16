/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg50TestData.java
 *
 * $Id: RFQRequestMsg50TestData.java,v 1.2 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.group.impl.v50.RFQRequestGroup50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for RFQRequestMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class RFQRequestMsg50TestData extends MsgTest {

    private static final RFQRequestMsg50TestData INSTANCE;

    static {
        INSTANCE = new RFQRequestMsg50TestData();
    }

    public static RFQRequestMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RFQRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setRfqReqID("SSS87766555");
        // RFQRequestGroup
        msg.setNoRelatedSyms(new Integer(2));
        RFQRequestGroup50TestData.getInstance().populate1(msg.getRFQRequestGroups()[0]);
        RFQRequestGroup50TestData.getInstance().populate2(msg.getRFQRequestGroups()[1]);

        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(RFQRequestMsg expected, RFQRequestMsg actual) throws Exception {
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getNoRelatedSyms().intValue());
        // RFQRequestGroup check
        RFQRequestGroup50TestData.getInstance().check(expected.getRFQRequestGroups()[0], actual.getRFQRequestGroups()[0]);
        RFQRequestGroup50TestData.getInstance().check(expected.getRFQRequestGroups()[1], actual.getRFQRequestGroups()[1]);

        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
    }
}
