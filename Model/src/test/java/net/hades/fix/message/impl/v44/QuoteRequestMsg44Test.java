/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg44Test.java
 *
 * $Id: QuoteRequestMsg44Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.group.impl.v44.QuoteRequestGroup44TestData;
import net.hades.fix.message.impl.v44.data.QuoteRequestMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.4 QuoteRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 09/04/2009, 3:12:30 PM
 */
public class QuoteRequestMsg44Test extends MsgTest {

    private DataDictionary dictionary;

    public QuoteRequestMsg44Test() {
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
        dictionary = getQF44DataDictionary();
        QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteReqID("X162773883");
        msg.setNoRelatedSym(new Integer(1));
        QuoteRequestGroup44TestData.getInstance().populate1(msg.getQuoteRelatedSymbols()[0]);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
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
        dictionary = getQF44DataDictionary();
        QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
        QuoteRequestMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix44.QuoteRequest qfMsg = new quickfix.fix44.QuoteRequest();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteRequestMsg44TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteRequestMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.QuoteRequest msg = new quickfix.fix44.QuoteRequest();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        // QuoteRequestGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 1);
        quickfix.fix44.QuoteRequest.NoRelatedSym grprelsym1 = new quickfix.fix44.QuoteRequest.NoRelatedSym();
        QuoteRequestGroup44TestData.getInstance().populate1(grprelsym1);
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
        dictionary = getQF44DataDictionary();
        quickfix.fix44.QuoteRequest msg = new quickfix.fix44.QuoteRequest();
        QuoteRequestMsg44TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteRequestMsg dmsg = (QuoteRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteRequestMsg44TestData.getInstance().check(msg, dmsg);
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
            QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
            QuoteRequestMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteRequestMsg dmsg = (QuoteRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteRequestMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of setNoRelatedSym method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testSetNoRelatedSym() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
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
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
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
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
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
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
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
            msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
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
            msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
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
            QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            dictionary = getQF44DataDictionary();
            quickfix.fix44.QuoteRequest msg = new quickfix.fix44.QuoteRequest();
            TestUtils.populateQuickFIX44HeaderAll(msg);
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
        assertEquals("This tag is not supported in [QuoteRequestMsg] message version [4.4].", ex.getMessage());
    }
}
