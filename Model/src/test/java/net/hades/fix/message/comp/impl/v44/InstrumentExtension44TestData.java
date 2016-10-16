/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentExtension44TestData.java
 *
 * $Id: InstrumentExtension44TestData.java,v 1.2 2011-09-28 08:10:22 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;


import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.InstrumentExtension;
import net.hades.fix.message.type.DeliveryForm;
import net.hades.fix.message.type.InstrAttribType;

/**
 * Test utility for InstrumentExtension44 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 18/03/2009, 7:52:01 PM
 */
public class InstrumentExtension44TestData extends MsgTest {

    private static final InstrumentExtension44TestData INSTANCE;

    static {
        INSTANCE = new InstrumentExtension44TestData();
    }

    public static InstrumentExtension44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(InstrumentExtension comp) {
        comp.setDeliveryForm(DeliveryForm.Bearer);
        comp.setPctAtRisk(12.5d);
        comp.setNoInstrAttrib(2);
        comp.getInstrmtAttribGroups()[0].setInstrAttribType(InstrAttribType.InterestBearing);
        comp.getInstrmtAttribGroups()[0].setInstrAttribValue("own");
        comp.getInstrmtAttribGroups()[1].setInstrAttribType(InstrAttribType.Callable);
        comp.getInstrmtAttribGroups()[1].setInstrAttribValue("hello");
    }

    public void check(InstrumentExtension expected, InstrumentExtension actual) {
        assertEquals(expected.getDeliveryForm(), actual.getDeliveryForm());
        assertEquals(expected.getPctAtRisk(), actual.getPctAtRisk());
        assertEquals(expected.getNoInstrAttrib(), actual.getNoInstrAttrib());
        assertEquals(expected.getInstrmtAttribGroups()[0].getInstrAttribType(), actual.getInstrmtAttribGroups()[0].getInstrAttribType());
        assertEquals(expected.getInstrmtAttribGroups()[0].getInstrAttribValue(), actual.getInstrmtAttribGroups()[0].getInstrAttribValue());
        assertEquals(expected.getInstrmtAttribGroups()[1].getInstrAttribType(), actual.getInstrmtAttribGroups()[1].getInstrAttribType());
        assertEquals(expected.getInstrmtAttribGroups()[1].getInstrAttribValue(), actual.getInstrmtAttribGroups()[1].getInstrAttribValue());
    }
}
