/*
 *   Copyright (c) 2006-2014 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMsgPriorityComparator.java
 *
 * $Id$
 */
package net.hades.fix.engine.process.protocol;

import net.hades.fix.message.FIXMsg;

import java.util.Comparator;

/**
 * Comparator of priority TX queue based on message priority.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision:$
 */
public class FIXMsgPriorityComparator implements Comparator<FIXMsg> {

    @Override
    public int compare(FIXMsg o1, FIXMsg o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null && o2 != null) {
            return -1;
        }
        if (o1 != null && o2 == null) {
            return 1;
        }

        if (o1.getPriority() < o2.getPriority()) {
            return -1;
        } else if (o1.getPriority() > o2.getPriority()) {
            return 1;
        }

        return 0;
    }
}
