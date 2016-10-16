/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg40TestData.java
 *
 * $Id: IOIMsg40TestData.java,v 1.2 2011-10-29 09:42:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOIQualifier;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for IOIMsg40 message class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 11:01:10 AM
 */
public class IOIMsg40TestData extends MsgTest {

    private static final IOIMsg40TestData INSTANCE;

    static {
        INSTANCE = new IOIMsg40TestData();
    }

    public static IOIMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix40.IndicationofInterest msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(quickfix.field.IOIID.FIELD, "IOI ID");
        msg.setString(quickfix.field.IOITransType.FIELD, IOITransType.New.getValue());
        msg.setString(quickfix.field.IOIRefID.FIELD, "Ref ID");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOT SFX");
        msg.setString(quickfix.field.SecurityID.FIELD, "SEC ID");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "ID Src");
        msg.setString(quickfix.field.Issuer.FIELD, "Issuer");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "MOT Shares");
        msg.setChar(quickfix.field.Side.FIELD, Side.BuyMinus.getValue());
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        msg.setString(quickfix.field.IOIQty.FIELD, IOIQty.Large.getValue());
        msg.setDouble(quickfix.field.Price.FIELD, 12.556);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setChar(quickfix.field.IOIQltyInd.FIELD, 'L');
        msg.setChar(quickfix.field.IOIOthSvc.FIELD, 'S');
        msg.setBoolean(quickfix.field.IOINaturalFlag.FIELD, true);
        msg.setChar(quickfix.field.IOIQualifier.FIELD, quickfix.field.IOIQualifier.AT_THE_MARKET);
        msg.setString(quickfix.field.Text.FIELD, "some text here");

    }

    public void populate(IOIMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.setIoiRefID("ref ID");
        msg.setSymbol("symbol");
        msg.setSymbolSfx("symbol sfx");
        msg.setSecurityID("sec ID");
        msg.setSecurityIDSource("sec ID source");
        msg.setIssuer("ISSUER");
        msg.setSecurityDesc("sec desc");
        msg.setSide(Side.Borrow);
        msg.setCurrency(Currency.AustralianDollar);
        msg.setIoiQty(IOIQty.Large);
        msg.setPrice(new Double(12.345));
        msg.setValidUntilTime(new Date());
        msg.setIoiQltyInd(IOIQltyInd.High);
        msg.setIoiOthSvc(new Character('s'));
        msg.setIoiNaturalFlag(Boolean.FALSE);
        msg.setIoiQualifier(IOIQualifier.AtTheMidpoint);
        msg.setText("I want these shares!");
    }

    public void check(quickfix.fix40.IndicationofInterest expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.IOIID.FIELD), actual.getIoiID());
        assertEquals(expected.getString(quickfix.field.IOITransType.FIELD), actual.getIoiTransType().getValue());
        assertEquals(expected.getString(quickfix.field.IOIRefID.FIELD), actual.getIoiRefID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.SecurityIDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertEquals(expected.getString(quickfix.field.IOIQty.FIELD), actual.getIoiQty().getValue());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getChar(quickfix.field.IOIQltyInd.FIELD), actual.getIoiQltyInd().getValue());
        assertEquals(expected.getChar(quickfix.field.IOIOthSvc.FIELD), actual.getIoiOthSvc().charValue());
        assertEquals(expected.getBoolean(quickfix.field.IOINaturalFlag.FIELD), actual.getIoiNaturalFlag().booleanValue());
        assertEquals(expected.getChar(quickfix.field.IOIQualifier.FIELD), actual.getIoiQualifier().getValue());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());

    }

    public void check(IOIMsg expected, quickfix.fix40.IndicationofInterest actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getString(quickfix.field.IOIID.FIELD));
        assertEquals(expected.getIoiTransType().getValue(), actual.getString(quickfix.field.IOITransType.FIELD));
        assertEquals(expected.getIoiRefID(), actual.getString(quickfix.field.IOIRefID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertEquals(expected.getIoiQty().getValue(), actual.getString(quickfix.field.IOIQty.FIELD));
        assertEquals(expected.getPrice().doubleValue(), actual.getDouble(quickfix.field.Price.FIELD), 0.001);
        assertTimestampEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD));
        assertEquals(expected.getIoiQltyInd().getValue(), actual.getChar(quickfix.field.IOIQltyInd.FIELD));
        assertEquals(expected.getIoiOthSvc().charValue(), actual.getChar(quickfix.field.IOIOthSvc.FIELD));
        assertEquals(expected.getIoiNaturalFlag().booleanValue(), actual.getBoolean(quickfix.field.IOINaturalFlag.FIELD));
        assertEquals(expected.getIoiQualifier().getValue(), actual.getChar(quickfix.field.IOIQualifier.FIELD));
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
    }

    public void check(IOIMsg expected, IOIMsg actual) {
        assertEquals(expected.getIoiID(), actual.getIoiID());
        assertEquals(expected.getIoiTransType().getValue(), actual.getIoiTransType().getValue());
        assertEquals(expected.getIoiRefID(), actual.getIoiRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getIoiQty(), actual.getIoiQty());
        assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getIoiQltyInd().getValue(), actual.getIoiQltyInd().getValue());
        assertEquals(expected.getIoiOthSvc().charValue(), actual.getIoiOthSvc().charValue());
        assertEquals(expected.getIoiNaturalFlag().booleanValue(), actual.getIoiNaturalFlag().booleanValue());
        assertEquals(expected.getIoiQualifier().getValue(), actual.getIoiQualifier().getValue());
        assertEquals(expected.getText(), actual.getText());
    }
}
