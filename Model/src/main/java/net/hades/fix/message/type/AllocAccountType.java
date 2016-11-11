/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocAccountType.java
 *
 * $Id: AllocAccountType.java,v 1.1 2011-02-06 04:44:15 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of account associated with a confirmation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocAccountType {

    @XmlEnumValue("1")  CustomerSideOfBooks                             (1),
    @XmlEnumValue("2")  NonCustomerSideOfBooks                          (2),
    @XmlEnumValue("3")  HouseTrader                                     (3),
    @XmlEnumValue("4")  FloorTrader                                     (4),
    @XmlEnumValue("6")  NonCustomerSideCrossMargined                    (6),
    @XmlEnumValue("7")  HouseTraderCrossMargined                        (7),
    @XmlEnumValue("8") 	JointBackOfficeAccount                          (8);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocAccountType> stringToEnum = new HashMap<String, AllocAccountType>();

    static {
        for (AllocAccountType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocAccountType */
    AllocAccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocAccountType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
