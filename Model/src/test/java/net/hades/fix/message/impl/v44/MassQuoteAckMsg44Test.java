/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg44Test.java
 *
 * $Id: MassQuoteAckMsg44Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.impl.v44.data.MassQuoteAckMsg44TestData;
import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteStatus;

/**
 * Test suite for FIX 4.4 MassQuoteAckMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MassQuoteAckMsg44Test extends MsgTest  {

    private DataDictionary dictionary;

    public MassQuoteAckMsg44Test() {
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
     * Test of encode method, of class MassQuoteAckMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF44DataDictionary();
        MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteStatus(QuoteStatus.Accepted);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteStatus().getValue(), qfMsg.getInt(quickfix.field.QuoteStatus.FIELD));
    }

    /**
     * Test of encode method, of class MassQuoteAckMsg all fields.
     * @throws Exception
     */
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF44DataDictionary();
        MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIX_4_4);
        MassQuoteAckMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix44.MassQuoteAcknowledgement qfMsg = new quickfix.fix44.MassQuoteAcknowledgement();
        qfMsg.fromString(encoded, dictionary, true);
        MassQuoteAckMsg44TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class MassQuoteAckMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.MassQuoteAcknowledgement msg = new quickfix.fix44.MassQuoteAcknowledgement();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setInt(quickfix.field.QuoteStatus.FIELD, quickfix.field.QuoteStatus.ACCEPTED);

        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        MassQuoteAckMsg dmsg = (MassQuoteAckMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getInt(quickfix.field.QuoteStatus.FIELD), dmsg.getQuoteStatus().getValue());
    }

    /**
     * Test of decode method, of class MassQuoteAckMsg for all fields.
     * @throws Exception
     */
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.MassQuoteAcknowledgement msg = new quickfix.fix44.MassQuoteAcknowledgement();
        MassQuoteAckMsg44TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        MassQuoteAckMsg dmsg = (MassQuoteAckMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MassQuoteAckMsg44TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode decode method, of class MassQuoteAckMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIX_4_4);
        MassQuoteAckMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MassQuoteAckMsg dmsg = (MassQuoteAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MassQuoteAckMsg44TestData.getInstance().check(msg, dmsg);
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
            MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIX_4_4);
            MassQuoteAckMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            MassQuoteAckMsg dmsg = (MassQuoteAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            MassQuoteAckMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter methods with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MassQuoteAckMsg msg = null;
        try {
            msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIX_4_4);
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
     * Test of encode setter methods with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MassQuoteAckMsg msg = null;
        try {
            msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIX_4_4);
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
     * Test of encode method, of class MassQuoteAckMsg with missing QuoteStatus data.
     */
    @Test
    public void testEncodeMissingQuoteStatus() {
        System.out.println("-->testEncodeMissingQuoteStatus");
        try {
            MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteStatus] is missing.", ex.getMessage());
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
            quickfix.fix44.MassQuoteAcknowledgement msg = new quickfix.fix44.MassQuoteAcknowledgement();
            TestUtils.populateQuickFIX44HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteStatus] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MassQuoteAckMsg] message version [4.4].", ex.getMessage());
    }
}
