/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsgFIXML44Test.java
 *
 * $Id: AdvertisementMsgFIXML44Test.java,v 1.2 2010-11-14 08:52:58 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.AdvertisementMsg44TestData;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for AdvertismentMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class AdvertisementMsgFIXML44Test extends MsgTest {

    public AdvertisementMsgFIXML44Test() {
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
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setAdvID("45");
            msg.setAdvTransType(AdvTransType.New);
            msg.getInstrument().setSymbol("MOT");
            msg.setAdvSide(AdvSide.Buy);
            msg.setQuantity(new Double("200"));
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

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getAdvID(), dmsg.getAdvID());
            assertEquals(msg.getAdvTransType().getValue(), dmsg.getAdvTransType().getValue());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getAdvSide().getValue(), dmsg.getAdvSide().getValue());
            assertEquals(200.0, dmsg.getQuantity().doubleValue(), 0.1);
        } finally {
            unsetPrintableFixml();
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
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_4);
            AdvertisementMsg44TestData.getInstance().populate(msg);
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

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            AdvertisementMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}