/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stipulations50TestData.java
 *
 * $Id: LegStipulations50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.type.StipulationType;

/**
 * Test utility for LegStipulations50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class LegStipulations50TestData extends MsgTest {

    private static final LegStipulations50TestData INSTANCE;

    static {
        INSTANCE = new LegStipulations50TestData();
    }

    public static LegStipulations50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(LegStipulations component) {
        component.setNoLegStipulations(new Integer(2));
        component.getLegStipulationsGroups()[0].setLegStipulationType(StipulationType.AutoReinvestment.getValue());
        component.getLegStipulationsGroups()[0].setLegStipulationValue("stip val 1");
        component.getLegStipulationsGroups()[1].setLegStipulationType(StipulationType.BankQualified.getValue());
        component.getLegStipulationsGroups()[1].setLegStipulationValue("stip val 2");
    }

    public void populate(quickfix.fix50.component.LegStipulations msg) throws Exception {
        msg.setInt(quickfix.field.NoLegStipulations.FIELD, 2);
        quickfix.fix50.component.LegStipulations.NoLegStipulations grp1 = new quickfix.fix50.component.LegStipulations.NoLegStipulations();
        grp1.setString(quickfix.field.LegStipulationType.FIELD, StipulationType.AutoReinvestment.getValue());
        grp1.setString(quickfix.field.LegStipulationValue.FIELD, "stip value 1");
        msg.addGroup(grp1);
        quickfix.fix50.component.LegStipulations.NoLegStipulations grp2 = new quickfix.fix50.component.LegStipulations.NoLegStipulations();
        grp2.setString(quickfix.field.LegStipulationType.FIELD, StipulationType.AverageLoanSize.getValue());
        grp2.setString(quickfix.field.LegStipulationValue.FIELD, "stip value 2");
        msg.addGroup(grp2);
    }

    public void check(LegStipulations expected, LegStipulations actual) throws Exception {
        assertEquals(expected.getNoLegStipulations().intValue(), actual.getNoLegStipulations().intValue());
        for (int i = 0; i < expected.getNoLegStipulations().intValue(); i++) {
            assertEquals(expected.getLegStipulationsGroups()[i].getLegStipulationType(), actual.getLegStipulationsGroups()[i].getLegStipulationType());
            assertEquals(expected.getLegStipulationsGroups()[i].getLegStipulationValue(), actual.getLegStipulationsGroups()[i].getLegStipulationValue());
        }
    }

    public void check(LegStipulations expected, quickfix.fix50.component.LegStipulations actual) throws Exception {
        assertEquals(expected.getNoLegStipulations().intValue(), actual.getInt(quickfix.field.NoLegStipulations.FIELD));
        for (int i = 0; i < expected.getNoLegStipulations().intValue(); i++) {
            quickfix.fix50.component.LegStipulations.NoLegStipulations grp = new quickfix.fix50.component.LegStipulations.NoLegStipulations();
            actual.getGroup(i + 1, grp);
            assertEquals(expected.getLegStipulationsGroups()[i].getLegStipulationType(), grp.getString(quickfix.field.LegStipulationType.FIELD));
            assertEquals(expected.getLegStipulationsGroups()[i].getLegStipulationValue(), grp.getString(quickfix.field.LegStipulationValue.FIELD));
        }
    }

    public void check(quickfix.fix50.component.LegStipulations expected, LegStipulations actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoLegStipulations.FIELD), actual.getNoLegStipulations().intValue());
        for (int i = 0; i < actual.getNoLegStipulations().intValue(); i++) {
            quickfix.fix50.component.LegStipulations.NoLegStipulations grp = new quickfix.fix50.component.LegStipulations.NoLegStipulations();
            expected.getGroup(i + 1, grp);
            assertEquals(actual.getLegStipulationsGroups()[i].getLegStipulationType(), grp.getString(quickfix.field.LegStipulationType.FIELD));
            assertEquals(actual.getLegStipulationsGroups()[i].getLegStipulationValue(), grp.getString(quickfix.field.LegStipulationValue.FIELD));
        }
    }
}
