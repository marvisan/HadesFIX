/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrumentPartyGroup50SP1.java
 *
 * $Id: DerivativeInstrumentPartyGroup50SP2.java,v 1.1 2011-09-19 08:15:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.group.DerivativeInstrumentPartyGroup;
import net.hades.fix.message.struct.Tag;
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

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivativeInstrumentPartySubIDGroup;
import net.hades.fix.message.type.PartyIDSource;

/**
 * FIX 5.0SP1 implementation of DerivativeInstrumentPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 02/01/2009, 3:52:05 PM
 */
@XmlRootElement(name = "Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeInstrumentPartyGroup50SP2 extends DerivativeInstrumentPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS = new DerivativeInstrumentPartySubIDGroup50SP2().getFragmentAllTags();

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

    public DerivativeInstrumentPartyGroup50SP2() {
    }

    public DerivativeInstrumentPartyGroup50SP2(FragmentContext context) {
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
    public String getDerivativeInstrumentPartyID() {
        return derivativeInstrumentPartyID;
    }

    @Override
    public void setDerivativeInstrumentPartyID(String derivativeInstrumentPartyID) {
        this.derivativeInstrumentPartyID = derivativeInstrumentPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getDerivativeInstrumentPartyIDSource() {
        return derivativeInstrumentPartyIDSource;
    }

    @Override
    public void setDerivativeInstrumentPartyIDSource(PartyIDSource derivativeInstrumentPartyIDSource) {
        this.derivativeInstrumentPartyIDSource = derivativeInstrumentPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getDerivativeInstrumentPartyRole() {
        return derivativeInstrumentPartyRole;
    }

    @Override
    public void setDerivativeInstrumentPartyRole(PartyRole derivativeInstrumentPartyRole) {
        this.derivativeInstrumentPartyRole = derivativeInstrumentPartyRole;
    }

    @Override
    public Integer getNoDerivativeInstrumentPartySubIDs() {
        return noDerivativeInstrumentPartySubIDs;
    }

    @Override
    public void setNoDerivativeInstrumentPartySubIDs(Integer noDerivativeInstrumentPartySubIDs) {
        this.noDerivativeInstrumentPartySubIDs = noDerivativeInstrumentPartySubIDs;
        if (noDerivativeInstrumentPartySubIDs != null) {
            derivativeInstrumentPartySubIDGroups = new DerivativeInstrumentPartySubIDGroup[noDerivativeInstrumentPartySubIDs.intValue()];
            for (int i = 0; i < derivativeInstrumentPartySubIDGroups.length; i++) {
                derivativeInstrumentPartySubIDGroups[i] = new DerivativeInstrumentPartySubIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public DerivativeInstrumentPartySubIDGroup[] getDerivativeInstrumentPartySubIDGroups() {
        return derivativeInstrumentPartySubIDGroups;
    }

    public void setDerivativeInstrumentPartySubIDGroups(DerivativeInstrumentPartySubIDGroup[] derivativeInstrumentPartySubIDGroups) {
        this.derivativeInstrumentPartySubIDGroups = derivativeInstrumentPartySubIDGroups;
        if (derivativeInstrumentPartySubIDGroups != null) {
            noDerivativeInstrumentPartySubIDs = new Integer(derivativeInstrumentPartySubIDGroups.length);
        }
    }

    @Override
    public DerivativeInstrumentPartySubIDGroup addDerivativeInstrumentPartySubIDGroup() {
        DerivativeInstrumentPartySubIDGroup group = new DerivativeInstrumentPartySubIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<DerivativeInstrumentPartySubIDGroup> groups = new ArrayList<DerivativeInstrumentPartySubIDGroup>();
        if (derivativeInstrumentPartySubIDGroups != null && derivativeInstrumentPartySubIDGroups.length > 0) {
            groups = new ArrayList<DerivativeInstrumentPartySubIDGroup>(Arrays.asList(derivativeInstrumentPartySubIDGroups));
        }
        groups.add(group);
        derivativeInstrumentPartySubIDGroups = groups.toArray(new DerivativeInstrumentPartySubIDGroup[groups.size()]);
        noDerivativeInstrumentPartySubIDs = new Integer(derivativeInstrumentPartySubIDGroups.length);

        return group;
    }

    @Override
    public DerivativeInstrumentPartySubIDGroup deleteDerivativeInstrumentPartySubIDGroup(int index) {
        DerivativeInstrumentPartySubIDGroup result = null;
        if (derivativeInstrumentPartySubIDGroups != null && derivativeInstrumentPartySubIDGroups.length > 0 && derivativeInstrumentPartySubIDGroups.length > index) {
            List<DerivativeInstrumentPartySubIDGroup> groups = new ArrayList<DerivativeInstrumentPartySubIDGroup>(Arrays.asList(derivativeInstrumentPartySubIDGroups));
            result = groups.remove(index);
            derivativeInstrumentPartySubIDGroups = groups.toArray(new DerivativeInstrumentPartySubIDGroup[groups.size()]);
            if (derivativeInstrumentPartySubIDGroups.length > 0) {
                noDerivativeInstrumentPartySubIDs = new Integer(derivativeInstrumentPartySubIDGroups.length);
            } else {
                derivativeInstrumentPartySubIDGroups = null;
                noDerivativeInstrumentPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearDerivativeInstrumentPartySubIDGroups() {
        int result = 0;
        if (derivativeInstrumentPartySubIDGroups != null && derivativeInstrumentPartySubIDGroups.length > 0) {
            result = derivativeInstrumentPartySubIDGroups.length;
            derivativeInstrumentPartySubIDGroups = null;
            noDerivativeInstrumentPartySubIDs = null;
        }

        return result;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noDerivativeInstrumentPartySubIDs != null && noDerivativeInstrumentPartySubIDs.intValue() > 0) {
            if (INSTRUMENT_PARTY_SUB_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                derivativeInstrumentPartySubIDGroups = new DerivativeInstrumentPartySubIDGroup[noDerivativeInstrumentPartySubIDs.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noDerivativeInstrumentPartySubIDs.intValue(); i++) {
                    DerivativeInstrumentPartySubIDGroup group = new DerivativeInstrumentPartySubIDGroup50SP2(context);
                    group.decode(message);
                    derivativeInstrumentPartySubIDGroups[i] = group;
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
        return "This tag is not supported in [DerivativeInstrumentPartyGroup] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
