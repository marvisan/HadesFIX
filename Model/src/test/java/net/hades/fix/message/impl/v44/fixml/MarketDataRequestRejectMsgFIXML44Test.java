/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestRejectMsgFIXML44Test.java
 *
 * $Id: MarketDataRequestRejectMsgFIXML44Test.java,v 1.1 2010-02-04 09:37:51 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataRequestRejectMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.MarketDataRequestRejectMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for MarketDataRequestRejectMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class MarketDataRequestRejectMsgFIXML44Test extends MsgTest {

    public MarketDataRequestRejectMsgFIXML44Test() {
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
     * Test of encode method, of class MarketDataIncrRefreshMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            MarketDataRequestRejectMsg msg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            MarketDataRequestRejectMsg44TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);

            MarketDataRequestRejectMsg dmsg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            MarketDataRequestRejectMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}