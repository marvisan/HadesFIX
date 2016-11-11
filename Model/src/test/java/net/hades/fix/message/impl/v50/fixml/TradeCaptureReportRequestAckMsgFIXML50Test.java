/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestAckMsgFIXML50Test.java
 *
 * $Id: TradeCaptureReportRequestAckMsgFIXML44Test.java,v 1.1 2011-10-08 08:43:04 vrotaru Exp $
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
import net.hades.fix.message.TradeCaptureReportRequestAckMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.TradeCaptureReportRequestAckMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.TradeRequestResult;
import net.hades.fix.message.type.TradeRequestStatus;
import net.hades.fix.message.type.TradeRequestType;

/**
 * Test suite for TradeCaptureReportRequestAckMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class TradeCaptureReportRequestAckMsgFIXML50Test extends MsgTest {

    public TradeCaptureReportRequestAckMsgFIXML50Test() {
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
     * Test of encode method, of class TradeCaptureReportRequestAckMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportRequestAckMsg msg = (TradeCaptureReportRequestAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequestAck.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setTradeRequestID("X162773883");
            msg.setTradeRequestType(TradeRequestType.AllTrades);
            msg.setTradeRequestResult(TradeRequestResult.Successful);
            msg.setTradeRequestStatus(TradeRequestStatus.Completed);
            msg.setInstrument();
            msg.getInstrument().setSymbol("MOT");
            
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

            TradeCaptureReportRequestAckMsg dmsg = (TradeCaptureReportRequestAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequestAck.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getTradeRequestID(), dmsg.getTradeRequestID());
            assertEquals(msg.getTradeRequestType(), dmsg.getTradeRequestType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class TradeCaptureReportRequestAckMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportRequestAckMsg msg = (TradeCaptureReportRequestAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequestAck.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            TradeCaptureReportRequestAckMsg50TestData.getInstance().populate(msg);
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

            TradeCaptureReportRequestAckMsg dmsg = (TradeCaptureReportRequestAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequestAck.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            TradeCaptureReportRequestAckMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}