/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosUndInstrmtGroup44TestData.java
 *
 * $Id: PosUndInstrmtGroup44TestData.java,v 1.2 2011-10-29 09:42:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.PosUndInstrmtGroup;
import net.hades.fix.message.type.SettlPriceType;

/**
 * Test utility for PosUndInstrmtGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/12/2011, 10:08:38 AM
 */
public class PosUndInstrmtGroup44TestData extends MsgTest {

    private static final PosUndInstrmtGroup44TestData INSTANCE;

    static {
        INSTANCE = new PosUndInstrmtGroup44TestData();
    }

    public static PosUndInstrmtGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PosUndInstrmtGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setUnderlyingSettlPrice(76.32d);
        grp.setUnderlyingSettlPriceType(SettlPriceType.Theoretical);
    }

    public void populate2(PosUndInstrmtGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setUnderlyingSettlPrice(36.32d);
        grp.setUnderlyingSettlPriceType(SettlPriceType.Final);
    }

    public void check(PosUndInstrmtGroup expected, PosUndInstrmtGroup actual) throws Exception {
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getUnderlyingSettlPrice(), actual.getUnderlyingSettlPrice());
        assertEquals(expected.getUnderlyingSettlPriceType(), actual.getUnderlyingSettlPriceType());
    }
}
