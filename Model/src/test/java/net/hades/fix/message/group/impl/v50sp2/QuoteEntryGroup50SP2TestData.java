/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryGroup50SP2TestData.java
 *
 * $Id: QuoteEntryGroup50SP2TestData.java,v 1.3 2011-10-29 09:42:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderRestrictions;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for QuoteEntryGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteEntryGroup50SP2TestData extends MsgTest {

    private static final QuoteEntryGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new QuoteEntryGroup50SP2TestData();
    }

    public static QuoteEntryGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(QuoteEntryGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteEntryID("FFF46466");
        // Instrument
        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setBidPx(new Double(23.44));
        msg.setOfferPx(new Double(23.98));
        msg.setBidSize(new Double(100.0));
        msg.setOfferSize(new Double(150));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.44));
        msg.setOfferSpotRate(new Double(12.54));
        msg.setBidForwardPoints(new Double(2.44));
        msg.setOfferForwardPoints(new Double(3.44));
        msg.setMidPx(new Double(222.32));
        msg.setBidYield(new Double(2.34));
        msg.setMidYield(new Double(1.34));
        msg.setOfferYield(new Double(2.67));
        msg.setTransactTime(new Date());
        msg.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Stop);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
        msg.setBidForwardPoints2(new Double(222.9));
        msg.setOfferForwardPoints2(new Double(222.3));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setBookingType(BookingType.RegularBooking);
        msg.setOrderCapacity(OrderCapacity.Proprietary);
        msg.setOrderRestrictions(OrderRestrictions.ActingAsMarketMakerInUnderlying.getValue());
    }

    public void populate2(QuoteEntryGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteEntryID("GGG878w7847");
        // Instrument
        msg.setInstrument();
        Instrument50SP2TestData.getInstance().populate(msg.getInstrument());
        // InstrumentLeg
        msg.setNoLegs(new Integer(2));
        InstrumentLeg50SP2TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setBidPx(new Double(23.55));
        msg.setOfferPx(new Double(23.55));
        msg.setBidSize(new Double(100.5));
        msg.setOfferSize(new Double(444));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.55));
        msg.setOfferSpotRate(new Double(12.55));
        msg.setBidForwardPoints(new Double(2.55));
        msg.setOfferForwardPoints(new Double(3.55));
        msg.setMidPx(new Double(222.44));
        msg.setBidYield(new Double(2.44));
        msg.setMidYield(new Double(1.44));
        msg.setOfferYield(new Double(2.44));
        msg.setTransactTime(new Date());
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Market);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.55));
        msg.setBidForwardPoints2(new Double(222.7));
        msg.setOfferForwardPoints2(new Double(222.7));
        msg.setCurrency(Currency.AustralianDollar);
        msg.setBookingType(BookingType.TotalReturnSwap);
        msg.setOrderCapacity(OrderCapacity.Individual);
        msg.setOrderRestrictions(OrderRestrictions.Algorithmic.getValue());
    }


    public void check(QuoteEntryGroup expected, QuoteEntryGroup actual) throws Exception {
        assertEquals(expected.getQuoteEntryID(), actual.getQuoteEntryID());
        // Instrument
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // InstrumentLeg
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[0],
            actual.getInstrumentLegs()[0]);
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLegs()[1],
            actual.getInstrumentLegs()[1]);

        assertEquals(expected.getBidPx().doubleValue(), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getOfferSize().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getOfferYield().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getBookingType(), actual.getBookingType());
        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
    }
}
