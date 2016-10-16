/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MultilegModificationRequestMsg44TestData.java
 *
 * $Id: MultilegModificationRequestMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.MultilegModificationRequestMsg;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43TestData;
import net.hades.fix.message.comp.impl.v44.DiscretionInstructions44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.comp.impl.v44.PegInstructions44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.group.impl.v44.LegOrdGroup44TestData;
import net.hades.fix.message.group.impl.v44.PreAllocMLegGroup44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.BookingUnit;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CashMargin;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.DayBookingInst;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MultiLegRptTypeReq;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PreallocMethod;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TargetStrategy;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for MultilegModificationRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MultilegModificationRequestMsg44TestData extends MsgTest {

    private static final MultilegModificationRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new MultilegModificationRequestMsg44TestData();
    }

    public static MultilegModificationRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MultilegModificationRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setOrderID("Order_1");
        msg.setOrigClOrdID("Orig_ord_1");
        msg.setClOrdID("AAA564567");
        msg.setSecondaryClOrdID("BBB363744");
        msg.setClOrdLinkID("SA88767788");
        // Parties
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
        
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setAccount("12735534784");
        msg.setAcctIDSource(AcctIDSource.Other);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setDayBookingInst(DayBookingInst.Accumulate);
        msg.setBookingUnit(BookingUnit.EachExecutionBookableUnit);
        msg.setPreallocMethod(PreallocMethod.ProRata);

        msg.setNoAllocs(new Integer(2));
        PreAllocMLegGroup44TestData.getInstance().populate1(msg.getAllocGroups()[0]);
        PreAllocMLegGroup44TestData.getInstance().populate2(msg.getAllocGroups()[1]);

        msg.setSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setCashMargin(CashMargin.Cash);
        msg.setClearingFeeIndicator(ClearingFeeIndicator.TradingOwnAcct1stYear);
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setMinQty(12.44d);
        msg.setMaxFloor(33.66d);
        msg.setExDestination("exchange");

        msg.setNoTradingSessions(new Integer(2));
        TradingSessionGroup43TestData.getInstance().populate1(msg.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(msg.getTradingSessionGroups()[1]);

        msg.setProcessCode(ProcessCode.Regular);
        msg.setSide(Side.Buy);

        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setPrevClosePx(29.45d);
        
        msg.setNoLegs(2);
        LegOrdGroup44TestData.getInstance().populate1(msg.getLegOrdGroups()[0]);
        LegOrdGroup44TestData.getInstance().populate2(msg.getLegOrdGroups()[1]);

        msg.setLocateReqd(Boolean.TRUE);
        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());
        msg.setQtyType(QtyType.Contracts);
        
        msg.setOrderQtyData();
        OrderQtyData43TestData.getInstance().populate(msg.getOrderQtyData());
        
        msg.setOrdType(OrdType.Limit);
        msg.setPriceType(PriceType.FixedAmount);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setComplianceID("compl ID");
        msg.setSolicitedFlag(Boolean.TRUE);
        msg.setIOIID("X25388405");
        msg.setQuoteID("G93847464");
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 22, 22, 22);
        msg.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 16, 22, 22, 22);
        msg.setExpireDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setGTBookingInst(GTBookingInst.BookOutAllTrades);

        msg.setCommissionData();
        CommissionData43TestData.getInstance().populate(msg.getCommissionData());

        msg.setOrderCapacity(OrderCapacity.Proprietary);
        msg.setOrderRestrictions("1");
        msg.setCustOrderCapacity(CustOrderCapacity.AllOther);
        msg.setForexReq(Boolean.FALSE);
        msg.setSettlCurrency(Currency.CanadianDollar);
        msg.setBookingType(BookingType.RegularBooking);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        cal.set(2010, 3, 14, 17, 30, 0);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        msg.setMaxShow(15.35d);
        
        msg.setPegInstructions();
        PegInstructions44TestData.getInstance().populate(msg.getPegInstructions());

        msg.setDiscretionInstructions();
        DiscretionInstructions44TestData.getInstance().populate(msg.getDiscretionInstructions());

        msg.setTargetStrategy(TargetStrategy.Participate.getValue());
        msg.setTargetStrategyParameters("p1 p2 p3");
        msg.setParticipationRate(34.33d);
        msg.setCancellationRights(CancellationRights.No_ExecutionOnly);
        msg.setMoneyLaunderingStatus(MoneyLaunderingStatus.Exempt_BelowLimit);
        msg.setRegistID("67628248247");
        msg.setDesignation("test");
        msg.setMultiLegRptTypeReq(MultiLegRptTypeReq.ReportInstrumentLegsOnly);
    }

    public void check(MultilegModificationRequestMsg expected, MultilegModificationRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getClOrdLinkID(), actual.getClOrdLinkID());

        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getDayBookingInst(), actual.getDayBookingInst());
        assertEquals(expected.getBookingUnit(), actual.getBookingUnit());
        assertEquals(expected.getPreallocMethod(), actual.getPreallocMethod());

        assertEquals(expected.getNoAllocs().intValue(), actual.getNoAllocs().intValue());
        PreAllocMLegGroup44TestData.getInstance().check(expected.getAllocGroups()[0], actual.getAllocGroups()[0]);
        PreAllocMLegGroup44TestData.getInstance().check(expected.getAllocGroups()[1], actual.getAllocGroups()[1]);

        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getCashMargin(), actual.getCashMargin());
        assertEquals(expected.getClearingFeeIndicator(), actual.getClearingFeeIndicator());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());

        assertEquals(expected.getNoTradingSessions().intValue(), actual.getNoTradingSessions().intValue());
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);

        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getSide(), actual.getSide());

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getPrevClosePx(), actual.getPrevClosePx());
        
        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        LegOrdGroup44TestData.getInstance().check(expected.getLegOrdGroups()[0], actual.getLegOrdGroups()[0]);
        LegOrdGroup44TestData.getInstance().check(expected.getLegOrdGroups()[1], actual.getLegOrdGroups()[1]);
                
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getQtyType(), actual.getQtyType());

        OrderQtyData43TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getIOIID(), actual.getIOIID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getGTBookingInst(), actual.getGTBookingInst());

        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());

        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getCustOrderCapacity(), actual.getCustOrderCapacity());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getBookingType(), actual.getBookingType());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getCoveredOrUncovered(), actual.getCoveredOrUncovered());
        assertEquals(expected.getMaxShow(), actual.getMaxShow());
        
        PegInstructions44TestData.getInstance().check(expected.getPegInstructions(), actual.getPegInstructions());

        DiscretionInstructions44TestData.getInstance().check(expected.getDiscretionInstructions(), actual.getDiscretionInstructions());

        assertEquals(expected.getTargetStrategy(), actual.getTargetStrategy());
        assertEquals(expected.getTargetStrategyParameters(), actual.getTargetStrategyParameters());
        assertEquals(expected.getParticipationRate(), actual.getParticipationRate());
        assertEquals(expected.getCancellationRights(), actual.getCancellationRights());
        assertEquals(expected.getMoneyLaunderingStatus(), actual.getMoneyLaunderingStatus());
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getDesignation(), actual.getDesignation());
        assertEquals(expected.getMultiLegRptTypeReq(), actual.getMultiLegRptTypeReq());
    }
}
