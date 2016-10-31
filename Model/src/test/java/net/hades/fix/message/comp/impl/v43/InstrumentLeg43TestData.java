/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg43TestData.java
 *
 * $Id: InstrumentLeg43TestData.java,v 1.1 2011-01-12 11:33:59 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for InstrumentLeg component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/02/2009, 4:45:14 PM
 */
public class InstrumentLeg43TestData extends MsgTest {

    private static final InstrumentLeg43TestData INSTANCE;

    static {
        INSTANCE = new InstrumentLeg43TestData();
    }

    public static InstrumentLeg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrumentLeg instrumentLeg) {
        instrumentLeg.setLegSymbol("MOT");
        instrumentLeg.setLegSymbolSfx("WI");
        instrumentLeg.setLegSecurityID("SEC_ID");
        instrumentLeg.setLegSecurityIDSource("4");
        instrumentLeg.setNoLegSecurityAltID(new Integer(2));
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_1");
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("2");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_2");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("3");
        instrumentLeg.setLegProduct(Product.EQUITY);
        instrumentLeg.setLegCFICode("cfi_code");
        instrumentLeg.setLegSecurityType(SecurityType.Option.getValue());
        instrumentLeg.setLegMaturityMonthYear("092009");
        Calendar calmd = Calendar.getInstance();
        calmd.set(Calendar.DAY_OF_MONTH, 3);
        calmd.set(Calendar.MONTH, 2);
        calmd.set(Calendar.YEAR, 2008);
        calmd.set(Calendar.HOUR_OF_DAY, 12);
        calmd.set(Calendar.MINUTE, 23);
        calmd.set(Calendar.SECOND, 55);
        calmd.set(Calendar.MILLISECOND, 0);
        instrumentLeg.setLegMaturityDate(calmd.getTime());
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
        instrumentLeg.setLegRepoCollateralSecurityType("FAC");
        instrumentLeg.setLegRepurchaseTerm(new Integer(12));
        instrumentLeg.setLegRepurchaseRate(new Double("12.25"));
        instrumentLeg.setLegFactor(new Double("2.34"));
        instrumentLeg.setLegCreditRating("AAA");
        instrumentLeg.setLegInstrRegistry("ISO");
        instrumentLeg.setLegCountryOfIssue("AU");
        instrumentLeg.setLegStateOrProvinceOfIssue("NSW");
        instrumentLeg.setLegLocaleOfIssue("AU");
        Calendar calrd = Calendar.getInstance();
        calrd.set(Calendar.DAY_OF_MONTH, 5);
        calrd.set(Calendar.MONTH, 3);
        calrd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegRedemptionDate(calrd.getTime());
        instrumentLeg.setLegStrikePrice(new Double(2.55));
        instrumentLeg.setLegOptAttribute(new Character('S'));
        instrumentLeg.setLegContractMultiplier(new Double("1.12"));
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
    }

    public void populate2(InstrumentLeg instrumentLeg) {
        instrumentLeg.setLegSymbol("MOT");
        instrumentLeg.setLegSymbolSfx("CD");
        instrumentLeg.setLegSecurityID("SEC_ID_1");
        instrumentLeg.setLegSecurityIDSource("5");
        instrumentLeg.setNoLegSecurityAltID(new Integer(2));
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltID("SEC_ALT_ID_3");
        instrumentLeg.getLegSecurityAltIDGroups()[0].setLegSecurityAltIDSource("1");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltID("SEC_ALT_ID_4");
        instrumentLeg.getLegSecurityAltIDGroups()[1].setLegSecurityAltIDSource("4");
        instrumentLeg.setLegProduct(Product.COMMODITY);
        instrumentLeg.setLegCFICode("cfi_code_1");
        instrumentLeg.setLegSecurityType(SecurityType.Future.getValue());
        instrumentLeg.setLegMaturityMonthYear("102009");
        Calendar calmd = Calendar.getInstance();
        calmd.set(Calendar.DAY_OF_MONTH, 3);
        calmd.set(Calendar.MONTH, 2);
        calmd.set(Calendar.YEAR, 2008);
        calmd.set(Calendar.HOUR_OF_DAY, 12);
        calmd.set(Calendar.MINUTE, 23);
        calmd.set(Calendar.SECOND, 55);
        calmd.set(Calendar.MILLISECOND, 0);
        instrumentLeg.setLegMaturityDate(calmd.getTime());
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
        instrumentLeg.setLegRepoCollateralSecurityType("FADN");
        instrumentLeg.setLegRepurchaseTerm(new Integer(12));
        instrumentLeg.setLegRepurchaseRate(new Double("12.251"));
        instrumentLeg.setLegFactor(new Double("2.341"));
        instrumentLeg.setLegCreditRating("AA");
        instrumentLeg.setLegInstrRegistry("BIC");
        instrumentLeg.setLegCountryOfIssue("AU");
        instrumentLeg.setLegStateOrProvinceOfIssue("NSW");
        instrumentLeg.setLegLocaleOfIssue("AU");
        Calendar calrd = Calendar.getInstance();
        calrd.set(Calendar.DAY_OF_MONTH, 5);
        calrd.set(Calendar.MONTH, 3);
        calrd.set(Calendar.YEAR, 2009);
        instrumentLeg.setLegRedemptionDate(calrd.getTime());
        instrumentLeg.setLegStrikePrice(new Double(2.44));
        instrumentLeg.setLegOptAttribute(new Character('S'));
        instrumentLeg.setLegContractMultiplier(new Double("1.121"));
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
    }

    public void check(InstrumentLeg expected, InstrumentLeg actual) throws Exception {
        assertEquals(expected.getLegSymbol(), actual.getLegSymbol());
        assertEquals(expected.getLegSymbolSfx(), actual.getLegSymbolSfx());
        assertEquals(expected.getLegSecurityID(), actual.getLegSecurityID());
        assertEquals(expected.getLegSecurityIDSource(), actual.getLegSecurityIDSource());
        assertEquals(expected.getNoLegSecurityAltID().intValue(), actual.getNoLegSecurityAltID().intValue());
        assertEquals(expected.getLegSecurityAltIDGroups()[0].getLegSecurityAltID(), actual.getLegSecurityAltIDGroups()[0].getLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[0].getLegSecurityAltIDSource(), actual.getLegSecurityAltIDGroups()[0].getLegSecurityAltIDSource());
        assertEquals(expected.getLegSecurityAltIDGroups()[1].getLegSecurityAltID(), actual.getLegSecurityAltIDGroups()[1].getLegSecurityAltID());
        assertEquals(expected.getLegSecurityAltIDGroups()[1].getLegSecurityAltIDSource(), actual.getLegSecurityAltIDGroups()[1].getLegSecurityAltIDSource());

        assertEquals(expected.getLegProduct().getValue(), actual.getLegProduct().getValue());
        assertEquals(expected.getLegCFICode(), actual.getLegCFICode());
        assertEquals(expected.getLegSecurityType(), actual.getLegSecurityType());
        assertEquals(expected.getLegMaturityMonthYear(), actual.getLegMaturityMonthYear());
        assertDateEquals(expected.getLegMaturityDate(), actual.getLegMaturityDate());
        assertDateEquals(expected.getLegCouponPaymentDate(), actual.getLegCouponPaymentDate());
        assertDateEquals(expected.getLegIssueDate(), actual.getLegIssueDate());
        assertEquals(expected.getLegRepoCollateralSecurityType(), actual.getLegRepoCollateralSecurityType());
        assertEquals(expected.getLegRepurchaseTerm().intValue(), actual.getLegRepurchaseTerm().intValue());
        assertEquals(expected.getLegRepurchaseRate().doubleValue(), actual.getLegRepurchaseRate().doubleValue(), 0.001);
        assertEquals(expected.getLegFactor().doubleValue(), actual.getLegFactor().doubleValue(), 0.001);
        assertEquals(expected.getLegCreditRating(), actual.getLegCreditRating());
        assertEquals(expected.getLegInstrRegistry(), actual.getLegInstrRegistry());
        assertEquals(expected.getLegCountryOfIssue(), actual.getLegCountryOfIssue());
        assertEquals(expected.getLegStateOrProvinceOfIssue(), actual.getLegStateOrProvinceOfIssue());
        assertEquals(expected.getLegLocaleOfIssue(), actual.getLegLocaleOfIssue());
        assertDateEquals(expected.getLegRedemptionDate(), actual.getLegRedemptionDate());
        assertEquals(expected.getLegStrikePrice().doubleValue(), actual.getLegStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getLegOptAttribute().charValue(), actual.getLegOptAttribute().charValue());
        assertEquals(expected.getLegContractMultiplier().doubleValue(), actual.getLegContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getLegCouponRate().doubleValue(), actual.getLegCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getLegSecurityExchange(), actual.getLegSecurityExchange());
        assertEquals(expected.getLegIssuer(), actual.getLegIssuer());
        assertEquals(expected.getEncodedLegIssuerLen().intValue(), actual.getEncodedLegIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedLegIssuer(), actual.getEncodedLegIssuer());
        assertEquals(expected.getLegSecurityDesc(), actual.getLegSecurityDesc());
        assertEquals(expected.getEncodedLegSecurityDescLen().intValue(), actual.getEncodedLegSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedLegSecurityDesc(), actual.getEncodedLegSecurityDesc());
        assertEquals(expected.getLegRatioQty().doubleValue(), actual.getLegRatioQty().doubleValue(), 0.001);
        assertEquals(expected.getLegSide().getValue(), actual.getLegSide().getValue());
    }
}
