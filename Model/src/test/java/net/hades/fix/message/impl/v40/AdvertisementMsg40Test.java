/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertismentMsgTest40.java
 *
 * $Id: AdvertisementMsg40Test.java,v 1.4 2010-03-21 10:18:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v40.data.AdvertisementMsg40TestData;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.0 AdvertisementMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 20/10/2008, 20:57:03
 */
public class AdvertisementMsg40Test extends MsgTest {

    private DataDictionary dictionary;
    
    public AdvertisementMsg40Test() {
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
     * Test of encode method, of class AdvertisementMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF40DataDictionary();
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.New);
        msg.setSymbol("MOT");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(45, qfMsg.getInt(quickfix.field.AdvId.FIELD));
        assertEquals(AdvTransType.New.getValue(), qfMsg.getString(quickfix.field.AdvTransType.FIELD));
        assertEquals("MOT", qfMsg.getString(quickfix.field.Symbol.FIELD));
        assertEquals(AdvSide.Buy.getValue(), qfMsg.getString(quickfix.field.AdvSide.FIELD));
        assertEquals(200.0, qfMsg.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), 0.1);
    }
    
    /**
     * Test of encode method, of class AdvertisementMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF40DataDictionary();
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
        AdvertisementMsg40TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix40.Advertisement qfMsg = new quickfix.fix40.Advertisement();
        qfMsg.fromString(encoded, dictionary, true);
        AdvertisementMsg40TestData.getInstance().check(msg, qfMsg);
    }
    
    /**
     * Test of decode method, of class AdvertisementMsg for FIX 4.0 only required.
     * @throws Exception 
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF40DataDictionary();
        quickfix.fix40.Advertisement msg = new quickfix.fix40.Advertisement();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(quickfix.field.AdvId.FIELD, "45");
        msg.setString(quickfix.field.AdvTransType.FIELD, "N");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.AdvSide.FIELD, "B");
        msg.setDecimal(quickfix.field.Quantity.FIELD, new BigDecimal("200"));
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);
        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals("45", dmsg.getAdvID());
        assertEquals("N", dmsg.getAdvTransType().getValue());
        assertEquals("MOT", dmsg.getSymbol());
        assertEquals("B", dmsg.getAdvSide().getValue());
        assertEquals(200.0, dmsg.getQuantity().doubleValue(), 0.1);
    }
    
    /**
     * Test of decode method, of class AdvertisementMsg for FIX 4.0 all fields.
     * @throws Exception 
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF40DataDictionary();
        quickfix.fix40.Advertisement msg = new quickfix.fix40.Advertisement();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        AdvertisementMsg40TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        AdvertisementMsg40TestData.getInstance().check(msg, dmsg);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
            AdvertisementMsg40TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            AdvertisementMsg40TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class AdvertisementMsg 4.0 with unsupported tag.
     */
    @Test
    public void x100_testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getInstrument();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoLegs();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getInstrumentLegs();
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
    }

    /**
     * Test of encode setter method, of class AdvertisementMsg 4.0 with unsupported tag.
     */
    @Test
    public void x101_testSetUnsupportedTag() {
        System.out.println("-->testSetUnsupportedAdvertismentMsgTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setNoLegs(new Integer(2));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addInstrumentLeg();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteInstrumentLeg(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearInstrumentLegs();
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
            msg.deleteUnderlyingInstrument(0);
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
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class AdvertisementMsg with missing AdvID data.
     */
    @Test
    public void x1_testEncodeMissingAdvID() {
        System.out.println("-->testEncodeMissingAdvID");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setAdvTransType(AdvTransType.New);
            msg.setSymbol("MOT");
            msg.setAdvSide(AdvSide.Buy);
            msg.setQuantity(new Double("200"));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [AdvID] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class AdvertisementMsg with missing AdvTransType data.
     */
    @Test
    public void x2_testEncodeMissingAdvTransType() {
        System.out.println("-->testEncodeMissingAdvTransType");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setAdvID("45");
            msg.setSymbol("MOT");
            msg.setAdvSide(AdvSide.Buy);
            msg.setQuantity(new Double("200"));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [AdvTransType] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class AdvertisementMsg with missing Symbol data.
     */
    @Test
    public void x3_testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setAdvID("45");
            msg.setAdvTransType(AdvTransType.New);
            msg.setAdvSide(AdvSide.Buy);
            msg.setQuantity(new Double("200"));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Symbol] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class AdvertisementMsg with missing AdvSide data.
     */
    @Test
    public void x4_testEncodeMissingAdvSide() {
        System.out.println("-->testEncodeMissingAdvSide");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setAdvID("45");
            msg.setAdvTransType(AdvTransType.New);
            msg.setSymbol("MOT");
            msg.setQuantity(new Double("200"));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [AdvSide] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class AdvertisementMsg with missing Quantity data.
     */
    @Test
    public void x5_testEncodeMissingQuantity() {
        System.out.println("-->testEncodeMissingQuantity");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setAdvID("45");
            msg.setAdvTransType(AdvTransType.New);
            msg.setSymbol("MOT");
            msg.setAdvSide(AdvSide.Buy);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Quantity] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class AdvertisementMsg with missing all required data.
     */
    @Test
    public void x6_testEncodeMissingAll() {
        System.out.println("-->testEncodeMissingQuantity");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [AdvID] [AdvTransType] [Symbol] [AdvSide] [Quantity] is missing.", ex.getMessage());
        }
    }
    
    @Test
    public void y1_testDecodeMissingReq() throws Exception {
        System.out.println("-->testDecodeMissingRequired");
        try {
            dictionary = getQF40DataDictionary();
            quickfix.fix40.Advertisement msg = new quickfix.fix40.Advertisement();
            TestUtils.populateQuickFIX40HeaderAll(msg);
            msg.setString(quickfix.field.AdvId.FIELD, "45");
            msg.setString(quickfix.field.AdvTransType.FIELD, "N");
            msg.setString(quickfix.field.AdvSide.FIELD, "B");
            msg.setDecimal(quickfix.field.Quantity.FIELD, new BigDecimal("200"));
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Symbol] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [AdvertismentMsg] message version [4.0].", ex.getMessage());
    }
}