/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidResponseMsgFIXML50Test.java
 *
 * $Id: BidResponseMsgFIXML50Test.java,v 1.1 2011-04-14 11:44:46 vrotaru Exp $
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
import net.hades.fix.message.BidResponseMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v44.BidRespComponentGroup44TestData;
import net.hades.fix.message.impl.v44.data.BidResponseMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for BidResponseMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class BidResponseMsgFIXML50Test extends MsgTest {

    public BidResponseMsgFIXML50Test() {
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
     * Test of encode method, of class BidResponseMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            BidResponseMsg msg = (BidResponseMsg) FIXMsgBuilder.build(MsgType.BidResponse.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.setNoBidComponents(1);
            BidRespComponentGroup44TestData.getInstance().populate1(msg.getBidComponentGroups()[0]);
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

            BidResponseMsg dmsg = (BidResponseMsg) FIXMsgBuilder.build(MsgType.BidResponse.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            
            assertEquals(msg.getNoBidComponents(), dmsg.getNoBidComponents());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class BidResponseMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            BidResponseMsg msg = (BidResponseMsg) FIXMsgBuilder.build(MsgType.BidResponse.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            BidResponseMsg44TestData.getInstance().populate(msg);
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

            BidResponseMsg dmsg = (BidResponseMsg) FIXMsgBuilder.build(MsgType.BidResponse.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            BidResponseMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}