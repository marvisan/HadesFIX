/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusMsgFIXML50SP1Test.java
 *
 * $Id: TradingSessionStatusMsgFIXML50SP1Test.java,v 1.1 2011-04-23 00:19:04 vrotaru Exp $
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
import net.hades.fix.message.TradingSessionStatusMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.TradingSessionStatusMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.TradSesStatus;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test suite for TradingSessionStatusMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class TradingSessionStatusMsgFIXML50SP1Test extends MsgTest {

    public TradingSessionStatusMsgFIXML50SP1Test() {
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
     * Test of encode method, of class TradingSessionStatusMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            TradingSessionStatusMsg msg = (TradingSessionStatusMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatus.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
            msg.setTradSesStatus(TradSesStatus.Closed);
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

            TradingSessionStatusMsg dmsg = (TradingSessionStatusMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatus.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getTradingSessionID(), dmsg.getTradingSessionID());
            assertEquals(msg.getTradSesStatus(), dmsg.getTradSesStatus());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class TradingSessionStatusMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            TradingSessionStatusMsg msg = (TradingSessionStatusMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatus.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            TradingSessionStatusMsg50SP1TestData.getInstance().populate(msg);
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

            TradingSessionStatusMsg dmsg = (TradingSessionStatusMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatus.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            TradingSessionStatusMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}