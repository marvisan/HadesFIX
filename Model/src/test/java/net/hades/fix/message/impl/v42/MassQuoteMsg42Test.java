/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsg42Test.java
 *
 * $Id: MassQuoteMsg42Test.java,v 1.4 2010-03-21 11:25:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.MassQuoteMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v42.data.MassQuoteMsg42TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.2 MassQuoteMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class MassQuoteMsg42Test extends MsgTest  {

    private DataDictionary dictionary;

    public MassQuoteMsg42Test() {
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
     * Test of encode method, of class MassQuoteMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        dictionary = getQF42DataDictionary();
        MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setQuoteID("X162773883");
        msg.setNoQuoteSets(new Integer(1));
        msg.getQuoteSetGroups()[0].setNoQuoteEntries(new Integer(1));
        msg.getQuoteSetGroups()[0].getQuoteEntryGroups()[0].setQuoteEntryID("TTT73849444");
        msg.getQuoteSetGroups()[0].setQuoteSetID("UUU94773666");
        msg.getQuoteSetGroups()[0].setUnderlyingSymbol("IBM");
        msg.getQuoteSetGroups()[0].setTotNoQuoteEntries(new Integer(3));

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(msg.getQuoteID(), qfMsg.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(msg.getNoQuoteSets().intValue(), qfMsg.getInt(quickfix.field.NoQuoteSets.FIELD));
    }

    /**
     * Test of encode method, of class MassQuoteMsg all fields.
     * @throws Exception
     */
//    @Test
    public void a2_testEncodeAll() throws Exception {
        System.out.println("-->testEncodeAll");
        dictionary = getQF42DataDictionary();
        MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
        MassQuoteMsg42TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        quickfix.fix42.MassQuote qfMsg = new quickfix.fix42.MassQuote();
        qfMsg.fromString(encoded, dictionary, true);
        MassQuoteMsg42TestData.getInstance().check(msg, qfMsg);
    }

    /**
     * Test of decode method, of class MassQuoteMsg only required.
     * @throws Exception
     */
    @Test
    public void b1_testDecodeReq() throws Exception {
        System.out.println("-->testDecodeReq");
        dictionary = getQF42DataDictionary();
        quickfix.fix42.MassQuote msg = new quickfix.fix42.MassQuote();
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.QuoteID.FIELD, "X162773883");
        msg.setInt(quickfix.field.NoQuoteSets.FIELD, 1);
        quickfix.fix42.MassQuote.NoQuoteSets grp = new quickfix.fix42.MassQuote.NoQuoteSets();
        grp.setString(quickfix.field.QuoteSetID.FIELD, "FFF7484763");
        grp.setString(quickfix.field.UnderlyingSymbol.FIELD, "ORCL");
        grp.setInt(quickfix.field.TotNoQuoteEntries.FIELD, 5);

        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        MassQuoteMsg dmsg = (MassQuoteMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getString(quickfix.field.QuoteID.FIELD), dmsg.getQuoteID());
        assertEquals(msg.getInt(quickfix.field.NoQuoteSets.FIELD), dmsg.getNoQuoteSets().intValue());
    }

    /**
     * Test of decode method, of class MassQuoteMsg for all fields.
     * @throws Exception
     */
//    @Test
    public void b2_testDecodeAll() throws Exception {
        System.out.println("-->testDecodeAll");
        dictionary = getQF42DataDictionary();
        quickfix.fix42.MassQuote msg = new quickfix.fix42.MassQuote();
        MassQuoteMsg42TestData.getInstance().populate(msg);
        String strMsg = msg.toString();
        System.out.println("qfix msg-->" + strMsg);

        MassQuoteMsg dmsg = (MassQuoteMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        MassQuoteMsg42TestData.getInstance().check(msg, dmsg);
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
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
            MassQuoteMsg42TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            MassQuoteMsg dmsg = (MassQuoteMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            MassQuoteMsg42TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter methods with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        MassQuoteMsg msg = null;
        try {
            msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter methods with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        MassQuoteMsg msg = null;
        try {
            msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
        } catch (Exception ex) {
            fail("Error building message");
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
     * Test of encode method, of class MassQuoteMsg with missing QuoteID data.
     */
    @Test
    public void testEncodeMissingQuoteID() {
        System.out.println("-->testEncodeMissingQuoteID");
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setNoQuoteSets(1);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MassQuoteMsg with missing NoQuoteSets data.
     */
    @Test
    public void testEncodeMissingNoQuoteSets() {
        System.out.println("-->testEncodeMissingSymbol");
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setQuoteID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoQuoteSets] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class MassQuoteMsg with missing all required data.
     */
    @Test
    public void testEncodeMissingAllReq() {
        System.out.println("-->testEncodeMissingAllReq");
        try {
            MassQuoteMsg msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [NoQuoteSets] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of decode method, of class NewsMsg with missing all required data.
     */
    @Test
    public void testDecodeMissingReq() {
        System.out.println("-->testDecodeMissingReq");
        try {
            dictionary = getQF42DataDictionary();
            quickfix.fix42.MassQuote msg = new quickfix.fix42.MassQuote();
            TestUtils.populateQuickFIX42HeaderAll(msg);
            String strMsg = msg.toString();
            System.out.println("qfix msg-->" + strMsg);
            FIXMsg dmsg = FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteID] [NoQuoteSets] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [MassQuoteMsg] message version [4.2].", ex.getMessage());
    }
}
