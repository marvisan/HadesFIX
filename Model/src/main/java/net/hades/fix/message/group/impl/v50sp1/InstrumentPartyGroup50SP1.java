/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartyGroup50SP1.java
 *
 * $Id: InstrumentPartyGroup50SP1.java,v 1.8 2011-04-14 23:44:51 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.InstrumentPartyGroup;
import net.hades.fix.message.group.InstrumentPartySubIDGroup;
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

/**
 * FIX 5.0SP1 implementation of InstrumentPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 02/01/2009, 3:52:05 PM
 */
@XmlRootElement(name = "Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentPartyGroup50SP1 extends InstrumentPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.InstrumentPartyID.getValue(),
        TagNum.InstrumentPartyIDSource.getValue(),
        TagNum.InstrumentPartyRole.getValue(),
        TagNum.NoInstrumentPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS = new InstrumentPartySubIDGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentPartyGroup50SP1() {
    }

    public InstrumentPartyGroup50SP1(FragmentContext context) {
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
    public String getInstrumentPartyID() {
        return instrumentPartyID;
    }

    @Override
    public void setInstrumentPartyID(String instrumentPartyID) {
        this.instrumentPartyID = instrumentPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getInstrumentPartyIDSource() {
        return instrumentPartyIDSource;
    }

    @Override
    public void setInstrumentPartyIDSource(PartyIDSource instrumentPartyIDSource) {
        this.instrumentPartyIDSource = instrumentPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getInstrumentPartyRole() {
        return instrumentPartyRole;
    }

    @Override
    public void setInstrumentPartyRole(PartyRole instrumentPartyRole) {
        this.instrumentPartyRole = instrumentPartyRole;
    }

    @Override
    public Integer getNoInstrumentPartySubIDs() {
        return noInstrumentPartySubIDs;
    }

    @Override
    public void setNoInstrumentPartySubIDs(Integer noInstrumentPartySubIDs) {
        this.noInstrumentPartySubIDs = noInstrumentPartySubIDs;
        if (noInstrumentPartySubIDs != null) {
            instrumentPartySubIDGroups = new InstrumentPartySubIDGroup[noInstrumentPartySubIDs.intValue()];
            for (int i = 0; i < instrumentPartySubIDGroups.length; i++) {
                instrumentPartySubIDGroups[i] = new InstrumentPartySubIDGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public InstrumentPartySubIDGroup[] getInstrumentPartySubIDGroups() {
        return instrumentPartySubIDGroups;
    }

    public void setInstrumentPartySubIDGroups(InstrumentPartySubIDGroup[] instrumentPartySubIDGroups) {
        this.instrumentPartySubIDGroups = instrumentPartySubIDGroups;
        if (instrumentPartySubIDGroups != null) {
            noInstrumentPartySubIDs = new Integer(instrumentPartySubIDGroups.length);
        }
    }

    @Override
    public InstrumentPartySubIDGroup addInstrumentPartySubIDGroup() {
        InstrumentPartySubIDGroup group = new InstrumentPartySubIDGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrumentPartySubIDGroup> groups = new ArrayList<InstrumentPartySubIDGroup>();
        if (instrumentPartySubIDGroups != null && instrumentPartySubIDGroups.length > 0) {
            groups = new ArrayList<InstrumentPartySubIDGroup>(Arrays.asList(instrumentPartySubIDGroups));
        }
        groups.add(group);
        instrumentPartySubIDGroups = groups.toArray(new InstrumentPartySubIDGroup[groups.size()]);
        noInstrumentPartySubIDs = new Integer(instrumentPartySubIDGroups.length);

        return group;
    }

    @Override
    public InstrumentPartySubIDGroup deleteInstrumentPartySubIDGroup(int index) {
        InstrumentPartySubIDGroup result = null;
        if (instrumentPartySubIDGroups != null && instrumentPartySubIDGroups.length > 0 && instrumentPartySubIDGroups.length > index) {
            List<InstrumentPartySubIDGroup> groups = new ArrayList<InstrumentPartySubIDGroup>(Arrays.asList(instrumentPartySubIDGroups));
            result = groups.remove(index);
            instrumentPartySubIDGroups = groups.toArray(new InstrumentPartySubIDGroup[groups.size()]);
            if (instrumentPartySubIDGroups.length > 0) {
                noInstrumentPartySubIDs = new Integer(instrumentPartySubIDGroups.length);
            } else {
                instrumentPartySubIDGroups = null;
                noInstrumentPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrumentPartySubIDGroups() {
        int result = 0;
        if (instrumentPartySubIDGroups != null && instrumentPartySubIDGroups.length > 0) {
            result = instrumentPartySubIDGroups.length;
            instrumentPartySubIDGroups = null;
            noInstrumentPartySubIDs = null;
        }

        return result;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noInstrumentPartySubIDs != null && noInstrumentPartySubIDs.intValue() > 0) {
            if (INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                instrumentPartySubIDGroups = new InstrumentPartySubIDGroup[noInstrumentPartySubIDs.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noInstrumentPartySubIDs.intValue(); i++) {
                    InstrumentPartySubIDGroup group = new InstrumentPartySubIDGroup50SP1(context);
                    group.decode(message);
                    instrumentPartySubIDGroups[i] = group;
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
        return "This tag is not supported in [InstrumentPartyGroup] component version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
