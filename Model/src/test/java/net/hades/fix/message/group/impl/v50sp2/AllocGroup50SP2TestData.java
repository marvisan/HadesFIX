/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocGroup50SP2TestData.java
 *
 * $Id: AllocGroup50SP2TestData.java,v 1.2 2011-10-29 09:42:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.CommissionData43TestData;
import net.hades.fix.message.comp.impl.v44.SettlInstructionsData44TestData;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties50SP2TestData;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.group.impl.v44.MiscFeeGroup44TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocHandlInst;
import net.hades.fix.message.type.AllocMethod;
import net.hades.fix.message.type.AllocPositionEffect;
import net.hades.fix.message.type.ClearingInstruction;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IndividualAllocType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlInstMode;

/**
 * Test utility for AllocGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class AllocGroup50SP2TestData extends MsgTest {

    private static final AllocGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new AllocGroup50SP2TestData();
    }

    public static AllocGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(AllocGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT384444");
        grp.setAllocAcctIDSource(AcctIDSource.SID);
        grp.setMatchStatus(MatchStatus.Uncompared);
        grp.setAllocPrice(26.33d);
        grp.setAllocQty(23.0d);
        grp.setIndividualAllocID("AALOC22222");
        grp.setProcessCode(ProcessCode.Regular);
        grp.setSecondaryIndividualAllocID("SECALOC8277333");
        grp.setAllocMethod(AllocMethod.Automatic);
        grp.setAllocCustomerCapacity("23");
        grp.setAllocPositionEffect(AllocPositionEffect.Close);
        grp.setIndividualAllocType(IndividualAllocType.SubAllocate);

        grp.setNestedParties();
        NestedParties50SP2TestData.getInstance().populate(grp.getNestedParties());

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
        grp.setAllocSettlCurrAmt(51.22d);
        grp.setSettlCurrency(Currency.CanadianDollar);
        grp.setAllocSettlCurrency(Currency.UnitedStatesDollar);
        grp.setSettlCurrFxRate(0.25d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        grp.setAccruedInterestAmt(21.66d);
        grp.setAllocAccruedInterestAmt(62.33d);
        grp.setAllocInterestAtMaturity(43.22d);
        grp.setSettlInstMode(SettlInstMode.Default);

        grp.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);

        grp.setNoClearingInstructions(2);
        grp.getClrInstGroups()[0].setClearingInstruction(ClearingInstruction.ExClearing);
        grp.getClrInstGroups()[1].setClearingInstruction(ClearingInstruction.BilateralNettingOnly);

        grp.setSettlInstructionsData();
        SettlInstructionsData44TestData.getInstance().populate(grp.getSettlInstructionsData());
    }

    public void populate2(AllocGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT3855555");
        grp.setAllocAcctIDSource(AcctIDSource.BIC);
        grp.setMatchStatus(MatchStatus.Advisory);
        grp.setAllocPrice(26.77d);
        grp.setAllocQty(24.0d);
        grp.setIndividualAllocID("AALOC44444");
        grp.setProcessCode(ProcessCode.PlanSponsor);
        grp.setSecondaryIndividualAllocID("SECALOC8277444");
        grp.setAllocMethod(AllocMethod.Guarantor);
        grp.setAllocCustomerCapacity("12");
        grp.setAllocPositionEffect(AllocPositionEffect.FIFO);
        grp.setIndividualAllocType(IndividualAllocType.ThirdPartyAllocation);

        grp.setNestedParties();
        NestedParties50SP2TestData.getInstance().populate(grp.getNestedParties());

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
        grp.setAllocSettlCurrAmt(57.22d);
        grp.setSettlCurrency(Currency.AustralianDollar);
        grp.setAllocSettlCurrency(Currency.AustralianDollar);
        grp.setSettlCurrFxRate(1.25d);
        grp.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Divide);
        grp.setAccruedInterestAmt(21.22d);
        grp.setAllocAccruedInterestAmt(61.33d);
        grp.setAllocInterestAtMaturity(41.22d);
        grp.setSettlInstMode(SettlInstMode.RequestReject);

        grp.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);

        grp.setNoClearingInstructions(2);
        grp.getClrInstGroups()[0].setClearingInstruction(ClearingInstruction.SelfClearing);
        grp.getClrInstGroups()[1].setClearingInstruction(ClearingInstruction.SpecialTrade);

        grp.setSettlInstructionsData();
        SettlInstructionsData44TestData.getInstance().populate(grp.getSettlInstructionsData());
    }

    public void check(AllocGroup expected, AllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getAllocPrice(), actual.getAllocPrice());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getIndividualAllocID(), actual.getIndividualAllocID());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getSecondaryIndividualAllocID(), actual.getSecondaryIndividualAllocID());
        assertEquals(expected.getAllocMethod(), actual.getAllocMethod());
        assertEquals(expected.getAllocCustomerCapacity(), actual.getAllocCustomerCapacity());
        assertEquals(expected.getAllocPositionEffect(), actual.getAllocPositionEffect());
        assertEquals(expected.getIndividualAllocType(), actual.getIndividualAllocType());

        NestedParties50TestData.getInstance().check(expected.getNestedParties(), actual.getNestedParties());

        assertEquals(expected.getNotifyBrokerOfCredit(), actual.getNotifyBrokerOfCredit());
        assertEquals(expected.getAllocHandlInst(), actual.getAllocHandlInst());
        assertEquals(expected.getAllocText(), actual.getAllocText());
        assertEquals(expected.getEncodedAllocTextLen().intValue(), actual.getEncodedAllocTextLen().intValue());
        assertArrayEquals(expected.getEncodedAllocText(), actual.getEncodedAllocText());

        CommissionData43TestData.getInstance().check(expected.getCommissionData(), actual.getCommissionData());

        assertEquals(expected.getAllocAvgPx(), actual.getAllocAvgPx());
        assertEquals(expected.getAllocNetMoney(), actual.getAllocNetMoney());
        assertEquals(expected.getSettlCurrAmt(), actual.getSettlCurrAmt());
        assertEquals(expected.getAllocSettlCurrAmt(), actual.getAllocSettlCurrAmt());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getAllocSettlCurrency(), actual.getAllocSettlCurrency());
        assertEquals(expected.getSettlCurrFxRate(), actual.getSettlCurrFxRate());
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getAllocAccruedInterestAmt(), actual.getAllocAccruedInterestAmt());
        assertEquals(expected.getAllocInterestAtMaturity(), actual.getAllocInterestAtMaturity());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);

        assertEquals(expected.getNoClearingInstructions(), actual.getNoClearingInstructions());
        assertEquals(expected.getClrInstGroups()[0].getClearingInstruction(), actual.getClrInstGroups()[0].getClearingInstruction());
        assertEquals(expected.getClrInstGroups()[1].getClearingInstruction(), actual.getClrInstGroups()[1].getClearingInstruction());
        
        SettlInstructionsData44TestData.getInstance().check(expected.getSettlInstructionsData(), actual.getSettlInstructionsData());
    }
}
