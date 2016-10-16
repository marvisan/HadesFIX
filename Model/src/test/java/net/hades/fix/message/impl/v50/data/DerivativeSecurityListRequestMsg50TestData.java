/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListRequestMsg50TestData.java
 *
 * $Id: DerivativeSecurityListRequestMsg50TestData.java,v 1.2 2011-10-29 09:42:07 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.DerivativeSecurityListRequestMsg;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityListRequestType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for DerivativeSecurityListRequestMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class DerivativeSecurityListRequestMsg50TestData extends MsgTest {

    private static final DerivativeSecurityListRequestMsg50TestData INSTANCE;

    static {
        INSTANCE = new DerivativeSecurityListRequestMsg50TestData();
    }

    public static DerivativeSecurityListRequestMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeSecurityListRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityListRequestType(SecurityListRequestType.Symbol);

        msg.setUnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstrument());
       
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

        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());
        
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
