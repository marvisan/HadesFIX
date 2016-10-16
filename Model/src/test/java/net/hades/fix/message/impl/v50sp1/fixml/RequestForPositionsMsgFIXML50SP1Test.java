/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RequestForPositionsMsgFIXML50SP1Test.java
 *
 * $Id: RequestForPositionsMsgFIXML50Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.RequestForPositionsMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.RequestForPositionsMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PosReqType;

/**
 * Test suite for RequestForPositionsMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/12/2011, 20:57:03
 */
public class RequestForPositionsMsgFIXML50SP1Test extends MsgTest {

    public RequestForPositionsMsgFIXML50SP1Test() {
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
     * Test of encode method, of class RequestForPositionsMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            RequestForPositionsMsg msg = (RequestForPositionsMsg) FIXMsgBuilder.build(MsgType.RequestForPositions.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            msg.setPosReqID("POS_REQ_2222");
            msg.setPosReqType(PosReqType.Positions);
            msg.setParties();
            msg.getParties().setNoPartyIDs(1);
            msg.getParties().getPartyIDGroups()[0].setPartyID("PARTY_9999");
            msg.getParties().getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.Proprietary);
            msg.getParties().getPartyIDGroups()[0].setPartyRole(PartyRole.Exchange);
            msg.setClearingBusinessDate(new Date());
            msg.setInstrument();
            msg.getInstrument().setSymbol("MSFT");
            msg.setTransactTime(new Date());
            
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

            RequestForPositionsMsg dmsg = (RequestForPositionsMsg) FIXMsgBuilder.build(MsgType.RequestForPositions.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getPosReqID(), dmsg.getPosReqID());
            assertEquals(msg.getPosReqType(), dmsg.getPosReqType());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getAccount(), dmsg.getAccount());
            assertEquals(msg.getAccountType(), dmsg.getAccountType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class RequestForPositionsMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            RequestForPositionsMsg msg = (RequestForPositionsMsg) FIXMsgBuilder.build(MsgType.RequestForPositions.getValue(), BeginString.FIX_5_0SP1);
            RequestForPositionsMsg50SP1TestData.getInstance().populate(msg);
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

            RequestForPositionsMsg dmsg = (RequestForPositionsMsg) FIXMsgBuilder.build(MsgType.RequestForPositions.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            RequestForPositionsMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}