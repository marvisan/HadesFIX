/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartyGroup50SP2.java
 *
 * $Id: PartyGroup50SP2.java,v 1.9 2011-04-14 23:44:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PartySubGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.nio.ByteBuffer;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX 5.0SP2 implementation of PartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class PartyGroup50SP2 extends PartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> PARTY_SUB_ID_GROUP_TAGS = new PartySubGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTY_SUB_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTY_SUB_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PartyGroup50SP2() {
    }

    public PartyGroup50SP2(FragmentContext context) {
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
    public String getPartyID() {
        return partyID;
    }

    @Override
    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getPartyIDSource() {
        return partyIDSource;
    }

    @Override
    public void setPartyIDSource(PartyIDSource partyIDSource) {
        this.partyIDSource = partyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getPartyRole() {
        return partyRole;
    }

    @Override
    public void setPartyRole(PartyRole partyRole) {
        this.partyRole = partyRole;
    }

    @Override
    public Integer getNoPartySubIDs() {
        return noPartySubIDs;
    }

    @Override
    public void setNoPartySubIDs(Integer noPartySubIDs) {
        this.noPartySubIDs = noPartySubIDs;
        if (noPartySubIDs != null) {
            partySubIDGroups = new PartySubGroup[noPartySubIDs.intValue()];
            for (int i = 0; i < partySubIDGroups.length; i++) {
                partySubIDGroups[i] = new PartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public PartySubGroup[] getPartySubIDGroups() {
        return partySubIDGroups;
    }

    public void setPartySubIDGroups(PartySubGroup[] partySubIDGroups) {
        this.partySubIDGroups = partySubIDGroups;
        if (partySubIDGroups != null) {
            noPartySubIDs = new Integer(partySubIDGroups.length);
        }
    }

    @Override
    public PartySubGroup addPartySubIDGroup() {
        PartySubGroup group = new PartySubGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<PartySubGroup> groups = new ArrayList<PartySubGroup>();
        if (partySubIDGroups != null && partySubIDGroups.length > 0) {
            groups = new ArrayList<PartySubGroup>(Arrays.asList(partySubIDGroups));
        }
        groups.add(group);
        partySubIDGroups = groups.toArray(new PartySubGroup[groups.size()]);
        noPartySubIDs = new Integer(partySubIDGroups.length);

        return group;
    }
    @Override
    public PartySubGroup deletePartySubIDGroup(int index) {
        PartySubGroup result = null;

        if (partySubIDGroups != null && partySubIDGroups.length > 0 && partySubIDGroups.length > index) {
            List<PartySubGroup> groups = new ArrayList<PartySubGroup>(Arrays.asList(partySubIDGroups));
            result = groups.remove(index);
            partySubIDGroups = groups.toArray(new PartySubGroup[groups.size()]);
            if (partySubIDGroups.length > 0) {
                noPartySubIDs = new Integer(partySubIDGroups.length);
            } else {
                partySubIDGroups = null;
                noPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearPartySubIDGroup() {

        int result = 0;
        if (partySubIDGroups != null && partySubIDGroups.length > 0) {
            result = partySubIDGroups.length;
            partySubIDGroups = null;
            noPartySubIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (PARTY_SUB_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPartySubIDs != null && noPartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (partySubIDGroups == null) {
                    partySubIDGroups = new PartySubGroup[noPartySubIDs.intValue()];
                }
                for (int i = 0; i < partySubIDGroups.length; i++) {
                    PartySubGroup group = new PartySubGroup50SP2(context);
                    group.decode(message);
                    partySubIDGroups[i] = group;
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
        return "This tag is not supported in [PartyGroup] group version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
