/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusRequestMsg50SP2Test.java
 *
 * $Id: TradingSessionStatusRequestMsg50SP2Test.java,v 1.1 2011-04-22 01:59:22 vrotaru Exp $
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
import net.hades.fix.message.TradingSessionStatusRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.TradingSessionStatusRequestMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP1 TradingSessionStatusRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class TradingSessionStatusRequestMsg50SP2Test extends MsgTest  {

    public TradingSessionStatusRequestMsg50SP2Test() {
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
        TradingSessionStatusRequestMsg msg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0SP2);
        TradingSessionStatusRequestMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        TradingSessionStatusRequestMsg dmsg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        TradingSessionStatusRequestMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        TradingSessionStatusRequestMsg msg = null;
        try {
            msg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class TradingSessionStatusRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        TradingSessionStatusRequestMsg msg = null;
        try {
            msg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class TradingSessionStatusRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            TradingSessionStatusRequestMsg msg = (TradingSessionStatusRequestMsg) FIXMsgBuilder.build(MsgType.TradingSessionStatusRequest.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [TradSesReqID] [SubscriptionRequestType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [TradingSessionStatusRequestMsg] message version [5.0SP2].", ex.getMessage());
    }
}
