/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootPartyGroup50SP1.java
 *
 * $Id: RootPartyGroup50SP1.java,v 1.7 2011-04-14 23:44:52 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
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
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.group.RootPartySubGroup;

/**
 * FIX 5.0SP1 implementation of RootPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 08/04/2009, 2:35:04 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class RootPartyGroup50SP1 extends RootPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6709799934967464156L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RootPartyID.getValue(),
        TagNum.RootPartyIDSource.getValue(),
        TagNum.RootPartyRole.getValue(),
        TagNum.NoRootPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> ROOT_PARTY_SUB_GROUP_TAGS = new RootPartySubGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(ROOT_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ROOT_PARTY_SUB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RootPartyGroup50SP1() {
    }

    public RootPartyGroup50SP1(FragmentContext context) {
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
    public Integer getNoRootPartySubIDs() {
        return noRootPartySubIDs;
    }

    @Override
    public void setNoRootPartySubIDs(Integer noRootPartySubIDs) {
        this.noRootPartySubIDs = noRootPartySubIDs;
        if (noRootPartySubIDs != null) {
            rootPartySubGroups = new RootPartySubGroup[noRootPartySubIDs.intValue()];
            for (int i = 0; i < rootPartySubGroups.length; i++) {
                rootPartySubGroups[i] = new RootPartySubGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public RootPartySubGroup[] getRootPartySubGroups() {
        return rootPartySubGroups;
    }

    public void setRootPartySubGroups(RootPartySubGroup[] rootPartySubGroups) {
        this.rootPartySubGroups = rootPartySubGroups;
        if (rootPartySubGroups != null) {
            noRootPartySubIDs = new Integer(rootPartySubGroups.length);
        }
    }

    @Override
    public RootPartySubGroup addRootPartySubGroup() {

        RootPartySubGroup group = new RootPartySubGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<RootPartySubGroup> groups = new ArrayList<RootPartySubGroup>();
        if (rootPartySubGroups != null && rootPartySubGroups.length > 0) {
            groups = new ArrayList<RootPartySubGroup>(Arrays.asList(rootPartySubGroups));
        }
        groups.add(group);
        rootPartySubGroups = groups.toArray(new RootPartySubGroup[groups.size()]);
        noRootPartySubIDs = new Integer(rootPartySubGroups.length);

        return group;
    }

    @Override
    public RootPartySubGroup deleteRootPartySubGroup(int index) {

        RootPartySubGroup result = null;
        if (rootPartySubGroups != null && rootPartySubGroups.length > 0 && rootPartySubGroups.length > index) {
            List<RootPartySubGroup> groups = new ArrayList<RootPartySubGroup>(Arrays.asList(rootPartySubGroups));
            result = groups.remove(index);
            rootPartySubGroups = groups.toArray(new RootPartySubGroup[groups.size()]);
            if (rootPartySubGroups.length > 0) {
                noRootPartySubIDs = new Integer(rootPartySubGroups.length);
            } else {
                rootPartySubGroups = null;
                noRootPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearRootPartySubGroups() {

        int result = 0;
        if (rootPartySubGroups != null && rootPartySubGroups.length > 0) {
            result = rootPartySubGroups.length;
            rootPartySubGroups = null;
            noRootPartySubIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (ROOT_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRootPartySubIDs != null && noRootPartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (rootPartySubGroups == null) {
                    rootPartySubGroups = new RootPartySubGroup[noRootPartySubIDs.intValue()];
                }
                for (int i = 0; i < rootPartySubGroups.length; i++) {
                    RootPartySubGroup group = new RootPartySubGroup50SP1(context);
                    group.decode(message);
                    rootPartySubGroups[i] = group;
                }
            }
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
        return "This tag is not supported in [RootPartyGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

}
