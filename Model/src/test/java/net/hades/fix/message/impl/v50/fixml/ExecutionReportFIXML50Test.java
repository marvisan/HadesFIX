/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportFIXML50Test.java
 *
 * $Id: ExecutionReportFIXML50Test.java,v 1.1 2011-01-15 02:10:11 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.ExecutionReportMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.Side;

/**
 * Test suite for ExecutionReport50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class ExecutionReportFIXML50Test extends MsgTest {

    public ExecutionReportFIXML50Test() {
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
     * Test of encode method, of class ExecutionReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            ExecutionReportMsg msg = (ExecutionReportMsg) FIXMsgBuilder.build(MsgType.ExecutionReport.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setOrderID("X162773883");
            msg.setExecType(ExecType.New);
            msg.setOrdStatus(OrdStatus.New);
            msg.setInstrument();
            msg.setExecID("DDD22242424");
            msg.getInstrument().setSymbol("SUN");
            msg.setSide(Side.Buy);
            msg.setLeavesQty(23.33d);
            msg.setCumQty(22.0d);
            msg.setAvgPx(22.55d);
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

            ExecutionReportMsg dmsg = (ExecutionReportMsg) FIXMsgBuilder.build(MsgType.ExecutionReport.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getClOrdID(), dmsg.getClOrdID());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getSide(), dmsg.getSide());
            assertEquals(msg.getOrdType(), dmsg.getOrdType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class ExecutionReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            ExecutionReportMsg msg = (ExecutionReportMsg) FIXMsgBuilder.build(MsgType.ExecutionReport.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            ExecutionReportMsg50TestData.getInstance().populate(msg);
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

            ExecutionReportMsg dmsg = (ExecutionReportMsg) FIXMsgBuilder.build(MsgType.ExecutionReport.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            ExecutionReportMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}