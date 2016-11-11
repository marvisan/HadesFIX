/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import java.util.Comparator;
import net.hades.fix.message.Message;

/**
 * Comparator of priority and sequence to force an ordering on a priority queue.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class MessagePriorityComparator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
	if (o1 == null && o2 == null) {
	    return 0;
	}
	if (o1 == null && o2 != null) {
	    return -1;
	}
	if (o1 != null && o2 == null) {
	    return 1;
	}

	int c = Integer.valueOf(o1.getPriority()).compareTo(o2.getPriority());
	if (c == 0) {
	    c = Long.valueOf(o1.getOrderSequence()).compareTo(o2.getOrderSequence());
	}
	return c;
    }
}
