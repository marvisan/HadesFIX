/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsgFIXML50Test.java
 *
 * $Id: NewOrderSingleMsgFIXML50Test.java,v 1.1 2010-12-12 09:13:09 vrotaru Exp $
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
import net.hades.fix.message.NewOrderSingleMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.NewOrderSingleMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for NewOrderSingleMsgFIXML50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class NewOrderSingleMsgFIXML50Test extends MsgTest {

    public NewOrderSingleMsgFIXML50Test() {
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
     * Test of encode method, of class NewOrderSingleMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            NewOrderSingleMsg msg = (NewOrderSingleMsg) FIXMsgBuilder.build(MsgType.NewOrderSingle.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setClOrdID("X162773883");
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setSide(Side.Buy);
            msg.setOrdType(OrdType.Stop);
            msg.setOrderQty(10.0d);
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

            NewOrderSingleMsg dmsg = (NewOrderSingleMsg) FIXMsgBuilder.build(MsgType.NewOrderSingle.getValue(), BeginString.FIX_5_0);
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
     * Test of encode method, of class NewOrderSingleMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            NewOrderSingleMsg msg = (NewOrderSingleMsg) FIXMsgBuilder.build(MsgType.NewOrderSingle.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            NewOrderSingleMsg50TestData.getInstance().populate(msg);
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

            NewOrderSingleMsg dmsg = (NewOrderSingleMsg) FIXMsgBuilder.build(MsgType.NewOrderSingle.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            NewOrderSingleMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}