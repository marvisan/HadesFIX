/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionReportMsgFIXML44Test.java
 *
 * $Id: PositionReportMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.PositionReportMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.PositionReportMsg44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PosAmtType;
import net.hades.fix.message.type.PosReqResult;
import net.hades.fix.message.type.PosType;
import net.hades.fix.message.type.SettlPriceType;

/**
 * Test suite for PositionReportMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 13/12/2011, 20:57:03
 */
public class PositionReportMsgFIXML44Test extends MsgTest {

    public PositionReportMsgFIXML44Test() {
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
     * Test of encode method, of class PositionReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            PositionReportMsg msg = (PositionReportMsg) FIXMsgBuilder.build(MsgType.PositionReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setPosMaintRptID("POS_REQ_2222");
            msg.setPosReqResult(PosReqResult.NotAuthorized.getValue());
            msg.setClearingBusinessDate(new Date());
            msg.setParties();
            msg.getParties().setNoPartyIDs(1);
            msg.getParties().getPartyIDGroups()[0].setPartyID("PARTY_9999");
            msg.getParties().getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.Proprietary);
            msg.getParties().getPartyIDGroups()[0].setPartyRole(PartyRole.Exchange);
            msg.setAccount("72634637632");
            msg.setAccountType(AccountType.FloorTrader);
            msg.setSettlPrice(35.33d);
            msg.setSettlPriceType(SettlPriceType.Theoretical);
            msg.setPriorSettlPrice(66.88d);
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

            PositionReportMsg dmsg = (PositionReportMsg) FIXMsgBuilder.build(MsgType.PositionReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getPosMaintRptID(), dmsg.getPosMaintRptID());
            assertEquals(msg.getPosReqResult(), dmsg.getPosReqResult());
            assertEquals(msg.getSettlPrice(), dmsg.getSettlPrice());
            assertEquals(msg.getSettlPriceType(), dmsg.getSettlPriceType());
            assertEquals(msg.getPriorSettlPrice(), dmsg.getPriorSettlPrice());
            assertEquals(msg.getAccount(), dmsg.getAccount());
            assertEquals(msg.getAccountType(), dmsg.getAccountType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class PositionReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            PositionReportMsg msg = (PositionReportMsg) FIXMsgBuilder.build(MsgType.PositionReport.getValue(), BeginString.FIX_4_4);
            PositionReportMsg44TestData.getInstance().populate(msg);
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

            PositionReportMsg dmsg = (PositionReportMsg) FIXMsgBuilder.build(MsgType.PositionReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            PositionReportMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}