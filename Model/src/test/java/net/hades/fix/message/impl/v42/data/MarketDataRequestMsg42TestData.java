/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg42TestData.java
 *
 * $Id: MarketDataRequestMsg42TestData.java,v 1.2 2009-11-10 11:10:40 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v42.MDReqGroup42TestData;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDUpdateType;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for MarketDataRequest42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataRequestMsg42TestData extends MsgTest {

    private static final MarketDataRequestMsg42TestData INSTANCE;

    static {
        INSTANCE = new MarketDataRequestMsg42TestData();
    }

    public static MarketDataRequestMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix42.MarketDataRequest msg) throws Exception {
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.MDReqID.FIELD, "X162773883");
        msg.setChar(quickfix.field.SubscriptionRequestType.FIELD, quickfix.field.SubscriptionRequestType.SNAPSHOT);
        msg.setInt(quickfix.field.MarketDepth.FIELD, 3);
        msg.setInt(quickfix.field.MDUpdateType.FIELD, quickfix.field.MDUpdateType.FULL_REFRESH);
        msg.setBoolean(quickfix.field.AggregatedBook.FIELD, false);
        // MDEntryTypes
        msg.setInt(quickfix.field.NoMDEntryTypes.FIELD, 2);
        quickfix.fix42.MarketDataRequest.NoMDEntryTypes qt1 = new quickfix.fix42.MarketDataRequest.NoMDEntryTypes();
        qt1.setChar(quickfix.field.MDEntryType.FIELD, quickfix.field.MDEntryType.AUCTION_CLEARING_PRICE);
        msg.addGroup(qt1);
        quickfix.fix42.MarketDataRequest.NoMDEntryTypes qt2 = new quickfix.fix42.MarketDataRequest.NoMDEntryTypes();
        qt2.setChar(quickfix.field.MDEntryType.FIELD, quickfix.field.MDEntryType.BID);
        msg.addGroup(qt2);
        // MDReqGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 2);
        quickfix.fix42.MarketDataRequest.NoRelatedSym qe1 = new quickfix.fix42.MarketDataRequest.NoRelatedSym();
        MDReqGroup42TestData.getInstance().populate1(qe1);
        msg.addGroup(qe1);
        quickfix.fix42.MarketDataRequest.NoRelatedSym qe2 = new quickfix.fix42.MarketDataRequest.NoRelatedSym();
        MDReqGroup42TestData.getInstance().populate2(qe2);
        msg.addGroup(qe2);
    }

    public void populate(MarketDataRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setMdReqID("AAA564567");
        msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
        msg.setMarketDepth(new Integer(3));
        msg.setMdUpdateType(MDUpdateType.FullRefresh);
        msg.setAggregatedBook(Boolean.FALSE);
        // MDEntryTypes
        msg.setNoMDEntryTypes(new Integer(2));
        msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.Bid.getValue());
        msg.getMdEntryTypeGroups()[1].setMdEntryType(MDEntryType.ClosingPrice.getValue());
        // MDReqGroup
        msg.setNoRelatedSym(new Integer(2));
        MDReqGroup42TestData.getInstance().populate1(msg.getMdReqGroups()[0]);
        MDReqGroup42TestData.getInstance().populate2(msg.getMdReqGroups()[1]);
    }

    public void check(quickfix.fix42.MarketDataRequest expected, MarketDataRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.MDReqID.FIELD), actual.getMdReqID());
        assertEquals(expected.getChar(quickfix.field.SubscriptionRequestType.FIELD), actual.getSubscriptionRequestType().getValue());
        assertEquals(expected.getInt(quickfix.field.MarketDepth.FIELD), actual.getMarketDepth().intValue());
        assertEquals(expected.getInt(quickfix.field.MDUpdateType.FIELD), actual.getMdUpdateType().getValue());
        assertEquals(expected.getBoolean(quickfix.field.AggregatedBook.FIELD), actual.getAggregatedBook().booleanValue());
        // MDEntryTypes
        assertEquals(expected.getInt(quickfix.field.NoMDEntryTypes.FIELD), actual.getNoMDEntryTypes().intValue());
        quickfix.fix42.MarketDataRequest.NoMDEntryTypes qt1 = new quickfix.fix42.MarketDataRequest.NoMDEntryTypes();
        expected.getGroup(1, qt1);
        assertEquals(qt1.getChar(quickfix.field.MDEntryType.FIELD), actual.getMdEntryTypeGroups()[0].getMdEntryType().charValue());
        quickfix.fix42.MarketDataRequest.NoMDEntryTypes qt2 = new quickfix.fix42.MarketDataRequest.NoMDEntryTypes();
        expected.getGroup(2, qt2);
        assertEquals(qt2.getChar(quickfix.field.MDEntryType.FIELD), actual.getMdEntryTypeGroups()[1].getMdEntryType().charValue());
        // MDReqGroup
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSym().intValue());
        quickfix.fix42.MarketDataRequest.NoRelatedSym qe1 = new quickfix.fix42.MarketDataRequest.NoRelatedSym();
        expected.getGroup(1, qe1);
        MDReqGroup42TestData.getInstance().check(qe1, actual.getMdReqGroups()[0]);
        quickfix.fix42.MarketDataRequest.NoRelatedSym qe2 = new quickfix.fix42.MarketDataRequest.NoRelatedSym();
        expected.getGroup(2, qe2);
        MDReqGroup42TestData.getInstance().check(qe2, actual.getMdReqGroups()[1]);
    }

    public void check(MarketDataRequestMsg expected, quickfix.fix42.MarketDataRequest actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getString(quickfix.field.MDReqID.FIELD));
        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getChar(quickfix.field.SubscriptionRequestType.FIELD));
        assertEquals(expected.getMarketDepth().intValue(), actual.getInt(quickfix.field.MarketDepth.FIELD));
        assertEquals(expected.getMdUpdateType().getValue(), actual.getInt(quickfix.field.MDUpdateType.FIELD));
        assertEquals(expected.getAggregatedBook().booleanValue(), actual.getBoolean(quickfix.field.AggregatedBook.FIELD));
        // MDEntryTypes
        assertEquals(expected.getNoMDEntryTypes().intValue(), actual.getInt(quickfix.field.NoMDEntryTypes.FIELD));
        quickfix.fix42.MarketDataRequest.NoMDEntryTypes qt1 = new quickfix.fix42.MarketDataRequest.NoMDEntryTypes();
        actual.getGroup(1, qt1);
        assertEquals(expected.getMdEntryTypeGroups()[0].getMdEntryType().charValue(), qt1.getChar(quickfix.field.MDEntryType.FIELD));
        quickfix.fix42.MarketDataRequest.NoMDEntryTypes qt2 = new quickfix.fix42.MarketDataRequest.NoMDEntryTypes();
        actual.getGroup(2, qt2);
        assertEquals(expected.getMdEntryTypeGroups()[1].getMdEntryType().charValue(), qt2.getChar(quickfix.field.MDEntryType.FIELD));
        // MDReqGroup
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        quickfix.fix42.MarketDataRequest.NoRelatedSym qe1 = new quickfix.fix42.MarketDataRequest.NoRelatedSym();
        actual.getGroup(1, qe1);
        MDReqGroup42TestData.getInstance().check(expected.getMdReqGroups()[0], qe1);
        quickfix.fix42.MarketDataRequest.NoRelatedSym qe2 = new quickfix.fix42.MarketDataRequest.NoRelatedSym();
        actual.getGroup(2, qe2);
        MDReqGroup42TestData.getInstance().check(expected.getMdReqGroups()[1], qe2);
     }

    public void check(MarketDataRequestMsg expected, MarketDataRequestMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
        assertEquals(expected.getMarketDepth().intValue(), actual.getMarketDepth().intValue());
        assertEquals(expected.getMdUpdateType().getValue(), actual.getMdUpdateType().getValue());
        assertEquals(expected.getAggregatedBook().booleanValue(), actual.getAggregatedBook().booleanValue());
        // MDEntryTypes
        assertEquals(expected.getNoMDEntryTypes().intValue(), actual.getNoMDEntryTypes().intValue());
        assertEquals(expected.getMdEntryTypeGroups()[0].getMdEntryType().charValue(), actual.getMdEntryTypeGroups()[0].getMdEntryType().charValue());
        assertEquals(expected.getMdEntryTypeGroups()[1].getMdEntryType().charValue(), actual.getMdEntryTypeGroups()[1].getMdEntryType().charValue());
        // MDReqGroup
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        MDReqGroup42TestData.getInstance().check(expected.getMdReqGroups()[0], actual.getMdReqGroups()[0]);
        MDReqGroup42TestData.getInstance().check(expected.getMdReqGroups()[1], actual.getMdReqGroups()[1]);
    }
}
