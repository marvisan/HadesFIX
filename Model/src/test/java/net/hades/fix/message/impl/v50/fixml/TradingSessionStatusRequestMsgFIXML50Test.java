/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusRequestMsgFIXML50Test.java
 *
 * $Id: TradingSessionStatusRequestMsgFIXML50Test.java,v 1.1 2011-04-22 01:59:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradingSessionStatusRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.TradingSessionStatusRequestMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test suite for TradingSessionStatusRequestMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class TradingSessionStatusRequestMsgFIXML50Test extends MsgTest {

    public TradingSessionStatusRequestMsgFIXML50Test() {
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
     * Test of encode method, of class TradingSessionStatusRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            TradingSessionStatusRequestMsg msg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setTradSesReqID("REQ_11111");
            msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);

            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            TradingSessionStatusRequestMsg dmsg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getTradSesReqID(), dmsg.getTradSesReqID());
            assertEquals(msg.getSubscriptionRequestType(), dmsg.getSubscriptionRequestType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class TradingSessionStatusRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            TradingSessionStatusRequestMsg msg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            TradingSessionStatusRequestMsg50TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            TradingSessionStatusRequestMsg dmsg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            TradingSessionStatusRequestMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}