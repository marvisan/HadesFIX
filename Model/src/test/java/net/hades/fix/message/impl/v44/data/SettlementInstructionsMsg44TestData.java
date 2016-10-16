/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsg44TestData.java
 *
 * $Id: SettlementInstructionsMsg44TestData.java,v 1.1 2011-03-26 03:24:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SettlementInstructionsMsg;
import net.hades.fix.message.group.impl.v44.SettlInstGroup44TestData;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlInstReqRejCode;

/**
 * Test utility for SettlementInstructionsMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SettlementInstructionsMsg44TestData extends MsgTest {

    private static final SettlementInstructionsMsg44TestData INSTANCE;

    static {
        INSTANCE = new SettlementInstructionsMsg44TestData();
    }

    public static SettlementInstructionsMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SettlementInstructionsMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setSettlInstMsgID("A88663322");
        msg.setSettlInstReqID("REQ334422");
        msg.setSettlInstMode(SettlInstMode.Default);
        msg.setSettlInstReqRejCode(SettlInstReqRejCode.NoMatchingSettlement);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setClOrdID("CLI998633");
        cal.set(2010, 3, 14, 11, 13, 32);
        msg.setTransactTime(cal.getTime());

        msg.setNoSettlInst(2);
        SettlInstGroup44TestData.getInstance().populate1(msg.getSettlInstGroups()[0]);
        SettlInstGroup44TestData.getInstance().populate2(msg.getSettlInstGroups()[1]);
    }

    public void check(SettlementInstructionsMsg expected, SettlementInstructionsMsg actual) throws Exception {
        assertEquals(expected.getSettlInstMsgID(), actual.getSettlInstMsgID());
        assertEquals(expected.getSettlInstReqID(), actual.getSettlInstReqID());
        assertEquals(expected.getSettlInstMode(), actual.getSettlInstMode());
        assertEquals(expected.getSettlInstReqRejCode(), actual.getSettlInstReqRejCode());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);

        assertEquals(expected.getNoSettlInst(), actual.getNoSettlInst());
        SettlInstGroup44TestData.getInstance().check(expected.getSettlInstGroups()[0], actual.getSettlInstGroups()[0]);
        SettlInstGroup44TestData.getInstance().check(expected.getSettlInstGroups()[1], actual.getSettlInstGroups()[1]);
    }
}
