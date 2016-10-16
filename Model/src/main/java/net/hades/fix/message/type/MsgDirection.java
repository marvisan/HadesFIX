/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionInst.java
 *
 * $Id: MsgDirection.java,v 1.5 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies the direction of the message from sender point of view.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 18/09/2008, 20:05:44
 */
@XmlType
@XmlEnum(String.class)
public enum MsgDirection {

    @XmlEnumValue("S")  Send                ("S"),
    @XmlEnumValue("R")  Received            ("R");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, MsgDirection> stringToEnum = new HashMap<String, MsgDirection>();

    static {
        for (MsgDirection tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of MsgDirection */
    MsgDirection(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static MsgDirection valueFor(String value) {
        return stringToEnum.get(value);
    }
}
