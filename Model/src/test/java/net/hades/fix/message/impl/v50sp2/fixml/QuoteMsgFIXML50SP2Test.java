/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsgFIXML50SP2Test.java
 *
 * $Id: QuoteMsgFIXML50SP2Test.java,v 1.2 2010-10-04 05:15:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.QuoteMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for QuoteMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class QuoteMsgFIXML50SP2Test extends MsgTest {

    public QuoteMsgFIXML50SP2Test() {
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
     * Test of encode method, of class QuoteMsg for FIXML 5.0SP2.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        setSessionApplVerID(ApplVerID.FIX50SP2);
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setQuoteID("X162773883");
            msg.getInstrument().setSymbol("SUN");
            msg.setBidPx(new Double(123.44));
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

            QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getQuoteID(), dmsg.getQuoteID());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getBidPx().doubleValue(), dmsg.getBidPx().doubleValue(), 0.001);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class QuoteMsg for FIXML 5.0SP2.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        setSessionApplVerID(ApplVerID.FIX50SP2);
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            QuoteMsg50SP2TestData.getInstance().populate(msg);
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

            QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            dmsg.fromFixml(fixml);
            QuoteMsg50SP2TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}