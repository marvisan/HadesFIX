/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentParties50TestData.java
 *
 * $Id: InstrumentParties50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.InstrumentParties;
import net.hades.fix.message.group.impl.v50.InstrumentPartyGroup50TestData;

/**
 * Test utility for InstrumentParties50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 6:50:59 PM
 */
public class InstrumentParties50TestData extends MsgTest {

    private static final InstrumentParties50TestData INSTANCE;

    static {
        INSTANCE = new InstrumentParties50TestData();
    }

    public static InstrumentParties50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix50.component.InstrumentParties msg) throws Exception {
        msg.setInt(quickfix.field.NoInstrumentParties.FIELD, 2);
        quickfix.fix50.component.InstrumentParties.NoInstrumentParties grp1 = new quickfix.fix50.component.InstrumentParties.NoInstrumentParties();
        InstrumentPartyGroup50TestData.getInstance().populate1(grp1);
        msg.addGroup(grp1);
        quickfix.fix50.component.InstrumentParties.NoInstrumentParties grp2 = new quickfix.fix50.component.InstrumentParties.NoInstrumentParties();
        InstrumentPartyGroup50TestData.getInstance().populate2(grp2);
        msg.addGroup(grp2);
    }

    public void populate(InstrumentParties component) {
        component.setNoInstrumentParties(new Integer(1));
        InstrumentPartyGroup50TestData.getInstance().populate1(component.getInstrumentPartyGroups()[0]);
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

    public void check(quickfix.fix50.component.InstrumentParties expected, InstrumentParties actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoInstrumentParties.FIELD), actual.getNoInstrumentParties().intValue());
        for (int i = 0; i < actual.getNoInstrumentParties().intValue(); i++) {
            quickfix.fix50.component.InstrumentParties.NoInstrumentParties grp = new quickfix.fix50.component.InstrumentParties.NoInstrumentParties();
            expected.getGroup(i + 1, grp);
            InstrumentPartyGroup50TestData.getInstance().check(grp, actual.getInstrumentPartyGroups()[i]);
        }
    }

    public void check(InstrumentParties expected, InstrumentParties actual) throws Exception {
        assertEquals(expected.getNoInstrumentParties().intValue(), actual.getNoInstrumentParties().intValue());
        for (int i = 0; i < expected.getNoInstrumentParties().intValue(); i++) {
            InstrumentPartyGroup50TestData.getInstance().check(expected.getInstrumentPartyGroups()[i], actual.getInstrumentPartyGroups()[i]);
        }
    }
}
