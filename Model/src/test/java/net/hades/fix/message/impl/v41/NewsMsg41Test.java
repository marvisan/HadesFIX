/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg41Test.java
 *
 * $Id: NewsMsg41Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

import net.hades.fix.message.impl.v41.data.NewsMsg41TestData;
import quickfix.DataDictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RelatedSymbolGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.1 NewsMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/03/2009, 7:38:11 PM
 */
public class NewsMsg41Test extends MsgTest {

    private DataDictionary dictionary;

    public NewsMsg41Test() {
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
     * Test of encode method, of class NewsMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF41DataDictionary();
        NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setHeadline("Main Headline");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);

        assertEquals(msg.getHeadline(), qfMsg.getString(quickfix.field.Headline.FIELD));
        assertEquals(msg.getNoLinesOfText().intValue(), qfMsg.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix41.News.LinesOfText grplot1 = new quickfix.fix41.News.LinesOfText();
        qfMsg.getGroup(1, grplot1);
        quickfix.field.Text flot1 = new quickfix.field.Text();
        grplot1.get(flot1);
        assertEquals(msg.getLinesOfTextGroups()[0].getText(), flot1.getValue());
        quickfix.fix41.News.LinesOfText grplot2 = new quickfix.fix41.News.LinesOfText();
        qfMsg.getGroup(2, grplot2);
        quickfix.field.Text flot2 = new quickfix.field.Text();
        grplot2.get(flot2);
        assertEquals(msg.getLinesOfTextGroups()[1].getText(), flot2.getValue());
    }

