/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderAllocGroup.java
 *
 * $Id: OrderAllocGroup.java,v 1.1 2011-02-06 04:44:15 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.NestedParties2;
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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;

/**
 * Order allocation group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class OrderAllocGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ClOrdID.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.ListID.getValue(),
        TagNum.OrderQty.getValue(),
        TagNum.OrderAvgPx.getValue(),
        TagNum.OrderBookingQty.getValue(),
        TagNum.WaveNo.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 11 REQUIRED. Starting with 4.0 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 37. Starting with 4.0 version.
     */
    protected String orderID;

    /**
     * TagNum = 198. Starting with 4.1 version.
     */
    protected String secondaryOrderID;

    /**
     * TagNum = 526. Starting with 4.3 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 66. Starting with 4.0 version.
     */
    protected String listID;

    /**
     * Starting with 4.4 version.
     */
    protected NestedParties2 nestedParties2;

    /**
     * TagNum = 38. Starting with 4.4 version.
     */
    protected Double orderQty;

    /**
     * TagNum = 799. Starting with 4.4 version.
     */
    protected Double orderAvgPx;

    /**
     * TagNum = 799. Starting with 4.4 version.
     */
    protected Double orderBookingQty;

    /**
     * TagNum = 105. Starting with 4.4 version.
     */
    protected String waveNo;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OrderAllocGroup() {
    }
    
    public OrderAllocGroup(FragmentContext context) {
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    /**
     * Message field setter.
     * @param secondaryOrderID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    /**
     * Message field setter.
     * @param secondaryClOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListID)
    public String getListID() {
        return listID;
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListID)
    public void setListID(String listID) {
        this.listID = listID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public NestedParties2 getNestedParties2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties2} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void setNestedParties2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties2} component to null.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void clearNestedParties2() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderQty)
    public Double getOrderQty() {
        return orderQty;
    }

    /**
     * Message field setter.
     * @param orderQty field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderQty)
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderAvgPx)
    public Double getOrderAvgPx() {
        return orderAvgPx;
    }

    /**
     * Message field setter.
     * @param orderAvgPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderAvgPx)
    public void setOrderAvgPx(Double orderAvgPx) {
        this.orderAvgPx = orderAvgPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderBookingQty)
    public Double getOrderBookingQty() {
        return orderBookingQty;
    }

    /**
     * Message field setter.
     * @param orderBookingQty field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderBookingQty)
    public void setOrderBookingQty(Double orderBookingQty) {
        this.orderBookingQty = orderBookingQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public String getWaveNo() {
        return waveNo;
    }

    /**
     * Message field setter.
     * @param waveNo field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.3")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.ClOrdID.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (clOrdID == null || clOrdID.trim().isEmpty()) {
            errorMsg.append(" [ClOrdID]");
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
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.ListID, listID);
            if (nestedParties2 != null) {
                bao.write(nestedParties2.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            TagEncoder.encode(bao, TagNum.OrderAvgPx, orderAvgPx);
            TagEncoder.encode(bao, TagNum.OrderBookingQty, orderBookingQty);
            TagEncoder.encode(bao, TagNum.WaveNo, waveNo);
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
            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

           case SecondaryOrderID:
                secondaryOrderID = new String(tag.value, sessionCharset);
                break;

            case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;

            case OrderQty:
                orderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case OrderAvgPx:
                orderAvgPx = new Double(new String(tag.value, sessionCharset));
                break;

            case OrderBookingQty:
                orderBookingQty = new Double(new String(tag.value, sessionCharset));
                break;

            case WaveNo:
                waveNo = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [OrderAllocGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        b.append("{OrderAllocGroup=");
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.SecondaryOrderID, secondaryOrderID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, nestedParties2);
        printTagValue(b, TagNum.OrderQty, orderQty);
        printTagValue(b, TagNum.OrderAvgPx, orderAvgPx);
        printTagValue(b, TagNum.OrderBookingQty, orderBookingQty);
        printTagValue(b, TagNum.WaveNo, waveNo);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
