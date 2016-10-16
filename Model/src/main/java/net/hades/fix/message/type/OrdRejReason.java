/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrdRejReason.java
 *
 * $Id: OrdRejReason.java,v 1.4 2011-01-12 11:33:59 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Reason for order rejection.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 6/07/2008, 15:25:18
 */
@XmlType
@XmlEnum(Integer.class)
public enum OrdRejReason {

    @XmlEnumValue("0") BrokerOption                        (0),
    @XmlEnumValue("1") UnknownSymbol                       (1),
    @XmlEnumValue("2") ExchangeClosed                      (2),
    @XmlEnumValue("3") OrderExceedsLimit                   (3),
    @XmlEnumValue("4") TooLateToEnter                      (4),
    @XmlEnumValue("5") UnknownOrder                        (5),
    @XmlEnumValue("6") DuplicateOrder                      (6),
    @XmlEnumValue("7") DuplicateOfVerballyCommunicated     (7),
    @XmlEnumValue("8") StaleOrder                          (8);

    private static final long serialVersionUID = -8330913901637718997L;
    
    private int value;

    private static final Map<String, OrdRejReason> stringToEnum = new HashMap<String, OrdRejReason>();

    static {
        for (OrdRejReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }
    
    /** Creates a new instance of OrdRejReason */
    OrdRejReason(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static OrdRejReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
