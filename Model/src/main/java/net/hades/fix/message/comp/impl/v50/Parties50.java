/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties50.java
 *
 * $Id: Parties50.java,v 1.8 2011-04-14 23:44:50 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.impl.v50.PartyGroup50;

/**
 * FIX 5.0 implementation of Parties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlTransient
public class Parties50 extends Parties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -4825738320486481239L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> PARTY_ID_GROUP_TAGS = new PartyGroup50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTY_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTY_ID_GROUP_TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Parties50() {
        super();
    }

    public Parties50(FragmentContext context) {
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

    @Override
    public Integer getNoPartyIDs() {
        return noPartyIDs;
    }

    @Override
    public void setNoPartyIDs(Integer noPartyIDs) {
        this.noPartyIDs = noPartyIDs;
        if (noPartyIDs != null) {
            partyIDGroups = new PartyGroup[noPartyIDs.intValue()];
            for (int i = 0; i < partyIDGroups.length; i++) {
                partyIDGroups[i] = new PartyGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public PartyGroup[] getPartyIDGroups() {
        return partyIDGroups;
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        this.partyIDGroups = partyIDGroups;
        if (partyIDGroups != null) {
            noPartyIDs = new Integer(partyIDGroups.length);
        }
    }

    @Override
    public PartyGroup addPartyIDGroup() {
        PartyGroup group = new PartyGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<PartyGroup> groups = new ArrayList<PartyGroup>();
        if (partyIDGroups != null && partyIDGroups.length > 0) {
            groups = new ArrayList<PartyGroup>(Arrays.asList(partyIDGroups));
        }
        groups.add(group);
        partyIDGroups = groups.toArray(new PartyGroup[groups.size()]);
        noPartyIDs = new Integer(partyIDGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (PARTY_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPartyIDs != null && noPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (partyIDGroups == null) {
                    partyIDGroups = new PartyGroup[noPartyIDs.intValue()];
                }
                for (int i = 0; i < partyIDGroups.length; i++) {
                    PartyGroup group = new PartyGroup50(context);
                    group.decode(message);
                    partyIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Parties] component version [5.0].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
