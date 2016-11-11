/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionRequestMsg50TestData.java
 *
 * $Id: SettlementInstructionRequestMsg44TestData.java,v 1.1 2011-03-26 03:24:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SettlementInstructionRequestMsg;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.StandInstDbType;

/**
 * Test utility for SettlementInstructionRequestMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SettlementInstructionRequestMsg50TestData extends MsgTest {

    private static final SettlementInstructionRequestMsg50TestData INSTANCE;

    static {
        INSTANCE = new SettlementInstructionRequestMsg50TestData();
    }

    public static SettlementInstructionRequestMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SettlementInstructionRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setSettlInstReqID("REQ334422");
        cal.set(2010, 3, 14, 11, 13, 32);
        msg.setTransactTime(cal.getTime());
        
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        
        msg.setAllocAccount("8734724724");
        msg.setAllocAcctIDSource(AcctIDSource.SID);
        msg.setSide(Side.Buy);
        msg.setProduct(Product.CURRENCY);
        msg.setSecurityType(SecurityType.BradyBond.getValue());
        msg.setCfiCode("CFI_889999");
        msg.setSettlCurrency(Currency.AustralianDollar);
        cal.set(2011, 3, 12, 11, 23, 32);
        msg.setEffectiveTime(cal.getTime());
        cal.set(2011, 4, 12, 25, 23, 45);
        msg.setExpireTime(cal.getTime());
        cal.set(2011, 4, 3, 25, 44, 45);
        msg.setLastUpdateTime(cal.getTime());
        msg.setStandInstDbType(StandInstDbType.DTC_SID);
        msg.setStandInstDbName("DB_1");
        msg.setStandInstDbID("DB_9999");
    }

    public void check(SettlementInstructionRequestMsg expected, SettlementInstructionRequestMsg actual) throws Exception {
        assertEquals(expected.getSettlInstReqID(), actual.getSettlInstReqID());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getProduct(), actual.getProduct());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getCfiCode(), actual.getCfiCode());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
        assertUTCTimestampEquals(expected.getLastUpdateTime(), actual.getLastUpdateTime(), false);
        assertEquals(expected.getStandInstDbType(), actual.getStandInstDbType());
        assertEquals(expected.getStandInstDbName(), actual.getStandInstDbName());
        assertEquals(expected.getStandInstDbID(), actual.getStandInstDbID());
    }
}
