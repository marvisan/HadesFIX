/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionQtyGroup.java
 *
 * $Id: PosAmtGroup.java,v 1.1 2011-02-10 10:02:16 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PosQtyStatus;
import net.hades.fix.message.type.PosType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Position amount details message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/12/2011, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class PositionQtyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PosType.getValue(),
        TagNum.LongQty.getValue(),
        TagNum.ShortQty.getValue(),
        TagNum.PosQtyStatus.getValue(),
        TagNum.QuantityDate.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * TagNum = 703. Starting with 5.0 version.
     */
    protected PosType posType;

    /**
     * TagNum = 704. Starting with 5.0 version.
     */
    protected Double longQty;
    
    /**
     * TagNum = 705. Starting with 5.0 version.
     */
    protected Double shortQty;

    /**
     * TagNum = 706. Starting with 5.0 version.
     */
    protected PosQtyStatus posQtyStatus;

    /**
     * TagNum = 976. Starting with 5.0 version.
     */
    protected Date quantityDate;

    /**
     * Starting with 5.0 version.
     */
    protected NestedParties nestedParties;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PositionQtyGroup() {
    }

    public PositionQtyGroup(FragmentContext context) {
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosType)
    public PosType getPosType() {
        return posType;
    }

    /**
     * Message field setter.
     * @param posType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosType)
    public void setPosType(PosType posType) {
        this.posType = posType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LongQty)
    public Double getLongQty() {
        return longQty;
    }

    /**
     * Message field setter.
     * @param longQty field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LongQty)
    public void setLongQty(Double longQty) {
        this.longQty = longQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ShortQty)
    public Double getShortQty() {
        return shortQty;
    }

    /**
     * Message field setter.
     * @param shortQty field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.ShortQty)
    public void setShortQty(Double shortQty) {
        this.shortQty = shortQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosQtyStatus)
    public PosQtyStatus getPosQtyStatus() {
        return posQtyStatus;
    }

    /**
     * Message field setter.
     * @param posQtyStatus field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosQtyStatus)
    public void setPosQtyStatus(PosQtyStatus posQtyStatus) {
        this.posQtyStatus = posQtyStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.QuantityDate)
    public Date getQuantityDate() {
        return quantityDate;
    }

    /**
     * Message field setter.
     * @param quantityDate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.QuantityDate)
    public void setQuantityDate(Date quantityDate) {
        this.quantityDate = quantityDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    public NestedParties getNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "5.0")
    public void setNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component to null.<br/>
     */
    @FIXVersion(introduced = "5.0")
    public void clearNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.PosType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (posType == null) {
            errorMsg.append(" [PosType]");
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
            if (posType != null) {
                TagEncoder.encode(bao, TagNum.PosType, posType.getValue());
            }
            TagEncoder.encode(bao, TagNum.LongQty, longQty);
            TagEncoder.encode(bao, TagNum.ShortQty, shortQty);
            if (posQtyStatus != null) {
                TagEncoder.encode(bao, TagNum.PosQtyStatus, posQtyStatus.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.QuantityDate, quantityDate);
            if (nestedParties != null) {
                bao.write(nestedParties.encode(MsgSecureType.ALL_FIELDS));
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
            case PosType:
                posType = PosType.valueFor(new String(tag.value, sessionCharset));
                break;

            case LongQty:
                longQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case ShortQty:
                shortQty = Double.valueOf(new String(tag.value, sessionCharset));
                break;
                
            case PosQtyStatus:
                posQtyStatus = PosQtyStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QuantityDate:
                quantityDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [PositionQtyGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
        b.append("{PositionQtyGroup=");
        printTagValue(b, TagNum.PosType, posType);
        printTagValue(b, TagNum.LongQty, longQty);
        printTagValue(b, TagNum.ShortQty, shortQty);
        printTagValue(b, TagNum.PosQtyStatus, posQtyStatus);
        printDateTagValue(b, TagNum.QuantityDate, quantityDate);
        printTagValue(b, nestedParties);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
