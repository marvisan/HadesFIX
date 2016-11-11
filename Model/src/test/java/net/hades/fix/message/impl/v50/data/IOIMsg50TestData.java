/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg50TestData.java
 *
 * $Id: IOIMsg50TestData.java,v 1.3 2011-10-29 09:42:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.group.impl.v50.LegIOIGroup50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.RoutingType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for IOIMsg50 message class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 11:01:10 AM
 */
public class IOIMsg50TestData extends MsgTest {

    private static final IOIMsg50TestData INSTANCE;

    static {
        INSTANCE = new IOIMsg50TestData();
    }

    public static IOIMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix50.IOI msg) throws Exception {
        TestUtils.populateQuickFIX50HeaderAll(msg);
        msg.setString(quickfix.field.IOIID.FIELD, "IOI ID");
        msg.setString(quickfix.field.IOITransType.FIELD, IOITransType.New.getValue());
        msg.setString(quickfix.field.IOIRefID.FIELD, "Ref ID");
        // Instrument
        quickfix.fix50.component.Instrument instr =  new quickfix.fix50.component.Instrument();
        Instrument50TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix50.component.FinancingDetails findet = new quickfix.fix50.component.FinancingDetails();
        FinancingDetails50TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix50.IOI.NoUnderlyings grpl1 = new quickfix.fix50.IOI.NoUnderlyings();
        quickfix.fix50.component.UnderlyingInstrument uinst1 = new quickfix.fix50.component.UnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate1(uinst1);
        grpl1.set(uinst1);
        grpl1.set(uinst1.getUnderlyingStipulations());
        msg.addGroup(grpl1);
        quickfix.fix50.IOI.NoUnderlyings grpl2 = new quickfix.fix50.IOI.NoUnderlyings();
        quickfix.fix50.component.UnderlyingInstrument uinst2 = new quickfix.fix50.component.UnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate2(uinst2);
        grpl2.set(uinst2);
        grpl2.set(uinst2.getUnderlyingStipulations());
        msg.addGroup(grpl2);

        msg.setChar(quickfix.field.Side.FIELD, Side.BuyMinus.getValue());
        msg.setInt(quickfix.field.QtyType.FIELD, QtyType.Units.getValue());
        // OrderQtyData
        OrderQtyData50TestData.getInstance().populate(msg);
        // Stipulations
        quickfix.fix50.component.Stipulations stips = new quickfix.fix50.component.Stipulations();
        Stipulations50TestData.getInstance().populate(stips);
        msg.set(stips);

        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        msg.setString(quickfix.field.IOIQty.FIELD, IOIQty.Large.getValue());
        // LegIOI
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix50.IOI.NoLegs leg1 = new quickfix.fix50.IOI.NoLegs();
        LegIOIGroup50TestData.getInstance().populate1(leg1);
        msg.addGroup(leg1);
        quickfix.fix50.IOI.NoLegs leg2 = new quickfix.fix50.IOI.NoLegs();
        LegIOIGroup50TestData.getInstance().populate2(leg2);
        msg.addGroup(leg2);

        msg.setInt(quickfix.field.PriceType.FIELD, 2);
        msg.setDouble(quickfix.field.Price.FIELD, 12.556);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setChar(quickfix.field.IOIQltyInd.FIELD, 'L');
        msg.setBoolean(quickfix.field.IOINaturalFlag.FIELD, true);
        msg.setInt(quickfix.field.NoIOIQualifiers.FIELD, 2);
        // IOIQualifiers
        quickfix.fix50.IOI.NoIOIQualifiers grpe1 = new quickfix.fix50.IOI.NoIOIQualifiers();
        grpe1.setChar(quickfix.field.IOIQualifier.FIELD, 'A');
        msg.addGroup(grpe1);
        quickfix.fix50.IOI.NoIOIQualifiers grpe2 = new quickfix.fix50.IOI.NoIOIQualifiers();
        grpe2.setChar(quickfix.field.IOIQualifier.FIELD, 'D');
        msg.addGroup(grpe2);

        msg.setString(quickfix.field.Text.FIELD, "some text");
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 6);
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(encText));
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.URLLink.FIELD, "www.cbot.com");
        // RoutingID
        quickfix.fix50.IOI.NoRoutingIDs grpr1 = new quickfix.fix50.IOI.NoRoutingIDs();
        grpr1.setInt(quickfix.field.RoutingType.FIELD, RoutingType.BlockFirm.getValue());
        grpr1.setString(quickfix.field.RoutingID.FIELD, "route 1");
        msg.addGroup(grpr1);
        quickfix.fix50.IOI.NoRoutingIDs grpr2 = new quickfix.fix50.IOI.NoRoutingIDs();
        grpr2.setInt(quickfix.field.RoutingType.FIELD, RoutingType.TargetFirm.getValue());
        grpr2.setString(quickfix.field.RoutingID.FIELD, "route 2");
        msg.addGroup(grpr2);
        // SpreadOrBenchmarkCurveData
        quickfix.fix50.component.SpreadOrBenchmarkCurveData spread = new quickfix.fix50.component.SpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(spread);
        msg.set(spread);
        // YieldData
        quickfix.fix50.component.YieldData yield = new quickfix.fix50.component.YieldData();
        YieldData50TestData.getInstance().populate(yield);
        msg.set(yield);
    }

    public void populate(IOIMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.setIoiRefID("ref ID");
        // Instrument
        Instrument50TestData.getInstance().populate(msg.getInstrument());
        // Parties
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setSide(Side.Borrow);
        msg.setQtyType(QtyType.Contracts);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());
        msg.setIoiQty(IOIQty.Large);
        msg.setCurrency(Currency.AustralianDollar);
        // Stipulations
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());
        // LegIOIGroup50
        msg.setNoLegs(new Integer(2));
        LegIOIGroup50TestData.getInstance().populate1(msg.getLegIOIGroups()[0]);
        LegIOIGroup50TestData.getInstance().populate2(msg.getLegIOIGroups()[1]);

        msg.setPriceType(PriceType.Discount);
        msg.setPrice(new Double(12.345));
        msg.setValidUntilTime(new Date());
        msg.setIoiQltyInd(IOIQltyInd.High);
        msg.setIoiNaturalFlag(Boolean.FALSE);
        msg.setNoIOIQualifiers(new Integer(3));
        msg.getIoiQualifiers()[0].setIoiQualifier(new Character('A'));
        msg.getIoiQualifiers()[1].setIoiQualifier(new Character('D'));
        msg.getIoiQualifiers()[2].setIoiQualifier(new Character('O'));
        msg.setText("I want these shares!");
        msg.setEncodedTextLen(new Integer(6));
        byte[] encText = new byte[] {(byte) 55, (byte) 56, (byte) 68, (byte) 50,
            (byte) 61, (byte) 80};
        msg.setEncodedText(encText);
        msg.setTransactTime(new Date());
        msg.setUrlLink("www.shares.com");
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        // YieldData
        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());
        // RoutingID
        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("route id 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.BlockFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("route id 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockList);
    }

    public void check(quickfix.fix50.IOI expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.IOIID.FIELD), actual.getIoiID());
        assertEquals(expected.getString(quickfix.field.IOITransType.FIELD), actual.getIoiTransType().getValue());
        assertEquals(expected.getString(quickfix.field.IOIRefID.FIELD), actual.getIoiRefID());
        // Instrument check
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.IOIQty.FIELD), actual.getIoiQty());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getChar(quickfix.field.IOIQltyInd.FIELD), actual.getIoiQltyInd().getValue());
        assertEquals(expected.getBoolean(quickfix.field.IOINaturalFlag.FIELD), actual.getIoiNaturalFlag().booleanValue());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getInt(quickfix.field.NoUnderlyings.FIELD), actual.getNoUnderlyings().intValue());
        quickfix.fix50.IOI.NoUnderlyings grpl1 = new quickfix.fix50.IOI.NoUnderlyings();
        expected.getGroup(1, grpl1);
        quickfix.fix50.component.UnderlyingInstrument uinst1 = new quickfix.fix50.component.UnderlyingInstrument();
        grpl1.get(uinst1);
        quickfix.fix50.component.UnderlyingStipulations unstip1 = new quickfix.fix50.component.UnderlyingStipulations();
        grpl1.get(unstip1);
        uinst1.set(unstip1);
        UnderlyingInstrument50TestData.getInstance().check(uinst1, actual.getUnderlyingInstruments()[0]);
        quickfix.fix50.IOI.NoUnderlyings grpl2 = new quickfix.fix50.IOI.NoUnderlyings();
        expected.getGroup(2, grpl2);
        quickfix.fix50.component.UnderlyingInstrument uinst2 = new quickfix.fix50.component.UnderlyingInstrument();
        grpl2.get(uinst2);
        quickfix.fix50.component.UnderlyingStipulations unstip2 = new quickfix.fix50.component.UnderlyingStipulations();
        grpl2.get(unstip2);
        uinst2.set(unstip2);
        UnderlyingInstrument50TestData.getInstance().check(uinst2, actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getString(quickfix.field.IOIQty.FIELD), actual.getIoiQty());
        assertEquals(expected.getInt(quickfix.field.QtyType.FIELD), actual.getQtyType().getValue());
        // OrderQtyData check
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        // Stipulations check
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        // LegIOIGroup50 check
        assertEquals(expected.getInt(quickfix.field.NoLegs.FIELD), actual.getLegIOIGroups().length);
        quickfix.fix50.IOI.NoLegs leg = new quickfix.fix50.IOI.NoLegs();
        expected.getGroup(1, leg);
        LegIOIGroup50TestData.getInstance().check(leg, actual.getLegIOIGroups()[0]);

        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getChar(quickfix.field.IOIQltyInd.FIELD), actual.getIoiQltyInd().getValue());
        assertEquals(expected.getBoolean(quickfix.field.IOINaturalFlag.FIELD), actual.getIoiNaturalFlag().booleanValue());
        assertEquals(expected.getInt(quickfix.field.NoIOIQualifiers.FIELD), actual.getIoiQualifiers().length);
        quickfix.fix50.IOI.NoIOIQualifiers grpe1 = new quickfix.fix50.IOI.NoIOIQualifiers();
        expected.getGroup(1, grpe1);
        assertEquals(grpe1.getChar(quickfix.field.IOIQualifier.FIELD), actual.getIoiQualifiers()[0].getIoiQualifier().charValue());
        quickfix.fix50.IOI.NoIOIQualifiers grpe2 = new quickfix.fix50.IOI.NoIOIQualifiers();
        expected.getGroup(2, grpe2);
        assertEquals(grpe2.getChar(quickfix.field.IOIQualifier.FIELD), actual.getIoiQualifiers()[1].getIoiQualifier().charValue());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedText());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.URLLink.FIELD), actual.getUrlLink());
        assertEquals(expected.getInt(quickfix.field.NoRoutingIDs.FIELD), actual.getNoRoutingIDs().intValue());
        quickfix.fix50.IOI.NoRoutingIDs grpr1 = new quickfix.fix50.IOI.NoRoutingIDs();
        expected.getGroup(1, grpr1) ;
        assertEquals(grpr1.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(grpr1.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[0].getRoutingID());
        quickfix.fix50.IOI.NoRoutingIDs grpr2 = new quickfix.fix50.IOI.NoRoutingIDs();
        expected.getGroup(2, grpr2) ;
        assertEquals(grpr2.getInt(quickfix.field.RoutingType.FIELD), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(grpr2.getString(quickfix.field.RoutingID.FIELD), actual.getRoutingIDGroups()[1].getRoutingID());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }

    public void check(IOIMsg expected, quickfix.fix50.IOI actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getString(quickfix.field.IOIID.FIELD));
        assertEquals(expected.getIoiTransType().getValue(), actual.getString(quickfix.field.IOITransType.FIELD));
        assertEquals(expected.getIoiRefID(), actual.getString(quickfix.field.IOIRefID.FIELD));
        // Instrument check
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getInt(quickfix.field.NoUnderlyings.FIELD));
        quickfix.fix50.IOI.NoUnderlyings grpu1 = new quickfix.fix50.IOI.NoUnderlyings();
        actual.getGroup(1, grpu1);
        quickfix.fix50.component.UnderlyingInstrument ui1 = new quickfix.fix50.component.UnderlyingInstrument();
        grpu1.get(ui1);
        ui1.set(grpu1.getUnderlyingStipulations());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], ui1);
        quickfix.fix50.IOI.NoUnderlyings grpu2 = new quickfix.fix50.IOI.NoUnderlyings();
        actual.getGroup(2, grpu2);
        quickfix.fix50.component.UnderlyingInstrument ui2 = new quickfix.fix50.component.UnderlyingInstrument();
        grpu2.get(ui2);
        ui2.set(grpu2.getUnderlyingStipulations());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], ui2);
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getQtyType().getValue(), actual.getInt(quickfix.field.QtyType.FIELD));
        // OrderQtyData
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        assertEquals(expected.getIoiQty(), actual.getString(quickfix.field.IOIQty.FIELD));
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        // Stipulations check
//        Stipulations50TestData.getInstance().check(msg.getStipulations(), actual);
        // LegIOIGroup50 check
        quickfix.fix50.IOI.NoLegs leg = new quickfix.fix50.IOI.NoLegs();
        actual.getGroup(1, leg);
        LegIOIGroup50TestData.getInstance().check(leg, expected.getLegIOIGroups()[0]);
