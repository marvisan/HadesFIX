/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg50SP2TestData.java
 *
 * $Id: AdvertisementMsg50SP2TestData.java,v 1.3 2011-10-29 09:42:20 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.QtyType;

/**
 * Test utility for AdvertismentMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 12:21:35 PM
 */
public class AdvertisementMsg50SP2TestData extends MsgTest {

    private static final AdvertisementMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new AdvertisementMsg50SP2TestData();
    }

    public static AdvertisementMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AdvertisementMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.New);
        msg.setAdvRefID("265");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        msg.setQtyType(QtyType.Contracts);
        msg.setPrice(new Double("23.0"));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTradeDate(new Date());
        msg.setTransactTime(new Date());
        msg.setText("Trade in confidence");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setUrlLink("http://www.symbol.com/symbol");
        msg.setLastMkt("CBT");
        msg.setTradingSessionID("1");
        msg.setTradingSessionSubID("3");
        // Instrument
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
    }
    
    public void check(AdvertisementMsg expected, AdvertisementMsg actual) throws Exception {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getAdvSide(), actual.getAdvSide());
        assertEquals(expected.getQuantity(), actual.getQuantity(), 0.1);
        assertEquals(expected.getQtyType().getValue(), actual.getQtyType().getValue());
        assertEquals(expected.getPrice(), actual.getPrice(), 0.01);
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getUrlLink(), actual.getUrlLink());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // Instrument check
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Instrument leg
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
    }
}
