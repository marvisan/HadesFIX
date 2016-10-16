/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RgstDtlsGroup50.java
 *
 * $Id: RgstDtlsGroup50.java,v 1.2 2011-10-29 01:31:23 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v50.NestedParties50;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.OwnerType;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.type.Country;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0 implementation of RgstDtlsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="RgDtl")
@XmlType(propOrder = {"nestedPartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class RgstDtlsGroup50 extends RgstDtlsGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RgstDtlsGroup50() {
    }
    
    public RgstDtlsGroup50(FragmentContext context) {
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

    @XmlAttribute(name = "RejRsnTxt")
    @Override
    public String getRegistDtls() {
        return registDtls;
    }

    @Override
    public void setRegistDtls(String registDtls) {
        this.registDtls = registDtls;
    }

    @XmlAttribute(name = "Email")
    @Override
    public String getRegistEmail() {
        return registEmail;
    }

    @Override
    public void setRegistEmail(String registEmail) {
        this.registEmail = registEmail;
    }

    @XmlAttribute(name = "MailingDtls")
    @Override
    public String getMailingDtls() {
        return mailingDtls;
    }

    @Override
    public void setMailingDtls(String mailingDtls) {
        this.mailingDtls = mailingDtls;
    }

    @XmlAttribute(name = "MailingInst")
    @Override
    public String getMailingInst() {
        return mailingInst;
    }

    @Override
    public void setMailingInst(String mailingInst) {
        this.mailingInst = mailingInst;
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

    @XmlAttribute(name = "OwnerTyp")
    @Override
    public OwnerType getOwnerType() {
        return ownerType;
    }

    @Override
    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @XmlAttribute(name = "DtOfBirth")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlAttribute(name = "InvestorCtryOfResidence")
    @Override
    public Country getInvestorCountryOfResidence() {
        return investorCountryOfResidence;
    }

    @Override
    public void setInvestorCountryOfResidence(Country investorCountryOfResidence) {
        this.investorCountryOfResidence = investorCountryOfResidence;
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
        return "This tag is not supported in [RgstDtlsGroup] group version [5.0].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
