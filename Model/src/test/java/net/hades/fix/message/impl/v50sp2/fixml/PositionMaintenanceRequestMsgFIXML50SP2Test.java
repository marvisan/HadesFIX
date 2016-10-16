/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionMaintenanceRequestMsgFIXML50SP2Test.java
 *
 * $Id: PositionMaintenanceRequestMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.PositionMaintenanceRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.PositionMaintenanceRequestMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PosMaintAction;
import net.hades.fix.message.type.PosTransType;
import net.hades.fix.message.type.PosType;

/**
 * Test suite for PositionMaintenanceRequestMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class PositionMaintenanceRequestMsgFIXML50SP2Test extends MsgTest {

    public PositionMaintenanceRequestMsgFIXML50SP2Test() {
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
     * Test of encode method, of class PositionMaintenanceRequestMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            PositionMaintenanceRequestMsg msg = (PositionMaintenanceRequestMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceRequest.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.setPosTransType(PosTransType.Exercise);
            msg.setPosMaintAction(PosMaintAction.Cancel);
            msg.setClearingBusinessDate(new Date());
            msg.setParties();
            msg.getParties().setNoPartyIDs(1);
            msg.getParties().getPartyIDGroups()[0].setPartyID("PARTY_9999");
            msg.getParties().getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.Proprietary);
            msg.getParties().getPartyIDGroups()[0].setPartyRole(PartyRole.Exchange);
            msg.setInstrument();
            msg.getInstrument().setSymbol("MSFT");
            msg.setNoPositions(1);
            msg.getPositionQtyGroups()[0].setPosType(PosType.PitTradeQty);
            
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

            PositionMaintenanceRequestMsg dmsg = (PositionMaintenanceRequestMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceRequest.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getPosReqID(), dmsg.getPosReqID());
            assertEquals(msg.getPosTransType(), dmsg.getPosTransType());
            assertEquals(msg.getPosMaintAction(), dmsg.getPosMaintAction());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getAccount(), dmsg.getAccount());
            assertEquals(msg.getAccountType(), dmsg.getAccountType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class PositionMaintenanceRequestMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            PositionMaintenanceRequestMsg msg = (PositionMaintenanceRequestMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceRequest.getValue(), BeginString.FIX_5_0SP2);
            PositionMaintenanceRequestMsg50SP2TestData.getInstance().populate(msg);
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

            PositionMaintenanceRequestMsg dmsg = (PositionMaintenanceRequestMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceRequest.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            PositionMaintenanceRequestMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}