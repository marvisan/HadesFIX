/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg50TestData.java
 *
 * $Id: MarketDataRequestMsg50TestData.java,v 1.2 2009-12-03 11:19:12 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v50.MDReqGroup50TestData;
import net.hades.fix.message.type.ApplQueueAction;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MDQuoteType;
import net.hades.fix.message.type.MDUpdateType;
import net.hades.fix.message.type.OpenCloseSettlFlag;
import net.hades.fix.message.type.Scope;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for MarketDataRequest50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataRequestMsg50TestData extends MsgTest {

    private static final MarketDataRequestMsg50TestData INSTANCE;

    static {
        INSTANCE = new MarketDataRequestMsg50TestData();
    }

    public static MarketDataRequestMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setMdReqID("AAA564567");
        msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
        msg.setMarketDepth(new Integer(3));
        msg.setMdUpdateType(MDUpdateType.FullRefresh);
        msg.setAggregatedBook(Boolean.FALSE);
        msg.setOpenCloseSettlFlag(new Character(OpenCloseSettlFlag.DailyOpen.getValue()).toString());
        msg.setScope(new Character(Scope.Global.getValue()).toString());
        msg.setMdImplicitDelete(Boolean.TRUE);
        // MDEntryTypes
        msg.setNoMDEntryTypes(new Integer(2));
        msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.Bid.getValue());
        msg.getMdEntryTypeGroups()[1].setMdEntryType(MDEntryType.ClosingPrice.getValue());
        // MDReqGroup
        msg.setNoRelatedSym(new Integer(2));
        MDReqGroup50TestData.getInstance().populate1(msg.getMdReqGroups()[0]);
        MDReqGroup50TestData.getInstance().populate2(msg.getMdReqGroups()[1]);

        msg.setApplQueueAction(ApplQueueAction.QueueFlushed);
        msg.setApplQueueMax(new Integer(4));
        msg.setMdQuoteType(MDQuoteType.Counter);
    }

    public void check(MarketDataRequestMsg expected, MarketDataRequestMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        assertEquals(expected.getSubscriptionRequestType().getValue(), actual.getSubscriptionRequestType().getValue());
        assertEquals(expected.getMarketDepth().intValue(), actual.getMarketDepth().intValue());
        assertEquals(expected.getMdUpdateType().getValue(), actual.getMdUpdateType().getValue());
        assertEquals(expected.getAggregatedBook().booleanValue(), actual.getAggregatedBook().booleanValue());
        assertEquals(expected.getOpenCloseSettlFlag(), actual.getOpenCloseSettlFlag());
        assertEquals(expected.getScope(), actual.getScope());
        assertEquals(expected.getMdImplicitDelete(), actual.getMdImplicitDelete());
        // MDEntryTypes
        assertEquals(expected.getNoMDEntryTypes().intValue(), actual.getNoMDEntryTypes().intValue());
        assertEquals(expected.getMdEntryTypeGroups()[0].getMdEntryType().charValue(), actual.getMdEntryTypeGroups()[0].getMdEntryType().charValue());
        assertEquals(expected.getMdEntryTypeGroups()[1].getMdEntryType().charValue(), actual.getMdEntryTypeGroups()[1].getMdEntryType().charValue());
        // MDReqGroup
        assertEquals(expected.getNoRelatedSym().intValue(), actual.getNoRelatedSym().intValue());
        MDReqGroup50TestData.getInstance().check(expected.getMdReqGroups()[0], actual.getMdReqGroups()[0]);
        MDReqGroup50TestData.getInstance().check(expected.getMdReqGroups()[1], actual.getMdReqGroups()[1]);

        assertEquals(expected.getApplQueueAction().getValue(), actual.getApplQueueAction().getValue());
        assertEquals(expected.getApplQueueMax().intValue(), actual.getApplQueueMax().intValue());
        assertEquals(expected.getMdQuoteType(), actual.getMdQuoteType());
    }
}
