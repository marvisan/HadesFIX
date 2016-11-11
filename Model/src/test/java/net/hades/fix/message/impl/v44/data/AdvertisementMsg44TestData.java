/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg44TestData.java
 *
 * $Id: AdvertisementMsg44TestData.java,v 1.2 2011-10-29 09:42:19 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.QtyType;

/**
 * Test utility for AdvertismentMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:21:35 PM
 */
public class AdvertisementMsg44TestData extends MsgTest {

    private static final AdvertisementMsg44TestData INSTANCE;

    static {
        INSTANCE = new AdvertisementMsg44TestData();
    }

    public static AdvertisementMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.Advertisement msg) throws Exception {
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.AdvId.FIELD, "45");
        msg.setString(quickfix.field.AdvTransType.FIELD, "N");
        msg.setString(quickfix.field.AdvRefID.FIELD, "265");
        msg.setString(quickfix.field.AdvSide.FIELD, "B");
        msg.setDouble(quickfix.field.Quantity.FIELD, new Double("200"));
        msg.setInt(quickfix.field.QtyType.FIELD, quickfix.field.QtyType.UNITS);
        msg.setDouble(quickfix.field.Price.FIELD, new Double("13.45"));
        msg.setString(quickfix.field.Currency.FIELD, "USD");
        msg.setUtcDateOnly(quickfix.field.TradeDate.FIELD, new Date());
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Text.FIELD, "I want these shares!");
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedText.FIELD, new String(textDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.URLLink.FIELD, "http://www.symbol.com/symbol");
        msg.setString(quickfix.field.LastMkt.FIELD, "CBT");
        msg.setString(quickfix.field.TradingSessionID.FIELD, "8");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "SUB-8");
        // Instrument
        quickfix.fix44.component.Instrument instr =  new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
        // InstrumentLeg
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix44.component.InstrumentLeg ileg1 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(ileg1);
        quickfix.fix44.Advertisement.NoLegs leg1 = new quickfix.fix44.Advertisement.NoLegs();
        leg1.set(ileg1);
        msg.addGroup(leg1);
        quickfix.fix44.component.InstrumentLeg ileg2 = new quickfix.fix44.component.InstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate2(ileg2);
        quickfix.fix44.Advertisement.NoLegs leg2 = new quickfix.fix44.Advertisement.NoLegs();
        leg2.set(ileg2);
        msg.addGroup(leg2);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix44.Advertisement.NoUnderlyings grpl1 = new quickfix.fix44.Advertisement.NoUnderlyings();
        quickfix.fix44.component.UnderlyingInstrument uinst1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(uinst1);
        grpl1.set(uinst1);
        grpl1.set(uinst1.getUnderlyingStipulations());
        msg.addGroup(grpl1);
        quickfix.fix44.Advertisement.NoUnderlyings grpl2 = new quickfix.fix44.Advertisement.NoUnderlyings();
        quickfix.fix44.component.UnderlyingInstrument uinst2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(uinst2);
        grpl2.set(uinst2);
        grpl2.set(uinst2.getUnderlyingStipulations());
        msg.addGroup(grpl2);
    }

    public void populate(AdvertisementMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.New);
        msg.setAdvRefID("Ref. 12345");
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
        msg.setTradingSessionID("8");
        msg.setTradingSessionSubID("ID:234890");
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);
    }

    public void check(quickfix.fix44.Advertisement expected, AdvertisementMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.AdvId.FIELD), actual.getAdvID());
        assertEquals(expected.getString(quickfix.field.AdvTransType.FIELD), actual.getAdvTransType().getValue());
        assertEquals(expected.getString(quickfix.field.AdvRefID.FIELD), actual.getAdvRefID());
        assertEquals(expected.getString(quickfix.field.AdvSide.FIELD), actual.getAdvSide().getValue());
        assertEquals(expected.getInt(quickfix.field.QtyType.FIELD), actual.getQtyType().getValue());
        assertEquals(expected.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), actual.getQuantity().doubleValue(), 0.1);
        assertEquals(expected.getDecimal(quickfix.field.Price.FIELD).doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertDateEquals(parseQFDateString(expected.getString(quickfix.field.TradeDate.FIELD)), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET),
            actual.getEncodedText());
        assertEquals(expected.getString(quickfix.field.URLLink.FIELD), actual.getUrlLink());
        assertEquals(expected.getString(quickfix.field.LastMkt.FIELD), actual.getLastMkt());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // InstrumentLegs
        assertNotNull(actual.getInstrumentLegs());
        assertEquals(2, actual.getInstrumentLegs().length);
        quickfix.fix44.Advertisement.NoLegs grpl = new quickfix.fix44.Advertisement.NoLegs();
        expected.getGroup(1, grpl);
        InstrumentLeg44TestData.getInstance().check(grpl.getInstrumentLeg(), actual.getInstrumentLegs()[0]);
        quickfix.fix44.Advertisement.NoLegs grp2 = new quickfix.fix44.Advertisement.NoLegs();
        expected.getGroup(2, grp2);
        InstrumentLeg44TestData.getInstance().check(grp2.getInstrumentLeg(), actual.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        assertNotNull(actual.getNoUnderlyings());
        assertEquals(new Integer(2), actual.getNoUnderlyings());
        assertNotNull(actual.getUnderlyingInstruments());
        assertEquals(2, actual.getUnderlyingInstruments().length);
        quickfix.fix44.Advertisement.NoUnderlyings grpl1 = new quickfix.fix44.Advertisement.NoUnderlyings();
        expected.getGroup(1, grpl1);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpl1.get(ui1);
        ui1.set(grpl1.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(ui1, actual.getUnderlyingInstruments()[0]);
        quickfix.fix44.Advertisement.NoUnderlyings grpl2 = new quickfix.fix44.Advertisement.NoUnderlyings();
        expected.getGroup(2, grpl2);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpl2.get(ui2);
        ui2.set(grpl2.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(ui2, actual.getUnderlyingInstruments()[1]);
    }

    public void check(AdvertisementMsg expected, quickfix.fix44.Advertisement actual) throws Exception {
        assertEquals(expected.getAdvID(), actual.getString(quickfix.field.AdvId.FIELD));
        assertEquals(expected.getAdvTransType().getValue(), actual.getString(quickfix.field.AdvTransType.FIELD));
        assertEquals(expected.getAdvRefID(), actual.getString(quickfix.field.AdvRefID.FIELD));
        assertEquals(expected.getAdvSide().getValue(), actual.getString(quickfix.field.AdvSide.FIELD));
        assertEquals(expected.getQuantity(), actual.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), 0.1);
        assertEquals(expected.getQtyType().getValue(), actual.getInt(quickfix.field.QtyType.FIELD));
        assertEquals(expected.getPrice(), actual.getDecimal(quickfix.field.Price.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertDateEquals(expected.getTradeDate(), actual.getUtcDateOnly(quickfix.field.TradeDate.FIELD));
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getUrlLink(), actual.getString(quickfix.field.URLLink.FIELD));
        assertEquals(expected.getLastMkt(), actual.getString(quickfix.field.LastMkt.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Instrument leg
        assertEquals(expected.getNoLegs().intValue(), actual.getInt(quickfix.field.NoLegs.FIELD));
        quickfix.fix44.Advertisement.NoLegs grp1 = new quickfix.fix44.Advertisement.NoLegs();
        actual.getGroup(1, grp1);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], grp1.getInstrumentLeg());
        quickfix.fix44.Advertisement.NoLegs grp2 = new quickfix.fix44.Advertisement.NoLegs();
        actual.getGroup(2, grp2);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], grp2.getInstrumentLeg());
        // UnderlyingInstrument
        assertTrue(actual.hasGroup(quickfix.field.NoUnderlyings.FIELD));
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getInt(quickfix.field.NoUnderlyings.FIELD));
        quickfix.fix44.Advertisement.NoUnderlyings grpu1 = new quickfix.fix44.Advertisement.NoUnderlyings();
        actual.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(ui1);
        ui1.set(grpu1.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], ui1);
        quickfix.fix44.Advertisement.NoUnderlyings grpu2 = new quickfix.fix44.Advertisement.NoUnderlyings();
        actual.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(ui2);
        ui2.set(grpu2.getUnderlyingStipulations());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], ui2);
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
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Instrument leg
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
    }
}
