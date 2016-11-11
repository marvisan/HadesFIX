/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg50SP2Test.java
 *
 * $Id: QuoteRequestMsg50SP2Test.java,v 1.3 2010-03-21 11:25:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.group.impl.v50sp2.QuoteRequestGroup50SP2TestData;
import net.hades.fix.message.impl.v50sp2.data.QuoteRequestMsg50SP2TestData;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for FIX 5.0SP2 QuoteRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 09/04/2009, 3:12:30 PM
 */
public class QuoteRequestMsg50SP2Test extends MsgTest {

    public QuoteRequestMsg50SP2Test() {
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
     * Test of encode method, of class QuoteRequestMsg for required fields only.
     * @throws Exception
     */
    @Test
    public void a1_testEncodeDecodeReq() throws Exception {
        System.out.println("-->testEncodeReq");
        QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
        msg.setQuoteReqID("X162773883");
        msg.setNoRelatedSym(new Integer(1));
        QuoteRequestGroup50SP2TestData.getInstance().populate1(msg.getQuoteRelatedSymbols()[0]);

        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        QuoteRequestMsg dmsg = (QuoteRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();

        assertEquals(msg.getQuoteReqID(), dmsg.getQuoteReqID());
        // QuoteRequestGroup check
        assertEquals(msg.getNoRelatedSym().intValue(), dmsg.getNoRelatedSym().intValue());
    }

    /**
     * Test of encode method, of class QuoteRequestMsg all fields.
     * @throws Exception
     */
    @Test
    public void a2_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        QuoteRequestMsg50SP2TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        QuoteRequestMsg dmsg = (QuoteRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        QuoteRequestMsg50SP2TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of setNoRelatedSym method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testSetNoRelatedSym() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        int i = 0;
        for (QuoteRequestGroup group : comp.getQuoteRelatedSymbols()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
    }

    /**
     * Test of addQuoteRelatedSymbolGroup method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testAddQuoteRelatedSymbolGroup() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(2));
        assertEquals(2, comp.getQuoteRelatedSymbols().length);
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        comp.addQuoteRelatedSymbolGroup();
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        comp.getQuoteRelatedSymbols()[2].getInstrument().setSymbol("SYM 2");
        int i = 0;
        for (QuoteRequestGroup group : comp.getQuoteRelatedSymbols()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
        assertEquals(3, comp.getNoRelatedSym().intValue());
    }

    /**
     * Test of deleteQuoteRelatedSymbolGroup method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testDeleteQuoteRelatedSymbolGroup() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        comp.deleteQuoteRelatedSymbolGroup(1);
        assertEquals(2, comp.getQuoteRelatedSymbols().length);
        assertEquals(2, comp.getNoRelatedSym().intValue());
        assertEquals("SYM 2", comp.getQuoteRelatedSymbols()[1].getInstrument().getSymbol());
    }

    /**
     * Test of clearQuoteRelatedSymbolGroups method, of class QuoteRequestMsg.
     * @throws Exception
     */
    @Test
    public void testClearQuoteRelatedSymbolGroups() throws Exception {
        QuoteRequestMsg comp = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        assertNull(comp.getQuoteRelatedSymbols());
        comp.setNoRelatedSym(new Integer(3));
        for (int i = 0; i < comp.getQuoteRelatedSymbols().length; i++) {
            QuoteRequestGroup group = comp.getQuoteRelatedSymbols()[i];
            group.getInstrument().setSymbol("SYM " + i);
        }
        assertEquals(3, comp.getQuoteRelatedSymbols().length);
        assertEquals(3, comp.getNoRelatedSym().intValue());
        int i = 0;
        for (QuoteRequestGroup group : comp.getQuoteRelatedSymbols()) {
            assertEquals("SYM " + i, group.getInstrument().getSymbol());
            i++;
        }
        comp.clearQuoteRelatedSymbolGroups();
        assertNull(comp.getQuoteRelatedSymbols());
        assertNull(comp.getNoRelatedSym());
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class QuoteRequestMsg with missing QuoteReqID data.
     */
    @Test
    public void testEncodeMissingQuoteReqID() {
        System.out.println("-->testEncodeMissingQuoteReqID");
        try {
            QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setNoRelatedSym(new Integer(2));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [QuoteReqID] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class QuoteRequestMsg with missing NoRelatedSym data.
     */
    @Test
    public void testEncodeMissingNoRelatedSym() {
        System.out.println("-->testEncodeMissingNoRelatedSym");
        try {
            QuoteRequestMsg msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setQuoteReqID("43423534534");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [NoRelatedSym] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        QuoteRequestMsg msg = null;
        try {
           msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getSymbol();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSymbolSfx();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSecurityID();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSecurityIDSource();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSecurityType();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getMaturityMonthYear();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getMaturityDay();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getPutOrCall();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getStrikePrice();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getOptAttribute();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSecurityExchange();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getIssuer();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSecurityDesc();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getPrevClosePx();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSide();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getOrderQty();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSettlDate();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getOrdType();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getSettlDate2();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getOrderQty2();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        QuoteRequestMsg msg = null;
        try {
            msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIXT_1_1, ApplVerID.FIX50SP2);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setSymbol("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSymbolSfx("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSecurityID("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSecurityIDSource("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSecurityType(SecurityType.Option.getValue());
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setMaturityMonthYear("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setMaturityDay(new Integer(3));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setPutOrCall(PutOrCall.Call);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setStrikePrice(new Double(1.1));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setOptAttribute(new Character('S'));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSecurityExchange("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setIssuer("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSecurityDesc("XXX");
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setPrevClosePx(new Double(1.1));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSide(Side.AsDefined);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setOrderQty(new Double(1.1));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSettlDate(new Date());
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setOrdType(OrdType.ForexLimit);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setSettlDate2(new Date());
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setOrderQty2(new Double(1.1));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [QuoteRequestMsg] message version [5.0SP2].", ex.getMessage());
    }
}
