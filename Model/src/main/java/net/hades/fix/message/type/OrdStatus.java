/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrdStatus.java
 *
 * $Id: OrdStatus.java,v 1.5 2011-01-12 11:33:59 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * State of the order.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 1/07/2008, 20:15:54
 */
@XmlType
@XmlEnum(String.class)
public enum OrdStatus {

    @XmlEnumValue("0") New                         ("0"),
    @XmlEnumValue("1") PartiallyFilled             ("1"),
    @XmlEnumValue("2") Filled                      ("2"),
    @XmlEnumValue("3") DoneForDay                  ("3"),
    @XmlEnumValue("4") Canceled                    ("4"),
    @XmlEnumValue("5") Replaced                    ("5"),
    @XmlEnumValue("6") PendingCancel               ("6"),
    @XmlEnumValue("7") Stopped                     ("7"),
    @XmlEnumValue("8") Rejected                    ("8"),
    @XmlEnumValue("9") Suspended                   ("9"),
    @XmlEnumValue("A") PendingNew                  ("A"),
    @XmlEnumValue("B") Calculated                  ("B"),
    @XmlEnumValue("C") Expired                     ("C"),
    @XmlEnumValue("D") AcceptedForBidding          ("D"),
    @XmlEnumValue("E") PendingReplace              ("E");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, OrdStatus> stringToEnum = new HashMap<String, OrdStatus>();

    static {
        for (OrdStatus tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of OrdStatus */
    OrdStatus(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static OrdStatus valueFor(String value) {
        return stringToEnum.get(value);
    }
}
