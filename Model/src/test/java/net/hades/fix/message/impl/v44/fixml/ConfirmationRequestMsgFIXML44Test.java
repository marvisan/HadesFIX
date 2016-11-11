/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmationRequestMsgFIXML44Test.java
 *
 * $Id: ConfirmationRequestMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.ConfirmationRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.ConfirmationRequestMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ConfirmType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for ConfirmationRequestMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class ConfirmationRequestMsgFIXML44Test extends MsgTest {

    public ConfirmationRequestMsgFIXML44Test() {
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
     * Test of encode method, of class ConfirmationRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            ConfirmationRequestMsg msg = (ConfirmationRequestMsg) FIXMsgBuilder.build(MsgType.ConfirmationRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setConfirmReqID("CONF_88888");
            msg.setConfirmType(ConfirmType.Confirmation);
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

            ConfirmationRequestMsg dmsg = (ConfirmationRequestMsg) FIXMsgBuilder.build(MsgType.ConfirmationRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getConfirmReqID(), dmsg.getConfirmReqID());
            assertEquals(msg.getConfirmType(), dmsg.getConfirmType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class ConfirmationRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            ConfirmationRequestMsg msg = (ConfirmationRequestMsg) FIXMsgBuilder.build(MsgType.ConfirmationRequest.getValue(), BeginString.FIX_4_4);
            ConfirmationRequestMsg44TestData.getInstance().populate(msg);
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

            ConfirmationRequestMsg dmsg = (ConfirmationRequestMsg) FIXMsgBuilder.build(MsgType.ConfirmationRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            ConfirmationRequestMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}