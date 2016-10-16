/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecType.java
 *
 * $Id: ExecType.java,v 1.4 2011-01-12 11:33:59 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of order execution.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 6/07/2008, 16:08:02
 */
@XmlType
@XmlEnum(String.class)
public enum ExecType {

    @XmlEnumValue("0") New                 ("0"),
    @XmlEnumValue("1") PartialFill         ("1"),
    @XmlEnumValue("2") Fill                ("2"),
    @XmlEnumValue("3") DoneForDay          ("3"),
    @XmlEnumValue("4") Canceled            ("4"),
    @XmlEnumValue("5") Replace             ("5"),
    @XmlEnumValue("6") PendingCancel       ("6"),
    @XmlEnumValue("7") Stopped             ("7"),
    @XmlEnumValue("8") Rejected            ("8"),
    @XmlEnumValue("9") Suspended           ("9"),
    @XmlEnumValue("A") PendingNew          ("A"),
    @XmlEnumValue("B") Calculated          ("B"),
    @XmlEnumValue("C") Expired             ("C"),
    @XmlEnumValue("D") Restated            ("D"), // (ExecutionRpt sent unsolicited by sellside, with ExecRestatementReason set)
    @XmlEnumValue("E") PendingReplace      ("E"); // (e.g. result of Order Cancel/Replace Request)

    private static final long serialVersionUID = -5735229708174999054L;

    private String value;

    private static final Map<String, ExecType> stringToEnum = new HashMap<String, ExecType>();

    static {
        for (ExecType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of ExecType */
    ExecType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static ExecType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
