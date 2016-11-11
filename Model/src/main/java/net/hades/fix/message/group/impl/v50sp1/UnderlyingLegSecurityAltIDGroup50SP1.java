/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingLegSecurityAltIDGroup50SP1.java
 *
 * $Id: UnderlyingLegSecurityAltIDGroup50SP1.java,v 1.2 2011-10-25 08:29:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.UnderlyingLegSecurityAltIDGroup;

/**
 * UnderlyingLegSecurityAltIDGroup for FIX version 5.0SP1.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 16/12/2008, 8:46:07 PM
 */
@XmlRootElement(name="AID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingLegSecurityAltIDGroup50SP1 extends UnderlyingLegSecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingLegSecurityAltIDGroup50SP1() {
    }

    public UnderlyingLegSecurityAltIDGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "AltID")
    @Override
    public String getUnderlyingLegSecurityAltID() {
        return underlyingLegSecurityAltID;
    }

    @Override
    public void setUnderlyingLegSecurityAltID(String underlyingLegSecurityAltID) {
        this.underlyingLegSecurityAltID = underlyingLegSecurityAltID;
    }

    @XmlAttribute(name = "AltIDSrc")
    @Override
    public String getUnderlyingLegSecurityAltIDSource() {
        return underlyingLegSecurityAltIDSource;
    }

    @Override
    public void setUnderlyingLegSecurityAltIDSource(String underlyingLegSecurityAltIDSource) {
        this.underlyingLegSecurityAltIDSource = underlyingLegSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
