/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsg50SP2Test.java
 *
 * $Id: OrderMassCancelReportMsg50SP2Test.java,v 1.2 2011-05-07 07:05:31 vrotaru Exp $
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
import net.hades.fix.message.OrderMassCancelReportMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.OrderMassCancelReportMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0SP2 OrderMassCancelReportMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 07/05/2011, 12:18:17 PM
 */
public class OrderMassCancelReportMsg50SP2Test extends MsgTest  {

    public OrderMassCancelReportMsg50SP2Test() {
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
        OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP2);
        OrderMassCancelReportMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        OrderMassCancelReportMsg dmsg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        OrderMassCancelReportMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        OrderMassCancelReportMsg msg = null;
        try {
            msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class OrderMassCancelReportMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        OrderMassCancelReportMsg msg = null;
        try {
            msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class OrderMassCancelReportMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0SP2);
            TestUtils.populate50HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [OrderID] [MassActionReportID] [MassCancelRequestType] [MassCancelResponse] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [OrderMassCancelReportMsg] message version [5.0SP2].", ex.getMessage());
    }
}
