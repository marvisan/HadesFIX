/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsg44.java
 *
 * $Id: NewOrderListMsg44.java,v 1.4 2011-04-14 23:44:37 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderListGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.impl.v44.OrderListGroup44;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ListExecInstType;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.ProgRptReqs;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 NewOrderListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="NewOrdList")
@XmlType(propOrder = {"header", "orderListGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class NewOrderListMsg44 extends NewOrderListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ORDER_LIST_GROUP_TAGS = new OrderListGroup44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(ORDER_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ORDER_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewOrderListMsg44() {
        super();
    }

    public NewOrderListMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewOrderListMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewOrderListMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        NewOrderListMsg44 fixml = (NewOrderListMsg44) fragment;
        if (fixml.getListID() != null) {
            listID = fixml.getListID();
        }
        if (fixml.getBidID() != null) {
            bidID = fixml.getBidID();
        }
        if (fixml.getClientBidID() != null) {
            clientBidID = fixml.getClientBidID();
        }
        if (fixml.getProgRptReqs() != null) {
            progRptReqs = fixml.getProgRptReqs();
        }
        if (fixml.getBidType() != null) {
            bidType = fixml.getBidType();
        }
        if (fixml.getProgPeriodInterval() != null) {
            progPeriodInterval = fixml.getProgPeriodInterval();
        }
        if (fixml.getCancellationRights() != null) {
            cancellationRights = fixml.getCancellationRights();
        }
        if (fixml.getMoneyLaunderingStatus() != null) {
            moneyLaunderingStatus = fixml.getMoneyLaunderingStatus();
        }
        if (fixml.getRegistID() != null) {
            registID = fixml.getRegistID();
        }
        if (fixml.getListExecInstType() != null) {
            listExecInstType = fixml.getListExecInstType();
        }
        if (fixml.getListExecInst() != null) {
            listExecInst = fixml.getListExecInst();
        }
        if (fixml.getEncodedListExecInstLen() != null) {
            encodedListExecInstLen = fixml.getEncodedListExecInstLen();
            encodedListExecInst = fixml.getEncodedListExecInst();
        }
        if (fixml.getAllowableOneSidednessPct() != null) {
            allowableOneSidednessPct = fixml.getAllowableOneSidednessPct();
        }
        if (fixml.getAllowableOneSidednessValue() != null) {
            allowableOneSidednessValue = fixml.getAllowableOneSidednessValue();
        }
        if (fixml.getAllowableOneSidednessCurr() != null) {
            allowableOneSidednessCurr = fixml.getAllowableOneSidednessCurr();
        }
        if (fixml.getTotNoOrders() != null) {
            totNoOrders = fixml.getTotNoOrders();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getOrderListGroups() != null && fixml.getOrderListGroups().length > 0) {
            setOrderListGroups(fixml.getOrderListGroups());
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

    @XmlAttribute(name = "BidID")
    @Override
    public String getBidID() {
        return bidID;
    }

    @Override
    public void setBidID(String bidID) {
        this.bidID = bidID;
    }

    @XmlAttribute(name = "ClBidID")
    @Override
    public String getClientBidID() {
        return clientBidID;
    }

    @Override
    public void setClientBidID(String clientBidID) {
        this.clientBidID = clientBidID;
    }

    @XmlAttribute(name = "ProgRptReqs")
    @Override
    public ProgRptReqs getProgRptReqs() {
        return progRptReqs;
    }

    @Override
    public void setProgRptReqs(ProgRptReqs progRptReqs) {
        this.progRptReqs = progRptReqs;
    }

    @XmlAttribute(name = "BidTyp")
    @Override
    public BidType getBidType() {
        return bidType;
    }

    @Override
    public void setBidType(BidType bidType) {
        this.bidType = bidType;
    }

    @XmlAttribute(name = "ProgPeriodIntvl")
    @Override
    public Integer getProgPeriodInterval() {
        return progPeriodInterval;
    }

    @Override
    public void setProgPeriodInterval(Integer progPeriodInterval) {
        this.progPeriodInterval = progPeriodInterval;
    }

    @XmlAttribute(name = "CxllationRights")
    @Override
    public CancellationRights getCancellationRights() {
        return cancellationRights;
    }

    @Override
    public void setCancellationRights(CancellationRights cancellationRights) {
        this.cancellationRights = cancellationRights;
    }

    @XmlAttribute(name = "MnyLaunderingStat")
    @Override
    public MoneyLaunderingStatus getMoneyLaunderingStatus() {
        return moneyLaunderingStatus;
    }

    @Override
    public void setMoneyLaunderingStatus(MoneyLaunderingStatus moneyLaunderingStatus) {
        this.moneyLaunderingStatus = moneyLaunderingStatus;
    }

    @XmlAttribute(name = "RegistID")
    @Override
    public String getRegistID() {
        return registID;
    }

    @Override
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    @XmlAttribute(name = "ListExecInstTyp")
    @Override
    public ListExecInstType getListExecInstType() {
        return listExecInstType;
    }

    @Override
    public void setListExecInstType(ListExecInstType listExecInstType) {
        this.listExecInstType = listExecInstType;
    }

    @XmlAttribute(name = "ListExecInst")
    @Override
    public String getListExecInst() {
        return listExecInst;
    }

    @Override
    public void setListExecInst(String listExecInst) {
        this.listExecInst = listExecInst;
    }

    @XmlAttribute(name = "EncListExecInstLen")
    @Override
    public Integer getEncodedListExecInstLen() {
        return encodedListExecInstLen;
    }

    @Override
    public void setEncodedListExecInstLen(Integer encodedListExecInstLen) {
        this.encodedListExecInstLen = encodedListExecInstLen;
    }

    @XmlAttribute(name = "EncListExecInst")
    @Override
    public byte[] getEncodedListExecInst() {
        return encodedListExecInst;
    }

    @Override
    public void setEncodedListExecInst(byte[] encodedListExecInst) {
        this.encodedListExecInst = encodedListExecInst;
        if (encodedListExecInstLen == null) {
            encodedListExecInstLen = new Integer(encodedListExecInst.length);
        }
    }

    @XmlAttribute(name = "AOSPct")
    @Override
    public Double getAllowableOneSidednessPct() {
        return allowableOneSidednessPct;
    }

    @Override
    public void setAllowableOneSidednessPct(Double allowableOneSidednessPct) {
        this.allowableOneSidednessPct = allowableOneSidednessPct;
    }

    @XmlAttribute(name = "AOSValu")
    @Override
    public Double getAllowableOneSidednessValue() {
        return allowableOneSidednessValue;
    }

    @Override
    public void setAllowableOneSidednessValue(Double allowableOneSidednessValue) {
        this.allowableOneSidednessValue = allowableOneSidednessValue;
    }

    @XmlAttribute(name = "AOSCurr")
    @Override
    public Currency getAllowableOneSidednessCurr() {
        return allowableOneSidednessCurr;
    }

    @Override
    public void setAllowableOneSidednessCurr(Currency allowableOneSidednessCurr) {
        this.allowableOneSidednessCurr = allowableOneSidednessCurr;
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
            orderListGroups = new OrderListGroup[noOrders.intValue()];
            for (int i = 0; i < orderListGroups.length; i++) {
                orderListGroups[i] = new OrderListGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public OrderListGroup[] getOrderListGroups() {
        return orderListGroups;
    }
    
    public void setOrderListGroups(OrderListGroup[] orderListGroups) {
        this.orderListGroups = orderListGroups;
        if (orderListGroups != null) {
            noOrders = new Integer(orderListGroups.length);
        }
    }

    @Override
    public OrderListGroup addOrderListGroup() {
        OrderListGroup group = new OrderListGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<OrderListGroup> groups = new ArrayList<OrderListGroup>();
        if (orderListGroups != null && orderListGroups.length > 0) {
            groups = new ArrayList<OrderListGroup>(Arrays.asList(orderListGroups));
        }
        groups.add(group);
        orderListGroups = groups.toArray(new OrderListGroup[groups.size()]);
        noOrders = new Integer(orderListGroups.length);

        return group;
    }

    @Override
    public OrderListGroup deleteOrderListGroup(int index) {
        OrderListGroup result = null;
        if (orderListGroups != null && orderListGroups.length > 0 && orderListGroups.length > index) {
            List<OrderListGroup> groups = new ArrayList<OrderListGroup>(Arrays.asList(orderListGroups));
            result = groups.remove(index);
            orderListGroups = groups.toArray(new OrderListGroup[groups.size()]);
            if (orderListGroups.length > 0) {
                noOrders = new Integer(orderListGroups.length);
            } else {
                orderListGroups = null;
                noOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearOrderListGroups() {
        int result = 0;
        if (orderListGroups != null && orderListGroups.length > 0) {
            result = orderListGroups.length;
            orderListGroups = null;
            noOrders = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
            }
            if (MsgUtil.isTagInList(TagNum.BidID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidID, bidID);
            }
            if (MsgUtil.isTagInList(TagNum.ClientBidID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientBidID, clientBidID);
            }
            if (progRptReqs != null && MsgUtil.isTagInList(TagNum.ProgRptReqs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ProgRptReqs, progRptReqs.getValue());
            }
            if (bidType != null && MsgUtil.isTagInList(TagNum.BidType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidType, bidType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ProgPeriodInterval, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ProgPeriodInterval, progPeriodInterval);
            }
            if (cancellationRights != null && MsgUtil.isTagInList(TagNum.CancellationRights, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CancellationRights, cancellationRights.getValue());
            }
            if (moneyLaunderingStatus != null && MsgUtil.isTagInList(TagNum.MoneyLaunderingStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.RegistID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistID, registID);
            }
            if (listExecInstType != null && MsgUtil.isTagInList(TagNum.ListExecInstType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListExecInstType, listExecInstType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ListExecInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListExecInst, listExecInst);
            }
            if (encodedListExecInstLen != null && encodedListExecInstLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedListExecInstLen, SECURED_TAGS, secured)) {
                if (encodedListExecInst != null && encodedListExecInst.length > 0) {
                    encodedListExecInstLen = new Integer(encodedListExecInst.length);
                    TagEncoder.encode(bao, TagNum.EncodedListExecInstLen, encodedListExecInstLen);
                    TagEncoder.encode(bao, TagNum.EncodedListExecInst, encodedListExecInst);
                }
            }
            if (MsgUtil.isTagInList(TagNum.AllowableOneSidednessPct, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllowableOneSidednessPct, allowableOneSidednessPct);
            }
            if (MsgUtil.isTagInList(TagNum.AllowableOneSidednessValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllowableOneSidednessValue, allowableOneSidednessValue);
            }
            if (allowableOneSidednessCurr != null && MsgUtil.isTagInList(TagNum.AllowableOneSidednessCurr, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllowableOneSidednessCurr, allowableOneSidednessCurr.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TotNoOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoOrders, totNoOrders);
            }
            if (MsgUtil.isTagInList(TagNum.LastFragment, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            }
            if (noOrders != null && MsgUtil.isTagInList(TagNum.NoOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoOrders, noOrders);
                if (orderListGroups != null && orderListGroups.length == noOrders.intValue()) {
                    for (int i = 0; i < noOrders.intValue(); i++) {
                        if (orderListGroups[i] != null) {
                            bao.write(orderListGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "OrderListGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoOrders.getValue(), error);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (ORDER_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noOrders != null && noOrders.intValue() > 0) {
                message.reset();
                orderListGroups = new OrderListGroup[noOrders.intValue()];
                for (int i = 0; i < noOrders.intValue(); i++) {
                    OrderListGroup group = new OrderListGroup44(context);
                    group.decode(message);
                    orderListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewOrderListMsg] message version [4.4].";
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
