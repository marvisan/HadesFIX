/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionReportMsg44TestData.java
 *
 * $Id: PositionReportMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.PositionReportMsg;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.group.impl.v44.PosAmtGroup44TestData;
import net.hades.fix.message.group.impl.v44.PosUndInstrmtGroup44TestData;
import net.hades.fix.message.group.impl.v44.PositionQtyGroup44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PosReqResult;
import net.hades.fix.message.type.PosReqType;
import net.hades.fix.message.type.RegistStatus;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.SettlSessID;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test utility for PositionReportMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/12/2011, 12:08:30 PM
 */
public class PositionReportMsg44TestData extends MsgTest {

    private static final PositionReportMsg44TestData INSTANCE;

    static {
        INSTANCE = new PositionReportMsg44TestData();
    }

    public static PositionReportMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(PositionReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
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

        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
        
        msg.setAccount("72634637632");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.FloorTrader);
       
        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setSettlPrice(44.66d);
        msg.setSettlPriceType(SettlPriceType.Theoretical);
        msg.setPriorSettlPrice(42.66d);
        
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setNoUnderlyings(new Integer(2));
        PosUndInstrmtGroup44TestData.getInstance().populate1(msg.getPosUndInstrmtGroups()[0]);
        PosUndInstrmtGroup44TestData.getInstance().populate2(msg.getPosUndInstrmtGroups()[1]);

        msg.setNoPositions(2);
        PositionQtyGroup44TestData.getInstance().populate1(msg.getPositionQtyGroups()[0]);
        PositionQtyGroup44TestData.getInstance().populate2(msg.getPositionQtyGroups()[1]);
        
        msg.setNoPosAmt(2);
        PosAmtGroup44TestData.getInstance().populate1(msg.getPosAmtGroups()[0]);
        PosAmtGroup44TestData.getInstance().populate2(msg.getPosAmtGroups()[1]);
        
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
        
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
                   
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSettlPrice(), actual.getSettlPrice());
        assertEquals(expected.getSettlPriceType(), actual.getSettlPriceType());
        assertEquals(expected.getPriorSettlPrice(), actual.getPriorSettlPrice());
               
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        PosUndInstrmtGroup44TestData.getInstance().check(expected.getPosUndInstrmtGroups()[0], actual.getPosUndInstrmtGroups()[0]);
        PosUndInstrmtGroup44TestData.getInstance().check(expected.getPosUndInstrmtGroups()[1], actual.getPosUndInstrmtGroups()[1]);

        assertEquals(expected.getNoPositions(), actual.getNoPositions());
        PositionQtyGroup44TestData.getInstance().check(expected.getPositionQtyGroups()[0], actual.getPositionQtyGroups()[0]);
        PositionQtyGroup44TestData.getInstance().check(expected.getPositionQtyGroups()[1], actual.getPositionQtyGroups()[1]);
        
        assertEquals(expected.getNoPosAmt(), actual.getNoPosAmt());
        PosAmtGroup44TestData.getInstance().check(expected.getPosAmtGroups()[0], actual.getPosAmtGroups()[0]);
        PosAmtGroup44TestData.getInstance().check(expected.getPosAmtGroups()[1], actual.getPosAmtGroups()[1]);

        assertEquals(expected.getRegistStatus(), actual.getRegistStatus());
        assertDateEquals(expected.getDeliveryDate(), actual.getDeliveryDate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
