/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;
import javax.xml.bind.annotation.*;


/**
 * Container of all the secured fields for a FIX message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name="listener")
@XmlType(name = "ListenerInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ListenerInfo implements Serializable {

     private static final long serialVersionUID = 1L;

    @XmlAttribute(name="className")
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
