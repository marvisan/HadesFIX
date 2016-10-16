/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg50SP1TestData.java
 *
 * $Id: NewsMsg50SP1TestData.java,v 1.1 2009-07-06 03:19:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.comp.impl.v50sp1.ApplicationSequenceControl50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.RoutingType;
import net.hades.fix.message.type.Urgency;

/**
 * Test utility for NewsMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:34:14 AM
 */
public class NewsMsg50SP1TestData extends MsgTest {

    private static final NewsMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new NewsMsg50SP1TestData();
    }

    public static NewsMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(NewsMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        // ApplicationSequenceControl
        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP1TestData.getInstance().populate(msg.getApplicationSequenceControl());
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
        Instrument50SP1TestData.getInstance().populate(msg.getInstruments()[0]);
        Instrument50SP1TestData.getInstance().populate(msg.getInstruments()[1]);
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP1TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
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

    public void check(NewsMsg expected, NewsMsg actual) throws Exception {
        // ApplicationSequenceControl check
        ApplicationSequenceControl50SP1TestData.getInstance().check(expected.getApplicationSequenceControl(), actual.getApplicationSequenceControl());
        
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
        Instrument50SP1TestData.getInstance().check(expected.getInstruments()[0], actual.getInstruments()[0]);
        Instrument50SP1TestData.getInstance().check(expected.getInstruments()[1], actual.getInstruments()[1]);
        // InstrumentLeg check
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

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
