/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportMsgFIXML44Test.java
 *
 * $Id: TradeCaptureReportMsgFIXML44Test.java,v 1.2 2011-10-29 01:31:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

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
import net.hades.fix.message.impl.v44.data.TradeCaptureReportMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for TradeCaptureReportMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class TradeCaptureReportMsgFIXML44Test extends MsgTest {

    public TradeCaptureReportMsgFIXML44Test() {
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
     * Test of encode method, of class TradeCaptureReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportMsg msg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setTradeReportID("X162773883");
            msg.setPreviouslyReported(Boolean.FALSE);
            msg.setLastQty(23.5d);
            msg.setLastPx(45.33d);
            msg.setTradeDate(new Date());
            msg.setTransactTime(new Date());
            msg.setInstrument();
            msg.getInstrument().setSymbol("MOT");
            msg.setNoSides(1);
            msg.getTrdCapRptSideGroups()[0].setSide(Side.Buy);
            msg.getTrdCapRptSideGroups()[0].setOrderID("ORD_2122");
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V44);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            TradeCaptureReportMsg dmsg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_4_4);
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
     * Test of encode method, of class TradeCaptureReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            TradeCaptureReportMsg msg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            TradeCaptureReportMsg44TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V44);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            TradeCaptureReportMsg dmsg = (TradeCaptureReportMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            TradeCaptureReportMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}