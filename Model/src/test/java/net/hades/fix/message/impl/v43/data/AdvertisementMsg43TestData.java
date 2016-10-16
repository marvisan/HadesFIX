/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg43TestData.java
 *
 * $Id: AdvertisementMsg43TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for AdvertismentMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:21:35 PM
 */
public class AdvertisementMsg43TestData extends MsgTest {

    private static final AdvertisementMsg43TestData INSTANCE;

    static {
        INSTANCE = new AdvertisementMsg43TestData();
    }

    public static AdvertisementMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.Advertisement msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.AdvId.FIELD, "45");
        msg.setString(quickfix.field.AdvTransType.FIELD, "N");
        msg.setString(quickfix.field.AdvRefID.FIELD, "265");
        msg.setChar(quickfix.field.AdvSide.FIELD, quickfix.field.AdvSide.BUY);
        msg.setDouble(quickfix.field.Quantity.FIELD, new Double("200"));
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
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);
    }

    public void populate(AdvertisementMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.Cancel);
        msg.setAdvRefID("Ref. 12345");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        msg.setPrice(new Double("23.0"));
        msg.setCurrency(Currency.AustralianDollar);
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
        Instrument43TestData.getInstance().populate(msg.getInstrument());
    }

    public void populate(AdvertisementMsg msg, SecurityType secType) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.Cancel);
        msg.setAdvRefID("Ref. 13335");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        msg.setPrice(new Double("23.0"));
        msg.setCurrency(Currency.AustralianDollar);
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
        Instrument43TestData.getInstance().populate(msg.getInstrument(), secType);
    }

    public void check(quickfix.fix43.Advertisement expected, AdvertisementMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.AdvId.FIELD), actual.getAdvID());
        assertEquals(expected.getString(quickfix.field.AdvTransType.FIELD), actual.getAdvTransType().getValue());
        assertEquals(expected.getString(quickfix.field.AdvRefID.FIELD), actual.getAdvRefID());
        assertEquals(expected.getString(quickfix.field.AdvSide.FIELD), actual.getAdvSide().getValue());
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
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(AdvertisementMsg expected, quickfix.fix43.Advertisement actual) throws Exception {
        assertEquals(expected.getAdvID(), actual.getString(quickfix.field.AdvId.FIELD));
        assertEquals(expected.getAdvTransType().getValue(), actual.getString(quickfix.field.AdvTransType.FIELD));
        assertEquals(expected.getAdvRefID(), actual.getString(quickfix.field.AdvRefID.FIELD));
        assertEquals(expected.getAdvSide().getValue(), actual.getString(quickfix.field.AdvSide.FIELD));
        assertEquals(expected.getQuantity(), actual.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), 0.1);
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
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(AdvertisementMsg expected, AdvertisementMsg actual) throws Exception {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getAdvSide(), actual.getAdvSide());
        assertEquals(expected.getQuantity(), actual.getQuantity(), 0.1);
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
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
    }

    public void check(AdvertisementMsg expected, AdvertisementMsg actual, SecurityType secType) throws Exception {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getAdvSide(), actual.getAdvSide());
        assertEquals(expected.getQuantity(), actual.getQuantity(), 0.1);
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
        if (secType.equals(SecurityType.MutualFund)) {
            Instrument43TestData.getInstance().checkSecTypeMF(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.Option)) {
            Instrument43TestData.getInstance().checkSecTypeOPT(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.Future)) {
            Instrument43TestData.getInstance().checkSecTypeMF(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.CommonStock)) {
            Instrument43TestData.getInstance().checkSecTypeCORP(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.USTreasuryBond)) {
            Instrument43TestData.getInstance().checkSecTypeGOV(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.MediumTermNotes)) {
            Instrument43TestData.getInstance().checkSecTypeMONEY(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.RevolverLoan)) {
            Instrument43TestData.getInstance().checkSecTypeLOAN(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.CorpMortgageBackedSecurities)) {
            Instrument43TestData.getInstance().checkSecTypeGOV(expected.getInstrument(), actual.getInstrument());
        } else if (secType.equals(SecurityType.SpecialAssessment)) {
            Instrument43TestData.getInstance().checkSecTypeLOAN(expected.getInstrument(), actual.getInstrument());
        }
    }

}
