/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg41TestData.java
 *
 * $Id: IOIMsg41TestData.java,v 1.2 2011-10-29 09:42:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for IOIMsg41 message class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 11:01:10 AM
 */
public class IOIMsg41TestData extends MsgTest {

    private static final IOIMsg41TestData INSTANCE;

    static {
        INSTANCE = new IOIMsg41TestData();
    }

    public static IOIMsg41TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix41.IndicationofInterest msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setString(quickfix.field.IOIID.FIELD, "IOI ID");
        msg.setString(quickfix.field.IOITransType.FIELD, IOITransType.New.getValue());
        msg.setString(quickfix.field.IOIRefID.FIELD, "Ref ID");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOT SFX");
        msg.setString(quickfix.field.SecurityID.FIELD, "SEC ID");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "ID Src");
        msg.setString(quickfix.field.SecurityType.FIELD, quickfix.field.SecurityType.OPTION);
        msg.setString(quickfix.field.MaturityMonthYear.FIELD, "092009");
        msg.setInt(quickfix.field.MaturityDay.FIELD, 2);
        msg.setInt(quickfix.field.PutOrCall.FIELD, 0);
        msg.setDouble(quickfix.field.StrikePrice.FIELD, 12.33);
        msg.setChar(quickfix.field.OptAttribute.FIELD, 'X');
        msg.setString(quickfix.field.SecurityExchange.FIELD, "CBOT");
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
        msg.setInt(quickfix.field.NoIOIQualifiers.FIELD, 2);
        quickfix.fix41.IndicationofInterest.NoIOIQualifiers grpe1 = new quickfix.fix41.IndicationofInterest.NoIOIQualifiers();
        grpe1.setChar(quickfix.field.IOIQualifier.FIELD, 'X');
        msg.addGroup(grpe1);
        quickfix.fix41.IndicationofInterest.NoIOIQualifiers grpe2 = new quickfix.fix41.IndicationofInterest.NoIOIQualifiers();
        grpe2.setChar(quickfix.field.IOIQualifier.FIELD, '9');
        msg.addGroup(grpe2);
        msg.setChar(quickfix.field.IOIQualifier.FIELD, '2');
        msg.setString(quickfix.field.Text.FIELD, "some text here");
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.URLLink.FIELD, "www.cbot.com");

    }

    public void populate(IOIMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate41HeaderAll(msg);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.setIoiRefID("ref ID");
        msg.setSymbol("symbol");
        msg.setSymbolSfx("symbol sfx");
        msg.setSecurityID("sec ID");
        msg.setSecurityIDSource("sec ID source");
        msg.setSecurityType(SecurityType.AmendedRestated.getValue());
        msg.setMaturityMonthYear("082009");
        msg.setMaturityDay(new Integer(3));
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(new Double(12.456));
        msg.setOptAttribute(new Character('Z'));
        msg.setSecurityExchange("NYSE");
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
        msg.setNoIOIQualifiers(new Integer(3));
        msg.getIoiQualifiers()[0].setIoiQualifier(new Character('1'));
        msg.getIoiQualifiers()[1].setIoiQualifier(new Character('5'));
        msg.getIoiQualifiers()[2].setIoiQualifier(new Character('B'));
        msg.setText("I want these shares!");
        msg.setTransactTime(new Date());
        msg.setUrlLink("www.shares.com");
    }

    public void check(quickfix.fix41.IndicationofInterest expected, IOIMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.IOIID.FIELD), actual.getIoiID());
        assertEquals(expected.getString(quickfix.field.IOITransType.FIELD), actual.getIoiTransType().getValue());
        assertEquals(expected.getString(quickfix.field.IOIRefID.FIELD), actual.getIoiRefID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.SecurityIDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.SecurityType.FIELD), actual.getSecurityType());
        assertEquals(expected.getString(quickfix.field.MaturityMonthYear.FIELD), actual.getMaturityMonthYear());
        assertEquals(expected.getInt(quickfix.field.MaturityDay.FIELD), actual.getMaturityDay().intValue());
        assertEquals(expected.getInt(quickfix.field.PutOrCall.FIELD), actual.getPutOrCall().getValue());
        assertEquals(expected.getDouble(quickfix.field.StrikePrice.FIELD), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.OptAttribute.FIELD), actual.getOptAttribute().charValue());
        assertEquals(expected.getString(quickfix.field.SecurityExchange.FIELD), actual.getSecurityExchange());
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
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals('X', actual.getIoiQualifiers()[0].getIoiQualifier().charValue());
        assertEquals('9', actual.getIoiQualifiers()[1].getIoiQualifier().charValue());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getString(quickfix.field.URLLink.FIELD), actual.getUrlLink());

    }

    public void check(IOIMsg expected, quickfix.fix41.IndicationofInterest actual) throws Exception {
        assertEquals(expected.getIoiID(), actual.getString(quickfix.field.IOIID.FIELD));
        assertEquals(expected.getIoiTransType().getValue(), actual.getString(quickfix.field.IOITransType.FIELD));
        assertEquals(expected.getIoiRefID(), actual.getString(quickfix.field.IOIRefID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getSecurityType(), actual.getString(quickfix.field.SecurityType.FIELD));
        assertEquals(expected.getMaturityMonthYear(), actual.getString(quickfix.field.MaturityMonthYear.FIELD));
        assertEquals(expected.getMaturityDay().intValue(), actual.getInt(quickfix.field.MaturityDay.FIELD));
        assertEquals(expected.getPutOrCall().getValue(), actual.getInt(quickfix.field.PutOrCall.FIELD));
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getDouble(quickfix.field.StrikePrice.FIELD), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getChar(quickfix.field.OptAttribute.FIELD));
        assertEquals(expected.getSecurityExchange(), actual.getString(quickfix.field.SecurityExchange.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertEquals(expected.getIoiQty().getValue(), actual.getString(quickfix.field.IOIQty.FIELD));
        assertEquals(expected.getPrice().doubleValue(), actual.getDouble(quickfix.field.Price.FIELD), 0.001);
        assertTimestampEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getIoiQltyInd().getValue(), actual.getChar(quickfix.field.IOIQltyInd.FIELD));
        assertEquals(expected.getIoiOthSvc().charValue(), actual.getChar(quickfix.field.IOIOthSvc.FIELD));
        assertEquals(expected.getIoiNaturalFlag().booleanValue(), actual.getBoolean(quickfix.field.IOINaturalFlag.FIELD));
        assertEquals(expected.getNoIOIQualifiers().intValue(), actual.getInt(quickfix.field.NoIOIQualifiers.FIELD));
        quickfix.fix41.IndicationofInterest.NoIOIQualifiers grp1 = new quickfix.fix41.IndicationofInterest.NoIOIQualifiers();
        actual.getGroup(1, grp1);
        assertEquals(expected.getIoiQualifiers()[0].getIoiQualifier().charValue(), grp1.getIOIQualifier().getValue());
        quickfix.fix41.IndicationofInterest.NoIOIQualifiers grp2 = new quickfix.fix41.IndicationofInterest.NoIOIQualifiers();
        actual.getGroup(2, grp2);
        assertEquals(expected.getIoiQualifiers()[1].getIoiQualifier().charValue(), grp2.getIOIQualifier().getValue());
        quickfix.fix41.IndicationofInterest.NoIOIQualifiers grp3 = new quickfix.fix41.IndicationofInterest.NoIOIQualifiers();
        actual.getGroup(3, grp3);
        assertEquals(expected.getIoiQualifiers()[2].getIoiQualifier().charValue(), grp3.getIOIQualifier().getValue());
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD));
        assertEquals(expected.getUrlLink(), actual.getString(quickfix.field.URLLink.FIELD));
    }

    public void check(IOIMsg expected, IOIMsg actual) {
        assertEquals(expected.getIoiID(), actual.getIoiID());
        assertEquals(expected.getIoiTransType().getValue(), actual.getIoiTransType().getValue());
        assertEquals(expected.getIoiRefID(), actual.getIoiRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay().intValue(), actual.getMaturityDay().intValue());
        assertEquals(expected.getPutOrCall().getValue(), actual.getPutOrCall().getValue());
        assertEquals(expected.getStrikePrice().doubleValue(), actual.getStrikePrice().doubleValue(), 0.001);
        assertEquals(expected.getOptAttribute().charValue(), actual.getOptAttribute().charValue());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
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
        assertEquals(expected.getNoIOIQualifiers().intValue(), actual.getNoIOIQualifiers().intValue());
        assertEquals(expected.getIoiQualifiers()[0].getIoiQualifier().charValue(), actual.getIoiQualifiers()[0].getIoiQualifier().charValue());
        assertEquals(expected.getIoiQualifiers()[1].getIoiQualifier().charValue(), actual.getIoiQualifiers()[1].getIoiQualifier().charValue());
        assertEquals(expected.getIoiQualifiers()[2].getIoiQualifier().charValue(), actual.getIoiQualifiers()[2].getIoiQualifier().charValue());
        assertEquals(expected.getText(), actual.getText());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getUrlLink(), actual.getUrlLink());
    }
}
