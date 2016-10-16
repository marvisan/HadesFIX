/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg50Test.java
 *
 * $Id: MassQuoteAckMsg50Test.java,v 1.5 2011-01-15 02:10:11 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.impl.v50.data.MassQuoteAckMsg50TestData;
import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteStatus;

/**
 * Test suite for FIX 5.0 MassQuoteAckMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MassQuoteAckMsg50Test extends MsgTest  {

    private DataDictionary dictionary;

    public MassQuoteAckMsg50Test() {
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
     * Test of encode method, of class MassQuoteAckMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF44DataDictionary();
        MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setQuoteStatus(QuoteStatus.Accepted);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        MassQuoteAckMsg dmsg = (MassQuoteAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getQuoteStatus().getValue(), dmsg.getQuoteStatus().getValue());
    }

    /**
     * Test of encode decode method, of class MassQuoteAckMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        MassQuoteAckMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MassQuoteAckMsg dmsg = (MassQuoteAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MassQuoteAckMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter methods with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MassQuoteAckMsg msg = null;
        try {
            msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
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
            msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
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
            MassQuoteAckMsg msg = (MassQuoteAckMsg) FIXMsgBuilder.build(MsgType.MassQuoteAck.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50);
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
            quickfix.fix50.MassQuoteAcknowledgement msg = new quickfix.fix50.MassQuoteAcknowledgement();
            TestUtils.populateQuickFIX50HeaderAll(msg);
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
        assertEquals("This tag is not supported in [MassQuoteAckMsg] message version [5.0].", ex.getMessage());
    }
}
