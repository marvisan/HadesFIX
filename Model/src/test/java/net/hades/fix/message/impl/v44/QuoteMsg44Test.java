/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsg44Test.java
 *
 * $Id: QuoteMsg44Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

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
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.QuoteMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.4 QuoteMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteMsg44Test extends MsgTest  {

    private DataDictionary dictionary;

    public QuoteMsg44Test() {
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
     * Test of encode method, of class QuoteMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF44DataDictionary();
        QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteID("X162773883");
        msg.getInstrument().setSymbol("SUN");
        msg.setBidPx(new Double(123.44));

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteID(), qfMsg.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(msg.getInstrument().getSymbol(), qfMsg.getString(quickfix.field.Symbol.FIELD));
        assertEquals(msg.getBidPx().doubleValue(), qfMsg.getDouble(quickfix.field.BidPx.FIELD), 0.001);
    }

    /**
     * Test of encode method, of class QuoteMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF44DataDictionary();
        QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
        QuoteMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix44.Quote qfMsg = new quickfix.fix44.Quote();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteMsg44TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.Quote msg = new quickfix.fix44.Quote();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setString(quickfix.field.Symbol.FIELD, "SUN");
        msg.setDouble(quickfix.field.BidPx.FIELD, 234.55);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getString(quickfix.field.QuoteID.FIELD), dmsg.getQuoteID());
        assertEquals(msg.getString(quickfix.field.Symbol.FIELD), dmsg.getInstrument().getSymbol());
        assertEquals(msg.getDouble(quickfix.field.BidPx.FIELD), dmsg.getBidPx().doubleValue(), 0.001);
    }

    /**
     * Test of decode method, of class QuoteMsg for all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.Quote msg = new quickfix.fix44.Quote();
        QuoteMsg44TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteMsg44TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void a3_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            QuoteMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteMsg44TestData.getInstance().check(msg, dmsg);
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
        QuoteMsg msg = null;
        try {
            msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class QuoteMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteMsg msg = null;
        try {
            msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteMsg with missing QuoteID data.
     */
    @Test
    public void testEncodeMissingQuoteID() {
        System.out.println("-->testEncodeMissingQuoteID");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.getInstrument().setSymbol("MOT");
            msg.setBidPx(new Double(123.44));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.setBidPx(new Double(123.44));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Symbol] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteMsg with missing BidPx data.
     */
    @Test
    public void testEncodeMissingBidPxOrOfferPx() {
        System.out.println("-->testEncodeMissingBidPxOrOfferPx");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.getInstrument().setSymbol("MOT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [BidPx/OfferPx] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Symbol] [BidPx/OfferPx] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF44DataDictionary();
            quickfix.fix44.Quote msg = new quickfix.fix44.Quote();
            TestUtils.populateQuickFIX44HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Symbol] [BidPx/OfferPx] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteMsg] message version [4.4].", ex.getMessage());
    }}
