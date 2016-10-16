/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg50SP1TestData.java
 *
 * $Id: QuoteStatusReportMsg50SP1TestData.java,v 1.2 2011-10-29 09:42:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.impl.v50sp1.LegQuoteStatusGroup50SP1TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.IOIQualifier;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuoteCancelType;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteStatusReportMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteStatusReportMsg50SP1TestData extends MsgTest {

    private static final QuoteStatusReportMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new QuoteStatusReportMsg50SP1TestData();
    }

    public static QuoteStatusReportMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(QuoteStatusReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
        msg.setQuoteStatusReqID("ADFS55666TT");
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteMsgID("DDD098WWW");
        msg.setQuoteRespID("ADFFF6677S");
        msg.setQuoteType(QuoteType.Indicative);
        msg.setQuoteCancelType(QuoteCancelType.CancelAllQuotes.getValue());
        // Parties
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());

        msg.setAccount("54543464");
        msg.setAcctIDSource(AcctIDSource.BIC);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
        // Instrument
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setSide(Side.Buy);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());

        msg.setBidPx(new Double(23.44));
        msg.setOfferPx(new Double(23.98));
        msg.setMktBidPx(new Double(34.55));
        msg.setMktOfferPx(new Double(37.55));
        msg.setMinBidSize(new Double(99.0));
        msg.setBidSize(new Double(100.0));
        msg.setMinOfferSize(new Double(30.0));
        msg.setOfferSize(new Double(150));
        msg.setMinQty(new Double(23.765));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.44));
        msg.setOfferSpotRate(new Double(12.54));
        msg.setBidForwardPoints(new Double(2.44));
        msg.setOfferForwardPoints(new Double(3.44));
        msg.setMidPx(new Double(114.0));
        msg.setBidYield(new Double(1.1));
        msg.setMidYield(new Double(1.05));
        msg.setOfferYield(new Double(1.15));
        msg.setTransactTime(new Date());
        msg.setSettlType(SettlType.Future.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexMarket);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
        msg.setBidForwardPoints2(new Double(2.222));
        msg.setOfferForwardPoints2(new Double(2.333));
        msg.setCurrency(Currency.UnitedStatesDollar);
        // Stipulations
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());
        // LegQuoteSymbolGroup
        msg.setNoLegs(new Integer(2));
        LegQuoteStatusGroup50SP1TestData.getInstance().populate1(msg.getLegQuoteStatusGroups()[0]);
        LegQuoteStatusGroup50SP1TestData.getInstance().populate2(msg.getLegQuoteStatusGroups()[1]);
        // QuoteQualifierGroup
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AtTheClose);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.AtTheMarket);
        msg.setExpireTime(new Date());
        msg.setPrice(new Double(12.576));
        msg.setPriceType(PriceType.Percentage);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        // YieldData
        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());

        msg.setSettlCurrBidFxRate(new Double(1.09));
        msg.setSettlCurrOfferFxRate(new Double(1.11));
        msg.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Divide);
        msg.setCommType(CommType.CashDiscount);
        msg.setCommission(new Double(6.99));
        msg.setCustOrderCapacity(CustOrderCapacity.MemberTradingForAnotherMember);
        msg.setExDestination("Some Destination");
        msg.setExDestinationIDSource(ExDestinationIDSource.BankIdentificationCode);
        msg.setQuoteStatus(QuoteStatus.Accepted);
        msg.setText("Trade in confidence");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(QuoteStatusReportMsg expected, QuoteStatusReportMsg actual) throws Exception {
        assertEquals(expected.getQuoteStatusReqID(), actual.getQuoteStatusReqID());
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteMsgID(), actual.getQuoteMsgID());
        assertEquals(expected.getQuoteRespID(), actual.getQuoteRespID());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getQuoteCancelType(), actual.getQuoteCancelType());
        // Parties check
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // Instrument check
        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        // OrderQtyData check
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        
        assertEquals(expected.getBidPx().doubleValue(), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMktBidPx().doubleValue(), actual.getMktBidPx().doubleValue(), 0.001);
        assertEquals(expected.getMktOfferPx().doubleValue(), actual.getMktOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMinBidSize().doubleValue(), actual.getMinBidSize().doubleValue(), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getMinOfferSize().doubleValue(), actual.getMinOfferSize().doubleValue(), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getOfferSize().doubleValue(), 0.001);
        assertEquals(expected.getMinQty().doubleValue(), actual.getMinQty().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getOfferYield().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        // Stipulations check
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        // LegQuoteSymbolGroup
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        LegQuoteStatusGroup50SP1TestData.getInstance().check(expected.getLegQuoteStatusGroups()[0], actual.getLegQuoteStatusGroups()[0]);
        LegQuoteStatusGroup50SP1TestData.getInstance().check(expected.getLegQuoteStatusGroups()[1], actual.getLegQuoteStatusGroups()[1]);
        // QuoteQualifierGroup
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getNoQuoteQualifiers().intValue());
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getPriceType().getValue(), actual.getPriceType().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getSettlCurrBidFxRate().doubleValue(), actual.getSettlCurrBidFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrOfferFxRate().doubleValue(), actual.getSettlCurrOfferFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getCommType().getValue(), actual.getCommType().getValue());
        assertEquals(expected.getCommission().doubleValue(), actual.getCommission().doubleValue(), 0.001);
        assertEquals(expected.getCustOrderCapacity().getValue(), actual.getCustOrderCapacity().getValue());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        assertEquals(expected.getExDestinationIDSource(), actual.getExDestinationIDSource());
        assertEquals(expected.getQuoteStatus().getValue(), actual.getQuoteStatus().getValue());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
