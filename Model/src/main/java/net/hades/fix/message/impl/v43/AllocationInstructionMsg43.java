/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionMsg43.java
 *
 * $Id: AllocationInstructionMsg43.java,v 1.3 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.group.ExecAllocGroup;
import net.hades.fix.message.group.OrderAllocGroup;
import net.hades.fix.message.group.impl.v42.ExecAllocGroup42;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
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

import javax.xml.bind.annotation.XmlElementRef;

import net.hades.fix.message.AllocationInstructionMsg;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.group.impl.v40.OrderAllocGroup40;
import net.hades.fix.message.group.impl.v43.AllocGroup43;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 AllocationInstructionMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class AllocationInstructionMsg43 extends AllocationInstructionMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ORDER_ALLOC_GROUP_TAGS = new OrderAllocGroup40().getFragmentAllTags();
    protected static final Set<Integer> EXEC_ALLOC_GROUP_TAGS = new ExecAllocGroup42().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_GROUP_TAGS = new AllocGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(ORDER_ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(EXEC_ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(ALLOC_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ORDER_ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(EXEC_ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AllocationInstructionMsg43() {
        super();
    }

    public AllocationInstructionMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public AllocationInstructionMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public AllocationInstructionMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
            orderAllocGroups = new OrderAllocGroup[noOrders.intValue()];
            for (int i = 0; i < orderAllocGroups.length; i++) {
                orderAllocGroups[i] = new OrderAllocGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public OrderAllocGroup[] getOrderAllocGroups() {
        return orderAllocGroups;
    }
    
    public void setOrderAllocGroups(OrderAllocGroup[] orderAllocGroups) {
        this.orderAllocGroups = orderAllocGroups;
        if (orderAllocGroups != null) {
            noOrders = new Integer(orderAllocGroups.length);
        }
    }

    @Override
    public OrderAllocGroup addOrderAllocGroup() {
        OrderAllocGroup group = new OrderAllocGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<OrderAllocGroup> groups = new ArrayList<OrderAllocGroup>();
        if (orderAllocGroups != null && orderAllocGroups.length > 0) {
            groups = new ArrayList<OrderAllocGroup>(Arrays.asList(orderAllocGroups));
        }
        groups.add(group);
        orderAllocGroups = groups.toArray(new OrderAllocGroup[groups.size()]);
        noOrders = new Integer(orderAllocGroups.length);

        return group;
    }

    @Override
    public OrderAllocGroup deleteOrderAllocGroup(int index) {
        OrderAllocGroup result = null;
        if (orderAllocGroups != null && orderAllocGroups.length > 0 && orderAllocGroups.length > index) {
            List<OrderAllocGroup> groups = new ArrayList<OrderAllocGroup>(Arrays.asList(orderAllocGroups));
            result = groups.remove(index);
            orderAllocGroups = groups.toArray(new OrderAllocGroup[groups.size()]);
            if (orderAllocGroups.length > 0) {
                noOrders = new Integer(orderAllocGroups.length);
            } else {
                orderAllocGroups = null;
                noOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearOrderAllocGroups() {
        int result = 0;
        if (orderAllocGroups != null && orderAllocGroups.length > 0) {
            result = orderAllocGroups.length;
            orderAllocGroups = null;
            noOrders = null;
        }

        return result;
    }

    @Override
    public Integer getNoExecs() {
        return noExecs;
    }

    @Override
    public void setNoExecs(Integer noExecs) {
        this.noExecs = noExecs;
        if (noExecs != null) {
            execAllocGroups = new ExecAllocGroup[noExecs.intValue()];
            for (int i = 0; i < execAllocGroups.length; i++) {
                execAllocGroups[i] = new ExecAllocGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public ExecAllocGroup[] getExecAllocGroups() {
        return execAllocGroups;
    }

    public void setExecAllocGroups(ExecAllocGroup[] execAllocGroups) {
        this.execAllocGroups = execAllocGroups;
        if (execAllocGroups != null) {
            noExecs = new Integer(execAllocGroups.length);
        }
    }

    @Override
    public ExecAllocGroup addExecAllocGroup() {
        ExecAllocGroup group = new ExecAllocGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<ExecAllocGroup> groups = new ArrayList<ExecAllocGroup>();
        if (execAllocGroups != null && execAllocGroups.length > 0) {
            groups = new ArrayList<ExecAllocGroup>(Arrays.asList(execAllocGroups));
        }
        groups.add(group);
        execAllocGroups = groups.toArray(new ExecAllocGroup[groups.size()]);
        noExecs = new Integer(execAllocGroups.length);

        return group;
    }

    @Override
    public ExecAllocGroup deleteExecAllocGroup(int index) {
        ExecAllocGroup result = null;
        if (execAllocGroups != null && execAllocGroups.length > 0 && execAllocGroups.length > index) {
            List<ExecAllocGroup> groups = new ArrayList<ExecAllocGroup>(Arrays.asList(execAllocGroups));
            result = groups.remove(index);
            execAllocGroups = groups.toArray(new ExecAllocGroup[groups.size()]);
            if (execAllocGroups.length > 0) {
                noExecs = new Integer(execAllocGroups.length);
            } else {
                execAllocGroups = null;
                noExecs = null;
            }
        }

        return result;
    }

    @Override
    public int clearExecAllocGroups() {
        int result = 0;
        if (execAllocGroups != null && execAllocGroups.length > 0) {
            result = execAllocGroups.length;
            execAllocGroups = null;
            noExecs = null;
        }

        return result;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument43(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    @Override
    public String getLastMkt() {
        return lastMkt;
    }

    @Override
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties43(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    @Override
    public Integer getNoAllocs() {
        return noAllocs;
    }

    @Override
    public void setNoAllocs(Integer noAllocs) {
        this.noAllocs = noAllocs;
        if (noAllocs != null) {
            allocGroups = new AllocGroup[noAllocs.intValue()];
            for (int i = 0; i < allocGroups.length; i++) {
                allocGroups[i] = new AllocGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public AllocGroup[] getAllocGroups() {
        return allocGroups;
    }

    public void setAllocGroups(AllocGroup[] allocGroups) {
        this.allocGroups = allocGroups;
        if (allocGroups != null) {
            noAllocs = new Integer(allocGroups.length);
        }
    }

    @Override
    public AllocGroup addAllocGroup() {
        AllocGroup group = new AllocGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<AllocGroup> groups = new ArrayList<AllocGroup>();
        if (allocGroups != null && allocGroups.length > 0) {
            groups = new ArrayList<AllocGroup>(Arrays.asList(allocGroups));
        }
        groups.add(group);
        allocGroups = groups.toArray(new AllocGroup[groups.size()]);
        noAllocs = new Integer(allocGroups.length);

        return group;
    }

    @Override
    public AllocGroup deleteAllocGroup(int index) {
        AllocGroup result = null;
        if (allocGroups != null && allocGroups.length > 0 && allocGroups.length > index) {
            List<AllocGroup> groups = new ArrayList<AllocGroup>(Arrays.asList(allocGroups));
            result = groups.remove(index);
            allocGroups = groups.toArray(new AllocGroup[groups.size()]);
            if (allocGroups.length > 0) {
                noAllocs = new Integer(allocGroups.length);
            } else {
                allocGroups = null;
                noAllocs = null;
            }
        }

        return result;
    }

    @Override
    public int clearAllocGroups() {
        int result = 0;
        if (allocGroups != null && allocGroups.length > 0) {
            result = allocGroups.length;
            allocGroups = null;
            noAllocs = null;
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
            if (MsgUtil.isTagInList(TagNum.AllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocID, allocID);
            }
            if (allocTransType != null && MsgUtil.isTagInList(TagNum.AllocTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocTransType, allocTransType.getValue());
            }
            if (allocType != null && MsgUtil.isTagInList(TagNum.AllocType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocType, allocType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryAllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryAllocID, secondaryAllocID);
            }
            if (MsgUtil.isTagInList(TagNum.RefAllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RefAllocID, refAllocID);
            }
            if (allocCancReplaceReason != null && MsgUtil.isTagInList(TagNum.AllocCancReplaceReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocCancReplaceReason, allocCancReplaceReason.getValue());
            }
            if (allocIntermedReqType != null && MsgUtil.isTagInList(TagNum.AllocIntermedReqType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocIntermedReqType, allocIntermedReqType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AllocLinkID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocLinkID, allocLinkID);
            }
            if (allocLinkType != null && MsgUtil.isTagInList(TagNum.AllocLinkType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocLinkType, allocLinkType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.BookingRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BookingRefID, bookingRefID);
            }
            if (noOrders != null && MsgUtil.isTagInList(TagNum.NoOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoOrders, noOrders);
                if (orderAllocGroups != null && orderAllocGroups.length == noOrders.intValue()) {
                    for (int i = 0; i < noOrders.intValue(); i++) {
                        if (orderAllocGroups[i] != null) {
                            bao.write(orderAllocGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "OrderAllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoOrders.getValue(), error);
                }
            }
            if (noExecs != null && MsgUtil.isTagInList(TagNum.NoExecs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoExecs, noExecs);
                if (execAllocGroups != null && execAllocGroups.length == noExecs.intValue()) {
                    for (int i = 0; i < noExecs.intValue(); i++) {
                        if (execAllocGroups[i] != null) {
                            bao.write(execAllocGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "ExecAllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoExecs.getValue(), error);
                }
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.Quantity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Quantity, quantity);
            }
            if (MsgUtil.isTagInList(TagNum.LastMkt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            }
            if (MsgUtil.isTagInList(TagNum.TradeOriginationDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (priceType != null && MsgUtil.isTagInList(TagNum.PriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            }
            if (MsgUtil.isTagInList(TagNum.AvgParPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgParPx, avgParPx);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AvgPxPrecision, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgPxPrecision, avgPxPrecision);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (bookingType != null && MsgUtil.isTagInList(TagNum.BookingType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BookingType, bookingType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.GrossTradeAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            }
            if (MsgUtil.isTagInList(TagNum.Concession, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Concession, concession);
            }
            if (MsgUtil.isTagInList(TagNum.TotalTakedown, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalTakedown, totalTakedown);
            }
            if (MsgUtil.isTagInList(TagNum.NetMoney, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
            }
            if (MsgUtil.isTagInList(TagNum.SettlCurrAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrAmt, settlCurrAmt);
            }
            if (settlCurrency != null && MsgUtil.isTagInList(TagNum.SettlCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (positionEffect != null && MsgUtil.isTagInList(TagNum.PositionEffect, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AutoAcceptIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AutoAcceptIndicator, autoAcceptIndicator);
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
            if (MsgUtil.isTagInList(TagNum.NumDaysInterest, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NumDaysInterest, numDaysInterest);
            }
            if (MsgUtil.isTagInList(TagNum.AccruedInterestRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            }
            if (MsgUtil.isTagInList(TagNum.AccruedInterestAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            }
            if (MsgUtil.isTagInList(TagNum.TotalAccruedInterestAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalAccruedInterestAmt, totalAccruedInterestAmt);
            }
            if (MsgUtil.isTagInList(TagNum.InterestAtMaturity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.InterestAtMaturity, interestAtMaturity);
            }
            if (MsgUtil.isTagInList(TagNum.EndAccruedInterestAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
            }
            if (MsgUtil.isTagInList(TagNum.StartCash, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StartCash, startCash);
            }
            if (MsgUtil.isTagInList(TagNum.EndCash, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.EndCash, endCash);
            }
            if (MsgUtil.isTagInList(TagNum.LegalConfirm, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegalConfirm, legalConfirm);
            }
            if (MsgUtil.isTagInList(TagNum.TotNoAllocs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoAllocs, totNoAllocs);
            }
            if (MsgUtil.isTagInList(TagNum.LastFragment, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            }
            if (noAllocs != null && MsgUtil.isTagInList(TagNum.NoAllocs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocGroups != null && allocGroups.length == noAllocs.intValue()) {
                    for (int i = 0; i < noAllocs.intValue(); i++) {
                        if (allocGroups[i] != null) {
                            bao.write(allocGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "AllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAllocs.getValue(), error);
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
        if (ORDER_ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noOrders != null && noOrders.intValue() > 0) {
                message.reset();
                orderAllocGroups = new OrderAllocGroup[noOrders.intValue()];
                for (int i = 0; i < noOrders.intValue(); i++) {
                    OrderAllocGroup group = new OrderAllocGroup40(context);
                    group.decode(message);
                    orderAllocGroups[i] = group;
                }
            }
        }
        if (EXEC_ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noExecs != null && noExecs.intValue() > 0) {
                message.reset();
                execAllocGroups = new ExecAllocGroup[noExecs.intValue()];
                for (int i = 0; i < noExecs.intValue(); i++) {
                    ExecAllocGroup group = new ExecAllocGroup42(context);
                    group.decode(message);
                    execAllocGroups[i] = group;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
        if (ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAllocs != null && noAllocs.intValue() > 0) {
                message.reset();
                allocGroups = new AllocGroup[noAllocs.intValue()];
                for (int i = 0; i < noAllocs.intValue(); i++) {
                    AllocGroup group = new AllocGroup43(context);
                    group.decode(message);
                    allocGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AllocationInstructionMsg] message version [4.3].";
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
