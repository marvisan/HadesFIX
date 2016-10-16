/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PutOrCall.java
 *
 * $Id: PutOrCall.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Put or Call indicator.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 6/07/2008, 16:44:57
 */
@XmlType
@XmlEnum(Integer.class)
public enum PutOrCall {

    @XmlEnumValue("0") Put         (0),
    @XmlEnumValue("1") Call        (1);

    private static final long serialVersionUID = -3438648112367105129L;
    
    private int value;

    private static final Map<String, PutOrCall> stringToEnum = new HashMap<String, PutOrCall>();

    static {
        for (PutOrCall tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PutOrCall */
    PutOrCall(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static PutOrCall valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
