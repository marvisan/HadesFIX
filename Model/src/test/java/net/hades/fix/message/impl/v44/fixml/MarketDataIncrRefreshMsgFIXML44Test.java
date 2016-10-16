/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsgFIXML44Test.java
 *
 * $Id: MarketDataIncrRefreshMsgFIXML44Test.java,v 1.1 2010-02-04 09:37:51 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.MarketDataIncrRefreshMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for MarketDataIncrRefreshMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class MarketDataIncrRefreshMsgFIXML44Test extends MsgTest {

    public MarketDataIncrRefreshMsgFIXML44Test() {
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
            MarketDataIncrRefreshMsg msg = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            MarketDataIncrRefreshMsg44TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);

            MarketDataIncrRefreshMsg dmsg = (MarketDataIncrRefreshMsg) FIXMsgBuilder.build(MsgType.MarketDataIncrRefresh.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            MarketDataIncrRefreshMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}