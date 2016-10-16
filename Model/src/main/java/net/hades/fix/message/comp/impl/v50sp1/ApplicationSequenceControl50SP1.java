/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ApplicationSequenceControl50SP1.java
 *
 * $Id: ApplicationSequenceControl50SP1.java,v 1.6 2010-02-04 10:11:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.ApplicationSequenceControl;

/**
 * FIX 5.0SP1 implementation of ApplicationSequenceControl component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 11/02/2009, 7:16:52 PM
 */
@XmlRootElement(name="ApplSeqCtrl")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ApplicationSequenceControl50SP1 extends ApplicationSequenceControl {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7372134504689561661L;

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

    public ApplicationSequenceControl50SP1() {
        super();
    }

    public ApplicationSequenceControl50SP1(FragmentContext context) {
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

    @XmlAttribute(name = "ApplID")
    @Override
    public String getApplID() {
        return applID;
    }

    @Override
    public void setApplID(String applID) {
        this.applID = applID;
    }

    @XmlAttribute(name = "ApplSeqNum")
    @Override
    public Integer getApplSeqNum() {
        return applSeqNum;
    }

    @Override
    public void setApplSeqNum(Integer applSeqNum) {
        this.applSeqNum = applSeqNum;
    }

    @XmlAttribute(name = "ApplLastSeqNum")
    @Override
    public Integer getApplLastSeqNum() {
        return applLastSeqNum;
    }

    @Override
    public void setApplLastSeqNum(Integer applLastSeqNum) {
        this.applLastSeqNum = applLastSeqNum;
    }

    @XmlAttribute(name = "ApplResendFlag")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getApplResendFlag() {
        return applResendFlag;
    }

    @Override
    public void setApplResendFlag(Boolean applResendFlag) {
        this.applResendFlag = applResendFlag;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ApplicationSequenceControl] component version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
