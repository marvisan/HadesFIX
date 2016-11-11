/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmationAckMsg44TestData.java
 *
 * $Id: ConfirmationAckMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ConfirmationAckMsg;
import net.hades.fix.message.type.AffirmStatus;
import net.hades.fix.message.type.ConfirmRejReason;
import net.hades.fix.message.type.MatchStatus;

/**
 * Test utility for ConfirmationAckMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ConfirmationAckMsg44TestData extends MsgTest {

    private static final ConfirmationAckMsg44TestData INSTANCE;

    static {
        INSTANCE = new ConfirmationAckMsg44TestData();
    }

    public static ConfirmationAckMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ConfirmationAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setConfirmID("CONF_88888");
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setAffirmStatus(AffirmStatus.Affirmed);
        msg.setConfirmRejReason(ConfirmRejReason.MismatchedAccount);
        msg.setMatchStatus(MatchStatus.Uncompared);
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(ConfirmationAckMsg expected, ConfirmationAckMsg actual) throws Exception {
        assertEquals(expected.getConfirmID(), actual.getConfirmID());
        assertDateEquals(expected.getTradeDate(), actual.getTradeDate());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getAffirmStatus(), actual.getAffirmStatus());
        assertEquals(expected.getConfirmRejReason(), actual.getConfirmRejReason());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
