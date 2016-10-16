/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg42Test.java
 *
 * $Id: QuoteCancelMsg42Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

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
import net.hades.fix.message.impl.v42.data.QuoteCancelMsg42TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteCancelType;

/**
 * Test suite for FIX 4.2 QuoteCancelMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteCancelMsg42Test extends MsgTest  {

    private DataDictionary dictionary;

    public QuoteCancelMsg42Test() {
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
        dictionary = getQF42DataDictionary();
        QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setQuoteID("X162773883");
        msg.setQuoteCancelType(QuoteCancelType.CancelAllQuotes.getValue());
        msg.setNoQuoteEntries(1);
        msg.getQuoteCancelEntries()[0].setSymbol("TEST");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
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
        dictionary = getQF42DataDictionary();
        QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
        QuoteCancelMsg42TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix42.QuoteCancel qfMsg = new quickfix.fix42.QuoteCancel();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteCancelMsg42TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteCancelMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF42DataDictionary();
        quickfix.fix42.QuoteCancel msg = new quickfix.fix42.QuoteCancel();
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteCancelType.FIELD, QuoteCancelType.CancelByQuoteType.getValue());
        msg.setInt(quickfix.field.NoQuoteEntries.FIELD, 1);
        quickfix.fix42.QuoteCancel.NoQuoteEntries qe = new quickfix.fix42.QuoteCancel.NoQuoteEntries();
        qe.setString(quickfix.field.Symbol.FIELD, "TEST");
        msg.addGroup(qe);
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
        dictionary = getQF42DataDictionary();
        quickfix.fix42.QuoteCancel msg = new quickfix.fix42.QuoteCancel();
        QuoteCancelMsg42TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteCancelMsg dmsg = (QuoteCancelMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteCancelMsg42TestData.getInstance().check(msg, dmsg);
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
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
            QuoteCancelMsg42TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteCancelMsg dmsg = (QuoteCancelMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteCancelMsg42TestData.getInstance().check(msg, dmsg);
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
            msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
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
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setQuoteCancelType(QuoteCancelType.CancelAllQuotes.getValue());
            msg.setNoQuoteEntries(1);
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
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.setNoQuoteEntries(1);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteCancelType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteCancelMsg with missing NoQuoteEntries data.
     */
    @Test
    public void testEncodeMissingNoQuoteEntries() {
        System.out.println("-->testEncodeMissingNoQuoteEntries");
        try {
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.setQuoteCancelType(QuoteCancelType.CancelAllQuotes.getValue());
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoQuoteEntries] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteCancelMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            QuoteCancelMsg msg = (QuoteCancelMsg) FIXMsgBuilder.build(MsgType.QuoteCancel.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [QuoteCancelType] [NoQuoteEntries] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class QuoteCancelMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF42DataDictionary();
            quickfix.fix42.QuoteCancel msg = new quickfix.fix42.QuoteCancel();
            TestUtils.populateQuickFIX42HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [QuoteCancelType] [NoQuoteEntries] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteCancelMsg] message version [4.2].", ex.getMessage());
    }
}
