/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RateSourceGroup50SP2TestData.java
 *
 * $Id: RateSourceGroup50SP2TestData.java,v 1.2 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.RateSourceGroup;
import net.hades.fix.message.type.RateSource;
import net.hades.fix.message.type.RateSourceType;

/**
 * Test utility for RateSourceGroup50SP2 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class RateSourceGroup50SP2TestData extends MsgTest {

    private static final RateSourceGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new RateSourceGroup50SP2TestData();
    }

    public static RateSourceGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(RateSourceGroup msg) throws UnsupportedEncodingException {
        msg.setRateSource(RateSource.Reuters);
        msg.setRateSourceType(RateSourceType.Secondary);
        msg.setReferencePage("REF1");
    }

    public void populate2(RateSourceGroup msg) throws UnsupportedEncodingException {
        msg.setRateSource(RateSource.Bloomberg);
        msg.setRateSourceType(RateSourceType.Primary);
        msg.setReferencePage("REF2");
    }

    public void check(RateSourceGroup expected, RateSourceGroup actual) throws Exception {
        assertEquals(expected.getRateSource(), actual.getRateSource());
        assertEquals(expected.getRateSourceType(), actual.getRateSourceType());
        assertEquals(expected.getReferencePage(), actual.getReferencePage());
    }
}
