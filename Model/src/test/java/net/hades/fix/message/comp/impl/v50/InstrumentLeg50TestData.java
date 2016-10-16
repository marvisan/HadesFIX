/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg50TestData.java
 *
 * $Id: InstrumentLeg50TestData.java,v 1.2 2011-10-29 09:42:26 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;

/**
 * Test utility for InstrumentLeg component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class InstrumentLeg50TestData extends MsgTest {

    private static final InstrumentLeg50TestData INSTANCE;

    static {
        INSTANCE = new InstrumentLeg50TestData();
    }

    public static InstrumentLeg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrumentLeg instrumentLeg) {
        instrumentLeg.setLegSymbol("MOT");
        instrumentLeg.setLegSymbolSfx("CD");
        instrumentLeg.setLegSecurityID("SEC_ID");
        instrumentLeg.setLegSecurityIDSource("2");
        instrumentLeg.setNoLegSecurityAltID(new Integer(1));
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_1");
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("2");
//        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_2");
//        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("3");
        instrumentLeg.setLegProduct(Product.EQUITY);
        instrumentLeg.setLegCFICode("cfi_code");
        instrumentLeg.setLegSecurityType("FAC");
        instrumentLeg.setLegSecuritySubType("sec_sub_234");
        instrumentLeg.setLegMaturityMonthYear("200806w2");
        instrumentLeg.setLegMaturityDate(new Date());
        instrumentLeg.setLegCouponPaymentDate(new Date());
        instrumentLeg.setLegIssueDate(new Date());
        instrumentLeg.setLegRepoCollateralSecurityType("1");
        instrumentLeg.setLegRepurchaseTerm(new Integer(12));
        instrumentLeg.setLegRepurchaseRate(new Double("12.25"));
        instrumentLeg.setLegFactor(new Double("2.34"));
        instrumentLeg.setLegCreditRating("AAA");
        instrumentLeg.setLegInstrRegistry("ISO");
        instrumentLeg.setLegCountryOfIssue("AU");
        instrumentLeg.setLegStateOrProvinceOfIssue("NSW");
        instrumentLeg.setLegLocaleOfIssue("AU");
        instrumentLeg.setLegRedemptionDate(new Date());
        instrumentLeg.setLegStrikePrice(new Double(2.55));
        instrumentLeg.setLegStrikeCurrency(Currency.UnitedStatesDollar);
        instrumentLeg.setLegOptAttribute(new Character('S'));
        instrumentLeg.setLegContractMultiplier(new Double("1.12"));
        instrumentLeg.setLegUnitOfMeasure(UnitOfMeasure.Gallons);
        instrumentLeg.setLegTimeUnit(TimeUnit.Hour);
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
        instrumentLeg.setLegDatedDate(new Date());
        instrumentLeg.setLegContractSettlMonth("200903w2");
        instrumentLeg.setLegInterestAccrualDate(new Date());
    }

    public void populate2(InstrumentLeg instrumentLeg) {
        instrumentLeg.setLegSymbol("MOT");
        instrumentLeg.setLegSymbolSfx("WI");
        instrumentLeg.setLegSecurityID("SEC_ID_1");
        instrumentLeg.setLegSecurityIDSource("3");
        instrumentLeg.setNoLegSecurityAltID(new Integer(1));
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_3");
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("1");
//        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_4");
//        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("4");
        instrumentLeg.setLegProduct(Product.COMMODITY);
        instrumentLeg.setLegCFICode("cfi_code_1");
        instrumentLeg.setLegSecurityType("SUPRA");
        instrumentLeg.setLegSecuritySubType("sec_sub_234_1");
        instrumentLeg.setLegMaturityMonthYear("200806w1");
        instrumentLeg.setLegMaturityDate(new Date());
        instrumentLeg.setLegCouponPaymentDate(new Date());
        instrumentLeg.setLegIssueDate(new Date());
        instrumentLeg.setLegRepoCollateralSecurityType("2");
        instrumentLeg.setLegRepurchaseTerm(new Integer(12));
        instrumentLeg.setLegRepurchaseRate(new Double("12.251"));
        instrumentLeg.setLegFactor(new Double("2.341"));
        instrumentLeg.setLegCreditRating("AA");
        instrumentLeg.setLegInstrRegistry("BIC");
        instrumentLeg.setLegCountryOfIssue("AU");
        instrumentLeg.setLegStateOrProvinceOfIssue("NSW");
        instrumentLeg.setLegLocaleOfIssue("AU");
        instrumentLeg.setLegRedemptionDate(new Date());
        instrumentLeg.setLegStrikePrice(new Double(2.44));
        instrumentLeg.setLegStrikeCurrency(Currency.AustralianDollar);
        instrumentLeg.setLegOptAttribute(new Character('S'));
        instrumentLeg.setLegContractMultiplier(new Double("1.121"));
        instrumentLeg.setLegUnitOfMeasure(UnitOfMeasure.Bushels);
        instrumentLeg.setLegTimeUnit(TimeUnit.Week);
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
        instrumentLeg.setLegDatedDate(new Date());
        instrumentLeg.setLegContractSettlMonth("200903w1");
        instrumentLeg.setLegInterestAccrualDate(new Date());
    }

    public void populate1(quickfix.fix50.component.InstrumentLeg grpl) throws Exception {
        grpl.setString(quickfix.field.LegSymbol.FIELD, "JAVA");
        grpl.setString(quickfix.field.LegSymbolSfx.FIELD, "WI");
        grpl.setString(quickfix.field.LegSecurityID.FIELD, "JAVA");
        grpl.setString(quickfix.field.LegSecurityIDSource.FIELD, "2");
        grpl.setInt(quickfix.field.NoLegSecurityAltID.FIELD, 2);
        quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID grple1 = new quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID();
        grple1.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID JAVA 1");
        grple1.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID JAVA NASDAQ 1");
        grpl.addGroup(grple1);
        quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID grple2 = new quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID();
        grple2.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID JAVA 2");
        grple2.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID JAVA NASDAQ 2");
        grpl.addGroup(grple2);
        grpl.setInt(quickfix.field.LegProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.LegCFICode.FIELD, "CFI MOT");
        grpl.setString(quickfix.field.LegSecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        grpl.setString(quickfix.field.LegSecuritySubType.FIELD, "OOO");
        grpl.setString(quickfix.field.LegMaturityMonthYear.FIELD, "200806w2");
        grpl.setUtcDateOnly(quickfix.field.LegMaturityDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.LegCouponPaymentDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.LegIssueDate.FIELD, new Date());
        grpl.setInt(quickfix.field.LegRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.LegRepurchaseTerm.FIELD, 5);
        grpl.setDecimal(quickfix.field.LegRepurchaseRate.FIELD, new BigDecimal("15.5"));
        grpl.setDecimal(quickfix.field.LegFactor.FIELD, new BigDecimal("1.2367"));
        grpl.setString(quickfix.field.LegCreditRating.FIELD, "AAA");
        grpl.setString(quickfix.field.LegInstrRegistry.FIELD, "Rego 1");
        grpl.setString(quickfix.field.LegCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.LegStateOrProvinceOfIssue.FIELD, "New York");
        grpl.setString(quickfix.field.LegLocaleOfIssue.FIELD, "US");
        grpl.setUtcDateOnly(quickfix.field.LegRedemptionDate.FIELD, new Date());
        grpl.setDecimal(quickfix.field.LegStrikePrice.FIELD, new BigDecimal("25.48"));
        grpl.setString(quickfix.field.LegStrikeCurrency.FIELD, "USD");
        grpl.setChar(quickfix.field.LegOptAttribute.FIELD, 'C');
        grpl.setDecimal(quickfix.field.LegContractMultiplier.FIELD, new BigDecimal("1.023"));
        grpl.setString(quickfix.field.LegUnitOfMeasure.FIELD, UnitOfMeasure.Bushels.getValue());
        grpl.setString(quickfix.field.LegTimeUnit.FIELD, TimeUnit.Hour.getValue());
        grpl.setDecimal(quickfix.field.LegCouponRate.FIELD, new BigDecimal("1.077"));
        grpl.setString(quickfix.field.LegSecurityExchange.FIELD, "NYSE");
        grpl.setString(quickfix.field.LegIssuer.FIELD, "NYSE PL");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        grpl.setInt(quickfix.field.EncodedLegIssuerLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedLegIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        grpl.setString(quickfix.field.LegSecurityDesc.FIELD, "Motorola shares");
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        grpl.setInt(quickfix.field.EncodedLegSecurityDescLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedLegSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        grpl.setDouble(quickfix.field.LegRatioQty.FIELD, 2.35);
        grpl.setChar(quickfix.field.LegSide.FIELD, 'A');
        grpl.setString(quickfix.field.LegCurrency.FIELD, "USD");
        grpl.setString(quickfix.field.LegPool.FIELD, "pool_1");
        grpl.setString(quickfix.field.LegDatedDate.FIELD, "20100908");
        grpl.setString(quickfix.field.LegContractSettlMonth.FIELD, "SEP");
        grpl.setString(quickfix.field.LegInterestAccrualDate.FIELD, "20100319");
    }

    public void populate2(quickfix.fix50.component.InstrumentLeg grpl) throws Exception {
        grpl.setString(quickfix.field.LegSymbol.FIELD, "IBM");
        grpl.setString(quickfix.field.LegSymbolSfx.FIELD, "CD");
        grpl.setString(quickfix.field.LegSecurityID.FIELD, "IBM");
        grpl.setString(quickfix.field.LegSecurityIDSource.FIELD, "LSE");
        grpl.setInt(quickfix.field.NoLegSecurityAltID.FIELD, 2);
        quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID grple1 = new quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID();
        grple1.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID IBM 1");
        grple1.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID IBM NASDAQ 1");
        grpl.addGroup(grple1);
        quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID grple2 = new quickfix.fix44.component.InstrumentLeg.NoLegSecurityAltID();
        grple2.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID IBM 2");
        grple2.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID IBM NASDAQ 2");
        grpl.addGroup(grple2);
        grpl.setInt(quickfix.field.LegProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.LegCFICode.FIELD, "CFI IBM");
        grpl.setString(quickfix.field.LegSecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        grpl.setString(quickfix.field.LegSecuritySubType.FIELD, "ZZZ");
        grpl.setString(quickfix.field.LegMaturityMonthYear.FIELD, "200906w2");
        grpl.setUtcDateOnly(quickfix.field.LegMaturityDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.LegCouponPaymentDate.FIELD, new Date());
        grpl.setUtcDateOnly(quickfix.field.LegIssueDate.FIELD, new Date());
        grpl.setInt(quickfix.field.LegRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.LegRepurchaseTerm.FIELD, 5);
        grpl.setDecimal(quickfix.field.LegRepurchaseRate.FIELD, new BigDecimal("15.51"));
        grpl.setDecimal(quickfix.field.LegFactor.FIELD, new BigDecimal("1.2361"));
        grpl.setString(quickfix.field.LegCreditRating.FIELD, "R");
        grpl.setString(quickfix.field.LegInstrRegistry.FIELD, "Rego 2");
        grpl.setString(quickfix.field.LegCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.LegStateOrProvinceOfIssue.FIELD, "New York");
        grpl.setString(quickfix.field.LegLocaleOfIssue.FIELD, "US");
        grpl.setUtcDateOnly(quickfix.field.LegRedemptionDate.FIELD, new Date());
        grpl.setDecimal(quickfix.field.LegStrikePrice.FIELD, new BigDecimal("25.481"));
        grpl.setString(quickfix.field.LegStrikeCurrency.FIELD, "USD");
        grpl.setChar(quickfix.field.LegOptAttribute.FIELD, 'C');
        grpl.setDecimal(quickfix.field.LegContractMultiplier.FIELD, new BigDecimal("1.0231"));
        grpl.setString(quickfix.field.LegUnitOfMeasure.FIELD, UnitOfMeasure.Barrels.getValue());
        grpl.setString(quickfix.field.LegTimeUnit.FIELD, TimeUnit.Day.getValue());
        grpl.setDecimal(quickfix.field.LegCouponRate.FIELD, new BigDecimal("1.0771"));
        grpl.setString(quickfix.field.LegSecurityExchange.FIELD, "LSE");
        grpl.setString(quickfix.field.LegIssuer.FIELD, "LSE PL");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        grpl.setInt(quickfix.field.EncodedLegIssuerLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedLegIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        grpl.setString(quickfix.field.LegSecurityDesc.FIELD, "IBM shares");
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        grpl.setInt(quickfix.field.EncodedLegSecurityDescLen.FIELD, 8);
        grpl.setString(quickfix.field.EncodedLegSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
        grpl.setDouble(quickfix.field.LegRatioQty.FIELD, 2.351);
        grpl.setChar(quickfix.field.LegSide.FIELD, 'A');
        grpl.setString(quickfix.field.LegCurrency.FIELD, "USD");
        grpl.setString(quickfix.field.LegPool.FIELD, "pool_2");
        grpl.setString(quickfix.field.LegDatedDate.FIELD, "20101008");
        grpl.setString(quickfix.field.LegContractSettlMonth.FIELD, "DEC");
        grpl.setString(quickfix.field.LegInterestAccrualDate.FIELD, "20101019");
    }

    public void check(InstrumentLeg expected, InstrumentLeg actual) throws Exception {
        assertEquals(expected.getLegSymbol(), actual.getLegSymbol());
        assertEquals(expected.getLegSymbolSfx(), actual.getLegSymbolSfx());
        assertEquals(expected.getLegSecurityID(), actual.getLegSecurityID());
        assertEquals(expected.getLegSecurityIDSource(), actual.getLegSecurityIDSource());
        assertEquals(expected.getNoLegSecurityAltID(), actual.getNoLegSecurityAltID());
        for (int i = 0; i < actual.getNoLegSecurityAltID().intValue(); i++) {
            assertEquals(expected.getLegSecurityAltIDGroups()[i].getLegSecurityAltID(), actual.getLegSecurityAltIDGroups()[i].getLegSecurityAltID());
            assertEquals(expected.getLegSecurityAltIDGroups()[i].getLegSecurityAltIDSource(), actual.getLegSecurityAltIDGroups()[i].getLegSecurityAltIDSource());
        }

        assertEquals(expected.getLegProduct(), actual.getLegProduct());
        assertEquals(expected.getLegCFICode(), actual.getLegCFICode());
        assertEquals(expected.getLegSecurityType(), actual.getLegSecurityType());
        assertEquals(expected.getLegSecuritySubType(), actual.getLegSecuritySubType());
        assertEquals(expected.getLegMaturityMonthYear(), actual.getLegMaturityMonthYear());
        assertDateEquals(expected.getLegMaturityDate(), actual.getLegMaturityDate());
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
        assertEquals(expected.getLegTimeUnit(), actual.getLegTimeUnit());
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
    }

    public void check(InstrumentLeg expected, quickfix.fix50.component.InstrumentLeg actual) throws Exception {
        assertEquals(expected.getLegSymbol(), actual.getString(quickfix.field.LegSymbol.FIELD));
        assertEquals(expected.getLegSymbolSfx(), actual.getString(quickfix.field.LegSymbolSfx.FIELD));
        assertEquals(expected.getLegSecurityID(), actual.getString(quickfix.field.LegSecurityID.FIELD));
        assertEquals(expected.getLegSecurityIDSource(), actual.getString(quickfix.field.LegSecurityIDSource.FIELD));
//        assertTrue(actual.hasGroup(quickfix.field.NoLegSecurityAltID.FIELD));
//        assertEquals(2, actual.getInt(quickfix.field.NoLegSecurityAltID.FIELD));
        assertEquals(expected.getLegProduct().getValue(), actual.getInt(quickfix.field.LegProduct.FIELD));
        assertEquals(expected.getLegCFICode(), actual.getString(quickfix.field.LegCFICode.FIELD));
        assertEquals(expected.getLegSecurityType(), actual.getString(quickfix.field.LegSecurityType.FIELD));
        assertEquals(expected.getLegSecuritySubType(), actual.getString(quickfix.field.LegSecuritySubType.FIELD));
        assertEquals(expected.getLegMaturityMonthYear(), actual.getString(quickfix.field.LegMaturityMonthYear.FIELD));
        assertEquals(formatQFStringDate(expected.getLegMaturityDate()), actual.getString(quickfix.field.LegMaturityDate.FIELD));
        assertEquals(formatQFStringDate(expected.getLegCouponPaymentDate()), actual.getString(quickfix.field.LegCouponPaymentDate.FIELD));
        assertEquals(formatQFStringDate(expected.getLegIssueDate()), actual.getString(quickfix.field.LegIssueDate.FIELD));
        assertEquals(expected.getLegRepoCollateralSecurityType(), actual.getString(quickfix.field.LegRepoCollateralSecurityType.FIELD));
        assertEquals(expected.getLegRepurchaseTerm().intValue(), actual.getInt(quickfix.field.LegRepurchaseTerm.FIELD));
        assertEquals(expected.getLegRepurchaseRate().doubleValue(), actual.getDouble(quickfix.field.LegRepurchaseRate.FIELD), 0.001);
        assertEquals(expected.getLegFactor().doubleValue(), actual.getDouble(quickfix.field.LegFactor.FIELD), 0.001);
        assertEquals(expected.getLegCreditRating(), actual.getString(quickfix.field.LegCreditRating.FIELD));
        assertEquals(expected.getLegInstrRegistry(), actual.getString(quickfix.field.LegInstrRegistry.FIELD));
        assertEquals(expected.getLegCountryOfIssue(), actual.getString(quickfix.field.LegCountryOfIssue.FIELD));
        assertEquals(expected.getLegStateOrProvinceOfIssue(), actual.getString(quickfix.field.LegStateOrProvinceOfIssue.FIELD));
        assertEquals(expected.getLegLocaleOfIssue(), actual.getString(quickfix.field.LegLocaleOfIssue.FIELD));
        assertEquals(formatQFStringDate(expected.getLegRedemptionDate()), actual.getString(quickfix.field.LegRedemptionDate.FIELD));
        assertEquals(expected.getLegStrikePrice().doubleValue(), actual.getDouble(quickfix.field.LegStrikePrice.FIELD), 0.001);
        assertEquals(expected.getLegStrikeCurrency().getValue(), actual.getString(quickfix.field.LegStrikeCurrency.FIELD));
        assertEquals(expected.getLegOptAttribute().charValue(), actual.getChar(quickfix.field.LegOptAttribute.FIELD));
        assertEquals(expected.getLegContractMultiplier().doubleValue(), actual.getDouble(quickfix.field.LegContractMultiplier.FIELD), 0.001);
        assertEquals(expected.getLegUnitOfMeasure().getValue(), actual.getString(quickfix.field.LegUnitOfMeasure.FIELD));
        assertEquals(expected.getLegTimeUnit().getValue(), actual.getString(quickfix.field.LegTimeUnit.FIELD));
        assertEquals(expected.getLegCouponRate().doubleValue(), actual.getDouble(quickfix.field.LegCouponRate.FIELD), 0.001);
        assertEquals(expected.getLegSecurityExchange(), actual.getString(quickfix.field.LegSecurityExchange.FIELD));
        assertEquals(expected.getLegIssuer(), actual.getString(quickfix.field.LegIssuer.FIELD));
        assertEquals(expected.getEncodedLegIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedLegIssuerLen.FIELD));
//        assertArrayEquals(expected.getEncodedLegIssuer(), actual.getString(quickfix.field.EncodedLegIssuer.FIELD).getBytes());
        assertEquals(expected.getLegSecurityDesc(), actual.getString(quickfix.field.LegSecurityDesc.FIELD));
        assertEquals(expected.getEncodedLegSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedLegSecurityDescLen.FIELD));
//        assertArrayEquals(expected.getEncodedLegSecurityDesc(), actual.getString(quickfix.field.EncodedLegSecurityDesc.FIELD).getBytes());
        assertEquals(expected.getLegRatioQty().doubleValue(), actual.getDouble(quickfix.field.LegRatioQty.FIELD), 0.001);
        assertEquals(expected.getLegSide().getValue(), actual.getChar(quickfix.field.LegSide.FIELD));
        assertEquals(expected.getLegCurrency().getValue(), actual.getString(quickfix.field.LegCurrency.FIELD));
        assertEquals(expected.getLegPool(), actual.getString(quickfix.field.LegPool.FIELD));
        assertEquals(formatQFStringDate(expected.getLegDatedDate()), actual.getString(quickfix.field.LegDatedDate.FIELD));
        assertEquals(expected.getLegContractSettlMonth(), actual.getString(quickfix.field.LegContractSettlMonth.FIELD));
        assertEquals(formatQFStringDate(expected.getLegInterestAccrualDate()), actual.getString(quickfix.field.LegInterestAccrualDate.FIELD));
    }

    public void check(quickfix.fix50.component.InstrumentLeg expected, InstrumentLeg actual) throws Exception {
        assertEquals(actual.getLegSymbol(), expected.getString(quickfix.field.LegSymbol.FIELD));
        assertEquals(actual.getLegSymbolSfx(), expected.getString(quickfix.field.LegSymbolSfx.FIELD));
        assertEquals(actual.getLegSecurityID(), expected.getString(quickfix.field.LegSecurityID.FIELD));
        assertEquals(actual.getLegSecurityIDSource(), expected.getString(quickfix.field.LegSecurityIDSource.FIELD));
//        assertTrue(expected.hasGroup(quickfix.field.NoLegSecurityAltID.FIELD));
        assertEquals(2, expected.getInt(quickfix.field.NoLegSecurityAltID.FIELD));
        assertEquals(actual.getLegProduct().getValue(), expected.getInt(quickfix.field.LegProduct.FIELD));
        assertEquals(actual.getLegCFICode(), expected.getString(quickfix.field.LegCFICode.FIELD));
        assertEquals(actual.getLegSecurityType(), expected.getString(quickfix.field.LegSecurityType.FIELD));
        assertEquals(actual.getLegSecuritySubType(), expected.getString(quickfix.field.LegSecuritySubType.FIELD));
        assertEquals(actual.getLegMaturityMonthYear(), expected.getString(quickfix.field.LegMaturityMonthYear.FIELD));
        assertEquals(formatQFStringDate(actual.getLegMaturityDate()), expected.getString(quickfix.field.LegMaturityDate.FIELD));
        assertEquals(formatQFStringDate(actual.getLegCouponPaymentDate()), expected.getString(quickfix.field.LegCouponPaymentDate.FIELD));
        assertEquals(formatQFStringDate(actual.getLegIssueDate()), expected.getString(quickfix.field.LegIssueDate.FIELD));
        assertEquals(actual.getLegRepoCollateralSecurityType(), expected.getString(quickfix.field.LegRepoCollateralSecurityType.FIELD));
        assertEquals(actual.getLegRepurchaseTerm().intValue(), expected.getInt(quickfix.field.LegRepurchaseTerm.FIELD));
        assertEquals(actual.getLegRepurchaseRate().doubleValue(), expected.getDouble(quickfix.field.LegRepurchaseRate.FIELD), 0.001);
        assertEquals(actual.getLegFactor().doubleValue(), expected.getDouble(quickfix.field.LegFactor.FIELD), 0.001);
        assertEquals(actual.getLegCreditRating(), expected.getString(quickfix.field.LegCreditRating.FIELD));
        assertEquals(actual.getLegInstrRegistry(), expected.getString(quickfix.field.LegInstrRegistry.FIELD));
        assertEquals(actual.getLegCountryOfIssue(), expected.getString(quickfix.field.LegCountryOfIssue.FIELD));
        assertEquals(actual.getLegStateOrProvinceOfIssue(), expected.getString(quickfix.field.LegStateOrProvinceOfIssue.FIELD));
        assertEquals(actual.getLegLocaleOfIssue(), expected.getString(quickfix.field.LegLocaleOfIssue.FIELD));
        assertEquals(formatQFStringDate(actual.getLegRedemptionDate()), expected.getString(quickfix.field.LegRedemptionDate.FIELD));
        assertEquals(actual.getLegStrikePrice().doubleValue(), expected.getDouble(quickfix.field.LegStrikePrice.FIELD), 0.001);
        assertEquals(actual.getLegStrikeCurrency().getValue(), expected.getString(quickfix.field.LegStrikeCurrency.FIELD));
        assertEquals(actual.getLegOptAttribute().charValue(), expected.getChar(quickfix.field.LegOptAttribute.FIELD));
        assertEquals(actual.getLegContractMultiplier().doubleValue(), expected.getDouble(quickfix.field.LegContractMultiplier.FIELD), 0.001);
        assertEquals(actual.getLegUnitOfMeasure().getValue(), expected.getString(quickfix.field.LegUnitOfMeasure.FIELD));
        assertEquals(actual.getLegTimeUnit().getValue(), expected.getString(quickfix.field.LegTimeUnit.FIELD));
        assertEquals(actual.getLegCouponRate().doubleValue(), expected.getDouble(quickfix.field.LegCouponRate.FIELD), 0.001);
        assertEquals(actual.getLegSecurityExchange(), expected.getString(quickfix.field.LegSecurityExchange.FIELD));
        assertEquals(actual.getLegIssuer(), expected.getString(quickfix.field.LegIssuer.FIELD));
        assertEquals(actual.getEncodedLegIssuerLen().intValue(), expected.getInt(quickfix.field.EncodedLegIssuerLen.FIELD));
//        assertArrayEquals(actual.getEncodedLegIssuer(), expected.getString(quickfix.field.EncodedLegIssuer.FIELD).getBytes());
        assertEquals(actual.getLegSecurityDesc(), expected.getString(quickfix.field.LegSecurityDesc.FIELD));
        assertEquals(actual.getEncodedLegSecurityDescLen().intValue(), expected.getInt(quickfix.field.EncodedLegSecurityDescLen.FIELD));
//        assertArrayEquals(actual.getEncodedLegSecurityDesc(), expected.getString(quickfix.field.EncodedLegSecurityDesc.FIELD).getBytes());
        assertEquals(actual.getLegRatioQty().doubleValue(), expected.getDouble(quickfix.field.LegRatioQty.FIELD), 0.001);
        assertEquals(actual.getLegSide().getValue(), expected.getChar(quickfix.field.LegSide.FIELD));
        assertEquals(actual.getLegCurrency().getValue(), expected.getString(quickfix.field.LegCurrency.FIELD));
        assertEquals(actual.getLegPool(), expected.getString(quickfix.field.LegPool.FIELD));
        assertEquals(formatQFStringDate(actual.getLegDatedDate()), expected.getString(quickfix.field.LegDatedDate.FIELD));
        assertEquals(actual.getLegContractSettlMonth(), expected.getString(quickfix.field.LegContractSettlMonth.FIELD));
        assertEquals(formatQFStringDate(actual.getLegInterestAccrualDate()), expected.getString(quickfix.field.LegInterestAccrualDate.FIELD));
    }
}
