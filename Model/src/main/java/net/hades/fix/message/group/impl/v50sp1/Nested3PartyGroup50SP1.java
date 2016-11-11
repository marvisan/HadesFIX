/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested3PartyGroup50SP1.java
 *
 * $Id: Nested3PartyGroup50SP1.java,v 1.2 2011-04-14 23:44:53 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.Nested3PartyGroup;
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
import net.hades.fix.message.group.Nested3PartySubGroup;

/**
 * FIX 5.0SP1 implementation of Nested2PartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/04/2009, 3:22:58 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Nested3PartyGroup50SP1 extends Nested3PartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NSTD3_PARTY_SUB_GROUP_TAGS = new Nested3PartySubGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NSTD3_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NSTD3_PARTY_SUB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested3PartyGroup50SP1() {
    }

    public Nested3PartyGroup50SP1(FragmentContext context) {
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
    public String getNested3PartyID() {
        return nested3PartyID;
    }

    @Override
    public void setNested3PartyID(String nested3PartyID) {
        this.nested3PartyID = nested3PartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getNested3PartyIDSource() {
        return nested3PartyIDSource;
    }

    @Override
    public void setNested3PartyIDSource(PartyIDSource nested3PartyIDSource) {
        this.nested3PartyIDSource = nested3PartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getNested3PartyRole() {
        return nested3PartyRole;
    }

    @Override
    public void setNested3PartyRole(PartyRole nested3PartyRole) {
        this.nested3PartyRole = nested3PartyRole;
    }

    @Override
    public Integer getNoNested3PartySubIDs() {
        return noNested3PartySubIDs;
    }

    @Override
    public void setNoNested3PartySubIDs(Integer noNested3PartySubIDs) {
        this.noNested3PartySubIDs = noNested3PartySubIDs;
        if (noNested3PartySubIDs != null) {
            nstd3PtysSubGroups = new Nested3PartySubGroup[noNested3PartySubIDs.intValue()];
            for (int i = 0; i < nstd3PtysSubGroups.length; i++) {
                nstd3PtysSubGroups[i] = new Nested3PartySubGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public Nested3PartySubGroup[] getNstd3PtysSubGroups() {
        return nstd3PtysSubGroups;
    }

    public void setNstd3PtysSubGroups(Nested3PartySubGroup[] nstd3PtysSubGroups) {
        this.nstd3PtysSubGroups = nstd3PtysSubGroups;
        if (nstd3PtysSubGroups != null) {
            noNested3PartySubIDs = new Integer(nstd3PtysSubGroups.length);
        }
    }

    @Override
    public Nested3PartySubGroup addNstd3PtysSubGroup() {
        Nested3PartySubGroup group = new Nested3PartySubGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<Nested3PartySubGroup> groups = new ArrayList<Nested3PartySubGroup>();
        if (nstd3PtysSubGroups != null && nstd3PtysSubGroups.length > 0) {
            groups = new ArrayList<Nested3PartySubGroup>(Arrays.asList(nstd3PtysSubGroups));
        }
        groups.add(group);
        nstd3PtysSubGroups = groups.toArray(new Nested3PartySubGroup[groups.size()]);
        noNested3PartySubIDs = new Integer(nstd3PtysSubGroups.length);

        return group;
    }

    @Override
    public Nested3PartySubGroup deleteNstd3PtysSubGroup(int index) {
        Nested3PartySubGroup result = null;
        if (nstd3PtysSubGroups != null && nstd3PtysSubGroups.length > 0 && nstd3PtysSubGroups.length > index) {
            List<Nested3PartySubGroup> groups = new ArrayList<Nested3PartySubGroup>(Arrays.asList(nstd3PtysSubGroups));
            result = groups.remove(index);
            nstd3PtysSubGroups = groups.toArray(new Nested3PartySubGroup[groups.size()]);
            if (nstd3PtysSubGroups.length > 0) {
                noNested3PartySubIDs = new Integer(nstd3PtysSubGroups.length);
            } else {
                nstd3PtysSubGroups = null;
                noNested3PartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearNstd3PtysSubGroups() {
        int result = 0;
        if (nstd3PtysSubGroups != null && nstd3PtysSubGroups.length > 0) {
            result = nstd3PtysSubGroups.length;
            nstd3PtysSubGroups = null;
            noNested3PartySubIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NSTD3_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNested3PartySubIDs != null && noNested3PartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (nstd3PtysSubGroups == null) {
                    nstd3PtysSubGroups = new Nested3PartySubGroup[noNested3PartySubIDs.intValue()];
                }
                for (int i = 0; i < nstd3PtysSubGroups.length; i++) {
                    Nested3PartySubGroup group = new Nested3PartySubGroup50SP1(context);
                    group.decode(message);
                    nstd3PtysSubGroups[i] = group;
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
        return "This tag is not supported in [Nested3PartyGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
