/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocGroup40.java
 *
 * $Id: AllocGroup40.java,v 1.3 2011-04-14 23:45:01 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v40;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AllocGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.List;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.DlvyInstGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.0 implementation of AllocGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class AllocGroup40 extends AllocGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V40 = new HashSet<Integer>(Arrays.asList(new Integer[] {
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
        TagNum.NotifyBrokerOfCredit.getValue(),
        TagNum.AllocHandlInst.getValue(),
        TagNum.AllocText.getValue(),
        TagNum.ClientID.getValue(),
        TagNum.ExecBroker.getValue(),
        TagNum.Commission.getValue(),
        TagNum.CommType.getValue(),
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
        TagNum.NoDlvyInst.getValue(),
        TagNum.NoMiscFees.getValue(),
        TagNum.ClearingFeeIndicator.getValue(),
        TagNum.AllocSettlInstType.getValue(),
    }));

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> START_DATA_TAGS_V40 = null;

    protected static final Set<Integer> DLVY_INST_GROUP_TAGS = new DlvyInstGroup40().getFragmentAllTags();
    protected static final Set<Integer> MISC_FEE_GROUP_TAGS = new MiscFeeGroup40().getFragmentAllTags();


    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V40);
        ALL_TAGS.addAll(DLVY_INST_GROUP_TAGS);
        ALL_TAGS.addAll(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MISC_FEE_GROUP_TAGS);
        START_COMP_TAGS.addAll(DLVY_INST_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AllocGroup40() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public AllocGroup40(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V40;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

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
    public Integer getNoDlvyInst() {
        return noDlvyInst;
    }

    @Override
    public void setNoDlvyInst(Integer noDlvyInst) {
        this.noDlvyInst = noDlvyInst;
        if (noDlvyInst != null) {
            dlvyInstGroups = new DlvyInstGroup[noDlvyInst.intValue()];
            for (int i = 0; i < dlvyInstGroups.length; i++) {
                dlvyInstGroups[i] = new DlvyInstGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public DlvyInstGroup[] getDlvyInstGroups() {
        return dlvyInstGroups;
    }

    public void setDlvyInstGroups(DlvyInstGroup[] dlvyInstGroups) {
        this.dlvyInstGroups = dlvyInstGroups;
        if (dlvyInstGroups != null) {
            noDlvyInst = new Integer(dlvyInstGroups.length);
        }
    }

    @Override
    public DlvyInstGroup addDlvyInstGroup() {
        DlvyInstGroup group = new DlvyInstGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<DlvyInstGroup> groups = new ArrayList<DlvyInstGroup>();
        if (dlvyInstGroups != null && dlvyInstGroups.length > 0) {
            groups = new ArrayList<DlvyInstGroup>(Arrays.asList(dlvyInstGroups));
        }
        groups.add(group);
        dlvyInstGroups = groups.toArray(new DlvyInstGroup[groups.size()]);
        noDlvyInst = new Integer(dlvyInstGroups.length);

        return group;
    }

    @Override
    public DlvyInstGroup deleteDlvyInstGroup(int index) {
        DlvyInstGroup result = null;
        if (dlvyInstGroups != null && dlvyInstGroups.length > 0 && dlvyInstGroups.length > index) {
            List<DlvyInstGroup> groups = new ArrayList<DlvyInstGroup>(Arrays.asList(dlvyInstGroups));
            result = groups.remove(index);
            dlvyInstGroups = groups.toArray(new DlvyInstGroup[groups.size()]);
            if (dlvyInstGroups.length > 0) {
                noDlvyInst = new Integer(dlvyInstGroups.length);
            } else {
                dlvyInstGroups = null;
                noDlvyInst = null;
            }
        }

        return result;
    }

    @Override
    public int clearDlvyInstGroups() {
        int result = 0;
        if (dlvyInstGroups != null && dlvyInstGroups.length > 0) {
            result = dlvyInstGroups.length;
            dlvyInstGroups = null;
            noDlvyInst = null;
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
                miscFeeGroups[i] = new MiscFeeGroup40(context);
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
        MiscFeeGroup group = new MiscFeeGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            if (MsgUtil.isTagInList(TagNum.ClientID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientID, clientID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecBroker, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            }
            if (MsgUtil.isTagInList(TagNum.Commission, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Commission, commission);
            }
            if (commType != null && MsgUtil.isTagInList(TagNum.CommType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
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
            if (noDlvyInst != null && MsgUtil.isTagInList(TagNum.NoDlvyInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoDlvyInst, noDlvyInst);
                if (dlvyInstGroups != null && dlvyInstGroups.length == noDlvyInst.intValue()) {
                    for (int i = 0; i < noMiscFees.intValue(); i++) {
                        if (dlvyInstGroups[i] != null) {
                            bao.write(dlvyInstGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "DlvyInstGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoDlvyInst.getValue(), error);
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
            if (clearingFeeIndicator != null && MsgUtil.isTagInList(TagNum.ClearingFeeIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClearingFeeIndicator, clearingFeeIndicator.getValue());
            }
            if (allocSettlInstType != null && MsgUtil.isTagInList(TagNum.AllocSettlInstType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocSettlInstType, allocSettlInstType.getValue());
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
        if (DLVY_INST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noDlvyInst != null && noDlvyInst.intValue() > 0) {
                message.reset();
                dlvyInstGroups = new DlvyInstGroup[noDlvyInst.intValue()];
                for (int i = 0; i < noDlvyInst.intValue(); i++) {
                    DlvyInstGroup group = new DlvyInstGroup40(context);
                    group.decode(message);
                    dlvyInstGroups[i] = group;
                }
            }
        }
        if (MISC_FEE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMiscFees != null && noMiscFees.intValue() > 0) {
                message.reset();
                miscFeeGroups = new MiscFeeGroup[noMiscFees.intValue()];
                for (int i = 0; i < noMiscFees.intValue(); i++) {
                    MiscFeeGroup component = new MiscFeeGroup40(context);
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
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_V40;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AllocGroup] group version [4.0].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
