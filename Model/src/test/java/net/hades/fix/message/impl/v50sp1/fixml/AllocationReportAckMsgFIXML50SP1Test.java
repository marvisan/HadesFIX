/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationReportAckMsgFIXML50SP1Test.java
 *
 * $Id: AllocationReportAckMsgFIXML44Test.java,v 1.1 2011-02-17 09:21:28 vrotaru Exp $
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
import net.hades.fix.message.AllocationReportAckMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.AllocationReportAckMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for AllocationReportAckMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class AllocationReportAckMsgFIXML50SP1Test extends MsgTest {

    public AllocationReportAckMsgFIXML50SP1Test() {
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
     * Test of encode method, of class AllocationReportAckMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            AllocationReportAckMsg msg = (AllocationReportAckMsg) FIXMsgBuilder.build(MsgType.AllocationReportAck.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate44HeaderAll(msg);
            msg.setAllocReportID("REP_3333");
            msg.setAllocID("AL33444");
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

            AllocationReportAckMsg dmsg = (AllocationReportAckMsg) FIXMsgBuilder.build(MsgType.AllocationReportAck.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getAllocReportID(), dmsg.getAllocReportID());
            assertEquals(msg.getAllocID(), dmsg.getAllocID());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AllocationReportAckMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            AllocationReportAckMsg msg = (AllocationReportAckMsg) FIXMsgBuilder.build(MsgType.AllocationReportAck.getValue(), BeginString.FIX_5_0SP1);
            AllocationReportAckMsg50TestData.getInstance().populate(msg);
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

            AllocationReportAckMsg dmsg = (AllocationReportAckMsg) FIXMsgBuilder.build(MsgType.AllocationReportAck.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            AllocationReportAckMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}