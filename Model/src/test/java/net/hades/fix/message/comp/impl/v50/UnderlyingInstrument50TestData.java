/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg50TestData.java
 *
 * $Id: UnderlyingInstrument50TestData.java,v 1.3 2011-10-29 09:42:25 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.FXRateCalc;
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
public class UnderlyingInstrument50TestData extends MsgTest {

    private static final UnderlyingInstrument50TestData INSTANCE;

    static {
        INSTANCE = new UnderlyingInstrument50TestData();
    }

    public static UnderlyingInstrument50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UnderlyingInstrument comp) {
        comp.setUnderlyingSymbol("JAVA_1");
        comp.setUnderlyingSymbolSfx("CD");
        comp.setUnderlyingSecurityID("sec_id_1");
        comp.setUnderlyingSecurityIDSource("5");
        comp.setNoUnderlyingSecurityAltID(new Integer(1));
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_11");
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("1");
//        comp.getUnderlyingSecurityAltIDGroups()[1].setUnderlyingSecurityAltID("sec_alt_id_12");
//        comp.getUnderlyingSecurityAltIDGroups()[1].setUnderlyingSecurityAltIDSource("4");
        comp.setUnderlyingProduct(Product.COMMODITY);
        comp.setUnderlyingCFICode("cfi_code_1");
        comp.setUnderlyingSecurityType(SecurityType.Option.getValue());
        comp.setUnderlyingSecuritySubType("sec_sub_type_1");
        comp.setUnderlyingMaturityMonthYear("200906w2");
        comp.setUnderlyingMaturityDate(new Date());
        comp.setUnderlyingCouponPaymentDate(new Date());
        comp.setUnderlyingIssueDate(new Date());
        comp.setUnderlyingRepoCollateralSecurityType("3");
        comp.setUnderlyingRepurchaseTerm(new Integer(31));
        comp.setUnderlyingRepurchaseRate(new Double(23.45));
        comp.setUnderlyingFactor(new Double(1.234));
        comp.setUnderlyingCreditRating("AAA");
        comp.setUnderlyingInstrRegistry("ISO");
        comp.setUnderlyingCountryOfIssue("US");
        comp.setUnderlyingStateOrProvinceOfIssue("NY");
        comp.setUnderlyingLocaleOfIssue("EN");
        comp.setUnderlyingRedemptionDate(new Date());
        comp.setUnderlyingStrikePrice(new Double(24.15));
        comp.setUnderlyingStrikeCurrency(Currency.UnitedStatesDollar);
        comp.setUnderlyingOptAttribute(new Character('B'));
        comp.setUnderlyingContractMultiplier(new Double(1.13));
        comp.setUnderlyingUnitOfMeasure(UnitOfMeasure.MetricTons);
        comp.setUnderlyingTimeUnit(TimeUnit.Minute);
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
        comp.setUnderlyingCashType("FIXED");
        comp.setUnderlyingPx(new Double(25.66));
        comp.setUnderlyingDirtyPrice(new Double(25.77));
        comp.setUnderlyingEndPrice(new Double(25.88));
        comp.setUnderlyingStartValue(new Double(27.17));
        comp.setUnderlyingCurrentValue(new Double(25.55));
        comp.setUnderlyingEndValue(new Double(25.99));
        comp.setUnderlyingStipulations();
        comp.getUnderlyingStipulations().setNoUnderlyingStips(new Integer(1));
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipType("ABS");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipValue("stips_value_1");
//        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipType("AMT");
//        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipValue("stips_value_2");
        comp.setUnderlyingAdjustedQuantity(new Double(2.888));
        comp.setUnderlyingFXRate(new Double("1.0345"));
        comp.setUnderlyingFXRateCalc(FXRateCalc.Divide);
        comp.setUnderlyingCapValue(new Double(22.499));
        // UndlyInstrumentParties
        comp.setUndlyInstrumentParties();
        UndlyInstrumentParties50TestData.getInstance().populate(comp.getUndlyInstrumentParties());
        comp.setUnderlyingSettlMethod("sett method 1");
    }

    public void populate2(UnderlyingInstrument comp) {
        comp.setUnderlyingSymbol("JAVA_2");
        comp.setUnderlyingSymbolSfx("WI");
        comp.setUnderlyingSecurityID("sec_id_2");
        comp.setUnderlyingSecurityIDSource("4");
        comp.setNoUnderlyingSecurityAltID(new Integer(1));
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_21");
        comp.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("1");
//        comp.getUnderlyingSecurityAltIDGroups()[1].setUnderlyingSecurityAltID("sec_alt_id_22");
//        comp.getUnderlyingSecurityAltIDGroups()[1].setUnderlyingSecurityAltIDSource("2");
        comp.setUnderlyingProduct(Product.COMMODITY);
        comp.setUnderlyingCFICode("cfi_code_2");
        comp.setUnderlyingSecurityType(SecurityType.CorporatePrivatePlacement.getValue());
        comp.setUnderlyingSecuritySubType("sec_sub_type_2");
        comp.setUnderlyingMaturityMonthYear("200906w1");
        comp.setUnderlyingMaturityDate(new Date());
        comp.setUnderlyingCouponPaymentDate(new Date());
        comp.setUnderlyingIssueDate(new Date());
        comp.setUnderlyingRepoCollateralSecurityType("4");
        comp.setUnderlyingRepurchaseTerm(new Integer(31));
        comp.setUnderlyingRepurchaseRate(new Double(23.45));
        comp.setUnderlyingFactor(new Double(1.234));
        comp.setUnderlyingCreditRating("AAA");
        comp.setUnderlyingInstrRegistry("BIC");
        comp.setUnderlyingCountryOfIssue("US");
        comp.setUnderlyingStateOrProvinceOfIssue("NY");
        comp.setUnderlyingLocaleOfIssue("EN");
        comp.setUnderlyingRedemptionDate(new Date());
        comp.setUnderlyingStrikePrice(new Double(24.15));
        comp.setUnderlyingStrikeCurrency(Currency.UnitedStatesDollar);
        comp.setUnderlyingOptAttribute(new Character('C'));
        comp.setUnderlyingContractMultiplier(new Double(1.13));
        comp.setUnderlyingUnitOfMeasure(UnitOfMeasure.Pounds);
        comp.setUnderlyingTimeUnit(TimeUnit.Year);
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
        comp.setUnderlyingCashType("DIFF");
        comp.setUnderlyingPx(new Double(25.661));
        comp.setUnderlyingDirtyPrice(new Double(25.771));
        comp.setUnderlyingEndPrice(new Double(25.881));
        comp.setUnderlyingStartValue(new Double(27.171));
        comp.setUnderlyingCurrentValue(new Double(25.551));
        comp.setUnderlyingEndValue(new Double(25.991));
        comp.setUnderlyingStipulations();
        comp.getUnderlyingStipulations().setNoUnderlyingStips(new Integer(1));
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipType("CPP");
        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[0].setUnderlyingStipValue("stips_value_3");
//        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipType("CPR");
//        comp.getUnderlyingStipulations().getUnderlyingStipsGroups()[1].setUnderlyingStipValue("stips_value_4");
        comp.setUnderlyingAdjustedQuantity(new Double(2.222));
        comp.setUnderlyingFXRate(new Double("1.505"));
        comp.setUnderlyingFXRateCalc(FXRateCalc.Multiply);
        comp.setUnderlyingCapValue(new Double(11.499));
        // UndlyInstrumentParties
        comp.setUndlyInstrumentParties();
        UndlyInstrumentParties50TestData.getInstance().populate(comp.getUndlyInstrumentParties());
        comp.setUnderlyingSettlMethod("sett method 2");
    }

    public void populate1(quickfix.fix50.component.UnderlyingInstrument grpl) throws Exception {
        grpl.setString(quickfix.field.UnderlyingSymbol.FIELD, "JAVA");
        grpl.setString(quickfix.field.UnderlyingSymbolSfx.FIELD, "JAVA Shares");
        grpl.setString(quickfix.field.UnderlyingSecurityID.FIELD, "JAVA");
        grpl.setString(quickfix.field.UnderlyingSecurityIDSource.FIELD, "NYSE");
        grpl.setInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD, 2);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple1 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple1.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID JAVA 1");
        grple1.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "Alt ID JAVA NASDAQ 1");
        grpl.addGroup(grple1);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple2 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple2.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID JAVA 2");
        grple2.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "Alt ID JAVA NASDAQ 2");
        grpl.addGroup(grple2);
        grpl.setInt(quickfix.field.UnderlyingProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.UnderlyingCFICode.FIELD, "CFI MOT");
        grpl.setString(quickfix.field.UnderlyingSecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        grpl.setString(quickfix.field.UnderlyingSecuritySubType.FIELD, "OOO");
        grpl.setString(quickfix.field.UnderlyingMaturityMonthYear.FIELD, "200806w2");
        grpl.setUtcDateOnly(quickfix.field.UnderlyingMaturityDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.UnderlyingCouponPaymentDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.UnderlyingIssueDate.FIELD, new Date());
        grpl.setInt(quickfix.field.UnderlyingRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.UnderlyingRepurchaseTerm.FIELD, 5);
        grpl.setDecimal(quickfix.field.UnderlyingRepurchaseRate.FIELD, new BigDecimal("15.5"));
        grpl.setDecimal(quickfix.field.UnderlyingFactor.FIELD, new BigDecimal("1.2367"));
        grpl.setString(quickfix.field.UnderlyingCreditRating.FIELD, "AAA");
        grpl.setString(quickfix.field.UnderlyingInstrRegistry.FIELD, "Rego 1");
        grpl.setString(quickfix.field.UnderlyingCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.UnderlyingStateOrProvinceOfIssue.FIELD, "New York");
        grpl.setString(quickfix.field.UnderlyingLocaleOfIssue.FIELD, "US");
        grpl.setUtcDateOnly(quickfix.field.UnderlyingRedemptionDate.FIELD, new Date());
        grpl.setDecimal(quickfix.field.UnderlyingStrikePrice.FIELD, new BigDecimal("25.48"));
        grpl.setString(quickfix.field.UnderlyingStrikeCurrency.FIELD, "USD");
        grpl.setChar(quickfix.field.UnderlyingOptAttribute.FIELD, 'C');
        grpl.setDecimal(quickfix.field.UnderlyingContractMultiplier.FIELD, new BigDecimal("1.023"));
        grpl.setString(quickfix.field.UnderlyingUnitOfMeasure.FIELD, UnitOfMeasure.Pounds.getValue());
        grpl.setString(quickfix.field.UnderlyingTimeUnit.FIELD, TimeUnit.Year.getValue());
        grpl.setDecimal(quickfix.field.UnderlyingCouponRate.FIELD, new BigDecimal("1.077"));
        grpl.setString(quickfix.field.UnderlyingSecurityExchange.FIELD, "NYSE");
        grpl.setString(quickfix.field.UnderlyingIssuer.FIELD, "NYSE PL");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        grpl.setInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedUnderlyingIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        grpl.setString(quickfix.field.UnderlyingSecurityDesc.FIELD, "Motorola shares");
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        grpl.setInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        grpl.setString(quickfix.field.UnderlyingCPProgram.FIELD, "CP program 1");
        grpl.setString(quickfix.field.UnderlyingCPRegType.FIELD, "CP reg type 1");
        grpl.setString(quickfix.field.UnderlyingCurrency.FIELD, "USD");
        grpl.setDouble(quickfix.field.UnderlyingQty.FIELD, new Double("24.5"));
        grpl.setInt(quickfix.field.UnderlyingSettlementType.FIELD, quickfix.field.UnderlyingSettlementType.Tp1);
        grpl.setDouble(quickfix.field.UnderlyingCashAmount.FIELD, new Double("33.323"));
        grpl.setString(quickfix.field.UnderlyingCashType.FIELD, quickfix.field.UnderlyingCashType.DIFF);
        grpl.setDouble(quickfix.field.UnderlyingPx.FIELD, new Double("3.457"));
        grpl.setDouble(quickfix.field.UnderlyingDirtyPrice.FIELD, new Double("3.556"));
        grpl.setDouble(quickfix.field.UnderlyingEndPrice.FIELD, new Double("3.677"));
        grpl.setDouble(quickfix.field.UnderlyingStartValue.FIELD, new Double("3.357"));
        grpl.setDouble(quickfix.field.UnderlyingCurrentValue.FIELD, new Double("3.367"));
        grpl.setDouble(quickfix.field.UnderlyingEndValue.FIELD, new Double("3.876"));
        grpl.setInt(quickfix.field.NoUnderlyingStips.FIELD, 2);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips grpls1 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips();
        grpls1.setString(quickfix.field.UnderlyingStipType.FIELD, "Stip Type 1");
        grpls1.setString(quickfix.field.UnderlyingStipValue.FIELD, "Stip Value 1");
        grpl.addGroup(grpls1);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips grpls2 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips();
        grpls2.setString(quickfix.field.UnderlyingStipType.FIELD, "Stip Type 2");
        grpls2.setString(quickfix.field.UnderlyingStipValue.FIELD, "Stip Value 2");
        grpl.addGroup(grpls2);
        grpl.setDouble(quickfix.field.UnderlyingAdjustedQuantity.FIELD, new Double("4.444"));
        grpl.setDouble(quickfix.field.UnderlyingAllocationPercent.FIELD, new Double("55.5"));
        grpl.setDouble(quickfix.field.UnderlyingFXRate.FIELD, new Double("1.555"));
        grpl.setChar(quickfix.field.UnderlyingFXRateCalc.FIELD, quickfix.field.UnderlyingFXRateCalc.DIVIDE);
        grpl.setDouble(quickfix.field.UnderlyingCapValue.FIELD, new Double("8.555"));
        // UndlyInstrumentParties
        quickfix.fix50.component.UndlyInstrumentParties undlyin = new quickfix.fix50.component.UndlyInstrumentParties();
        UndlyInstrumentParties50TestData.getInstance().populate(undlyin);
        grpl.set(undlyin);

        grpl.setString(quickfix.field.UnderlyingSettlMethod.FIELD, "settlment_1");
    }

    public void populate2(quickfix.fix50.component.UnderlyingInstrument grpl) throws Exception {
        grpl.setString(quickfix.field.UnderlyingSymbol.FIELD, "ORACLE");
        grpl.setString(quickfix.field.UnderlyingSymbolSfx.FIELD, "ORACLE Shares");
        grpl.setString(quickfix.field.UnderlyingSecurityID.FIELD, "ORACLE");
        grpl.setString(quickfix.field.UnderlyingSecurityIDSource.FIELD, "NYSE");
        grpl.setInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD, 2);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple1 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple1.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID ORACLE 1");
        grple1.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "Alt ID ORACLE NASDAQ 1");
        grpl.addGroup(grple1);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple2 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple2.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID ORACLE 2");
        grple2.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "Alt ID ORACLE NASDAQ 2");
        grpl.addGroup(grple2);
        grpl.setInt(quickfix.field.UnderlyingProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.UnderlyingCFICode.FIELD, "CFI ORACLE");
        grpl.setString(quickfix.field.UnderlyingSecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        grpl.setString(quickfix.field.UnderlyingSecuritySubType.FIELD, "XXX");
        grpl.setString(quickfix.field.UnderlyingMaturityMonthYear.FIELD, "200906w2");
        grpl.setUtcDateOnly(quickfix.field.UnderlyingMaturityDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.UnderlyingCouponPaymentDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.UnderlyingIssueDate.FIELD, new Date());
        grpl.setInt(quickfix.field.UnderlyingRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.UnderlyingRepurchaseTerm.FIELD, 5);
        grpl.setDecimal(quickfix.field.UnderlyingRepurchaseRate.FIELD, new BigDecimal("15.51"));
        grpl.setDecimal(quickfix.field.UnderlyingFactor.FIELD, new BigDecimal("1.2361"));
        grpl.setString(quickfix.field.UnderlyingCreditRating.FIELD, "AA");
        grpl.setString(quickfix.field.UnderlyingInstrRegistry.FIELD, "Rego 2");
        grpl.setString(quickfix.field.UnderlyingCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.UnderlyingStateOrProvinceOfIssue.FIELD, "Los Angeles");
        grpl.setString(quickfix.field.UnderlyingLocaleOfIssue.FIELD, "US");
        grpl.setUtcDateOnly(quickfix.field.UnderlyingRedemptionDate.FIELD, new Date());
        grpl.setDecimal(quickfix.field.UnderlyingStrikePrice.FIELD, new BigDecimal("25.481"));
        grpl.setString(quickfix.field.UnderlyingStrikeCurrency.FIELD, "USD");
        grpl.setChar(quickfix.field.UnderlyingOptAttribute.FIELD, 'C');
        grpl.setDecimal(quickfix.field.UnderlyingContractMultiplier.FIELD, new BigDecimal("1.0231"));
        grpl.setString(quickfix.field.UnderlyingUnitOfMeasure.FIELD, UnitOfMeasure.Bushels.getValue());
        grpl.setString(quickfix.field.UnderlyingTimeUnit.FIELD, TimeUnit.Year.getValue());
        grpl.setDecimal(quickfix.field.UnderlyingCouponRate.FIELD, new BigDecimal("1.0771"));
        grpl.setString(quickfix.field.UnderlyingSecurityExchange.FIELD, "NYSE");
        grpl.setString(quickfix.field.UnderlyingIssuer.FIELD, "NYSE PL");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        grpl.setInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedUnderlyingIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        grpl.setString(quickfix.field.UnderlyingSecurityDesc.FIELD, "Oracle shares");
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        grpl.setInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        grpl.setString(quickfix.field.UnderlyingCPProgram.FIELD, "CP program 2");
        grpl.setString(quickfix.field.UnderlyingCPRegType.FIELD, "CP reg type 2");
        grpl.setString(quickfix.field.UnderlyingCurrency.FIELD, "USD");
        grpl.setDouble(quickfix.field.UnderlyingQty.FIELD, new Double("24.51"));
        grpl.setInt(quickfix.field.UnderlyingSettlementType.FIELD, quickfix.field.UnderlyingSettlementType.Tp3);
        grpl.setDouble(quickfix.field.UnderlyingCashAmount.FIELD, new Double("44.323"));
        grpl.setString(quickfix.field.UnderlyingCashType.FIELD, quickfix.field.UnderlyingCashType.FIXED);
        grpl.setDouble(quickfix.field.UnderlyingPx.FIELD, new Double("3.4571"));
        grpl.setDouble(quickfix.field.UnderlyingDirtyPrice.FIELD, new Double("3.5561"));
        grpl.setDouble(quickfix.field.UnderlyingEndPrice.FIELD, new Double("3.6771"));
        grpl.setDouble(quickfix.field.UnderlyingStartValue.FIELD, new Double("3.3571"));
        grpl.setDouble(quickfix.field.UnderlyingCurrentValue.FIELD, new Double("3.3671"));
        grpl.setDouble(quickfix.field.UnderlyingEndValue.FIELD, new Double("3.8761"));
        grpl.setInt(quickfix.field.NoUnderlyingStips.FIELD, 2);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips grpls1 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips();
        grpls1.setString(quickfix.field.UnderlyingStipType.FIELD, "Stip Type 3");
        grpls1.setString(quickfix.field.UnderlyingStipValue.FIELD, "Stip Value 3");
        grpl.addGroup(grpls1);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips grpls2 = new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingStips();
        grpls2.setString(quickfix.field.UnderlyingStipType.FIELD, "Stip Type 4");
        grpls2.setString(quickfix.field.UnderlyingStipValue.FIELD, "Stip Value 4");
        grpl.addGroup(grpls2);
        grpl.setDouble(quickfix.field.UnderlyingAdjustedQuantity.FIELD, new Double("4.666"));
        grpl.setDouble(quickfix.field.UnderlyingAllocationPercent.FIELD, new Double("66.5"));
        grpl.setDouble(quickfix.field.UnderlyingFXRate.FIELD, new Double("1.666"));
        grpl.setChar(quickfix.field.UnderlyingFXRateCalc.FIELD, quickfix.field.UnderlyingFXRateCalc.MULTIPLY);
        grpl.setDouble(quickfix.field.UnderlyingCapValue.FIELD, new Double("8.666"));
        // UndlyInstrumentParties
        quickfix.fix50.component.UndlyInstrumentParties undlyin = new quickfix.fix50.component.UndlyInstrumentParties();
        UndlyInstrumentParties50TestData.getInstance().populate(undlyin);
        grpl.set(undlyin);
        
        grpl.setString(quickfix.field.UnderlyingSettlMethod.FIELD, "settlment_2");
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
        assertEquals(expected.getUnderlyingTimeUnit(), actual.getUnderlyingTimeUnit());
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
        for (int i = 0; i < actual.getUnderlyingStipulations().getNoUnderlyingStips().intValue(); i++) {
            assertEquals(expected.getUnderlyingStipulations().getUnderlyingStipsGroups()[i].getUnderlyingStipType(),
                actual.getUnderlyingStipulations().getUnderlyingStipsGroups()[i].getUnderlyingStipType());
            assertEquals(expected.getUnderlyingStipulations().getUnderlyingStipsGroups()[i].getUnderlyingStipValue(),
                actual.getUnderlyingStipulations().getUnderlyingStipsGroups()[i].getUnderlyingStipValue());
        }

        assertEquals(expected.getUnderlyingAdjustedQuantity().doubleValue(), actual.getUnderlyingAdjustedQuantity().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingFXRate().doubleValue(), actual.getUnderlyingFXRate().doubleValue(), 0.01);
        assertEquals(expected.getUnderlyingFXRateCalc(), actual.getUnderlyingFXRateCalc());
        assertEquals(expected.getUnderlyingCapValue().doubleValue(), actual.getUnderlyingCapValue().doubleValue(), 0.01);
        assertNotNull(actual.getUndlyInstrumentParties());
        assertEquals(expected.getUnderlyingSettlMethod(), actual.getUnderlyingSettlMethod());
    }

    public void check(UnderlyingInstrument expected, quickfix.fix50.component.UnderlyingInstrument actual) throws Exception {
        assertEquals(expected.getUnderlyingSymbol(), actual.getString(quickfix.field.UnderlyingSymbol.FIELD));
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getString(quickfix.field.UnderlyingSymbolSfx.FIELD));
        assertEquals(expected.getUnderlyingSecurityID(), actual.getString(quickfix.field.UnderlyingSecurityID.FIELD));
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getString(quickfix.field.UnderlyingSecurityIDSource.FIELD));
        assertTrue(actual.hasGroup(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        assertEquals(expected.getNoUnderlyingSecurityAltID().intValue(), actual.getInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grp1 =
            new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        actual.getGroup(1, grp1);
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltID(), grp1.getUnderlyingSecurityAltID().getValue());
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltIDSource(), grp1.getUnderlyingSecurityAltIDSource().getValue());
        assertEquals(expected.getUnderlyingProduct().getValue(), actual.getInt(quickfix.field.UnderlyingProduct.FIELD));

        assertEquals(expected.getUnderlyingCFICode(), actual.getString(quickfix.field.UnderlyingCFICode.FIELD));
        assertEquals(expected.getUnderlyingSecurityType(), actual.getString(quickfix.field.UnderlyingSecurityType.FIELD));
        assertEquals(expected.getUnderlyingSecuritySubType(), actual.getString(quickfix.field.UnderlyingSecuritySubType.FIELD));
        assertEquals(expected.getUnderlyingMaturityMonthYear(), actual.getString(quickfix.field.UnderlyingMaturityMonthYear.FIELD));
        assertEquals(formatQFStringDate(expected.getUnderlyingMaturityDate()), actual.getString(quickfix.field.UnderlyingMaturityDate.FIELD));
        assertEquals(formatQFStringDate(expected.getUnderlyingCouponPaymentDate()), actual.getString(quickfix.field.UnderlyingCouponPaymentDate.FIELD));
        assertEquals(formatQFStringDate(expected.getUnderlyingIssueDate()), actual.getString(quickfix.field.UnderlyingIssueDate.FIELD));
        assertEquals(expected.getUnderlyingRepoCollateralSecurityType(), actual.getString(quickfix.field.UnderlyingRepoCollateralSecurityType.FIELD));
        assertEquals(expected.getUnderlyingRepurchaseTerm().intValue(), actual.getInt(quickfix.field.UnderlyingRepurchaseTerm.FIELD));
        assertEquals(expected.getUnderlyingRepurchaseRate().doubleValue(), actual.getDouble(quickfix.field.UnderlyingRepurchaseRate.FIELD), 0.001);
        assertEquals(expected.getUnderlyingFactor().doubleValue(), actual.getDouble(quickfix.field.UnderlyingFactor.FIELD), 0.001);
        assertEquals(expected.getUnderlyingCreditRating(), actual.getString(quickfix.field.UnderlyingCreditRating.FIELD));
        assertEquals(expected.getUnderlyingInstrRegistry(), actual.getString(quickfix.field.UnderlyingInstrRegistry.FIELD));
        assertEquals(expected.getUnderlyingCountryOfIssue(), actual.getString(quickfix.field.UnderlyingCountryOfIssue.FIELD));
        assertEquals(expected.getUnderlyingStateOrProvinceOfIssue(), actual.getString(quickfix.field.UnderlyingStateOrProvinceOfIssue.FIELD));
        assertEquals(expected.getUnderlyingLocaleOfIssue(), actual.getString(quickfix.field.UnderlyingLocaleOfIssue.FIELD));
        assertEquals(formatQFStringDate(expected.getUnderlyingRedemptionDate()), actual.getString(quickfix.field.UnderlyingRedemptionDate.FIELD));
        assertEquals(expected.getUnderlyingStrikePrice().doubleValue(), actual.getDouble(quickfix.field.UnderlyingStrikePrice.FIELD), 0.001);
        assertEquals(expected.getUnderlyingStrikeCurrency().getValue(), actual.getString(quickfix.field.UnderlyingStrikeCurrency.FIELD));
        assertEquals(expected.getUnderlyingOptAttribute().charValue(), actual.getChar(quickfix.field.UnderlyingOptAttribute.FIELD));
        assertEquals(expected.getUnderlyingContractMultiplier().doubleValue(), actual.getDouble(quickfix.field.UnderlyingContractMultiplier.FIELD), 0.001);
        assertEquals(expected.getUnderlyingUnitOfMeasure().getValue(), actual.getString(quickfix.field.UnderlyingUnitOfMeasure.FIELD));
        assertEquals(expected.getUnderlyingTimeUnit().getValue(), actual.getString(quickfix.field.UnderlyingTimeUnit.FIELD));
        assertEquals(expected.getUnderlyingCouponRate().doubleValue(), actual.getDouble(quickfix.field.UnderlyingCouponRate.FIELD), 0.001);
        assertEquals(expected.getUnderlyingSecurityExchange(), actual.getString(quickfix.field.UnderlyingSecurityExchange.FIELD));
        assertEquals(expected.getUnderlyingIssuer(), actual.getString(quickfix.field.UnderlyingIssuer.FIELD));
        assertEquals(expected.getEncodedUnderlyingIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD));
