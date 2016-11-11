/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegAllocGroup50SP2.java
 *
 * $Id: LegAllocGroup50SP2.java,v 1.2 2011-04-14 23:44:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties2;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties250SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.LegAllocGroup;
import net.hades.fix.message.group.Nested2PartyGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;

import java.nio.ByteBuffer;
import java.util.HashSet;
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
 * FIX 5.0SP2 implementation of LegAllocGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2009, 6:46:57 PM
 */
@XmlRootElement(name = "PreAll")
@XmlType(propOrder = {"nested2PartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class LegAllocGroup50SP2 extends LegAllocGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> NESTED_PARTIES2_COMP_TAGS = new NestedParties250SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES2_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES2_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegAllocGroup50SP2() {
        super();
    }

    public LegAllocGroup50SP2(FragmentContext context) {
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

    @XmlAttribute(name = "AllocAcct")
    @Override
    public String getLegAllocAccount() {
        return legAllocAccount;
    }

    @Override
    public void setLegAllocAccount(String legAllocAccount) {
        this.legAllocAccount = legAllocAccount;
    }

    @XmlAttribute(name = "IndAllocID")
    @Override
    public String getLegIndividualAllocID() {
        return legIndividualAllocID;
    }

    @Override
    public void setLegIndividualAllocID(String legIndividualAllocID) {
        this.legIndividualAllocID = legIndividualAllocID;
    }

    @Override
    public NestedParties2 getNestedParties2() {
        return nestedParties2;
    }

    @Override
    public void setNestedParties2() {
        this.nestedParties2 = new NestedParties250SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearNestedParties2() {
        this.nestedParties2 = null;
    }

    @XmlElementRef
    public Nested2PartyGroup[] getNested2PartyIDGroups() {
        return nestedParties2 == null ? null : nestedParties2.getNested2PartyIDGroups();
    }

    public void setNested2PartyIDGroups(Nested2PartyGroup[] nested2PartyIDGroups) {
        if (nested2PartyIDGroups != null) {
            if (nestedParties2 == null) {
                setNestedParties2();
            }
            ((NestedParties250SP2) nestedParties2).setNested2PartyIDGroups(nested2PartyIDGroups);
        }
    }

    @XmlAttribute(name = "AllocQty")
    @Override
    public Double getLegAllocQty() {
        return legAllocQty;
    }

    @Override
    public void setLegAllocQty(Double legAllocQty) {
        this.legAllocQty = legAllocQty;
    }

    @XmlAttribute(name = "AllocAcctIDSrc")
    @Override
    public String getLegAllocAcctIDSource() {
        return legAllocAcctIDSource;
    }

    @Override
    public void setLegAllocAcctIDSource(String legAllocAcctIDSource) {
        this.legAllocAcctIDSource = legAllocAcctIDSource;
    }

    @XmlAttribute(name = "AllocSettlCcy")
    @Override
    public Currency getLegAllocSettlCurrency() {
        return legAllocSettlCurrency;
    }

    @Override
    public void setLegAllocSettlCurrency(Currency legAllocSettlCurrency) {
        this.legAllocSettlCurrency = legAllocSettlCurrency;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NESTED_PARTIES2_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties2 == null) {
                nestedParties2 = new NestedParties250SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
            nestedParties2.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LegAllocGroup] group version [5.0SP2].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
