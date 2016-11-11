/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg43TestData.java
 *
 * $Id: NewsMsg43TestData.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.type.RoutingType;
import net.hades.fix.message.type.Urgency;

/**
 * Test utility for NewsMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:34:14 AM
 */
public class NewsMsg43TestData extends MsgTest {

    private static final NewsMsg43TestData INSTANCE;

    static {
        INSTANCE = new NewsMsg43TestData();
    }

    public static NewsMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.News msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setUtcTimeStamp(quickfix.field.OrigTime.FIELD, new Date(), true);
        msg.setString(quickfix.field.Urgency.FIELD, Urgency.Normal.getValue());
        msg.setString(quickfix.field.Headline.FIELD, "Headline");
        byte[] encHeadlineText = new byte[] {(byte) 57, (byte) 58, (byte) 70, (byte) 52,
            (byte) 64, (byte) 82};
        msg.setInt(quickfix.field.EncodedHeadlineLen.FIELD, 6);
        msg.setString(quickfix.field.EncodedHeadline.FIELD, new String(encHeadlineText, DEFAULT_CHARACTER_SET));
        // RoutingIDGroup
        quickfix.fix43.News.NoRoutingIDs grpr1 = new quickfix.fix43.News.NoRoutingIDs();
        grpr1.setString(quickfix.field.RoutingID.FIELD, "route id 1");
        grpr1.setInt(quickfix.field.RoutingType.FIELD, RoutingType.BlockFirm.getValue());
        msg.addGroup(grpr1);
        quickfix.fix43.News.NoRoutingIDs grpr2 = new quickfix.fix43.News.NoRoutingIDs();
        grpr2.setString(quickfix.field.RoutingID.FIELD, "route id 2");
        grpr2.setInt(quickfix.field.RoutingType.FIELD, RoutingType.BlockList.getValue());
        msg.addGroup(grpr2);
        // Instrument
        quickfix.fix43.News.NoRelatedSym grps1 = new quickfix.fix43.News.NoRelatedSym();
        quickfix.fix43.component.Instrument instr1 = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr1);
        grps1.set(instr1);
        msg.addGroup(grps1);
        quickfix.fix43.News.NoRelatedSym grps2 = new quickfix.fix43.News.NoRelatedSym();
        quickfix.fix43.component.Instrument instr2 = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr2);
        grps2.set(instr2);
        msg.addGroup(grps2);
        // LinesOfTextGroup
        msg.setInt(quickfix.field.LinesOfText.FIELD, 2);
        quickfix.fix43.News.LinesOfText grplot1 = new quickfix.fix43.News.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        grplot1.setInt(quickfix.field.EncodedTextLen.FIELD, 3);
        byte[] encTextLine1 = new byte[] {(byte) 57, (byte) 58, (byte) 70};
        grplot1.setString(quickfix.field.EncodedText.FIELD, new String(encTextLine1, DEFAULT_CHARACTER_SET));
        msg.addGroup(grplot1);
        quickfix.fix43.News.LinesOfText grplot2 = new quickfix.fix43.News.LinesOfText();
        grplot2.setString(quickfix.field.Text.FIELD, "TEXT 2");
        grplot2.setInt(quickfix.field.EncodedTextLen.FIELD, 3);
        byte[] encTextLine2 = new byte[] {(byte) 22, (byte) 44, (byte) 55};
        grplot2.setString(quickfix.field.EncodedText.FIELD, new String(encTextLine2, DEFAULT_CHARACTER_SET));
        msg.addGroup(grplot2);
        msg.setString(quickfix.field.URLLink.FIELD, "http://www.reuters.com");
        msg.setInt(quickfix.field.RawDataLength.FIELD, 6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setString(quickfix.field.RawData.FIELD, new String(encText));
    }

    public void populate(NewsMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setOrigTime(new Date());
        msg.setUrgency(Urgency.Flash);
        msg.setHeadline("Main Headline");
        byte[] encHeadlineText = new byte[] {(byte) 57, (byte) 58, (byte) 70, (byte) 52,
            (byte) 64, (byte) 82};
        msg.setEncodedHeadlineLen(new Integer(6));
        msg.setEncodedHeadline(encHeadlineText);
        // RoutingIDGroup
        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("route id 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.BlockFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("route id 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockList);
        // Instrument
        msg.setNoRelatedSyms(new Integer(2));
        Instrument43TestData.getInstance().populate(msg.getInstruments()[0]);
        Instrument43TestData.getInstance().populate(msg.getInstruments()[1]);
        // LinesOfTextGroup
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[0].setEncodedTextLen(new Integer(3));
        byte[] encTextLine1 = new byte[] {(byte) 57, (byte) 58, (byte) 70};
        msg.getLinesOfTextGroups()[0].setEncodedText(encTextLine1);
        msg.getLinesOfTextGroups()[1].setText("line of text 2");
        msg.getLinesOfTextGroups()[1].setEncodedTextLen(new Integer(3));
        byte[] encTextLine2 = new byte[] {(byte) 22, (byte) 44, (byte) 55};
        msg.getLinesOfTextGroups()[1].setEncodedText(encTextLine2);
        msg.setUrlLink("http://somesite.com");
        msg.setRawDataLength(new Integer(6));
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setRawData(encText);
    }

    public void check(quickfix.fix43.News expected, NewsMsg actual) throws Exception {
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.OrigTime.FIELD), actual.getOrigTime(), false);
        assertEquals(expected.getString(quickfix.field.Urgency.FIELD), actual.getUrgency().getValue());
        assertEquals(expected.getString(quickfix.field.Headline.FIELD), actual.getHeadline());
        assertEquals(expected.getInt(quickfix.field.EncodedHeadlineLen.FIELD), actual.getEncodedHeadlineLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedHeadline.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedHeadline());
        // RoutingIDGroup check
        assertEquals(expected.getInt(quickfix.field.NoRoutingIDs.FIELD), actual.getNoRoutingIDs().intValue());
        quickfix.fix43.News.NoRoutingIDs grpr1 = new quickfix.fix43.News.NoRoutingIDs();
        expected.getGroup(1, grpr1);
        assertEquals(grpr1.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(grpr1.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[0].getRoutingID());
        quickfix.fix43.News.NoRoutingIDs grpr2 = new quickfix.fix43.News.NoRoutingIDs();
        expected.getGroup(2, grpr2);
        assertEquals(grpr2.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(grpr2.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[1].getRoutingID());
        // Instrument
        quickfix.fix43.News.NoRelatedSym grps1 = new quickfix.fix43.News.NoRelatedSym();
        quickfix.fix43.component.Instrument instr1 = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr1);
        grps1.set(instr1);
        expected.addGroup(grps1);
        quickfix.fix43.News.NoRelatedSym grps2 = new quickfix.fix43.News.NoRelatedSym();
        quickfix.fix43.component.Instrument instr2 = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr2);
        grps2.set(instr2);
        expected.addGroup(grps2);
        // LinesOfTextGroup check
        assertEquals(expected.getInt(quickfix.field.LinesOfText.FIELD), actual.getNoLinesOfText().intValue());
        quickfix.fix43.News.LinesOfText grplot1 = new quickfix.fix43.News.LinesOfText();
        expected.getGroup(1, grplot1);
        assertEquals(grplot1.getString(quickfix.field.Text.FIELD), actual.getLinesOfTextGroups()[0].getText());
        assertEquals(grplot1.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getLinesOfTextGroups()[0].getEncodedTextLen().intValue());
        assertArrayEquals(grplot1.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getLinesOfTextGroups()[0].getEncodedText());
        quickfix.fix43.News.LinesOfText grplot2 = new quickfix.fix43.News.LinesOfText();
        expected.getGroup(2, grplot2);
        assertEquals(grplot2.getString(quickfix.field.Text.FIELD), actual.getLinesOfTextGroups()[1].getText());
        assertEquals(grplot2.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getLinesOfTextGroups()[1].getEncodedTextLen().intValue());
        assertArrayEquals(grplot2.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getLinesOfTextGroups()[1].getEncodedText());
        assertEquals(expected.getString(quickfix.field.URLLink.FIELD), actual.getUrlLink());
        assertEquals(expected.getInt(quickfix.field.RawDataLength.FIELD), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getString(quickfix.field.RawData.FIELD).getBytes(), actual.getRawData());

    }

    public void check(NewsMsg expected, quickfix.fix43.News actual) throws Exception {
        assertTimestampEquals(expected.getOrigTime(), actual.getUtcTimeStamp(quickfix.field.OrigTime.FIELD));
        assertEquals(expected.getUrgency().getValue(), actual.getString(quickfix.field.Urgency.FIELD));
        assertEquals(expected.getHeadline(), actual.getString(quickfix.field.Headline.FIELD));
        assertEquals(expected.getEncodedHeadlineLen().intValue(), actual.getInt(quickfix.field.EncodedHeadlineLen.FIELD));
        assertArrayEquals(expected.getEncodedHeadline(), actual.getString(quickfix.field.EncodedHeadline.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        // RoutingIDGroup check
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getInt(quickfix.field.NoRoutingIDs.FIELD));
        quickfix.fix43.News.NoRoutingIDs grpr1 = new quickfix.fix43.News.NoRoutingIDs();
        actual.getGroup(1, grpr1);
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), grpr1.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), grpr1.getRoutingID().getValue());
        quickfix.fix43.News.NoRoutingIDs grpr2 = new quickfix.fix43.News.NoRoutingIDs();
        actual.getGroup(2, grpr2);
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), grpr2.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), grpr2.getRoutingID().getValue());
        // Instrument check
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        quickfix.fix43.News.NoRelatedSym grps1 = new quickfix.fix43.News.NoRelatedSym();
        actual.getGroup(1, grps1);
        Instrument43TestData.getInstance().check(expected.getInstruments()[0], grps1.getInstrument());
        quickfix.fix43.News.NoRelatedSym grps2 = new quickfix.fix43.News.NoRelatedSym();
        actual.getGroup(2, grps2);
        Instrument43TestData.getInstance().check(expected.getInstruments()[1], grps2.getInstrument());
        // LinesOfTextGroup
        assertEquals(expected.getNoLinesOfText().intValue(), actual.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix43.News.LinesOfText grplot1 = new quickfix.fix43.News.LinesOfText();
        actual.getGroup(1, grplot1);
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), grplot1.getText().getValue());
        assertEquals(expected.getLinesOfTextGroups()[0].getEncodedTextLen().intValue(), grplot1.getEncodedTextLen().getValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[0].getEncodedText(), grplot1.getEncodedText().getValue().getBytes(DEFAULT_CHARACTER_SET));
        quickfix.fix43.News.LinesOfText grplot2 = new quickfix.fix43.News.LinesOfText();
        actual.getGroup(2, grplot2);
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), grplot2.getText().getValue());
        assertEquals(expected.getLinesOfTextGroups()[1].getEncodedTextLen().intValue(), grplot2.getEncodedTextLen().getValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[1].getEncodedText(), grplot2.getEncodedText().getValue().getBytes(DEFAULT_CHARACTER_SET));

        assertEquals(expected.getUrlLink(), actual.getString(quickfix.field.URLLink.FIELD));
        assertEquals(expected.getRawDataLength().intValue(), actual.getInt(quickfix.field.RawDataLength.FIELD));
        assertArrayEquals(expected.getRawData(), actual.getString(quickfix.field.RawData.FIELD).getBytes(DEFAULT_CHARACTER_SET));
    }

    public void check(NewsMsg expected, NewsMsg actual) throws Exception {
        assertUTCTimestampEquals(expected.getOrigTime(), actual.getOrigTime(), false);
        assertEquals(expected.getUrgency().getValue(), actual.getUrgency().getValue());
        assertEquals(expected.getHeadline(), actual.getHeadline());
        assertEquals(expected.getEncodedHeadlineLen().intValue(), actual.getEncodedHeadlineLen().intValue());
        assertArrayEquals(expected.getEncodedHeadline(), actual.getEncodedHeadline());
        // RoutingIDGroup check
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getNoRoutingIDs().intValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), actual.getRoutingIDGroups()[0].getRoutingID());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), actual.getRoutingIDGroups()[1].getRoutingID());
        // Instrument check
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getNoRelatedSyms().intValue());
        Instrument43TestData.getInstance().check(expected.getInstruments()[0], actual.getInstruments()[0]);
        Instrument43TestData.getInstance().check(expected.getInstruments()[1], actual.getInstruments()[1]);

        assertEquals(expected.getNoLinesOfText().intValue(), actual.getNoLinesOfText().intValue());
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), actual.getLinesOfTextGroups()[0].getText());
        assertEquals(expected.getLinesOfTextGroups()[0].getEncodedTextLen().intValue(), actual.getLinesOfTextGroups()[0].getEncodedTextLen().intValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[0].getEncodedText(), actual.getLinesOfTextGroups()[0].getEncodedText());
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), actual.getLinesOfTextGroups()[1].getText());
        assertEquals(expected.getLinesOfTextGroups()[1].getEncodedTextLen().intValue(), actual.getLinesOfTextGroups()[1].getEncodedTextLen().intValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[1].getEncodedText(), actual.getLinesOfTextGroups()[1].getEncodedText());
        assertEquals(expected.getUrlLink(), actual.getUrlLink());
        assertEquals(expected.getRawDataLength().intValue(), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getRawData(), actual.getRawData());
    }
}
