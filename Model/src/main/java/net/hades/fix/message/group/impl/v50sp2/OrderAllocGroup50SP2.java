/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderAllocGroup50SP2.java
 *
 * $Id: OrderAllocGroup50SP2.java,v 1.3 2011-04-14 23:44:30 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties2;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties250SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested2PartyGroup;
import net.hades.fix.message.group.OrderAllocGroup;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElementRef;

/**
 * FIX 5.0SP2 implementation of OrderAllocGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="OrdAlloc")
@XmlType(propOrder = {"nested2PartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class OrderAllocGroup50SP2 extends OrderAllocGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> NESTED_PARTIES2_COMP_TAGS = new NestedParties250SP2().getFragmentAllTags();

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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OrderAllocGroup50SP2() {
    }
    
    public OrderAllocGroup50SP2(FragmentContext context) {
        super(context);
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
        this.nestedParties2 = new NestedParties250SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
            ((NestedParties250SP2) nestedParties2).setNested2PartyIDGroups(nested2PartyIDGroups);
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NESTED_PARTIES2_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties2 == null) {
                nestedParties2 = new NestedParties250SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        return "This tag is not supported in [OrderAllocGroup] group version [5.0SP2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
