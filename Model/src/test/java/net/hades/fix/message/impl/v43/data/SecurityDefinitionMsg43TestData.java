/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionMsg43TestData.java
 *
 * $Id: SecurityDefinitionMsg43TestData.java,v 1.2 2011-10-29 09:42:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityDefinitionMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.comp.impl.v43.InstrumentLeg43TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SecurityResponseType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityDefinitionMsg43 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityDefinitionMsg43TestData extends MsgTest {

    private static final SecurityDefinitionMsg43TestData INSTANCE;

    static {
        INSTANCE = new SecurityDefinitionMsg43TestData();
    }

    public static SecurityDefinitionMsg43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityDefinitionMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate43HeaderAll(msg);
        msg.setSecurityReqID("REQ_11111");
        msg.setSecurityResponseID("RSP888777");
        msg.setSecurityResponseType(SecurityResponseType.AcceptSecProposalAsIs);

        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());
       
        msg.setCurrency(Currency.AustralianDollar);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);

        msg.setNoLegs(2);
        InstrumentLeg43TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg43TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setRoundLot(45.66);
        msg.setMinTradeVol(44.8);
    }

    public void check(SecurityDefinitionMsg expected, SecurityDefinitionMsg actual) throws Exception {
        assertEquals(expected.getSecurityReqID(), actual.getSecurityReqID());
        assertEquals(expected.getSecurityResponseID(), actual.getSecurityResponseID());
        assertEquals(expected.getSecurityResponseType(), actual.getSecurityResponseType());

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

        assertEquals(expected.getRoundLot(), actual.getRoundLot());
        assertEquals(expected.getMinTradeVol(), actual.getMinTradeVol());
    }
}
