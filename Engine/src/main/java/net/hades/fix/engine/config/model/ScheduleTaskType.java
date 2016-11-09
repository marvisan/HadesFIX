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
public enum ScheduleTaskType {

    @XmlEnumValue("start")      StartSession                ("start"),
    @XmlEnumValue("stop")       StopSession                 ("stop"),
    @XmlEnumValue("reset")      SessionReset                ("reset"),
    @XmlEnumValue("connect")    ConnectSession              ("connect"),
    @XmlEnumValue("disconnect") DisconnectSession           ("disconnect"),
    @XmlEnumValue("shutdown")   ShutdownInstance            ("shutdown");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, ScheduleTaskType> stringToEnum = new HashMap<>();

    static {
        for (ScheduleTaskType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ScheduleTaskType */
    ScheduleTaskType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ScheduleTaskType valueFor(String value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
