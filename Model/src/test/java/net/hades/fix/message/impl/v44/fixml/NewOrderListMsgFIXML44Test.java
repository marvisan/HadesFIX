/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsgFIXML44Test.java
 *
 * $Id: NewOrderListMsgFIXML44Test.java,v 1.1 2011-02-02 10:03:16 vrotaru Exp $
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
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v44.OrderListGroup44TestData;
import net.hades.fix.message.impl.v44.data.NewOrderListMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for NewOrderListMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class NewOrderListMsgFIXML44Test extends MsgTest {

    public NewOrderListMsgFIXML44Test() {
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
     * Test of encode method, of class NewOrderListMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            NewOrderListMsg msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setListID("X162773883");
            msg.setBidType(BidType.Disclosed);
            msg.setTotNoOrders(2);
            msg.setNoOrders(1);
            OrderListGroup44TestData.getInstance().populate1(msg.getOrderListGroups()[0]);
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

            NewOrderListMsg dmsg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertEquals(msg.getBidType(), dmsg.getBidType());
            assertEquals(msg.getTotNoOrders(), dmsg.getTotNoOrders());
            assertEquals(msg.getNoOrders(), dmsg.getNoOrders());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class NewOrderListMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            NewOrderListMsg msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            NewOrderListMsg44TestData.getInstance().populate(msg);
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

            NewOrderListMsg dmsg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            NewOrderListMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}