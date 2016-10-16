/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeReportOrderDetail50SP2TestData.java
 *
 * $Id: TradeReportOrderDetail50SP2TestData.java,v 1.1 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;


import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.TradeReportOrderDetail;
import net.hades.fix.message.comp.impl.v50.DisplayInstructions50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderRestrictions;
import net.hades.fix.message.type.OrigCustOrderCapacity;
import net.hades.fix.message.type.RefOrdIDReason;
import net.hades.fix.message.type.RefOrderIDSource;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for TradeReportOrderDetails50SP2 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class TradeReportOrderDetail50SP2TestData extends MsgTest {

    private static final TradeReportOrderDetail50SP2TestData INSTANCE;

    static {
        INSTANCE = new TradeReportOrderDetail50SP2TestData();
    }

    public static TradeReportOrderDetail50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradeReportOrderDetail comp) {
        Calendar cal = Calendar.getInstance();
        
        comp.setOrderID("ORD_8888");
        comp.setSecondaryOrderID("SEC_ORD_555");
        comp.setClOrdID("CLI_ORD_222");
        comp.setSecondaryClOrdID("SEC_CLI_ORD_777");
        comp.setListID("LIST_111");
        comp.setRefOrderID("REF_ORD_333");
        comp.setRefOrderIDSource(RefOrderIDSource.OrderID);
        comp.setRefOrdIDReason(RefOrdIDReason.OrderChanged);
        comp.setOrdType(OrdType.Stop);
        comp.setPrice(34.56d);
        comp.setStopPx(39.55d);
        comp.setExecInst(ExecInst.CallFirst.getValue());
        comp.setOrdStatus(OrdStatus.Replaced);
        
        comp.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(comp.getOrderQtyData());
        
        comp.setLeavesQty(39.88d);
        comp.setCumQty(32.3d);
        comp.setTimeInForce(TimeInForce.Opening);
        cal.set(2011, 10, 11, 11, 12, 22);
        comp.setExpireTime(cal.getTime());
        
        comp.setDisplayInstruction();
        DisplayInstructions50TestData.getInstance().populate(comp.getDisplayInstruction());
        
        comp.setOrderCapacity(OrderCapacity.Proprietary);
        comp.setOrderRestrictions(OrderRestrictions.Cross.getValue());
        comp.setBookingType(BookingType.RegularBooking);
        comp.setOrigCustOrderCapacity(OrigCustOrderCapacity.AllOther);
        comp.setOrderInputDevice("DEV1");
        comp.setLotType(LotType.BlockLot);
        cal.set(2011, 9, 1, 23, 11, 22);
        comp.setTransBkdTime(cal.getTime());
        cal.set(2011, 8, 1, 10, 2, 2);
        comp.setOrigOrdModTime(cal.getTime());
    }

    public void check(TradeReportOrderDetail expected, TradeReportOrderDetail actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getRefOrderID(), actual.getRefOrderID());
        assertEquals(expected.getRefOrderIDSource(), actual.getRefOrderIDSource());
        assertEquals(expected.getRefOrdIDReason(), actual.getRefOrdIDReason());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getOrdStatus(), actual.getOrdStatus());
        
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        
        assertEquals(expected.getLeavesQty(), actual.getLeavesQty());
        assertEquals(expected.getCumQty(), actual.getCumQty());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        
        DisplayInstructions50TestData.getInstance().check(expected.getDisplayInstruction(), actual.getDisplayInstruction());
        
        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getBookingType(), actual.getBookingType());
        assertEquals(expected.getOrigCustOrderCapacity(), actual.getOrigCustOrderCapacity());
        assertEquals(expected.getOrderInputDevice(), actual.getOrderInputDevice());
        assertUTCTimestampEquals(expected.getTransBkdTime(), actual.getTransBkdTime(), false);
        assertUTCTimestampEquals(expected.getOrigOrdModTime(), actual.getOrigOrdModTime(), false);
    }
}
