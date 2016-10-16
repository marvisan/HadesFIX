/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BatchSetMsg50TestData.java
 *
 * $Id: BatchSetMsg50TestData.java,v 1.1 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.*;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.comp.Batch;
import net.hades.fix.message.impl.v44.data.SecurityTypesMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test utility for BatchSetMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class BatchSetMsg50TestData extends MsgTest {

    private static final BatchSetMsg50TestData INSTANCE;

    static {
        INSTANCE = new BatchSetMsg50TestData();
    }

    public static BatchSetMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(BatchSetMsg batchSet) throws Exception {
        SecurityTypeRequestMsg m1b1 = (SecurityTypeRequestMsg) FIXMsgBuilder.build(MsgType.SecurityTypeRequest.getValue(), BeginString.FIX_5_0);
        SecurityTypeRequestMsg50TestData.getInstance().populate(m1b1);
        SecurityStatusRequestMsg m2b1 = (SecurityStatusRequestMsg) FIXMsgBuilder.build(MsgType.SecurityStatusRequest.getValue(), BeginString.FIX_5_0);
        SecurityStatusRequestMsg50TestData.getInstance().populate(m2b1);
        Batch b1 = batchSet.addBatch();
        b1.setID("BATCH_1");
        b1.addMessage(m1b1);
        b1.addMessage(m2b1);
        b1.setHeader();
        TestUtils.populate44BatcHeader(b1.getHeader());
    }

    public void populate2(BatchSetMsg batchSet) throws Exception {
        SecurityTypeRequestMsg m1b1 = (SecurityTypeRequestMsg) FIXMsgBuilder.build(MsgType.SecurityTypeRequest.getValue(), BeginString.FIX_5_0);
        SecurityTypeRequestMsg50TestData.getInstance().populate(m1b1);
        SecurityStatusRequestMsg m2b1 = (SecurityStatusRequestMsg) FIXMsgBuilder.build(MsgType.SecurityStatusRequest.getValue(), BeginString.FIX_5_0);
        SecurityStatusRequestMsg50TestData.getInstance().populate(m2b1);
        Batch b1 = batchSet.addBatch();
        b1.setID("BATCH_1");
        b1.addMessage(m1b1);
        b1.addMessage(m2b1);
        b1.setHeader();
        TestUtils.populate44BatcHeader(b1.getHeader());
        SecurityTypesMsg m1b2 = (SecurityTypesMsg) FIXMsgBuilder.build(MsgType.SecurityTypes.getValue(), BeginString.FIX_5_0);
        SecurityTypesMsg44TestData.getInstance().populate(m1b2);
        SecurityStatusMsg m2b2 = (SecurityStatusMsg) FIXMsgBuilder.build(MsgType.SecurityStatus.getValue(), BeginString.FIX_5_0);
        SecurityStatusMsg50TestData.getInstance().populate(m2b2);
        Batch b2 = batchSet.addBatch();
        b2.setID("BATCH_2");
        b2.addMessage(m1b2);
        b2.addMessage(m2b2);
        b2.setHeader();
        TestUtils.populate44BatcHeader(b2.getHeader());
    }

    public void check1(BatchSetMsg expected, BatchSetMsg actual) throws Exception {
        assertEquals(expected.getNoBatches(), actual.getNoBatches());
        assertEquals(expected.getBatches().length, actual.getBatches().length);
        
        assertEquals(expected.getBatches()[0].getMessages()[0].getClass().getName(),
                actual.getBatches()[0].getMessages()[0].getClass().getName());
        assertEquals(expected.getBatches()[0].getMessages()[1].getClass().getName(),
                actual.getBatches()[0].getMessages()[1].getClass().getName());
        SecurityTypeRequestMsg50TestData.getInstance().check((SecurityTypeRequestMsg) expected.getBatches()[0].getMessages()[0],
                (SecurityTypeRequestMsg) actual.getBatches()[0].getMessages()[0]);
        SecurityStatusRequestMsg50TestData.getInstance().check((SecurityStatusRequestMsg) expected.getBatches()[0].getMessages()[1],
                (SecurityStatusRequestMsg) actual.getBatches()[0].getMessages()[1]);
    }

    public void check2(BatchSetMsg expected, BatchSetMsg actual) throws Exception {
        assertEquals(expected.getNoBatches(), actual.getNoBatches());
        assertEquals(expected.getBatches().length, actual.getBatches().length);

        assertEquals(expected.getBatches()[0].getMessages()[0].getClass().getName(),
                actual.getBatches()[0].getMessages()[0].getClass().getName());
        assertEquals(expected.getBatches()[0].getMessages()[1].getClass().getName(),
                actual.getBatches()[0].getMessages()[1].getClass().getName());
        SecurityTypeRequestMsg50TestData.getInstance().check((SecurityTypeRequestMsg) expected.getBatches()[0].getMessages()[0],
                (SecurityTypeRequestMsg) actual.getBatches()[0].getMessages()[0]);
        SecurityStatusRequestMsg50TestData.getInstance().check((SecurityStatusRequestMsg) expected.getBatches()[0].getMessages()[1],
                (SecurityStatusRequestMsg) actual.getBatches()[0].getMessages()[1]);

        assertEquals(expected.getBatches()[1].getMessages()[0].getClass().getName(),
                actual.getBatches()[1].getMessages()[0].getClass().getName());
        assertEquals(expected.getBatches()[1].getMessages()[1].getClass().getName(),
                actual.getBatches()[1].getMessages()[1].getClass().getName());
        SecurityTypesMsg44TestData.getInstance().check((SecurityTypesMsg) expected.getBatches()[1].getMessages()[0],
                (SecurityTypesMsg) actual.getBatches()[1].getMessages()[0]);
        SecurityStatusMsg50TestData.getInstance().check((SecurityStatusMsg) expected.getBatches()[1].getMessages()[1],
                (SecurityStatusMsg) actual.getBatches()[1].getMessages()[1]);
    }
}
