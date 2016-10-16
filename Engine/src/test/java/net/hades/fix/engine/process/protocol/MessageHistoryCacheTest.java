/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MessageHistoryCacheTest.java
 *
 * $Id: MessageHistoryCacheTest.java,v 1.4 2011-03-19 08:14:23 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 *Test cases for message history cache.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 11/07/2009, 2:02:47 PM
 */
public class MessageHistoryCacheTest {

    public MessageHistoryCacheTest() {
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
    public void testAddRandomMessage() throws Exception {
        System.out.println("testAddMessage");
        MessageHistoryCache instance = new MessageHistoryCache();
        FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(0);
        instance.addMessage(msg);
        msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(5);
        instance.addMessage(msg);
        msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(6);
        instance.addMessage(msg);
        msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(1);
        instance.addMessage(msg);
        msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(4);
        instance.addMessage(msg);
        msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(2);
        instance.addMessage(msg);
        msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(3);
        instance.addMessage(msg);

        assertNotNull(instance.getMessages(2, 5));
        NavigableMap<Integer, FIXMsg> res = instance.getMessages(2, 5);
        for (FIXMsg key : res.values()) {
            System.out.println("seq=" + key.getHeader().getMsgSeqNum());
        }
    }

    @Test
    public void testAddMessage() throws Exception {
        System.out.println("testAddMessage");
        MessageHistoryCache instance = new MessageHistoryCache();
        for (int i = 0; i < MessageHistoryCache.MAX_CACHE_SIZE; i++) {
            FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
            msg.getHeader().setMsgSeqNum(i);
            instance.addMessage(msg);
        }
        assertEquals(MessageHistoryCache.MAX_CACHE_SIZE, instance.size());
        FIXMsg msg = FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_0);
        msg.getHeader().setMsgSeqNum(MessageHistoryCache.MAX_CACHE_SIZE);
        instance.addMessage(msg);
        assertNotNull(instance.getMessages(1, 1));
        NavigableMap<Integer, FIXMsg> res = instance.getMessages(1, 1);
        assertEquals(1, res.firstEntry().getValue().getHeader().getMsgSeqNum().intValue());
        NavigableMap<Integer, FIXMsg> res1000 = instance.getMessages(1000, 1000);
        assertEquals(1000, res1000.firstEntry().getValue().getHeader().getMsgSeqNum().intValue());
    }

    @Test
    public void testGetMessages_FullSeq() throws Exception {
        System.out.println("testGetMessages_FullSeq");
        MessageHistoryCache instance = new MessageHistoryCache();
        for (int i = 0; i < 10; i++) {
            FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
            msg.getHeader().setMsgSeqNum(i);
            instance.addMessage(msg);
        }

        NavigableMap<Integer, FIXMsg> result = instance.getMessages(3, 6);
        assertEquals(4, result.size());
        Iterator<FIXMsg> it = result.values().iterator();
        for (int i = 3; i < 7; i++) {
            assertEquals(i, it.next().getHeader().getMsgSeqNum().intValue());
        }
    }

    @Test
    public void testGetMessages_SwapParam() throws Exception {
        System.out.println("testGetMessages_SwapParam");
        MessageHistoryCache instance = new MessageHistoryCache();
        for (int i = 0; i < 10; i++) {
            FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
            msg.getHeader().setMsgSeqNum(i);
            instance.addMessage(msg);
        }

        NavigableMap<Integer, FIXMsg> result = instance.getMessages(6, 3);
        assertEquals(4, result.size());
        Iterator<FIXMsg> it = result.values().iterator();
        for (int i = 3; i < 7; i++) {
            assertEquals(i, it.next().getHeader().getMsgSeqNum().intValue());
        }
    }

    @Test
    public void testGetMessages_PartialLow() throws Exception {
        System.out.println("testGetMessages_PartialLow");
        MessageHistoryCache instance = new MessageHistoryCache();
        for (int i = 10; i < 20; i++) {
            FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
            msg.getHeader().setMsgSeqNum(i);
            instance.addMessage(msg);
        }

        NavigableMap<Integer, FIXMsg> result = instance.getMessages(5, 13);
        assertEquals(4, result.size());
        Iterator<FIXMsg> it = result.values().iterator();
        for (int i = 10; i < 14; i++) {
            assertEquals(i, it.next().getHeader().getMsgSeqNum().intValue());
        }
    }

