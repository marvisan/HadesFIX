/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument43TestData.java
 *
 * $Id: Instrument44TestData.java,v 1.3 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import java.util.Date;

import net.hades.fix.message.MsgTest;
import quickfix.fix44.Message;

import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for Instrument component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class Instrument44TestData extends MsgTest {

    private static final Instrument44TestData INSTANCE;

    static {
        INSTANCE = new Instrument44TestData();
    }

    public static Instrument44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.component.Instrument msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "WI");
        msg.setString(quickfix.field.SecurityID.FIELD, "MOTO");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "D");
        msg.setInt(quickfix.field.NoSecurityAltID.FIELD, 2);
        quickfix.fix44.component.Instrument.NoSecurityAltID grp1 = new quickfix.fix44.component.Instrument.NoSecurityAltID();
        grp1.setString(quickfix.field.SecurityAltID.FIELD, "Alt ID MOT 1");
        grp1.setString(quickfix.field.SecurityAltIDSource.FIELD, "1");
        msg.addGroup(grp1);
        quickfix.fix44.component.Instrument.NoSecurityAltID grp2 = new quickfix.fix44.component.Instrument.NoSecurityAltID();
        grp2.setString(quickfix.field.SecurityAltID.FIELD, "Alt ID MOT 2");
        grp2.setString(quickfix.field.SecurityAltIDSource.FIELD, "2");
        msg.addGroup(grp2);
        msg.setInt(quickfix.field.Product.FIELD, Product.EQUITY.getValue());
        msg.setString(quickfix.field.CFICode.FIELD, "CFI MOT");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.SecuritySubType.FIELD, "OOO");
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "200806w2");
        msg.setUtcDateOnly(quickfix.field.MaturityDate.FIELD, new Date());
        msg.setUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD, new Date());
        msg.setUtcDateOnly(quickfix.field.IssueDate.FIELD, new Date());
        msg.setString(quickfix.field.RepoCollateralSecurityType.FIELD, "EUSUPRA");
        msg.setInt(quickfix.field.RepurchaseTerm.FIELD, 5);
        msg.setDouble(quickfix.field.RepurchaseRate.FIELD, 15.5);
        msg.setDouble(quickfix.field.Factor.FIELD, 1.2367);
        msg.setString(quickfix.field.CreditRating.FIELD, "AAA");
        msg.setString(quickfix.field.InstrRegistry.FIELD, "BIC");
        msg.setString(quickfix.field.CountryOfIssue.FIELD, "US");
        msg.setString(quickfix.field.StateOrProvinceOfIssue.FIELD, "New York");
        msg.setString(quickfix.field.LocaleOfIssue.FIELD, "US");
        msg.setUtcDateOnly(quickfix.field.RedemptionDate.FIELD, new Date());
        msg.setDouble(quickfix.field.StrikePrice.FIELD, new Double("25.48"));
        msg.setString(quickfix.field.StrikeCurrency.FIELD, "USD");
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
        msg.setString(quickfix.field.Pool.FIELD, "pool");
        msg.setString(quickfix.field.ContractSettlMonth.FIELD, "200901w1");
        msg.setInt(quickfix.field.CPProgram.FIELD, 2);
        msg.setString(quickfix.field.CPRegType.FIELD, "reg_type");
        msg.setInt(quickfix.field.NoEvents.FIELD, 2);
        quickfix.fix44.component.Instrument.NoEvents grpe1 = new quickfix.fix44.component.Instrument.NoEvents();
        grpe1.setInt(quickfix.field.EventType.FIELD, 2);
        grpe1.setString(quickfix.field.EventDate.FIELD, "20090202");
        grpe1.setDouble(quickfix.field.EventPx.FIELD, 12.22);
        grpe1.setString(quickfix.field.EventText.FIELD, "text 1");
        msg.addGroup(grpe1);
        quickfix.fix44.component.Instrument.NoEvents grpe2 = new quickfix.fix44.component.Instrument.NoEvents();
        grpe2.setInt(quickfix.field.EventType.FIELD, 3);
        grpe2.setString(quickfix.field.EventDate.FIELD, "20090303");
        grpe2.setDouble(quickfix.field.EventPx.FIELD, 22.22);
        grpe2.setString(quickfix.field.EventText.FIELD, "text 2");
        msg.addGroup(grpe2);
        msg.setString(quickfix.field.DatedDate.FIELD, "20100508");
        msg.setString(quickfix.field.InterestAccrualDate.FIELD, "20100808");
    }

    public void populate(Instrument instrument) {
        instrument.setSymbol("MOT");
        instrument.setSymbolSfx("WI");
        instrument.setSecurityID("MOTX");
        instrument.setSecurityIDSource("D");
        instrument.setNoSecurityAltID(new Integer(2));
        instrument.getSecurityAltIDGroups()[0].setSecurityAltID("Alt ID MOT 1");
        instrument.getSecurityAltIDGroups()[0].setSecurityAltIDSource("2");
        instrument.getSecurityAltIDGroups()[1].setSecurityAltID("Alt ID MOT 2");
        instrument.getSecurityAltIDGroups()[1].setSecurityAltIDSource("3");
        instrument.setProduct(Product.EQUITY);
        instrument.setCfiCode("CFI MOT");
        instrument.setSecurityType(SecurityType.Option.getValue());
        instrument.setSecuritySubType("Sub");
        instrument.setMaturityMonthYear("200806w2");
        instrument.setMaturityDate(new Date());
        instrument.setCouponPaymentDate(new Date());
        instrument.setIssueDate(new Date());
        instrument.setRepoCollateralSecurityType("EUSUPRA");
        instrument.setRepurchaseTerm(new Integer(5));
        instrument.setRepurchaseRate(new Double("15.5"));
        instrument.setFactor(new Double("1.2367"));
        instrument.setCreditRating("AAA");
        instrument.setInstrRegistry("BIC");
        instrument.setCountryOfIssue("AU");
        instrument.setStateOrProvinceOfIssue("New York");
        instrument.setLocaleOfIssue("AU");
        Calendar calr = Calendar.getInstance();
        instrument.setRedemptionDate(calr.getTime());
        instrument.setStrikePrice(new Double("22.22"));
        instrument.setStrikeCurrency(Currency.AustralianDollar);
        instrument.setOptAttribute(new Character('C'));
        instrument.setContractMultiplier(new Double("1.023"));
        instrument.setCouponRate(new Double("1.077"));
        instrument.setSecurityExchange("NYSE");
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
        instrument.setPool("POOL");
        instrument.setContractSettlMonth("200901w1");
        instrument.setCpProgram(new Integer(2));
        instrument.setCpRegType("CpReg");
        instrument.setNoEvents(new Integer(2));
        instrument.getEvents()[0].setEventType(new Integer(4));
        instrument.getEvents()[0].setEventDate(new Date());
        instrument.getEvents()[0].setEventText("Event 1");
        instrument.getEvents()[1].setEventType(new Integer(3));
        instrument.getEvents()[1].setEventPx(new Double("24.23"));
        instrument.getEvents()[1].setEventText("Event 2");
        instrument.setDatedDate(new Date());
        instrument.setInterestAccrualDate(new Date());
    }

    public void check(Instrument expected, Message actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getInt(quickfix.field.NoSecurityAltID.FIELD));
        quickfix.fix44.component.Instrument.NoSecurityAltID grp1 = new quickfix.fix44.component.Instrument.NoSecurityAltID();
        actual.getGroup(1, grp1);
        quickfix.field.SecurityAltID f1 = new quickfix.field.SecurityAltID();
        grp1.get(f1);
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), f1.getValue());
        quickfix.field.SecurityAltIDSource f2 = new quickfix.field.SecurityAltIDSource();
        grp1.get(f2);
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), f2.getValue());
        quickfix.fix44.component.Instrument.NoSecurityAltID grp2 = new quickfix.fix44.component.Instrument.NoSecurityAltID();
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
        assertEquals(expected.getSecuritySubType(), actual.getString(quickfix.field.SecuritySubType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertDateEquals(expected.getMaturityDate(), actual.getUtcDateOnly(quickfix.field.MaturityDate.FIELD));
        assertDateEquals(expected.getCouponPaymentDate(), actual.getUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD));
        assertDateEquals(expected.getIssueDate(), actual.getUtcDateOnly(quickfix.field.IssueDate.FIELD));
        assertEquals(expected.getRepoCollateralSecurityType(), actual.getString(quickfix.field.RepoCollateralSecurityType.FIELD));
        assertEquals(expected.getRepurchaseTerm().intValue(), actual.getInt(quickfix.field.RepurchaseTerm.FIELD));
        assertEquals(expected.getRepurchaseRate().doubleValue(), actual.getDecimal(quickfix.field.RepurchaseRate.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getFactor().doubleValue(), actual.getDecimal(quickfix.field.Factor.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCreditRating(), actual.getString(quickfix.field.CreditRating.FIELD));
        assertEquals(expected.getInstrRegistry(), actual.getString(quickfix.field.InstrRegistry.FIELD));
        assertEquals(expected.getCountryOfIssue(), actual.getString(quickfix.field.CountryOfIssue.FIELD));
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getString(quickfix.field.StateOrProvinceOfIssue.FIELD));
        assertEquals(expected.getLocaleOfIssue(), actual.getString(quickfix.field.LocaleOfIssue.FIELD));
        assertDateEquals(expected.getRedemptionDate(), actual.getUtcDateOnly(quickfix.field.RedemptionDate.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getStrikeCurrency().getValue(), actual.getString(quickfix.field.StrikeCurrency.FIELD));
        assertEquals(expected.getOptAttribute(), new Character(actual.getChar(quickfix.field.OptAttribute.FIELD)));
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getPool(), actual.getString(quickfix.field.Pool.FIELD));
        assertEquals(expected.getContractSettlMonth(), actual.getString(quickfix.field.ContractSettlMonth.FIELD));
        assertEquals(expected.getCpProgram().intValue(), actual.getInt(quickfix.field.CPProgram.FIELD));
        assertEquals(expected.getCpRegType(), actual.getString(quickfix.field.CPRegType.FIELD));
        assertEquals(expected.getNoEvents().intValue(), actual.getInt(quickfix.field.NoEvents.FIELD));
        quickfix.fix44.component.Instrument.NoEvents grpe1 = new quickfix.fix44.component.Instrument.NoEvents();
        actual.getGroup(1, grpe1);
        quickfix.field.EventType fe1 = new quickfix.field.EventType();
        grpe1.get(fe1);
        assertEquals(expected.getEvents()[0].getEventType().intValue(), fe1.getValue());
        quickfix.field.EventDate fe2 = new quickfix.field.EventDate();
        grpe1.get(fe2);
        assertEquals(formatQFStringDate(expected.getEvents()[0].getEventDate()), fe2.getValue());
        quickfix.field.EventText fe4 = new quickfix.field.EventText();
        grpe1.get(fe4);
        assertEquals(expected.getEvents()[0].getEventText(), fe4.getValue());
        quickfix.fix44.component.Instrument.NoEvents grpe2 = new quickfix.fix44.component.Instrument.NoEvents();
        actual.getGroup(2, grpe2);
        quickfix.field.EventType fe11 = new quickfix.field.EventType();
        grpe2.get(fe11);
        assertEquals(expected.getEvents()[1].getEventType().intValue(), fe11.getValue());
        quickfix.field.EventPx fe13 = new quickfix.field.EventPx();
        grpe2.get(fe13);
        assertEquals(expected.getEvents()[1].getEventPx().doubleValue(), fe13.getValue(), 0.01);
        quickfix.field.EventText fe14 = new quickfix.field.EventText();
        grpe2.get(fe14);
        assertEquals(expected.getEvents()[1].getEventText(), fe14.getValue());
        assertDateEquals(expected.getDatedDate(), actual.getUtcDateOnly(quickfix.field.DatedDate.FIELD));
        assertDateEquals(expected.getInterestAccrualDate(), actual.getUtcDateOnly(quickfix.field.InterestAccrualDate.FIELD));
    }

    public void check(Message expected, Instrument actual) throws Exception {
        assertEquals(actual.getSymbol(), expected.getString(quickfix.field.Symbol.FIELD));
        assertEquals(actual.getSymbolSfx(), expected.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(actual.getSecurityID(), expected.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(actual.getSecurityIDSource(), expected.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(actual.getNoSecurityAltID().intValue(), expected.getInt(quickfix.field.NoSecurityAltID.FIELD));
        quickfix.fix44.component.Instrument.NoSecurityAltID grp1 = new quickfix.fix44.component.Instrument.NoSecurityAltID();
        expected.getGroup(1, grp1);
        quickfix.field.SecurityAltID f1 = new quickfix.field.SecurityAltID();
        grp1.get(f1);
        assertEquals(actual.getSecurityAltIDGroups()[0].getSecurityAltID(), f1.getValue());
        quickfix.field.SecurityAltIDSource f2 = new quickfix.field.SecurityAltIDSource();
        grp1.get(f2);
        assertEquals(actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), f2.getValue());
        quickfix.fix44.component.Instrument.NoSecurityAltID grp2 = new quickfix.fix44.component.Instrument.NoSecurityAltID();
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
        assertEquals(actual.getSecuritySubType(), expected.getString(quickfix.field.SecuritySubType.FIELD));
        assertEquals(actual.getMaturityMonthYear(), expected.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertDateEquals(actual.getMaturityDate(), expected.getUtcDateOnly(quickfix.field.MaturityDate.FIELD));
        assertDateEquals(actual.getCouponPaymentDate(), expected.getUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD));
        assertDateEquals(actual.getIssueDate(), expected.getUtcDateOnly(quickfix.field.IssueDate.FIELD));
        assertEquals(actual.getRepoCollateralSecurityType(), expected.getString(quickfix.field.RepoCollateralSecurityType.FIELD));
        assertEquals(actual.getRepurchaseTerm().intValue(), expected.getInt(quickfix.field.RepurchaseTerm.FIELD));
        assertEquals(actual.getRepurchaseRate().doubleValue(), expected.getDecimal(quickfix.field.RepurchaseRate.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getFactor().doubleValue(), expected.getDecimal(quickfix.field.Factor.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getCreditRating(), expected.getString(quickfix.field.CreditRating.FIELD));
        assertEquals(actual.getInstrRegistry(), expected.getString(quickfix.field.InstrRegistry.FIELD));
        assertEquals(actual.getCountryOfIssue(), expected.getString(quickfix.field.CountryOfIssue.FIELD));
        assertEquals(actual.getStateOrProvinceOfIssue(), expected.getString(quickfix.field.StateOrProvinceOfIssue.FIELD));
        assertEquals(actual.getLocaleOfIssue(), expected.getString(quickfix.field.LocaleOfIssue.FIELD));
        assertDateEquals(actual.getRedemptionDate(), expected.getUtcDateOnly(quickfix.field.RedemptionDate.FIELD));
        assertEquals(actual.getStrikePrice().doubleValue(), expected.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getStrikeCurrency().getValue(), expected.getString(quickfix.field.StrikeCurrency.FIELD));
        assertEquals(actual.getOptAttribute(), new Character(expected.getChar(quickfix.field.OptAttribute.FIELD)));
        assertEquals(actual.getContractMultiplier().doubleValue(), expected.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getCouponRate().doubleValue(), expected.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getSecurityExchange(), expected.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(actual.getIssuer(), expected.getString(quickfix.field.Issuer.FIELD));
        assertEquals(actual.getEncodedIssuerLen().intValue(), expected.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(actual.getEncodedIssuer(), expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(actual.getEncodedSecurityDescLen().intValue(), expected.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(actual.getEncodedSecurityDesc(), expected.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(actual.getPool(), expected.getString(quickfix.field.Pool.FIELD));
        assertEquals(actual.getContractSettlMonth(), expected.getString(quickfix.field.ContractSettlMonth.FIELD));
        assertEquals(actual.getCpProgram().intValue(), expected.getInt(quickfix.field.CPProgram.FIELD));
        assertEquals(actual.getCpRegType(), expected.getString(quickfix.field.CPRegType.FIELD));
        assertEquals(actual.getNoEvents().intValue(), expected.getInt(quickfix.field.NoEvents.FIELD));
        quickfix.fix44.component.Instrument.NoEvents grpe1 = new quickfix.fix44.component.Instrument.NoEvents();
        expected.getGroup(1, grpe1);
        quickfix.field.EventType fe1 = new quickfix.field.EventType();
        grpe1.get(fe1);
        assertEquals(actual.getEvents()[0].getEventType().intValue(), fe1.getValue());
        quickfix.field.EventDate fe2 = new quickfix.field.EventDate();
        grpe1.get(fe2);
        assertEquals(formatQFStringDate(actual.getEvents()[0].getEventDate()), fe2.getValue());
        quickfix.field.EventText fe4 = new quickfix.field.EventText();
        grpe1.get(fe4);
        assertEquals(actual.getEvents()[0].getEventText(), fe4.getValue());
        quickfix.fix44.component.Instrument.NoEvents grpe2 = new quickfix.fix44.component.Instrument.NoEvents();
        expected.getGroup(2, grpe2);
        quickfix.field.EventType fe11 = new quickfix.field.EventType();
        grpe2.get(fe11);
        assertEquals(actual.getEvents()[1].getEventType().intValue(), fe11.getValue());
        quickfix.field.EventPx fe13 = new quickfix.field.EventPx();
        grpe2.get(fe13);
        assertEquals(actual.getEvents()[1].getEventPx().doubleValue(), fe13.getValue(), 0.01);
        quickfix.field.EventText fe14 = new quickfix.field.EventText();
        grpe2.get(fe14);
        assertEquals(actual.getEvents()[1].getEventText(), fe14.getValue());
        assertDateEquals(actual.getDatedDate(), expected.getUtcDateOnly(quickfix.field.DatedDate.FIELD));
        assertDateEquals(actual.getInterestAccrualDate(), expected.getUtcDateOnly(quickfix.field.InterestAccrualDate.FIELD));
    }

    public void check(Instrument expected, quickfix.fix44.component.Instrument actual) throws Exception {
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getNoSecurityAltID().intValue(), actual.getInt(quickfix.field.NoSecurityAltID.FIELD));
        quickfix.fix43.Advertisement.NoSecurityAltID grp1 = new quickfix.fix43.Advertisement.NoSecurityAltID();
        actual.getGroup(1, grp1);
        quickfix.field.SecurityAltID f1 = new quickfix.field.SecurityAltID();
        grp1.get(f1);
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltID(), f1.getValue());
        quickfix.field.SecurityAltIDSource f2 = new quickfix.field.SecurityAltIDSource();
        grp1.get(f2);
        assertEquals(expected.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), f2.getValue());
        quickfix.fix43.Advertisement.NoSecurityAltID grp2 = new quickfix.fix43.Advertisement.NoSecurityAltID();
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
        assertEquals(expected.getSecuritySubType(), actual.getString(quickfix.field.SecuritySubType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertDateEquals(expected.getMaturityDate(), actual.getUtcDateOnly(quickfix.field.MaturityDate.FIELD));
        assertDateEquals(expected.getCouponPaymentDate(), actual.getUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD));
        assertDateEquals(expected.getIssueDate(), actual.getUtcDateOnly(quickfix.field.IssueDate.FIELD));
        assertEquals(expected.getRepoCollateralSecurityType(), actual.getString(quickfix.field.RepoCollateralSecurityType.FIELD));
        assertEquals(expected.getRepurchaseTerm().intValue(), actual.getInt(quickfix.field.RepurchaseTerm.FIELD));
        assertEquals(expected.getRepurchaseRate().doubleValue(), actual.getDecimal(quickfix.field.RepurchaseRate.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getFactor().doubleValue(), actual.getDecimal(quickfix.field.Factor.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCreditRating(), actual.getString(quickfix.field.CreditRating.FIELD));
        assertEquals(expected.getInstrRegistry(), actual.getString(quickfix.field.InstrRegistry.FIELD));
        assertEquals(expected.getCountryOfIssue(), actual.getString(quickfix.field.CountryOfIssue.FIELD));
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getString(quickfix.field.StateOrProvinceOfIssue.FIELD));
        assertEquals(expected.getLocaleOfIssue(), actual.getString(quickfix.field.LocaleOfIssue.FIELD));
        assertDateEquals(expected.getRedemptionDate(), actual.getUtcDateOnly(quickfix.field.RedemptionDate.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getStrikeCurrency().getValue(), actual.getString(quickfix.field.StrikeCurrency.FIELD));
        assertEquals(expected.getOptAttribute(), new Character(actual.getChar(quickfix.field.OptAttribute.FIELD)));
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(expected.getEncodedIssuer(), actual.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(expected.getPool(), actual.getString(quickfix.field.Pool.FIELD));
        assertEquals(expected.getContractSettlMonth(), actual.getString(quickfix.field.ContractSettlMonth.FIELD));
        assertEquals(expected.getCpProgram().intValue(), actual.getInt(quickfix.field.CPProgram.FIELD));
        assertEquals(expected.getCpRegType(), actual.getString(quickfix.field.CPRegType.FIELD));
        assertEquals(expected.getNoEvents().intValue(), actual.getInt(quickfix.field.NoEvents.FIELD));
        quickfix.fix44.Advertisement.NoEvents grpe1 = new quickfix.fix44.Advertisement.NoEvents();
        actual.getGroup(1, grpe1);
        quickfix.field.EventType fe1 = new quickfix.field.EventType();
        grpe1.get(fe1);
        assertEquals(expected.getEvents()[0].getEventType().intValue(), fe1.getValue());
        quickfix.field.EventDate fe2 = new quickfix.field.EventDate();
        grpe1.get(fe2);
        assertEquals(formatQFStringDate(expected.getEvents()[0].getEventDate()), fe2.getValue());
        quickfix.field.EventText fe4 = new quickfix.field.EventText();
        grpe1.get(fe4);
        assertEquals(expected.getEvents()[0].getEventText(), fe4.getValue());
        quickfix.fix44.Advertisement.NoEvents grpe2 = new quickfix.fix44.Advertisement.NoEvents();
        actual.getGroup(2, grpe2);
        quickfix.field.EventType fe11 = new quickfix.field.EventType();
        grpe2.get(fe11);
        assertEquals(expected.getEvents()[1].getEventType().intValue(), fe11.getValue());
        quickfix.field.EventPx fe13 = new quickfix.field.EventPx();
        grpe2.get(fe13);
        assertEquals(expected.getEvents()[1].getEventPx().doubleValue(), fe13.getValue(), 0.01);
        quickfix.field.EventText fe14 = new quickfix.field.EventText();
        grpe2.get(fe14);
        assertEquals(expected.getEvents()[1].getEventText(), fe14.getValue());
        assertDateEquals(expected.getDatedDate(), actual.getUtcDateOnly(quickfix.field.DatedDate.FIELD));
        assertDateEquals(expected.getInterestAccrualDate(), actual.getUtcDateOnly(quickfix.field.InterestAccrualDate.FIELD));
    }

    public void check(quickfix.fix44.component.Instrument expected, Instrument actual) throws Exception {
        assertEquals(actual.getSymbol(), expected.getString(quickfix.field.Symbol.FIELD));
        assertEquals(actual.getSymbolSfx(), expected.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(actual.getSecurityID(), expected.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(actual.getSecurityIDSource(), expected.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(actual.getNoSecurityAltID().intValue(), expected.getInt(quickfix.field.NoSecurityAltID.FIELD));
        quickfix.fix43.Advertisement.NoSecurityAltID grp1 = new quickfix.fix43.Advertisement.NoSecurityAltID();
        expected.getGroup(1, grp1);
        quickfix.field.SecurityAltID f1 = new quickfix.field.SecurityAltID();
        grp1.get(f1);
        assertEquals(actual.getSecurityAltIDGroups()[0].getSecurityAltID(), f1.getValue());
        quickfix.field.SecurityAltIDSource f2 = new quickfix.field.SecurityAltIDSource();
        grp1.get(f2);
        assertEquals(actual.getSecurityAltIDGroups()[0].getSecurityAltIDSource(), f2.getValue());
        quickfix.fix43.Advertisement.NoSecurityAltID grp2 = new quickfix.fix43.Advertisement.NoSecurityAltID();
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
        assertEquals(actual.getSecuritySubType(), expected.getString(quickfix.field.SecuritySubType.FIELD));
        assertEquals(actual.getMaturityMonthYear(), expected.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertDateEquals(actual.getMaturityDate(), expected.getUtcDateOnly(quickfix.field.MaturityDate.FIELD));
        assertDateEquals(actual.getCouponPaymentDate(), expected.getUtcDateOnly(quickfix.field.CouponPaymentDate.FIELD));
        assertDateEquals(actual.getIssueDate(), expected.getUtcDateOnly(quickfix.field.IssueDate.FIELD));
        assertEquals(actual.getRepoCollateralSecurityType(), expected.getString(quickfix.field.RepoCollateralSecurityType.FIELD));
        assertEquals(actual.getRepurchaseTerm().intValue(), expected.getInt(quickfix.field.RepurchaseTerm.FIELD));
        assertEquals(actual.getRepurchaseRate().doubleValue(), expected.getDecimal(quickfix.field.RepurchaseRate.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getFactor().doubleValue(), expected.getDecimal(quickfix.field.Factor.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getCreditRating(), expected.getString(quickfix.field.CreditRating.FIELD));
        assertEquals(actual.getInstrRegistry(), expected.getString(quickfix.field.InstrRegistry.FIELD));
        assertEquals(actual.getCountryOfIssue(), expected.getString(quickfix.field.CountryOfIssue.FIELD));
        assertEquals(actual.getStateOrProvinceOfIssue(), expected.getString(quickfix.field.StateOrProvinceOfIssue.FIELD));
        assertEquals(actual.getLocaleOfIssue(), expected.getString(quickfix.field.LocaleOfIssue.FIELD));
        assertDateEquals(actual.getRedemptionDate(), expected.getUtcDateOnly(quickfix.field.RedemptionDate.FIELD));
        assertEquals(actual.getStrikePrice().doubleValue(), expected.getDecimal(quickfix.field.StrikePrice.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getStrikeCurrency().getValue(), expected.getString(quickfix.field.StrikeCurrency.FIELD));
        assertEquals(actual.getOptAttribute(), new Character(expected.getChar(quickfix.field.OptAttribute.FIELD)));
        assertEquals(actual.getContractMultiplier().doubleValue(), expected.getDecimal(quickfix.field.ContractMultiplier.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getCouponRate().doubleValue(), expected.getDecimal(quickfix.field.CouponRate.FIELD).doubleValue(), 0.01);
        assertEquals(actual.getSecurityExchange(), expected.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(actual.getIssuer(), expected.getString(quickfix.field.Issuer.FIELD));
        assertEquals(actual.getEncodedIssuerLen().intValue(), expected.getInt(quickfix.field.EncodedIssuerLen.FIELD));
        assertArrayEquals(actual.getEncodedIssuer(), expected.getString(quickfix.field.EncodedIssuer.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(actual.getEncodedSecurityDescLen().intValue(), expected.getInt(quickfix.field.EncodedSecurityDescLen.FIELD));
        assertArrayEquals(actual.getEncodedSecurityDesc(), expected.getString(quickfix.field.EncodedSecurityDesc.FIELD).getBytes(DEFAULT_CHARACTER_SET));
        assertEquals(actual.getPool(), expected.getString(quickfix.field.Pool.FIELD));
        assertEquals(actual.getContractSettlMonth(), expected.getString(quickfix.field.ContractSettlMonth.FIELD));
        assertEquals(actual.getCpProgram().intValue(), expected.getInt(quickfix.field.CPProgram.FIELD));
        assertEquals(actual.getCpRegType(), expected.getString(quickfix.field.CPRegType.FIELD));
        assertEquals(actual.getNoEvents().intValue(), expected.getInt(quickfix.field.NoEvents.FIELD));
        quickfix.fix44.Advertisement.NoEvents grpe1 = new quickfix.fix44.Advertisement.NoEvents();
        expected.getGroup(1, grpe1);
        quickfix.field.EventType fe1 = new quickfix.field.EventType();
        grpe1.get(fe1);
        assertEquals(actual.getEvents()[0].getEventType().intValue(), fe1.getValue());
        quickfix.field.EventDate fe2 = new quickfix.field.EventDate();
        grpe1.get(fe2);
        assertEquals(formatQFStringDate(actual.getEvents()[0].getEventDate()), fe2.getValue());
        quickfix.field.EventText fe4 = new quickfix.field.EventText();
        grpe1.get(fe4);
        assertEquals(actual.getEvents()[0].getEventText(), fe4.getValue());
        quickfix.fix44.Advertisement.NoEvents grpe2 = new quickfix.fix44.Advertisement.NoEvents();
        expected.getGroup(2, grpe2);
        quickfix.field.EventType fe11 = new quickfix.field.EventType();
        grpe2.get(fe11);
        assertEquals(actual.getEvents()[1].getEventType().intValue(), fe11.getValue());
        quickfix.field.EventPx fe13 = new quickfix.field.EventPx();
        grpe2.get(fe13);
        assertEquals(actual.getEvents()[1].getEventPx().doubleValue(), fe13.getValue(), 0.01);
        quickfix.field.EventText fe14 = new quickfix.field.EventText();
        grpe2.get(fe14);
        assertEquals(actual.getEvents()[1].getEventText(), fe14.getValue());
        assertDateEquals(actual.getDatedDate(), expected.getUtcDateOnly(quickfix.field.DatedDate.FIELD));
        assertDateEquals(actual.getInterestAccrualDate(), expected.getUtcDateOnly(quickfix.field.InterestAccrualDate.FIELD));
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
        assertEquals(actual.getSecuritySubType(), expected.getSecuritySubType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertDateEquals(expected.getMaturityDate(), actual.getMaturityDate());
        assertDateEquals(expected.getCouponPaymentDate(), actual.getCouponPaymentDate());
        assertDateEquals(expected.getIssueDate(), actual.getIssueDate());
        assertEquals(expected.getRepoCollateralSecurityType(), actual.getRepoCollateralSecurityType());
        assertEquals(expected.getRepurchaseTerm().intValue(), actual.getRepurchaseTerm().intValue());
        assertEquals(expected.getRepurchaseRate().doubleValue(), actual.getRepurchaseRate().doubleValue(), 0.01);
        assertEquals(expected.getFactor().doubleValue(), actual.getFactor().doubleValue(), 0.01);
        assertEquals(expected.getCreditRating(), actual.getCreditRating());
        assertEquals(expected.getInstrRegistry(), actual.getInstrRegistry());
        assertEquals(expected.getCountryOfIssue(), actual.getCountryOfIssue());
        assertEquals(expected.getStateOrProvinceOfIssue(), actual.getStateOrProvinceOfIssue());
        assertEquals(expected.getLocaleOfIssue(), actual.getLocaleOfIssue());
        assertDateEquals(expected.getRedemptionDate(), actual.getRedemptionDate());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.01);
        assertEquals(actual.getStrikeCurrency().getValue(), expected.getStrikeCurrency().getValue());
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.01);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.01);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getPool(), actual.getPool());
        assertEquals(expected.getContractSettlMonth(), actual.getContractSettlMonth());
        assertEquals(expected.getCpProgram().intValue(), actual.getCpProgram().intValue());
        assertEquals(expected.getCpRegType(), actual.getCpRegType());
        assertEquals(expected.getNoEvents().intValue(), actual.getNoEvents().intValue());
        assertEquals(expected.getEvents()[0].getEventType().intValue(), actual.getEvents()[0].getEventType().intValue());
        assertDateEquals(expected.getEvents()[0].getEventDate(), actual.getEvents()[0].getEventDate());
        assertEquals(expected.getEvents()[0].getEventText(), actual.getEvents()[0].getEventText());
        assertEquals(expected.getEvents()[1].getEventType().intValue(), actual.getEvents()[1].getEventType().intValue());
        assertEquals(expected.getEvents()[1].getEventPx().doubleValue(), actual.getEvents()[1].getEventPx().doubleValue(), 0.01);
        assertEquals(expected.getEvents()[1].getEventText(), actual.getEvents()[1].getEventText());
        assertDateEquals(expected.getDatedDate(), actual.getDatedDate());
        assertDateEquals(expected.getInterestAccrualDate(), actual.getInterestAccrualDate());
    }
}
