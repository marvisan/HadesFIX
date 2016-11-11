/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreAllocMLegGroup43TestData.java
 *
 * $Id: PreAllocMLegGroup43TestData.java,v 1.1 2011-09-08 08:54:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PreAllocMLegGroup;

/**
 * Test utility for PreAllocMLegGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class PreAllocMLegGroup43TestData extends MsgTest {

    private static final PreAllocMLegGroup43TestData INSTANCE;

    static {
        INSTANCE = new PreAllocMLegGroup43TestData();
    }

    public static PreAllocMLegGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PreAllocMLegGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("53465465475");
        group.setIndividualAllocID("DD846555");
        group.setAllocQty(34.0d);
    }

    public void populate2(PreAllocMLegGroup group) throws UnsupportedEncodingException {
        group.setAllocAccount("92773553");
        group.setIndividualAllocID("AA846555");
        group.setAllocQty(14.0d);
    }

    public void check(PreAllocMLegGroup expected, PreAllocMLegGroup actual) throws Exception {
        assertEquals(expected.getAllocAccount(), actual.getAllocAccount());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
        assertEquals(expected.getAllocQty(), actual.getAllocQty());
    }
}
