/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FinancingDetails.java
 *
 * $Id: FinancingDetails.java,v 1.9 2010-11-23 10:20:20 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SessionRejectReason;

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

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.DeliveryType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TerminationType;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Component block is optionally used only for financing deals to identify the legal
 * agreement under which the deal was made and other unique characteristics of the
 * transaction. The AgreementDesc field refers to base standard documents such as MRA
 * 1996 Repurchase Agreement, GMRA 2000 Bills Transaction (U.K.), MSLA 1993 Securities
 * Loan – Amended 1998, for example.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class FinancingDetails extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AgreementDesc.getValue(),
        TagNum.AgreementID.getValue(),
        TagNum.AgreementDate.getValue(),
        TagNum.AgreementCurrency.getValue(),
        TagNum.TerminationType.getValue(),
        TagNum.StartDate.getValue(),
        TagNum.EndDate.getValue(),
        TagNum.DeliveryType.getValue(),
        TagNum.MarginRatio.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AgreementDesc.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 913. Starting with 4.4 version.
     */
    protected String agreementDesc;

    /**
     * TagNum = 914. Starting with 4.4 version.
     */
    protected String agreementID;

    /**
     * TagNum = 915. Starting with 4.4 version.
     */
    protected Date agreementDate;

    /**
     * TagNum = 918. Starting with 4.4 version.
     */
    protected Currency agreementCurrency;

    /**
     * TagNum = 788. Starting with 4.4 version.
     */
    protected TerminationType terminationType;

    /**
     * TagNum = 916. Starting with 4.4 version.
     */
    protected Date startDate;

    /**
     * TagNum = 916. Starting with 4.4 version.
     */
    protected Date endDate;

    /**
     * TagNum = 916. Starting with 4.4 version.
     */
    protected DeliveryType deliveryType;

    /**
     * TagNum = 898. Starting with 4.4 version.
     */
    protected Double marginRatio;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public FinancingDetails() {
    }

    public FinancingDetails(FragmentContext context) {
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementDesc)
    public String getAgreementDesc() {
        return agreementDesc;
    }

    /**
     * Message field setter.
     * @param agreementDesc field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementDesc)
    public void setAgreementDesc(String agreementDesc) {
        this.agreementDesc = agreementDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementID)
    public String getAgreementID() {
        return agreementID;
    }

    /**
     * Message field setter.
     * @param agreementID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementID)
    public void setAgreementID(String agreementID) {
        this.agreementID = agreementID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementDate)
    public Date getAgreementDate() {
        return agreementDate;
    }

    /**
     * Message field setter.
     * @param agreementDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementDate)
    public void setAgreementDate(Date agreementDate) {
        this.agreementDate = agreementDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementCurrency)
    public Currency getAgreementCurrency() {
        return agreementCurrency;
    }

    /**
     * Message field setter.
     * @param agreementCurrency field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AgreementCurrency)
    public void setAgreementCurrency(Currency agreementCurrency) {
        this.agreementCurrency = agreementCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.TerminationType)
    public TerminationType getTerminationType() {
        return terminationType;
    }

    /**
     * Message field setter.
     * @param terminationType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.TerminationType)
    public void setTerminationType(TerminationType terminationType) {
        this.terminationType = terminationType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StartDate)
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Message field setter.
     * @param startDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StartDate)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EndDate)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Message field setter.
     * @param endDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.EndDate)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DeliveryType)
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    /**
     * Message field setter.
     * @param deliveryType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DeliveryType)
    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.MarginRatio)
    public Double getMarginRatio() {
        return marginRatio;
    }

    /**
     * Message field setter.
     * @param marginRatio field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.MarginRatio)
    public void setMarginRatio(Double marginRatio) {
        this.marginRatio = marginRatio;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.AgreementDesc.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (agreementDesc == null) {
            errorMsg.append(" [AgreementDesc]");
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
            TagEncoder.encode(bao, TagNum.AgreementDesc, agreementDesc, sessionCharset);
            TagEncoder.encode(bao, TagNum.AgreementID, agreementID, sessionCharset);
            TagEncoder.encodeDate(bao, TagNum.AgreementDate, agreementDate);
            if (agreementCurrency != null) {
                TagEncoder.encode(bao, TagNum.AgreementCurrency, agreementCurrency.getValue());
            }
            if (terminationType != null) {
                TagEncoder.encode(bao, TagNum.TerminationType, terminationType.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.StartDate, startDate);
            TagEncoder.encodeDate(bao, TagNum.EndDate, endDate);
            if (deliveryType != null) {
                TagEncoder.encode(bao, TagNum.DeliveryType, deliveryType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MarginRatio, marginRatio);
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
            case AgreementDesc:
                agreementDesc = new String(tag.value, sessionCharset);;
                break;

            case AgreementID:
                agreementID = new String(tag.value, sessionCharset);
                break;

            case AgreementDate:
                agreementDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AgreementCurrency:
                agreementCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case TerminationType:
                terminationType = TerminationType.valueFor(new Integer(new String(tag.value, sessionCharset)).intValue());
                break;

            case StartDate:
                startDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case EndDate:
                endDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case DeliveryType:
                deliveryType = DeliveryType.valueFor(new Integer(new String(tag.value, sessionCharset)).intValue());
                break;

            case MarginRatio:
                marginRatio = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [FinancingDetails] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{FinancingDetails=");
        printTagValue(b, TagNum.AgreementDesc, agreementDesc);
        printTagValue(b, TagNum.AgreementID, agreementID);
        printDateTagValue(b, TagNum.AgreementDate, agreementDate);
        printTagValue(b, TagNum.AgreementCurrency, agreementCurrency);
        printTagValue(b, TagNum.TerminationType, terminationType);
        printDateTagValue(b, TagNum.StartDate, startDate);
        printDateTagValue(b, TagNum.EndDate, endDate);
        printTagValue(b, TagNum.DeliveryType, deliveryType);
        printTagValue(b, TagNum.MarginRatio, marginRatio);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
