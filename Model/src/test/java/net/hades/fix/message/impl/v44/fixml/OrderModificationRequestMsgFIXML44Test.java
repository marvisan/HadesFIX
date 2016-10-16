/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderModificationRequestMsgFIXML44Test.java
 *
 * $Id: OrderModificationRequestMsgFIXML44Test.java,v 1.1 2011-01-21 10:23:13 vrotaru Exp $
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
import net.hades.fix.message.OrderModificationRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.OrderModificationRequestMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for OrderModificationRequestMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class OrderModificationRequestMsgFIXML44Test extends MsgTest {

    public OrderModificationRequestMsgFIXML44Test() {
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
     * Test of encode method, of class OrderModificationRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            OrderModificationRequestMsg msg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setOrigClOrdID("DD7264363");
            msg.setClOrdID("X162773883");
            msg.setHandlInst(HandlInst.ManualOrder);
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setSide(Side.Buy);
            msg.setOrderQtyData();
            msg.getOrderQtyData().setOrderQty(10.0d);
            msg.setOrdType(OrdType.Stop);
            msg.setTransactTime(new Date());
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

            OrderModificationRequestMsg dmsg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_4);
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
     * Test of encode method, of class OrderModificationRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            OrderModificationRequestMsg msg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            OrderModificationRequestMsg44TestData.getInstance().populate(msg);
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

            OrderModificationRequestMsg dmsg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            OrderModificationRequestMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}