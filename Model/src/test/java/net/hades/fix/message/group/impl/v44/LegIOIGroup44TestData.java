/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegIOIGroup44TestData.java
 *
 * $Id: LegIOIGroup44TestData.java,v 1.1 2009-07-06 03:19:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.LegStipulations44TestData;
import quickfix.FieldNotFound;
import quickfix.Message;

import static org.junit.Assert.*;

import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.group.LegIOIGroup;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.Product;

/**
 * Test utility for LegIOIGroup44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/03/2009, 6:40:39 PM
 */
public class LegIOIGroup44TestData extends MsgTest {

    private static final LegIOIGroup44TestData INSTANCE;

    static {
        INSTANCE = new LegIOIGroup44TestData();
    }

    public static LegIOIGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(Message msg) throws UnsupportedEncodingException, FieldNotFound {
        quickfix.fix44.IndicationOfInterest.NoLegs grpl = new quickfix.fix44.IndicationOfInterest.NoLegs();
        grpl.setString(quickfix.field.LegSymbol.FIELD, "JAVA");
        grpl.setString(quickfix.field.LegSymbolSfx.FIELD, "JAVA Shares");
        grpl.setString(quickfix.field.LegSecurityID.FIELD, "JAVA");
        grpl.setString(quickfix.field.LegSecurityIDSource.FIELD, "NYSE");
        grpl.setInt(quickfix.field.NoLegSecurityAltID.FIELD, 2);
        quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID grple1 = new quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID();
        grple1.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID JAVA 1");
        grple1.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID JAVA NASDAQ 1");
        grpl.addGroup(grple1);
        quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID grple2 = new quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID();
        grple2.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID JAVA 2");
        grple2.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID JAVA NASDAQ 2");
        grpl.addGroup(grple2);
        grpl.setInt(quickfix.field.LegProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.LegCFICode.FIELD, "CFI MOT");
        grpl.setString(quickfix.field.LegSecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        grpl.setString(quickfix.field.LegSecuritySubType.FIELD, "OOO");
        grpl.setString(quickfix.field.LegMaturityMonthYear.FIELD, "200806w2");
        Calendar calm = Calendar.getInstance();
        calm.set(2009, 9, 21, 23, 59, 59);
        calm.set(Calendar.MILLISECOND, 0);
        grpl.setUtcDateOnly(quickfix.field.LegMaturityDate.FIELD, calm.getTime());
        calm.add(Calendar.MONTH, 1);
        grpl.setUtcDateOnly(quickfix.field.LegCouponPaymentDate.FIELD, calm.getTime());
        calm.add(Calendar.YEAR, -1);
        grpl.setUtcDateOnly(quickfix.field.LegIssueDate.FIELD, calm.getTime());
        grpl.setInt(quickfix.field.LegRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.LegRepurchaseTerm.FIELD, 5);
        grpl.setDecimal(quickfix.field.LegRepurchaseRate.FIELD, new BigDecimal("15.5"));
        grpl.setDecimal(quickfix.field.LegFactor.FIELD, new BigDecimal("1.2367"));
        grpl.setString(quickfix.field.LegCreditRating.FIELD, "AAA");
        grpl.setString(quickfix.field.LegInstrRegistry.FIELD, "Rego 1");
        grpl.setString(quickfix.field.LegCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.LegStateOrProvinceOfIssue.FIELD, "New York");
        grpl.setString(quickfix.field.LegLocaleOfIssue.FIELD, "US");
        Calendar calr = Calendar.getInstance();
        calr.set(2009, 1, 16, 23, 59, 59);
        calr.set(Calendar.MILLISECOND, 0);
        grpl.setUtcDateOnly(quickfix.field.LegRedemptionDate.FIELD, calr.getTime());
        grpl.setDecimal(quickfix.field.LegStrikePrice.FIELD, new BigDecimal("25.48"));
        grpl.setString(quickfix.field.LegStrikeCurrency.FIELD, "USD");
        grpl.setChar(quickfix.field.LegOptAttribute.FIELD, 'C');
        grpl.setDecimal(quickfix.field.LegContractMultiplier.FIELD, new BigDecimal("1.023"));
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
        grpl.setString(quickfix.field.LegIOIQty.FIELD, "M");
        grpl.setInt(quickfix.field.NoLegStipulations.FIELD, 2);
        quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations grp1 = new quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations();
        grp1.setString(quickfix.field.LegStipulationType.FIELD, "stip typ 1");
        grp1.setString(quickfix.field.LegStipulationValue.FIELD, "stip value 1");
        grpl.addGroup(grp1);
        quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations grp2 = new quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations();
        grp2.setString(quickfix.field.LegStipulationType.FIELD, "stip typ 2");
        grp2.setString(quickfix.field.LegStipulationValue.FIELD, "stip value 2");
        grpl.addGroup(grp2);
        msg.addGroup(grpl);
    }

    public void populate2(Message msg) throws UnsupportedEncodingException, FieldNotFound {
        quickfix.fix44.IndicationOfInterest.NoLegs grpl = new quickfix.fix44.IndicationOfInterest.NoLegs();
        grpl.setString(quickfix.field.LegSymbol.FIELD, "IBM");
        grpl.setString(quickfix.field.LegSymbolSfx.FIELD, "IBM Shares");
        grpl.setString(quickfix.field.LegSecurityID.FIELD, "IBM");
        grpl.setString(quickfix.field.LegSecurityIDSource.FIELD, "LSE");
        grpl.setInt(quickfix.field.NoLegSecurityAltID.FIELD, 2);
        quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID grple1 = new quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID();
        grple1.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID IBM 1");
        grple1.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID IBM NASDAQ 1");
        grpl.addGroup(grple1);
        quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID grple2 = new quickfix.fix44.Advertisement.NoLegs.NoLegSecurityAltID();
        grple2.setString(quickfix.field.LegSecurityAltID.FIELD, "Alt ID IBM 2");
        grple2.setString(quickfix.field.LegSecurityAltIDSource.FIELD, "Alt ID IBM NASDAQ 2");
        grpl.addGroup(grple2);
        grpl.setInt(quickfix.field.LegProduct.FIELD, Product.EQUITY.getValue());
        grpl.setString(quickfix.field.LegCFICode.FIELD, "CFI IBM");
        grpl.setString(quickfix.field.LegSecurityType.FIELD, quickfix.field.SecurityType.OPTIONS_ON_FUTURES);
        grpl.setString(quickfix.field.LegSecuritySubType.FIELD, "ZZZ");
        grpl.setString(quickfix.field.LegMaturityMonthYear.FIELD, "200906w2");
        Calendar calm = Calendar.getInstance();
        calm.set(2009, 10, 1);
        grpl.setUtcDateOnly(quickfix.field.LegMaturityDate.FIELD, calm.getTime());
        calm.add(Calendar.MONTH, 1);
        grpl.setUtcDateOnly(quickfix.field.LegCouponPaymentDate.FIELD, calm.getTime());
        calm.add(Calendar.YEAR, -1);
        grpl.setUtcDateOnly(quickfix.field.LegIssueDate.FIELD, calm.getTime());
        grpl.setInt(quickfix.field.LegRepoCollateralSecurityType.FIELD, 16);
        grpl.setInt(quickfix.field.LegRepurchaseTerm.FIELD, 5);
        grpl.setDecimal(quickfix.field.LegRepurchaseRate.FIELD, new BigDecimal("15.51"));
        grpl.setDecimal(quickfix.field.LegFactor.FIELD, new BigDecimal("1.2361"));
        grpl.setString(quickfix.field.LegCreditRating.FIELD, "R");
        grpl.setString(quickfix.field.LegInstrRegistry.FIELD, "Rego 2");
        grpl.setString(quickfix.field.LegCountryOfIssue.FIELD, "US");
        grpl.setString(quickfix.field.LegStateOrProvinceOfIssue.FIELD, "New York");
        grpl.setString(quickfix.field.LegLocaleOfIssue.FIELD, "US");
        Calendar calr = Calendar.getInstance();
        calr.set(2009, 1, 18);
        grpl.setUtcDateOnly(quickfix.field.LegRedemptionDate.FIELD, calr.getTime());
        grpl.setDecimal(quickfix.field.LegStrikePrice.FIELD, new BigDecimal("25.481"));
        grpl.setString(quickfix.field.LegStrikeCurrency.FIELD, "USD");
        grpl.setChar(quickfix.field.LegOptAttribute.FIELD, 'C');
        grpl.setDecimal(quickfix.field.LegContractMultiplier.FIELD, new BigDecimal("1.0231"));
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
        msg.addGroup(grpl);
        msg.setString(quickfix.field.LegIOIQty.FIELD, "L");
        msg.setInt(quickfix.field.NoLegStipulations.FIELD, 2);
        quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations grp1 = new quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations();
        grp1.setString(quickfix.field.LegStipulationType.FIELD, "stip typ 1");
        grp1.setString(quickfix.field.LegStipulationValue.FIELD, "stip value 1");
        msg.addGroup(grp1);
        quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations grp2 = new quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations();
        grp2.setString(quickfix.field.LegStipulationType.FIELD, "stip typ 2");
        grp2.setString(quickfix.field.LegStipulationValue.FIELD, "stip value 2");
        msg.addGroup(grp2);
    }

    public void populate1(LegIOIGroup component) {
        component.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate1(component.getInstrumentLeg());
        component.setLegIOIQty(IOIQty.Large);
        component.setLegStipulations();
        LegStipulations44TestData.getInstance().populate(component.getLegStipulations());
    }

    public void populate2(LegIOIGroup component) {
        component.setInstrumentLeg();
        InstrumentLeg44TestData.getInstance().populate2(component.getInstrumentLeg());
        component.setLegIOIQty(IOIQty.Large);
//        component.setLegStipulations();
//        LegStipulations44TestData.getInstance().populate(component.getLegStipulations());
    }

    public void check1(LegIOIGroup expected, Message actual) throws Exception {
        quickfix.fix44.IndicationOfInterest.NoLegs grpi = new quickfix.fix44.IndicationOfInterest.NoLegs();
        actual.getGroup(1, grpi);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), grpi.getInstrumentLeg());
        assertEquals(expected.getLegIOIQty().getValue(), grpi.getString(quickfix.field.LegIOIQty.FIELD));
//        assertNotNull(expected.getLegStipulations());
//        for (int i = 0; i < expected.getLegStipulations().getNoLegStipulations().intValue(); i++) {
//            quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations grp = new quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations();
//            grpi.getGroup(i + 1, grp);
//            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType(), grp.getString(quickfix.field.LegStipulationType.FIELD));
//            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue(), grp.getString(quickfix.field.LegStipulationValue.FIELD));
//        }
    }

    public void check2(LegIOIGroup expected, Message actual) throws Exception {
        quickfix.fix44.IndicationOfInterest.NoLegs grpi = new quickfix.fix44.IndicationOfInterest.NoLegs();
        actual.getGroup(2, grpi);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), grpi.getInstrumentLeg());
        assertEquals(grpi.getLegIOIQty().getValue(), actual.getString(quickfix.field.LegIOIQty.FIELD));
