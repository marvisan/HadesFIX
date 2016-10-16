/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentPartyIDGroup50SP1.java
 *
 * $Id: UndlyInstrumentPartyIDGroup50SP1.java,v 1.8 2011-04-14 23:44:52 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.UndlyInstrumentPartyIDGroup;
import net.hades.fix.message.group.UndlyInstrumentPartySubIDGroup;
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

/**
 * FIX 5.0SP1 implementation of UndlyInstrumentPartyIDGroup.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 02/01/2009, 11:10:14 AM
 */
@XmlRootElement(name="Pty")
@XmlType(propOrder = {"undlyInstrumentPartySubIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class UndlyInstrumentPartyIDGroup50SP1 extends UndlyInstrumentPartyIDGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -4059590815052605614L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UNDLY_INSTR_PARTY_SUBID_GROUP_TAGS = new UndlyInstrumentPartySubIDGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(UNDLY_INSTR_PARTY_SUBID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDLY_INSTR_PARTY_SUBID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UndlyInstrumentPartyIDGroup50SP1() {
    }

    public UndlyInstrumentPartyIDGroup50SP1(FragmentContext context) {
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
    public String getUndlyInstrumentPartyID() {
        return undlyInstrumentPartyID;
    }

    @Override
    public void setUndlyInstrumentPartyID(String undlyInstrumentPartyID) {
        this.undlyInstrumentPartyID = undlyInstrumentPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getUndlyInstrumentPartyIDSource() {
        return undlyInstrumentPartyIDSource;
    }

    @Override
    public void setUndlyInstrumentPartyIDSource(PartyIDSource undlyInstrumentPartyIDSource) {
        this.undlyInstrumentPartyIDSource = undlyInstrumentPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getUndlyInstrumentPartyRole() {
        return undlyInstrumentPartyRole;
    }

    @Override
    public void setUndlyInstrumentPartyRole(PartyRole undlyInstrumentPartyRole) {
        this.undlyInstrumentPartyRole = undlyInstrumentPartyRole;
    }

    @Override
    public Integer getNoUndlyInstrumentPartySubIDGroups() {
        return noUndlyInstrumentPartySubIDGroups;
    }

    @Override
    public void setNoUndlyInstrumentPartySubIDGroups(Integer noUndlyInstrumentPartySubIDGroups) {
        this.noUndlyInstrumentPartySubIDGroups = noUndlyInstrumentPartySubIDGroups;
        if (noUndlyInstrumentPartySubIDGroups != null) {
            undlyInstrumentPartySubIDGroups = new UndlyInstrumentPartySubIDGroup[noUndlyInstrumentPartySubIDGroups.intValue()];
            for (int i = 0; i < undlyInstrumentPartySubIDGroups.length; i++) {
                undlyInstrumentPartySubIDGroups[i] = new UndlyInstrumentPartySubIDGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public UndlyInstrumentPartySubIDGroup[] getUndlyInstrumentPartySubIDGroups() {
        return undlyInstrumentPartySubIDGroups;
    }

    public void setUndlyInstrumentPartySubIDGroups(UndlyInstrumentPartySubIDGroup[] undlyInstrumentPartySubIDGroups) {
        this.undlyInstrumentPartySubIDGroups = undlyInstrumentPartySubIDGroups;
        if (undlyInstrumentPartySubIDGroups != null) {
            noUndlyInstrumentPartySubIDGroups = new Integer(undlyInstrumentPartySubIDGroups.length);
        }
    }

    @Override
    public UndlyInstrumentPartySubIDGroup addUndlyInstrumentPartySubIDGroup() {
        UndlyInstrumentPartySubIDGroup group = new UndlyInstrumentPartySubIDGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UndlyInstrumentPartySubIDGroup> groups = new ArrayList<UndlyInstrumentPartySubIDGroup>();
        if (undlyInstrumentPartySubIDGroups != null && undlyInstrumentPartySubIDGroups.length > 0) {
            groups = new ArrayList<UndlyInstrumentPartySubIDGroup>(Arrays.asList(undlyInstrumentPartySubIDGroups));
        }
        groups.add(group);
        undlyInstrumentPartySubIDGroups = groups.toArray(new UndlyInstrumentPartySubIDGroup[groups.size()]);
        noUndlyInstrumentPartySubIDGroups = new Integer(undlyInstrumentPartySubIDGroups.length);

        return group;
    }

    @Override
    public UndlyInstrumentPartySubIDGroup deleteUndlyInstrumentPartySubIDGroup(int index) {
        UndlyInstrumentPartySubIDGroup result = null;
        if (undlyInstrumentPartySubIDGroups != null && undlyInstrumentPartySubIDGroups.length > 0 && undlyInstrumentPartySubIDGroups.length > index) {
            List<UndlyInstrumentPartySubIDGroup> groups = new ArrayList<UndlyInstrumentPartySubIDGroup>(Arrays.asList(undlyInstrumentPartySubIDGroups));
            result = groups.remove(index);
            undlyInstrumentPartySubIDGroups = groups.toArray(new UndlyInstrumentPartySubIDGroup[groups.size()]);
            if (undlyInstrumentPartySubIDGroups.length > 0) {
                noUndlyInstrumentPartySubIDGroups = new Integer(undlyInstrumentPartySubIDGroups.length);
            } else {
                undlyInstrumentPartySubIDGroups = null;
                noUndlyInstrumentPartySubIDGroups = null;
            }
        }

        return result;
    }

    @Override
    public int clearUndlyInstrumentPartySubIDGroups() {
        int result = 0;
        if (undlyInstrumentPartySubIDGroups != null && undlyInstrumentPartySubIDGroups.length > 0) {
            result = undlyInstrumentPartySubIDGroups.length;
            undlyInstrumentPartySubIDGroups = null;
            noUndlyInstrumentPartySubIDGroups = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (UNDLY_INSTR_PARTY_SUBID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noUndlyInstrumentPartySubIDGroups != null && noUndlyInstrumentPartySubIDGroups.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (undlyInstrumentPartySubIDGroups == null) {
                    undlyInstrumentPartySubIDGroups = new UndlyInstrumentPartySubIDGroup[noUndlyInstrumentPartySubIDGroups.intValue()];
                }
                for (int i = 0; i < undlyInstrumentPartySubIDGroups.length; i++) {
                    UndlyInstrumentPartySubIDGroup group = new UndlyInstrumentPartySubIDGroup50SP1(context);
                    group.decode(message);
                    undlyInstrumentPartySubIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [UndlyInstrumentPartyIDGroup] component version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
