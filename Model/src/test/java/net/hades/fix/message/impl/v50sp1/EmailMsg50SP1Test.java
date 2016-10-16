/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg50SP1Test.java
 *
 * $Id: EmailMsg50SP1Test.java,v 1.3 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.impl.v50sp1.data.EmailMsg50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test suite for FIX 5.0SP1 EmailMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/04/2009, 8:57:55 AM
 */
public class EmailMsg50SP1Test extends MsgTest {

    public EmailMsg50SP1Test() {
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
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.setEmailThreadID("Thread1");
        msg.setEmailType(EmailType.New);
        msg.setSubject("Subject text");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getEmailThreadID(), dmsg.getEmailThreadID());
        assertEquals(msg.getEmailType().getValue(), dmsg.getEmailType().getValue());
        assertEquals(msg.getSubject(), dmsg.getSubject());
        assertEquals(msg.getNoLinesOfText().intValue(), dmsg.getNoLinesOfText().intValue());
        assertEquals(msg.getLinesOfTextGroups()[0].getText(), dmsg.getLinesOfTextGroups()[0].getText());
        assertEquals(msg.getLinesOfTextGroups()[1].getText(), dmsg.getLinesOfTextGroups()[1].getText());
    }

    /**
     * Test of encode method, of class EmailMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        EmailMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        EmailMsg50SP1TestData.getInstance().check(msg, dmsg);
    }
    /*
     * Test of setNoRoutingIDs method, of class EmailMsg.
     */
    @Test
    public void testSetNoRoutingIDs() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
     * Test of setNoLegs method, of class EmailMsg.
     */
    @Test
    public void testSetNoLegs() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getInstrumentLegs());
        comp.setNoLegs(new Integer(3));
        for (int i = 0; i < comp.getInstrumentLegs().length; i++) {
            InstrumentLeg group = comp.getInstrumentLegs()[i];
            group.setLegSymbol("MOT " + i);
        }
        assertEquals(3, comp.getInstrumentLegs().length);
        int i = 0;
        for (InstrumentLeg group : comp.getInstrumentLegs()) {
            assertEquals("MOT " + i, group.getLegSymbol());
            i++;
        }
    }

    /*
     * Test of addInstrumentLeg method, of class EmailMsg.
     */
    @Test
    public void testAddInstrumentLeg() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getInstrumentLegs());
        comp.setNoLegs(new Integer(2));
        assertEquals(2, comp.getInstrumentLegs().length);
        for (int i = 0; i < comp.getInstrumentLegs().length; i++) {
            InstrumentLeg group = comp.getInstrumentLegs()[i];
            group.setLegSymbol("MOT " + i);
        }
        comp.addInstrumentLeg();
        assertEquals(3, comp.getInstrumentLegs().length);
        comp.getInstrumentLegs()[2].setLegSymbol("MOT 2");
        int i = 0;
        for (InstrumentLeg group : comp.getInstrumentLegs()) {
            assertEquals("MOT " + i, group.getLegSymbol());
            i++;
        }
        assertEquals(3, comp.getNoLegs().intValue());
    }

    /*
     * Test of deleteInstrumentLeg method, of class EmailMsg.
     */
    @Test
    public void testDeleteInstrumentLeg() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getInstrumentLegs());
        comp.setNoLegs(new Integer(3));
        for (int i = 0; i < comp.getInstrumentLegs().length; i++) {
            InstrumentLeg group = comp.getInstrumentLegs()[i];
            group.setLegSymbol("MOT " + i);
        }
        assertEquals(3, comp.getInstrumentLegs().length);
        comp.deleteInstrumentLeg(1);
        assertEquals(2, comp.getInstrumentLegs().length);
        assertEquals(2, comp.getNoLegs().intValue());
        assertEquals("MOT 2", comp.getInstrumentLegs()[1].getLegSymbol());
    }

    /*
     * Test of clearInstrumentLegs method, of class EmailMsg.
     */
    @Test
    public void testClearInstrumentLegs() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getInstrumentLegs());
        comp.setNoLegs(new Integer(3));
        for (int i = 0; i < comp.getInstrumentLegs().length; i++) {
            InstrumentLeg group = comp.getInstrumentLegs()[i];
            group.setLegSymbol("MOT " + i);
        }
        assertEquals(3, comp.getInstrumentLegs().length);
        assertEquals(3, comp.getNoLegs().intValue());
        int i = 0;
        for (InstrumentLeg group : comp.getInstrumentLegs()) {
            assertEquals("MOT " + i, group.getLegSymbol());
            i++;
        }
        comp.clearInstrumentLegs();
        assertNull(comp.getNoLegs());
        assertNull(comp.getInstrumentLegs());
    }

    /*
     * Test of setNoUnderlyings method, of class EmailMsg.
     */
    @Test
    public void testSetNoUnderlyings() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getUnderlyingInstruments());
        comp.setNoUnderlyings(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingInstruments().length; i++) {
            UnderlyingInstrument group = comp.getUnderlyingInstruments()[i];
            group.setUnderlyingSymbol("MOT " + i);
        }
        assertEquals(3, comp.getUnderlyingInstruments().length);
        int i = 0;
        for (UnderlyingInstrument group : comp.getUnderlyingInstruments()) {
            assertEquals("MOT " + i, group.getUnderlyingSymbol());
            i++;
        }
    }

    /*
     * Test of addUnderlyingInstrument method, of class EmailMsg.
     */
    @Test
    public void testAddUnderlyingInstrument() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getUnderlyingInstruments());
        comp.setNoUnderlyings(new Integer(2));
        assertEquals(2, comp.getUnderlyingInstruments().length);
        for (int i = 0; i < comp.getUnderlyingInstruments().length; i++) {
            UnderlyingInstrument group = comp.getUnderlyingInstruments()[i];
            group.setUnderlyingSymbol("MOT " + i);
        }
        comp.addUnderlyingInstrument();
        assertEquals(3, comp.getUnderlyingInstruments().length);
        comp.getUnderlyingInstruments()[2].setUnderlyingSymbol("MOT 2");
        int i = 0;
        for (UnderlyingInstrument group : comp.getUnderlyingInstruments()) {
            assertEquals("MOT " + i, group.getUnderlyingSymbol());
            i++;
        }
        assertEquals(3, comp.getNoUnderlyings().intValue());
    }

    /*
     * Test of deleteUnderlyingInstrument method, of class EmailMsg.
     */
    @Test
    public void testDeleteUnderlyingInstrument() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getUnderlyingInstruments());
        comp.setNoUnderlyings(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingInstruments().length; i++) {
            UnderlyingInstrument group = comp.getUnderlyingInstruments()[i];
            group.setUnderlyingSymbol("MOT " + i);
        }
        assertEquals(3, comp.getUnderlyingInstruments().length);
        comp.deleteUnderlyingInstrument(1);
        assertEquals(2, comp.getUnderlyingInstruments().length);
        assertEquals(2, comp.getNoUnderlyings().intValue());
        assertEquals("MOT 2", comp.getUnderlyingInstruments()[1].getUnderlyingSymbol());
    }

    /*
     * Test of clearUnderlyingInstruments method, of class EmailMsg.
     */
    @Test
    public void testClearUnderlyingInstruments() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        assertNull(comp.getUnderlyingInstruments());
        comp.setNoUnderlyings(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingInstruments().length; i++) {
            UnderlyingInstrument group = comp.getUnderlyingInstruments()[i];
            group.setUnderlyingSymbol("MOT " + i);
        }
        assertEquals(3, comp.getUnderlyingInstruments().length);
        assertEquals(3, comp.getNoUnderlyings().intValue());
        int i = 0;
        for (UnderlyingInstrument group : comp.getUnderlyingInstruments()) {
            assertEquals("MOT " + i, group.getUnderlyingSymbol());
            i++;
        }
        comp.clearUnderlyingInstruments();
        assertNull(comp.getNoUnderlyings());
        assertNull(comp.getUnderlyingInstruments());
    }

    /*
     * Test of setNoLinesOfText method, of class EmailMsg.
     */
    @Test
    public void testSetNoLinesOfText() throws Exception {
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
        EmailMsg comp = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getRelatedSymGroups();
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
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
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
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
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


    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [EmailMsg] message version [5.0SP1].", ex.getMessage());
    }
}
