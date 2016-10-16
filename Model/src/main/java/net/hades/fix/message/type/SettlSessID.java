/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlSessID.java
 *
 * $Id: SettlSessID.java,v 1.1 2011-01-03 09:19:35 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies a specific settlement session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/06/2009, 11:04:42 AM
 */
@XmlType
@XmlEnum(String.class)
public enum SettlSessID {

    @XmlEnumValue("ITD") Intraday                           ("ITD"),
    @XmlEnumValue("RTH") RegularTradingHours                ("RTH"),
    @XmlEnumValue("ETH") ElectronicTradingHours             ("ETH"),
    @XmlEnumValue("EOD") EndOfDay                           ("EOD");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, SettlSessID> stringToEnum = new HashMap<String, SettlSessID>();

    static {
        for (SettlSessID tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of SettlSessID */
    SettlSessID(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SettlSessID valueFor(String value) {
        return stringToEnum.get(value);
    }
}
