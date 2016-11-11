/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg43Test.java
 *
 * $Id: RFQRequestMsg43Test.java,v 1.4 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.impl.v43.data.RFQRequestMsg43TestData;
import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v43.RFQRequestGroup43TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.3 RFQRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 09/04/2009, 3:12:30 PM
 */
public class RFQRequestMsg43Test extends MsgTest {

    private DataDictionary dictionary;

    public RFQRequestMsg43Test() {
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
        dictionary = getQF43DataDictionary();
        RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setRfqReqID("X162773883");
        msg.setNoRelatedSyms(new Integer(1));
        RFQRequestGroup43TestData.getInstance().populate1(msg.getRFQRequestGroups()[0]);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getRfqReqID(), qfMsg.getString(quickfix.field.RFQReqID.FIELD));
        assertEquals(msg.getNoRelatedSyms().intValue(), qfMsg.getInt(quickfix.field.NoRelatedSym.FIELD));
    }

    /**
     * Test of encode method, of class RFQRequest all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF43DataDictionary();
        RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
        RFQRequestMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix43.RFQRequest qfMsg = new quickfix.fix43.RFQRequest();
        qfMsg.fromString(encoded, dictionary, true);
        RFQRequestMsg43TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class RFQRequest only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.RFQRequest msg = new quickfix.fix43.RFQRequest();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.RFQReqID.FIELD, "X162773883");
        // QuoteRequestGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 1);
        quickfix.fix43.RFQRequest.NoRelatedSym grprelsym1 = new quickfix.fix43.RFQRequest.NoRelatedSym();
        RFQRequestGroup43TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);

        String strMsg = msg.toString();

        System.out.println("qfix msg-->" + strMsg);
        RFQRequestMsg dmsg = (RFQRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.RFQReqID.FIELD), dmsg.getRfqReqID());
        // QuoteRequestGroup check
        assertEquals(msg.getInt(quickfix.field.NoRelatedSym.FIELD), dmsg.getNoRelatedSyms().intValue());
    }

    /**
     * Test of decode method, of class RFQRequest all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.RFQRequest msg = new quickfix.fix43.RFQRequest();
        RFQRequestMsg43TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        RFQRequestMsg dmsg = (RFQRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        RFQRequestMsg43TestData.getInstance().check(msg, dmsg);
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
            RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
            RFQRequestMsg43TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            RFQRequestMsg dmsg = (RFQRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            RFQRequestMsg43TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of setNoRelatedSym method, of class RFQRequestMsg.
     * @throws Exception
     */
    @Test
    public void testSetNoRelatedSyms() throws Exception {
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
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
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
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
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
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
        RFQRequestMsg comp = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
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

    /**
     * Test of encode getter method, of class RFQRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        RFQRequestMsg msg = null;
        try {
            msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class RFQRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        RFQRequestMsg msg = null;
        try {
            msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
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
            RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            RFQRequestMsg msg = (RFQRequestMsg) FIXMsgBuilder.build(MsgType.RFQRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setRfqReqID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
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
            quickfix.fix43.RFQRequest msg = new quickfix.fix43.RFQRequest();
            TestUtils.populateQuickFIX43HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RFQReqID] [Instrument] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [RFQRequestMsg] message version [4.3].", ex.getMessage());
    }
}
