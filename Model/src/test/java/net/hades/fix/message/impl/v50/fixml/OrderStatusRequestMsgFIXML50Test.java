/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderStatusRequestMsgFIXML50Test.java
 *
 * $Id: OrderStatusRequestMsgFIXML50Test.java,v 1.1 2011-01-24 10:50:01 vrotaru Exp $
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
import net.hades.fix.message.OrderStatusRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.OrderStatusRequestMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for OrderStatusRequestMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class OrderStatusRequestMsgFIXML50Test extends MsgTest {

    public OrderStatusRequestMsgFIXML50Test() {
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
     * Test of encode method, of class OrderStatusRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            OrderStatusRequestMsg msg = (OrderStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderStatusRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setClOrdID("X162773883");
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setSide(Side.Buy);
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

            OrderStatusRequestMsg dmsg = (OrderStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderStatusRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getClOrdID(), dmsg.getClOrdID());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getSide(), dmsg.getSide());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class OrderStatusRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            OrderStatusRequestMsg msg = (OrderStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderStatusRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            OrderStatusRequestMsg50TestData.getInstance().populate(msg);
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

            OrderStatusRequestMsg dmsg = (OrderStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderStatusRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            OrderStatusRequestMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}