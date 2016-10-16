/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegOrdGroup43TestData.java
 *
 * $Id: LegOrdGroup43TestData.java,v 1.1 2011-09-08 08:54:48 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.InstrumentLeg43TestData;
import net.hades.fix.message.comp.impl.v43.NestedParties43TestData;
import net.hades.fix.message.group.LegOrdGroup;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SettlType;

/**
 * Test utility for LegOrdGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class LegOrdGroup43TestData extends MsgTest {

    private static final LegOrdGroup43TestData INSTANCE;

    static {
        INSTANCE = new LegOrdGroup43TestData();
    }

    public static LegOrdGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(LegOrdGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        group.setInstrumentLeg();
        InstrumentLeg43TestData.getInstance().populate1(group.getInstrumentLeg());
        
        group.setLegPositionEffect(PositionEffect.Close);
        group.setLegCoveredOrUncovered(CoveredOrUncovered.Uncovered);

        group.setNestedParties();
        NestedParties43TestData.getInstance().populate(group.getNestedParties());

        group.setLegRefID("LEG_REF_1");
        group.setLegPrice(23.55);
        group.setLegSettlType(SettlType.Cash.getValue());
        cal.set(2010, 3, 14, 12, 13, 13);
        group.setLegSettlDate(cal.getTime());
    }

    public void populate2(LegOrdGroup group) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        group.setInstrumentLeg();
        InstrumentLeg43TestData.getInstance().populate2(group.getInstrumentLeg());
        
        group.setLegPositionEffect(PositionEffect.Open);
        group.setLegCoveredOrUncovered(CoveredOrUncovered.Covered);

        group.setNestedParties();
        NestedParties43TestData.getInstance().populate(group.getNestedParties());

        group.setLegRefID("LEG_REF_2");
        group.setLegPrice(33.55);
        group.setLegSettlType(SettlType.NextDay.getValue());
        cal.set(2011, 3, 12, 12, 13, 13);
        group.setLegSettlDate(cal.getTime());
    }

    public void check(LegOrdGroup expected, LegOrdGroup actual) throws Exception {
        InstrumentLeg43TestData.getInstance().check(expected.getInstrumentLeg(), actual.getInstrumentLeg());
        
        assertEquals(expected.getLegPositionEffect(), actual.getLegPositionEffect());
        assertEquals(expected.getLegCoveredOrUncovered(), actual.getLegCoveredOrUncovered());

        NestedParties43TestData.getInstance().check(expected.getNestedParties(), expected.getNestedParties());

        assertEquals(expected.getLegRefID(), actual.getLegRefID());
        assertEquals(expected.getLegPrice(), actual.getLegPrice());
        assertEquals(expected.getLegSettlType(), actual.getLegSettlType());
        assertDateEquals(expected.getLegSettlDate(), actual.getLegSettlDate());
    }
}
