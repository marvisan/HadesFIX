/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataSnapshotMsg44Test.java
 *
 * $Id: MarketDataSnapshotMsg44Test.java,v 1.3 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.MarketDataSnapshotMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.4 MarketDataSnapshotMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MarketDataSnapshotMsg44Test extends MsgTest  {

    public MarketDataSnapshotMsg44Test() {
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
     * Test of encode method, of class MarketDataRequestMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeDecodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        MarketDataSnapshotMsg msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setSymbol("MOT");
        msg.setNoMDEntries(new Integer(1));
        msg.getMdFullGroups()[0].setMdEntryType(MDEntryType.Trade);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MarketDataSnapshotMsg dmsg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getSymbol(), dmsg.getSymbol());
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        MarketDataSnapshotMsg msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIX_4_4);
        MarketDataSnapshotMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MarketDataSnapshotMsg dmsg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MarketDataSnapshotMsg44TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b4_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            MarketDataSnapshotMsg msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIX_4_4);
            MarketDataSnapshotMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            MarketDataSnapshotMsg dmsg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            MarketDataSnapshotMsg44TestData.getInstance().check(msg, dmsg);
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
        MarketDataSnapshotMsg msg = null;
        try {
            msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoRoutingIDs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getRoutingIDGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class MarketDataRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MarketDataSnapshotMsg msg = null;
        try {
            msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoRoutingIDs(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addRoutingIDGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteRoutingIDGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearRoutingIDGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            MarketDataSnapshotMsg msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setNoMDEntries(new Integer(1));
            msg.getMdFullGroups()[0].setMdEntryType(MDEntryType.Trade);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [Symbol] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg with missing MDFullGroups data.
     */
    @Test
    public void testEncodeMissingMDFullGroups() {
        System.out.println("-->testEncodeMissingMDFullGroups");
        try {
            MarketDataSnapshotMsg msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setSymbol("MSFT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [NoMDEntries] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MarketDataSnapshotMsg] message version [4.4].", ex.getMessage());
    }
}
