/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg50Test.java
 *
 * $Id: QuoteStatusReportMsg50Test.java,v 1.4 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.impl.v50.data.QuoteStatusReportMsg50TestData;
import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0 QuoteStatusReportMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteStatusReportMsg50Test extends MsgTest  {

    private DataDictionary dictionary;

    public QuoteStatusReportMsg50Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        setSessionApplVerID(ApplVerID.FIX50);
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
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setQuoteID("X162773883");
        msg.getInstrument().setSymbol("SUN");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getQuoteID(), dmsg.getQuoteID());
        assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        QuoteStatusReportMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteStatusReportMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteStatusReportRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteStatusReportMsg msg = null;
        try {
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getTargetParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class QuoteMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteStatusReportMsg msg = null;
        try {
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setTargetParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearTargetParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteStatusReportMsg with missing QuoteID data.
     */
    @Test
    public void testEncodeMissingQuoteID() {
        System.out.println("-->testEncodeMissingQuoteID");
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getInstrument().setSymbol("MOT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF43DataDictionary();
            quickfix.fix50.QuoteStatusReport msg = new quickfix.fix50.QuoteStatusReport();
            TestUtils.populateQuickFIX50HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Instrument] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteStatusReportMsg] message version [5.0].", ex.getMessage());
    }
}
