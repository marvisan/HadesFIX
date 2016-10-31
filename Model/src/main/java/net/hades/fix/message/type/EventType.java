/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EventType.java
 *
 * $Id: EventType.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Type of events.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 06/12/2008, 11:54:54 AM
 */
public enum EventType {

    Put                         (1),
    Call                        (2),
    Tender                      (3),
    SinkingFundCall             (4),
    Activation                  (5),
    Inactiviation               (6),
    LastEligibleTradeDate       (7),
    SwapStartDate               (8),
    SwapEndDate                 (9),
    SwapRollDate                (10),
    SwapNextStartDate           (11),
    SwapNextRollDate            (12),
    FirstDeliveryDate           (13),
    LastDeliveryDate            (14),
    InitialInventoryDueDate     (15),
    FinalInventoryDueDate       (16),
    FirstIntentDate             (17),
    LastIntentDate              (18),
    PositionRemovalDate         (19),
    Other                       (99);

    private static final long serialVersionUID = -4281413057008505745L;

    private int value;

    private static final Map<String, EventType> stringToEnum = new HashMap<String, EventType>();

    static {
        for (EventType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of EventType */
    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EventType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
