/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg43TestData.java
 *
 * $Id: RFQRequestMsg43TestData.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.group.impl.v43.RFQRequestGroup43TestData;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for RFQRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class RFQRequestMsg43TestData extends MsgTest {

    private static final RFQRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new RFQRequestMsg43TestData();
    }

    public static RFQRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.RFQRequest msg) throws Exception {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.RFQReqID.FIELD, "TTT5676555");
        // RFQRequestGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 2);
        quickfix.fix43.RFQRequest.NoRelatedSym grprelsym1 = new quickfix.fix43.RFQRequest.NoRelatedSym();
        RFQRequestGroup43TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);
        quickfix.fix43.RFQRequest.NoRelatedSym grprelsym2 = new quickfix.fix43.RFQRequest.NoRelatedSym();
        RFQRequestGroup43TestData.getInstance().populate2(grprelsym2);
        msg.addGroup(grprelsym2);

        msg.setChar(quickfix.field.SubscriptionRequestType.FIELD, quickfix.field.SubscriptionRequestType.SNAPSHOT);
    }

    public void populate(RFQRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setRfqReqID("SSS87766555");
        // RFQRequestGroup
        msg.setNoRelatedSyms(new Integer(2));
        RFQRequestGroup43TestData.getInstance().populate1(msg.getRFQRequestGroups()[0]);
        RFQRequestGroup43TestData.getInstance().populate2(msg.getRFQRequestGroups()[1]);

        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(quickfix.fix43.RFQRequest expected, RFQRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.RFQReqID.FIELD), actual.getRfqReqID());
        // RFQRequestGroup check
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSyms().intValue());
        quickfix.fix43.RFQRequest.NoRelatedSym grprelsym1 = new quickfix.fix43.RFQRequest.NoRelatedSym();
        expected.getGroup(1, grprelsym1);
        RFQRequestGroup43TestData.getInstance().check(grprelsym1, actual.getRFQRequestGroups()[0]);
        quickfix.fix43.RFQRequest.NoRelatedSym grprelsym2 = new quickfix.fix43.RFQRequest.NoRelatedSym();
        expected.getGroup(2, grprelsym2);
        RFQRequestGroup43TestData.getInstance().check(grprelsym2, actual.getRFQRequestGroups()[1]);

        assertEquals(expected.getChar(quickfix.field.SubscriptionRequestType.FIELD), actual.getSubscriptionRequestType().getValue());
    }

    public void check(RFQRequestMsg expected, quickfix.fix43.RFQRequest actual) throws Exception {
        assertEquals(expected.getRfqReqID(), actual.getString(quickfix.field.RFQReqID.FIELD));
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        // RFQRequestGroup check
        quickfix.fix43.RFQRequest.NoRelatedSym grprelsym1 = new quickfix.fix43.RFQRequest.NoRelatedSym();
        actual.getGroup(1, grprelsym1);
        RFQRequestGroup43TestData.getInstance().check(expected.getRFQRequestGroups()[0], grprelsym1);
        quickfix.fix43.RFQRequest.NoRelatedSym grprelsym2 = new quickfix.fix43.RFQRequest.NoRelatedSym();
        actual.getGroup(2, grprelsym2);
        RFQRequestGroup43TestData.getInstance().check(expected.getRFQRequestGroups()[1], grprelsym2);

        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getChar(quickfix.field.SubscriptionRequestType.FIELD));
    }

    public void check(RFQRequestMsg expected, RFQRequestMsg actual) throws Exception {
        assertEquals(expected.getRfqReqID(), actual.getRfqReqID());
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getNoRelatedSyms().intValue());
        // RFQRequestGroup check
        RFQRequestGroup43TestData.getInstance().check(expected.getRFQRequestGroups()[0], actual.getRFQRequestGroups()[0]);
        RFQRequestGroup43TestData.getInstance().check(expected.getRFQRequestGroups()[1], actual.getRFQRequestGroups()[1]);

        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
    }
}
