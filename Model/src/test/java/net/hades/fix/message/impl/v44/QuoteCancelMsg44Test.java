/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg44Test.java
 *
 * $Id: QuoteCancelMsg44Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
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
import net.hades.fix.message.QuoteCancelMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.QuoteCancelMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteCancelType;

/**
 * Test suite for FIX 4.4 QuoteCancelMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteCancelMsg44Test extends MsgTest  {

    private DataDictionary dictionary;

    public QuoteCancelMsg44Test() {
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
     * Test of encode method, of class QuoteCancelMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF44DataDictionary();
        QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteID("X162773883");
        msg.setQuoteCancelType(QuoteCancelType.CancelAllQuotes.getValue());

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteID(), qfMsg.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(msg.getQuoteCancelType().intValue(), qfMsg.getInt(quickfix.field.QuoteCancelType.FIELD));
    }

    /**
     * Test of encode method, of class QuoteCancelMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF44DataDictionary();
        QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
        QuoteCancelMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix44.QuoteCancel qfMsg = new quickfix.fix44.QuoteCancel();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteCancelMsg44TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteCancelMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.QuoteCancel msg = new quickfix.fix44.QuoteCancel();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteCancelType.FIELD, QuoteCancelType.CancelByQuoteType.getValue());
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteCancelMsg dmsg = (QuoteCancelMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getString(quickfix.field.QuoteID.FIELD), dmsg.getQuoteID());
        assertEquals(msg.getInt(quickfix.field.QuoteCancelType.FIELD), dmsg.getQuoteCancelType().intValue());
    }

    /**
     * Test of decode method, of class QuoteCancelMsg for all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.QuoteCancel msg = new quickfix.fix44.QuoteCancel();
        QuoteCancelMsg44TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteCancelMsg dmsg = (QuoteCancelMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteCancelMsg44TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode/decode method, of class QuoteCancelMsg for all fields.
     * @throws Exception
     */
    @Test
    public void b2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
        QuoteCancelMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteCancelMsg dmsg = (QuoteCancelMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteCancelMsg44TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
            QuoteCancelMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteCancelMsg dmsg = (QuoteCancelMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteCancelMsg44TestData.getInstance().check(msg, dmsg);
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
        QuoteCancelMsg msg = null;
        try {
            msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
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
     * Test of encode setter method, of class QuoteCancelMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteCancelMsg msg = null;
        try {
            msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
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
     * Test of encode method, of class QuoteCancelMsg with missing QuoteID data.
     */
    @Test
    public void testEncodeMissingQuoteID() {
        System.out.println("-->testEncodeMissingQuoteID");
        try {
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setQuoteCancelType(QuoteCancelType.CancelAllQuotes.getValue());
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteCancelMsg with missing QuoteCancelType data.
     */
    @Test
    public void testEncodeMissingQuoteCancelType() {
        System.out.println("-->testEncodeMissingQuoteCancelType");
        try {
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteCancelType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteCancelMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [QuoteCancelType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class QuoteCancelMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF43DataDictionary();
            quickfix.fix44.QuoteCancel msg = new quickfix.fix44.QuoteCancel();
            TestUtils.populateQuickFIX44HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [QuoteCancelType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteCancelMsg] message version [4.4].", ex.getMessage());
    }
}
