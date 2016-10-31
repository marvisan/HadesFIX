/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DeliveryInstructionGroup50.java
 *
 * $Id: DeliveryInstructionGroup50.java,v 1.2 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.DeliveryInstructionGroup;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SettlPartyGroup;
import net.hades.fix.message.type.DlvyInstType;
import net.hades.fix.message.type.SettlInstSource;

/**
 * FIX 5.0 implementation of DeliveryInstructionGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="DlvInst")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DeliveryInstructionGroup50 extends DeliveryInstructionGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> SETTL_PARTY_GROUP_TAGS = new SettlPartyGroup50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(SETTL_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SETTL_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DeliveryInstructionGroup50() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DeliveryInstructionGroup50(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "InstSrc")
    @Override
    public SettlInstSource getSettlInstSource() {
        return settlInstSource;
    }

    @Override
    public void setSettlInstSource(SettlInstSource settlInstSource) {
        this.settlInstSource = settlInstSource;
    }

    @XmlAttribute(name = "InstTyp")
    @Override
    public DlvyInstType getDlvyInstType() {
        return dlvyInstType;
    }

    @Override
    public void setDlvyInstType(DlvyInstType dlvyInstType) {
        this.dlvyInstType = dlvyInstType;
    }

    @Override
    public Integer getNoSettlPartyIDs() {
        return noSettlPartyIDs;
    }

    @Override
    public void setNoSettlPartyIDs(Integer noSettlPartyIDs) {
        this.noSettlPartyIDs = noSettlPartyIDs;
        if (noSettlPartyIDs != null) {
            settlPartyGroups = new SettlPartyGroup[noSettlPartyIDs.intValue()];
            for (int i = 0; i < settlPartyGroups.length; i++) {
                settlPartyGroups[i] = new SettlPartyGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SettlPartyGroup[] getSettlPartyGroups() {
        return settlPartyGroups;
    }

    public void setSettlPartyGroups(SettlPartyGroup[] settlPartyGroups) {
        this.settlPartyGroups = settlPartyGroups;
        if (settlPartyGroups != null) {
            noSettlPartyIDs = new Integer(settlPartyGroups.length);
        }
    }

    @Override
    public SettlPartyGroup addSettlPartyGroup() {
        SettlPartyGroup group = new SettlPartyGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SettlPartyGroup> groups = new ArrayList<SettlPartyGroup>();
        if (settlPartyGroups != null && settlPartyGroups.length > 0) {
            groups = new ArrayList<SettlPartyGroup>(Arrays.asList(settlPartyGroups));
        }
        groups.add(group);
        settlPartyGroups = groups.toArray(new SettlPartyGroup[groups.size()]);
        noSettlPartyIDs = new Integer(settlPartyGroups.length);

        return group;
    }

    @Override
    public SettlPartyGroup deleteSettlPartyGroup(int index) {
        SettlPartyGroup result = null;
        if (settlPartyGroups != null && settlPartyGroups.length > 0 && settlPartyGroups.length > index) {
            List<SettlPartyGroup> groups = new ArrayList<SettlPartyGroup>(Arrays.asList(settlPartyGroups));
            result = groups.remove(index);
            settlPartyGroups = groups.toArray(new SettlPartyGroup[groups.size()]);
            if (settlPartyGroups.length > 0) {
                noSettlPartyIDs = new Integer(settlPartyGroups.length);
            } else {
                settlPartyGroups = null;
                noSettlPartyIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearSettlPartyGroups() {
        int result = 0;
        if (settlPartyGroups != null && settlPartyGroups.length > 0) {
            result = settlPartyGroups.length;
            settlPartyGroups = null;
            noSettlPartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (SETTL_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSettlPartyIDs != null && noSettlPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (settlPartyGroups == null) {
                    settlPartyGroups = new SettlPartyGroup[noSettlPartyIDs.intValue()];
                }
                for (int i = 0; i < settlPartyGroups.length; i++) {
                    SettlPartyGroup group = new SettlPartyGroup50(context);
                    group.decode(message);
                    settlPartyGroups[i] = group;
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
        return "This tag is not supported in [DeliveryInstructionGroup] group version [5.0].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
