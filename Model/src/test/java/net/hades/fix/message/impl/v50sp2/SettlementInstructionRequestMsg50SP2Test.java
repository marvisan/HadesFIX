/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionRequestMsg50SP2Test.java
 *
 * $Id: SettlementInstructionRequestMsg44Test.java,v 1.1 2011-03-26 03:24:27 vrotaru Exp $
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
import net.hades.fix.message.SettlementInstructionRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.SettlementInstructionRequestMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 SettlementInstructionRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class SettlementInstructionRequestMsg50SP2Test extends MsgTest  {

    public SettlementInstructionRequestMsg50SP2Test() {
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
        SettlementInstructionRequestMsg msg = (SettlementInstructionRequestMsg) FIXMsgBuilder.build(MsgType.SettlementInstructionRequest.getValue(), BeginString.FIX_5_0SP2);
        SettlementInstructionRequestMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        SettlementInstructionRequestMsg dmsg = (SettlementInstructionRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        SettlementInstructionRequestMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        SettlementInstructionRequestMsg msg = null;
        try {
            msg = (SettlementInstructionRequestMsg) FIXMsgBuilder.build(MsgType.SettlementInstructionRequest.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class SettlementInstructionRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        SettlementInstructionRequestMsg msg = null;
        try {
            msg = (SettlementInstructionRequestMsg) FIXMsgBuilder.build(MsgType.SettlementInstructionRequest.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class SettlementInstructionRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            SettlementInstructionRequestMsg msg = (SettlementInstructionRequestMsg) FIXMsgBuilder.build(MsgType.SettlementInstructionRequest.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [SettlInstReqID] [TransactTime] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [SettlementInstructionRequestMsg] message version [5.0SP2].", ex.getMessage());
    }
}
