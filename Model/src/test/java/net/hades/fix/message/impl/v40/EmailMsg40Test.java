/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg40Test.java
 *
 * $Id: EmailMsg40Test.java,v 1.4 2010-03-21 10:18:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.impl.v40.data.EmailMsg40TestData;
import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.0 EmailMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/04/2009, 8:57:55 AM
 */
public class EmailMsg40Test extends MsgTest {

    private DataDictionary dictionary;

    public EmailMsg40Test() {
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
        dictionary = getQF40DataDictionary();
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setEmailType(EmailType.New);
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);

        assertEquals(msg.getEmailType().getValue(), qfMsg.getChar(quickfix.field.EmailType.FIELD));
        assertEquals(msg.getNoLinesOfText().intValue(), qfMsg.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix40.News.LinesOfText grplot1 = new quickfix.fix40.News.LinesOfText();
        qfMsg.getGroup(1, grplot1);
        quickfix.field.Text flot1 = new quickfix.field.Text();
        grplot1.get(flot1);
        assertEquals(msg.getLinesOfTextGroups()[0].getText(), flot1.getValue());
        quickfix.fix40.News.LinesOfText grplot2 = new quickfix.fix40.News.LinesOfText();
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
        dictionary = getQF40DataDictionary();
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
        EmailMsg40TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix40.Email qfMsg = new quickfix.fix40.Email();
        qfMsg.fromString(encoded, dictionary, true);
        EmailMsg40TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class EmailMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF40DataDictionary();
        quickfix.fix40.Email msg = new quickfix.fix40.Email();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setChar(quickfix.field.EmailType.FIELD, '0');
        msg.setInt(quickfix.field.LinesOfText.FIELD, 2);
        quickfix.fix40.News.LinesOfText grplot1 = new quickfix.fix40.News.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        msg.addGroup(grplot1);
        quickfix.fix40.News.LinesOfText grplot2 = new quickfix.fix40.News.LinesOfText();
        grplot2.setString(quickfix.field.Text.FIELD, "TEXT 2");
        msg.addGroup(grplot2);

        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);
        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getChar(quickfix.field.EmailType.FIELD), dmsg.getEmailType().getValue());
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
        dictionary = getQF40DataDictionary();
        quickfix.fix40.Email msg = new quickfix.fix40.Email();
        EmailMsg40TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        EmailMsg40TestData.getInstance().check(msg, dmsg);
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
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
            EmailMsg40TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            EmailMsg40TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class EmailMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        EmailMsg msg = null;
        try {
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
        } catch (Exception ex) {
            fail("Error building message");
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
            msg.getNoRelatedSyms();
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
     * Test of encode setter method, of class EmailMsg 4.0 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        EmailMsg msg = null;
        try {
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
        } catch (Exception ex) {
            fail("Error building message");
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
            msg.setNoRelatedSyms(new Integer(1));
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
     * Test of encode method, of class EmailMsg with missing NoLinesOfText data.
     */
    @Test
    public void testEncodeMissingNoLinesOfText() {
        System.out.println("-->testEncodeMissingNoLinesOfText");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setEmailType(EmailType.AdminReply);
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
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("TEXT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EmailType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class EmailMsg with missing NoLinesOfText data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF40DataDictionary();
            quickfix.fix40.Email msg = new quickfix.fix40.Email();
            TestUtils.populateQuickFIX40HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EmailType] [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    /*
     * Test of setNoLinesOfText method, of class EmailMsg.
     */
    @Test
    public void testSetNoLinesOfText() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_0);
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

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [EmailMsg] message version [4.0].", ex.getMessage());
    }
}
