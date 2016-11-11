/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreTradeAllocGroup50TestData.java
 *
 * $Id: PreTradeAllocGroup50TestData.java,v 1.2 2011-10-29 09:42:29 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.NestedParties50TestData;
import net.hades.fix.message.group.PreTradeAllocGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for PreTradeAllocGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class PreTradeAllocGroup50TestData extends MsgTest {

    private static final PreTradeAllocGroup50TestData INSTANCE;

    static {
        INSTANCE = new PreTradeAllocGroup50TestData();
    }

    public static PreTradeAllocGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("53465465475");
        group.setIndividualAllocID("DD846555");
        group.setAllocAcctIDSource(AcctIDSource.SID);
        group.setAllocSettlCurrency(Currency.UnitedStatesDollar);
        // NestedParties
        group.setNestedParties();
        NestedParties50TestData.getInstance().populate(group.getNestedParties());

        group.setAllocQty(34.0d);
    }

    public void populate2(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("92773553");
        group.setIndividualAllocID("AA846555");
        group.setAllocAcctIDSource(AcctIDSource.DTCC);
        group.setAllocSettlCurrency(Currency.AustralianDollar);
        // NestedParties
        group.setNestedParties();
        NestedParties50TestData.getInstance().populate(group.getNestedParties());

        group.setAllocQty(14.0d);
    }

    public void check(PreTradeAllocGroup expected, PreTradeAllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getAllocAcctIDSource(), actual.getAllocAcctIDSource());
        assertEquals(expected.getAllocSettlCurrency(), actual.getAllocSettlCurrency());
        // NestedParties
        NestedParties50TestData.getInstance().check(expected.getNestedParties(), expected.getNestedParties());

        assertEquals(expected.getAllocQty(), actual.getAllocQty());
    }
}
