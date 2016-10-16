/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderListGroup40.java
 *
 * $Id: OrderStatusGroup40.java,v 1.1 2011-02-03 10:36:54 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v40;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderStatusGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.0 implementation of OrderStatusGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class OrderStatusGroup40 extends OrderStatusGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_DATA_TAGS_V40 = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OrderStatusGroup40() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public OrderStatusGroup40(FragmentContext context) {
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (clOrdID == null || clOrdID.trim().isEmpty()) {
            errorMsg.append(" [ClOrdID]");
            hasMissingTag = true;
        }
        if (cumQty == null) {
            errorMsg.append(" [CumQty]");
            hasMissingTag = true;
        }
        if (cxlQty == null) {
            errorMsg.append(" [CxlQty]");
            hasMissingTag = true;
        }
        if (avgPx == null) {
            errorMsg.append(" [AvgPx]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.CumQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CumQty, cumQty);
            }
            if (ordStatus != null && MsgUtil.isTagInList(TagNum.OrdStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.WorkingIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.WorkingIndicator, workingIndicator);
            }
            if (MsgUtil.isTagInList(TagNum.LeavesQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LeavesQty, leavesQty);
            }
            if (MsgUtil.isTagInList(TagNum.CxlQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CxlQty, cxlQty);
            }
            if (MsgUtil.isTagInList(TagNum.AvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            }
            if (ordRejReason != null && MsgUtil.isTagInList(TagNum.OrdRejReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdRejReason, ordRejReason.getValue());
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
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_V40;
    }
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderStatusGroup] group version [4.0].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
