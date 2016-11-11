/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderMultilegMsg43.java
 *
 * $Id: NewOrderMultilegMsg43.java,v 1.2 2011-09-08 08:50:57 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

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
import net.hades.fix.message.NewOrderMultilegMsg;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v43.CommissionData43;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.comp.impl.v43.OrderQtyData43;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegOrdGroup;
import net.hades.fix.message.group.PreAllocMLegGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.group.impl.v43.LegOrdGroup43;
import net.hades.fix.message.group.impl.v43.PreAllocMLegGroup43;
import net.hades.fix.message.group.impl.v43.TradingSessionGroup43;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 NewOrderMultilegMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class NewOrderMultilegMsg43 extends NewOrderMultilegMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ClOrdID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.ClOrdLinkID.getValue(),
        TagNum.TradeOriginationDate.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.DayBookingInst.getValue(),
        TagNum.BookingUnit.getValue(),
        TagNum.PreallocMethod.getValue(),
        TagNum.AllocID.getValue(),
        TagNum.NoAllocs.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.CashMargin.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.HandlInst.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.MatchIncrement.getValue(),
        TagNum.MaxPriceLevels.getValue(),
        TagNum.MaxFloor.getValue(),
        TagNum.ExDestination.getValue(),
        TagNum.ExDestinationIDSource.getValue(),
        TagNum.NoTradingSessions.getValue(),
        TagNum.ProcessCode.getValue(),
        TagNum.Side.getValue(),
        TagNum.PrevClosePx.getValue(),
        TagNum.SwapPoints.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.LocateReqd.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.QuantityType.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.MultilegModel.getValue(),
        TagNum.MultilegPriceMethod.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceProtectionScope.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.IOIID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.RefOrderID.getValue(),
        TagNum.RefOrderIDSource.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.GTBookingInst.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.PreTradeAnonymity.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.ForexReq.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.BookingType.getValue(),
        TagNum.Text.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.CoveredOrUncovered.getValue(),
        TagNum.MaxShow.getValue(),
        TagNum.TargetStrategy.getValue(),
        TagNum.TargetStrategyParameters.getValue(),
        TagNum.ParticipationRate.getValue(),
        TagNum.RiskFreeRate.getValue(),
        TagNum.PegOffsetValue.getValue(),
        TagNum.DiscretionInst.getValue(),
        TagNum.DiscretionOffsetValue.getValue(),
        TagNum.CancellationRights.getValue(),
        TagNum.MoneyLaunderingStatus.getValue(),
        TagNum.RegistID.getValue(),
        TagNum.Designation.getValue(),
        TagNum.MultiLegRptTypeReq.getValue(),
        TagNum.NetMoney.getValue()
    }));

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_GROUP_TAGS = new PreAllocMLegGroup43().getFragmentAllTags();
    protected static final Set<Integer> TRAD_SESSION_GROUP_TAGS = new TradingSessionGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> LEG_ORD_GROUP_TAGS = new LegOrdGroup43().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData43().getFragmentAllTags();
    protected static final Set<Integer> COMMISSION_DATA_COMP_TAGS = new CommissionData43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(ALLOC_GROUP_TAGS);
        ALL_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(LEG_ORD_GROUP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRAD_SESSION_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_ORD_GROUP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V43;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewOrderMultilegMsg43() {
        super();
    }

    public NewOrderMultilegMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewOrderMultilegMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NewOrderMultilegMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @Override
    public Integer getNoAllocs() {
        return noAllocs;
    }

    @Override
    public void setNoAllocs(Integer noAllocs) {
        this.noAllocs = noAllocs;
        if (noAllocs != null) {
            allocGroups = new PreAllocMLegGroup[noAllocs.intValue()];
            for (int i = 0; i < allocGroups.length; i++) {
                allocGroups[i] = new PreAllocMLegGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public PreAllocMLegGroup[] getAllocGroups() {
        return allocGroups;
    }

    public void setAllocGroups(PreAllocMLegGroup[] allocGroups) {
        this.allocGroups = allocGroups;
        if (allocGroups != null) {
            noAllocs = new Integer(allocGroups.length);
        }
    }

    @Override
    public PreAllocMLegGroup addAllocGroup() {
        PreAllocMLegGroup group = new PreAllocMLegGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<PreAllocMLegGroup> groups = new ArrayList<PreAllocMLegGroup>();
        if (allocGroups != null && allocGroups.length > 0) {
            groups = new ArrayList<PreAllocMLegGroup>(Arrays.asList(allocGroups));
        }
        groups.add(group);
        allocGroups = groups.toArray(new PreAllocMLegGroup[groups.size()]);
        noAllocs = new Integer(allocGroups.length);

        return group;
    }

    @Override
    public PreAllocMLegGroup deleteAllocGroup(int index) {
        PreAllocMLegGroup result = null;
        if (allocGroups != null && allocGroups.length > 0 && allocGroups.length > index) {
            List<PreAllocMLegGroup> groups = new ArrayList<PreAllocMLegGroup>(Arrays.asList(allocGroups));
            result = groups.remove(index);
            allocGroups = groups.toArray(new PreAllocMLegGroup[groups.size()]);
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

    @Override
    public Integer getNoTradingSessions() {
        return noTradingSessions;
    }

    @Override
    public void setNoTradingSessions(Integer noTradingSessions) {
        this.noTradingSessions = noTradingSessions;
        if (noTradingSessions != null) {
            tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
            for (int i = 0; i < tradingSessionGroups.length; i++) {
                tradingSessionGroups[i] = new TradingSessionGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public TradingSessionGroup[] getTradingSessionGroups() {
        return tradingSessionGroups;
    }

    public void setTradingSessionGroups(TradingSessionGroup[] tradingSessionGroups) {
        this.tradingSessionGroups = tradingSessionGroups;
        if (tradingSessionGroups != null) {
            noTradingSessions = new Integer(tradingSessionGroups.length);
        }
    }
    @Override
    public TradingSessionGroup addTradingSessionGroup() {
        TradingSessionGroup group = new TradingSessionGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<TradingSessionGroup> groups = new ArrayList<TradingSessionGroup>();
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0) {
            groups = new ArrayList<TradingSessionGroup>(Arrays.asList(tradingSessionGroups));
        }
        groups.add(group);
        tradingSessionGroups = groups.toArray(new TradingSessionGroup[groups.size()]);
        noTradingSessions = new Integer(tradingSessionGroups.length);

        return group;
    }

    @Override
    public TradingSessionGroup deleteTradingSessionGroup(int index) {
        TradingSessionGroup result = null;
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0 && tradingSessionGroups.length > index) {
            List<TradingSessionGroup> groups = new ArrayList<TradingSessionGroup>(Arrays.asList(tradingSessionGroups));
            result = groups.remove(index);
            tradingSessionGroups = groups.toArray(new TradingSessionGroup[groups.size()]);
            if (tradingSessionGroups.length > 0) {
                noTradingSessions = new Integer(tradingSessionGroups.length);
            } else {
                tradingSessionGroups = null;
                noTradingSessions = null;
            }
        }

        return result;
    }

    @Override
    public int clearTradingSessionGroups() {
        int result = 0;
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0) {
            result = tradingSessionGroups.length;
            tradingSessionGroups = null;
            noTradingSessions = null;
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
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            legOrdGroups = new LegOrdGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < legOrdGroups.length; i++) {
                legOrdGroups[i] = new LegOrdGroup43(context);
            }
        }
    }

    @Override
    public LegOrdGroup[] getLegOrdGroups() {
        return legOrdGroups;
    }

    public void setlegOrdGroups(LegOrdGroup[] legOrdGroups) {
        this.legOrdGroups = legOrdGroups;
        if (legOrdGroups != null) {
            noLegs = new Integer(legOrdGroups.length);
        }
    }

    @Override
    public LegOrdGroup addLegOrdGroup() {
        LegOrdGroup group = new LegOrdGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<LegOrdGroup> groups = new ArrayList<LegOrdGroup>();
        if (legOrdGroups != null && legOrdGroups.length > 0) {
            groups = new ArrayList<LegOrdGroup>(Arrays.asList(legOrdGroups));
        }
        groups.add(group);
        legOrdGroups = groups.toArray(new LegOrdGroup[groups.size()]);
        noLegs = new Integer(legOrdGroups.length);

        return group;
    }

    @Override
    public LegOrdGroup deleteLegOrdGroup(int index) {
        LegOrdGroup result = null;

        if (legOrdGroups != null && legOrdGroups.length > 0 && legOrdGroups.length > index) {
            List<LegOrdGroup> groups = new ArrayList<LegOrdGroup>(Arrays.asList(legOrdGroups));
            result = groups.remove(index);
            legOrdGroups = groups.toArray(new LegOrdGroup[groups.size()]);
            if (legOrdGroups.length > 0) {
                noLegs = new Integer(legOrdGroups.length);
            } else {
                legOrdGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearLegOrdGroups() {
        int result = 0;
        if (legOrdGroups != null && legOrdGroups.length > 0) {
            result = legOrdGroups.length;
            legOrdGroups = null;
            noLegs = null;
        }

        return result;
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

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    @Override
    public CommissionData getCommissionData() {
        return commissionData;
    }

    public void setCommissionData(CommissionData commissionData) {
        this.commissionData = commissionData;
    }

    @Override
    public void setCommissionData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.commissionData = new CommissionData43(context);
    }

    @Override
    public void clearCommissionData() {
        commissionData = null;
    }

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
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (handlInst == null) {
            errorMsg.append(" [HandlInst]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
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
            if (MsgUtil.isTagInList(TagNum.ClOrdLinkID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdLinkID, clOrdLinkID);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.TradeOriginationDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (acctIDSource != null && MsgUtil.isTagInList(TagNum.AcctIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null && MsgUtil.isTagInList(TagNum.AccountType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (dayBookingInst != null && MsgUtil.isTagInList(TagNum.DayBookingInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DayBookingInst, dayBookingInst.getValue());
            }
            if (bookingUnit != null && MsgUtil.isTagInList(TagNum.BookingUnit, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BookingUnit, bookingUnit.getValue());
            }
            if (preallocMethod != null && MsgUtil.isTagInList(TagNum.PreallocMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PreallocMethod, preallocMethod.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocID, allocID);
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
                    String error = "AllocationGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAllocs.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (cashMargin != null && MsgUtil.isTagInList(TagNum.CashMargin, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashMargin, cashMargin.getValue());
            }
            if (clearingFeeIndicator != null && MsgUtil.isTagInList(TagNum.ClearingFeeIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (handlInst != null && MsgUtil.isTagInList(TagNum.HandlInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.HandlInst, handlInst.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExecInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            }
            if (MsgUtil.isTagInList(TagNum.MinQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MinQty, minQty);
            }
            if (MsgUtil.isTagInList(TagNum.MaxFloor, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaxFloor, maxFloor);
            }
            if (MsgUtil.isTagInList(TagNum.ExDestination, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExDestination, exDestination);
            }
            if (noTradingSessions != null && MsgUtil.isTagInList(TagNum.NoTradingSessions, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoTradingSessions, noTradingSessions);
                if (tradingSessionGroups != null && tradingSessionGroups.length == noTradingSessions.intValue()) {
                    for (int i = 0; i < noTradingSessions.intValue(); i++) {
                        if (tradingSessionGroups[i] != null) {
                            bao.write(tradingSessionGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "TradingSessionsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTradingSessions.getValue(), error);
                }
            }
            if (processCode != null && MsgUtil.isTagInList(TagNum.ProcessCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.PrevClosePx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            }
            if (noLegs != null && MsgUtil.isTagInList(TagNum.NoLegs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (legOrdGroups != null && legOrdGroups.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (legOrdGroups[i] != null) {
                            bao.write(legOrdGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "LegOrdGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            
            if (MsgUtil.isTagInList(TagNum.LocateReqd, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LocateReqd, locateReqd);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (quantityType != null && MsgUtil.isTagInList(TagNum.QuantityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuantityType, quantityType.getValue());
            }
            if (orderQtyData != null) {
                bao.write(orderQtyData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (ordType != null && MsgUtil.isTagInList(TagNum.OrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            if (priceType != null && MsgUtil.isTagInList(TagNum.PriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (MsgUtil.isTagInList(TagNum.StopPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StopPx, stopPx);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ComplianceID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ComplianceID, complianceID);
            }
            if (MsgUtil.isTagInList(TagNum.SolicitedFlag, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SolicitedFlag, solicitedFlag);
            }
            if (MsgUtil.isTagInList(TagNum.IOIID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIID, IOIID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            }
            if (timeInForce != null && MsgUtil.isTagInList(TagNum.TimeInForce, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.EffectiveTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            }
            if (MsgUtil.isTagInList(TagNum.ExpireDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            }
            if (MsgUtil.isTagInList(TagNum.ExpireTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            }
            if (GTBookingInst != null && MsgUtil.isTagInList(TagNum.GTBookingInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.GTBookingInst, GTBookingInst.getValue());
            }
            if (commissionData != null) {
                bao.write(commissionData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (orderCapacity != null && MsgUtil.isTagInList(TagNum.OrderCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrderRestrictions, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            }
            if (custOrderCapacity != null && MsgUtil.isTagInList(TagNum.CustOrderCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ForexReq, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ForexReq, forexReq);
            }
            if (settlCurrency != null && MsgUtil.isTagInList(TagNum.SettlCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (bookingType != null && MsgUtil.isTagInList(TagNum.BookingType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BookingType, bookingType.getValue());
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
            if (positionEffect != null && MsgUtil.isTagInList(TagNum.PositionEffect, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
            }
            if (coveredOrUncovered != null && MsgUtil.isTagInList(TagNum.CoveredOrUncovered, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CoveredOrUncovered, coveredOrUncovered.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MaxShow, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaxShow, maxShow);
            }
            if (MsgUtil.isTagInList(TagNum.TargetStrategy, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TargetStrategy, targetStrategy);
            }
            if (MsgUtil.isTagInList(TagNum.TargetStrategyParameters, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TargetStrategyParameters, targetStrategyParameters);
            }
            if (MsgUtil.isTagInList(TagNum.ParticipationRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ParticipationRate, participationRate);
            }           
            if (MsgUtil.isTagInList(TagNum.PegOffsetValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            }
            if (discretionInst != null && MsgUtil.isTagInList(TagNum.DiscretionInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DiscretionOffsetValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
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
            if (MsgUtil.isTagInList(TagNum.Designation, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Designation, designation);
            }
            if (multiLegRptTypeReq != null && MsgUtil.isTagInList(TagNum.MultiLegRptTypeReq, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MultiLegRptTypeReq, multiLegRptTypeReq.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.NetMoney, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
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
        if (ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAllocs != null && noAllocs.intValue() > 0) {
                message.reset();
                allocGroups = new PreAllocMLegGroup[noAllocs.intValue()];
                for (int i = 0; i < noAllocs.intValue(); i++) {
                    PreAllocMLegGroup group = new PreAllocMLegGroup43(context);
                    group.decode(message);
                    allocGroups[i] = group;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
        if (TRAD_SESSION_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTradingSessions != null && noTradingSessions.intValue() > 0) {
                message.reset();
                tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
                for (int i = 0; i < noTradingSessions.intValue(); i++) {
                    TradingSessionGroup group = new TradingSessionGroup43(context);
                    group.decode(message);
                    tradingSessionGroups[i] = group;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument43(context);
            }
            instrument.decode(tag, message);
        }
        if (LEG_ORD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                legOrdGroups = new LegOrdGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    LegOrdGroup group = new LegOrdGroup43(context);
                    group.decode(message);
                    legOrdGroups[i] = group;
                }
            }
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData43(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (COMMISSION_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (commissionData == null) {
                commissionData = new CommissionData43(context);
            }
            commissionData.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NewOrderMultilegMsg] message version [4.3].";
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
