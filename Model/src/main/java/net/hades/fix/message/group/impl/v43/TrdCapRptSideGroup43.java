/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdCapRptSideGroup43.java
 *
 * $Id: TrdCapRptSideGroup43.java,v 1.1 2011-10-21 10:31:05 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.ClrInstGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v43.CommissionData43;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ContAmtGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.group.TrdCapRptSideGroup;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.MultiLegReportingType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of TrdCapRptSideGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class TrdCapRptSideGroup43 extends TrdCapRptSideGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Side.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.Account.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.ProcessCode.getValue(),
        TagNum.OddLot.getValue(),
        TagNum.NoClearingInstructions.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.TradeInputSource.getValue(),
        TagNum.TradeInputDevice.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.SolicitedFlag.getValue(),
        TagNum.OrderCapacity.getValue(),
        TagNum.OrderRestrictions.getValue(),
        TagNum.CustOrderCapacity.getValue(),
        TagNum.TransBkdTime.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.GrossTradeAmt.getValue(),
        TagNum.NumDaysInterest.getValue(),
        TagNum.ExDate.getValue(),
        TagNum.AccruedInterestRate.getValue(),
        TagNum.AccruedInterestAmt.getValue(),
        TagNum.Concession.getValue(),
        TagNum.TotalTakedown.getValue(),
        TagNum.NetMoney.getValue(),
        TagNum.SettlCurrAmt.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.SettlCurrFxRate.getValue(),
        TagNum.SettlCurrFxRateCalc.getValue(),
        TagNum.PositionEffect.getValue(),
        TagNum.Text.getValue(),
        TagNum.MultiLegReportingType.getValue(),
        TagNum.NoContAmts.getValue(),
        TagNum.NoMiscFees.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    protected static final Set<Integer> CLR_INST_GROUP_TAGS = new ClrInstGroup43().getFragmentAllTags();
    protected static final Set<Integer> COMMISSION_DATA_COMP_TAGS = new CommissionData43().getFragmentAllTags();
    protected static final Set<Integer> CONT_AMT_GROUP_TAGS = new ContAmtGroup43().getFragmentAllTags();
    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(CLR_INST_GROUP_TAGS);
        ALL_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        ALL_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(CLR_INST_GROUP_TAGS);
        START_COMP_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(CONT_AMT_GROUP_TAGS);
        START_COMP_TAGS.addAll(MISC_FEE_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TrdCapRptSideGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public TrdCapRptSideGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }
            
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
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
    public Integer getNoClearingInstructions() {
        return noClearingInstructions;
    }

    @Override
    public void setNoClearingInstructions(Integer noClearingInstructions) {
        this.noClearingInstructions = noClearingInstructions;
        if (noClearingInstructions != null) {
            clrInstGroups = new ClrInstGroup[noClearingInstructions.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < clrInstGroups.length; i++) {
                clrInstGroups[i] = new ClrInstGroup43(context);
            }
        }
    }

    @Override
    public ClrInstGroup[] getClrInstGroups() {
        return clrInstGroups;
    }

    public void setClrInstGroups(ClrInstGroup[] clrInstGroups) {
        this.clrInstGroups = clrInstGroups;
        if (clrInstGroups != null) {
            noClearingInstructions = new Integer(clrInstGroups.length);
        }
    }

    @Override
    public ClrInstGroup addClrInstGroup() {
        ClrInstGroup group = new ClrInstGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<ClrInstGroup> groups = new ArrayList<ClrInstGroup>();
        if (clrInstGroups != null && clrInstGroups.length > 0) {
            groups = new ArrayList<ClrInstGroup>(Arrays.asList(clrInstGroups));
        }
        groups.add(group);
        clrInstGroups = groups.toArray(new ClrInstGroup[groups.size()]);
        noClearingInstructions = new Integer(clrInstGroups.length);

        return group;
    }

    @Override
    public ClrInstGroup deleteClrInstGroup(int index) {
        ClrInstGroup result = null;
        if (clrInstGroups != null && clrInstGroups.length > 0 && clrInstGroups.length > index) {
            List<ClrInstGroup> groups = new ArrayList<ClrInstGroup>(Arrays.asList(clrInstGroups));
            result = groups.remove(index);
            clrInstGroups = groups.toArray(new ClrInstGroup[groups.size()]);
            if (clrInstGroups.length > 0) {
                noClearingInstructions = new Integer(clrInstGroups.length);
            } else {
                clrInstGroups = null;
                noClearingInstructions = null;
            }
        }

        return result;
    }

    @Override
    public int clearClrInstGroups() {
        int result = 0;
        if (clrInstGroups != null && clrInstGroups.length > 0) {
            result = clrInstGroups.length;
            clrInstGroups = null;
            noClearingInstructions = null;
        }

        return result;
    }

    @Override
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    @Override
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    @Override
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    @Override
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    @Override
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    @Override
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
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

    @Override
    public Double getGrossTradeAmt() {
        return grossTradeAmt;
    }

    @Override
    public void setGrossTradeAmt(Double grossTradeAmt) {
        this.grossTradeAmt = grossTradeAmt;
    }

    @Override
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    @Override
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    @Override
    public MultiLegReportingType getMultilegReportingType() {
        return multilegReportingType;
    }

    @Override
    public void setMultilegReportingType(MultiLegReportingType multilegReportingType) {
        this.multilegReportingType = multilegReportingType;
    }

    @Override
    public Integer getNoContAmts() {
        return noContAmts;
    }

    @Override
    public void setNoContAmts(Integer noContAmts) {
        this.noContAmts = noContAmts;
        if (noContAmts != null) {
            contAmtGroups = new ContAmtGroup[noContAmts.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < contAmtGroups.length; i++) {
                contAmtGroups[i] = new ContAmtGroup43(context);
            }
        }
    }

    @Override
    public ContAmtGroup[] getContAmtGroups() {
        return contAmtGroups;
    }

    public void setContAmtGroups(ContAmtGroup[] contAmtGroups) {
        this.contAmtGroups = contAmtGroups;
        if (contAmtGroups != null) {
            noContAmts = new Integer(contAmtGroups.length);
        }
    }

    @Override
    public ContAmtGroup addContAmtGroup() {
        ContAmtGroup group = new ContAmtGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<ContAmtGroup> groups = new ArrayList<ContAmtGroup>();
        if (contAmtGroups != null && contAmtGroups.length > 0) {
            groups = new ArrayList<ContAmtGroup>(Arrays.asList(contAmtGroups));
        }
        groups.add(group);
        contAmtGroups = groups.toArray(new ContAmtGroup[groups.size()]);
        noContAmts = new Integer(contAmtGroups.length);

        return group;
    }

    @Override
    public ContAmtGroup deleteContAmtGroup(int index) {
        ContAmtGroup result = null;
        if (contAmtGroups != null && contAmtGroups.length > 0 && contAmtGroups.length > index) {
            List<ContAmtGroup> groups = new ArrayList<ContAmtGroup>(Arrays.asList(contAmtGroups));
            result = groups.remove(index);
            contAmtGroups = groups.toArray(new ContAmtGroup[groups.size()]);
            if (contAmtGroups.length > 0) {
                noContAmts = new Integer(contAmtGroups.length);
            } else {
                contAmtGroups = null;
                noContAmts = null;
            }
        }

        return result;
    }

    @Override
    public int clearContAmtGroups() {
        int result = 0;
        if (contAmtGroups != null && contAmtGroups.length > 0) {
            result = contAmtGroups.length;
            contAmtGroups = null;
            noContAmts = null;
        }

        return result;
    }

    @Override
    public Integer getNoMiscFees() {
        return noMiscFees;
    }

    @Override
    public void setNoMiscFees(Integer noMiscFees) {
        this.noMiscFees = noMiscFees;
        if (noMiscFees != null) {
            miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < miscFeeGroups.length; i++) {
                miscFeeGroups[i] = new MiscFeeGroup43(context);
            }
        }
    }

    @Override
    public MiscFeeGroup[] getMiscFeeGroups() {
        return miscFeeGroups;
    }

    public void setMiscFeeGroups(MiscFeeGroup[] miscFeeGroups) {
        this.miscFeeGroups = miscFeeGroups;
        if (miscFeeGroups != null) {
            noMiscFees = new Integer(miscFeeGroups.length);
        }
    }

    @Override
    public MiscFeeGroup addMiscFeeGroup() {
        MiscFeeGroup group = new MiscFeeGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<MiscFeeGroup> groups = new ArrayList<MiscFeeGroup>();
        if (miscFeeGroups != null && miscFeeGroups.length > 0) {
            groups = new ArrayList<MiscFeeGroup>(Arrays.asList(miscFeeGroups));
        }
        groups.add(group);
        miscFeeGroups = groups.toArray(new MiscFeeGroup[groups.size()]);
        noMiscFees = new Integer(miscFeeGroups.length);

        return group;
    }

    @Override
    public MiscFeeGroup deleteMiscFeeGroup(int index) {
        MiscFeeGroup result = null;
        if (miscFeeGroups != null && miscFeeGroups.length > 0 && miscFeeGroups.length > index) {
            List<MiscFeeGroup> groups = new ArrayList<MiscFeeGroup>(Arrays.asList(miscFeeGroups));
            result = groups.remove(index);
            miscFeeGroups = groups.toArray(new MiscFeeGroup[groups.size()]);
            if (miscFeeGroups.length > 0) {
                noMiscFees = new Integer(miscFeeGroups.length);
            } else {
                miscFeeGroups = null;
                noMiscFees = null;
            }
        }

        return result;
    }

    @Override
    public int clearMiscFeeGroups() {
        int result = 0;
        if (miscFeeGroups != null && miscFeeGroups.length > 0) {
            result = miscFeeGroups.length;
            miscFeeGroups = null;
            noMiscFees = null;
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
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (accountType != null && MsgUtil.isTagInList(TagNum.AccountType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (processCode != null && MsgUtil.isTagInList(TagNum.ProcessCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OddLot, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OddLot, oddLot);
            }
            if (noClearingInstructions != null && MsgUtil.isTagInList(TagNum.NoClearingInstructions, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoClearingInstructions, noClearingInstructions);
                if (clrInstGroups != null && clrInstGroups.length == noClearingInstructions.intValue()) {
                    for (int i = 0; i < noClearingInstructions.intValue(); i++) {
                        if (clrInstGroups[i] != null) {
                            bao.write(clrInstGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "ClrInstGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoClearingInstructions.getValue(), error);
                }
            }
            if (clearingFeeIndicator != null && MsgUtil.isTagInList(TagNum.ClearingFeeIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeInputSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeInputSource, tradeInputSource);
            }
            if (MsgUtil.isTagInList(TagNum.TradeInputDevice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradeInputDevice, tradeInputDevice);
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
            if (orderCapacity != null && MsgUtil.isTagInList(TagNum.OrderCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderCapacity, orderCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrderRestrictions, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderRestrictions, orderRestrictions);
            }
            if (custOrderCapacity != null && MsgUtil.isTagInList(TagNum.CustOrderCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CustOrderCapacity, custOrderCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TransBkdTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransBkdTime, transBkdTime);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (commissionData != null) {
                bao.write(commissionData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.GrossTradeAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.GrossTradeAmt, grossTradeAmt);
            }
            if (MsgUtil.isTagInList(TagNum.NumDaysInterest, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NumDaysInterest, numDaysInterest);
            }
            if (MsgUtil.isTagInList(TagNum.ExDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.ExDate, exDate);
            }
            if (MsgUtil.isTagInList(TagNum.AccruedInterestRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            }
            if (MsgUtil.isTagInList(TagNum.AccruedInterestAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
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
            if (MsgUtil.isTagInList(TagNum.SettlCurrFxRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRate, settlCurrFxRate);
            }
            if (settlCurrFxRateCalc != null && MsgUtil.isTagInList(TagNum.SettlCurrFxRateCalc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            if (positionEffect != null && MsgUtil.isTagInList(TagNum.PositionEffect, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PositionEffect, positionEffect.getValue());
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
            if (multilegReportingType != null && MsgUtil.isTagInList(TagNum.MultiLegReportingType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MultiLegReportingType, multilegReportingType.getValue());
            }
            if (noContAmts != null && MsgUtil.isTagInList(TagNum.NoContAmts, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoContAmts, noContAmts);
                if (contAmtGroups != null && contAmtGroups.length == noContAmts.intValue()) {
                    for (int i = 0; i < noContAmts.intValue(); i++) {
                        if (contAmtGroups[i] != null) {
                            bao.write(contAmtGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "ContAmtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoContAmts.getValue(), error);
                }
            }
            if (noMiscFees != null && MsgUtil.isTagInList(TagNum.NoMiscFees, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoMiscFees, noMiscFees);
                if (miscFeeGroups != null && miscFeeGroups.length == noMiscFees.intValue()) {
                    for (int i = 0; i < noMiscFees.intValue(); i++) {
                        if (miscFeeGroups[i] != null) {
                            bao.write(miscFeeGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "MiscFeeGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMiscFees.getValue(), error);
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
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
        if (CLR_INST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noClearingInstructions != null && noClearingInstructions.intValue() > 0) {
                message.reset();
                clrInstGroups = new ClrInstGroup[noClearingInstructions.intValue()];
                for (int i = 0; i < noClearingInstructions.intValue(); i++) {
                    ClrInstGroup component = new ClrInstGroup43(context);
                    component.decode(message);
                    clrInstGroups[i] = component;
                }
            }
        }
        if (COMMISSION_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (commissionData == null) {
                commissionData = new CommissionData43(context);
            }
            commissionData.decode(tag, message);
        }
        if (CONT_AMT_GROUP_TAGS.contains(tag.tagNum)) {
            if (noContAmts != null && noContAmts.intValue() > 0) {
                message.reset();
                contAmtGroups = new ContAmtGroup[noContAmts.intValue()];
                for (int i = 0; i < noContAmts.intValue(); i++) {
                    ContAmtGroup component = new ContAmtGroup43(context);
                    component.decode(message);
                    contAmtGroups[i] = component;
                }
            }
        }
        if (MISC_FEE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMiscFees != null && noMiscFees.intValue() > 0) {
                message.reset();
                miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
                for (int i = 0; i < noMiscFees.intValue(); i++) {
                    MiscFeeGroup component = new MiscFeeGroup43(context);
                    component.decode(message);
                    miscFeeGroups[i] = component;
                }
            }
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
        return "This tag is not supported in [TrdCapRptSideGroup] group version [4.3].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
