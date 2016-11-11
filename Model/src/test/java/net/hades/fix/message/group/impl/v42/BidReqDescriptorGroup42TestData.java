/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidReqDescriptorGroup42TestData.java
 *
 * $Id: BidReqDescriptorGroup42TestData.java,v 1.1 2011-04-14 11:44:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.BidReqDescriptorGroup;
import net.hades.fix.message.type.BidDescriptorType;
import net.hades.fix.message.type.SideValueInd;

/**
 * Test utility for BidReqDescriptorGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class BidReqDescriptorGroup42TestData extends MsgTest {

    private static final BidReqDescriptorGroup42TestData INSTANCE;

    static {
        INSTANCE = new BidReqDescriptorGroup42TestData();
    }

    public static BidReqDescriptorGroup42TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(BidReqDescriptorGroup grp) throws UnsupportedEncodingException {
        grp.setBidDescriptorType(BidDescriptorType.Sector);
        grp.setBidDescriptor("BID-DESCR222");
        grp.setSideValueInd(SideValueInd.SideValue_1);
        grp.setLiquidityValue(33.44);
        grp.setLiquidityNumSecurities(2);
        grp.setLiquidityPctLow(10.2);
        grp.setLiquidityPctHigh(20.5);
        grp.setEFPTrackingError(0.55);
        grp.setFairValue(23.4);
        grp.setOutsideIndexPct(25.75);
        grp.setValueOfFutures(44.55);
    }

    public void populate2(BidReqDescriptorGroup grp) throws UnsupportedEncodingException {
        grp.setBidDescriptorType(BidDescriptorType.Country);
        grp.setBidDescriptor("BID-DESCR333");
        grp.setSideValueInd(SideValueInd.SideValue_2);
        grp.setLiquidityValue(33.55);
        grp.setLiquidityNumSecurities(3);
        grp.setLiquidityPctLow(10.6);
        grp.setLiquidityPctHigh(20.8);
        grp.setEFPTrackingError(0.75);
        grp.setFairValue(23.9);
        grp.setOutsideIndexPct(25.95);
        grp.setValueOfFutures(44.75);
    }

    public void check(BidReqDescriptorGroup expected, BidReqDescriptorGroup actual) throws Exception {
        assertEquals(expected.getBidDescriptorType(), actual.getBidDescriptorType());
        assertEquals(expected.getBidDescriptor(), actual.getBidDescriptor());
        assertEquals(expected.getSideValueInd(), actual.getSideValueInd());
        assertEquals(expected.getLiquidityValue(), actual.getLiquidityValue());
        assertEquals(expected.getLiquidityNumSecurities(), actual.getLiquidityNumSecurities());
        assertEquals(expected.getLiquidityPctLow(), actual.getLiquidityPctLow());
        assertEquals(expected.getLiquidityPctHigh(), actual.getLiquidityPctHigh());
        assertEquals(expected.getEFPTrackingError(), actual.getEFPTrackingError());
        assertEquals(expected.getFairValue(), actual.getFairValue());
        assertEquals(expected.getOutsideIndexPct(), actual.getOutsideIndexPct());
        assertEquals(expected.getValueOfFutures(), actual.getValueOfFutures());
    }
}
