/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralInquiryAckMsgFIXML50SP1Test.java
 *
 * $Id: CollateralInquiryAckMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CollateralInquiryAckMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.CollateralInquiryAckMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CollInquiryStatus;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for CollateralInquiryAckMsg50SP1 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 19/12/2011, 20:57:03
 */
public class CollateralInquiryAckMsgFIXML50SP1Test extends MsgTest {

    public CollateralInquiryAckMsgFIXML50SP1Test() {
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
     * Test of encode method, of class CollateralInquiryAckMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            CollateralInquiryAckMsg msg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate44HeaderAll(msg);
            msg.setCollInquiryID("COLL_INQ_9999");
            msg.setCollInquiryStatus(CollInquiryStatus.Completed);

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

            CollateralInquiryAckMsg dmsg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getCollInquiryID(), dmsg.getCollInquiryID());
            assertEquals(msg.getCollInquiryStatus(), dmsg.getCollInquiryStatus());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class CollateralInquiryAckMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            CollateralInquiryAckMsg msg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
            CollateralInquiryAckMsg50SP1TestData.getInstance().populate(msg);
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

            CollateralInquiryAckMsg dmsg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            CollateralInquiryAckMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}