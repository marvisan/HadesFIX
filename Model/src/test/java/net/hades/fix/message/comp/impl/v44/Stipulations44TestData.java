/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stipulations44TestData.java
 *
 * $Id: Stipulations44TestData.java,v 1.1 2009-07-06 03:18:51 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import java.io.UnsupportedEncodingException;

import quickfix.FieldNotFound;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.type.StipulationType;

/**
 * Test utility for Stipulations44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class Stipulations44TestData extends MsgTest {

    private static final Stipulations44TestData INSTANCE;

    static {
        INSTANCE = new Stipulations44TestData();
    }

    public static Stipulations44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.component.Stipulations msg) throws UnsupportedEncodingException, FieldNotFound {
        msg.setInt(quickfix.field.NoStipulations.FIELD, 2);
        quickfix.fix44.component.Stipulations.NoStipulations grp1 = new quickfix.fix44.component.Stipulations.NoStipulations();
        grp1.setString(quickfix.field.StipulationType.FIELD, StipulationType.AbsolutePrepaymentSpeed.getValue());
        grp1.setString(quickfix.field.StipulationValue.FIELD, "stip value 1");
        msg.addGroup(grp1);
        quickfix.fix44.component.Stipulations.NoStipulations grp2 = new quickfix.fix44.component.Stipulations.NoStipulations();
        grp2.setString(quickfix.field.StipulationType.FIELD, StipulationType.AutoReinvestment.getValue());
        grp2.setString(quickfix.field.StipulationValue.FIELD, "stip value 2");
        msg.addGroup(grp2);
    }

    public void populate(Stipulations component) {
        component.setNoStipulations(new Integer(2));
        component.getStipulationsGroups()[0].setStipulationType(StipulationType.AbsolutePrepaymentSpeed.getValue());
        component.getStipulationsGroups()[0].setStipulationValue("stip val 1");
        component.getStipulationsGroups()[1].setStipulationType(StipulationType.BankQualified.getValue());
        component.getStipulationsGroups()[1].setStipulationValue("stip val 2");
    }

    public void check(Stipulations expected, quickfix.fix44.component.Stipulations actual) throws Exception {
        assertEquals(expected.getNoStipulations().intValue(), actual.getInt(quickfix.field.NoStipulations.FIELD));
        for (int i = 0; i < expected.getNoStipulations().intValue(); i++) {
            quickfix.fix44.component.Stipulations.NoStipulations grp = new quickfix.fix44.component.Stipulations.NoStipulations();
            actual.getGroup(i + 1, grp);
            assertEquals(expected.getStipulationsGroups()[i].getStipulationType(), grp.getString(quickfix.field.StipulationType.FIELD));
            assertEquals(expected.getStipulationsGroups()[i].getStipulationValue(), grp.getString(quickfix.field.StipulationValue.FIELD));
        }
    }

    public void check(quickfix.fix44.component.Stipulations expected, Stipulations actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoStipulations.FIELD), actual.getNoStipulations().intValue());
        for (int i = 0; i < actual.getNoStipulations().intValue(); i++) {
            quickfix.fix44.component.Stipulations.NoStipulations grp = new quickfix.fix44.component.Stipulations.NoStipulations();
            expected.getGroup(i + 1, grp);
            assertEquals(actual.getStipulationsGroups()[i].getStipulationType(), grp.getString(quickfix.field.StipulationType.FIELD));
            assertEquals(actual.getStipulationsGroups()[i].getStipulationValue(), grp.getString(quickfix.field.StipulationValue.FIELD));
        }
    }

    public void check(Stipulations expected, Stipulations actual) throws Exception {
        assertEquals(expected.getNoStipulations().intValue(), actual.getNoStipulations().intValue());
        for (int i = 0; i < expected.getNoStipulations().intValue(); i++) {
            assertEquals(expected.getStipulationsGroups()[i].getStipulationType(), actual.getStipulationsGroups()[i].getStipulationType());
            assertEquals(expected.getStipulationsGroups()[i].getStipulationValue(), actual.getStipulationsGroups()[i].getStipulationValue());
        }
    }
}
