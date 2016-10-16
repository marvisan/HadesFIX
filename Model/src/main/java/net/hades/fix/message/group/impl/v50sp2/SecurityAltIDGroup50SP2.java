/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityAltIDGroup50SP2.java
 *
 * $Id: SecurityAltIDGroup50SP2.java,v 1.4 2010-02-25 08:37:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.SecurityAltIDGroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX 5.0SP1 implementation of SecurityAltIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/01/2009, 4:32:12 PM
 */
@XmlRootElement(name = "AID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityAltIDGroup50SP2 extends SecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityAltIDGroup50SP2() {
    }

    public SecurityAltIDGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "AltID")
    @Override
    public String getSecurityAltID() {
        return securityAltID;
    }

    @Override
    public void setSecurityAltID(String securityAltID) {
        this.securityAltID = securityAltID;
    }

    @XmlAttribute(name = "AltIDSrc")
    @Override
    public String getSecurityAltIDSource() {
        return securityAltIDSource;
    }

    @Override
    public void setSecurityAltIDSource(String securityAltIDSource) {
        this.securityAltIDSource = securityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityAltIDGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
