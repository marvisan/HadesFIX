/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument50SP1TestData.java
 *
 * $Id: UnderlyingInstrument50SP1TestData.java,v 1.3 2011-10-29 09:42:13 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FXRateCalc;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnderlyingSettlementType;
import net.hades.fix.message.type.UnitOfMeasure;

/**
 * Test utility for UnderlyingInstrument component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class UnderlyingInstrument50SP1TestData extends MsgTest {

    private static final UnderlyingInstrument50SP1TestData INSTANCE;

    static {
        INSTANCE = new UnderlyingInstrument50SP1TestData();
    }

    public static UnderlyingInstrument50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UnderlyingInstrument underlyingInstrument) {
        underlyingInstrument.setUnderlyingSymbol("JAVA_1");
        underlyingInstrument.setUnderlyingSymbolSfx("CD");
        underlyingInstrument.setUnderlyingSecurityID("sec_id_1");
        underlyingInstrument.setUnderlyingSecurityIDSource("1");
        underlyingInstrument.setNoUnderlyingSecurityAltID(new Integer(1));
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_1");
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("2");
        underlyingInstrument.setUnderlyingProduct(Product.COMMODITY);
        underlyingInstrument.setUnderlyingCFICode("cfi_code_1");
        underlyingInstrument.setUnderlyingSecurityType(SecurityType.Option.getValue());
        underlyingInstrument.setUnderlyingSecuritySubType("sec_sub_type_1");
        underlyingInstrument.setUnderlyingMaturityMonthYear("200906w1");
        Calendar calmd = Calendar.getInstance();
        calmd.set(2009, 7, 23, 10, 24, 55);
        calmd.set(Calendar.MILLISECOND, 0);
        underlyingInstrument.setUnderlyingMaturityDate(calmd.getTime());
        underlyingInstrument.setUnderlyingMaturityTime(calmd.getTime());
        Calendar calcd = Calendar.getInstance();
        calcd.set(2010, 8, 13);
        underlyingInstrument.setUnderlyingCouponPaymentDate(calcd.getTime());
        Calendar calid = Calendar.getInstance();
        calid.set(2010, 2, 4);
        underlyingInstrument.setUnderlyingIssueDate(calid.getTime());
        underlyingInstrument.setUnderlyingRepoCollateralSecurityType("1");
        underlyingInstrument.setUnderlyingRepurchaseTerm(new Integer(31));
        underlyingInstrument.setUnderlyingRepurchaseRate(new Double(23.45));
        underlyingInstrument.setUnderlyingFactor(new Double(1.234));
        underlyingInstrument.setUnderlyingCreditRating("AAA");
        underlyingInstrument.setUnderlyingInstrRegistry("instr_reg_1");
        underlyingInstrument.setUnderlyingCountryOfIssue("US");
        underlyingInstrument.setUnderlyingStateOrProvinceOfIssue("NY");
        underlyingInstrument.setUnderlyingLocaleOfIssue("EN");
        Calendar calrd = Calendar.getInstance();
        calrd.set(2010, 1, 14);
        underlyingInstrument.setUnderlyingRedemptionDate(calrd.getTime());
        underlyingInstrument.setUnderlyingStrikePrice(new Double(24.15));
        underlyingInstrument.setUnderlyingStrikeCurrency(Currency.UnitedStatesDollar);
        underlyingInstrument.setUnderlyingOptAttribute(new Character('B'));
        underlyingInstrument.setUnderlyingContractMultiplier(new Double(1.13));
        underlyingInstrument.setUnderlyingUnitOfMeasure(UnitOfMeasure.MetricTons);
        underlyingInstrument.setUnderlyingUnitOfMeasureQty(new Double(22.888));
        underlyingInstrument.setUnderlyingPriceUnitOfMeasure(PriceUnitOfMeasure.Gallons);
        underlyingInstrument.setUnderlyingPriceUnitOfMeasureQty(new Double(1.022));
        underlyingInstrument.setUnderlyingTimeUnit(TimeUnit.Minute);
        underlyingInstrument.setUnderlyingExerciseStyle(ExerciseStyle.American);
        underlyingInstrument.setUnderlyingCouponRate(new Double(1.023));
        underlyingInstrument.setUnderlyingSecurityExchange("COMEX");
        underlyingInstrument.setUnderlyingIssuer("NYSE_1");
        underlyingInstrument.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        underlyingInstrument.setEncodedUnderlyingIssuer(encLegIssuer);
        underlyingInstrument.setUnderlyingSecurityDesc("sec_desc_1");
        underlyingInstrument.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        underlyingInstrument.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        underlyingInstrument.setUnderlyingCPProgram("cp_program_1");
        underlyingInstrument.setUnderlyingCPRegType("reg_type_1");
        underlyingInstrument.setUnderlyingAllocationPercent(new Double(1.765));
        underlyingInstrument.setUnderlyingCurrency(Currency.UnitedStatesDollar);
        underlyingInstrument.setUnderlyingQty(new Double(2.13));
        underlyingInstrument.setUnderlyingSettlementType(UnderlyingSettlementType.Tplus1);
        underlyingInstrument.setUnderlyingCashAmount(new Double(231.45));
        underlyingInstrument.setUnderlyingCashType("DIFF");
        underlyingInstrument.setUnderlyingPx(new Double(25.66));
        underlyingInstrument.setUnderlyingDirtyPrice(new Double(25.77));
        underlyingInstrument.setUnderlyingEndPrice(new Double(25.88));
        underlyingInstrument.setUnderlyingStartValue(new Double(27.17));
        underlyingInstrument.setUnderlyingCurrentValue(new Double(25.55));
        underlyingInstrument.setUnderlyingEndValue(new Double(25.99));
        underlyingInstrument.setUnderlyingStipulations();
        underlyingInstrument.getUnderlyingStipulations().setNoUnderlyingStips(new Integer(2));
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipType("ABS");
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipValue("stips_value_1");
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipType("AMT");
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipValue("stips_value_2");
        underlyingInstrument.setUnderlyingAdjustedQuantity(new Double(2.888));
        underlyingInstrument.setUnderlyingFXRate(new Double("1.0345"));
        underlyingInstrument.setUnderlyingFXRateCalc(FXRateCalc.Divide);
        underlyingInstrument.setUnderlyingCapValue(new Double(22.499));
        underlyingInstrument.setUndlyInstrumentParties();
        underlyingInstrument.getUndlyInstrumentParties().setNoUndlyInstrumentParties(new Integer(2));
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyID("instr_party_id_0");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyRole(PartyRole.Agent);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_11");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.BIC);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_12");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.Application);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyID("instr_party_id_1");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyRole(PartyRole.AcceptableCounterparty);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_21");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.CashAccountName);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_22");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.ContactName);
        underlyingInstrument.setUnderlyingSettlMethod("settlment_0");
    }

    public void populate2(UnderlyingInstrument underlyingInstrument) {
        underlyingInstrument.setUnderlyingSymbol("JAVA_2");
        underlyingInstrument.setUnderlyingSymbolSfx("WI");
        underlyingInstrument.setUnderlyingSecurityID("sec_id_2");
        underlyingInstrument.setUnderlyingSecurityIDSource("2");
        underlyingInstrument.setNoUnderlyingSecurityAltID(new Integer(1));
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_2");
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("2");
        underlyingInstrument.setUnderlyingProduct(Product.COMMODITY);
        underlyingInstrument.setUnderlyingCFICode("cfi_code_2");
        underlyingInstrument.setUnderlyingSecurityType(SecurityType.OptionsOnComboEquity.getValue());
        underlyingInstrument.setUnderlyingSecuritySubType("sec_sub_type_2");
        underlyingInstrument.setUnderlyingMaturityMonthYear("200906w2");
        Calendar calmd = Calendar.getInstance();
        calmd.set(2009, 6, 22, 11, 25, 56);
        calmd.set(Calendar.MILLISECOND, 0);
        underlyingInstrument.setUnderlyingMaturityDate(calmd.getTime());
        underlyingInstrument.setUnderlyingMaturityTime(calmd.getTime());
        Calendar calcd = Calendar.getInstance();
        calcd.set(2010, 7, 13);
        underlyingInstrument.setUnderlyingCouponPaymentDate(calcd.getTime());
        Calendar calid = Calendar.getInstance();
        calid.set(2010, 1, 4);
        underlyingInstrument.setUnderlyingIssueDate(calid.getTime());
        underlyingInstrument.setUnderlyingRepoCollateralSecurityType("2");
        underlyingInstrument.setUnderlyingRepurchaseTerm(new Integer(31));
        underlyingInstrument.setUnderlyingRepurchaseRate(new Double(23.45));
        underlyingInstrument.setUnderlyingFactor(new Double(1.234));
        underlyingInstrument.setUnderlyingCreditRating("AAA");
        underlyingInstrument.setUnderlyingInstrRegistry("instr_reg_2");
        underlyingInstrument.setUnderlyingCountryOfIssue("US");
        underlyingInstrument.setUnderlyingStateOrProvinceOfIssue("NY");
        underlyingInstrument.setUnderlyingLocaleOfIssue("EN");
        Calendar calrd = Calendar.getInstance();
        calrd.set(2010, 0, 14);
        underlyingInstrument.setUnderlyingRedemptionDate(calrd.getTime());
        underlyingInstrument.setUnderlyingStrikePrice(new Double(24.15));
        underlyingInstrument.setUnderlyingStrikeCurrency(Currency.UnitedStatesDollar);
        underlyingInstrument.setUnderlyingOptAttribute(new Character('C'));
        underlyingInstrument.setUnderlyingContractMultiplier(new Double(1.13));
        underlyingInstrument.setUnderlyingUnitOfMeasure(UnitOfMeasure.Pounds);
        underlyingInstrument.setUnderlyingUnitOfMeasureQty(new Double(22.666));
        underlyingInstrument.setUnderlyingPriceUnitOfMeasure(PriceUnitOfMeasure.USTons);
        underlyingInstrument.setUnderlyingPriceUnitOfMeasureQty(new Double(1.033));
        underlyingInstrument.setUnderlyingTimeUnit(TimeUnit.Year);
        underlyingInstrument.setUnderlyingExerciseStyle(ExerciseStyle.Bermuda);
        underlyingInstrument.setUnderlyingCouponRate(new Double(1.023));
        underlyingInstrument.setUnderlyingSecurityExchange("COMEX");
        underlyingInstrument.setUnderlyingIssuer("NYSE_2");
        underlyingInstrument.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        underlyingInstrument.setEncodedUnderlyingIssuer(encLegIssuer);
        underlyingInstrument.setUnderlyingSecurityDesc("sec_desc_2");
        underlyingInstrument.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        underlyingInstrument.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        underlyingInstrument.setUnderlyingCPProgram("cp_program_2");
        underlyingInstrument.setUnderlyingCPRegType("reg_type_2");
        underlyingInstrument.setUnderlyingAllocationPercent(new Double(1.234));
        underlyingInstrument.setUnderlyingCurrency(Currency.UnitedStatesDollar);
        underlyingInstrument.setUnderlyingQty(new Double(2.131));
        underlyingInstrument.setUnderlyingSettlementType(UnderlyingSettlementType.Tplus3);
        underlyingInstrument.setUnderlyingCashAmount(new Double(124.45));
        underlyingInstrument.setUnderlyingCashType("FIXED");
        underlyingInstrument.setUnderlyingPx(new Double(25.661));
        underlyingInstrument.setUnderlyingDirtyPrice(new Double(25.771));
        underlyingInstrument.setUnderlyingEndPrice(new Double(25.881));
        underlyingInstrument.setUnderlyingStartValue(new Double(27.171));
        underlyingInstrument.setUnderlyingCurrentValue(new Double(25.551));
        underlyingInstrument.setUnderlyingEndValue(new Double(25.991));
        underlyingInstrument.setUnderlyingStipulations();
        underlyingInstrument.getUnderlyingStipulations().setNoUnderlyingStips(new Integer(2));
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipType("CPP");
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipValue("stips_value_3");
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipType("CPR");
        underlyingInstrument.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipValue("stips_value_4");
        underlyingInstrument.setUnderlyingAdjustedQuantity(new Double(2.222));
        underlyingInstrument.setUnderlyingFXRate(new Double("1.505"));
        underlyingInstrument.setUnderlyingFXRateCalc(FXRateCalc.Multiply);
        underlyingInstrument.setUnderlyingCapValue(new Double(11.499));
        underlyingInstrument.setUndlyInstrumentParties();
        underlyingInstrument.getUndlyInstrumentParties().setNoUndlyInstrumentParties(new Integer(2));
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyID("instr_party_id_0");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyRole(PartyRole.Agent);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_11");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.BIC);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_12");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.Application);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyID("instr_party_id_1");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyRole(PartyRole.AcceptableCounterparty);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_21");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.CashAccountName);
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_22");
        underlyingInstrument.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.ContactName);
        underlyingInstrument.setUnderlyingSettlMethod("settlment_1");
    }

    public void check(UnderlyingInstrument expected, UnderlyingInstrument actual) throws Exception {
        assertEquals(expected.getUnderlyingSymbol(), actual.getUnderlyingSymbol());
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getUnderlyingSymbolSfx());
        assertEquals(expected.getUnderlyingSecurityID(), actual.getUnderlyingSecurityID());
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getUnderlyingSecurityIDSource());
        assertEquals(expected.getNoUnderlyingSecurityAltID().intValue(), actual.getNoUnderlyingSecurityAltID().intValue());
        assertNotNull(actual.getUnderlyingSecurityAltIDGroups());
        assertEquals(expected.getUnderlyingSecurityAltIDGroups().length, actual.getUnderlyingSecurityAltIDGroups().length);
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltID(),
            actual.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltID());
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltIDSource(),
            actual.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltIDSource());
        assertEquals(expected.getUnderlyingProduct(), actual.getUnderlyingProduct());
        assertEquals(expected.getUnderlyingCFICode(), actual.getUnderlyingCFICode());
        assertEquals(expected.getUnderlyingSecurityType(), actual.getUnderlyingSecurityType());
        assertEquals(expected.getUnderlyingSecuritySubType(), actual.getUnderlyingSecuritySubType());
        assertEquals(expected.getUnderlyingMaturityMonthYear(), actual.getUnderlyingMaturityMonthYear());
        assertDateEquals(expected.getUnderlyingMaturityDate(), actual.getUnderlyingMaturityDate());
//        assertTimeEquals(expected.getUnderlyingMaturityTime(), actual.getUnderlyingMaturityTime());
        assertDateEquals(expected.getUnderlyingCouponPaymentDate(), actual.getUnderlyingCouponPaymentDate());
        assertDateEquals(expected.getUnderlyingIssueDate(), actual.getUnderlyingIssueDate());
        assertEquals(expected.getUnderlyingRepoCollateralSecurityType(), actual.getUnderlyingRepoCollateralSecurityType());
        assertEquals(expected.getUnderlyingRepurchaseTerm().intValue(), actual.getUnderlyingRepurchaseTerm().intValue());
        assertEquals(expected.getUnderlyingRepurchaseRate().doubleValue(), actual.getUnderlyingRepurchaseRate().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingFactor().doubleValue(), actual.getUnderlyingFactor().doubleValue(), 0.00001);
        assertEquals(expected.getUnderlyingCreditRating(), actual.getUnderlyingCreditRating());
        assertEquals(expected.getUnderlyingInstrRegistry(), actual.getUnderlyingInstrRegistry());
        assertEquals(expected.getUnderlyingCountryOfIssue(), actual.getUnderlyingCountryOfIssue());
        assertEquals(expected.getUnderlyingStateOrProvinceOfIssue(), actual.getUnderlyingStateOrProvinceOfIssue());
        assertEquals(expected.getUnderlyingLocaleOfIssue(), actual.getUnderlyingLocaleOfIssue());
        assertDateEquals(expected.getUnderlyingRedemptionDate(), actual.getUnderlyingRedemptionDate());
        assertEquals(expected.getUnderlyingStrikePrice().doubleValue(), actual.getUnderlyingStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingStrikeCurrency(), actual.getUnderlyingStrikeCurrency());
        assertEquals(expected.getUnderlyingOptAttribute().charValue(), actual.getUnderlyingOptAttribute().charValue());
        assertEquals(expected.getUnderlyingContractMultiplier().doubleValue(), actual.getUnderlyingContractMultiplier().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingUnitOfMeasure(), actual.getUnderlyingUnitOfMeasure());
        assertEquals(expected.getUnderlyingUnitOfMeasureQty().doubleValue(), actual.getUnderlyingUnitOfMeasureQty().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingPriceUnitOfMeasure(), actual.getUnderlyingPriceUnitOfMeasure());
        assertEquals(expected.getUnderlyingPriceUnitOfMeasureQty().doubleValue(), actual.getUnderlyingPriceUnitOfMeasureQty().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingTimeUnit(), actual.getUnderlyingTimeUnit());
        assertEquals(expected.getUnderlyingExerciseStyle(), actual.getUnderlyingExerciseStyle());
        assertEquals(expected.getUnderlyingCouponRate().doubleValue(), actual.getUnderlyingCouponRate().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingSecurityExchange(), actual.getUnderlyingSecurityExchange());
        assertEquals(expected.getUnderlyingIssuer(), actual.getUnderlyingIssuer());
        assertEquals(expected.getEncodedUnderlyingIssuerLen().intValue(), actual.getEncodedUnderlyingIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedUnderlyingIssuer(), actual.getEncodedUnderlyingIssuer());
        assertEquals(expected.getUnderlyingSecurityDesc(), actual.getUnderlyingSecurityDesc());
        assertEquals(expected.getEncodedUnderlyingSecurityDescLen().intValue(), actual.getEncodedUnderlyingSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedUnderlyingSecurityDesc(), actual.getEncodedUnderlyingSecurityDesc());
        assertEquals(expected.getUnderlyingCPProgram(), actual.getUnderlyingCPProgram());
        assertEquals(expected.getUnderlyingCPRegType(), actual.getUnderlyingCPRegType());
        assertEquals(expected.getUnderlyingAllocationPercent().doubleValue(), actual.getUnderlyingAllocationPercent().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingCurrency(), actual.getUnderlyingCurrency());
        assertEquals(expected.getUnderlyingQty().doubleValue(), actual.getUnderlyingQty().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingSettlementType(), actual.getUnderlyingSettlementType());
        assertEquals(expected.getUnderlyingCashAmount().doubleValue(), actual.getUnderlyingCashAmount().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingCashType(), actual.getUnderlyingCashType());
        assertEquals(expected.getUnderlyingPx().doubleValue(), actual.getUnderlyingPx().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingDirtyPrice().doubleValue(), actual.getUnderlyingDirtyPrice().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingEndPrice().doubleValue(), actual.getUnderlyingEndPrice().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingStartValue().doubleValue(), actual.getUnderlyingStartValue().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingCurrentValue().doubleValue(), actual.getUnderlyingCurrentValue().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingEndValue().doubleValue(), actual.getUnderlyingEndValue().doubleValue(), 0.01);
        assertNotNull(actual.getUnderlyingStipulations());
        assertEquals(expected.getUnderlyingStipulations().getNoUnderlyingStips().intValue(),
            actual.getUnderlyingStipulations().getNoUnderlyingStips().intValue());
        assertNotNull(actual.getUnderlyingStipulations().getUnderlyingStipsGroups());
        assertEquals(expected.getUnderlyingStipulations().getUnderlyingStipsGroups().length,
            actual.getUnderlyingStipulations().getUnderlyingStipsGroups().length);
        assertEquals(expected.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].getUnderlyingStipType(),
            actual.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].getUnderlyingStipType());
        assertEquals(expected.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].getUnderlyingStipValue(),
            actual.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].getUnderlyingStipValue());
        assertEquals(expected.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].getUnderlyingStipType(),
            actual.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].getUnderlyingStipType());
        assertEquals(expected.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].getUnderlyingStipValue(),
            actual.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].getUnderlyingStipValue());
        assertEquals(expected.getUnderlyingAdjustedQuantity().doubleValue(), actual.getUnderlyingAdjustedQuantity().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingFXRate().doubleValue(), actual.getUnderlyingFXRate().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingFXRateCalc(), actual.getUnderlyingFXRateCalc());
        assertEquals(expected.getUnderlyingCapValue().doubleValue(), actual.getUnderlyingCapValue().doubleValue(), 0.01);
        assertNotNull(actual.getUndlyInstrumentParties());
        assertEquals(expected.getUndlyInstrumentParties().getNoUndlyInstrumentParties().intValue(),
            actual.getUndlyInstrumentParties().getNoUndlyInstrumentParties().intValue());
        assertNotNull(actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups().length,
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups().length);
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartyID(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartyID());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartyIDSource(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartyIDSource());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartyRole(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartyRole());
        assertNotNull(actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubID(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubID());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubIDType(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubIDType());

        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyID(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyID());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyIDSource(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyIDSource());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyRole(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyRole());
        assertNotNull(actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubID(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubID());
        assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubIDType(),
            actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].getUndlyInstrumentPartySubIDType());

        assertEquals(expected.getUnderlyingSettlMethod(), actual.getUnderlyingSettlMethod());
    }
}
