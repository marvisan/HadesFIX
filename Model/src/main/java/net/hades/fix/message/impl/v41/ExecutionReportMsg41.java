/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecutionReportMsg41.java
 *
 * $Id: ExecutionReportMsg41.java,v 1.3 2011-04-14 23:45:00 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v41.MiscFeeGroup41;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExecTransType;
import net.hades.fix.message.type.ExecType;
import net.hades.fix.message.type.LastCapacity;
import net.hades.fix.message.type.OrdRejReason;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.Rule80A;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.1 ExecutionReport implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class ExecutionReportMsg41 extends ExecutionReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V41 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.OrigClOrdID.getValue(),
        TagNum.ClientID.getValue(),
        TagNum.ExecBroker.getValue(),
        TagNum.ListID.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.ExecTransType.getValue(),
        TagNum.ExecRefID.getValue(),
        TagNum.ExecType.getValue(),
        TagNum.OrdStatus.getValue(),
        TagNum.OrdRejReason.getValue(),
        TagNum.Account.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.Symbol.getValue(),
        TagNum.SymbolSfx.getValue(),
        TagNum.SecurityID.getValue(),
        TagNum.SecurityIDSource.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.MaturityMonthYear.getValue(),
        TagNum.MaturityDay.getValue(),
        TagNum.PutOrCall.getValue(),
        TagNum.StrikePrice.getValue(),
        TagNum.OptAttribute.getValue(),
        TagNum.SecurityExchange.getValue(),
        TagNum.Issuer.getValue(),
        TagNum.SecurityDesc.getValue(),
        TagNum.Side.getValue(),
        TagNum.OrderQty.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.Price.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.PegOffsetValue.getValue(),
        TagNum.Currency.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.Rule80A.getValue(),
        TagNum.LastQty.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.LastSpotRate.getValue(),
        TagNum.LastForwardPoints.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.LastCapacity.getValue(),
        TagNum.LeavesQty.getValue(),
        TagNum.CumQty.getValue(),
        TagNum.AvgPx.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.ReportToExch.getValue(),
        TagNum.Commission.getValue(),
        TagNum.CommType.getValue(),
        TagNum.NetMoney.getValue(),
        TagNum.SettlCurrAmt.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.SettlCurrFxRate.getValue(),
        TagNum.SettlCurrFxRateCalc.getValue(),
        TagNum.Text.getValue(),
        TagNum.NoMiscFees.getValue()
    }));

    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup41().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V41);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MISC_FEE_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V41;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ExecutionReportMsg41() {
        super();
    }

    public ExecutionReportMsg41(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ExecutionReportMsg41(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public ExecutionReportMsg41(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V41;
    }

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
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    @Override
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    @Override
    public String getClientID() {
        return clientID;
    }

    @Override
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    @Override
    public String getExecBroker() {
        return execBroker;
    }

    @Override
    public void setExecBroker(String execBroker) {
        this.execBroker = execBroker;
    }

    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
    }

    @Override
    public String getExecID() {
        return execID;
    }

    @Override
    public void setExecID(String execID) {
        this.execID = execID;
    }

    @Override
    public ExecTransType getExecTransType() {
        return execTransType;
    }

    @Override
    public void setExecTransType(ExecTransType execTransType) {
        this.execTransType = execTransType;
    }

    @Override
    public String getExecRefID() {
        return execRefID;
    }

    @Override
    public void setExecRefID(String execRefID) {
        this.execRefID = execRefID;
    }
    
    @Override
    public ExecType getExecType() {
        return execType;
    }

    @Override
    public void setExecType(ExecType execType) {
        this.execType = execType;
    }

    @Override
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    @Override
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    @Override
    public OrdRejReason getOrdRejReason() {
        return ordRejReason;
    }

    @Override
    public void setOrdRejReason(OrdRejReason ordRejReason) {
        this.ordRejReason = ordRejReason;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String getSettlType() {
        return settlType;
    }

    @Override
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    @Override
    public Date getSettlDate() {
        return settlDate;
    }

    @Override
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbolSfx() {
        return symbolSfx;
    }

    @Override
    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
    }

    @Override
    public String getSecurityID() {
        return securityID;
    }

    @Override
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    @Override
    public String getSecurityIDSource() {
        return securityIDSource;
    }

    @Override
    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
    }

    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    @Override
    public String getMaturityMonthYear() {
        return maturityMonthYear;
    }

    @Override
    public void setMaturityMonthYear(String maturityMonthYear) {
        this.maturityMonthYear = maturityMonthYear;
    }

    @Override
    public Integer getMaturityDay() {
        return maturityDay;
    }

    @Override
    public void setMaturityDay(Integer maturityDay) {
        this.maturityDay = maturityDay;
    }

    @Override
    public PutOrCall getPutOrCall() {
        return putOrCall;
    }

    @Override
    public void setPutOrCall(PutOrCall putOrCall) {
        this.putOrCall = putOrCall;
    }

    @Override
    public Double getStrikePrice() {
        return strikePrice;
    }

    @Override
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }
    
    @Override
    public Character getOptAttribute() {
        return optAttribute;
    }

    @Override
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
    }

    @Override
    public String getSecurityExchange() {
        return securityExchange;
    }

    @Override
    public void setSecurityExchange(String securityExchange) {
        this.securityExchange = securityExchange;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String getSecurityDesc() {
        return securityDesc;
    }

    @Override
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public Double getOrderQty() {
        return orderQty;
    }

    @Override
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public OrdType getOrdType() {
        return ordType;
    }

    @Override
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Double getStopPx() {
        return stopPx;
    }

    @Override
    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    @Override
    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    @Override
    public void setPegOffsetValue(Double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
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
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    @Override
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String getExecInst() {
        return execInst;
    }

    @Override
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    @Override
    public Rule80A getRule80A() {
        return rule80A;
    }

    @Override
    public void setRule80A(Rule80A rule80A) {
        this.rule80A = rule80A;
    }

    @Override
    public Double getLastQty() {
        return lastQty;
    }

    @Override
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    @Override
    public Double getLastPx() {
        return lastPx;
    }

    @Override
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    @Override
    public Double getLastSpotRate() {
        return lastSpotRate;
    }

    @Override
    public void setLastSpotRate(Double lastSpotRate) {
        this.lastSpotRate = lastSpotRate;
    }

    @Override
    public Double getLastForwardPoints() {
        return lastForwardPoints;
    }

    @Override
    public void setLastForwardPoints(Double lastForwardPoints) {
        this.lastForwardPoints = lastForwardPoints;
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
    public LastCapacity getLastCapacity() {
        return lastCapacity;
    }

    @Override
    public void setLastCapacity(LastCapacity lastCapacity) {
        this.lastCapacity = lastCapacity;
    }

    @Override
    public Double getLeavesQty() {
        return leavesQty;
    }

    @Override
    public void setLeavesQty(Double leavesQty) {
        this.leavesQty = leavesQty;
    }

    @Override
    public Double getCumQty() {
        return cumQty;
    }

    @Override
    public void setCumQty(Double cumQty) {
        this.cumQty = cumQty;
    }

    @Override
    public Double getAvgPx() {
        return avgPx;
    }

    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public Boolean getReportToExch() {
        return reportToExch;
    }

    @Override
    public void setReportToExch(Boolean reportToExch) {
        this.reportToExch = reportToExch;
    }

    @Override
    public Double getCommission() {
        return commission;
    }

    @Override
    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Override
    public CommType getCommType() {
        return commType;
    }

    @Override
    public void setCommType(CommType commType) {
        this.commType = commType;
    }

    @Override
    public Double getNetMoney() {
        return netMoney;
    }

    @Override
    public void setNetMoney(Double netMoney) {
        this.netMoney = netMoney;
    }

    @Override
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    @Override
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
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
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    @Override
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    @Override
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    @Override
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
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
                miscFeeGroups[i] = new MiscFeeGroup41(context);
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
        MiscFeeGroup group = new MiscFeeGroup41(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
            hasMissingTag = true;
        }
        if (execID == null || execID.trim().isEmpty()) {
            errorMsg.append(" [ExecID]");
            hasMissingTag = true;
        }
        if (execTransType == null) {
            errorMsg.append(" [ExecTransType]");
            hasMissingTag = true;
        }
        if (execType == null) {
            errorMsg.append(" [ExecType]");
            hasMissingTag = true;
        }
        if (ordStatus == null) {
            errorMsg.append(" [OrdStatus]");
            hasMissingTag = true;
        }
        if (symbol == null || symbol.trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (orderQty == null) {
            errorMsg.append(" [OrderQty]");
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
        if (leavesQty == null) {
            errorMsg.append(" [LeavesQty]");
            hasMissingTag = true;
        }
        if (cumQty == null) {
            errorMsg.append(" [CumQty]");
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
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.OrigClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrigClOrdID, origClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.ClientID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientID, clientID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecBroker, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            }
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
            }
             if (MsgUtil.isTagInList(TagNum.ExecID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecID, execID);
            }
            if (execTransType != null && MsgUtil.isTagInList(TagNum.ExecTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecTransType, execTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExecRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecRefID, execRefID);
            }
            if (execType != null && MsgUtil.isTagInList(TagNum.ExecType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecType, execType.getValue());
            }
            if (ordStatus != null && MsgUtil.isTagInList(TagNum.OrdStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            if (ordRejReason != null && MsgUtil.isTagInList(TagNum.OrdRejReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdRejReason, ordRejReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (MsgUtil.isTagInList(TagNum.SettlType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlType, settlType);
            }
            if (MsgUtil.isTagInList(TagNum.SettlDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            }
            if (MsgUtil.isTagInList(TagNum.Symbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Symbol, symbol);
            }
            if (MsgUtil.isTagInList(TagNum.SymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityID, securityID);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource);
            }
            if (securityType != null && MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityDay, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityDay, maturityDay);
            }
            if (putOrCall != null && MsgUtil.isTagInList(TagNum.PutOrCall, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.StrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            }
            if (MsgUtil.isTagInList(TagNum.OptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.Issuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Issuer, issuer);
            }
            if (securityDesc != null && !securityDesc.trim().isEmpty() && MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.OrderQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderQty, orderQty);
            }
            if (ordType != null && MsgUtil.isTagInList(TagNum.OrdType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (MsgUtil.isTagInList(TagNum.StopPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StopPx, stopPx);
            }
            if (MsgUtil.isTagInList(TagNum.PegOffsetValue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (timeInForce != null && MsgUtil.isTagInList(TagNum.TimeInForce, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ExpireTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            }
            if (MsgUtil.isTagInList(TagNum.ExecInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            }
            if (rule80A != null && MsgUtil.isTagInList(TagNum.Rule80A, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Rule80A, rule80A.getValue());
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
            if (lastCapacity != null && MsgUtil.isTagInList(TagNum.LastCapacity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastCapacity, lastCapacity.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.LeavesQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LeavesQty, leavesQty);
            }
            if (MsgUtil.isTagInList(TagNum.CumQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CumQty, cumQty);
            }
            if (MsgUtil.isTagInList(TagNum.AvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AvgPx, avgPx);
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.ReportToExch, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ReportToExch, reportToExch);
            }
            if (MsgUtil.isTagInList(TagNum.Commission, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Commission, commission);
            }
            if (commType != null && MsgUtil.isTagInList(TagNum.CommType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
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
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
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
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMiscFees.getValue(), error);
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
        if (MISC_FEE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMiscFees != null && noMiscFees.intValue() > 0) {
                message.reset();
                miscFeeGroups = new MiscFeeGroup41[noMiscFees.intValue()];
                for (int i = 0; i < noMiscFees.intValue(); i++) {
                    MiscFeeGroup component = new MiscFeeGroup41(context);
                    component.decode(message);
                    miscFeeGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ExecutionReportMsg] message version [4.1].";
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
