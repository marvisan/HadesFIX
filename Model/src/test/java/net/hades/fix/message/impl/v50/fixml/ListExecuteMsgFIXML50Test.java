/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListExecuteMsgFIXML50Test.java
 *
 * $Id: ListExecuteMsgFIXML50Test.java,v 1.1 2011-02-05 08:52:44 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListExecuteMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for ListExecuteMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class ListExecuteMsgFIXML50Test extends MsgTest {

    public ListExecuteMsgFIXML50Test() {
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
     * Test of encode method, of class ListExecuteMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            ListExecuteMsg msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            Calendar cal = Calendar.getInstance();
            msg.setListID("LST564567");
            cal.set(2010, 3, 14, 12, 13, 13);
            msg.setTransactTime(cal.getTime());
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

            ListExecuteMsg dmsg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class ListExecuteMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            ListExecuteMsg msg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            TestUtils.populate44HeaderAll(msg);
            Calendar cal = Calendar.getInstance();
            msg.setListID("LST564567");
            msg.setClientBidID("CLIBID837474");
            msg.setBidID("BID98374634");
            cal.set(2010, 3, 14, 12, 13, 13);
            msg.setTransactTime(cal.getTime());
            msg.setText("some text");
            msg.setEncodedTextLen(new Integer(8));
            byte[] encodedText = new byte[] { (byte) 18, (byte) 33, (byte) 44, (byte) 96,
                (byte) 177, (byte) 199, (byte) 224, (byte) 253 };
            msg.setEncodedText(encodedText);
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

            ListExecuteMsg dmsg = (ListExecuteMsg) FIXMsgBuilder.build(MsgType.ListExecute.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertEquals(msg.getClientBidID(), dmsg.getClientBidID());
            assertEquals(msg.getBidID(), dmsg.getBidID());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
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