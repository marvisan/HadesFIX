/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusMsg42TestData.java
 *
 * $Id: TradingSessionStatusMsg42TestData.java,v 1.1 2011-04-23 00:19:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradingSessionStatusMsg;
import net.hades.fix.message.type.TradSesMethod;
import net.hades.fix.message.type.TradSesMode;
import net.hades.fix.message.type.TradSesStatus;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for TradingSessionStatusMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class TradingSessionStatusMsg42TestData extends MsgTest {

    private static final TradingSessionStatusMsg42TestData INSTANCE;

    static {
        INSTANCE = new TradingSessionStatusMsg42TestData();
    }

    public static TradingSessionStatusMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(TradingSessionStatusMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setTradSesReqID("REQ_11111");
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradSesMethod(TradSesMethod.OpenOutcry);
        msg.setTradSesMode(TradSesMode.Production);
        msg.setUnsolicitedIndicator(Boolean.TRUE);
        msg.setTradSesStatus(TradSesStatus.Halted);
        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setTradSesStartTime(cal.getTime());
        cal.set(2011, 6, 11, 15, 33, 44);
        msg.setTradSesOpenTime(cal.getTime());
        cal.set(2011, 6, 11, 16, 12, 23);
        msg.setTradSesPreCloseTime(cal.getTime());
        cal.set(2011, 6, 11, 17, 11, 19);
        msg.setTradSesCloseTime(cal.getTime());
        cal.set(2011, 6, 11, 22, 11, 55);
        msg.setTradSesEndTime(cal.getTime());
        msg.setTotalVolumeTraded(25000d);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(TradingSessionStatusMsg expected, TradingSessionStatusMsg actual) throws Exception {
        assertEquals(expected.getTradSesReqID(), actual.getTradSesReqID());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradSesMethod(), actual.getTradSesMethod());
        assertEquals(expected.getTradSesMode(), actual.getTradSesMode());
        assertEquals(expected.getUnsolicitedIndicator(), actual.getUnsolicitedIndicator());
        assertEquals(expected.getTradSesStatus(), actual.getTradSesStatus());
        assertUTCTimestampEquals(expected.getTradSesStartTime(), actual.getTradSesStartTime(), false);
        assertUTCTimestampEquals(expected.getTradSesOpenTime(), actual.getTradSesOpenTime(), false);
        assertUTCTimestampEquals(expected.getTradSesPreCloseTime(), actual.getTradSesPreCloseTime(), false);
        assertUTCTimestampEquals(expected.getTradSesCloseTime(), actual.getTradSesCloseTime(), false);
        assertUTCTimestampEquals(expected.getTradSesEndTime(), actual.getTradSesEndTime(), false);
        assertEquals(expected.getTotalVolumeTraded(), actual.getTotalVolumeTraded());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
