/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SideCrossOrdModGroup43TestData.java
 *
 * $Id: SideCrossOrdModGroup43TestData.java,v 1.2 2011-10-29 09:42:16 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43TestData;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.group.SideCrossOrdModGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for SideCrossOrdModGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/05/2011, 10:08:38 AM
 */
public class SideCrossOrdModGroup43TestData extends MsgTest {

    private static final SideCrossOrdModGroup43TestData INSTANCE;

    static {
        INSTANCE = new SideCrossOrdModGroup43TestData();
    }

    public static SideCrossOrdModGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SideCrossOrdModGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSide(Side.Buy);
        grp.setClOrdID("CLORD53773");
        grp.setSecondaryClOrdID("BBB363744");

        grp.setParties();
        Parties43TestData.getInstance().populate(grp.getParties());

        cal.set(2010, 3, 14, 12, 13, 13);
        grp.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 15, 12, 13, 13);
        grp.setTradeDate(cal.getTime());
        grp.setAccount("12735534784");
        grp.setAccountType(AccountType.HouseTrader);
        grp.setDayBookingInst(DayBookingInst.Accumulate);
        grp.setBookingUnit(BookingUnit.EachExecutionBookableUnit);
        grp.setPreallocMethod(PreallocMethod.DiscussFirst);
        grp.setAllocID("ALLOC_0099");

        grp.setNoAllocs(2);
        PreTradeAllocGroup43TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        PreTradeAllocGroup43TestData.getInstance().populate2(grp.getAllocGroups()[1]);

        grp.setQuantityType(QuantityType.CONTRACTS);

        grp.setOrderQtyData();
        OrderQtyData43TestData.getInstance().populate(grp.getOrderQtyData());
       
        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setOrderCapacity(OrderCapacity.Proprietary);
        grp.setOrderRestrictions("restrict");
        grp.setCustOrderCapacity(CustOrderCapacity.AllOther);
        grp.setForexReq(Boolean.TRUE);
        grp.setSettlCurrency(Currency.CanadianDollar);
        grp.setText("text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
        grp.setPositionEffect(PositionEffect.Open);
        grp.setCoveredOrUncovered(CoveredOrUncovered.Covered);
        grp.setCashMargin(CashMargin.Cash);
        grp.setClearingFeeIndicator(ClearingFeeIndicator.TradingOwnAcct1stYear);
        grp.setSolicitedFlag(Boolean.TRUE);
        grp.setSideComplianceID("COMPL-0000");
    }

    public void populate2(SideCrossOrdModGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSide(Side.Sell);
        grp.setClOrdID("CLORD53788");
        grp.setSecondaryClOrdID("BBB363755");

        grp.setParties();
        Parties43TestData.getInstance().populate(grp.getParties());

        cal.set(2010, 3, 16, 12, 13, 13);
        grp.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 17, 12, 13, 13);
        grp.setTradeDate(cal.getTime());
        grp.setAccount("12735534722");
        grp.setAccountType(AccountType.FloorTrader);
        grp.setDayBookingInst(DayBookingInst.CanTriggerBookingWithoutReference);
        grp.setBookingUnit(BookingUnit.AggregateExecutionsForSymbol);
        grp.setPreallocMethod(PreallocMethod.ProRata);
        grp.setAllocID("ALLOC_0088");

        grp.setNoAllocs(2);
        PreTradeAllocGroup43TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        PreTradeAllocGroup43TestData.getInstance().populate2(grp.getAllocGroups()[1]);

        grp.setQuantityType(QuantityType.BONDS);

        grp.setOrderQtyData();
        OrderQtyData43TestData.getInstance().populate(grp.getOrderQtyData());
       
        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

        grp.setOrderCapacity(OrderCapacity.AgentForAnotherMember);
        grp.setOrderRestrictions("comm");
        grp.setCustOrderCapacity(CustOrderCapacity.ClearingFirmTrading);
        grp.setForexReq(Boolean.FALSE);
        grp.setSettlCurrency(Currency.AustralianDollar);
        grp.setText("text 2");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 44, (byte) 49, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
        grp.setPositionEffect(PositionEffect.Close);
        grp.setCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        grp.setCashMargin(CashMargin.MarginClose);
        grp.setClearingFeeIndicator(ClearingFeeIndicator.CBOEMember);
        grp.setSolicitedFlag(Boolean.FALSE);
        grp.setSideComplianceID("COMPL-1111");
    }

    public void check(SideCrossOrdModGroup expected, SideCrossOrdModGroup actual) throws Exception {
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());

        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertDateEquals(expected.getTradeOriginationDate(), actual.getTradeOriginationDate());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getBookingUnit(), actual.getBookingUnit());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());
        assertEquals(expected.getAllocID(), actual.getAllocID());

        assertEquals(expected.getNoAllocs().intValue(), actual.getNoAllocs().intValue());
        PreTradeAllocGroup43TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        PreTradeAllocGroup43TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);
        
        assertEquals(expected.getQuantityType(), actual.getQuantityType());

        OrderQtyData43TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());

        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getCustOrderCapacity(), actual.getCustOrderCapacity());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getCoveredOrUncovered(), actual.getCoveredOrUncovered());
        assertEquals(expected.getCashMargin(), actual.getCashMargin());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getSideComplianceID(), actual.getSideComplianceID());
    }
}
