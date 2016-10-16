/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataSnapshotMsgFIXML50Test.java
 *
 * $Id: MarketDataSnapshotMsgFIXML50Test.java,v 1.1 2010-02-04 09:37:50 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.MarketDataSnapshotMsg50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for MarketDataSnapshot50Msg class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class MarketDataSnapshotMsgFIXML50Test extends MsgTest {

    public MarketDataSnapshotMsgFIXML50Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class MassQuoteAckMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableFixml();
        try {
            MarketDataSnapshotMsg msg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            MarketDataSnapshotMsg50TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);

            MarketDataSnapshotMsg dmsg = (MarketDataSnapshotMsg) FIXMsgBuilder.build(MsgType.MarketDataSnapshot.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            dmsg.fromFixml(fixml);
            MarketDataSnapshotMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}