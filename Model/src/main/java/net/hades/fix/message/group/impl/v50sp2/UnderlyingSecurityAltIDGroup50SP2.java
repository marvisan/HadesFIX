/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingSecurityAltIDGroup50SP2.java
 *
 * $Id: UnderlyingSecurityAltIDGroup50SP2.java,v 1.6 2010-02-04 10:11:06 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

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
 * UnderlyingSecurityAltIDGroup for FIX version 5.0SP2.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 16/12/2008, 8:46:07 PM
 */
@XmlRootElement(name="UndAID")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingSecurityAltIDGroup50SP2 extends UnderlyingSecurityAltIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -8528993641022284773L;

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

    public UnderlyingSecurityAltIDGroup50SP2() {
    }

    public UnderlyingSecurityAltIDGroup50SP2(FragmentContext context) {
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
