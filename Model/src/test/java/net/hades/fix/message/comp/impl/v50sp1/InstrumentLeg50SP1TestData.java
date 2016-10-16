/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg50SP1TestData.java
 *
 * $Id: InstrumentLeg50SP1TestData.java,v 1.3 2011-10-29 09:42:12 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
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
 * @version $Revision: 1.3 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class InstrumentLeg50SP1TestData extends MsgTest {

    private static final InstrumentLeg50SP1TestData INSTANCE;

    static {
        INSTANCE = new InstrumentLeg50SP1TestData();
    }

    public static InstrumentLeg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrumentLeg instrumentLeg) {
        instrumentLeg.setLegSymbol("MOT");
        instrumentLeg.setLegSymbolSfx("CD");
        instrumentLeg.setLegSecurityID("SEC_ID");
        instrumentLeg.setLegSecurityIDSource("1");
        instrumentLeg.setNoLegSecurityAltID(new Integer(2));
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_1");
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("1");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_2");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("2");
        instrumentLeg.setLegProduct(Product.EQUITY);
        instrumentLeg.setLegCFICode("cfi_code");
        instrumentLeg.setLegSecurityType("FAC");
        instrumentLeg.setLegSecuritySubType("sec_sub_234");
        instrumentLeg.setLegMaturityMonthYear("200806w2");
        Calendar calmd = Calendar.getInstance();
        calmd.set(Calendar.DAY_OF_MONTH, 3);
        calmd.set(Calendar.MONTH, 2);
        calmd.set(Calendar.YEAR, 2008);
        calmd.set(Calendar.HOUR_OF_DAY, 12);
        calmd.set(Calendar.MINUTE, 23);
        calmd.set(Calendar.SECOND, 55);
        calmd.set(Calendar.MILLISECOND, 0);
        instrumentLeg.setLegMaturityDate(calmd.getTime());
        instrumentLeg.setLegMaturityTime(calmd.getTime());
        Calendar calpd = Calendar.getInstance();
        calpd.set(Calendar.DAY_OF_MONTH, 7);
        calpd.set(Calendar.MONTH, 7);
        calpd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegCouponPaymentDate(calpd.getTime());
        Calendar calid = Calendar.getInstance();
        calid.set(Calendar.DAY_OF_MONTH, 6);
        calid.set(Calendar.MONTH, 6);
        calid.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegIssueDate(calid.getTime());
        instrumentLeg.setLegRepoCollateralSecurityType("1");
        instrumentLeg.setLegRepurchaseTerm(new Integer(12));
        instrumentLeg.setLegRepurchaseRate(new Double("12.25"));
        instrumentLeg.setLegFactor(new Double("2.34"));
        instrumentLeg.setLegCreditRating("AAA");
        instrumentLeg.setLegInstrRegistry("IN_REG");
        instrumentLeg.setLegCountryOfIssue("AU");
        instrumentLeg.setLegStateOrProvinceOfIssue("NSW");
        instrumentLeg.setLegLocaleOfIssue("AU");
        Calendar calrd = Calendar.getInstance();
        calrd.set(Calendar.DAY_OF_MONTH, 5);
        calrd.set(Calendar.MONTH, 3);
        calrd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegRedemptionDate(calrd.getTime());
        instrumentLeg.setLegStrikePrice(new Double(2.55));
        instrumentLeg.setLegStrikeCurrency(Currency.UnitedStatesDollar);
        instrumentLeg.setLegOptAttribute(new Character('S'));
        instrumentLeg.setLegContractMultiplier(new Double("1.12"));
        instrumentLeg.setLegUnitOfMeasure(UnitOfMeasure.Gallons);
        instrumentLeg.setLegUnitOfMeasureQty(new Double(23.777));
        instrumentLeg.setLegPriceUnitOfMeasure(PriceUnitOfMeasure.BillionCubicFeet);
        instrumentLeg.setLegPriceUnitOfMeasureQty(new Double(34.222));
        instrumentLeg.setLegTimeUnit(TimeUnit.Hour);
        instrumentLeg.setLegExerciseStyle(ExerciseStyle.Bermuda);
        instrumentLeg.setLegCouponRate(new Double("2.56"));
        instrumentLeg.setLegSecurityExchange("NYSE");
        instrumentLeg.setLegIssuer("IB");
        instrumentLeg.setEncodedLegIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        instrumentLeg.setEncodedLegIssuer(encLegIssuer);
        instrumentLeg.setLegSecurityDesc("MOT shares");
        instrumentLeg.setEncodedLegSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        instrumentLeg.setEncodedLegSecurityDesc(encLegSecDesc);
        instrumentLeg.setLegRatioQty(new Double("2.0"));
        instrumentLeg.setLegSide(Side.Buy);
        instrumentLeg.setLegCurrency(Currency.UnitedStatesDollar);
        instrumentLeg.setLegPool("leg_pool");
        Calendar caldd = Calendar.getInstance();
        caldd.set(Calendar.DAY_OF_MONTH, 10);
        caldd.set(Calendar.MONTH, 10);
        caldd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegDatedDate(caldd.getTime());
        instrumentLeg.setLegContractSettlMonth("200903w2");
        Calendar calad = Calendar.getInstance();
        calad.set(Calendar.DAY_OF_MONTH, 11);
        calad.set(Calendar.MONTH, 11);
        calad.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegInterestAccrualDate(calad.getTime());
        instrumentLeg.setLegPutOrCall(PutOrCall.Call);
        instrumentLeg.setLegOptionRatio(new Double(1.098));
        instrumentLeg.setLegPrice(new Double(22.345));
    }

    public void populate2(InstrumentLeg instrumentLeg) {
        instrumentLeg.setLegSymbol("MOT");
        instrumentLeg.setLegSymbolSfx("WI");
        instrumentLeg.setLegSecurityID("SEC_ID_1");
        instrumentLeg.setLegSecurityIDSource("2");
        instrumentLeg.setNoLegSecurityAltID(new Integer(2));
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_3");
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("3");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_4");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("4");
        instrumentLeg.setLegProduct(Product.COMMODITY);
        instrumentLeg.setLegCFICode("cfi_code_1");
        instrumentLeg.setLegSecurityType("SUPRA");
        instrumentLeg.setLegSecuritySubType("sec_sub_234_1");
        instrumentLeg.setLegMaturityMonthYear("200806w1");
        Calendar calmd = Calendar.getInstance();
        calmd.set(Calendar.DAY_OF_MONTH, 3);
        calmd.set(Calendar.MONTH, 2);
        calmd.set(Calendar.YEAR, 2008);
        calmd.set(Calendar.HOUR_OF_DAY, 12);
        calmd.set(Calendar.MINUTE, 23);
        calmd.set(Calendar.SECOND, 55);
        calmd.set(Calendar.MILLISECOND, 0);
        instrumentLeg.setLegMaturityDate(calmd.getTime());
        instrumentLeg.setLegMaturityTime(calmd.getTime());
        Calendar calpd = Calendar.getInstance();
        calpd.set(Calendar.DAY_OF_MONTH, 7);
        calpd.set(Calendar.MONTH, 7);
        calpd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegCouponPaymentDate(calpd.getTime());
        Calendar calid = Calendar.getInstance();
        calid.set(Calendar.DAY_OF_MONTH, 6);
        calid.set(Calendar.MONTH, 6);
        calid.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegIssueDate(calid.getTime());
        instrumentLeg.setLegRepoCollateralSecurityType("2");
        instrumentLeg.setLegRepurchaseTerm(new Integer(12));
        instrumentLeg.setLegRepurchaseRate(new Double("12.251"));
        instrumentLeg.setLegFactor(new Double("2.341"));
        instrumentLeg.setLegCreditRating("AA");
        instrumentLeg.setLegInstrRegistry("IN_REG_1");
        instrumentLeg.setLegCountryOfIssue("AU");
        instrumentLeg.setLegStateOrProvinceOfIssue("NSW");
        instrumentLeg.setLegLocaleOfIssue("AU");
        Calendar calrd = Calendar.getInstance();
        calrd.set(Calendar.DAY_OF_MONTH, 5);
        calrd.set(Calendar.MONTH, 3);
        calrd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegRedemptionDate(calrd.getTime());
        instrumentLeg.setLegStrikePrice(new Double(2.44));
        instrumentLeg.setLegStrikeCurrency(Currency.AustralianDollar);
        instrumentLeg.setLegOptAttribute(new Character('S'));
        instrumentLeg.setLegContractMultiplier(new Double("1.121"));
        instrumentLeg.setLegUnitOfMeasure(UnitOfMeasure.Bushels);
        instrumentLeg.setLegUnitOfMeasureQty(new Double(23.777));
        instrumentLeg.setLegPriceUnitOfMeasure(PriceUnitOfMeasure.BillionCubicFeet);
        instrumentLeg.setLegPriceUnitOfMeasureQty(new Double(34.222));
        instrumentLeg.setLegTimeUnit(TimeUnit.Week);
        instrumentLeg.setLegExerciseStyle(ExerciseStyle.Bermuda);
        instrumentLeg.setLegCouponRate(new Double("2.561"));
        instrumentLeg.setLegSecurityExchange("NYSE");
        instrumentLeg.setLegIssuer("IB");
        instrumentLeg.setEncodedLegIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        instrumentLeg.setEncodedLegIssuer(encLegIssuer);
        instrumentLeg.setLegSecurityDesc("MOT shares");
        instrumentLeg.setEncodedLegSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        instrumentLeg.setEncodedLegSecurityDesc(encLegSecDesc);
        instrumentLeg.setLegRatioQty(new Double("2.01"));
        instrumentLeg.setLegSide(Side.Lend);
        instrumentLeg.setLegCurrency(Currency.AustralianDollar);
        instrumentLeg.setLegPool("leg_pool_1");
        Calendar caldd = Calendar.getInstance();
        caldd.set(Calendar.DAY_OF_MONTH, 10);
        caldd.set(Calendar.MONTH, 10);
        caldd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegDatedDate(caldd.getTime());
        instrumentLeg.setLegContractSettlMonth("200903w1");
        Calendar calad = Calendar.getInstance();
        calad.set(Calendar.DAY_OF_MONTH, 11);
        calad.set(Calendar.MONTH, 11);
        calad.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegInterestAccrualDate(calad.getTime());
        instrumentLeg.setLegPutOrCall(PutOrCall.Call);
        instrumentLeg.setLegOptionRatio(new Double(1.098));
        instrumentLeg.setLegPrice(new Double(12.345));
    }

    public void check(InstrumentLeg expected, InstrumentLeg actual) throws Exception {
        assertEquals(expected.getLegSymbol(), actual.getLegSymbol());
        assertEquals(expected.getLegSymbolSfx(), actual.getLegSymbolSfx());
        assertEquals(expected.getLegSecurityID(), actual.getLegSecurityID());
        assertEquals(expected.getLegSecurityIDSource(), actual.getLegSecurityIDSource());
        assertEquals(expected.getNoLegSecurityAltID(), actual.getNoLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[0].getLegSecurityAltID(), actual.getLegSecurityAltIDGroups()[0].getLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[0].getLegSecurityAltIDSource(), actual.getLegSecurityAltIDGroups()[0].getLegSecurityAltIDSource());
        assertEquals(expected.getLegSecurityAltIDGroups()[1].getLegSecurityAltID(), actual.getLegSecurityAltIDGroups()[1].getLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[1].getLegSecurityAltIDSource(), actual.getLegSecurityAltIDGroups()[1].getLegSecurityAltIDSource());
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
        assertEquals(expected.getLegPrice(), actual.getLegPrice());
    }
}
