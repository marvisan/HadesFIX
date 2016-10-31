/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsgFIXML44Test.java
 *
 * $Id: MarketDataRequestMsgFIXML44Test.java,v 1.2 2010-11-14 08:52:58 vrotaru Exp $
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
import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.MarketDataRequestMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDEntryType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SubscriptionRequestType;

/**
 * Test suite for MarketDataRequestMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class MarketDataRequestMsgFIXML44Test extends MsgTest {

    public MarketDataRequestMsgFIXML44Test() {
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
     * Test of encode method, of class MarketDataRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setMdReqID("X162773883");
            msg.setSubscriptionRequestType(SubscriptionRequestType.Snapshot);
            msg.setMarketDepth(new Integer(3));
            msg.setNoMDEntryTypes(1);
            msg.getMdEntryTypeGroups()[0].setMdEntryType(MDEntryType.Bid.getValue());
            msg.setNoRelatedSym(1);
            msg.getMdReqGroups()[0].getInstrument().setSymbol("IBM");
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

            MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getMdReqID(), dmsg.getMdReqID());
            assertEquals(msg.getSubscriptionRequestType().getValue(), dmsg.getSubscriptionRequestType().getValue());
            assertEquals(msg.getMarketDepth().intValue(), dmsg.getMarketDepth().intValue());
            assertEquals(msg.getNoMDEntryTypes().intValue(), dmsg.getNoMDEntryTypes().intValue());
            assertEquals(msg.getNoRelatedSym().intValue(), dmsg.getNoRelatedSym().intValue());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class MarketDataRequestMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            MarketDataRequestMsg msg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            MarketDataRequestMsg44TestData.getInstance().populate(msg);
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

            MarketDataRequestMsg dmsg = (MarketDataRequestMsg) FIXMsgBuilder.build(MsgType.MarketDataRequest.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            MarketDataRequestMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}