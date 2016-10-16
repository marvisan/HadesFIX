/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionMaintenanceReportMsgFIXML44Test.java
 *
 * $Id: PositionMaintenanceReportMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.PositionMaintenanceReportMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.PositionMaintenanceReportMsg44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PosAmtType;
import net.hades.fix.message.type.PosMaintAction;
import net.hades.fix.message.type.PosMaintStatus;
import net.hades.fix.message.type.PosTransType;
import net.hades.fix.message.type.PosType;

/**
 * Test suite for PositionMaintenanceReportMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/12/2011, 20:57:03
 */
public class PositionMaintenanceReportMsgFIXML44Test extends MsgTest {

    public PositionMaintenanceReportMsgFIXML44Test() {
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
     * Test of encode method, of class PositionMaintenanceReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            PositionMaintenanceReportMsg msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setPosMaintRptID("POS_REQ_2222");
            msg.setPosTransType(PosTransType.Exercise);
            msg.setPosMaintAction(PosMaintAction.Cancel);
            msg.setOrigPosReqRefID("ORIG_POS_6666");
            msg.setPosMaintStatus(PosMaintStatus.Completed);
            msg.setClearingBusinessDate(new Date());
            msg.setParties();
            msg.getParties().setNoPartyIDs(1);
            msg.getParties().getPartyIDGroups()[0].setPartyID("PARTY_9999");
            msg.getParties().getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.Proprietary);
            msg.getParties().getPartyIDGroups()[0].setPartyRole(PartyRole.Exchange);
            msg.setAccount("72634637632");
            msg.setAccountType(AccountType.FloorTrader);
            msg.setInstrument();
            msg.getInstrument().setSymbol("MSFT");
            msg.setTransactTime(new Date());
            msg.setNoPositions(1);
            msg.getPositionQtyGroups()[0].setPosType(PosType.PitTradeQty);
            msg.setNoPosAmt(1);
            msg.getPosAmtGroups()[0].setPosAmtType(PosAmtType.CashAmount);
            
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

            PositionMaintenanceReportMsg dmsg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getPosMaintRptID(), dmsg.getPosMaintRptID());
            assertEquals(msg.getPosTransType(), dmsg.getPosTransType());
            assertEquals(msg.getPosMaintAction(), dmsg.getPosMaintAction());
            assertEquals(msg.getOrigPosReqRefID(), dmsg.getOrigPosReqRefID());
            assertEquals(msg.getPosMaintStatus(), dmsg.getPosMaintStatus());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getAccount(), dmsg.getAccount());
            assertEquals(msg.getAccountType(), dmsg.getAccountType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class PositionMaintenanceReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            PositionMaintenanceReportMsg msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_4_4);
            PositionMaintenanceReportMsg44TestData.getInstance().populate(msg);
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

            PositionMaintenanceReportMsg dmsg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            PositionMaintenanceReportMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}