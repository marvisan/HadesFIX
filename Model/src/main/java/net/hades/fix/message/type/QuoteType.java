/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteType.java
 *
 * $Id: QuoteType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type of quote.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/04/2009, 10:02:54 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuoteType {

    @XmlEnumValue("0") Indicative                      (0),
    @XmlEnumValue("1") Tradeable                       (1),
    @XmlEnumValue("2") RestrictedTradeable             (2),
    @XmlEnumValue("3") Counter                         (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, QuoteType> stringToEnum = new HashMap<String, QuoteType>();

    static {
        for (QuoteType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteType */
    QuoteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
