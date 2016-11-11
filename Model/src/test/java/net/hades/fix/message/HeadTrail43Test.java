/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeadTrail43Test.java
 *
 * $Id: HeadTrail43Test.java,v 1.6 2011-04-04 09:37:10 vrotaru Exp $
 */
package net.hades.fix.message;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.FieldNotFound;
import quickfix.field.DeliverToCompID;
import quickfix.field.DeliverToLocationID;
import quickfix.field.DeliverToSubID;
import quickfix.field.HopCompID;
import quickfix.field.HopRefID;
import quickfix.field.HopSendingTime;
import quickfix.field.LastMsgSeqNumProcessed;
import quickfix.field.MessageEncoding;
import quickfix.field.MsgSeqNum;
import quickfix.field.NoHops;
import quickfix.field.OnBehalfOfCompID;
import quickfix.field.OnBehalfOfLocationID;
import quickfix.field.OnBehalfOfSendingTime;
import quickfix.field.OnBehalfOfSubID;
import quickfix.field.OrigSendingTime;
import quickfix.field.PossDupFlag;
import quickfix.field.PossResend;
import quickfix.field.SenderCompID;
import quickfix.field.SenderLocationID;
import quickfix.field.SenderSubID;
import quickfix.field.SendingTime;
import quickfix.field.Signature;
import quickfix.field.SignatureLength;
import quickfix.field.TargetCompID;
import quickfix.field.TargetLocationID;
import quickfix.field.TargetSubID;
import quickfix.field.TestReqID;
import quickfix.field.XmlData;
import quickfix.field.XmlDataLen;

import net.hades.fix.TestUtils;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.BeginString;

