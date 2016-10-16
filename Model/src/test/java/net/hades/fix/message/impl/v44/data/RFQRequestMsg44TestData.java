/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg44TestData.java
 *
 * $Id: RFQRequestMsg44TestData.java,v 1.1 2009-07-06 03:19:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.group.impl.v44.RFQRequestGroup44TestData;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for RFQRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class RFQRequestMsg44TestData extends MsgTest {

    private static final RFQRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new RFQRequestMsg44TestData();
    }

    public static RFQRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RFQRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setRfqReqID("SSS87766555");
        // RFQRequestGroup
        msg.setNoRelatedSyms(new Integer(2));
        RFQRequestGroup44TestData.getInstance().populate1(msg.getRFQRequestGroups()[0]);
        RFQRequestGroup44TestData.getInstance().populate2(msg.getRFQRequestGroups()[1]);

        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(RFQRequestMsg expected, RFQRequestMsg actual) throws Exception {
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getNoRelatedSyms().intValue());
        // RFQRequestGroup check
        RFQRequestGroup44TestData.getInstance().check(expected.getRFQRequestGroups()[0], actual.getRFQRequestGroups()[0]);
        RFQRequestGroup44TestData.getInstance().check(expected.getRFQRequestGroups()[1], actual.getRFQRequestGroups()[1]);

        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
    }
}
