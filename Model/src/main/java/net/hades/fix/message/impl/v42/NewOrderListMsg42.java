/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsg42.java
 *
 * $Id: NewOrderListMsg42.java,v 1.2 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderListGroup;
import net.hades.fix.message.group.impl.v42.OrderListGroup42;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.2 NewOrderListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class NewOrderListMsg42 extends NewOrderListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ORDER_LIST_GROUP_TAGS = new OrderListGroup42().getFragmentAllTags();

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

    public NewOrderListMsg42() {
        super();
    }

    public NewOrderListMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewOrderListMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewOrderListMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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

    // ACCESSOR METHODS
    //////////////////////////////////////////

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
                orderListGroups[i] = new OrderListGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

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
        OrderListGroup group = new OrderListGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
                    OrderListGroup group = new OrderListGroup42(context);
                    group.decode(message);
                    orderListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewOrderListMsg] message version [4.2].";
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
