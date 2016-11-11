/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.MsgType;

/**
 * A simple message cache used for the transmitted message history.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class MessageCache {

    private static final Logger Log = Logger.getLogger(MessageCache.class.getName());

    public static final int MAX_CACHE_SIZE = 3000;
    private static final String MESSAGE_CACHE_NAME = "fix_msg_cache.obj";

    private NavigableMap<Integer, FIXMsg> CACHE;
    private final Protocol protocol;
    private final String cacheFolder;

    public MessageCache(Protocol protocol) {
	this.protocol = protocol;
	CACHE = new ConcurrentSkipListMap<>();
	cacheFolder = protocol.getSessConfigDir();
	loadCachedMessages();
    }

    /**
     * This method adds a FIX message to the cache. If the cache is filled with <code>MAX_CACHE_SIZE</code> messages then the message with the lowest sequence
     * number (the oldest) is evicted before the new one is inserted.
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
     * This method retrieves all the messages in the given sequence number interval. If not all the messages in the gap are in the cache then only the remaining
     * messages are returned. If the gap messages are no longer in the cache null is returned.
     *
     * @param startGap start sequence number of the gap
     * @param endGap end sequence number of the gap
     * @return collection with FIX messages or null if none are found in the cache
     */
    public NavigableMap<Integer, FIXMsg> getMessages(int startGap, int endGap) {
	NavigableMap<Integer, FIXMsg> result;
	int start = startGap;
	int end = endGap;
	if (startGap > endGap) {
	    start = endGap;
	}
	// check if the newer sequence still exists
	boolean hasStart = CACHE.containsKey(start);
	boolean hasEnd = CACHE.containsKey(end);
	if (!hasStart && !hasEnd) {
	    result = new TreeMap<>();
	    if (CACHE.firstKey() > start && CACHE.lastKey() < end) {
		int firstKey = CACHE.firstKey();
		int lastKey = CACHE.lastKey();
		SequenceResetMsg gapFill = buildGapFillMsg(start);
		if (gapFill != null) {
		    gapFill.setNewSeqNo(firstKey - 1);
		    result.put(start, gapFill);
		}
		gapFill = buildGapFillMsg(lastKey + 1);
		if (gapFill != null) {
		    gapFill.setNewSeqNo(end);
		    result.put(lastKey + 1, gapFill);
		}
		result.putAll(CACHE.subMap(firstKey, true, lastKey, true));
	    } else {
		SequenceResetMsg gapFill = buildGapFillMsg(end);
		if (gapFill != null) {
		    result.put(end, gapFill);
		}
	    }
	} else if (hasStart && !hasEnd) {
	    result = CACHE.tailMap(start, true);
	    int lastKey = result.lastKey();
	    SequenceResetMsg gapFill = buildGapFillMsg(lastKey + 1);
	    if (gapFill != null) {
		gapFill.setNewSeqNo(end);
		result.put(lastKey + 1, gapFill);
	    }
	} else if (!hasStart) {
	    result = CACHE.headMap(end, true);
	    int firstKey = result.firstKey();
	    SequenceResetMsg gapFill = buildGapFillMsg(start);
	    if (gapFill != null) {
		gapFill.setNewSeqNo(firstKey - 1);
		result.put(start, gapFill);
	    }
	} else {
	    result = CACHE.subMap(start, true, end, true);
	}
	return consolidateGapFills(result);
    }

    /**
     * Save the message cache on the persistent store taht defaults to file system
     */
    public void save() {
	File cache = new File(cacheFolder + File.separator, MESSAGE_CACHE_NAME);
	ObjectOutputStream oos = null;
	if (cache.exists()) {
	    try {
		if (!cache.delete()) {
		    Log.log(Level.SEVERE, "Could not delete mesage cache file [" + MESSAGE_CACHE_NAME + "].");
		} else {
		    if (CACHE.isEmpty()) {
			Log.log(Level.SEVERE, "Mesage cache file [" + MESSAGE_CACHE_NAME + "] is empty.");
			return;
		    }
		    oos = new ObjectOutputStream(new FileOutputStream(cache));
		    oos.writeObject(CACHE);
		    oos.flush();
		}
	    } catch (IOException ex) {
		Log.log(Level.SEVERE, "Could not save mesage cache file [" + MESSAGE_CACHE_NAME + "]. Message cache is empty.", ex);
	    } finally {
		if (oos != null) {
		    try {
			oos.close();
		    } catch (IOException ex) {
			Log.log(Level.SEVERE, "Could not close message cache stream.", ex);
		    }
		}
	    }
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

    //----------------------------------------------------------------------------------------------------
    
    private void addReplaceAdminMessages(FIXMsg message) {
	message.getHeader().setPossDupFlag(Boolean.TRUE);
	if (MsgType.Heartbeat.getValue().equals(message.getHeader().getMsgType())
		|| MsgType.Logon.getValue().equals(message.getHeader().getMsgType())
		|| MsgType.Logout.getValue().equals(message.getHeader().getMsgType())
		|| MsgType.ResendRequest.getValue().equals(message.getHeader().getMsgType())
		|| MsgType.SequenceReset.getValue().equals(message.getHeader().getMsgType())
		|| MsgType.TestRequest.getValue().equals(message.getHeader().getMsgType())) {
	    // replace admin messages with a gap fill
	    SequenceResetMsg replacer = buildGapFillMsg(message.getHeader().getMsgSeqNum());
	    if (replacer == null) {
		CACHE.put(message.getHeader().getMsgSeqNum(), message);
	    } else {
		CACHE.put(replacer.getHeader().getMsgSeqNum(), replacer);
	    }
	} else {
	    CACHE.put(message.getHeader().getMsgSeqNum(), message);
	}
    }

    private SequenceResetMsg buildGapFillMsg(int seqNum) {
	try {
	    SequenceResetMsg gapFillMsg = MessageFiller.buildSequenceResetMsg(protocol, seqNum);
	    gapFillMsg.setGapFillFlag(Boolean.TRUE);
	    gapFillMsg.getHeader().setPossDupFlag(Boolean.TRUE);
	    gapFillMsg.setNewSeqNo(seqNum);
	    return gapFillMsg;
	} catch (InvalidMsgException ex) {
	    Log.log(Level.SEVERE, "Could not create a SequenceReset message", ex);
	}
	return null;
    }

    private NavigableMap<Integer, FIXMsg> consolidateGapFills(NavigableMap<Integer, FIXMsg> gapMsgs) {
	NavigableMap<Integer, FIXMsg> result = new TreeMap<>();
	SequenceResetMsg firstGap = null;
	for (Entry<Integer, FIXMsg> msg : gapMsgs.entrySet()) {
	    if (msg.getValue() instanceof SequenceResetMsg) {
		if (firstGap == null) {
		    firstGap = (SequenceResetMsg) msg.getValue();
		} else {
		    firstGap.setNewSeqNo(((SequenceResetMsg) msg.getValue()).getNewSeqNo());
		}
		// last message add it
		if (gapMsgs.lastEntry().getKey().equals(msg.getKey())) {
		    result.put(firstGap.getHeader().getMsgSeqNum(), firstGap);
		}
	    } else {
		if (firstGap != null) {
		    result.put(firstGap.getHeader().getMsgSeqNum(), firstGap);
		    firstGap = null;
		}
		result.put(msg.getKey(), msg.getValue());
	    }
	}
	return result;
    }

    private void loadCachedMessages() {
	File cache = new File(cacheFolder + File.separator, MESSAGE_CACHE_NAME);
	ObjectInputStream ois = null;
	if (cache.exists()) {
	    try {
		ois = new ObjectInputStream(new FileInputStream(cache));
		CACHE = (NavigableMap<Integer, FIXMsg>) ois.readObject();
	    } catch (IOException ex) {
		Log.log(Level.SEVERE, "Could not load mesage cache file [" + MESSAGE_CACHE_NAME + "]. Message cache is empty.", ex);
	    } catch (ClassNotFoundException ex) {
		Log.log(Level.SEVERE, "Could not load message cache file. Message cache is empty.", ex);
	    } finally {
		if (ois != null) {
		    try {
			ois.close();
		    } catch (IOException ex) {
			Log.log(Level.SEVERE, "Could not close message cache stream.", ex);
		    }
		}
	    }
	}
    }
}
