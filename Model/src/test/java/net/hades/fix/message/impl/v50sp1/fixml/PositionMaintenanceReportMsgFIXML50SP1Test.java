/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionMaintenanceReportMsgFIXML50SP1Test.java
 *
 * $Id: PositionMaintenanceReportMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.PositionMaintenanceReportMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.PositionMaintenanceReportMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PosMaintAction;
import net.hades.fix.message.type.PosMaintStatus;
import net.hades.fix.message.type.PosTransType;
import net.hades.fix.message.type.PosType;

/**
 * Test suite for PositionMaintenanceReportMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/12/2011, 20:57:03
 */
public class PositionMaintenanceReportMsgFIXML50SP1Test extends MsgTest {

    public PositionMaintenanceReportMsgFIXML50SP1Test() {
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
     * Test of encode method, of class PositionMaintenanceReportMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            PositionMaintenanceReportMsg msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            msg.setPosMaintRptID("POS_REQ_2222");
            msg.setPosTransType(PosTransType.Exercise);
            msg.setPosMaintAction(PosMaintAction.Cancel);
            msg.setPosMaintStatus(PosMaintStatus.Completed);
            msg.setClearingBusinessDate(new Date());
            msg.setInstrument();
            msg.getInstrument().setSymbol("MSFT");
            msg.setTransactTime(new Date());
            msg.setNoPositions(1);
            msg.getPositionQtyGroups()[0].setPosType(PosType.PitTradeQty);
            
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

            PositionMaintenanceReportMsg dmsg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getPosMaintRptID(), dmsg.getPosMaintRptID());
            assertEquals(msg.getPosTransType(), dmsg.getPosTransType());
            assertEquals(msg.getPosMaintAction(), dmsg.getPosMaintAction());
            assertEquals(msg.getPosMaintStatus(), dmsg.getPosMaintStatus());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class PositionMaintenanceReportMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            PositionMaintenanceReportMsg msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0SP1);
            PositionMaintenanceReportMsg50SP1TestData.getInstance().populate(msg);
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

            PositionMaintenanceReportMsg dmsg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            PositionMaintenanceReportMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}