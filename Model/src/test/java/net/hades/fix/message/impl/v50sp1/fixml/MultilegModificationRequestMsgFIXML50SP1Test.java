/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MultilegModificationRequestMsgFIXML50SP1Test.java
 *
 * $Id: MultilegModificationRequestMsgFIXML50SP1Test.java,v 1.1 2011-09-10 05:41:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.MultilegModificationRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1TestData;
import net.hades.fix.message.group.impl.v50sp1.LegOrdGroup50SP1TestData;
import net.hades.fix.message.impl.v50sp1.data.MultilegModificationRequestMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for MultilegModificationRequestMsg class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class MultilegModificationRequestMsgFIXML50SP1Test extends MsgTest {

    public MultilegModificationRequestMsgFIXML50SP1Test() {
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
     * Test of encode method, of class MultilegModificationRequestMsg for FIXML 5.0SP1.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            MultilegModificationRequestMsg msg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            msg.setOrigClOrdID("Orig_ord");
            msg.setClOrdID("X162773883");
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setSide(Side.Buy);
            msg.setOrdType(OrdType.Stop);
            msg.setTransactTime(new Date());
            msg.setNoUnderlyings(1);
            UnderlyingInstrument50SP1TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
            msg.setNoLegs(1);
            LegOrdGroup50SP1TestData.getInstance().populate1(msg.getLegOrdGroups()[0]);
            msg.setOrderQtyData();
            OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());
            
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

            MultilegModificationRequestMsg dmsg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getClOrdID(), dmsg.getClOrdID());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getSide(), dmsg.getSide());
            assertEquals(msg.getOrdType(), dmsg.getOrdType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class MultilegModificationRequestMsg for FIXML 5.0SP1.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            MultilegModificationRequestMsg msg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            MultilegModificationRequestMsg50SP1TestData.getInstance().populate(msg);
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

            MultilegModificationRequestMsg dmsg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            MultilegModificationRequestMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}