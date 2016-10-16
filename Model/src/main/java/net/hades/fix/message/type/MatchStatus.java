/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MatchStatus.java
 *
 * $Id: MatchStatus.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The status of this trade with respect to matching or comparison.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 26/09/2009, 8:44:41 PM
 */
@XmlType
@XmlEnum(String.class)
public enum MatchStatus {

    @XmlEnumValue("0") Compared                 ('0'),
    @XmlEnumValue("1") Uncompared               ('1'),
    @XmlEnumValue("2") Advisory                 ('2');

    private static final long serialVersionUID = -3985356850302273367L;

    private char value;

    private static final Map<String, MatchStatus> stringToEnum = new HashMap<String, MatchStatus>();

    static {
        for (MatchStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MatchStatus */
    MatchStatus(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static MatchStatus valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
