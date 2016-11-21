/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Connection information.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlType(name = "ConnectionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ConnectionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public ConnectionInfo() {
    }
    
    @XmlAttribute(name="id")
    protected String id;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
    
    
}
