/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityAltIDGroup50SP2.java
 *
 * $Id: DerivativeSecurityAltIDGroup50SP2.java,v 1.2 2011-09-22 08:54:31 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.DerivativeSecurityAltIDGroup;

/**
 * FIX 5.0SP2 implementation of DerivativeSecurityAltIDGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 02/01/2009, 4:32:12 PM
 */
@XmlRootElement(name = "AID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeSecurityAltIDGroup50SP2 extends DerivativeSecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityAltIDGroup50SP2() {
    }

    public DerivativeSecurityAltIDGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getDerivativeSecurityAltID() {
        return derivativeSecurityAltID;
    }

    @Override
    public void setDerivativeSecurityAltID(String derivativeSecurityAltID) {
        this.derivativeSecurityAltID = derivativeSecurityAltID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getDerivativeSecurityAltIDSource() {
        return derivativeSecurityAltIDSource;
    }

    @Override
    public void setDerivativeSecurityAltIDSource(String derivativeSecurityAltIDSource) {
        this.derivativeSecurityAltIDSource = derivativeSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeSecurityAltIDGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
