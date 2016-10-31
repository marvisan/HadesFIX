/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogoutMsgTest.java
 *
 * $Id: LogoutMsgTest.java,v 1.3 2011-04-04 09:37:11 vrotaru Exp $
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

/**
 * Test suite for LogoutMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/08/2008, 20:34:20
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogoutMsgTest extends MsgTest {

    private DataDictionary dictionary;
    
    public LogoutMsgTest() {
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
     * Test of encode method, of class LogoutMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReq40() throws Exception {
        System.out.println("-->testEncodeReq40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a2_testEncodeAll40() throws Exception {
        System.out.println("-->testEncodeAll40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setText("logout message");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("logout message", qfMsg.getString(quickfix.field.Text.FIELD));
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a5_testEncodeReq41() throws Exception {
        System.out.println("-->testEncodeReq41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a6_testEncodeAll41() throws Exception {
        System.out.println("-->testEncodeAll41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setText("logout message");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("logout message", qfMsg.getString(quickfix.field.Text.FIELD));
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a10_testEncodeReq42() throws Exception {
        System.out.println("-->testEncodeReq42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a11_testEncodeAll42() throws Exception {
        System.out.println("-->testEncodeAll42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setText("logout message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedText(raw);
        msg.setEncodedTextLen(new Integer(raw.length));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("logout message", qfMsg.getString(quickfix.field.Text.FIELD));
        assertEquals(raw.length, qfMsg.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.EncodedText.FIELD));
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a15_testEncodeReq43() throws Exception {
        System.out.println("-->testEncodeReq43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a16_testEncodeAll43() throws Exception {
        System.out.println("-->testEncodeAll43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setText("logout message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedText(raw);
        msg.setEncodedTextLen(new Integer(raw.length));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("logout message", qfMsg.getString(quickfix.field.Text.FIELD));
        assertEquals(raw.length, qfMsg.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.EncodedText.FIELD));
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a20_testEncodeReq44() throws Exception {
        System.out.println("-->testEncodeReq44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a21_testEncodeAll44() throws Exception {
        System.out.println("-->testEncodeAll44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setText("logout message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedText(raw);
        msg.setEncodedTextLen(new Integer(raw.length));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals("logout message", qfMsg.getString(quickfix.field.Text.FIELD));
        assertEquals(raw.length, qfMsg.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.EncodedText.FIELD));
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIXT 1.1.
     * @throws Exception 
     */
    @Test
    public void a25_testEncodeReqT11() throws Exception {
        System.out.println("-->testEncodeReqT11");
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        LogoutMsg decoded = (LogoutMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
    }
    
    /**
     * Test of encode method, of class LogoutMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a26_testEncodeAllT11() throws Exception {
        System.out.println("-->testEncodeAllT11");
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setText("logout message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setEncodedTextLen(new Integer(raw.length));
        msg.setEncodedText(raw);
        
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        LogoutMsg decoded = (LogoutMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        decoded.decode();

        assertEquals("logout message", decoded.getText());
        assertEquals(raw.length, decoded.getEncodedTextLen().intValue());
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(decoded.getEncodedText(), DEFAULT_CHARACTER_SET));
    }

    /**
     * Test of decode method, of class LogoutMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void b1_testDecode40() throws Exception {
        System.out.println("-->testDecode40");
        String strMsg = buildMessage40().toString();
        System.out.println("msg-->" + strMsg);
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals("This is a logout message", msg.getText());
    }
    
    /**
     * Test of decode method, of class LogoutMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void b5_testDecode41() throws Exception {
        System.out.println("-->testDecode41");
        String strMsg = buildMessage41().toString();
        System.out.println("msg-->" + strMsg);
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals("This is a logout message", msg.getText());
    }
    
    /**
     * Test of decode method, of class LogoutMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void b10_testDecode42() throws Exception {
        System.out.println("-->testDecode42");
        String strMsg = buildMessage42().toString();
        System.out.println("msg-->" + strMsg);
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals("This is a logout message", msg.getText());
        assertEquals(8, msg.getEncodedTextLen().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getEncodedText(), DEFAULT_CHARACTER_SET));
    }
    
    /**
     * Test of decode method, of class LogoutMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void b15_testDecode43() throws Exception {
        System.out.println("-->testDecode43");
        String strMsg = buildMessage43().toString();
        System.out.println("msg-->" + strMsg);
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals("This is a logout message", msg.getText());
        assertEquals(8, msg.getEncodedTextLen().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getEncodedText(), DEFAULT_CHARACTER_SET));
    }
    
    /**
     * Test of decode method, of class LogoutMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a14_testDecode44() throws Exception {
        System.out.println("-->testDecode44");
        String strMsg = buildMessage44().toString();
        System.out.println("msg-->" + strMsg);
        LogoutMsg msg = (LogoutMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        
        assertEquals("This is a logout message", msg.getText());
        assertEquals(8, msg.getEncodedTextLen().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getEncodedText(), DEFAULT_CHARACTER_SET));
    }
    
    // NEGATIVE TEST CASES
    /////////////////////////////////////////
      
    // UTILITY METHODS

    private quickfix.fix40.Logout buildMessage40() throws Exception {
        quickfix.fix40.Logout msg = new quickfix.fix40.Logout();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(quickfix.field.Text.FIELD, "This is a logout message");

        return msg;
    }

    
    private quickfix.fix41.Logout buildMessage41() throws Exception {
        quickfix.fix41.Logout msg = new quickfix.fix41.Logout();
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setString(quickfix.field.Text.FIELD, "This is a logout message");

        return msg;
    }
    
    private quickfix.fix42.Logout buildMessage42() throws Exception {
        quickfix.fix42.Logout msg = new quickfix.fix42.Logout();
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setString(quickfix.field.Text.FIELD, "This is a logout message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        
        return msg;
    }
    
    private quickfix.fix43.Logout buildMessage43() throws Exception {
        quickfix.fix43.Logout msg = new quickfix.fix43.Logout();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setString(quickfix.field.Text.FIELD, "This is a logout message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        
        return msg;
    }
    
    private quickfix.fix44.Logout buildMessage44() throws Exception {
        quickfix.fix44.Logout msg = new quickfix.fix44.Logout();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.Text.FIELD, "This is a logout message");
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.EncodedText.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        
        return msg;
    }
}