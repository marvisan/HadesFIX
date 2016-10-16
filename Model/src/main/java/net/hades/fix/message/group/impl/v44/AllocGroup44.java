/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocGroup44.java
 *
 * $Id: AllocGroup44.java,v 1.3 2011-04-14 23:44:43 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.comp.impl.v44.CommissionData44;
import net.hades.fix.message.comp.impl.v44.NestedParties44;
import net.hades.fix.message.comp.impl.v44.SettlInstructionsData44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.group.ClrInstGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocHandlInst;
import net.hades.fix.message.type.AllocSettlInstType;
import net.hades.fix.message.type.ClearingFeeIndicator;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of ExecAllocGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="Alloc")
@XmlType(propOrder = {"nestedPartyIDGroups", "commissionData", "miscFeeGroups", "clrInstGroups", "settlInstructionsData"})
@XmlAccessorType(XmlAccessType.NONE)
public class AllocGroup44 extends AllocGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties44().getFragmentAllTags();
    protected static final Set<Integer> COMMISSION_DATA_COMP_TAGS = new CommissionData44().getFragmentAllTags();
    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup44().getFragmentAllTags();
    protected static final Set<Integer> CLR_INST_GROUP_TAGS = new ClrInstGroup44().getFragmentAllTags();
    protected static final Set<Integer> SETTL_INST_COMP_TAGS = new SettlInstructionsData44().getFragmentAllTags();

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        ALL_TAGS.addAll(CLR_INST_GROUP_TAGS);
        ALL_TAGS.addAll(SETTL_INST_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(COMMISSION_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(CLR_INST_GROUP_TAGS);
        START_COMP_TAGS.addAll(SETTL_INST_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AllocGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public AllocGroup44(FragmentContext context) {
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

    @XmlAttribute(name = "Acct")
    @Override
    public String getAllocAccount() {
        return allocAccount;
    }

    @Override
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    @XmlAttribute(name = "ActIDSrc")
    @Override
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    @Override
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
    }

    @XmlAttribute(name = "MtchStat")
    @Override
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    @Override
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getAllocPrice() {
        return allocPrice;
    }

    @Override
    public void setAllocPrice(Double allocPrice) {
        this.allocPrice = allocPrice;
    }

    @XmlAttribute(name = "Qty")
    @Override
    public Double getAllocQty() {
        return allocQty;
    }

    @Override
    public void setAllocQty(Double allocQty) {
        this.allocQty = allocQty;
    }

    @XmlAttribute(name = "IndAllocID")
    @Override
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    @Override
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
    }

    @XmlAttribute(name = "ProcCode")
    @Override
    public ProcessCode getProcessCode() {
        return processCode;
    }

    @Override
    public void setProcessCode(ProcessCode processCode) {
        this.processCode = processCode;
    }

    @Override
    public NestedParties getNestedParties() {
        return nestedParties;
    }

    @Override
    public void setNestedParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.nestedParties = new NestedParties44(context);
    }

    @Override
    public void clearNestedParties() {
        this.nestedParties = null;
    }

    @XmlElementRef
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        return nestedParties == null ? null : nestedParties.getNestedPartyIDGroups();
    }

    public void setNestedPartyIDGroups(NestedPartyGroup[] nestedPartyIDGroups) {
        if (nestedPartyIDGroups != null) {
            if (nestedParties == null) {
                setNestedParties();
            }
            ((NestedParties44) nestedParties).setNestedPartyIDGroups(nestedPartyIDGroups);
        }
    }

    @XmlAttribute(name = "NotifyBrkrOfCredit")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getNotifyBrokerOfCredit() {
        return notifyBrokerOfCredit;
    }

    @Override
    public void setNotifyBrokerOfCredit(Boolean notifyBrokerOfCredit) {
        this.notifyBrokerOfCredit = notifyBrokerOfCredit;
    }

    @XmlAttribute(name = "HandlInst")
    @Override
    public AllocHandlInst getAllocHandlInst() {
        return allocHandlInst;
    }

    @Override
    public void setAllocHandlInst(AllocHandlInst allocHandlInst) {
        this.allocHandlInst = allocHandlInst;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getAllocText() {
        return allocText;
    }

    @Override
    public void setAllocText(String allocText) {
        this.allocText = allocText;
    }

    @XmlAttribute(name = "EncAllocTextLen")
    @Override
    public Integer getEncodedAllocTextLen() {
        return encodedAllocTextLen;
    }

    @Override
    public void setEncodedAllocTextLen(Integer encodedAllocTextLen) {
        this.encodedAllocTextLen = encodedAllocTextLen;
    }

    @XmlAttribute(name = "EncAllocText")
    @Override
    public byte[] getEncodedAllocText() {
        return encodedAllocText;
    }

    @Override
    public void setEncodedAllocText(byte[] encodedAllocText) {
        this.encodedAllocText = encodedAllocText;
        if (encodedAllocTextLen == null) {
            encodedAllocTextLen = new Integer(encodedAllocText.length);
        }
    }

    @XmlElementRef
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
        this.commissionData = new CommissionData44(context);
    }

    @Override
    public void clearCommissionData() {
        commissionData = null;
    }

    @XmlAttribute(name = "AvgPx")
    @Override
    public Double getAllocAvgPx() {
        return allocAvgPx;
    }

    @Override
    public void setAllocAvgPx(Double allocAvgPx) {
        this.allocAvgPx = allocAvgPx;
    }

    @XmlAttribute(name = "NetMny")
    @Override
    public Double getAllocNetMoney() {
        return allocNetMoney;
    }

    @Override
    public void setAllocNetMoney(Double allocNetMoney) {
        this.allocNetMoney = allocNetMoney;
    }

    @XmlAttribute(name = "SettlCurrAmt")
    @Override
    public Double getSettlCurrAmt() {
        return settlCurrAmt;
    }

    @Override
    public void setSettlCurrAmt(Double settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
    }

    @XmlAttribute(name = "SettlCcyAmt")
    @Override
    public Double getAllocSettlCurrAmt() {
        return allocSettlCurrAmt;
    }

    @Override
    public void setAllocSettlCurrAmt(Double allocSettlCurrAmt) {
        this.allocSettlCurrAmt = allocSettlCurrAmt;
    }

    @XmlAttribute(name = "SettlCcy")
    @Override
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    @Override
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    @XmlAttribute(name = "AllocSettlCcy")
    @Override
    public Currency getAllocSettlCurrency() {
        return allocSettlCurrency;
    }

    @Override
    public void setAllocSettlCurrency(Currency allocSettlCurrency) {
        this.allocSettlCurrency = allocSettlCurrency;
    }

    @XmlAttribute(name = "SettlCurrFxRt")
    @Override
    public Double getSettlCurrFxRate() {
        return settlCurrFxRate;
    }

    @Override
    public void setSettlCurrFxRate(Double settlCurrFxRate) {
        this.settlCurrFxRate = settlCurrFxRate;
    }

    @XmlAttribute(name = "SettlCurrFxRtCalc")
    @Override
    public SettlCurrFxRateCalc getSettlCurrFxRateCalc() {
        return settlCurrFxRateCalc;
    }

    @Override
    public void setSettlCurrFxRateCalc(SettlCurrFxRateCalc settlCurrFxRateCalc) {
        this.settlCurrFxRateCalc = settlCurrFxRateCalc;
    }

    @XmlAttribute(name = "AcrdIntAmt")
    @Override
    public Double getAllocAccruedInterestAmt() {
        return allocAccruedInterestAmt;
    }

    @Override
    public void setAllocAccruedInterestAmt(Double allocAccruedInterestAmt) {
        this.allocAccruedInterestAmt = allocAccruedInterestAmt;
    }

    @XmlAttribute(name = "IntAtMat")
    @Override
    public Double getAllocInterestAtMaturity() {
        return allocInterestAtMaturity;
    }

    @Override
    public void setAllocInterestAtMaturity(Double allocInterestAtMaturity) {
        this.allocInterestAtMaturity = allocInterestAtMaturity;
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
                miscFeeGroups[i] = new MiscFeeGroup44(context);
            }
        }
    }

    @XmlElementRef
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
        MiscFeeGroup group = new MiscFeeGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
                clrInstGroups[i] = new ClrInstGroup44(context);
            }
        }
    }

    @XmlElementRef
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
        ClrInstGroup group = new ClrInstGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @XmlAttribute(name = "ClrFeeInd")
    @Override
    public ClearingFeeIndicator getClearingFeeIndicator() {
        return clearingFeeIndicator;
    }

    @Override
    public void setClearingFeeIndicator(ClearingFeeIndicator clearingFeeIndicator) {
        this.clearingFeeIndicator = clearingFeeIndicator;
    }

    @XmlAttribute(name = "SettlInstTyp")
    @Override
    public AllocSettlInstType getAllocSettlInstType() {
        return allocSettlInstType;
    }

    @Override
    public void setAllocSettlInstType(AllocSettlInstType allocSettlInstType) {
        this.allocSettlInstType = allocSettlInstType;
    }

    @XmlElementRef
    @Override
    public SettlInstructionsData getSettlInstructionsData() {
        return settlInstructionsData;
    }

    @Override
    public void setSettlInstructionsData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.settlInstructionsData = new SettlInstructionsData44(context);
    }

    public void setSettlInstructionsData(SettlInstructionsData settlInstructionsData) {
        this.settlInstructionsData = settlInstructionsData;
    }

    @Override
    public void clearSettlInstructionsData() {
        this.settlInstructionsData = null;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.AllocAccount, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            }
            if (allocAcctIDSource != null && MsgUtil.isTagInList(TagNum.AllocAcctIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocAcctIDSource, allocAcctIDSource.getValue());
            }
            if (matchStatus != null && MsgUtil.isTagInList(TagNum.MatchStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AllocPrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocPrice, allocPrice);
            }
            if (MsgUtil.isTagInList(TagNum.AllocQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocQty, allocQty);
            }
            if (MsgUtil.isTagInList(TagNum.IndividualAllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            }
            if (processCode != null && MsgUtil.isTagInList(TagNum.ProcessCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.BrokerOfCredit, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BrokerOfCredit, brokerOfCredit);
            }
            if (MsgUtil.isTagInList(TagNum.NotifyBrokerOfCredit, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NotifyBrokerOfCredit, notifyBrokerOfCredit);
            }
            if (allocHandlInst != null && MsgUtil.isTagInList(TagNum.AllocHandlInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocHandlInst, allocHandlInst.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AllocText, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocText, allocText);
            }
            if (encodedAllocTextLen != null && encodedAllocTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedAllocTextLen, SECURED_TAGS, secured)) {
                if (encodedAllocText != null && encodedAllocText.length > 0) {
                    encodedAllocTextLen = new Integer(encodedAllocText.length);
                    TagEncoder.encode(bao, TagNum.EncodedAllocTextLen, encodedAllocTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedAllocText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.ClientID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientID, clientID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecBroker, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            }
            if (commissionData != null) {
                bao.write(commissionData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.AllocAvgPx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocAvgPx, allocAvgPx);
            }
            if (MsgUtil.isTagInList(TagNum.AllocNetMoney, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocNetMoney, allocNetMoney);
            }
            if (MsgUtil.isTagInList(TagNum.SettlCurrAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrAmt, settlCurrAmt);
            }
            if (MsgUtil.isTagInList(TagNum.AllocSettlCurrAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocSettlCurrAmt, allocSettlCurrAmt);
            }
            if (settlCurrency != null && MsgUtil.isTagInList(TagNum.SettlCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            if (allocSettlCurrency != null && MsgUtil.isTagInList(TagNum.AllocSettlCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocSettlCurrency, allocSettlCurrency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlCurrFxRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRate, settlCurrFxRate);
            }
            if (settlCurrFxRateCalc != null && MsgUtil.isTagInList(TagNum.SettlCurrFxRateCalc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlCurrFxRateCalc, settlCurrFxRateCalc.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AccruedInterestAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            }
            if (MsgUtil.isTagInList(TagNum.AllocAccruedInterestAmt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocAccruedInterestAmt, allocAccruedInterestAmt);
            }
            if (MsgUtil.isTagInList(TagNum.AllocInterestAtMaturity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocInterestAtMaturity, allocInterestAtMaturity);
            }
            if (settlInstMode != null && MsgUtil.isTagInList(TagNum.SettlInstMode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstMode, settlInstMode.getValue());
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
            if (allocSettlInstType != null && MsgUtil.isTagInList(TagNum.AllocSettlInstType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocSettlInstType, allocSettlInstType.getValue());
            }
            if (settlInstructionsData != null) {
                bao.write(settlInstructionsData.encode(getMsgSecureTypeForFlag(secured)));
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
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties44(context);
            }
            nestedParties.decode(tag, message);
        }
        if (COMMISSION_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (commissionData == null) {
                commissionData = new CommissionData44(context);
            }
            commissionData.decode(tag, message);
        }
        if (MISC_FEE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMiscFees != null && noMiscFees.intValue() > 0) {
                message.reset();
                miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
                for (int i = 0; i < noMiscFees.intValue(); i++) {
                    MiscFeeGroup component = new MiscFeeGroup44(context);
                    component.decode(message);
                    miscFeeGroups[i] = component;
                }
            }
        }
        if (CLR_INST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noClearingInstructions != null && noClearingInstructions.intValue() > 0) {
                message.reset();
                clrInstGroups = new ClrInstGroup[noClearingInstructions.intValue()];
                for (int i = 0; i < noClearingInstructions.intValue(); i++) {
                    ClrInstGroup component = new ClrInstGroup44(context);
                    component.decode(message);
                    clrInstGroups[i] = component;
                }
            }
        }
        if (SETTL_INST_COMP_TAGS.contains(tag.tagNum)) {
            if (settlInstructionsData == null) {
                settlInstructionsData = new SettlInstructionsData44(context);
            }
            settlInstructionsData.decode(tag, message);
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
        return "This tag is not supported in [AllocGroup] group version [4.4].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
