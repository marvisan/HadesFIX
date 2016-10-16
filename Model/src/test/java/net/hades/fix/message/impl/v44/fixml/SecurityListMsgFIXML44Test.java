/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsgFIXML44Test.java
 *
 * $Id: SecurityListMsgFIXML44Test.java,v 1.1 2011-04-29 03:11:03 vrotaru Exp $
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
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.SecurityListMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test suite for SecurityListMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class SecurityListMsgFIXML44Test extends MsgTest {

    public SecurityListMsgFIXML44Test() {
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
     * Test of encode method, of class SecurityListMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            SecurityListMsg msg = (SecurityListMsg) FIXMsgBuilder.build(MsgType.SecurityList.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setSecurityReqID("X162773883");
            msg.setSecurityResponseID("RESP_665544");
            msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
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

            SecurityListMsg dmsg = (SecurityListMsg) FIXMsgBuilder.build(MsgType.SecurityList.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getSecurityReqID(), dmsg.getSecurityReqID());
            assertEquals(msg.getSecurityResponseID(), dmsg.getSecurityResponseID());
            assertEquals(msg.getSecurityRequestResult(), dmsg.getSecurityRequestResult());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class SecurityListMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            SecurityListMsg msg = (SecurityListMsg) FIXMsgBuilder.build(MsgType.SecurityList.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            SecurityListMsg44TestData.getInstance().populate(msg);
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

            SecurityListMsg dmsg = (SecurityListMsg) FIXMsgBuilder.build(MsgType.SecurityList.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            SecurityListMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}