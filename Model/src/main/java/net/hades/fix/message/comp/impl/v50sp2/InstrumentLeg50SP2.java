/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg50SP2.java
 *
 * $Id: InstrumentLeg50SP2.java,v 1.10 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.group.impl.v50sp2.LegSecurityAltIDGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.FlowScheduleType;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.PutOrCall;

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

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LegSecurityAltIDGroup;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharacterAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

/**
 * InstrumentLeg FIX version 5.0SP2 implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 23/11/2008, 10:21:01 AM
 */
@XmlRootElement(name="Leg")
@XmlType(propOrder = {"legSecurityAltIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class InstrumentLeg50SP2 extends InstrumentLeg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> LEG_SEC_ALT_ID_GROUP_TAGS = new LegSecurityAltIDGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(LEG_SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(LEG_SEC_ALT_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentLeg50SP2() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public InstrumentLeg50SP2(FragmentContext context) {
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
                legSecurityAltIDGroups[i] = new LegSecurityAltIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

        LegSecurityAltIDGroup group = new LegSecurityAltIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "MatTm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getLegMaturityTime() {
        return legMaturityTime;
    }

    @Override
    public void setLegMaturityTime(Date legMaturityTime) {
        this.legMaturityTime = legMaturityTime;
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
    @XmlJavaTypeAdapter(FixCharacterAdapter.class)
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

    @XmlAttribute(name = "MultTyp")
    @Override
    public ContractMultiplierUnit getLegContractMultiplierUnit() {
        return legContractMultiplierUnit;
    }

    @Override
    public void setLegContractMultiplierUnit(ContractMultiplierUnit legContractMultiplierUnit) {
        this.legContractMultiplierUnit = legContractMultiplierUnit;
    }

    @XmlAttribute(name = "FlowSchedTyp")
    @Override
    public FlowScheduleType getLegFlowScheduleType() {
        return legFlowScheduleType;
    }

    @Override
    public void setLegFlowScheduleType(FlowScheduleType legFlowScheduleType) {
        this.legFlowScheduleType = legFlowScheduleType;
    }

    @XmlAttribute(name = "UOM")
    @Override
    public UnitOfMeasure getLegUnitOfMeasure() {
        return legUnitOfMeasure;
    }

    @Override
    public void setLegUnitOfMeasure(UnitOfMeasure legUnitOfMeasure) {
        this.legUnitOfMeasure = legUnitOfMeasure;
    }

    @XmlAttribute(name = "UOMQty")
    @Override
    public Double getLegUnitOfMeasureQty() {
        return legUnitOfMeasureQty;
    }

    @Override
    public void setLegUnitOfMeasureQty(Double legUnitOfMeasureQty) {
        this.legUnitOfMeasureQty = legUnitOfMeasureQty;
    }

    @XmlAttribute(name = "PxUOM")
    @Override
    public PriceUnitOfMeasure getLegPriceUnitOfMeasure() {
        return legPriceUnitOfMeasure;
    }

    @Override
    public void setLegPriceUnitOfMeasure(PriceUnitOfMeasure legPriceUnitOfMeasure) {
        this.legPriceUnitOfMeasure = legPriceUnitOfMeasure;
    }

    @XmlAttribute(name = "PxUOMQty")
    @Override
    public Double getLegPriceUnitOfMeasureQty() {
        return legPriceUnitOfMeasureQty;
    }

    @Override
    public void setLegPriceUnitOfMeasureQty(Double legPriceUnitOfMeasureQty) {
        this.legPriceUnitOfMeasureQty = legPriceUnitOfMeasureQty;
    }

    @XmlAttribute(name = "TmUnit")
    @Override
    public TimeUnit getLegTimeUnit() {
        return legTimeUnit;
    }

    @Override
    public void setLegTimeUnit(TimeUnit legTimeUnit) {
        this.legTimeUnit = legTimeUnit;
    }

    @XmlAttribute(name = "ExerStyle")
    @Override
    public ExerciseStyle getLegExerciseStyle() {
        return legExerciseStyle;
    }

    @Override
    public void setLegExerciseStyle(ExerciseStyle legExerciseStyle) {
        this.legExerciseStyle = legExerciseStyle;
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

    @XmlAttribute(name = "PutCall")
    @Override
    public PutOrCall getLegPutOrCall() {
        return legPutOrCall;
    }

    @Override
    public void setLegPutOrCall(PutOrCall legPutOrCall) {
        this.legPutOrCall = legPutOrCall;
    }

    @XmlAttribute(name = "LegOptionRatio")
    @Override
    public Double getLegOptionRatio() {
        return legOptionRatio;
    }

    @Override
    public void setLegOptionRatio(Double legOptionRatio) {
        this.legOptionRatio = legOptionRatio;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getLegPrice() {
        return legPrice;
    }

    @Override
    public void setLegPrice(Double legPrice) {
        this.legPrice = legPrice;
    }

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (noLegSecurityAltID != null && noLegSecurityAltID.intValue() > 0) {
            if (LEG_SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                legSecurityAltIDGroups = new LegSecurityAltIDGroup[noLegSecurityAltID.intValue()];
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                for (int i = 0; i < noLegSecurityAltID.intValue(); i++) {
                    LegSecurityAltIDGroup group = new LegSecurityAltIDGroup50SP2(context);
                    group.decode(message);
                    legSecurityAltIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [InstrumentLeg] component version [5.0SP2].";
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
