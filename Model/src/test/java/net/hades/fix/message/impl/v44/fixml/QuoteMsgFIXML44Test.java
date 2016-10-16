/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsgFIXML44Test.java
 *
 * $Id: QuoteMsgFIXML44Test.java,v 1.2 2010-11-14 08:52:58 vrotaru Exp $
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
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.QuoteMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for QuoteMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class QuoteMsgFIXML44Test extends MsgTest {

    public QuoteMsgFIXML44Test() {
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
     * Test of encode method, of class QuoteMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setQuoteID("X162773883");
            msg.getInstrument().setSymbol("SUN");
            msg.setBidPx(new Double(123.44));
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

            QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getQuoteID(), dmsg.getQuoteID());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getBidPx().doubleValue(), dmsg.getBidPx().doubleValue(), 0.001);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class QuoteMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            QuoteMsg44TestData.getInstance().populate(msg);
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

            QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            QuoteMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}