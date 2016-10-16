/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg42TestData.java
 *
 * $Id: ExecutionReportMsg42TestData.java,v 1.3 2011-10-29 09:42:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v40.MiscFeeGroup40TestData;
import net.hades.fix.message.group.impl.v42.ContraBrokerGroup42TestData;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExecInst;
import net.hades.fix.message.type.ExecRestatementReason;
import net.hades.fix.message.type.ExecTransType;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.LastCapacity;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for ExecutionReportMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ExecutionReportMsg42TestData extends MsgTest {

    private static final ExecutionReportMsg42TestData INSTANCE;

    static {
        INSTANCE = new ExecutionReportMsg42TestData();
    }

    public static ExecutionReportMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ExecutionReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setOrderID("XXX9374994");
        msg.setSecondaryOrderID("SSS778877");
        msg.setClOrdID("AAA564567");
        msg.setOrigClOrdID("COD003883");
        msg.setClientID("client");
        msg.setExecBroker("broker");

        msg.setNoContraBrokers(2);
        ContraBrokerGroup42TestData.getInstance().populate1(msg.getContraBrokers()[0]);
        ContraBrokerGroup42TestData.getInstance().populate2(msg.getContraBrokers()[1]);

        msg.setListID("LST44555");
        msg.setExecID("EXEC273663");
        msg.setExecTransType(ExecTransType.Cancel);
        msg.setExecRefID("EXEC836684");
        msg.setExecType(ExecType.Replace);
        msg.setOrdStatus(OrdStatus.Replaced);
        msg.setOrdRejReason(OrdRejReason.DuplicateOrder);
        msg.setExecRestatementReason(ExecRestatementReason.BrokerOption);
        msg.setAccount("12735534784");
        msg.setSettlType(SettlType.Cash.getValue());
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setSettlDate(cal.getTime());
        msg.setSymbol("BHP.AX");
        msg.setSymbolSfx("CDDF");
        msg.setSecurityID("BHP");
        msg.setSecurityIDSource("BHP-src");
        msg.setSecurityType(SecurityType.Cash.getValue());
        msg.setMaturityMonthYear("112011");
        msg.setMaturityDay(3);
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(22.22d);
        msg.setOptAttribute('0');
        msg.setContractMultiplier(1.22d);
        msg.setCouponRate(1.22d);
        msg.setSecurityExchange("ASX");
        msg.setIssuer("issuer");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] encodedIssuer = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(encodedIssuer);
        msg.setSecurityDesc("description");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encodedSecDesc = new byte[] {(byte) 18, (byte) 34, (byte) 45, (byte) 97,
            (byte) 177, (byte) 200, (byte) 224, (byte) 253};
        msg.setEncodedSecurityDesc(encodedSecDesc);
        msg.setSide(Side.Buy);
        msg.setOrderQty(88.45d);
        msg.setCashOrderQty(15.22d);
        msg.setOrdType(OrdType.Limit);
        msg.setPrice(50.67d);
        msg.setStopPx(51.67d);
        msg.setPegOffsetValue(0.33d);
        msg.setDiscretionInst(DiscretionInst.RelatedToMarketPrice);
        msg.setDiscretionOffsetValue(0.22d);
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setComplianceID("COMP_ID");
        msg.setSolicitedFlag(Boolean.TRUE);
        msg.setTimeInForce(TimeInForce.Opening);
        cal.set(2010, 3, 14, 11, 11, 11);
        msg.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 15, 12, 12, 12);
        msg.setExpireDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 30, 44);
        msg.setExpireTime(cal.getTime());
        msg.setExecInst(ExecInst.CallFirst.getValue());
        msg.setRule80A(Rule80A.Principal);
        msg.setLastQty(23.44d);
        msg.setLastPx(33.33d);
        msg.setLastSpotRate(13.12d);
        msg.setLastForwardPoints(2.22d);
        msg.setLastMkt("ASX");
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setLastCapacity(LastCapacity.Principal);
        msg.setLeavesQty(23.66d);
        msg.setCumQty(39.44d);
        msg.setAvgPx(33.44d);
        msg.setDayOrderQty(22.34d);
        msg.setDayCumQty(123.45d);
        msg.setDayAvgPx(23.35d);
        msg.setGTBookingInst(GTBookingInst.BookOutAllTrades);
        cal.set(2010, 3, 14, 12, 55, 55);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 12, 56, 56);
        msg.setTransactTime(cal.getTime());
        msg.setReportToExch(Boolean.TRUE);
        msg.setCommission(1.34d);
        msg.setCommType(CommType.Absolute);
        msg.setGrossTradeAmt(35.44d);
        msg.setNetMoney(44.45d);
        msg.setSettlCurrAmt(22.34d);
        msg.setSettlCurrency(Currency.AustralianDollar);
        msg.setSettlCurrFxRate(23.33d);
        msg.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        msg.setHandlInst(HandlInst.ManualOrder);
        msg.setMinQty(23.50d);
        msg.setMaxFloor(24.5d);
        msg.setPositionEffect(PositionEffect.Close);
        msg.setMaxShow(25.60d);
        msg.setText("some text here");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        cal.set(2010, 3, 24, 11, 22, 33);
        msg.setSettlDate2(cal.getTime());
        msg.setOrderQty2(33.78d);
        msg.setClearingFirm("company name");
        msg.setClearingAccount("273628494");
        msg.setMultilegReportingType(MultiLegReportingType.MultiLegSecurity);
        
        msg.setNoMiscFees(2);
        MiscFeeGroup40TestData.getInstance().populate1(msg.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().populate1(msg.getMiscFeeGroups()[1]);
    }

    public void check(ExecutionReportMsg expected, ExecutionReportMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrigClOrdID(), actual.getOrigClOrdID());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        ContraBrokerGroup42TestData.getInstance().check(expected.getContraBrokers()[0], actual.getContraBrokers()[0]);
        ContraBrokerGroup42TestData.getInstance().check(expected.getContraBrokers()[1], actual.getContraBrokers()[1]);

        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getExecTransType(), actual.getExecTransType());
        assertEquals(expected.getExecRefID(), actual.getExecRefID());
        assertEquals(expected.getExecType(), actual.getExecType());
        assertEquals(expected.getOrdStatus(), actual.getOrdStatus());
        assertEquals(expected.getOrdRejReason(), actual.getOrdRejReason());
        assertEquals(expected.getExecRestatementReason(), actual.getExecRestatementReason());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay(), actual.getMaturityDay());
        assertEquals(expected.getPutOrCall(), actual.getPutOrCall());
        assertEquals(expected.getStrikePrice(), actual.getStrikePrice());
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getContractMultiplier(), actual.getContractMultiplier());
        assertEquals(expected.getCouponRate(), actual.getCouponRate());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getOrderQty(), actual.getOrderQty());
        assertEquals(expected.getCashOrderQty(), actual.getCashOrderQty());
        assertEquals(expected.getOrdType(), actual.getOrdType());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getStopPx(), actual.getStopPx());
        assertEquals(expected.getPegOffsetValue(), actual.getPegOffsetValue());
        assertEquals(expected.getDiscretionInst(), actual.getDiscretionInst());
        assertEquals(expected.getDiscretionOffsetValue(), actual.getDiscretionOffsetValue());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getComplianceID(), actual.getComplianceID());
        assertEquals(expected.getSolicitedFlag(), actual.getSolicitedFlag());
        assertEquals(expected.getTimeInForce(), actual.getTimeInForce());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getExecInst(), actual.getExecInst());
        assertEquals(expected.getRule80A(), actual.getRule80A());
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getLastSpotRate(), actual.getLastSpotRate());
        assertEquals(expected.getLastForwardPoints(), actual.getLastForwardPoints());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getLastCapacity(), actual.getLastCapacity());
        assertEquals(expected.getLeavesQty(), actual.getLeavesQty());
        assertEquals(expected.getCumQty(), actual.getCumQty());
        assertEquals(expected.getAvgPx(), actual.getAvgPx());
        assertEquals(expected.getDayOrderQty(), actual.getDayOrderQty());
        assertEquals(expected.getDayCumQty(), actual.getDayCumQty());
        assertEquals(expected.getDayAvgPx(), actual.getDayAvgPx());
        assertEquals(expected.getGTBookingInst(), actual.getGTBookingInst());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getReportToExch(), actual.getReportToExch());
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getGrossTradeAmt(), actual.getGrossTradeAmt());
        assertEquals(expected.getNetMoney(), actual.getNetMoney());
        assertEquals(expected.getSettlCurrAmt(), actual.getSettlCurrAmt());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getSettlCurrFxRate(), actual.getSettlCurrFxRate());
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getHandlInst(), actual.getHandlInst());
        assertEquals(expected.getMinQty(), actual.getMinQty());
        assertEquals(expected.getMaxFloor(), actual.getMaxFloor());
        assertEquals(expected.getPositionEffect(), actual.getPositionEffect());
        assertEquals(expected.getMaxShow(), actual.getMaxShow());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2(), actual.getOrderQty2());
        assertEquals(expected.getClearingFirm(), actual.getClearingFirm());
        assertEquals(expected.getClearingAccount(), actual.getClearingAccount());
        assertEquals(expected.getMultilegReportingType(), actual.getMultilegReportingType());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);
    }
}
