/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stipulations43TestData.java
 *
 * $Id: Stipulations43TestData.java,v 1.1 2009-07-06 03:19:16 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import java.io.UnsupportedEncodingException;

import quickfix.FieldNotFound;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.Stipulations;

/**
 * Test utility for Stipulations43 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/03/2009, 4:17:02 PM
 */
public class Stipulations43TestData extends MsgTest {

    private static final Stipulations43TestData INSTANCE;

    static {
        INSTANCE = new Stipulations43TestData();
    }

    public static Stipulations43TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix43.component.Stipulations msg) throws UnsupportedEncodingException, FieldNotFound {
        msg.setInt(quickfix.field.NoStipulations.FIELD, 2);
        quickfix.fix43.component.Stipulations.NoStipulations grp1 = new quickfix.fix43.component.Stipulations.NoStipulations();
        grp1.setString(quickfix.field.StipulationType.FIELD, "stip typ 1");
        grp1.setString(quickfix.field.StipulationValue.FIELD, "stip value 1");
        msg.addGroup(grp1);
        quickfix.fix43.component.Stipulations.NoStipulations grp2 = new quickfix.fix43.component.Stipulations.NoStipulations();
        grp2.setString(quickfix.field.StipulationType.FIELD, "stip typ 2");
        grp2.setString(quickfix.field.StipulationValue.FIELD, "stip value 2");
        msg.addGroup(grp2);
    }

    public void populate(Stipulations component) {
        component.setNoStipulations(new Integer(2));
        component.getStipulationsGroups()[0].setStipulationType("stip type 1");
        component.getStipulationsGroups()[0].setStipulationValue("stip val 1");
        component.getStipulationsGroups()[1].setStipulationType("stip type 2");
        component.getStipulationsGroups()[1].setStipulationValue("stip val 2");
    }

    public void check(Stipulations expected, quickfix.fix43.component.Stipulations actual) throws Exception {
        assertEquals(expected.getNoStipulations().intValue(), actual.getInt(quickfix.field.NoStipulations.FIELD));
        for (int i = 0; i < expected.getNoStipulations().intValue(); i++) {
            quickfix.fix43.component.Stipulations.NoStipulations grp = new quickfix.fix43.component.Stipulations.NoStipulations();
            actual.getGroup(i + 1, grp);
            assertEquals(expected.getStipulationsGroups()[i].getStipulationType(), grp.getString(quickfix.field.StipulationType.FIELD));
            assertEquals(expected.getStipulationsGroups()[i].getStipulationValue(), grp.getString(quickfix.field.StipulationValue.FIELD));
        }

    }

    public void check(quickfix.fix43.component.Stipulations expected, Stipulations actual) throws Exception {
        assertEquals(expected.getInt(quickfix.field.NoStipulations.FIELD), actual.getNoStipulations().intValue());
        for (int i = 0; i < actual.getNoStipulations().intValue(); i++) {
            quickfix.fix43.component.Stipulations.NoStipulations grp = new quickfix.fix43.component.Stipulations.NoStipulations();
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
