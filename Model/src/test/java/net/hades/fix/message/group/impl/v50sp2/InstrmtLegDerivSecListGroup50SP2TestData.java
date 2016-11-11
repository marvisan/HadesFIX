/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegDerivSecListGroup50SP2TestData.java
 *
 * $Id: InstrmtLegDerivSecListGroup50SP2TestData.java,v 1.1 2011-09-28 08:10:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;


import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2TestData;
import net.hades.fix.message.group.InstrmtLegDerivSecListGroup;

/**
 * Test utility for InstrmtLegDerivSecListGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtLegDerivSecListGroup50SP2TestData extends MsgTest {

    private static final InstrmtLegDerivSecListGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new InstrmtLegDerivSecListGroup50SP2TestData();
    }

    public static InstrmtLegDerivSecListGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtLegDerivSecListGroup grp) {
        grp.setInstrumentLeg();
        InstrumentLeg50SP2TestData.getInstance().populate1(grp.getInstrumentLeg());
    }

    public void populate2(InstrmtLegDerivSecListGroup grp) {
        grp.setInstrumentLeg();
        InstrumentLeg50SP2TestData.getInstance().populate2(grp.getInstrumentLeg());
    }

    public void check(InstrmtLegDerivSecListGroup expected, InstrmtLegDerivSecListGroup actual) {
        InstrumentLeg50SP2TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
    }
}