/**
 * Test suite for TestRequestMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 00/09/2008, 15:14:31
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeadTrail43Test extends MsgTest {
    
    private DataDictionary dictionary;

    public HeadTrail43Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        try {
            dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        } catch (ConfigError ex) {
            System.out.println("ERROR : " + ex.toString());
        }
        TestUtils.enableValidation();
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReqOnly() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
        TestUtils.populateHeaderReq(msg);
        msg.setTestReqID("Test header/trailer 4.3");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        checkHeaderReq((quickfix.fix43.Message.Header) qfMsg.getHeader());
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a4_testEncodeAll() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setTestReqID("Test header/trailer 4.3");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        checkHeaderAll((quickfix.fix43.Message.Header) qfMsg.getHeader());
        checkTrailer(qfMsg.getTrailer());
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIX 4.3 with secure data.
     */
    @Test
    public void a6_testEncodeDecodeSecure() {
        try {
            setSecuredDataDES();
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setTestReqID("Test header/trailer 4.3");
            byte[] encodedMsg = msg.encode();
            String encoded = new String(encodedMsg, DEFAULT_CHARACTER_SET);
            System.out.println("encoded secured-->" + encoded);
            TestRequestMsg decoded = (TestRequestMsg) FIXMsgBuilder.build(encodedMsg);
//            quickfix.fix43.TestRequest qfMsg = (quickfix.fix43.TestRequest) new quickfix.Message(encoded);
        } catch (Exception ex) {
            fail(ex.toString());
        } finally {
            unsetSecuredData();
        }
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIX 4.3 with custom fields.
     * @throws Exception 
     */
    @Ignore
    public void a8_testEncodeCustomFields() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.addCustomTag("8888", "Test custom tag");
        msg.setTestReqID("Test header/trailer 4.3");
        byte[] encodedMsg = msg.encode();
        String encoded = new String(encodedMsg, DEFAULT_CHARACTER_SET);
        System.out.println("encoded custom-->" + encoded);
        quickfix.Message qfMsg = new quickfix.Message(encoded);
        String customTag = qfMsg.getString(8888);
        assertEquals("Test custom tag", customTag);
    }
    
    /**
     * Test of decode method, of a message class Header/Tailer for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a10_testDecodeReqOnly() throws Exception {
        String strMsg = buildTestRequestReq().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        checkQuickFIXHeaderReq(msg);
    }
    
    /**
     * Test of decode method, of a message class Header/Tailer for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a12_testDecodeAll() throws Exception {
        System.out.println("testDecodeAll");
        String strMsg = buildTestRequestAll().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        checkQuickFIXHeaderAll(msg);
    }
    
    /**
     * Test of decode method, of a message class Header/Tailer for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a14_testDecodeCustom() throws Exception {
        quickfix.fix43.TestRequest qfMsg =  buildTestRequestAll();
        qfMsg.setString(9999, "Custom tag value");
        String strMsg = qfMsg.toString();
        System.out.println("msg custom-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        checkQuickFIXHeaderAll(msg);
        assertEquals("Custom tag value", msg.getCustomTag("9999"));
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class Header 4.3 with missing required header data.
     */
    @Test
    public void x1_testEncodeMissingSomeReq() {
        try {
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
            msg.setTestReqID("Test header/trailer 4.3");
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
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
            msg.setTestReqID("Test header/trailer 4.3");
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
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
            msg.getHeader().getApplVerID();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("This tag is not supported in release [" + BeginString.FIX_4_3.getValue() + "].",
                    ex.getMessage());
        }
    }
    
    // UTILITY METHODS

    private quickfix.fix43.TestRequest buildTestRequestReq() {
        quickfix.fix43.TestRequest msg = new quickfix.fix43.TestRequest();
        TestUtils.populateQuickFIX43HeaderReq(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.3"));
        
        return msg;
    }
    
    private quickfix.fix43.TestRequest buildTestRequestAll() {
        quickfix.fix43.TestRequest msg = new quickfix.fix43.TestRequest();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.3"));
        
        return msg;
    }
    
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
        assertEquals(BeginString.FIX_4_3, msg.getHeader().getBeginString());
        assertEquals(MsgType.TestRequest.getValue(), msg.getHeader().getMsgType());
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
        assertEquals(BeginString.FIX_4_3, msg.getHeader().getBeginString());
        assertEquals(MsgType.TestRequest.getValue(), msg.getHeader().getMsgType());
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
        assertEquals("Company on behalf name", msg.getHeader().getOnBehalfOfCompID());
        assertEquals("Deliver to this company", msg.getHeader().getDeliverToCompID());
        assertEquals("Sender sub identifier", msg.getHeader().getSenderSubID());
        assertEquals("Sender location identifier", msg.getHeader().getSenderLocationID());
        assertEquals("Target sub identifier", msg.getHeader().getTargetSubID());
        assertEquals("Target location identifier", msg.getHeader().getTargetLocationID());
        assertEquals("On behalf of sub identifier", msg.getHeader().getOnBehalfOfSubID());
        assertEquals("On behalf of location identifier", msg.getHeader().getOnBehalfOfLocationID());
        assertEquals("Deliver to this company", msg.getHeader().getDeliverToSubID());
        assertEquals("Deliver to this company location", msg.getHeader().getDeliverToLocationID());
        assertTrue(msg.getHeader().getPossDupFlag().booleanValue());
        assertFalse(msg.getHeader().getPossResend().booleanValue());
        assertEquals("UTF-8", msg.getHeader().getMessageEncoding().name());
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>";
        assertEquals(xmlData, new String(msg.getHeader().getXmlData(), DEFAULT_CHARACTER_SET));
        assertEquals(xmlData.getBytes(DEFAULT_CHARACTER_SET).length, msg.getHeader().getXmlDataLen().intValue());
        assertEquals(1, msg.getHeader().getLastMsgSeqNumProcessed().intValue());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        cal1 = Calendar.getInstance();
        cal1.setTime(msg.getHeader().getOrigSendingTime());
        assertEquals(cal.getTime(), cal1.getTime());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        cal1.setTime(msg.getHeader().getOnBehalfOfSendingTime());
        assertEquals(cal.getTime(), cal1.getTime());
        assertNotNull(msg.getHeader().getNoHops());
        assertEquals(2, msg.getHeader().getNoHops().intValue());
        assertEquals(2, msg.getHeader().getHopsGroups().length);
        HopsGroup grp1 = msg.getHeader().getHopsGroups()[0];
        assertEquals("Hop Comp ID 1", grp1.getHopCompID());
        assertEquals(cal.getTime(), grp1.getHopSendingTime());
        assertEquals(new Integer(6), grp1.getHopRefID());
        cal.add(Calendar.HOUR_OF_DAY, -1);
        HopsGroup grp2 = msg.getHeader().getHopsGroups()[1];
        assertEquals("Hop Comp ID 2", grp2.getHopCompID());
        assertEquals(cal.getTime(), grp2.getHopSendingTime());
        assertEquals(new Integer(8), grp2.getHopRefID());
    }
    
    private void checkHeaderReq(quickfix.Message.Header header) {
        try {
            assertEquals("Marvisan", header.getString(SenderCompID.FIELD));
            assertEquals("IB", header.getString(TargetCompID.FIELD));
            assertEquals(1, header.getInt(MsgSeqNum.FIELD));
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal.set(Calendar.DAY_OF_MONTH, 23);
            cal.set(Calendar.MONTH, 6);
            cal.set(Calendar.YEAR, 2008);
            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 45);
            cal.set(Calendar.SECOND, 15);
            cal.set(Calendar.MILLISECOND, 0);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal1.setTimeInMillis(header.getUtcTimeStamp(SendingTime.FIELD).getTime());
            assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
        } catch (FieldNotFound ex) {
            fail(ex.toString());
        }
    }
    
    private void checkTrailer(quickfix.Message.Trailer trailer) {
        try {
            assertEquals(8, trailer.getInt(SignatureLength.FIELD));
            String sig = trailer.getString(Signature.FIELD);
            byte[] expected = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
                (byte) 234, (byte) 236, (byte) 238, (byte) 240};
            assertEquals(new String(expected, DEFAULT_CHARACTER_SET), sig);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
    
    private void checkHeaderAll(quickfix.fix43.Message.Header header) {
        try {
            assertEquals("Marvisan", header.getString(SenderCompID.FIELD));
            assertEquals("IB", header.getString(TargetCompID.FIELD));
            assertEquals(10, header.getInt(MsgSeqNum.FIELD));
            assertEquals("Company on behalf name", header.getString(OnBehalfOfCompID.FIELD));
            assertEquals("Deliver to this company", header.getString(DeliverToCompID.FIELD));
            assertEquals("Sender sub identifier", header.getString(SenderSubID.FIELD));
            assertEquals("Sender location identifier", header.getString(SenderLocationID.FIELD));
            assertEquals("Target sub identifier", header.getString(TargetSubID.FIELD));
            assertEquals("Target location identifier", header.getString(TargetLocationID.FIELD));
            assertEquals("On behalf of sub identifier", header.getString(OnBehalfOfSubID.FIELD));
            assertEquals("On behalf of location identifier", header.getString(OnBehalfOfLocationID.FIELD));
            assertEquals("Deliver to this company", header.getString(DeliverToSubID.FIELD));
            assertEquals("Deliver to this location", header.getString(DeliverToLocationID.FIELD));
            assertEquals("UTF-8", header.getString(MessageEncoding.FIELD));
            assertEquals(49, header.getInt(XmlDataLen.FIELD));
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><doc></doc>", 
                    header.getString(XmlData.FIELD));
            assertEquals(9, header.getInt(LastMsgSeqNumProcessed.FIELD));
            assertTrue(header.getBoolean(PossDupFlag.FIELD));
            assertFalse(header.getBoolean(PossResend.FIELD));
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal.set(Calendar.DAY_OF_MONTH, 23);
            cal.set(Calendar.MONTH, 6);
            cal.set(Calendar.YEAR, 2008);
            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 45);
            cal.set(Calendar.SECOND, 15);
            cal.set(Calendar.MILLISECOND, 0);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal1.setTimeInMillis(header.getUtcTimeStamp(SendingTime.FIELD).getTime());
            assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
            cal.add(Calendar.HOUR_OF_DAY, -2);
            cal1.setTimeInMillis(header.getUtcTimeStamp(OrigSendingTime.FIELD).getTime());
            assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
            cal.add(Calendar.HOUR_OF_DAY, -1);
            cal1.setTimeInMillis(header.getUtcTimeStamp(OnBehalfOfSendingTime.FIELD).getTime());
            assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
            assertEquals(2, header.getInt(NoHops.FIELD));
            assertTrue(header.hasGroup(NoHops.FIELD));
            quickfix.fix43.Message.Header.NoHops grp1 = new quickfix.fix43.Message.Header.NoHops();
            header.getGroup(1, grp1);
            HopCompID f1 = new HopCompID();
            grp1.get(f1);
            assertEquals("Hop No 1", f1.getValue());
            HopSendingTime f2 = new HopSendingTime();
            grp1.get(f2);
            assertUTCTimestampEquals(cal.getTime(), f2.getValue(), false);
            HopRefID f3 = new HopRefID();
            grp1.get(f3);
            assertEquals(3, f3.getValue());
            cal.add(Calendar.HOUR_OF_DAY, -1);
            quickfix.fix43.Message.Header.NoHops grp2 = new quickfix.fix43.Message.Header.NoHops();
            header.getGroup(2, grp2);
            f1 = new HopCompID();
            grp2.get(f1);
            assertEquals("Hop No 2", f1.getValue());
            f2 = new HopSendingTime();
            grp2.get(f2);
            assertUTCTimestampEquals(cal.getTime(), f2.getValue(), false);
            f3 = new HopRefID();
            grp2.get(f3);
            assertEquals(4, f3.getValue());
        } catch (FieldNotFound ex) {
            fail(ex.toString());
        }
    }
}