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
 * Schedule task parameter data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="taskParam")
@XmlType(name = "ScheduleTaskParamInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ScheduleTaskParamInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name="name", required = true)
    private ScheduleFieldName name;

    @XmlAttribute(name="value", required = true)
    private String value;

    public ScheduleTaskParamInfo() {
    }

    public ScheduleTaskParamInfo(ScheduleFieldName name, String value) {
        this.name = name;
        this.value = value;
    }

    public ScheduleFieldName getName() {
        return name;
    }

    public void setName(ScheduleFieldName name) {
        this.name = name;
    }
   
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ScheduleTaskParamInfo[");
        if (name != null) {
            sb.append("name=").append(name);
        }
        if (value != null) {
            sb.append(",").append("value=").append(value);
        }
        sb.append("]}");
        return sb.toString();
    }

}
