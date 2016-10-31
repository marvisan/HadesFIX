/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocGroup43TestData.java
 *
 * $Id: AllocGroup43TestData.java,v 1.3 2011-10-29 09:42:16 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v43.NestedParties43TestData;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.group.impl.v40.MiscFeeGroup40TestData;
import net.hades.fix.message.type.AllocHandlInst;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlInstMode;

/**
 * Test utility for AllocGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class AllocGroup43TestData extends MsgTest {

    private static final AllocGroup43TestData INSTANCE;

    static {
        INSTANCE = new AllocGroup43TestData();
    }

    public static AllocGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(AllocGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT384444");
        grp.setAllocPrice(26.33d);
        grp.setAllocQty(23.0d);
        grp.setIndividualAllocID("AALOC22222");
        grp.setProcessCode(ProcessCode.Regular);

        grp.setNestedParties();
        NestedParties43TestData.getInstance().populate(grp.getNestedParties());

        grp.setNotifyBrokerOfCredit(Boolean.TRUE);
        grp.setAllocHandlInst(AllocHandlInst.Forward);
        grp.setAllocText("ALLOC1");
        grp.setEncodedAllocTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        grp.setEncodedAllocText(encodedText);
        grp.setClientID("CLI222444");
        grp.setExecBroker("BRO83444");

        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

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
        grp.setAllocPrice(26.77d);
        grp.setAllocQty(24.0d);
        grp.setIndividualAllocID("AALOC44444");
        grp.setProcessCode(ProcessCode.PlanSponsor);

        grp.setNestedParties();
        NestedParties43TestData.getInstance().populate(grp.getNestedParties());

        grp.setNotifyBrokerOfCredit(Boolean.FALSE);
        grp.setAllocHandlInst(AllocHandlInst.Match);
        grp.setAllocText("ALLOC2");
        grp.setEncodedAllocTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 44, (byte) 43, (byte) 95,
            (byte) 177, (byte) 21, (byte) 66, (byte) 253};
        grp.setEncodedAllocText(encodedText);
        grp.setClientID("CLI222555");
        grp.setExecBroker("BRO835555");

        grp.setCommissionData();
        CommissionData43TestData.getInstance().populate(grp.getCommissionData());

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
        assertEquals(expected.getAllocPrice(), actual.getAllocPrice());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getIndividualAllocID(), actual.getIndividualAllocID());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());

        NestedParties43TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());

        assertEquals(expected.getNotifyBrokerOfCredit(), actual.getNotifyBrokerOfCredit());
        assertEquals(expected.getAllocHandlInst(), actual.getAllocHandlInst());
        assertEquals(expected.getAllocText(), actual.getAllocText());
        assertEquals(expected.getEncodedAllocTextLen().intValue(), actual.getEncodedAllocTextLen().intValue());
        assertArrayEquals(expected.getEncodedAllocText(), actual.getEncodedAllocText());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());

        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());

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
