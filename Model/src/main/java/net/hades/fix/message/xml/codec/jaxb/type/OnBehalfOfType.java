/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OnBehalfOfType.java
 *
 * $Id: OnBehalfOfType.java,v 1.1 2009-07-06 03:18:50 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.jaxb.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXML JAXB type for header OnBehalfOf group tag.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 24/05/2009, 12:05:17 PM
 */
@XmlRootElement(name = "OnBehalfOf")
@XmlType(propOrder = {"compID", "subID", "locationID"})
@XmlAccessorType(XmlAccessType.NONE)
public class OnBehalfOfType {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    @XmlElement(name = "CompID")
    private String compID;

    @XmlElement(name = "SubID")
    private String subID;

    @XmlElement(name = "LocationID")
    private String locationID;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
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
