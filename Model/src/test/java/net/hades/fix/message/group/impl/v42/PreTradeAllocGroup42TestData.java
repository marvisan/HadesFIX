/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreTradeAllocGroup42TestData.java
 *
 * $Id: PreTradeAllocGroup42TestData.java,v 1.1 2010-12-12 09:13:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PreTradeAllocGroup;

/**
 * Test utility for MDreqGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class PreTradeAllocGroup42TestData extends MsgTest {

    private static final PreTradeAllocGroup42TestData INSTANCE;

    static {
        INSTANCE = new PreTradeAllocGroup42TestData();
    }

    public static PreTradeAllocGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("53465465475");
        group.setAllocQty(34.0d);
    }

    public void populate2(PreTradeAllocGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("92773553");
        group.setAllocQty(14.0d);
    }

    public void check(PreTradeAllocGroup expected, PreTradeAllocGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
    }
}
