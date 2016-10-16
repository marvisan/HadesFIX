/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListRequestMsg50SP1TestData.java
 *
 * $Id: DerivativeSecurityListRequestMsg50SP1TestData.java,v 1.2 2011-10-29 09:42:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.DerivativeSecurityListRequestMsg;
import net.hades.fix.message.comp.impl.v50sp1.DerivativeInstrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityListRequestType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for DerivativeSecurityListRequestMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DerivativeSecurityListRequestMsg50SP1TestData extends MsgTest {

    private static final DerivativeSecurityListRequestMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new DerivativeSecurityListRequestMsg50SP1TestData();
    }

    public static DerivativeSecurityListRequestMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeSecurityListRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityListRequestType(SecurityListRequestType.Symbol);
        msg.setMarketID("CBOT");
        msg.setMarketSegmentID("Commodities");

        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstrument());
        
        msg.setDerivativeInstrument();
        DerivativeInstrument50SP1TestData.getInstance().populate(msg.getDerivativeInstrument());
       
        msg.setSecuritySubType("1");
        msg.setCurrency(Currency.AustralianDollar);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(DerivativeSecurityListRequestMsg expected, DerivativeSecurityListRequestMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityListRequestType(), actual.getSecurityListRequestType());
        assertEquals(expected.getMarketID(), actual.getMarketID());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());

        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
        DerivativeInstrument50SP1TestData.getInstance().check(expected.getDerivativeInstrument(), actual.getDerivativeInstrument());
        
        assertEquals(expected.getSecuritySubType(), actual.getSecuritySubType());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
    }
}
