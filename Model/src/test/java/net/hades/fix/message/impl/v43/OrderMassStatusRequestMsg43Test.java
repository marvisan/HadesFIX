/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassStatusRequestMsg43Test.java
 *
 * $Id: OrderMassStatusRequestMsg43Test.java,v 1.1 2011-05-09 08:21:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderMassStatusRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v43.data.OrderMassStatusRequestMsg43TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.3 OrderMassStatusRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class OrderMassStatusRequestMsg43Test extends MsgTest  {

    public OrderMassStatusRequestMsg43Test() {
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
        OrderMassStatusRequestMsg msg = (OrderMassStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderMassStatusRequest.getValue(), BeginString.FIX_4_3);
        OrderMassStatusRequestMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        OrderMassStatusRequestMsg dmsg = (OrderMassStatusRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        OrderMassStatusRequestMsg43TestData.getInstance().check(msg, dmsg);
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
            OrderMassStatusRequestMsg msg = (OrderMassStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderMassStatusRequest.getValue(), BeginString.FIX_4_3);
            OrderMassStatusRequestMsg43TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            OrderMassStatusRequestMsg dmsg = (OrderMassStatusRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            OrderMassStatusRequestMsg43TestData.getInstance().check(msg, dmsg);
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
        OrderMassStatusRequestMsg msg = null;
        try {
            msg = (OrderMassStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderMassStatusRequest.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getTargetParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class OrderMassStatusRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        OrderMassStatusRequestMsg msg = null;
        try {
            msg = (OrderMassStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderMassStatusRequest.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
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
     * Test of encode method, of class OrderMassStatusRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            OrderMassStatusRequestMsg msg = (OrderMassStatusRequestMsg) FIXMsgBuilder.build(MsgType.OrderMassStatusRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [MassStatusReqID] [MassStatusReqType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [OrderMassStatusRequestMsg] message version [4.3].", ex.getMessage());
    }
}
