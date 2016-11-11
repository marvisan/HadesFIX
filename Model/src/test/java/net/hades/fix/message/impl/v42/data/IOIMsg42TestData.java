/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg42TestData.java
 *
 * $Id: IOIMsg42TestData.java,v 1.2 2011-10-29 09:42:07 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.type.Benchmark;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.RoutingType;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for IOIMsg42 message class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 11:01:10 AM
 */
public class IOIMsg42TestData extends MsgTest {

    private static final IOIMsg42TestData INSTANCE;

    static {
        INSTANCE = new IOIMsg42TestData();
    }

    public static IOIMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix42.IndicationofInterest msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.IOIID.FIELD, "IOI ID");
        msg.setString(quickfix.field.IOITransType.FIELD, IOITransType.New.getValue());
        msg.setString(quickfix.field.IOIRefID.FIELD, "Ref ID");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOT SFX");
        msg.setString(quickfix.field.SecurityID.FIELD, "SEC ID");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "ID Src");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "092009");
        msg.setInt(quickfix.field.MaturityDay.FIELD, 2);
        msg.setInt(quickfix.field.PutOrCall.FIELD, 0);
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 12.33);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'X');
        msg.setDouble(quickfix.field.ContractMultiplier.FIELD, 23.134);
        msg.setDouble(quickfix.field.CouponRate.FIELD, 1.234);
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBOT");
        msg.setString(quickfix.field.Issuer.FIELD, "Issuer");
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 7);
        byte[] encEncIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(encEncIssuer));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "MOT Shares");
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 6);
        byte[] encEncSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(encEncSecDesc));
        msg.setChar(quickfix.field.Side.FIELD, Side.BuyMinus.getValue());
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        msg.setString(quickfix.field.IOIQty.FIELD, IOIQty.Large.getValue());
        msg.setDouble(quickfix.field.Price.FIELD, 12.556);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setChar(quickfix.field.IOIQltyInd.FIELD, 'L');
        msg.setBoolean(quickfix.field.IOINaturalFlag.FIELD, true);
        msg.setInt(quickfix.field.NoIOIQualifiers.FIELD, 2);
        quickfix.fix42.IndicationofInterest.NoIOIQualifiers grpe1 = new quickfix.fix42.IndicationofInterest.NoIOIQualifiers();
        grpe1.setChar(quickfix.field.IOIQualifier.FIELD, '2');
        msg.addGroup(grpe1);
        quickfix.fix42.IndicationofInterest.NoIOIQualifiers grpe2 = new quickfix.fix42.IndicationofInterest.NoIOIQualifiers();
        grpe2.setChar(quickfix.field.IOIQualifier.FIELD, '3');
        msg.addGroup(grpe2);
        msg.setString(quickfix.field.Text.FIELD, "some text");
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(encText));
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.URLLink.FIELD, "www.cbot.com");
        quickfix.fix42.IndicationofInterest.NoRoutingIDs grpr1 = new quickfix.fix42.IndicationofInterest.NoRoutingIDs();
        grpr1.setInt(quickfix.field.RoutingType.FIELD, RoutingType.BlockFirm.getValue());
        grpr1.setString(quickfix.field.RoutingID.FIELD, "route 1");
        msg.addGroup(grpr1);
        quickfix.fix42.IndicationofInterest.NoRoutingIDs grpr2 = new quickfix.fix42.IndicationofInterest.NoRoutingIDs();
        grpr2.setInt(quickfix.field.RoutingType.FIELD, RoutingType.TargetFirm.getValue());
        grpr2.setString(quickfix.field.RoutingID.FIELD, "route 2");
        msg.addGroup(grpr2);
        msg.setDouble(quickfix.field.SpreadToBenchmark.FIELD, 12.345);
        msg.setChar(quickfix.field.Benchmark.FIELD, Benchmark.CURVE.getValue());
    }

    public void populate(IOIMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.setIoiRefID("ref ID");
        msg.setSymbol("symbol");
        msg.setSymbolSfx("symbol sfx");
        msg.setSecurityID("sec ID");
        msg.setSecurityIDSource("sec ID source");
        msg.setSecurityType(SecurityType.AmendedRestated.getValue());
        msg.setMaturityMonthYear("082009");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(12.456));
        msg.setOptAttribute(new Character('Z'));
        msg.setContractMultiplier(new Double(23.44));
        msg.setCouponRate(new Double(12.999));
        msg.setSecurityExchange("NYSE");
        msg.setIssuer("ISSUER");
        msg.setEncodedIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedIssuer(encLegIssuer);
        msg.setSecurityDesc("sec desc");
        msg.setEncodedSecurityDescLen(new Integer(6));
        byte[] encEncSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedSecurityDesc(encEncSecDesc);
        msg.setSide(Side.Borrow);
        msg.setCurrency(Currency.AustralianDollar);
        msg.setIoiQty(IOIQty.Large);
        msg.setPrice(new Double(12.345));
        msg.setValidUntilTime(new Date());
        msg.setIoiQltyInd(IOIQltyInd.High);
        msg.setIoiNaturalFlag(Boolean.FALSE);
        msg.setNoIOIQualifiers(new Integer(3));
        msg.getIoiQualifiers()[0].setIoiQualifier(new Character('1'));
        msg.getIoiQualifiers()[1].setIoiQualifier(new Character('5'));
        msg.getIoiQualifiers()[2].setIoiQualifier(new Character('B'));
        msg.setText("I want these shares!");
        msg.setEncodedTextLen(new Integer(6));
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setEncodedText(encText);
        msg.setTransactTime(new Date());
        msg.setUrlLink("www.shares.com");
        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("route id 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.BlockFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("route id 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockList);
        msg.setSpreadToBenchmark(new Double(11.111));
        msg.setBenchmark(Benchmark.CURVE);
    }

    public void check(quickfix.fix42.IndicationofInterest expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.IOIID.FIELD), actual.getIoiID());
        assertEquals(expected.getString(quickfix.field.IOITransType.FIELD), actual.getIoiTransType().getValue());
        assertEquals(expected.getString(quickfix.field.IOIRefID.FIELD), actual.getIoiRefID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.SecurityIDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getInt(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().intValue());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDouble(quickfix.field.StrikePrice.FIELD), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getDouble(quickfix.field.ContractMultiplier.FIELD), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.CouponRate.FIELD), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getInt(quickfix.field.EncodedIssuerLen.FIELD), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(TEST_CHARSET), actual.getEncodedIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getInt(quickfix.field.EncodedSecurityDescLen.FIELD), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(TEST_CHARSET), actual.getEncodedSecurityDesc());
        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.IOIQty.FIELD), actual.getIoiQty().getValue());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getChar(quickfix.field.IOIQltyInd.FIELD), actual.getIoiQltyInd().getValue());
        assertEquals(expected.getBoolean(quickfix.field.IOINaturalFlag.FIELD), actual.getIoiNaturalFlag().booleanValue());
        quickfix.fix42.IndicationofInterest.NoIOIQualifiers grp1 = new quickfix.fix42.IndicationofInterest.NoIOIQualifiers();
        expected.getGroup(1, grp1);
        assertEquals(grp1.getChar(quickfix.field.IOIQualifier.FIELD), actual.getIoiQualifiers()[0].getIoiQualifier().charValue());
        quickfix.fix42.IndicationofInterest.NoIOIQualifiers grp2 = new quickfix.fix42.IndicationofInterest.NoIOIQualifiers();
        expected.getGroup(2, grp2);
        assertEquals(grp2.getChar(quickfix.field.IOIQualifier.FIELD), actual.getIoiQualifiers()[1].getIoiQualifier().charValue());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(TEST_CHARSET), actual.getEncodedText());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.URLLink.FIELD), actual.getUrlLink());
        assertEquals(expected.getInt(quickfix.field.NoRoutingIDs.FIELD), actual.getNoRoutingIDs().intValue());
        quickfix.fix42.IndicationofInterest.NoRoutingIDs grpr1 = new quickfix.fix42.IndicationofInterest.NoRoutingIDs();
        expected.getGroup(1, grpr1);
        assertEquals(grpr1.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(grpr1.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[0].getRoutingID());
        quickfix.fix42.IndicationofInterest.NoRoutingIDs grpr2 = new quickfix.fix42.IndicationofInterest.NoRoutingIDs();
        expected.getGroup(2, grpr2);
        assertEquals(grpr2.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(grpr2.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[1].getRoutingID());
        assertEquals(expected.getDouble(quickfix.field.SpreadToBenchmark.FIELD), actual.getSpreadToBenchmark().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.Benchmark.FIELD), actual.getBenchmark().getValue());
    }

    public void check(IOIMsg expected, quickfix.fix42.IndicationofInterest actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getString(quickfix.field.IOIID.FIELD));
        assertEquals(expected.getIoiTransType().getValue(), actual.getString(quickfix.field.IOITransType.FIELD));
        assertEquals(expected.getIoiRefID(), actual.getString(quickfix.field.IOIRefID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getSecurityType(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().intValue(), actual.getInt(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDouble(quickfix.field.StrikePrice.FIELD), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDouble(quickfix.field.ContractMultiplier.FIELD), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDouble(quickfix.field.CouponRate.FIELD), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(TEST_CHARSET));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(TEST_CHARSET));
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertEquals(expected.getIoiQty().getValue(), actual.getString(quickfix.field.IOIQty.FIELD));
        assertEquals(expected.getPrice().doubleValue(), actual.getDouble(quickfix.field.Price.FIELD), 0.001);
        assertTimeEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getIoiQltyInd().getValue(), actual.getChar(quickfix.field.IOIQltyInd.FIELD));
        assertEquals(expected.getIoiNaturalFlag().booleanValue(), actual.getBoolean(quickfix.field.IOINaturalFlag.FIELD));
        assertEquals(expected.getNoIOIQualifiers().intValue(), actual.getInt(quickfix.field.NoIOIQualifiers.FIELD));
        quickfix.fix42.IndicationofInterest.NoIOIQualifiers grp1 = new quickfix.fix42.IndicationofInterest.NoIOIQualifiers();
        actual.getGroup(1, grp1);
        assertEquals(expected.getIoiQualifiers()[0].getIoiQualifier().charValue(), grp1.getIOIQualifier().getValue());
        quickfix.fix42.IndicationofInterest.NoIOIQualifiers grp2 = new quickfix.fix42.IndicationofInterest.NoIOIQualifiers();
        actual.getGroup(2, grp2);
        assertEquals(expected.getIoiQualifiers()[1].getIoiQualifier().charValue(), grp2.getIOIQualifier().getValue());
        quickfix.fix42.IndicationofInterest.NoIOIQualifiers grp3 = new quickfix.fix42.IndicationofInterest.NoIOIQualifiers();
        actual.getGroup(3, grp3);
        assertEquals(expected.getIoiQualifiers()[2].getIoiQualifier().charValue(), grp3.getIOIQualifier().getValue());
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(TEST_CHARSET));
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD));
        assertEquals(expected.getUrlLink(), actual.getString(quickfix.field.URLLink.FIELD));
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getInt(quickfix.field.NoRoutingIDs.FIELD));
        quickfix.fix42.IndicationofInterest.NoRoutingIDs grpr1 = new quickfix.fix42.IndicationofInterest.NoRoutingIDs();
        actual.getGroup(1, grpr1);
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), grpr1.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), grpr1.getRoutingID().getValue());
        quickfix.fix42.IndicationofInterest.NoRoutingIDs grpr2 = new quickfix.fix42.IndicationofInterest.NoRoutingIDs();
        actual.getGroup(2, grpr2);
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), grpr2.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), grpr2.getRoutingID().getValue());
        assertEquals(expected.getSpreadToBenchmark().doubleValue(), actual.getDouble(quickfix.field.SpreadToBenchmark.FIELD), 0.001);
        assertEquals(expected.getBenchmark().getValue(), actual.getChar(quickfix.field.Benchmark.FIELD));
    }

    public void check(IOIMsg expected, IOIMsg actual) {
        assertEquals(expected.getIoiID(), actual.getIoiID());
        assertEquals(expected.getIoiTransType().getValue(), actual.getIoiTransType().getValue());
        assertEquals(expected.getIoiRefID(), actual.getIoiRefID());
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
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getIoiQty(), actual.getIoiQty());
        assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getIoiNaturalFlag().booleanValue(), actual.getIoiNaturalFlag().booleanValue());
        assertEquals(expected.getNoIOIQualifiers().intValue(), actual.getNoIOIQualifiers().intValue());
        assertEquals(expected.getIoiQualifiers()[0].getIoiQualifier().charValue(), actual.getIoiQualifiers()[0].getIoiQualifier().charValue());
        assertEquals(expected.getIoiQualifiers()[1].getIoiQualifier().charValue(), actual.getIoiQualifiers()[1].getIoiQualifier().charValue());
        assertEquals(expected.getIoiQualifiers()[2].getIoiQualifier().charValue(), actual.getIoiQualifiers()[2].getIoiQualifier().charValue());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getUrlLink(), actual.getUrlLink());
        assertEquals(expected.getSpreadToBenchmark().doubleValue(), actual.getSpreadToBenchmark().doubleValue(), 0.001);
        assertEquals(expected.getBenchmark().getValue(), actual.getBenchmark().getValue());
    }
}
