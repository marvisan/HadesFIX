/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecAllocGroup.java
 *
 * $Id: AllocGroup.java,v 1.2 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.AllocSettlInstType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocHandlInst;
import net.hades.fix.message.type.AllocMethod;
import net.hades.fix.message.type.AllocPositionEffect;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IndividualAllocType;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * Execution allocation group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AllocGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AllocAccount.getValue(),
        TagNum.AllocAcctIDSource.getValue(),
        TagNum.MatchStatus.getValue(),
        TagNum.AllocPrice.getValue(),
        TagNum.AllocQty.getValue(),
        TagNum.IndividualAllocID.getValue(),
        TagNum.ProcessCode.getValue(),
        TagNum.SecondaryIndividualAllocID.getValue(),
        TagNum.AllocMethod.getValue(),
        TagNum.AllocCustomerCapacity.getValue(),
        TagNum.AllocPositionEffect.getValue(),
        TagNum.IndividualAllocType.getValue(),
        TagNum.BrokerOfCredit.getValue(),
        TagNum.NotifyBrokerOfCredit.getValue(),
        TagNum.AllocHandlInst.getValue(),
        TagNum.AllocText.getValue(),
        TagNum.ClientID.getValue(),
        TagNum.ExecBroker.getValue(),
        TagNum.AllocAvgPx.getValue(),
        TagNum.AllocNetMoney.getValue(),
        TagNum.SettlCurrAmt.getValue(),
        TagNum.AllocSettlCurrAmt.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.AllocSettlCurrency.getValue(),
        TagNum.SettlCurrFxRate.getValue(),
        TagNum.SettlCurrFxRateCalc.getValue(),
        TagNum.AccruedInterestAmt.getValue(),
        TagNum.AllocAccruedInterestAmt.getValue(),
        TagNum.AllocInterestAtMaturity.getValue(),
        TagNum.SettlInstMode.getValue(),
        TagNum.NoMiscFees.getValue(),
        TagNum.NoClearingInstructions.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.AllocSettlInstType.getValue(),
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedAllocTextLen.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 79 REQUIRED. Starting with 4.0 version.
     */
    protected String allocAccount;

    /**
     * TagNum = 661. Starting with 4.4 version.
     */
    protected AcctIDSource allocAcctIDSource;

    /**
     * TagNum = 573. Starting with 4.4 version.
     */
    protected MatchStatus matchStatus;

    /**
     * TagNum = 366. Starting with 4.2 version.
     */
    protected Double allocPrice;

    /**
     * TagNum = 80 REQUIRED. Starting with 4.0 version.
     */
    protected Double allocQty;

    /**
     * TagNum = 467. Starting with 4.3 version.
     */
    protected String individualAllocID;

    /**
     * TagNum = 81. Starting with 4.0 version.
     */
    protected ProcessCode processCode;

    /**
     * TagNum = 999. Starting with 5.0 version.
     */
    protected String secondaryIndividualAllocID;

    /**
     * TagNum = 1002. Starting with 5.0 version.
     */
    protected AllocMethod allocMethod;

    /**
     * TagNum = 993. Starting with 5.0 version.
     */
    protected String allocCustomerCapacity;

    /**
     * TagNum = 1047. Starting with 5.0 version.
     */
    protected AllocPositionEffect allocPositionEffect;

    /**
     * TagNum = 992. Starting with 5.0 version.
     */
    protected IndividualAllocType individualAllocType;

    /**
     * Starting with 4.3 version.
     */
    protected NestedParties nestedParties;

    /**
     * TagNum = 92. Starting with 4.1 version.
     */
    protected String brokerOfCredit;

    /**
     * TagNum = 208. Starting with 4.1 version.
     */
    protected Boolean notifyBrokerOfCredit;

    /**
     * TagNum = 209. Starting with 4.1 version.
     */
    protected AllocHandlInst allocHandlInst;

    /**
     * TagNum = 161. Starting with 4.1 version.
     */
    protected String allocText;

    /**
     * TagNum = 360. Starting with 4.2 version.
     */
    protected Integer encodedAllocTextLen;

    /**
     * TagNum = 361. Starting with 4.2 version.
     */
    protected byte[] encodedAllocText;

    /**
     * TagNum = 109. Starting with 4.0 version.
     */
    protected String clientID;

    /**
     * TagNum = 76. Starting with 4.0 version.
     */
    protected String execBroker;

    /**
     * Starting with 4.3 version.
     */
    protected CommissionData commissionData;

    /**
     * TagNum = 12. Starting with 4.0 version.
     */
    protected Double commission;

    /**
     * TagNum = 13. Starting with 4.0 version.
     */
    protected CommType commType;

    /**
     * TagNum = 153. Starting with 4.1 version.
     */
    protected Double allocAvgPx;

    /**
     * TagNum = 154. Starting with 4.1 version.
     */
    protected Double allocNetMoney;

    /**
     * TagNum = 119. Starting with 4.1 version.
     */
    protected Double settlCurrAmt;

    /**
     * TagNum = 737. Starting with 4.4 version.
     */
    protected Double allocSettlCurrAmt;

    /**
     * TagNum = 120. Starting with 4.1 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 120. Starting with 4.4 version.
     */
    protected Currency allocSettlCurrency;

    /**
     * TagNum = 155. Starting with 4.1 version.
     */
    protected Double settlCurrFxRate;

    /**
     * TagNum = 156. Starting with 4.1 version.
     */
    protected SettlCurrFxRateCalc settlCurrFxRateCalc;

    /**
     * TagNum = 159. Starting with 4.1 version.
     */
    protected Double accruedInterestAmt;

    /**
     * TagNum = 742. Starting with 4.4 version.
     */
    protected Double allocAccruedInterestAmt;

    /**
     * TagNum = 741. Starting with 4.4 version.
     */
    protected Double allocInterestAtMaturity;

    /**
     * TagNum = 160. Starting with 4.1 version.
     */
    protected SettlInstMode settlInstMode;

    /**
     * TagNum = 160. Starting with 4.0 version.
     */
    protected Integer noDlvyInst;

    /**
     * TagNum = 160. Starting with 4.0 version.
     */
    protected DlvyInstGroup[] dlvyInstGroups;

    /**
     * TagNum = 555. Starting with 4.0 version.
     */
    protected Integer noMiscFees;

    /**
     * Starting with 4.0 version.
     */
    protected MiscFeeGroup[] miscFeeGroups;

    /**
     * TagNum = 576. Starting with 4.4 version.
     */
    protected Integer noClearingInstructions;

    /**
     * Starting with 4.4 version.
     */
    protected ClrInstGroup[] clrInstGroups;

    /**
     * TagNum = 635. Starting with 4.4 version.
     */
    protected ClearingFeeIndicator clearingFeeIndicator;

    /**
     * TagNum = 780. Starting with 4.4 version.
     */
    protected AllocSettlInstType allocSettlInstType;

    /**
     * Starting with 4.4 version.
     */
    protected SettlInstructionsData settlInstructionsData;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AllocGroup() {
    }
    
    public AllocGroup(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AllocAccount, required=true)
    public String getAllocAccount() {
        return allocAccount;
    }

    /**
     * Message field setter.
     * @param allocAccount field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AllocAccount, required=true)
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAcctIDSource)
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    /**
     * Message field setter.
     * @param allocAcctIDSource field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAcctIDSource)
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    /**
     * Message field setter.
     * @param matchStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MatchStatus)
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.AllocPrice)
    public Double getAllocPrice() {
        return allocPrice;
    }

    /**
     * Message field setter.
     * @param allocPrice field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.AllocPrice)
    public void setAllocPrice(Double allocPrice) {
        this.allocPrice = allocPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AllocQty)
    public Double getAllocQty() {
        return allocQty;
    }

    /**
     * Message field setter.
     * @param allocQty field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.AllocQty)
    public void setAllocQty(Double allocQty) {
        this.allocQty = allocQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.IndividualAllocID)
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    /**
     * Message field setter.
     * @param individualAllocID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.IndividualAllocID)
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public ProcessCode getProcessCode() {
        return processCode;
    }

    /**
     * Message field setter.
     * @param processCode field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public void setProcessCode(ProcessCode processCode) {
        this.processCode = processCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryIndividualAllocID)
    public String getSecondaryIndividualAllocID() {
        return secondaryIndividualAllocID;
    }

    /**
     * Message field setter.
     * @param secondaryIndividualAllocID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SecondaryIndividualAllocID)
    public void setSecondaryIndividualAllocID(String secondaryIndividualAllocID) {
        this.secondaryIndividualAllocID = secondaryIndividualAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocMethod)
    public AllocMethod getAllocMethod() {
        return allocMethod;
    }

    /**
     * Message field setter.
     * @param allocMethod field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocMethod)
    public void setAllocMethod(AllocMethod allocMethod) {
        this.allocMethod = allocMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocCustomerCapacity)
    public String getAllocCustomerCapacity() {
        return allocCustomerCapacity;
    }

    /**
     * Message field setter.
     * @param allocCustomerCapacity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocCustomerCapacity)
    public void setAllocCustomerCapacity(String allocCustomerCapacity) {
        this.allocCustomerCapacity = allocCustomerCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocPositionEffect)
    public AllocPositionEffect getAllocPositionEffect() {
        return allocPositionEffect;
    }

    /**
     * Message field setter.
     * @param allocPositionEffect field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.AllocPositionEffect)
    public void setAllocPositionEffect(AllocPositionEffect allocPositionEffect) {
        this.allocPositionEffect = allocPositionEffect;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.IndividualAllocType)
    public IndividualAllocType getIndividualAllocType() {
        return individualAllocType;
    }

    /**
     * Message field setter.
     * @param individualAllocType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.IndividualAllocType)
    public void setIndividualAllocType(IndividualAllocType individualAllocType) {
        this.individualAllocType = individualAllocType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public NestedParties getNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void setNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component to null.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void clearNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.BrokerOfCredit)
    public String getBrokerOfCredit() {
        return brokerOfCredit;
    }

    /**
     * Message field setter.
     * @param brokerOfCredit field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.BrokerOfCredit)
    public void setBrokerOfCredit(String brokerOfCredit) {
        this.brokerOfCredit = brokerOfCredit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.NotifyBrokerOfCredit)
    public Boolean getNotifyBrokerOfCredit() {
        return notifyBrokerOfCredit;
    }

    /**
     * Message field setter.
     * @param notifyBrokerOfCredit field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.NotifyBrokerOfCredit)
    public void setNotifyBrokerOfCredit(Boolean notifyBrokerOfCredit) {
        this.notifyBrokerOfCredit = notifyBrokerOfCredit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocHandlInst)
    public AllocHandlInst getAllocHandlInst() {
        return allocHandlInst;
    }

    /**
     * Message field setter.
     * @param allocHandlInst field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocHandlInst)
    public void setAllocHandlInst(AllocHandlInst allocHandlInst) {
        this.allocHandlInst = allocHandlInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocText)
    public String getAllocText() {
        return allocText;
    }

    /**
     * Message field setter.
     * @param allocText field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocText)
    public void setAllocText(String allocText) {
        this.allocText = allocText;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedAllocTextLen)
    public Integer getEncodedAllocTextLen() {
        return encodedAllocTextLen;
    }

    /**
     * Message field setter.
     * @param encodedAllocTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedAllocTextLen)
    public void setEncodedAllocTextLen(Integer encodedAllocTextLen) {
        this.encodedAllocTextLen = encodedAllocTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedAllocText)
    public byte[] getEncodedAllocText() {
        return encodedAllocText;
    }

    /**
     * Message field setter.
     * @param encodedAllocText field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedAllocText)
    public void setEncodedAllocText(byte[] encodedAllocText) {
        this.encodedAllocText = encodedAllocText;
        if (encodedAllocTextLen == null) {
            encodedAllocTextLen = new Integer(encodedAllocText.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public String getClientID() {
        return clientID;
    }

    /**
     * Message field setter.
     * @param clientID field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public String getExecBroker() {
        return execBroker;
    }

    /**
     * Message field setter.
     * @param execBroker field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public void setExecBroker(String execBroker) {
        this.execBroker = execBroker;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public CommissionData getCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the CommissionData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the CommissionData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public Double getCommission() {
        return getSafeCommissionData().getCommission();
    }

    /**
     * Message field setter.
     * @param commission field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public void setCommission(Double commission) {
        getSafeCommissionData().setCommission(commission);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public CommType getCommType() {
        return getSafeCommissionData().getCommType();
    }

    /**
     * Message field setter.
     * @param commType field value
     */
    @FIXVersion(introduced="4.0", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public void setCommType(CommType commType) {
        getSafeCommissionData().setCommType(commType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocAvgPx)
    public Double getAllocAvgPx() {
        return allocAvgPx;
    }

    /**
     * Message field setter.
     * @param allocAvgPx field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocAvgPx)
    public void setAllocAvgPx(Double allocAvgPx) {
        this.allocAvgPx = allocAvgPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocNetMoney)
    public Double getAllocNetMoney() {
        return allocNetMoney;
    }

    /**
     * Message field setter.
     * @param allocNetMoney field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AllocNetMoney)
    public void setAllocNetMoney(Double allocNetMoney) {
        this.allocNetMoney = allocNetMoney;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrAmt)
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    /**
     * Message field setter.
     * @param settlCurrAmt field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrAmt)
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocSettlCurrAmt)
    public Double getAllocSettlCurrAmt() {
        return allocSettlCurrAmt;
    }

    /**
     * Message field setter.
     * @param allocSettlCurrAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocSettlCurrAmt)
    public void setAllocSettlCurrAmt(Double allocSettlCurrAmt) {
        this.allocSettlCurrAmt = allocSettlCurrAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocSettlCurrency)
    public Currency getAllocSettlCurrency() {
        return allocSettlCurrency;
    }

    /**
     * Message field setter.
     * @param allocSettlCurrency field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocSettlCurrency)
    public void setAllocSettlCurrency(Currency allocSettlCurrency) {
        this.allocSettlCurrency = allocSettlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRate)
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    /**
     * Message field setter.
     * @param settlCurrFxRate field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRate)
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    /**
     * Message field setter.
     * @param settlCurrFxRateCalc field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlCurrFxRateCalc)
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param accruedInterestAmt field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccruedInterestAmt)
    public Double getAllocAccruedInterestAmt() {
        return allocAccruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param allocAccruedInterestAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccruedInterestAmt)
    public void setAllocAccruedInterestAmt(Double allocAccruedInterestAmt) {
        this.allocAccruedInterestAmt = allocAccruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocInterestAtMaturity)
    public Double getAllocInterestAtMaturity() {
        return allocInterestAtMaturity;
    }

    /**
     * Message field setter.
     * @param allocInterestAtMaturity field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocInterestAtMaturity)
    public void setAllocInterestAtMaturity(Double allocInterestAtMaturity) {
        this.allocInterestAtMaturity = allocInterestAtMaturity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlInstMode)
    public SettlInstMode getSettlInstMode() {
        return settlInstMode;
    }

    /**
     * Message field setter.
     * @param settlInstMode field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlInstMode)
    public void setSettlInstMode(SettlInstMode settlInstMode) {
        this.settlInstMode = settlInstMode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired="4.1")
    @TagNumRef(tagNum=TagNum.NoDlvyInst)
    public Integer getNoDlvyInst() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link DlvyInstGroup} components. It will also create an array
     * of {@link DlvyInstGroup} objects and set the <code>dlvyInstGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>dlvyInstGroups</code> array they will be discarded.<br/>
     * @param noDlvyInst number of DlvyInstGroup objects
     */
    @FIXVersion(introduced="4.0", retired="4.1")
    @TagNumRef(tagNum=TagNum.NoDlvyInst)
    public void setNoDlvyInst(Integer noDlvyInst) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link DlvyInstGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.0", retired="4.1")
    public DlvyInstGroup[] getDlvyInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link DlvyInstGroup} object to the existing array of <code>dlvyInstGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noDlvyInst</code> field to the proper value.<br/>
     * Note: If the <code>setNoDlvyInst</code> method has been called there will already be a number of objects in the
     * <code>dlvyInstGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.0", retired="4.1")
    public DlvyInstGroup addDlvyInstGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link DlvyInstGroup} object from the existing array of <code>dlvyInstGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDlvyInst</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.0", retired="4.1")
    public DlvyInstGroup deleteDlvyInstGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link DlvyInstGroup} objects from the <code>DlvyInstGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDlvyInst</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.0", retired="4.1")
    public int clearDlvyInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NoMiscFees)
    public Integer getNoMiscFees() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MiscFeeGroup} groups. It will also create an array
     * of {@link MiscFeeGroup} objects and set the <code>miscFeeGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>miscFeeGroups</code> array they will be discarded.<br/>
     * @param noMiscFees field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NoMiscFees)
    public void setNoMiscFees(Integer noMiscFees) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MiscFeeGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.0")
    public MiscFeeGroup[] getMiscFeeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MiscFeeGroup} object to the existing array of <code>miscFeeGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMiscFees</code> field to the proper value.<br/>
     * Note: If the <code>setNoMiscFees</code> method has been called there will already be a number of objects in the
     * <code>MiscFeeGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.0")
    public MiscFeeGroup addMiscFeeGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MiscFeeGroup} object from the existing array of <code>miscFeeGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMiscFees</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.0")
    public MiscFeeGroup deleteMiscFeeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MiscFeeGroup} objects from the <code>miscFeeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMiscFees</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.0")
    public int clearMiscFeeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoClearingInstructions)
    public Integer getNoClearingInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link ClrInstGroup} groups. It will also create an array
     * of {@link ClrInstGroup} objects and set the <code>clrInstGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>clrInstGroups</code> array they will be discarded.<br/>
     * @param noClearingInstructions field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoClearingInstructions)
    public void setNoClearingInstructions(Integer noClearingInstructions) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link ClrInstGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public ClrInstGroup[] getClrInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ClrInstGroup} object to the existing array of <code>clrInstGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noClearingInstructions</code> field to the proper value.<br/>
     * Note: If the <code>setNoClearingInstructions</code> method has been called there will already be a number of objects in the
     * <code>clrInstGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public ClrInstGroup addClrInstGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link ClrInstGroup} object from the existing array of <code>clrInstGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noClearingInstructions</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public ClrInstGroup deleteClrInstGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link ClrInstGroup} objects from the <code>clrInstGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noClearingInstructions</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.0")
    public int clearClrInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    /**
     * Message field setter.
     * @param clearingFeeIndicator field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingFeeIndicator)
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocSettlInstType)
    public AllocSettlInstType getAllocSettlInstType() {
        return allocSettlInstType;
    }

    /**
     * Message field setter.
     * @param allocSettlInstType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocSettlInstType)
    public void setAllocSettlInstType(AllocSettlInstType allocSettlInstType) {
        this.allocSettlInstType = allocSettlInstType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public SettlInstructionsData getSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SettlInstructionsData component class to the proper implementation.
     */
    @FIXVersion(introduced = "4.4")
    public void setSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SettlInstructionsData component to null.
     */
    @FIXVersion(introduced = "4.4")
    public void clearSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.AllocAccount.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (allocAccount == null || allocAccount.trim().isEmpty()) {
            errorMsg.append(" [AllocAccount]");
            hasMissingTag = true;
        }
        if (allocQty == null) {
            errorMsg.append(" [AllocQty]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }
    
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            if (allocAcctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AllocAcctIDSource, allocAcctIDSource.getValue());
            }
            if (matchStatus != null) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocPrice, allocPrice);
            TagEncoder.encode(bao, TagNum.AllocQty, allocQty);
            TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            if (processCode != null) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecondaryIndividualAllocID, secondaryIndividualAllocID);
            if (allocMethod != null) {
                TagEncoder.encode(bao, TagNum.AllocMethod, allocMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocCustomerCapacity, allocCustomerCapacity);
            if (allocPositionEffect != null) {
                TagEncoder.encode(bao, TagNum.AllocPositionEffect, allocPositionEffect.getValue());
            }
            if (individualAllocType != null) {
                TagEncoder.encode(bao, TagNum.IndividualAllocType, individualAllocType.getValue());
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.BrokerOfCredit, brokerOfCredit);
            TagEncoder.encode(bao, TagNum.NotifyBrokerOfCredit, notifyBrokerOfCredit);
            if (allocHandlInst != null) {
                TagEncoder.encode(bao, TagNum.AllocHandlInst, allocHandlInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocText, allocText);
            if (encodedAllocTextLen != null && encodedAllocTextLen.intValue() > 0) {
                if (encodedAllocText != null && encodedAllocText.length > 0) {
                    encodedAllocTextLen = new Integer(encodedAllocText.length);
                    TagEncoder.encode(bao, TagNum.EncodedAllocTextLen, encodedAllocTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedAllocText);
                }
            }
            TagEncoder.encode(bao, TagNum.ClientID, clientID);
            TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            if (commissionData != null) {
                bao.write(commissionData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Commission, commission);
            if (commType != null) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocAvgPx, allocAvgPx);
            TagEncoder.encode(bao, TagNum.AllocNetMoney, allocNetMoney);
            TagEncoder.encode(bao, TagNum.SettlCurrAmt, settlCurrAmt);
            TagEncoder.encode(bao, TagNum.AllocSettlCurrAmt, allocSettlCurrAmt);
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (allocSettlCurrency != null) {
                TagEncoder.encode(bao, TagNum.AllocSettlCurrency, allocSettlCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.SettlCurrFxRate, settlCurrFxRate);
            if (settlCurrFxRateCalc != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            TagEncoder.encode(bao, TagNum.AllocAccruedInterestAmt, allocAccruedInterestAmt);
            TagEncoder.encode(bao, TagNum.AllocInterestAtMaturity, allocInterestAtMaturity);
            if (settlInstMode != null) {
                TagEncoder.encode(bao, TagNum.SettlInstMode, settlInstMode.getValue());
            }
            if (noDlvyInst != null) {
                TagEncoder.encode(bao, TagNum.NoDlvyInst, noDlvyInst);
                if (dlvyInstGroups != null && dlvyInstGroups.length == noDlvyInst.intValue()) {
                    for (int i = 0; i < noMiscFees.intValue(); i++) {
                        if (dlvyInstGroups[i] != null) {
                            bao.write(dlvyInstGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "DlvyInstGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoDlvyInst.getValue(), error);
                }
            }
            if (noMiscFees != null) {
                TagEncoder.encode(bao, TagNum.NoMiscFees, noMiscFees);
                if (miscFeeGroups != null && miscFeeGroups.length == noMiscFees.intValue()) {
                    for (int i = 0; i < noMiscFees.intValue(); i++) {
                        if (miscFeeGroups[i] != null) {
                            bao.write(miscFeeGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MiscFeeGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMiscFees.getValue(), error);
                }
            }
            if (noClearingInstructions != null) {
                TagEncoder.encode(bao, TagNum.NoClearingInstructions, noClearingInstructions);
                if (clrInstGroups != null && clrInstGroups.length == noClearingInstructions.intValue()) {
                    for (int i = 0; i < noClearingInstructions.intValue(); i++) {
                        if (clrInstGroups[i] != null) {
                            bao.write(clrInstGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "ClrInstGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoClearingInstructions.getValue(), error);
                }
            }
            if (clearingFeeIndicator != null) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (allocSettlInstType != null) {
                TagEncoder.encode(bao, TagNum.AllocSettlInstType, allocSettlInstType.getValue());
            }
            if (settlInstructionsData != null) {
                bao.write(settlInstructionsData.encode(MsgSecureType.ALL_FIELDS));
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }
    
    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case AllocAccount:
                allocAccount = new String(tag.value, sessionCharset);
                break;

            case AllocAcctIDSource:
                allocAcctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MatchStatus:
                matchStatus = MatchStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case AllocPrice:
                allocPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case AllocQty:
                allocQty = new Double(new String(tag.value, sessionCharset));
                break;

            case IndividualAllocID:
                individualAllocID = new String(tag.value, sessionCharset);
                break;

            case ProcessCode:
                processCode = ProcessCode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SecondaryIndividualAllocID:
                secondaryIndividualAllocID = new String(tag.value, sessionCharset);
                break;

            case AllocMethod:
                allocMethod = AllocMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocCustomerCapacity:
                allocCustomerCapacity = new String(tag.value, sessionCharset);
                break;

            case AllocPositionEffect:
                allocPositionEffect = AllocPositionEffect.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case IndividualAllocType:
                individualAllocType = IndividualAllocType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;


            case BrokerOfCredit:
                brokerOfCredit = new String(tag.value, sessionCharset);
                break;

            case NotifyBrokerOfCredit:
                notifyBrokerOfCredit = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case AllocHandlInst:
                allocHandlInst = AllocHandlInst.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AllocText:
                allocText = new String(tag.value, sessionCharset);
                break;

            case EncodedAllocTextLen:
                encodedAllocTextLen = Integer.valueOf(new String(tag.value, sessionCharset));
                break;

            case ClientID:
                clientID = new String(tag.value, sessionCharset);
                break;

            case ExecBroker:
                execBroker = new String(tag.value, sessionCharset);
                break;

            case Commission:
                commission = new Double(new String(tag.value, sessionCharset));
                break;

            case CommType:
                commType = CommType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case AllocAvgPx:
                allocAvgPx = new Double(new String(tag.value, sessionCharset));
                break;

            case AllocNetMoney:
                allocNetMoney = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrAmt:
                settlCurrAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case AllocSettlCurrAmt:
                allocSettlCurrAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case AllocSettlCurrency:
                allocSettlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case SettlCurrFxRate:
                settlCurrFxRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlCurrFxRateCalc:
                settlCurrFxRateCalc = SettlCurrFxRateCalc.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case AccruedInterestAmt:
                accruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case AllocAccruedInterestAmt:
                allocAccruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case AllocInterestAtMaturity:
                allocInterestAtMaturity = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlInstMode:
                settlInstMode = SettlInstMode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case NoDlvyInst:
                noDlvyInst = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoMiscFees:
                noMiscFees = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoClearingInstructions:
                noClearingInstructions = new Integer(new String(tag.value, sessionCharset));
                break;

            case ClearingFeeIndicator:
                clearingFeeIndicator = ClearingFeeIndicator.valueFor(new String(tag.value, sessionCharset));
                break;

            case AllocSettlInstType:
                allocSettlInstType = AllocSettlInstType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [AllocGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedAllocTextLen.getValue()) {
            try {
                encodedAllocTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedAllocTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedAllocTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedAllocTextLen.intValue());
            encodedAllocText = dataTag.value;
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    /// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private CommissionData getSafeCommissionData() {
        if (getCommissionData() == null) {
            setCommissionData();
        }

        return getCommissionData();
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{AllocGroup=");
        printTagValue(b, TagNum.AllocAccount, allocAccount);
        printTagValue(b, TagNum.AllocAcctIDSource, allocAcctIDSource);
        printTagValue(b, TagNum.MatchStatus, matchStatus);
        printTagValue(b, TagNum.AllocPrice, allocPrice);
        printTagValue(b, TagNum.AllocQty, allocQty);
        printTagValue(b, TagNum.IndividualAllocID, individualAllocID);
        printTagValue(b, TagNum.ProcessCode, processCode);
        printTagValue(b, TagNum.SecondaryIndividualAllocID, secondaryIndividualAllocID);
        printTagValue(b, TagNum.AllocMethod, allocMethod);
        printTagValue(b, TagNum.AllocCustomerCapacity, allocCustomerCapacity);
        printTagValue(b, TagNum.AllocPositionEffect, allocPositionEffect);
        printTagValue(b, nestedParties);
        printTagValue(b, TagNum.BrokerOfCredit, brokerOfCredit);
        printTagValue(b, TagNum.NotifyBrokerOfCredit, notifyBrokerOfCredit);
        printTagValue(b, TagNum.AllocHandlInst, allocHandlInst);
        printTagValue(b, TagNum.EncodedAllocTextLen, encodedAllocTextLen);
        printTagValue(b, TagNum.EncodedAllocText, encodedAllocText);
        printTagValue(b, TagNum.ClientID, clientID);
        printTagValue(b, TagNum.ExecBroker, execBroker);
        printTagValue(b, commissionData);
        printTagValue(b, TagNum.Commission, commission);
        printTagValue(b, TagNum.CommType, commType);
        printTagValue(b, TagNum.AllocAvgPx, allocAvgPx);
        printTagValue(b, TagNum.AllocNetMoney, allocNetMoney);
        printTagValue(b, TagNum.SettlCurrAmt, settlCurrAmt);
        printTagValue(b, TagNum.AllocSettlCurrAmt, allocSettlCurrAmt);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printTagValue(b, TagNum.AllocSettlCurrency, allocSettlCurrency);
        printTagValue(b, TagNum.SettlCurrFxRate, settlCurrFxRate);
        printTagValue(b, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc);
        printTagValue(b, TagNum.AccruedInterestAmt, accruedInterestAmt);
        printTagValue(b, TagNum.AllocAccruedInterestAmt, allocAccruedInterestAmt);
        printTagValue(b, TagNum.AllocInterestAtMaturity, allocInterestAtMaturity);
        printTagValue(b, TagNum.SettlInstMode, settlInstMode);
        printTagValue(b, TagNum.NoDlvyInst, noDlvyInst);
        printTagValue(b, dlvyInstGroups);
        printTagValue(b, TagNum.NoMiscFees, noMiscFees);
        printTagValue(b, miscFeeGroups);
        printTagValue(b, TagNum.NoClearingInstructions, noClearingInstructions);
        printTagValue(b, clrInstGroups);
        printTagValue(b, TagNum.ClearingFeeIndicator, clearingFeeIndicator);
        printTagValue(b, TagNum.AllocSettlInstType, allocSettlInstType);
        printTagValue(b, settlInstructionsData);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
