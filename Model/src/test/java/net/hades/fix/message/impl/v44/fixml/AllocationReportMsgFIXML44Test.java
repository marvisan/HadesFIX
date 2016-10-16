/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationReportMsgFIXML44Test.java
 *
 * $Id: AllocationReportMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationReportMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.AllocationReportMsg44TestData;
import net.hades.fix.message.type.AllocNoOrdersType;
import net.hades.fix.message.type.AllocReportType;
import net.hades.fix.message.type.AllocStatus;
import net.hades.fix.message.type.AllocTransType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for AllocationReportMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class AllocationReportMsgFIXML44Test extends MsgTest {

    public AllocationReportMsgFIXML44Test() {
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
     * Test of encode method, of class AllocationReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            AllocationReportMsg msg = (AllocationReportMsg) FIXMsgBuilder.build(MsgType.AllocationReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setAllocReportID("AL33444");
            msg.setAllocTransType(AllocTransType.Calculated);
            msg.setAllocReportType(AllocReportType.SellsideUsingPreliminary);
            msg.setAllocStatus(AllocStatus.Accepted);
            msg.setAllocNoOrdersType(AllocNoOrdersType.ExplicitListProvided);
            msg.setSide(Side.Buy);
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setQuantity(12.0d);
            msg.setAvgPx(12.50d);
            msg.setTradeDate(new Date());
            
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

            AllocationReportMsg dmsg = (AllocationReportMsg) FIXMsgBuilder.build(MsgType.AllocationReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getAllocReportID(), dmsg.getAllocReportID());
            assertEquals(msg.getAllocTransType(), dmsg.getAllocTransType());
            assertEquals(msg.getAllocReportType(), dmsg.getAllocReportType());
            assertEquals(msg.getAllocNoOrdersType(), dmsg.getAllocNoOrdersType());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getQuantity(), dmsg.getQuantity());
            assertEquals(msg.getAvgPx(), dmsg.getAvgPx());
            assertDateEquals(msg.getTradeDate(), dmsg.getTradeDate());
            assertEquals(msg.getSide(), dmsg.getSide());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AllocationReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            AllocationReportMsg msg = (AllocationReportMsg) FIXMsgBuilder.build(MsgType.AllocationReport.getValue(), BeginString.FIX_4_4);
            AllocationReportMsg44TestData.getInstance().populate(msg);
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

            AllocationReportMsg dmsg = (AllocationReportMsg) FIXMsgBuilder.build(MsgType.AllocationReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            AllocationReportMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}