//        assertArrayEquals(expected.getEncodedUnderlyingIssuer(), actual.getString(quickfix.field.EncodedUnderlyingIssuer.FIELD).getBytes());
        assertEquals(expected.getUnderlyingSecurityDesc(), actual.getString(quickfix.field.UnderlyingSecurityDesc.FIELD));
        assertEquals(expected.getEncodedUnderlyingSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD));
//        assertArrayEquals(expected.getEncodedUnderlyingSecurityDesc(), actual.getString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD).getBytes());
        assertEquals(expected.getUnderlyingCPProgram(), actual.getString(quickfix.field.UnderlyingCPProgram.FIELD));
        assertEquals(expected.getUnderlyingCPRegType(), actual.getString(quickfix.field.UnderlyingCPRegType.FIELD));
        assertEquals(expected.getUnderlyingAllocationPercent().doubleValue(), actual.getDouble(quickfix.field.UnderlyingAllocationPercent.FIELD), 0.001);
        assertEquals(expected.getUnderlyingCurrency().getValue(), actual.getString(quickfix.field.UnderlyingCurrency.FIELD));
        assertEquals(expected.getUnderlyingQty().doubleValue(), actual.getDouble(quickfix.field.UnderlyingQty.FIELD), 0.001);
        assertEquals(expected.getUnderlyingSettlementType().getValue(), actual.getInt(quickfix.field.UnderlyingSettlementType.FIELD));
        assertEquals(expected.getUnderlyingCashAmount().doubleValue(), actual.getDouble(quickfix.field.UnderlyingCashAmount.FIELD), 0.001);
        assertEquals(expected.getUnderlyingCashType(), actual.getString(quickfix.field.UnderlyingCashType.FIELD));
        assertEquals(expected.getUnderlyingPx().doubleValue(), actual.getDouble(quickfix.field.UnderlyingPx.FIELD), 0.001);
        assertEquals(expected.getUnderlyingDirtyPrice().doubleValue(), actual.getDouble(quickfix.field.UnderlyingDirtyPrice.FIELD), 0.001);
        assertEquals(expected.getUnderlyingEndPrice().doubleValue(), actual.getDouble(quickfix.field.UnderlyingEndPrice.FIELD), 0.001);
        assertEquals(expected.getUnderlyingStartValue().doubleValue(), actual.getDouble(quickfix.field.UnderlyingStartValue.FIELD), 0.001);
        assertEquals(expected.getUnderlyingCurrentValue().doubleValue(), actual.getDouble(quickfix.field.UnderlyingCurrentValue.FIELD), 0.001);
        assertEquals(expected.getUnderlyingEndValue().doubleValue(), actual.getDouble(quickfix.field.UnderlyingEndValue.FIELD), 0.001);

        assertEquals(expected.getUnderlyingAdjustedQuantity().doubleValue(), actual.getDouble(quickfix.field.UnderlyingAdjustedQuantity.FIELD), 0.001);
        assertEquals(expected.getUnderlyingFXRate().doubleValue(), actual.getDouble(quickfix.field.UnderlyingFXRate.FIELD), 0.001);
        assertEquals(expected.getUnderlyingFXRateCalc().getValue(), actual.getString(quickfix.field.UnderlyingFXRateCalc.FIELD));
        assertEquals(expected.getUnderlyingCapValue().doubleValue(), actual.getDouble(quickfix.field.UnderlyingCapValue.FIELD), 0.001);
        // UndlyInstrumentParties
        UndlyInstrumentParties50TestData.getInstance().check(expected.getUndlyInstrumentParties(), actual.getUndlyInstrumentParties());

        assertEquals(expected.getUnderlyingSettlMethod(), actual.getString(quickfix.field.UnderlyingSettlMethod.FIELD));
    }

    public void check(quickfix.fix50.component.UnderlyingInstrument expected, UnderlyingInstrument actual) throws Exception {
        assertEquals(actual.getUnderlyingSymbol(), expected.getString(quickfix.field.UnderlyingSymbol.FIELD));
        assertEquals(actual.getUnderlyingSymbolSfx(), expected.getString(quickfix.field.UnderlyingSymbolSfx.FIELD));
        assertEquals(actual.getUnderlyingSecurityID(), expected.getString(quickfix.field.UnderlyingSecurityID.FIELD));
        assertEquals(actual.getUnderlyingSecurityIDSource(), expected.getString(quickfix.field.UnderlyingSecurityIDSource.FIELD));
        assertTrue(expected.hasGroup(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        assertEquals(actual.getNoUnderlyingSecurityAltID().intValue(), expected.getInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grp1 =
            new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        expected.getGroup(1, grp1);
        quickfix.field.UnderlyingSecurityAltID f1 = new quickfix.field.UnderlyingSecurityAltID();
        grp1.get(f1);
        assertEquals(actual.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltID(), f1.getValue());
        quickfix.field.UnderlyingSecurityAltIDSource f2 = new quickfix.field.UnderlyingSecurityAltIDSource();
        grp1.get(f2);
        assertEquals(actual.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltIDSource(), f2.getValue());
        assertEquals(actual.getUnderlyingProduct().getValue(), expected.getInt(quickfix.field.UnderlyingProduct.FIELD));

        assertEquals(actual.getUnderlyingCFICode(), expected.getString(quickfix.field.UnderlyingCFICode.FIELD));
        assertEquals(actual.getUnderlyingSecurityType(), expected.getString(quickfix.field.UnderlyingSecurityType.FIELD));
        assertEquals(actual.getUnderlyingSecuritySubType(), expected.getString(quickfix.field.UnderlyingSecuritySubType.FIELD));
        assertEquals(actual.getUnderlyingMaturityMonthYear(), expected.getString(quickfix.field.UnderlyingMaturityMonthYear.FIELD));
        assertEquals(formatQFStringDate(actual.getUnderlyingMaturityDate()), expected.getString(quickfix.field.UnderlyingMaturityDate.FIELD));
        assertEquals(formatQFStringDate(actual.getUnderlyingCouponPaymentDate()), expected.getString(quickfix.field.UnderlyingCouponPaymentDate.FIELD));
        assertEquals(formatQFStringDate(actual.getUnderlyingIssueDate()), expected.getString(quickfix.field.UnderlyingIssueDate.FIELD));
        assertEquals(actual.getUnderlyingRepoCollateralSecurityType(), expected.getString(quickfix.field.UnderlyingRepoCollateralSecurityType.FIELD));
        assertEquals(actual.getUnderlyingRepurchaseTerm().intValue(), expected.getInt(quickfix.field.UnderlyingRepurchaseTerm.FIELD));
        assertEquals(actual.getUnderlyingRepurchaseRate().doubleValue(), expected.getDouble(quickfix.field.UnderlyingRepurchaseRate.FIELD), 0.001);
        assertEquals(actual.getUnderlyingFactor().doubleValue(), expected.getDouble(quickfix.field.UnderlyingFactor.FIELD), 0.001);
        assertEquals(actual.getUnderlyingCreditRating(), expected.getString(quickfix.field.UnderlyingCreditRating.FIELD));
        assertEquals(actual.getUnderlyingInstrRegistry(), expected.getString(quickfix.field.UnderlyingInstrRegistry.FIELD));
        assertEquals(actual.getUnderlyingCountryOfIssue(), expected.getString(quickfix.field.UnderlyingCountryOfIssue.FIELD));
        assertEquals(actual.getUnderlyingStateOrProvinceOfIssue(), expected.getString(quickfix.field.UnderlyingStateOrProvinceOfIssue.FIELD));
        assertEquals(actual.getUnderlyingLocaleOfIssue(), expected.getString(quickfix.field.UnderlyingLocaleOfIssue.FIELD));
        assertEquals(formatQFStringDate(actual.getUnderlyingRedemptionDate()), expected.getString(quickfix.field.UnderlyingRedemptionDate.FIELD));
        assertEquals(actual.getUnderlyingStrikePrice().doubleValue(), expected.getDouble(quickfix.field.UnderlyingStrikePrice.FIELD), 0.001);
        assertEquals(actual.getUnderlyingStrikeCurrency().getValue(), expected.getString(quickfix.field.UnderlyingStrikeCurrency.FIELD));
        assertEquals(actual.getUnderlyingOptAttribute().charValue(), expected.getChar(quickfix.field.UnderlyingOptAttribute.FIELD));
        assertEquals(actual.getUnderlyingContractMultiplier().doubleValue(), expected.getDouble(quickfix.field.UnderlyingContractMultiplier.FIELD), 0.001);
        assertEquals(actual.getUnderlyingUnitOfMeasure().getValue(), expected.getString(quickfix.field.UnderlyingUnitOfMeasure.FIELD));
        assertEquals(actual.getUnderlyingTimeUnit().getValue(), expected.getString(quickfix.field.UnderlyingTimeUnit.FIELD));
        assertEquals(actual.getUnderlyingCouponRate().doubleValue(), expected.getDouble(quickfix.field.UnderlyingCouponRate.FIELD), 0.001);
        assertEquals(actual.getUnderlyingSecurityExchange(), expected.getString(quickfix.field.UnderlyingSecurityExchange.FIELD));
        assertEquals(actual.getUnderlyingIssuer(), expected.getString(quickfix.field.UnderlyingIssuer.FIELD));
        assertEquals(actual.getEncodedUnderlyingIssuerLen().intValue(), expected.getInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD));
//        assertArrayEquals(actual.getEncodedUnderlyingIssuer(), expected.getString(quickfix.field.EncodedUnderlyingIssuer.FIELD).getBytes());
        assertEquals(actual.getUnderlyingSecurityDesc(), expected.getString(quickfix.field.UnderlyingSecurityDesc.FIELD));
        assertEquals(actual.getEncodedUnderlyingSecurityDescLen().intValue(), expected.getInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD));
//        assertArrayEquals(actual.getEncodedUnderlyingSecurityDesc(), expected.getString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD).getBytes());
        assertEquals(actual.getUnderlyingCPProgram(), expected.getString(quickfix.field.UnderlyingCPProgram.FIELD));
        assertEquals(actual.getUnderlyingCPRegType(), expected.getString(quickfix.field.UnderlyingCPRegType.FIELD));
        assertEquals(actual.getUnderlyingAllocationPercent().doubleValue(), expected.getDouble(quickfix.field.UnderlyingAllocationPercent.FIELD), 0.001);
        assertEquals(actual.getUnderlyingCurrency().getValue(), expected.getString(quickfix.field.UnderlyingCurrency.FIELD));
        assertEquals(actual.getUnderlyingQty().doubleValue(), expected.getDouble(quickfix.field.UnderlyingQty.FIELD), 0.001);
        assertEquals(actual.getUnderlyingSettlementType().getValue(), expected.getInt(quickfix.field.UnderlyingSettlementType.FIELD));
        assertEquals(actual.getUnderlyingCashAmount().doubleValue(), expected.getDouble(quickfix.field.UnderlyingCashAmount.FIELD), 0.001);
        assertEquals(actual.getUnderlyingCashType(), expected.getString(quickfix.field.UnderlyingCashType.FIELD));
        assertEquals(actual.getUnderlyingPx().doubleValue(), expected.getDouble(quickfix.field.UnderlyingPx.FIELD), 0.001);
        assertEquals(actual.getUnderlyingDirtyPrice().doubleValue(), expected.getDouble(quickfix.field.UnderlyingDirtyPrice.FIELD), 0.001);
        assertEquals(actual.getUnderlyingEndPrice().doubleValue(), expected.getDouble(quickfix.field.UnderlyingEndPrice.FIELD), 0.001);
        assertEquals(actual.getUnderlyingStartValue().doubleValue(), expected.getDouble(quickfix.field.UnderlyingStartValue.FIELD), 0.001);
        assertEquals(actual.getUnderlyingCurrentValue().doubleValue(), expected.getDouble(quickfix.field.UnderlyingCurrentValue.FIELD), 0.001);
        assertEquals(actual.getUnderlyingEndValue().doubleValue(), expected.getDouble(quickfix.field.UnderlyingEndValue.FIELD), 0.001);
        // UndlyInstrumentParties
        UndlyInstrumentParties50TestData.getInstance().check(actual.getUndlyInstrumentParties(), expected.getUndlyInstrumentParties());

        assertEquals(actual.getUnderlyingSettlMethod(), expected.getString(quickfix.field.UnderlyingSettlMethod.FIELD));
    }
}
