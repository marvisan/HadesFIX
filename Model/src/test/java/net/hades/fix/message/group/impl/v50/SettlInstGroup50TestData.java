/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstGroup50TestData.java
 *
 * $Id: SettlInstGroup50TestData.java,v 1.2 2011-10-29 09:42:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.comp.impl.v44.SettlInstructionsData44TestData;
import net.hades.fix.message.group.SettlInstGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PaymentMethod;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.SettlInstTransType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for SettlInstGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class SettlInstGroup50TestData extends MsgTest {

    private static final SettlInstGroup50TestData INSTANCE;

    static {
        INSTANCE = new SettlInstGroup50TestData();
    }

    public static SettlInstGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(SettlInstGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSettlInstID("X19273773");
        grp.setSettlInstTransType(SettlInstTransType.Restate);
        grp.setSettlInstRefID("REF555555");

        grp.setParties();
        Parties44TestData.getInstance().populate(grp.getParties());

        grp.setSide(Side.Buy);
        grp.setProduct(Product.COMMODITY);
        grp.setSecurityType(SecurityType.BradyBond.getValue());
        grp.setCFICode("A");
        grp.setSettlCurrency(Currency.CanadianDollar);
        cal.set(2010, 3, 14, 10, 21, 11);
        grp.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 15, 11, 22, 21);
        grp.setExpireTime(cal.getTime());
        cal.set(2010, 3, 16, 12, 23, 22);
        grp.setLastUpdateTime(cal.getTime());

        grp.setSettlInstructionsData();
        SettlInstructionsData44TestData.getInstance().populate(grp.getSettlInstructionsData());

        grp.setPaymentMethod(PaymentMethod.DirectDebit);
        grp.setPaymentRef("PREF9387444");
        grp.setCardHolderName("Missy Lizzy");
        grp.setCardNumber("8888777766665555");
        cal.set(2010, 1, 1, 11, 13, 32);
        grp.setCardStartDate(cal.getTime());
        cal.set(2015, 1, 1, 11, 13, 32);
        grp.setCardExpDate(cal.getTime());
        grp.setCardIssNum("765");
        cal.set(2011, 4, 6, 11, 13, 32);
        grp.setPaymentDate(cal.getTime());
        grp.setPaymentRemitterID("REM77766");
    }

    public void populate2(SettlInstGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        grp.setSettlInstID("X19273788");
        grp.setSettlInstTransType(SettlInstTransType.New);
        grp.setSettlInstRefID("REF5557777");

        grp.setParties();
        Parties44TestData.getInstance().populate(grp.getParties());

        grp.setSide(Side.Sell);
        grp.setProduct(Product.CURRENCY);
        grp.setSecurityType(SecurityType.BuySellback.getValue());
        grp.setCFICode("B");
        grp.setSettlCurrency(Currency.AustralianDollar);
        cal.set(2010, 3, 14, 20, 3, 3);
        grp.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 15, 19, 4, 4);
        grp.setExpireTime(cal.getTime());
        cal.set(2010, 3, 16, 18, 5, 5);
        grp.setLastUpdateTime(cal.getTime());

        grp.setSettlInstructionsData();
        SettlInstructionsData44TestData.getInstance().populate(grp.getSettlInstructionsData());

        grp.setPaymentMethod(PaymentMethod.BPAY);
        grp.setPaymentRef("PREF9387555");
        grp.setCardHolderName("Missy Gizzy");
        grp.setCardNumber("88887777666688888");
        cal.set(2010, 3, 10, 11, 13, 32);
        grp.setCardStartDate(cal.getTime());
        cal.set(2015, 5, 10, 11, 13, 32);
        grp.setCardExpDate(cal.getTime());
        grp.setCardIssNum("768");
        cal.set(2011, 4, 7, 11, 13, 32);
        grp.setPaymentDate(cal.getTime());
        grp.setPaymentRemitterID("REM77799");
    }

    public void check(SettlInstGroup expected, SettlInstGroup actual) throws Exception {
        assertEquals(expected.getSettlInstID(), actual.getSettlInstID());
        assertEquals(expected.getSettlInstTransType(), actual.getSettlInstTransType());
        assertEquals(expected.getSettlInstRefID(), actual.getSettlInstRefID());
        
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getProduct(), actual.getProduct());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getCFICode(), actual.getCFICode());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getLastUpdateTime(), actual.getLastUpdateTime(), false);

        SettlInstructionsData44TestData.getInstance().check(expected.getSettlInstructionsData(), actual.getSettlInstructionsData());

        assertEquals(expected.getPaymentMethod(), actual.getPaymentMethod());
        assertEquals(expected.getPaymentRef(), actual.getPaymentRef());
        assertEquals(expected.getCardHolderName(), actual.getCardHolderName());
        assertEquals(expected.getCardNumber(), actual.getCardNumber());
        assertDateEquals(expected.getCardStartDate(), actual.getCardStartDate());
        assertDateEquals(expected.getCardExpDate(), actual.getCardExpDate());
        assertEquals(expected.getCardIssNum(), actual.getCardIssNum());
        assertDateEquals(expected.getPaymentDate(), actual.getPaymentDate());
        assertEquals(expected.getPaymentRemitterID(), actual.getPaymentRemitterID());
    }
}
