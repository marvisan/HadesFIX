/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeAllocGroup50SP1.java
 *
 * $Id: TradeAllocGroup50SP1.java,v 1.1 2011-10-21 10:31:02 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.Nested2PartyGroup;
import net.hades.fix.message.group.TradeAllocGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.AllocMethod;
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

import net.hades.fix.message.comp.impl.v50sp1.NestedParties250SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ClearingFeeIndicator;

/**
 * FIX 5.0SP1 implementation of TradeAllocGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name = "Alloc")
@XmlType(propOrder = {"nested2PartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class TradeAllocGroup50SP1 extends TradeAllocGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NESTED_PARTIES2_COMP_TAGS = new NestedParties250SP1().getFragmentAllTags();
 
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

    public TradeAllocGroup50SP1() {
    }

    public TradeAllocGroup50SP1(FragmentContext context) {
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
    public NestedParties2 getNestedParties2() {
        return nestedParties2;
    }

    @Override
    public void setNestedParties2() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.nestedParties2 = new NestedParties250SP1(context);
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
            ((NestedParties250SP1) nestedParties2).setNested2PartyIDGroups(nested2PartyIDGroups);
        }
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

    @XmlAttribute(name = "CustCpcty")
    @Override
    public String getAllocCustomerCapacity() {
        return allocCustomerCapacity;
    }

    @Override
    public void setAllocCustomerCapacity(String allocCustomerCapacity) {
        this.allocCustomerCapacity = allocCustomerCapacity;
    }

    @XmlAttribute(name = "Meth")
    @Override
    public AllocMethod getAllocMethod() {
        return allocMethod;
    }

    @Override
    public void setAllocMethod(AllocMethod allocMethod) {
        this.allocMethod = allocMethod;
    }

    @XmlAttribute(name = "IndAllocID2")
    @Override
    public String getSecondaryIndividualAllocID() {
        return secondaryIndividualAllocID;
    }

    @Override
    public void setSecondaryIndividualAllocID(String secondaryIndividualAllocID) {
        this.secondaryIndividualAllocID = secondaryIndividualAllocID;
    }

    @XmlAttribute(name = "ClrFeeInd")
    @Override
    public ClearingFeeIndicator getAllocClearingFeeIndicator() {
        return allocClearingFeeIndicator;
    }

    @Override
    public void setAllocClearingFeeIndicator(ClearingFeeIndicator allocClearingFeeIndicator) {
        this.allocClearingFeeIndicator = allocClearingFeeIndicator;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (NESTED_PARTIES2_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties2 == null) {
                nestedParties2 = new NestedParties250SP1(context);
            }
            nestedParties2.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradeAllocGroup] group version [5.0SP1].";
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
