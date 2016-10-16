/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportAckMsg50SP1Test.java
 *
 * $Id: TradeCaptureReportAckMsg44Test.java,v 1.1 2011-10-25 08:29:20 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.TradeCaptureReportAckMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.TradeCaptureReportAckMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP1 TradeCaptureReportAckMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class TradeCaptureReportAckMsg50SP1Test extends MsgTest  {

    public TradeCaptureReportAckMsg50SP1Test() {
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
        TradeCaptureReportAckMsg msg = (TradeCaptureReportAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportAck.getValue(), BeginString.FIX_5_0SP1);
        TradeCaptureReportAckMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        TradeCaptureReportAckMsg dmsg = (TradeCaptureReportAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        TradeCaptureReportAckMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        TradeCaptureReportAckMsg msg = null;
        try {
            msg = (TradeCaptureReportAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportAck.getValue(), BeginString.FIX_5_0SP1);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getNoAllocs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getAllocGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class TradeCaptureReportAckMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        TradeCaptureReportAckMsg msg = null;
        try {
            msg = (TradeCaptureReportAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportAck.getValue(), BeginString.FIX_5_0SP1);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setNoAllocs(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addAllocGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteAllocGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearAllocGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class TradeCaptureReportAckMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            TradeCaptureReportAckMsg msg = (TradeCaptureReportAckMsg) FIXMsgBuilder.build(MsgType.TradeCaptureReportAck.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [Symbol] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [TradeCaptureReportAckMsg] message version [5.0SP1].", ex.getMessage());
    }
}
