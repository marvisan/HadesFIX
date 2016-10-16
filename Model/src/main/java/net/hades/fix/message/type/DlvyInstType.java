/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DlvyInstType.java
 *
 * $Id: DlvyInstType.java,v 1.1 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of delivery instruction.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum DlvyInstType {

    @XmlEnumValue("C") Cash                 ('C'),
    @XmlEnumValue("S") Securities           ('S');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, DlvyInstType> stringToEnum = new HashMap<String, DlvyInstType>();

    static {
        for (DlvyInstType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DlvyInstType */
    DlvyInstType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static DlvyInstType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
