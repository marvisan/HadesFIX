/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradePublishIndicator.java
 *
 * $Id: TradePublishIndicator.java,v 1.1 2011-10-13 07:18:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of of acknowledgment being sent.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/09/2009, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TradePublishIndicator {

    @XmlEnumValue("0") DoNotPublish                 (0),
    @XmlEnumValue("1") Publish                      (1),
    @XmlEnumValue("2") DeferredPublish              (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TradePublishIndicator> stringToEnum = new HashMap<String, TradePublishIndicator>();

    static {
        for (TradePublishIndicator tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TradePublishIndicator */
    TradePublishIndicator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TradePublishIndicator valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
