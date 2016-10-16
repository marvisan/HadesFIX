/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PreTradeAllocGroup.java
 *
 * $Id: PreTradeAllocGroup.java,v 1.1 2010-12-05 08:13:27 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

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
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;

/**
 * Pre trade allocation group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class PreTradeAllocGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AllocAccount.getValue(),
        TagNum.AllocAcctIDSource.getValue(),
        TagNum.AllocSettlCurrency.getValue(),
        TagNum.IndividualAllocID.getValue(),
        TagNum.AllocQty.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 79. Starting with 4.2 version.
     */
    protected String allocAccount;

    /**
     * TagNum = 661. Starting with 4.4 version.
     */
    protected AcctIDSource allocAcctIDSource;

    /**
     * TagNum = 736. Starting with 4.4 version.
     */
    protected Currency allocSettlCurrency;

    /**
     * TagNum = 467. Starting with 4.2 version.
     */
    protected String individualAllocID;

    /**
     * Starting with 4.3 version.
     */
    protected NestedParties nestedParties;

    /**
     * TagNum = 80. Starting with 4.2 version.
     */
    protected Double allocQty;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PreTradeAllocGroup() {
    }

    public PreTradeAllocGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.AllocAccount.getValue();
    }

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
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.AllocAccount)
    public String getAllocAccount() {
        return allocAccount;
    }

    /**
     * Message field setter.
     * @param allocAccount field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.AllocAccount)
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AllocAcctIDSource)
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    /**
     * Message field setter.
     * @param allocAcctIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AllocAcctIDSource)
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.AllocQty)
    public Double getAllocQty() {
        return allocQty;
    }

    /**
     * Message field setter.
     * @param allocQty field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.AllocQty)
    public void setAllocQty(Double allocQty) {
        this.allocQty = allocQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AllocSettlCurrency)
    public Currency getAllocSettlCurrency() {
        return allocSettlCurrency;
    }

    /**
     * Message field setter.
     * @param allocSettlCurrency field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AllocSettlCurrency)
    public void setAllocSettlCurrency(Currency allocSettlCurrency) {
        this.allocSettlCurrency = allocSettlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.IndividualAllocID)
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    /**
     * Message field setter.
     * @param individualAllocID field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.IndividualAllocID)
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (allocAccount == null || allocAccount.trim().isEmpty()) {
            errorMsg.append(" [AllocAccount]");
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
            TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            if (allocAcctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AllocAcctIDSource, allocAcctIDSource.getValue());
            }
            if (allocSettlCurrency != null) {
                TagEncoder.encode(bao, TagNum.AllocSettlCurrency, allocSettlCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            if (nestedParties != null) {
                bao.write(nestedParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.AllocQty, allocQty);
            
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
            case AllocAccount:
                allocAccount = new String(tag.value, sessionCharset);
                break;

            case AllocAcctIDSource:
                allocAcctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocSettlCurrency:
                allocSettlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case IndividualAllocID:
                individualAllocID = new String(tag.value, sessionCharset);
                break;

            case AllocQty:
                allocQty = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [PreTradeAllocGroup] fields.";
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
        b.append("{PreTradeAllocGroup=");
        printTagValue(b, TagNum.AllocAccount, allocAccount);
        printTagValue(b, TagNum.AllocAcctIDSource, allocAcctIDSource);
        printTagValue(b, TagNum.AllocSettlCurrency, allocSettlCurrency);
        printTagValue(b, TagNum.IndividualAllocID, individualAllocID);
        printTagValue(b, nestedParties);
        printTagValue(b, TagNum.AllocQty, allocQty);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
