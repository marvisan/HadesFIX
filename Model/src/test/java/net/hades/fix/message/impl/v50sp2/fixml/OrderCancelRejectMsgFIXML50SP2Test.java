/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRejectMsgFIXML50SP2Test.java
 *
 * $Id: OrderCancelRejectMsgFIXML50SP2Test.java,v 1.1 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderCancelRejectMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.OrderCancelRejectMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CxlRejResponseTo;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdStatus;

/**
 * Test suite for OrderCancelRejectMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class OrderCancelRejectMsgFIXML50SP2Test extends MsgTest {

    public OrderCancelRejectMsgFIXML50SP2Test() {
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
     * Test of encode method, of class OrderCancelRejectMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            OrderCancelRejectMsg msg = (OrderCancelRejectMsg) FIXMsgBuilder.build(MsgType.OrderCancelReject.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.setOrderID("ORDD7264363");
            msg.setClOrdID("X162773883");
            msg.setOrigClOrdID("DD7264363");
            msg.setOrdStatus(OrdStatus.Canceled);
            msg.setCxlRejResponseTo(CxlRejResponseTo.OrderCancelRequest);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP2);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            OrderCancelRejectMsg dmsg = (OrderCancelRejectMsg) FIXMsgBuilder.build(MsgType.OrderCancelReject.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getOrderID(), dmsg.getOrderID());
            assertEquals(msg.getClOrdID(), dmsg.getClOrdID());
            assertEquals(msg.getOrigClOrdID(), dmsg.getOrigClOrdID());
            assertEquals(msg.getOrdStatus(), dmsg.getOrdStatus());
            assertEquals(msg.getCxlRejResponseTo(), dmsg.getCxlRejResponseTo());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class OrderCancelRejectMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            OrderCancelRejectMsg msg = (OrderCancelRejectMsg) FIXMsgBuilder.build(MsgType.OrderCancelReject.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            OrderCancelRejectMsg50SP2TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP2);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            OrderCancelRejectMsg dmsg = (OrderCancelRejectMsg) FIXMsgBuilder.build(MsgType.OrderCancelReject.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            OrderCancelRejectMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}