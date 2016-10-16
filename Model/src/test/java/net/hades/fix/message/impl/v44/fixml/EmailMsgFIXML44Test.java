/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsgFIXML44Test.java
 *
 * $Id: EmailMsgFIXML44Test.java,v 1.2 2010-11-14 08:52:58 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.EmailMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for EmailMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/10/2008, 20:57:03
 */
public class EmailMsgFIXML44Test extends MsgTest {

    public EmailMsgFIXML44Test() {
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
     * Test of encode method, of class EmailMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setEmailThreadID("Thread1");
            msg.setEmailType(EmailType.New);
            msg.setSubject("Subject text");
            msg.setNoLinesOfText(new Integer(2));
            msg.getLinesOfTextGroups()[0].setText("line of text 1");
            msg.getLinesOfTextGroups()[1].setText("line of text 2");
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

            EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getEmailThreadID(), dmsg.getEmailThreadID());
            assertEquals(msg.getEmailType().getValue(), dmsg.getEmailType().getValue());
            assertEquals(msg.getSubject(), dmsg.getSubject());
            assertEquals(msg.getNoLinesOfText().intValue(), dmsg.getNoLinesOfText().intValue());
            assertEquals(msg.getLinesOfTextGroups()[0].getText(), dmsg.getLinesOfTextGroups()[0].getText());
            assertEquals(msg.getLinesOfTextGroups()[1].getText(), dmsg.getLinesOfTextGroups()[1].getText());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class EmailMsg for FIXML 4.1.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            EmailMsg44TestData.getInstance().populate(msg);
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

            EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            EmailMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}