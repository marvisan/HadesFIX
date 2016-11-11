/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradeCaptureReportMsg43.java
 *
 * $Id: TradeCaptureReportMsg43.java,v 1.1 2011-10-21 10:31:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.Header;
import net.hades.fix.message.TradeCaptureReportMsg;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.TrdCapRptSideGroup;
import net.hades.fix.message.group.impl.v43.TrdCapRptSideGroup43;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 TradeCaptureReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class TradeCaptureReportMsg43 extends TradeCaptureReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.TradeReportID.getValue(),
        TagNum.TradeReportTransType.getValue(),
        TagNum.TradeRequestID.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.TradeReportRefID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.ExecRestatementReason.getValue(),
        TagNum.PreviouslyReported.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.LastSpotRate.getValue(),
        TagNum.LastForwardPoints.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.MatchStatus.getValue(),
        TagNum.MatchType.getValue(),
        TagNum.NoSides.getValue()
    }));

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData43().getFragmentAllTags();
    protected static final Set<Integer> TRD_CAP_RPT_SIDE_GROUP_TAGS = new TrdCapRptSideGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(TRD_CAP_RPT_SIDE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(TRD_CAP_RPT_SIDE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V43;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradeCaptureReportMsg43() {
        super();
    }

    public TradeCaptureReportMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public TradeCaptureReportMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V43;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument43(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.orderQtyData = new OrderQtyData43(context);
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }


    @Override
    public Integer getNoSides() {
        return noSides;
    }

    @Override
    public void setNoSides(Integer noSides) {
        this.noSides = noSides;
        if (noSides != null) {
            trdCapRptSideGroups = new TrdCapRptSideGroup[noSides.intValue()];
            for (int i = 0; i < trdCapRptSideGroups.length; i++) {
                trdCapRptSideGroups[i] = new TrdCapRptSideGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public TrdCapRptSideGroup[] getTrdCapRptSideGroups() {
        return trdCapRptSideGroups;
    }

    public void setTrdCapRptSideGroups(TrdCapRptSideGroup[] sideCrossOrdModGroups) {
        this.trdCapRptSideGroups = sideCrossOrdModGroups;
        if (sideCrossOrdModGroups != null) {
            noSides = new Integer(sideCrossOrdModGroups.length);
        }
    }

    @Override
    public TrdCapRptSideGroup addTrdCapRptSideGroup() {
        TrdCapRptSideGroup group = new TrdCapRptSideGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<TrdCapRptSideGroup> groups = new ArrayList<TrdCapRptSideGroup>();
        if (trdCapRptSideGroups != null && trdCapRptSideGroups.length > 0) {
            groups = new ArrayList<TrdCapRptSideGroup>(Arrays.asList(trdCapRptSideGroups));
        }
        groups.add(group);
        trdCapRptSideGroups = groups.toArray(new TrdCapRptSideGroup[groups.size()]);
        noSides = new Integer(trdCapRptSideGroups.length);

        return group;
    }

    @Override
    public TrdCapRptSideGroup deleteTrdCapRptSideGroup(int index) {
        TrdCapRptSideGroup result = null;
        if (trdCapRptSideGroups != null && trdCapRptSideGroups.length > 0 && trdCapRptSideGroups.length > index) {
            List<TrdCapRptSideGroup> groups = new ArrayList<TrdCapRptSideGroup>(Arrays.asList(trdCapRptSideGroups));
            result = groups.remove(index);
            trdCapRptSideGroups = groups.toArray(new TrdCapRptSideGroup[groups.size()]);
            if (trdCapRptSideGroups.length > 0) {
                noSides = new Integer(trdCapRptSideGroups.length);
            } else {
                trdCapRptSideGroups = null;
                noSides = null;
            }
        }

        return result;
    }

    @Override
    public int clearTrdCapRptSideGroups() {
        int result = 0;
        if (trdCapRptSideGroups != null && trdCapRptSideGroups.length > 0) {
            result = trdCapRptSideGroups.length;
            trdCapRptSideGroups = null;
            noSides = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (tradeReportID == null || tradeReportID.trim().isEmpty()) {
            errorMsg.append(" [TradeReportID]");
            hasMissingTag = true;
        }
        if (execType == null) {
            errorMsg.append(" [ExecType]");
            hasMissingTag = true;
        }
        if (previouslyReported == null) {
            errorMsg.append(" [PreviouslyReported]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (lastQty == null) {
            errorMsg.append(" [LastQty]");
            hasMissingTag = true;
        }
        if (lastPx == null) {
            errorMsg.append(" [LastPx]");
            hasMissingTag = true;
        }
        if (tradeDate == null) {
            errorMsg.append(" [TradeDate]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
            hasMissingTag = true;
        }
        if (noSides == null) {
            errorMsg.append(" [NoSides]");
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
            if (MsgUtil.isTagInList(TagNum.TradeReportID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportID, tradeReportID);
            }
            if (tradeReportTransType != null && MsgUtil.isTagInList(TagNum.TradeReportTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportTransType, tradeReportTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeRequestID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeRequestID, tradeRequestID);
            }
            if (execType != null && MsgUtil.isTagInList(TagNum.ExecType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeReportRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeReportRefID, tradeReportRefID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            }
            if (execRestatementReason != null && MsgUtil.isTagInList(TagNum.ExecRestatementReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecRestatementReason, execRestatementReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PreviouslyReported, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PreviouslyReported, previouslyReported);
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.LastQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            }
            if (MsgUtil.isTagInList(TagNum.LastPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            }
            if (MsgUtil.isTagInList(TagNum.LastSpotRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastSpotRate, lastSpotRate);
            }
            if (MsgUtil.isTagInList(TagNum.LastForwardPoints, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastForwardPoints, lastForwardPoints);
            }
            if (MsgUtil.isTagInList(TagNum.LastMkt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
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
            if (matchStatus != null && MsgUtil.isTagInList(TagNum.MatchStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (matchType != null && MsgUtil.isTagInList(TagNum.MatchType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MatchType, matchType.getValue());
            }
            if (noSides != null && MsgUtil.isTagInList(TagNum.NoSides, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (trdCapRptSideGroups != null && trdCapRptSideGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (trdCapRptSideGroups[i] != null) {
                            bao.write(trdCapRptSideGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TrdCapRptSideGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
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
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData43(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (TRD_CAP_RPT_SIDE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSides != null && noSides.intValue() > 0) {
                message.reset();
                trdCapRptSideGroups = new TrdCapRptSideGroup[noSides.intValue()];
                for (int i = 0; i < noSides.intValue(); i++) {
                    TrdCapRptSideGroup component = new TrdCapRptSideGroup43(context);
                    component.decode(message);
                    trdCapRptSideGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TradeCaptureReportMsg] message version [4.3].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
