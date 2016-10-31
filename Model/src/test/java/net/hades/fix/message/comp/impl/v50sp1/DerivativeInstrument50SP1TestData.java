/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument50SP1TestData.java
 *
 * $Id: DerivativeInstrument50SP1TestData.java,v 1.2 2011-10-29 09:42:12 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.DerivativeInstrument;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FuturesValuationMethod;
import net.hades.fix.message.type.InstrmtAssignmentMethod;
import net.hades.fix.message.type.ListMethod;
import net.hades.fix.message.type.PriceQuoteMethod;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityStatus;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.SettlMethod;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;

/**
 * Test utility for DerivativeInstrument component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 26/02/2009, 7:56:44 PM
 */
public class DerivativeInstrument50SP1TestData extends MsgTest {

    private static final DerivativeInstrument50SP1TestData INSTANCE;

    static {
        INSTANCE = new DerivativeInstrument50SP1TestData();
    }

    public static DerivativeInstrument50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeInstrument comp) {
        comp.setDerivativeSymbol("MOT");
        comp.setDerivativeSymbolSfx("CD");
        comp.setDerivativeSecurityID("MOTX");
        comp.setDerivativeSecurityIDSource("D");
        
        comp.setNoDerivativeSecurityAltID(new Integer(2));
        comp.getDerivativeSecurityAltIDGroups()[0].setDerivativeSecurityAltID("Alt ID MOT 1");
        comp.getDerivativeSecurityAltIDGroups()[0].setDerivativeSecurityAltIDSource("1");
        comp.getDerivativeSecurityAltIDGroups()[1].setDerivativeSecurityAltID("Alt ID MOT 2");
        comp.getDerivativeSecurityAltIDGroups()[1].setDerivativeSecurityAltIDSource("2");
        
        comp.setDerivativeProduct(Product.EQUITY);
        comp.setDerivativeProductComplex("product_complex");
        comp.setDerivativeSecurityGroup("sec_group");
        comp.setDerivativeCFICode("CFI MOT");
        comp.setDerivativeSecurityType(SecurityType.Option.getValue());
        comp.setDerivativeSecuritySubType("Sub");
        comp.setDerivativeMaturityMonthYear("200803w2");
        comp.setDerivativeMaturityDate(new Date());
        comp.setDerivativeMaturityTime(new Date());
        comp.setDerivativeSettleOnOpenFlag("settle_on_open");
        comp.setDerivativeInstrmtAssignmentMethod(InstrmtAssignmentMethod.ProRata);
        comp.setDerivativeSecurityStatus(SecurityStatus.Active);
        comp.setDerivativeIssueDate(new Date());
        comp.setDerivativeInstrRegistry("Rego 1");
        comp.setDerivativeCountryOfIssue("US");
        comp.setDerivativeStateOrProvinceOfIssue("New York");
        comp.setDerivativeLocaleOfIssue("US");
        comp.setDerivativeStrikePrice(new Double("22.22"));
        comp.setDerivativeStrikeCurrency(Currency.AustralianDollar);
        comp.setDerivativeStrikeMultiplier(new Double(1.267));
        comp.setDerivativeOptAttribute(new Character('C'));
        comp.setDerivativeContractMultiplier(new Double("1.023"));
        comp.setDerivativeMinPriceIncrement(new Double("0.555"));
        comp.setDerivativeMinPriceIncrementAmount(new Double(1.033));
        comp.setDerivativeUnitOfMeasure(UnitOfMeasure.Gallons);
        comp.setDerivativeUnitOfMeasureQty(new Double(1.22));
        comp.setDerivativePriceUnitOfMeasure(PriceUnitOfMeasure.Barrels);
        comp.setDerivativePriceUnitOfMeasureQty(new Double(1.003));
        comp.setDerivativeSettlMethod(SettlMethod.Cash);
        comp.setDerivativePriceQuoteMethod(PriceQuoteMethod.Index);
        comp.setDerivativeValuationMethod(FuturesValuationMethod.Equity);
        comp.setDerivativeListMethod(ListMethod.PreListedOnly);
        comp.setDerivativeCapPrice(new Double(23.456));
        comp.setDerivativeFloorPrice(new Double(25.678));
        comp.setDerivativePutOrCall(PutOrCall.Call);
        comp.setDerivativeExerciseStyle(ExerciseStyle.American);
        comp.setDerivativeOptPayAmount(34.76d);
        comp.setDerivativeTimeUnit(TimeUnit.Hour);
        comp.setDerivativeSecurityExchange("NYSE");
        comp.setDerivativePositionLimit(100);
        comp.setDerivativeNTPositionLimit(150);
        comp.setDerivativeIssuer("NYSE PL");
        comp.setDerivativeEncodedIssuerLen(new Integer(8));
        byte[] issuerData = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        comp.setDerivativeEncodedIssuer(issuerData);
        comp.setDerivativeSecurityDesc("Motorola shares");
        comp.setDerivativeEncodedSecurityDescLen(new Integer(8));
        byte[] securityData = new byte[] {(byte) 44, (byte) 22, (byte) 55, (byte) 36,
            (byte) 166, (byte) 233, (byte) 19, (byte) 95};
        comp.setDerivativeEncodedSecurityDesc(securityData);
        
        comp.setDerivativeSecurityXML();
        String instrDoc = "<?xml version='1.0' encoding='UTF-8'?>" +
            "<Instrmt Sfx='WI' Sym='MOT'/>";
        byte[] xmlData = instrDoc.getBytes();
        comp.getDerivativeSecurityXML().setDerivativeSecurityXML(xmlData);
        comp.getDerivativeSecurityXML().setDerivativeSecurityXMLSchema("Schema goes here");
        

        comp.setDerivativeContractSettlMonth("200803w1");

        comp.setNoDerivativeEvents(new Integer(2));
        comp.getDerivativeEvents()[0].setDerivativeEventType(new Integer(4));
        comp.getDerivativeEvents()[0].setDerivativeEventDate(new Date());
        comp.getDerivativeEvents()[0].setDerivativeEventText("Event 1");
        comp.getDerivativeEvents()[1].setDerivativeEventType(new Integer(5));
        comp.getDerivativeEvents()[1].setDerivativeEventPx(new Double("24.23"));
        comp.getDerivativeEvents()[1].setDerivativeEventText("Event 2");

        comp.setDerivativeInstrumentParties();
        DerivativeInstrumentParties50SP1TestData.getInstance().populate(comp.getDerivativeInstrumentParties());
    }

    public void check(DerivativeInstrument expected, DerivativeInstrument actual) throws Exception {
        assertEquals(expected.getDerivativeSymbol(), actual.getDerivativeSymbol());
        assertEquals(expected.getDerivativeSymbolSfx(), actual.getDerivativeSymbolSfx());
        assertEquals(expected.getDerivativeSecurityID(), actual.getDerivativeSecurityID());
        assertEquals(expected.getDerivativeSecurityIDSource(), actual.getDerivativeSecurityIDSource());
        
        assertEquals(expected.getNoDerivativeSecurityAltID(), actual.getNoDerivativeSecurityAltID());
        assertEquals(expected.getDerivativeSecurityAltIDGroups()[0].getDerivativeSecurityAltID(), actual.getDerivativeSecurityAltIDGroups()[0].getDerivativeSecurityAltID());
        assertEquals(expected.getDerivativeSecurityAltIDGroups()[0].getDerivativeSecurityAltIDSource(), actual.getDerivativeSecurityAltIDGroups()[0].getDerivativeSecurityAltIDSource());
        assertEquals(expected.getDerivativeSecurityAltIDGroups()[1].getDerivativeSecurityAltID(), actual.getDerivativeSecurityAltIDGroups()[1].getDerivativeSecurityAltID());
        assertEquals(expected.getDerivativeSecurityAltIDGroups()[1].getDerivativeSecurityAltIDSource(), actual.getDerivativeSecurityAltIDGroups()[1].getDerivativeSecurityAltIDSource());
        
        assertEquals(expected.getDerivativeProduct(), actual.getDerivativeProduct());
        assertEquals(expected.getDerivativeProductComplex(), actual.getDerivativeProductComplex());
        assertEquals(expected.getDerivativeSecurityGroup(), actual.getDerivativeSecurityGroup());
        assertEquals(expected.getDerivativeCFICode(), actual.getDerivativeCFICode());
        assertEquals(expected.getDerivativeSecurityType(), actual.getDerivativeSecurityType());
        assertEquals(expected.getDerivativeSecuritySubType(), actual.getDerivativeSecuritySubType());
        assertEquals(expected.getDerivativeMaturityMonthYear(), actual.getDerivativeMaturityMonthYear());
        assertDateEquals(expected.getDerivativeMaturityDate(), actual.getDerivativeMaturityDate());
//        assertTimeEquals(expected.getDerivativeMaturityTime(), actual.getDerivativeMaturityTime(), false);
        assertEquals(expected.getDerivativeSettleOnOpenFlag(), actual.getDerivativeSettleOnOpenFlag());
        assertEquals(expected.getDerivativeInstrmtAssignmentMethod(), actual.getDerivativeInstrmtAssignmentMethod());
        assertEquals(expected.getDerivativeSecurityStatus(), actual.getDerivativeSecurityStatus());
        assertDateEquals(expected.getDerivativeIssueDate(), actual.getDerivativeIssueDate());
        assertEquals(expected.getDerivativeInstrRegistry(), actual.getDerivativeInstrRegistry());
        assertEquals(expected.getDerivativeCountryOfIssue(), actual.getDerivativeCountryOfIssue());
        assertEquals(expected.getDerivativeStateOrProvinceOfIssue(), actual.getDerivativeStateOrProvinceOfIssue());
        assertEquals(expected.getDerivativeLocaleOfIssue(), actual.getDerivativeLocaleOfIssue());
        assertEquals(expected.getDerivativeStrikePrice().doubleValue(), actual.getDerivativeStrikePrice().doubleValue(), 0.01);
        assertEquals(expected.getDerivativeStrikeCurrency(), actual.getDerivativeStrikeCurrency());
        assertEquals(expected.getDerivativeStrikeMultiplier().doubleValue(), actual.getDerivativeStrikeMultiplier().doubleValue(), 0.01);
        assertEquals(expected.getDerivativeOptAttribute(), actual.getDerivativeOptAttribute());
        assertEquals(expected.getDerivativeContractMultiplier().doubleValue(), actual.getDerivativeContractMultiplier().doubleValue(), 0.01);
        assertEquals(expected.getDerivativeMinPriceIncrement().doubleValue(), actual.getDerivativeMinPriceIncrement().doubleValue(), 0.01);
        assertEquals(expected.getDerivativeMinPriceIncrementAmount().doubleValue(), actual.getDerivativeMinPriceIncrementAmount().doubleValue(), 0.01);
        assertEquals(expected.getDerivativeUnitOfMeasure(), actual.getDerivativeUnitOfMeasure());
        assertEquals(expected.getDerivativeUnitOfMeasureQty().doubleValue(), actual.getDerivativeUnitOfMeasureQty().doubleValue(), 0.01);
        assertEquals(expected.getDerivativePriceUnitOfMeasure(), actual.getDerivativePriceUnitOfMeasure());
        assertEquals(expected.getDerivativePriceUnitOfMeasureQty(), actual.getDerivativePriceUnitOfMeasureQty());
        assertEquals(expected.getDerivativeSettlMethod(), actual.getDerivativeSettlMethod());
        assertEquals(expected.getDerivativeExerciseStyle(), actual.getDerivativeExerciseStyle());
        assertEquals(expected.getDerivativeOptPayAmount().doubleValue(), actual.getDerivativeOptPayAmount().doubleValue(), 0.01);
        assertEquals(expected.getDerivativePriceQuoteMethod(), actual.getDerivativePriceQuoteMethod());
        assertEquals(expected.getDerivativeValuationMethod(), actual.getDerivativeValuationMethod());
        assertEquals(expected.getDerivativeListMethod(), actual.getDerivativeListMethod());
        assertEquals(expected.getDerivativeCapPrice().doubleValue(), actual.getDerivativeCapPrice().doubleValue(), 0.01);
        assertEquals(expected.getDerivativeFloorPrice(), actual.getDerivativeFloorPrice());
        assertEquals(expected.getDerivativePutOrCall(), actual.getDerivativePutOrCall());
        assertEquals(expected.getDerivativeTimeUnit(), actual.getDerivativeTimeUnit());
        assertEquals(expected.getDerivativePositionLimit(), actual.getDerivativePositionLimit());
        assertEquals(expected.getDerivativeNTPositionLimit(), actual.getDerivativeNTPositionLimit());
        assertEquals(expected.getDerivativeSecurityExchange(), actual.getDerivativeSecurityExchange());
        assertEquals(expected.getDerivativeIssuer(), actual.getDerivativeIssuer());
        assertEquals(expected.getDerivativeEncodedIssuerLen(), actual.getDerivativeEncodedIssuerLen());
        assertArrayEquals(expected.getDerivativeEncodedIssuer(), actual.getDerivativeEncodedIssuer());
        assertEquals(expected.getDerivativeEncodedSecurityDescLen(), actual.getDerivativeEncodedSecurityDescLen());
        assertArrayEquals(expected.getDerivativeEncodedSecurityDesc(), actual.getDerivativeEncodedSecurityDesc());
        
        assertNotNull(actual.getDerivativeSecurityXML());
//        assertEquals(expected.getSecurityXML().getSecurityXMLLen().intValue(), actual.getSecurityXML().getSecurityXMLLen().intValue());
//        assertEquals(new String(expected.getSecurityXML().getSecurityXML()), new String(actual.getSecurityXML().getSecurityXML()));
        assertEquals(expected.getDerivativeSecurityXML().getDerivativeSecurityXMLSchema(), actual.getDerivativeSecurityXML().getDerivativeSecurityXMLSchema());
        assertEquals(expected.getDerivativeContractSettlMonth(), actual.getDerivativeContractSettlMonth());

        assertEquals(expected.getNoDerivativeEvents(), actual.getNoDerivativeEvents());
        assertEquals(expected.getDerivativeEvents()[0].getDerivativeEventType(), actual.getDerivativeEvents()[0].getDerivativeEventType());
        assertDateEquals(expected.getDerivativeEvents()[0].getDerivativeEventDate(), actual.getDerivativeEvents()[0].getDerivativeEventDate());
        assertEquals(expected.getDerivativeEvents()[0].getDerivativeEventText(), actual.getDerivativeEvents()[0].getDerivativeEventText());
        assertEquals(expected.getDerivativeEvents()[1].getDerivativeEventType(), actual.getDerivativeEvents()[1].getDerivativeEventType());
        assertDateEquals(expected.getDerivativeEvents()[1].getDerivativeEventDate(), actual.getDerivativeEvents()[1].getDerivativeEventDate());
        assertEquals(expected.getDerivativeEvents()[1].getDerivativeEventText(), actual.getDerivativeEvents()[1].getDerivativeEventText());

        assertNotNull(expected.getDerivativeInstrumentParties());
        DerivativeInstrumentParties50SP1TestData.getInstance().check(expected.getDerivativeInstrumentParties(), actual.getDerivativeInstrumentParties());
    }
}
