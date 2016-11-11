/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FinancingDetails44TestData.java
 *
 * $Id: FinancingDetails44TestData.java,v 1.2 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DeliveryType;
import net.hades.fix.message.type.TerminationType;

/**
 * Test utility for FinancingDetails component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/03/2009, 2:01:26 PM
 */
public class FinancingDetails44TestData extends MsgTest {

    private static final FinancingDetails44TestData INSTANCE;

    static {
        INSTANCE = new FinancingDetails44TestData();
    }

    public static FinancingDetails44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.component.FinancingDetails msg) throws UnsupportedEncodingException {
        msg.setString(quickfix.field.AgreementDesc.FIELD, "some agreement");
        msg.setString(quickfix.field.AgreementCurrency.FIELD, "USD");
        msg.setInt(quickfix.field.TerminationType.FIELD, 1);
        msg.setUtcDateOnly(quickfix.field.StartDate.FIELD, new Date());
        msg.setUtcDateOnly(quickfix.field.EndDate.FIELD, new Date());
        msg.setInt(quickfix.field.DeliveryType.FIELD, 2);
        msg.setDouble(quickfix.field.MarginRatio.FIELD, 123.0);
    }

    public void populate(FinancingDetails component) {
        component.setAgreementDesc("agr desc");
        component.setAgreementCurrency(Currency.AustralianDollar);
        component.setTerminationType(TerminationType.Flexible);
        component.setStartDate(new Date());
        component.setEndDate(new Date());
        component.setDeliveryType(DeliveryType.Free);
        component.setMarginRatio(new Double(300.0));
    }

    public void check(FinancingDetails expected, quickfix.fix44.component.FinancingDetails actual) throws Exception {
        assertEquals(expected.getAgreementDesc(), actual.getString(quickfix.field.AgreementDesc.FIELD));
        assertEquals(expected.getAgreementCurrency().getValue(), actual.getString(quickfix.field.AgreementCurrency.FIELD));
        assertEquals(expected.getTerminationType().getValue(), actual.getInt(quickfix.field.TerminationType.FIELD));
        assertDateEquals(expected.getStartDate(), actual.getUtcDateOnly(quickfix.field.StartDate.FIELD));
        assertDateEquals(expected.getEndDate(), actual.getUtcDateOnly(quickfix.field.EndDate.FIELD));
        assertEquals(expected.getDeliveryType().getValue(), actual.getInt(quickfix.field.DeliveryType.FIELD));
        assertEquals(expected.getMarginRatio().doubleValue(), actual.getDouble(quickfix.field.MarginRatio.FIELD), 0.0001);
    }

    public void check(quickfix.fix44.component.FinancingDetails expected, FinancingDetails actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.AgreementDesc.FIELD), actual.getAgreementDesc());
        assertEquals(expected.getString(quickfix.field.AgreementCurrency.FIELD), actual.getAgreementCurrency().getValue());
        assertEquals(expected.getInt(quickfix.field.TerminationType.FIELD), actual.getTerminationType().getValue());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.StartDate.FIELD), actual.getStartDate());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.EndDate.FIELD), actual.getEndDate());
        assertEquals(expected.getInt(quickfix.field.DeliveryType.FIELD), actual.getDeliveryType().getValue());
        assertEquals(expected.getDouble(quickfix.field.MarginRatio.FIELD), actual.getMarginRatio().doubleValue(), 0.0001);
    }

    public void check(FinancingDetails expected, FinancingDetails actual) throws Exception {
        assertEquals(expected.getAgreementDesc(), actual.getAgreementDesc());
        assertEquals(expected.getAgreementCurrency().getValue(), actual.getAgreementCurrency().getValue());
        assertEquals(expected.getTerminationType().getValue(), actual.getTerminationType().getValue());
        assertDateEquals(expected.getStartDate(), actual.getStartDate());
        assertDateEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getDeliveryType().getValue(), actual.getDeliveryType().getValue());
        assertEquals(expected.getMarginRatio().doubleValue(), actual.getMarginRatio().doubleValue(), 0.0001);
    }
}
