/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingAmountGroup50TestData.java
 *
 * $Id: UnderlyingAmountGroup50TestData.java,v 1.2 2011-10-29 09:42:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.UnderlyingAmountGroup;

/**
 * Test utility for UnderlyingAmountGroup50 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/05/2011, 10:08:38 AM
 */
public class UnderlyingAmountGroup50TestData extends MsgTest {

    private static final UnderlyingAmountGroup50TestData INSTANCE;

    static {
        INSTANCE = new UnderlyingAmountGroup50TestData();
    }

    public static UnderlyingAmountGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(UnderlyingAmountGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setUnderlyingPayAmount(55.77d);
        grp.setUnderlyingCollectAmount(66.77d);
        cal.set(2010, 3, 14, 12, 13, 13);
        grp.setUnderlyingSettlementDate(cal.getTime());
        grp.setUnderlyingSettlementStatus("SETTLED");
    }

    public void populate2(UnderlyingAmountGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setUnderlyingPayAmount(77.77d);
        grp.setUnderlyingCollectAmount(66.77d);
        cal.set(2011, 3, 12, 12, 13, 13);
        grp.setUnderlyingSettlementDate(cal.getTime());
        grp.setUnderlyingSettlementStatus("NOT SETTLEDD");
    }

    public void check(UnderlyingAmountGroup expected, UnderlyingAmountGroup actual) throws Exception {
        assertEquals(expected.getUnderlyingPayAmount(), actual.getUnderlyingPayAmount());
        assertEquals(expected.getUnderlyingCollectAmount(), actual.getUnderlyingCollectAmount());
        assertDateEquals(expected.getUnderlyingSettlementDate(), actual.getUnderlyingSettlementDate());
        assertEquals(expected.getUnderlyingSettlementStatus(), actual.getUnderlyingSettlementStatus());
    }
}
