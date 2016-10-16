/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg44TestData.java
 *
 * $Id: EmailMsg44TestData.java,v 1.1 2009-07-06 03:19:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.RoutingType;

/**
 * Test utility for EmailMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 10:38:10 AM
 */
public class EmailMsg44TestData extends MsgTest {

    private static final EmailMsg44TestData INSTANCE;

    static {
        INSTANCE = new EmailMsg44TestData();
    }

    public static EmailMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.Email msg) throws Exception {
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.EmailThreadID.FIELD, "thread no 1");
        msg.setChar(quickfix.field.EmailType.FIELD, '0');
        msg.setUtcTimeStamp(quickfix.field.OrigTime.FIELD, new Date(), true);
        msg.setString(quickfix.field.Subject.FIELD, "Email subject");
        byte[] encSubjectText = new byte[] {(byte) 57, (byte) 58, (byte) 70, (byte) 52,
            (byte) 64, (byte) 82};
        msg.setInt(quickfix.field.EncodedSubjectLen.FIELD, 6);
        msg.setString(quickfix.field.EncodedSubject.FIELD, new String(encSubjectText, DEFAULT_CHARACTER_SET));
        // RoutingIDGroup
        quickfix.fix44.Email.NoRoutingIDs grpr1 = new quickfix.fix44.Email.NoRoutingIDs();
        grpr1.setString(quickfix.field.RoutingID.FIELD, "route id 1");
        grpr1.setInt(quickfix.field.RoutingType.FIELD, RoutingType.BlockFirm.getValue());
        msg.addGroup(grpr1);
        quickfix.fix44.Email.NoRoutingIDs grpr2 = new quickfix.fix44.Email.NoRoutingIDs();
        grpr2.setString(quickfix.field.RoutingID.FIELD, "route id 2");
        grpr2.setInt(quickfix.field.RoutingType.FIELD, RoutingType.BlockList.getValue());
        msg.addGroup(grpr2);
        // Instrument
        quickfix.fix44.Email.NoRelatedSym grps1 = new quickfix.fix44.Email.NoRelatedSym();
        quickfix.fix44.component.Instrument instr1 = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr1);
        grps1.set(instr1);
        msg.addGroup(grps1);
        quickfix.fix44.Email.NoRelatedSym grps2 = new quickfix.fix44.Email.NoRelatedSym();
        quickfix.fix44.component.Instrument instr2 = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr2);
        grps2.set(instr2);
        msg.addGroup(grps2);
        // InstrumentLeg
        quickfix.fix44.Email.NoLegs grpi1 = new quickfix.fix44.Email.NoLegs();
        quickfix.fix44.component.InstrumentLeg instrleg1 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(instrleg1);
        grpi1.set(instrleg1);
        msg.addGroup(grpi1);
        quickfix.fix44.Email.NoLegs grpi2 = new quickfix.fix44.Email.NoLegs();
        quickfix.fix44.component.InstrumentLeg instrleg2 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate2(instrleg2);
        grpi2.set(instrleg2);
        msg.addGroup(grpi2);
        // UnderlyingInstrument
        quickfix.fix44.Email.NoUnderlyings grpu1 = new quickfix.fix44.Email.NoUnderlyings();
        quickfix.fix44.component.UnderlyingInstrument undinst1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(undinst1);
        grpu1.set(undinst1);
        grpu1.set(undinst1.getUnderlyingStipulations());
        msg.addGroup(grpu1);
        quickfix.fix44.Email.NoUnderlyings grpu2 = new quickfix.fix44.Email.NoUnderlyings();
        quickfix.fix44.component.UnderlyingInstrument undinst2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(undinst2);
        grpu2.set(undinst2);
        grpu2.set(undinst2.getUnderlyingStipulations());
        msg.addGroup(grpu2);
        

        msg.setString(quickfix.field.OrderID.FIELD, "65466355354");
        msg.setString(quickfix.field.ClOrdID.FIELD, "AAA7766555");
        msg.setInt(quickfix.field.LinesOfText.FIELD, 2);
        quickfix.fix44.Email.LinesOfText grplot1 = new quickfix.fix44.Email.LinesOfText();
        grplot1.setString(quickfix.field.Text.FIELD, "TEXT 1");
        grplot1.setInt(quickfix.field.EncodedTextLen.FIELD, 4);
        grplot1.setString(quickfix.field.EncodedText.FIELD,
            new String(new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50}));
        msg.addGroup(grplot1);
        quickfix.fix44.Email.LinesOfText grplot2 = new quickfix.fix44.Email.LinesOfText();
        grplot2.setString(quickfix.field.Text.FIELD, "TEXT 2");
        grplot2.setInt(quickfix.field.EncodedTextLen.FIELD, 4);
        grplot2.setString(quickfix.field.EncodedText.FIELD,
            new String(new byte[] {(byte) 56, (byte) 57, (byte) 69, (byte) 51}));
        msg.addGroup(grplot2);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setString(quickfix.field.RawData.FIELD, new String(encText));
    }

    public void populate(EmailMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setEmailThreadID("Thread1");
        msg.setEmailType(EmailType.New);
        msg.setSubject("Subject text");
        msg.setOrigTime(new Date());
        byte[] encSubjectText = new byte[] {(byte) 57, (byte) 58, (byte) 70, (byte) 52,
            (byte) 64, (byte) 82};
        msg.setEncodedSubjectLen(new Integer(6));
        msg.setEncodedSubject(encSubjectText);
        // RoutingIDGroup
        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("route id 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.BlockFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("route id 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockList);
       // Instrument
        msg.setNoRelatedSyms(new Integer(2));
        Instrument44TestData.getInstance().populate(msg.getInstruments()[0]);
        Instrument44TestData.getInstance().populate(msg.getInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setOrderID("C12345678");
        msg.setClOrdID("ASX657433");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[0].setEncodedTextLen(new Integer(4));
        msg.getLinesOfTextGroups()[0].setEncodedText(new String(new byte[] {(byte) 56, (byte) 57, (byte) 69, (byte) 51}).getBytes(DEFAULT_CHARACTER_SET));
        msg.getLinesOfTextGroups()[1].setText("line of text 2");
        msg.getLinesOfTextGroups()[1].setEncodedTextLen(new Integer(4));
        msg.getLinesOfTextGroups()[1].setEncodedText(new String(new byte[] {(byte) 57, (byte) 58, (byte) 70, (byte) 52}).getBytes(DEFAULT_CHARACTER_SET));
        msg.setRawDataLength(new Integer(6));
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setRawData(encText);
    }

    public void check(quickfix.fix44.Email expected, EmailMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.EmailThreadID.FIELD), actual.getEmailThreadID());
        assertEquals(expected.getChar(quickfix.field.EmailType.FIELD), actual.getEmailType().getValue());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.OrigTime.FIELD), actual.getOrigTime(), false);
        assertEquals(expected.getString(quickfix.field.Subject.FIELD), actual.getSubject());
        assertEquals(expected.getInt(quickfix.field.EncodedSubjectLen.FIELD), actual.getEncodedSubjectLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedSubject.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedSubject());
        // RoutingIDGroup check
        assertEquals(expected.getInt(quickfix.field.NoRoutingIDs.FIELD), actual.getNoRoutingIDs().intValue());
        quickfix.fix44.Email.NoRoutingIDs grpr1 = new quickfix.fix44.Email.NoRoutingIDs();
        expected.getGroup(1, grpr1);
        assertEquals(grpr1.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(grpr1.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[0].getRoutingID());
        quickfix.fix44.Email.NoRoutingIDs grpr2 = new quickfix.fix44.Email.NoRoutingIDs();
        expected.getGroup(2, grpr2);
        assertEquals(grpr2.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(grpr2.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[1].getRoutingID());
        // Instrument check
        assertEquals(expected.getInt(quickfix.field.NoRelatedSym.FIELD), actual.getNoRelatedSyms().intValue());
        quickfix.fix44.Email.NoRelatedSym grps1 = new quickfix.fix44.Email.NoRelatedSym();
        expected.getGroup(1, grps1);
        Instrument44TestData.getInstance().check(grps1.getInstrument(), actual.getInstruments()[0]);
        quickfix.fix44.Email.NoRelatedSym grps2 = new quickfix.fix44.Email.NoRelatedSym();
        expected.getGroup(2, grps2);
        Instrument44TestData.getInstance().check(grps2.getInstrument(), actual.getInstruments()[1]);
        // InstrumentLeg check
        assertEquals(actual.getNoLegs().intValue(), expected.getInt(quickfix.field.NoLegs.FIELD));
        quickfix.fix44.Email.NoLegs grpi1 = new quickfix.fix44.Email.NoLegs();
        expected.getGroup(1, grpi1);
        InstrumentLeg44TestData.getInstance().check(actual.getInstrumentLegs()[0], grpi1.getInstrumentLeg());
        quickfix.fix44.Email.NoLegs grpi2 = new quickfix.fix44.Email.NoLegs();
        expected.getGroup(2, grpi2);
        InstrumentLeg44TestData.getInstance().check(actual.getInstrumentLegs()[1], grpi2.getInstrumentLeg());
        // UnderlyingInstrument
        assertEquals(actual.getNoUnderlyings().intValue(), expected.getInt(quickfix.field.NoUnderlyings.FIELD));
        quickfix.fix44.Email.NoUnderlyings grpu1 = new quickfix.fix44.Email.NoUnderlyings();
        expected.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(ui1);
        ui1.set(grpu1.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(actual.getUnderlyingInstruments()[0], ui1);
        quickfix.fix44.Email.NoUnderlyings grpu2 = new quickfix.fix44.Email.NoUnderlyings();
        expected.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(ui2);
        ui2.set(grpu2.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(actual.getUnderlyingInstruments()[1], ui2);

        assertEquals(expected.getString(quickfix.field.OrderID.FIELD), actual.getOrderID());
        assertEquals(expected.getString(quickfix.field.ClOrdID.FIELD), actual.getClOrdID());
        assertEquals(expected.getInt(quickfix.field.LinesOfText.FIELD), actual.getNoLinesOfText().intValue());
        // LinesOfText
        quickfix.fix44.Email.LinesOfText grpt1 = new quickfix.fix44.Email.LinesOfText();
        expected.getGroup(1, grpt1);
        assertEquals(grpt1.getText().getValue(), actual.getLinesOfTextGroups()[0].getText());
        assertEquals(grpt1.getEncodedTextLen().getValue(), actual.getLinesOfTextGroups()[0].getEncodedTextLen().intValue());
        assertArrayEquals(grpt1.getEncodedText().getValue().getBytes(DEFAULT_CHARACTER_SET),
            actual.getLinesOfTextGroups()[0].getEncodedText());
        quickfix.fix44.Email.LinesOfText grpt2 = new quickfix.fix44.Email.LinesOfText();
        expected.getGroup(2, grpt2);
        assertEquals(grpt2.getText().getValue(), actual.getLinesOfTextGroups()[1].getText());
        assertEquals(grpt2.getEncodedTextLen().getValue(), actual.getLinesOfTextGroups()[1].getEncodedTextLen().intValue());
        assertArrayEquals(grpt2.getEncodedText().getValue().getBytes(DEFAULT_CHARACTER_SET),
            actual.getLinesOfTextGroups()[1].getEncodedText());
        
        assertEquals(expected.getInt(quickfix.field.RawDataLength.FIELD), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getString(quickfix.field.RawData.FIELD).getBytes(), actual.getRawData());
    }

    public void check(EmailMsg expected, quickfix.fix44.Email actual) throws Exception {
        assertEquals(expected.getEmailThreadID(), actual.getString(quickfix.field.EmailThreadID.FIELD));
        assertEquals(expected.getEmailType().getValue(), actual.getChar(quickfix.field.EmailType.FIELD));
        assertTimestampEquals(expected.getOrigTime(), actual.getUtcTimeStamp(quickfix.field.OrigTime.FIELD));
        assertEquals(expected.getSubject(), actual.getString(quickfix.field.Subject.FIELD));
        assertEquals(expected.getEncodedSubjectLen().intValue(), actual.getInt(quickfix.field.EncodedSubjectLen.FIELD));
        assertArrayEquals(expected.getEncodedSubject(), actual.getString(quickfix.field.EncodedSubject.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        // RoutingIDGroup check
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getInt(quickfix.field.NoRoutingIDs.FIELD));
        quickfix.fix44.Email.NoRoutingIDs grpr1 = new quickfix.fix44.Email.NoRoutingIDs();
        actual.getGroup(1, grpr1);
        quickfix.field.RoutingType fr11 = new quickfix.field.RoutingType();
        grpr1.get(fr11);
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), fr11.getValue());
        quickfix.field.RoutingID fr12 = new quickfix.field.RoutingID();
        grpr1.get(fr12);
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), fr12.getValue());
        quickfix.fix44.Email.NoRoutingIDs grpr2 = new quickfix.fix44.Email.NoRoutingIDs();
        actual.getGroup(2, grpr2);
        quickfix.field.RoutingType fr21 = new quickfix.field.RoutingType();
        grpr2.get(fr21);
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), fr21.getValue());
        quickfix.field.RoutingID fr22 = new quickfix.field.RoutingID();
        grpr2.get(fr22);
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), fr22.getValue());
        // Instrument check
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getInt(quickfix.field.NoRelatedSym.FIELD));
        quickfix.fix44.Email.NoRelatedSym grps1 = new quickfix.fix44.Email.NoRelatedSym();
        actual.getGroup(1, grps1);
        Instrument44TestData.getInstance().check(expected.getInstruments()[0], grps1.getInstrument());
        quickfix.fix44.Email.NoRelatedSym grps2 = new quickfix.fix44.Email.NoRelatedSym();
        actual.getGroup(2, grps2);
        Instrument44TestData.getInstance().check(expected.getInstruments()[1], grps2.getInstrument());
        // InstrumentLeg check
        assertEquals(expected.getNoLegs().intValue(), actual.getInt(quickfix.field.NoLegs.FIELD));
        quickfix.fix44.Email.NoLegs grpi1 = new quickfix.fix44.Email.NoLegs();
        actual.getGroup(1, grpi1);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], grpi1.getInstrumentLeg());
        quickfix.fix44.Email.NoLegs grpi2 = new quickfix.fix44.Email.NoLegs();
        actual.getGroup(2, grpi2);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], grpi2.getInstrumentLeg());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getInt(quickfix.field.NoUnderlyings.FIELD));
        quickfix.fix44.Email.NoUnderlyings grpu1 = new quickfix.fix44.Email.NoUnderlyings();
        actual.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(ui1);
        ui1.set(grpu1.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], ui1);
        quickfix.fix44.Email.NoUnderlyings grpu2 = new quickfix.fix44.Email.NoUnderlyings();
        actual.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(ui2);
        ui2.set(grpu2.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], ui2);

        assertEquals(expected.getOrderID(), actual.getString(quickfix.field.OrderID.FIELD));
        assertEquals(expected.getClOrdID(), actual.getString(quickfix.field.ClOrdID.FIELD));
        // LinesOfText
        assertEquals(expected.getNoLinesOfText().intValue(), actual.getInt(quickfix.field.LinesOfText.FIELD));
        quickfix.fix44.Email.LinesOfText grplot1 = new quickfix.fix44.Email.LinesOfText();
        actual.getGroup(1, grplot1);
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), grplot1.getText().getValue());
        assertEquals(expected.getLinesOfTextGroups()[0].getEncodedTextLen().intValue(), grplot1.getEncodedTextLen().getValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[0].getEncodedText(),
            grplot1.getEncodedText().getValue().getBytes(DEFAULT_CHARACTER_SET));
        quickfix.fix44.Email.LinesOfText grplot2 = new quickfix.fix44.Email.LinesOfText();
        actual.getGroup(2, grplot2);
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), grplot2.getText().getValue());
        assertEquals(expected.getLinesOfTextGroups()[1].getEncodedTextLen().intValue(), grplot2.getEncodedTextLen().getValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[1].getEncodedText(),
            grplot2.getEncodedText().getValue().getBytes(DEFAULT_CHARACTER_SET));

        assertEquals(expected.getRawDataLength().intValue(), actual.getInt(quickfix.field.RawDataLength.FIELD));
        assertArrayEquals(expected.getRawData(), actual.getString(quickfix.field.RawData.FIELD).getBytes());
    }

    public void check(EmailMsg expected, EmailMsg actual) throws Exception {
        assertEquals(expected.getEmailThreadID(), actual.getEmailThreadID());
        assertEquals(expected.getEmailType().getValue(), actual.getEmailType().getValue());
        assertUTCTimestampEquals(expected.getOrigTime(), actual.getOrigTime(), false);
        assertEquals(expected.getSubject(), actual.getSubject());
        assertEquals(expected.getEncodedSubjectLen().intValue(), actual.getEncodedSubjectLen().intValue());
        assertArrayEquals(expected.getEncodedSubject(), actual.getEncodedSubject());
        // RoutingIDGroup check
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getNoRoutingIDs().intValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), actual.getRoutingIDGroups()[0].getRoutingID());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), actual.getRoutingIDGroups()[1].getRoutingID());
        // Instrument check
        assertEquals(expected.getNoRelatedSyms().intValue(), actual.getNoRelatedSyms().intValue());
        Instrument44TestData.getInstance().check(expected.getInstruments()[0], actual.getInstruments()[0]);
        Instrument44TestData.getInstance().check(expected.getInstruments()[1], actual.getInstruments()[1]);
        // InstrumentLeg check
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        // LinesOfText
        assertEquals(expected.getNoLinesOfText().intValue(), actual.getNoLinesOfText().intValue());
        assertEquals(expected.getLinesOfTextGroups()[0].getText(), actual.getLinesOfTextGroups()[0].getText());
        assertEquals(expected.getLinesOfTextGroups()[0].getEncodedTextLen().intValue(),
            expected.getLinesOfTextGroups()[0].getEncodedTextLen().intValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[0].getEncodedText(),
            expected.getLinesOfTextGroups()[0].getEncodedText());
        assertEquals(expected.getLinesOfTextGroups()[1].getText(), actual.getLinesOfTextGroups()[1].getText());
        assertEquals(expected.getLinesOfTextGroups()[1].getEncodedTextLen().intValue(),
            expected.getLinesOfTextGroups()[1].getEncodedTextLen().intValue());
        assertArrayEquals(expected.getLinesOfTextGroups()[1].getEncodedText(),
            expected.getLinesOfTextGroups()[1].getEncodedText());
        
        assertEquals(expected.getRawDataLength().intValue(), actual.getRawDataLength().intValue());
        assertArrayEquals(expected.getRawData(), actual.getRawData());
    }
}
