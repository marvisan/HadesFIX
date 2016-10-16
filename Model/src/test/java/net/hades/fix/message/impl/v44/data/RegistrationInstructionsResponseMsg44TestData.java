/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsResponseMsg44TestData.java
 *
 * $Id: RegistrationInstructionsResponseMsg44TestData.java,v 1.1 2011-10-29 02:54:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import net.hades.fix.TestUtils;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RegistrationInstructionsResponseMsg;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.RegistRejReasonCode;
import net.hades.fix.message.type.RegistStatus;
import net.hades.fix.message.type.RegistTransType;

/**
 * Test utility for RegistrationInstructionsResponseMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class RegistrationInstructionsResponseMsg44TestData extends MsgTest {

    private static final RegistrationInstructionsResponseMsg44TestData INSTANCE;

    static {
        INSTANCE = new RegistrationInstructionsResponseMsg44TestData();
    }

    public static RegistrationInstructionsResponseMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RegistrationInstructionsResponseMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        
        msg.setRegistID("LST564567");
        msg.setRegistTransType(RegistTransType.Replace);
        msg.setRegistRefID("REG_REF_888");
        msg.setClOrdID("CLI_ORD_666");
        
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
        
        msg.setAccount("8236483764");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setRegistStatus(RegistStatus.Rejected);
        msg.setRegistRejReasonCode(RegistRejReasonCode.InvalidRegSeqNo);
        msg.setRegistRejReasonText("Some reason here.");
    }

    public void check(RegistrationInstructionsResponseMsg expected, RegistrationInstructionsResponseMsg actual) throws Exception {
        assertEquals(expected.getRegistID(), actual.getRegistID());
        assertEquals(expected.getRegistTransType(), actual.getRegistTransType());
        assertEquals(expected.getRegistRefID(), actual.getRegistRefID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getRegistStatus(), actual.getRegistStatus());
        assertEquals(expected.getRegistRejReasonCode(), actual.getRegistRejReasonCode());
        assertEquals(expected.getRegistRejReasonText(), actual.getRegistRejReasonText());
    }
}
