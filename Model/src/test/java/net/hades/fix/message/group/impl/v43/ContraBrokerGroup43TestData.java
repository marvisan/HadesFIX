/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * ContraBrokerGroup43TestData.java
 *
 * $Id: ContraBrokerGroup43TestData.java,v 1.1 2011-01-12 11:33:57 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.ContraBrokerGroup;

/**
 * Test utility for ContraBrokerGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class ContraBrokerGroup43TestData extends MsgTest {

    private static final ContraBrokerGroup43TestData INSTANCE;

    static {
        INSTANCE = new ContraBrokerGroup43TestData();
    }

    public static ContraBrokerGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(ContraBrokerGroup grp) throws UnsupportedEncodingException {
        grp.setContraBroker("CB1");
        grp.setContraTrader("CT1");
        grp.setContraTradeQty(33.33d);
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 3, 14, 12, 15, 33);
        grp.setContraTradeTime(cal.getTime());
        grp.setContraLegRefID("LEG6374555");
    }

    public void populate2(ContraBrokerGroup grp) throws UnsupportedEncodingException {
        grp.setContraBroker("CB2");
        grp.setContraTrader("CT2");
        grp.setContraTradeQty(44.44d);
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 3, 14, 10, 10, 33);
        grp.setContraTradeTime(cal.getTime());
        grp.setContraLegRefID("LEG6364645");
    }

    public void check(ContraBrokerGroup expected, ContraBrokerGroup actual) throws Exception {
        assertEquals(expected.getContraBroker(), actual.getContraBroker());
        assertEquals(expected.getContraTrader(), actual.getContraTrader());
        assertEquals(expected.getContraTradeQty(), actual.getContraTradeQty());
        assertUTCTimestampEquals(expected.getContraTradeTime(), actual.getContraTradeTime(), false);
        assertEquals(expected.getContraLegRefID(), actual.getContraLegRefID());
    }
}
