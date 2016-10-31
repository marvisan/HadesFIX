/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListCancelRequestMsgFIXML44Test.java
 *
 * $Id: ListCancelRequestMsgFIXML44Test.java,v 1.1 2011-02-06 01:06:14 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListCancelRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for ListCancelRequestMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class ListCancelRequestMsgFIXML44Test extends MsgTest {

    public ListCancelRequestMsgFIXML44Test() {
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
     * Test of encode method, of class ListCancelRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            ListCancelRequestMsg msg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            Calendar cal = Calendar.getInstance();
            msg.setListID("LST564567");
            cal.set(2010, 3, 14, 12, 13, 13);
            msg.setTransactTime(cal.getTime());
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

            ListCancelRequestMsg dmsg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class ListCancelRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            ListCancelRequestMsg msg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            Calendar cal = Calendar.getInstance();
            msg.setListID("LST564567");
            msg.setTransactTime(cal.getTime());
            cal.set(2010, 3, 14, 12, 13, 13);
            msg.setTradeOriginationDate(cal.getTime());
            cal.set(2010, 3, 14, 13, 14, 15);
            msg.setTradeDate(cal.getTime());
            msg.setText("some text");
            msg.setEncodedTextLen(new Integer(8));
            byte[] encodedText = new byte[] { (byte) 18, (byte) 33, (byte) 44, (byte) 96,
                (byte) 177, (byte) 199, (byte) 224, (byte) 253 };
            msg.setEncodedText(encodedText);
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

            ListCancelRequestMsg dmsg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
            assertDateEquals(msg.getTradeOriginationDate(), dmsg.getTradeOriginationDate());
            assertDateEquals(msg.getTradeDate(), dmsg.getTradeDate());
            assertEquals(msg.getText(), dmsg.getText());
            assertEquals(msg.getEncodedTextLen(), dmsg.getEncodedTextLen());
            assertArrayEquals(msg.getEncodedText(), dmsg.getEncodedText());
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}