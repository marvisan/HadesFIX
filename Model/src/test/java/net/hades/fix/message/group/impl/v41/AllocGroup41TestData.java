/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocGroup41TestData.java
 *
 * $Id: AllocGroup41TestData.java,v 1.2 2011-10-29 09:42:25 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v41;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.group.impl.v40.MiscFeeGroup40TestData;
import net.hades.fix.message.type.AllocHandlInst;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlInstMode;

/**
 * Test utility for AllocGroup40 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class AllocGroup41TestData extends MsgTest {

    private static final AllocGroup41TestData INSTANCE;

    static {
        INSTANCE = new AllocGroup41TestData();
    }

    public static AllocGroup41TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(AllocGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT384444");
        grp.setAllocQty(23.0d);
        grp.setProcessCode(ProcessCode.Regular);
        grp.setBrokerOfCredit("BR");
        grp.setNotifyBrokerOfCredit(Boolean.TRUE);
        grp.setAllocHandlInst(AllocHandlInst.Forward);
        grp.setAllocText("ALLOC1");
        grp.setClientID("CLI222444");
        grp.setExecBroker("BRO83444");
        grp.setCommission(2.33d);
        grp.setCommType(CommType.Absolute);
        grp.setAllocAvgPx(23.22d);
        grp.setAllocNetMoney(44.33d);
        grp.setSettlCurrAmt(66.22d);
        grp.setSettlCurrency(Currency.CanadianDollar);
        grp.setSettlCurrFxRate(0.25d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        grp.setAccruedInterestAmt(21.66d);
        grp.setSettlInstMode(SettlInstMode.Default);

        grp.setNoMiscFees(2);
        MiscFeeGroup40TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);
    }

    public void populate2(AllocGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT3855555");
        grp.setAllocQty(24.0d);
        grp.setProcessCode(ProcessCode.PlanSponsor);
        grp.setBrokerOfCredit("CR");
        grp.setNotifyBrokerOfCredit(Boolean.FALSE);
        grp.setAllocHandlInst(AllocHandlInst.Match);
        grp.setAllocText("ALLOC2");
        grp.setClientID("CLI222555");
        grp.setExecBroker("BRO835555");
        grp.setCommission(2.43d);
        grp.setCommType(CommType.CashDiscount);
        grp.setAllocAvgPx(23.33d);
        grp.setAllocNetMoney(44.44d);
        grp.setSettlCurrAmt(66.66d);
        grp.setSettlCurrency(Currency.AustralianDollar);
        grp.setSettlCurrFxRate(1.25d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Divide);
        grp.setAccruedInterestAmt(21.22d);
        grp.setSettlInstMode(SettlInstMode.RequestReject);

        grp.setNoMiscFees(2);
        MiscFeeGroup40TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);
    }

    public void check(AllocGroup expected, AllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getBrokerOfCredit(), actual.getBrokerOfCredit());
        assertEquals(expected.getNotifyBrokerOfCredit(), actual.getNotifyBrokerOfCredit());
        assertEquals(expected.getAllocHandlInst(), actual.getAllocHandlInst());
        assertEquals(expected.getAllocText(), actual.getAllocText());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getAllocAvgPx(), actual.getAllocAvgPx());
        assertEquals(expected.getAllocNetMoney(), actual.getAllocNetMoney());
        assertEquals(expected.getSettlCurrAmt(), actual.getSettlCurrAmt());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getSettlCurrFxRate(), actual.getSettlCurrFxRate());
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getAccruedInterestAmt(), actual.getAccruedInterestAmt());
        assertEquals(expected.getSettlInstMode(), actual.getSettlInstMode());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);
    }
}
