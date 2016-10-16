/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsMsg50SP1TestData.java
 *
 * $Id: RegistrationInstructionsMsg50SP1TestData.java,v 1.1 2011-10-29 01:31:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import net.hades.fix.TestUtils;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RegistrationInstructionsMsg;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.group.impl.v43.DistribInstsGroup43TestData;
import net.hades.fix.message.group.impl.v50.RgstDtlsGroup50TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.OwnershipType;
import net.hades.fix.message.type.RegistTransType;
import net.hades.fix.message.type.TaxAdvantageType;

/**
 * Test utility for RegistrationInstructionsMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class RegistrationInstructionsMsg50SP1TestData extends MsgTest {

    private static final RegistrationInstructionsMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new RegistrationInstructionsMsg50SP1TestData();
    }

    public static RegistrationInstructionsMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RegistrationInstructionsMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        
        msg.setRegistID("LST564567");
        msg.setRegistTransType(RegistTransType.Replace);
        msg.setRegistRefID("REG_REF_888");
        msg.setClOrdID("CLI_ORD_666");
        
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        
        msg.setAccount("8236483764");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setRegistAcctType("CASH");
        msg.setTaxAdvantageType(TaxAdvantageType.CurrentYearPayment.getValue());
        msg.setOwnershipType(OwnershipType.JointInvestors);
        
        msg.setNoRegistDtls(2);
        RgstDtlsGroup50TestData.getInstance().populate1(msg.getRgstDtlsGroups()[0]);
        RgstDtlsGroup50TestData.getInstance().populate2(msg.getRgstDtlsGroups()[1]);
        
        msg.setNoDistribInsts(2);
        DistribInstsGroup43TestData.getInstance().populate1(msg.getDistribInstsGroups()[0]);
        DistribInstsGroup43TestData.getInstance().populate2(msg.getDistribInstsGroups()[1]);
    }

    public void check(RegistrationInstructionsMsg expected, RegistrationInstructionsMsg actual) throws Exception {
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getRegistTransType(), actual.getRegistTransType());
        assertEquals(expected.getRegistRefID(), actual.getRegistRefID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getRegistAcctType(), actual.getRegistAcctType());
        assertEquals(expected.getTaxAdvantageType(), actual.getTaxAdvantageType());
        assertEquals(expected.getOwnershipType(), actual.getOwnershipType());
        
        assertEquals(expected.getNoRegistDtls(), actual.getNoRegistDtls());
        RgstDtlsGroup50TestData.getInstance().check(expected.getRgstDtlsGroups()[0], actual.getRgstDtlsGroups()[0]);
        RgstDtlsGroup50TestData.getInstance().check(expected.getRgstDtlsGroups()[1], actual.getRgstDtlsGroups()[1]);
        
        assertEquals(expected.getNoDistribInsts(), actual.getNoDistribInsts());
        DistribInstsGroup43TestData.getInstance().check(expected.getDistribInstsGroups()[0], actual.getDistribInstsGroups()[0]);
        DistribInstsGroup43TestData.getInstance().check(expected.getDistribInstsGroups()[1], actual.getDistribInstsGroups()[1]);
    }
}
