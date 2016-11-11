/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TickRuleType.java
 *
 * $Id: TickRuleType.java,v 1.1 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of tick rule.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 30/08/2009, 12:35:04 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TickRuleType {

    @XmlEnumValue("0") Regular                  (0),
    @XmlEnumValue("1") Variable                 (1),
    @XmlEnumValue("2") Fixed                    (2),
    @XmlEnumValue("3") TradedAsSpreadLeg        (3),
    @XmlEnumValue("4") Settledasspreadleg       (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TickRuleType> stringToEnum = new HashMap<String, TickRuleType>();

    static {
        for (TickRuleType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of TickRuleType */
    TickRuleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TickRuleType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
