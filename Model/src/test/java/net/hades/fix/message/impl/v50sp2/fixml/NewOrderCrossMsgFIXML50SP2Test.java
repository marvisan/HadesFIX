/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderCrossMsgFIXML50SP2Test.java
 *
 * $Id: NewOrderCrossMsgFIXML50SP2Test.java,v 1.1 2011-05-13 08:16:08 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderCrossMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50sp2.SideCrossOrdModGroup50SP2TestData;
import net.hades.fix.message.impl.v50sp2.data.NewOrderCrossMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;

/**
 * Test suite for NewOrderCrossMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class NewOrderCrossMsgFIXML50SP2Test extends MsgTest {

    public NewOrderCrossMsgFIXML50SP2Test() {
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
     * Test of encode method, of class NewOrderCrossMsg.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            NewOrderCrossMsg msg = (NewOrderCrossMsg) FIXMsgBuilder.build(MsgType.NewOrderCross.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate44HeaderAll(msg);
            msg.setCrossID("X162773883");
            msg.setCrossType(CrossType.CrossAON);
            msg.setCrossPrioritization(CrossPrioritization.BuySidePrioritized);
            msg.setNoSides(1);
            SideCrossOrdModGroup50SP2TestData.getInstance().populate1(msg.getSideCrossOrdModGroups()[0]);
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setTransactTime(new Date());
            msg.setOrdType(OrdType.Stop);
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

            NewOrderCrossMsg dmsg = (NewOrderCrossMsg) FIXMsgBuilder.build(MsgType.NewOrderCross.getValue(), BeginString.FIX_5_0SP2);
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
     * Test of encode method, of class NewOrderCrossMsg.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            NewOrderCrossMsg msg = (NewOrderCrossMsg) FIXMsgBuilder.build(MsgType.NewOrderCross.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            NewOrderCrossMsg50SP2TestData.getInstance().populate(msg);
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

            NewOrderCrossMsg dmsg = (NewOrderCrossMsg) FIXMsgBuilder.build(MsgType.NewOrderCross.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            NewOrderCrossMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}