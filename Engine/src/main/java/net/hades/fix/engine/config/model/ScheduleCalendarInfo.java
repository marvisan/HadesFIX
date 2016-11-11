/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Calendar used by a scheduler.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="calendar")
@XmlType(name = "ScheduleCalendarInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ScheduleCalendarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlElementWrapper(name = "excludes", required = true)
    @XmlElementRef()
    private ScheduleDateInfo[] excludes;

    public ScheduleCalendarInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScheduleDateInfo[] getExcludes() {
        return excludes;
    }

    public void setExcludes(ScheduleDateInfo[] excludes) {
        this.excludes = excludes;
    }

}
