/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEvents.java
 *
 * $Id: ComplexEvents.java,v 1.11 2010-11-23 10:20:20 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ComplexEventCondition;
import net.hades.fix.message.type.ComplexEventPriceBoundaryMethod;
import net.hades.fix.message.type.ComplexEventPriceTimeType;
import net.hades.fix.message.type.ComplexEventType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Complex events component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 04/06/2009, 10:29:22 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ComplexEvents extends Component {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ComplexEventType.getValue(),
        TagNum.ComplexOptPayoutAmount.getValue(),
        TagNum.ComplexEventPrice.getValue(),
        TagNum.ComplexEventPriceBoundaryMethod.getValue(),
        TagNum.ComplexEventPriceBoundaryPrecision.getValue(),
        TagNum.ComplexEventPriceTimeType.getValue(),
        TagNum.ComplexEventCondition.getValue()
    }));
    
    protected static final Set<Integer> START_DATA_TAGS = null;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 1484. Starting with 5.0SP2 version.
     */
    protected ComplexEventType complexEventType;
    
    /**
     * TagNum = 1485. Starting with 5.0SP2 version.
     */
    protected Double complexOptPayoutAmount;

    /**
     * TagNum = 1486. Starting with 5.0SP2 version.
     */
    protected Double complexEventPrice;

    /**
     * TagNum = 1487. Starting with 5.0SP2 version.
     */
    protected ComplexEventPriceBoundaryMethod complexEventPriceBoundaryMethod;

    /**
     * TagNum = 1488. Starting with 5.0SP2 version.
     */
    protected Double complexEventPriceBoundaryPrecision;

    /**
     * TagNum = 1489. Starting with 5.0SP2 version.
     */
    protected ComplexEventPriceTimeType complexEventPriceTimeType;

    /**
     * TagNum = 1490. Starting with 5.0SP2 version.
     */
    protected ComplexEventCondition complexEventCondition;

    /**
     * Starting with 5.0SP2 version.
     */
    protected ComplexEventDates complexEventDates;
   
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ComplexEvents() {
        super();
    }
    
    public ComplexEvents(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventType)
    public ComplexEventType getComplexEventType() {
        return complexEventType;
    }

    /**
     * Message field setter.
     * @param complexEventType field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventType)
    public void setComplexEventType(ComplexEventType complexEventType) {
        this.complexEventType = complexEventType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexOptPayoutAmount)
    public Double getComplexOptPayoutAmount() {
        return complexOptPayoutAmount;
    }

    /**
     * Message field setter.
     * @param complexOptPayoutAmount field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexOptPayoutAmount)
    public void setComplexOptPayoutAmount(Double complexOptPayoutAmount) {
        this.complexOptPayoutAmount = complexOptPayoutAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPrice)
    public Double getComplexEventPrice() {
        return complexEventPrice;
    }

    /**
     * Message field setter.
     * @param complexEventPrice field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPrice)
    public void setComplexEventPrice(Double complexEventPrice) {
        this.complexEventPrice = complexEventPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPriceBoundaryMethod)
    public ComplexEventPriceBoundaryMethod getComplexEventPriceBoundaryMethod() {
        return complexEventPriceBoundaryMethod;
    }

    /**
     * Message field setter.
     * @param complexEventPriceBoundaryMethod field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPriceBoundaryMethod)
    public void setComplexEventPriceBoundaryMethod(ComplexEventPriceBoundaryMethod complexEventPriceBoundaryMethod) {
        this.complexEventPriceBoundaryMethod = complexEventPriceBoundaryMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPriceBoundaryPrecision)
    public Double getComplexEventPriceBoundaryPrecision() {
        return complexEventPriceBoundaryPrecision;
    }

    /**
     * Message field setter.
     * @param complexEventPriceBoundaryPrecision field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPriceBoundaryPrecision)
    public void setComplexEventPriceBoundaryPrecision(Double complexEventPriceBoundaryPrecision) {
        this.complexEventPriceBoundaryPrecision = complexEventPriceBoundaryPrecision;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPriceTimeType)
    public ComplexEventPriceTimeType getComplexEventPriceTimeType() {
        return complexEventPriceTimeType;
    }

    /**
     * Message field setter.
     * @param complexEventPriceTimeType field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventPriceTimeType)
    public void setComplexEventPriceTimeType(ComplexEventPriceTimeType complexEventPriceTimeType) {
        this.complexEventPriceTimeType = complexEventPriceTimeType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventCondition)
    public ComplexEventCondition getComplexEventCondition() {
        return complexEventCondition;
    }

    /**
     * Message field setter.
     * @param complexEventCondition field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ComplexEventCondition)
    public void setComplexEventCondition(ComplexEventCondition complexEventCondition) {
        this.complexEventCondition = complexEventCondition;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    public ComplexEventDates getComplexEventDates() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the correct implementation.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void setComplexEventDates() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void clearComplexEventDates() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.ComplexEventType.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (complexEventType == null) {
            errorMsg.append(" [ComplexEventType]");
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
            if (complexEventType != null) {
                TagEncoder.encode(bao, TagNum.ComplexEventType, complexEventType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ComplexOptPayoutAmount, complexOptPayoutAmount);
            TagEncoder.encode(bao, TagNum.ComplexEventPrice, complexEventPrice);
            if (complexEventPriceBoundaryMethod != null) {
                TagEncoder.encode(bao, TagNum.ComplexEventPriceBoundaryMethod, complexEventPriceBoundaryMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.ComplexEventPriceBoundaryPrecision, complexEventPriceBoundaryPrecision);
            if (complexEventPriceTimeType != null) {
                TagEncoder.encode(bao, TagNum.ComplexEventPriceTimeType, complexEventPriceTimeType.getValue());
            }
            if (complexEventCondition != null) {
                TagEncoder.encode(bao, TagNum.ComplexEventCondition, complexEventCondition.getValue());
            }
            if (complexEventDates != null) {
                bao.write(complexEventDates.encode(MsgSecureType.ALL_FIELDS));
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
            case ComplexEventType:
                complexEventType = ComplexEventType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ComplexOptPayoutAmount:
                complexOptPayoutAmount = new Double(new String(tag.value, sessionCharset));
                break;

            case ComplexEventPrice:
                complexEventPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case ComplexEventPriceBoundaryMethod:
                complexEventPriceBoundaryMethod = ComplexEventPriceBoundaryMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ComplexEventPriceBoundaryPrecision:
                complexEventPriceBoundaryPrecision = new Double(new String(tag.value, sessionCharset));
                break;

            case ComplexEventPriceTimeType:
                complexEventPriceTimeType = ComplexEventPriceTimeType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ComplexEventCondition:
                complexEventCondition = ComplexEventCondition.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ComplexEvents] fields.";
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
        b.append("{ComplexEvents=");
        printTagValue(b, TagNum.ComplexEventType, complexEventType);
        printTagValue(b, TagNum.ComplexOptPayoutAmount, complexOptPayoutAmount);
        printTagValue(b, TagNum.ComplexEventPrice, complexEventPrice);
        printTagValue(b, TagNum.ComplexEventPriceBoundaryMethod, complexEventPriceBoundaryMethod);
        printTagValue(b, TagNum.ComplexEventPriceBoundaryPrecision, complexEventPriceBoundaryPrecision);
        printTagValue(b, TagNum.ComplexEventPriceTimeType, complexEventPriceTimeType);
        printTagValue(b, TagNum.ComplexEventCondition, complexEventCondition);
        printTagValue(b, complexEventDates);
        b.append("}");

        return b.toString();
    }
    
    // </editor-fold>
    
}
