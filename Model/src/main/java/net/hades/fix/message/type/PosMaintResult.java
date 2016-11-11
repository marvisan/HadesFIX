/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * AdjustmentType.java
 *
 * $Id: PosMaintResult.java,v 1.1 2011-01-03 09:19:35 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Result of Position Maintenance Request.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PosMaintResult {

    @XmlEnumValue("0")  SuccessfulCompletion        (0),
    @XmlEnumValue("1")  Rejected                    (1),
    @XmlEnumValue("99") Other                       (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PosMaintResult> stringToEnum = new HashMap<String, PosMaintResult>();

    static {
        for (PosMaintResult tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AdjustmentType */
    PosMaintResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PosMaintResult valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
