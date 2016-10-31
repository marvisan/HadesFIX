/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegIOIGroup50TestData.java
 *
 * $Id: LegIOIGroup50TestData.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.group.LegIOIGroup;
import net.hades.fix.message.type.IOIQty;

/**
 * Test utility for LegIOIGroup50 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/03/2009, 6:40:39 PM
 */
public class LegIOIGroup50TestData extends MsgTest {

    private static final LegIOIGroup50TestData INSTANCE;

    static {
        INSTANCE = new LegIOIGroup50TestData();
    }

    public static LegIOIGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(LegIOIGroup component) {
        component.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(component.getInstrumentLeg());
        component.setLegIOIQty(IOIQty.Large);
        component.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(component.getLegStipulations());
    }

    public void populate1(quickfix.fix50.IOI.NoLegs component) throws Exception {
        quickfix.fix50.component.InstrumentLeg insleg = new quickfix.fix50.component.InstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(insleg);
        component.set(insleg);
        component.setString(quickfix.field.LegIOIQty.FIELD, "L");
        quickfix.fix50.component.LegStipulations stipleg = new quickfix.fix50.component.LegStipulations();
        LegStipulations50TestData.getInstance().populate(stipleg);
        component.set(stipleg);
    }

    public void populate2(LegIOIGroup component) {
        component.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate2(component.getInstrumentLeg());
        component.setLegIOIQty(IOIQty.Large);
        component.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(component.getLegStipulations());
    }

    public void populate2(quickfix.fix50.IOI.NoLegs component) throws Exception {
        quickfix.fix50.component.InstrumentLeg insleg = new quickfix.fix50.component.InstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate2(insleg);
        component.set(insleg);
        component.setString(quickfix.field.LegIOIQty.FIELD, "L");
        quickfix.fix50.component.LegStipulations stipleg = new quickfix.fix50.component.LegStipulations();
        LegStipulations50TestData.getInstance().populate(stipleg);
        component.set(stipleg);
    }

    public void check(quickfix.fix50.IOI.NoLegs expected, LegIOIGroup actual) throws Exception {
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        assertEquals(expected.getLegIOIQty().getValue(), actual.getLegIOIQty().getValue());
        assertNotNull(actual.getLegStipulations());
        for (int i = 0; i < actual.getLegStipulations().getNoLegStipulations().intValue(); i++) {
            assertEquals(expected.getString(quickfix.field.LegStipulationType.FIELD),
                actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType());
            assertEquals(expected.getString(quickfix.field.LegStipulationValue.FIELD),
                actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue());
        }
    }

    public void check(LegIOIGroup expected, LegIOIGroup actual) throws Exception {
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        assertEquals(expected.getLegIOIQty().getValue(), actual.getLegIOIQty().getValue());
        assertNotNull(actual.getLegStipulations());
        for (int i = 0; i < actual.getLegStipulations().getNoLegStipulations().intValue(); i++) {
            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType(),
                actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationType());
            assertEquals(expected.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue(),
                actual.getLegStipulations().getLegStipulationsGroups()[i].getLegStipulationValue());
        }
    }
}
