/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrAttribGroup50SP1TestData.java
 *
 * $Id: DerivativeInstrAttribGroup50SP1TestData.java,v 1.1 2011-09-28 08:10:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.DerivativeInstrAttribGroup;
import net.hades.fix.message.type.InstrAttribType;

/**
 * Test utility for DerivativeInstrAttribGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class DerivativeInstrAttribGroup50SP1TestData extends MsgTest {

    private static final DerivativeInstrAttribGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new DerivativeInstrAttribGroup50SP1TestData();
    }

    public static DerivativeInstrAttribGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(DerivativeInstrAttribGroup grp) {
        grp.setDerivativeInstrAttribType(InstrAttribType.InterestBearing);
        grp.setDerivativeInstrAttribValue("option 1");
    }

    public void populate2(DerivativeInstrAttribGroup grp) {
        grp.setDerivativeInstrAttribType(InstrAttribType.InDefault);
        grp.setDerivativeInstrAttribValue("true");
    }

    public void check(DerivativeInstrAttribGroup expected, DerivativeInstrAttribGroup actual) {
        assertEquals(expected.getDerivativeInstrAttribType(), actual.getDerivativeInstrAttribType());
        assertEquals(expected.getDerivativeInstrAttribValue(), actual.getDerivativeInstrAttribValue());
    }
}
