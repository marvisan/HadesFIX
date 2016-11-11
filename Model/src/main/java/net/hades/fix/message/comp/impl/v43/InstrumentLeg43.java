/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg43.java
 *
 * $Id: InstrumentLeg43.java,v 1.2 2011-04-14 23:45:01 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v43.LegSecurityAltIDGroup43;
import net.hades.fix.message.struct.Tag;
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

import javax.xml.bind.annotation.XmlElementRef;

import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegSecurityAltIDGroup;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FlowScheduleType;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;
import net.hades.fix.message.util.TagEncoder;

/**
 * InstrumentLeg FIX version 4.3 implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 23/11/2008, 10:21:01 AM
 */
public class InstrumentLeg43 extends InstrumentLeg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegSymbol.getValue(),
        TagNum.LegSymbolSfx.getValue(),
        TagNum.LegSecurityID.getValue(),
        TagNum.LegSecurityIDSource.getValue(),
        TagNum.NoLegSecurityAltID.getValue(),
        TagNum.LegProduct.getValue(),
        TagNum.LegCFICode.getValue(),
        TagNum.LegSecurityType.getValue(),
        TagNum.LegMaturityMonthYear.getValue(),
        TagNum.LegMaturityDate.getValue(),
        TagNum.LegCouponPaymentDate.getValue(),
        TagNum.LegIssueDate.getValue(),
        TagNum.LegRepoCollateralSecurityType.getValue(),
        TagNum.LegRepurchaseTerm.getValue(),
        TagNum.LegRepurchaseRate.getValue(),
        TagNum.LegFactor.getValue(),
        TagNum.LegCreditRating.getValue(),
        TagNum.LegInstrRegistry.getValue(),
        TagNum.LegCountryOfIssue.getValue(),
        TagNum.LegStateOrProvinceOfIssue.getValue(),
        TagNum.LegLocaleOfIssue.getValue(),
        TagNum.LegRedemptionDate.getValue(),
        TagNum.LegStrikePrice.getValue(),
        TagNum.LegOptAttribute.getValue(),
        TagNum.LegContractMultiplier.getValue(),
        TagNum.LegCouponRate.getValue(),
        TagNum.LegSecurityExchange.getValue(),
        TagNum.LegIssuer.getValue(),
        TagNum.LegSecurityDesc.getValue(),
        TagNum.LegRatioQty.getValue(),
        TagNum.LegSide.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LEG_SEC_ALT_ID_GROUP_TAGS = new LegSecurityAltIDGroup43().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(LEG_SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LEG_SEC_ALT_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentLeg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public InstrumentLeg43(FragmentContext context) {
        super(context);
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
    public Integer getNoLegSecurityAltID() {
        return noLegSecurityAltID;
    }

    @Override
    public void setNoLegSecurityAltID(Integer noLegSecurityAltID) {
        this.noLegSecurityAltID = noLegSecurityAltID;
        if (noLegSecurityAltID != null) {
            legSecurityAltIDGroups = new LegSecurityAltIDGroup[noLegSecurityAltID.intValue()];
            for (int i = 0; i < legSecurityAltIDGroups.length; i++) {
                legSecurityAltIDGroups[i] = new LegSecurityAltIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public LegSecurityAltIDGroup[] getLegSecurityAltIDGroups() {
        return legSecurityAltIDGroups;
    }

    public void setLegSecurityAltIDGroups(LegSecurityAltIDGroup[] legSecurityAltIDGroups) {
        this.legSecurityAltIDGroups = legSecurityAltIDGroups;
        if (legSecurityAltIDGroups != null) {
            noLegSecurityAltID = legSecurityAltIDGroups.length;
        }
    }

    @Override
    public LegSecurityAltIDGroup addLegSecurityAltIDGroup() {

        LegSecurityAltIDGroup group = new LegSecurityAltIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<LegSecurityAltIDGroup> groups = new ArrayList<LegSecurityAltIDGroup>();
        if (legSecurityAltIDGroups != null && legSecurityAltIDGroups.length > 0) {
            groups = new ArrayList<LegSecurityAltIDGroup>(Arrays.asList(legSecurityAltIDGroups));
        }
        groups.add(group);
        legSecurityAltIDGroups = groups.toArray(new LegSecurityAltIDGroup[groups.size()]);
        noLegSecurityAltID = new Integer(legSecurityAltIDGroups.length);

        return group;
    }

    @Override
    public LegSecurityAltIDGroup deleteLegSecurityAltIDGroup(int index) {

        LegSecurityAltIDGroup result = null;

        if (legSecurityAltIDGroups != null && legSecurityAltIDGroups.length > 0 && legSecurityAltIDGroups.length > index) {
            List<LegSecurityAltIDGroup> groups = new ArrayList<LegSecurityAltIDGroup>(Arrays.asList(legSecurityAltIDGroups));
            result = groups.remove(index);
            legSecurityAltIDGroups = groups.toArray(new LegSecurityAltIDGroup[groups.size()]);
            if (legSecurityAltIDGroups.length > 0) {
                noLegSecurityAltID = new Integer(legSecurityAltIDGroups.length);
            } else {
                legSecurityAltIDGroups = null;
                noLegSecurityAltID = null;
            }
        }

        return result;
    }

    @Override
    public int clearLegSecurityAltIDGroups() {

        int result = 0;
        if (legSecurityAltIDGroups != null && legSecurityAltIDGroups.length > 0) {
            result = legSecurityAltIDGroups.length;
            legSecurityAltIDGroups = null;
            noLegSecurityAltID = null;
        }

        return result;
    }

    @Override
    public String getLegSecuritySubType() {
        return legSecuritySubType;
    }

    @Override
    public void setLegSecuritySubType(String legSecuritySubType) {
        this.legSecuritySubType = legSecuritySubType;
    }
    
    @Override
    public Date getLegMaturityTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    @Override
    public void setLegMaturityTime(Date legMaturityTime) {
        this.legMaturityTime = legMaturityTime;
    }
    
    @Override
    public Currency getLegStrikeCurrency() {
        return legStrikeCurrency;
    }

    @Override
    public void setLegStrikeCurrency(Currency legStrikeCurrency) {
        this.legStrikeCurrency = legStrikeCurrency;
    }

    @Override
    public Double getLegContractMultiplier() {
        return legContractMultiplier;
    }

    @Override
    public void setLegContractMultiplier(Double legContractMultiplier) {
        this.legContractMultiplier = legContractMultiplier;
    }

    @Override
    public ContractMultiplierUnit getLegContractMultiplierUnit() {
        return legContractMultiplierUnit;
    }

    @Override
    public void setLegContractMultiplierUnit(ContractMultiplierUnit legContractMultiplierUnit) {
        this.legContractMultiplierUnit = legContractMultiplierUnit;
    }

    @Override
    public FlowScheduleType getLegFlowScheduleType() {
        return legFlowScheduleType;
    }

    @Override
    public void setLegFlowScheduleType(FlowScheduleType legFlowScheduleType) {
        this.legFlowScheduleType = legFlowScheduleType;
    }

    @Override
    public UnitOfMeasure getLegUnitOfMeasure() {
        return legUnitOfMeasure;
    }

    @Override
    public void setLegUnitOfMeasure(UnitOfMeasure legUnitOfMeasure) {
        this.legUnitOfMeasure = legUnitOfMeasure;
    }

    @Override
    public Double getLegUnitOfMeasureQty() {
        return legUnitOfMeasureQty;
    }

    @Override
    public void setLegUnitOfMeasureQty(Double legUnitOfMeasureQty) {
        this.legUnitOfMeasureQty = legUnitOfMeasureQty;
    }

    @Override
    public PriceUnitOfMeasure getLegPriceUnitOfMeasure() {
        return legPriceUnitOfMeasure;
    }

    @Override
    public void setLegPriceUnitOfMeasure(PriceUnitOfMeasure legPriceUnitOfMeasure) {
        this.legPriceUnitOfMeasure = legPriceUnitOfMeasure;
    }

    @Override
    public Double getLegPriceUnitOfMeasureQty() {
        return legPriceUnitOfMeasureQty;
    }

    @Override
    public void setLegPriceUnitOfMeasureQty(Double legPriceUnitOfMeasureQty) {
        this.legPriceUnitOfMeasureQty = legPriceUnitOfMeasureQty;
    }

    @Override
    public TimeUnit getLegTimeUnit() {
        return legTimeUnit;
    }

    @Override
    public void setLegTimeUnit(TimeUnit legTimeUnit) {
        this.legTimeUnit = legTimeUnit;
    }

    @Override
    public ExerciseStyle getLegExerciseStyle() {
        return legExerciseStyle;
    }

    @Override
    public void setLegExerciseStyle(ExerciseStyle legExerciseStyle) {
        this.legExerciseStyle = legExerciseStyle;
    }
    
    @Override
    public Currency getLegCurrency() {
        return legCurrency;
    }

    @Override
    public void setLegCurrency(Currency legCurrency) {
        this.legCurrency = legCurrency;
    }

    @Override
    public String getLegPool() {
        return legPool;
    }

    @Override
    public void setLegPool(String legPool) {
        this.legPool = legPool;
    }

    @Override
    public Date getLegDatedDate() {
        return legDatedDate;
    }

    @Override
    public void setLegDatedDate(Date legDatedDate) {
        this.legDatedDate = legDatedDate;
    }

    @Override
    public String getLegContractSettlMonth() {
        return legContractSettlMonth;
    }

    @Override
    public void setLegContractSettlMonth(String legContractSettlMonth) {
        this.legContractSettlMonth = legContractSettlMonth;
    }

    @Override
    public Date getLegInterestAccrualDate() {
        return legInterestAccrualDate;
    }

    @Override
    public void setLegInterestAccrualDate(Date legInterestAccrualDate) {
        this.legInterestAccrualDate = legInterestAccrualDate;
    }

    @Override
    public PutOrCall getLegPutOrCall() {
        return legPutOrCall;
    }

    @Override
    public void setLegPutOrCall(PutOrCall legPutOrCall) {
        this.legPutOrCall = legPutOrCall;
    }

    @Override
    public Double getLegOptionRatio() {
        return legOptionRatio;
    }

    @Override
    public void setLegOptionRatio(Double legOptionRatio) {
        this.legOptionRatio = legOptionRatio;
    }

    @Override
    public Double getLegPrice() {
        return legPrice;
    }

    @Override
    public void setLegPrice(Double legPrice) {
        this.legPrice = legPrice;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.LegSymbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSymbol, legSymbol);
            }
            if (MsgUtil.isTagInList(TagNum.LegSymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSymbolSfx, legSymbolSfx, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.LegSecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityID, legSecurityID, sessionCharset);
            }
            if (legSecurityIDSource != null && !legSecurityIDSource.trim().isEmpty() && MsgUtil.isTagInList(TagNum.LegSecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityIDSource, legSecurityIDSource, sessionCharset);
            }
            if (noLegSecurityAltID != null && noLegSecurityAltID.intValue() > 0 && MsgUtil.isTagInList(TagNum.NoLegSecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoLegSecurityAltID, noLegSecurityAltID);
                if (legSecurityAltIDGroups != null && legSecurityAltIDGroups.length == noLegSecurityAltID.intValue()) {
                    for (int i = 0; i < noLegSecurityAltID.intValue(); i++) {
                        if (legSecurityAltIDGroups[i] != null) {
                            bao.write(legSecurityAltIDGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "LegSecurityAltIDGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegSecurityAltID.getValue(), error);
                }
            }
            if (legProduct != null && MsgUtil.isTagInList(TagNum.LegProduct, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegProduct, legProduct.getValue());
            }
            if (legCFICode != null && !legCFICode.trim().isEmpty() && MsgUtil.isTagInList(TagNum.LegCFICode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegCFICode, legCFICode, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.LegSecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityType, legSecurityType);
            }
            if (MsgUtil.isTagInList(TagNum.LegMaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegMaturityMonthYear, legMaturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.LegMaturityDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.LegMaturityDate, legMaturityDate);
            }
            if (MsgUtil.isTagInList(TagNum.LegCouponPaymentDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.LegCouponPaymentDate, legCouponPaymentDate);
            }
            if (MsgUtil.isTagInList(TagNum.LegIssueDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.LegIssueDate, legIssueDate);
            }
            if (MsgUtil.isTagInList(TagNum.LegRepoCollateralSecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegRepoCollateralSecurityType, legRepoCollateralSecurityType);
            }
            if (MsgUtil.isTagInList(TagNum.LegRepurchaseTerm, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegRepurchaseTerm, legRepurchaseTerm);
            }
            if (MsgUtil.isTagInList(TagNum.LegRepurchaseRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegRepurchaseRate, legRepurchaseRate);
            }
            if (MsgUtil.isTagInList(TagNum.LegFactor, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegFactor, legFactor);
            }
            if (MsgUtil.isTagInList(TagNum.LegCreditRating, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegCreditRating, legCreditRating);
            }
            if (MsgUtil.isTagInList(TagNum.LegInstrRegistry, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegInstrRegistry, legInstrRegistry);
            }
            if (MsgUtil.isTagInList(TagNum.LegCountryOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegCountryOfIssue, legCountryOfIssue);
            }
            if (MsgUtil.isTagInList(TagNum.LegStateOrProvinceOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegStateOrProvinceOfIssue, legStateOrProvinceOfIssue);
            }
            if (MsgUtil.isTagInList(TagNum.LegLocaleOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegLocaleOfIssue, legLocaleOfIssue);
            }
            if (MsgUtil.isTagInList(TagNum.LegRedemptionDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.LegRedemptionDate, legRedemptionDate);
            }
            if (MsgUtil.isTagInList(TagNum.LegStrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegStrikePrice, legStrikePrice);
            }
            if (legStrikeCurrency != null && MsgUtil.isTagInList(TagNum.LegStrikeCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegStrikeCurrency, legStrikeCurrency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.LegOptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegOptAttribute, legOptAttribute);
            }
            if (MsgUtil.isTagInList(TagNum.LegContractMultiplier, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegContractMultiplier, legContractMultiplier);
            }
            if (MsgUtil.isTagInList(TagNum.LegCouponRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegCouponRate, legCouponRate);
            }
            if (!legSecurityExchange.trim().isEmpty() && MsgUtil.isTagInList(TagNum.LegSecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityExchange, legSecurityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.LegIssuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegIssuer, legIssuer, sessionCharset);
            }
            if (encodedLegIssuerLen != null && encodedLegIssuerLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedLegIssuerLen, SECURED_TAGS, secured)) {
                if (encodedLegIssuer != null && encodedLegIssuer.length > 0) {
                    encodedLegIssuerLen = new Integer(encodedLegIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedLegIssuerLen, encodedLegIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedLegIssuer, encodedLegIssuer);
                }
            }
            if (MsgUtil.isTagInList(TagNum.LegSecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecurityDesc, legSecurityDesc, sessionCharset);
            }
            if (encodedLegSecurityDescLen != null && encodedLegSecurityDescLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedLegSecurityDescLen, SECURED_TAGS, secured)) {
                if (encodedLegSecurityDesc != null && encodedLegSecurityDesc.length > 0) {
                    encodedLegSecurityDescLen = new Integer(encodedLegSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedLegSecurityDescLen, encodedLegSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedLegSecurityDesc, encodedLegSecurityDesc);
                }
            }
            if (MsgUtil.isTagInList(TagNum.LegRatioQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegRatioQty, legRatioQty);
            }
            if (legSide != null && MsgUtil.isTagInList(TagNum.LegSide, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSide, legSide.getValue());
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
        if (noLegSecurityAltID != null && noLegSecurityAltID.intValue() > 0) {
            if (LEG_SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                legSecurityAltIDGroups = new LegSecurityAltIDGroup[noLegSecurityAltID.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                for (int i = 0; i < noLegSecurityAltID.intValue(); i++) {
                    LegSecurityAltIDGroup group = new LegSecurityAltIDGroup43(context);
                    group.decode(message);
                    legSecurityAltIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrumentLeg] component version [4.3].";
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
