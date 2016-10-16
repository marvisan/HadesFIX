/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsResponseResponseMsgFIXML50Test.java
 *
 * $Id: RegistrationInstructionsResponseMsgFIXML50Test.java,v 1.1 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RegistrationInstructionsResponseMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.RegistrationInstructionsResponseMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RegistStatus;
import net.hades.fix.message.type.RegistTransType;

/**
 * Test suite for RegistrationInstructionsResponseMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class RegistrationInstructionsResponseMsgFIXML50Test extends MsgTest {

    public RegistrationInstructionsResponseMsgFIXML50Test() {
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
     * Test of encode method, of class RegistrationInstructionsResponseMsg for FIXML
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            RegistrationInstructionsResponseMsg msg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.setRegistID("X162773883");
            msg.setRegistTransType(RegistTransType.Replace);
            msg.setRegistRefID("TEG_REF_999");
            msg.setRegistStatus(RegistStatus.Rejected);
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

            RegistrationInstructionsResponseMsg dmsg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getRegistID(), dmsg.getRegistID());
            assertEquals(msg.getRegistTransType(), dmsg.getRegistTransType());
            assertEquals(msg.getRegistRefID(), dmsg.getRegistRefID());
            assertEquals(msg.getRegistStatus(), dmsg.getRegistStatus());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class RegistrationInstructionsResponseMsg for FIXML
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            RegistrationInstructionsResponseMsg msg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            RegistrationInstructionsResponseMsg50TestData.getInstance().populate(msg);
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

            RegistrationInstructionsResponseMsg dmsg = (RegistrationInstructionsResponseMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructionsResponse.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            RegistrationInstructionsResponseMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}