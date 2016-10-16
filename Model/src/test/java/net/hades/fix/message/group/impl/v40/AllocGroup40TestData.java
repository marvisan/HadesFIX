/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocGroup40TestData.java
 *
 * $Id: AllocGroup40TestData.java,v 1.1 2011-02-16 11:24:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v40;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.ProcessCode;

/**
 * Test utility for AllocGroup40 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class AllocGroup40TestData extends MsgTest {

    private static final AllocGroup40TestData INSTANCE;

    static {
        INSTANCE = new AllocGroup40TestData();
    }

    public static AllocGroup40TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(AllocGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT384444");
        grp.setAllocQty(23.0d);
        grp.setProcessCode(ProcessCode.Regular);
        grp.setClientID("CLI222444");
        grp.setExecBroker("BRO83444");
        grp.setCommission(2.33d);
        grp.setCommType(CommType.Absolute);
        grp.setNoDlvyInst(2);
        grp.getDlvyInstGroups()[0].setBrokerOfCredit("CR");
        grp.getDlvyInstGroups()[0].setDlvyInst("44");
        grp.getDlvyInstGroups()[1].setBrokerOfCredit("BR");
        grp.getDlvyInstGroups()[1].setDlvyInst("55");

        grp.setNoMiscFees(2);
        MiscFeeGroup40TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);

    }

    public void populate2(AllocGroup grp) throws UnsupportedEncodingException {
        grp.setAllocAccount("ACCT3855555");
        grp.setAllocQty(24.0d);
        grp.setProcessCode(ProcessCode.PlanSponsor);
        grp.setClientID("CLI222555");
        grp.setExecBroker("BRO835555");
        grp.setCommission(2.43d);
        grp.setCommType(CommType.CashDiscount);
        grp.setNoDlvyInst(2);
        grp.getDlvyInstGroups()[0].setBrokerOfCredit("BR");
        grp.getDlvyInstGroups()[0].setDlvyInst("66");
        grp.getDlvyInstGroups()[1].setBrokerOfCredit("DR");
        grp.getDlvyInstGroups()[1].setDlvyInst("77");

        grp.setNoMiscFees(2);
        MiscFeeGroup40TestData.getInstance().populate1(grp.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().populate2(grp.getMiscFeeGroups()[1]);
    }

    public void check(AllocGroup expected, AllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getProcessCode(), actual.getProcessCode());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getCommission(), actual.getCommission());
        assertEquals(expected.getCommType(), actual.getCommType());
        assertEquals(expected.getNoDlvyInst(), actual.getNoDlvyInst());
        assertEquals(expected.getDlvyInstGroups()[0].getBrokerOfCredit(), actual.getDlvyInstGroups()[0].getBrokerOfCredit());
        assertEquals(expected.getDlvyInstGroups()[0].getDlvyInst(), actual.getDlvyInstGroups()[0].getDlvyInst());
        assertEquals(expected.getDlvyInstGroups()[1].getBrokerOfCredit(), actual.getDlvyInstGroups()[1].getBrokerOfCredit());
        assertEquals(expected.getDlvyInstGroups()[1].getDlvyInst(), actual.getDlvyInstGroups()[1].getDlvyInst());

        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup40TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);
    }
}
