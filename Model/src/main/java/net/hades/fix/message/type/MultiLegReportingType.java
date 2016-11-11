/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MultiLegReportingType.java
 *
 * $Id: MultilegReportingType.java,v 1.7 2011-02-16 11:36:00 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicate type of multi leg report.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 7/07/2008, 20:28:29
 */
@XmlType
@XmlEnum(String.class)
public enum MultiLegReportingType {

    @XmlEnumValue("1") SingleSecurity                              ("1"),
    @XmlEnumValue("2") IndivLegOfAMultiLegSecurity                 ("2"),
    @XmlEnumValue("3") MultiLegSecurity                            ("3");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, MultiLegReportingType> stringToEnum = new HashMap<String, MultiLegReportingType>();

    static {
        for (MultiLegReportingType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of MultiLegReportingType */
    MultiLegReportingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MultiLegReportingType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
