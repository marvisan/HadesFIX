/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegSecListGroup50SP1TestData.java
 *
 * $Id: InstrmtLegSecListGroup50SP1TestData.java,v 1.1 2011-04-29 03:11:02 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.LegBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.LegStipulations50TestData;
import net.hades.fix.message.comp.impl.v50sp1.InstrumentLeg50SP1TestData;
import net.hades.fix.message.group.InstrmtLegSecListGroup;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for InstrmtLegSecListGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtLegSecListGroup50SP1TestData extends MsgTest {

    private static final InstrmtLegSecListGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new InstrmtLegSecListGroup50SP1TestData();
    }

    public static InstrmtLegSecListGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtLegSecListGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate1(grp.getInstrumentLeg());

        grp.setLegSwapType(LegSwapType.ParForPar);
        grp.setLegSettlType(SettlType.Cash.getValue());

        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());

        grp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(grp.getLegBenchmarkCurveData());
    }

    public void populate2(InstrmtLegSecListGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50SP1TestData.getInstance().populate2(grp.getInstrumentLeg());

        grp.setLegSwapType(LegSwapType.Risk);
        grp.setLegSettlType(SettlType.Future.getValue());

        grp.setLegStipulations();
        LegStipulations50TestData.getInstance().populate(grp.getLegStipulations());

        grp.setLegBenchmarkCurveData();
        LegBenchmarkCurveData50TestData.getInstance().populate(grp.getLegBenchmarkCurveData());
    }

    public void check(InstrmtLegSecListGroup expected, InstrmtLegSecListGroup actual) throws Exception {
        InstrumentLeg50SP1TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());

        assertEquals(expected.getLegSwapType(), actual.getLegSwapType());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());

        LegStipulations50TestData.getInstance().check(expected.getLegStipulations(), actual.getLegStipulations());

        LegBenchmarkCurveData50TestData.getInstance().check(expected.getLegBenchmarkCurveData(), actual.getLegBenchmarkCurveData());
    }
}
