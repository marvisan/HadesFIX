/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CxlRejResponseTo.java
 *
 * $Id: CxlRejResponseTo.java,v 1.5 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of request that a cancel replace request is in response to.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 7/07/2008, 20:17:16
 */
@XmlType
@XmlEnum(Integer.class)
public enum CxlRejResponseTo {

    @XmlEnumValue("1") OrderCancelRequest          (1),
    @XmlEnumValue("2") OrderCancelReplaceRequest   (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, CxlRejResponseTo> stringToEnum = new HashMap<String, CxlRejResponseTo>();

    static {
        for (CxlRejResponseTo tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }
    
    /** Creates a new instance of CxlRejResponseTo */
    CxlRejResponseTo(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static CxlRejResponseTo valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
