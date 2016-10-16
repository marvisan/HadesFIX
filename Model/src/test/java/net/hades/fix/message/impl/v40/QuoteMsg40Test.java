/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsg40Test.java
 *
 * $Id: QuoteMsg40Test.java,v 1.4 2010-03-21 10:18:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v40.data.QuoteMsg40TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;


/**
 * Test suite for FIX 4.0 QuoteMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteMsg40Test extends MsgTest  {

    private DataDictionary dictionary;

    public QuoteMsg40Test() {
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
     * Test of encode method, of class QuoteMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF40DataDictionary();
        QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setQuoteID("X162773883");
        msg.setSymbol("SUN");
        msg.setBidPx(new Double(123.44));

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteID(), qfMsg.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(msg.getSymbol(), qfMsg.getString(quickfix.field.Symbol.FIELD));
        assertEquals(msg.getBidPx().doubleValue(), qfMsg.getDouble(quickfix.field.BidPx.FIELD), 0.001);
    }

    /**
     * Test of encode method, of class QuoteMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF40DataDictionary();
        QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
        QuoteMsg40TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix40.Quote qfMsg = new quickfix.fix40.Quote();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteMsg40TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF40DataDictionary();
        quickfix.fix40.Quote msg = new quickfix.fix40.Quote();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setString(quickfix.field.Symbol.FIELD, "SUN");
        msg.setDouble(quickfix.field.BidPx.FIELD, 234.55);

        String strMsg = msg.toString();

        System.out.println("qfix msg-->" + strMsg);
        QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.QuoteID.FIELD), dmsg.getQuoteID());
        assertEquals(msg.getString(quickfix.field.Symbol.FIELD), dmsg.getSymbol());
        assertEquals(msg.getDouble(quickfix.field.BidPx.FIELD), dmsg.getBidPx().doubleValue(), 0.001);
    }

    /**
     * Test of decode method, of class QuoteMsg for FIX 4.0 all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF40DataDictionary();
        quickfix.fix40.Quote msg = new quickfix.fix40.Quote();
        QuoteMsg40TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteMsg40TestData.getInstance().check(msg, dmsg);
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
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
            QuoteMsg40TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteMsg dmsg = (QuoteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteMsg40TestData.getInstance().check(msg, dmsg);
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
        QuoteMsg msg = null;
        try {
            msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
        } catch (Exception ex) {
            fail("Error building message");
        }
        try {
            msg.getNoQuoteQualifiers();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getQuoteQualifierGroups();
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
        try {
            msg.getInstrument();
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
            msg.getStipulations();
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
            msg.getLegQuoteSymbolGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSpreadOrBenchmarkCurveData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getYieldData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class QuoteMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteMsg msg = null;
        try {
            msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setNoQuoteQualifiers(new Integer(2));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addQuoteQualifierGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteQuoteQualifierGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearQuoteQualifierGroups();
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
            msg.setFinancingDetails();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoUnderlyings(new Integer(2));
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
            msg.setOrderQtyData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setStipulations();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoLegs(new Integer(2));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addLegQuoteSymbolGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteLegQuoteSymbolGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearLegQuoteSymbolGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSpreadOrBenchmarkCurveData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setYieldData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteMsg with missing QuoteID data.
     */
    @Test
    public void testEncodeMissingQuoteID() {
        System.out.println("-->testEncodeMissingQuoteID");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setSymbol("MOT");
            msg.setBidPx(new Double(123.44));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.setBidPx(new Double(123.44));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Symbol] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteMsg with missing BidPx data.
     */
    @Test
    public void testEncodeMissingBidPx() {
        System.out.println("-->testEncodeMissingBidPx");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.setSymbol("MOT");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [BidPx] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            QuoteMsg msg = (QuoteMsg) FIXMsgBuilder.build(MsgType.Quote.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Symbol] [BidPx] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF40DataDictionary();
            quickfix.fix40.Quote msg = new quickfix.fix40.Quote();
            TestUtils.populateQuickFIX40HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Symbol] [BidPx] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteMsg] message version [4.0].", ex.getMessage());
    }}
