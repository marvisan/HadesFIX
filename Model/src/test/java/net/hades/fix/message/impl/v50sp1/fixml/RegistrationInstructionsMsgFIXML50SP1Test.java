/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsMsgFIXML50SP1Test.java
 *
 * $Id: RegistrationInstructionsMsgFIXML50SP1Test.java,v 1.1 2011-10-29 01:31:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.fixml;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RegistrationInstructionsMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.RegistrationInstructionsMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.RegistTransType;

/**
 * Test suite for RegistrationInstructionsMsg50SP1 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class RegistrationInstructionsMsgFIXML50SP1Test extends MsgTest {

    public RegistrationInstructionsMsgFIXML50SP1Test() {
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
     * Test of encode method, of class RegistrationInstructionsMsg for FIXML
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            RegistrationInstructionsMsg msg = (RegistrationInstructionsMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructions.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            msg.setRegistID("X162773883");
            msg.setRegistTransType(RegistTransType.Replace);
            msg.setRegistRefID("TEG_REF_999");
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

            RegistrationInstructionsMsg dmsg = (RegistrationInstructionsMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructions.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getRegistID(), dmsg.getRegistID());
            assertEquals(msg.getRegistTransType(), dmsg.getRegistTransType());
            assertEquals(msg.getRegistRefID(), dmsg.getRegistRefID());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class RegistrationInstructionsMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            RegistrationInstructionsMsg msg = (RegistrationInstructionsMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructions.getValue(), BeginString.FIX_5_0SP1);
            RegistrationInstructionsMsg50SP1TestData.getInstance().populate(msg);
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

            RegistrationInstructionsMsg dmsg = (RegistrationInstructionsMsg) FIXMsgBuilder.build(MsgType.RegistrationInstructions.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            RegistrationInstructionsMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}