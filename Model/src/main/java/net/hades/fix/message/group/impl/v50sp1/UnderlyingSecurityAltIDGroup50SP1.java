/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingSecurityAltIDGroup50SP1.java
 *
 * $Id: UnderlyingSecurityAltIDGroup50SP1.java,v 1.7 2010-02-25 08:37:43 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.UnderlyingSecurityAltIDGroup;

/**
 * UnderlyingSecurityAltIDGroup for FIX version 5.0SP1.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 16/12/2008, 8:46:07 PM
 */
@XmlRootElement(name="UndAID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingSecurityAltIDGroup50SP1 extends UnderlyingSecurityAltIDGroup {

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

    public UnderlyingSecurityAltIDGroup50SP1() {
    }

    public UnderlyingSecurityAltIDGroup50SP1(FragmentContext context) {
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
    public String getUnderlyingSecurityAltID() {
        return underlyingSecurityAltID;
    }

    @Override
    public void setUnderlyingSecurityAltID(String underlyingSecurityAltID) {
        this.underlyingSecurityAltID = underlyingSecurityAltID;
    }

    @XmlAttribute(name = "AltIDSrc")
    @Override
    public String getUnderlyingSecurityAltIDSource() {
        return underlyingSecurityAltIDSource;
    }

    @Override
    public void setUnderlyingSecurityAltIDSource(String underlyingSecurityAltIDSource) {
        this.underlyingSecurityAltIDSource = underlyingSecurityAltIDSource;
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
