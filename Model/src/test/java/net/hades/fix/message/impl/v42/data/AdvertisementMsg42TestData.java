/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg42TestData.java
 *
 * $Id: AdvertisementMsg42TestData.java,v 1.4 2011-10-29 09:42:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityIDSource;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for AdvertismentMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 11/05/2009, 12:21:35 PM
 */
public class AdvertisementMsg42TestData extends MsgTest {

    private static final AdvertisementMsg42TestData INSTANCE;

    static {
        INSTANCE = new AdvertisementMsg42TestData();
    }

    public static AdvertisementMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix42.Advertisement msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.AdvId.FIELD, "45");
        msg.setString(quickfix.field.AdvTransType.FIELD, "N");
        msg.setString(quickfix.field.AdvRefID.FIELD, "265");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOTOROLA Shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "MOTO");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "NYSE");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "200806w2");
        msg.setInt(quickfix.field.MaturityDay.FIELD, 14);
        msg.setInt(quickfix.field.PutOrCall.FIELD, 1);
        msg.setDecimal(quickfix.field.StrikePrice.FIELD, new BigDecimal("25.48"));
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'T');
        msg.setDecimal(quickfix.field.ContractMultiplier.FIELD, new BigDecimal("1.67"));
        msg.setDecimal(quickfix.field.CouponRate.FIELD, new BigDecimal("10.55"));
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBT");
        msg.setString(quickfix.field.Issuer.FIELD, "HADES");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "One Motorola Share");
        msg.setString(quickfix.field.AdvSide.FIELD, "B");
        msg.setDecimal(quickfix.field.Quantity.FIELD, new BigDecimal("200"));
        msg.setDecimal(quickfix.field.Price.FIELD, new BigDecimal("13.45"));
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
    }

    public void populate(AdvertisementMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.Cancel);
        msg.setAdvRefID("265");
        msg.setSymbol("MOT");
        msg.setSymbolSfx("MOTOROLA Shares");
        msg.setSecurityID("MOTO");
        msg.setSecurityIDSource(SecurityIDSource.QUIK.getValue());
        msg.setSecurityType(SecurityType.MutualFund.getValue());
        msg.setMaturityMonthYear("200806w2");
        msg.setMaturityDay(new Integer(14));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(25.48));
        msg.setOptAttribute(new Character('T'));
        msg.setContractMultiplier(new Double("1.67"));
        msg.setCouponRate(new Double("10.55"));
        msg.setSecurityExchange("CO");
        msg.setIssuer("HADES");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("One Motorola Share");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        msg.setPrice(new Double("13.45"));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTradeDate(new Date());
        msg.setTransactTime(new Date());
        msg.setText("I want these shares!");
        msg.setEncodedTextLen(new Integer(8));
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setEncodedText(textDataExp);
        msg.setUrlLink("http://www.symbol.com/symbol");
        msg.setLastMkt("CM");
        msg.setTradingSessionID("8");
    }

    public void populate(AdvertisementMsg msg, SecurityType secType) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.Cancel);
        msg.setAdvRefID("265");
        msg.setSymbol("MOT");
        msg.setSymbolSfx("MOTOROLA Shares");
        msg.setSecurityID("MOTO");
        msg.setSecurityIDSource(SecurityIDSource.QUIK.getValue());
        msg.setSecurityType(secType.getValue());
        msg.setMaturityMonthYear("200806w2");
        msg.setMaturityDay(new Integer(14));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(25.48));
        msg.setOptAttribute(new Character('T'));
        msg.setContractMultiplier(new Double("1.67"));
        msg.setCouponRate(new Double("10.55"));
        msg.setSecurityExchange("CO");
        msg.setIssuer("HADES");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(issuerDataExp);
        msg.setSecurityDesc("One Motorola Share");
        byte[] secDescDataExp = new byte[] {(byte) 20, (byte) 35, (byte) 46, (byte) 98,
            (byte) 179, (byte) 202, (byte) 226, (byte) 257};
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        msg.setPrice(new Double("13.45"));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTradeDate(new Date());
        msg.setTransactTime(new Date());
        msg.setText("I want these shares!");
        msg.setEncodedTextLen(new Integer(8));
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setEncodedText(textDataExp);
        msg.setUrlLink("http://www.symbol.com/symbol");
        msg.setLastMkt("CM");
        msg.setTradingSessionID("8");
    }

    public void check(quickfix.fix42.Advertisement expected, AdvertisementMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.AdvId.FIELD), actual.getAdvID());
        assertEquals(expected.getString(quickfix.field.AdvTransType.FIELD), actual.getAdvTransType().getValue());
        assertEquals(expected.getString(quickfix.field.AdvRefID.FIELD), actual.getAdvRefID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.SecurityIDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getInt(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().intValue());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getDouble(quickfix.field.ContractMultiplier.FIELD), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CouponRate.FIELD), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getInt(quickfix.field.EncodedIssuerLen.FIELD), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET),
            actual.getEncodedIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
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

    }

    public void check(AdvertisementMsg expected, quickfix.fix42.Advertisement actual) throws Exception {
        assertEquals(expected.getAdvID(), actual.getString(quickfix.field.AdvId.FIELD));
        assertEquals(expected.getAdvTransType().getValue(), actual.getString(quickfix.field.AdvTransType.FIELD));
        assertEquals(expected.getAdvRefID(), actual.getString(quickfix.field.AdvRefID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getSecurityType(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().intValue(), actual.getInt(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getAdvSide().getValue(), actual.getString(quickfix.field.AdvSide.FIELD));
        assertEquals(expected.getQuantity(), actual.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), 0.1);
        assertEquals(expected.getPrice(), actual.getDecimal(quickfix.field.Price.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertDateEquals(expected.getTradeDate(), actual.getUtcDateOnly(quickfix.field.TradeDate.FIELD));
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(),
            actual.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getUrlLink(), actual.getString(quickfix.field.URLLink.FIELD));
        assertEquals(expected.getLastMkt(), actual.getString(quickfix.field.LastMkt.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
    }

    public void check(AdvertisementMsg expected, AdvertisementMsg actual) {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().intValue(), actual.getMaturityDay().intValue());
        assertEquals(expected.getPutOrCall().getValue(), actual.getPutOrCall().getValue());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getOptAttribute().charValue());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
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
    }

    public void checkSecTypeMF(AdvertisementMsg expected, AdvertisementMsg actual) {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
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
    }

    public void checkSecTypeOPT(AdvertisementMsg expected, AdvertisementMsg actual) {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().intValue(), actual.getMaturityDay().intValue());
        assertEquals(expected.getPutOrCall().getValue(), actual.getPutOrCall().getValue());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getOptAttribute().charValue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
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
    }

    public void checkSecTypeFUT(AdvertisementMsg expected, AdvertisementMsg actual) {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().intValue(), actual.getMaturityDay().intValue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
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
    }

    public void checkSecTypeFI(AdvertisementMsg expected, AdvertisementMsg actual) {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
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
    }
}
