/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RateSource.java
 *
 * $Id: RateSource.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the source of rate information.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/06/2009, 8:29:03 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum RateSource {

    @XmlEnumValue("0")  Bloomberg           (0),
    @XmlEnumValue("1")  Reuters             (1),
    @XmlEnumValue("2")  Telerate            (2),
    @XmlEnumValue("99") Call                (99);

    private static final long serialVersionUID = -6470797483230441709L;

    private int value;

    private static final Map<String, RateSource> stringToEnum = new HashMap<String, RateSource>();

    static {
        for (RateSource tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RateSource */
    RateSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RateSource valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
