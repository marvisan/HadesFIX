/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestRejectMsg50SP2Test.java
 *
 * $Id: MarketDataRequestRejectMsg50SP2Test.java,v 1.2 2010-03-21 11:25:16 vrotaru Exp $
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
import net.hades.fix.message.MarketDataRequestRejectMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp1.data.MarketDataRequestRejectMsg50SP1TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 MarketDataRequestRejectMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MarketDataRequestRejectMsg50SP2Test extends MsgTest  {

    public MarketDataRequestRejectMsg50SP2Test() {
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
        MarketDataRequestRejectMsg msg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(),
                BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        MarketDataRequestRejectMsg50SP1TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        MarketDataRequestRejectMsg dmsg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MarketDataRequestRejectMsg50SP1TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b4_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            MarketDataRequestRejectMsg msg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(),
                    BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            MarketDataRequestRejectMsg50SP1TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            MarketDataRequestRejectMsg dmsg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            MarketDataRequestRejectMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MarketDataRequestRejectMsg msg = null;
        try {
            msg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(),
                    BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class MarketDataRequestRejectMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MarketDataRequestRejectMsg msg = null;
        try {
            msg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(),
                    BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class MarketDataRequestRejectMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingMDIncGroups() {
        System.out.println("-->testEncodeMissingMDIncGroups");
        try {
            MarketDataRequestRejectMsg msg = (MarketDataRequestRejectMsg) FIXMsgBuilder.build(MsgType.MarketDataRequestReject.getValue(),
                    BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [MDReqID] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MarketDataRequestRejectMsg] message version [5.0SP2].", ex.getMessage());
    }
}
