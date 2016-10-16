/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportRequestMsg50SP2Test.java
 *
 * $Id: TradeCaptureReportRequestMsg50SP2Test.java,v 1.1 2011-10-08 08:43:05 vrotaru Exp $
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
import net.hades.fix.message.TradeCaptureReportRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.TradeCaptureReportRequestMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 TradeCaptureReportRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class TradeCaptureReportRequestMsg50SP2Test extends MsgTest  {

    public TradeCaptureReportRequestMsg50SP2Test() {
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
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        TradeCaptureReportRequestMsg msg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0SP2);
        TradeCaptureReportRequestMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        TradeCaptureReportRequestMsg dmsg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        TradeCaptureReportRequestMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        TradeCaptureReportRequestMsg msg = null;
        try {
            msg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class TradeCaptureReportRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        TradeCaptureReportRequestMsg msg = null;
        try {
            msg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class TradeCaptureReportRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            TradeCaptureReportRequestMsg msg = (TradeCaptureReportRequestMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportRequest.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [TradeRequestID] [TradeRequestType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [TradeCaptureReportRequestMsg] message version [5.0SP2].", ex.getMessage());
    }
}
