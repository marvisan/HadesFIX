/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg50SP2Test.java
 *
 * $Id: QuoteStatusReportMsg50SP2Test.java,v 1.4 2010-03-21 11:25:16 vrotaru Exp $
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
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.QuoteStatusReportMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 QuoteStatusReportMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteStatusReportMsg50SP2Test extends MsgTest  {

    public QuoteStatusReportMsg50SP2Test() {
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
     * Test of encode method, of class QuoteMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        QuoteStatusReportMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteStatusReportMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteStatusReportRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteStatusReportMsg msg = null;
        try {
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class QuoteMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteStatusReportMsg msg = null;
        try {
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteStatusReportMsg] message version [5.0SP2].", ex.getMessage());
    }
}
