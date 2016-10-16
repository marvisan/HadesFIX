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
 * Container of all the secured fields for a FIX message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="securedMessage")
@XmlType(name = "SecuredMessageInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class SecuredMessageInfo implements Serializable {

     private static final long serialVersionUID = 1L;

    private String type;
    
    private SecuredFieldInfo[] fields;

    @XmlElementWrapper(name = "securedFields")
    @XmlElementRef()
    public SecuredFieldInfo[] getFields() {
        return fields;
    }

    public void setFields(SecuredFieldInfo[] fields) {
        this.fields = fields;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{SecuredMessageInfo[");
        if (type != null) {
            sb.append("type=").append(type);
        }
        if (fields != null && fields.length > 0) {
            sb.append(", fields=").append("\n");
            for (SecuredFieldInfo field : fields) {
                sb.append(field.toString()).append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

}