    /**
     * Test of encode method, of class IOIMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF41DataDictionary();
        NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        NewsMsg41TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix41.News qfMsg = new quickfix.fix41.News();
        qfMsg.fromString(encoded, dictionary, true);
        NewsMsg41TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class NewsMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF41DataDictionary();
        quickfix.fix41.News msg = new quickfix.fix41.News();
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setString(quickfix.field.Headline.FIELD, "Headline");
        msg.setInt(quickfix.field.LinesOfText.FIELD, 2);
        quickfix.fix41.News.LinesOfText grplot1 = new quickfix.fix41.News.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        msg.addGroup(grplot1);
        quickfix.fix41.News.LinesOfText grplot2 = new quickfix.fix41.News.LinesOfText();
        grplot2.setString(quickfix.field.Text.FIELD, "TEXT 2");
        msg.addGroup(grplot2);

        String strMsg = msg.toString();

        System.out.println("qfix msg-->" + strMsg);
        NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.Headline.FIELD), dmsg.getHeadline());
        assertEquals(msg.getInt(quickfix.field.LinesOfText.FIELD), dmsg.getNoLinesOfText().intValue());
        assertEquals("TEXT 1", dmsg.getLinesOfTextGroups()[0].getText());
        assertEquals("TEXT 2", dmsg.getLinesOfTextGroups()[1].getText());
    }

    /**
     * Test of decode method, of class NewsMsg for FIX 4.0 all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF41DataDictionary();
        quickfix.fix41.News msg = new quickfix.fix41.News();
        NewsMsg41TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        NewsMsg41TestData.getInstance().check(msg, dmsg);
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
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
            NewsMsg41TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            NewsMsg41TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /*
     * Test of setNoRelatedSyms method, of class NewsMsg.
     */
    @Test
    public void testSetNoRelatedSyms() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getRelatedSymGroups());
        comp.setNoRelatedSyms(new Integer(3));
        for (int i = 0; i < comp.getRelatedSymGroups().length; i++) {
            RelatedSymbolGroup group = comp.getRelatedSymGroups()[i];
            group.setRelatedSym("MOT " + i);
        }
        assertEquals(3, comp.getRelatedSymGroups().length);
        int i = 0;
        for (RelatedSymbolGroup group : comp.getRelatedSymGroups()) {
            assertEquals("MOT " + i, group.getRelatedSym());
            i++;
        }
    }

    /*
     * Test of addRelatedSymGroup method, of class NewsMsg.
     */
    @Test
    public void testAddRelatedSymGroup() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getRelatedSymGroups());
        comp.setNoRelatedSyms(new Integer(2));
        assertEquals(2, comp.getRelatedSymGroups().length);
        for (int i = 0; i < comp.getRelatedSymGroups().length; i++) {
            RelatedSymbolGroup group = comp.getRelatedSymGroups()[i];
            group.setRelatedSym("MOT " + i);
        }
        comp.addRelatedSymGroup();
        assertEquals(3, comp.getRelatedSymGroups().length);
        comp.getRelatedSymGroups()[2].setRelatedSym("MOT 2");
        int i = 0;
        for (RelatedSymbolGroup group : comp.getRelatedSymGroups()) {
            assertEquals("MOT " + i, group.getRelatedSym());
            i++;
        }
        assertEquals(3, comp.getNoRelatedSyms().intValue());
    }

    /*
     * Test of deleteRelatedSymGroup method, of class NewsMsg.
     */
    @Test
    public void testDeleteRelatedSymGroup() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getRelatedSymGroups());
        comp.setNoRelatedSyms(new Integer(3));
        for (int i = 0; i < comp.getRelatedSymGroups().length; i++) {
            RelatedSymbolGroup group = comp.getRelatedSymGroups()[i];
            group.setRelatedSym("MOT " + i);
        }
        assertEquals(3, comp.getRelatedSymGroups().length);
        comp.deleteRelatedSymGroup(1);
        assertEquals(2, comp.getRelatedSymGroups().length);
        assertEquals(2, comp.getNoRelatedSyms().intValue());
        assertEquals("MOT 2", comp.getRelatedSymGroups()[1].getRelatedSym());
    }

    /*
     * Test of clearRelatedSymGroups method, of class NewsMsg.
     */
    @Test
    public void testClearRelatedSymGroups() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getRelatedSymGroups());
        comp.setNoRelatedSyms(new Integer(3));
        for (int i = 0; i < comp.getRelatedSymGroups().length; i++) {
            RelatedSymbolGroup group = comp.getRelatedSymGroups()[i];
            group.setRelatedSym("MOT " + i);
        }
        assertEquals(3, comp.getRelatedSymGroups().length);
        assertEquals(3, comp.getNoRelatedSyms().intValue());
        int i = 0;
        for (RelatedSymbolGroup group : comp.getRelatedSymGroups()) {
            assertEquals("MOT " + i, group.getRelatedSym());
            i++;
        }
        comp.clearRelatedSymGroups();
        assertNull(comp.getNoRelatedSyms());
        assertNull(comp.getRelatedSymGroups());
    }

    /*
     * Test of setNoLinesOfText method, of class NewsMsg40.
     */
    @Test
    public void testSetNoLinesOfText() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getLinesOfTextGroups());
        comp.setNoLinesOfText(new Integer(3));
        for (int i = 0; i < comp.getLinesOfTextGroups().length; i++) {
            LinesOfTextGroup group = comp.getLinesOfTextGroups()[i];
            group.setText("TEXT " + i);
        }
        assertEquals(3, comp.getLinesOfTextGroups().length);
        int i = 0;
        for (LinesOfTextGroup group : comp.getLinesOfTextGroups()) {
            assertEquals("TEXT " + i, group.getText());
            i++;
        }
    }

    /*
     * Test of addLinesOfTextGroup method, of class NewsMsg40.
     */
    @Test
    public void testAddLinesOfTextGroup() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getLinesOfTextGroups());
        comp.setNoLinesOfText(new Integer(2));
        assertEquals(2, comp.getLinesOfTextGroups().length);
        for (int i = 0; i < comp.getLinesOfTextGroups().length; i++) {
            LinesOfTextGroup group = comp.getLinesOfTextGroups()[i];
            group.setText("TEXT " + i);
        }
        comp.addLinesOfTextGroup();
        assertEquals(3, comp.getLinesOfTextGroups().length);
        comp.getLinesOfTextGroups()[2].setText("TEXT 2");
        int i = 0;
        for (LinesOfTextGroup group : comp.getLinesOfTextGroups()) {
            assertEquals("TEXT " + i, group.getText());
            i++;
        }
        assertEquals(3, comp.getNoLinesOfText().intValue());
    }

    /*
     * Test of deleteLinesOfTextGroup method, of class NewsMsg40.
     */
    @Test
    public void testDeleteLinesOfTextGroup() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getLinesOfTextGroups());
        comp.setNoLinesOfText(new Integer(3));
        for (int i = 0; i < comp.getLinesOfTextGroups().length; i++) {
            LinesOfTextGroup group = comp.getLinesOfTextGroups()[i];
            group.setText("TEXT " + i);
        }
        assertEquals(3, comp.getLinesOfTextGroups().length);
        comp.deleteLinesOfTextGroup(1);
        assertEquals(2, comp.getLinesOfTextGroups().length);
        assertEquals(2, comp.getNoLinesOfText().intValue());
        assertEquals("TEXT 2", comp.getLinesOfTextGroups()[1].getText());
    }

    /*
     * Test of clearLinesOfTextGroups method, of class NewsMsg40.
     */
    @Test
    public void testClearLinesOfTextGroups() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
        assertNull(comp.getLinesOfTextGroups());
        comp.setNoLinesOfText(new Integer(3));
        for (int i = 0; i < comp.getLinesOfTextGroups().length; i++) {
            LinesOfTextGroup group = comp.getLinesOfTextGroups()[i];
            group.setText("TEXT " + i);
        }
        assertEquals(3, comp.getLinesOfTextGroups().length);
        assertEquals(3, comp.getNoLinesOfText().intValue());
        int i = 0;
        for (LinesOfTextGroup group : comp.getLinesOfTextGroups()) {
            assertEquals("TEXT " + i, group.getText());
            i++;
        }
        comp.clearLinesOfTextGroups();
        assertNull(comp.getNoLinesOfText());
        assertNull(comp.getLinesOfTextGroups());
    }

    /**
     * Test of encode getter method, of class NewsMsg 4.0 with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        NewsMsg msg = null;
        try {
            msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
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
        try {
            msg.getInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoLegs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getInstrumentLegs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoUnderlyings();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getUnderlyingInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class NewsMsg 4.0 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        NewsMsg msg = null;
        try {
            msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
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
            msg.setNoRoutingIDs(new Integer(4));
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
            msg.deleteRoutingIDGroup(2);
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
        try {
            msg.addInstrument();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteInstrument(2);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoLegs(new Integer(1));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addInstrumentLeg();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteInstrumentLeg(2);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearInstrumentLegs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoUnderlyings(new Integer(3));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addUnderlyingInstrument();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteUnderlyingInstrument(3);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearUnderlyingInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class NewsMsg with missing NoLinesOfText data.
     */
    @Test
    public void testEncodeMissingReqNoLinesOfText() {
        System.out.println("-->testEncodeMissingReqNoLinesOfText");
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.setHeadline("Headline");
            msg.encode();
            
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class NewsMsg with missing Headline data.
     */
    @Test
    public void testEncodeMissingReqHeadline() {
        System.out.println("-->testEncodeMissingReqHeadline");
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("Line of text 1");
            msg.encode();
            
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [Headline] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllRequired() {
        System.out.println("-->testEncodeMissingAllRequired");
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [Headline] [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing NoLinesOfText data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF41DataDictionary();
            quickfix.fix41.News msg = new quickfix.fix41.News();
            TestUtils.populateQuickFIX41HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [Headline] [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [NewsMsg] message version [4.1].", ex.getMessage());
    }
}
