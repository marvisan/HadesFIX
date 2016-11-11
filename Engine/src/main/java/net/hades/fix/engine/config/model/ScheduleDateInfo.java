/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Date for a scheduler.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="date")
@XmlType(name = "ScheduleDateInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ScheduleDateInfo implements Serializable {

     private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "hour")
    private String hour;

    @XmlAttribute(name = "minute")
    private String minute;

    @XmlAttribute(name = "dayOfWeek")
    private String dayOfWeek;

    @XmlAttribute(name = "dayOfMonth")
    private String dayOfMonth;

    @XmlAttribute(name = "month")
    private String month;

    @XmlAttribute(name = "year")
    private String year;

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
