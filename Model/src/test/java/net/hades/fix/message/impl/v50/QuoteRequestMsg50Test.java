/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg50Test.java
 *
 * $Id: QuoteRequestMsg50Test.java,v 1.5 2011-01-15 02:10:11 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.group.impl.v50.QuoteRequestGroup50TestData;
import net.hades.fix.message.impl.v50.data.QuoteRequestMsg50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0 QuoteRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 09/04/2009, 3:12:30 PM
 */
public class QuoteRequestMsg50Test extends MsgTest {

    public QuoteRequestMsg50Test() {
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
     * Test of encode method, of class QuoteRequestMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setQuoteReqID("X162773883");
        msg.setNoRelatedSym(new Integer(1));
        QuoteRequestGroup50TestData.getInstance().populate1(msg.getQuoteRelatedSymbols()[0]);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix50.Message qfMsg = new quickfix.fix50.Message();
        qfMsg.fromString(encoded, getQFSessDataDictionary(), getQF50DataDictionary(), true);
        assertEquals(msg.getQuoteReqID(), qfMsg.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(msg.getNoRelatedSym().intValue(), qfMsg.getInt(quickfix.field.NoRelatedSym.FIELD));
    }

    /**
     * Test of encode method, of class QuoteRequestMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        QuoteRequestMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix50.QuoteRequest qfMsg = new quickfix.fix50.QuoteRequest();
        qfMsg.fromString(encoded, getQFSessDataDictionary(), getQF50DataDictionary(), true);
        QuoteRequestMsg50TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteRequestMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        quickfix.fix50.QuoteRequest msg = new quickfix.fix50.QuoteRequest();
        TestUtils.populateQuickFIX50HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        // QuoteRequestGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 1);
        quickfix.fix50.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix50.QuoteRequest.NoRelatedSym();
        QuoteRequestGroup50TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);

        String strMsg = msg.toString();

        System.out.println("qfix msg-->" + strMsg);
        QuoteRequestMsg dmsg = (QuoteRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.QuoteReqID.FIELD), dmsg.getQuoteReqID());
        // QuoteRequestGroup check
        assertEquals(msg.getInt(quickfix.field.NoRelatedSym.FIELD), dmsg.getNoRelatedSym().intValue());
    }

    /**
     * Test of decode method, of class QuoteRequestMsg all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        quickfix.fix50.QuoteRequest msg = new quickfix.fix50.QuoteRequest();
        QuoteRequestMsg50TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteRequestMsg dmsg = (QuoteRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteRequestMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of setNoRelatedSym method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testSetNoRelatedSym() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        int i = 0;
        for (QuoteRequestGroup group : comp.getQuoteRelatedSymbols()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
    }

    /**
     * Test of addQuoteRelatedSymbolGroup method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testAddQuoteRelatedSymbolGroup() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(2));
        assertEquals(2, comp.getQuoteRelatedSymbols().length);
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        comp.addQuoteRelatedSymbolGroup();
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        comp.getQuoteRelatedSymbols()[2].getInstrument().setSymbol("SYM 2");
        int i = 0;
        for (QuoteRequestGroup group : comp.getQuoteRelatedSymbols()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
        assertEquals(3, comp.getNoRelatedSym().intValue());
    }

    /**
     * Test of deleteQuoteRelatedSymbolGroup method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testDeleteQuoteRelatedSymbolGroup() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        comp.deleteQuoteRelatedSymbolGroup(1);
        assertEquals(2, comp.getQuoteRelatedSymbols().length);
        assertEquals(2, comp.getNoRelatedSym().intValue());
        assertEquals("SYM 2", comp.getQuoteRelatedSymbols()[1].getInstrument().getSymbol());
    }

    /**
     * Test of clearQuoteRelatedSymbolGroups method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testClearQuoteRelatedSymbolGroups() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        assertEquals(3, comp.getNoRelatedSym().intValue());
        int i = 0;
        for (QuoteRequestGroup group : comp.getQuoteRelatedSymbols()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
        comp.clearQuoteRelatedSymbolGroups();
        assertNull(comp.getQuoteRelatedSymbols());
        assertNull(comp.getNoRelatedSym());
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteRequestMsg msg = null;
        try {
           msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getRootParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteRequestMsg msg = null;
        try {
            msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setRootParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearRootParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteRequestMsg with missing QuoteReqID data.
     */
    @Test
    public void testEncodeMissingQuoteReqID() {
        System.out.println("-->testEncodeMissingQuoteReqID");
        try {
            QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setNoRelatedSym(new Integer(2));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteReqID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteRequestMsg with missing NoRelatedSym data.
     */
    @Test
    public void testEncodeMissingNoRelatedSym() {
        System.out.println("-->testEncodeMissingNoRelatedSym");
        try {
            QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setQuoteReqID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoRelatedSym] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            quickfix.fix50.QuoteRequest msg = new quickfix.fix50.QuoteRequest();
            TestUtils.populateQuickFIX50HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteReqID] [NoRelatedSym] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteRequestMsg] message version [5.0].", ex.getMessage());
    }
}
