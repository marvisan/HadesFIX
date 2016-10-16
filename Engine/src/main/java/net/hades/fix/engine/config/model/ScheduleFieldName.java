/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of schedule tasks.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlType
@XmlEnum(String.class)
public enum ScheduleFieldName {

    @XmlEnumValue("cptyAddress")      CptyAddress               ("cptyAddress"),
    @XmlEnumValue("sessAddress")      SessAddress               ("sessAddress");

    private static final long serialVersionUID = 1L;

    private final String value;

    private static final Map<String, ScheduleFieldName> stringToEnum = new HashMap<>();

    static {
        for (ScheduleFieldName tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ScheduleFieldName */
    ScheduleFieldName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ScheduleFieldName valueFor(String value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
