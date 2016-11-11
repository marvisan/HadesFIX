/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg43TestData.java
 *
 * $Id: IOIMsg43TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.SpreadOrBenchmarkCurveData43TestData;
import net.hades.fix.message.type.Benchmark;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.RoutingType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for IOIMsg43 message class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 11:01:10 AM
 */
public class IOIMsg43TestData extends MsgTest {

    private static final IOIMsg43TestData INSTANCE;

    static {
        INSTANCE = new IOIMsg43TestData();
    }

    public static IOIMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.IndicationOfInterest msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.IOIID.FIELD, "IOI ID");
        msg.setString(quickfix.field.IOITransType.FIELD, IOITransType.New.getValue());
        msg.setString(quickfix.field.IOIRefID.FIELD, "Ref ID");
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setChar(quickfix.field.Side.FIELD, Side.BuyMinus.getValue());
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        msg.setString(quickfix.field.IOIQty.FIELD, IOIQty.Large.getValue());
        msg.setInt(quickfix.field.PriceType.FIELD, 2);
        msg.setDouble(quickfix.field.Price.FIELD, 12.556);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setChar(quickfix.field.IOIQltyInd.FIELD, 'L');
        msg.setBoolean(quickfix.field.IOINaturalFlag.FIELD, true);
        msg.setInt(quickfix.field.NoIOIQualifiers.FIELD, 2);
        quickfix.fix43.IndicationOfInterest.NoIOIQualifiers grpe1 = new quickfix.fix43.IndicationOfInterest.NoIOIQualifiers();
        grpe1.setChar(quickfix.field.IOIQualifier.FIELD, '2');
        msg.addGroup(grpe1);
        quickfix.fix43.IndicationOfInterest.NoIOIQualifiers grpe2 = new quickfix.fix43.IndicationOfInterest.NoIOIQualifiers();
        grpe2.setChar(quickfix.field.IOIQualifier.FIELD, '3');
        msg.addGroup(grpe2);
        msg.setString(quickfix.field.Text.FIELD, "some text");
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(encText));
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.URLLink.FIELD, "www.cbot.com");
        quickfix.fix43.IndicationOfInterest.NoRoutingIDs grpr1 = new quickfix.fix43.IndicationOfInterest.NoRoutingIDs();
        grpr1.setInt(quickfix.field.RoutingType.FIELD, RoutingType.BlockFirm.getValue());
        grpr1.setString(quickfix.field.RoutingID.FIELD, "route 1");
        msg.addGroup(grpr1);
        quickfix.fix43.IndicationOfInterest.NoRoutingIDs grpr2 = new quickfix.fix43.IndicationOfInterest.NoRoutingIDs();
        grpr2.setInt(quickfix.field.RoutingType.FIELD, RoutingType.TargetFirm.getValue());
        grpr2.setString(quickfix.field.RoutingID.FIELD, "route 2");
        msg.addGroup(grpr2);
        // SpreadOrBenchmarkCurveData43
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(msg);
        
        msg.setChar(quickfix.field.Benchmark.FIELD, Benchmark.CURVE.getValue());
    }

    public void populate(IOIMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.setIoiRefID("ref ID");
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());
        msg.setSide(Side.Borrow);
        msg.setQuantityType(QuantityType.BONDS);
        msg.setCurrency(Currency.AustralianDollar);
        msg.setIoiQty(IOIQty.Large);
        msg.setPriceType(PriceType.FixedAmount);
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
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData43TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());

        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("route id 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.BlockFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("route id 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockList);
        msg.setBenchmark(Benchmark.CURVE);
    }

    public void check(quickfix.fix43.IndicationOfInterest expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.IOIID.FIELD), actual.getIoiID());
        assertEquals(expected.getString(quickfix.field.IOITransType.FIELD), actual.getIoiTransType().getValue());
        assertEquals(expected.getString(quickfix.field.IOIRefID.FIELD), actual.getIoiRefID());
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.IOIQty.FIELD), actual.getIoiQty().getValue());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getChar(quickfix.field.IOIQltyInd.FIELD), actual.getIoiQltyInd().getValue());
        assertEquals(expected.getBoolean(quickfix.field.IOINaturalFlag.FIELD), actual.getIoiNaturalFlag().booleanValue());
        // IOIQualifiers
        quickfix.fix43.IndicationOfInterest.NoIOIQualifiers grpe1 = new quickfix.fix43.IndicationOfInterest.NoIOIQualifiers();
        expected.getGroup(1, grpe1);
        assertEquals(grpe1.getChar(quickfix.field.IOIQualifier.FIELD), actual.getIoiQualifiers()[0].getIoiQualifier().charValue());
        quickfix.fix43.IndicationOfInterest.NoIOIQualifiers grpe2 = new quickfix.fix43.IndicationOfInterest.NoIOIQualifiers();
        expected.getGroup(2, grpe2);
        assertEquals(grpe2.getChar(quickfix.field.IOIQualifier.FIELD), actual.getIoiQualifiers()[1].getIoiQualifier().charValue());
        
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedText());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.URLLink.FIELD), actual.getUrlLink());
        // RoutingID
        assertEquals(expected.getInt(quickfix.field.NoRoutingIDs.FIELD), actual.getNoRoutingIDs().intValue());
        quickfix.fix43.IndicationOfInterest.NoRoutingIDs grpr1 = new quickfix.fix43.IndicationOfInterest.NoRoutingIDs();
        expected.getGroup(1, grpr1);
        assertEquals(grpr1.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(grpr1.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[0].getRoutingID());
        quickfix.fix43.IndicationOfInterest.NoRoutingIDs grpr2 = new quickfix.fix43.IndicationOfInterest.NoRoutingIDs();
        expected.getGroup(2, grpr2);
        assertEquals(grpr2.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(grpr2.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[1].getRoutingID());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected, actual.getSpreadOrBenchmarkCurveData());
        assertEquals(expected.getChar(quickfix.field.Benchmark.FIELD), actual.getBenchmark().getValue());
    }

    public void check(IOIMsg expected, quickfix.fix43.IndicationOfInterest actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getString(quickfix.field.IOIID.FIELD));
        assertEquals(expected.getIoiTransType().getValue(), actual.getString(quickfix.field.IOITransType.FIELD));
        assertEquals(expected.getIoiRefID(), actual.getString(quickfix.field.IOIRefID.FIELD));
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getQuantityType().getValue(), actual.getInt(quickfix.field.QuantityType.FIELD));
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertEquals(expected.getIoiQty().getValue(), actual.getString(quickfix.field.IOIQty.FIELD));
        assertEquals(expected.getPriceType().getValue(), actual.getInt(quickfix.field.PriceType.FIELD));
        assertEquals(expected.getPrice().doubleValue(), actual.getDouble(quickfix.field.Price.FIELD), 0.001);
        assertTimeEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getIoiQltyInd().getValue(), actual.getChar(quickfix.field.IOIQltyInd.FIELD));
        assertEquals(expected.getIoiNaturalFlag().booleanValue(), actual.getBoolean(quickfix.field.IOINaturalFlag.FIELD));
        // IOIQualifier
        assertEquals(expected.getNoIOIQualifiers().intValue(), actual.getInt(quickfix.field.NoIOIQualifiers.FIELD));
        quickfix.fix43.IndicationOfInterest.NoIOIQualifiers grp1 = new quickfix.fix43.IndicationOfInterest.NoIOIQualifiers();
        actual.getGroup(1, grp1);
        assertEquals(expected.getIoiQualifiers()[0].getIoiQualifier().charValue(), grp1.getIOIQualifier().getValue());
        quickfix.fix43.IndicationOfInterest.NoIOIQualifiers grp2 = new quickfix.fix43.IndicationOfInterest.NoIOIQualifiers();
        actual.getGroup(2, grp2);
        assertEquals(expected.getIoiQualifiers()[1].getIoiQualifier().charValue(), grp2.getIOIQualifier().getValue());
        quickfix.fix43.IndicationOfInterest.NoIOIQualifiers grp3 = new quickfix.fix43.IndicationOfInterest.NoIOIQualifiers();
        actual.getGroup(3, grp3);
        assertEquals(expected.getIoiQualifiers()[2].getIoiQualifier().charValue(), grp3.getIOIQualifier().getValue());
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(TEST_CHARSET));
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD));
        assertEquals(expected.getUrlLink(), actual.getString(quickfix.field.URLLink.FIELD));
        // ROutingIDs
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getInt(quickfix.field.NoRoutingIDs.FIELD));
        quickfix.fix43.IndicationOfInterest.NoRoutingIDs grpr1 = new quickfix.fix43.IndicationOfInterest.NoRoutingIDs();
        actual.getGroup(1, grpr1);
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), grpr1.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), grpr1.getRoutingID().getValue());
        quickfix.fix43.IndicationOfInterest.NoRoutingIDs grpr2 = new quickfix.fix43.IndicationOfInterest.NoRoutingIDs();
        actual.getGroup(2, grpr2);
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), grpr2.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), grpr2.getRoutingID().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual);
        assertEquals(expected.getBenchmark().getValue(), actual.getChar(quickfix.field.Benchmark.FIELD));
    }

    public void check(IOIMsg expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getIoiID());
        assertEquals(expected.getIoiTransType().getValue(), actual.getIoiTransType().getValue());
        assertEquals(expected.getIoiRefID(), actual.getIoiRefID());
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getQuantityType().getValue(), actual.getQuantityType().getValue());
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getIoiQty(), actual.getIoiQty());
        assertEquals(expected.getPriceType().getValue(), actual.getPriceType().getValue());
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
        assertEquals(expected.getBenchmark().getValue(), actual.getBenchmark().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData43TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
    }
}
