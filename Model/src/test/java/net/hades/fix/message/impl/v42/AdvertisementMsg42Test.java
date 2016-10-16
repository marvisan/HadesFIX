/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertismentMsgTest.java
 *
 * $Id: AdvertisementMsg42Test.java,v 1.5 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.impl.v42.data.AdvertisementMsg42TestData;
import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for AdvertisementMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 20/10/2008, 20:57:03
 */
public class AdvertisementMsg42Test extends MsgTest {

    private DataDictionary dictionary;
    
    public AdvertisementMsg42Test() {
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
     * Test of encode method, of class AdvertisementMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF42DataDictionary();
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.New);
        msg.setSymbol("MOT");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(45, qfMsg.getInt(quickfix.field.AdvId.FIELD));
        assertEquals(AdvTransType.New.getValue(), qfMsg.getString(quickfix.field.AdvTransType.FIELD));
        assertEquals("MOT", qfMsg.getString(quickfix.field.Symbol.FIELD));
        assertEquals(AdvSide.Buy.getValue(), qfMsg.getString(quickfix.field.AdvSide.FIELD));
        assertEquals(200.0, qfMsg.getDecimal(quickfix.field.Quantity.FIELD).doubleValue(), 0.1);
    }
    
    /**
     * Test of encode method, of class AdvertisementMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF42DataDictionary();
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
        AdvertisementMsg42TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix42.Advertisement qfMsg = new quickfix.fix42.Advertisement();
        qfMsg.fromString(encoded, dictionary, true);
        AdvertisementMsg42TestData.getInstance().check(msg, qfMsg);
    }
    
    /**
     * Test of decode method, of class AdvertisementMsg for FIX 4.2 only required.
     * @throws Exception 
     */
    @Test
    public void b10_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF42DataDictionary();
        quickfix.fix42.Advertisement msg = new quickfix.fix42.Advertisement();
        TestUtils.populateQuickFIX42HeaderAll(msg);
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
     * Test of decode method, of class AdvertisementMsg for FIX 4.2 all fields.
     * @throws Exception 
     */
    @Test
    public void b11_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF42DataDictionary();
        quickfix.fix42.Advertisement msg = new quickfix.fix42.Advertisement();
        AdvertisementMsg42TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        AdvertisementMsg42TestData.getInstance().check(msg, dmsg);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
            AdvertisementMsg42TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            AdvertisementMsg42TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class AdvertisementMsg 4.2 with unsupported tag.
     */
    @Test
    public void testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
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
     * Test of encode setter method, of class AdvertisementMsg 4.2 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedTag() {
        System.out.println("-->testSetUnsupportedAdvertismentMsgTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
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
        
    @Test
    public void y10_testDecodeMissingReq() throws Exception {
        System.out.println("-->testDecodeMissingRequired");
        try {
            dictionary = getQF42DataDictionary();
            quickfix.fix42.Advertisement msg = new quickfix.fix42.Advertisement();
            TestUtils.populateQuickFIX42HeaderAll(msg);
            msg.setString(quickfix.field.AdvId.FIELD, "45");
            msg.setString(quickfix.field.AdvTransType.FIELD, "N");
            msg.setString(quickfix.field.Symbol.FIELD, "MOT");
            msg.setString(quickfix.field.AdvSide.FIELD, "B");
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Quantity] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg with missing AdvID data.
     */
    @Test
    public void x1_testEncodeMissingAdvID() {
        System.out.println("-->testEncodeMissingAdvID");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
            TestUtils.populate41HeaderAll(msg);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
            TestUtils.populate41HeaderAll(msg);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
            TestUtils.populate41HeaderAll(msg);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
            TestUtils.populate41HeaderAll(msg);
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
        System.out.println("-->testEncodeMissingAll");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIX_4_2);
            TestUtils.populate41HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [AdvID] [AdvTransType] [Symbol] [AdvSide] [Quantity] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [AdvertismentMsg] message version [4.2].", ex.getMessage());
    }

}