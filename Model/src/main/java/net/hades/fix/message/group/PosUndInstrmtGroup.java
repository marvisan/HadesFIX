/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosUndInstrmtGroup.java
 *
 * $Id: PartyGroup.java,v 1.12 2011-05-21 23:53:24 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;

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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * PosUndInstrmtGroup group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 12/12/2012, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class PosUndInstrmtGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingSettlPrice.getValue(),
        TagNum.UnderlyingSettlPriceType.getValue(),
        TagNum.UnderlyingDeliveryAmount.getValue(),
        TagNum.NoUnderlyingAmounts.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument underlyingInstrument;

    /**
     * TagNum = 732. Starting with 4.4 version.
     */
    protected Double underlyingSettlPrice;

    /**
     * TagNum = 733. Starting with 4.4 version.
     */
    protected SettlPriceType underlyingSettlPriceType;

    /**
     * TagNum = 1037. Starting with 5.0 version.
     */
    protected Double underlyingDeliveryAmount;

    /**
     * TagNum = 984. Starting with 5.0 version.
     */
    protected Integer noUnderlyingAmounts;

    /**
     * Starting with 5.0 version.
     */
    protected UnderlyingAmountGroup[] underlyingAmountGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PosUndInstrmtGroup() {
    }

    public PosUndInstrmtGroup(FragmentContext context) {
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
    public UnderlyingInstrument getUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced = "4.4")
    public void setUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "4.4")
    public void clearUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlPrice)
    public Double getUnderlyingSettlPrice() {
        return underlyingSettlPrice;
    }

    /**
     * Message field setter.
     * @param underlyingSettlPrice field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlPrice)
    public void setUnderlyingSettlPrice(Double underlyingSettlPrice) {
        this.underlyingSettlPrice = underlyingSettlPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlPriceType)
    public SettlPriceType getUnderlyingSettlPriceType() {
        return underlyingSettlPriceType;
    }

    /**
     * Message field setter.
     * @param underlyingSettlPriceType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSettlPriceType)
    public void setUnderlyingSettlPriceType(SettlPriceType underlyingSettlPriceType) {
        this.underlyingSettlPriceType = underlyingSettlPriceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingDeliveryAmount)
    public Double getUnderlyingDeliveryAmount() {
        return underlyingDeliveryAmount;
    }

    /**
     * Message field setter.
     * @param underlyingDeliveryAmount field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnderlyingDeliveryAmount)
    public void setUnderlyingDeliveryAmount(Double underlyingDeliveryAmount) {
        this.underlyingDeliveryAmount = underlyingDeliveryAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoUnderlyingAmounts)
    public Integer getNoUnderlyingAmounts() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingAmountGroup} groups. It will also create an array
     * of {@link UnderlyingAmountGroup} objects and set the <code>underlyingAmountGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingAmountGroups</code> array they will be discarded.<br/>
     * @param noUnderlyingAmounts field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoUnderlyingAmounts)
    public void setNoUnderlyingAmounts(Integer noUnderlyingAmounts) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingAmountGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public UnderlyingAmountGroup[] getUnderlyingAmountGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link UnderlyingAmountGroup} object to the existing array of <code>underlyingAmountGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyingAmounts</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyingAmounts</code> method has been called there will already be a number of objects in the
     * <code>underlyingAmountGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0")
    public UnderlyingAmountGroup addUnderlyingAmountGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingAmountGroup} object from the existing array of <code>underlyingAmountGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyingAmounts</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0")
    public UnderlyingAmountGroup deleteUnderlyingAmountGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingAmountGroup} objects from the <code>underlyingAmountGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyingAmounts</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0")
    public int clearUnderlyingAmountGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UnderlyingSymbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (underlyingInstrument == null || underlyingInstrument.getUnderlyingSymbol() == null || underlyingInstrument.getUnderlyingSymbol().trim().isEmpty()) {
            errorMsg.append(" [UnderlyingSymbol]");
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
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.UnderlyingSettlPrice, underlyingSettlPrice);
            if (underlyingSettlPriceType != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingSettlPriceType, underlyingSettlPriceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingDeliveryAmount, underlyingDeliveryAmount);
            if (noUnderlyingAmounts != null) {
                TagEncoder.encode(bao, TagNum.NoUnderlyingAmounts, noUnderlyingAmounts);
                if (underlyingAmountGroups != null && underlyingAmountGroups.length == noUnderlyingAmounts.intValue()) {
                    for (int i = 0; i < noUnderlyingAmounts.intValue(); i++) {
                        if (underlyingAmountGroups[i] != null) {
                            bao.write(underlyingAmountGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingAmountGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyingAmounts.getValue(), error);
                }
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
            case UnderlyingSettlPrice:
                underlyingSettlPrice = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSettlPriceType:
                underlyingSettlPriceType = SettlPriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnderlyingDeliveryAmount:
                underlyingDeliveryAmount = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case NoUnderlyingAmounts:
                noUnderlyingAmounts = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [PosUndInstrmtGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{PosUndInstrmtGroup=");
        printTagValue(b, underlyingInstrument);
        printTagValue(b, TagNum.UnderlyingSettlPrice, underlyingSettlPrice);
        printTagValue(b, TagNum.UnderlyingSettlPriceType, underlyingSettlPriceType);
        printTagValue(b, TagNum.UnderlyingDeliveryAmount, underlyingDeliveryAmount);
        printTagValue(b, TagNum.NoUnderlyingAmounts, noUnderlyingAmounts);
        printTagValue(b, underlyingAmountGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>

}
