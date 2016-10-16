/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityDefinitionMsgFIXML50Test.java
 *
 * $Id: SecurityDefinitionMsgFIXML50Test.java,v 1.1 2011-04-19 12:13:37 vrotaru Exp $
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
import net.hades.fix.message.SecurityDefinitionMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.SecurityDefinitionMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityResponseType;

/**
 * Test suite for SecurityDefinitionMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class SecurityDefinitionMsgFIXML50Test extends MsgTest {

    public SecurityDefinitionMsgFIXML50Test() {
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
     * Test of encode method, of class SecurityDefinitionMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            SecurityDefinitionMsg msg = (SecurityDefinitionMsg) FIXMsgBuilder.build(MsgType.SecurityDefinition.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.setSecurityReqID("X162773883");
            msg.setSecurityResponseID("RSP666777333");
            msg.setSecurityResponseType(SecurityResponseType.RejectSecurityProposal);
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

            SecurityDefinitionMsg dmsg = (SecurityDefinitionMsg) FIXMsgBuilder.build(MsgType.SecurityDefinition.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getSecurityReqID(), dmsg.getSecurityReqID());
            assertEquals(msg.getSecurityResponseID(), dmsg.getSecurityResponseID());
            assertEquals(msg.getSecurityResponseType(), dmsg.getSecurityResponseType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class SecurityDefinitionMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            SecurityDefinitionMsg msg = (SecurityDefinitionMsg) FIXMsgBuilder.build(MsgType.SecurityDefinition.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            SecurityDefinitionMsg50TestData.getInstance().populate(msg);
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

            SecurityDefinitionMsg dmsg = (SecurityDefinitionMsg) FIXMsgBuilder.build(MsgType.SecurityDefinition.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            SecurityDefinitionMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}