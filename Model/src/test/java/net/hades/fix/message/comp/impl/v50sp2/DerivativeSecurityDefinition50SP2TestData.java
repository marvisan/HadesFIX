/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityDefinition50SP2TestData.java
 *
 * $Id: DerivativeSecurityDefinition50SP2TestData.java,v 1.1 2011-09-28 08:10:21 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.DerivativeSecurityDefinition;
import net.hades.fix.message.group.impl.v50sp1.DerivativeInstrAttribGroup50SP1TestData;
import net.hades.fix.message.group.impl.v50sp1.MarketSegmentGroup50SP1TestData;

/**
 * Test utility for DerivativeSecurityDefinition component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class DerivativeSecurityDefinition50SP2TestData extends MsgTest {

    private static final DerivativeSecurityDefinition50SP2TestData INSTANCE;

    static {
        INSTANCE = new DerivativeSecurityDefinition50SP2TestData();
    }

    public static DerivativeSecurityDefinition50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate(DerivativeSecurityDefinition comp) {
        comp.setDerivativeInstrument();
        DerivativeInstrument50SP2TestData.getInstance().populate(comp.getDerivativeInstrument());
        
        comp.setNoDerivativeInstrAttrib(2);
        DerivativeInstrAttribGroup50SP1TestData.getInstance().populate1(comp.getDerivativeInstrAttribGroups()[0]);
        DerivativeInstrAttribGroup50SP1TestData.getInstance().populate2(comp.getDerivativeInstrAttribGroups()[1]);
        
        comp.setNoMarketSegments(2);
        MarketSegmentGroup50SP1TestData.getInstance().populate1(comp.getMarketSegmentGroups()[0]);
        MarketSegmentGroup50SP1TestData.getInstance().populate2(comp.getMarketSegmentGroups()[1]);
    }

    public void check(DerivativeSecurityDefinition expected, DerivativeSecurityDefinition actual) throws Exception {
        DerivativeInstrument50SP2TestData.getInstance().check(expected.getDerivativeInstrument(), actual.getDerivativeInstrument());
        
        assertEquals(expected.getNoDerivativeInstrAttrib(), actual.getNoDerivativeInstrAttrib());
        DerivativeInstrAttribGroup50SP1TestData.getInstance().check(expected.getDerivativeInstrAttribGroups()[0], actual.getDerivativeInstrAttribGroups()[0]);
        DerivativeInstrAttribGroup50SP1TestData.getInstance().check(expected.getDerivativeInstrAttribGroups()[1], actual.getDerivativeInstrAttribGroups()[1]);
        
        assertEquals(expected.getNoMarketSegments(), actual.getNoMarketSegments());
        MarketSegmentGroup50SP1TestData.getInstance().check(expected.getMarketSegmentGroups()[0], actual.getMarketSegmentGroups()[0]);
        MarketSegmentGroup50SP1TestData.getInstance().check(expected.getMarketSegmentGroups()[1], actual.getMarketSegmentGroups()[1]);
    }
}
