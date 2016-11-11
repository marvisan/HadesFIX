/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg50SP1Test.java
 *
 * $Id: QuoteRequestRejectMsg50SP1Test.java,v 1.3 2010-03-21 11:25:17 vrotaru Exp $
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
import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.QuoteRequestRejectGroup;
import net.hades.fix.message.group.impl.v50sp1.QuoteRequestRejectGroup50SP1TestData;
import net.hades.fix.message.impl.v50sp1.data.QuoteRequestRejectMsg50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteRequestRejectReason;

/**
 * Test suite for FIX 5.0SP1 QuoteRequestRejectMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 09/04/2009, 3:12:30 PM
 */
public class QuoteRequestRejectMsg50SP1Test extends MsgTest {

    public QuoteRequestRejectMsg50SP1Test() {
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
     * Test of encode method, of class QuoteRequestMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.setQuoteReqID("X162773883");
        msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.InsufficientCredit.getValue());
        msg.setNoRelatedSym(new Integer(1));
        QuoteRequestRejectGroup50SP1TestData.getInstance().populate1(msg.getQuoteRequestRejectGroups()[0]);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        QuoteRequestRejectMsg dmsg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getQuoteReqID(), dmsg.getQuoteReqID());
        assertEquals(msg.getQuoteRequestRejectReason().intValue(), dmsg.getQuoteRequestRejectReason().intValue());
        assertEquals(msg.getNoRelatedSym().intValue(), dmsg.getNoRelatedSym().intValue());
    }

    /**
     * Test of encode method, of class QuoteRequestRejectMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        QuoteRequestRejectMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteRequestRejectMsg dmsg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteRequestRejectMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of setNoRelatedSym method, of class QuoteRequestRejectMsg.
     * @throws Exception
     */
    @Test
    public void testSetNoRelatedSym() throws Exception {
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getQuoteRequestRejectGroups());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRequestRejectGroups().length; i++) {
            QuoteRequestRejectGroup group = comp.getQuoteRequestRejectGroups()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRequestRejectGroups().length);
        int i = 0;
        for (QuoteRequestRejectGroup group : comp.getQuoteRequestRejectGroups()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
    }

    /**
     * Test of addQuoteRelatedSymbolGroup method, of class QuoteRequestRejectMsg.
     * @throws Exception
     */
    @Test
    public void testAddQuoteRelatedSymbolGroup() throws Exception {
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getQuoteRequestRejectGroups());
        comp.setNoRelatedSym(new Integer(2));
        assertEquals(2, comp.getQuoteRequestRejectGroups().length);
        for (int i = 0; i < comp.getQuoteRequestRejectGroups().length; i++) {
            QuoteRequestRejectGroup group = comp.getQuoteRequestRejectGroups()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        comp.addQuoteRequestRejectGroup();
        assertEquals(3, comp.getQuoteRequestRejectGroups().length);
        comp.getQuoteRequestRejectGroups()[2].getInstrument().setSymbol("SYM 2");
        int i = 0;
        for (QuoteRequestRejectGroup group : comp.getQuoteRequestRejectGroups()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
        assertEquals(3, comp.getNoRelatedSym().intValue());
    }

    /**
     * Test of deleteQuoteRelatedSymbolGroup method, of class QuoteRequestRejectMsg.
     * @throws Exception
     */
    @Test
    public void testDeleteQuoteRelatedSymbolGroup() throws Exception {
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getQuoteRequestRejectGroups());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRequestRejectGroups().length; i++) {
            QuoteRequestRejectGroup group = comp.getQuoteRequestRejectGroups()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRequestRejectGroups().length);
        comp.deleteQuoteRequestRejectGroup(1);
        assertEquals(2, comp.getQuoteRequestRejectGroups().length);
        assertEquals(2, comp.getNoRelatedSym().intValue());
        assertEquals("SYM 2", comp.getQuoteRequestRejectGroups()[1].getInstrument().getSymbol());
    }

    /**
     * Test of clearQuoteRelatedSymbolGroups method, of class QuoteRequestRejectMsg.
     * @throws Exception
     */
    @Test
    public void testClearQuoteRelatedSymbolGroups() throws Exception {
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getQuoteRequestRejectGroups());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRequestRejectGroups().length; i++) {
            QuoteRequestRejectGroup group = comp.getQuoteRequestRejectGroups()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRequestRejectGroups().length);
        assertEquals(3, comp.getNoRelatedSym().intValue());
        int i = 0;
        for (QuoteRequestRejectGroup group : comp.getQuoteRequestRejectGroups()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
        comp.clearQuoteRequestRejectGroups();
        assertNull(comp.getQuoteRequestRejectGroups());
        assertNull(comp.getNoRelatedSym());
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteRequestRejectMsg with missing QuoteReqID data.
     */
    @Test
    public void testEncodeMissingQuoteReqID() {
        System.out.println("-->testEncodeMissingQuoteReqID");
        try {
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
            msg.setNoRelatedSym(new Integer(2));
            msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.ExchangeClosed.getValue());
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteReqID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteRequestRejectMsg with missing NoRelatedSym data.
     */
    @Test
    public void testEncodeMissingNoRelatedSym() {
        System.out.println("-->testEncodeMissingNoRelatedSym");
        try {
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
            msg.setQuoteReqID("43423534534");
            msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.ExchangeClosed.getValue());
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteRequestRejectMsg with missing QuoteRequestRejectReason data.
     */
    @Test
    public void testEncodeMissingQuoteRequestRejectReason() {
        System.out.println("-->testEncodeMissingQuoteRequestRejectReason");
        try {
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
            msg.setQuoteReqID("43423534534");
            msg.setNoRelatedSym(new Integer(2));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteRequestRejectReason] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

}
