/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsg50SP1TestData.java
 *
 * $Id: OrderMassCancelReportMsg50SP2TestData.java,v 1.1 2011-05-07 06:58:53 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.data;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderMassCancelReportMsg;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.TargetParties50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingInstrument50SP2TestData;
import net.hades.fix.message.group.impl.v43.AffectedOrdGroup43TestData;
import net.hades.fix.message.group.impl.v50sp1.NotAffectedOrdGroup50SP1TestData;
import net.hades.fix.message.type.MassCancelRejectReason;
import net.hades.fix.message.type.MassCancelRequestType;
import net.hades.fix.message.type.MassCancelResponse;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for OrderMassCancelReportMsg50SP1 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/05/2011, 12:08:30 PM
 */
public class OrderMassCancelReportMsg50SP2TestData extends MsgTest {

    private static final OrderMassCancelReportMsg50SP2TestData INSTANCE;

    static {
        INSTANCE = new OrderMassCancelReportMsg50SP2TestData();
    }

    public static OrderMassCancelReportMsg50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderMassCancelReportMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setClOrdID("AAA564567");
        msg.setSecondaryClOrdID("BBB363744");
        msg.setOrderID("ORD_778866");
        msg.setMassActionReportID("REP_666777");
        msg.setSecondaryOrderID("SECORD_772211");
        msg.setMassCancelRequestType(MassCancelRequestType.CancelAllOrders);
        msg.setMassCancelResponse(MassCancelResponse.CancelAllOrders);
        msg.setMassCancelRejectReason(MassCancelRejectReason.MassCancelNotSupported);
        msg.setTotalAffectedOrders(25);

        msg.setNoAffectedOrders(2);
        AffectedOrdGroup43TestData.getInstance().populate1(msg.getAffectedOrdGroups()[0]);
        AffectedOrdGroup43TestData.getInstance().populate2(msg.getAffectedOrdGroups()[1]);
        
        msg.setNoNotAffectedOrders(2);
        NotAffectedOrdGroup50SP1TestData.getInstance().populate1(msg.getNotAffectedOrdGroups()[0]);
        NotAffectedOrdGroup50SP1TestData.getInstance().populate2(msg.getNotAffectedOrdGroups()[1]);
        
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
        
        msg.setParties();
        Parties50SP2TestData.getInstance().populate(msg.getParties());
        
        msg.setTargetParties();
        TargetParties50SP2TestData.getInstance().populate(msg.getTargetParties());

        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        
        msg.setUnderlyingInstrument();
        UnderlyingInstrument50SP2TestData.getInstance().populate1(msg.getUnderlyingInstrument());

        msg.setMarketID("Commodities");
        msg.setMarketSegmentID("seg 1");
        msg.setSide(Side.Buy);
        cal.set(2010, 3, 14, 15, 18, 32);
        msg.setTransactTime(cal.getTime());
        msg.setText("text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 32, (byte) 43, (byte) 95,
            (byte) 177, (byte) 198, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(OrderMassCancelReportMsg expected, OrderMassCancelReportMsg actual) throws Exception {
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getSecondaryClOrdID(), actual.getSecondaryClOrdID());
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getMassActionReportID(), actual.getMassActionReportID());
        assertEquals(expected.getSecondaryOrderID(), actual.getSecondaryOrderID());
        assertEquals(expected.getMassCancelRequestType(), actual.getMassCancelRequestType());
        assertEquals(expected.getMassCancelResponse(), actual.getMassCancelResponse());
        assertEquals(expected.getMassCancelRejectReason(), actual.getMassCancelRejectReason());
        assertEquals(expected.getTotalAffectedOrders(), actual.getTotalAffectedOrders());
        
        assertEquals(expected.getNoAffectedOrders(), actual.getNoAffectedOrders());
        AffectedOrdGroup43TestData.getInstance().check(expected.getAffectedOrdGroups()[0], actual.getAffectedOrdGroups()[0]);
        AffectedOrdGroup43TestData.getInstance().check(expected.getAffectedOrdGroups()[1], actual.getAffectedOrdGroups()[1]);
        
        assertEquals(expected.getNoNotAffectedOrders(), actual.getNoNotAffectedOrders());
        NotAffectedOrdGroup50SP1TestData.getInstance().check(expected.getNotAffectedOrdGroups()[0], actual.getNotAffectedOrdGroups()[0]);
        NotAffectedOrdGroup50SP1TestData.getInstance().check(expected.getNotAffectedOrdGroups()[1], actual.getNotAffectedOrdGroups()[1]);

        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        
        Parties50SP2TestData.getInstance().check(expected.getParties(), actual.getParties());
               
        TargetParties50SP2TestData.getInstance().check(expected.getTargetParties(), actual.getTargetParties());

        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        UnderlyingInstrument50SP2TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getMarketID(), actual.getMarketID());
        assertEquals(expected.getMarketSegmentID(), actual.getMarketSegmentID());
        assertEquals(expected.getSide(), actual.getSide());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
