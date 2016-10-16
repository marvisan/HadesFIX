/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AssignmentReportMsg50TestData.java
 *
 * $Id: AssignmentReportMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AssignmentReportMsg;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.group.impl.v50.PosAmtGroup50TestData;
import net.hades.fix.message.group.impl.v50.PositionQtyGroup50TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AssignmentMethod;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseMethod;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.SettlSessID;

/**
 * Test utility for AssignmentReportMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 16/12/2011, 12:08:30 PM
 */
public class AssignmentReportMsg50TestData extends MsgTest {

    private static final AssignmentReportMsg50TestData INSTANCE;

    static {
        INSTANCE = new AssignmentReportMsg50TestData();
    }

    public static AssignmentReportMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(AssignmentReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setAsgnRptID("ASGN_RPT_4444");
        //msg.setPosReqID("POS_REQ_2222");
        msg.setTotNumAssignmentReports(4);
        msg.setLastRptRequested(Boolean.TRUE);
        
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
                
        msg.setAccount("72634637632");
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

        msg.setNoPositions(2);
        PositionQtyGroup50TestData.getInstance().populate1(msg.getPositionQtyGroups()[0]);
        PositionQtyGroup50TestData.getInstance().populate2(msg.getPositionQtyGroups()[1]);
        
        msg.setNoPosAmt(2);
        PosAmtGroup50TestData.getInstance().populate1(msg.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().populate2(msg.getPosAmtGroups()[1]);

        msg.setThresholdAmount(33.77d);
        msg.setSettlPrice(44.66d);
        msg.setSettlPriceType(SettlPriceType.Theoretical);
        msg.setUnderlyingSettlPrice(55.77d);
        msg.setPriorSettlPrice(42.66d);
        cal.set(2011, 3, 3, 13, 14, 15);
        msg.setExpireDate(cal.getTime());
        msg.setAssignmentMethod(AssignmentMethod.Random);
        msg.setAssignmentUnit(4.55d);
        msg.setOpenInterest(45333d);
        msg.setExerciseMethod(ExerciseMethod.Automatic);
        msg.setSettlSessID(SettlSessID.Intraday.getValue());
        msg.setSettlSessSubID("SETT_SESS_SUB_1111");
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(AssignmentReportMsg expected, AssignmentReportMsg actual) throws Exception {
        assertEquals(expected.getAsgnRptID(), actual.getAsgnRptID());
        //assertEquals(expected.getPosReqID(), actual.getPosReqID());
        assertEquals(expected.getTotNumAssignmentReports(), actual.getTotNumAssignmentReports());
        assertEquals(expected.getLastRptRequested(), actual.getLastRptRequested());
               
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType(), actual.getAccountType());
                         
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());
               
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getNoPositions(), actual.getNoPositions());
        PositionQtyGroup50TestData.getInstance().check(expected.getPositionQtyGroups()[0], actual.getPositionQtyGroups()[0]);
        PositionQtyGroup50TestData.getInstance().check(expected.getPositionQtyGroups()[1], actual.getPositionQtyGroups()[1]);
        
        assertEquals(expected.getNoPosAmt(), actual.getNoPosAmt());
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[0], actual.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[1], actual.getPosAmtGroups()[1]);

        assertEquals(expected.getThresholdAmount(), actual.getThresholdAmount());
        assertEquals(expected.getSettlPrice(), actual.getSettlPrice());
        assertEquals(expected.getSettlPriceType(), actual.getSettlPriceType());
        assertEquals(expected.getUnderlyingSettlPrice(), actual.getUnderlyingSettlPrice());
        assertEquals(expected.getPriorSettlPrice(), actual.getPriorSettlPrice());
        assertDateEquals(expected.getExpireDate(), actual.getExpireDate());
        assertEquals(expected.getAssignmentMethod(), actual.getAssignmentMethod());
        assertEquals(expected.getAssignmentUnit(), actual.getAssignmentUnit());
        assertEquals(expected.getOpenInterest(), actual.getOpenInterest());
        assertEquals(expected.getExerciseMethod(), actual.getExerciseMethod());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
