/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsg50SP1Test.java
 *
 * $Id: QuoteMsg50SP1Test.java,v 1.5 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.QuoteMsg50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP1 QuoteMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteMsg50SP1Test extends MsgTest  {

    public QuoteMsg50SP1Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        setSessionApplVerID(ApplVerID.FIX50SP1);
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
        QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.setQuoteID("X162773883");
        msg.getInstrument().setSymbol("SUN");
        msg.setBidPx(new Double(123.44));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getQuoteID(), dmsg.getQuoteID());
        assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
        assertEquals(msg.getBidPx().doubleValue(), dmsg.getBidPx().doubleValue(), 0.001);
    }

    /**
     * Test of encode method, of class QuoteMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        QuoteMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteMsg msg = null;
        try {
            msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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
            quickfix.fix50.Quote msg = new quickfix.fix50.Quote();
            TestUtils.populateQuickFIX50HeaderAll(msg);
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
        assertEquals("This tag is not supported in [QuoteMsg] message version [5.0SP1].", ex.getMessage());
    }}
