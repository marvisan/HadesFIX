/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InternalInfo.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.config.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Internal information used by the administration GUI.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
@XmlRootElement(name="internalInfo")
@XmlType(name = "InternalInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class InternalInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlAttribute(name="nextConnInfoId", required = true)
    private Integer nextConnInfoId;
    
    public InternalInfo() {
        nextConnInfoId = new Integer(0);
    }

    public Integer getNextConnInfoId() {
        return nextConnInfoId;
    }

    public void setNextConnInfoId(Integer nextConnInfoId) {
        this.nextConnInfoId = nextConnInfoId;
    }

    
    
}
