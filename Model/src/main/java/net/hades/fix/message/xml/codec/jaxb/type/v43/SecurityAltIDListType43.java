/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityAltIDListType.java
 *
 * $Id: SecurityAltIDListType43.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type.v43;

import net.hades.fix.message.group.SecurityAltIDGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.Group;

/**
 * FIXML JAXB type for SecurityAltIDList group tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 26/05/2009, 12:16:17 PM
 */
@XmlRootElement(name = "SecurityAltIDList")
@XmlType(propOrder = {"noSecurityAltID", "securityAltIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityAltIDListType43 {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElement(name = "NoSecurityAltID")
    private Integer noSecurityAltID;

    @XmlElementRefs({
            @XmlElementRef(type = Group.class)
    })
    private SecurityAltIDGroup[] securityAltIDGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public Integer getNoSecurityAltID() {
        return noSecurityAltID;
    }

    public void setNoSecurityAltID(Integer noSecurityAltID) {
        this.noSecurityAltID = noSecurityAltID;
    }

    public SecurityAltIDGroup[] getSecurityAltIDGroups() {
        return securityAltIDGroups;
    }

    public void setSecurityAltIDGroups(SecurityAltIDGroup[] securityAltIDGroups) {
        this.securityAltIDGroups = securityAltIDGroups;
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
