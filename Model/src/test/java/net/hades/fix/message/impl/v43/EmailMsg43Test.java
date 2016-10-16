/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg43Test.java
 *
 * $Id: EmailMsg43Test.java,v 1.3 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.impl.v43.data.EmailMsg43TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test suite for FIX 4.3 EmailMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/04/2009, 8:57:55 AM
 */
public class EmailMsg43Test extends MsgTest {

    private DataDictionary dictionary;

    public EmailMsg43Test() {
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
     * Test of encode method, of class EmailMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF43DataDictionary();
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setEmailThreadID("Thread1");
        msg.setEmailType(EmailType.New);
        msg.setSubject("Subject text");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);

        assertEquals(msg.getEmailThreadID(), qfMsg.getString(quickfix.field.EmailThreadID.FIELD));
        assertEquals(msg.getEmailType().getValue(), qfMsg.getChar(quickfix.field.EmailType.FIELD));
        assertEquals(msg.getSubject(), qfMsg.getString(quickfix.field.Subject.FIELD));
        assertEquals(msg.getNoLinesOfText().intValue(), qfMsg.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix43.News.LinesOfText grplot1 = new quickfix.fix43.News.LinesOfText();
        qfMsg.getGroup(1, grplot1);
        quickfix.field.Text flot1 = new quickfix.field.Text();
        grplot1.get(flot1);
        assertEquals(msg.getLinesOfTextGroups()[0].getText(), flot1.getValue());
        quickfix.fix43.News.LinesOfText grplot2 = new quickfix.fix43.News.LinesOfText();
        qfMsg.getGroup(2, grplot2);
        quickfix.field.Text flot2 = new quickfix.field.Text();
        grplot2.get(flot2);
        assertEquals(msg.getLinesOfTextGroups()[1].getText(), flot2.getValue());
    }

    /**
     * Test of encode method, of class EmailMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF43DataDictionary();
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        EmailMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix43.Email qfMsg = new quickfix.fix43.Email();
        qfMsg.fromString(encoded, dictionary, true);
        EmailMsg43TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class EmailMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.Email msg = new quickfix.fix43.Email();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.EmailThreadID.FIELD, "thread no 1");
        msg.setChar(quickfix.field.EmailType.FIELD, '0');
        msg.setString(quickfix.field.Subject.FIELD, "Email subject");
        msg.setInt(quickfix.field.LinesOfText.FIELD, 2);
        quickfix.fix43.News.LinesOfText grplot1 = new quickfix.fix43.News.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        msg.addGroup(grplot1);
        quickfix.fix43.News.LinesOfText grplot2 = new quickfix.fix43.News.LinesOfText();
        grplot2.setString(quickfix.field.Text.FIELD, "TEXT 2");
        msg.addGroup(grplot2);

        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);
        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.EmailThreadID.FIELD), dmsg.getEmailThreadID());
        assertEquals(msg.getChar(quickfix.field.EmailType.FIELD), dmsg.getEmailType().getValue());
        assertEquals(msg.getString(quickfix.field.Subject.FIELD), dmsg.getSubject());
        assertEquals(msg.getInt(quickfix.field.LinesOfText.FIELD), dmsg.getNoLinesOfText().intValue());
        assertEquals("TEXT 1", dmsg.getLinesOfTextGroups()[0].getText());
        assertEquals("TEXT 2", dmsg.getLinesOfTextGroups()[1].getText());
    }

    /**
     * Test of decode method, of class EmailMsg all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.Email msg = new quickfix.fix43.Email();
        EmailMsg43TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        EmailMsg43TestData.getInstance().check(msg, dmsg);
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
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
            EmailMsg43TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            EmailMsg43TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /*
     * Test of setNoRoutingIDs method, of class EmailMsg.
     */
    @Test
    public void testSetNoRoutingIDs() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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
     * Test of addRoutingIDGroup method, of class EmailMsg.
     */
    @Test
    public void testAddRoutingIDGroup() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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
     * Test of deleteRoutingIDGroup method, of class EmailMsg.
     */
    @Test
    public void testDeleteRoutingIDGroup() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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
     * Test of clearRoutingIDGroups method, of class EmailMsg.
     */
    @Test
    public void testClearRoutingIDGroups() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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


