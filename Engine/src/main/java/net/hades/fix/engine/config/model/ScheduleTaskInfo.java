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
 * Schedule task data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="task")
@XmlType(name = "ScheduleTaskInfo", propOrder = {"date", "taskParams", "calendars"})
@XmlAccessorType(XmlAccessType.NONE)
public class ScheduleTaskInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "taskType", required = true)
    private ScheduleTaskType taskType;

    @XmlElementRef()
    private ScheduleDateInfo date;

    @XmlElementWrapper(name = "taskParams")
    @XmlElementRef()
    private ScheduleTaskParamInfo[] taskParams;

    @XmlElementWrapper(name = "calendars")
    @XmlElementRef()
    private CalendarRefInfo[] calendars;

    public ScheduleTaskInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScheduleTaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(ScheduleTaskType taskType) {
        this.taskType = taskType;
    }

    public ScheduleTaskParamInfo[] getTaskParams() {
        return taskParams;
    }

    public void setTaskParams(ScheduleTaskParamInfo[] taskParams) {
        this.taskParams = taskParams;
    }

    public ScheduleDateInfo getDate() {
        return date;
    }

    public void setDate(ScheduleDateInfo date) {
        this.date = date;
    }

    public CalendarRefInfo[] getCalendars() {
        return calendars;
    }

    public void setCalendars(CalendarRefInfo[] calendars) {
        this.calendars = calendars;
    }

    public String getParameter(ScheduleFieldName name) {
        String result = null;
        if (taskParams != null && taskParams.length > 0) {
            for (ScheduleTaskParamInfo parameter : taskParams) {
                if (parameter.getName().equals(name)) {
                    result = parameter.getValue();
                    break;
                }
            }
        }
        return result;
    }
}
