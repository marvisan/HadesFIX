/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionQtyGroup44TestData.java
 *
 * $Id: PositionQtyGroup44TestData.java,v 1.2 2011-01-15 02:10:12 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.PositionQtyGroup;
import net.hades.fix.message.type.PosQtyStatus;
import net.hades.fix.message.type.PosType;

/**
 * Test utility for PositionQtyGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class PositionQtyGroup44TestData extends MsgTest {

    private static final PositionQtyGroup44TestData INSTANCE;

    static {
        INSTANCE = new PositionQtyGroup44TestData();
    }

    public static PositionQtyGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(PositionQtyGroup grp) throws UnsupportedEncodingException {
        grp.setPosType(PosType.DeliveryQty);
        grp.setLongQty(34.5d);
        grp.setShortQty(90.4d);
        grp.setPosQtyStatus(PosQtyStatus.Submitted);

        grp.setNestedParties();
        grp.getNestedParties().setNoNestedPartyIDs(2);
        NestedPartyGroup44TestData.getInstance().populate1(grp.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup44TestData.getInstance().populate2(grp.getNestedParties().getNestedPartyIDGroups()[1]);
    }

    public void populate2(PositionQtyGroup grp) throws UnsupportedEncodingException {
        grp.setPosType(PosType.AdjustmentQty);
        grp.setLongQty(54.5d);
        grp.setShortQty(30.4d);
        grp.setPosQtyStatus(PosQtyStatus.Rejected);
        
        grp.setNestedParties();
        grp.getNestedParties().setNoNestedPartyIDs(2);
        NestedPartyGroup44TestData.getInstance().populate1(grp.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup44TestData.getInstance().populate2(grp.getNestedParties().getNestedPartyIDGroups()[1]);
    }

    public void check(PositionQtyGroup expected, PositionQtyGroup actual) throws Exception {
        assertEquals(expected.getPosType(), actual.getPosType());
        assertEquals(expected.getLongQty(), actual.getLongQty());
        assertEquals(expected.getShortQty(), actual.getShortQty());
        assertEquals(expected.getPosQtyStatus(), actual.getPosQtyStatus());

        assertEquals(expected.getNestedParties().getNoNestedPartyIDs(), actual.getNestedParties().getNoNestedPartyIDs());
        NestedPartyGroup44TestData.getInstance().check(expected.getNestedParties().getNestedPartyIDGroups()[0],
                actual.getNestedParties().getNestedPartyIDGroups()[0]);
        NestedPartyGroup44TestData.getInstance().check(expected.getNestedParties().getNestedPartyIDGroups()[1],
                actual.getNestedParties().getNestedPartyIDGroups()[1]);
    }
}
