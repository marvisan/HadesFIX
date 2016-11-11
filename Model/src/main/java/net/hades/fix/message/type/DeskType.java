/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TimeUnit.java
 *
 * $Id: DeskType.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Measure units symbols for time.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/11/2008, 8:21:20 PM
 */
@XmlType
@XmlEnum(String.class)
public enum DeskType {

    @XmlEnumValue("A")      Agency                  ("A"),
    @XmlEnumValue("AR")     Arbitrage               ("AR"),
    @XmlEnumValue("D")      Derivatives             ("D"),
    @XmlEnumValue("IN")     International           ("IN"),
    @XmlEnumValue("IS")     Institutional           ("IS"),
    @XmlEnumValue("O")      Other                   ("O"),
    @XmlEnumValue("PF")     PreferredTrading        ("PF"),
    @XmlEnumValue("PR")     Proprietary             ("PR"),
    @XmlEnumValue("PT")     ProgramTrading          ("PT"),
    @XmlEnumValue("S")      Sales                   ("S"),
    @XmlEnumValue("T")      Trading                 ("T");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, DeskType> stringToEnum = new HashMap<String, DeskType>();

    static {
        for (DeskType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of TimeUnit. */
    DeskType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeskType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
