/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg50SP2Test.java
 *
 * $Id: IOIMsg50SP2Test.java,v 1.5 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.IOIMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for FIX 5.0SP1 IOIMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 02/03/2009, 7:38:11 PM
 */
public class IOIMsg50SP2Test extends MsgTest {

    public IOIMsg50SP2Test() {
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
     * Test of encode method, of class IOIMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.getInstrument().setSymbol("symbol");
        msg.setSide(Side.Borrow);
        msg.setIoiQty(IOIQty.Large);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        IOIMsg dmsg = (IOIMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getIoiID(), dmsg.getIoiID());
        assertEquals(msg.getIoiTransType(), dmsg.getIoiTransType());
        assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
        assertEquals(msg.getSide(), dmsg.getSide());
        assertEquals(msg.getIoiQty(), dmsg.getIoiQty());
    }

    /**
     * Test of encode method, of class IOIMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        IOIMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        IOIMsg dmsg = (IOIMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        IOIMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class IOIMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedIOIMsgTag");
        IOIMsg msg = null;
        try {
            msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class IOIMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedTag() {
        System.out.println("-->testSetUnsupportedIOIMsgTag");
        IOIMsg msg = null;
        try {
            msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class IOIMsg with missing IOIID data.
     */
    @Test
    public void x1_testEncodeMissingIOIID() {
        System.out.println("-->testEncodeMissingIOIID");
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setIoiTransType(IOITransType.New);
            msg.getInstrument().setSymbol("symbol");
            msg.setSide(Side.Borrow);
            msg.setIoiQty(IOIQty.Large);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [IOIID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class IOIMsg with missing IOITransType data.
     */
    @Test
    public void x2_testEncodeMissingIOITransType() {
        System.out.println("-->testEncodeMissingIOITransType");
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setIoiID("IOI ID");
            msg.getInstrument().setSymbol("symbol");
            msg.setSide(Side.Borrow);
            msg.setIoiQty(IOIQty.Large);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [IOITransType] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class IOIMsg with missing Symbol data.
     */
    @Test
    public void x3_testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setIoiID("IOI ID");
            msg.setIoiTransType(IOITransType.New);
            msg.setSide(Side.Borrow);
            msg.setIoiQty(IOIQty.Large);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     *  Test of encode method, of class IOIMsg with missing Side data.
     */
    @Test
    public void x4_testEncodeMissingSide() {
        System.out.println("-->testEncodeMissingSide");
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setIoiID("IOI ID");
            msg.setIoiTransType(IOITransType.New);
            msg.getInstrument().setSymbol("symbol");
            msg.setIoiQty(IOIQty.Large);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Side] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class IOIMsg with missing IOIQty data.
     */
    @Test
    public void x5_testEncodeMissingIOIQty() {
        System.out.println("-->testEncodeMissingIOIQty");
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setIoiID("IOI ID");
            msg.setIoiTransType(IOITransType.New);
            msg.getInstrument().setSymbol("symbol");
            msg.setSide(Side.Borrow);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [IOIQty] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class IOIMsg with missing all required data.
     */
    @Test
    public void x6_testEncodeMissingAll() {
        System.out.println("-->testEncodeMissingAll");
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [IOIID] [IOITransType] [Instrument] [Side] [IOIQty] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [IOIMsg] message version [5.0SP2].", ex.getMessage());
    }
}
