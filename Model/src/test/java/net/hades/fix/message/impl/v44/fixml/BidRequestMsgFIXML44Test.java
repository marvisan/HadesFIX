/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRequestMsgFIXML44Test.java
 *
 * $Id: BidRequestMsgFIXML44Test.java,v 1.1 2011-04-13 10:13:46 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.BidRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.BidRequestMsg44TestData;
import net.hades.fix.message.type.BasisPxType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BidRequestTransType;
import net.hades.fix.message.type.BidTradeType;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for BidRequestMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class BidRequestMsgFIXML44Test extends MsgTest {

    public BidRequestMsgFIXML44Test() {
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
     * Test of encode method, of class BidRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            BidRequestMsg msg = (BidRequestMsg) FIXMsgBuilder.build(MsgType.BidRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setClientBidID("CLIBID7364844");
            msg.setBidRequestTransType(BidRequestTransType.Cancel);
            msg.setTotNoRelatedSym(3);
            msg.setBidType(BidType.Disclosed);
            msg.setBidTradeType(BidTradeType.RiskTrade);
            msg.setBasisPxType(BasisPxType.ClosingPrice);
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

            BidRequestMsg dmsg = (BidRequestMsg) FIXMsgBuilder.build(MsgType.BidRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            
            assertEquals(msg.getClientBidID(), dmsg.getClientBidID());
            assertEquals(msg.getBidRequestTransType(), dmsg.getBidRequestTransType());
            assertEquals(msg.getTotNoRelatedSym(), dmsg.getTotNoRelatedSym());
            assertEquals(msg.getBidType(), dmsg.getBidType());
            assertEquals(msg.getBidTradeType(), dmsg.getBidTradeType());
            assertEquals(msg.getBasisPxType(), dmsg.getBasisPxType());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class BidRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            BidRequestMsg msg = (BidRequestMsg) FIXMsgBuilder.build(MsgType.BidRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            BidRequestMsg44TestData.getInstance().populate(msg);
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

            BidRequestMsg dmsg = (BidRequestMsg) FIXMsgBuilder.build(MsgType.BidRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            BidRequestMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}