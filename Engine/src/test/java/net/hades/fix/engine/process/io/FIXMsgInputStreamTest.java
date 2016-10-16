/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMsgInputStreamTest.java
 *
 * $Id: FIXMsgInputStreamTest.java,v 1.2 2010-06-06 07:59:16 vrotaru Exp $
 */
package net.hades.fix.engine.process.io;

import java.io.ByteArrayInputStream;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EncryptMethod;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for Configurator class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 5/05/2010, 11:18:16 AM
 */
public class FIXMsgInputStreamTest {

    public FIXMsgInputStreamTest() {
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

    @Test
    public void testReadMessage() throws Exception {
        LogonMsg fixMsg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
        fixMsg.getHeader().setSenderCompID("MARV");
        fixMsg.getHeader().setTargetCompID("NYSE");
        fixMsg.getHeader().setMsgSeqNum(new Integer(1));
        fixMsg.getHeader().setSendingTime(new Date());
        fixMsg.setEncryptMethod(EncryptMethod.DES);
        fixMsg.setHeartBtInt(new Integer(30));
        fixMsg.setUsername("username");
        fixMsg.setRawData(new byte[] {56, 56, 57, 58, 66, 60});
        byte[] expected = fixMsg.encode();
        
        System.out.println("expected=" + new String(expected));

        ByteArrayInputStream bis = new ByteArrayInputStream(expected);
        FIXMsgInputStream instance = new FIXMsgInputStream(bis);
        byte[] actual = instance.readMessage();

        System.out.println("actual=" + new String(actual));

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testReadMessage_2Msg() throws Exception {
        LogonMsg fixMsg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
        fixMsg.getHeader().setSenderCompID("MARV");
        fixMsg.getHeader().setTargetCompID("NYSE");
        fixMsg.getHeader().setMsgSeqNum(new Integer(1));
        fixMsg.getHeader().setSendingTime(new Date());
        fixMsg.setEncryptMethod(EncryptMethod.DES);
        fixMsg.setHeartBtInt(new Integer(30));
        fixMsg.setUsername("username");
        fixMsg.setRawData(new byte[] {56, 56, 57, 58, 66, 60});
        byte[] fixMsgArr = fixMsg.encode();
        byte[] expected = new byte[2 * fixMsgArr.length];
        System.arraycopy(fixMsgArr, 0, expected, 0, fixMsgArr.length);
        System.arraycopy(fixMsgArr, 0, expected, fixMsgArr.length, fixMsgArr.length);

        System.out.println("expected=" + new String(expected));

        ByteArrayInputStream bis = new ByteArrayInputStream(expected);
        FIXMsgInputStream instance = new FIXMsgInputStream(bis);
        byte[] actual = instance.readMessage();
        System.out.println("actual1=" + new String(actual));
        actual = instance.readMessage();
        System.out.println("actual2=" + new String(actual));
    }

    @Test
    public void testReadMessage_Long() throws Exception {
        LogonMsg fixMsg = (LogonMsg) FIXMsgBuilder.build(MsgType.Logon.getValue(), BeginString.FIX_4_4);
        fixMsg.getHeader().setSenderCompID("MARV");
        fixMsg.getHeader().setTargetCompID("NYSE");
        fixMsg.getHeader().setMsgSeqNum(new Integer(1));
        fixMsg.getHeader().setSendingTime(new Date());
        fixMsg.setEncryptMethod(EncryptMethod.DES);
        fixMsg.setHeartBtInt(new Integer(30));
        fixMsg.setUsername("username");
        byte[] rawData = new byte[1500];
        for (int i = 0; i < rawData.length; i++) {
            rawData[i] = 56;
        }
        fixMsg.setRawData(rawData);
        byte[] expected = fixMsg.encode();

        System.out.println("expected=" + new String(expected));

        ByteArrayInputStream bis = new ByteArrayInputStream(expected);
        FIXMsgInputStream instance = new FIXMsgInputStream(bis);
        byte[] actual = instance.readMessage();
        System.out.println("actual=" + new String(actual));
    }
}