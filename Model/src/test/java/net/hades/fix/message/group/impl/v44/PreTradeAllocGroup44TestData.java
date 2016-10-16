/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreTradeAllocGroup44TestData.java
 *
 * $Id: PreTradeAllocGroup44TestData.java,v 1.2 2011-10-29 09:42:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.NestedParties44TestData;
import net.hades.fix.message.group.PreTradeAllocGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for PreTradeAllocGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class PreTradeAllocGroup44TestData extends MsgTest {

    private static final PreTradeAllocGroup44TestData INSTANCE;

    static {
        INSTANCE = new PreTradeAllocGroup44TestData();
    }

    public static PreTradeAllocGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("53465465475");
        group.setIndividualAllocID("DD846555");
        group.setAllocAcctIDSource(AcctIDSource.SID);
        group.setAllocSettlCurrency(Currency.UnitedStatesDollar);
        // NestedParties
        group.setNestedParties();
        NestedParties44TestData.getInstance().populate(group.getNestedParties());

        group.setAllocQty(34.0d);
    }

    public void populate2(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("92773553");
        group.setIndividualAllocID("AA846555");
        group.setAllocAcctIDSource(AcctIDSource.DTCC);
        group.setAllocSettlCurrency(Currency.AustralianDollar);
        // NestedParties
        group.setNestedParties();
        NestedParties44TestData.getInstance().populate(group.getNestedParties());

        group.setAllocQty(14.0d);
    }

    public void check(PreTradeAllocGroup expected, PreTradeAllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocSettlCurrency(), actual.getAllocSettlCurrency());
        // NestedParties
        NestedParties44TestData.getInstance().check(expected.getNestedParties(), expected.getNestedParties());

        assertEquals(expected.getAllocQty(), actual.getAllocQty());
    }
}
