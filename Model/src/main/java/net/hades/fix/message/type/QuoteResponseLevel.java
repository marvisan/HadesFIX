/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteResponseLevel.java
 *
 * $Id: QuoteResponseLevel.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Level of Response requested from receiver of quote messages.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:51:34 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuoteResponseLevel {

    @XmlEnumValue("0") NoAck                               (0),
    @XmlEnumValue("1") AckOnlyNegativeOrErroneous          (1),
    @XmlEnumValue("2") AckEachQuote                        (2),
    @XmlEnumValue("3") SummaryAck                          (3);

    private static final long serialVersionUID = -8326896911735835286L;

    private int value;

    private static final Map<String, QuoteResponseLevel> stringToEnum = new HashMap<String, QuoteResponseLevel>();

    static {
        for (QuoteResponseLevel tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteResponseLevel */
    QuoteResponseLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteResponseLevel valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
