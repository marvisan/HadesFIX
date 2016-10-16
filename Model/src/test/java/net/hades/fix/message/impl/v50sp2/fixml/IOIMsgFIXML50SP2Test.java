/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsgFIXML50SP2Test.java
 *
 * $Id: IOIMsgFIXML50SP2Test.java,v 1.2 2010-10-04 05:15:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.IOIMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for IOIMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class IOIMsgFIXML50SP2Test extends MsgTest {

    public IOIMsgFIXML50SP2Test() {
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
     * Test of encode method, of class AdvertisementMsg for FIXML 4.1.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        setSessionApplVerID(ApplVerID.FIX50SP2);
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setIoiID("IOI ID");
            msg.setIoiTransType(IOITransType.New);
            msg.getInstrument().setSymbol("symbol");
            msg.setSide(Side.Borrow);
            msg.setIoiQty(IOIQty.Large);
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

            IOIMsg dmsg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getIoiID(), dmsg.getIoiID());
            assertEquals(msg.getIoiTransType(), dmsg.getIoiTransType());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getSide(), dmsg.getSide());
            assertEquals(msg.getIoiQty(), dmsg.getIoiQty());
        } finally {
            unsetSessionData();
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg for FIXML 4.1.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        setSessionApplVerID(ApplVerID.FIX50SP2);
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            IOIMsg50SP2TestData.getInstance().populate(msg);
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

            IOIMsg dmsg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            dmsg.fromFixml(fixml);
            IOIMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSessionData();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}