/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CommissionData.java
 *
 * $Id: CommissionData.java,v 1.1 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.util.TagEncoder;

/**
 * The CommissionDate component block is used to carry commission information
 * such as the type of commission and the rate.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 14/02/2009, 2:52:41 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CommissionData extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Commission.getValue(),
        TagNum.CommType.getValue(),
        TagNum.CommCurrency.getValue(),
        TagNum.FundRenewWaiv.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Commission.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 12. Starting with 4.3 version.
     */
    protected Double commission;

    /**
     * TagNum = 13. Starting with 4.3 version.
     */
    protected CommType commType;

    /**
     * TagNum = 479. Starting with 4.3 version.
     */
    protected Currency commCurrency;

    /**
     * TagNum = 497. Starting with 4.3 version.
     */
    protected Boolean fundRenewWaiv;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public CommissionData() {
    }

    public CommissionData(FragmentContext context) {
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
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Commission)
    public Double getCommission() {
        return commission;
    }

    /**
     * Message field setter.
     * @param commission field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Commission)
    public void setCommission(Double commission) {
        this.commission = commission;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CommType)
    public CommType getCommType() {
        return commType;
    }

    /**
     * Message field setter.
     * @param commType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CommType)
    public void setCommType(CommType commType) {
        this.commType = commType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CommCurrency)
    public Currency getCommCurrency() {
        return commCurrency;
    }

    /**
     * Message field setter.
     * @param commCurrency field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CommCurrency)
    public void setCommCurrency(Currency commCurrency) {
        this.commCurrency = commCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.FundRenewWaiv)
    public Boolean getFundRenewWaiv() {
        return fundRenewWaiv;
    }

    /**
     * Message field setter.
     * @param fundRenewWaiv field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.FundRenewWaiv)
    public void setFundRenewWaiv(Boolean fundRenewWaiv) {
        this.fundRenewWaiv = fundRenewWaiv;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.Commission.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            TagEncoder.encode(bao, TagNum.Commission, commission);
            if (commType != null) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            if (commCurrency != null) {
                TagEncoder.encode(bao, TagNum.CommCurrency, commCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.FundRenewWaiv, fundRenewWaiv);

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
            case Commission:
                commission = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case CommType:
                commType = CommType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case CommCurrency:
                commCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case FundRenewWaiv:
                fundRenewWaiv = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DisplayInstruction] fields.";
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
        b.append("{CommissionData=");
        printTagValue(b, TagNum.Commission, commission);
        printTagValue(b, TagNum.CommType, commType);
        printTagValue(b, TagNum.CommCurrency, commCurrency);
        printTagValue(b, TagNum.FundRenewWaiv, fundRenewWaiv);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
