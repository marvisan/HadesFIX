/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionMsgFIXML50Test.java
 *
 * $Id: AllocationInstructionMsgFIXML50Test.java,v 1.1 2011-02-16 11:24:34 vrotaru Exp $
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
import net.hades.fix.message.AllocationInstructionMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.AllocationInstructionMsg50TestData;
import net.hades.fix.message.type.AllocNoOrdersType;
import net.hades.fix.message.type.AllocTransType;
import net.hades.fix.message.type.AllocType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for AllocationInstructionMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class AllocationInstructionMsgFIXML50Test extends MsgTest {

    public AllocationInstructionMsgFIXML50Test() {
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
     * Test of encode method, of class AllocationInstructionMsg for FIXML 5.0.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            AllocationInstructionMsg msg = (AllocationInstructionMsg) FIXMsgBuilder.build(MsgType.Allocation.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setAllocID("AL33444");
            msg.setAllocTransType(AllocTransType.Calculated);
            msg.setAllocType(AllocType.Calculated);
            msg.setAllocNoOrdersType(AllocNoOrdersType.ExplicitListProvided);
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setQuantity(12.0d);
            msg.setAvgPx(12.50d);
            msg.setTradeDate(new Date());
            msg.setSide(Side.Buy);
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

            AllocationInstructionMsg dmsg = (AllocationInstructionMsg) FIXMsgBuilder.build(MsgType.Allocation.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getAllocID(), dmsg.getAllocID());
            assertEquals(msg.getAllocTransType(), dmsg.getAllocTransType());
            assertEquals(msg.getAllocType(), dmsg.getAllocType());
            assertEquals(msg.getAllocNoOrdersType(), dmsg.getAllocNoOrdersType());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getQuantity(), dmsg.getQuantity());
            assertEquals(msg.getAvgPx(), dmsg.getAvgPx());
            assertDateEquals(msg.getTradeDate(), dmsg.getTradeDate());
            assertEquals(msg.getSide(), dmsg.getSide());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AllocationInstructionMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            AllocationInstructionMsg msg = (AllocationInstructionMsg) FIXMsgBuilder.build(MsgType.Allocation.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            AllocationInstructionMsg50TestData.getInstance().populate(msg);
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

            AllocationInstructionMsg dmsg = (AllocationInstructionMsg) FIXMsgBuilder.build(MsgType.Allocation.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            AllocationInstructionMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}