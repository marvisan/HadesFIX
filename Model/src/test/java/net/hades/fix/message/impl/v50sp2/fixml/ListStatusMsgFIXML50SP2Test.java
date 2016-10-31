/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusMsgFIXML50SP2Test.java
 *
 * $Id: ListStatusMsgFIXML50SP2Test.java,v 1.1 2011-02-04 09:58:23 vrotaru Exp $
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
import net.hades.fix.message.ListStatusMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50sp1.OrderStatusGroup50SP1TestData;
import net.hades.fix.message.impl.v50sp2.data.ListStatusMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ListOrderStatus;
import net.hades.fix.message.type.ListStatusType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for ListStatusMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class ListStatusMsgFIXML50SP2Test extends MsgTest {

    public ListStatusMsgFIXML50SP2Test() {
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
     * Test of encode method, of class ListStatusMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            ListStatusMsg msg = (ListStatusMsg) FIXMsgBuilder.build(MsgType.ListStatus.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.setListID("LST564567");
            msg.setListStatusType(ListStatusType.Response);
            msg.setNoRpts(3);
            msg.setListOrderStatus(ListOrderStatus.Alert);
            msg.setRptSeq(1);
            msg.setTotNoOrders(3);
            msg.setNoOrders(1);
            OrderStatusGroup50SP1TestData.getInstance().populate1(msg.getOrderStatusGroups()[0]);
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

            ListStatusMsg dmsg = (ListStatusMsg) FIXMsgBuilder.build(MsgType.ListStatus.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertEquals(msg.getListStatusType(), dmsg.getListStatusType());
            assertEquals(msg.getNoRpts(), dmsg.getNoRpts());
            assertEquals(msg.getListOrderStatus(), dmsg.getListOrderStatus());
            assertEquals(msg.getRptSeq(), dmsg.getRptSeq());
            assertEquals(msg.getTotNoOrders(), dmsg.getTotNoOrders());
            assertEquals(msg.getNoOrders(), dmsg.getNoOrders());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class ListStatusMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            ListStatusMsg msg = (ListStatusMsg) FIXMsgBuilder.build(MsgType.ListStatus.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            ListStatusMsg50SP2TestData.getInstance().populate(msg);
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

            ListStatusMsg dmsg = (ListStatusMsg) FIXMsgBuilder.build(MsgType.ListStatus.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            ListStatusMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}