/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AssignmentReportMsgFIXML44Test.java
 *
 * $Id: AssignmentReportMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.AssignmentReportMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.AssignmentReportMsg44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AssignmentMethod;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ExerciseMethod;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PosAmtType;
import net.hades.fix.message.type.PosType;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.SettlSessID;

/**
 * Test suite for AssignmentReportMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 16/12/2011, 20:57:03
 */
public class AssignmentReportMsgFIXML44Test extends MsgTest {

    public AssignmentReportMsgFIXML44Test() {
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
     * Test of encode method, of class AssignmentReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            AssignmentReportMsg msg = (AssignmentReportMsg) FIXMsgBuilder.build(MsgType.AssignmentReport.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setAsgnRptID("ASGN_RPT_4444");
            msg.setParties();
            msg.getParties().setNoPartyIDs(1);
            msg.getParties().getPartyIDGroups()[0].setPartyID("PARTY_9999");
            msg.getParties().getPartyIDGroups()[0].setPartyIDSource(PartyIDSource.Proprietary);
            msg.getParties().getPartyIDGroups()[0].setPartyRole(PartyRole.Exchange);
            msg.setAccountType(AccountType.FloorTrader);
            msg.setNoPositions(1);
            msg.getPositionQtyGroups()[0].setPosType(PosType.PitTradeQty);
            msg.setNoPosAmt(1);
            msg.getPosAmtGroups()[0].setPosAmtType(PosAmtType.CashAmount);
            msg.setSettlPrice(35.33d);
            msg.setSettlPriceType(SettlPriceType.Theoretical);
            msg.setUnderlyingSettlPrice(66.88d);
            msg.setAssignmentMethod(AssignmentMethod.Random);
            msg.setOpenInterest(44.55d);
            msg.setExerciseMethod(ExerciseMethod.Automatic);
            msg.setSettlSessID(SettlSessID.Intraday.getValue());
            msg.setSettlSessSubID("SETTL_SUB_8888");
            msg.setClearingBusinessDate(new Date());
            
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

            AssignmentReportMsg dmsg = (AssignmentReportMsg) FIXMsgBuilder.build(MsgType.AssignmentReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getSettlPrice(), dmsg.getSettlPrice());
            assertEquals(msg.getSettlPriceType(), dmsg.getSettlPriceType());
            assertEquals(msg.getUnderlyingSettlPrice(), dmsg.getUnderlyingSettlPrice());
            assertEquals(msg.getAccountType(), dmsg.getAccountType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AssignmentReportMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            AssignmentReportMsg msg = (AssignmentReportMsg) FIXMsgBuilder.build(MsgType.AssignmentReport.getValue(), BeginString.FIX_4_4);
            AssignmentReportMsg44TestData.getInstance().populate(msg);
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

            AssignmentReportMsg dmsg = (AssignmentReportMsg) FIXMsgBuilder.build(MsgType.AssignmentReport.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            AssignmentReportMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}