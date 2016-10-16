/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument43TestData.java
 *
 * $Id: Instrument43TestData.java,v 1.7 2011-04-29 03:11:04 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for Instrument component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class Instrument43TestData extends MsgTest {

    private static final Instrument43TestData INSTANCE;

    static {
        INSTANCE = new Instrument43TestData();
    }

    public static Instrument43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.component.Instrument msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOTOROLA Shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "MOTO");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "NYSE");
        msg.setInt(quickfix.field.NoSecurityAltID.FIELD, 2);
        // SecurityAltID
        quickfix.fix43.component.Instrument.NoSecurityAltID grp1 = new quickfix.fix43.component.Instrument.NoSecurityAltID();
        grp1.setString(quickfix.field.SecurityAltID.FIELD, "Alt ID MOT NASDAQ 1");
        grp1.setString(quickfix.field.SecurityAltIDSource.FIELD, "2");
        msg.addGroup(grp1);
        quickfix.fix43.component.Instrument.NoSecurityAltID grp2 = new quickfix.fix43.component.Instrument.NoSecurityAltID();
        grp2.setString(quickfix.field.SecurityAltID.FIELD, "Alt ID MOT NASDAQ 2");
        grp2.setString(quickfix.field.SecurityAltIDSource.FIELD, "3");
        msg.addGroup(grp2);
        
        msg.setInt(quickfix.field.Product.FIELD, Product.EQUITY.getValue());
        msg.setString(quickfix.field.CFICode.FIELD, "CFI MOT");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "200806w2");
        Calendar calm = Calendar.getInstance();
        calm.set(2009, 9, 21, 23, 59, 59);
        calm.set(Calendar.MILLISECOND, 0);
        msg.setUtcDateOnly(quickfix.field.MaturityDate.FIELD, calm.getTime());
        calm.add(Calendar.MONTH, 1);
        msg.setUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD, calm.getTime());
        calm.add(Calendar.YEAR, -1);
        msg.setUtcDateOnly(quickfix.field.IssueDate.FIELD, calm.getTime());
        msg.setString(quickfix.field.RepoCollateralSecurityType.FIELD, "RVRP");
        msg.setInt(quickfix.field.RepurchaseTerm.FIELD, 5);
        msg.setDouble(quickfix.field.RepurchaseRate.FIELD, 15.5);
        msg.setDouble(quickfix.field.Factor.FIELD, 1.2367);
        msg.setString(quickfix.field.CreditRating.FIELD, "AAA");
        msg.setString(quickfix.field.InstrRegistry.FIELD, "Rego 1");
        msg.setString(quickfix.field.CountryOfIssue.FIELD, "US");
        msg.setString(quickfix.field.StateOrProvinceOfIssue.FIELD, "New York");
        msg.setString(quickfix.field.LocaleOfIssue.FIELD, "US");
        Calendar calr = Calendar.getInstance();
        calr.set(2009, 1, 16, 23, 59, 59);
        calr.set(Calendar.MILLISECOND, 0);
        msg.setUtcDateOnly(quickfix.field.RedemptionDate.FIELD, calr.getTime());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 25.48);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'C');
        msg.setDouble(quickfix.field.ContractMultiplier.FIELD, 1.023);
        msg.setDouble(quickfix.field.CouponRate.FIELD, 1.077);
        msg.setString(quickfix.field.SecurityExchange.FIELD, "NYSE");
        msg.setString(quickfix.field.Issuer.FIELD, "NYSE PL");
        byte[] issuerDataExp = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setInt(quickfix.field.EncodedIssuerLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedIssuer.FIELD, new String(issuerDataExp, DEFAULT_CHARACTER_SET));
        msg.setString(quickfix.field.SecurityDesc.FIELD, "Motorola shares");
        byte[] encSecExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setInt(quickfix.field.EncodedSecurityDescLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedSecurityDesc.FIELD, new String(encSecExp, DEFAULT_CHARACTER_SET));
    }

    public void populate(Instrument instrument) {
        instrument.setSymbol("MOT");
        instrument.setSymbolSfx("SFX code");
        instrument.setSecurityID("MOTX");
        instrument.setSecurityIDSource("A");
        instrument.setNoSecurityAltID(new Integer(2));
        instrument.getSecurityAltIDGroups()[0].setSecurityAltID("Alt ID MOT NASDAQ 1");
        instrument.getSecurityAltIDGroups()[0].setSecurityAltIDSource("2");
        instrument.getSecurityAltIDGroups()[1].setSecurityAltID("Alt ID MOT NASDAQ 2");
        instrument.getSecurityAltIDGroups()[1].setSecurityAltIDSource("3");
        instrument.setProduct(Product.CURRENCY);
        instrument.setCfiCode("CFI MOT");
        instrument.setSecurityType(SecurityType.MutualFund.getValue());
        instrument.setMaturityMonthYear("200806w2");
        Calendar calm = Calendar.getInstance();
        calm.set(Calendar.DAY_OF_MONTH, 21);
        calm.set(Calendar.MONTH, 9);
        calm.set(Calendar.YEAR, 2009);
        instrument.setMaturityDate(calm.getTime());
        calm.add(Calendar.MONTH, 1);
        instrument.setCouponPaymentDate(calm.getTime());
        calm.add(Calendar.YEAR, -1);
        instrument.setIssueDate(calm.getTime());
        instrument.setRepoCollateralSecurityType("RVRP");
        instrument.setRepurchaseTerm(new Integer(5));
        instrument.setRepurchaseRate(new Double("15.5"));
        instrument.setFactor(new Double("1.2367"));
        instrument.setCreditRating("AAA");
        instrument.setInstrRegistry("Rego 1");
        instrument.setCountryOfIssue("US");
        instrument.setStateOrProvinceOfIssue("New York");
        instrument.setLocaleOfIssue("US");
        Calendar calr = Calendar.getInstance();
        calr.set(Calendar.DAY_OF_MONTH, 16);
        calr.set(Calendar.MONTH, 1);
        calr.set(Calendar.YEAR, 2009);
        instrument.setRedemptionDate(calr.getTime());
        instrument.setStrikePrice(new Double("22.22"));
        instrument.setOptAttribute(new Character('C'));
        instrument.setContractMultiplier(new Double("1.023"));
        instrument.setCouponRate(new Double("1.077"));
        instrument.setSecurityExchange("CBT");
        instrument.setIssuer("NYSE PL");
        instrument.setEncodedIssuerLen(new Integer(8));
        byte[] issuerData = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        instrument.setEncodedIssuer(issuerData);
        instrument.setSecurityDesc("Motorola shares");
        instrument.setEncodedSecurityDescLen(new Integer(8));
        byte[] securityData = new byte[] {(byte) 44, (byte) 22, (byte) 55, (byte) 36,
            (byte) 166, (byte) 233, (byte) 19, (byte) 95};
        instrument.setEncodedSecurityDesc(securityData);
    }

    public void populate(Instrument instrument, SecurityType secType) {
        instrument.setSymbol("MOT");
        instrument.setSymbolSfx("SFX code");
        instrument.setSecurityID("MOTX");
        instrument.setSecurityIDSource("A");
        instrument.setNoSecurityAltID(new Integer(2));
        instrument.getSecurityAltIDGroups()[0].setSecurityAltID("Alt ID MOT NASDAQ 1");
        instrument.getSecurityAltIDGroups()[0].setSecurityAltIDSource("2");
        instrument.getSecurityAltIDGroups()[1].setSecurityAltID("Alt ID MOT NASDAQ 2");
        instrument.getSecurityAltIDGroups()[1].setSecurityAltIDSource("3");
        instrument.setSecurityType(secType.getValue());
        instrument.setCfiCode("CFI MOT");
        instrument.setProduct(Product.EQUITY);
        instrument.setMaturityMonthYear("200806w2");
        Calendar calm = Calendar.getInstance();
        calm.set(Calendar.DAY_OF_MONTH, 21);
        calm.set(Calendar.MONTH, 9);
        calm.set(Calendar.YEAR, 2009);
        instrument.setMaturityDate(calm.getTime());
        calm.add(Calendar.MONTH, 1);
        instrument.setCouponPaymentDate(calm.getTime());
        calm.add(Calendar.YEAR, -1);
        instrument.setIssueDate(calm.getTime());
        instrument.setRepoCollateralSecurityType("RVRP");
        instrument.setRepurchaseTerm(new Integer(5));
        instrument.setRepurchaseRate(new Double("15.5"));
        instrument.setFactor(new Double("1.2367"));
        instrument.setCreditRating("AAA");
        instrument.setInstrRegistry("Rego 1");
        instrument.setCountryOfIssue("US");
        instrument.setStateOrProvinceOfIssue("New York");
        instrument.setLocaleOfIssue("US");
        Calendar calr = Calendar.getInstance();
        calr.set(Calendar.DAY_OF_MONTH, 16);
        calr.set(Calendar.MONTH, 1);
        calr.set(Calendar.YEAR, 2009);
        instrument.setRedemptionDate(calr.getTime());
        instrument.setStrikePrice(new Double("22.22"));
        instrument.setOptAttribute(new Character('C'));
        instrument.setContractMultiplier(new Double("1.023"));
        instrument.setCouponRate(new Double("1.077"));
        instrument.setSecurityExchange("CBT");
        instrument.setIssuer("NYSE PL");
        instrument.setEncodedIssuerLen(new Integer(8));
        byte[] issuerData = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        instrument.setEncodedIssuer(issuerData);
        instrument.setSecurityDesc("Motorola shares");
        instrument.setEncodedSecurityDescLen(new Integer(8));
        byte[] securityData = new byte[] {(byte) 44, (byte) 22, (byte) 55, (byte) 36,
            (byte) 166, (byte) 233, (byte) 19, (byte) 95};
        instrument.setEncodedSecurityDesc(securityData);
    }

    public void check(Instrument expected, quickfix.fix43.component.Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getInt(quickfix.field.NoSecurityAltID.FIELD));
        quickfix.fix43.component.Instrument.NoSecurityAltID grp1 = new quickfix.fix43.component.Instrument.NoSecurityAltID();
        actual.getGroup(1, grp1);
        quickfix.field.SecurityAltID f1 = new quickfix.field.SecurityAltID();
        grp1.get(f1);
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), f1.getValue());
        quickfix.field.SecurityAltIDSource f2 = new quickfix.field.SecurityAltIDSource();
        grp1.get(f2);
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), f2.getValue());
        quickfix.fix43.component.Instrument.NoSecurityAltID grp2 = new quickfix.fix43.component.Instrument.NoSecurityAltID();
        actual.getGroup(2, grp2);
        quickfix.field.SecurityAltID f11 = new quickfix.field.SecurityAltID();
        grp2.get(f11);
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), f11.getValue());
        quickfix.field.SecurityAltIDSource f12 = new quickfix.field.SecurityAltIDSource();
        grp2.get(f12);
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), f12.getValue());
        assertEquals(expected.getProduct().getValue(), actual.getInt(quickfix.field.Product.FIELD));
        assertEquals(expected.getCfiCode(), actual.getString(quickfix.field.CFICode.FIELD));
        assertEquals(expected.getSecurityType(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertDateEquals(expected.getMaturityDate(), actual.getUtcDateOnly(quickfix.field.MaturityDate.FIELD));
        assertUTCDateEquals(expected.getCouponPaymentDate(), actual.getUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD));
        assertUTCDateEquals(expected.getIssueDate(), actual.getUtcDateOnly(quickfix.field.IssueDate.FIELD));
        assertEquals(expected.getRepoCollateralSecurityType(), actual.getString(quickfix.field.RepoCollateralSecurityType.FIELD));
        assertEquals(expected.getRepurchaseTerm().intValue(), actual.getInt(quickfix.field.RepurchaseTerm.FIELD));
        assertEquals(expected.getRepurchaseRate().doubleValue(), actual.getDecimal(quickfix.field.RepurchaseRate.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getFactor().doubleValue(), actual.getDecimal(quickfix.field.Factor.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCreditRating(), actual.getString(quickfix.field.CreditRating.FIELD));
        assertEquals(expected.getInstrRegistry(), actual.getString(quickfix.field.InstrRegistry.FIELD));
        assertEquals(expected.getCountryOfIssue(), actual.getString(quickfix.field.CountryOfIssue.FIELD));
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getString(quickfix.field.StateOrProvinceOfIssue.FIELD));
        assertEquals(expected.getLocaleOfIssue(), actual.getString(quickfix.field.LocaleOfIssue.FIELD));
        assertUTCDateEquals(expected.getRedemptionDate(), actual.getUtcDateOnly(quickfix.field.RedemptionDate.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute(), new Character(actual.getChar(quickfix.field.OptAttribute.FIELD)));
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
    }

    public void check(quickfix.fix43.component.Instrument expected, Instrument actual) throws Exception {
        assertEquals(actual.getSymbol(), expected.getString(quickfix.field.Symbol.FIELD));
        assertEquals(actual.getSymbolSfx(), expected.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(actual.getSecurityID(), expected.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(actual.getSecurityIDSource(), expected.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(actual.getNoSecurityAltID().intValue(), expected.getInt(quickfix.field.NoSecurityAltID.FIELD));
        quickfix.fix43.component.Instrument.NoSecurityAltID grp1 = new quickfix.fix43.component.Instrument.NoSecurityAltID();
        expected.getGroup(1, grp1);
        quickfix.field.SecurityAltID f1 = new quickfix.field.SecurityAltID();
        grp1.get(f1);
        assertEquals(actual.getSecurityAltIDGroups()[0].getSecurityAltID(), f1.getValue());
        quickfix.field.SecurityAltIDSource f2 = new quickfix.field.SecurityAltIDSource();
        grp1.get(f2);
        assertEquals(actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), f2.getValue());
        quickfix.fix43.component.Instrument.NoSecurityAltID grp2 = new quickfix.fix43.component.Instrument.NoSecurityAltID();
        expected.getGroup(2, grp2);
        quickfix.field.SecurityAltID f11 = new quickfix.field.SecurityAltID();
        grp2.get(f11);
        assertEquals(actual.getSecurityAltIDGroups()[1].getSecurityAltID(), f11.getValue());
        quickfix.field.SecurityAltIDSource f12 = new quickfix.field.SecurityAltIDSource();
        grp2.get(f12);
        assertEquals(actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), f12.getValue());
        assertEquals(actual.getProduct().getValue(), expected.getInt(quickfix.field.Product.FIELD));
        assertEquals(actual.getCfiCode(), expected.getString(quickfix.field.CFICode.FIELD));
        assertEquals(actual.getSecurityType(), expected.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(actual.getMaturityMonthYear(), expected.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertDateEquals(actual.getMaturityDate(), expected.getUtcDateOnly(quickfix.field.MaturityDate.FIELD));
        assertUTCDateEquals(actual.getCouponPaymentDate(), expected.getUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD));
        assertUTCDateEquals(actual.getIssueDate(), expected.getUtcDateOnly(quickfix.field.IssueDate.FIELD));
        assertEquals(actual.getRepoCollateralSecurityType(), expected.getString(quickfix.field.RepoCollateralSecurityType.FIELD));
        assertEquals(actual.getRepurchaseTerm().intValue(), expected.getInt(quickfix.field.RepurchaseTerm.FIELD));
        assertEquals(actual.getRepurchaseRate().doubleValue(), expected.getDecimal(quickfix.field.RepurchaseRate.FIELD).doubleValue(), 0.0001);
        assertEquals(actual.getFactor().doubleValue(), expected.getDecimal(quickfix.field.Factor.FIELD).doubleValue(), 0.0001);
        assertEquals(actual.getCreditRating(), expected.getString(quickfix.field.CreditRating.FIELD));
        assertEquals(actual.getInstrRegistry(), expected.getString(quickfix.field.InstrRegistry.FIELD));
        assertEquals(actual.getCountryOfIssue(), expected.getString(quickfix.field.CountryOfIssue.FIELD));
        assertEquals(actual.getStateOrProvinceOfIssue(), expected.getString(quickfix.field.StateOrProvinceOfIssue.FIELD));
        assertEquals(actual.getLocaleOfIssue(), expected.getString(quickfix.field.LocaleOfIssue.FIELD));
        assertUTCDateEquals(actual.getRedemptionDate(), expected.getUtcDateOnly(quickfix.field.RedemptionDate.FIELD));
        assertEquals(actual.getStrikePrice().doubleValue(), expected.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.0001);
        assertEquals(actual.getOptAttribute(), new Character(expected.getChar(quickfix.field.OptAttribute.FIELD)));
        assertEquals(actual.getContractMultiplier().doubleValue(), expected.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.0001);
        assertEquals(actual.getCouponRate().doubleValue(), expected.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.0001);
        assertEquals(actual.getSecurityExchange(), expected.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(actual.getIssuer(), expected.getString(quickfix.field.Issuer.FIELD));
        assertEquals(actual.getEncodedIssuerLen().intValue(), expected.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(actual.getEncodedIssuer(), expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(actual.getEncodedSecurityDescLen().intValue(), expected.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(actual.getEncodedSecurityDesc(), expected.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
    }

    public void check(Instrument expected, Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getNoSecurityAltID().intValue());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getProduct().getValue(), actual.getProduct().getValue());
        assertEquals(expected.getCfiCode(), actual.getCfiCode());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertDateEquals(expected.getMaturityDate(), actual.getMaturityDate());
        assertUTCDateEquals(expected.getCouponPaymentDate(), actual.getCouponPaymentDate());
        assertUTCDateEquals(expected.getIssueDate(), actual.getIssueDate());
        assertEquals(expected.getRepoCollateralSecurityType(), actual.getRepoCollateralSecurityType());
        assertEquals(expected.getRepurchaseTerm().intValue(), actual.getRepurchaseTerm().intValue());
        assertEquals(expected.getRepurchaseRate().doubleValue(), actual.getRepurchaseRate().doubleValue(), 0.01);
        assertEquals(expected.getFactor().doubleValue(), actual.getFactor().doubleValue(), 0.01);
        assertEquals(expected.getCreditRating(), actual.getCreditRating());
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertUTCDateEquals(expected.getRedemptionDate(), actual.getRedemptionDate());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.0001);
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.01);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.01);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
    }

    public void checkSecTypeMF(Instrument expected, Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getNoSecurityAltID().intValue());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
    }

    public void checkSecTypeOPT(Instrument expected, Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getNoSecurityAltID().intValue());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.0001);
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
    }

    public void checkSecTypeCORP(Instrument expected, Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getNoSecurityAltID().intValue());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertDateEquals(expected.getCouponPaymentDate(), actual.getCouponPaymentDate());
        assertDateEquals(expected.getIssueDate(), actual.getIssueDate());
        assertDateEquals(expected.getRedemptionDate(), actual.getRedemptionDate());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.0001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.0001);
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
    }

    public void checkSecTypeGOV(Instrument expected, Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getNoSecurityAltID().intValue());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertDateEquals(expected.getIssueDate(), actual.getIssueDate());
        assertEquals(expected.getFactor(), actual.getFactor(), 0.001);
        assertDateEquals(expected.getRedemptionDate(), actual.getRedemptionDate());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.0001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.0001);
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
    }

    public void checkSecTypeMONEY(Instrument expected, Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getNoSecurityAltID().intValue());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertDateEquals(expected.getIssueDate(), actual.getIssueDate());
        assertEquals(expected.getRepoCollateralSecurityType(), actual.getRepoCollateralSecurityType());
        assertEquals(expected.getRepurchaseTerm().intValue(), actual.getRepurchaseTerm().intValue());
        assertEquals(expected.getRepurchaseRate().doubleValue(), actual.getRepurchaseRate().doubleValue(), 0.0001);
        assertDateEquals(expected.getRedemptionDate(), actual.getRedemptionDate());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.0001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.0001);
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
    }

    public void checkSecTypeLOAN(Instrument expected, Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getNoSecurityAltID().intValue());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), actual.getSecurityAltIDGroups()[0].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltID(), actual.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals(expected.getSecurityAltIDGroups()[1].getSecurityAltIDSource(), actual.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertDateEquals(expected.getIssueDate(), actual.getIssueDate());
        assertDateEquals(expected.getRedemptionDate(), actual.getRedemptionDate());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.0001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.0001);
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
    }

}
