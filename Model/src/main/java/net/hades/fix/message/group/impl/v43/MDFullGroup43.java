/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDReqGroup43.java
 *
 * $Id: MDFullGroup43.java,v 1.8 2010-11-23 10:20:16 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDFullGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of MDReqGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlTransient
public class MDFullGroup43 extends MDFullGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDFullGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MDFullGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (mdEntryType != null && MsgUtil.isTagInList(TagNum.MDEntryType, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryType, mdEntryType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryPx, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryPx, mdEntryPx);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDEntrySize, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntrySize, mdEntrySize);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryDate, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUtcDate(bao, TagNum.MDEntryDate, mdEntryDate);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryTime, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUTCTime(bao, TagNum.MDEntryTime, mdEntryTime);
            }
            if (tickDirection != null && MsgUtil.isTagInList(TagNum.TickDirection, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.TickDirection, tickDirection.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDMkt, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDMkt, mdMkt);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (quoteCondition != null && MsgUtil.isTagInList(TagNum.QuoteCondition, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.QuoteCondition, quoteCondition.getValue());
            }
            if (tradeCondition != null && MsgUtil.isTagInList(TagNum.TradeCondition, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.TradeCondition, tradeCondition.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryOriginator, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryOriginator, mdEntryOriginator);
            }
            if (MsgUtil.isTagInList(TagNum.LocationID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.LocationID, locationID);
            }
            if (MsgUtil.isTagInList(TagNum.DeskID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.DeskID, deskID);
            }
            if (MsgUtil.isTagInList(TagNum.OpenCloseSettlFlag, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.OpenCloseSettlFlag, openCloseSettlFlag);
            }
            if (timeInForce != null && MsgUtil.isTagInList(TagNum.TimeInForce, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExpireDate, SECURED_TAGS, secured)) {
                 TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            }
            if (MsgUtil.isTagInList(TagNum.ExpireTime, SECURED_TAGS, secured)) {
                 TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            }
            if (MsgUtil.isTagInList(TagNum.MinQty, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MinQty, minQty);
            }
            if (MsgUtil.isTagInList(TagNum.ExecInst, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            }
            if (MsgUtil.isTagInList(TagNum.SellerDays, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.SellerDays, sellerDays);
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteEntryID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.QuoteEntryID, quoteEntryID);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryBuyer, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryBuyer, mdEntryBuyer);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntrySeller, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntrySeller, mdEntrySeller);
            }
            if (MsgUtil.isTagInList(TagNum.NumberOfOrders, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.NumberOfOrders, numberOfOrders);
            }
            if (MsgUtil.isTagInList(TagNum.MDEntryPositionNo, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.MDEntryPositionNo, mdEntryPositionNo);
            }
            if (MsgUtil.isTagInList(TagNum.Scope, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.Scope, scope);
            }
            if (MsgUtil.isTagInList(TagNum.PriceDelta, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.PriceDelta, priceDelta);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.MDPriceLevel, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MDPriceLevel, mdPriceLevel);
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MDFullGroup] group version [4.3].";
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
