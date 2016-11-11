/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DistribInstsGroup43TestData.java
 *
 * $Id: DistribInstsGroup43TestData.java,v 1.2 2011-10-29 09:42:15 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DistribPaymentMethod;

/**
 * Test utility for DistribInstsGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class DistribInstsGroup43TestData extends MsgTest {

    private static final DistribInstsGroup43TestData INSTANCE;

    static {
        INSTANCE = new DistribInstsGroup43TestData();
    }

    public static DistribInstsGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(DistribInstsGroup grp) throws UnsupportedEncodingException {
        grp.setDistribPaymentMethod(DistribPaymentMethod.DirectCredit);
        grp.setDistribPercentage(23.56d);
        grp.setCashDistribCurr(Currency.UnitedStatesDollar);
        grp.setCashDistribAgentName("AGENT 007");
        grp.setCashDistribAgentCode("007");
        grp.setCashDistribAgentAcctNumber("7777777");
        grp.setCashDistribPayRef("REF_77");
        grp.setCashDistribAgentAcctName("James Bond");
    }

    public void populate2(DistribInstsGroup grp) throws UnsupportedEncodingException {
        grp.setDistribPaymentMethod(DistribPaymentMethod.BPAY);
        grp.setDistribPercentage(27.56d);
        grp.setCashDistribCurr(Currency.AustralianDollar);
        grp.setCashDistribAgentName("AGENT 008");
        grp.setCashDistribAgentCode("008");
        grp.setCashDistribAgentAcctNumber("8888888");
        grp.setCashDistribPayRef("REF_88");
        grp.setCashDistribAgentAcctName("James, James Bond");
    }

    public void check(DistribInstsGroup expected, DistribInstsGroup actual) throws Exception {
        assertEquals(expected.getDistribPaymentMethod(), actual.getDistribPaymentMethod());
        assertEquals(expected.getDistribPercentage(), actual.getDistribPercentage());
        assertEquals(expected.getCashDistribCurr(), actual.getCashDistribCurr());
        assertEquals(expected.getCashDistribAgentName(), actual.getCashDistribAgentName());
        assertEquals(expected.getCashDistribAgentCode(), actual.getCashDistribAgentCode());
        assertEquals(expected.getCashDistribAgentAcctNumber(), actual.getCashDistribAgentAcctNumber());
        assertEquals(expected.getCashDistribPayRef(), actual.getCashDistribPayRef());
        assertEquals(expected.getCashDistribAgentAcctName(), actual.getCashDistribAgentAcctName());
    }
}
