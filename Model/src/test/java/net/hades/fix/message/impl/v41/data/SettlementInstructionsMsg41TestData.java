/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsg41TestData.java
 *
 * $Id: SettlementInstructionsMsg41TestData.java,v 1.1 2011-03-26 03:24:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SettlementInstructionsMsg;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.SettlDeliveryType;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlInstSource;
import net.hades.fix.message.type.SettlInstTransType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.StandInstDbType;

/**
 * Test utility for SettlementInstructionsMsg41 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SettlementInstructionsMsg41TestData extends MsgTest {

    private static final SettlementInstructionsMsg41TestData INSTANCE;

    static {
        INSTANCE = new SettlementInstructionsMsg41TestData();
    }

    public static SettlementInstructionsMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SettlementInstructionsMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setSettlInstID("X19273773");
        msg.setSettlInstTransType(SettlInstTransType.Restate);
        msg.setSettlInstMode(SettlInstMode.Default);
        msg.setSettlInstSource(SettlInstSource.Investor);
        msg.setAllocAccount("7266399933");
        msg.setSettlLocation("Eastwood");
        cal.set(2010, 3, 14, 12, 15, 33);
        msg.setTradeDate(cal.getTime());
        msg.setAllocID("99383776764");
        msg.setLastMkt("CBOT");
        msg.setSide(Side.Buy);
        msg.setSecurityType(SecurityType.BankNotes.getValue());
        cal.set(2010, 3, 14, 10, 21, 11);
        msg.setEffectiveTime(cal.getTime());
        cal.set(2010, 3, 14, 11, 13, 32);
        msg.setTransactTime(cal.getTime());
        msg.setClientID("572837222");
        msg.setExecBroker("BrokerFirst");
        msg.setStandInstDbType(StandInstDbType.DTC_SID);
        msg.setStandInstDbName("DBDDD");
        msg.setStandInstDbID("D123");
        msg.setSettlDeliveryType(SettlDeliveryType.TriParty);
        msg.setSettlDepositoryCode("DDD7273622");
        msg.setSettlBrkrCode("BRK88888");
        msg.setSettlInstCode("COMP76666");
        msg.setSecuritySettlAgentName("BigAgent");
        msg.setSecuritySettlAgentCode("BB76554444");
        msg.setSecuritySettlAgentAcctNum("986774433");
        msg.setSecuritySettlAgentAcctName("Big Agent Account");
        msg.setSecuritySettlAgentContactName("Jim Morrison");
        msg.setSecuritySettlAgentContactPhone("8766622333");
        msg.setCashSettlAgentName("Big Bank");
        msg.setCashSettlAgentCode("BBB666");
        msg.setCashSettlAgentAcctNum("88962566122");
        msg.setCashSettlAgentAcctName("Big Bank Account");
        msg.setCashSettlAgentContactName("Jack Theripper");
        msg.setCashSettlAgentContactPhone("9377366222");
    }

    public void check(SettlementInstructionsMsg expected, SettlementInstructionsMsg actual) throws Exception {
        assertEquals(expected.getSettlInstID(), actual.getSettlInstID());
        assertEquals(expected.getSettlInstTransType(), actual.getSettlInstTransType());
        assertEquals(expected.getSettlInstMode(), actual.getSettlInstMode());
        assertEquals(expected.getSettlInstSource(), actual.getSettlInstSource());
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getSettlLocation(), actual.getSettlLocation());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getLastMkt(), actual.getLastMkt());
        assertEquals(expected.getSide(), actual.getSide());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertUTCTimestampEquals(expected.getEffectiveTime(), actual.getEffectiveTime(), false);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getStandInstDbType(), actual.getStandInstDbType());
        assertEquals(expected.getStandInstDbName(), actual.getStandInstDbName());
        assertEquals(expected.getStandInstDbID(), actual.getStandInstDbID());
        assertEquals(expected.getSettlDeliveryType(), actual.getSettlDeliveryType());
        assertEquals(expected.getSettlDepositoryCode(), actual.getSettlDepositoryCode());
        assertEquals(expected.getSettlBrkrCode(), actual.getSettlBrkrCode());
        assertEquals(expected.getSettlInstCode(), actual.getSettlInstCode());
        assertEquals(expected.getSecuritySettlAgentName(), actual.getSecuritySettlAgentName());
        assertEquals(expected.getSecuritySettlAgentCode(), actual.getSecuritySettlAgentCode());
        assertEquals(expected.getSecuritySettlAgentAcctNum(), actual.getSecuritySettlAgentAcctNum());
        assertEquals(expected.getSecuritySettlAgentAcctName(), actual.getSecuritySettlAgentAcctName());
        assertEquals(expected.getSecuritySettlAgentContactName(), actual.getSecuritySettlAgentContactName());
        assertEquals(expected.getSecuritySettlAgentContactPhone(), actual.getSecuritySettlAgentContactPhone());
        assertEquals(expected.getCashSettlAgentName(), actual.getCashSettlAgentName());
        assertEquals(expected.getCashSettlAgentCode(), actual.getCashSettlAgentCode());
        assertEquals(expected.getCashSettlAgentAcctNum(), actual.getCashSettlAgentAcctNum());
        assertEquals(expected.getCashSettlAgentAcctName(), actual.getCashSettlAgentAcctName());
        assertEquals(expected.getCashSettlAgentContactName(), actual.getCashSettlAgentContactName());
        assertEquals(expected.getCashSettlAgentContactPhone(), actual.getCashSettlAgentContactPhone());
    }
}
