/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument43TestData.java
 *
 * $Id: UnderlyingInstrument43TestData.java,v 1.2 2009-07-21 08:58:46 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for UnderlyingInstrument component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class UnderlyingInstrument43TestData extends MsgTest {

    private static final UnderlyingInstrument43TestData INSTANCE;

    static {
        INSTANCE = new UnderlyingInstrument43TestData();
    }

    public static UnderlyingInstrument43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.component.UnderlyingInstrument grpl) throws Exception {
        grpl.setString(quickfix.field.UnderlyingSymbol.FIELD, "JAVA");
        grpl.setString(quickfix.field.UnderlyingSymbolSfx.FIELD, "WI");
        grpl.setString(quickfix.field.UnderlyingSecurityID.FIELD, "JAVA");
        grpl.setString(quickfix.field.UnderlyingSecurityIDSource.FIELD, "2");
        grpl.setInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD, 2);
        // UnderlyingSecurityAltID
        quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple1 = new quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple1.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID JAVA 1");
        grple1.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "2");
        grpl.addGroup(grple1);
        quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple2 = new quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple2.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID JAVA 2");
        grple2.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "3");
        grpl.addGroup(grple2);

        grpl.setInt(quickfix.field.UnderlyingProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.UnderlyingCFICode.FIELD, "CFI MOT");
        grpl.setString(quickfix.field.UnderlyingSecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        grpl.setString(quickfix.field.UnderlyingMaturityMonthYear.FIELD, "200806w2");
        grpl.setUtcDateOnly(quickfix.field.UnderlyingMaturityDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.UnderlyingCouponPaymentDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.UnderlyingIssueDate.FIELD, new Date());
        grpl.setInt(quickfix.field.UnderlyingRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.UnderlyingRepurchaseTerm.FIELD, 5);
        grpl.setDecimal(quickfix.field.UnderlyingRepurchaseRate.FIELD, new BigDecimal("15.5"));
        grpl.setDecimal(quickfix.field.UnderlyingFactor.FIELD, new BigDecimal("1.2367"));
        grpl.setString(quickfix.field.UnderlyingCreditRating.FIELD, "AAA");
        grpl.setString(quickfix.field.UnderlyingInstrRegistry.FIELD, "BIC");
        grpl.setString(quickfix.field.UnderlyingCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.UnderlyingStateOrProvinceOfIssue.FIELD, "New York");
        grpl.setString(quickfix.field.UnderlyingLocaleOfIssue.FIELD, "US");
        grpl.setUtcDateOnly(quickfix.field.UnderlyingRedemptionDate.FIELD, new Date());
        grpl.setDecimal(quickfix.field.UnderlyingStrikePrice.FIELD, new BigDecimal("25.48"));
        grpl.setChar(quickfix.field.UnderlyingOptAttribute.FIELD, 'C');
        grpl.setDecimal(quickfix.field.UnderlyingContractMultiplier.FIELD, new BigDecimal("1.023"));
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
    }

    public void populate1(UnderlyingInstrument underlyingInstrument) {
        underlyingInstrument.setUnderlyingSymbol("JAVA_1");
        underlyingInstrument.setUnderlyingSymbolSfx("CD");
        underlyingInstrument.setUnderlyingSecurityID("sec_id_1");
        underlyingInstrument.setUnderlyingSecurityIDSource("3");
        underlyingInstrument.setNoUnderlyingSecurityAltID(new Integer(1));
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_1");
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("4");
        underlyingInstrument.setUnderlyingProduct(Product.COMMODITY);
        underlyingInstrument.setUnderlyingCFICode("cfi_code_1");
        underlyingInstrument.setUnderlyingSecurityType(SecurityType.Option.getValue());
        underlyingInstrument.setUnderlyingMaturityMonthYear("072009");
        underlyingInstrument.setUnderlyingMaturityDate(new Date());
        underlyingInstrument.setUnderlyingCouponPaymentDate(new Date());
        underlyingInstrument.setUnderlyingIssueDate(new Date());
        underlyingInstrument.setUnderlyingRepoCollateralSecurityType("FAC");
        underlyingInstrument.setUnderlyingRepurchaseTerm(new Integer(31));
        underlyingInstrument.setUnderlyingRepurchaseRate(new Double(23.45));
        underlyingInstrument.setUnderlyingFactor(new Double(1.234));
        underlyingInstrument.setUnderlyingCreditRating("AAA");
        underlyingInstrument.setUnderlyingInstrRegistry("BIC");
        underlyingInstrument.setUnderlyingCountryOfIssue("US");
        underlyingInstrument.setUnderlyingStateOrProvinceOfIssue("NY");
        underlyingInstrument.setUnderlyingLocaleOfIssue("EN");
        underlyingInstrument.setUnderlyingRedemptionDate(new Date());
        underlyingInstrument.setUnderlyingStrikePrice(new Double(24.15));
        underlyingInstrument.setUnderlyingOptAttribute(new Character('B'));
        underlyingInstrument.setUnderlyingContractMultiplier(new Double(1.13));
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
    }

    public void populate2(quickfix.fix43.component.UnderlyingInstrument grpl) throws Exception {
        grpl.setString(quickfix.field.UnderlyingSymbol.FIELD, "ORACLE");
        grpl.setString(quickfix.field.UnderlyingSymbolSfx.FIELD, "WI");
        grpl.setString(quickfix.field.UnderlyingSecurityID.FIELD, "ORACLE");
        grpl.setString(quickfix.field.UnderlyingSecurityIDSource.FIELD, "4");
        grpl.setInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD, 2);
        quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple1 = new quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple1.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID ORACLE 1");
        grple1.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "1");
        grpl.addGroup(grple1);
        quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grple2 = new quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        grple2.setString(quickfix.field.UnderlyingSecurityAltID.FIELD, "Alt ID ORACLE 2");
        grple2.setString(quickfix.field.UnderlyingSecurityAltIDSource.FIELD, "4");
        grpl.addGroup(grple2);
        grpl.setInt(quickfix.field.UnderlyingProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.UnderlyingCFICode.FIELD, "CFI ORACLE");
        grpl.setString(quickfix.field.UnderlyingSecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        grpl.setString(quickfix.field.UnderlyingMaturityMonthYear.FIELD, "200906w2");
        Calendar calm = Calendar.getInstance();
        calm.set(2009, 9, 21, 23, 59, 59);
        calm.set(Calendar.MILLISECOND, 0);
        grpl.setUtcDateOnly(quickfix.field.UnderlyingMaturityDate.FIELD, calm.getTime());
        calm.add(Calendar.MONTH, 1);
        grpl.setUtcDateOnly(quickfix.field.UnderlyingCouponPaymentDate.FIELD, calm.getTime());
        calm.add(Calendar.YEAR, -1);
        grpl.setUtcDateOnly(quickfix.field.UnderlyingIssueDate.FIELD, calm.getTime());
        grpl.setInt(quickfix.field.UnderlyingRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.UnderlyingRepurchaseTerm.FIELD, 5);
        grpl.setDouble(quickfix.field.UnderlyingRepurchaseRate.FIELD, 15.51);
        grpl.setDouble(quickfix.field.UnderlyingFactor.FIELD, 1.2361);
        grpl.setString(quickfix.field.UnderlyingCreditRating.FIELD, "AA");
        grpl.setString(quickfix.field.UnderlyingInstrRegistry.FIELD, "ISO");
        grpl.setString(quickfix.field.UnderlyingCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.UnderlyingStateOrProvinceOfIssue.FIELD, "Los Angeles");
        grpl.setString(quickfix.field.UnderlyingLocaleOfIssue.FIELD, "US");
        Calendar calr = Calendar.getInstance();
        calr.set(2009, 1, 16, 23, 59, 59);
        calr.set(Calendar.MILLISECOND, 0);
        grpl.setUtcDateOnly(quickfix.field.UnderlyingRedemptionDate.FIELD, calr.getTime());
        grpl.setDouble(quickfix.field.UnderlyingStrikePrice.FIELD, 25.481);
        grpl.setChar(quickfix.field.UnderlyingOptAttribute.FIELD, 'C');
        grpl.setDouble(quickfix.field.UnderlyingContractMultiplier.FIELD, 1.0231);
        grpl.setDouble(quickfix.field.UnderlyingCouponRate.FIELD, 1.0771);
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
    }

    public void populate2(UnderlyingInstrument underlyingInstrument) {
        underlyingInstrument.setUnderlyingSymbol("JAVA_2");
        underlyingInstrument.setUnderlyingSymbolSfx("CD");
        underlyingInstrument.setUnderlyingSecurityID("sec_id_2");
        underlyingInstrument.setUnderlyingSecurityIDSource("5");
        underlyingInstrument.setNoUnderlyingSecurityAltID(new Integer(1));
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltID("sec_alt_id_2");
        underlyingInstrument.getUnderlyingSecurityAltIDGroups()[0].setUnderlyingSecurityAltIDSource("2");
        underlyingInstrument.setUnderlyingProduct(Product.COMMODITY);
        underlyingInstrument.setUnderlyingCFICode("cfi_code_2");
        underlyingInstrument.setUnderlyingSecurityType(SecurityType.OptionsOnComboEquity.getValue());
        underlyingInstrument.setUnderlyingMaturityMonthYear("082009");
        Calendar calmd = Calendar.getInstance();
        calmd.set(2009, 6, 23);
        underlyingInstrument.setUnderlyingMaturityDate(calmd.getTime());
        Calendar calcd = Calendar.getInstance();
        calcd.set(2010, 7, 13);
        underlyingInstrument.setUnderlyingCouponPaymentDate(calcd.getTime());
        Calendar calid = Calendar.getInstance();
        calid.set(2010, 1, 4);
        underlyingInstrument.setUnderlyingIssueDate(calid.getTime());
        underlyingInstrument.setUnderlyingRepoCollateralSecurityType("FUT");
        underlyingInstrument.setUnderlyingRepurchaseTerm(new Integer(31));
        underlyingInstrument.setUnderlyingRepurchaseRate(new Double(23.45));
        underlyingInstrument.setUnderlyingFactor(new Double(1.234));
        underlyingInstrument.setUnderlyingCreditRating("AAA");
        underlyingInstrument.setUnderlyingInstrRegistry("ISO");
        underlyingInstrument.setUnderlyingCountryOfIssue("US");
        underlyingInstrument.setUnderlyingStateOrProvinceOfIssue("NY");
        underlyingInstrument.setUnderlyingLocaleOfIssue("EN");
        Calendar calrd = Calendar.getInstance();
        calrd.set(2010, 0, 14);
        underlyingInstrument.setUnderlyingRedemptionDate(calrd.getTime());
        underlyingInstrument.setUnderlyingStrikePrice(new Double(24.15));
        underlyingInstrument.setUnderlyingOptAttribute(new Character('C'));
        underlyingInstrument.setUnderlyingContractMultiplier(new Double(1.13));
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
    }

    public void check(UnderlyingInstrument expected, quickfix.fix43.component.UnderlyingInstrument actual) throws Exception {
        assertEquals(expected.getUnderlyingSymbol(), actual.getString(quickfix.field.UnderlyingSymbol.FIELD));
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getString(quickfix.field.UnderlyingSymbolSfx.FIELD));
        assertEquals(expected.getUnderlyingSecurityID(), actual.getString(quickfix.field.UnderlyingSecurityID.FIELD));
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getString(quickfix.field.UnderlyingSecurityIDSource.FIELD));
        assertTrue(actual.hasGroup(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        assertEquals(expected.getNoUnderlyingSecurityAltID().intValue(), actual.getInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grp1 =
            new quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        actual.getGroup(1, grp1);
        quickfix.field.UnderlyingSecurityAltID f1 = new quickfix.field.UnderlyingSecurityAltID();
        grp1.get(f1);
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltID(), f1.getValue());
        quickfix.field.UnderlyingSecurityAltIDSource f2 = new quickfix.field.UnderlyingSecurityAltIDSource();
        grp1.get(f2);
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltIDSource(), f2.getValue());
        assertEquals(expected.getUnderlyingProduct().getValue(), actual.getInt(quickfix.field.UnderlyingProduct.FIELD));

        assertEquals(expected.getUnderlyingCFICode(), actual.getString(quickfix.field.UnderlyingCFICode.FIELD));
        assertEquals(expected.getUnderlyingSecurityType(), actual.getString(quickfix.field.UnderlyingSecurityType.FIELD));
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
        assertEquals(expected.getUnderlyingOptAttribute().charValue(), actual.getChar(quickfix.field.UnderlyingOptAttribute.FIELD));
        assertEquals(expected.getUnderlyingContractMultiplier().doubleValue(), actual.getDouble(quickfix.field.UnderlyingContractMultiplier.FIELD), 0.001);
        assertEquals(expected.getUnderlyingCouponRate().doubleValue(), actual.getDouble(quickfix.field.UnderlyingCouponRate.FIELD), 0.001);
        assertEquals(expected.getUnderlyingSecurityExchange(), actual.getString(quickfix.field.UnderlyingSecurityExchange.FIELD));
        assertEquals(expected.getUnderlyingIssuer(), actual.getString(quickfix.field.UnderlyingIssuer.FIELD));
        assertEquals(expected.getEncodedUnderlyingIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedUnderlyingIssuer(), actual.getString(quickfix.field.EncodedUnderlyingIssuer.FIELD).getBytes());
        assertEquals(expected.getUnderlyingSecurityDesc(), actual.getString(quickfix.field.UnderlyingSecurityDesc.FIELD));
        assertEquals(expected.getEncodedUnderlyingSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedUnderlyingSecurityDesc(), actual.getString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD).getBytes());
    }

    public void check(quickfix.fix43.component.UnderlyingInstrument expected, UnderlyingInstrument actual) throws Exception {
        assertEquals(actual.getUnderlyingSymbol(), expected.getString(quickfix.field.UnderlyingSymbol.FIELD));
        assertEquals(actual.getUnderlyingSymbolSfx(), expected.getString(quickfix.field.UnderlyingSymbolSfx.FIELD));
        assertEquals(actual.getUnderlyingSecurityID(), expected.getString(quickfix.field.UnderlyingSecurityID.FIELD));
        assertEquals(actual.getUnderlyingSecurityIDSource(), expected.getString(quickfix.field.UnderlyingSecurityIDSource.FIELD));
        assertTrue(expected.hasGroup(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        assertEquals(actual.getNoUnderlyingSecurityAltID().intValue(), expected.getInt(quickfix.field.NoUnderlyingSecurityAltID.FIELD));
        quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID grp1 =
            new quickfix.fix43.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
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
        assertEquals(actual.getUnderlyingOptAttribute().charValue(), expected.getChar(quickfix.field.UnderlyingOptAttribute.FIELD));
        assertEquals(actual.getUnderlyingContractMultiplier().doubleValue(), expected.getDouble(quickfix.field.UnderlyingContractMultiplier.FIELD), 0.001);
        assertEquals(actual.getUnderlyingCouponRate().doubleValue(), expected.getDouble(quickfix.field.UnderlyingCouponRate.FIELD), 0.001);
        assertEquals(actual.getUnderlyingSecurityExchange(), expected.getString(quickfix.field.UnderlyingSecurityExchange.FIELD));
        assertEquals(actual.getUnderlyingIssuer(), expected.getString(quickfix.field.UnderlyingIssuer.FIELD));
        assertEquals(actual.getEncodedUnderlyingIssuerLen().intValue(), expected.getInt(quickfix.field.EncodedUnderlyingIssuerLen.FIELD));
        assertArrayEquals(actual.getEncodedUnderlyingIssuer(), expected.getString(quickfix.field.EncodedUnderlyingIssuer.FIELD).getBytes());
        assertEquals(actual.getUnderlyingSecurityDesc(), expected.getString(quickfix.field.UnderlyingSecurityDesc.FIELD));
        assertEquals(actual.getEncodedUnderlyingSecurityDescLen().intValue(), expected.getInt(quickfix.field.EncodedUnderlyingSecurityDescLen.FIELD));
        assertArrayEquals(actual.getEncodedUnderlyingSecurityDesc(), expected.getString(quickfix.field.EncodedUnderlyingSecurityDesc.FIELD).getBytes());
    }

    public void check(UnderlyingInstrument expected, UnderlyingInstrument actual) throws Exception {
        assertEquals(expected.getUnderlyingSymbol(), actual.getUnderlyingSymbol());
        assertEquals(expected.getUnderlyingSymbolSfx(), actual.getUnderlyingSymbolSfx());
        assertEquals(expected.getUnderlyingSecurityID(), actual.getUnderlyingSecurityID());
        assertEquals(expected.getUnderlyingSecurityIDSource(), actual.getUnderlyingSecurityIDSource());
        assertEquals(expected.getNoUnderlyingSecurityAltID().intValue(), actual.getNoUnderlyingSecurityAltID().intValue());
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltID(), actual.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltID());
        assertEquals(expected.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltIDSource(), actual.getUnderlyingSecurityAltIDGroups()[0].getUnderlyingSecurityAltIDSource());

        assertEquals(expected.getUnderlyingCFICode(), actual.getUnderlyingCFICode());
        assertEquals(expected.getUnderlyingSecurityType(), actual.getUnderlyingSecurityType());
        assertEquals(expected.getUnderlyingMaturityMonthYear(), actual.getUnderlyingMaturityMonthYear());
        assertDateEquals(expected.getUnderlyingMaturityDate(), actual.getUnderlyingMaturityDate());
        assertDateEquals(expected.getUnderlyingCouponPaymentDate(), actual.getUnderlyingCouponPaymentDate());
        assertDateEquals(expected.getUnderlyingIssueDate(), actual.getUnderlyingIssueDate());
        assertEquals(expected.getUnderlyingRepoCollateralSecurityType(), actual.getUnderlyingRepoCollateralSecurityType());
        assertEquals(expected.getUnderlyingRepurchaseTerm().intValue(), actual.getUnderlyingRepurchaseTerm().intValue());
        assertEquals(expected.getUnderlyingRepurchaseRate().doubleValue(), actual.getUnderlyingRepurchaseRate().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingFactor().doubleValue(), actual.getUnderlyingFactor().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingCreditRating(), actual.getUnderlyingCreditRating());
        assertEquals(expected.getUnderlyingInstrRegistry(), actual.getUnderlyingInstrRegistry());
        assertEquals(expected.getUnderlyingCountryOfIssue(), actual.getUnderlyingCountryOfIssue());
        assertEquals(expected.getUnderlyingStateOrProvinceOfIssue(), actual.getUnderlyingStateOrProvinceOfIssue());
        assertEquals(expected.getUnderlyingLocaleOfIssue(), actual.getUnderlyingLocaleOfIssue());
        assertDateEquals(expected.getUnderlyingRedemptionDate(), actual.getUnderlyingRedemptionDate());
        assertEquals(expected.getUnderlyingStrikePrice().doubleValue(), actual.getUnderlyingStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingOptAttribute().charValue(), actual.getUnderlyingOptAttribute().charValue());
        assertEquals(expected.getUnderlyingContractMultiplier().doubleValue(), actual.getUnderlyingContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingCouponRate().doubleValue(), actual.getUnderlyingCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getUnderlyingSecurityExchange(), actual.getUnderlyingSecurityExchange());
        assertEquals(expected.getUnderlyingIssuer(), actual.getUnderlyingIssuer());
        assertEquals(expected.getEncodedUnderlyingIssuerLen().intValue(), actual.getEncodedUnderlyingIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedUnderlyingIssuer(), actual.getEncodedUnderlyingIssuer());
        assertEquals(expected.getUnderlyingSecurityDesc(), actual.getUnderlyingSecurityDesc());
        assertEquals(expected.getEncodedUnderlyingSecurityDescLen().intValue(), actual.getEncodedUnderlyingSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedUnderlyingSecurityDesc(), actual.getEncodedUnderlyingSecurityDesc());
    }
}
