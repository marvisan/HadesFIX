/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootPartyGroup50SP2.java
 *
 * $Id: RootPartyGroup50SP2.java,v 1.7 2011-04-14 23:44:32 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.RootSubParties;
import net.hades.fix.message.comp.impl.v50sp2.RootSubParties50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.group.RootPartySubGroup;
import net.hades.fix.message.type.TagNum;

/**
 * FIX 5.0SP2 implementation of RootPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 08/04/2009, 2:35:04 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class RootPartyGroup50SP2 extends RootPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6709799934967464156L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RootPartyID.getValue(),
        TagNum.RootPartyIDSource.getValue(),
        TagNum.RootPartyRole.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> ROOT_PARTY_SUB_GROUP_COMP_TAGS = new RootSubParties50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ROOT_PARTY_SUB_GROUP_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ROOT_PARTY_SUB_GROUP_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RootPartyGroup50SP2() {
    }

    public RootPartyGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getRootPartyID() {
        return rootPartyID;
    }

    @Override
    public void setRootPartyID(String rootPartyID) {
        this.rootPartyID = rootPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getRootPartyIDSource() {
        return rootPartyIDSource;
    }

    @Override
    public void setRootPartyIDSource(PartyIDSource rootPartyIDSource) {
        this.rootPartyIDSource = rootPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getRootPartyRole() {
        return rootPartyRole;
    }

    @Override
    public void setRootPartyRole(PartyRole rootPartyRole) {
        this.rootPartyRole = rootPartyRole;
    }

    @Override
    public RootSubParties getRootSubParties() {
        return rootSubParties;
    }

    @Override
    public void setRootSubParties() {
        this.rootSubParties = new RootSubParties50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearRootSubParties() {
        this.rootSubParties = null;
    }

    @XmlElementRef
    public RootPartySubGroup[] getRootPrtySubGrps() {
        return rootSubParties == null ? null : rootSubParties.getRootPartySubGroups();
    }

    public void setRootPrtySubGrps(RootPartySubGroup[] rootPrtySubGrps) {
        if (rootPrtySubGrps != null) {
            if (rootSubParties == null) {
                setRootSubParties();
            }
            ((RootSubParties50SP2) rootSubParties).setRootPartySubGroups(rootPrtySubGrps);
        }
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (ROOT_PARTY_SUB_GROUP_COMP_TAGS.contains(tag.tagNum)) {
            if (rootSubParties == null) {
                rootSubParties = new RootSubParties50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
            rootSubParties.decode(tag, message);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RootPartyGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

}
