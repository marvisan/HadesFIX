/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogonMsgTest.java
 *
 * $Id: LogonMsgTest.java,v 1.6 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import quickfix.DataDictionary;
import quickfix.field.NoMsgTypes;
import quickfix.field.RefMsgType;
import quickfix.field.TestMessageIndicator;

import net.hades.fix.TestUtils;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.MsgTypeGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.EncryptMethod;
import net.hades.fix.message.type.MsgDirection;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.BeginString;

/**
 * Test suite for LogonMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 9/07/2008, 20:54:14
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogonMsgTest extends MsgTest {

    private DataDictionary dictionary;
    
    public LogonMsgTest() {
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
            setFIXT11DefaultApplVersion();
        } catch (Exception ex) {
            System.out.println("Could not set default application version in session.");
            throw new RuntimeException("Could not set default application version in session.", ex);
        }
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReq40() throws Exception {
        System.out.println("-->testEncodeReq40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a2_testEncodeAll40() throws Exception {
        System.out.println("-->testEncodeAll40");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX40.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setRawDataLength(new Integer(8));
        msg.setRawData(raw);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix40.Message qfMsg = new quickfix.fix40.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
        assertEquals(8, qfMsg.getInt(quickfix.field.RawDataLength.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.RawData.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a5_testEncodeReq41() throws Exception {
        System.out.println("-->testEncodeReq41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void a6_testEncodeAll41() throws Exception {
        System.out.println("-->testEncodeAll41");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX41.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_1);
        TestUtils.populate41HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setRawDataLength(new Integer(8));
        msg.setRawData(raw);
        msg.setResetSeqNumFlag(Boolean.TRUE);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix41.Message qfMsg = new quickfix.fix41.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
        assertEquals(8, qfMsg.getInt(quickfix.field.RawDataLength.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.RawData.FIELD));
        assertTrue(qfMsg.getBoolean(quickfix.field.ResetSeqNumFlag.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a10_testEncode42() throws Exception {
        System.out.println("-->testEncodeReq42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void a11_testEncodeAll42() throws Exception {
        System.out.println("-->testEncodeAll42");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX42.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_2);
        TestUtils.populate42HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setRawDataLength(new Integer(8));
        msg.setRawData(raw);
        msg.setResetSeqNumFlag(Boolean.TRUE);
        msg.setMaxMessageSize(new Integer(2000));
        msg.setNoMsgTypes(new Integer(1));
        msg.getMsgTypeGroups()[0].setRefMsgType(MsgType.Heartbeat.getValue());
        msg.getMsgTypeGroups()[0].setMsgDirection(MsgDirection.Send);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix42.Message qfMsg = new quickfix.fix42.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
        assertEquals(8, qfMsg.getInt(quickfix.field.RawDataLength.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.RawData.FIELD));
        assertTrue(qfMsg.getBoolean(quickfix.field.ResetSeqNumFlag.FIELD));
        assertEquals(2000, qfMsg.getInt(quickfix.field.MaxMessageSize.FIELD));
        assertTrue(qfMsg.hasGroup(NoMsgTypes.FIELD));
        assertEquals(1, qfMsg.getInt(NoMsgTypes.FIELD));
        quickfix.fix42.Logon.NoMsgTypes g1 = new quickfix.fix42.Logon.NoMsgTypes();
        qfMsg.getGroup(1, g1);
        RefMsgType f1 = new RefMsgType();
        g1.get(f1);
        assertEquals(MsgType.Heartbeat.getValue(), f1.getValue());
        quickfix.field.MsgDirection f2 = new quickfix.field.MsgDirection();
        g1.get(f2);
        assertEquals(MsgDirection.Send.getValue(), new Character(f2.getValue()).toString());
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a15_testEncodeReq43() throws Exception {
        System.out.println("-->testEncodeReq43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void a16_testEncodeAll43() throws Exception {
        System.out.println("-->testEncodeAll43");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX43.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_3);
        TestUtils.populate43HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setRawDataLength(new Integer(8));
        msg.setRawData(raw);
        msg.setResetSeqNumFlag(Boolean.TRUE);
        msg.setMaxMessageSize(new Integer(2000));
        MsgTypeGroup grp1 = msg.addMsgTypeGroup();
        MsgTypeGroup grp2 = msg.addMsgTypeGroup();
        grp1.setRefMsgType(MsgType.Heartbeat.getValue());
        grp1.setMsgDirection(MsgDirection.Send);
        grp2.setRefMsgType(MsgType.Allocation.getValue());
        grp2.setMsgDirection(MsgDirection.Received);
        msg.setTestMessageIndicator(Boolean.TRUE);
        msg.setUsername("username");
        msg.setPassword("password");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix43.Message qfMsg = new quickfix.fix43.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
        assertEquals(8, qfMsg.getInt(quickfix.field.RawDataLength.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.RawData.FIELD));
        assertTrue(qfMsg.getBoolean(quickfix.field.ResetSeqNumFlag.FIELD));
        assertEquals(2000, qfMsg.getInt(quickfix.field.MaxMessageSize.FIELD));
        assertTrue(qfMsg.hasGroup(NoMsgTypes.FIELD));
        assertEquals(2, qfMsg.getInt(NoMsgTypes.FIELD));
        quickfix.fix43.Logon.NoMsgTypes g1 = new quickfix.fix43.Logon.NoMsgTypes();
        qfMsg.getGroup(1, g1);
        RefMsgType f1 = new RefMsgType();
        g1.get(f1);
        assertEquals(MsgType.Heartbeat.getValue(), f1.getValue());
        quickfix.field.MsgDirection f2 = new quickfix.field.MsgDirection();
        g1.get(f2);
        assertEquals(MsgDirection.Send.getValue(), new Character(f2.getValue()).toString());
        quickfix.fix43.Logon.NoMsgTypes g2 = new quickfix.fix43.Logon.NoMsgTypes();
        qfMsg.getGroup(2, g2);
        RefMsgType f11 = new RefMsgType();
        g2.get(f11);
        assertEquals(MsgType.Allocation.getValue(), f11.getValue());
        quickfix.field.MsgDirection f12 = new quickfix.field.MsgDirection();
        g2.get(f12);
        assertEquals(MsgDirection.Received.getValue(), new Character(f12.getValue()).toString());
        assertTrue(qfMsg.getBoolean(TestMessageIndicator.FIELD));
        assertEquals("username", qfMsg.getString(quickfix.field.Username.FIELD));
        assertEquals("password", qfMsg.getString(quickfix.field.Password.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a20_testEncodeReq44() throws Exception {
        System.out.println("-->testEncodeReq44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a21_testEncodeAll44() throws Exception {
        System.out.println("-->testEncodeAll44");
        dictionary = new DataDictionary(this.getClass().getClassLoader().getResourceAsStream("FIX44.xml"));
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
        TestUtils.populate44HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setRawDataLength(new Integer(8));
        msg.setRawData(raw);
        msg.setResetSeqNumFlag(Boolean.TRUE);
        msg.setNextExpectedMsgSeqNum(new Integer(22));
        msg.setMaxMessageSize(new Integer(2000));
        msg.setNoMsgTypes(new Integer(5));
        assertEquals(5, msg.getMsgTypeGroups().length);
        msg.clearMsgTypeGroups();
        assertNull(msg.getMsgTypeGroups());
        assertNull(msg.getNoMsgTypes());
        msg.addMsgTypeGroup();
        msg.addMsgTypeGroup();
        msg.getMsgTypeGroups()[0].setRefMsgType(MsgType.Heartbeat.getValue());
        msg.getMsgTypeGroups()[0].setMsgDirection(MsgDirection.Send);
        msg.getMsgTypeGroups()[1].setRefMsgType(MsgType.Allocation.getValue());
        msg.getMsgTypeGroups()[1].setMsgDirection(MsgDirection.Received);
        msg.setTestMessageIndicator(Boolean.TRUE);
        msg.setUsername("username");
        msg.setPassword("password");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.fix44.Message qfMsg = new quickfix.fix44.Message();
        qfMsg.fromString(encoded, dictionary, true);
        assertEquals(EncryptMethod.DES.getValue(), qfMsg.getInt(quickfix.field.EncryptMethod.FIELD));
        assertEquals(60, qfMsg.getInt(quickfix.field.HeartBtInt.FIELD));
        assertEquals(8, qfMsg.getInt(quickfix.field.RawDataLength.FIELD));
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), qfMsg.getString(quickfix.field.RawData.FIELD));
        assertTrue(qfMsg.getBoolean(quickfix.field.ResetSeqNumFlag.FIELD));
        assertEquals(22, qfMsg.getInt(quickfix.field.NextExpectedMsgSeqNum.FIELD));
        assertEquals(2000, qfMsg.getInt(quickfix.field.MaxMessageSize.FIELD));
        assertTrue(qfMsg.hasGroup(NoMsgTypes.FIELD));
        assertEquals(2, qfMsg.getInt(NoMsgTypes.FIELD));
        quickfix.fix44.Logon.NoMsgTypes g1 = new quickfix.fix44.Logon.NoMsgTypes();
        qfMsg.getGroup(1, g1);
        RefMsgType f1 = new RefMsgType();
        g1.get(f1);
        assertEquals(MsgType.Heartbeat.getValue(), f1.getValue());
        quickfix.field.MsgDirection f2 = new quickfix.field.MsgDirection();
        g1.get(f2);
        assertEquals(MsgDirection.Send.getValue(), new Character(f2.getValue()).toString());
        quickfix.fix44.Logon.NoMsgTypes g2 = new quickfix.fix44.Logon.NoMsgTypes();
        qfMsg.getGroup(2, g2);
        RefMsgType f11 = new RefMsgType();
        g2.get(f11);
        assertEquals(MsgType.Allocation.getValue(), f11.getValue());
        quickfix.field.MsgDirection f12 = new quickfix.field.MsgDirection();
        g2.get(f12);
        assertEquals(MsgDirection.Received.getValue(), new Character(f12.getValue()).toString());
        assertTrue(qfMsg.getBoolean(TestMessageIndicator.FIELD));
        assertEquals("username", qfMsg.getString(quickfix.field.Username.FIELD));
        assertEquals("password", qfMsg.getString(quickfix.field.Password.FIELD));
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIXT 1.1.
     * @throws Exception 
     */
    @Test
    public void a25_testEncodeReqT11() throws Exception {
        System.out.println("-->testEncodeReqT11");
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        msg.setDefaultApplVerID(ApplVerID.FIX44);
        msg.setNoMsgTypes(new Integer(5));
        assertEquals(5, msg.getMsgTypeGroups().length);
        msg.deleteMsgTypeGroup(2);
        msg.deleteMsgTypeGroup(2);
        assertEquals(3, msg.getMsgTypeGroups().length);
        assertEquals(3, msg.clearMsgTypeGroups());
        assertNull(msg.getMsgTypeGroups());
        assertNull(msg.getNoMsgTypes());
        msg.addMsgTypeGroup();
        msg.addMsgTypeGroup();
        msg.getMsgTypeGroups()[0].setRefMsgType(MsgType.Heartbeat.getValue());
        msg.getMsgTypeGroups()[0].setMsgDirection(MsgDirection.Send);
        msg.getMsgTypeGroups()[0].setRefApplVerID(ApplVerID.FIX42);
        msg.getMsgTypeGroups()[0].setRefCstmApplVerID("V3.2");
        msg.getMsgTypeGroups()[1].setRefMsgType(MsgType.Allocation.getValue());
        msg.getMsgTypeGroups()[1].setMsgDirection(MsgDirection.Received);
        msg.getMsgTypeGroups()[1].setRefApplVerID(ApplVerID.FIX44);
        msg.getMsgTypeGroups()[1].setRefCstmApplVerID("V3.8");
        
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        LogonMsg decoded = (LogonMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        decoded.decode();

        assertEquals(2, decoded.getNoMsgTypes().intValue());
        assertEquals(2, decoded.getMsgTypeGroups().length);
        MsgTypeGroup grp1 = decoded.getMsgTypeGroups()[0];
        assertEquals(MsgType.Heartbeat.getValue(), grp1.getRefMsgType());
        assertEquals(MsgDirection.Send, grp1.getMsgDirection());
        assertEquals(ApplVerID.FIX42, grp1.getRefApplVerID());
        assertEquals("V3.2", grp1.getRefCstmApplVerID());
        MsgTypeGroup grp2 = decoded.getMsgTypeGroups()[1];
        assertEquals(MsgType.Allocation.getValue(), grp2.getRefMsgType());
        assertEquals(MsgDirection.Received, grp2.getMsgDirection());
        assertEquals(ApplVerID.FIX44, grp2.getRefApplVerID());
        assertEquals("V3.8", grp2.getRefCstmApplVerID());
    }
    
    /**
     * Test of encode method, of class LogonMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a26_testEncodeAllT11() throws Exception {
        System.out.println("-->testEncodeAllT11");
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_5_0);
        TestUtils.populateFIXT11HeaderAll(msg);
        msg.setEncryptMethod(EncryptMethod.DES);
        msg.setHeartBtInt(new Integer(60));
        msg.setDefaultApplVerID(ApplVerID.FIX44);
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setRawDataLength(new Integer(8));
        msg.setRawData(raw);
        msg.setResetSeqNumFlag(Boolean.TRUE);
        msg.setNextExpectedMsgSeqNum(new Integer(22));
        msg.setMaxMessageSize(new Integer(2000));
        msg.setNoMsgTypes(new Integer(2));
        msg.getMsgTypeGroups()[0].setRefMsgType(MsgType.Heartbeat.getValue());
        msg.getMsgTypeGroups()[0].setMsgDirection(MsgDirection.Send);
        msg.getMsgTypeGroups()[0].setRefApplVerID(ApplVerID.FIX41);
        msg.getMsgTypeGroups()[0].setRefCstmApplVerID("V3.2");
        msg.getMsgTypeGroups()[1].setRefMsgType(MsgType.Allocation.getValue());
        msg.getMsgTypeGroups()[1].setMsgDirection(MsgDirection.Received);
        msg.getMsgTypeGroups()[1].setRefApplVerID(ApplVerID.FIX44);
        msg.getMsgTypeGroups()[1].setRefCstmApplVerID("V3.8");
        msg.setTestMessageIndicator(Boolean.TRUE);
        msg.setUsername("username");
        msg.setPassword("password");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        LogonMsg decoded = (LogonMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        decoded.decode();
        
        assertEquals(ApplVerID.FIX44, decoded.getDefaultApplVerID());
        assertEquals(2, decoded.getNoMsgTypes().intValue());
        assertEquals(2, decoded.getMsgTypeGroups().length);
        MsgTypeGroup grp1 = decoded.getMsgTypeGroups()[0];
        assertEquals(MsgType.Heartbeat.getValue(), grp1.getRefMsgType());
        assertEquals(MsgDirection.Send, grp1.getMsgDirection());
        assertEquals(ApplVerID.FIX41, grp1.getRefApplVerID());
        assertEquals("V3.2", grp1.getRefCstmApplVerID());
        MsgTypeGroup grp2 = decoded.getMsgTypeGroups()[1];
        assertEquals(MsgType.Allocation.getValue(), grp2.getRefMsgType());
        assertEquals(MsgDirection.Received, grp2.getMsgDirection());
        assertEquals(ApplVerID.FIX44, grp2.getRefApplVerID());
        assertEquals("V3.8", grp2.getRefCstmApplVerID());
    }

    /**
     * Test of decode method, of class LogonMsg for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void b1_testDecode40() throws Exception {
        System.out.println("-->testDecode40");
        String strMsg = buildMessage40().toString();
        System.out.println("msg-->" + strMsg);
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(EncryptMethod.PKCS, msg.getEncryptMethod());
        assertEquals(44, msg.getHeartBtInt().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getRawData(), DEFAULT_CHARACTER_SET));
    }
    
    /**
     * Test of decode method, of class LogonMsg for FIX 4.1.
     * @throws Exception 
     */
    @Test
    public void b5_testDecode41() throws Exception {
        System.out.println("-->testDecode41");
        String strMsg = buildMessage41().toString();
        System.out.println("msg-->" + strMsg);
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(EncryptMethod.PKCS, msg.getEncryptMethod());
        assertEquals(44, msg.getHeartBtInt().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getRawData(), DEFAULT_CHARACTER_SET));
        assertEquals(true, msg.getResetSeqNumFlag().booleanValue());
    }
    
    /**
     * Test of decode method, of class LogonMsg for FIX 4.2.
     * @throws Exception 
     */
    @Test
    public void b10_testDecode42() throws Exception {
        System.out.println("-->testDecode42");
        String strMsg = buildMessage42().toString();
        System.out.println("msg-->" + strMsg);
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();

        assertEquals(EncryptMethod.PKCS, msg.getEncryptMethod());
        assertEquals(44, msg.getHeartBtInt().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        assertEquals(2048, msg.getMaxMessageSize().intValue());
        assertEquals(1, msg.getNoMsgTypes().intValue());
        MsgTypeGroup[] msgTypeGroups = msg.getMsgTypeGroups();
        assertNotNull(msgTypeGroups);
        assertTrue(msgTypeGroups.length == 1);
        assertEquals(MsgType.News.getValue(), msgTypeGroups[0].getRefMsgType());
        assertEquals(MsgDirection.Received, msgTypeGroups[0].getMsgDirection());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getRawData(), DEFAULT_CHARACTER_SET));
        assertEquals(true, msg.getResetSeqNumFlag().booleanValue());
    }
    
    /**
     * Test of decode method, of class LogonMsg for FIX 4.3.
     * @throws Exception 
     */
    @Test
    public void b15_testDecode43() throws Exception {
        System.out.println("-->testDecode43");
        String strMsg = buildMessage43().toString();
        System.out.println("msg-->" + strMsg);
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        
        assertEquals(EncryptMethod.PKCS, msg.getEncryptMethod());
        assertEquals(44, msg.getHeartBtInt().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        assertEquals(2048, msg.getMaxMessageSize().intValue());
        assertEquals(2, msg.getNoMsgTypes().intValue());
        MsgTypeGroup[] msgTypeGroups = msg.getMsgTypeGroups();
        assertNotNull(msgTypeGroups);
        assertTrue(msgTypeGroups.length == 2);
        assertEquals(MsgType.News.getValue(), msgTypeGroups[0].getRefMsgType());
        assertEquals(MsgDirection.Received, msgTypeGroups[0].getMsgDirection());
        assertEquals(MsgType.NewOrderSingle.getValue(), msgTypeGroups[1].getRefMsgType());
        assertEquals(MsgDirection.Send, msgTypeGroups[1].getMsgDirection());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getRawData(), DEFAULT_CHARACTER_SET));
        assertEquals(true, msg.getResetSeqNumFlag().booleanValue());
        assertEquals(true, msg.getTestMessageIndicator().booleanValue());
        assertEquals("username", msg.getUsername());
        assertEquals("password", msg.getPassword());
    }
    
    /**
     * Test of decode method, of class LogonMsg for FIX 4.4.
     * @throws Exception 
     */
    @Test
    public void a14_testDecode44() throws Exception {
        System.out.println("-->testDecode44");
        String strMsg = buildMessage44().toString();
        System.out.println("msg-->" + strMsg);
        LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        
        assertEquals(EncryptMethod.PKCS, msg.getEncryptMethod());
        assertEquals(44, msg.getHeartBtInt().intValue());
        assertEquals(8, msg.getRawDataLength().intValue());
        assertEquals(12, msg.getNextExpectedMsgSeqNum().intValue());
        assertEquals(2048, msg.getMaxMessageSize().intValue());
        assertEquals(2, msg.getNoMsgTypes().intValue());
        MsgTypeGroup[] msgTypeGroups = msg.getMsgTypeGroups();
        assertNotNull(msgTypeGroups);
        assertTrue(msgTypeGroups.length == 2);
        assertEquals(MsgType.News.getValue(), msgTypeGroups[0].getRefMsgType());
        assertEquals(MsgDirection.Received, msgTypeGroups[0].getMsgDirection());
        assertEquals(MsgType.NewOrderSingle.getValue(), msgTypeGroups[1].getRefMsgType());
        assertEquals(MsgDirection.Send, msgTypeGroups[1].getMsgDirection());
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        assertEquals(new String(raw, DEFAULT_CHARACTER_SET), new String(msg.getRawData(), DEFAULT_CHARACTER_SET));
        assertEquals(true, msg.getResetSeqNumFlag().booleanValue());
        assertEquals(true, msg.getTestMessageIndicator().booleanValue());
        assertEquals("username", msg.getUsername());
        assertEquals("password", msg.getPassword());
    }
    
    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class LogonMsg with missing EncryptMethod data.
     */
    @Test
    public void x1_testEncodeMissingEncryptMethod40() {
        System.out.println("-->testEncodeMissingEncryptMethod40");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setHeartBtInt(new Integer(30));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing HeartBtInt data.
     */
    @Test
    public void x2_testEncodeMissingHeartBtInt40() {
        System.out.println("-->testEncodeMissingHeartBtInt40");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setEncryptMethod(EncryptMethod.PEM_DES_MD5);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing all required data.
     */
    @Test
    public void x3_testEncodeMissingAllReq40() {
        System.out.println("-->testEncodeMissingAllReq40");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg 4.0 with unsupported tag.
     */
    @Test
    public void x4_testGetUnsupportedLogonMsg40Tag() {
        System.out.println("-->testGetUnsupportedLogonMsg40Tag");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_0);
            msg.getResetSeqNumFlag();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("This tag is not supported in [LogonMsg] message version [4.0].",
                    ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing EncryptMethod data.
     */
    @Test
    public void x5_testEncodeMissingEncryptMethod41() {
        System.out.println("-->testEncodeMissingEncryptMethod41");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.setHeartBtInt(new Integer(30));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing HeartBtInt data.
     */
    @Test
    public void x6_testEncodeMissingHeartBtInt41() {
        System.out.println("-->testEncodeMissingHeartBtInt41");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.setEncryptMethod(EncryptMethod.PEM_DES_MD5);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing all required data.
     */
    @Test
    public void x7_testEncodeMissingAllReq41() {
        System.out.println("-->testEncodeMissingAllReq41");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_1);
            TestUtils.populate41HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg 4.1 with unsupported tag.
     */
    @Test
    public void x8_testGetUnsupportedLogonMsg41Tag() {
        System.out.println("-->testGetUnsupportedLogonMsg41Tag");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_1);
            msg.getMaxMessageSize();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("This tag is not supported in [LogonMsg] message version [4.1].",
                    ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing EncryptMethod data.
     */
    @Test
    public void x10_testEncodeMissingEncryptMethod42() {
        System.out.println("-->testEncodeMissingEncryptMethod42");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setHeartBtInt(new Integer(30));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing HeartBtInt data.
     */
    @Test
    public void x11_testEncodeMissingHeartBtInt42() {
        System.out.println("-->testEncodeMissingHeartBtInt42");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.setEncryptMethod(EncryptMethod.PEM_DES_MD5);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing all required data.
     */
    @Test
    public void x12_testEncodeMissingAllReq42() {
        System.out.println("-->testEncodeMissingAllReq42");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_2);
            TestUtils.populate42HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg 4.2 with unsupported tag.
     */
    @Test
    public void x13_testGetUnsupportedLogonMsg42Tag() {
        System.out.println("-->testGetUnsupportedLogonMsg42Tag");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_2);
            msg.getTestMessageIndicator();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("This tag is not supported in [LogonMsg] message version [4.2].",
                    ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing EncryptMethod data.
     */
    @Test
    public void x15_testEncodeMissingEncryptMethod43() {
        System.out.println("-->testEncodeMissingEncryptMethod43");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setHeartBtInt(new Integer(30));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing HeartBtInt data.
     */
    @Test
    public void x16_testEncodeMissingHeartBtInt43() {
        System.out.println("-->testEncodeMissingHeartBtInt43");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.setEncryptMethod(EncryptMethod.PEM_DES_MD5);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing all required data.
     */
    @Test
    public void x17_testEncodeMissingAllReq43() {
        System.out.println("-->testEncodeMissingAllReq43");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_3);
            TestUtils.populate43HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg 4.2 with unsupported tag.
     */
    @Test
    public void x18_testGetUnsupportedLogonMsg43Tag() {
        System.out.println("-->testGetUnsupportedLogonMsg43Tag");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_3);
            msg.getNextExpectedMsgSeqNum();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("This tag is not supported in [LogonMsg] message version [4.3].",
                    ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing EncryptMethod data.
     */
    @Test
    public void x20_testEncodeMissingEncryptMethod44() {
        System.out.println("-->testEncodeMissingEncryptMethod44");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setHeartBtInt(new Integer(30));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing HeartBtInt data.
     */
    @Test
    public void x21_testEncodeMissingHeartBtInt44() {
        System.out.println("-->testEncodeMissingHeartBtInt44");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setEncryptMethod(EncryptMethod.PEM_DES_MD5);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing all required data.
     */
    @Test
    public void x22_testEncodeMissingAllReq44() {
        System.out.println("-->testEncodeMissingAllReq44");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg 4.2 with unsupported tag.
     */
    @Test
    public void x23_testGetUnsupportedLogonMsg44Tag() {
        System.out.println("-->testGetUnsupportedLogonMsg44Tag");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
            msg.getDefaultApplVerID();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("This tag is not supported in [LogonMsg] message version [4.4].",
                    ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing EncryptMethod data.
     */
    @Test
    public void x25_testEncodeMissingEncryptMethodT11() {
        System.out.println("-->testEncodeMissingEncryptMethodT11");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_5_0);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setHeartBtInt(new Integer(30));
            msg.setDefaultApplVerID(ApplVerID.FIX44);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing HeartBtInt data.
     */
    @Test
    public void x26_testEncodeMissingHeartBtIntT11() {
        System.out.println("-->testEncodeMissingHeartBtIntT11");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_5_0);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setEncryptMethod(EncryptMethod.PEM_DES_MD5);
            msg.setDefaultApplVerID(ApplVerID.FIX44);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [HeartBtInt] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing DefaultApplVerID data.
     */
    @Test
    public void x27_testEncodeMissingHeartBtIntT11() {
        System.out.println("-->testEncodeMissingDefaultApplVerIDT11");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_5_0);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.setEncryptMethod(EncryptMethod.PEM_DES_MD5);
            msg.setHeartBtInt(new Integer(30));
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [DefaultApplVerID] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class LogonMsg with missing all required data.
     */
    @Test
    public void x28_testEncodeMissingAllReqT11() {
        System.out.println("-->testEncodeMissingAllReqT11");
        try {
            LogonMsg msg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_5_0);
            TestUtils.populateFIXT11HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [EncryptMethod] [HeartBtInt] [DefaultApplVerID] is missing.", ex.getMessage());
        }
    }
    
    // UTILITY METHODS

    private quickfix.fix40.Logon buildMessage40() throws Exception {
        quickfix.fix40.Logon msg = new quickfix.fix40.Logon();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setInt(quickfix.field.EncryptMethod.FIELD, 1);
        msg.setInt(quickfix.field.HeartBtInt.FIELD, 44);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 8);
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.RawData.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        
        return msg;
    }

    
    private quickfix.fix41.Logon buildMessage41() throws Exception {
        quickfix.fix41.Logon msg = new quickfix.fix41.Logon();
        TestUtils.populateQuickFIX41HeaderAll(msg);
        msg.setInt(quickfix.field.EncryptMethod.FIELD, 1);
        msg.setInt(quickfix.field.HeartBtInt.FIELD, 44);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 8);
        msg.setBoolean(quickfix.field.ResetSeqNumFlag.FIELD, true);
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.RawData.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        
        return msg;
    }
    
    private quickfix.fix42.Logon buildMessage42() throws Exception {
        quickfix.fix42.Logon msg = new quickfix.fix42.Logon();
        TestUtils.populateQuickFIX42HeaderAll(msg);
        msg.setInt(quickfix.field.EncryptMethod.FIELD, 1);
        msg.setInt(quickfix.field.HeartBtInt.FIELD, 44);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 8);
        msg.setBoolean(quickfix.field.ResetSeqNumFlag.FIELD, true);
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.RawData.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.MaxMessageSize.FIELD, 2048);
        msg.setInt(NoMsgTypes.FIELD, 1);
        quickfix.fix42.Logon.NoMsgTypes g1 = new quickfix.fix42.Logon.NoMsgTypes();
        g1.setString(quickfix.field.RefMsgType.FIELD, "B");
        g1.setChar(quickfix.field.MsgDirection.FIELD, 'R');
        msg.addGroup(g1);
        
        return msg;
    }
    
    private quickfix.fix43.Logon buildMessage43() throws Exception {
        quickfix.fix43.Logon msg = new quickfix.fix43.Logon();
        TestUtils.populateQuickFIX43HeaderAll(msg);
        msg.setInt(quickfix.field.EncryptMethod.FIELD, 1);
        msg.setInt(quickfix.field.HeartBtInt.FIELD, 44);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 8);
        msg.setBoolean(quickfix.field.ResetSeqNumFlag.FIELD, true);
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.RawData.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.MaxMessageSize.FIELD, 2048);
        msg.setInt(NoMsgTypes.FIELD, 1);
        quickfix.fix43.Logon.NoMsgTypes g1 = new quickfix.fix43.Logon.NoMsgTypes();
        g1.setString(quickfix.field.RefMsgType.FIELD, "B");
        g1.setChar(quickfix.field.MsgDirection.FIELD, 'R');
        quickfix.fix43.Logon.NoMsgTypes g2 = new quickfix.fix43.Logon.NoMsgTypes();
        g2.setString(quickfix.field.RefMsgType.FIELD, "D");
        g2.setChar(quickfix.field.MsgDirection.FIELD, 'S');
        msg.addGroup(g1);
        msg.addGroup(g2);
        msg.setBoolean(quickfix.field.TestMessageIndicator.FIELD, true);
        msg.setString(quickfix.field.Username.FIELD, "username");
        msg.setString(quickfix.field.Password.FIELD, "password");
        
        return msg;
    }
    
    private quickfix.fix44.Logon buildMessage44() throws Exception {
        quickfix.fix44.Logon msg = new quickfix.fix44.Logon();
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setInt(quickfix.field.EncryptMethod.FIELD, 1);
        msg.setInt(quickfix.field.HeartBtInt.FIELD, 44);
        msg.setInt(quickfix.field.RawDataLength.FIELD, 8);
        msg.setInt(quickfix.field.NextExpectedMsgSeqNum.FIELD, 12);
        msg.setBoolean(quickfix.field.ResetSeqNumFlag.FIELD, true);
        byte[] raw = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
            (byte) 234, (byte) 236, (byte) 238, (byte) 240};
        msg.setString(quickfix.field.RawData.FIELD, new String(raw, DEFAULT_CHARACTER_SET));
        msg.setInt(quickfix.field.MaxMessageSize.FIELD, 2048);
        msg.setInt(NoMsgTypes.FIELD, 1);
        quickfix.fix44.Logon.NoMsgTypes g1 = new quickfix.fix44.Logon.NoMsgTypes();
        g1.setString(quickfix.field.RefMsgType.FIELD, "B");
        g1.setChar(quickfix.field.MsgDirection.FIELD, 'R');
        quickfix.fix44.Logon.NoMsgTypes g2 = new quickfix.fix44.Logon.NoMsgTypes();
        g2.setString(quickfix.field.RefMsgType.FIELD, "D");
        g2.setChar(quickfix.field.MsgDirection.FIELD, 'S');
        msg.addGroup(g1);
        msg.addGroup(g2);
        msg.setBoolean(quickfix.field.TestMessageIndicator.FIELD, true);
        msg.setString(quickfix.field.Username.FIELD, "username");
        msg.setString(quickfix.field.Password.FIELD, "password");
        
        return msg;
    }

}