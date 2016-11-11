/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteResponseMsg50Test.java
 *
 * $Id: QuoteResponseMsg50Test.java,v 1.5 2011-01-15 02:10:11 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteResponseMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.QuoteResponseMsg50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteRespType;

/**
 * Test suite for FIX 5.0 QuoteResponseMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteResponseMsg50Test extends MsgTest  {

    public QuoteResponseMsg50Test() {
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
     * Test of encode method, of class QuoteResponseMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setQuoteRespID("X162773883");
        msg.setQuoteRespType(QuoteRespType.Counter);
        msg.getInstrument().setSymbol("SUN");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix50.Message qfMsg = new quickfix.fix50.Message();
        qfMsg.fromString(encoded, getQFSessDataDictionary(), getQF50DataDictionary(), true);
        assertEquals(msg.getQuoteRespID(), qfMsg.getString(quickfix.field.QuoteRespID.FIELD));
        assertEquals(msg.getQuoteRespType().getValue(), qfMsg.getInt(quickfix.field.QuoteRespType.FIELD));
        assertEquals(msg.getInstrument().getSymbol(), qfMsg.getString(quickfix.field.Symbol.FIELD));
    }

    /**
     * Test of encode method, of class QuoteResponseMsg all fields.
     * @throws Exception
     */
//    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        QuoteResponseMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix50.QuoteResponse qfMsg = new quickfix.fix50.QuoteResponse();
        qfMsg.fromString(encoded, getQFSessDataDictionary(), getQF50DataDictionary(), true);
        QuoteResponseMsg50TestData.getInstance().check(qfMsg, msg);
    }

    /**
     * Test of decode method, of class QuoteResponseMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        quickfix.fix50.QuoteResponse msg = new quickfix.fix50.QuoteResponse();
        TestUtils.populateQuickFIX50HeaderAll(msg);
        msg.setString(quickfix.field.QuoteRespID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteRespType.FIELD, quickfix.field.QuoteRespType.COUNTER);
        msg.setString(quickfix.field.Symbol.FIELD, "SUN");
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteResponseMsg dmsg = (QuoteResponseMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getString(quickfix.field.QuoteRespID.FIELD), dmsg.getQuoteRespID());
        assertEquals(msg.getInt(quickfix.field.QuoteRespType.FIELD), dmsg.getQuoteRespType().getValue());
        assertEquals(msg.getString(quickfix.field.Symbol.FIELD), dmsg.getInstrument().getSymbol());
    }

    /**
     * Test of decode method, of class QuoteResponseMsg for all fields.
     * @throws Exception
     */
//    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        quickfix.fix50.QuoteResponse msg = new quickfix.fix50.QuoteResponse();
        TestUtils.populateQuickFIX50HeaderAll(msg);
        QuoteResponseMsg50TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteResponseMsg dmsg = (QuoteResponseMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteResponseMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of class QuoteResponseMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        QuoteResponseMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteResponseMsg dmsg = (QuoteResponseMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteResponseMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteResponseMsg msg = null;
        try {
            msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteResponseMsg msg = null;
        try {
            msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteResponseMsg with missing QuoteRespID data.
     */
    @Test
    public void testEncodeMissingQuoteRespID() {
        System.out.println("-->testEncodeMissingQuoteRespID");
        try {
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getInstrument().setSymbol("MOT");
            msg.setQuoteRespType(QuoteRespType.Counter);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteRespID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteResponseMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setQuoteRespID("43423534534");
            msg.setQuoteRespType(QuoteRespType.Counter);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteResponseMsg with missing QuoteRespType data.
     */
    @Test
    public void testEncodeMissingQuoteRespType() {
        System.out.println("-->testEncodeMissingQuoteRespType");
        try {
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setQuoteRespID("43423534534");
            msg.getInstrument().setSymbol("MOT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteRespType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteResponseMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteRespID] [QuoteRespType] [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
           quickfix.fix50.QuoteResponse msg = new quickfix.fix50.QuoteResponse();
            TestUtils.populateQuickFIX50HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteRespID] [QuoteRespType] [Instrument] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteResponseMsg] message version [5.0].", ex.getMessage());
    }}
