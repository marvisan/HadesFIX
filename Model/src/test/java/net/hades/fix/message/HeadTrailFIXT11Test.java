/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeadTrailFIXT11Test.java
 *
 * $Id: HeadTrailFIXT11Test.java,v 1.7 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;

import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.BeginString;

/**
 * Test suite for TestRequestMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 00/09/2008, 15:14:31
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeadTrailFIXT11Test extends MsgTest {
    
    public HeadTrailFIXT11Test() {
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
     * Test of encode method, of a message class Header/Tailer for FIXT 1.1.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReqOnly() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_5_0);
        TestUtils.populateHeaderReq(msg);
        msg.setTestReqID("Test header/trailer FIXT 1.1");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIXT 1.1.
     * @throws Exception 
     */
    @Test
    public void a4_testEncodeAll() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_5_0SP2);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setTestReqID("Test header/trailer FIXT 1.1");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        TestRequestMsg result = (TestRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        result.decode();
        checkQuickFIXHeaderAll(result);
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIXT 1.1 with custom fields.
     * @throws Exception 
     */
    @Ignore
    public void a8_testEncodeCustomFields() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIXT_1_1);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.addCustomTag("8888", "Test custom tag");
        msg.setTestReqID("Test header/trailer 4.4");
        byte[] encodedMsg = msg.encode();
        String encoded = new String(encodedMsg, DEFAULT_CHARACTER_SET);
        System.out.println("encoded custom-->" + encoded);
        quickfix.Message qfMsg = new quickfix.Message(encoded);
        String customTag = qfMsg.getString(8888);
        assertEquals("Test custom tag", customTag);
    }
    
    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class Header 4.3 with missing required header data.
     */
    @Test
    public void x1_testEncodeMissingSomeReq() {
        try {
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_4);
            msg.setTestReqID("Test header/trailer 4.4");
            populateHeaderReqMissing(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [TargetCompID] [MsgSeqNum] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class Header 4.3 with missing required header data.
     */
    @Test
    public void x2_testEncodeMissingManyReq() {
        try {
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_4);
            msg.setTestReqID("Test header/trailer 4.4");
            populateHeaderAllReqMissing(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [SenderCompID] [TargetCompID] [MsgSeqNum] [SendingTime] is missing.", 
                    ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class Header 4.3 with unsupported header tag.
     */
    @Test
    public void x4_testGetUnsupportedHeaderTag() {
        try {
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_4);
            msg.getHeader().getOnBehalfOfSendingTime();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "This tag is not supported in release [" + BeginString.FIX_4_4.getValue() + "].",
                    ex.getMessage());
        }
    }
    
    // UTILITY METHODS
    
    private static void populateHeaderReqMissing(FIXMsg msg) {
        msg.getHeader().setSenderCompID("Marvisan");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
    }
    
    private static void populateHeaderAllReqMissing(FIXMsg msg) {
    }
    
    private void checkQuickFIXHeaderReq(TestRequestMsg msg) {
        assertEquals(BeginString.FIX_4_4, msg.getHeader().getBeginString());
        assertEquals(MsgType.TestRequest, msg.getHeader().getMsgType());
        assertEquals(67, msg.getHeader().getBodyLength());
        assertEquals("Teleton", msg.getHeader().getSenderCompID());
        assertEquals("BI", msg.getHeader().getTargetCompID());
        assertEquals(2, msg.getHeader().getMsgSeqNum().intValue());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(msg.getHeader().getSendingTime());
        assertEquals(cal.getTime(), cal1.getTime());
    }
    
    private void checkQuickFIXHeaderAll(TestRequestMsg msg) throws UnsupportedEncodingException {
        assertEquals(BeginString.FIXT_1_1, msg.getHeader().getBeginString());
        assertEquals(MsgType.TestRequest.getValue(), msg.getHeader().getMsgType());
        assertEquals("7", msg.getHeader().getApplVerID().getValue());
        assertEquals("Custom version 2", msg.getHeader().getCstmApplVerID());
        assertEquals("Marvisan", msg.getHeader().getSenderCompID());
        assertEquals("IB", msg.getHeader().getTargetCompID());
        assertEquals(10, msg.getHeader().getMsgSeqNum().intValue());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(msg.getHeader().getSendingTime());
        assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
        assertEquals("Company on behalf name", msg.getHeader().getOnBehalfOfCompID());
        assertEquals("Deliver to this company", msg.getHeader().getDeliverToCompID());
        assertEquals("Sender sub identifier", msg.getHeader().getSenderSubID());
        assertEquals("Sender location identifier", msg.getHeader().getSenderLocationID());
        assertEquals("Target sub identifier", msg.getHeader().getTargetSubID());
        assertEquals("Target location identifier", msg.getHeader().getTargetLocationID());
        assertEquals("On behalf of sub identifier", msg.getHeader().getOnBehalfOfSubID());
        assertEquals("On behalf of location identifier", msg.getHeader().getOnBehalfOfLocationID());
        assertEquals("Deliver to this company", msg.getHeader().getDeliverToSubID());
        assertEquals("Deliver to this location", msg.getHeader().getDeliverToLocationID());
        assertTrue(msg.getHeader().getPossDupFlag().booleanValue());
        assertFalse(msg.getHeader().getPossResend().booleanValue());
        assertEquals("UTF-8", msg.getHeader().getMessageEncoding().name());
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc>Test</doc>";
        assertEquals(xmlData, new String(msg.getHeader().getXmlData(), DEFAULT_CHARACTER_SET));
        assertEquals(xmlData.getBytes(DEFAULT_CHARACTER_SET).length, msg.getHeader().getXmlDataLen().intValue());
        assertEquals(9, msg.getHeader().getLastMsgSeqNumProcessed().intValue());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        cal1 = Calendar.getInstance();
        cal1.setTime(msg.getHeader().getOrigSendingTime());
        assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
        assertNotNull(msg.getHeader().getHopsGroups());
        assertEquals(2, msg.getHeader().getNoHops().intValue());
        assertEquals(2, msg.getHeader().getHopsGroups().length);
        HopsGroup grp1 = msg.getHeader().getHopsGroups()[0];
        assertEquals("Hop No 1", grp1.getHopCompID());
        assertUTCTimestampEquals(cal.getTime(), grp1.getHopSendingTime(), false);
        assertEquals(new Integer(3), grp1.getHopRefID());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        HopsGroup grp2 = msg.getHeader().getHopsGroups()[1];
        assertEquals("Hop No 2", grp2.getHopCompID());
        assertUTCTimestampEquals(cal.getTime(), grp2.getHopSendingTime(), false);
        assertEquals(new Integer(4), grp2.getHopRefID());
    }
}