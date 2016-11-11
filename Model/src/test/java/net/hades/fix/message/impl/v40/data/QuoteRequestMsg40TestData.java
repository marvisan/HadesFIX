/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg40TestData.java
 *
 * $Id: QuoteRequestMsg40TestData.java,v 1.1 2009-07-06 03:19:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.type.Side;

/**
 * Test utility for QuoteRequestMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 11:57:03 AM
 */
public class QuoteRequestMsg40TestData extends MsgTest {

    private static final QuoteRequestMsg40TestData INSTANCE;

    static {
        INSTANCE = new QuoteRequestMsg40TestData();
    }

    public static QuoteRequestMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix40.QuoteRequest msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.Symbol.FIELD, "SUN");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "SUN");
        msg.setString(quickfix.field.SecurityID.FIELD, "SUN");
        msg.setString(quickfix.field.IDSource.FIELD, "SUN Microsystems");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "SUN options");
        msg.setString(quickfix.field.Issuer.FIELD, "SUN & Co");
        msg.setDouble(quickfix.field.PrevClosePx.FIELD, 23.444);
        msg.setChar(quickfix.field.Side.FIELD, Side.CrossShort.getValue());
        msg.setDouble(quickfix.field.OrderQty.FIELD, 32.0);

    }

    public void populate(QuoteRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setQuoteReqID("X162773883");
        msg.setSymbol("SUN");
        msg.setSymbolSfx("SUN");
        msg.setSecurityID("SUN");
        msg.setSecurityIDSource("SUN Microsystems");
        msg.setIssuer("SUN Co");
        msg.setSecurityDesc("SUN options");
        msg.setPrevClosePx(new Double(23.444));
        msg.setSide(Side.AsDefined);
        msg.setOrderQty(new Double(12.0));
    }

    public void check(quickfix.fix40.QuoteRequest expected, QuoteRequestMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.IDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getDouble(quickfix.field.PrevClosePx.FIELD), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getDouble(quickfix.field.OrderQty.FIELD), actual.getOrderQty().doubleValue(), 0.001);

    }

    public void check(QuoteRequestMsg expected, quickfix.fix40.QuoteRequest actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.IDSource.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getDouble(quickfix.field.PrevClosePx.FIELD), 0.001);
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getOrderQty().doubleValue(), actual.getDouble(quickfix.field.OrderQty.FIELD), 0.001);
    }

    public void check(QuoteRequestMsg expected, QuoteRequestMsg actual) {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getPrevClosePx().doubleValue(), actual.getPrevClosePx().doubleValue(), 0.001);
        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getOrderQty().doubleValue(), actual.getOrderQty().doubleValue(), 0.001);
    }
}
