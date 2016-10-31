/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument50SP1TestData.java
 *
 * $Id: Instrument50SP2TestData.java,v 1.5 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FuturesValuationMethod;
import net.hades.fix.message.type.InstrmtAssignmentMethod;
import net.hades.fix.message.type.ListMethod;
import net.hades.fix.message.type.OptPayoutType;
import net.hades.fix.message.type.PriceQuoteMethod;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.RestructuringType;
import net.hades.fix.message.type.SecurityStatus;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Seniority;
import net.hades.fix.message.type.SettlMethod;
import net.hades.fix.message.type.StrikePriceBoundaryMethod;
import net.hades.fix.message.type.StrikePriceDeterminationMethod;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnderlyingPriceDeterminationMethod;
import net.hades.fix.message.type.UnitOfMeasure;

/**
 * Test utility for Instrument component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class Instrument50SP2TestData extends MsgTest {

    private static final Instrument50SP2TestData INSTANCE;

    static {
        INSTANCE = new Instrument50SP2TestData();
    }

    public static Instrument50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(Instrument comp) {
        comp.setSymbol("MOT");
        comp.setSymbolSfx("CD");
        comp.setSecurityID("MOTX");
        comp.setSecurityIDSource("D");
        comp.setNoSecurityAltID(new Integer(2));
        comp.getSecurityAltIDGroups()[0].setSecurityAltID("Alt ID MOT 1");
        comp.getSecurityAltIDGroups()[0].setSecurityAltIDSource("1");
        comp.getSecurityAltIDGroups()[1].setSecurityAltID("Alt ID MOT 2");
        comp.getSecurityAltIDGroups()[1].setSecurityAltIDSource("2");
        comp.setProduct(Product.EQUITY);
        comp.setProductComplex("product_complex");
        comp.setSecurityGroup("sec_group");
        comp.setCfiCode("CFI MOT");
        comp.setSecurityType(SecurityType.Option.getValue());
        comp.setSecuritySubType("Sub");
        comp.setMaturityMonthYear("200803w2");
        comp.setMaturityDate(new Date());
        comp.setMaturityTime(new Date());
        comp.setSettleOnOpenFlag("settle_on_open");
        comp.setInstrmtAssignmentMethod(InstrmtAssignmentMethod.ProRata);
        comp.setSecurityStatus(SecurityStatus.Active);
        comp.setCouponPaymentDate(new Date());
        comp.setRestructuringType(RestructuringType.Full);
        comp.setSeniority(Seniority.Senior);
        comp.setNotionalPercentageOutstanding(new Double(12.55));
        comp.setOriginalNotionalPercentageOutstanding(new Double(13.66));
        comp.setAttachmentPoint(new Double(34.55));
        comp.setDetachmentPoint(new Double(36.99));
        comp.setIssueDate(new Date());
        comp.setRepoCollateralSecurityType("2");
        comp.setRepurchaseTerm(new Integer(5));
        comp.setRepurchaseRate(new Double("15.5"));
        comp.setFactor(new Double("1.2367"));
        comp.setCreditRating("AAA");
        comp.setInstrRegistry("Rego 1");
        comp.setCountryOfIssue("US");
        comp.setStateOrProvinceOfIssue("New York");
        comp.setLocaleOfIssue("US");
        comp.setRedemptionDate(new Date());
        comp.setStrikePrice(new Double("22.22"));
        comp.setStrikeCurrency(Currency.AustralianDollar);
        comp.setStrikeMultiplier(new Double(1.267));
        comp.setStrikeValue(new Double(16.5));
        comp.setStrikePriceDeterminationMethod(StrikePriceDeterminationMethod.FixedStrike);
        comp.setStrikePriceBoundaryMethod(StrikePriceBoundaryMethod.GreaterThanOrEqualUnderlyingPriceITM);
        comp.setStrikePriceBoundaryPrecision(new Double(0.0005));
        comp.setUnderlyingPriceDeterminationMethod(UnderlyingPriceDeterminationMethod.OptimalValue);
        comp.setOptAttribute(new Character('C'));
        comp.setContractMultiplier(new Double("1.023"));
        comp.setContractMultiplierUnit(ContractMultiplierUnit.Days);
        comp.setFlowScheduleType(new Integer(2));
        comp.setMinPriceIncrement(new Double("0.555"));
        comp.setMinPriceIncrementAmount(new Double(1.033));
        comp.setUnitOfMeasure(UnitOfMeasure.Gallons);
        comp.setUnitOfMeasureQty(new Double(1.22));
        comp.setPriceUnitOfMeasure(PriceUnitOfMeasure.Barrels);
        comp.setPriceUnitOfMeasureQty(new Double(1.003));
        comp.setSettlMethod(SettlMethod.Cash);
        comp.setExerciseStyle(ExerciseStyle.American);
        comp.setOptPayoutType(OptPayoutType.Binary);
        comp.setOptPayAmount(new Double(2.345));
        comp.setPriceQuoteMethod(PriceQuoteMethod.Index);
        comp.setFuturesValuationMethod(FuturesValuationMethod.Equity);
        comp.setListMethod(ListMethod.PreListedOnly);
        comp.setCapPrice(new Double(23.456));
        comp.setFloorPrice(new Double(25.678));
        comp.setPutOrCall(PutOrCall.Call);
        comp.setFlexibleIndicator(Boolean.FALSE);
        comp.setFlexProductEligibilityIndicator(Boolean.TRUE);
        comp.setTimeUnit(TimeUnit.Hour);
        comp.setCouponRate(new Double("1.077"));
        comp.setSecurityExchange("NYSE");
        comp.setPositionLimit(new Integer(100));
        comp.setNtPositionLimit(new Integer(150));
        comp.setIssuer("NYSE PL");
        comp.setEncodedIssuerLen(new Integer(8));
        byte[] issuerData = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        comp.setEncodedIssuer(issuerData);
        comp.setSecurityDesc("Motorola shares");
        comp.setEncodedSecurityDescLen(new Integer(8));
        byte[] securityData = new byte[] {(byte) 44, (byte) 22, (byte) 55, (byte) 36,
            (byte) 166, (byte) 233, (byte) 19, (byte) 95};
        comp.setEncodedSecurityDesc(securityData);
        comp.setSecurityXML();
        String instrDoc = "<?xml version='1.0' encoding='UTF-8'?>" +
            "<Instrmt Sfx='WI' Sym='MOT'/>";
        byte[] xmlData = instrDoc.getBytes();
        comp.getSecurityXML().setSecurityXML(xmlData);
        comp.getSecurityXML().setSecurityXMLSchema("Schema goes here");
        comp.setPool("POOL");
        comp.setContractSettlMonth("200803w1");
        comp.setCpProgram(new Integer(2));
        comp.setCpRegType("CpReg");
        comp.setNoEvents(new Integer(2));
        comp.getEvents()[0].setEventType(new Integer(4));
        comp.getEvents()[0].setEventDate(new Date());
        comp.getEvents()[0].setEventText("Event 1");
        comp.getEvents()[1].setEventType(new Integer(5));
        comp.getEvents()[1].setEventPx(new Double("24.23"));
        comp.getEvents()[1].setEventText("Event 2");
        comp.setDatedDate(new Date());
        comp.setInterestAccrualDate(new Date());
        comp.setInstrumentParties();
        // InstrumentParties
        InstrumentParties50SP2TestData.getInstance().populate(comp.getInstrumentParties());
        // ComplexEvents
        comp.setComplexEvents();
        ComplexEvents50SP2TestData.getInstance().populate(comp.getComplexEvents());
    }

    public void check(Instrument expected, Instrument actual) {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID(), actual.getNoSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(),
            actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(),
            actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(),
            actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(),
            actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getProduct(), actual.getProduct());
        assertEquals(expected.getProductComplex(), actual.getProductComplex());
        assertEquals(expected.getSecurityGroup(), actual.getSecurityGroup());
        assertEquals(expected.getCfiCode(), actual.getCfiCode());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getSecuritySubType(), actual.getSecuritySubType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertDateEquals(expected.getMaturityDate(), actual.getMaturityDate());
//        assertUTCTimeEquals(expected.getMaturityTime(), actual.getMaturityTime(), false);
        assertEquals(expected.getSettleOnOpenFlag(), actual.getSettleOnOpenFlag());
        assertEquals(expected.getInstrmtAssignmentMethod(), actual.getInstrmtAssignmentMethod());
        assertEquals(expected.getSecurityStatus(), actual.getSecurityStatus());
        assertDateEquals(expected.getCouponPaymentDate(), actual.getCouponPaymentDate());
        assertEquals(expected.getRestructuringType(), actual.getRestructuringType());
        assertEquals(expected.getSeniority(), actual.getSeniority());
        assertEquals(expected.getNotionalPercentageOutstanding().doubleValue(), actual.getNotionalPercentageOutstanding().doubleValue(), 0.01);
        assertEquals(expected.getOriginalNotionalPercentageOutstanding().doubleValue(), actual.getOriginalNotionalPercentageOutstanding().doubleValue(), 0.01);
        assertEquals(expected.getAttachmentPoint().doubleValue(), actual.getAttachmentPoint().doubleValue(), 0.01);
        assertEquals(expected.getDetachmentPoint().doubleValue(), actual.getDetachmentPoint().doubleValue(), 0.01);
        assertDateEquals(expected.getIssueDate(), actual.getIssueDate());
        assertEquals(expected.getRepoCollateralSecurityType(), actual.getRepoCollateralSecurityType());
        assertEquals(expected.getRepurchaseTerm(), actual.getRepurchaseTerm());
        assertEquals(expected.getRepurchaseRate().doubleValue(), actual.getRepurchaseRate().doubleValue(), 0.01);
        assertEquals(expected.getFactor().doubleValue(), actual.getFactor().doubleValue(), 0.01);
        assertEquals(expected.getCreditRating(), actual.getCreditRating());
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertDateEquals(expected.getRedemptionDate(), actual.getRedemptionDate());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.01);
        assertEquals(expected.getStrikeCurrency(), actual.getStrikeCurrency());
        assertEquals(expected.getStrikeMultiplier().doubleValue(), actual.getStrikeMultiplier().doubleValue(), 0.01);
        assertEquals(expected.getStrikeValue().doubleValue(), actual.getStrikeValue().doubleValue(), 0.01);
        assertEquals(expected.getStrikePriceDeterminationMethod(), actual.getStrikePriceDeterminationMethod());
        assertEquals(expected.getStrikePriceBoundaryMethod(), actual.getStrikePriceBoundaryMethod());
        assertEquals(expected.getStrikePriceBoundaryPrecision().doubleValue(), actual.getStrikePriceBoundaryPrecision().doubleValue(), 0.01);
//        assertEquals(expected.getUnderlyingPriceDeterminationMethod(), actual.getUnderlyingPriceDeterminationMethod());
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.01);
//        assertEquals(expected.getContractMultiplierUnit(), actual.getContractMultiplierUnit());
//        assertEquals(expected.getFlowScheduleType(), actual.getFlowScheduleType());
        assertEquals(expected.getMinPriceIncrement().doubleValue(), actual.getMinPriceIncrement().doubleValue(), 0.01);
        assertEquals(expected.getMinPriceIncrementAmount().doubleValue(), actual.getMinPriceIncrementAmount().doubleValue(), 0.01);
        assertEquals(expected.getUnitOfMeasure(), actual.getUnitOfMeasure());
        assertEquals(expected.getUnitOfMeasureQty().doubleValue(), actual.getUnitOfMeasureQty().doubleValue(), 0.01);
        assertEquals(expected.getPriceUnitOfMeasure(), actual.getPriceUnitOfMeasure());
        assertEquals(expected.getPriceUnitOfMeasureQty().doubleValue(), actual.getPriceUnitOfMeasureQty().doubleValue(), 0.01);
        assertEquals(expected.getSettlMethod(), actual.getSettlMethod());
        assertEquals(expected.getExerciseStyle(), actual.getExerciseStyle());
        assertEquals(expected.getOptPayoutType(), actual.getOptPayoutType());
        assertEquals(expected.getOptPayAmount().doubleValue(), actual.getOptPayAmount().doubleValue(), 0.01);
        assertEquals(expected.getPriceQuoteMethod(), actual.getPriceQuoteMethod());
//        assertEquals(expected.getFuturesValuationMethod(), actual.getFuturesValuationMethod());
        assertEquals(expected.getListMethod(), actual.getListMethod());
        assertEquals(expected.getCapPrice().doubleValue(), actual.getCapPrice().doubleValue(), 0.01);
        assertEquals(expected.getFloorPrice().doubleValue(), actual.getFloorPrice().doubleValue(), 0.01);
        assertEquals(expected.getPutOrCall(), actual.getPutOrCall());
        assertEquals(expected.getFlexibleIndicator(), actual.getFlexibleIndicator());
        assertEquals(expected.getFlexProductEligibilityIndicator(), actual.getFlexProductEligibilityIndicator());
        assertEquals(expected.getTimeUnit(), actual.getTimeUnit());
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.01);
        assertEquals(expected.getPositionLimit().intValue(), actual.getPositionLimit().intValue());
        assertEquals(expected.getNtPositionLimit().intValue(), actual.getNtPositionLimit().intValue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen(), actual.getEncodedIssuerLen());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen(), actual.getEncodedSecurityDescLen());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertNotNull(actual.getSecurityXML());
//        assertEquals(expected.getSecurityXML().getSecurityXMLLen().intValue(), actual.getSecurityXML().getSecurityXMLLen().intValue());
//        assertEquals(new String(expected.getSecurityXML().getSecurityXML()), new String(actual.getSecurityXML().getSecurityXML()));
        assertEquals(expected.getSecurityXML().getSecurityXMLSchema(), actual.getSecurityXML().getSecurityXMLSchema());
        assertEquals(expected.getPool(), actual.getPool());
        assertEquals(expected.getContractSettlMonth(), actual.getContractSettlMonth());
        assertEquals(expected.getCpProgram(), actual.getCpProgram());
        assertEquals(expected.getCpRegType(), actual.getCpRegType());
        assertEquals(expected.getNoEvents(), actual.getNoEvents());
        assertEquals(expected.getEvents()[0].getEventType(), actual.getEvents()[0].getEventType());
        assertDateEquals(expected.getEvents()[0].getEventDate(), actual.getEvents()[0].getEventDate());
        assertEquals(expected.getEvents()[0].getEventText(), actual.getEvents()[0].getEventText());
        assertEquals(expected.getEvents()[1].getEventType(), actual.getEvents()[1].getEventType());
        assertDateEquals(expected.getEvents()[1].getEventDate(), actual.getEvents()[1].getEventDate());
        assertEquals(expected.getEvents()[1].getEventText(), actual.getEvents()[1].getEventText());
        assertDateEquals(expected.getDatedDate(), actual.getDatedDate());
        assertDateEquals(expected.getInterestAccrualDate(), actual.getInterestAccrualDate());
        assertNotNull(expected.getInstrumentParties());
        assertEquals(expected.getInstrumentParties().getNoInstrumentParties(), actual.getInstrumentParties().getNoInstrumentParties());
        // InstrumentParties check
        InstrumentParties50SP2TestData.getInstance().check(expected.getInstrumentParties(), actual.getInstrumentParties());
        // ComplexEvents
        ComplexEvents50SP2TestData.getInstance().check(expected.getComplexEvents(), actual.getComplexEvents());
    }
}
