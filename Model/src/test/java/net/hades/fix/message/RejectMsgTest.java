/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RejectMsgTest.java
 *
 * $Id: RejectMsgTest.java,v 1.5 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import quickfix.DataDictionary;

import net.hades.fix.TestUtils;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

/**
 * Test suite for RejectMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 11/08/2008, 19:57:08
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RejectMsgTest extends MsgTest {

    private DataDictionary dictionary;
    
    public RejectMsgTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReq40() throws Exception {
        System.out.println("-->testEncodeReq40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a2_testEncodeAll40() throws Exception {
        System.out.println("-->testEncodeAll40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        msg.setText("Reject reason text");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
        assertEquals("Reject reason text", qfMsg.getString(quickfix.field.Text.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a5_testEncodeReq41() throws Exception {
        System.out.println("-->testEncodeReq41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a6_testEncodeAll41() throws Exception {
        System.out.println("-->testEncodeAll41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        msg.setText("Reject reason text");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
        assertEquals("Reject reason text", qfMsg.getString(quickfix.field.Text.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a10_testEncode42() throws Exception {
        System.out.println("-->testEncodeReq42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a11_testEncodeAll42() throws Exception {
        System.out.println("-->testEncodeAll42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        msg.setRefTagID(new Integer(TagNum.ExecInst.getValue()));
        msg.setRefMsgType(MsgType.ListStatus.getValue());
        msg.setSessionRejectReason(SessionRejectReason.InvalidTagNumber);
        msg.setText("Reject reason text");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedText(raw);
        msg.setEncodedTextLen(new Integer(raw.length));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
        assertEquals(TagNum.ExecInst.getValue(), qfMsg.getInt(quickfix.field.RefTagID.FIELD));
        assertEquals(MsgType.ListStatus.getValue(), qfMsg.getString(quickfix.field.RefMsgType.FIELD));
        assertEquals(SessionRejectReason.InvalidTagNumber.getValue(), qfMsg.getInt(quickfix.field.SessionRejectReason.FIELD));
        assertEquals("Reject reason text", qfMsg.getString(quickfix.field.Text.FIELD));
        assertEquals(raw.length, qfMsg.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.EncodedText.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a15_testEncodeReq43() throws Exception {
        System.out.println("-->testEncodeReq43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a16_testEncodeAll43() throws Exception {
        System.out.println("-->testEncodeAll43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        msg.setRefTagID(new Integer(TagNum.ExecInst.getValue()));
        msg.setRefMsgType(MsgType.ListStatus.getValue());
        msg.setSessionRejectReason(SessionRejectReason.InvalidTagNumber);
        msg.setText("Reject reason text");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedText(raw);
        msg.setEncodedTextLen(new Integer(raw.length));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
        assertEquals(TagNum.ExecInst.getValue(), qfMsg.getInt(quickfix.field.RefTagID.FIELD));
        assertEquals(MsgType.ListStatus.getValue(), qfMsg.getString(quickfix.field.RefMsgType.FIELD));
        assertEquals(SessionRejectReason.InvalidTagNumber.getValue(), qfMsg.getInt(quickfix.field.SessionRejectReason.FIELD));
        assertEquals("Reject reason text", qfMsg.getString(quickfix.field.Text.FIELD));
        assertEquals(raw.length, qfMsg.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.EncodedText.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a20_testEncodeReq44() throws Exception {
        System.out.println("-->testEncodeReq44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a21_testEncodeAll44() throws Exception {
        System.out.println("-->testEncodeAll44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setRefSeqNo(new Integer(34));
        msg.setRefTagID(new Integer(TagNum.ExecInst.getValue()));
        msg.setRefMsgType(MsgType.ListStatus.getValue());
        msg.setSessionRejectReason(SessionRejectReason.InvalidTagNumber);
        msg.setText("Reject reason text");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedText(raw);
        msg.setEncodedTextLen(new Integer(raw.length));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(34, qfMsg.getInt(quickfix.field.RefSeqNum.FIELD));
        assertEquals(TagNum.ExecInst.getValue(), qfMsg.getInt(quickfix.field.RefTagID.FIELD));
        assertEquals(MsgType.ListStatus.getValue(), qfMsg.getString(quickfix.field.RefMsgType.FIELD));
        assertEquals(SessionRejectReason.InvalidTagNumber.getValue(), qfMsg.getInt(quickfix.field.SessionRejectReason.FIELD));
        assertEquals("Reject reason text", qfMsg.getString(quickfix.field.Text.FIELD));
        assertEquals(raw.length, qfMsg.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.EncodedText.FIELD));
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIXT 1.1.
     * @throws Exception 
     */
    @Test
    public void a25_testEncodeReqT11() throws Exception {
        System.out.println("-->testEncodeReqT11");
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setRefSeqNo(new Integer(34));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        RejectMsg decoded = (RejectMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        decoded.decode();

        assertEquals(34, decoded.getRefSeqNo().intValue());
    }
    
    /**
     * Test of encode method, of class RejectMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a26_testEncodeAllT11() throws Exception {
        System.out.println("-->testEncodeAllT11");
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setRefSeqNo(new Integer(34));
        msg.setRefTagID(new Integer(TagNum.ExecInst.getValue()));
        msg.setRefMsgType(MsgType.ListStatus.getValue());
        msg.setSessionRejectReason(SessionRejectReason.InvalidTagNumber);
        msg.setText("Reject reason text");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedText(raw);
        msg.setEncodedTextLen(new Integer(raw.length));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        RejectMsg decoded = (RejectMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        decoded.decode();

        assertEquals(34, decoded.getRefSeqNo().intValue());
        assertEquals(TagNum.ExecInst.getValue(), decoded.getRefTagID().intValue());
        assertEquals(MsgType.ListStatus.getValue(), decoded.getRefMsgType());
        assertEquals(SessionRejectReason.InvalidTagNumber, decoded.getSessionRejectReason());
        assertEquals("Reject reason text", decoded.getText());
        assertEquals(raw.length, decoded.getEncodedTextLen().intValue());
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(decoded.getEncodedText(), DEFAULT_CHARACTER_SET));
    }

    /**
     * Test of decode method, of class RejectMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void b1_testDecode40() throws Exception {
        System.out.println("-->testDecode40");
        String strMsg = buildMessage40().toString();
        System.out.println("msg-->" + strMsg);
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        
        assertEquals(88, msg.getRefSeqNo().intValue());
        assertEquals("This is a reject message", msg.getText());
    }
    
    /**
     * Test of decode method, of class RejectMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void b5_testDecode41() throws Exception {
        System.out.println("-->testDecode41");
        String strMsg = buildMessage41().toString();
        System.out.println("msg-->" + strMsg);
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(88, msg.getRefSeqNo().intValue());
        assertEquals("This is a reject message", msg.getText());
    }
    
    /**
     * Test of decode method, of class RejectMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void b10_testDecode42() throws Exception {
        System.out.println("-->testDecode42");
        String strMsg = buildMessage42().toString();
        System.out.println("msg-->" + strMsg);
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(88, msg.getRefSeqNo().intValue());
        assertEquals(TagNum.EncryptMethod.getValue(), msg.getRefTagID().intValue());
        assertEquals(MsgType.MarketDataSnapshot.getValue(), msg.getRefMsgType());
        assertEquals(SessionRejectReason.XMLValidationError, msg.getSessionRejectReason());
        assertEquals("This is a reject message", msg.getText());
        assertEquals(8, msg.getEncodedTextLen().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getEncodedText(), DEFAULT_CHARACTER_SET));
    }
    
    /**
     * Test of decode method, of class RejectMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void b15_testDecode43() throws Exception {
        System.out.println("-->testDecode43");
        String strMsg = buildMessage43().toString();
        System.out.println("msg-->" + strMsg);
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(88, msg.getRefSeqNo().intValue());
        assertEquals(TagNum.EncryptMethod.getValue(), msg.getRefTagID().intValue());
        assertEquals(MsgType.MarketDataSnapshot.getValue(), msg.getRefMsgType());
        assertEquals(SessionRejectReason.XMLValidationError, msg.getSessionRejectReason());
        assertEquals("This is a reject message", msg.getText());
        assertEquals(8, msg.getEncodedTextLen().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getEncodedText(), DEFAULT_CHARACTER_SET));
    }
    
    /**
     * Test of decode method, of class RejectMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a14_testDecode44() throws Exception {
        System.out.println("-->testDecode44");
        String strMsg = buildMessage44().toString();
        System.out.println("msg-->" + strMsg);
        RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(88, msg.getRefSeqNo().intValue());
        assertEquals(TagNum.EncryptMethod.getValue(), msg.getRefTagID().intValue());
        assertEquals(MsgType.MarketDataSnapshot.getValue(), msg.getRefMsgType());
        assertEquals(SessionRejectReason.XMLValidationError, msg.getSessionRejectReason());
        assertEquals("This is a reject message", msg.getText());
        assertEquals(8, msg.getEncodedTextLen().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getEncodedText(), DEFAULT_CHARACTER_SET));
    }
    
    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class RejectMsg with missing RefSeqNo data.
     */
    @Test
    public void x1_testEncodeMissingRefSeqNo40() {
        System.out.println("-->testEncodeMissingRefSeqNo40");
        try {
            RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setText("some text here");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RefSeqNo] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class RejectMsg with missing RefSeqNo data.
     */
    @Test
    public void x5_testEncodeMissingRefSeqNo41() {
        System.out.println("-->testEncodeMissingRefSeqNo41");
        try {
            RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.setText("some text here");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RefSeqNo] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class RejectMsg with missing RefSeqNo data.
     */
    @Test
    public void x10_testEncodeMissingRefSeqNo42() {
        System.out.println("-->testEncodeMissingRefSeqNo42");
        try {
            RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setText("some text here");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RefSeqNo] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class RejectMsg with missing RefSeqNo data.
     */
    @Test
    public void x15_testEncodeMissingRefSeqNo43() {
        System.out.println("-->testEncodeMissingRefSeqNo43");
        try {
            RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setText("some text here");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RefSeqNo] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class RejectMsg with missing RefSeqNo data.
     */
    @Test
    public void x20_testEncodeMissingRefSeqNo44() {
        System.out.println("-->testEncodeMissingRefSeqNo44");
        try {
            RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setText("some text here");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RefSeqNo] is missing.", ex.getMessage());
        }
    }

    /**
     * Test of encode method, of class RejectMsg with missing EncryptMethod data.
     */
    @Test
    public void x25_testEncodeMissingEncryptMethodT11() {
        System.out.println("-->testEncodeMissingRefSeqNoT11");
        try {
            RejectMsg msg = (RejectMsg) FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_5_0);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setText("some text here");
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [RefSeqNo] is missing.", ex.getMessage());
        }
    }
     
    // UTILITY METHODS

    private quickfix.fix40.Reject buildMessage40() throws Exception {
        quickfix.fix40.Reject msg = new quickfix.fix40.Reject();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setInt(quickfix.field.RefSeqNum.FIELD, 88);
        msg.setString(quickfix.field.Text.FIELD, "This is a reject message");

        return msg;
    }

    
    private quickfix.fix41.Reject buildMessage41() throws Exception {
        quickfix.fix41.Reject msg = new quickfix.fix41.Reject();
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setInt(quickfix.field.RefSeqNum.FIELD, 88);
        msg.setString(quickfix.field.Text.FIELD, "This is a reject message");

        return msg;
    }
    
    private quickfix.fix42.Reject buildMessage42() throws Exception {
        quickfix.fix42.Reject msg = new quickfix.fix42.Reject();
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setInt(quickfix.field.RefSeqNum.FIELD, 88);
        msg.setInt(quickfix.field.RefTagID.FIELD, 98);
        msg.setString(quickfix.field.RefMsgType.FIELD, "W");
        msg.setInt(quickfix.field.SessionRejectReason.FIELD, 12);
        msg.setString(quickfix.field.Text.FIELD, "This is a reject message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        
        return msg;
    }
    
    private quickfix.fix43.Reject buildMessage43() throws Exception {
        quickfix.fix43.Reject msg = new quickfix.fix43.Reject();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setInt(quickfix.field.RefSeqNum.FIELD, 88);
        msg.setInt(quickfix.field.RefTagID.FIELD, 98);
        msg.setString(quickfix.field.RefMsgType.FIELD, "W");
        msg.setInt(quickfix.field.SessionRejectReason.FIELD, 12);
        msg.setString(quickfix.field.Text.FIELD, "This is a reject message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        
        return msg;
    }
    
    private quickfix.fix44.Reject buildMessage44() throws Exception {
        quickfix.fix44.Reject msg = new quickfix.fix44.Reject();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setInt(quickfix.field.RefSeqNum.FIELD, 88);
        msg.setInt(quickfix.field.RefTagID.FIELD, 98);
        msg.setString(quickfix.field.RefMsgType.FIELD, "W");
        msg.setInt(quickfix.field.SessionRejectReason.FIELD, 12);
        msg.setString(quickfix.field.Text.FIELD, "This is a reject message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        
        return msg;
    }
}