/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg40TestData.java
 *
 * $Id: NewsMsg40TestData.java,v 1.1 2009-07-06 03:19:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.type.Urgency;

/**
 * Test utility for NewsMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:34:14 AM
 */
public class NewsMsg40TestData extends MsgTest {

    private static final NewsMsg40TestData INSTANCE;

    static {
        INSTANCE = new NewsMsg40TestData();
    }

    public static NewsMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix40.News msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setUtcTimeStamp(quickfix.field.OrigTime.FIELD, new Date(), true);
        msg.setString(quickfix.field.Urgency.FIELD, Urgency.Normal.getValue());
        msg.setString(quickfix.field.RelatdSym.FIELD, "SUN");
        msg.setInt(quickfix.field.LinesOfText.FIELD, 2);
        quickfix.fix40.News.LinesOfText grplot1 = new quickfix.fix40.News.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        msg.addGroup(grplot1);
        quickfix.fix40.News.LinesOfText grplot2 = new quickfix.fix40.News.LinesOfText();
        grplot2.setString(quickfix.field.Text.FIELD, "TEXT 2");
        msg.addGroup(grplot2);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setString(quickfix.field.RawData.FIELD, new String(encText));

    }

    public void populate(NewsMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setOrigTime(new Date());
        msg.setUrgency(Urgency.Flash);
        msg.addRelatedSymGroup();
        msg.getRelatedSymGroups()[0].setRelatedSym("MOT");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");
        msg.setRawDataLength(new Integer(6));
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setRawData(encText);
    }

    public void check(quickfix.fix40.News expected, NewsMsg actual) throws Exception {
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.OrigTime.FIELD), actual.getOrigTime(), false);
        assertEquals(expected.getString(quickfix.field.Urgency.FIELD), actual.getUrgency().getValue());
        assertEquals(expected.getString(quickfix.field.RelatdSym.FIELD), actual.getRelatedSymGroups()[0].getRelatedSym());
        assertEquals(expected.getInt(quickfix.field.LinesOfText.FIELD), actual.getNoLinesOfText().intValue());
        assertEquals("TEXT 1", actual.getLinesOfTextGroups()[0].getText());
        assertEquals("TEXT 2", actual.getLinesOfTextGroups()[1].getText());
        assertEquals(expected.getInt(quickfix.field.RawDataLength.FIELD), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getString(quickfix.field.RawData.FIELD).getBytes(), actual.getRawData());

    }

    public void check(NewsMsg expected, quickfix.fix40.News actual) throws Exception {
        assertTimestampEquals(expected.getOrigTime(), actual.getUtcTimeStamp(quickfix.field.OrigTime.FIELD));
        assertEquals(expected.getUrgency().getValue(), actual.getString(quickfix.field.Urgency.FIELD));
        assertEquals(expected.getRelatedSymGroups()[0].getRelatedSym(), actual.getString(quickfix.field.RelatdSym.FIELD));
        assertEquals(expected.getNoLinesOfText().intValue(), actual.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix40.News.LinesOfText grplot1 = new quickfix.fix40.News.LinesOfText();
        actual.getGroup(1, grplot1);
        quickfix.field.Text flot1 = new quickfix.field.Text();
        grplot1.get(flot1);
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), flot1.getValue());
        quickfix.fix40.News.LinesOfText grplot2 = new quickfix.fix40.News.LinesOfText();
        actual.getGroup(2, grplot2);
        quickfix.field.Text flot2 = new quickfix.field.Text();
        grplot2.get(flot2);
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), flot2.getValue());
        assertEquals(expected.getRawDataLength().intValue(), actual.getInt(quickfix.field.RawDataLength.FIELD));
        assertArrayEquals(expected.getRawData(), actual.getString(quickfix.field.RawData.FIELD).getBytes());
    }

    public void check(NewsMsg expected, NewsMsg actual) {
        assertUTCTimestampEquals(expected.getOrigTime(), actual.getOrigTime(), false);
        assertEquals(expected.getUrgency().getValue(), actual.getUrgency().getValue());
        assertEquals(expected.getRelatedSymGroups()[0].getRelatedSym(), actual.getRelatedSymGroups()[0].getRelatedSym());
        assertEquals(expected.getNoLinesOfText().intValue(), actual.getNoLinesOfText().intValue());
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), actual.getLinesOfTextGroups()[0].getText());
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), actual.getLinesOfTextGroups()[1].getText());
        assertEquals(expected.getRawDataLength().intValue(), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getRawData(), actual.getRawData());
    }
}
