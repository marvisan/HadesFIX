/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsg50Test.java
 *
 * $Id: MarketDataIncrRefreshMsg50Test.java,v 1.3 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.impl.v50.data.MarketDataIncrRefreshMsg50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test suite for FIX 5.0 MarketDataIncrRefreshMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MarketDataIncrRefreshMsg50Test extends MsgTest  {

    public MarketDataIncrRefreshMsg50Test() {
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
     * Test of encode/decode method message.
     * @throws Exception
     */
    @Test
    public void b3_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        MarketDataIncrRefreshMsg msg = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        MarketDataIncrRefreshMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MarketDataIncrRefreshMsg dmsg = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MarketDataIncrRefreshMsg50TestData.getInstance().check(msg, dmsg);
    }

    /*
     * Test of setNoRoutingIDs method, of class MarketDataIncrRefreshMsg.
     */
    @Test
    public void testSetNoRoutingIDs() throws Exception {
        MarketDataIncrRefreshMsg comp = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getRoutingIDGroups());
        comp.setNoRoutingIDs(new Integer(3));
        for (int i = 0; i < comp.getRoutingIDGroups().length; i++) {
            RoutingIDGroup group = comp.getRoutingIDGroups()[i];
            group.setRoutingType(RoutingType.BlockList);
            group.setRoutingID("ROUTE " + i);
        }
        assertEquals(3, comp.getRoutingIDGroups().length);
        int i = 0;
        for (RoutingIDGroup group : comp.getRoutingIDGroups()) {
            assertEquals("ROUTE " + i, group.getRoutingID());
            assertEquals(RoutingType.BlockList, group.getRoutingType());
            i++;
        }
    }

    /*
     * Test of addRoutingIDGroup method, of class MarketDataIncrRefreshMsg.
     */
    @Test
    public void testAddRoutingIDGroup() throws Exception {
        MarketDataIncrRefreshMsg comp = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getRoutingIDGroups());
        comp.setNoRoutingIDs(new Integer(2));
        assertEquals(2, comp.getRoutingIDGroups().length);
        for (int i = 0; i < comp.getRoutingIDGroups().length; i++) {
            RoutingIDGroup group = comp.getRoutingIDGroups()[i];
            group.setRoutingType(RoutingType.BlockList);
            group.setRoutingID("ROUTE " + i);
        }
        comp.addRoutingIDGroup();
        assertEquals(3, comp.getRoutingIDGroups().length);
        comp.getRoutingIDGroups()[2].setRoutingType(RoutingType.BlockList);
        comp.getRoutingIDGroups()[2].setRoutingID("ROUTE 2");
        int i = 0;
        for (RoutingIDGroup group : comp.getRoutingIDGroups()) {
            assertEquals("ROUTE " + i, group.getRoutingID());
            assertEquals(RoutingType.BlockList, group.getRoutingType());
            i++;
        }
        assertEquals(3, comp.getNoRoutingIDs().intValue());
    }

    /*
     * Test of deleteRoutingIDGroup method, of class MarketDataIncrRefreshMsg.
     */
    @Test
    public void testDeleteRoutingIDGroup() throws Exception {
        MarketDataIncrRefreshMsg comp = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getRoutingIDGroups());
        comp.setNoRoutingIDs(new Integer(3));
        for (int i = 0; i < comp.getRoutingIDGroups().length; i++) {
            RoutingIDGroup group = comp.getRoutingIDGroups()[i];
            group.setRoutingType(RoutingType.BlockList);
            group.setRoutingID("ROUTE " + i);
        }
        assertEquals(3, comp.getRoutingIDGroups().length);
        comp.deleteRoutingIDGroup(1);
        assertEquals(2, comp.getRoutingIDGroups().length);
        assertEquals(2, comp.getNoRoutingIDs().intValue());
        assertEquals("ROUTE 2", comp.getRoutingIDGroups()[1].getRoutingID());
    }

    /*
     * Test of clearRoutingIDGroups method, of class MarketDataIncrRefreshMsg.
     */
    @Test
    public void testClearRoutingIDGroups() throws Exception {
        MarketDataIncrRefreshMsg comp = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getRoutingIDGroups());
        comp.setNoRoutingIDs(new Integer(3));
        for (int i = 0; i < comp.getRoutingIDGroups().length; i++) {
            RoutingIDGroup group = comp.getRoutingIDGroups()[i];
            group.setRoutingType(RoutingType.BlockList);
            group.setRoutingID("ROUTE " + i);
        }
        assertEquals(3, comp.getRoutingIDGroups().length);
        assertEquals(3, comp.getNoRoutingIDs().intValue());
        int i = 0;
        for (RoutingIDGroup group : comp.getRoutingIDGroups()) {
            assertEquals("ROUTE " + i, group.getRoutingID());
            assertEquals(RoutingType.BlockList, group.getRoutingType());
            i++;
        }
        comp.clearRoutingIDGroups();
        assertNull(comp.getNoRoutingIDs());
        assertNull(comp.getRoutingIDGroups());
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MarketDataIncrRefreshMsg msg = null;
        try {
            msg = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class MarketDataIncrRefreshMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MarketDataIncrRefreshMsg msg = null;
        try {
            msg = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class MarketDataIncrRefreshMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingMDIncGroups() {
        System.out.println("-->testEncodeMissingMDIncGroups");
        try {
            MarketDataIncrRefreshMsg msg = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [NoMDEntries] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MarketDataIncrRefreshMsg] message version [5.0].", ex.getMessage());
    }
}
