/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreTradeAllocGroup43TestData.java
 *
 * $Id: PreTradeAllocGroup43TestData.java,v 1.1 2010-12-12 09:13:11 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.NestedParties43TestData;
import net.hades.fix.message.group.PreTradeAllocGroup;

/**
 * Test utility for PreTradeAllocGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class PreTradeAllocGroup43TestData extends MsgTest {

    private static final PreTradeAllocGroup43TestData INSTANCE;

    static {
        INSTANCE = new PreTradeAllocGroup43TestData();
    }

    public static PreTradeAllocGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("53465465475");
        group.setIndividualAllocID("DD846555");
        // NestedParties
        group.setNestedParties();
        NestedParties43TestData.getInstance().populate(group.getNestedParties());

        group.setAllocQty(34.0d);
    }

    public void populate2(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("92773553");
        group.setIndividualAllocID("AA846555");
        // NestedParties
        group.setNestedParties();
        NestedParties43TestData.getInstance().populate(group.getNestedParties());

        group.setAllocQty(14.0d);
    }

    public void check(PreTradeAllocGroup expected, PreTradeAllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        // NestedParties
        NestedParties43TestData.getInstance().check(expected.getNestedParties(), expected.getNestedParties());

        assertEquals(expected.getAllocQty(), actual.getAllocQty());
    }
}
