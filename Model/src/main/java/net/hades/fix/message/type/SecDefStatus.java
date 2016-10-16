/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecDefStatus.java
 *
 * $Id: SecDefStatus.java,v 1.3 2010-01-14 09:06:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * State of a security definition request.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/09/2009, 11:07:28 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecDefStatus {

    @XmlEnumValue("0")      PendingApproval                 (0),
    @XmlEnumValue("1")      Approved                        (1),
    @XmlEnumValue("2")      Rejected                        (2),
    @XmlEnumValue("3")      UnauthorizedRequest             (3),
    @XmlEnumValue("4")      InvalidDefinitionRequest        (4);

    private static final long serialVersionUID = -6065461480014513405L;

    private int value;

    private static final Map<String, SecDefStatus> stringToEnum = new HashMap<String, SecDefStatus>();

    static {
        for (SecDefStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecDefStatus */
    SecDefStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecDefStatus valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
