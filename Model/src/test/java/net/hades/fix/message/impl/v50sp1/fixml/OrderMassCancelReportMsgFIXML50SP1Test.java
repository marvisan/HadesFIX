/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsgFIXML50SP1Test.java
 *
 * $Id: OrderMassCancelReportMsgFIXML50SP1Test.java,v 1.1 2011-05-07 06:58:54 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.fixml;

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
import net.hades.fix.message.impl.v50sp1.data.OrderMassCancelReportMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MassCancelRequestType;
import net.hades.fix.message.type.MassCancelResponse;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for OrderMassCancelReportMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/05/2011, 20:57:03
 */
public class OrderMassCancelReportMsgFIXML50SP1Test extends MsgTest {

    public OrderMassCancelReportMsgFIXML50SP1Test() {
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
     * Test of encode method, of class OrderMassCancelReportMsg for FIXML 5.0.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            msg.setOrderID("X162773883");
            msg.setMassActionReportID("REP_666777");
            msg.setMassCancelRequestType(MassCancelRequestType.CancelAllOrders);
            msg.setMassCancelResponse(MassCancelResponse.CancelAllOrders);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP1);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            OrderMassCancelReportMsg dmsg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getOrderID(), dmsg.getOrderID());
            assertEquals(msg.getMassActionReportID(), dmsg.getMassActionReportID());
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
            OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP1);
            OrderMassCancelReportMsg50SP1TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP1);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            OrderMassCancelReportMsg dmsg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            OrderMassCancelReportMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}