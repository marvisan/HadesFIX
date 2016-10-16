/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderQtyData.java
 *
 * $Id: OrderQtyData.java,v 1.9 2011-05-02 04:04:19 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.RoundingDirection;
import net.hades.fix.message.util.TagEncoder;

/**
 * The OrderQtyData component block contains the fields commonly used for indicating
 * the amount or quantity of an order. Note that when this component block is marked
 * as "required" in a message either one of these three fields must be used to identify
 * the amount: OrderQty, CashOrderQty or OrderPercent (in the case of CIV).
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 14/02/2009, 7:16:44 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class OrderQtyData extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderQty.getValue(),
        TagNum.CashOrderQty.getValue(),
        TagNum.OrderPercent.getValue(),
        TagNum.RoundingDirection.getValue(),
        TagNum.RoundingModulus.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderQty.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 38. Starting with 4.4 version.
     */
    protected Double orderQty;

    /**
     * TagNum = 152. Starting with 4.4 version.
     */
    protected Double cashOrderQty;

    /**
     * TagNum = 516. Starting with 4.4 version.
     */
    protected Double orderPercent;

    /**
     * TagNum = 468. Starting with 4.4 version.
     */
    protected RoundingDirection roundingDirection;

    /**
     * TagNum = 469. Starting with 4.4 version.
     */
    protected Double roundingModulus;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderQtyData() {
        super();
    }

    public OrderQtyData(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.OrderQty)
    public Double getOrderQty() {
        return orderQty;
    }

    /**
     * Message field setter.
     * @param orderQty field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.OrderQty)
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CashOrderQty)
    public Double getCashOrderQty() {
        return cashOrderQty;
    }

    /**
     * Message field setter.
     * @param cashOrderQty field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CashOrderQty)
    public void setCashOrderQty(Double cashOrderQty) {
        this.cashOrderQty = cashOrderQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.OrderPercent)
    public Double getOrderPercent() {
        return orderPercent;
    }

    /**
     * Message field setter.
     * @param orderPercent field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.OrderPercent)
    public void setOrderPercent(Double orderPercent) {
        this.orderPercent = orderPercent;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.RoundingDirection)
    public RoundingDirection getRoundingDirection() {
        return roundingDirection;
    }

    /**
     * Message field setter.
     * @param roundingDirection field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.RoundingDirection)
    public void setRoundingDirection(RoundingDirection roundingDirection) {
        this.roundingDirection = roundingDirection;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.RoundingModulus)
    public Double getRoundingModulus() {
        return roundingModulus;
    }

    /**
     * Message field setter.
     * @param roundingModulus field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.RoundingModulus)
    public void setRoundingModulus(Double roundingModulus) {
        this.roundingModulus = roundingModulus;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.OrderQty.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (orderQty == null) {
            errorMsg.append(" [OrderQty]");
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
            TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            TagEncoder.encode(bao, TagNum.CashOrderQty, cashOrderQty);
            TagEncoder.encode(bao, TagNum.OrderPercent, orderPercent);
            if (roundingDirection != null) {
                TagEncoder.encode(bao, TagNum.RoundingDirection, roundingDirection.getValue());
            }
            TagEncoder.encode(bao, TagNum.RoundingModulus, roundingModulus);
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
            case OrderQty:
                orderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case CashOrderQty:
                cashOrderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case OrderPercent:
                orderPercent = new Double(new String(tag.value, sessionCharset));
                break;

            case RoundingDirection:
                roundingDirection = RoundingDirection.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case RoundingModulus:
                roundingModulus = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [OrderQtyData44] fields.";
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
        b.append("{OrderQtyData=");
        printTagValue(b, TagNum.OrderQty, orderQty);
        printTagValue(b, TagNum.CashOrderQty, cashOrderQty);
        printTagValue(b, TagNum.OrderPercent, orderPercent);
        printTagValue(b, TagNum.RoundingDirection, roundingDirection);
        printTagValue(b, TagNum.RoundingModulus, roundingModulus);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
