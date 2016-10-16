/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg44.java
 *
 * $Id: InstrumentLeg44.java,v 1.12 2011-04-14 23:44:59 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.struct.Tag;

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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegSecurityAltIDGroup;
import net.hades.fix.message.group.impl.v44.LegSecurityAltIDGroup44;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * InstrumentLeg FIX version 4.4 implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 23/11/2008, 10:21:01 AM
 */
@XmlRootElement(name="Leg")
@XmlType(propOrder = {"legSecurityAltIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentLeg44 extends InstrumentLeg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V44 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegSymbol.getValue(),
        TagNum.LegSymbolSfx.getValue(),
        TagNum.LegSecurityID.getValue(),
        TagNum.LegSecurityIDSource.getValue(),
        TagNum.NoLegSecurityAltID.getValue(),
        TagNum.LegProduct.getValue(),
        TagNum.LegCFICode.getValue(),
        TagNum.LegSecurityType.getValue(),
        TagNum.LegSecuritySubType.getValue(),
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
        TagNum.LegStrikeCurrency.getValue(),
        TagNum.LegOptAttribute.getValue(),
        TagNum.LegContractMultiplier.getValue(),
        TagNum.LegContractMultiplierUnit.getValue(),
        TagNum.LegFlowScheduleType.getValue(),
        TagNum.LegUnitOfMeasure.getValue(),
        TagNum.LegUnitOfMeasureQty.getValue(),
        TagNum.LegPriceUnitOfMeasure.getValue(),
        TagNum.LegPriceUnitOfMeasureQty.getValue(),
        TagNum.LegTimeUnit.getValue(),
        TagNum.LegExerciseStyle.getValue(),
        TagNum.LegCouponRate.getValue(),
        TagNum.LegSecurityExchange.getValue(),
        TagNum.LegIssuer.getValue(),
        TagNum.LegSecurityDesc.getValue(),
        TagNum.LegRatioQty.getValue(),
        TagNum.LegSide.getValue(),
        TagNum.LegCurrency.getValue(),
        TagNum.LegPool.getValue(),
        TagNum.LegDatedDate.getValue(),
        TagNum.LegContractSettlMonth.getValue(),
        TagNum.LegInterestAccrualDate.getValue(),
        TagNum.LegPutOrCall.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LEG_SEC_ALT_ID_GROUP_TAGS = new LegSecurityAltIDGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V44);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(LEG_SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LEG_SEC_ALT_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentLeg44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public InstrumentLeg44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V44;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Sym")
    @Override
    public String getLegSymbol() {
        return legSymbol;
    }

    @Override
    public void setLegSymbol(String legSymbol) {
        this.legSymbol = legSymbol;
    }

    @XmlAttribute(name = "Sfx")
    @Override
    public String getLegSymbolSfx() {
        return legSymbolSfx;
    }

    @Override
    public void setLegSymbolSfx(String legSymbolSfx) {
        this.legSymbolSfx = legSymbolSfx;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getLegSecurityID() {
        return legSecurityID;
    }

    @Override
    public void setLegSecurityID(String legSecurityID) {
        this.legSecurityID = legSecurityID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getLegSecurityIDSource() {
        return legSecurityIDSource;
    }

    @Override
    public void setLegSecurityIDSource(String legSecurityIDSource) {
        this.legSecurityIDSource = legSecurityIDSource;
    }

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
                legSecurityAltIDGroups[i] = new LegSecurityAltIDGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

        LegSecurityAltIDGroup group = new LegSecurityAltIDGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @XmlAttribute(name = "Prod")
    @Override
    public Product getLegProduct() {
        return legProduct;
    }

    @Override
    public void setLegProduct(Product legProduct) {
        this.legProduct = legProduct;
    }

    @XmlAttribute(name = "CFI")
    @Override
    public String getLegCFICode() {
        return legCFICode;
    }

    @Override
    public void setLegCFICode(String legCFICode) {
        this.legCFICode = legCFICode;
    }

    @XmlAttribute(name = "SecTyp")
    @Override
    public String getLegSecurityType() {
        return legSecurityType;
    }

    @Override
    public void setLegSecurityType(String legSecurityType) {
        this.legSecurityType = legSecurityType;
    }

    @XmlAttribute(name = "SecSubTyp")
    @Override
    public String getLegSecuritySubType() {
        return legSecuritySubType;
    }

    @Override
    public void setLegSecuritySubType(String legSecuritySubType) {
        this.legSecuritySubType = legSecuritySubType;
    }

    @XmlAttribute(name = "MMY")
    @Override
    public String getLegMaturityMonthYear() {
        return legMaturityMonthYear;
    }

    @Override
    public void setLegMaturityMonthYear(String legMaturityMonthYear) {
        this.legMaturityMonthYear = legMaturityMonthYear;
    }

    @XmlAttribute(name = "Mat")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegMaturityDate() {
        return legMaturityDate;
    }

    @Override
    public void setLegMaturityDate(Date legMaturityDate) {
        this.legMaturityDate = legMaturityDate;
    }

    @XmlAttribute(name = "CpnPmt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegCouponPaymentDate() {
        return legCouponPaymentDate;
    }

    @Override
    public void setLegCouponPaymentDate(Date legCouponPaymentDate) {
        this.legCouponPaymentDate = legCouponPaymentDate;
    }

    @XmlAttribute(name = "Issued")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegIssueDate() {
        return legIssueDate;
    }

    @Override
    public void setLegIssueDate(Date legIssueDate) {
        this.legIssueDate = legIssueDate;
    }

    @XmlAttribute(name = "RepoCollSecTyp")
    @Override
    public String getLegRepoCollateralSecurityType() {
        return legRepoCollateralSecurityType;
    }

    @Override
    public void setLegRepoCollateralSecurityType(String legRepoCollateralSecurityType) {
        this.legRepoCollateralSecurityType = legRepoCollateralSecurityType;
    }

    @XmlAttribute(name = "RepoTrm")
    @Override
    public Integer getLegRepurchaseTerm() {
        return legRepurchaseTerm;
    }

    @Override
    public void setLegRepurchaseTerm(Integer legRepurchaseTerm) {
        this.legRepurchaseTerm = legRepurchaseTerm;
    }

    @XmlAttribute(name = "RepoRt")
    @Override
    public Double getLegRepurchaseRate() {
        return legRepurchaseRate;
    }

    @Override
    public void setLegRepurchaseRate(Double legRepurchaseRate) {
        this.legRepurchaseRate = legRepurchaseRate;
    }

    @XmlAttribute(name = "Fctr")
    @Override
    public Double getLegFactor() {
        return legFactor;
    }

    @Override
    public void setLegFactor(Double legFactor) {
        this.legFactor = legFactor;
    }

    @XmlAttribute(name = "CrdRtg")
    @Override
    public String getLegCreditRating() {
        return legCreditRating;
    }

    @Override
    public void setLegCreditRating(String legCreditRating) {
        this.legCreditRating = legCreditRating;
    }

    @XmlAttribute(name = "Rgstry")
    @Override
    public String getLegInstrRegistry() {
        return legInstrRegistry;
    }

    @Override
    public void setLegInstrRegistry(String legInstrRegistry) {
        this.legInstrRegistry = legInstrRegistry;
    }

    @XmlAttribute(name = "Ctry")
    @Override
    public String getLegCountryOfIssue() {
        return legCountryOfIssue;
    }

    @Override
    public void setLegCountryOfIssue(String legCountryOfIssue) {
        this.legCountryOfIssue = legCountryOfIssue;
    }

    @XmlAttribute(name = "StOrProvnc")
    @Override
    public String getLegStateOrProvinceOfIssue() {
        return legStateOrProvinceOfIssue;
    }

    @Override
    public void setLegStateOrProvinceOfIssue(String legStateOrProvinceOfIssue) {
        this.legStateOrProvinceOfIssue = legStateOrProvinceOfIssue;
    }

    @XmlAttribute(name = "Lcl")
    @Override
    public String getLegLocaleOfIssue() {
        return legLocaleOfIssue;
    }

    @Override
    public void setLegLocaleOfIssue(String legLocaleOfIssue) {
        this.legLocaleOfIssue = legLocaleOfIssue;
    }

    @XmlAttribute(name = "Redeem")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegRedemptionDate() {
        return legRedemptionDate;
    }

    @Override
    public void setLegRedemptionDate(Date legRedemptionDate) {
        this.legRedemptionDate = legRedemptionDate;
    }

    @XmlAttribute(name = "Strk")
    @Override
    public Double getLegStrikePrice() {
        return legStrikePrice;
    }

    @Override
    public void setLegStrikePrice(Double legStrikePrice) {
        this.legStrikePrice = legStrikePrice;
    }

    @XmlAttribute(name = "StrkCcy")
    @Override
    public Currency getLegStrikeCurrency() {
        return legStrikeCurrency;
    }

    @Override
    public void setLegStrikeCurrency(Currency legStrikeCurrency) {
        this.legStrikeCurrency = legStrikeCurrency;
    }

    @XmlAttribute(name = "OptA")
    @Override
    public Character getLegOptAttribute() {
        return legOptAttribute;
    }

    @Override
    public void setLegOptAttribute(Character legOptAttribute) {
        this.legOptAttribute = legOptAttribute;
    }

    @XmlAttribute(name = "Cmult")
    @Override
    public Double getLegContractMultiplier() {
        return legContractMultiplier;
    }

    @Override
    public void setLegContractMultiplier(Double legContractMultiplier) {
        this.legContractMultiplier = legContractMultiplier;
    }

    @XmlAttribute(name = "CpnRt")
    @Override
    public Double getLegCouponRate() {
        return legCouponRate;
    }

    @Override
    public void setLegCouponRate(Double legCouponRate) {
        this.legCouponRate = legCouponRate;
    }

    @XmlAttribute(name = "Exch")
    @Override
    public String getLegSecurityExchange() {
        return legSecurityExchange;
    }

    @Override
    public void setLegSecurityExchange(String legSecurityExchange) {
        this.legSecurityExchange = legSecurityExchange;
    }

    @XmlAttribute(name = "Issr")
    @Override
    public String getLegIssuer() {
        return legIssuer;
    }

    @Override
    public void setLegIssuer(String legIssuer) {
        this.legIssuer = legIssuer;
    }

    @XmlAttribute(name = "EncLegIssrLen")
    @Override
    public Integer getEncodedLegIssuerLen() {
        return encodedLegIssuerLen;
    }

    @Override
    public void setEncodedLegIssuerLen(Integer encodedLegIssuerLen) {
        this.encodedLegIssuerLen = encodedLegIssuerLen;
    }

    @XmlAttribute(name = "EncLegIssr")
    @Override
    public byte[] getEncodedLegIssuer() {
        return encodedLegIssuer;
    }

    @Override
    public void setEncodedLegIssuer(byte[] encodedLegIssuer) {
        this.encodedLegIssuer = encodedLegIssuer;
        if (encodedLegIssuerLen == null) {
            encodedLegIssuerLen = new Integer(encodedLegIssuer.length);
        }
    }

    @XmlAttribute(name = "Desc")
    @Override
    public String getLegSecurityDesc() {
        return legSecurityDesc;
    }

    @Override
    public void setLegSecurityDesc(String legSecurityDesc) {
        this.legSecurityDesc = legSecurityDesc;
    }

    @XmlAttribute(name = "EncLegSecDesc")
    @Override
    public byte[] getEncodedLegSecurityDesc() {
        return encodedLegSecurityDesc;
    }

    @Override
    public void setEncodedLegSecurityDesc(byte[] encodedLegSecurityDesc) {
        this.encodedLegSecurityDesc = encodedLegSecurityDesc;
        if (encodedLegSecurityDescLen == null) {
            encodedLegSecurityDescLen = new Integer(encodedLegSecurityDesc.length);
        }
    }

    @XmlAttribute(name = "EncLegSecDescLen")
    @Override
    public Integer getEncodedLegSecurityDescLen() {
        return encodedLegSecurityDescLen;
    }

    @Override
    public void setEncodedLegSecurityDescLen(Integer encodedLegSecurityDescLen) {
        this.encodedLegSecurityDescLen = encodedLegSecurityDescLen;
    }

    @XmlAttribute(name = "RatioQty")
    @Override
    public Double getLegRatioQty() {
        return legRatioQty;
    }

    @Override
    public void setLegRatioQty(Double legRatioQty) {
        this.legRatioQty = legRatioQty;
    }

    @XmlAttribute(name = "Side")
    @Override
    public Side getLegSide() {
        return legSide;
    }

    @Override
    public void setLegSide(Side legSide) {
        this.legSide = legSide;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getLegCurrency() {
        return legCurrency;
    }

    @Override
    public void setLegCurrency(Currency legCurrency) {
        this.legCurrency = legCurrency;
    }

    @XmlAttribute(name = "Pool")
    @Override
    public String getLegPool() {
        return legPool;
    }

    @Override
    public void setLegPool(String legPool) {
        this.legPool = legPool;
    }

    @XmlAttribute(name = "Dated")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegDatedDate() {
        return legDatedDate;
    }

    @Override
    public void setLegDatedDate(Date legDatedDate) {
        this.legDatedDate = legDatedDate;
    }

    @XmlAttribute(name = "CSetMo")
    @Override
    public String getLegContractSettlMonth() {
        return legContractSettlMonth;
    }

    @Override
    public void setLegContractSettlMonth(String legContractSettlMonth) {
        this.legContractSettlMonth = legContractSettlMonth;
    }

    @XmlAttribute(name = "IntAcrl")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegInterestAccrualDate() {
        return legInterestAccrualDate;
    }

    @Override
    public void setLegInterestAccrualDate(Date legInterestAccrualDate) {
        this.legInterestAccrualDate = legInterestAccrualDate;
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
            if (MsgUtil.isTagInList(TagNum.LegSecuritySubType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegSecuritySubType, legSecuritySubType);
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
            if (legCurrency != null && MsgUtil.isTagInList(TagNum.LegCurrency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegCurrency, legCurrency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.LegPool, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegPool, legPool);
            }
            if (MsgUtil.isTagInList(TagNum.LegDatedDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.LegDatedDate, legDatedDate);
            }
            if (MsgUtil.isTagInList(TagNum.LegContractSettlMonth, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegContractSettlMonth, legContractSettlMonth);
            }
            if (MsgUtil.isTagInList(TagNum.LegInterestAccrualDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.LegInterestAccrualDate, legInterestAccrualDate);
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
                    LegSecurityAltIDGroup group = new LegSecurityAltIDGroup44(context);
                    group.decode(message);
                    legSecurityAltIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrumentLeg] component version [4.4].";
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
