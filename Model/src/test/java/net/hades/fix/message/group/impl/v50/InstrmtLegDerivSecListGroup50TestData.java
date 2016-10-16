/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegDerivSecListGroup50TestData.java
 *
 * $Id: InstrmtLegDerivSecListGroup50TestData.java,v 1.1 2011-09-28 08:10:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.io.UnsupportedEncodingException;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50TestData;
import net.hades.fix.message.group.InstrmtLegDerivSecListGroup;

/**
 * Test utility for InstrmtLegDerivSecListGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class InstrmtLegDerivSecListGroup50TestData extends MsgTest {

    private static final InstrmtLegDerivSecListGroup50TestData INSTANCE;

    static {
        INSTANCE = new InstrmtLegDerivSecListGroup50TestData();
    }

    public static InstrmtLegDerivSecListGroup50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(InstrmtLegDerivSecListGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate1(grp.getInstrumentLeg());
    }

    public void populate2(InstrmtLegDerivSecListGroup grp) throws UnsupportedEncodingException {
        grp.setInstrumentLeg();
        InstrumentLeg50TestData.getInstance().populate2(grp.getInstrumentLeg());
    }

    public void check(InstrmtLegDerivSecListGroup expected, InstrmtLegDerivSecListGroup actual) throws Exception {
        InstrumentLeg50TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
    }
}
