/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusMsg44TestData.java
 *
 * $Id: SecurityStatusMsg44TestData.java,v 1.2 2011-10-29 09:42:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityStatusMsg;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.type.Adjustment;
import net.hades.fix.message.type.CorporateAction;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.HaltReason;
import net.hades.fix.message.type.SecurityTradingStatus;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityStatusMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityStatusMsg44TestData extends MsgTest {

    private static final SecurityStatusMsg44TestData INSTANCE;

    static {
        INSTANCE = new SecurityStatusMsg44TestData();
    }

    public static SecurityStatusMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityStatusMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setSecurityStatusReqID("REQ_11111");

        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());

        msg.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(msg.getInstrumentExtension());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(2);
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setCurrency(Currency.AustralianDollar);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setUnsolicitedIndicator(Boolean.TRUE);
        msg.setSecurityTradingStatus(SecurityTradingStatus.TradingHalt);
        msg.setFinancialStatus("1");
        msg.setCorporateAction(CorporateAction.ExDividend);
        msg.setHaltReason(HaltReason.NewsDissOld);
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

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

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
