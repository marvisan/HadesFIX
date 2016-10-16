/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsgFIXML50Test.java
 *
 * $Id: QuoteRequestRejectMsgFIXML50Test.java,v 1.3 2010-12-12 09:13:09 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50.QuoteRequestRejectGroup50TestData;
import net.hades.fix.message.impl.v50.data.QuoteRequestRejectMsg50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteRequestRejectReason;

/**
 * Test suite for QuoteRequestRejectMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 20/10/2008, 20:57:03
 */
public class QuoteRequestRejectMsgFIXML50Test extends MsgTest {

    public QuoteRequestRejectMsgFIXML50Test() {
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
     * Test of encode method, of class QuoteRequestRejectMsg for FIXML 5.0.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populate50HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50);
            msg.setQuoteReqID("X162773883");
            msg.setQuoteRequestRejectReason(QuoteRequestRejectReason.ExchangeClosed.getValue());
            msg.setNoRelatedSym(new Integer(1));
            QuoteRequestRejectGroup50TestData.getInstance().populate1(msg.getQuoteRequestRejectGroups()[0]);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            QuoteRequestRejectMsg dmsg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getQuoteReqID(), dmsg.getQuoteReqID());
            assertEquals(msg.getQuoteRequestRejectReason().intValue(), dmsg.getQuoteRequestRejectReason().intValue());
            assertEquals(msg.getNoRelatedSym().intValue(), dmsg.getNoRelatedSym().intValue());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class QuoteRequestRejectMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            QuoteRequestRejectMsg msg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            QuoteRequestRejectMsg50TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            QuoteRequestRejectMsg dmsg = (QuoteRequestRejectMsg) FIXMsgBuilder.build(MsgType.QuoteRequestReject.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            dmsg.fromFixml(fixml);
            QuoteRequestRejectMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}