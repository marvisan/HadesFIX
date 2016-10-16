/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg50SP1Test.java
 *
 * $Id: RFQRequestMsg50SP1Test.java,v 1.3 2010-03-21 11:25:17 vrotaru Exp $
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
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50sp1.RFQRequestGroup50SP1TestData;
import net.hades.fix.message.impl.v50sp1.data.RFQRequestMsg50SP1TestData;
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
public class RFQRequestMsg50SP1Test extends MsgTest {

    public RFQRequestMsg50SP1Test() {
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
        RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.setRfqReqID("X162773883");
        msg.setNoRelatedSyms(new Integer(1));
        RFQRequestGroup50SP1TestData.getInstance().populate1(msg.getRFQRequestGroups()[0]);

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
        RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        RFQRequestMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        RFQRequestMsg dmsg = (RFQRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        RFQRequestMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of setNoRelatedSym method, of class RFQRequestMsg.
     * @throws Exception
     */
    @Test
    public void testSetNoRelatedSyms() throws Exception {
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getRFQRequestGroups());
        comp.setNoRelatedSyms(new Integer(3));
        assertEquals(3, comp.getRFQRequestGroups().length);
        for (int i = 0; i < comp.getRFQRequestGroups().length; i++) {
            assertNotNull(comp.getRFQRequestGroups()[i]);
        }
    }

    /**
     * Test of addRFQRequestGroup method, of class RFQRequestMsg.
     * @throws Exception
     */
    @Test
    public void testAddRFQRequestGroup() throws Exception {
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getRFQRequestGroups());
        comp.setNoRelatedSyms(new Integer(2));
        assertEquals(2, comp.getRFQRequestGroups().length);
        comp.addRFQRequestGroup();
        assertEquals(3, comp.getRFQRequestGroups().length);
        for (int i = 0; i < comp.getRFQRequestGroups().length; i++) {
            assertNotNull(comp.getRFQRequestGroups()[i]);
        }
        assertEquals(3, comp.getNoRelatedSyms().intValue());
    }

    /**
     * Test of deleteRFQRequestGroup method, of class RFQRequestMsg.
     * @throws Exception
     */
    @Test
    public void testDeleteRFQRequestGroup() throws Exception {
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getRFQRequestGroups());
        comp.setNoRelatedSyms(new Integer(3));
        assertEquals(3, comp.getRFQRequestGroups().length);
        for (int i = 0; i < comp.getRFQRequestGroups().length; i++) {
            assertNotNull(comp.getRFQRequestGroups()[i]);
        }
        comp.deleteRFQRequestGroup(1);
        assertEquals(2, comp.getRFQRequestGroups().length);
        assertEquals(2, comp.getNoRelatedSyms().intValue());
    }

    /**
     * Test of clearRFQRequestGroups method, of class RFQRequestMsg.
     * @throws Exception
     */
    @Test
    public void testClearRFQRequestGroups() throws Exception {
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getRFQRequestGroups());
        comp.setNoRelatedSyms(new Integer(3));
        assertEquals(3, comp.getRFQRequestGroups().length);
        for (int i = 0; i < comp.getRFQRequestGroups().length; i++) {
            assertNotNull(comp.getRFQRequestGroups()[i]);
        }
        assertEquals(3, comp.getNoRelatedSyms().intValue());
        comp.clearRFQRequestGroups();
        assertNull(comp.getRFQRequestGroups());
        assertNull(comp.getNoRelatedSyms());
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
            RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
