/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassStatusReqType.java
 *
 * $Id: MassStatusReqType.java,v 1.4 2011-05-09 08:21:13 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Mass Status Request Type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 9:29:51 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum MassStatusReqType {

    @XmlEnumValue("1")  SecurityOrderStatus                     (1),
    @XmlEnumValue("2")  UnderlyingSecurityOrderStatus           (2),
    @XmlEnumValue("3")  ProductOrderStatus                      (3),
    @XmlEnumValue("4")  CFICodeOrderStatus                      (4),
    @XmlEnumValue("5")  SecurityTypeOrderStatus                 (5),
    @XmlEnumValue("6")  TradingSessionOrderStatus               (6),
    @XmlEnumValue("7")  AllOrdersStatus                         (7),
    @XmlEnumValue("8")  PartyIDOrderStatus                      (8),
    @XmlEnumValue("9")  SecurityIssuerStatus                    (9),
    @XmlEnumValue("10") UnderlyingIssuerStatus                  (10);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, MassStatusReqType> stringToEnum = new HashMap<String, MassStatusReqType>();

    static {
        for (MassStatusReqType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of MassStatusReqType */
    MassStatusReqType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MassStatusReqType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
