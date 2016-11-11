/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionReportMsg50TestData.java
 *
 * $Id: PositionReportMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.PositionReportMsg;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.group.impl.v50.PosAmtGroup50TestData;
import net.hades.fix.message.group.impl.v50.PosUndInstrmtGroup50TestData;
import net.hades.fix.message.group.impl.v50.PositionQtyGroup50TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.PosReqResult;
import net.hades.fix.message.type.PosReqType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.RegistStatus;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.SettlSessID;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for PositionReportMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/12/2011, 12:08:30 PM
 */
public class PositionReportMsg50TestData extends MsgTest {

    private static final PositionReportMsg50TestData INSTANCE;

    static {
        INSTANCE = new PositionReportMsg50TestData();
    }

    public static PositionReportMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(PositionReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setPosMaintRptID("POS_MAINT_3333");
        msg.setPosReqID("POS_REQ_2222");
        msg.setPosReqType(PosReqType.Positions);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTotalNumPosReports(2);
        msg.setUnsolicitedIndicator(Boolean.TRUE);
        msg.setPosReqResult(PosReqResult.NotAuthorized.getValue());
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setSettlSessID(SettlSessID.Intraday.getValue());
        msg.setSettlSessSubID("SETT_SESS_SUB_1111");
        msg.setPriceType(PriceType.Percentage);
        msg.setSettlCurrency(Currency.UnitedStatesDollar);
        msg.setMessageEventSource("DC");

        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        
        msg.setAccount("72634637632");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.FloorTrader);
       
        msg.setInstrument();
        Instrument50TestData.getInstance().populate(msg.getInstrument());

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setSettlPrice(44.66d);
        msg.setSettlPriceType(SettlPriceType.Theoretical);
        msg.setPriorSettlPrice(42.66d);
        msg.setMatchStatus(MatchStatus.Uncompared);
        
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setNoUnderlyings(new Integer(2));
        PosUndInstrmtGroup50TestData.getInstance().populate1(msg.getPosUndInstrmtGroups()[0]);
        PosUndInstrmtGroup50TestData.getInstance().populate2(msg.getPosUndInstrmtGroups()[1]);

        msg.setNoPositions(2);
        PositionQtyGroup50TestData.getInstance().populate1(msg.getPositionQtyGroups()[0]);
        PositionQtyGroup50TestData.getInstance().populate2(msg.getPositionQtyGroups()[1]);
        
        msg.setNoPosAmt(2);
        PosAmtGroup50TestData.getInstance().populate1(msg.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().populate2(msg.getPosAmtGroups()[1]);
        
        msg.setRegistStatus(RegistStatus.Rejected);
        cal.set(2011, 3, 12, 13, 11, 15);
        msg.setDeliveryDate(cal.getTime());
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(PositionReportMsg expected, PositionReportMsg actual) throws Exception {
        assertEquals(expected.getPosMaintRptID(), actual.getPosMaintRptID());
        assertEquals(expected.getPosReqID(), actual.getPosReqID());
        assertEquals(expected.getPosReqType(), actual.getPosReqType());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTotalNumPosReports(), actual.getTotalNumPosReports());
        assertEquals(expected.getUnsolicitedIndicator(), actual.getUnsolicitedIndicator());
        assertEquals(expected.getPosReqResult(), actual.getPosReqResult());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getMessageEventSource(), actual.getMessageEventSource());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
                   
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSettlPrice(), actual.getSettlPrice());
        assertEquals(expected.getSettlPriceType(), actual.getSettlPriceType());
        assertEquals(expected.getPriorSettlPrice(), actual.getPriorSettlPrice());
        assertEquals(expected.getMatchStatus(), actual.getMatchStatus());
               
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        PosUndInstrmtGroup50TestData.getInstance().check(expected.getPosUndInstrmtGroups()[0], actual.getPosUndInstrmtGroups()[0]);
        PosUndInstrmtGroup50TestData.getInstance().check(expected.getPosUndInstrmtGroups()[1], actual.getPosUndInstrmtGroups()[1]);

        assertEquals(expected.getNoPositions(), actual.getNoPositions());
        PositionQtyGroup50TestData.getInstance().check(expected.getPositionQtyGroups()[0], actual.getPositionQtyGroups()[0]);
        PositionQtyGroup50TestData.getInstance().check(expected.getPositionQtyGroups()[1], actual.getPositionQtyGroups()[1]);
        
        assertEquals(expected.getNoPosAmt(), actual.getNoPosAmt());
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[0], actual.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[1], actual.getPosAmtGroups()[1]);

        assertEquals(expected.getRegistStatus(), actual.getRegistStatus());
        assertDateEquals(expected.getDeliveryDate(), actual.getDeliveryDate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
