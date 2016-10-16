/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested4PartyGroup50SP2.java
 *
 * $Id: Nested4PartyGroup50SP2.java,v 1.2 2011-04-14 23:44:32 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested4PartyGroup;
import net.hades.fix.message.group.Nested4PartySubGroup;
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

/**
 * FIX 5.0SP2 implementation of Nested4PartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/04/2009, 3:22:58 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Nested4PartyGroup50SP2 extends Nested4PartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NSTD4_PARTY_SUB_GROUP_TAGS = new Nested4PartySubGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NSTD4_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NSTD4_PARTY_SUB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested4PartyGroup50SP2() {
    }

    public Nested4PartyGroup50SP2(FragmentContext context) {
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
    public String getNested4PartyID() {
        return nested4PartyID;
    }

    @Override
    public void setNested4PartyID(String nested4PartyID) {
        this.nested4PartyID = nested4PartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getNested4PartyIDSource() {
        return nested4PartyIDSource;
    }

    @Override
    public void setNested4PartyIDSource(PartyIDSource nested4PartyIDSource) {
        this.nested4PartyIDSource = nested4PartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getNested4PartyRole() {
        return nested4PartyRole;
    }

    @Override
    public void setNested4PartyRole(PartyRole nested4PartyRole) {
        this.nested4PartyRole = nested4PartyRole;
    }

    @Override
    public Integer getNoNested4PartySubIDs() {
        return noNested4PartySubIDs;
    }

    @Override
    public void setNoNested4PartySubIDs(Integer noNested4PartySubIDs) {
        this.noNested4PartySubIDs = noNested4PartySubIDs;
        if (noNested4PartySubIDs != null) {
            nstd4PtysSubGroups = new Nested4PartySubGroup[noNested4PartySubIDs.intValue()];
            for (int i = 0; i < nstd4PtysSubGroups.length; i++) {
                nstd4PtysSubGroups[i] = new Nested4PartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public Nested4PartySubGroup[] getNstd4PtysSubGroups() {
        return nstd4PtysSubGroups;
    }

    public void setNstd4PtysSubGroups(Nested4PartySubGroup[] nstd4PtysSubGroups) {
        this.nstd4PtysSubGroups = nstd4PtysSubGroups;
        if (nstd4PtysSubGroups != null) {
            noNested4PartySubIDs = new Integer(nstd4PtysSubGroups.length);
        }
    }

    @Override
    public Nested4PartySubGroup addNstd4PtysSubGroup() {
        Nested4PartySubGroup group = new Nested4PartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<Nested4PartySubGroup> groups = new ArrayList<Nested4PartySubGroup>();
        if (nstd4PtysSubGroups != null && nstd4PtysSubGroups.length > 0) {
            groups = new ArrayList<Nested4PartySubGroup>(Arrays.asList(nstd4PtysSubGroups));
        }
        groups.add(group);
        nstd4PtysSubGroups = groups.toArray(new Nested4PartySubGroup[groups.size()]);
        noNested4PartySubIDs = new Integer(nstd4PtysSubGroups.length);

        return group;
    }

    @Override
    public Nested4PartySubGroup deleteNstd4PtysSubGroup(int index) {
        Nested4PartySubGroup result = null;
        if (nstd4PtysSubGroups != null && nstd4PtysSubGroups.length > 0 && nstd4PtysSubGroups.length > index) {
            List<Nested4PartySubGroup> groups = new ArrayList<Nested4PartySubGroup>(Arrays.asList(nstd4PtysSubGroups));
            result = groups.remove(index);
            nstd4PtysSubGroups = groups.toArray(new Nested4PartySubGroup[groups.size()]);
            if (nstd4PtysSubGroups.length > 0) {
                noNested4PartySubIDs = new Integer(nstd4PtysSubGroups.length);
            } else {
                nstd4PtysSubGroups = null;
                noNested4PartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearNstd4PtysSubGroups() {
        int result = 0;
        if (nstd4PtysSubGroups != null && nstd4PtysSubGroups.length > 0) {
            result = nstd4PtysSubGroups.length;
            nstd4PtysSubGroups = null;
            noNested4PartySubIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NSTD4_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNested4PartySubIDs != null && noNested4PartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (nstd4PtysSubGroups == null) {
                    nstd4PtysSubGroups = new Nested4PartySubGroup[noNested4PartySubIDs.intValue()];
                }
                for (int i = 0; i < nstd4PtysSubGroups.length; i++) {
                    Nested4PartySubGroup group = new Nested4PartySubGroup50SP2(context);
                    group.decode(message);
                    nstd4PtysSubGroups[i] = group;
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
        return "This tag is not supported in [Nested4PartyGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
