/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecTypesGroup.java
 *
 * $Id: SecTypesGroup.java,v 1.1 2011-04-27 01:09:59 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

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
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Security type group for SecurityTypes message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SecTypesGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityType.getValue(),
        TagNum.SecuritySubType.getValue(),
        TagNum.Product.getValue(),
        TagNum.CFICode.getValue(),
        TagNum.TransactTime.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 167. Starting with 4.3 version.
     */
    protected String securityType;

    /**
     * TagNum = 762. Starting with 4.4 version.
     */
    protected String securitySubType;

    /**
     * TagNum = 460. Starting with 4.3 version.
     */
    protected Product product;

    /**
     * TagNum = 461. Starting with 4.3 version.
     */
    protected String cfiCode;

    /**
     * TagNum = 60. Starting with 5.0SP2 version.
     */
    protected Date transactTime;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecTypesGroup() {
    }

    public SecTypesGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.SecurityType.getValue();
    }

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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityType)
    public String getSecurityType() {
        return securityType;
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySubType)
    public String getSecuritySubType() {
        return securitySubType;
    }

    /**
     * Message field setter.
     * @param securitySubType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySubType)
    public void setSecuritySubType(String securitySubType) {
        this.securitySubType = securitySubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Product)
    public Product getProduct() {
        return product;
    }

    /**
     * Message field setter.
     * @param product field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Product)
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CFICode)
    public String getCfiCode() {
        return cfiCode;
    }

    /**
     * Message field setter.
     * @param cfiCode field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CFICode)
    public void setCfiCode(String cfiCode) {
        this.cfiCode = cfiCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (securityType == null || securityType.trim().isEmpty()) {
            errorMsg.append(" [SecurityType]");
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
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.SecuritySubType, securitySubType);
            if (product != null) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            TagEncoder.encode(bao, TagNum.CFICode, cfiCode);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
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
            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case SecuritySubType:
                securitySubType = new String(tag.value, sessionCharset);
                break;

            case Product:
                product = Product.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case CFICode:
                cfiCode = new String(tag.value, sessionCharset);
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecTypesGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{SecTypesGroup=");
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.SecuritySubType, securitySubType);
        printTagValue(b, TagNum.Product, product);
        printTagValue(b, TagNum.CFICode, cfiCode);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
