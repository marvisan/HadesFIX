/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CpctyConfGroup44TestData.java
 *
 * $Id: CpctyConfGroup44TestData.java,v 1.1 2011-04-27 01:09:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.CpctyConfGroup;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.OrderRestrictions;

/**
 * Test utility for CpctyConfGroup44 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class CpctyConfGroup44TestData extends MsgTest {

    private static final CpctyConfGroup44TestData INSTANCE;

    static {
        INSTANCE = new CpctyConfGroup44TestData();
    }

    public static CpctyConfGroup44TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(CpctyConfGroup grp) throws UnsupportedEncodingException {
        grp.setOrderCapacity(OrderCapacity.Proprietary);
        grp.setOrderRestrictions(OrderRestrictions.IndexArbitrage.getValue());
        grp.setOrderCapacityQty(44.66d);
    }

    public void populate2(CpctyConfGroup grp) throws UnsupportedEncodingException {
        grp.setOrderCapacity(OrderCapacity.Agency);
        grp.setOrderRestrictions(OrderRestrictions.ForeignEntity.getValue());
        grp.setOrderCapacityQty(22.55);
    }

    public void check(CpctyConfGroup expected, CpctyConfGroup actual) throws Exception {
        assertEquals(expected.getOrderCapacity(), actual.getOrderCapacity());
        assertEquals(expected.getOrderRestrictions(), actual.getOrderRestrictions());
        assertEquals(expected.getOrderCapacityQty(), actual.getOrderCapacityQty());
    }
}
