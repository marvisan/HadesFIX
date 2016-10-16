/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsgFIXML50SP1Test.java
 *
 * $Id: NewsMsgFIXML50SP1Test.java,v 1.2 2010-11-14 08:52:58 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.NewsMsg50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for NewsMsg50SP1 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class NewsMsgFIXML50SP1Test extends MsgTest {

    public NewsMsgFIXML50SP1Test() {
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
     * Test of encode method, of class NewsMsg for FIXML 5.0.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        setSessionApplVerID(ApplVerID.FIX50SP1);
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setHeadline("Main Headline");
            msg.setNoLinesOfText(new Integer(2));
            msg.getLinesOfTextGroups()[0].setText("line of text 1");
            msg.getLinesOfTextGroups()[1].setText("line of text 2");
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

            NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getHeadline(), dmsg.getHeadline());
            assertEquals(msg.getNoLinesOfText().intValue(), dmsg.getNoLinesOfText().intValue());
            assertEquals(msg.getLinesOfTextGroups()[0].getText(), dmsg.getLinesOfTextGroups()[0].getText());
            assertEquals(msg.getLinesOfTextGroups()[1].getText(), dmsg.getLinesOfTextGroups()[1].getText());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class NewsMsg for FIXML 5.0.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        setSessionApplVerID(ApplVerID.FIX50SP1);
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            TestUtils.populateFIXT11HeaderAll(msg);
            NewsMsg50SP1TestData.getInstance().populate(msg);
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

            NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP1);
            dmsg.fromFixml(fixml);
            NewsMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}