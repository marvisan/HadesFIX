/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg50SP2TestData.java
 *
 * $Id: InstrumentLeg50SP2TestData.java,v 1.4 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FlowScheduleType;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;

/**
 * Test utility for InstrumentLeg component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class InstrumentLeg50SP2TestData extends MsgTest {

    private static final InstrumentLeg50SP2TestData INSTANCE;

    static {
        INSTANCE = new InstrumentLeg50SP2TestData();
    }

    public static InstrumentLeg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrumentLeg comp) {
        comp.setLegSymbol("MOT");
        comp.setLegSymbolSfx("CD");
        comp.setLegSecurityID("SEC_ID");
        comp.setLegSecurityIDSource("1");
        comp.setNoLegSecurityAltID(new Integer(2));
        comp.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_1");
        comp.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("1");
        comp.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_2");
        comp.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("2");
        comp.setLegProduct(Product.EQUITY);
        comp.setLegCFICode("cfi_code");
        comp.setLegSecurityType("FAC");
        comp.setLegSecuritySubType("sec_sub_234");
        comp.setLegMaturityMonthYear("200806w2");
        comp.setLegMaturityDate(new Date());
        comp.setLegMaturityTime(new Date());
        comp.setLegCouponPaymentDate(new Date());
        comp.setLegIssueDate(new Date());
        comp.setLegRepoCollateralSecurityType("1");
        comp.setLegRepurchaseTerm(new Integer(12));
        comp.setLegRepurchaseRate(new Double("12.25"));
        comp.setLegFactor(new Double("2.34"));
        comp.setLegCreditRating("AAA");
        comp.setLegInstrRegistry("IN_REG");
        comp.setLegCountryOfIssue("AU");
        comp.setLegStateOrProvinceOfIssue("NSW");
        comp.setLegLocaleOfIssue("AU");
        comp.setLegRedemptionDate(new Date());
        comp.setLegStrikePrice(new Double(2.55));
        comp.setLegStrikeCurrency(Currency.UnitedStatesDollar);
        comp.setLegOptAttribute(new Character('S'));
        comp.setLegContractMultiplier(new Double("1.12"));
        comp.setLegContractMultiplierUnit(ContractMultiplierUnit.Days);
        comp.setLegFlowScheduleType(FlowScheduleType.NERCCalendarAllDays);
        comp.setLegUnitOfMeasure(UnitOfMeasure.Gallons);
        comp.setLegUnitOfMeasureQty(new Double(23.777));
        comp.setLegPriceUnitOfMeasure(PriceUnitOfMeasure.BillionCubicFeet);
        comp.setLegPriceUnitOfMeasureQty(new Double(34.222));
        comp.setLegTimeUnit(TimeUnit.Hour);
        comp.setLegExerciseStyle(ExerciseStyle.Bermuda);
        comp.setLegCouponRate(new Double("2.56"));
        comp.setLegSecurityExchange("NYSE");
        comp.setLegIssuer("IB");
        comp.setEncodedLegIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        comp.setEncodedLegIssuer(encLegIssuer);
        comp.setLegSecurityDesc("MOT shares");
        comp.setEncodedLegSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        comp.setEncodedLegSecurityDesc(encLegSecDesc);
        comp.setLegRatioQty(new Double("2.0"));
        comp.setLegSide(Side.Buy);
        comp.setLegCurrency(Currency.UnitedStatesDollar);
        comp.setLegPool("leg_pool");
        comp.setLegDatedDate(new Date());
        comp.setLegContractSettlMonth("200903w2");
        comp.setLegInterestAccrualDate(new Date());
        comp.setLegPutOrCall(PutOrCall.Call);
        comp.setLegOptionRatio(new Double(1.098));
        comp.setLegPrice(new Double(22.345));
    }

    public void populate2(InstrumentLeg comp) {
        comp.setLegSymbol("MOT");
        comp.setLegSymbolSfx("WI");
        comp.setLegSecurityID("SEC_ID_1");
        comp.setLegSecurityIDSource("2");
        comp.setNoLegSecurityAltID(new Integer(2));
        comp.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_3");
        comp.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("3");
        comp.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_4");
        comp.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("4");
        comp.setLegProduct(Product.COMMODITY);
        comp.setLegCFICode("cfi_code_1");
        comp.setLegSecurityType("SUPRA");
        comp.setLegSecuritySubType("sec_sub_234_1");
        comp.setLegMaturityMonthYear("200806w1");
        comp.setLegMaturityDate(new Date());
        comp.setLegMaturityTime(new Date());
        comp.setLegCouponPaymentDate(new Date());
        comp.setLegIssueDate(new Date());
        comp.setLegRepoCollateralSecurityType("2");
        comp.setLegRepurchaseTerm(new Integer(12));
        comp.setLegRepurchaseRate(new Double("12.251"));
        comp.setLegFactor(new Double("2.341"));
        comp.setLegCreditRating("AA");
        comp.setLegInstrRegistry("IN_REG_1");
        comp.setLegCountryOfIssue("AU");
        comp.setLegStateOrProvinceOfIssue("NSW");
        comp.setLegLocaleOfIssue("AU");
        comp.setLegRedemptionDate(new Date());
        comp.setLegStrikePrice(new Double(2.44));
        comp.setLegStrikeCurrency(Currency.AustralianDollar);
        comp.setLegOptAttribute(new Character('S'));
        comp.setLegContractMultiplier(new Double("1.121"));
        comp.setLegContractMultiplierUnit(ContractMultiplierUnit.Hours);
        comp.setLegFlowScheduleType(FlowScheduleType.NERCEasternPeak);
        comp.setLegUnitOfMeasure(UnitOfMeasure.Bushels);
        comp.setLegUnitOfMeasureQty(new Double(23.777));
        comp.setLegPriceUnitOfMeasure(PriceUnitOfMeasure.BillionCubicFeet);
        comp.setLegPriceUnitOfMeasureQty(new Double(34.222));
        comp.setLegTimeUnit(TimeUnit.Week);
        comp.setLegExerciseStyle(ExerciseStyle.Bermuda);
        comp.setLegCouponRate(new Double("2.561"));
        comp.setLegSecurityExchange("NYSE");
        comp.setLegIssuer("IB");
        comp.setEncodedLegIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        comp.setEncodedLegIssuer(encLegIssuer);
        comp.setLegSecurityDesc("MOT shares");
        comp.setEncodedLegSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        comp.setEncodedLegSecurityDesc(encLegSecDesc);
        comp.setLegRatioQty(new Double("2.01"));
        comp.setLegSide(Side.Lend);
        comp.setLegCurrency(Currency.AustralianDollar);
        comp.setLegPool("leg_pool_1");
        comp.setLegDatedDate(new Date());
        comp.setLegContractSettlMonth("200903w1");
        comp.setLegInterestAccrualDate(new Date());
        comp.setLegPutOrCall(PutOrCall.Call);
        comp.setLegOptionRatio(new Double(1.098));
        comp.setLegPrice(new Double(22.345));
    }

    public void check(InstrumentLeg expected, InstrumentLeg actual) {
        assertEquals(expected.getLegSymbol(), actual.getLegSymbol());
        assertEquals(expected.getLegSymbolSfx(), actual.getLegSymbolSfx());
        assertEquals(expected.getLegSecurityID(), actual.getLegSecurityID());
        assertEquals(expected.getLegSecurityIDSource(), actual.getLegSecurityIDSource());
        assertEquals(expected.getNoLegSecurityAltID(), actual.getNoLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[0].getLegSecurityAltID(),
            actual.getLegSecurityAltIDGroups()[0].getLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[0].getLegSecurityAltIDSource(),
            actual.getLegSecurityAltIDGroups()[0].getLegSecurityAltIDSource());
        assertEquals(expected.getLegSecurityAltIDGroups()[1].getLegSecurityAltID(),
            actual.getLegSecurityAltIDGroups()[1].getLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[1].getLegSecurityAltIDSource(),
            actual.getLegSecurityAltIDGroups()[1].getLegSecurityAltIDSource());
        assertEquals(expected.getLegProduct(), actual.getLegProduct());
        assertEquals(expected.getLegCFICode(), actual.getLegCFICode());
        assertEquals(expected.getLegSecurityType(), actual.getLegSecurityType());
        assertEquals(expected.getLegSecuritySubType(), actual.getLegSecuritySubType());
        assertEquals(expected.getLegMaturityMonthYear(), actual.getLegMaturityMonthYear());
        assertDateEquals(expected.getLegMaturityDate(), actual.getLegMaturityDate());
//        assertTZTimeEquals(expected.getLegMaturityTime(), actual.getLegMaturityTime(), false);
        assertEquals(expected.getLegRepoCollateralSecurityType(), actual.getLegRepoCollateralSecurityType());
        assertEquals(expected.getLegRepurchaseTerm(), actual.getLegRepurchaseTerm());
        assertEquals(expected.getLegRepurchaseRate().doubleValue(), actual.getLegRepurchaseRate().doubleValue(), 0.0001);
        assertEquals(expected.getLegFactor().doubleValue(), actual.getLegFactor().doubleValue(), 0.0001);
        assertEquals(expected.getLegCreditRating(), actual.getLegCreditRating());
        assertEquals(expected.getLegInstrRegistry(), actual.getLegInstrRegistry());
        assertEquals(expected.getLegCountryOfIssue(), actual.getLegCountryOfIssue());
        assertEquals(expected.getLegStateOrProvinceOfIssue(), actual.getLegStateOrProvinceOfIssue());
        assertEquals(expected.getLegLocaleOfIssue(), actual.getLegLocaleOfIssue());
        assertDateEquals(expected.getLegRedemptionDate(), actual.getLegRedemptionDate());
        assertEquals(expected.getLegStrikePrice().doubleValue(), actual.getLegStrikePrice().doubleValue(), 0.0001);
        assertEquals(expected.getLegStrikeCurrency(), actual.getLegStrikeCurrency());
        assertEquals(expected.getLegOptAttribute(), actual.getLegOptAttribute());
        assertEquals(expected.getLegContractMultiplier().doubleValue(), actual.getLegContractMultiplier().doubleValue(), 0.0001);
        assertEquals(expected.getLegContractMultiplierUnit(), actual.getLegContractMultiplierUnit());
        assertEquals(expected.getLegFlowScheduleType(), actual.getLegFlowScheduleType());
        assertEquals(expected.getLegUnitOfMeasure(), actual.getLegUnitOfMeasure());
        assertEquals(expected.getLegUnitOfMeasureQty().doubleValue(), actual.getLegUnitOfMeasureQty().doubleValue(), 0.0001);
        assertEquals(expected.getLegPriceUnitOfMeasure(), actual.getLegPriceUnitOfMeasure());
        assertEquals(expected.getLegPriceUnitOfMeasureQty().doubleValue(), actual.getLegPriceUnitOfMeasureQty().doubleValue(), 0.0001);
        assertEquals(expected.getLegTimeUnit(), actual.getLegTimeUnit());
        assertEquals(expected.getLegExerciseStyle(), actual.getLegExerciseStyle());
        assertEquals(expected.getLegCouponRate().doubleValue(), actual.getLegCouponRate().doubleValue(), 0.0001);
        assertEquals(expected.getLegSecurityExchange(), actual.getLegSecurityExchange());
        assertEquals(expected.getLegIssuer(), actual.getLegIssuer());
        assertEquals(expected.getEncodedLegIssuerLen(), actual.getEncodedLegIssuerLen());
        assertArrayEquals(expected.getEncodedLegIssuer(), actual.getEncodedLegIssuer());
        assertEquals(expected.getLegSecurityDesc(), actual.getLegSecurityDesc());
        assertEquals(expected.getEncodedLegSecurityDescLen(), actual.getEncodedLegSecurityDescLen());
        assertArrayEquals(expected.getEncodedLegSecurityDesc(), actual.getEncodedLegSecurityDesc());
        assertEquals(expected.getLegRatioQty().doubleValue(), actual.getLegRatioQty().doubleValue(), 0.0001);
        assertEquals(expected.getLegSide(), actual.getLegSide());
        assertEquals(expected.getLegCurrency(), actual.getLegCurrency());
        assertEquals(expected.getLegPool(), actual.getLegPool());
        assertDateEquals(expected.getLegDatedDate(), actual.getLegDatedDate());
        assertEquals(expected.getLegContractSettlMonth(), actual.getLegContractSettlMonth());
        assertDateEquals(expected.getLegInterestAccrualDate(), actual.getLegInterestAccrualDate());
        assertEquals(expected.getLegPutOrCall(), actual.getLegPutOrCall());
        assertEquals(expected.getLegOptionRatio().doubleValue(), actual.getLegOptionRatio().doubleValue(), 0.0001);
        assertEquals(expected.getLegPrice().doubleValue(), actual.getLegPrice().doubleValue(), 0.0001);
    }
}