    @Test
    public void testGetMessages_PartialHigh() throws Exception {
        System.out.println("testGetMessages_PartialHigh");
        MessageHistoryCache instance = new MessageHistoryCache();
        for (int i = 10; i < 20; i++) {
            FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
            msg.getHeader().setMsgSeqNum(i);
            instance.addMessage(msg);
        }

        NavigableMap<Integer, FIXMsg> result = instance.getMessages(16, 25);
        assertEquals(4, result.size());
        Iterator<FIXMsg> it = result.values().iterator();
        for (int i = 16; i < 20; i++) {
            assertEquals(i, it.next().getHeader().getMsgSeqNum().intValue());
        }
    }

    @Test
    public void testGetMessages_None() throws Exception {
        System.out.println("testGetMessages_None");
        MessageHistoryCache instance = new MessageHistoryCache();
        for (int i = 10; i < 20; i++) {
            FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
            msg.getHeader().setMsgSeqNum(i);
            instance.addMessage(msg);
        }
        NavigableMap<Integer, FIXMsg> result = instance.getMessages(2, 8);
        assertNull(result);
    }

    @Test
    public void testGetConsequentMessages_Normal() throws Exception {
        System.out.println("testGetConsequentMessages_Normal");
        MessageHistoryCache instance = new MessageHistoryCache();
        FIXMsg msg1 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg1.getHeader().setMsgSeqNum(10);
        instance.addMessage(msg1);
        FIXMsg msg2 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg2.getHeader().setMsgSeqNum(11);
        instance.addMessage(msg2);
        FIXMsg msg3 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg3.getHeader().setMsgSeqNum(17);
        instance.addMessage(msg3);
        FIXMsg msg4 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg4.getHeader().setMsgSeqNum(18);
        instance.addMessage(msg4);
        FIXMsg msg5 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg5.getHeader().setMsgSeqNum(19);
        instance.addMessage(msg5);

        Collection<FIXMsg> result = instance.getConsequentMessages(10, 20);
        assertNotNull(result);
        assertTrue(result.size() == 2);
        FIXMsg[] msgs = result.toArray(new FIXMsg[0]);
        assertEquals(msgs[0].getHeader().getMsgSeqNum().intValue(), 10);
        assertEquals(msgs[1].getHeader().getMsgSeqNum().intValue(), 11);
    }

    @Test
    public void testGetConsequentMessages_None() throws Exception {
        System.out.println("testGetConsequentMessages_None");
        MessageHistoryCache instance = new MessageHistoryCache();
        FIXMsg msg1 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg1.getHeader().setMsgSeqNum(13);
        instance.addMessage(msg1);
        FIXMsg msg2 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg2.getHeader().setMsgSeqNum(14);
        instance.addMessage(msg2);
        FIXMsg msg3 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg3.getHeader().setMsgSeqNum(17);
        instance.addMessage(msg3);
        FIXMsg msg4 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg4.getHeader().setMsgSeqNum(18);
        instance.addMessage(msg4);
        FIXMsg msg5 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg5.getHeader().setMsgSeqNum(19);
        instance.addMessage(msg5);

        Collection<FIXMsg> result = instance.getConsequentMessages(10, 20);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetLastConsequentMessages_Normal() throws Exception {
        System.out.println("testGetLastConsequentMessages_Normal");
        MessageHistoryCache instance = new MessageHistoryCache();
        FIXMsg msg1 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg1.getHeader().setMsgSeqNum(10);
        instance.addMessage(msg1);
        FIXMsg msg2 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg2.getHeader().setMsgSeqNum(11);
        instance.addMessage(msg2);
        FIXMsg msg3 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg3.getHeader().setMsgSeqNum(17);
        instance.addMessage(msg3);
        FIXMsg msg4 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg4.getHeader().setMsgSeqNum(18);
        instance.addMessage(msg4);
        FIXMsg msg5 = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
        msg5.getHeader().setMsgSeqNum(19);
        instance.addMessage(msg5);

        Collection<FIXMsg> result = instance.getLastConsequentMessages();
        assertNotNull(result);
        assertTrue(result.size() == 3);
        FIXMsg[] msgs = result.toArray(new FIXMsg[0]);
        assertEquals(msgs[0].getHeader().getMsgSeqNum().intValue(), 19);
        assertEquals(msgs[1].getHeader().getMsgSeqNum().intValue(), 18);
        assertEquals(msgs[2].getHeader().getMsgSeqNum().intValue(), 17);
    }
}