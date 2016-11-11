/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportMsgFIXML50Test.java
 *
 * $Id: TradeCaptureReportMsgFIXML50Test.java,v 1.1 2011-10-25 08:29:20 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.TradeCaptureReportMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for TradeCaptureReportMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class TradeCaptureReportMsgFIXML50Test extends MsgTest {

    public TradeCaptureReportMsgFIXML50Test() {
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
     * Test of encode method, of class TradeCaptureReportMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportMsg msg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.setTradeReportID("X162773883");
            msg.setLastQty(23.5d);
            msg.setLastPx(45.33d);
            msg.setTradeDate(new Date());
            msg.setInstrument();
            msg.getInstrument().setSymbol("MOT");
            msg.setNoSides(1);
            msg.getTrdCapRptSideGroups()[0].setSide(Side.Buy);
            msg.getTrdCapRptSideGroups()[0].setOrderID("ORD_2122");
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

            TradeCaptureReportMsg dmsg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getTradeReportID(), dmsg.getTradeReportID());
            assertEquals(msg.getPreviouslyReported(), dmsg.getPreviouslyReported());
            assertEquals(msg.getLastQty(), dmsg.getLastQty());
            assertEquals(msg.getLastPx(), dmsg.getLastPx());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class TradeCaptureReportMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportMsg msg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            TradeCaptureReportMsg50TestData.getInstance().populate(msg);
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

            TradeCaptureReportMsg dmsg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            TradeCaptureReportMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}