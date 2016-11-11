/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegAllocGroup.java
 *
 * $Id: LegAllocGroup.java,v 1.2 2011-07-14 07:06:18 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.NestedParties2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Instrument leg allocation group for Execution message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LegAllocGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegAllocAccount.getValue(),
        TagNum.LegIndividualAllocID.getValue(),
        TagNum.LegAllocQty.getValue(),
        TagNum.LegAllocAcctIDSource.getValue(),
        TagNum.LegAllocSettlCurrency.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 671. Starting with 5.0SP1 version.
     */
    protected String legAllocAccount;

    /**
     * TagNum = 672. Starting with 5.0SP1 version.
     */
    protected String legIndividualAllocID;

    /**
     * Starting with 5.0SP1 version.
     */
    protected NestedParties2 nestedParties2;

    /**
     * TagNum = 673. Starting with 5.0SP1 version.
     */
    protected Double legAllocQty;

    /**
     * TagNum = 674. Starting with 5.0SP1 version.
     */
    protected String legAllocAcctIDSource;

    /**
     * TagNum = 675. Starting with 4.4 version.
     */
    protected Currency legSettlCurrency;

    /**
     * TagNum = 1367. Starting with 5.0SP1 version.
     */
    protected Currency legAllocSettlCurrency;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegAllocGroup() {
    }

    public LegAllocGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.LegAllocAccount.getValue();
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocAccount)
    public String getLegAllocAccount() {
        return legAllocAccount;
    }

    /**
     * Message field setter.
     * @param legAllocAccount field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocAccount)
    public void setLegAllocAccount(String legAllocAccount) {
        this.legAllocAccount = legAllocAccount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegIndividualAllocID)
    public String getLegIndividualAllocID() {
        return legIndividualAllocID;
    }

    /**
     * Message field setter.
     * @param legIndividualAllocID field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegIndividualAllocID)
    public void setLegIndividualAllocID(String legIndividualAllocID) {
        this.legIndividualAllocID = legIndividualAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public NestedParties2 getNestedParties2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties2} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setNestedParties2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties2} component to null.<br/>
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearNestedParties2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocQty)
    public Double getLegAllocQty() {
        return legAllocQty;
    }

    /**
     * Message field setter.
     * @param legAllocQty field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocQty)
    public void setLegAllocQty(Double legAllocQty) {
        this.legAllocQty = legAllocQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocAcctIDSource)
    public String getLegAllocAcctIDSource() {
        return legAllocAcctIDSource;
    }

    /**
     * Message field setter.
     * @param legAllocAcctIDSource field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocAcctIDSource)
    public void setLegAllocAcctIDSource(String legAllocAcctIDSource) {
        this.legAllocAcctIDSource = legAllocAcctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegSettlCurrency)
    public Currency getLegSettlCurrency() {
        return legSettlCurrency;
    }

    /**
     * Message field setter.
     * @param legSettlCurrency field value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegSettlCurrency)
    public void setLegSettlCurrency(Currency legSettlCurrency) {
        this.legSettlCurrency = legSettlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocSettlCurrency)
    public Currency getLegAllocSettlCurrency() {
        return legAllocSettlCurrency;
    }

    /**
     * Message field setter.
     * @param LegAllocSettlCurrency field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegAllocSettlCurrency)
    public void setLegAllocSettlCurrency(Currency LegAllocSettlCurrency) {
        this.legAllocSettlCurrency = LegAllocSettlCurrency;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (legAllocAccount == null || legAllocAccount.trim().isEmpty()) {
            errorMsg.append(" [LegAllocAccount]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.LegAllocAccount, legAllocAccount);
            TagEncoder.encode(bao, TagNum.LegIndividualAllocID, legIndividualAllocID);
            if (nestedParties2 != null) {
                bao.write(nestedParties2.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.LegAllocQty, legAllocQty);
            TagEncoder.encode(bao, TagNum.LegAllocAcctIDSource, legAllocAcctIDSource);
            if (legSettlCurrency != null) {
                TagEncoder.encode(bao, TagNum.LegSettlCurrency, legSettlCurrency.getValue());
            }
            if (legAllocSettlCurrency != null) {
                TagEncoder.encode(bao, TagNum.LegAllocSettlCurrency, legAllocSettlCurrency.getValue());
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
            case LegAllocAccount:
                legAllocAccount = new String(tag.value, sessionCharset);
                break;

            case LegIndividualAllocID:
                legIndividualAllocID = new String(tag.value, sessionCharset);
                break;

            case LegAllocQty:
                legAllocQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegAllocAcctIDSource:
                legAllocAcctIDSource = new String(tag.value, sessionCharset);
                break;

            case LegSettlCurrency:
                legSettlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegAllocSettlCurrency:
                legAllocSettlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [LegAllocGroup] fields.";
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
        b.append("{LegAllocGroup=");
        printTagValue(b, TagNum.LegAllocAccount, legAllocAccount);
        printTagValue(b, TagNum.LegIndividualAllocID, legIndividualAllocID);
        printTagValue(b, nestedParties2);
        printTagValue(b, TagNum.LegAllocQty, legAllocQty);
        printTagValue(b, TagNum.LegAllocAcctIDSource, legAllocAcctIDSource);
        printTagValue(b, TagNum.LegSettlCurrency, legSettlCurrency);
        printTagValue(b, TagNum.LegAllocSettlCurrency, legAllocSettlCurrency);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
