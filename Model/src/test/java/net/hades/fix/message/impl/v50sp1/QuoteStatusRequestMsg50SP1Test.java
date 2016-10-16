/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusRequestMsg50SP1Test.java
 *
 * $Id: QuoteStatusRequestMsg50SP1Test.java,v 1.5 2010-03-21 11:25:17 vrotaru Exp $
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
import net.hades.fix.message.QuoteStatusRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.QuoteStatusRequestMsg50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP1 QuoteStatusRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteStatusRequestMsg50SP1Test extends MsgTest {

    public QuoteStatusRequestMsg50SP1Test() {
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
     * Test of encode method, of class QuoteStatusRequestMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        QuoteStatusRequestMsg msg = (QuoteStatusRequestMsg) FIXMsgBuilder.build(MsgType.QuoteStatusRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.getInstrument().setSymbol("IBM");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteStatusRequestMsg dmsg = (QuoteStatusRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
    }

    /**
     * Test of encode method, of class QuoteStatusRequestMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        QuoteStatusRequestMsg msg = (QuoteStatusRequestMsg) FIXMsgBuilder.build(MsgType.QuoteStatusRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        QuoteStatusRequestMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteStatusRequestMsg dmsg = (QuoteStatusRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteStatusRequestMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteStatusRequestMsg msg = null;
        try {
            msg = (QuoteStatusRequestMsg) FIXMsgBuilder.build(MsgType.QuoteStatusRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
     * Test of encode setter method, of class QuoteStatusRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteStatusRequestMsg msg = null;
        try {
            msg = (QuoteStatusRequestMsg) FIXMsgBuilder.build(MsgType.QuoteStatusRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
     * Test of encode method, of class QuoteStatusRequestMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            QuoteStatusRequestMsg msg = (QuoteStatusRequestMsg) FIXMsgBuilder.build(MsgType.QuoteStatusRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteStatusRequestMsg] message version [5.0SP1].", ex.getMessage());
    }}
