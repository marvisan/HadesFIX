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
 * Configuration info for a secured field in a message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="securedField")
@XmlType(name = "SecuredFieldInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class SecuredFieldInfo implements Serializable {

     private static final long serialVersionUID = 1L;

    private Integer tagNum;

    @XmlAttribute(name = "tagNum")
    public Integer getTagNum() {
        return tagNum;
    }

    public void setTagNum(Integer tagNum) {
        this.tagNum = tagNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{SecuredFieldInfo[");
        if (tagNum != null) {
            sb.append("tagNum=").append(tagNum);
        }
        sb.append("]}");

        return sb.toString();
    }
}