//        assertNotNull(expected.getLegStipulations());
//        for (int i = 0; i < expected.getLegStipulations().getNoLegStipulations().intValue(); i++) {
//            quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations grp = new quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations();
//            grpi.getGroup(i + 1, grp);
//            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType(), grp.getString(quickfix.field.LegStipulationType.FIELD));
//            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue(), grp.getString(quickfix.field.LegStipulationValue.FIELD));
//        }
    }

    public void check(Message expected, LegIOIGroup actual) throws Exception {
        quickfix.fix44.IndicationOfInterest.NoLegs grpi = new quickfix.fix44.IndicationOfInterest.NoLegs();
        expected.getGroup(1, grpi);
        InstrumentLeg44TestData.getInstance().check(grpi.getInstrumentLeg(), actual.getInstrumentLeg());
        assertEquals(grpi.getString(quickfix.field.LegIOIQty.FIELD), actual.getLegIOIQty().getValue());
        assertNotNull(actual.getLegStipulations());
        for (int i = 0; i < actual.getLegStipulations().getNoLegStipulations().intValue(); i++) {
            quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations grp = new quickfix.fix44.IndicationOfInterest.NoLegs.NoLegStipulations();
            grpi.getGroup(i + 1, grp);
            assertEquals(grp.getString(quickfix.field.LegStipulationType.FIELD), actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType());
            assertEquals(grp.getString(quickfix.field.LegStipulationValue.FIELD), actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue());
        }
    }

    public void check(LegIOIGroup expected, LegIOIGroup actual) throws Exception {
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        assertEquals(expected.getLegIOIQty().getValue(), actual.getLegIOIQty().getValue());
        assertNotNull(expected.getLegStipulations());
        for (int i = 0; i < expected.getLegStipulations().getNoLegStipulations().intValue(); i++) {
            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType(),
                actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType());
            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue(),
                actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue());
        }
    }
}
