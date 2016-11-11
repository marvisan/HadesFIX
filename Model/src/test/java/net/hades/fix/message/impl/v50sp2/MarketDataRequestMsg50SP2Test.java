/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg50SP2Test.java
 *
 * $Id: MarketDataRequestMsg50SP2Test.java,v 1.3 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.MarketDataRequestMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test suite for FIX 5.0SP2 MarketDataRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MarketDataRequestMsg50SP2Test extends MsgTest  {

    public MarketDataRequestMsg50SP2Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        TestUtils.enableValidation();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setMdReqID("X162773883");
        msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
        msg.setMarketDepth(new Integer(3));
        msg.setNoMDEntryTypes(1);
        msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.AuctionClearingPrice.getValue());
        msg.setNoRelatedSym(1);
        msg.getMdReqGroups()[0].getInstrument().setSymbol("IBM");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getMdReqID(), dmsg.getMdReqID());
        assertEquals(msg.getSubscriptionRequestType().getValue(), dmsg.getSubscriptionRequestType().getValue());
        assertEquals(msg.getMarketDepth().intValue(), dmsg.getMarketDepth().intValue());
        assertEquals(msg.getNoMDEntryTypes().intValue(), dmsg.getNoMDEntryTypes().intValue());
        assertEquals(msg.getNoRelatedSym().intValue(), dmsg.getNoRelatedSym().intValue());
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b1_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        MarketDataRequestMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MarketDataRequestMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MarketDataRequestMsg msg = null;
        try {
            msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class MarketDataRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MarketDataRequestMsg msg = null;
        try {
            msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing MDReqID data.
     */
    @Test
    public void testEncodeMissingMDReqID() {
        System.out.println("-->testEncodeMissingMDReqID");
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
            msg.setMarketDepth(new Integer(3));
            msg.setNoMDEntryTypes(1);
            msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.AuctionClearingPrice.getValue());
            msg.setNoRelatedSym(1);
            msg.getMdReqGroups()[0].getInstrument().setSymbol("IBM");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [MDReqID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing SubscriptionRequestType data.
     */
    @Test
    public void testEncodeMissingSubscriptionRequestType() {
        System.out.println("-->testEncodeMissingSubscriptionRequestType");
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.setMdReqID("X162773883");
            msg.setMarketDepth(new Integer(3));
            msg.setNoMDEntryTypes(1);
            msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.AuctionClearingPrice.getValue());
            msg.setNoRelatedSym(1);
            msg.getMdReqGroups()[0].getInstrument().setSymbol("IBM");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [SubscriptionRequestType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing MarketDepth data.
     */
    @Test
    public void testEncodeMissingMarketDepth() {
        System.out.println("-->testEncodeMissingMarketDepth");
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.setMdReqID("X162773883");
            msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
            msg.setNoMDEntryTypes(1);
            msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.AuctionClearingPrice.getValue());
            msg.setNoRelatedSym(1);
            msg.getMdReqGroups()[0].getInstrument().setSymbol("IBM");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [MarketDepth] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing NoMDEntryTypes data.
     */
    @Test
    public void testEncodeMissingNoMDEntryTypes() {
        System.out.println("-->testEncodeMissingNoMDEntryTypes");
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.setMdReqID("X162773883");
            msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
            msg.setMarketDepth(new Integer(3));
            msg.setNoRelatedSym(1);
            msg.getMdReqGroups()[0].getInstrument().setSymbol("IBM");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoMDEntryTypes] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing NoRelatedSym data.
     */
    @Test
    public void testEncodeMissingNoRelatedSym() {
        System.out.println("-->testEncodeMissingNoRelatedSym");
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.setMdReqID("X162773883");
            msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
            msg.setMarketDepth(new Integer(3));
            msg.setNoMDEntryTypes(1);
            msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.AuctionClearingPrice.getValue());
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoRelatedSym] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [MDReqID] [SubscriptionRequestType] [MarketDepth] [NoMDEntryTypes] [NoRelatedSym] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MarketDataRequestMsg] message version [5.0SP2].", ex.getMessage());
    }
}
