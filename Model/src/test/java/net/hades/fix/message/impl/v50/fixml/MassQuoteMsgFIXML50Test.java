/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsgFIXML50Test.java
 *
 * $Id: MassQuoteMsgFIXML50Test.java,v 1.3 2010-12-12 09:13:09 vrotaru Exp $
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
import net.hades.fix.message.MassQuoteMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.MassQuoteMsg50TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for MassQuoteMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 20/10/2008, 20:57:03
 */
public class MassQuoteMsgFIXML50Test extends MsgTest {

    public MassQuoteMsgFIXML50Test() {
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
     * Test of encode method, of class MassQuoteMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populate50HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50);
            msg.setQuoteID("X162773883");
            msg.setNoQuoteSets(new Integer(1));
            msg.getQuoteSetGroups()[0].setNoQuoteEntries(new Integer(1));
            msg.getQuoteSetGroups()[0].getQuoteEntryGroups()[0].setQuoteEntryID("TTT73849444");
            msg.getQuoteSetGroups()[0].setQuoteSetID("UUU94773666");
            msg.getQuoteSetGroups()[0].setUnderlyingInstrument();
            msg.getQuoteSetGroups()[0].getUnderlyingInstrument().setUnderlyingSymbol("IBM");
            msg.getQuoteSetGroups()[0].setTotNoQuoteEntries(new Integer(3));

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

            MassQuoteMsg dmsg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getQuoteID(), dmsg.getQuoteID());
            assertEquals(msg.getNoQuoteSets().intValue(), dmsg.getNoQuoteSets().intValue());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class MassQuoteMsg for FIXML 4.1.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            MassQuoteMsg50TestData.getInstance().populate(msg);
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

            MassQuoteMsg dmsg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            dmsg.fromFixml(fixml);
            MassQuoteMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}