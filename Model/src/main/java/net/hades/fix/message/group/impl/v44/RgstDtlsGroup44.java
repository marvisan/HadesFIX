/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RgstDtlsGroup44.java
 *
 * $Id: RgstDtlsGroup44.java,v 1.2 2011-10-29 01:31:21 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v44.NestedParties44;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Country;
import net.hades.fix.message.type.OwnerType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 4.4 implementation of RgstDtlsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="RgDtl")
@XmlType(propOrder = {"nestedPartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class RgstDtlsGroup44 extends RgstDtlsGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties44().getFragmentAllTags();

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
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RgstDtlsGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public RgstDtlsGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.nestedParties = new NestedParties44(context);
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
            ((NestedParties44) nestedParties).setNestedPartyIDGroups(nestedPartyIDGroups);
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.RegistDtls, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistDtls, registDtls);
            }
            if (MsgUtil.isTagInList(TagNum.RegistEmail, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistEmail, registEmail);
            }
            if (MsgUtil.isTagInList(TagNum.MailingDtls, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MailingDtls, mailingDtls);
            }
            if (MsgUtil.isTagInList(TagNum.MailingInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MailingInst, mailingInst);
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (ownerType != null && MsgUtil.isTagInList(TagNum.OwnerType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OwnerType, ownerType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DateOfBirth, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.DateOfBirth, dateOfBirth);
            }
            if (investorCountryOfResidence != null && MsgUtil.isTagInList(TagNum.InvestorCountryOfResidence, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.InvestorCountryOfResidence, investorCountryOfResidence.getValue());
            }

            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }
        
        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties44(context);
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
        return "This tag is not supported in [RgstDtlsGroup] group version [4.4].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
