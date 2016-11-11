/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralResponseMsg50TestData.java
 *
 * $Id: CollateralResponseMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CollateralResponseMsg;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.group.impl.v44.ExecCollGroup44TestData;
import net.hades.fix.message.group.impl.v44.MiscFeeGroup44TestData;
import net.hades.fix.message.group.impl.v44.TrdCollGroup44TestData;
import net.hades.fix.message.group.impl.v50.TrdRegTimestamps50TestData;
import net.hades.fix.message.group.impl.v50.UndInstrmtCollGroup50TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.CollApplType;
import net.hades.fix.message.type.CollAsgnReason;
import net.hades.fix.message.type.CollAsgnRejectReason;
import net.hades.fix.message.type.CollAsgnRespType;
import net.hades.fix.message.type.CollAsgnTransType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.FinancialStatus;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for CollateralResponseMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 19/12/2011, 12:08:30 PM
 */
public class CollateralResponseMsg50TestData extends MsgTest {

    private static final CollateralResponseMsg50TestData INSTANCE;

    static {
        INSTANCE = new CollateralResponseMsg50TestData();
    }

    public static CollateralResponseMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(CollateralResponseMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setCollRespID("COLL_RESP_8888");
        msg.setCollAsgnID("COLL_ASGN_5555");
        msg.setCollReqID("COLL_REQ_4444");
        msg.setCollAsgnReason(CollAsgnReason.MarginExcess);
        msg.setCollAsgnTransType(CollAsgnTransType.Release);
        msg.setCollAsgnRespType(CollAsgnRespType.Accepted);
        msg.setCollAsgnRejectReason(CollAsgnRejectReason.UnknownDeal);
        cal.set(2011, 3, 14, 12, 14, 15);
        msg.setTransactTime(cal.getTime());
        msg.setCollApplType(CollApplType.General);
        msg.setFinancialStatus(String.valueOf(FinancialStatus.Bankrupt.getValue()));
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setClearingBusinessDate(cal.getTime());
        
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());
        
        msg.setAccount("72634637632");
        msg.setAccountType(AccountType.FloorTrader);
        msg.setClOrdID("CLI_ORD_7777");
        msg.setOrderID("ORD_2222");
        msg.setSecondaryOrderID("SEC_ORD_0000");
        msg.setSecondaryClOrdID("SEC_CLI_ORD_8888");
        
        msg.setNoExecs(2);
        ExecCollGroup44TestData.getInstance().populate1(msg.getExecCollGroups()[0]);
        ExecCollGroup44TestData.getInstance().populate2(msg.getExecCollGroups()[1]);
        
        msg.setNoTrades(2);
        TrdCollGroup44TestData.getInstance().populate1(msg.getTrdCollGroups()[0]);
        TrdCollGroup44TestData.getInstance().populate2(msg.getTrdCollGroups()[1]);
               
        msg.setInstrument();
        Instrument50TestData.getInstance().populate(msg.getInstrument());
        
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());

        cal.set(2011, 6, 14, 21, 14, 11);
        msg.setSettlDate(cal.getTime());
        msg.setQuantity(34.66d);
        msg.setQtyType(QtyType.Units);
        msg.setCurrency(Currency.UnitedStatesDollar);
                
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setNoUnderlyings(new Integer(2));
        UndInstrmtCollGroup50TestData.getInstance().populate1(msg.getUndInstrmtCollGroups()[0]);
        UndInstrmtCollGroup50TestData.getInstance().populate2(msg.getUndInstrmtCollGroups()[1]);
        
        msg.setMarginExcess(55.66d);
        msg.setTotalNetValue(66.99d);
        msg.setCashOutstanding(23.12d);
        
        msg.setNoTrdRegTimestamps(2);
        TrdRegTimestamps50TestData.getInstance().populate1(msg.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().populate2(msg.getTrdRegTimestampsGroups()[1]);
        
        msg.setSide(Side.Buy);
                
        msg.setNoMiscFees(2);
        MiscFeeGroup44TestData.getInstance().populate1(msg.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().populate1(msg.getMiscFeeGroups()[1]);

        msg.setPrice(11.44d);
        msg.setPriceType(PriceType.Percentage);
        msg.setAccruedInterestAmt(24.55d);
        msg.setEndAccruedInterestAmt(44.77d);
        msg.setStartCash(25.67d);
        msg.setEndCash(28.99d);
                
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());

        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(CollateralResponseMsg expected, CollateralResponseMsg actual) throws Exception {
        assertEquals(expected.getCollRespID(), actual.getCollRespID());
        assertEquals(expected.getCollAsgnID(), actual.getCollAsgnID());
        assertEquals(expected.getCollReqID(), actual.getCollReqID());
        assertEquals(expected.getCollAsgnReason(), actual.getCollAsgnReason());
        assertEquals(expected.getCollAsgnTransType(), actual.getCollAsgnTransType());
        assertEquals(expected.getCollAsgnRespType(), actual.getCollAsgnRespType());
        assertEquals(expected.getCollAsgnRejectReason(), actual.getCollAsgnRejectReason());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getCollApplType(), actual.getCollApplType());
        assertEquals(expected.getFinancialStatus(), actual.getFinancialStatus());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
                
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAccountType(), actual.getAccountType());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        
        assertEquals(expected.getNoExecs(), actual.getNoExecs());
        ExecCollGroup44TestData.getInstance().check(expected.getExecCollGroups()[0], actual.getExecCollGroups()[0]);
        ExecCollGroup44TestData.getInstance().check(expected.getExecCollGroups()[1], actual.getExecCollGroups()[1]);
        
        assertEquals(expected.getNoTrades(), actual.getNoTrades());
        TrdCollGroup44TestData.getInstance().check(expected.getTrdCollGroups()[0], actual.getTrdCollGroups()[0]);
        TrdCollGroup44TestData.getInstance().check(expected.getTrdCollGroups()[1], actual.getTrdCollGroups()[1]);
                          
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
                
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getQtyType(), actual.getQtyType());
        assertEquals(expected.getCurrency(), actual.getCurrency());
                      
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UndInstrmtCollGroup50TestData.getInstance().check(expected.getUndInstrmtCollGroups()[0], actual.getUndInstrmtCollGroups()[0]);
        UndInstrmtCollGroup50TestData.getInstance().check(expected.getUndInstrmtCollGroups()[1], actual.getUndInstrmtCollGroups()[1]);

        assertEquals(expected.getMarginExcess(), actual.getMarginExcess());
        assertEquals(expected.getTotalNetValue(), actual.getTotalNetValue());
        assertEquals(expected.getCashOutstanding(), actual.getCashOutstanding());
               
        assertEquals(expected.getNoTrdRegTimestamps(), actual.getNoTrdRegTimestamps());
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[0], actual.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps50TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[1], actual.getTrdRegTimestampsGroups()[1]);

        assertEquals(expected.getSide(), actual.getSide());
        
        assertEquals(expected.getNoMiscFees(), actual.getNoMiscFees());
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[0], actual.getMiscFeeGroups()[0]);
        MiscFeeGroup44TestData.getInstance().check(expected.getMiscFeeGroups()[1], actual.getMiscFeeGroups()[1]);

        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getPriceType(), actual.getPriceType());
        assertEquals(expected.getAccruedInterestAmt(), actual.getAccruedInterestAmt());
        assertEquals(expected.getEndAccruedInterestAmt(), actual.getEndAccruedInterestAmt());
        assertEquals(expected.getStartCash(), actual.getStartCash());
        assertEquals(expected.getEndCash(), actual.getEndCash());
              
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
