/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCapacity.java
 *
 * $Id: OrderCapacity.java,v 1.4 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The capacity of the Firm placing the order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 7/07/2008, 20:40:07
 */
@XmlType
@XmlEnum(String.class)
public enum OrderCapacity {

    @XmlEnumValue("A") Agency                      ('A'),
    @XmlEnumValue("G") Proprietary                 ('G'),
    @XmlEnumValue("I") Individual                  ('I'),
    @XmlEnumValue("P") Principal                   ('P'),
    @XmlEnumValue("R") RisklessPrincipal           ('R'),
    @XmlEnumValue("W") AgentForAnotherMember       ('W');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, OrderCapacity> stringToEnum = new HashMap<String, OrderCapacity>();

    static {
        for (OrderCapacity tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OrderCapacity */
    OrderCapacity(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
    
    public static OrderCapacity valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
