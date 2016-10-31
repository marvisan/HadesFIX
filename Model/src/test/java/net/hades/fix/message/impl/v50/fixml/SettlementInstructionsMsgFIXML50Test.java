/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsgFIXML50Test.java
 *
 * $Id: SettlementInstructionsMsgFIXML50Test.java,v 1.1 2011-03-26 03:24:29 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SettlementInstructionsMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.SettlementInstructionsMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SettlInstMode;

/**
 * Test suite for SettlementInstructionsMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class SettlementInstructionsMsgFIXML50Test extends MsgTest {

    public SettlementInstructionsMsgFIXML50Test() {
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
     * Test of encode method, of class SettlementInstructionsMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            SettlementInstructionsMsg msg = (SettlementInstructionsMsg) FIXMsgBuilder.build(MsgType.SettlInstructions.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setSettlInstMsgID("A88663322");
            msg.setSettlInstMode(SettlInstMode.Default);
            msg.setTransactTime(new Date());
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

            SettlementInstructionsMsg dmsg = (SettlementInstructionsMsg) FIXMsgBuilder.build(MsgType.SettlInstructions.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getSettlInstMsgID(), dmsg.getSettlInstMsgID());
            assertEquals(msg.getSettlInstMode(), dmsg.getSettlInstMode());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class SettlementInstructionsMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            SettlementInstructionsMsg msg = (SettlementInstructionsMsg) FIXMsgBuilder.build(MsgType.SettlInstructions.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            SettlementInstructionsMsg50TestData.getInstance().populate(msg);
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

            SettlementInstructionsMsg dmsg = (SettlementInstructionsMsg) FIXMsgBuilder.build(MsgType.SettlInstructions.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            SettlementInstructionsMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}