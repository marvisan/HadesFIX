/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg44Test.java
 *
 * $Id: IOIMsg44Test.java,v 1.4 2010-03-21 11:25:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;


import quickfix.DataDictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.IOIMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for FIX 4.4 IOIMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/03/2009, 7:38:11 PM
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/03/2009, 7:38:11 PM
 */
public class IOIMsg44Test extends MsgTest {

    private DataDictionary dictionary;

    public IOIMsg44Test() {
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
        dictionary = getQF44DataDictionary();
        IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setIoiID("IOI ID");
        msg.setIoiTransType(IOITransType.New);
        msg.getInstrument().setSymbol("symbol");
        msg.setSide(Side.Borrow);
        msg.setIoiQty(IOIQty.Large);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getIoiID(), qfMsg.getString(quickfix.field.IOIID.FIELD));
        assertEquals(msg.getIoiTransType().getValue(), qfMsg.getString(quickfix.field.IOITransType.FIELD));
        assertEquals(msg.getInstrument().getSymbol(), qfMsg.getString(quickfix.field.Symbol.FIELD));
        assertEquals(msg.getSide().getValue(), qfMsg.getChar(quickfix.field.Side.FIELD));
        assertEquals(msg.getIoiQty().getValue(), qfMsg.getString(quickfix.field.IOIQty.FIELD));
    }

    /**
     * Test of encode method, of class IOIMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF44DataDictionary();
        IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
        IOIMsg44TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix44.IndicationOfInterest qfMsg = new quickfix.fix44.IndicationOfInterest();
        qfMsg.fromString(encoded, dictionary, true);
        IOIMsg44TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class IOIMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.IndicationOfInterest msg = new quickfix.fix44.IndicationOfInterest();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.IOIID.FIELD, "IOI ID");
        msg.setString(quickfix.field.IOITransType.FIELD, IOITransType.New.getValue());
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setChar(quickfix.field.Side.FIELD, Side.BuyMinus.getValue());
        msg.setString(quickfix.field.IOIQty.FIELD, IOIQty.Large.getValue());
        String strMsg = msg.toString();

        System.out.println("qfix msg-->" + strMsg);
        IOIMsg dmsg = (IOIMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals( msg.getString(quickfix.field.IOIID.FIELD), dmsg.getIoiID());
        assertEquals(msg.getString(quickfix.field.IOITransType.FIELD), dmsg.getIoiTransType().getValue());
        assertEquals(msg.getString(quickfix.field.Symbol.FIELD), dmsg.getInstrument().getSymbol());
        assertEquals(msg.getChar(quickfix.field.Side.FIELD), dmsg.getSide().getValue());
        assertEquals(msg.getString(quickfix.field.IOIQty.FIELD), dmsg.getIoiQty().getValue());
    }

    /**
     * Test of decode method, of class AdvertismentMsg for FIX 4.0 all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF44DataDictionary();
        quickfix.fix44.IndicationOfInterest msg = new quickfix.fix44.IndicationOfInterest();
        IOIMsg44TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        IOIMsg dmsg = (IOIMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        IOIMsg44TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
            IOIMsg44TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            IOIMsg dmsg = (IOIMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            IOIMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class IOIMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedIOIMsgTag");
        IOIMsg msg = null;
        try {
            msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearApplicationSequenceControl();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
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
            IOIMsg msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [IOIID] [IOITransType] [Instrument] [Side] [IOIQty] is missing.", ex.getMessage());
        }
    }

    @Test
    public void y1_testDecodeMissingReq() throws Exception {
        System.out.println("-->testDecodeMissingRequired");
        try {
            dictionary = getQF44DataDictionary();
            quickfix.fix44.IndicationOfInterest msg = new quickfix.fix44.IndicationOfInterest();
            TestUtils.populateQuickFIX44HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [IOIID] [IOITransType] [Instrument] [Side] [IOIQty] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [IOIMsg] message version [4.4].", ex.getMessage());
    }
}
