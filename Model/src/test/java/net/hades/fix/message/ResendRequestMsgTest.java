/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ResendRequestMsgTest.java
 *
 * $Id: ResendRequestMsgTest.java,v 1.3 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import quickfix.DataDictionary;
import quickfix.field.BeginSeqNo;
import quickfix.field.EndSeqNo;

import net.hades.fix.TestUtils;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.BeginString;

/**
 * Test suite for ResendRequestMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/08/2008, 14:43:49
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResendRequestMsgTest extends MsgTest {

    private DataDictionary dictionary;
    
    public ResendRequestMsgTest() {
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
     * Test of encode method, of class ResendRequestMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a1_testEncode40() throws Exception {
        System.out.println("-->testEncode40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setBeginSeqNo(new Integer(20));
        msg.setEndSeqNo(new Integer(30));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(20, qfMsg.getInt(quickfix.field.BeginSeqNo.FIELD));
        assertEquals(30, qfMsg.getInt(quickfix.field.EndSeqNo.FIELD));
    }
    
    /**
     * Test of encode method, of class ResendRequestMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a2_testEncode41() throws Exception {
        System.out.println("-->testEncode41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setBeginSeqNo(new Integer(20));
        msg.setEndSeqNo(new Integer(30));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(20, qfMsg.getInt(quickfix.field.BeginSeqNo.FIELD));
        assertEquals(30, qfMsg.getInt(quickfix.field.EndSeqNo.FIELD));
    }
    
    /**
     * Test of encode method, of class ResendRequestMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a3_testEncode42() throws Exception {
        System.out.println("-->testEncode42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setBeginSeqNo(new Integer(20));
        msg.setEndSeqNo(new Integer(30));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(20, qfMsg.getInt(quickfix.field.BeginSeqNo.FIELD));
        assertEquals(30, qfMsg.getInt(quickfix.field.EndSeqNo.FIELD));
    }
    
    /**
     * Test of encode method, of class ResendRequestMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a4_testEncode43() throws Exception {
        System.out.println("-->testEncode43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setBeginSeqNo(new Integer(20));
        msg.setEndSeqNo(new Integer(30));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(20, qfMsg.getInt(quickfix.field.BeginSeqNo.FIELD));
        assertEquals(30, qfMsg.getInt(quickfix.field.EndSeqNo.FIELD));
    }
    
    /**
     * Test of encode method, of class ResendRequestMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a5_testEncode44() throws Exception {
        System.out.println("-->testEncode44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setBeginSeqNo(new Integer(20));
        msg.setEndSeqNo(new Integer(30));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(20, qfMsg.getInt(quickfix.field.BeginSeqNo.FIELD));
        assertEquals(30, qfMsg.getInt(quickfix.field.EndSeqNo.FIELD));
    }
    
    /**
     * Test of encode method, of class ResendRequestMsg for FIXT 1.1.
     * @throws Exception 
     */
    @Test
    public void a6_testEncodeT11() throws Exception {
        System.out.println("-->testEncodeT11");
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setBeginSeqNo(new Integer(20));
        msg.setEndSeqNo(new Integer(30));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        msg = (ResendRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        
        assertEquals(20, msg.getBeginSeqNo().intValue());
        assertEquals(30, msg.getEndSeqNo().intValue());
    }

    /**
     * Test of decode method, of class ResendRequestMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a10_testDecode40() throws Exception {
        System.out.println("-->testDecode40");
        String strMsg = buildMessage40().toString();
        System.out.println("msg-->" + strMsg);
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(50, msg.getBeginSeqNo().intValue());
        assertEquals(56, msg.getEndSeqNo().intValue());
    }
    
    /**
     * Test of decode method, of class ResendRequestMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a11_testDecode41() throws Exception {
        System.out.println("-->testDecode41");
        String strMsg = buildMessage41().toString();
        System.out.println("msg-->" + strMsg);
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(50, msg.getBeginSeqNo().intValue());
        assertEquals(56, msg.getEndSeqNo().intValue());
    }
    
    /**
     * Test of decode method, of class ResendRequestMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a12_testDecode42() throws Exception {
        System.out.println("-->testDecode42");
        String strMsg = buildMessage42().toString();
        System.out.println("msg-->" + strMsg);
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(50, msg.getBeginSeqNo().intValue());
        assertEquals(56, msg.getEndSeqNo().intValue());
    }
    
    /**
     * Test of decode method, of class ResendRequestMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a13_testDecode43() throws Exception {
        System.out.println("-->testDecode43");
        String strMsg = buildMessage43().toString();
        System.out.println("msg-->" + strMsg);
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(50, msg.getBeginSeqNo().intValue());
        assertEquals(56, msg.getEndSeqNo().intValue());
    }
    
    /**
     * Test of decode method, of class ResendRequestMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a14_testDecode44() throws Exception {
        System.out.println("-->testDecode44");
        String strMsg = buildMessage44().toString();
        System.out.println("msg-->" + strMsg);
        ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(50, msg.getBeginSeqNo().intValue());
        assertEquals(56, msg.getEndSeqNo().intValue());
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class ResendRequestMsg with missing BeginSeqNo data.
     */
    @Test
    public void x1_testEncodeMissingBeginSeqNo() {
        try {
            ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.setEndSeqNo(new Integer(33));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [BeginSeqNo] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class ResendRequestMsg with missing EndSeqNo data.
     */
    @Test
    public void x2_testEncodeMissingEndSeqNo() {
        try {
            ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setBeginSeqNo(new Integer(33));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [EndSeqNo] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class ResendRequestMsg with missing all required data.
     */
    @Test
    public void x3_testEncodeMissingAllReq() {
        try {
            ResendRequestMsg msg = (ResendRequestMsg) FIXMsgBuilder.build(MsgType.ResendRequest.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [BeginSeqNo] [EndSeqNo] is missing.", ex.getMessage());
        }
    }
    
    // UTILITY METHODS

    private quickfix.fix40.ResendRequest buildMessage40() {
        quickfix.fix40.ResendRequest msg = new quickfix.fix40.ResendRequest();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setInt(BeginSeqNo.FIELD, 50);
        msg.setInt(EndSeqNo.FIELD, 56);
        
        return msg;
    }

    
    private quickfix.fix41.ResendRequest buildMessage41() {
        quickfix.fix41.ResendRequest msg = new quickfix.fix41.ResendRequest();
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setInt(BeginSeqNo.FIELD, 50);
        msg.setInt(EndSeqNo.FIELD, 56);
        
        return msg;
    }
    
    private quickfix.fix42.ResendRequest buildMessage42() {
        quickfix.fix42.ResendRequest msg = new quickfix.fix42.ResendRequest();
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setInt(BeginSeqNo.FIELD, 50);
        msg.setInt(EndSeqNo.FIELD, 56);
        
        return msg;
    }
    
    private quickfix.fix43.ResendRequest buildMessage43() {
        quickfix.fix43.ResendRequest msg = new quickfix.fix43.ResendRequest();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setInt(BeginSeqNo.FIELD, 50);
        msg.setInt(EndSeqNo.FIELD, 56);
        
        return msg;
    }
    
    private quickfix.fix44.ResendRequest buildMessage44() {
        quickfix.fix44.ResendRequest msg = new quickfix.fix44.ResendRequest();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setInt(BeginSeqNo.FIELD, 50);
        msg.setInt(EndSeqNo.FIELD, 56);
        
        return msg;
    }
}