/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg50SP2Test.java
 *
 * $Id: RFQRequestMsg50SP2Test.java,v 1.3 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50sp2.RFQRequestGroup50SP2TestData;
import net.hades.fix.message.impl.v50sp2.data.RFQRequestMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP1 RFQRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 09/04/2009, 3:12:30 PM
 */
public class RFQRequestMsg50SP2Test extends MsgTest {

    public RFQRequestMsg50SP2Test() {
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
     * Test of encode method, of class RFQRequest for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setRfqReqID("X162773883");
        msg.setNoRelatedSyms(new Integer(1));
        RFQRequestGroup50SP2TestData.getInstance().populate1(msg.getRFQRequestGroups()[0]);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        RFQRequestMsg dmsg = (RFQRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getRfqReqID(), dmsg.getRfqReqID());
        // RFQRequestGroup check
        assertEquals(msg.getNoRelatedSyms().intValue(), dmsg.getNoRelatedSyms().intValue());
    }

    /**
     * Test of encode method, of class RFQRequest all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        RFQRequestMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        RFQRequestMsg dmsg = (RFQRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        RFQRequestMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class RFQRequestMsg with missing RFQReqID data.
     */
    @Test
    public void testEncodeMissingRFQReqID() {
        System.out.println("-->testEncodeMissingRFQReqID");
        try {
            RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setNoRelatedSyms(new Integer(2));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RFQReqID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class RFQRequestMsg with missing NoRelatedSym data.
     */
    @Test
    public void testEncodeMissingNoRelatedSym() {
        System.out.println("-->testEncodeMissingNoRelatedSym");
        try {
            RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setRfqReqID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////
}
