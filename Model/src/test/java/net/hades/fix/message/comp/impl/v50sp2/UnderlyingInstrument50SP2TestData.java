/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument50SP2TestData.java
 *
 * $Id: UnderlyingInstrument50SP2TestData.java,v 1.4 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FXRateCalc;
import net.hades.fix.message.type.FlowScheduleType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.RestructuringType;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Seniority;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnderlyingSettlementType;
import net.hades.fix.message.type.UnitOfMeasure;

/**
 * Test utility for UnderlyingInstrument component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class UnderlyingInstrument50SP2TestData extends MsgTest {

    private static final UnderlyingInstrument50SP2TestData INSTANCE;

    static {
        INSTANCE = new UnderlyingInstrument50SP2TestData();
    }

    public static UnderlyingInstrument50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UnderlyingInstrument comp) {
        comp.setUnderlyingSymbol("JAVA_1");
        comp.setUnderlyingSymbolSfx("CD");
        comp.setUnderlyingSecurityID("sec_id_1");
        comp.setUnderlyingSecurityIDSource("1");
        comp.setNoUnderlyingSecurityAltID(new Integer(1));
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_1");
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("2");
        comp.setUnderlyingProduct(Product.COMMODITY);
        comp.setUnderlyingCFICode("cfi_code_1");
        comp.setUnderlyingSecurityType(SecurityType.Option.getValue());
        comp.setUnderlyingSecuritySubType("sec_sub_type_1");
        comp.setUnderlyingMaturityMonthYear("200906w1");
        Calendar calmd = Calendar.getInstance();
        calmd.set(2009, 7, 23, 10, 24, 55);
        calmd.set(Calendar.MILLISECOND, 0);
        comp.setUnderlyingMaturityDate(calmd.getTime());
        comp.setUnderlyingMaturityTime(calmd.getTime());
        Calendar calcd = Calendar.getInstance();
        calcd.set(2010, 8, 13);
        comp.setUnderlyingCouponPaymentDate(calcd.getTime());
        comp.setUnderlyingRestructuringType(RestructuringType.Full);
        comp.setUnderlyingSeniority(Seniority.SeniorSecured);
        comp.setUnderlyingNotionalPercentageOutstanding(new Double(34.66));
        comp.setUnderlyingOriginalNotionalPercentageOutstanding(new Double(24.77));
        comp.setUnderlyingAttachmentPoint(new Double(31.22));
        comp.setUnderlyingDetachmentPoint(new Double(44.55));
        Calendar calid = Calendar.getInstance();
        calid.set(2010, 2, 4);
        comp.setUnderlyingIssueDate(calid.getTime());
        comp.setUnderlyingRepoCollateralSecurityType("1");
        comp.setUnderlyingRepurchaseTerm(new Integer(31));
        comp.setUnderlyingRepurchaseRate(new Double(23.45));
        comp.setUnderlyingFactor(new Double(1.234));
        comp.setUnderlyingCreditRating("AAA");
        comp.setUnderlyingInstrRegistry("instr_reg_1");
        comp.setUnderlyingCountryOfIssue("US");
        comp.setUnderlyingStateOrProvinceOfIssue("NY");
        comp.setUnderlyingLocaleOfIssue("EN");
        Calendar calrd = Calendar.getInstance();
        calrd.set(2010, 1, 14);
        comp.setUnderlyingRedemptionDate(calrd.getTime());
        comp.setUnderlyingStrikePrice(new Double(24.15));
        comp.setUnderlyingStrikeCurrency(Currency.UnitedStatesDollar);
        comp.setUnderlyingOptAttribute(new Character('B'));
        comp.setUnderlyingContractMultiplier(new Double(1.13));
        comp.setUnderlyingContractMultiplierUnit(ContractMultiplierUnit.Days);
        comp.setUnderlyingFlowScheduleType(FlowScheduleType.NERCCalendarAllDays);
        comp.setUnderlyingUnitOfMeasure(UnitOfMeasure.MetricTons);
        comp.setUnderlyingUnitOfMeasureQty(new Double(22.888));
        comp.setUnderlyingPriceUnitOfMeasure(PriceUnitOfMeasure.Gallons);
        comp.setUnderlyingPriceUnitOfMeasureQty(new Double(1.022));
        comp.setUnderlyingTimeUnit(TimeUnit.Minute);
        comp.setUnderlyingExerciseStyle(ExerciseStyle.American);
        comp.setUnderlyingCouponRate(new Double(1.023));
        comp.setUnderlyingSecurityExchange("COMEX");
        comp.setUnderlyingIssuer("NYSE_1");
        comp.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        comp.setEncodedUnderlyingIssuer(encLegIssuer);
        comp.setUnderlyingSecurityDesc("sec_desc_1");
        comp.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        comp.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        comp.setUnderlyingCPProgram("cp_program_1");
        comp.setUnderlyingCPRegType("reg_type_1");
        comp.setUnderlyingAllocationPercent(new Double(1.765));
        comp.setUnderlyingCurrency(Currency.UnitedStatesDollar);
        comp.setUnderlyingQty(new Double(2.13));
        comp.setUnderlyingSettlementType(UnderlyingSettlementType.Tplus1);
        comp.setUnderlyingCashAmount(new Double(231.45));
        comp.setUnderlyingCashType("DIFF");
        comp.setUnderlyingPx(new Double(25.66));
        comp.setUnderlyingDirtyPrice(new Double(25.77));
        comp.setUnderlyingEndPrice(new Double(25.88));
        comp.setUnderlyingStartValue(new Double(27.17));
        comp.setUnderlyingCurrentValue(new Double(25.55));
        comp.setUnderlyingEndValue(new Double(25.99));
        comp.setUnderlyingStipulations();
        comp.getUnderlyingStipulations().setNoUnderlyingStips(new Integer(2));
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipType("ABS");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipValue("stips_value_1");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipType("AMT");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipValue("stips_value_2");
        comp.setUnderlyingAdjustedQuantity(new Double(2.888));
        comp.setUnderlyingFXRate(new Double("1.0345"));
        comp.setUnderlyingFXRateCalc(FXRateCalc.Divide);
        comp.setUnderlyingCapValue(new Double(22.499));
        comp.setUndlyInstrumentParties();
        comp.getUndlyInstrumentParties().setNoUndlyInstrumentParties(new Integer(2));
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyID("instr_party_id_0");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyRole(PartyRole.Agent);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_11");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.BIC);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_12");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.Application);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyID("instr_party_id_1");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyRole(PartyRole.AcceptableCounterparty);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_21");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.CashAccountName);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_22");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.ContactName);
        comp.setUnderlyingSettlMethod("settlment_0");
    }

    public void populate2(UnderlyingInstrument comp) {
        comp.setUnderlyingSymbol("JAVA_2");
        comp.setUnderlyingSymbolSfx("WI");
        comp.setUnderlyingSecurityID("sec_id_2");
        comp.setUnderlyingSecurityIDSource("2");
        comp.setNoUnderlyingSecurityAltID(new Integer(1));
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_2");
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("2");
        comp.setUnderlyingProduct(Product.COMMODITY);
        comp.setUnderlyingCFICode("cfi_code_2");
        comp.setUnderlyingSecurityType(SecurityType.OptionsOnComboEquity.getValue());
        comp.setUnderlyingSecuritySubType("sec_sub_type_2");
        comp.setUnderlyingMaturityMonthYear("200906w2");
        Calendar calmd = Calendar.getInstance();
        calmd.set(2009, 6, 22, 11, 25, 56);
        calmd.set(Calendar.MILLISECOND, 0);
        comp.setUnderlyingMaturityDate(calmd.getTime());
        comp.setUnderlyingMaturityTime(calmd.getTime());
        Calendar calcd = Calendar.getInstance();
        calcd.set(2010, 7, 13);
        comp.setUnderlyingCouponPaymentDate(calcd.getTime());
        comp.setUnderlyingRestructuringType(RestructuringType.Modified);
        comp.setUnderlyingSeniority(Seniority.Subordinated);
        comp.setUnderlyingNotionalPercentageOutstanding(new Double(34.69));
        comp.setUnderlyingOriginalNotionalPercentageOutstanding(new Double(24.79));
        comp.setUnderlyingAttachmentPoint(new Double(31.29));
        comp.setUnderlyingDetachmentPoint(new Double(44.59));
        Calendar calid = Calendar.getInstance();
        calid.set(2010, 1, 4);
        comp.setUnderlyingIssueDate(calid.getTime());
        comp.setUnderlyingRepoCollateralSecurityType("2");
        comp.setUnderlyingRepurchaseTerm(new Integer(31));
        comp.setUnderlyingRepurchaseRate(new Double(23.45));
        comp.setUnderlyingFactor(new Double(1.234));
        comp.setUnderlyingCreditRating("AAA");
        comp.setUnderlyingInstrRegistry("instr_reg_2");
        comp.setUnderlyingCountryOfIssue("US");
        comp.setUnderlyingStateOrProvinceOfIssue("NY");
        comp.setUnderlyingLocaleOfIssue("EN");
        Calendar calrd = Calendar.getInstance();
        calrd.set(2010, 0, 14);
        comp.setUnderlyingRedemptionDate(calrd.getTime());
        comp.setUnderlyingStrikePrice(new Double(24.15));
        comp.setUnderlyingStrikeCurrency(Currency.UnitedStatesDollar);
        comp.setUnderlyingOptAttribute(new Character('C'));
        comp.setUnderlyingContractMultiplier(new Double(1.13));
        comp.setUnderlyingContractMultiplierUnit(ContractMultiplierUnit.Hours);
        comp.setUnderlyingFlowScheduleType(FlowScheduleType.NERCEasternPeak);
        comp.setUnderlyingUnitOfMeasure(UnitOfMeasure.Pounds);
        comp.setUnderlyingUnitOfMeasureQty(new Double(22.666));
        comp.setUnderlyingPriceUnitOfMeasure(PriceUnitOfMeasure.USTons);
        comp.setUnderlyingPriceUnitOfMeasureQty(new Double(1.033));
        comp.setUnderlyingTimeUnit(TimeUnit.Year);
        comp.setUnderlyingExerciseStyle(ExerciseStyle.Bermuda);
        comp.setUnderlyingCouponRate(new Double(1.023));
        comp.setUnderlyingSecurityExchange("COMEX");
        comp.setUnderlyingIssuer("NYSE_2");
        comp.setEncodedUnderlyingIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        comp.setEncodedUnderlyingIssuer(encLegIssuer);
        comp.setUnderlyingSecurityDesc("sec_desc_2");
        comp.setEncodedUnderlyingSecurityDescLen(new Integer(6));
        byte[] encLegSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        comp.setEncodedUnderlyingSecurityDesc(encLegSecDesc);
        comp.setUnderlyingCPProgram("cp_program_2");
        comp.setUnderlyingCPRegType("reg_type_2");
        comp.setUnderlyingAllocationPercent(new Double(1.234));
        comp.setUnderlyingCurrency(Currency.UnitedStatesDollar);
        comp.setUnderlyingQty(new Double(2.131));
        comp.setUnderlyingSettlementType(UnderlyingSettlementType.Tplus3);
        comp.setUnderlyingCashAmount(new Double(124.45));
        comp.setUnderlyingCashType("FIXED");
        comp.setUnderlyingPx(new Double(25.661));
        comp.setUnderlyingDirtyPrice(new Double(25.771));
        comp.setUnderlyingEndPrice(new Double(25.881));
        comp.setUnderlyingStartValue(new Double(27.171));
        comp.setUnderlyingCurrentValue(new Double(25.551));
        comp.setUnderlyingEndValue(new Double(25.991));
        comp.setUnderlyingStipulations();
        comp.getUnderlyingStipulations().setNoUnderlyingStips(new Integer(2));
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipType("CPP");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipValue("stips_value_3");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipType("CPR");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipValue("stips_value_4");
        comp.setUnderlyingAdjustedQuantity(new Double(2.222));
        comp.setUnderlyingFXRate(new Double("1.505"));
        comp.setUnderlyingFXRateCalc(FXRateCalc.Multiply);
        comp.setUnderlyingCapValue(new Double(11.499));
        comp.setUndlyInstrumentParties();
        comp.getUndlyInstrumentParties().setNoUndlyInstrumentParties(new Integer(2));
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyID("instr_party_id_0");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setUndlyInstrumentPartyRole(PartyRole.Agent);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_11");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.BIC);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_12");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[0].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.Application);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyID("instr_party_id_1");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setUndlyInstrumentPartyRole(PartyRole.AcceptableCounterparty);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].setNoUndlyInstrumentPartySubIDGroups(new Integer(2));
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubID("instr_party_sub_id_21");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[0].setUndlyInstrumentPartySubIDType(PartySubIDType.CashAccountName);
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubID("instr_party_sub_id_22");
        comp.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartySubIDGroups()[1].setUndlyInstrumentPartySubIDType(PartySubIDType.ContactName);
        comp.setUnderlyingSettlMethod("settlment_1");
    }

    public void check(UnderlyingInstrument expected, UnderlyingInstrument actual) throws Exception {
        assertEquals(expected.getUnderlyingSymbol(), actual.getUnderlyingSymbol());
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getUnderlyingSymbolSfx());
        assertEquals(expected.getUnderlyingSecurityID(), actual.getUnderlyingSecurityID());
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getUnderlyingSecurityIDSource());
        assertEquals(expected.getNoUnderlyingSecurityAltID().intValue(),
            actual.getNoUnderlyingSecurityAltID().intValue());
        assertNotNull(actual.getUnderlyingSecurityAltIDGroups());

        assertEquals(expected.getUnderlyingSecurityAltIDGroups().length,
            actual.getUnderlyingSecurityAltIDGroups().length);
        for (int i = 0; i < actual.getNoUnderlyingSecurityAltID().intValue(); i++) {
            assertEquals(expected.getUnderlyingSecurityAltIDGroups()[i].getUnderlyingSecurityAltID(),
                actual.getUnderlyingSecurityAltIDGroups()[i].getUnderlyingSecurityAltID());
            assertEquals(expected.getUnderlyingSecurityAltIDGroups()[i].getUnderlyingSecurityAltIDSource(),
                actual.getUnderlyingSecurityAltIDGroups()[i].getUnderlyingSecurityAltIDSource());
        }
        assertEquals(expected.getUnderlyingProduct(), actual.getUnderlyingProduct());
        assertEquals(expected.getUnderlyingCFICode(), actual.getUnderlyingCFICode());
        assertEquals(expected.getUnderlyingSecurityType(), actual.getUnderlyingSecurityType());
        assertEquals(expected.getUnderlyingSecuritySubType(), actual.getUnderlyingSecuritySubType());
        assertEquals(expected.getUnderlyingMaturityMonthYear(), actual.getUnderlyingMaturityMonthYear());
        assertDateEquals(expected.getUnderlyingMaturityDate(), actual.getUnderlyingMaturityDate());
//        assertTimeEquals(expected.getUnderlyingMaturityTime(), actual.getUnderlyingMaturityTime());
        assertDateEquals(expected.getUnderlyingCouponPaymentDate(), actual.getUnderlyingCouponPaymentDate());
        assertEquals(expected.getUnderlyingRestructuringType(), actual.getUnderlyingRestructuringType());
        assertEquals(expected.getUnderlyingSeniority(), actual.getUnderlyingSeniority());
        assertEquals(expected.getUnderlyingNotionalPercentageOutstanding().doubleValue(),
            actual.getUnderlyingNotionalPercentageOutstanding().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingOriginalNotionalPercentageOutstanding().doubleValue(),
            actual.getUnderlyingOriginalNotionalPercentageOutstanding().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingAttachmentPoint().doubleValue(), actual.getUnderlyingAttachmentPoint().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingDetachmentPoint().doubleValue(), actual.getUnderlyingDetachmentPoint().doubleValue(), 0.001);
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
//        assertEquals(expected.getUnderlyingContractMultiplierUnit(), actual.getUnderlyingContractMultiplierUnit());
        assertEquals(expected.getUnderlyingFlowScheduleType(), actual.getUnderlyingFlowScheduleType());
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
        for (int i = 0; i < actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups().length; i++) {
            assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyID(),
                actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyID());
            assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyIDSource(),
                actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyIDSource());
            assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyRole(),
                actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartyRole());
            assertNotNull(actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i]);
            assertNotNull(actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups());
            for (int j = 0; j < actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups().length; j++) {
                assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubID(),
                    actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubID());
                assertEquals(expected.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubIDType(),
                    actual.getUndlyInstrumentParties().getUndlyInstrumentPartyIDGroups()[i].getUndlyInstrumentPartySubIDGroups()[j].getUndlyInstrumentPartySubIDType());
            }
        }
        assertEquals(expected.getUnderlyingSettlMethod(), actual.getUnderlyingSettlMethod());
    }
}
