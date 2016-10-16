/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionRequestMsg43TestData.java
 *
 * $Id: SecurityDefinitionRequestMsg43TestData.java,v 1.2 2011-10-29 09:42:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityDefinitionRequestMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.InstrumentLeg43TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityRequestType;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityDefinitionRequestMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityDefinitionRequestMsg43TestData extends MsgTest {

    private static final SecurityDefinitionRequestMsg43TestData INSTANCE;

    static {
        INSTANCE = new SecurityDefinitionRequestMsg43TestData();
    }

    public static SecurityDefinitionRequestMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityDefinitionRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate42HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityRequestType(SecurityRequestType.MarketID);

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());
       
        msg.setCurrency(Currency.AustralianDollar);
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());

        msg.setNoLegs(2);
        InstrumentLeg43TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg43TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
    }

    public void check(SecurityDefinitionRequestMsg expected, SecurityDefinitionRequestMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityRequestType(), actual.getSecurityRequestType());

        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg43TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg43TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
    }
}
