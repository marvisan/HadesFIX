/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsg41.java
 *
 * $Id: SettlementInstructionsMsg41.java,v 1.1 2011-03-25 04:50:53 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

import net.hades.fix.message.Header;
import net.hades.fix.message.SettlementInstructionsMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.PaymentMethod;
import net.hades.fix.message.type.SettlDeliveryType;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlInstReqRejCode;
import net.hades.fix.message.type.SettlInstSource;
import net.hades.fix.message.type.SettlInstTransType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.StandInstDbType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.1 SettlementInstructionsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class SettlementInstructionsMsg41 extends SettlementInstructionsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V41 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlInstID.getValue(),
        TagNum.SettlInstMsgID.getValue(),
        TagNum.SettlInstReqID.getValue(),
        TagNum.SettlInstTransType.getValue(),
        TagNum.SettlInstRefID.getValue(),
        TagNum.SettlInstMode.getValue(),
        TagNum.SettlInstReqRejCode.getValue(),
        TagNum.Text.getValue(),
        TagNum.SettlInstSource.getValue(),
        TagNum.AllocAccount.getValue(),
        TagNum.IndividualAllocID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.SettlLocation.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.AllocID.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.Side.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.ClientID.getValue(),
        TagNum.ExecBroker.getValue(),
        TagNum.StandInstDbType.getValue(),
        TagNum.StandInstDbName.getValue(),
        TagNum.StandInstDbID.getValue(),
        TagNum.SettlDeliveryType.getValue(),
        TagNum.SettlDepositoryCode.getValue(),
        TagNum.SettlBrkrCode.getValue(),
        TagNum.SettlInstCode.getValue(),
        TagNum.SecuritySettlAgentName.getValue(),
        TagNum.SecuritySettlAgentCode.getValue(),
        TagNum.SecuritySettlAgentAcctNum.getValue(),
        TagNum.SecuritySettlAgentAcctName.getValue(),
        TagNum.SecuritySettlAgentContactName.getValue(),
        TagNum.SecuritySettlAgentContactPhone.getValue(),
        TagNum.CashSettlAgentName.getValue(),
        TagNum.CashSettlAgentCode.getValue(),
        TagNum.CashSettlAgentAcctNum.getValue(),
        TagNum.CashSettlAgentAcctName.getValue(),
        TagNum.CashSettlAgentContactName.getValue(),
        TagNum.CashSettlAgentContactPhone.getValue(),
        TagNum.PaymentMethod.getValue(),
        TagNum.PaymentRef.getValue(),
        TagNum.CardHolderName.getValue(),
        TagNum.CardNumber.getValue(),
        TagNum.CardStartDate.getValue(),
        TagNum.CardExpDate.getValue(),
        TagNum.CardIssNum.getValue(),
        TagNum.PaymentDate.getValue(),
        TagNum.PaymentRemitterID.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS_V41 = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V41);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V41;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlementInstructionsMsg41() {
        super();
    }

    public SettlementInstructionsMsg41(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SettlementInstructionsMsg41(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SettlementInstructionsMsg41(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public String getSettlInstID() {
        return settlInstID;
    }

    @Override
    public void setSettlInstID(String settlInstID) {
        this.settlInstID = settlInstID;
    }

    @Override
    public String getSettlInstMsgID() {
        return settlInstMsgID;
    }

    @Override
    public void setSettlInstMsgID(String settlInstMsgID) {
        this.settlInstMsgID = settlInstMsgID;
    }

    @Override
    public String getSettlInstReqID() {
        return settlInstReqID;
    }

    @Override
    public void setSettlInstReqID(String settlInstReqID) {
        this.settlInstReqID = settlInstReqID;
    }

    @Override
    public SettlInstTransType getSettlInstTransType() {
        return settlInstTransType;
    }

    @Override
    public void setSettlInstTransType(SettlInstTransType settlInstTransType) {
        this.settlInstTransType = settlInstTransType;
    }

    @Override
    public String getSettlInstRefID() {
        return settlInstRefID;
    }

    @Override
    public void setSettlInstRefID(String settlInstRefID) {
        this.settlInstRefID = settlInstRefID;
    }

    @Override
    public SettlInstMode getSettlInstMode() {
        return settlInstMode;
    }

    @Override
    public void setSettlInstMode(SettlInstMode settlInstMode) {
        this.settlInstMode = settlInstMode;
    }

    @Override
    public SettlInstReqRejCode getSettlInstReqRejCode() {
        return settlInstReqRejCode;
    }

    @Override
    public void setSettlInstReqRejCode(SettlInstReqRejCode settlInstReqRejCode) {
        this.settlInstReqRejCode = settlInstReqRejCode;
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
    public SettlInstSource getSettlInstSource() {
        return settlInstSource;
    }

    @Override
    public void setSettlInstSource(SettlInstSource settlInstSource) {
        this.settlInstSource = settlInstSource;
    }

    @Override
    public String getAllocAccount() {
        return allocAccount;
    }

    @Override
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    @Override
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    @Override
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
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
    public String getSettlLocation() {
        return settlLocation;
    }

    @Override
    public void setSettlLocation(String settlLocation) {
        this.settlLocation = settlLocation;
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
    public String getAllocID() {
        return allocID;
    }

    @Override
    public void setAllocID(String allocID) {
        this.allocID = allocID;
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
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
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
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }
    
    @Override
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    @Override
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
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
    public StandInstDbType getStandInstDbType() {
        return standInstDbType;
    }

    @Override
    public void setStandInstDbType(StandInstDbType standInstDbType) {
        this.standInstDbType = standInstDbType;
    }

    @Override
    public String getStandInstDbName() {
        return standInstDbName;
    }

    @Override
    public void setStandInstDbName(String standInstDbName) {
        this.standInstDbName = standInstDbName;
    }

    @Override
    public String getStandInstDbID() {
        return standInstDbID;
    }

    @Override
    public void setStandInstDbID(String standInstDbID) {
        this.standInstDbID = standInstDbID;
    }

    @Override
    public SettlDeliveryType getSettlDeliveryType() {
        return settlDeliveryType;
    }

    @Override
    public void setSettlDeliveryType(SettlDeliveryType settlDeliveryType) {
        this.settlDeliveryType = settlDeliveryType;
    }

    @Override
    public String getSettlDepositoryCode() {
        return settlDepositoryCode;
    }

    @Override
    public void setSettlDepositoryCode(String settlDepositoryCode) {
        this.settlDepositoryCode = settlDepositoryCode;
    }

    @Override
    public String getSettlBrkrCode() {
        return settlBrkrCode;
    }

    @Override
    public void setSettlBrkrCode(String settlBrkrCode) {
        this.settlBrkrCode = settlBrkrCode;
    }

    @Override
    public String getSettlInstCode() {
        return settlInstCode;
    }

    @Override
    public void setSettlInstCode(String settlInstCode) {
        this.settlInstCode = settlInstCode;
    }

    @Override
    public String getSecuritySettlAgentName() {
        return securitySettlAgentName;
    }

    @Override
    public void setSecuritySettlAgentName(String securitySettlAgentName) {
        this.securitySettlAgentName = securitySettlAgentName;
    }

    @Override
    public String getSecuritySettlAgentCode() {
        return securitySettlAgentCode;
    }

    @Override
    public void setSecuritySettlAgentCode(String securitySettlAgentCode) {
        this.securitySettlAgentCode = securitySettlAgentCode;
    }

    @Override
    public String getSecuritySettlAgentAcctNum() {
        return securitySettlAgentAcctNum;
    }

    @Override
    public void setSecuritySettlAgentAcctNum(String securitySettlAgentAcctNum) {
        this.securitySettlAgentAcctNum = securitySettlAgentAcctNum;
    }

    @Override
    public String getSecuritySettlAgentAcctName() {
        return securitySettlAgentAcctName;
    }

    @Override
    public void setSecuritySettlAgentAcctName(String securitySettlAgentAcctName) {
        this.securitySettlAgentAcctName = securitySettlAgentAcctName;
    }

    @Override
    public String getSecuritySettlAgentContactName() {
        return securitySettlAgentContactName;
    }

    @Override
    public void setSecuritySettlAgentContactName(String securitySettlAgentContactName) {
        this.securitySettlAgentContactName = securitySettlAgentContactName;
    }

    @Override
    public String getSecuritySettlAgentContactPhone() {
        return securitySettlAgentContactPhone;
    }

    @Override
    public void setSecuritySettlAgentContactPhone(String securitySettlAgentContactPhone) {
        this.securitySettlAgentContactPhone = securitySettlAgentContactPhone;
    }

    @Override
    public String getCashSettlAgentName() {
        return cashSettlAgentName;
    }

    @Override
    public void setCashSettlAgentName(String cashSettlAgentName) {
        this.cashSettlAgentName = cashSettlAgentName;
    }

    @Override
    public String getCashSettlAgentCode() {
        return cashSettlAgentCode;
    }

    @Override
    public void setCashSettlAgentCode(String cashSettlAgentCode) {
        this.cashSettlAgentCode = cashSettlAgentCode;
    }

    @Override
    public String getCashSettlAgentAcctNum() {
        return cashSettlAgentAcctNum;
    }

    @Override
    public void setCashSettlAgentAcctNum(String cashSettlAgentAcctNum) {
        this.cashSettlAgentAcctNum = cashSettlAgentAcctNum;
    }

    @Override
    public String getCashSettlAgentAcctName() {
        return cashSettlAgentAcctName;
    }

    @Override
    public void setCashSettlAgentAcctName(String cashSettlAgentAcctName) {
        this.cashSettlAgentAcctName = cashSettlAgentAcctName;
    }

    @Override
    public String getCashSettlAgentContactName() {
        return cashSettlAgentContactName;
    }

    @Override
    public void setCashSettlAgentContactName(String cashSettlAgentContactName) {
        this.cashSettlAgentContactName = cashSettlAgentContactName;
    }

    @Override
    public String getCashSettlAgentContactPhone() {
        return cashSettlAgentContactPhone;
    }

    @Override
    public void setCashSettlAgentContactPhone(String cashSettlAgentContactPhone) {
        this.cashSettlAgentContactPhone = cashSettlAgentContactPhone;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String getPaymentRef() {
        return paymentRef;
    }

    @Override
    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    @Override
    public String getCardHolderName() {
        return cardHolderName;
    }

    @Override
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public Date getCardStartDate() {
        return cardStartDate;
    }

    @Override
    public void setCardStartDate(Date cardStartDate) {
        this.cardStartDate = cardStartDate;
    }

    @Override
    public Date getCardExpDate() {
        return cardExpDate;
    }

    @Override
    public void setCardExpDate(Date cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    @Override
    public String getCardIssNum() {
        return cardIssNum;
    }

    @Override
    public void setCardIssNum(String cardIssNum) {
        this.cardIssNum = cardIssNum;
    }

    @Override
    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String getPaymentRemitterID() {
        return paymentRemitterID;
    }

    @Override
    public void setPaymentRemitterID(String paymentRemitterID) {
        this.paymentRemitterID = paymentRemitterID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (settlInstID == null || settlInstID.trim().isEmpty()) {
            errorMsg.append(" [SettlInstID]");
            hasMissingTag = true;
        }
        if (settlInstTransType == null) {
            errorMsg.append(" [SettlInstTransType]");
            hasMissingTag = true;
        }
        if (settlInstMode == null) {
            errorMsg.append(" [SettlInstMode]");
            hasMissingTag = true;
        }
        if (settlInstSource == null) {
            errorMsg.append(" [SettlInstSource]");
            hasMissingTag = true;
        }
        if (allocAccount == null || allocAccount.trim().isEmpty()) {
            errorMsg.append(" [AllocAccount]");
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
            if (MsgUtil.isTagInList(TagNum.SettlInstID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstID, settlInstID);
            }
            if (MsgUtil.isTagInList(TagNum.SettlInstMsgID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstMsgID, settlInstMsgID);
            }
            if (MsgUtil.isTagInList(TagNum.SettlInstReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstReqID, settlInstReqID);
            }
            if (settlInstTransType != null && MsgUtil.isTagInList(TagNum.SettlInstTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstTransType, settlInstTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlInstRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstRefID, settlInstRefID);
            }
            if (settlInstMode != null && MsgUtil.isTagInList(TagNum.SettlInstMode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstMode, settlInstMode.getValue());
            }
            if (settlInstReqRejCode != null && MsgUtil.isTagInList(TagNum.SettlInstReqRejCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstReqRejCode, settlInstReqRejCode.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (settlInstSource != null && MsgUtil.isTagInList(TagNum.SettlInstSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstSource, settlInstSource.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AllocAccount, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            }
            if (MsgUtil.isTagInList(TagNum.IndividualAllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.SettlLocation, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlLocation, settlLocation);
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.AllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocID, allocID);
            }
            if (MsgUtil.isTagInList(TagNum.LastMkt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.EffectiveTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.ClientID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientID, clientID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecBroker, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            }
            if (standInstDbType != null && MsgUtil.isTagInList(TagNum.StandInstDbType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StandInstDbType, standInstDbType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.StandInstDbName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StandInstDbName, standInstDbName);
            }
            if (MsgUtil.isTagInList(TagNum.StandInstDbID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StandInstDbID, standInstDbID);
            }
            if (settlDeliveryType != null && MsgUtil.isTagInList(TagNum.SettlDeliveryType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlDeliveryType, settlDeliveryType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlDepositoryCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlDepositoryCode, settlDepositoryCode);
            }
            if (MsgUtil.isTagInList(TagNum.SettlBrkrCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlBrkrCode, settlBrkrCode);
            }
            if (MsgUtil.isTagInList(TagNum.SettlInstCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstCode, settlInstCode);
            }
            if (MsgUtil.isTagInList(TagNum.SecuritySettlAgentName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecuritySettlAgentName, securitySettlAgentName);
            }
            if (MsgUtil.isTagInList(TagNum.SecuritySettlAgentCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecuritySettlAgentCode, securitySettlAgentCode);
            }
            if (MsgUtil.isTagInList(TagNum.SecuritySettlAgentAcctNum, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecuritySettlAgentAcctNum, securitySettlAgentAcctNum);
            }
            if (MsgUtil.isTagInList(TagNum.SecuritySettlAgentAcctName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecuritySettlAgentAcctName, securitySettlAgentAcctName);
            }
            if (MsgUtil.isTagInList(TagNum.SecuritySettlAgentContactName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecuritySettlAgentContactName, securitySettlAgentContactName);
            }
            if (MsgUtil.isTagInList(TagNum.SecuritySettlAgentContactPhone, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecuritySettlAgentContactPhone, securitySettlAgentContactPhone);
            }
            if (MsgUtil.isTagInList(TagNum.CashSettlAgentName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashSettlAgentName, cashSettlAgentName);
            }
            if (MsgUtil.isTagInList(TagNum.CashSettlAgentCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashSettlAgentCode, cashSettlAgentCode);
            }
            if (MsgUtil.isTagInList(TagNum.CashSettlAgentAcctNum, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashSettlAgentAcctNum, cashSettlAgentAcctNum);
            }
            if (MsgUtil.isTagInList(TagNum.CashSettlAgentAcctName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashSettlAgentAcctName, cashSettlAgentAcctName);
            }
            if (MsgUtil.isTagInList(TagNum.CashSettlAgentContactName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashSettlAgentContactName, cashSettlAgentContactName);
            }
            if (MsgUtil.isTagInList(TagNum.CashSettlAgentContactPhone, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CashSettlAgentContactPhone, cashSettlAgentContactPhone);
            }
            if (paymentMethod != null && MsgUtil.isTagInList(TagNum.PaymentMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PaymentMethod, paymentMethod.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PaymentRef, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PaymentRef, paymentRef);
            }
            if (MsgUtil.isTagInList(TagNum.CardHolderName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CardHolderName, cardHolderName);
            }
            if (MsgUtil.isTagInList(TagNum.CardNumber, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CardNumber, cardNumber);
            }
            if (MsgUtil.isTagInList(TagNum.CardStartDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.CardStartDate, cardStartDate);
            }
            if (MsgUtil.isTagInList(TagNum.CardExpDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.CardExpDate, cardExpDate);
            }
            if (MsgUtil.isTagInList(TagNum.CardIssNum, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CardIssNum, cardIssNum);
            }
            if (MsgUtil.isTagInList(TagNum.PaymentDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.PaymentDate, paymentDate);
            }
            if (MsgUtil.isTagInList(TagNum.PaymentRemitterID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PaymentRemitterID, paymentRemitterID);
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
        return "This tag is not supported in [SettlementInstructionsMsg] message version [4.1].";
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
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_V41;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
