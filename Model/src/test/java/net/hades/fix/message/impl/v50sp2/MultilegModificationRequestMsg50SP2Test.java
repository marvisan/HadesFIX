/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MultilegModificationRequestMsg50SP2Test.java
 *
 * $Id: MultilegModificationRequestMsg50SP2Test.java,v 1.1 2011-09-10 05:41:13 vrotaru Exp $
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
import net.hades.fix.message.MultilegModificationRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.MultilegModificationRequestMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for MultilegModificationRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MultilegModificationRequestMsg50SP2Test extends MsgTest  {

    public MultilegModificationRequestMsg50SP2Test() {
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
        MultilegModificationRequestMsg msg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIX_5_0SP2);
        MultilegModificationRequestMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MultilegModificationRequestMsg dmsg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MultilegModificationRequestMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MultilegModificationRequestMsg msg = null;
        try {
            msg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class MultilegModificationRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MultilegModificationRequestMsg msg = null;
        try {
            msg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class MultilegModificationRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            MultilegModificationRequestMsg msg = (MultilegModificationRequestMsg) FIXMsgBuilder.build(MsgType.MultilegModificationRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [OrigClOrdID] [ClOrdID] [Symbol] [Side] [LegOrdGroups] [OrderQty] [OrdType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MultilegModificationRequestMsg] message version [5.0SP2].", ex.getMessage());
    }
}
