/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestGroup43TestData.java
 *
 * $Id: RFQRequestGroup43TestData.java,v 1.2 2009-11-21 09:57:16 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.group.RFQRequestGroup;
import net.hades.fix.message.type.QuoteRequestType;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for RFQRequestGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class RFQRequestGroup43TestData extends MsgTest {

    private static final RFQRequestGroup43TestData INSTANCE;

    static {
        INSTANCE = new RFQRequestGroup43TestData();
    }

    public static RFQRequestGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.RFQRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 1.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 1);
        msg.setInt(quickfix.field.QuoteType.FIELD, 1);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637478466");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "A546464645");
    }

    public void populate2(quickfix.fix43.RFQRequest.NoRelatedSym msg) throws Exception {
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 2.456);
        msg.setInt(quickfix.field.QuoteRequestType.FIELD, 2);
        msg.setInt(quickfix.field.QuoteType.FIELD, 1);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637767888");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "A546464999");
    }

    public void populate1(RFQRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setPrevClosePx(new Double(1.456));
        msg.setQuoteRequestType(QuoteRequestType.Automatic);
        msg.setQuoteType(QuoteType.Indicative);
        msg.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Closing.getValue());
    }

    public void populate2(RFQRequestGroup msg) throws UnsupportedEncodingException {
        // Instrument
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setPrevClosePx(new Double(1.356));
        msg.setQuoteRequestType(QuoteRequestType.Manual);
        msg.setQuoteType(QuoteType.Counter);
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.ContinuousTrading.getValue());
    }

    public void check(RFQRequestGroup expected, quickfix.fix43.RFQRequest.NoRelatedSym actual) throws Exception {
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getDouble(quickfix.field.PrevClosePx.FIELD), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getInt(quickfix.field.QuoteRequestType.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
    }

    public void check(quickfix.fix43.RFQRequest.NoRelatedSym expected, RFQRequestGroup actual) throws Exception {
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        assertEquals(expected.getDouble(quickfix.field.PrevClosePx.FIELD), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.QuoteRequestType.FIELD), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
    }

    public void check(RFQRequestGroup expected, RFQRequestGroup actual) throws Exception {
        // Instrument check
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getQuoteRequestType().getValue(), actual.getQuoteRequestType().getValue());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
    }
}
