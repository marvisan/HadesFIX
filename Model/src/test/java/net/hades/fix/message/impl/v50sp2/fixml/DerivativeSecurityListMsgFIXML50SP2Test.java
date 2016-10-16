/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListMsgFIXML50SP2Test.java
 *
 * $Id: DerivativeSecurityListMsgFIXML50SP2Test.java,v 1.1 2011-09-28 08:10:22 vrotaru Exp $
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
import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.DerivativeSecurityListMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityRequestResult;

/**
 * Test suite for DerivativeSecurityListMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class DerivativeSecurityListMsgFIXML50SP2Test extends MsgTest {

    public DerivativeSecurityListMsgFIXML50SP2Test() {
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
     * Test of encode method, of class DerivativeSecurityListMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            DerivativeSecurityListMsg msg = (DerivativeSecurityListMsg) FIXMsgBuilder.build(MsgType.DerivativeSecurityList.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.setSecurityReqID("X162773883");
            msg.setSecurityResponseID("RESP_665544");
            msg.setSecurityRequestResult(SecurityRequestResult.ValidRequest);
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

            DerivativeSecurityListMsg dmsg = (DerivativeSecurityListMsg) FIXMsgBuilder.build(MsgType.DerivativeSecurityList.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getSecurityReqID(), dmsg.getSecurityReqID());
            assertEquals(msg.getSecurityResponseID(), dmsg.getSecurityResponseID());
            assertEquals(msg.getSecurityRequestResult(), dmsg.getSecurityRequestResult());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class DerivativeSecurityListMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            DerivativeSecurityListMsg msg = (DerivativeSecurityListMsg) FIXMsgBuilder.build(MsgType.DerivativeSecurityList.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            DerivativeSecurityListMsg50SP2TestData.getInstance().populate(msg);
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

            DerivativeSecurityListMsg dmsg = (DerivativeSecurityListMsg) FIXMsgBuilder.build(MsgType.DerivativeSecurityList.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            DerivativeSecurityListMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}