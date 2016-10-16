/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg44Test.java
 *
 * $Id: NewsMsg44Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.impl.v44.data.NewsMsg44TestData;
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
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test suite for FIX 4.4 NewsMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/03/2009, 7:38:11 PM
 */
public class NewsMsg44Test extends MsgTest {

    private DataDictionary dictionary;

    public NewsMsg44Test() {
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
        dictionary = getQF44DataDictionary();
        NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setHeadline("Main Headline");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);

        assertEquals(msg.getHeadline(), qfMsg.getString(quickfix.field.Headline.FIELD));
        assertEquals(msg.getNoLinesOfText().intValue(), qfMsg.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix44.News.LinesOfText grplot1 = new quickfix.fix44.News.LinesOfText();
        qfMsg.getGroup(1, grplot1);
        quickfix.field.Text flot1 = new quickfix.field.Text();
        grplot1.get(flot1);
        assertEquals(msg.getLinesOfTextGroups()[0].getText(), flot1.getValue());
        quickfix.fix44.News.LinesOfText grplot2 = new quickfix.fix44.News.LinesOfText();
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
        dictionary = getQF44DataDictionary();
        NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
        NewsMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix44.News qfMsg = new quickfix.fix44.News();
        qfMsg.fromString(encoded, dictionary, true);
        NewsMsg44TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class NewsMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.News msg = new quickfix.fix44.News();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.Headline.FIELD, "Headline");
        quickfix.fix44.News.LinesOfText grplot1 = new quickfix.fix44.News.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        msg.addGroup(grplot1);
        quickfix.fix44.News.LinesOfText grplot2 = new quickfix.fix44.News.LinesOfText();
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
        dictionary = getQF44DataDictionary();
        quickfix.fix44.News msg = new quickfix.fix44.News();
        NewsMsg44TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        NewsMsg44TestData.getInstance().check(msg, dmsg);
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
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
            NewsMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            NewsMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /*
     * Test of setNoRelatedSyms method, of class NewsMsg.
     */
    @Test
    public void testSetNoRelatedSyms() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of addInstrument method, of class NewsMsg.
     */
    @Test
    public void testAddInstrument() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of deleteInstrument method, of class NewsMsg.
     */
    @Test
    public void testDeleteInstrument() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of clearInstruments method, of class NewsMsg.
     */
    @Test
    public void testClearInstruments() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of setNoLinesOfText method, of class NewsMsg40.
     */
    @Test
    public void testSetNoLinesOfText() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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

    /*
     * Test of setNoRoutingIDs method, of class NewsMsg.
     */
    @Test
    public void testSetNoRoutingIDs() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of addRoutingIDGroup method, of class NewsMsg.
     */
    @Test
    public void testAddRoutingIDGroup() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of deleteRoutingIDGroup method, of class NewsMsg.
     */
    @Test
    public void testDeleteRoutingIDGroup() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of clearRoutingIDGroups method, of class NewsMsg.
     */
    @Test
    public void testClearRoutingIDGroups() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of setNoLegs method, of class NewsMsg.
     */
    @Test
    public void testSetNoLegs() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of addInstrumentLeg method, of class NewsMsg.
     */
    @Test
    public void testAddInstrumentLeg() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of deleteInstrumentLeg method, of class NewsMsg.
     */
    @Test
    public void testDeleteInstrumentLeg() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of clearInstrumentLegs method, of class NewsMsg.
     */
    @Test
    public void testClearInstrumentLegs() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of setNoUnderlyings method, of class NewsMsg.
     */
    @Test
    public void testSetNoUnderlyings() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of addUnderlyingInstrument method, of class NewsMsg.
     */
    @Test
    public void testAddUnderlyingInstrument() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of deleteUnderlyingInstrument method, of class NewsMsg.
     */
    @Test
    public void testDeleteUnderlyingInstrument() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of clearUnderlyingInstruments method, of class NewsMsg.
     */
    @Test
    public void testClearUnderlyingInstruments() throws Exception {
        NewsMsg comp = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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

    /**
     * Test of encode getter method, of class NewsMsg 4.2 with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        NewsMsg msg = null;
        try {
            msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
            msg.getRelatedSymGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class NewsMsg 4.2 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        NewsMsg msg = null;
        try {
            msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
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
     * Test of encode method, of class NewsMsg with missing NoLinesOfText data.
     */
    @Test
    public void testEncodeMissingReqNoLinesOfText() {
        System.out.println("-->testEncodeMissingReqNoLinesOfText");
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            dictionary = getQF44DataDictionary();
            quickfix.fix44.News msg = new quickfix.fix44.News();
            TestUtils.populateQuickFIX44HeaderAll(msg);
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
        assertEquals("This tag is not supported in [NewsMsg] message version [4.4].", ex.getMessage());
    }
}
