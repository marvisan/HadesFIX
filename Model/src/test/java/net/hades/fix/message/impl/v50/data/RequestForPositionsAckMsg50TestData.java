/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RequestForPositionsAckMsg50TestData.java
 *
 * $Id: RequestForPositionsAckMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RequestForPositionsAckMsg;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.PosReqResult;
import net.hades.fix.message.type.PosReqStatus;
import net.hades.fix.message.type.PosReqType;
import net.hades.fix.message.type.ResponseTransportType;
import net.hades.fix.message.type.SettlSessID;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for RequestForPositionsAckMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/12/2011, 12:08:30 PM
 */
public class RequestForPositionsAckMsg50TestData extends MsgTest {

    private static final RequestForPositionsAckMsg50TestData INSTANCE;

    static {
        INSTANCE = new RequestForPositionsAckMsg50TestData();
    }

    public static RequestForPositionsAckMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(RequestForPositionsAckMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setPosMaintRptID("POS_MAINT_6666");
        msg.setPosReqID("POS_REQ_2222");
        msg.setTotalNumPosReports(2);
        msg.setUnsolicitedIndicator(Boolean.TRUE);
        msg.setPosReqResult(PosReqResult.NotAuthorized.getValue());
        msg.setPosReqStatus(PosReqStatus.Rejected);
        msg.setPosReqType(PosReqType.Positions);
        msg.setMatchStatus(MatchStatus.Uncompared);
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setSettlSessID(SettlSessID.Intraday.getValue());
        msg.setSettlSessSubID("SETT_SESS_SUB_1111");
        msg.setSettlCurrency(Currency.AustralianDollar);

        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
       
        msg.setAccount("72634637632");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.FloorTrader);
       
        msg.setInstrument();
        Instrument50TestData.getInstance().populate(msg.getInstrument());

        msg.setCurrency(Currency.UnitedStatesDollar);
        
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setResponseTransportType(ResponseTransportType.Inband);
        msg.setResponseDestination("To me");
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(RequestForPositionsAckMsg expected, RequestForPositionsAckMsg actual) throws Exception {
        assertEquals(expected.getPosMaintRptID(), actual.getPosMaintRptID());
        assertEquals(expected.getPosReqID(), actual.getPosReqID());
        assertEquals(expected.getTotalNumPosReports(), actual.getTotalNumPosReports());
        assertEquals(expected.getUnsolicitedIndicator(), actual.getUnsolicitedIndicator());
        assertEquals(expected.getPosReqResult(), actual.getPosReqResult());
        assertEquals(expected.getPosReqStatus(), actual.getPosReqStatus());
        assertEquals(expected.getPosReqType(), actual.getPosReqType());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
                   
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());
               
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getResponseTransportType(), actual.getResponseTransportType());
        assertEquals(expected.getResponseDestination(), actual.getResponseDestination());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
