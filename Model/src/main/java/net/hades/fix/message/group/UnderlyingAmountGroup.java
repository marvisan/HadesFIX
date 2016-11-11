/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingAmountGroup.java
 *
 * $Id: UnderlyingStipsGroup.java,v 1.10 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Underlying amount group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 11/12/2011, 11:34:25 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UnderlyingAmountGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingPayAmount.getValue(),
        TagNum.UnderlyingCollectAmount.getValue(),
        TagNum.UnderlyingSettlementDate.getValue(),
        TagNum.UnderlyingSettlementStatus.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    /**
     * TagNum = 985. Starting with 5.0 version.
     */
    protected Double underlyingPayAmount;

    /**
     * TagNum = 986. Starting with 5.0 version.
     */
    protected Double underlyingCollectAmount;

    /**
     * TagNum = 987. Starting with 5.0 version.
     */
    protected Date underlyingSettlementDate;

    /**
     * TagNum = 988. Starting with 5.0 version.
     */
    protected String underlyingSettlementStatus;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingAmountGroup() {
    }

    public UnderlyingAmountGroup(FragmentContext context) {
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingPayAmount)
    public Double getUnderlyingPayAmount() {
        return underlyingPayAmount;
    }

    /**
     * Message field setter.
     * @param underlyingPayAmount field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingPayAmount)
    public void setUnderlyingPayAmount(Double underlyingPayAmount) {
        this.underlyingPayAmount = underlyingPayAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingCollectAmount)
    public Double getUnderlyingCollectAmount() {
        return underlyingCollectAmount;
    }

    /**
     * Message field setter.
     * @param underlyingCollectAmount field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingCollectAmount)
    public void setUnderlyingCollectAmount(Double underlyingCollectAmount) {
        this.underlyingCollectAmount = underlyingCollectAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlementDate)
    public Date getUnderlyingSettlementDate() {
        return underlyingSettlementDate;
    }

    /**
     * Message field setter.
     * @param underlyingSettlementDate field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlementDate)
    public void setUnderlyingSettlementDate(Date underlyingSettlementDate) {
        this.underlyingSettlementDate = underlyingSettlementDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlementStatus)
    public String getUnderlyingSettlementStatus() {
        return underlyingSettlementStatus;
    }

    /**
     * Message field setter.
     * @param underlyingSettlementStatus field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlementStatus)
    public void setUnderlyingSettlementStatus(String underlyingSettlementStatus) {
        this.underlyingSettlementStatus = underlyingSettlementStatus;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UnderlyingPayAmount.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (underlyingPayAmount == null) {
            errorMsg.append(" [UnderlyingPayAmount]");
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
            TagEncoder.encode(bao, TagNum.UnderlyingPayAmount, underlyingPayAmount);
            TagEncoder.encode(bao, TagNum.UnderlyingCollectAmount, underlyingCollectAmount);
            TagEncoder.encodeDate(bao, TagNum.UnderlyingSettlementDate, underlyingSettlementDate);
            TagEncoder.encode(bao, TagNum.UnderlyingSettlementStatus, underlyingSettlementStatus);
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
            case UnderlyingPayAmount:
                underlyingPayAmount = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCollectAmount:
                underlyingCollectAmount = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSettlementDate:
                underlyingSettlementDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSettlementStatus:
                underlyingSettlementStatus = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UnderlyingAmountGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        throw new UnsupportedOperationException("No need for error message in group [UnderlyingAmountGroup].");
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
        b.append("{UnderlyingAmountGroup=");
        printTagValue(b, TagNum.UnderlyingPayAmount, underlyingPayAmount);
        printTagValue(b, TagNum.UnderlyingCollectAmount, underlyingCollectAmount);
        printDateTagValue(b, TagNum.UnderlyingSettlementDate, underlyingSettlementDate);
        printTagValue(b, TagNum.UnderlyingSettlementStatus, underlyingSettlementStatus);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
