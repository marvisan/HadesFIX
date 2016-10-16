/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderModificationRequestMsg43Test.java
 *
 * $Id: OrderModificationRequestMsg43Test.java,v 1.1 2011-01-21 10:23:14 vrotaru Exp $
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
import net.hades.fix.message.OrderModificationRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v43.data.OrderModificationRequestMsg43TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.3 OrderModificationRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class OrderModificationRequestMsg43Test extends MsgTest  {

    public OrderModificationRequestMsg43Test() {
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
        OrderModificationRequestMsg msg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_3);
        OrderModificationRequestMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        OrderModificationRequestMsg dmsg = (OrderModificationRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        OrderModificationRequestMsg43TestData.getInstance().check(msg, dmsg);
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
            OrderModificationRequestMsg msg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_3);
            OrderModificationRequestMsg43TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            OrderModificationRequestMsg dmsg = (OrderModificationRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            OrderModificationRequestMsg43TestData.getInstance().check(msg, dmsg);
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
        OrderModificationRequestMsg msg = null;
        try {
            msg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getDisplayInstruction();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getFinancingDetails();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoUnderlyings();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getUnderlyingInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getTriggeringInstruction();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getPegInstructions();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getDiscretionInstructions();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoStrategyParameters();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getStrategyParametersGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoTrdRegTimestamps();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getTrdRegTimestampsGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class OrderModificationRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        OrderModificationRequestMsg msg = null;
        try {
            msg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setDisplayInstruction();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setFinancingDetails();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoUnderlyings(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addUnderlyingInstrument();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteUnderlyingInstrument(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearUnderlyingInstruments();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setTriggeringInstruction();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setPegInstructions();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setDiscretionInstructions();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoStrategyParameters(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addStrategyParametersGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteStrategyParametersGroup(0);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearStrategyParametersGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoTrdRegTimestamps(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addTrdRegTimestampsGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteTrdRegTimestampsGroup(0);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearTrdRegTimestampsGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class OrderModificationRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            OrderModificationRequestMsg msg = (OrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.OrderModificationRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [OrigClOrdID] [ClOrdID] [HandlInst] [Symbol] [Side] [TransactTime] [OrderQty] [OrdType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [OrderModificationRequestMsg] message version [4.3].", ex.getMessage());
    }
}
