/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg41TestData.java
 *
 * $Id: EmailMsg41TestData.java,v 1.1 2009-07-06 03:19:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v41.RelatedSymbolGroup41TestData;
import net.hades.fix.message.type.EmailType;

/**
 * Test utility for EmailMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 10:38:10 AM
 */
public class EmailMsg41TestData extends MsgTest {

    private static final EmailMsg41TestData INSTANCE;

    static {
        INSTANCE = new EmailMsg41TestData();
    }

    public static EmailMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix41.Email msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setString(quickfix.field.EmailThreadID.FIELD, "thread no 1");
        msg.setChar(quickfix.field.EmailType.FIELD, '0');
        msg.setUtcTimeStamp(quickfix.field.OrigTime.FIELD, new Date(), true);
        msg.setString(quickfix.field.Subject.FIELD, "Email subject");
        // RelatedSymbolGroup
        quickfix.fix41.Email.NoRelatedSym grps1 = new quickfix.fix41.Email.NoRelatedSym();
        RelatedSymbolGroup41TestData.getInstance().populate1(grps1);
        msg.addGroup(grps1);
        quickfix.fix41.Email.NoRelatedSym grps2 = new quickfix.fix41.Email.NoRelatedSym();
        RelatedSymbolGroup41TestData.getInstance().populate1(grps2);
        msg.addGroup(grps2);

        msg.setString(quickfix.field.OrderID.FIELD, "65466355354");
        msg.setString(quickfix.field.ClOrdID.FIELD, "AAA7766555");
        msg.setInt(quickfix.field.LinesOfText.FIELD, 2);
        quickfix.fix41.News.LinesOfText grplot1 = new quickfix.fix41.News.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        msg.addGroup(grplot1);
        quickfix.fix41.News.LinesOfText grplot2 = new quickfix.fix41.News.LinesOfText();
        grplot2.setString(quickfix.field.Text.FIELD, "TEXT 2");
        msg.addGroup(grplot2);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setString(quickfix.field.RawData.FIELD, new String(encText));
    }

    public void populate(EmailMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        msg.setEmailThreadID("Thread1");
        msg.setEmailType(EmailType.New);
        msg.setSubject("Subject text");
        msg.setOrigTime(new Date());
        // RelatedSymbolGroup
        msg.setNoRelatedSyms(new Integer(2));
        RelatedSymbolGroup41TestData.getInstance().populate1(msg.getRelatedSymGroups()[0]);
        RelatedSymbolGroup41TestData.getInstance().populate2(msg.getRelatedSymGroups()[1]);

        msg.setOrderID("C12345678");
        msg.setClOrdID("ASX657433");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");
        msg.setRawDataLength(new Integer(6));
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setRawData(encText);
    }

    public void check(quickfix.fix41.Email expected, EmailMsg actual) throws Exception {
        assertEquals(expected.getChar(quickfix.field.EmailType.FIELD), actual.getEmailType().getValue());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.OrigTime.FIELD), actual.getOrigTime(), false);
         // RelatedSymbolGroup check
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSyms().intValue());
        quickfix.fix41.Email.NoRelatedSym grps1 = new quickfix.fix41.Email.NoRelatedSym();
        expected.getGroup(1, grps1);
        RelatedSymbolGroup41TestData.getInstance().check(grps1, actual.getRelatedSymGroups()[0]);
        quickfix.fix41.Email.NoRelatedSym grps2 = new quickfix.fix41.Email.NoRelatedSym();
        expected.getGroup(2, grps2);
        RelatedSymbolGroup41TestData.getInstance().check(grps2, actual.getRelatedSymGroups()[1]);

        assertEquals(expected.getString(quickfix.field.OrderID.FIELD), actual.getOrderID());
        assertEquals(expected.getString(quickfix.field.ClOrdID.FIELD), actual.getClOrdID());
        assertEquals(expected.getInt(quickfix.field.LinesOfText.FIELD), actual.getNoLinesOfText().intValue());
        assertEquals("TEXT 1", actual.getLinesOfTextGroups()[0].getText());
        assertEquals("TEXT 2", actual.getLinesOfTextGroups()[1].getText());
        assertEquals(expected.getInt(quickfix.field.RawDataLength.FIELD), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getString(quickfix.field.RawData.FIELD).getBytes(), actual.getRawData());

    }

    public void check(EmailMsg expected, quickfix.fix41.Email actual) throws Exception {
        assertEquals(expected.getEmailThreadID(), actual.getString(quickfix.field.EmailThreadID.FIELD));
        assertEquals(expected.getEmailType().getValue(), actual.getChar(quickfix.field.EmailType.FIELD));
        assertTimestampEquals(expected.getOrigTime(), actual.getUtcTimeStamp(quickfix.field.OrigTime.FIELD));
        assertEquals(expected.getSubject(), actual.getString(quickfix.field.Subject.FIELD));
        // RelatedSymbolGroup check
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        quickfix.fix41.Email.NoRelatedSym grps1 = new quickfix.fix41.Email.NoRelatedSym();
        actual.getGroup(1, grps1);
        RelatedSymbolGroup41TestData.getInstance().check(expected.getRelatedSymGroups()[0], grps1);
        quickfix.fix41.Email.NoRelatedSym grps2 = new quickfix.fix41.Email.NoRelatedSym();
        actual.getGroup(2, grps2);
        RelatedSymbolGroup41TestData.getInstance().check(expected.getRelatedSymGroups()[1], grps2);

        assertEquals(expected.getOrderID(), actual.getString(quickfix.field.OrderID.FIELD));
        assertEquals(expected.getClOrdID(), actual.getString(quickfix.field.ClOrdID.FIELD));
        assertEquals(expected.getNoLinesOfText().intValue(), actual.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix41.News.LinesOfText grplot1 = new quickfix.fix41.News.LinesOfText();
        actual.getGroup(1, grplot1);
        quickfix.field.Text flot1 = new quickfix.field.Text();
        grplot1.get(flot1);
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), flot1.getValue());
        quickfix.fix41.News.LinesOfText grplot2 = new quickfix.fix41.News.LinesOfText();
        actual.getGroup(2, grplot2);
        quickfix.field.Text flot2 = new quickfix.field.Text();
        grplot2.get(flot2);
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), flot2.getValue());
        assertEquals(expected.getRawDataLength().intValue(), actual.getInt(quickfix.field.RawDataLength.FIELD));
        assertArrayEquals(expected.getRawData(), actual.getString(quickfix.field.RawData.FIELD).getBytes());
    }

    public void check(EmailMsg expected, EmailMsg actual) throws Exception {
        assertEquals(expected.getEmailThreadID(), actual.getEmailThreadID());
        assertEquals(expected.getEmailType().getValue(), actual.getEmailType().getValue());
        assertUTCTimestampEquals(expected.getOrigTime(), actual.getOrigTime(), false);
        assertEquals(expected.getSubject(), actual.getSubject());
        // RelatedSymbolGroup check
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getNoRelatedSyms().intValue());
        RelatedSymbolGroup41TestData.getInstance().check(expected.getRelatedSymGroups()[0], actual.getRelatedSymGroups()[0]);
        RelatedSymbolGroup41TestData.getInstance().check(expected.getRelatedSymGroups()[1], expected.getRelatedSymGroups()[1]);

        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getNoLinesOfText().intValue(), actual.getNoLinesOfText().intValue());
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), actual.getLinesOfTextGroups()[0].getText());
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), actual.getLinesOfTextGroups()[1].getText());
        assertEquals(expected.getRawDataLength().intValue(), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getRawData(), actual.getRawData());
    }
}
