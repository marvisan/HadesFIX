/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DistribInstsGroup.java
 *
 * $Id: DistribInstsGroup.java,v 1.1 2011-10-27 09:16:59 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.DistribPaymentMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Distribution instruction group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DistribInstsGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DistribPaymentMethod.getValue(),
        TagNum.DistribPercentage.getValue(),
        TagNum.CashDistribCurr.getValue(),
        TagNum.CashDistribAgentName.getValue(),
        TagNum.CashDistribAgentCode.getValue(),
        TagNum.CashDistribAgentAcctNumber.getValue(),
        TagNum.CashDistribPayRef.getValue(),
        TagNum.CashDistribAgentAcctName.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * TagNum = 477. Starting with 4.3 version.
     */
    protected DistribPaymentMethod distribPaymentMethod;

    /**
     * TagNum = 512. Starting with 4.3 version.
     */
    protected Double distribPercentage;
    
    /**
     * TagNum = 478. Starting with 4.3 version.
     */
    protected Currency cashDistribCurr;
    
    /**
     * TagNum = 498. Starting with 4.3 version.
     */
    protected String cashDistribAgentName;
    
    /**
     * TagNum = 499. Starting with 4.3 version.
     */
    protected String cashDistribAgentCode;
    
    /**
     * TagNum = 500. Starting with 4.3 version.
     */
    protected String cashDistribAgentAcctNumber;
        
    /**
     * TagNum = 501. Starting with 4.3 version.
     */
    protected String cashDistribPayRef;
        
    /**
     * TagNum = 502. Starting with 4.3 version.
     */
    protected String cashDistribAgentAcctName;

    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DistribInstsGroup() {
    }

    public DistribInstsGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DistribPaymentMethod)
    public DistribPaymentMethod getDistribPaymentMethod() {
        return distribPaymentMethod;
    }

    /**
     * Message field setter.
     * @param distribPaymentMethod field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DistribPaymentMethod)
    public void setDistribPaymentMethod(DistribPaymentMethod distribPaymentMethod) {
        this.distribPaymentMethod = distribPaymentMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DistribPercentage)
    public Double getDistribPercentage() {
        return distribPercentage;
    }

    /**
     * Message field setter.
     * @param distribPercentage field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.DistribPercentage)
    public void setDistribPercentage(Double distribPercentage) {
        this.distribPercentage = distribPercentage;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribCurr)
    public Currency getCashDistribCurr() {
        return cashDistribCurr;
    }

    /**
     * Message field setter.
     * @param cashDistribCurr field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribCurr)
    public void setCashDistribCurr(Currency cashDistribCurr) {
        this.cashDistribCurr = cashDistribCurr;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentName)
    public String getCashDistribAgentName() {
        return cashDistribAgentName;
    }

    /**
     * Message field setter.
     * @param cashDistribAgentName field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentName)
    public void setCashDistribAgentName(String cashDistribAgentName) {
        this.cashDistribAgentName = cashDistribAgentName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentCode)
    public String getCashDistribAgentCode() {
        return cashDistribAgentCode;
    }

    /**
     * Message field setter.
     * @param cashDistribAgentCode field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentCode)
    public void setCashDistribAgentCode(String cashDistribAgentCode) {
        this.cashDistribAgentCode = cashDistribAgentCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentAcctNumber)
    public String getCashDistribAgentAcctNumber() {
        return cashDistribAgentAcctNumber;
    }

    /**
     * Message field setter.
     * @param cashDistribAgentAcctNumber field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentAcctNumber)
    public void setCashDistribAgentAcctNumber(String cashDistribAgentAcctNumber) {
        this.cashDistribAgentAcctNumber = cashDistribAgentAcctNumber;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribPayRef)
    public String getCashDistribPayRef() {
        return cashDistribPayRef;
    }

    /**
     * Message field setter.
     * @param cashDistribPayRef field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribPayRef)
    public void setCashDistribPayRef(String cashDistribPayRef) {
        this.cashDistribPayRef = cashDistribPayRef;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentAcctName)
    public String getCashDistribAgentAcctName() {
        return cashDistribAgentAcctName;
    }

    /**
     * Message field setter.
     * @param cashDistribAgentAcctName field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CashDistribAgentAcctName)
    public void setCashDistribAgentAcctName(String cashDistribAgentAcctName) {
        this.cashDistribAgentAcctName = cashDistribAgentAcctName;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.DistribPaymentMethod.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (distribPaymentMethod == null) {
            errorMsg.append(" [DistribPaymentMethod]");
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
            if (distribPaymentMethod != null) {
                TagEncoder.encode(bao, TagNum.DistribPaymentMethod, distribPaymentMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.DistribPercentage, distribPercentage);
            if (cashDistribCurr != null) {
                TagEncoder.encode(bao, TagNum.CashDistribCurr, cashDistribCurr.getValue());
            }
            TagEncoder.encode(bao, TagNum.CashDistribAgentName, cashDistribAgentName);
            TagEncoder.encode(bao, TagNum.CashDistribAgentCode, cashDistribAgentCode);
            TagEncoder.encode(bao, TagNum.CashDistribAgentAcctNumber, cashDistribAgentAcctNumber);
            TagEncoder.encode(bao, TagNum.CashDistribPayRef, cashDistribPayRef);
            TagEncoder.encode(bao, TagNum.CashDistribAgentAcctName, cashDistribAgentAcctName);
            
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
            case DistribPaymentMethod:
                distribPaymentMethod = DistribPaymentMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DistribPercentage:
                distribPercentage = new Double(new String(tag.value, sessionCharset));
                break;

            case CashDistribCurr:
                cashDistribCurr = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case CashDistribAgentName:
                cashDistribAgentName = new String(tag.value, sessionCharset);
                break;

            case CashDistribAgentCode:
                cashDistribAgentCode = new String(tag.value, sessionCharset);
                break;

            case CashDistribAgentAcctNumber:
                cashDistribAgentAcctNumber = new String(tag.value, sessionCharset);
                break;

            case CashDistribPayRef:
                cashDistribPayRef = new String(tag.value, sessionCharset);
                break;

            case CashDistribAgentAcctName:
                cashDistribAgentAcctName = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DistribInstsGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{DistribInstsGroup=");
        printTagValue(b, TagNum.DistribPaymentMethod, distribPaymentMethod);
        printTagValue(b, TagNum.DistribPercentage, distribPercentage);
        printTagValue(b, TagNum.CashDistribCurr, cashDistribCurr);
        printTagValue(b, TagNum.CashDistribAgentName, cashDistribAgentName);
        printTagValue(b, TagNum.CashDistribAgentCode, cashDistribAgentCode);
        printTagValue(b, TagNum.CashDistribAgentAcctNumber, cashDistribAgentAcctNumber);
        printTagValue(b, TagNum.CashDistribPayRef, cashDistribPayRef);
        printTagValue(b, TagNum.CashDistribAgentAcctName, cashDistribAgentAcctName);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
