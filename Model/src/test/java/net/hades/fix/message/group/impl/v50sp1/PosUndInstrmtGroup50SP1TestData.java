/*
 *   Copyright (c) 2006-20011 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosUndInstrmtGroup50SP1TestData.java
 *
 * $Id: PosUndInstrmtGroup44TestData.java,v 1.2 2011-10-29 09:42:10 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.PosUndInstrmtGroup;
import net.hades.fix.message.group.impl.v50.UnderlyingAmountGroup50TestData;
import net.hades.fix.message.type.SettlPriceType;

/**
 * Test utility for PosUndInstrmtGroup50SP1 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/12/2011, 10:08:38 AM
 */
public class PosUndInstrmtGroup50SP1TestData extends MsgTest {

    private static final PosUndInstrmtGroup50SP1TestData INSTANCE;

    static {
        INSTANCE = new PosUndInstrmtGroup50SP1TestData();
    }

    public static PosUndInstrmtGroup50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PosUndInstrmtGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setUnderlyingSettlPrice(76.32d);
        grp.setUnderlyingSettlPriceType(SettlPriceType.Theoretical);
        grp.setUnderlyingDeliveryAmount(56.33d);
        
        grp.setNoUnderlyingAmounts(2);
        UnderlyingAmountGroup50TestData.getInstance().populate1(grp.getUnderlyingAmountGroups()[0]);
        UnderlyingAmountGroup50TestData.getInstance().populate2(grp.getUnderlyingAmountGroups()[1]);
    }

    public void populate2(PosUndInstrmtGroup grp) throws UnsupportedEncodingException {
        grp.setUnderlyingInstrument();
        UnderlyingInstrument50SP1TestData.getInstance().populate1(grp.getUnderlyingInstrument());

        grp.setUnderlyingSettlPrice(36.32d);
        grp.setUnderlyingSettlPriceType(SettlPriceType.Final);
        
        grp.setNoUnderlyingAmounts(2);
        UnderlyingAmountGroup50TestData.getInstance().populate1(grp.getUnderlyingAmountGroups()[0]);
        UnderlyingAmountGroup50TestData.getInstance().populate2(grp.getUnderlyingAmountGroups()[1]);
    }

    public void check(PosUndInstrmtGroup expected, PosUndInstrmtGroup actual) throws Exception {
        UnderlyingInstrument50SP1TestData.getInstance().check(expected.getUnderlyingInstrument(), actual.getUnderlyingInstrument());

        assertEquals(expected.getUnderlyingSettlPrice(), actual.getUnderlyingSettlPrice());
        assertEquals(expected.getUnderlyingSettlPriceType(), actual.getUnderlyingSettlPriceType());
        
        assertEquals(expected.getNoUnderlyingAmounts(), actual.getNoUnderlyingAmounts());
        UnderlyingAmountGroup50TestData.getInstance().check(expected.getUnderlyingAmountGroups()[0], actual.getUnderlyingAmountGroups()[0]);
        UnderlyingAmountGroup50TestData.getInstance().check(expected.getUnderlyingAmountGroups()[1], actual.getUnderlyingAmountGroups()[1]);
    }
}
