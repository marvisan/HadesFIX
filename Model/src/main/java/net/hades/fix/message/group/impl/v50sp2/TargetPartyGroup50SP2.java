/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TargetPartyGroup50SP2.java
 *
 * $Id: TargetPartyGroup50SP2.java,v 1.6 2010-02-04 10:11:06 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.TargetPartyGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX 5.0SP2 implementation of TargetPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="TgtPty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class TargetPartyGroup50SP2 extends TargetPartyGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6027272868300736860L;
    
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
    
    public TargetPartyGroup50SP2() {
    }
    
    public TargetPartyGroup50SP2(FragmentContext context) {
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
    
    @XmlAttribute(name = "ID")
    @Override
    public String getTargetPartyID() {
        return targetPartyID;
    }

    @Override
    public void setTargetPartyID(String targetPartyID) {
        this.targetPartyID = targetPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getTargetPartyIDSource() {
        return targetPartyIDSource;
    }

    @Override
    public void setTargetPartyIDSource(PartyIDSource targetPartyIDSource) {
        this.targetPartyIDSource = targetPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getTargetPartyRole() {
        return targetPartyRole;
    }

    @Override
    public void setTargetPartyRole(PartyRole targetPartyRole) {
        this.targetPartyRole = targetPartyRole;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TargetPartyGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
