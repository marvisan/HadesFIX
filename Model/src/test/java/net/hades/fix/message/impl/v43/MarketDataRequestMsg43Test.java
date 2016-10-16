/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg43Test.java
 *
 * $Id: MarketDataRequestMsg43Test.java,v 1.5 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v43.data.MarketDataRequestMsg43TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test suite for FIX 4.3 MarketDataRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MarketDataRequestMsg43Test extends MsgTest  {

    private DataDictionary dictionary;

    public MarketDataRequestMsg43Test() {
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
        dictionary = getQF43DataDictionary();
        MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setMdReqID("X162773883");
        msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
        msg.setMarketDepth(new Integer(3));
        msg.setNoMDEntryTypes(1);
        msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.AuctionClearingPrice.getValue());
        msg.setNoRelatedSym(1);
        msg.getMdReqGroups()[0].getInstrument().setSymbol("IBM");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);

        assertEquals(msg.getMdReqID(), qfMsg.getString(quickfix.field.MDReqID.FIELD));
        assertEquals(msg.getSubscriptionRequestType().getValue(), qfMsg.getChar(quickfix.field.SubscriptionRequestType.FIELD));
        assertEquals(msg.getMarketDepth().intValue(), qfMsg.getInt(quickfix.field.MarketDepth.FIELD));
        assertEquals(msg.getNoMDEntryTypes().intValue(), qfMsg.getInt(quickfix.field.NoMDEntryTypes.FIELD));
        assertEquals(msg.getNoRelatedSym().intValue(), qfMsg.getInt(quickfix.field.NoRelatedSym.FIELD));
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF43DataDictionary();
        MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
        MarketDataRequestMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix43.MarketDataRequest qfMsg = new quickfix.fix43.MarketDataRequest();
        qfMsg.fromString(encoded, dictionary, true);
        MarketDataRequestMsg43TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class MarketDataRequestMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.MarketDataRequest msg = new quickfix.fix43.MarketDataRequest();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.MDReqID.FIELD, "X162773883");
        msg.setChar(quickfix.field.SubscriptionRequestType.FIELD, quickfix.field.SubscriptionRequestType.SNAPSHOT);
        msg.setInt(quickfix.field.MarketDepth.FIELD, 3);
        msg.setInt(quickfix.field.NoMDEntryTypes.FIELD, 1);
        quickfix.fix43.MarketDataRequest.NoMDEntryTypes qt1 = new quickfix.fix43.MarketDataRequest.NoMDEntryTypes();
        qt1.setChar(quickfix.field.MDEntryType.FIELD, quickfix.field.MDEntryType.AUCTION_CLEARING_PRICE);
        msg.addGroup(qt1);
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 1);
        quickfix.fix43.MarketDataRequest.NoRelatedSym qe1 = new quickfix.fix43.MarketDataRequest.NoRelatedSym();
        qe1.setString(quickfix.field.Symbol.FIELD, "ORCL");
        msg.addGroup(qe1);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(dmsg.getMdReqID(), msg.getString(quickfix.field.MDReqID.FIELD));
        assertEquals(dmsg.getSubscriptionRequestType().getValue(), msg.getChar(quickfix.field.SubscriptionRequestType.FIELD));
        assertEquals(dmsg.getMarketDepth().intValue(), msg.getInt(quickfix.field.MarketDepth.FIELD));
        assertEquals(dmsg.getNoMDEntryTypes().intValue(), msg.getInt(quickfix.field.NoMDEntryTypes.FIELD));
        assertEquals(dmsg.getNoRelatedSym().intValue(), msg.getInt(quickfix.field.NoRelatedSym.FIELD));
    }

    /**
     * Test of decode method, of class MarketDataRequestMsg for all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.MarketDataRequest msg = new quickfix.fix43.MarketDataRequest();
        MarketDataRequestMsg43TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MarketDataRequestMsg43TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
        MarketDataRequestMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MarketDataRequestMsg43TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b4_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
            MarketDataRequestMsg43TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            MarketDataRequestMsg43TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MarketDataRequestMsg msg = null;
        try {
            msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [MDReqID] [SubscriptionRequestType] [MarketDepth] [NoMDEntryTypes] [NoRelatedSym] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class MarketDataRequestMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF43DataDictionary();
            quickfix.fix43.MarketDataRequest msg = new quickfix.fix43.MarketDataRequest();
            TestUtils.populateQuickFIX43HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [MDReqID] [SubscriptionRequestType] [MarketDepth] [NoMDEntryTypes] [NoRelatedSym] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MarketDataRequestMsg] message version [4.3].", ex.getMessage());
    }
}
