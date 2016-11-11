/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocAckGroup50.java
 *
 * $Id: AllocAckGroup50.java,v 1.2 2011-04-14 23:44:35 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.IndividualAllocType;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.comp.impl.v50.NestedParties50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AllocAckGroup;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.IndividualAllocRejCode;

/**
 * FIX 5.0 implementation of AllocAckGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="AllocAck")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class AllocAckGroup50 extends AllocAckGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties50().getFragmentAllTags();

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AllocAckGroup50() {
    }
    
    public AllocAckGroup50(FragmentContext context) {
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

    @XmlAttribute(name = "Px")
    @Override
    public Double getAllocPrice() {
        return allocPrice;
    }

    @Override
    public void setAllocPrice(Double allocPrice) {
        this.allocPrice = allocPrice;
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

    @XmlAttribute(name = "IndAllocRejCode")
    @Override
    public IndividualAllocRejCode getIndividualAllocRejCode() {
        return individualAllocRejCode;
    }

    @Override
    public void setIndividualAllocRejCode(IndividualAllocRejCode individualAllocRejCode) {
        this.individualAllocRejCode = individualAllocRejCode;
    }

    @Override
    public NestedParties getNestedParties() {
        return nestedParties;
    }

    @Override
    public void setNestedParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.nestedParties = new NestedParties50(context);
    }

    @Override
    public void clearNestedParties() {
        this.nestedParties = null;
    }

    @XmlElementRef
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        return nestedParties == null ? null : nestedParties.getNestedPartyIDGroups();
    }

    public void setNestedPartyIDGroups(NestedPartyGroup[] nestedPartyIDGroups) {
        if (nestedPartyIDGroups != null) {
            if (nestedParties == null) {
                setNestedParties();
            }
            ((NestedParties50) nestedParties).setNestedPartyIDGroups(nestedPartyIDGroups);
        }
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getAllocText() {
        return allocText;
    }

    @Override
    public void setAllocText(String allocText) {
        this.allocText = allocText;
    }

    @XmlAttribute(name = "EncAllocTextLen")
    @Override
    public Integer getEncodedAllocTextLen() {
        return encodedAllocTextLen;
    }

    @Override
    public void setEncodedAllocTextLen(Integer encodedAllocTextLen) {
        this.encodedAllocTextLen = encodedAllocTextLen;
    }

    @XmlAttribute(name = "EncAllocText")
    @Override
    public byte[] getEncodedAllocText() {
        return encodedAllocText;
    }

    @Override
    public void setEncodedAllocText(byte[] encodedAllocText) {
        this.encodedAllocText = encodedAllocText;
        if (encodedAllocTextLen == null) {
            encodedAllocTextLen = new Integer(encodedAllocText.length);
        }
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

    @XmlAttribute(name = "CustCpcty")
    @Override
    public String getAllocCustomerCapacity() {
        return allocCustomerCapacity;
    }

    @Override
    public void setAllocCustomerCapacity(String allocCustomerCapacity) {
        this.allocCustomerCapacity = allocCustomerCapacity;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public IndividualAllocType getIndividualAllocType() {
        return individualAllocType;
    }

    @Override
    public void setIndividualAllocType(IndividualAllocType individualAllocType) {
        this.individualAllocType = individualAllocType;
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties50(context);
            }
            nestedParties.decode(tag, message);
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
        return "This tag is not supported in [AllocAckGroup] group version [5.0].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
