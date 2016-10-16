/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MessageUtil.java
 *
 * $Id: MessageUtil.java,v 1.3 2010-11-22 10:43:14 vrotaru Exp $
 */
package net.hades.fix.engine.util;

import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.type.MsgType;

/**
 * Utilities used in FIX message processing.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public class MessageUtil {

    private MessageUtil() {
    }

    /**
     * Utility method that check id a FIX message is an admin message.
     * @param msg FIX message
     * @return true if it is an admin message
     */
    public static boolean isAdminMessage(FIXMsg msg) {
        if (msg == null) {
            throw new IllegalArgumentException("Given message parameter cannot be null.");
        }

        boolean result = false;
        if (MsgType.TestRequest.getValue().equals(msg.getHeader().getMsgType()) ||
            MsgType.ResendRequest.getValue().equals(msg.getHeader().getMsgType()) ||
            MsgType.Heartbeat.getValue().equals(msg.getHeader().getMsgType()) ||
            MsgType.Reject.getValue().equals(msg.getHeader().getMsgType()) ||
            MsgType.SequenceReset.getValue().equals(msg.getHeader().getMsgType()) ||
            MsgType.ResendRequest.getValue().equals(msg.getHeader().getMsgType()) ||
            MsgType.Logout.getValue().equals(msg.getHeader().getMsgType()) ||
            MsgType.Logon.getValue().equals(msg.getHeader().getMsgType())) {
            result = true;
        }

        return result;
    }

    /**
     * Gets the deliver to address.
     * @param msg message
     * @return counterparty address
     */
    public static CounterpartyAddress getDeliverToAddress(FIXMsg msg) {
       return new CounterpartyAddress(msg.getHeader().getDeliverToCompID(),
               msg.getHeader().getDeliverToSubID(),
               msg.getHeader().getDeliverToLocationID());
    }

    /**
     * Checks if the message is a Heartbeat or TestRequest message.
     * @param fixMsg message
     * @return true if session is idle
     */
    public static boolean isIdleSessionMessage(FIXMsg fixMsg) {
        boolean result = false;
        if (fixMsg != null) {
            String msgType = fixMsg.getHeader().getMsgType();
            if (msgType.equals(MsgType.Heartbeat.getValue()) || msgType.equals(MsgType.TestRequest.getValue())) {
                result = true;
            }
        }

        return result;
    }
}
