/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusMsg43TestData.java
 *
 * $Id: SecurityStatusMsg43TestData.java,v 1.2 2011-10-29 09:42:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityStatusMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.type.Adjustment;
import net.hades.fix.message.type.CorporateAction;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.HaltReason;
import net.hades.fix.message.type.SecurityTradingStatus;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityStatusMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityStatusMsg43TestData extends MsgTest {

    private static final SecurityStatusMsg43TestData INSTANCE;

    static {
        INSTANCE = new SecurityStatusMsg43TestData();
    }

    public static SecurityStatusMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityStatusMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setSecurityStatusReqID("REQ_11111");

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());
       
        msg.setCurrency(Currency.AustralianDollar);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setUnsolicitedIndicator(Boolean.TRUE);
        msg.setSecurityTradingStatus(SecurityTradingStatus.TradingHalt);
        msg.setFinancialStatus("bankrupt");
        msg.setCorporateAction(CorporateAction.SpecialAction);
        msg.setHaltReason(HaltReason.NewsPending);
        msg.setInViewOfCommon(Boolean.TRUE);
        msg.setDueToRelated(Boolean.TRUE);
        msg.setBuyVolume(35.0);
        msg.setSellVolume(23.0);
        msg.setHighPx(66.0);
        msg.setLowPx(64.0);
        msg.setLastPx(65.3);
        cal.set(2011, 6, 11, 12, 35, 45);
        msg.setTransactTime(cal.getTime());
        msg.setAdjustment(Adjustment.Cancel);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(SecurityStatusMsg expected, SecurityStatusMsg actual) throws Exception {
        assertEquals(expected.getSecurityStatusReqID(), actual.getSecurityStatusReqID());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getUnsolicitedIndicator(), actual.getUnsolicitedIndicator());
        assertEquals(expected.getSecurityTradingStatus(), actual.getSecurityTradingStatus());
        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
        assertEquals(expected.getHaltReason(), actual.getHaltReason());
        assertEquals(expected.getInViewOfCommon(), actual.getInViewOfCommon());
        assertEquals(expected.getDueToRelated(), actual.getDueToRelated());
        assertEquals(expected.getBuyVolume(), actual.getBuyVolume());
        assertEquals(expected.getSellVolume(), actual.getSellVolume());
        assertEquals(expected.getHighPx(), actual.getHighPx());
        assertEquals(expected.getLowPx(), actual.getLowPx());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getAdjustment(), actual.getAdjustment());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
