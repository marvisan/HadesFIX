/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument50.java
 *
 * $Id: UnderlyingInstrument50.java,v 1.9 2011-04-14 23:44:50 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.UndlyInstrumentPartyIDGroup;
import net.hades.fix.message.group.impl.v50.UnderlyingSecurityAltIDGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.FXRateCalc;
import net.hades.fix.message.type.UnitOfMeasure;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.UnderlyingStipulations;
import net.hades.fix.message.comp.UndlyInstrumentParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.UnderlyingSecurityAltIDGroup;
import net.hades.fix.message.group.UnderlyingStipsGroup;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnderlyingSettlementType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharacterAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 *  The UnderlyingInstrument component block for FIX version 4.4.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 16/12/2008, 7:28:06 PM
 */
@XmlRootElement(name="Undly")
@XmlType(propOrder = {"underlyingSecurityAltIDGroups", "underlyingStipsGroups", "undlyInstrumentPartyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class UnderlyingInstrument50 extends UnderlyingInstrument {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UNDLY_SEC_ALT_ID_GROUP_TAGS = new UnderlyingSecurityAltIDGroup50().getFragmentAllTags();
    protected static final Set<Integer> UNDLY_STIP_GROUP_TAGS = new UnderlyingStipulations50().getFragmentAllTags();
    protected static final Set<Integer> UNDLY_INSTR_PARTIES_COMP_TAGS = new UndlyInstrumentParties50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(UNDLY_SEC_ALT_ID_GROUP_TAGS);
        ALL_TAGS.addAll(UNDLY_STIP_GROUP_TAGS);
        ALL_TAGS.addAll(UNDLY_INSTR_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDLY_SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(UNDLY_STIP_GROUP_TAGS);
        START_COMP_TAGS.addAll(UNDLY_INSTR_PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingInstrument50() {
        super();
    }

    public UnderlyingInstrument50(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Sym")
    @Override
    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    @Override
    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    @XmlAttribute(name = "Sfx")
    @Override
    public String getUnderlyingSymbolSfx() {
        return underlyingSymbolSfx;
    }

    @Override
    public void setUnderlyingSymbolSfx(String underlyingSymbolSfx) {
        this.underlyingSymbolSfx = underlyingSymbolSfx;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    @Override
    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getUnderlyingSecurityIDSource() {
        return underlyingSecurityIDSource;
    }

    @Override
    public void setUnderlyingSecurityIDSource(String underlyingSecurityIDSource) {
        this.underlyingSecurityIDSource = underlyingSecurityIDSource;
    }

    @Override
    public Integer getNoUnderlyingSecurityAltID() {
        return noUnderlyingSecurityAltID;
    }

    @Override
    public void setNoUnderlyingSecurityAltID(Integer noUnderlyingSecurityAltID) {
        this.noUnderlyingSecurityAltID = noUnderlyingSecurityAltID;
        if (noUnderlyingSecurityAltID != null) {
            underlyingSecurityAltIDGroups = new UnderlyingSecurityAltIDGroup[noUnderlyingSecurityAltID.intValue()];
            for (int i = 0; i < underlyingSecurityAltIDGroups.length; i++) {
                underlyingSecurityAltIDGroups[i] = new UnderlyingSecurityAltIDGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingSecurityAltIDGroup[] getUnderlyingSecurityAltIDGroups() {
        return underlyingSecurityAltIDGroups;
    }

    public void setUnderlyingSecurityAltIDGroups(UnderlyingSecurityAltIDGroup[] underlyingSecurityAltIDGroups) {
        this.underlyingSecurityAltIDGroups = underlyingSecurityAltIDGroups;
        if (underlyingSecurityAltIDGroups != null) {
            noUnderlyingSecurityAltID = new Integer(underlyingSecurityAltIDGroups.length);
        }
    }

    @Override
    public UnderlyingSecurityAltIDGroup addUnderlyingSecurityAltIDGroup() {

        UnderlyingSecurityAltIDGroup group = new UnderlyingSecurityAltIDGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingSecurityAltIDGroup> groups = new ArrayList<UnderlyingSecurityAltIDGroup>();
        if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length > 0) {
            groups = new ArrayList<UnderlyingSecurityAltIDGroup>(Arrays.asList(underlyingSecurityAltIDGroups));
        }
        groups.add(group);
        underlyingSecurityAltIDGroups = groups.toArray(new UnderlyingSecurityAltIDGroup[groups.size()]);
        noUnderlyingSecurityAltID = new Integer(underlyingSecurityAltIDGroups.length);

        return group;
    }

    @Override
    public UnderlyingSecurityAltIDGroup deleteUnderlyingSecurityAltIDGroup(int index) {

        UnderlyingSecurityAltIDGroup result = null;

        if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length > 0 && underlyingSecurityAltIDGroups.length > index) {
            List<UnderlyingSecurityAltIDGroup> groups = new ArrayList<UnderlyingSecurityAltIDGroup>(Arrays.asList(underlyingSecurityAltIDGroups));
            result = groups.remove(index);
            underlyingSecurityAltIDGroups = groups.toArray(new UnderlyingSecurityAltIDGroup[groups.size()]);
            if (underlyingSecurityAltIDGroups.length > 0) {
                noUnderlyingSecurityAltID = new Integer(underlyingSecurityAltIDGroups.length);
            } else {
                underlyingSecurityAltIDGroups = null;
                noUnderlyingSecurityAltID = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingSecurityAltIDGroups() {

        int result = 0;
        if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length > 0) {
            result = underlyingSecurityAltIDGroups.length;
            underlyingSecurityAltIDGroups = null;
            noUnderlyingSecurityAltID = null;
        }

        return result;
    }

    @XmlAttribute(name = "Prod")
    @Override
    public Product getUnderlyingProduct() {
        return underlyingProduct;
    }

    @Override
    public void setUnderlyingProduct(Product underlyingProduct) {
        this.underlyingProduct = underlyingProduct;
    }

    @XmlAttribute(name = "CFI")
    @Override
    public String getUnderlyingCFICode() {
        return underlyingCFICode;
    }

    @Override
    public void setUnderlyingCFICode(String underlyingCFICode) {
        this.underlyingCFICode = underlyingCFICode;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public String getUnderlyingSecurityType() {
        return underlyingSecurityType;
    }

    @Override
    public void setUnderlyingSecurityType(String underlyingSecurityType) {
        this.underlyingSecurityType = underlyingSecurityType;
    }

    @XmlAttribute(name = "SubTyp")
    @Override
    public String getUnderlyingSecuritySubType() {
        return underlyingSecuritySubType;
    }

    @Override
    public void setUnderlyingSecuritySubType(String underlyingSecuritySubType) {
        this.underlyingSecuritySubType = underlyingSecuritySubType;
    }

    @XmlAttribute(name = "MMY")
    @Override
    public String getUnderlyingMaturityMonthYear() {
        return underlyingMaturityMonthYear;
    }

    @Override
    public void setUnderlyingMaturityMonthYear(String underlyingMaturityMonthYear) {
        this.underlyingMaturityMonthYear = underlyingMaturityMonthYear;
    }

    @XmlAttribute(name = "Mat")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getUnderlyingMaturityDate() {
        return underlyingMaturityDate;
    }

    @Override
    public void setUnderlyingMaturityDate(Date underlyingMaturityDate) {
        this.underlyingMaturityDate = underlyingMaturityDate;
    }

    @XmlAttribute(name = "CpnPmt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getUnderlyingCouponPaymentDate() {
        return underlyingCouponPaymentDate;
    }

    @Override
    public void setUnderlyingCouponPaymentDate(Date underlyingCouponPaymentDate) {
        this.underlyingCouponPaymentDate = underlyingCouponPaymentDate;
    }

    @XmlAttribute(name = "Issued")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getUnderlyingIssueDate() {
        return underlyingIssueDate;
    }

    @Override
    public void setUnderlyingIssueDate(Date underlyingIssueDate) {
        this.underlyingIssueDate = underlyingIssueDate;
    }

    @XmlAttribute(name = "RepoCollSecTyp")
    @Override
    public String getUnderlyingRepoCollateralSecurityType() {
        return underlyingRepoCollateralSecurityType;
    }

    @Override
    public void setUnderlyingRepoCollateralSecurityType(String underlyingRepoCollateralSecurityType) {
        this.underlyingRepoCollateralSecurityType = underlyingRepoCollateralSecurityType;
    }

    @XmlAttribute(name = "RepoTrm")
    @Override
    public Integer getUnderlyingRepurchaseTerm() {
        return underlyingRepurchaseTerm;
    }

    @Override
    public void setUnderlyingRepurchaseTerm(Integer underlyingRepurchaseTerm) {
        this.underlyingRepurchaseTerm = underlyingRepurchaseTerm;
    }

    @XmlAttribute(name = "RepoRt")
    @Override
    public Double getUnderlyingRepurchaseRate() {
        return underlyingRepurchaseRate;
    }

    @Override
    public void setUnderlyingRepurchaseRate(Double underlyingRepurchaseRate) {
        this.underlyingRepurchaseRate = underlyingRepurchaseRate;
    }

    @XmlAttribute(name = "Fctr")
    @Override
    public Double getUnderlyingFactor() {
        return underlyingFactor;
    }

    @Override
    public void setUnderlyingFactor(Double underlyingFactor) {
        this.underlyingFactor = underlyingFactor;
    }

    @XmlAttribute(name = "CrdRtg")
    @Override
    public String getUnderlyingCreditRating() {
        return underlyingCreditRating;
    }

    @Override
    public void setUnderlyingCreditRating(String underlyingCreditRating) {
        this.underlyingCreditRating = underlyingCreditRating;
    }

    @XmlAttribute(name = "Rgstry")
    @Override
    public String getUnderlyingInstrRegistry() {
        return underlyingInstrRegistry;
    }

    @Override
    public void setUnderlyingInstrRegistry(String underlyingInstrRegistry) {
        this.underlyingInstrRegistry = underlyingInstrRegistry;
    }

    @XmlAttribute(name = "Ctry")
    @Override
    public String getUnderlyingCountryOfIssue() {
        return underlyingCountryOfIssue;
    }

    @Override
    public void setUnderlyingCountryOfIssue(String underlyingCountryOfIssue) {
        this.underlyingCountryOfIssue = underlyingCountryOfIssue;
    }

    @XmlAttribute(name = "StOrProvnc")
    @Override
    public String getUnderlyingStateOrProvinceOfIssue() {
        return underlyingStateOrProvinceOfIssue;
    }

    @Override
    public void setUnderlyingStateOrProvinceOfIssue(String underlyingStateOrProvinceOfIssue) {
        this.underlyingStateOrProvinceOfIssue = underlyingStateOrProvinceOfIssue;
    }

    @XmlAttribute(name = "Lcl")
    @Override
    public String getUnderlyingLocaleOfIssue() {
        return underlyingLocaleOfIssue;
    }

    @Override
    public void setUnderlyingLocaleOfIssue(String underlyingLocaleOfIssue) {
        this.underlyingLocaleOfIssue = underlyingLocaleOfIssue;
    }

    @XmlAttribute(name = "Redeem")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getUnderlyingRedemptionDate() {
        return underlyingRedemptionDate;
    }

    @Override
    public void setUnderlyingRedemptionDate(Date underlyingRedemptionDate) {
        this.underlyingRedemptionDate = underlyingRedemptionDate;
    }

    @XmlAttribute(name = "StrkPx")
    @Override
    public Double getUnderlyingStrikePrice() {
        return underlyingStrikePrice;
    }

    @Override
    public void setUnderlyingStrikePrice(Double underlyingStrikePrice) {
        this.underlyingStrikePrice = underlyingStrikePrice;
    }

    @XmlAttribute(name = "StrkCcy")
    @Override
    public Currency getUnderlyingStrikeCurrency() {
        return underlyingStrikeCurrency;
    }

    @Override
    public void setUnderlyingStrikeCurrency(Currency underlyingStrikeCurrency) {
        this.underlyingStrikeCurrency = underlyingStrikeCurrency;
    }

    @XmlAttribute(name = "OptA")
    @XmlJavaTypeAdapter(FixCharacterAdapter.class)
    @Override
    public Character getUnderlyingOptAttribute() {
        return underlyingOptAttribute;
    }

    @Override
    public void setUnderlyingOptAttribute(Character underlyingOptAttribute) {
        this.underlyingOptAttribute = underlyingOptAttribute;
    }

    @XmlAttribute(name = "Mult")
    @Override
    public Double getUnderlyingContractMultiplier() {
        return underlyingContractMultiplier;
    }

    @Override
    public void setUnderlyingContractMultiplier(Double underlyingContractMultiplier) {
        this.underlyingContractMultiplier = underlyingContractMultiplier;
    }

    @XmlAttribute(name = "UOM")
    @Override
    public UnitOfMeasure getUnderlyingUnitOfMeasure() {
        return underlyingUnitOfMeasure;
    }

    @Override
    public void setUnderlyingUnitOfMeasure(UnitOfMeasure underlyingUnitOfMeasure) {
        this.underlyingUnitOfMeasure = underlyingUnitOfMeasure;
    }

    @XmlAttribute(name = "TmUnit")
    @Override
    public TimeUnit getUnderlyingTimeUnit() {
        return underlyingTimeUnit;
    }

    @Override
    public void setUnderlyingTimeUnit(TimeUnit underlyingTimeUnit) {
        this.underlyingTimeUnit = underlyingTimeUnit;
    }

    @XmlAttribute(name = "CpnRt")
    @Override
    public Double getUnderlyingCouponRate() {
        return underlyingCouponRate;
    }

    @Override
    public void setUnderlyingCouponRate(Double underlyingCouponRate) {
        this.underlyingCouponRate = underlyingCouponRate;
    }

    @XmlAttribute(name = "Exch")
    @Override
    public String getUnderlyingSecurityExchange() {
        return underlyingSecurityExchange;
    }

    @Override
    public void setUnderlyingSecurityExchange(String underlyingSecurityExchange) {
        this.underlyingSecurityExchange = underlyingSecurityExchange;
    }

    @XmlAttribute(name = "Issr")
    @Override
    public String getUnderlyingIssuer() {
        return underlyingIssuer;
    }

    @Override
    public void setUnderlyingIssuer(String underlyingIssuer) {
        this.underlyingIssuer = underlyingIssuer;
    }

    @XmlAttribute(name = "EncUndIssrLen")
    @Override
    public Integer getEncodedUnderlyingIssuerLen() {
        return encodedUnderlyingIssuerLen;
    }

    @Override
    public void setEncodedUnderlyingIssuerLen(Integer encodedUnderlyingIssuerLen) {
        this.encodedUnderlyingIssuerLen = encodedUnderlyingIssuerLen;
    }

    @XmlAttribute(name = "EncUndIssr")
    @Override
    public byte[] getEncodedUnderlyingIssuer() {
        return encodedUnderlyingIssuer;
    }

    @Override
    public void setEncodedUnderlyingIssuer(byte[] encodedUnderlyingIssuer) {
        this.encodedUnderlyingIssuer = encodedUnderlyingIssuer;
        if (encodedUnderlyingIssuerLen == null) {
            encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
        }
    }

    @XmlAttribute(name = "Desc")
    @Override
    public String getUnderlyingSecurityDesc() {
        return underlyingSecurityDesc;
    }

    @Override
    public void setUnderlyingSecurityDesc(String underlyingSecurityDesc) {
        this.underlyingSecurityDesc = underlyingSecurityDesc;
    }

    @XmlAttribute(name = "EncUndSecDescLen")
    @Override
    public Integer getEncodedUnderlyingSecurityDescLen() {
        return encodedUnderlyingSecurityDescLen;
    }

    @Override
    public void setEncodedUnderlyingSecurityDescLen(Integer encodedUnderlyingSecurityDescLen) {
        this.encodedUnderlyingSecurityDescLen = encodedUnderlyingSecurityDescLen;
    }

    @XmlAttribute(name = "EncUndSecDesc")
    @Override
    public byte[] getEncodedUnderlyingSecurityDesc() {
        return encodedUnderlyingSecurityDesc;
    }

    @Override
    public void setEncodedUnderlyingSecurityDesc(byte[] encodedUnderlyingSecurityDesc) {
        this.encodedUnderlyingSecurityDesc = encodedUnderlyingSecurityDesc;
    }

    @XmlAttribute(name = "CPPgm")
    @Override
    public String getUnderlyingCPProgram() {
        return underlyingCPProgram;
    }

    @Override
    public void setUnderlyingCPProgram(String underlyingCPProgram) {
        this.underlyingCPProgram = underlyingCPProgram;
    }

    @XmlAttribute(name = "CPRegTyp")
    @Override
    public String getUnderlyingCPRegType() {
        return underlyingCPRegType;
    }

    @Override
    public void setUnderlyingCPRegType(String underlyingCPRegType) {
        this.underlyingCPRegType = underlyingCPRegType;
    }

    @XmlAttribute(name = "AllocPct")
    @Override
    public Double getUnderlyingAllocationPercent() {
        return underlyingAllocationPercent;
    }

    @Override
    public void setUnderlyingAllocationPercent(Double underlyingAllocationPercent) {
        this.underlyingAllocationPercent = underlyingAllocationPercent;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getUnderlyingCurrency() {
        return underlyingCurrency;
    }

    @Override
    public void setUnderlyingCurrency(Currency underlyingCurrency) {
        this.underlyingCurrency = underlyingCurrency;
    }

    @XmlAttribute(name = "Qty")
    @Override
    public Double getUnderlyingQty() {
        return underlyingQty;
    }

    @Override
    public void setUnderlyingQty(Double underlyingQty) {
        this.underlyingQty = underlyingQty;
    }

    @XmlAttribute(name = "SettlTyp")
    @Override
    public UnderlyingSettlementType getUnderlyingSettlementType() {
        return underlyingSettlementType;
    }

    @Override
    public void setUnderlyingSettlementType(UnderlyingSettlementType underlyingSettlementType) {
        this.underlyingSettlementType = underlyingSettlementType;
    }

    @XmlAttribute(name = "CashAmt")
    @Override
    public Double getUnderlyingCashAmount() {
        return underlyingCashAmount;
    }

    @Override
    public void setUnderlyingCashAmount(Double underlyingCashAmount) {
        this.underlyingCashAmount = underlyingCashAmount;
    }

    @XmlAttribute(name = "CashTyp")
    @Override
    public String getUnderlyingCashType() {
        return underlyingCashType;
    }

    @Override
    public void setUnderlyingCashType(String underlyingCashType) {
        this.underlyingCashType = underlyingCashType;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getUnderlyingPx() {
        return underlyingPx;
    }

    @Override
    public void setUnderlyingPx(Double underlyingPx) {
        this.underlyingPx = underlyingPx;
    }

    @XmlAttribute(name = "DirtPx")
    @Override
    public Double getUnderlyingDirtyPrice() {
        return underlyingDirtyPrice;
    }

    @Override
    public void setUnderlyingDirtyPrice(Double underlyingDirtyPrice) {
        this.underlyingDirtyPrice = underlyingDirtyPrice;
    }

    @XmlAttribute(name = "EndPx")
    @Override
    public Double getUnderlyingEndPrice() {
        return underlyingEndPrice;
    }

    @Override
    public void setUnderlyingEndPrice(Double underlyingEndPrice) {
        this.underlyingEndPrice = underlyingEndPrice;
    }

    @XmlAttribute(name = "StartVal")
    @Override
    public Double getUnderlyingStartValue() {
        return underlyingStartValue;
    }

    @Override
    public void setUnderlyingStartValue(Double underlyingStartValue) {
        this.underlyingStartValue = underlyingStartValue;
    }

    @XmlAttribute(name = "CurVal")
    @Override
    public Double getUnderlyingCurrentValue() {
        return underlyingCurrentValue;
    }

    @Override
    public void setUnderlyingCurrentValue(Double underlyingCurrentValue) {
        this.underlyingCurrentValue = underlyingCurrentValue;
    }

    @XmlAttribute(name = "EndVal")
    @Override
    public Double getUnderlyingEndValue() {
        return underlyingEndValue;
    }

    @Override
    public void setUnderlyingEndValue(Double underlyingEndValue) {
        this.underlyingEndValue = underlyingEndValue;
    }

    @Override
    public UnderlyingStipulations getUnderlyingStipulations() {
        return underlyingStipulations;
    }

    public void setUnderlyingStipulations(UnderlyingStipulations underlyingStipulations) {
         this.underlyingStipulations = underlyingStipulations;
    }

    @XmlElementRef
    public UnderlyingStipsGroup[] getUnderlyingStipsGroups() {
        return underlyingStipulations == null ? null : underlyingStipulations.getUnderlyingStipsGroups();
    }

    public void setUnderlyingStipsGroups(UnderlyingStipsGroup[] underlyingStipsGroups) {
        if (underlyingStipsGroups != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            underlyingStipulations = new UnderlyingStipulations50(context);
            ((UnderlyingStipulations50) underlyingStipulations).setUnderlyingStipsGroups(underlyingStipsGroups);
        }
    }

    @Override
    public void setUnderlyingStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.underlyingStipulations = new UnderlyingStipulations50(context);
    }

    @Override
    public void clearUnderlyingStipulations() {
        this.underlyingStipulations = null;
    }

    @XmlAttribute(name = "AdjQty")
    @Override
    public Double getUnderlyingAdjustedQuantity() {
        return underlyingAdjustedQuantity;
    }

    @Override
    public void setUnderlyingAdjustedQuantity(Double underlyingAdjustedQuantity) {
        this.underlyingAdjustedQuantity = underlyingAdjustedQuantity;
    }

    @XmlAttribute(name = "FXRate")
    @Override
    public Double getUnderlyingFXRate() {
        return underlyingFXRate;
    }

    @Override
    public void setUnderlyingFXRate(Double underlyingFXRate) {
        this.underlyingFXRate = underlyingFXRate;
    }

    @XmlAttribute(name = "FXRateCalc")
    @Override
    public FXRateCalc getUnderlyingFXRateCalc() {
        return underlyingFXRateCalc;
    }

    @Override
    public void setUnderlyingFXRateCalc(FXRateCalc underlyingFXRateCalc) {
        this.underlyingFXRateCalc = underlyingFXRateCalc;
    }

    @XmlAttribute(name = "CapValu")
    @Override
    public Double getUnderlyingCapValue() {
        return underlyingCapValue;
    }

    @Override
    public void setUnderlyingCapValue(Double underlyingCapValue) {
        this.underlyingCapValue = underlyingCapValue;
    }

    @Override
    public UndlyInstrumentParties getUndlyInstrumentParties() {
        return undlyInstrumentParties;
    }

    @Override
    public void setUndlyInstrumentParties() {
        undlyInstrumentParties = new UndlyInstrumentParties50();
    }

    @Override
    public void clearUndlyInstrumentParties() {
        undlyInstrumentParties = null;
    }

    @XmlElementRef
    public UndlyInstrumentPartyIDGroup[] getUndlyInstrumentPartyIDGroups() {
        return undlyInstrumentParties == null ? null : undlyInstrumentParties.getUndlyInstrumentPartyIDGroups();
    }

    public void setUndlyInstrumentPartyIDGroups(UndlyInstrumentPartyIDGroup[] undlyInstrumentPartyIDGroups) {
        if (undlyInstrumentPartyIDGroups != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            undlyInstrumentParties = new UndlyInstrumentParties50(context);
            undlyInstrumentParties.setNoUndlyInstrumentParties(undlyInstrumentPartyIDGroups.length);
            ((UndlyInstrumentParties50) undlyInstrumentParties).setUndlyInstrumentPartyIDGroups(undlyInstrumentPartyIDGroups);
        }
    }

    @XmlAttribute(name = "SetMeth")
    @Override
    public String getUnderlyingSettlMethod() {
        return underlyingSettlMethod;
    }

    @Override
    public void setUnderlyingSettlMethod(String underlyingSettlMethod) {
        this.underlyingSettlMethod = underlyingSettlMethod;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (UNDLY_SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyingSecurityAltID != null && noUnderlyingSecurityAltID.intValue() > 0) {
                message.reset();
                underlyingSecurityAltIDGroups = new UnderlyingSecurityAltIDGroup[noUnderlyingSecurityAltID.intValue()];
                for (int i = 0; i < noUnderlyingSecurityAltID.intValue(); i++) {
                    UnderlyingSecurityAltIDGroup group = new UnderlyingSecurityAltIDGroup50(context);
                    group.decode(message);
                    underlyingSecurityAltIDGroups[i] = group;
                }
            }
        }
        if (UNDLY_STIP_GROUP_TAGS.contains(tag.tagNum)) {
            if (underlyingStipulations == null) {
                underlyingStipulations = new UnderlyingStipulations50(context);
            }
            message.reset();
            underlyingStipulations.decode(message);
        }
        if (UNDLY_INSTR_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (undlyInstrumentParties == null) {
                undlyInstrumentParties = new UndlyInstrumentParties50(context);
            }
            message.reset();
            undlyInstrumentParties.decode(message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [UnderlyingInstrument] component version [5.0].";
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
