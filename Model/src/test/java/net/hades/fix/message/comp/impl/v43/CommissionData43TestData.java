/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CommissionData43TestData.java
 *
 * $Id: CommissionData43TestData.java,v 1.2 2011-10-29 09:42:32 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for OrderQtyData43 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/03/2009, 3:30:53 PM
 */
public class CommissionData43TestData extends MsgTest {

    private static final CommissionData43TestData INSTANCE;

    static {
        INSTANCE = new CommissionData43TestData();
    }

    public static CommissionData43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(CommissionData component) {
        component.setCommission(1.666d);
        component.setCommType(CommType.CashDiscount);
        component.setCommCurrency(Currency.AustralianDollar);
        component.setFundRenewWaiv(Boolean.TRUE);
    }

    public void check(CommissionData expected, CommissionData actual) throws Exception {
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getCommCurrency(), actual.getCommCurrency());
        assertEquals(expected.getFundRenewWaiv(), actual.getFundRenewWaiv());
    }
}
