/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RgstDtlsGroup.java
 *
 * $Id: RgstDtlsGroup.java,v 1.2 2011-10-29 01:31:20 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Country;
import net.hades.fix.message.type.OwnerType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.util.TagEncoder;

/**
 * Registration details group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RgstDtlsGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RegistDtls.getValue(),
        TagNum.RegistEmail.getValue(),
        TagNum.MailingDtls.getValue(),
        TagNum.MailingInst.getValue(),
        TagNum.OwnerType.getValue(),
        TagNum.DateOfBirth.getValue(),
        TagNum.InvestorCountryOfResidence.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 509 REQUIRED. Starting with 4.3 version.
     */
    protected String registDtls;

    /**
     * TagNum = 511. Starting with 4.3 version.
     */
    protected String registEmail;

    /**
     * TagNum = 474. Starting with 4.3 version.
     */
    protected String mailingDtls;

    /**
     * TagNum = 474. Starting with 4.3 version.
     */
    protected String mailingInst;

    /**
     * Starting with 4.3 version.
     */
    protected NestedParties nestedParties;

    /**
     * TagNum = 522. Starting with 4.3 version.
     */
    protected OwnerType ownerType;

    /**
     * TagNum = 486. Starting with 4.3 version.
     */
    protected Date dateOfBirth;

    /**
     * TagNum = 475. Starting with 4.3 version.
     */
    protected Country investorCountryOfResidence;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RgstDtlsGroup() {
    }
    
    public RgstDtlsGroup(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistDtls, required=true)
    public String getRegistDtls() {
        return registDtls;
    }

    /**
     * Message field setter.
     * @param registDtls field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistDtls, required=true)
    public void setRegistDtls(String registDtls) {
        this.registDtls = registDtls;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistEmail)
    public String getRegistEmail() {
        return registEmail;
    }

    /**
     * Message field setter.
     * @param registEmail field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistEmail)
    public void setRegistEmail(String registEmail) {
        this.registEmail = registEmail;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MailingDtls)
    public String getMailingDtls() {
        return mailingDtls;
    }

    /**
     * Message field setter.
     * @param mailingDtls field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MailingDtls)
    public void setMailingDtls(String mailingDtls) {
        this.mailingDtls = mailingDtls;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MailingInst)
    public String getMailingInst() {
        return mailingInst;
    }

    /**
     * Message field setter.
     * @param mailingInst field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MailingInst)
    public void setMailingInst(String mailingInst) {
        this.mailingInst = mailingInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public NestedParties getNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void setNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component to null.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void clearNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OwnerType)
    public OwnerType getOwnerType() {
        return ownerType;
    }

    /**
     * Message field setter.
     * @param ownerType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OwnerType)
    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DateOfBirth)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Message field setter.
     * @param dateOfBirth field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DateOfBirth)
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.InvestorCountryOfResidence)
    public Country getInvestorCountryOfResidence() {
        return investorCountryOfResidence;
    }

    /**
     * Message field setter.
     * @param investorCountryOfResidence field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.InvestorCountryOfResidence)
    public void setInvestorCountryOfResidence(Country investorCountryOfResidence) {
        this.investorCountryOfResidence = investorCountryOfResidence;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.RegistDtls.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (registDtls == null || registDtls.trim().isEmpty()) {
            errorMsg.append(" [RegistDtls]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }
    
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            TagEncoder.encode(bao, TagNum.RegistDtls, registDtls);
            TagEncoder.encode(bao, TagNum.RegistEmail, registEmail);
            TagEncoder.encode(bao, TagNum.MailingDtls, mailingDtls);
            TagEncoder.encode(bao, TagNum.MailingInst, mailingInst);
            if (nestedParties != null) {
                bao.write(nestedParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (ownerType != null) {
                TagEncoder.encode(bao, TagNum.OwnerType, ownerType.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.DateOfBirth, dateOfBirth);
            if (investorCountryOfResidence != null) {
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }
    
    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case RegistDtls:
                registDtls = new String(tag.value, sessionCharset);
                break;
                
           case RegistEmail:
                registEmail = new String(tag.value, sessionCharset);
                break;
               
           case MailingDtls:
                mailingDtls = new String(tag.value, sessionCharset);
                break;
               
           case MailingInst:
                mailingInst = new String(tag.value, sessionCharset);
                break;

            case OwnerType:
                ownerType = OwnerType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DateOfBirth:
                dateOfBirth = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;
              
           case InvestorCountryOfResidence:
                investorCountryOfResidence = Country.valueFor(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [RgstDtlsGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    /// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{RgstDtlsGroup=");
        printTagValue(b, TagNum.RegistDtls, registDtls);
        printTagValue(b, TagNum.RegistEmail, registEmail);
        printTagValue(b, TagNum.MailingDtls, mailingDtls);
        printTagValue(b, TagNum.MailingInst, mailingInst);
        printTagValue(b, nestedParties);
        printTagValue(b, TagNum.OwnerType, ownerType);
        printDateTagValue(b, TagNum.DateOfBirth, dateOfBirth);
        printTagValue(b, TagNum.InvestorCountryOfResidence, investorCountryOfResidence);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
