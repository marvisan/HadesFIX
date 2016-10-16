/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderCancelRequestMsgFIXML44Test.java
 *
 * $Id: CrossOrderCancelRequestMsgFIXML44Test.java,v 1.1 2011-05-21 23:53:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CrossOrderCancelRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v44.SideCrossOrdCxlGroup44TestData;
import net.hades.fix.message.impl.v44.data.CrossOrderCancelRequestMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for CrossOrderCancelRequestMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class CrossOrderCancelRequestMsgFIXML44Test extends MsgTest {

    public CrossOrderCancelRequestMsgFIXML44Test() {
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
     * Test of encode method, of class CrossOrderCancelRequestMsg.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            CrossOrderCancelRequestMsg msg = (CrossOrderCancelRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderCancelRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setCrossID("X162773883");
            msg.setOrigCrossID("ORIG777883");
            msg.setCrossType(CrossType.CrossAON);
            msg.setCrossPrioritization(CrossPrioritization.BuySidePrioritized);
            msg.setNoSides(1);
            SideCrossOrdCxlGroup44TestData.getInstance().populate1(msg.getSideCrossOrdCxlGroups()[0]);
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setTransactTime(new Date());

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

            CrossOrderCancelRequestMsg dmsg = (CrossOrderCancelRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderCancelRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getCrossID(), dmsg.getCrossID());
            assertEquals(msg.getCrossType(), dmsg.getCrossType());
            assertEquals(msg.getCrossPrioritization(), dmsg.getCrossPrioritization());
            assertEquals(msg.getNoSides(), dmsg.getNoSides());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class CrossOrderCancelRequestMsg.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            CrossOrderCancelRequestMsg msg = (CrossOrderCancelRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderCancelRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            CrossOrderCancelRequestMsg44TestData.getInstance().populate(msg);
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

            CrossOrderCancelRequestMsg dmsg = (CrossOrderCancelRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderCancelRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            CrossOrderCancelRequestMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}