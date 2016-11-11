/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg50SP1TestData.java
 *
 * $Id: RFQRequestMsg50SP1TestData.java,v 1.1 2009-07-06 03:19:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.group.impl.v50sp1.RFQRequestGroup50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for RFQRequestMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class RFQRequestMsg50SP1TestData extends MsgTest {

    private static final RFQRequestMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new RFQRequestMsg50SP1TestData();
    }

    public static RFQRequestMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RFQRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.setRfqReqID("SSS87766555");
        // Parties
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        // RFQRequestGroup
        msg.setNoRelatedSyms(new Integer(2));
        RFQRequestGroup50SP1TestData.getInstance().populate1(msg.getRFQRequestGroups()[0]);
        RFQRequestGroup50SP1TestData.getInstance().populate2(msg.getRFQRequestGroups()[1]);

        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setPrivateQuote(Boolean.TRUE);
    }

    public void check(RFQRequestMsg expected, RFQRequestMsg actual) throws Exception {
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        // Parties check
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getNoRelatedSyms().intValue());
        // RFQRequestGroup check
        RFQRequestGroup50SP1TestData.getInstance().check(expected.getRFQRequestGroups()[0], actual.getRFQRequestGroups()[0]);
        RFQRequestGroup50SP1TestData.getInstance().check(expected.getRFQRequestGroups()[1], actual.getRFQRequestGroups()[1]);

        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
        assertEquals(expected.getPrivateQuote(), actual.getPrivateQuote());
    }
}
