/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrumentParties50SP1TestData.java
 *
 * $Id: DerivativeInstrumentParties50SP1TestData.java,v 1.1 2011-09-22 08:54:33 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.DerivativeInstrumentParties;
import net.hades.fix.message.group.impl.v50sp1.DerivativeInstrumentPartyGroup50SP1TestData;

/**
 * Test utility for DerivativInstrumentParties50SP1 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 6:50:59 PM
 */
public class DerivativeInstrumentParties50SP1TestData extends MsgTest {

    private static final DerivativeInstrumentParties50SP1TestData INSTANCE;

    static {
        INSTANCE = new DerivativeInstrumentParties50SP1TestData();
    }

    public static DerivativeInstrumentParties50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeInstrumentParties component) {
        component.setNoDerivativeInstrumentParties(new Integer(1));
        DerivativeInstrumentPartyGroup50SP1TestData.getInstance().populate1(component.getDerivativeInstrumentPartyGroups()[0]);
    }


    public void check(DerivativeInstrumentParties expected, DerivativeInstrumentParties actual) throws Exception {
        assertEquals(expected.getNoDerivativeInstrumentParties(), actual.getNoDerivativeInstrumentParties());
        for (int i = 0; i < expected.getNoDerivativeInstrumentParties().intValue(); i++) {
            DerivativeInstrumentPartyGroup50SP1TestData.getInstance().check(expected.getDerivativeInstrumentPartyGroups()[i], actual.getDerivativeInstrumentPartyGroups()[i]);
        }
    }
}
