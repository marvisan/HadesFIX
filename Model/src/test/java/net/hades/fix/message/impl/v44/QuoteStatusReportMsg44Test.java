/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg44Test.java
 *
 * $Id: QuoteStatusReportMsg44Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
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
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.QuoteStatusReportMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.4 QuoteStatusReportMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteStatusReportMsg44Test extends MsgTest  {

    private DataDictionary dictionary;

    public QuoteStatusReportMsg44Test() {
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
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteID("X162773883");
        msg.getInstrument().setSymbol("SUN");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteID(), qfMsg.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(msg.getInstrument().getSymbol(), qfMsg.getString(quickfix.field.Symbol.FIELD));
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF44DataDictionary();
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
        QuoteStatusReportMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix44.QuoteStatusReport qfMsg = new quickfix.fix44.QuoteStatusReport();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteStatusReportMsg44TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteStatusReportMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.QuoteStatusReport msg = new quickfix.fix44.QuoteStatusReport();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setString(quickfix.field.Symbol.FIELD, "SUN");
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getString(quickfix.field.QuoteID.FIELD), dmsg.getQuoteID());
        assertEquals(msg.getString(quickfix.field.Symbol.FIELD), dmsg.getInstrument().getSymbol());
    }

    /**
     * Test of decode method, of class QuoteStatusReportMsg for all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.QuoteStatusReport msg = new quickfix.fix44.QuoteStatusReport();
        QuoteStatusReportMsg44TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteStatusReportMsg44TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
        QuoteStatusReportMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteStatusReportMsg44TestData.getInstance().check(msg, dmsg);
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
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
            QuoteStatusReportMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteStatusReportMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class QuoteStatusReportRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteStatusReportMsg msg = null;
        try {
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
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
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
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
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
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
     * Test of encode method, of class QuoteStatusReportMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            quickfix.fix44.QuoteStatusReport msg = new quickfix.fix44.QuoteStatusReport();
            TestUtils.populateQuickFIX44HeaderAll(msg);
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
        assertEquals("This tag is not supported in [QuoteStatusReportMsg] message version [4.4].", ex.getMessage());
    }}
