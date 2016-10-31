/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MsgUtilTest.java
 *
 * $Id: MsgUtilTest.java,v 1.5 2010-04-03 07:02:28 vrotaru Exp $
 */
package net.hades.fix.message.util;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.BeginString;

/**
 * Test suite for MsgUtil class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 17/08/2008, 17:40:32
 */
public class MsgUtilTest {

    public MsgUtilTest() {
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
     * Test of calculateChecksum method, of class MsgUtil.
     */
    @Test
    public void testCalculateChecksum_String() {
    }

    /**
     * Test of calculateChecksum method, of class MsgUtil.
     */
    @Test
    public void testCalculateChecksum_charArr() {
    }

    /**
     * Test of getTagValue method, of class MsgUtil.
     */
    @Test
    public void testGetTagValue_TagNum_String() {
    }

    /**
     * Test of getTagValue method, of class MsgUtil.
     */
    @Test
    public void testGetTagValue_3args() {
    }

    /**
     * Test of getTagValueErrorMissing method, of class MsgUtil.
     */
    @Test
    public void testGetTagValueErrorMissing() {
    }

    /**
     * Test of getNextTagNum method, of class MsgUtil.
     */
    @Test
    public void a0_testGetNextTagNum() {
        try {
            quickfix.fix40.Heartbeat msg = new quickfix.fix40.Heartbeat();
            TestUtils.populateQuicFIX40HeaderReq(msg);
            System.out.println("msg-->" + msg.toString());
            ByteBuffer message = ByteBuffer.wrap(msg.toString().getBytes());
            int actual = MsgUtil.getNextTagNum(message);
            assertEquals(TagNum.BeginString.getValue(), actual);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
    
    /**
     * Test of getNextTag method, of class MsgUtil.
     * @throws Exception 
     */
    @Test
    public void a2_testGetNextTag() throws Exception {
        quickfix.fix40.Heartbeat msg = new quickfix.fix40.Heartbeat();
        TestUtils.populateQuicFIX40HeaderReq(msg);
        System.out.println("msg-->" + msg.toString());
        ByteBuffer message = ByteBuffer.wrap(msg.toString().getBytes());
        Tag actual = MsgUtil.getNextTag(message);
        assertEquals(TagNum.BeginString.getValue(), actual.tagNum);
        assertEquals("FIX.4.0", new String(actual.value, "UTF-8"));
    }
    
    /**
     * Test of getNextTag method, of class MsgUtil unknown message.
     * @throws Exception 
     */
    @Test
    public void a4_testGetNextTag_unknown_message() throws Exception {
        quickfix.fix40.Heartbeat msg = new quickfix.fix40.Heartbeat();
        TestUtils.populateQuicFIX40HeaderReq(msg);
        String msgStr = msg.toString();
        msgStr = msgStr.replaceAll("[8][=]", "9999=");
        System.out.println("msg-->" + msgStr);
        ByteBuffer message = ByteBuffer.wrap(msgStr.getBytes());
        Tag actual = MsgUtil.getNextTag(message);
        assertNotNull(actual);
        assertEquals(9999, actual.tagNum);
    }
    
    /**
     * Test of getNextTag method, of class MsgUtil with a binary data value.
     * @throws Exception 
     */
    @Test
    public void a6_testGetNextTagLength() throws Exception {
        quickfix.fix40.Heartbeat msg = new quickfix.fix40.Heartbeat();
        TestUtils.populateQuicFIX40HeaderReq(msg);
        System.out.println("msg-->" + msg.toString());
        ByteBuffer message = ByteBuffer.wrap(msg.toString().getBytes());
        Tag actual = MsgUtil.getNextTag(message, 7);
        assertEquals(TagNum.BeginString.getValue(), actual.tagNum);
        assertEquals(BeginString.FIX_4_0.getValue(), new String(actual.value, "UTF-8"));
    }
    
    /**
     * Test of getNextTag method, of class MsgUtil with a binary data value.
     */
    @Test
    public void a8_testInsertByteArray() {
        byte[] old = new byte[] {'A','B','C','D','E','F','G','H','I','J','K','L','M'};
        ByteBuffer oldMsg = ByteBuffer.wrap(old);
        oldMsg.position(5);
        byte[] insert = new byte[] {'1','2','3','4','5'};
        ByteBuffer actual = MsgUtil.insertByteArray(insert, oldMsg);
        System.out.println("actual-->" + new String(actual.array()));
        assertEquals(new String(new byte[] {'A','B','C','D','E','1','2','3','4','5','F','G','H','I','J','K','L','M'}), 
                new String(actual.array()));
        assertEquals(5, actual.position());
    }
        

    @Test
    public void b1_testCompareBeginString_lower() {
        int actual = MsgUtil.compare(BeginString.FIX_4_1, BeginString.FIX_4_2);
        assertEquals(-1, actual);
    }

    @Test
    public void b2_testCompareBeginString_equals() {
        int actual = MsgUtil.compare(BeginString.FIX_4_2, BeginString.FIX_4_2);
        assertEquals(0, actual);
    }

    @Test
    public void b3_testCompareBeginString_higher() {
        int actual = MsgUtil.compare(BeginString.FIX_4_4, BeginString.FIX_4_2);
        assertEquals(1, actual);
    }

    @Test
    public void c1_testCompareApplVerID_lower() {
        int actual = MsgUtil.compare(ApplVerID.FIX40, ApplVerID.FIX43);
        assertEquals(-1, actual);
    }

    @Test
    public void c2_testCompareApplVerID_equal() {
        int actual = MsgUtil.compare(ApplVerID.FIX43, ApplVerID.FIX43);
        assertEquals(0, actual);
    }

    @Test
    public void c3_testCompareApplVerID_higher() {
        int actual = MsgUtil.compare(ApplVerID.FIX44, ApplVerID.FIX43);
        assertEquals(1, actual);
    }

    @Test
    public void c4_testGetBeginStringFromString() {
        BeginString actual = MsgUtil.getBeginStringFromString("4.3");
        assertNotNull(actual);
        assertEquals(BeginString.FIX_4_3, actual);
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
       
    /**
     * Test of getNextTag method, of class MsgUtil unknown tag.
     */
    @Test
    public void x1_testGetNextTag_bad_position() {
        try {
            quickfix.fix40.Heartbeat msg = new quickfix.fix40.Heartbeat();
            TestUtils.populateQuicFIX40HeaderReq(msg);
            System.out.println("msg-->" + msg.toString());
            ByteBuffer message = ByteBuffer.wrap(msg.toString().getBytes("UTF-8"));
            message.get();
            MsgUtil.getNextTag(message);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertTrue(ex.getMessage().startsWith("Wrong message tag format received or bad index position"));
        }
    }

    /**
     * Test of getNextTag method, of class MsgUtil bad pointer.
     */
    @Test
    public void x2_testGetNextTag_bad_format() {
        try {
            quickfix.fix40.Heartbeat msg = new quickfix.fix40.Heartbeat();
            TestUtils.populateQuicFIX40HeaderReq(msg);
            System.out.println("msg-->" + msg.toString());
            ByteBuffer message = ByteBuffer.wrap(msg.toString().getBytes());
            message.get();
            message.get();
            MsgUtil.getNextTag(message);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertTrue(ex.getMessage().startsWith("Wrong message tag format received or bad index position"));
        }
    }
}
