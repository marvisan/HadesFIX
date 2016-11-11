/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionAckMsg43TestData.java
 *
 * $Id: AllocationInstructionAckMsg43TestData.java,v 1.1 2011-02-17 09:21:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationInstructionAckMsg;
import net.hades.fix.message.comp.impl.v43.Parties43TestData;
import net.hades.fix.message.type.AllocRejCode;
import net.hades.fix.message.type.AllocStatus;

/**
 * Test utility for AllocationInstructionAckMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class AllocationInstructionAckMsg43TestData extends MsgTest {

    private static final AllocationInstructionAckMsg43TestData INSTANCE;

    static {
        INSTANCE = new AllocationInstructionAckMsg43TestData();
    }

    public static AllocationInstructionAckMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AllocationInstructionAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setAllocID("ALLOC775555");

        msg.setParties();
        Parties43TestData.getInstance().populate(msg.getParties());

        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setAllocStatus(AllocStatus.Accepted);
        msg.setAllocRejCode(AllocRejCode.Other);
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setLegalConfirm(Boolean.TRUE);
    }

    public void check(AllocationInstructionAckMsg expected, AllocationInstructionAckMsg actual) throws Exception {
        assertEquals(expected.getAllocID(), actual.getAllocID());

        Parties43TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getAllocStatus(), actual.getAllocStatus());
        assertEquals(expected.getAllocRejCode(), actual.getAllocRejCode());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getLegalConfirm(), actual.getLegalConfirm());
    }
}
