/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg40TestData.java
 *
 * $Id: AdvertisementMsg40TestData.java,v 1.2 2011-10-29 09:42:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40.data;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for AdvertismentMsg40 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 10:00:42 AM
 */
public class AdvertisementMsg40TestData extends MsgTest {

    private static final AdvertisementMsg40TestData INSTANCE;

    static {
        INSTANCE = new AdvertisementMsg40TestData();
    }

    public static AdvertisementMsg40TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix40.Advertisement msg) throws UnsupportedEncodingException {
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(quickfix.field.AdvId.FIELD, "45");
        msg.setString(quickfix.field.AdvTransType.FIELD, "N");
        msg.setString(quickfix.field.AdvRefID.FIELD, "265");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.SymbolSfx.FIELD, "MOTOROLA Shares");
        msg.setString(quickfix.field.SecurityID.FIELD, "MOTO");
        msg.setString(quickfix.field.SecurityIDSource.FIELD, "NYSE");
        msg.setString(quickfix.field.Issuer.FIELD, "HADES");
        msg.setString(quickfix.field.SecurityDesc.FIELD, "One Motorola Share");
        msg.setString(quickfix.field.AdvSide.FIELD, "B");
        msg.setDecimal(quickfix.field.Quantity.FIELD, new BigDecimal("200"));
        msg.setDecimal(quickfix.field.Price.FIELD, new BigDecimal("13.45"));
        msg.setString(quickfix.field.Currency.FIELD, "USD");
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.Text.FIELD, "I want these shares!");
    }

    public void populate(AdvertisementMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.New);
        msg.setAdvRefID("265");
        msg.setSymbol("MOT");
        msg.setSymbolSfx("MOTOROLA Shares");
        msg.setSecurityID("MOTO");
        msg.setSecurityIDSource("NYSE");
        msg.setIssuer("HADES");
        msg.setSecurityDesc("One Motorola Share");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        msg.setPrice(new Double("13.45"));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setTransactTime(new Date());
        msg.setText("I want these shares!");
    }

    public void check(quickfix.fix40.Advertisement expected, AdvertisementMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.AdvId.FIELD), actual.getAdvID());
        assertEquals(expected.getString(quickfix.field.AdvTransType.FIELD), actual.getAdvTransType().getValue());
        assertEquals(expected.getString(quickfix.field.AdvRefID.FIELD), actual.getAdvRefID());
        assertEquals(expected.getString(quickfix.field.Symbol.FIELD), actual.getSymbol());
        assertEquals(expected.getString(quickfix.field.SymbolSfx.FIELD), actual.getSymbolSfx());
        assertEquals(expected.getString(quickfix.field.SecurityID.FIELD), actual.getSecurityID());
        assertEquals(expected.getString(quickfix.field.SecurityIDSource.FIELD), actual.getSecurityIDSource());
        assertEquals(expected.getString(quickfix.field.Issuer.FIELD), actual.getIssuer());
        assertEquals(expected.getString(quickfix.field.SecurityDesc.FIELD), actual.getSecurityDesc());
        assertEquals(expected.getString(quickfix.field.AdvSide.FIELD), actual.getAdvSide().getValue());
        assertEquals(expected.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), actual.getQuantity().doubleValue(), 0.1);
        assertEquals(expected.getDecimal(quickfix.field.Price.FIELD).doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());

    }

    public void check(AdvertisementMsg expected, quickfix.fix40.Advertisement actual) throws Exception {
        assertEquals(expected.getAdvID(), actual.getString(quickfix.field.AdvId.FIELD));
        assertEquals(expected.getAdvTransType().getValue(), actual.getString(quickfix.field.AdvTransType.FIELD));
        assertEquals(expected.getAdvRefID(), actual.getString(quickfix.field.AdvRefID.FIELD));
        assertEquals(expected.getSymbol(), actual.getString(quickfix.field.Symbol.FIELD));
        assertEquals(expected.getSymbolSfx(), actual.getString(quickfix.field.SymbolSfx.FIELD));
        assertEquals(expected.getSecurityID(), actual.getString(quickfix.field.SecurityID.FIELD));
        assertEquals(expected.getSecurityIDSource(), actual.getString(quickfix.field.SecurityIDSource.FIELD));
        assertEquals(expected.getIssuer(), actual.getString(quickfix.field.Issuer.FIELD));
        assertEquals(expected.getSecurityDesc(), actual.getString(quickfix.field.SecurityDesc.FIELD));
        assertEquals(expected.getAdvSide().getValue(), actual.getString(quickfix.field.AdvSide.FIELD));
        assertEquals(expected.getQuantity(), actual.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), 0.1);
        assertEquals(expected.getPrice(), actual.getDecimal(quickfix.field.Price.FIELD).doubleValue(), 0.01);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
    }

    public void check(AdvertisementMsg expected, AdvertisementMsg actual) {
        assertEquals(expected.getAdvID(), actual.getAdvID());
        assertEquals(expected.getAdvTransType(), actual.getAdvTransType());
        assertEquals(expected.getAdvRefID(), actual.getAdvRefID());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getAdvSide(), actual.getAdvSide());
        assertEquals(expected.getQuantity(), actual.getQuantity(), 0.1);
        assertEquals(expected.getPrice(), actual.getPrice(), 0.01);
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getText(), actual.getText());
    }
}
