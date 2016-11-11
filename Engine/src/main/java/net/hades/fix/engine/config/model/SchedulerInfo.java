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
 * Scheduler instance configuration.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="scheduler")
@XmlType(name = "SchedulerInfo", propOrder = {"calendars", "tasks"})
@XmlAccessorType(XmlAccessType.NONE)
public class SchedulerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "id")
    private String id;

    @XmlElementWrapper(name = "calendars", required = true)
    @XmlElementRef()
    private ScheduleCalendarInfo[] calendars;

    @XmlElementWrapper(name = "tasks")
    @XmlElementRef()
    private ScheduleTaskInfo[] tasks;

    public SchedulerInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ScheduleCalendarInfo[] getCalendars() {
        return calendars;
    }

    public void setCalendars(ScheduleCalendarInfo[] calendars) {
        this.calendars = calendars;
    }

    public ScheduleTaskInfo[] getTasks() {
        return tasks;
    }

    public void setTasks(ScheduleTaskInfo[] tasks) {
        this.tasks = tasks;
    }
}
