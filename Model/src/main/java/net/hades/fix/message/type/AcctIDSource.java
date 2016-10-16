/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AcctIDSource.java
 *
 * $Id: AcctIDSource.java,v 1.4 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Used to identify the source of the {@link TagNum#Account} (1) code.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AcctIDSource {

    @XmlEnumValue("1")  BIC                         (1),
    @XmlEnumValue("2")  SID                         (2),
    @XmlEnumValue("3")  TFM                         (3),
    @XmlEnumValue("4")  OMGEO                       (4),
    @XmlEnumValue("5")  DTCC                        (5),
    @XmlEnumValue("99") Other                       (99);

    private static final long serialVersionUID = -8506968018943639285L;

    private int value;

    private static final Map<String, AcctIDSource> stringToEnum = new HashMap<String, AcctIDSource>();

    static {
        for (AcctIDSource tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AcctIDSource */
    AcctIDSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AcctIDSource valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
