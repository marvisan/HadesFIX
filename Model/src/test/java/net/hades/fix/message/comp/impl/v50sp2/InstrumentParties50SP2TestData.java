/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentParties50TestData.java
 *
 * $Id: InstrumentParties50SP2TestData.java,v 1.2 2011-09-28 08:10:21 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.InstrumentParties;
import net.hades.fix.message.group.impl.v50.InstrumentPartyGroup50TestData;
import net.hades.fix.message.group.impl.v50sp2.InstrumentPartyGroup50SP2TestData;

/**
 * Test utility for InstrumentParties50SP2TestData component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 14/04/2009, 6:50:59 PM
 */
public class InstrumentParties50SP2TestData extends MsgTest {

    private static final InstrumentParties50SP2TestData INSTANCE;

    static {
        INSTANCE = new InstrumentParties50SP2TestData();
    }

    public static InstrumentParties50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(InstrumentParties component) {
        component.setNoInstrumentParties(new Integer(1));
        InstrumentPartyGroup50SP2TestData.getInstance().populate1(component.getInstrumentPartyGroups()[0]);
//        InstrumentPartyGroup50TestData.getInstance().populate2(component.getInstrumentPartyGroups()[1]);
    }

    public void check(InstrumentParties expected, quickfix.fix50.component.InstrumentParties actual) throws Exception {
        assertEquals(expected.getNoInstrumentParties().intValue(), actual.getInt(quickfix.field.NoInstrumentParties.FIELD));
        for (int i = 0; i < expected.getNoInstrumentParties().intValue(); i++) {
            quickfix.fix50.component.InstrumentParties.NoInstrumentParties grp = new quickfix.fix50.component.InstrumentParties.NoInstrumentParties();
            actual.getGroup(i + 1, grp);
            InstrumentPartyGroup50TestData.getInstance().check(expected.getInstrumentPartyGroups()[i], grp);
        }
    }

    public void check(InstrumentParties expected, InstrumentParties actual) {
        assertEquals(expected.getNoInstrumentParties().intValue(), actual.getNoInstrumentParties().intValue());
        for (int i = 0; i < expected.getNoInstrumentParties().intValue(); i++) {
            InstrumentPartyGroup50SP2TestData.getInstance().check(expected.getInstrumentPartyGroups()[i], actual.getInstrumentPartyGroups()[i]);
        }
    }
}
