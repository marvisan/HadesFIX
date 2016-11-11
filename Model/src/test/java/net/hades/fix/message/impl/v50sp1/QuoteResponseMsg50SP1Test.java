/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteResponseMsg50SP1Test.java
 *
 * $Id: QuoteResponseMsg50SP1Test.java,v 1.5 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteResponseMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.QuoteResponseMsg50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteRespType;

/**
 * Test suite for FIX 5.0SP1 QuoteResponseMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteResponseMsg50SP1Test extends MsgTest  {

    public QuoteResponseMsg50SP1Test() {
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
     * Test of encode method, of class QuoteResponseMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.setQuoteRespID("X162773883");
        msg.setQuoteRespType(QuoteRespType.Counter);
        msg.getInstrument().setSymbol("SUN");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        QuoteResponseMsg dmsg = (QuoteResponseMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getQuoteRespID(), dmsg.getQuoteRespID());
        assertEquals(msg.getQuoteRespType().getValue(), dmsg.getQuoteRespType().getValue());
        assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
    }

    /**
     * Test of encode method, of class QuoteResponseMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        QuoteResponseMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteResponseMsg dmsg = (QuoteResponseMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteResponseMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteResponseMsg msg = null;
        try {
            msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            QuoteResponseMsg msg = (QuoteResponseMsg) FIXMsgBuilder.build(MsgType.QuoteResponse.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteRespID] [QuoteRespType] [Instrument] is missing.", ex.getMessage());
        }
    }


    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteResponseMsg] message version [5.0SP1].", ex.getMessage());
    }}
