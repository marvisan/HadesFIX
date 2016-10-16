/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg50SP2Test.java
 *
 * $Id: AdvertisementMsg50SP2Test.java,v 1.4 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

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
import net.hades.fix.message.impl.v50sp2.data.AdvertisementMsg50SP2TestData;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for AdvertisementMsg 5.0 SP2 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 07/02/2009, 9:07:29 PM
 */
public class AdvertisementMsg50SP2Test extends MsgTest {

    public AdvertisementMsg50SP2Test() {
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
     * Test of encode method, of class AdvertisementMsg.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setAdvID("45");
        msg.setAdvTransType(AdvTransType.New);
        msg.getInstrument().setSymbol("MOT");
        msg.setAdvSide(AdvSide.Buy);
        msg.setQuantity(new Double("200"));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getAdvID(), dmsg.getAdvID());
        assertEquals(msg.getAdvTransType(), dmsg.getAdvTransType());
        assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
        assertEquals(msg.getAdvSide(), dmsg.getAdvSide());
        assertEquals(msg.getQuantity().doubleValue(), dmsg.getQuantity().doubleValue(), 0.1);
    }

    /**
     * Test of encode method, of class AdvertisementMsg.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        AdvertisementMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        AdvertisementMsg dmsg = (AdvertisementMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        AdvertisementMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

   /**
     * Test of encode getter method, of class AdvertismentMsgwith unsupported tag.
     */
    @Test
    public void testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedAdvertismentMsgTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class AdvertisementMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedgTag() {
        System.out.println("-->testSetUnsupportedAdvertismentMsgTag");
        AdvertisementMsg msg = null;
        try {
            msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
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
            AdvertisementMsg msg = (AdvertisementMsg) FIXMsgBuilder.build(MsgType.Advertisement.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [AdvID] [AdvTransType] [Instrument] [AdvSide] [Quantity] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [AdvertismentMsg] message version [5.0SP2].", ex.getMessage());
    }
}
