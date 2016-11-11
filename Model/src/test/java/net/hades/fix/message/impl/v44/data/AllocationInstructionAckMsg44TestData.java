/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionAckMsg44TestData.java
 *
 * $Id: AllocationInstructionAckMsg44TestData.java,v 1.1 2011-02-17 09:21:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationInstructionAckMsg;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.group.impl.v44.AllocAckGroup44TestData;
import net.hades.fix.message.type.AllocIntermedReqType;
import net.hades.fix.message.type.AllocRejCode;
import net.hades.fix.message.type.AllocStatus;
import net.hades.fix.message.type.AllocType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for AllocationInstructionAckMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class AllocationInstructionAckMsg44TestData extends MsgTest {

    private static final AllocationInstructionAckMsg44TestData INSTANCE;

    static {
        INSTANCE = new AllocationInstructionAckMsg44TestData();
    }

    public static AllocationInstructionAckMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AllocationInstructionAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setAllocID("ALLOC775555");

        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());

        msg.setSecondaryAllocID("SECALL999");
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setAllocStatus(AllocStatus.Accepted);
        msg.setAllocRejCode(AllocRejCode.UnknownAccount);
        msg.setAllocType(AllocType.Calculated);
        msg.setAllocIntermedReqType(AllocIntermedReqType.BlockLevelReject);
        msg.setMatchStatus(MatchStatus.Uncompared);
        msg.setProduct(Product.AGENCY);
        msg.setSecurityType(SecurityType.BankNotes.getValue());

        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);

        msg.setNoAllocs(2);
        AllocAckGroup44TestData.getInstance().populate1(msg.getAllocAckGroups()[0]);
        AllocAckGroup44TestData.getInstance().populate2(msg.getAllocAckGroups()[1]);
    }

    public void check(AllocationInstructionAckMsg expected, AllocationInstructionAckMsg actual) throws Exception {
        assertEquals(expected.getAllocID(), actual.getAllocID());

        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getSecondaryAllocID(), actual.getSecondaryAllocID());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getAllocStatus(), actual.getAllocStatus());
        assertEquals(expected.getAllocRejCode(), actual.getAllocRejCode());
        assertEquals(expected.getAllocType(), actual.getAllocType());
        assertEquals(expected.getAllocIntermedReqType(), actual.getAllocIntermedReqType());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getProduct(), actual.getProduct());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());

        assertEquals(expected.getNoAllocs(), actual.getNoAllocs());
        AllocAckGroup44TestData.getInstance().check(expected.getAllocAckGroups()[0], actual.getAllocAckGroups()[0]);
        AllocAckGroup44TestData.getInstance().check(expected.getAllocAckGroups()[1], actual.getAllocAckGroups()[1]);
    }
}
