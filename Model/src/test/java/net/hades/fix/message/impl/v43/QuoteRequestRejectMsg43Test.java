/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg43Test.java
 *
 * $Id: QuoteRequestRejectMsg43Test.java,v 1.4 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.group.impl.v43.QuoteRequestRejectGroup43TestData;
import net.hades.fix.message.impl.v43.data.QuoteRequestRejectMsg43TestData;
import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.QuoteRequestRejectGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteRequestRejectReason;

/**
 * Test suite for FIX 4.3 QuoteRequestRejectMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 09/04/2009, 3:12:30 PM
 */
public class QuoteRequestRejectMsg43Test extends MsgTest {

    private DataDictionary dictionary;

    public QuoteRequestRejectMsg43Test() {
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
        dictionary = getQF43DataDictionary();
        QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteReqID("X162773883");
        msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.InsufficientCredit.getValue());
        msg.setNoRelatedSym(new Integer(1));
        QuoteRequestRejectGroup43TestData.getInstance().populate1(msg.getQuoteRequestRejectGroups()[0]);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteReqID(), qfMsg.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(msg.getQuoteRequestRejectReason().intValue(), qfMsg.getInt(quickfix.field.QuoteRequestRejectReason.FIELD));
        assertEquals(msg.getNoRelatedSym().intValue(), qfMsg.getInt(quickfix.field.NoRelatedSym.FIELD));
    }

    /**
     * Test of encode method, of class QuoteRequestRejectMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF43DataDictionary();
        QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
        QuoteRequestRejectMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix43.QuoteRequestReject qfMsg = new quickfix.fix43.QuoteRequestReject();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteRequestRejectMsg43TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteRequestRejectMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.QuoteRequestReject msg = new quickfix.fix43.QuoteRequestReject();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setInt(quickfix.field.QuoteRequestRejectReason.FIELD, quickfix.field.QuoteRejectReason.DUPLICATE_QUOTE);
        // QuoteRequestGroup
        msg.setInt(quickfix.field.NoRelatedSym.FIELD, 1);
        quickfix.fix43.QuoteRequestReject.NoRelatedSym grprelsym1 = new quickfix.fix43.QuoteRequestReject.NoRelatedSym();
        QuoteRequestRejectGroup43TestData.getInstance().populate1(grprelsym1);
        msg.addGroup(grprelsym1);
        String strMsg = msg.toString();

        System.out.println("qfix msg-->" + strMsg);
        QuoteRequestRejectMsg dmsg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.QuoteReqID.FIELD), dmsg.getQuoteReqID());
        assertEquals(msg.getInt(quickfix.field.QuoteRequestRejectReason.FIELD), dmsg.getQuoteRequestRejectReason().intValue());
        // QuoteRequestGroup check
        assertEquals(msg.getInt(quickfix.field.NoRelatedSym.FIELD), dmsg.getNoRelatedSym().intValue());
    }

    /**
     * Test of decode method, of class NewsMsg for FIX 4.0 all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.QuoteRequestReject msg = new quickfix.fix43.QuoteRequestReject();
        QuoteRequestRejectMsg43TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteRequestRejectMsg dmsg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteRequestRejectMsg43TestData.getInstance().check(msg, dmsg);
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
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
            QuoteRequestRejectMsg43TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteRequestRejectMsg dmsg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteRequestRejectMsg43TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of setNoRelatedSym method, of class QuoteRequestRejectMsg.
     * @throws Exception
     */
    @Test
    public void testSetNoRelatedSym() throws Exception {
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
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
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
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
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
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
        QuoteRequestRejectMsg comp = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
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

    /**
     * Test of encode getter method with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteRequestRejectMsg msg = null;
        try {
            msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
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
     * Test of encode setter method with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteRequestRejectMsg msg = null;
        try {
            msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
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
     * Test of encode method, of class QuoteRequestRejectMsg with missing QuoteReqID data.
     */
    @Test
    public void testEncodeMissingQuoteReqID() {
        System.out.println("-->testEncodeMissingQuoteReqID");
        try {
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
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
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setQuoteReqID("43423534534");
            msg.setNoRelatedSym(new Integer(2));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteRequestRejectReason] is missing.", ex.getMessage());
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
            quickfix.fix43.QuoteRequestReject msg = new quickfix.fix43.QuoteRequestReject();
            TestUtils.populateQuickFIX43HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteReqID] [QuoteRequestRejectReason] [Instrument] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteRequestRejectMsg] message version [4.3].", ex.getMessage());
    }
}