//        LegIOIGroup50TestData.getInstance().check2(msg.getLegIOIGroups()[1], actual);

        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertEquals(expected.getIoiQty(), actual.getString(quickfix.field.IOIQty.FIELD));
        assertEquals(expected.getPriceType().getValue(), actual.getInt(quickfix.field.PriceType.FIELD));
        assertEquals(expected.getPrice().doubleValue(), actual.getDouble(quickfix.field.Price.FIELD), 0.001);
        assertTimeEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getIoiQltyInd().getValue(), actual.getChar(quickfix.field.IOIQltyInd.FIELD));
        assertEquals(expected.getIoiNaturalFlag().booleanValue(), actual.getBoolean(quickfix.field.IOINaturalFlag.FIELD));
        // IOIQualifier
        assertEquals(expected.getNoIOIQualifiers().intValue(), actual.getInt(quickfix.field.NoIOIQualifiers.FIELD));
        quickfix.fix50.IOI.NoIOIQualifiers grp1 = new quickfix.fix50.IOI.NoIOIQualifiers();
        actual.getGroup(1, grp1);
        assertEquals(expected.getIoiQualifiers()[0].getIoiQualifier().charValue(), grp1.getIOIQualifier().getValue());
        quickfix.fix50.IOI.NoIOIQualifiers grp2 = new quickfix.fix50.IOI.NoIOIQualifiers();
        actual.getGroup(2, grp2);
        assertEquals(expected.getIoiQualifiers()[1].getIoiQualifier().charValue(), grp2.getIOIQualifier().getValue());
        quickfix.fix50.IOI.NoIOIQualifiers grp3 = new quickfix.fix50.IOI.NoIOIQualifiers();
        actual.getGroup(3, grp3);
        assertEquals(expected.getIoiQualifiers()[2].getIoiQualifier().charValue(), grp3.getIOIQualifier().getValue());
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(TEST_CHARSET));
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD));
        assertEquals(expected.getUrlLink(), actual.getString(quickfix.field.URLLink.FIELD));
        // RoutingIDs
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getInt(quickfix.field.NoRoutingIDs.FIELD));
        quickfix.fix50.IOI.NoRoutingIDs grpr1 = new quickfix.fix50.IOI.NoRoutingIDs();
        actual.getGroup(1, grpr1);
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), grpr1.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), grpr1.getRoutingID().getValue());
        quickfix.fix50.IOI.NoRoutingIDs grpr2 = new quickfix.fix50.IOI.NoRoutingIDs();
        actual.getGroup(2, grpr2);
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), grpr2.getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), grpr2.getRoutingID().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }

    public void check(IOIMsg expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getIoiID());
        assertEquals(expected.getIoiTransType().getValue(), actual.getIoiTransType().getValue());
        assertEquals(expected.getIoiRefID(), actual.getIoiRefID());
        // Instrument check
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Parties
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getQtyType().getValue(), actual.getQtyType().getValue());
        // OrderQtyData
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        assertEquals(expected.getIoiQty(), actual.getIoiQty());
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        // Stipulations check
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        // LegIOIGroup check
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        LegIOIGroup50TestData.getInstance().check(expected.getLegIOIGroups()[0], actual.getLegIOIGroups()[0]);
        LegIOIGroup50TestData.getInstance().check(expected.getLegIOIGroups()[1], actual.getLegIOIGroups()[1]);

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
        // RoutingIDs
        assertEquals(expected.getNoRoutingIDs().intValue(), actual.getNoRoutingIDs().intValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingType().getValue(), actual.getRoutingIDGroups()[0].getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[0].getRoutingID(), actual.getRoutingIDGroups()[0].getRoutingID());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingType().getValue(), actual.getRoutingIDGroups()[1].getRoutingType().getValue());
        assertEquals(expected.getRoutingIDGroups()[1].getRoutingID(), actual.getRoutingIDGroups()[1].getRoutingID());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }
}
