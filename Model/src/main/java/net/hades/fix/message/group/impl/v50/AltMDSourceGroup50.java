/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AltMDSourceGroup50.java
 *
 * $Id: AltMDSourceGroup50.java,v 1.2 2010-02-04 10:11:07 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.AltMDSourceGroup;

/**
 * FIX 5.0 implementation of SecurityAltIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 02/01/2009, 4:32:12 PM
 */
@XmlRootElement(name = "Rjct")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class AltMDSourceGroup50 extends AltMDSourceGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -2313683477176915052L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AltMDSourceGroup50() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public AltMDSourceGroup50(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "AltMDSrcID")
    @Override
    public String getAltMDSourceID() {
        return altMDSourceID;
    }

    @Override
    public void setAltMDSourceID(String altMDSourceID) {
        this.altMDSourceID = altMDSourceID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AltMDSourceGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
