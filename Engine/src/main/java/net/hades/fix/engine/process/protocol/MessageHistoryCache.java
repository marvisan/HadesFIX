/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MessageHistoryCache.java
 *
 * $Id: MessageHistoryCache.java,v 1.7 2011-03-22 11:53:52 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.util.MsgUtil;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple message history cache.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 */
public class MessageHistoryCache {

    private static final Logger LOGGER = Logger.getLogger(MessageHistoryCache.class.getName());

    public static final int MAX_CACHE_SIZE = 1000;

    private final NavigableMap<Integer, FIXMsg> CACHE = new ConcurrentSkipListMap<Integer, FIXMsg>();

    public MessageHistoryCache() {
    }

    /**
     * This method adds a FIX message to the history cache. If the cache is filled with <code>MAX_CACHE_SIZE</code>
     * messages then the message with the lowest sequence number (the oldest) is evicted before the new one is inserted.
     *
     * @param message message to be added to the history cache
     */
    public void addMessage(FIXMsg message) {
        if (message == null) {
            throw new IllegalArgumentException("The message to be added cannot be null.");
        }
        if (CACHE.size() >= MAX_CACHE_SIZE) {
            CACHE.pollFirstEntry();
        }
        addReplaceAdminMessages(message);
    }

    /**
     *  This method adds a FIX message to the history cache if it doesn't exists.
     * 
     * @param message message message to be added to the history cache
     */
    public void addMessageSafe(FIXMsg message) {
        if (message == null) {
            throw new IllegalArgumentException("The message to be added cannot be null.");
        }
        if (CACHE.get(message.getHeader().getMsgSeqNum()) == null) {
            if (CACHE.size() >= MAX_CACHE_SIZE) {
                CACHE.pollFirstEntry();
            }
            addReplaceAdminMessages(message);
        }
    }

    /**
     * This method retrieves all the messages in the given sequence number interval. If not all the
     * messages in the gap are in the cache then only the remaining messages are returned. If the gap
     * messages are no longer in the cache null is returned.
     *
     * @param startGap start sequence number of the gap
     * @param endGap end sequence number of the gap
     * @return collection with FIX messages or null if none are found in the cache
     */
    public NavigableMap<Integer, FIXMsg> getMessages(int startGap, int endGap) {
        NavigableMap<Integer, FIXMsg> result;
        int start = startGap;
        int end =  endGap;
        if (startGap > endGap) {
            start = endGap;
            end = startGap;
        }
        // check if the newer sequence still message exists
        boolean hasStart = CACHE.containsKey(start);
        boolean hasEnd = CACHE.containsKey(end);
        if (!hasStart && !hasEnd) {
            result = null;
        } else if (hasStart && !hasEnd) {
            result = CACHE.tailMap(start, true);
        } else if (!hasStart) {
            result = CACHE.headMap(end, true);
        } else {
            result = CACHE.subMap(start, true, end, true);
        }

        return result;
    }

    /**
     * Get in sequence consequent message in the given gap.
     *
     * @param startGap gap begin inclusive
     * @param endGap gap end inclusive
     * @return collection with consequent messages or empty array if none
     */
    public List<FIXMsg> getConsequentMessages(int startGap, int endGap) {
        List<FIXMsg> result = new ArrayList<FIXMsg>();
        SortedMap<Integer,FIXMsg> gap = CACHE.subMap(startGap, true, endGap, true);
        if (gap.containsKey(startGap)) {
            for (int i = startGap; i <= endGap; i++) {
                if (gap.containsKey(i)) {
                    result.add(CACHE.get(i));
                } else {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Returns the last group of consequent messages.
     *
     * @return list with the last group of consequent messages.
     */
    public Deque<FIXMsg> getLastConsequentMessages() {
        Deque<FIXMsg> result = new LinkedList<FIXMsg>();
        NavigableSet<Integer> descKeys = CACHE.descendingKeySet();
        int prevKey = -1;
        for (int key : descKeys) {
            if (prevKey == -1) {
                prevKey = key;
                result.add(CACHE.get(key));
            } else {
                if (Math.abs(key - prevKey) == 1) {
                    result.add(CACHE.get(key));
                    prevKey = key;
                } else {
                    break;
                }
            }
        }

        return result;
    }

    public void clearMessages(int first, int last) {
        for (int i = first; i <= last; i++) {
            CACHE.remove(i);
        }
    }

    /**
     * Returns the current size of the messages in the cache.
     * 
     * @return no of messages in the cache
     */
    public int size() {
        return CACHE.size();
    }

    /**
     * Remove all the entry from the cache.
     */
    public void clear() {
        CACHE.clear();
    }

    private void addReplaceAdminMessages(FIXMsg message) {
        message.getHeader().setOrigSendingTime(new Date());
        if (MsgType.Heartbeat.getValue().equals(message.getHeader().getMsgType()) ||
            MsgType.Logon.getValue().equals(message.getHeader().getMsgType()) ||
            MsgType.Logout.getValue().equals(message.getHeader().getMsgType()) ||
            MsgType.ResendRequest.getValue().equals(message.getHeader().getMsgType()) ||
            MsgType.SequenceReset.getValue().equals(message.getHeader().getMsgType()) ||
            MsgType.TestRequest.getValue().equals(message.getHeader().getMsgType())) {
            // replace admin messages with a gap fill
            SequenceResetMsg replacer;
            try {
                if (MsgUtil.compare(message.getHeader().getBeginString(), BeginString.FIXT_1_1) >= 0) {
                    replacer = (SequenceResetMsg) FIXMsgBuilder.build(MsgType.SequenceReset.getValue(),
                            message.getHeader().getBeginString(), message.getHeader().getApplVerID());

                } else {
                    replacer = (SequenceResetMsg) FIXMsgBuilder.build(MsgType.SequenceReset.getValue(),
                            message.getHeader().getBeginString());
                }
                replacer.getHeader().copyFrom(message.getHeader());
                replacer.getHeader().setMsgType(MsgType.SequenceReset.getValue());
                replacer.setGapFillFlag(Boolean.TRUE);
                replacer.setNewSeqNo(message.getHeader().getMsgSeqNum());
                CACHE.put(replacer.getHeader().getMsgSeqNum(), replacer);
            } catch (InvalidMsgException ex) {
                LOGGER.log(Level.SEVERE, "Could not create a SequenceReset message. Adding the original message. Error was : {0}", ex.toString());
                CACHE.put(message.getHeader().getMsgSeqNum(), message);
            }
        } else {
            CACHE.put(message.getHeader().getMsgSeqNum(), message);
        }
    }
}
