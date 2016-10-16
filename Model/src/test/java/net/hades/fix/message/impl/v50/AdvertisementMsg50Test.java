/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg50Test.java
 *
 * $Id: AdvertisementMsg50Test.java,v 1.5 2011-01-15 02:10:11 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.AdvertisementMsg50TestData;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for AdvertisementMsg 5.0 class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/02/2009, 11:26:59 AM
 */
public class AdvertisementMsg50Test extends MsgTest {

    public AdvertisementMsg50Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        setSessionApplVerID(ApplVerID.FIX50);
        TestUtils.enableValidation();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class AdvertisementMsg.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        TestUtils.populate50HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.New);
        msg.getInstrument().setSymbol("MOT");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix50.Advertisement qfMsg = new quickfix.fix50.Advertisement();
        qfMsg.fromString(encoded, getQFSessDataDictionary(), getQF50DataDictionary(), true);
        assertEquals(msg.getAdvID(), qfMsg.getString(quickfix.field.AdvId.FIELD));
        assertEquals(msg.getAdvTransType().getValue(), qfMsg.getString(quickfix.field.AdvTransType.FIELD));
        assertEquals(msg.getInstrument().getSymbol(), qfMsg.getString(quickfix.field.Symbol.FIELD));
        assertEquals(msg.getAdvSide().getValue(), qfMsg.getString(quickfix.field.AdvSide.FIELD));
        assertEquals(msg.getQuantity().doubleValue(), qfMsg.getDouble(quickfix.field.Quantity.FIELD), 0.1);
    }

    /**
     * Test of encode method, of class AdvertisementMsg.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        AdvertisementMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix50.Advertisement qfMsg = new quickfix.fix50.Advertisement();
        qfMsg.fromString(encoded, getQFSessDataDictionary(), getQF50DataDictionary(), true);
        AdvertisementMsg50TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class AdvertisementMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        quickfix.fix50.Advertisement msg = new quickfix.fix50.Advertisement();
        TestUtils.populateQuickFIX50HeaderAll(msg);
        msg.setString(quickfix.field.AdvId.FIELD, "45");
        msg.setString(quickfix.field.AdvTransType.FIELD, "N");
        msg.setString(quickfix.field.Symbol.FIELD, "MOT");
        msg.setString(quickfix.field.AdvSide.FIELD, "B");
        msg.setDouble(quickfix.field.Quantity.FIELD, new Double("200"));
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);
        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals("45", dmsg.getAdvID());
        assertEquals("N", dmsg.getAdvTransType().getValue());
        assertEquals("MOT", dmsg.getInstrument().getSymbol());
        assertEquals("B", dmsg.getAdvSide().getValue());
        assertEquals(200.0, dmsg.getQuantity().doubleValue(), 0.1);
    }

    /**
     * Test of decode method, of class AdvertisementMsg all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        quickfix.fix50.Advertisement msg = new quickfix.fix50.Advertisement();
        AdvertisementMsg50TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        AdvertisementMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of decode method, of class AdvertisementMsg all fields.
     * @throws Exception
     */
    @Test
    public void b2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        AdvertisementMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        AdvertisementMsg50TestData.getInstance().check(msg, dmsg);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setAdvTransType(AdvTransType.New);
            msg.getInstrument().setSymbol("MOT");
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setAdvID("45");
            msg.getInstrument().setSymbol("MOT");
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setAdvID("45");
            msg.setAdvTransType(AdvTransType.New);
            msg.setAdvSide(AdvSide.Buy);
            msg.setQuantity(new Double("200"));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class AdvertisementMsg with missing AdvSide data.
     */
    @Test
    public void x4_testEncodeMissingAdvSide() {
        System.out.println("-->testEncodeMissingAdvSide");
        try {
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setAdvID("45");
            msg.setAdvTransType(AdvTransType.New);
            msg.getInstrument().setSymbol("MOT");
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setAdvID("45");
            msg.setAdvTransType(AdvTransType.New);
            msg.getInstrument().setSymbol("MOT");
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [AdvID] [AdvTransType] [Instrument] [AdvSide] [Quantity] is missing.", ex.getMessage());
        }
    }

   /**
     * Test of encode getter method, of class AdvertisementMsg 4.4 with unsupported tag.
     */
    @Test
    public void x100_testGetUnsupportedAdvertismentMsgTag() {
        System.out.println("-->testGetUnsupportedAdvertismentMsgTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class AdvertisementMsg 4.4 with unsupported tag.
     */
    @Test
    public void x101_testSetUnsupportedAdvertismentMsgTag() {
        System.out.println("-->testSetUnsupportedAdvertismentMsgTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [AdvertismentMsg] message version [5.0].", ex.getMessage());
    }
}
