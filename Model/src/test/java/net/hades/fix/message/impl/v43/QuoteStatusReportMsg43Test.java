/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteStatusReportMsg43Test.java
 *
 * $Id: QuoteStatusReportMsg43Test.java,v 1.5 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

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
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v43.data.QuoteStatusReportMsg43TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.3 QuoteStatusReportMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class QuoteStatusReportMsg43Test extends MsgTest  {

    private DataDictionary dictionary;

    public QuoteStatusReportMsg43Test() {
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
        dictionary = getQF43DataDictionary();
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setQuoteID("X162773883");
        msg.getInstrument().setSymbol("SUN");

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteID(), qfMsg.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(msg.getInstrument().getSymbol(), qfMsg.getString(quickfix.field.Symbol.FIELD));
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF43DataDictionary();
        QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
        QuoteStatusReportMsg43TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix43.QuoteStatusReport qfMsg = new quickfix.fix43.QuoteStatusReport();
        qfMsg.fromString(encoded, dictionary, true);
        QuoteStatusReportMsg43TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class QuoteStatusReportMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.QuoteStatusReport msg = new quickfix.fix43.QuoteStatusReport();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setString(quickfix.field.Symbol.FIELD, "SUN");
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.QuoteID.FIELD), dmsg.getQuoteID());
        assertEquals(msg.getString(quickfix.field.Symbol.FIELD), dmsg.getInstrument().getSymbol());
    }

    /**
     * Test of decode method, of class QuoteStatusReportMsg for all fields.
     * @throws Exception
     */
    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF43DataDictionary();
        quickfix.fix43.QuoteStatusReport msg = new quickfix.fix43.QuoteStatusReport();
        QuoteStatusReportMsg43TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteStatusReportMsg43TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void a3_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
            QuoteStatusReportMsg43TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            QuoteStatusReportMsg dmsg = (QuoteStatusReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            QuoteStatusReportMsg43TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class QuoteStatusReportRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteStatusReportMsg msg = null;
        try {
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getTargetParties();
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
            msg.getOrderQtyData();
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
            msg.getLegQuoteStatusGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
        QuoteStatusReportMsg msg = null;
        try {
            msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
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
        try {
            msg.setFinancingDetails();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearFinancingDetails();
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
            msg.setOrderQtyData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearOrderQtyData();
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
            msg.clearStipulations();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoLegs(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addLegQuoteStatusGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteLegQuoteStatusGroup(1);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearLegQuoteStatusGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
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
            msg.setSpreadOrBenchmarkCurveData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearSpreadOrBenchmarkCurveData();
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
        try {
            msg.clearYieldData();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteStatusReportMsg with missing QuoteID data.
     */
    @Test
    public void testEncodeMissingQuoteID() {
        System.out.println("-->testEncodeMissingQuoteID");
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.getInstrument().setSymbol("MOT");
            msg.setBidPx(new Double(123.44));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg with missing Symbol data.
     */
    @Test
    public void testEncodeMissingSymbol() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteStatusReportMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            QuoteStatusReportMsg msg = (QuoteStatusReportMsg) FIXMsgBuilder.build(MsgType.QuoteStatusReport.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Instrument] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF43DataDictionary();
            quickfix.fix43.QuoteStatusReport msg = new quickfix.fix43.QuoteStatusReport();
            TestUtils.populateQuickFIX43HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [Instrument] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteStatusReportMsg] message version [4.3].", ex.getMessage());
    }}
