/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg50SP2Test.java
 *
 * $Id: EmailMsg50SP2Test.java,v 1.5 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.EmailMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 EmailMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 02/04/2009, 8:57:55 AM
 */
public class EmailMsg50SP2Test extends MsgTest {

    public EmailMsg50SP2Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        TestUtils.enableValidation();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class EmailMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setEmailThreadID("Thread1");
        msg.setEmailType(EmailType.New);
        msg.setSubject("Subject text");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getEmailThreadID(), dmsg.getEmailThreadID());
        assertEquals(msg.getEmailType().getValue(), dmsg.getEmailType().getValue());
        assertEquals(msg.getSubject(), dmsg.getSubject());
        assertEquals(msg.getNoLinesOfText().intValue(), dmsg.getNoLinesOfText().intValue());
        assertEquals(msg.getLinesOfTextGroups()[0].getText(), dmsg.getLinesOfTextGroups()[0].getText());
        assertEquals(msg.getLinesOfTextGroups()[1].getText(), dmsg.getLinesOfTextGroups()[1].getText());
    }

    /**
     * Test of encode method, of class EmailMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        EmailMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        EmailMsg dmsg = (EmailMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        EmailMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class EmailMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        EmailMsg msg = null;
        try {
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class EmailMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        EmailMsg msg = null;
        try {
            msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class EmailMsg with missing NoLinesOfText data.
     */
    @Test
    public void testEncodeMissingNoLinesOfText() {
        System.out.println("-->testEncodeMissingNoLinesOfText");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setEmailType(EmailType.AdminReply);
            msg.setEmailThreadID("Thread");
            msg.setSubject("Subject");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class EmailMsg with missing EmailType data.
     */
    @Test
    public void testEncodeMissingEmailType() {
        System.out.println("-->testEncodeMissingEmailType");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setEmailThreadID("Thread");
            msg.setSubject("Subject");
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("TEXT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EmailType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class EmailMsg with missing EmailThreadID data.
     */
    @Test
    public void testEncodeMissingEmailThreadID() {
        System.out.println("-->testEncodeMissingEmailThreadID");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setEmailType(EmailType.AdminReply);
            msg.setSubject("Subject");
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("TEXT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EmailThreadID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class EmailMsg with missing Subject data.
     */
    @Test
    public void testEncodeMissingSubject() {
        System.out.println("-->testEncodeMissingSubject");
        try {
            EmailMsg msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setEmailType(EmailType.AdminReply);
            msg.setEmailThreadID("Thread");
            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("TEXT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Subject] is missing.", ex.getMessage());
        }
    }


    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [EmailMsg] message version [5.0SP2].", ex.getMessage());
    }
}
