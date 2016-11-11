/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsg50SP2Test.java
 *
 * $Id: MassQuoteMsg50SP2Test.java,v 1.3 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import net.hades.fix.TestUtils;
import net.hades.fix.message.MassQuoteMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.MassQuoteMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 MassQuoteMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MassQuoteMsg50SP2Test extends MsgTest  {

    public MassQuoteMsg50SP2Test() {
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
     * Test of encode method, of class MassQuoteMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setQuoteID("X162773883");
        msg.setNoQuoteSets(new Integer(1));
        msg.getQuoteSetGroups()[0].setNoQuoteEntries(new Integer(1));
        msg.getQuoteSetGroups()[0].getQuoteEntryGroups()[0].setQuoteEntryID("TTT73849444");
        msg.getQuoteSetGroups()[0].setQuoteSetID("UUU94773666");
        msg.getQuoteSetGroups()[0].setUnderlyingInstrument();
        msg.getQuoteSetGroups()[0].getUnderlyingInstrument().setUnderlyingSymbol("IBM");
        msg.getQuoteSetGroups()[0].setTotNoQuoteEntries(new Integer(3));

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        MassQuoteMsg dmsg = (MassQuoteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getQuoteID(), dmsg.getQuoteID());
        assertEquals(msg.getNoQuoteSets().intValue(), dmsg.getNoQuoteSets().intValue());
    }

    /**
     * Test of encode method, of class MassQuoteMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        MassQuoteMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MassQuoteMsg dmsg = (MassQuoteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MassQuoteMsg50SP2TestData.getInstance().check(msg, dmsg);
    }


    /**
     * Test of encode getter methods with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MassQuoteMsg msg = null;
        try {
            msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter methods with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MassQuoteMsg msg = null;
        try {
            msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class MassQuoteMsg with missing QuoteID data.
     */
    @Test
    public void testEncodeMissingQuoteID() {
        System.out.println("-->testEncodeMissingQuoteID");
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.setNoQuoteSets(1);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MassQuoteMsg with missing NoQuoteSets data.
     */
    @Test
    public void testEncodeMissingNoQuoteSets() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.setQuoteID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoQuoteSets] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MassQuoteMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [NoQuoteSets] is missing.", ex.getMessage());
        }
    }


    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MassQuoteMsg] message version [5.0SP2].", ex.getMessage());
    }
}
