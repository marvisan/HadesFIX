/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralRequestMsg44TestData.java
 *
 * $Id: CollateralRequestMsg44TestData.java,v 1.2 2011-10-29 09:42:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CollateralRequestMsg;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.group.impl.v44.ExecCollGroup44TestData;
import net.hades.fix.message.group.impl.v44.MiscFeeGroup44TestData;
import net.hades.fix.message.group.impl.v44.TrdCollGroup44TestData;
import net.hades.fix.message.group.impl.v44.TrdRegTimestamps44TestData;
import net.hades.fix.message.group.impl.v44.UndInstrmtCollGroup44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.CollAsgnReason;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SettlSessID;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for CollateralRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/12/2011, 12:08:30 PM
 */
public class CollateralRequestMsg44TestData extends MsgTest {

    private static final CollateralRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new CollateralRequestMsg44TestData();
    }

    public static CollateralRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(CollateralRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        
        msg.setCollReqID("COLL_REQ_4444");
        msg.setCollAsgnReason(CollAsgnReason.MarginExcess);
        cal.set(2011, 3, 14, 12, 14, 15);
        msg.setTransactTime(cal.getTime());
        cal.set(2011, 2, 23, 12, 11, 15);
        msg.setExpireTime(cal.getTime());
        
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
        
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
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());

        cal.set(2011, 6, 14, 21, 14, 11);
        msg.setSettlDate(cal.getTime());
        msg.setQuantity(34.66d);
        msg.setQtyType(QtyType.Units);
        msg.setCurrency(Currency.UnitedStatesDollar);
                
        msg.setNoLegs(new Integer(2));
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setNoUnderlyings(new Integer(2));
        UndInstrmtCollGroup44TestData.getInstance().populate1(msg.getUndInstrmtCollGroups()[0]);
        UndInstrmtCollGroup44TestData.getInstance().populate2(msg.getUndInstrmtCollGroups()[1]);
        
        msg.setMarginExcess(55.66d);
        msg.setTotalNetValue(66.99d);
        msg.setCashOutstanding(23.12d);
        
        msg.setNoTrdRegTimestamps(2);
        TrdRegTimestamps44TestData.getInstance().populate1(msg.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps44TestData.getInstance().populate2(msg.getTrdRegTimestampsGroups()[1]);
        
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
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        
        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());

        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
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

    public void check(CollateralRequestMsg expected, CollateralRequestMsg actual) throws Exception {
        assertEquals(expected.getCollReqID(), actual.getCollReqID());
        assertEquals(expected.getCollAsgnReason(), actual.getCollAsgnReason());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertUTCTimestampEquals(expected.getExpireTime(), actual.getExpireTime(), false);
                
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

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
                          
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
                
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getQtyType(), actual.getQtyType());
        assertEquals(expected.getCurrency(), actual.getCurrency());
                      
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UndInstrmtCollGroup44TestData.getInstance().check(expected.getUndInstrmtCollGroups()[0], actual.getUndInstrmtCollGroups()[0]);
        UndInstrmtCollGroup44TestData.getInstance().check(expected.getUndInstrmtCollGroups()[1], actual.getUndInstrmtCollGroups()[1]);

        assertEquals(expected.getMarginExcess(), actual.getMarginExcess());
        assertEquals(expected.getTotalNetValue(), actual.getTotalNetValue());
        assertEquals(expected.getCashOutstanding(), actual.getCashOutstanding());
               
        assertEquals(expected.getNoTrdRegTimestamps(), actual.getNoTrdRegTimestamps());
        TrdRegTimestamps44TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[0], actual.getTrdRegTimestampsGroups()[0]);
        TrdRegTimestamps44TestData.getInstance().check(expected.getTrdRegTimestampsGroups()[1], actual.getTrdRegTimestampsGroups()[1]);

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
              
        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());

        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertEquals(expected.getSettlSessID(), actual.getSettlSessID());
        assertEquals(expected.getSettlSessSubID(), actual.getSettlSessSubID());
        assertDateEquals(expected.getClearingBusinessDate(), actual.getClearingBusinessDate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
