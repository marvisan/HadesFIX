/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosReqResult.java
 *
 * $Id: PosReqResult.java,v 1.1 2011-01-03 09:19:35 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Result of Request for Position.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/10/2009, 2:50:40 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PosReqResult {

    @XmlEnumValue("0")  ValidRequest                            (0),
    @XmlEnumValue("1")  UnsupportedRequest                      (1),
    @XmlEnumValue("2")  NoPositionsFound                        (2),
    @XmlEnumValue("3")  NotAuthorized                           (3),
    @XmlEnumValue("4")  RequestForPositionNotSupported          (4),
    @XmlEnumValue("99") BackoutMessage                          (99);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PosReqResult> stringToEnum = new HashMap<String, PosReqResult>();

    static {
        for (PosReqResult tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PosReqResult */
    PosReqResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PosReqResult valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
