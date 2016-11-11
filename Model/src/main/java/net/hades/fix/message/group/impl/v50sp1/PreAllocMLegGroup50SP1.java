/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreAllocMLegGroup50SP1.java
 *
 * $Id: PreAllocMLegGroup50SP1.java,v 1.2 2011-09-09 08:05:13 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties3;
import net.hades.fix.message.comp.impl.v50sp1.NestedParties350SP1;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested3PartyGroup;
import net.hades.fix.message.group.PreAllocMLegGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.AcctIDSource;
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

/**
 * FIX 5.0SP1 implementation of PreAllocMLegGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/05/2011, 11:39:24 AM
 */
@XmlRootElement(name = "PreAllocMleg")
@XmlType(propOrder = {"nestedPartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class PreAllocMLegGroup50SP1 extends PreAllocMLegGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NESTED_PARTIES3_COMP_TAGS = new NestedParties350SP1().getFragmentAllTags();
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES3_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES3_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PreAllocMLegGroup50SP1() {
    }

    public PreAllocMLegGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "Acct")
    @Override
    public String getAllocAccount() {
        return allocAccount;
    }

    @Override
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    @XmlAttribute(name = "ActIDSrc")
    @Override
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    @Override
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
    }

    @XmlAttribute(name = "Qty")
    @Override
    public Double getAllocQty() {
        return allocQty;
    }

    @Override
    public void setAllocQty(Double allocQty) {
        this.allocQty = allocQty;
    }

    @XmlAttribute(name = "AllocSettlCcy")
    @Override
    public Currency getAllocSettlCurrency() {
        return allocSettlCurrency;
    }

    @Override
    public void setAllocSettlCurrency(Currency allocSettlCurrency) {
        this.allocSettlCurrency = allocSettlCurrency;
    }

    @XmlAttribute(name = "IndAllocID")
    @Override
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    @Override
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
    }

    @Override
    public NestedParties3 getNestedParties3() {
        return nestedParties3;
    }

    @Override
    public void setNestedParties3() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.nestedParties3 = new NestedParties350SP1(context);
    }

    @Override
    public void clearNestedParties3() {
        this.nestedParties3 = null;
    }

    @XmlElementRef
    public Nested3PartyGroup[] getNestedPartyIDGroups() {
        return nestedParties3 == null ? null : nestedParties3.getNested3PartyIDGroups();
    }

    public void setNestedPartyIDGroups(Nested3PartyGroup[] nestedPartyIDGroups) {
        if (nestedPartyIDGroups != null) {
            if (nestedParties3 == null) {
                setNestedParties3();
            }
            ((NestedParties350SP1) nestedParties3).setNested3PartyIDGroups(nestedPartyIDGroups);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (NESTED_PARTIES3_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties3 == null) {
                nestedParties3 = new NestedParties350SP1(context);
            }
            nestedParties3.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PreMLegAllocGroup] group version [5.0SP1].";
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
