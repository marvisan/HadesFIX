/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlPartyGroup50.java
 *
 * $Id: SettlPartyGroup50.java,v 1.2 2011-04-14 23:44:35 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SettlPartyGroup;
import net.hades.fix.message.group.SettlPartySubGroup;
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

/**
 * FIX 5.0 implementation of SettlPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SettlPartyGroup50 extends SettlPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> SETTL_PARTY_SUB_GROUP_TAGS = new SettlPartySubGroup50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(SETTL_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SETTL_PARTY_SUB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlPartyGroup50() {
    }

    public SettlPartyGroup50(FragmentContext context) {
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
    public String getSettlPartyID() {
        return settlPartyID;
    }
    @Override
    public void setSettlPartyID(String settlPartyID) {
        this.settlPartyID = settlPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getSettlPartyIDSource() {
        return settlPartyIDSource;
    }

    @Override
    public void setSettlPartyIDSource(PartyIDSource settlPartyIDSource) {
        this.settlPartyIDSource = settlPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getSettlPartyRole() {
        return settlPartyRole;
    }

    @Override
    public void setSettlPartyRole(PartyRole settlPartyRole) {
        this.settlPartyRole = settlPartyRole;
    }

    @Override
    public Integer getNoSettlPartySubIDs() {
        return noSettlPartySubIDs;
    }

    @Override
    public void setNoSettlPartySubIDs(Integer noSettlPartySubIDs) {
        this.noSettlPartySubIDs = noSettlPartySubIDs;
        if (noSettlPartySubIDs != null) {
            settlPartySubIDGroups = new SettlPartySubGroup[noSettlPartySubIDs.intValue()];
            for (int i = 0; i < settlPartySubIDGroups.length; i++) {
                settlPartySubIDGroups[i] = new SettlPartySubGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SettlPartySubGroup[] getSettlPartySubIDGroups() {
        return settlPartySubIDGroups;
    }

    public void setSettlPartySubIDGroups(SettlPartySubGroup[] settlPartySubIDGroups) {
        this.settlPartySubIDGroups = settlPartySubIDGroups;
        if (settlPartySubIDGroups != null) {
            noSettlPartySubIDs = new Integer(settlPartySubIDGroups.length);
        }
    }

    @Override
    public SettlPartySubGroup addSettlPartySubIDGroup() {
        SettlPartySubGroup group = new SettlPartySubGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SettlPartySubGroup> groups = new ArrayList<SettlPartySubGroup>();
        if (settlPartySubIDGroups != null && settlPartySubIDGroups.length > 0) {
            groups = new ArrayList<SettlPartySubGroup>(Arrays.asList(settlPartySubIDGroups));
        }
        groups.add(group);
        settlPartySubIDGroups = groups.toArray(new SettlPartySubGroup[groups.size()]);
        noSettlPartySubIDs = new Integer(settlPartySubIDGroups.length);

        return group;
    }

    @Override
    public SettlPartySubGroup deleteSettlPartySubIDGroup(int index) {
        SettlPartySubGroup result = null;
        if (settlPartySubIDGroups != null && settlPartySubIDGroups.length > 0 && settlPartySubIDGroups.length > index) {
            List<SettlPartySubGroup> groups = new ArrayList<SettlPartySubGroup>(Arrays.asList(settlPartySubIDGroups));
            result = groups.remove(index);
            settlPartySubIDGroups = groups.toArray(new SettlPartySubGroup[groups.size()]);
            if (settlPartySubIDGroups.length > 0) {
                noSettlPartySubIDs = new Integer(settlPartySubIDGroups.length);
            } else {
                settlPartySubIDGroups = null;
                noSettlPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearSettlPartySubIDGroup() {
        int result = 0;
        if (settlPartySubIDGroups != null && settlPartySubIDGroups.length > 0) {
            result = settlPartySubIDGroups.length;
            settlPartySubIDGroups = null;
            noSettlPartySubIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (SETTL_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSettlPartySubIDs != null && noSettlPartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (settlPartySubIDGroups == null) {
                    settlPartySubIDGroups = new SettlPartySubGroup[noSettlPartySubIDs.intValue()];
                }
                for (int i = 0; i < settlPartySubIDGroups.length; i++) {
                    SettlPartySubGroup group = new SettlPartySubGroup50(context);
                    group.decode(message);
                    settlPartySubIDGroups[i] = group;
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
        return "This tag is not supported in [SettlPartyGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
