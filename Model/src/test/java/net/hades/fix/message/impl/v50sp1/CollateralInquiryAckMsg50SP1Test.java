/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralInquiryAckMsg50SP1Test.java
 *
 * $Id: CollateralInquiryAckMsg44Test.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.TestUtils;
import net.hades.fix.message.CollateralInquiryAckMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.CollateralInquiryAckMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP1 CollateralInquiryAckMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 19/12/2011, 12:18:17 PM
 */
public class CollateralInquiryAckMsg50SP1Test extends MsgTest  {

    public CollateralInquiryAckMsg50SP1Test() {
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
        CollateralInquiryAckMsg msg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
        CollateralInquiryAckMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        CollateralInquiryAckMsg dmsg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        CollateralInquiryAckMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class CollateralInquiryAckMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        CollateralInquiryAckMsg msg = null;
        try {
            msg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class CollateralInquiryAckMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        CollateralInquiryAckMsg msg = null;
        try {
            msg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class CollateralReportMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            CollateralInquiryAckMsg msg = (CollateralInquiryAckMsg) FIXMsgBuilder.build(MsgType.CollateralInquiryAck.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [CollInquiryID] [CollInquiryStatus] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [CollateralInquiryAckMsg] message version [5.0SP1].", ex.getMessage());
    }
}
