/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusMsg42TestData.java
 *
 * $Id: SecurityStatusMsg42TestData.java,v 1.2 2011-10-29 09:42:07 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityStatusMsg;
import net.hades.fix.message.type.Adjustment;
import net.hades.fix.message.type.CorporateAction;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.HaltReason;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityTradingStatus;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for SecurityStatusMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityStatusMsg42TestData extends MsgTest {

    private static final SecurityStatusMsg42TestData INSTANCE;

    static {
        INSTANCE = new SecurityStatusMsg42TestData();
    }

    public static SecurityStatusMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityStatusMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setSecurityStatusReqID("REQ_11111");

        msg.setSymbol("BHP.AX");
        msg.setSymbolSfx("CDDF");
        msg.setSecurityID("BHP");
        msg.setSecurityIDSource("BHP-src");
        msg.setSecurityType(SecurityType.Cash.getValue());
        msg.setMaturityMonthYear("022010");
        msg.setMaturityDay(2);
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(12.55d);
        msg.setOptAttribute('A');
        msg.setContractMultiplier(34.88d);
        msg.setCouponRate(35.66d);
        msg.setSecurityExchange("ASX");
        msg.setIssuer("issuer");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] encodedIssuer = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(encodedIssuer);
        msg.setSecurityDesc("description");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encodedSecDesc = new byte[] {(byte) 18, (byte) 34, (byte) 45, (byte) 97,
            (byte) 177, (byte) 200, (byte) 224, (byte) 253};
        msg.setEncodedSecurityDesc(encodedSecDesc);
        msg.setCurrency(Currency.AustralianDollar);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
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
    }

    public void check(SecurityStatusMsg expected, SecurityStatusMsg actual) throws Exception {
        assertEquals(expected.getSecurityStatusReqID(), actual.getSecurityStatusReqID());

        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay(), actual.getMaturityDay());
        assertEquals(expected.getPutOrCall(), actual.getPutOrCall());
        assertEquals(expected.getStrikePrice(), actual.getStrikePrice());
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getContractMultiplier(), actual.getContractMultiplier());
        assertEquals(expected.getCouponRate(), actual.getCouponRate());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
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
    }
}
