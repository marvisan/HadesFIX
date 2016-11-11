/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocHandlInst.java
 *
 * $Id: AllocHandlInst.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Process handling type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 01/09/2009, 8:59:45 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocHandlInst {

    @XmlEnumValue("1") Match                (1),
    @XmlEnumValue("2") Forward              (2),
    @XmlEnumValue("3") ForwardAndMatch      (3);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocHandlInst> stringToEnum = new HashMap<String, AllocHandlInst>();

    static {
        for (AllocHandlInst tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocHandlInst */
    AllocHandlInst(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocHandlInst valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
