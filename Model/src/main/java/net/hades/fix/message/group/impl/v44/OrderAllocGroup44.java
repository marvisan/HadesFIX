/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderAllocGroup44.java
 *
 * $Id: OrderAllocGroup44.java,v 1.3 2011-04-14 23:44:45 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties2;
import net.hades.fix.message.comp.impl.v44.NestedParties244;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested2PartyGroup;
import net.hades.fix.message.group.OrderAllocGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElementRef;

import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of OrderAllocGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="OrdAlloc")
@XmlType(propOrder = {"nested2PartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class OrderAllocGroup44 extends OrderAllocGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> NESTED_PARTIES2_COMP_TAGS = new NestedParties244().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES2_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES2_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OrderAllocGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public OrderAllocGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

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

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "OrdID2")
    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    @XmlAttribute(name = "ClOrdID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @XmlAttribute(name = "ListID")
    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @Override
    public NestedParties2 getNestedParties2() {
        return nestedParties2;
    }

    @Override
    public void setNestedParties2() {
        this.nestedParties2 = new NestedParties244(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
    }

    @Override
    public void clearNestedParties2() {
        this.nestedParties2 = null;
    }

    @XmlElementRef
    public Nested2PartyGroup[] getNested2PartyIDGroups() {
        return nestedParties2 == null ? null : nestedParties2.getNested2PartyIDGroups();
    }

    public void setNested2PartyIDGroups(Nested2PartyGroup[] nested2PartyIDGroups) {
        if (nested2PartyIDGroups != null) {
            if (nestedParties2 == null) {
                setNestedParties2();
            }
            ((NestedParties244) nestedParties2).setNested2PartyIDGroups(nested2PartyIDGroups);
        }
    }

    @XmlAttribute(name = "Qty")
    @Override
    public Double getOrderQty() {
        return orderQty;
    }

    @Override
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    @XmlAttribute(name = "AvgPx")
    @Override
    public Double getOrderAvgPx() {
        return orderAvgPx;
    }

    @Override
    public void setOrderAvgPx(Double orderAvgPx) {
        this.orderAvgPx = orderAvgPx;
    }

    @XmlAttribute(name = "BkngQty")
    @Override
    public Double getOrderBookingQty() {
        return orderBookingQty;
    }

    @Override
    public void setOrderBookingQty(Double orderBookingQty) {
        this.orderBookingQty = orderBookingQty;
    }

    @Override
    public String getWaveNo() {
        return waveNo;
    }

    @Override
    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
            }
            if (nestedParties2 != null) {
                bao.write(nestedParties2.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.OrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            }
            if (MsgUtil.isTagInList(TagNum.OrderAvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderAvgPx, orderAvgPx);
            }
            if (MsgUtil.isTagInList(TagNum.OrderBookingQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderBookingQty, orderBookingQty);
            }
            if (MsgUtil.isTagInList(TagNum.WaveNo, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.WaveNo, waveNo);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NESTED_PARTIES2_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties2 == null) {
                nestedParties2 = new NestedParties244(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
            nestedParties2.decode(tag, message);
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderAllocGroup] group version [4.4].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
