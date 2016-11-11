/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsg50SP2Test.java
 *
 * $Id: NewsMsg50SP2Test.java,v 1.4 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.NewsMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 NewsMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/03/2009, 7:38:11 PM
 */
public class NewsMsg50SP2Test extends MsgTest {

    public NewsMsg50SP2Test() {
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
     * Test of encode method, of class NewsMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setHeadline("Main Headline");
        msg.setNoLinesOfText(new Integer(2));
        msg.getLinesOfTextGroups()[0].setText("line of text 1");
        msg.getLinesOfTextGroups()[1].setText("line of text 2");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getHeadline(), dmsg.getHeadline());
        assertEquals(msg.getNoLinesOfText().intValue(), dmsg.getNoLinesOfText().intValue());
        assertEquals(msg.getLinesOfTextGroups()[0].getText(), dmsg.getLinesOfTextGroups()[0].getText());
        assertEquals(msg.getLinesOfTextGroups()[1].getText(), dmsg.getLinesOfTextGroups()[1].getText());
    }

    /**
     * Test of encode method, of class IOIMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        NewsMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        NewsMsg dmsg = (NewsMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        NewsMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class NewsMsg 4.2 with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        NewsMsg msg = null;
        try {
            msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class NewsMsg 4.2 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        NewsMsg msg = null;
        try {
            msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    /**
     * Test of encode method, of class NewsMsg with missing NoLinesOfText data.
     */
    @Test
    public void testEncodeMissingReqNoLinesOfText() {
        System.out.println("-->testEncodeMissingReqNoLinesOfText");
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setHeadline("Headline");
            msg.encode();

            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class NewsMsg with missing Headline data.
     */
    @Test
    public void testEncodeMissingReqHeadline() {
        System.out.println("-->testEncodeMissingReqHeadline");
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);

            msg.setNoLinesOfText(new Integer(1));
            msg.getLinesOfTextGroups()[0].setText("Line of text 1");
            msg.encode();

            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [Headline] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllRequired() {
        System.out.println("-->testEncodeMissingAllRequired");
        try {
            NewsMsg msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [Headline] [NoLinesOfText] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////
    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [NewsMsg] message version [5.0SP2].", ex.getMessage());
    }

}
