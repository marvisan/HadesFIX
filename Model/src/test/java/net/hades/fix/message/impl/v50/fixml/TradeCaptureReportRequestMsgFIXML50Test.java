/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestMsgFIXML44Test.java
 *
 * $Id: TradeCaptureReportRequestMsgFIXML50Test.java,v 1.1 2011-10-08 08:43:04 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.TradeCaptureReportRequestMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.TradeRequestType;

/**
 * Test suite for TradeCaptureReportRequestMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class TradeCaptureReportRequestMsgFIXML50Test extends MsgTest {

    public TradeCaptureReportRequestMsgFIXML50Test() {
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
     * Test of encode method, of class TradeCaptureReportRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportRequestMsg msg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setTradeRequestID("X162773883");
            msg.setTradeRequestType(TradeRequestType.AllTrades);
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

            TradeCaptureReportRequestMsg dmsg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getTradeRequestID(), dmsg.getTradeRequestID());
            assertEquals(msg.getTradeRequestType(), dmsg.getTradeRequestType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class TradeCaptureReportRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportRequestMsg msg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            TradeCaptureReportRequestMsg50TestData.getInstance().populate(msg);
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

            TradeCaptureReportRequestMsg dmsg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            TradeCaptureReportRequestMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}