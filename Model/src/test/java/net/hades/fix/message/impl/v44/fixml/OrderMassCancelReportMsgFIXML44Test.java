/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsgFIXML44Test.java
 *
 * $Id: OrderMassCancelReportMsgFIXML44Test.java,v 1.1 2011-05-07 06:58:56 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderMassCancelReportMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.OrderMassCancelReportMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MassCancelRequestType;
import net.hades.fix.message.type.MassCancelResponse;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for OrderMassCancelReportMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/05/2011, 20:57:03
 */
public class OrderMassCancelReportMsgFIXML44Test extends MsgTest {

    public OrderMassCancelReportMsgFIXML44Test() {
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
     * Test of encode method, of class OrderMassCancelReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setOrderID("X162773883");
            msg.setMassCancelRequestType(MassCancelRequestType.CancelAllOrders);
            msg.setMassCancelResponse(MassCancelResponse.CancelAllOrders);
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

            OrderMassCancelReportMsg dmsg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getOrderID(), dmsg.getOrderID());
            assertEquals(msg.getMassCancelRequestType(), dmsg.getMassCancelRequestType());
            assertEquals(msg.getMassCancelResponse(), dmsg.getMassCancelResponse());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class OrderMassCancelReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            OrderMassCancelReportMsg44TestData.getInstance().populate(msg);
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

            OrderMassCancelReportMsg dmsg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            OrderMassCancelReportMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}