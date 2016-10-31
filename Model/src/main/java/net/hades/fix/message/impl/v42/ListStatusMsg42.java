/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusMsg42.java
 *
 * $Id: ListStatusMsg42.java,v 1.3 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.ListStatusMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.OrderStatusGroup;
import net.hades.fix.message.group.impl.v42.OrderStatusGroup42;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.2 NewOrderListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class ListStatusMsg42 extends ListStatusMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ORDER_STATUS_GROUP_TAGS = new OrderStatusGroup42().getFragmentAllTags();

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

    public ListStatusMsg42() {
        super();
    }

    public ListStatusMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ListStatusMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ListStatusMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
            orderStatusGroups = new OrderStatusGroup[noOrders.intValue()];
            for (int i = 0; i < orderStatusGroups.length; i++) {
                orderStatusGroups[i] = new OrderStatusGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

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
        OrderStatusGroup group = new OrderStatusGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
            }
            if (listStatusType != null && MsgUtil.isTagInList(TagNum.ListStatusType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListStatusType, listStatusType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.WaveNo, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.WaveNo, waveNo);
            }
            if (MsgUtil.isTagInList(TagNum.NoRpts, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRpts, noRpts);
            }
            if (listOrderStatus != null && MsgUtil.isTagInList(TagNum.ListOrderStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListOrderStatus, listOrderStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.RptSeq, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RptSeq, rptSeq);
            }
            if (MsgUtil.isTagInList(TagNum.ListStatusText, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListStatusText, listStatusText);
            }
            if (encodedListStatusTextLen != null && encodedListStatusTextLen.intValue() > 0
                    && MsgUtil.isTagInList(TagNum.EncodedListStatusTextLen, SECURED_TAGS, secured)) {
                if (encodedListStatusText != null && encodedListStatusText.length > 0) {
                    encodedListStatusTextLen = new Integer(encodedListStatusText.length);
                    TagEncoder.encode(bao, TagNum.EncodedListStatusTextLen, encodedListStatusTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedListStatusText, encodedListStatusText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.TotNoOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoOrders, totNoOrders);
            }
            if (MsgUtil.isTagInList(TagNum.LastFragment, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            }
            if (noOrders != null && MsgUtil.isTagInList(TagNum.NoOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoOrders, noOrders);
                if (orderStatusGroups != null && orderStatusGroups.length == noOrders.intValue()) {
                    for (int i = 0; i < noOrders.intValue(); i++) {
                        if (orderStatusGroups[i] != null) {
                            bao.write(orderStatusGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "OrderStatusGroups field has been set but there is no data or the number of groups does not match.";
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
        if (ORDER_STATUS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noOrders != null && noOrders.intValue() > 0) {
                message.reset();
                orderStatusGroups = new OrderStatusGroup[noOrders.intValue()];
                for (int i = 0; i < noOrders.intValue(); i++) {
                    OrderStatusGroup group = new OrderStatusGroup42(context);
                    group.decode(message);
                    orderStatusGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ListStatusMsg] message version [4.2].";
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
