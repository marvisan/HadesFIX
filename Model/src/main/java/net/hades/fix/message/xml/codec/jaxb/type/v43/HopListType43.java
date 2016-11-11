/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HopListType.java
 *
 * $Id: HopListType43.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type.v43;

import net.hades.fix.message.group.HopsGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.Group;

/**
 * FIXML JAXB type for HopList group tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/05/2009, 10:17:47 AM
 */
@XmlRootElement(name = "HopList")
@XmlType(propOrder = {"noHops", "hopGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class HopListType43 {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElement(name = "NoHops")
    private Integer noHops;

    @XmlElementRefs({
            @XmlElementRef(type = Group.class)
    })
    private HopsGroup[] hopGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public HopListType43() {
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public HopsGroup[] getHopGroups() {
        return hopGroups;
    }

    public void setHopGroups(HopsGroup[] hopGroups) {
        this.hopGroups = hopGroups;
    }

    public Integer getNoHops() {
        return noHops;
    }

    public void setNoHops(Integer noHops) {
        this.noHops = noHops;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
