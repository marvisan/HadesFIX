/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmationMsgFIXML50SP2Test.java
 *
 * $Id: ConfirmationMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.ConfirmationMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.ConfirmationMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ConfirmStatus;
import net.hades.fix.message.type.ConfirmTransType;
import net.hades.fix.message.type.ConfirmType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.Side;

/**
 * Test suite for ConfirmationMsg class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class ConfirmationMsgFIXML50SP2Test extends MsgTest {

    public ConfirmationMsgFIXML50SP2Test() {
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
     * Test of encode method, of class ConfirmationMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            ConfirmationMsg msg = (ConfirmationMsg) FIXMsgBuilder.build(MsgType.Confirmation.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate44HeaderAll(msg);
            msg.setConfirmID("CONF_88888");
            msg.setConfirmTransType(ConfirmTransType.Replace);
            msg.setConfirmType(ConfirmType.Confirmation);
            msg.setConfirmStatus(ConfirmStatus.Confirmed);
            msg.setTransactTime(new Date());
            msg.setTradeDate(new Date());
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setAllocAccount("08973237");
            msg.setAllocQty(24.33d);
            msg.setSide(Side.Buy);
            msg.setNoCapacities(1);
            msg.getCpctyConfGroups()[0].setOrderCapacity(OrderCapacity.Proprietary);
            msg.getCpctyConfGroups()[0].setOrderCapacityQty(23.50d);
            msg.setAvgPx(12.50d);
            msg.setGrossTradeAmt(34.55);
            msg.setNetMoney(65.22d);
            
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

            ConfirmationMsg dmsg = (ConfirmationMsg) FIXMsgBuilder.build(MsgType.Confirmation.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getConfirmID(), dmsg.getConfirmID());
            assertEquals(msg.getConfirmTransType(), dmsg.getConfirmTransType());
            assertEquals(msg.getConfirmType(), dmsg.getConfirmType());
            assertEquals(msg.getConfirmStatus(), dmsg.getConfirmStatus());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getAllocQty(), dmsg.getAllocQty());
            assertEquals(msg.getAvgPx(), dmsg.getAvgPx());
            assertEquals(msg.getSide(), dmsg.getSide());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class ConfirmationMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            ConfirmationMsg msg = (ConfirmationMsg) FIXMsgBuilder.build(MsgType.Confirmation.getValue(), BeginString.FIX_5_0SP2);
            ConfirmationMsg50SP2TestData.getInstance().populate(msg);
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

            ConfirmationMsg dmsg = (ConfirmationMsg) FIXMsgBuilder.build(MsgType.Confirmation.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            ConfirmationMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}