/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested2PartyGroup50SP2.java
 *
 * $Id: Nested2PartyGroup50SP2.java,v 1.2 2011-04-14 23:44:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.Nested2PartyGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

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
import net.hades.fix.message.group.Nested2PartySubGroup;

/**
 * FIX 5.0SP2 implementation of Nested2PartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/04/2009, 3:22:58 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Nested2PartyGroup50SP2 extends Nested2PartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NSTD2_PARTY_SUB_GROUP_TAGS = new Nested2PartySubGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NSTD2_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NSTD2_PARTY_SUB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested2PartyGroup50SP2() {
    }

    public Nested2PartyGroup50SP2(FragmentContext context) {
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
    public String getNested2PartyID() {
        return nested2PartyID;
    }

    @Override
    public void setNested2PartyID(String nested2PartyID) {
        this.nested2PartyID = nested2PartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getNested2PartyIDSource() {
        return nested2PartyIDSource;
    }

    @Override
    public void setNested2PartyIDSource(PartyIDSource nested2PartyIDSource) {
        this.nested2PartyIDSource = nested2PartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getNested2PartyRole() {
        return nested2PartyRole;
    }

    @Override
    public void setNested2PartyRole(PartyRole nested2PartyRole) {
        this.nested2PartyRole = nested2PartyRole;
    }

    @Override
    public Integer getNoNested2PartySubIDs() {
        return noNested2PartySubIDs;
    }

    @Override
    public void setNoNested2PartySubIDs(Integer noNested2PartySubIDs) {
        this.noNested2PartySubIDs = noNested2PartySubIDs;
        if (noNested2PartySubIDs != null) {
            nstd2PtysSubGroups = new Nested2PartySubGroup[noNested2PartySubIDs.intValue()];
            for (int i = 0; i < nstd2PtysSubGroups.length; i++) {
                nstd2PtysSubGroups[i] = new Nested2PartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public Nested2PartySubGroup[] getNstd2PtysSubGroups() {
        return nstd2PtysSubGroups;
    }

    public void setNstd2PtysSubGroups(Nested2PartySubGroup[] nstd2PtysSubGroups) {
        this.nstd2PtysSubGroups = nstd2PtysSubGroups;
        if (nstd2PtysSubGroups != null) {
            noNested2PartySubIDs = new Integer(nstd2PtysSubGroups.length);
        }
    }

    @Override
    public Nested2PartySubGroup addNstd2PtysSubGroup() {    
        Nested2PartySubGroup group = new Nested2PartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<Nested2PartySubGroup> groups = new ArrayList<Nested2PartySubGroup>();
        if (nstd2PtysSubGroups != null && nstd2PtysSubGroups.length > 0) {
            groups = new ArrayList<Nested2PartySubGroup>(Arrays.asList(nstd2PtysSubGroups));
        }
        groups.add(group);
        nstd2PtysSubGroups = groups.toArray(new Nested2PartySubGroup[groups.size()]);
        noNested2PartySubIDs = new Integer(nstd2PtysSubGroups.length);

        return group;
    }

    @Override
    public Nested2PartySubGroup deleteNstd2PtysSubGroup(int index) {
        Nested2PartySubGroup result = null;
        if (nstd2PtysSubGroups != null && nstd2PtysSubGroups.length > 0 && nstd2PtysSubGroups.length > index) {
            List<Nested2PartySubGroup> groups = new ArrayList<Nested2PartySubGroup>(Arrays.asList(nstd2PtysSubGroups));
            result = groups.remove(index);
            nstd2PtysSubGroups = groups.toArray(new Nested2PartySubGroup[groups.size()]);
            if (nstd2PtysSubGroups.length > 0) {
                noNested2PartySubIDs = new Integer(nstd2PtysSubGroups.length);
            } else {
                nstd2PtysSubGroups = null;
                noNested2PartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearNstd2PtysSubGroups() {
        int result = 0;
        if (nstd2PtysSubGroups != null && nstd2PtysSubGroups.length > 0) {
            result = nstd2PtysSubGroups.length;
            nstd2PtysSubGroups = null;
            noNested2PartySubIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NSTD2_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNested2PartySubIDs != null && noNested2PartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (nstd2PtysSubGroups == null) {
                    nstd2PtysSubGroups = new Nested2PartySubGroup[noNested2PartySubIDs.intValue()];
                }
                for (int i = 0; i < nstd2PtysSubGroups.length; i++) {
                    Nested2PartySubGroup group = new Nested2PartySubGroup50SP2(context);
                    group.decode(message);
                    nstd2PtysSubGroups[i] = group;
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
        return "This tag is not supported in [Nested2PartyGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
