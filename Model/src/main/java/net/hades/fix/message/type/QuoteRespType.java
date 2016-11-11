/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRespType.java
 *
 * $Id: QuoteRespType.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Quote Response.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 22/06/2009, 3:02:41 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuoteRespType {

    @XmlEnumValue("1") HitLift          (1),
    @XmlEnumValue("2") Counter          (2),
    @XmlEnumValue("3") Expired          (3),
    @XmlEnumValue("4") Cover            (4),
    @XmlEnumValue("5") DoneAway         (5),
    @XmlEnumValue("6") Pass             (6),
    @XmlEnumValue("7") EndTrade         (7),
    @XmlEnumValue("8") TimedOut         (8);

    private static final long serialVersionUID = -4328710025360959626L;

    private int value;

    private static final Map<String, QuoteRespType> stringToEnum = new HashMap<String, QuoteRespType>();

    static {
        for (QuoteRespType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteRespType */
    QuoteRespType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteRespType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
