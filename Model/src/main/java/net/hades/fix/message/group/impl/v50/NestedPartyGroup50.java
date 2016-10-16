/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup50.java
 *
 * $Id: NestedPartyGroup50.java,v 1.8 2011-04-14 23:44:36 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.NestedPartySubGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.TagNum;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartyGroup;

/**
 * FIX 5.0 implementation of NestedPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 06/04/2009, 3:22:58 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NestedPartyGroup50 extends NestedPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V50 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NestedPartyID.getValue(),
        TagNum.NestedPartyIDSource.getValue(),
        TagNum.NestedPartyRole.getValue(),
        TagNum.NoNestedPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NSTD_PARTY_SUB_GROUP_TAGS = new NestedPartySubGroup50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V50);
        ALL_TAGS.addAll(NSTD_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NSTD_PARTY_SUB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedPartyGroup50() {
    }

    public NestedPartyGroup50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V50;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "ID")
    @Override
    public String getNestedPartyID() {
        return nestedPartyID;
    }

    @Override
    public void setNestedPartyID(String nestedPartyID) {
        this.nestedPartyID = nestedPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getNestedPartyIDSource() {
        return nestedPartyIDSource;
    }

    @Override
    public void setNestedPartyIDSource(PartyIDSource nestedPartyIDSource) {
        this.nestedPartyIDSource = nestedPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getNestedPartyRole() {
        return nestedPartyRole;
    }

    @Override
    public void setNestedPartyRole(PartyRole nestedPartyRole) {
        this.nestedPartyRole = nestedPartyRole;
    }

    @Override
    public Integer getNoNestedPartySubIDs() {
        return noNestedPartySubIDs;
    }

    @Override
    public void setNoNestedPartySubIDs(Integer noNestedPartySubIDs) {
        this.noNestedPartySubIDs = noNestedPartySubIDs;
        if (noNestedPartySubIDs != null) {
            nstdPtysSubGroups = new NestedPartySubGroup[noNestedPartySubIDs.intValue()];
            for (int i = 0; i < nstdPtysSubGroups.length; i++) {
                nstdPtysSubGroups[i] = new NestedPartySubGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public NestedPartySubGroup[] getNstdPtysSubGroups() {
        return nstdPtysSubGroups;
    }

    public void setNstdPtysSubGroups(NestedPartySubGroup[] nstdPtysSubGroups) {
        this.nstdPtysSubGroups = nstdPtysSubGroups;
        if (nstdPtysSubGroups != null) {
            noNestedPartySubIDs = new Integer(nstdPtysSubGroups.length);
        }
    }

    @Override
    public NestedPartySubGroup addNstdPtysSubGroup() {
        
        NestedPartySubGroup group = new NestedPartySubGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<NestedPartySubGroup> groups = new ArrayList<NestedPartySubGroup>();
        if (nstdPtysSubGroups != null && nstdPtysSubGroups.length > 0) {
            groups = new ArrayList<NestedPartySubGroup>(Arrays.asList(nstdPtysSubGroups));
        }
        groups.add(group);
        nstdPtysSubGroups = groups.toArray(new NestedPartySubGroup[groups.size()]);
        noNestedPartySubIDs = new Integer(nstdPtysSubGroups.length);

        return group;
    }

    @Override
    public NestedPartySubGroup deleteNstdPtysSubGroup(int index) {

        NestedPartySubGroup result = null;
        if (nstdPtysSubGroups != null && nstdPtysSubGroups.length > 0 && nstdPtysSubGroups.length > index) {
            List<NestedPartySubGroup> groups = new ArrayList<NestedPartySubGroup>(Arrays.asList(nstdPtysSubGroups));
            result = groups.remove(index);
            nstdPtysSubGroups = groups.toArray(new NestedPartySubGroup[groups.size()]);
            if (nstdPtysSubGroups.length > 0) {
                noNestedPartySubIDs = new Integer(nstdPtysSubGroups.length);
            } else {
                nstdPtysSubGroups = null;
                noNestedPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearNstdPtysSubGroups() {

        int result = 0;
        if (nstdPtysSubGroups != null && nstdPtysSubGroups.length > 0) {
            result = nstdPtysSubGroups.length;
            nstdPtysSubGroups = null;
            noNestedPartySubIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NSTD_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNestedPartySubIDs != null && noNestedPartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (nstdPtysSubGroups == null) {
                    nstdPtysSubGroups = new NestedPartySubGroup[noNestedPartySubIDs.intValue()];
                }
                for (int i = 0; i < nstdPtysSubGroups.length; i++) {
                    NestedPartySubGroup group = new NestedPartySubGroup50(context);
                    group.decode(message);
                    nstdPtysSubGroups[i] = group;
                }
            }
        }
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
        return "This tag is not supported in [NestedPartyGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
