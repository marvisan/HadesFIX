/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RequestForPositionsAckMsgFIXML50Test.java
 *
 * $Id: RequestForPositionsAckMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.RequestForPositionsAckMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.RequestForPositionsAckMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PosReqResult;
import net.hades.fix.message.type.PosReqStatus;

/**
 * Test suite for RequestForPositionsAckMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/12/2011, 20:57:03
 */
public class RequestForPositionsAckMsgFIXML50Test extends MsgTest {

    public RequestForPositionsAckMsgFIXML50Test() {
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
     * Test of encode method, of class RequestForPositionsAckMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            RequestForPositionsAckMsg msg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.setPosMaintRptID("POS_MAINT_6666");
            msg.setPosReqResult(PosReqResult.NotAuthorized.getValue());
            msg.setPosReqStatus(PosReqStatus.Rejected);
            msg.setParties();
            msg.getParties().setNoPartyIDs(1);
            msg.getParties().getPartyIDGroups()[0].setPartyID("PARTY_9999");
            msg.getParties().getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.Proprietary);
            msg.getParties().getPartyIDGroups()[0].setPartyRole(PartyRole.Exchange);
            
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

            RequestForPositionsAckMsg dmsg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getPosMaintRptID(), dmsg.getPosMaintRptID());
            assertEquals(msg.getPosReqResult(), dmsg.getPosReqResult());
            assertEquals(msg.getPosReqStatus(), dmsg.getPosReqStatus());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class RequestForPositionsAckMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            RequestForPositionsAckMsg msg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_5_0);
            RequestForPositionsAckMsg50TestData.getInstance().populate(msg);
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

            RequestForPositionsAckMsg dmsg = (RequestForPositionsAckMsg) FIXMsgBuilder.build(MsgType.RequestForPositionsAck.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            RequestForPositionsAckMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}