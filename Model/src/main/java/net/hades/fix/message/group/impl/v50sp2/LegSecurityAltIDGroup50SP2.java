/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegSecurityAltIDGroup52.java
 *
 * $Id: LegSecurityAltIDGroup50SP2.java,v 1.3 2010-02-04 10:11:05 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.LegSecurityAltIDGroup;

/**
 * FIX 5.0SP2 implementation of LegSecurityAltIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/01/2009, 4:20:00 PM
 */
@XmlRootElement(name = "LegAID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class LegSecurityAltIDGroup50SP2 extends LegSecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -421162095883415219L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegSecurityAltIDGroup50SP2() {
    }

    public LegSecurityAltIDGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @XmlAttribute(name = "SecAltID")
    @Override
    public String getLegSecurityAltID() {
        return legSecurityAltID;
    }

    @Override
    public void setLegSecurityAltID(String legSecurityAltID) {
        this.legSecurityAltID = legSecurityAltID;
    }

    @XmlAttribute(name = "SecAltIDSrc")
    @Override
    public String getLegSecurityAltIDSource() {
        return legSecurityAltIDSource;
    }

    @Override
    public void setLegSecurityAltIDSource(String legSecurityAltIDSource) {
        this.legSecurityAltIDSource = legSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegSecurityAltIDGroup] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
