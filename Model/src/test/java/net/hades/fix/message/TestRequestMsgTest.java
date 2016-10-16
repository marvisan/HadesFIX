/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestRequestMsgTest.java
 *
 * $Id: TestRequestMsgTest.java,v 1.3 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import quickfix.DataDictionary;
import quickfix.field.TestReqID;

import net.hades.fix.TestUtils;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.BeginString;

/**
 * Test suite for TestRequestMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/07/2008, 15:14:31
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRequestMsgTest extends MsgTest {

    private DataDictionary dictionary;
    
    public TestRequestMsgTest() {
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
     * Test of encode method, of class TestRequestMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a1_testEncode40() throws Exception {
        System.out.println("-->testEncode40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setTestReqID("Test field 4.0");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("Test field 4.0", qfMsg.getString(quickfix.field.TestReqID.FIELD));
    }
    
    /**
     * Test of encode method, of class TestRequestMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a2_testEncode41() throws Exception {
        System.out.println("-->testEncode41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setTestReqID("Test field 4.1");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("Test field 4.1", qfMsg.getString(quickfix.field.TestReqID.FIELD));
    }
    
    /**
     * Test of encode method, of class TestRequestMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a3_testEncode42() throws Exception {
        System.out.println("-->testEncode42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setTestReqID("Test field 4.2");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("Test field 4.2", qfMsg.getString(quickfix.field.TestReqID.FIELD));
    }
    
    /**
     * Test of encode method, of class TestRequestMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a4_testEncode43() throws Exception {
        System.out.println("-->testEncode43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setTestReqID("Test field 4.3");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("Test field 4.3", qfMsg.getString(quickfix.field.TestReqID.FIELD));
    }
    
    /**
     * Test of encode method, of class TestRequestMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a5_testEncode44() throws Exception {
        System.out.println("-->testEncode44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setTestReqID("Test field 4.4");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("Test field 4.4", qfMsg.getString(quickfix.field.TestReqID.FIELD));
    }
    
    /**
     * Test of encode method, of class TestRequestMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a6_testEncodeT11() throws Exception {
        System.out.println("-->testEncodeT11");
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.setTestReqID("Test field T 1.1");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
    }

    /**
     * Test of decode method, of class TestRequestMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a10_testDecode40() throws Exception {
        System.out.println("-->testDecode40");
        String strMsg = buildMessage40().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        
        assertEquals("Test field 4.0", msg.getTestReqID());
    }
    
    /**
     * Test of decode method, of class TestRequestMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a11_testDecode41() throws Exception {
        System.out.println("-->testDecode41");
        String strMsg = buildMessage41().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals("Test field 4.1", msg.getTestReqID());
    }
    
    /**
     * Test of decode method, of class TestRequestMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a12_testDecode42() throws Exception {
        System.out.println("-->testDecode42");
        String strMsg = buildMessage42().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals("Test field 4.2", msg.getTestReqID());
    }
    
    /**
     * Test of decode method, of class TestRequestMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a13_testDecode43() throws Exception {
        System.out.println("-->testDecode43");
        String strMsg = buildMessage43().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals("Test field 4.3", msg.getTestReqID());
    }
    
    /**
     * Test of decode method, of class TestRequestMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a14_testDecode44() throws Exception {
        System.out.println("-->testDecode44");
        String strMsg = buildMessage44().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        
        assertEquals("Test field 4.4", msg.getTestReqID());
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class LogonMsg with missing TestReqID data.
     */
    @Test
    public void x1_testEncodeMissingTestReqID() {
        try {
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals( "Tag value(s) for [TestReqID] is missing.", ex.getMessage());
        }
    }
    
    // UTILITY METHODS

    private quickfix.fix40.TestRequest buildMessage40() {
        quickfix.fix40.TestRequest msg = new quickfix.fix40.TestRequest();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.0"));
        
        return msg;
    }

    
    private quickfix.fix41.TestRequest buildMessage41() {
        quickfix.fix41.TestRequest msg = new quickfix.fix41.TestRequest();
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.1"));
        
        return msg;
    }
    
    private quickfix.fix42.TestRequest buildMessage42() {
        quickfix.fix42.TestRequest msg = new quickfix.fix42.TestRequest();
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.2"));
        
        return msg;
    }
    
    private quickfix.fix43.TestRequest buildMessage43() {
        quickfix.fix43.TestRequest msg = new quickfix.fix43.TestRequest();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.3"));
        
        return msg;
    }
    
    private quickfix.fix44.TestRequest buildMessage44() {
        quickfix.fix44.TestRequest msg = new quickfix.fix44.TestRequest();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.4"));
        
        return msg;
    }

}