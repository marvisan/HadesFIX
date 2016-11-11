/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg42TestData.java
 *
 * $Id: MarketDataSnapshotMsg42TestData.java,v 1.2 2009-12-03 11:19:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.impl.v42.MDFullGroup42TestData;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;

/**
 * Test utility for MarketDataSnapshotMsg message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataSnapshotMsg42TestData extends MsgTest {

    private static final MarketDataSnapshotMsg42TestData INSTANCE;

    static {
        INSTANCE = new MarketDataSnapshotMsg42TestData();
    }

    public static MarketDataSnapshotMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataSnapshotMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setMdReqID("AAA564567");
        msg.setSymbol("symbol");
        msg.setSymbolSfx("symbol sfx");
        msg.setSecurityID("sec ID");
        msg.setSecurityIDSource("sec ID source");
        msg.setSecurityType(SecurityType.AmendedRestated.getValue());
        msg.setMaturityMonthYear("082009");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(12.456));
        msg.setOptAttribute(new Character('Z'));
        msg.setContractMultiplier(new Double(23.44));
        msg.setCouponRate(new Double(12.999));
        msg.setSecurityExchange("NYSE");
        msg.setIssuer("ISSUER");
        msg.setEncodedIssuerLen(new Integer(7));
        byte[] encLegIssuer = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225};
        msg.setEncodedIssuer(encLegIssuer);
        msg.setSecurityDesc("sec desc");
        msg.setEncodedSecurityDescLen(new Integer(6));
        byte[] encEncSecDesc = new byte[] {(byte) 25, (byte) 30, (byte) 35, (byte) 40,
            (byte) 41, (byte) 50};
        msg.setEncodedSecurityDesc(encEncSecDesc);
        msg.setFinancialStatus("fin stat 1");
        msg.setCorporateAction("corp act");
        msg.setTotalVolumeTraded(new Double(20.2));
        // MDFullGroup
        msg.setNoMDEntries(new Integer(2));
        MDFullGroup42TestData.getInstance().populate1(msg.getMdFullGroups()[0]);
        MDFullGroup42TestData.getInstance().populate2(msg.getMdFullGroups()[1]);
    }

    public void check(MarketDataSnapshotMsg expected, MarketDataSnapshotMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().intValue(), actual.getMaturityDay().intValue());
        assertEquals(expected.getPutOrCall().getValue(), actual.getPutOrCall().getValue());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getOptAttribute().charValue());
        assertEquals(expected.getContractMultiplier().doubleValue(), actual.getContractMultiplier().doubleValue(), 0.001);
        assertEquals(expected.getCouponRate().doubleValue(), actual.getCouponRate().doubleValue(), 0.001);
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
        assertEquals(expected.getTotalVolumeTraded().doubleValue(), actual.getTotalVolumeTraded().doubleValue(), 0.001);
        // MDFullGroup
        assertEquals(expected.getNoMDEntries(), actual.getNoMDEntries());
        MDFullGroup42TestData.getInstance().check(expected.getMdFullGroups()[0], actual.getMdFullGroups()[0]);
        MDFullGroup42TestData.getInstance().check(expected.getMdFullGroups()[1], actual.getMdFullGroups()[1]);
    }
}
