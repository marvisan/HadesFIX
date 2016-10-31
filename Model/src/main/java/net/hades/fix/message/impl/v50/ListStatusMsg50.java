/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusMsg50.java
 *
 * $Id: ListStatusMsg50.java,v 1.2 2011-04-14 23:44:42 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.ListStatusMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderStatusGroup;
import net.hades.fix.message.group.impl.v50.OrderStatusGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ListOrderStatus;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.type.ListStatusType;

/**
 * FIX version 5.0 NewOrderListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="ListStat")
@XmlType(propOrder = {"header", "orderStatusGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class ListStatusMsg50 extends ListStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ORDER_STATUS_GROUP_TAGS = new OrderStatusGroup50().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(ORDER_STATUS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ORDER_STATUS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ListStatusMsg50() {
        super();
    }

    public ListStatusMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ListStatusMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ListStatusMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        ListStatusMsg50 fixml = (ListStatusMsg50) fragment;
        if (fixml.getListID() != null) {
            listID = fixml.getListID();
        }
        if (fixml.getListStatusType() != null) {
            listStatusType = fixml.getListStatusType();
        }
        if (fixml.getNoRpts() != null) {
            noRpts = fixml.getNoRpts();
        }
        if (fixml.getListOrderStatus() != null) {
            listOrderStatus = fixml.getListOrderStatus();
        }
        if (fixml.getContingencyType() != null) {
            contingencyType = fixml.getContingencyType();
        }
        if (fixml.getListRejectReason() != null) {
            listRejectReason = fixml.getListRejectReason();
        }
        if (fixml.getRptSeq() != null) {
            rptSeq = fixml.getRptSeq();
        }
        if (fixml.getListStatusText() != null) {
            listStatusText = fixml.getListStatusText();
        }
        if (fixml.getEncodedListStatusTextLen() != null) {
            encodedListStatusTextLen = fixml.getEncodedListStatusTextLen();
            encodedListStatusText = fixml.getEncodedListStatusText();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getTotNoOrders() != null) {
            totNoOrders = fixml.getTotNoOrders();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getOrderStatusGroups() != null && fixml.getOrderStatusGroups().length > 0) {
            setOrderStatusGroups(fixml.getOrderStatusGroups());
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @XmlAttribute(name = "ListStatTyp")
    @Override
    public ListStatusType getListStatusType() {
        return listStatusType;
    }

    @Override
    public void setListStatusType(ListStatusType listStatusType) {
        this.listStatusType = listStatusType;
    }

    @Override
    public String getWaveNo() {
        return waveNo;
    }

    @Override
    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }

    @XmlAttribute(name = "NoRpts")
    @Override
    public Integer getNoRpts() {
        return noRpts;
    }

    @Override
    public void setNoRpts(Integer noRpts) {
        this.noRpts = noRpts;
    }

    @XmlAttribute(name = "ListOrdStat")
    @Override
    public ListOrderStatus getListOrderStatus() {
        return listOrderStatus;
    }

    @Override
    public void setListOrderStatus(ListOrderStatus listOrderStatus) {
        this.listOrderStatus = listOrderStatus;
    }

    @XmlAttribute(name = "RptSeq")
    @Override
    public Integer getRptSeq() {
        return rptSeq;
    }

    @Override
    public void setRptSeq(Integer rptSeq) {
        this.rptSeq = rptSeq;
    }

    @XmlAttribute(name = "ListStatText")
    @Override
    public String getListStatusText() {
        return listStatusText;
    }

    @Override
    public void setListStatusText(String listStatusText) {
        this.listStatusText = listStatusText;
    }

    @XmlAttribute(name = "EncListStatTextLen")
    @Override
    public Integer getEncodedListStatusTextLen() {
        return encodedListStatusTextLen;
    }

    @Override
    public void setEncodedListStatusTextLen(Integer encodedListStatusTextLen) {
        this.encodedListStatusTextLen = encodedListStatusTextLen;
    }

    @XmlAttribute(name = "EncListStatText")
    @Override
    public byte[] getEncodedListStatusText() {
        return encodedListStatusText;
    }

    @Override
    public void setEncodedListStatusText(byte[] encodedListStatusText) {
        this.encodedListStatusText = encodedListStatusText;
        if (encodedListStatusTextLen == null) {
            encodedListStatusTextLen = new Integer(encodedListStatusText.length);
        }
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @XmlAttribute(name = "TotNoOrds")
    @Override
    public Integer getTotNoOrders() {
        return totNoOrders;
    }

    @Override
    public void setTotNoOrders(Integer totNoOrders) {
        this.totNoOrders = totNoOrders;
    }

    @XmlAttribute(name = "LastFragment")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getLastFragment() {
        return lastFragment;
    }

    @Override
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    @Override
    public Integer getNoOrders() {
        return noOrders;
    }

    @Override
    public void setNoOrders(Integer noOrders) {
        this.noOrders = noOrders;
        if (noOrders != null) {
            orderStatusGroups = new OrderStatusGroup[noOrders.intValue()];
            for (int i = 0; i < orderStatusGroups.length; i++) {
                orderStatusGroups[i] = new OrderStatusGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public OrderStatusGroup[] getOrderStatusGroups() {
        return orderStatusGroups;
    }

    public void setOrderStatusGroups(OrderStatusGroup[] orderStatusGroups) {
        this.orderStatusGroups = orderStatusGroups;
        if (orderStatusGroups != null) {
            noOrders = new Integer(orderStatusGroups.length);
        }
    }

    @Override
    public OrderStatusGroup addOrderStatusGroup() {
        OrderStatusGroup group = new OrderStatusGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<OrderStatusGroup> groups = new ArrayList<OrderStatusGroup>();
        if (orderStatusGroups != null && orderStatusGroups.length > 0) {
            groups = new ArrayList<OrderStatusGroup>(Arrays.asList(orderStatusGroups));
        }
        groups.add(group);
        orderStatusGroups = groups.toArray(new OrderStatusGroup[groups.size()]);
        noOrders = new Integer(orderStatusGroups.length);

        return group;
    }

    @Override
    public OrderStatusGroup deleteOrderStatusGroup(int index) {
        OrderStatusGroup result = null;
        if (orderStatusGroups != null && orderStatusGroups.length > 0 && orderStatusGroups.length > index) {
            List<OrderStatusGroup> groups = new ArrayList<OrderStatusGroup>(Arrays.asList(orderStatusGroups));
            result = groups.remove(index);
            orderStatusGroups = groups.toArray(new OrderStatusGroup[groups.size()]);
            if (orderStatusGroups.length > 0) {
                noOrders = new Integer(orderStatusGroups.length);
            } else {
                orderStatusGroups = null;
                noOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearOrderStatusGroups() {
        int result = 0;
        if (orderStatusGroups != null && orderStatusGroups.length > 0) {
            result = orderStatusGroups.length;
            orderStatusGroups = null;
            noOrders = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (ORDER_STATUS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noOrders != null && noOrders.intValue() > 0) {
                message.reset();
                orderStatusGroups = new OrderStatusGroup[noOrders.intValue()];
                for (int i = 0; i < noOrders.intValue(); i++) {
                    OrderStatusGroup group = new OrderStatusGroup50(context);
                    group.decode(message);
                    orderStatusGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ListStatusMsg] message version [5.0].";
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
}
