/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeAllocGroup44TestData.java
 *
 * $Id: TradeAllocGroup44TestData.java,v 1.2 2011-10-29 09:42:09 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.NestedParties244TestData;
import net.hades.fix.message.group.TradeAllocGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for TradeAllocGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class TradeAllocGroup44TestData extends MsgTest {

    private static final TradeAllocGroup44TestData INSTANCE;

    static {
        INSTANCE = new TradeAllocGroup44TestData();
    }

    public static TradeAllocGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(TradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("53465465475");
        group.setAllocAcctIDSource(AcctIDSource.SID);
        group.setAllocSettlCurrency(Currency.UnitedStatesDollar);
        group.setIndividualAllocID("DD846555");

        group.setNestedParties2();
        NestedParties244TestData.getInstance().populate(group.getNestedParties2());

        group.setAllocQty(34.0d);
    }

    public void populate2(TradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("92773553");
        group.setAllocAcctIDSource(AcctIDSource.DTCC);
        group.setAllocSettlCurrency(Currency.AustralianDollar);
        group.setIndividualAllocID("AA846555");

        group.setNestedParties2();
        NestedParties244TestData.getInstance().populate(group.getNestedParties2());

        group.setAllocQty(14.0d);
    }

    public void check(TradeAllocGroup expected, TradeAllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocSettlCurrency(), actual.getAllocSettlCurrency());

        NestedParties244TestData.getInstance().check(expected.getNestedParties2(), expected.getNestedParties2());

        assertEquals(expected.getAllocQty(), actual.getAllocQty());
    }
}
