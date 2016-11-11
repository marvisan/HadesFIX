/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDepth.java
 *
 * $Id: MarketDepth.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of market depth.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/11/2009, 8:31:08 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MarketDepth {

    @XmlEnumValue("0")      FullBookDepth               (0),
    @XmlEnumValue("1")      TopOfBook                   (1);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MarketDepth> stringToEnum = new HashMap<String, MarketDepth>();

    static {
        for (MarketDepth tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MarketDepth */
    MarketDepth(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MarketDepth valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
