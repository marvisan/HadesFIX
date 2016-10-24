/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ResendGapMessagesStatusTest.java
 *
 * $Id: ResendGapMessagesStatusTest.java,v 1.3 2011-04-03 08:00:08 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.protocol.MessageHistoryCache;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.protocol.server.FixServer;
import net.hades.fix.engine.process.session.ServerSessionCoordinator;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test cases for ResendGapMessagesStatus cache.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public class ResendGapMessagesStatusTest {

    public ResendGapMessagesStatusTest() {
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
    public void testProcess() throws Exception {
        System.out.println("process");
        SessionAddress address = new SessionAddress();
        address.setLocalAddress(new CounterpartyAddress("CLIENT", null, null));
        address.setRemoteAddress(new CounterpartyAddress("SERVER", null, null));
        ServerSessionCoordinator coordinator = new ServerSessionCoordinator(null, null, null, address);
        coordinator.setName("SESS");
        Protocol protocol = new FixServer(coordinator, null);
        ResendGapMessagesStatus instance = new ResendGapMessagesStatus(protocol.getStateProcessor());
        addTestMessages(protocol.getHistoryCache());

        Status result = instance.process();
    }

    private void addTestMessages(MessageHistoryCache messageHistoryCache) throws Exception {
        for (int i = 5; i < 30; i++) {
            FIXMsg msg = FIXMsgBuilder.build(MsgType.OrderStatusRequest.getValue(), BeginString.FIX_4_0);
            msg.getHeader().setMsgSeqNum(i);
            messageHistoryCache.addMessage(msg);
        }
    }

}