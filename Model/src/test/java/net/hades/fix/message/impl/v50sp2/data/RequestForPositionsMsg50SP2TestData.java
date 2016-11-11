/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RequestForPositionsMsg50SP2TestData.java
 *
 * $Id: RequestForPositionsMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RequestForPositionsMsg;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.PosReqType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SettlSessID;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for RequestForPositionsMsg50SP2 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/12/2011, 12:08:30 PM
 */
public class RequestForPositionsMsg50SP2TestData extends MsgTest {

    private static final RequestForPositionsMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new RequestForPositionsMsg50SP2TestData();
    }

    public static RequestForPositionsMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RequestForPositionsMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setPosReqID("POS_REQ_2222");
        msg.setPosReqType(PosReqType.Positions);
        msg.setMatchStatus(MatchStatus.Uncompared);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setSettlCurrency(Currency.Euro);

        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
       
        msg.setAccount("72634637632");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.FloorTrader);
       
        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());

        msg.setCurrency(Currency.UnitedStatesDollar);
        
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setSettlSessID(SettlSessID.Intraday.getValue());
        msg.setSettlSessSubID("SETT_SESS_SUB_1111");
 
        msg.setNoTradingSessions(2);
        TradingSessionGroup43TestData.getInstance().populate1(msg.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(msg.getTradingSessionGroups()[1]);
        
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        msg.setResponseTransportType(ResponseTransportType.Inband);
        msg.setResponseDestination("To me");
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(RequestForPositionsMsg expected, RequestForPositionsMsg actual) throws Exception {
        assertEquals(expected.getPosReqID(), actual.getPosReqID());
        assertEquals(expected.getPosReqType(), actual.getPosReqType());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
                   
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());
               
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());
        
        assertEquals(expected.getNoTradingSessions(), actual.getNoTradingSessions());
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);
        
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getResponseTransportType(), actual.getResponseTransportType());
        assertEquals(expected.getResponseDestination(), actual.getResponseDestination());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
