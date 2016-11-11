/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecAckStatus.java
 *
 * $Id: AssignmentMethod.java,v 1.1 2011-10-21 10:31:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The status of this execution acknowledgment message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/11/2012, 7:25:57 PM
 */
@XmlType
@XmlEnum(String.class)
public enum ExecAckStatus {

    @XmlEnumValue("0") Received                  ('0'),
    @XmlEnumValue("1") Accepted                  ('1'),
    @XmlEnumValue("2") Rejected                  ('2');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, ExecAckStatus> stringToEnum = new HashMap<String, ExecAckStatus>();

    static {
        for (ExecAckStatus tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ExecAckStatus */
    ExecAckStatus(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static ExecAckStatus valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
