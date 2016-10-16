/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionMaintenanceReportMsg50SP1TestData.java
 *
 * $Id: PositionMaintenanceReportMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.PositionMaintenanceReportMsg;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43TestData;
import net.hades.fix.message.group.impl.v50.PosAmtGroup50TestData;
import net.hades.fix.message.group.impl.v50.PositionQtyGroup50TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AdjustmentType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PosMaintAction;
import net.hades.fix.message.type.PosMaintResult;
import net.hades.fix.message.type.PosMaintStatus;
import net.hades.fix.message.type.PosTransType;
import net.hades.fix.message.type.SettlSessID;

/**
 * Test utility for PositionMaintenanceReportMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 08/12/2011, 12:08:30 PM
 */
public class PositionMaintenanceReportMsg50SP1TestData extends MsgTest {

    private static final PositionMaintenanceReportMsg50SP1TestData INSTANCE;

    static {
        INSTANCE = new PositionMaintenanceReportMsg50SP1TestData();
    }

    public static PositionMaintenanceReportMsg50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(PositionMaintenanceReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setPosMaintRptID("POS_MNT_REP_0000");
        msg.setPosTransType(PosTransType.Exercise);
        msg.setPosReqID("POS_REQ_2222");
        msg.setPosMaintAction(PosMaintAction.Cancel);
        msg.setOrigPosReqRefID("ORIG_POS_REQ_6666");
        msg.setPosMaintStatus(PosMaintStatus.Completed);
        msg.setPosMaintResult(PosMaintResult.Other.getValue());
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setClearingBusinessDate(cal.getTime());
        msg.setSettlSessID(SettlSessID.Intraday.getValue());
        msg.setSettlSessSubID("SETT_SESS_SUB_1111");

        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        
        msg.setAccount("72634637632");
        msg.setAcctIDSource(AcctIDSource.SID);
        msg.setAccountType(AccountType.FloorTrader);
        msg.setPosMaintRptRefID("POS_MNTC_6666");
       
        msg.setInstrument();
        Instrument50SP1TestData.getInstance().populate(msg.getInstrument());

        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setSettlCurrency(Currency.NewZealandDollar);
        msg.setContraryInstructionIndicator(Boolean.TRUE);
        msg.setPriorSpreadIndicator(Boolean.FALSE);
        
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP1TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoTradingSessions(2);
        TradingSessionGroup43TestData.getInstance().populate1(msg.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().populate2(msg.getTradingSessionGroups()[1]);
        
        cal.set(2010, 3, 14, 33, 22, 15);
        msg.setTransactTime(cal.getTime());
        
        msg.setNoPositions(2);
        PositionQtyGroup50TestData.getInstance().populate1(msg.getPositionQtyGroups()[0]);
        PositionQtyGroup50TestData.getInstance().populate2(msg.getPositionQtyGroups()[1]);
        
        msg.setNoPosAmt(2);
        PosAmtGroup50TestData.getInstance().populate1(msg.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().populate2(msg.getPosAmtGroups()[1]);
        
        msg.setAdjustmentType(AdjustmentType.DeltaPlus);
        msg.setThresholdAmount(36.88d);
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(PositionMaintenanceReportMsg expected, PositionMaintenanceReportMsg actual) throws Exception {
        assertEquals(expected.getPosMaintRptID(), actual.getPosMaintRptID());
        assertEquals(expected.getPosTransType(), actual.getPosTransType());
        assertEquals(expected.getPosReqID(), actual.getPosReqID());
        assertEquals(expected.getPosMaintAction(), actual.getPosMaintAction());
        assertEquals(expected.getOrigPosReqRefID(), actual.getOrigPosReqRefID());
        assertEquals(expected.getPosMaintStatus(), actual.getPosMaintStatus());
        assertEquals(expected.getPosMaintResult(), actual.getPosMaintResult());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());
        
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource(), actual.getAcctIDSource());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getPosMaintRptRefID(), actual.getPosMaintRptRefID());
                   
        Instrument50SP1TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSettlCurrency(), actual.getSettlCurrency());
        assertEquals(expected.getContraryInstructionIndicator(), actual.getContraryInstructionIndicator());
        assertEquals(expected.getPriorSpreadIndicator(), actual.getPriorSpreadIndicator());
        
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);
        
        assertEquals(expected.getNoTradingSessions(), actual.getNoTradingSessions());
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[0], actual.getTradingSessionGroups()[0]);
        TradingSessionGroup43TestData.getInstance().check(expected.getTradingSessionGroups()[1], actual.getTradingSessionGroups()[1]);
        
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        
        assertEquals(expected.getNoPositions(), actual.getNoPositions());
        PositionQtyGroup50TestData.getInstance().check(expected.getPositionQtyGroups()[0], actual.getPositionQtyGroups()[0]);
        PositionQtyGroup50TestData.getInstance().check(expected.getPositionQtyGroups()[1], actual.getPositionQtyGroups()[1]);
        
        assertEquals(expected.getNoPosAmt(), actual.getNoPosAmt());
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[0], actual.getPosAmtGroups()[0]);
        PosAmtGroup50TestData.getInstance().check(expected.getPosAmtGroups()[1], actual.getPosAmtGroups()[1]);

        assertEquals(expected.getAdjustmentType(), actual.getAdjustmentType());
        assertEquals(expected.getThresholdAmount(), actual.getThresholdAmount());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
