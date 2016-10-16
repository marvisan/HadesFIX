/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionRequestMsgFIXML50Test.java
 *
 * $Id: SecurityDefinitionRequestMsgFIXML50Test.java,v 1.1 2011-04-16 09:39:03 vrotaru Exp $
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
import net.hades.fix.message.SecurityDefinitionRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.SecurityDefinitionRequestMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityRequestType;

/**
 * Test suite for SecurityDefinitionRequestMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class SecurityDefinitionRequestMsgFIXML50Test extends MsgTest {

    public SecurityDefinitionRequestMsgFIXML50Test() {
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
     * Test of encode method, of class SecurityDefinitionRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            SecurityDefinitionRequestMsg msg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.setSecurityReqID("X162773883");
            msg.setSecurityRequestType(SecurityRequestType.ReqListSecTypes);
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

            SecurityDefinitionRequestMsg dmsg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getSecurityReqID(), dmsg.getSecurityReqID());
            assertEquals(msg.getSecurityRequestType(), dmsg.getSecurityRequestType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class SecurityDefinitionRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            SecurityDefinitionRequestMsg msg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            SecurityDefinitionRequestMsg50TestData.getInstance().populate(msg);
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

            SecurityDefinitionRequestMsg dmsg = (SecurityDefinitionRequestMsg) FIXMsgBuilder.build(MsgType.SecurityDefinitionRequest.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            SecurityDefinitionRequestMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}