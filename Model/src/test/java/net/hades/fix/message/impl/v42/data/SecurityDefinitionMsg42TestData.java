/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionMsg42TestData.java
 *
 * $Id: SecurityDefinitionMsg42TestData.java,v 1.2 2011-10-29 09:42:07 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityDefinitionMsg;
import net.hades.fix.message.group.impl.v42.UndlySecurityGroup42TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityResponseType;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for SecurityDefinitionMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityDefinitionMsg42TestData extends MsgTest {

    private static final SecurityDefinitionMsg42TestData INSTANCE;

    static {
        INSTANCE = new SecurityDefinitionMsg42TestData();
    }

    public static SecurityDefinitionMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityDefinitionMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RSP888777");
        msg.setSecurityResponseType(SecurityResponseType.AcceptSecProposalAsIs);
        msg.setTotNoRelatedSym(4);
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

        msg.setNoRelatedSyms(2);
        UndlySecurityGroup42TestData.getInstance().populate1(msg.getUndlySecurityGroups()[0]);
        UndlySecurityGroup42TestData.getInstance().populate2(msg.getUndlySecurityGroups()[1]);
    }

    public void check(SecurityDefinitionMsg expected, SecurityDefinitionMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityResponseType(), actual.getSecurityResponseType());
        assertEquals(expected.getTotNoRelatedSym(), actual.getTotNoRelatedSym());
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
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());

        assertEquals(expected.getNoRelatedSyms(), actual.getNoRelatedSyms());
        UndlySecurityGroup42TestData.getInstance().check(expected.getUndlySecurityGroups()[0], actual.getUndlySecurityGroups()[0]);
        UndlySecurityGroup42TestData.getInstance().check(expected.getUndlySecurityGroups()[1], actual.getUndlySecurityGroups()[1]);
    }
}
