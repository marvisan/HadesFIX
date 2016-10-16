/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsg50Test.java
 *
 * $Id: OrderMassCancelReportMsg50Test.java,v 1.1 2011-05-07 06:58:54 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

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
import net.hades.fix.message.impl.v50.data.OrderMassCancelReportMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0 OrderMassCancelReportMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/05/2011, 12:18:17 PM
 */
public class OrderMassCancelReportMsg50Test extends MsgTest  {

    public OrderMassCancelReportMsg50Test() {
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
        OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0);
        OrderMassCancelReportMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        OrderMassCancelReportMsg dmsg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        OrderMassCancelReportMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        OrderMassCancelReportMsg msg = null;
        try {
            msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getNotAffectedOrdGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getTargetParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setNoNotAffectedOrders(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addNotAffectedOrdGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteNotAffectedOrdGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearNotAffectedOrdGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setTargetParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearTargetParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            OrderMassCancelReportMsg msg = (OrderMassCancelReportMsg) FIXMsgBuilder.build(MsgType.OrderMassCancelReport.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [OrderID] [MassCancelRequestType] [MassCancelResponse] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [OrderMassCancelReportMsg] message version [5.0].", ex.getMessage());
    }
}
