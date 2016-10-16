/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ImpliedMarketIndicator.java
 *
 * $Id: ImpliedMarketIndicator.java,v 1.1 2011-04-19 12:13:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates how a market should be created.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/09/2009, 10:37:33 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ImpliedMarketIndicator {

    @XmlEnumValue("0")  NotImplied          (0),
    @XmlEnumValue("1")  ImpliedIn           (1),
    @XmlEnumValue("2")  ImpliedOut          (2),
    @XmlEnumValue("3")  BothImplied         (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, ImpliedMarketIndicator> stringToEnum = new HashMap<String, ImpliedMarketIndicator>();

    static {
        for (ImpliedMarketIndicator tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ImpliedMarketIndicator */
    ImpliedMarketIndicator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ImpliedMarketIndicator valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
