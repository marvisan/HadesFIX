/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderListGroup42TestData.java
 *
 * $Id: OrderListGroup42TestData.java,v 1.2 2011-10-29 09:42:27 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.OrderListGroup;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustomerOrFirm;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SecurityIDSource;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.SideValueInd;
import net.hades.fix.message.type.TimeInForce;

/**
 * Test utility for OrderListGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class OrderListGroup42TestData extends MsgTest {

    private static final OrderListGroup42TestData INSTANCE;

    static {
        INSTANCE = new OrderListGroup42TestData();
    }

    public static OrderListGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(OrderListGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD53773");
        grp.setListSeqNo(1);
        grp.setSettlInstMode(SettlInstMode.Default);
        grp.setClientID("client1");
        grp.setExecBroker("broker1");
        grp.setAccount("12735534784");

        grp.setNoAllocs(2);
        PreTradeAllocGroup42TestData.getInstance().populate1(grp.getAllocGroups()[0]);
        PreTradeAllocGroup42TestData.getInstance().populate2(grp.getAllocGroups()[1]);

        grp.setSettlType(SettlType.Cash.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 14, 12, 15, 33);
        grp.setSettlDate(cal.getTime());
        grp.setHandlInst(HandlInst.ManualOrder);
        grp.setExecInst(ExecInst.CallFirst.getValue());
        grp.setMinQty(12.44d);
        grp.setMaxFloor(33.66d);
        grp.setExDestination("exchange1");

        grp.setNoTradingSessions(new Integer(2));
        TradingSessionGroup42TestData.getInstance().populate1(grp.getTradingSessionGroups()[0]);
        TradingSessionGroup42TestData.getInstance().populate2(grp.getTradingSessionGroups()[1]);

        grp.setProcessCode(ProcessCode.Regular);
        grp.setSymbol("MOT");
        grp.setSymbolSfx("MOTOROLA Shares");
        grp.setSecurityID("MOTO");
        grp.setSecurityIDSource(SecurityIDSource.QUIK.getValue());
        grp.setSecurityType(SecurityType.MutualFund.getValue());
        grp.setMaturityMonthYear("200806w2");
        grp.setMaturityDay(new Integer(14));
        grp.setPutOrCall(PutOrCall.Call);
        grp.setStrikePrice(new Double(25.48));
        grp.setOptAttribute(new Character('T'));
        grp.setContractMultiplier(new Double("1.67"));
        grp.setCouponRate(new Double("10.55"));
        grp.setSecurityExchange("CO");
        grp.setIssuer("HADES");
        grp.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        grp.setEncodedIssuer(issuerDataExp);
        grp.setSecurityDesc("One Motorola Share");
        byte[] secDescDataExp = new byte[] {(byte) 20, (byte) 35, (byte) 46, (byte) 98,
            (byte) 179, (byte) 202, (byte) 226, (byte) 257};
        grp.setEncodedSecurityDescLen(new Integer(8));
        grp.setEncodedSecurityDesc(secDescDataExp);

        grp.setPrevClosePx(29.45d);
        grp.setSide(Side.Buy);
        grp.setSideValueInd(SideValueInd.SideValue_1);
        grp.setLocateReqd(Boolean.TRUE);
        cal.set(2010, 3, 14, 15, 18, 32);
        grp.setTransactTime(cal.getTime());
        grp.setOrderQty(88.22);
        grp.setCashOrderQty(22.44d);
        grp.setOrdType(OrdType.ForexLimit);
        grp.setPrice(22.67d);
        grp.setStopPx(44.67d);
        grp.setCurrency(Currency.AustralianDollar);
        grp.setComplianceID("compl ID 1");
        grp.setSolicitedFlag(Boolean.FALSE);
        grp.setIOIID("X25383535");
        grp.setQuoteID("G93846533");
        grp.setTimeInForce(TimeInForce.Day);
        cal.set(2010, 3, 14, 33, 33, 33);
        grp.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 16, 44, 44, 4);
        grp.setExpireDate(cal.getTime());
        cal.set(2010, 3, 17, 55, 55, 55);
        grp.setExpireTime(cal.getTime());
        grp.setGTBookingInst(GTBookingInst.AccumUntilVerballlyNotified);
        grp.setCommission(1.24d);
        grp.setCommType(CommType.CashDiscount);
        grp.setRule80A(Rule80A.ProgramOrderIndexArbAgency);
        grp.setForexReq(Boolean.TRUE);
        grp.setText("text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
        cal.set(2010, 3, 14, 17, 22, 0);
        grp.setSettlDate2(cal.getTime());
        grp.setOrderQty2(22.0d);
        grp.setPositionEffect(PositionEffect.Open);
        grp.setCoveredOrUncovered(CoveredOrUncovered.Covered);
        grp.setCustomerOrFirm(CustomerOrFirm.Firm);
        grp.setMaxShow(15.25d);
        grp.setPegOffsetValue(0.25d);
        grp.setDiscretionInst(DiscretionInst.RelatedToDisplayPrice);
        grp.setDiscretionOffsetValue(22.66d);
        grp.setClearingFirm("some company 1");
        grp.setClearingAccount("726535322");
    }

    public void populate2(OrderListGroup grp) throws UnsupportedEncodingException {
        grp.setClOrdID("CLORD53744");
        grp.setListSeqNo(2);
        grp.setSettlInstMode(SettlInstMode.RequestReject);
        grp.setClientID("client2");
        grp.setExecBroker("broker2");
        grp.setAccount("127355324342");

        grp.setSettlType(SettlType.Future.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 14, 34, 11, 22);
        grp.setSettlDate(cal.getTime());
        grp.setHandlInst(HandlInst.PrivateNoBroker);
        grp.setExecInst(ExecInst.Held.getValue());
        grp.setMinQty(12.55d);
        grp.setMaxFloor(33.77d);
        grp.setExDestination("exchange2");

        grp.setNoTradingSessions(new Integer(2));
        TradingSessionGroup42TestData.getInstance().populate1(grp.getTradingSessionGroups()[0]);
        TradingSessionGroup42TestData.getInstance().populate2(grp.getTradingSessionGroups()[1]);

        grp.setProcessCode(ProcessCode.PlanSponsor);
        grp.setSymbol("IBM");
        grp.setSymbolSfx("IBM Shares");
        grp.setSecurityID("IBMCO");
        grp.setSecurityIDSource(SecurityIDSource.ClearingHouse.getValue());
        grp.setSecurityType(SecurityType.BankersAcceptance.getValue());
        grp.setMaturityMonthYear("200807w2");
        grp.setMaturityDay(new Integer(15));
        grp.setPutOrCall(PutOrCall.Put);
        grp.setStrikePrice(new Double(25.48));
        grp.setOptAttribute(new Character('T'));
        grp.setContractMultiplier(new Double("1.69"));
        grp.setCouponRate(new Double("10.59"));
        grp.setSecurityExchange("CBOT");
        grp.setIssuer("MARV");
        grp.setEncodedIssuerLen(new Integer(8));
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 34, (byte) 44, (byte) 96,
            (byte) 177, (byte) 195, (byte) 224, (byte) 253};
        grp.setEncodedIssuer(issuerDataExp);
        grp.setSecurityDesc("One IBM Share");
        byte[] secDescDataExp = new byte[] {(byte) 20, (byte) 37, (byte) 46, (byte) 98,
            (byte) 179, (byte) 209, (byte) 226, (byte) 257};
        grp.setEncodedSecurityDescLen(new Integer(8));
        grp.setEncodedSecurityDesc(secDescDataExp);

        grp.setPrevClosePx(29.22d);
        grp.setSide(Side.Cross);
        grp.setSideValueInd(SideValueInd.SideValue_2);
        grp.setLocateReqd(Boolean.FALSE);
        cal.set(2010, 3, 14, 11, 22, 33);
        grp.setTransactTime(cal.getTime());
        grp.setOrderQty(88.45d);
        grp.setCashOrderQty(90.44d);
        grp.setOrdType(OrdType.Limit);
        grp.setPrice(50.67d);
        grp.setStopPx(51.67d);
        grp.setCurrency(Currency.UnitedStatesDollar);
        grp.setComplianceID("compl ID");
        grp.setSolicitedFlag(Boolean.TRUE);
        grp.setIOIID("X25388405");
        grp.setQuoteID("G93847464");
        grp.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 22, 22, 22);
        grp.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 16, 22, 22, 22);
        grp.setExpireDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 30, 44);
        grp.setExpireTime(cal.getTime());
        grp.setGTBookingInst(GTBookingInst.BookOutAllTrades);
        grp.setCommission(1.34d);
        grp.setCommType(CommType.Absolute);
        grp.setRule80A(Rule80A.Principal);
        grp.setForexReq(Boolean.FALSE);
        grp.setText("text");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedText(encodedText);
        cal.set(2010, 3, 14, 17, 30, 0);
        grp.setSettlDate2(cal.getTime());
        grp.setOrderQty2(25.0d);
        grp.setPositionEffect(PositionEffect.Close);
        grp.setCoveredOrUncovered(CoveredOrUncovered.Uncovered);
        grp.setCustomerOrFirm(CustomerOrFirm.Customer);
        grp.setMaxShow(15.35d);
        grp.setPegOffsetValue(0.65d);
        grp.setDiscretionInst(DiscretionInst.RelatedToMarketPrice);
        grp.setDiscretionOffsetValue(24.66d);
        grp.setClearingFirm("some company 2");
        grp.setClearingAccount("726535373");
    }

    public void check(OrderListGroup expected, OrderListGroup actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getListSeqNo(), actual.getListSeqNo());
        assertEquals(expected.getSettlInstMode(), actual.getSettlInstMode());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getAccount(), actual.getAccount());

        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getExDestination(), actual.getExDestination());

        assertEquals(expected.getNoTradingSessions().intValue(), actual.getNoTradingSessions().intValue());
        TradingSessionGroup42TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup42TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);

        assertEquals(expected.getProcessCode(), actual.getProcessCode());
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

        assertEquals(expected.getPrevClosePx(), actual.getPrevClosePx());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getSideValueInd(), actual.getSideValueInd());
        assertEquals(expected.getLocateReqd(), actual.getLocateReqd());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getCashOrderQty(), actual.getCashOrderQty());
        assertEquals(expected.getOrdType(), actual.getOrdType());
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
        assertEquals(expected.getGTBookingInst(), actual.getGTBookingInst());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getRule80A(), actual.getRule80A());
        assertEquals(expected.getForexReq(), actual.getForexReq());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2(), actual.getOrderQty2());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getCoveredOrUncovered(), actual.getCoveredOrUncovered());
        assertEquals(expected.getCustomerOrFirm(), actual.getCustomerOrFirm());
        assertEquals(expected.getMaxShow(), actual.getMaxShow());
        assertEquals(expected.getPegOffsetValue(), actual.getPegOffsetValue());
        assertEquals(expected.getDiscretionInst(), actual.getDiscretionInst());
        assertEquals(expected.getDiscretionOffsetValue(), actual.getDiscretionOffsetValue());
        assertEquals(expected.getClearingFirm(), actual.getClearingFirm());
        assertEquals(expected.getClearingAccount(), actual.getClearingAccount());
    }
}