    /*
     * Test of setNoRelatedSyms method, of class EmailMsg.
     */
    @Test
    public void testSetNoRelatedSyms() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        assertNull(comp.getInstruments());
        comp.setNoRelatedSyms(new Integer(3));
        for (int i = 0; i < comp.getInstruments().length; i++) {
            Instrument group = comp.getInstruments()[i];
            group.setSymbol("MOT " + i);
        }
        assertEquals(3, comp.getInstruments().length);
        int i = 0;
        for (Instrument group : comp.getInstruments()) {
            assertEquals("MOT " + i, group.getSymbol());
            i++;
        }
    }

    /*
     * Test of addInstrument method, of class EmailMsg.
     */
    @Test
    public void testAddInstrument() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        assertNull(comp.getInstruments());
        comp.setNoRelatedSyms(new Integer(2));
        assertEquals(2, comp.getInstruments().length);
        for (int i = 0; i < comp.getInstruments().length; i++) {
            Instrument group = comp.getInstruments()[i];
            group.setSymbol("MOT " + i);
        }
        comp.addInstrument();
        assertEquals(3, comp.getInstruments().length);
        comp.getInstruments()[2].setSymbol("MOT 2");
        int i = 0;
        for (Instrument group : comp.getInstruments()) {
            assertEquals("MOT " + i, group.getSymbol());
            i++;
        }
        assertEquals(3, comp.getNoRelatedSyms().intValue());
    }

    /*
     * Test of deleteInstrument method, of class EmailMsg.
     */
    @Test
    public void testDeleteInstrument() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        assertNull(comp.getInstruments());
        comp.setNoRelatedSyms(new Integer(3));
        for (int i = 0; i < comp.getInstruments().length; i++) {
            Instrument group = comp.getInstruments()[i];
            group.setSymbol("MOT " + i);
        }
        assertEquals(3, comp.getInstruments().length);
        comp.deleteInstrument(1);
        assertEquals(2, comp.getInstruments().length);
        assertEquals(2, comp.getNoRelatedSyms().intValue());
        assertEquals("MOT 2", comp.getInstruments()[1].getSymbol());
    }

    /*
     * Test of clearInstruments method, of class EmailMsg.
     */
    @Test
    public void testClearInstruments() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        assertNull(comp.getInstruments());
        comp.setNoRelatedSyms(new Integer(3));
        for (int i = 0; i < comp.getInstruments().length; i++) {
            Instrument group = comp.getInstruments()[i];
            group.setSymbol("MOT " + i);
        }
        assertEquals(3, comp.getInstruments().length);
        assertEquals(3, comp.getNoRelatedSyms().intValue());
        int i = 0;
        for (Instrument group : comp.getInstruments()) {
            assertEquals("MOT " + i, group.getSymbol());
            i++;
        }
        comp.clearInstruments();
        assertNull(comp.getNoRelatedSyms());
        assertNull(comp.getInstruments());
    }

    /*
     * Test of setNoLinesOfText method, of class EmailMsg.
     */
    @Test
    public void testSetNoLinesOfText() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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
     * Test of addLinesOfTextGroup method, of class EmailMsg.
     */
    @Test
    public void testAddLinesOfTextGroup() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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
     * Test of deleteLinesOfTextGroup method, of class EmailMsg.
     */
    @Test
    public void testDeleteLinesOfTextGroup() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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
     * Test of clearLinesOfTextGroups method, of class EmailMsg.
     */
    @Test
    public void testClearLinesOfTextGroups() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
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
     * Test of encode getter method, of class EmailMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        EmailMsg msg = null;
        try {
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getRelatedSymGroups();
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
     * Test of encode setter method, of class EmailMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        EmailMsg msg = null;
        try {
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.addRelatedSymGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteRelatedSymGroup(2);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearRelatedSymGroups();
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
     * Test of encode method, of class EmailMsg with missing NoLinesOfText data.
     */
    @Test
    public void testEncodeMissingNoLinesOfText() {
        System.out.println("-->testEncodeMissingNoLinesOfText");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setEmailType(EmailType.AdminReply);
            msg.setEmailThreadID("Thread");
            msg.setSubject("Subject");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class EmailMsg with missing EmailType data.
     */
    @Test
    public void testEncodeMissingEmailType() {
        System.out.println("-->testEncodeMissingEmailType");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setEmailThreadID("Thread");
            msg.setSubject("Subject");
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("TEXT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EmailType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class EmailMsg with missing EmailThreadID data.
     */
    @Test
    public void testEncodeMissingEmailThreadID() {
        System.out.println("-->testEncodeMissingEmailThreadID");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setEmailType(EmailType.AdminReply);
            msg.setSubject("Subject");
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("TEXT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EmailThreadID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class EmailMsg with missing Subject data.
     */
    @Test
    public void testEncodeMissingSubject() {
        System.out.println("-->testEncodeMissingSubject");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setEmailType(EmailType.AdminReply);
            msg.setEmailThreadID("Thread");
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("TEXT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Subject] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class EmailMsg with missing NoLinesOfText data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF43DataDictionary();
            quickfix.fix43.Email msg = new quickfix.fix43.Email();
            TestUtils.populateQuickFIX43HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EmailThreadID] [EmailType] [Subject] [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [EmailMsg] message version [4.3].", ex.getMessage());
    }
}
