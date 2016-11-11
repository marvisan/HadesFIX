/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionAckMsg40TestData.java
 *
 * $Id: AllocationInstructionAckMsg40TestData.java,v 1.1 2011-02-17 09:21:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationInstructionAckMsg;
import net.hades.fix.message.type.AllocRejCode;
import net.hades.fix.message.type.AllocStatus;

/**
 * Test utility for AllocationInstructionAckMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class AllocationInstructionAckMsg40TestData extends MsgTest {

    private static final AllocationInstructionAckMsg40TestData INSTANCE;

    static {
        INSTANCE = new AllocationInstructionAckMsg40TestData();
    }

    public static AllocationInstructionAckMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AllocationInstructionAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setAllocID("ALLOC775555");
        msg.setClientID("CLI7333333");
        msg.setExecBroker("BROKER1");
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setAllocStatus(AllocStatus.Accepted);
        msg.setAllocRejCode(AllocRejCode.Other);
        msg.setText("some text");
    }

    public void check(AllocationInstructionAckMsg expected, AllocationInstructionAckMsg actual) throws Exception {
        assertEquals(expected.getAllocID(), actual.getAllocID());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getAllocStatus(), actual.getAllocStatus());
        assertEquals(expected.getAllocRejCode(), actual.getAllocRejCode());
        assertEquals(expected.getText(), actual.getText());
    }
}
