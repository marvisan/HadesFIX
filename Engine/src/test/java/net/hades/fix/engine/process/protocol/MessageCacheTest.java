/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import java.util.Iterator;
import java.util.NavigableMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;


public class MessageCacheTest {

    private Protocol protocol;

    public MessageCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
	protocol = mock(Protocol.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddRandomMessage() throws Exception {
	System.out.println("testAddMessage");
	MessageCache instance = new MessageCache(protocol);
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
	MessageCache instance = new MessageCache(protocol);
	for (int i = 0; i < MessageCache.MAX_CACHE_SIZE; i++) {
	    FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
	    msg.getHeader().setMsgSeqNum(i);
	    instance.addMessage(msg);
	}
	assertEquals(MessageCache.MAX_CACHE_SIZE, instance.size());
	FIXMsg msg = FIXMsgBuilder.build(MsgType.Reject.getValue(), BeginString.FIX_4_0);
	msg.getHeader().setMsgSeqNum(MessageCache.MAX_CACHE_SIZE);
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
	MessageCache instance = new MessageCache(protocol);
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
	MessageCache instance = new MessageCache(protocol);
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
	MessageCache instance = new MessageCache(protocol);
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
	MessageCache instance = new MessageCache(protocol);
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
	MessageCache instance = new MessageCache(protocol);
	for (int i = 10; i < 20; i++) {
	    FIXMsg msg = FIXMsgBuilder.build(MsgType.Logout.getValue(), BeginString.FIX_4_0);
	    msg.getHeader().setMsgSeqNum(i);
	    instance.addMessage(msg);
	}
	NavigableMap<Integer, FIXMsg> result = instance.getMessages(2, 8);
	assertNull(result);
    }

}
