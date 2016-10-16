/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg50SP2TestData.java
 *
 * $Id: IOIMsg50SP2TestData.java,v 1.3 2011-10-29 09:42:19 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.comp.impl.v50sp1.ApplicationSequenceControl50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.impl.v50sp2.LegIOIGroup50SP2TestData;
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
 * Test utility for IOIMsg50SP2 message class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 11:01:10 AM
 */
public class IOIMsg50SP2TestData extends MsgTest {

    private static final IOIMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new IOIMsg50SP2TestData();
    }

    public static IOIMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(IOIMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.setIoiRefID("ref ID");
        // ApplicationSequenceControl
        msg.setApplicationSequenceControl();
        ApplicationSequenceControl50SP1TestData.getInstance().populate(msg.getApplicationSequenceControl());
        // Instrument
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // Parties
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setSide(Side.Borrow);
        msg.setQtyType(QtyType.Contracts);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());
        msg.setIoiQty(IOIQty.Large);
        msg.setCurrency(Currency.AustralianDollar);
        // Stipulations
        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());
        // LegIOIGroup
        msg.setNoLegs(new Integer(2));
        LegIOIGroup50SP2TestData.getInstance().populate1(msg.getLegIOIGroups()[0]);
        LegIOIGroup50SP2TestData.getInstance().populate2(msg.getLegIOIGroups()[1]);

        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(new Double(12.345));
        msg.setValidUntilTime(new Date());
        msg.setIoiQltyInd(IOIQltyInd.High);
        msg.setIoiNaturalFlag(Boolean.FALSE);
        msg.setNoIOIQualifiers(new Integer(3));
        msg.getIoiQualifiers()[0].setIoiQualifier(new Character('A'));
        msg.getIoiQualifiers()[1].setIoiQualifier(new Character('D'));
        msg.getIoiQualifiers()[2].setIoiQualifier(new Character('P'));
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
        msg.setNoRoutingIDs(new Integer(2));
        msg.getRoutingIDGroups()[0].setRoutingID("route id 1");
        msg.getRoutingIDGroups()[0].setRoutingType(RoutingType.BlockFirm);
        msg.getRoutingIDGroups()[1].setRoutingID("route id 2");
        msg.getRoutingIDGroups()[1].setRoutingType(RoutingType.BlockList);
    }
    
    public void check(IOIMsg expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getIoiID());
        assertEquals(expected.getIoiTransType().getValue(), actual.getIoiTransType().getValue());
        assertEquals(expected.getIoiRefID(), actual.getIoiRefID());
        // ApplicationSequenceControl check
        ApplicationSequenceControl50SP1TestData.getInstance().check(expected.getApplicationSequenceControl(), actual.getApplicationSequenceControl());
        // Instrument check
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // Parties
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getQtyType().getValue(), actual.getQtyType().getValue());
        // OrderQtyData
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        assertEquals(expected.getIoiQty(), actual.getIoiQty());
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        // Stipulations check
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        // LegIOIGroup check
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        LegIOIGroup50SP2TestData.getInstance().check(expected.getLegIOIGroups()[0], actual.getLegIOIGroups()[0]);
        LegIOIGroup50SP2TestData.getInstance().check(expected.getLegIOIGroups()[1], actual.getLegIOIGroups()[1]);

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
