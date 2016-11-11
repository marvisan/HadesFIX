/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrument50SP2.java
 *
 * $Id: DerivativeInstrument50SP2.java,v 1.2 2011-09-22 08:54:31 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.DerivativeInstrumentPartyGroup;
import net.hades.fix.message.group.impl.v50sp2.DerivativeSecurityAltIDGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.UnitOfMeasure;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
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

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.comp.DerivativeInstrument;
import net.hades.fix.message.comp.DerivativeInstrumentParties;
import net.hades.fix.message.comp.DerivativeSecurityXML;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivativeEventGroup;
import net.hades.fix.message.group.DerivativeSecurityAltIDGroup;
import net.hades.fix.message.group.impl.v50sp2.DerivativeEventGroup50SP2;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FuturesValuationMethod;
import net.hades.fix.message.type.InstrmtAssignmentMethod;
import net.hades.fix.message.type.ListMethod;
import net.hades.fix.message.type.PriceQuoteMethod;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityStatus;
import net.hades.fix.message.type.SettlMethod;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharacterAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

/**
 * FIX version 5.0SP2 DerivativeInstrument component data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 30/11/2008, 8:43:40 PM
 */
@XmlRootElement(name="DerivInstrmt")
@XmlType(propOrder = {"derivativeSecurityAltIDGroups", "derivativeSecurityXML", "derivativeEvents", "derivativeInstrumentPartyGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class DerivativeInstrument50SP2 extends DerivativeInstrument {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> SEC_ALT_ID_GROUP_TAGS = new DerivativeSecurityAltIDGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> SECURITY_XML_COMP_TAGS = new DerivativeSecurityXML50SP2().getFragmentAllTags();
    protected static final Set<Integer> EVENT_GROUP_TAGS = new DerivativeEventGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_PARTIES_COMP_TAGS = new DerivativeInstrumentParties50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SEC_ALT_ID_GROUP_TAGS);
        ALL_TAGS.addAll(SECURITY_XML_COMP_TAGS);
        ALL_TAGS.addAll(EVENT_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(SECURITY_XML_COMP_TAGS);
        START_COMP_TAGS.addAll(EVENT_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public DerivativeInstrument50SP2() {
        super();
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
    }
    
    public DerivativeInstrument50SP2(FragmentContext context) {
        super(context);
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
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
    public String getDerivativeSymbol() {
        return derivativeSymbol;
    }

    @Override
    public void setDerivativeSymbol(String derivativeSymbol) {
        this.derivativeSymbol = derivativeSymbol;
    }

    @XmlAttribute(name = "Sfx")
    @Override
    public String getDerivativeSymbolSfx() {
        return derivativeSymbolSfx;
    }

    @Override
    public void setDerivativeSymbolSfx(String derivativeSymbolSfx) {
        this.derivativeSymbolSfx = derivativeSymbolSfx;
    }

    @XmlAttribute(name = "CFI")
    @Override
    public String getDerivativeCFICode() {
        return derivativeCFICode;
    }

    @Override
    public void setDerivativeCFICode(String derivativeCFICode) {
        this.derivativeCFICode = derivativeCFICode;
    }

    @XmlAttribute(name = "Ctry")
    @Override
    public String getDerivativeCountryOfIssue() {
        return derivativeCountryOfIssue;
    }

    @Override
    public void setDerivativeCountryOfIssue(String derivativeCountryOfIssue) {
        this.derivativeCountryOfIssue = derivativeCountryOfIssue;
    }

    @XmlAttribute(name = "Rgstry")
    @Override
    public String getDerivativeInstrRegistry() {
        return derivativeInstrRegistry;
    }

    @Override
    public void setDerivativeInstrRegistry(String derivativeInstrRegistry) {
        this.derivativeInstrRegistry = derivativeInstrRegistry;
    }

    @XmlAttribute(name = "IssDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getDerivativeIssueDate() {
        return derivativeIssueDate;
    }

    @Override
    public void setDerivativeIssueDate(Date derivativeIssueDate) {
        this.derivativeIssueDate = derivativeIssueDate;
    }

    @XmlAttribute(name = "Issr")
    @Override
    public String getDerivativeIssuer() {
        return derivativeIssuer;
    }

    @Override
    public void setDerivativeIssuer(String derivativeIssuer) {
        this.derivativeIssuer = derivativeIssuer;
    }

    @XmlAttribute(name = "Lcl")
    @Override
    public String getDerivativeLocaleOfIssue() {
        return derivativeLocaleOfIssue;
    }

    @Override
    public void setDerivativeLocaleOfIssue(String derivativeLocaleOfIssue) {
        this.derivativeLocaleOfIssue = derivativeLocaleOfIssue;
    }

    @XmlAttribute(name = "MatDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getDerivativeMaturityDate() {
        return derivativeMaturityDate;
    }

    @Override
    public void setDerivativeMaturityDate(Date derivativeMaturityDate) {
        this.derivativeMaturityDate = derivativeMaturityDate;
    }

    @XmlAttribute(name = "MMY")
    @Override
    public String getDerivativeMaturityMonthYear() {
        return derivativeMaturityMonthYear;
    }

    @Override
    public void setDerivativeMaturityMonthYear(String derivativeMaturityMonthYear) {
        this.derivativeMaturityMonthYear = derivativeMaturityMonthYear;
    }

    @XmlAttribute(name = "MatTm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getDerivativeMaturityTime() {
        return derivativeMaturityTime;
    }

    @Override
    public void setDerivativeMaturityTime(Date derivativeMaturityTime) {
        this.derivativeMaturityTime = derivativeMaturityTime;
    }

    @XmlAttribute(name = "OpenCloseSettlFlag")
    @Override
    public String getDerivativeSettleOnOpenFlag() {
        return derivativeSettleOnOpenFlag;
    }

    @Override
    public void setDerivativeSettleOnOpenFlag(String derivativeSettleOnOpenFlag) {
        this.derivativeSettleOnOpenFlag = derivativeSettleOnOpenFlag;
    }

    @XmlAttribute(name = "AsgnMeth")
    @Override
    public InstrmtAssignmentMethod getDerivativeInstrmtAssignmentMethod() {
        return derivativeInstrmtAssignmentMethod;
    }

    @Override
    public void setDerivativeInstrmtAssignmentMethod(InstrmtAssignmentMethod derivativeInstrmtAssignmentMethod) {
        this.derivativeInstrmtAssignmentMethod = derivativeInstrmtAssignmentMethod;
    }

    @XmlAttribute(name = "Status")
    @Override
    public SecurityStatus getDerivativeSecurityStatus() {
        return derivativeSecurityStatus;
    }

    @Override
    public void setDerivativeSecurityStatus(SecurityStatus derivativeSecurityStatus) {
        this.derivativeSecurityStatus = derivativeSecurityStatus;
    }

    @XmlAttribute(name = "OptAt")
    @XmlJavaTypeAdapter(FixCharacterAdapter.class)
    @Override
    public Character getDerivativeOptAttribute() {
        return derivativeOptAttribute;
    }

    @Override
    public void setDerivativeOptAttribute(Character derivativeOptAttribute) {
        this.derivativeOptAttribute = derivativeOptAttribute;
    }

    @XmlAttribute(name = "Prod")
    @Override
    public Product getDerivativeProduct() {
        return derivativeProduct;
    }

    @Override
    public void setDerivativeProduct(Product derivativeProduct) {
        this.derivativeProduct = derivativeProduct;
    }

    @XmlAttribute(name = "ProdCmplx")
    @Override
    public String getDerivativeProductComplex() {
        return derivativeProductComplex;
    }

    @Override
    public void setDerivativeProductComplex(String derivativeProductComplex) {
        this.derivativeProductComplex = derivativeProductComplex;
    }

    @XmlAttribute(name = "SecGrp")
    @Override
    public String getDerivativeSecurityGroup() {
        return derivativeSecurityGroup;
    }

    @Override
    public void setDerivativeSecurityGroup(String derivativeSecurityGroup) {
        this.derivativeSecurityGroup = derivativeSecurityGroup;
    }

    @Override
    public Integer getNoDerivativeSecurityAltID() {
        return noDerivativeSecurityAltID;
    }

    @Override
    public void setNoDerivativeSecurityAltID(Integer noDerivativeSecurityAltID) {
        this.noDerivativeSecurityAltID = noDerivativeSecurityAltID;
        if (noDerivativeSecurityAltID != null) {
            derivativeSecurityAltIDGroups = new DerivativeSecurityAltIDGroup[noDerivativeSecurityAltID.intValue()];
            for (int i = 0; i < derivativeSecurityAltIDGroups.length; i++) {
                derivativeSecurityAltIDGroups[i] = new DerivativeSecurityAltIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public DerivativeSecurityAltIDGroup[] getDerivativeSecurityAltIDGroups() {
        return derivativeSecurityAltIDGroups;
    }

    public void setDerivativeSecurityAltIDGroups(DerivativeSecurityAltIDGroup[] derivativeSecurityAltIDGroups) {
        this.derivativeSecurityAltIDGroups = derivativeSecurityAltIDGroups;
        if (derivativeSecurityAltIDGroups != null) {
            noDerivativeSecurityAltID = new Integer(derivativeSecurityAltIDGroups.length);
        }
    }

    @Override
    public DerivativeSecurityAltIDGroup addDerivativeSecurityAltIDGroup() {       
        DerivativeSecurityAltIDGroup group = new DerivativeSecurityAltIDGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<DerivativeSecurityAltIDGroup> groups = new ArrayList<DerivativeSecurityAltIDGroup>();
        if (derivativeSecurityAltIDGroups != null && derivativeSecurityAltIDGroups.length > 0) {
            groups = new ArrayList<DerivativeSecurityAltIDGroup>(Arrays.asList(derivativeSecurityAltIDGroups));
        }
        groups.add(group);
        derivativeSecurityAltIDGroups = groups.toArray(new DerivativeSecurityAltIDGroup[groups.size()]);
        noDerivativeSecurityAltID = new Integer(derivativeSecurityAltIDGroups.length);
        
        return group;
    }
    
    @Override
    public DerivativeSecurityAltIDGroup deleteDerivativeSecurityAltIDGroup(int index) {
        DerivativeSecurityAltIDGroup result = null;
        if (derivativeSecurityAltIDGroups != null && derivativeSecurityAltIDGroups.length > 0 && derivativeSecurityAltIDGroups.length > index) {
            List<DerivativeSecurityAltIDGroup> groups = new ArrayList<DerivativeSecurityAltIDGroup>(Arrays.asList(derivativeSecurityAltIDGroups));
            result = groups.remove(index);
            derivativeSecurityAltIDGroups = groups.toArray(new DerivativeSecurityAltIDGroup[groups.size()]);
            if (derivativeSecurityAltIDGroups.length > 0) {
                noDerivativeSecurityAltID = new Integer(derivativeSecurityAltIDGroups.length);
            } else {
                derivativeSecurityAltIDGroups = null;
                noDerivativeSecurityAltID = null;
            }
        }
        
        return result;
    }

    @Override
    public int clearDerivativeSecurityAltIDGroups() {
        int result = 0;
        if (derivativeSecurityAltIDGroups != null && derivativeSecurityAltIDGroups.length > 0) {
            result = derivativeSecurityAltIDGroups.length;
            derivativeSecurityAltIDGroups = null;
            noDerivativeSecurityAltID = null;
        }
        
        return result;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getDerivativeSecurityID() {
        return derivativeSecurityID;
    }

    @Override
    public void setDerivativeSecurityID(String derivativeSecurityID) {
        this.derivativeSecurityID = derivativeSecurityID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getDerivativeSecurityIDSource() {
        return derivativeSecurityIDSource;
    }

    @Override
    public void setDerivativeSecurityIDSource(String derivativeSecurityIDSource) {
        this.derivativeSecurityIDSource = derivativeSecurityIDSource;
    }

    @XmlAttribute(name = "SecTyp")
    @Override
    public String getDerivativeSecurityType() {
        return derivativeSecurityType;
    }

    @Override
    public void setDerivativeSecurityType(String derivativeSecurityType) {
        this.derivativeSecurityType = derivativeSecurityType;
    }

    @XmlAttribute(name = "SecSubTyp")
    @Override
    public String getDerivativeSecuritySubType() {
        return derivativeSecuritySubType;
    }

    @Override
    public void setDerivativeSecuritySubType(String derivativeSecuritySubType) {
        this.derivativeSecuritySubType = derivativeSecuritySubType;
    }

    @XmlAttribute(name = "StPrv")
    @Override
    public String getDerivativeStateOrProvinceOfIssue() {
        return derivativeStateOrProvinceOfIssue;
    }

    @Override
    public void setDerivativeStateOrProvinceOfIssue(String derivativeStateOrProvinceOfIssue) {
        this.derivativeStateOrProvinceOfIssue = derivativeStateOrProvinceOfIssue;
    }

    @XmlAttribute(name = "StrkPx")
    @Override
    public Double getDerivativeStrikePrice() {
        return derivativeStrikePrice;
    }

    @Override
    public void setDerivativeStrikePrice(Double derivativeStrikePrice) {
        this.derivativeStrikePrice = derivativeStrikePrice;
    }

    @XmlAttribute(name = "StrkCcy")
    @Override
    public Currency getDerivativeStrikeCurrency() {
        return derivativeStrikeCurrency;
    }

    @Override
    public void setDerivativeStrikeCurrency(Currency derivativeStrikeCurrency) {
        this.derivativeStrikeCurrency = derivativeStrikeCurrency;
    }

    @XmlAttribute(name = "StrkMult")
    @Override
    public Double getDerivativeStrikeMultiplier() {
        return derivativeStrikeMultiplier;
    }

    @Override
    public void setDerivativeStrikeMultiplier(Double derivativeStrikeMultiplier) {
        this.derivativeStrikeMultiplier = derivativeStrikeMultiplier;
    }

    @XmlAttribute(name = "Mult")
    @Override
    public Double getDerivativeContractMultiplier() {
        return derivativeContractMultiplier;
    }

    @Override
    public void setDerivativeContractMultiplier(Double derivativeContractMultiplier) {
        this.derivativeContractMultiplier = derivativeContractMultiplier;
    }
    
    @XmlAttribute(name = "MultTyp")
    @Override
    public ContractMultiplierUnit getDerivativeContractMultiplierUnit() {
        return derivativeContractMultiplierUnit;
    }

    @Override
    public void setDerivativeContractMultiplierUnit(ContractMultiplierUnit derivativeContractMultiplierUnit) {
        this.derivativeContractMultiplierUnit = derivativeContractMultiplierUnit;
    }
    
    @XmlAttribute(name = "FlowSchedTyp")
    @Override
    public Integer getDerivativeFlowScheduleType() {
        return derivativeFlowScheduleType;
    }

    @Override
    public void setDerivativeFlowScheduleType(Integer derivativeFlowScheduleType) {
        this.derivativeFlowScheduleType = derivativeFlowScheduleType;
    }

    @XmlAttribute(name = "MinPxIncr")
    @Override
    public Double getDerivativeMinPriceIncrement() {
        return derivativeMinPriceIncrement;
    }

    @Override
    public void setDerivativeMinPriceIncrement(Double derivativeMinPriceIncrement) {
        this.derivativeMinPriceIncrement = derivativeMinPriceIncrement;
    }

    @XmlAttribute(name = "MinPxIncrAmt")
    @Override
    public Double getDerivativeMinPriceIncrementAmount() {
        return derivativeMinPriceIncrementAmount;
    }

    @Override
    public void setDerivativeMinPriceIncrementAmount(Double derivativeMinPriceIncrementAmount) {
        this.derivativeMinPriceIncrementAmount = derivativeMinPriceIncrementAmount;
    }

    @XmlAttribute(name = "UOM")
    @Override
    public UnitOfMeasure getDerivativeUnitOfMeasure() {
        return derivativeUnitOfMeasure;
    }

    @Override
    public void setDerivativeUnitOfMeasure(UnitOfMeasure derivativeUnitOfMeasure) {
        this.derivativeUnitOfMeasure = derivativeUnitOfMeasure;
    }

    @XmlAttribute(name = "UOMQty")
    @Override
    public Double getDerivativeUnitOfMeasureQty() {
        return derivativeUnitOfMeasureQty;
    }

    @Override
    public void setDerivativeUnitOfMeasureQty(Double derivativeUnitOfMeasureQty) {
        this.derivativeUnitOfMeasureQty = derivativeUnitOfMeasureQty;
    }

    @XmlAttribute(name = "PxUOM")
    @Override
    public PriceUnitOfMeasure getDerivativePriceUnitOfMeasure() {
        return derivativePriceUnitOfMeasure;
    }

    @Override
    public void setDerivativePriceUnitOfMeasure(PriceUnitOfMeasure derivativePriceUnitOfMeasure) {
        this.derivativePriceUnitOfMeasure = derivativePriceUnitOfMeasure;
    }

    @XmlAttribute(name = "PxUOMQty")
    @Override
    public Double getDerivativePriceUnitOfMeasureQty() {
        return derivativePriceUnitOfMeasureQty;
    }

    @Override
    public void setDerivativePriceUnitOfMeasureQty(Double derivativePriceUnitOfMeasureQty) {
        this.derivativePriceUnitOfMeasureQty = derivativePriceUnitOfMeasureQty;
    }

    @XmlAttribute(name = "SettlMeth")
    @Override
    public SettlMethod getDerivativeSettlMethod() {
        return derivativeSettlMethod;
    }

    @Override
    public void setDerivativeSettlMethod(SettlMethod derivativeSettlMethod) {
        this.derivativeSettlMethod = derivativeSettlMethod;
    }

    @XmlAttribute(name = "ExerStyle")
    @Override
    public ExerciseStyle getDerivativeExerciseStyle() {
        return derivativeExerciseStyle;
    }

    @Override
    public void setDerivativeExerciseStyle(ExerciseStyle derivativeExerciseStyle) {
        this.derivativeExerciseStyle = derivativeExerciseStyle;
    }

    @XmlAttribute(name = "OptPayAmt")
    @Override
    public Double getDerivativeOptPayAmount() {
        return derivativeOptPayAmount;
    }

    @Override
    public void setDerivativeOptPayAmount(Double derivativeOptPayAmount) {
        this.derivativeOptPayAmount = derivativeOptPayAmount;
    }

    @XmlAttribute(name = "PxQteMeth")
    @Override
    public PriceQuoteMethod getDerivativePriceQuoteMethod() {
        return derivativePriceQuoteMethod;
    }

    @Override
    public void setDerivativePriceQuoteMethod(PriceQuoteMethod derivativePriceQuoteMethod) {
        this.derivativePriceQuoteMethod = derivativePriceQuoteMethod;
    }

    @XmlAttribute(name = "ValMeth")
    @Override
    public FuturesValuationMethod getDerivativeValuationMethod() {
        return derivativeValuationMethod;
    }

    @Override
    public void setDerivativeValuationMethod(FuturesValuationMethod derivativeValuationMethod) {
        this.derivativeValuationMethod = derivativeValuationMethod;
    }

    @XmlAttribute(name = "ListMeth")
    @Override
    public ListMethod getDerivativeListMethod() {
        return derivativeListMethod;
    }

    @Override
    public void setDerivativeListMethod(ListMethod derivativeListMethod) {
        this.derivativeListMethod = derivativeListMethod;
    }

    @XmlAttribute(name = "CapPx")
    @Override
    public Double getDerivativeCapPrice() {
        return derivativeCapPrice;
    }

    @Override
    public void setDerivativeCapPrice(Double derivativeCapPrice) {
        this.derivativeCapPrice = derivativeCapPrice;
    }

    @XmlAttribute(name = "FlrPx")
    @Override
    public Double getDerivativeFloorPrice() {
        return derivativeFloorPrice;
    }

    @Override
    public void setDerivativeFloorPrice(Double derivativeFloorPrice) {
        this.derivativeFloorPrice = derivativeFloorPrice;
    }

    @XmlAttribute(name = "PutCall")
    @Override
    public PutOrCall getDerivativePutOrCall() {
        return derivativePutOrCall;
    }

    @Override
    public void setDerivativePutOrCall(PutOrCall derivativePutOrCall) {
        this.derivativePutOrCall = derivativePutOrCall;
    }

    @XmlAttribute(name = "FlexProdElig")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getDerivFlexProductEligibilityIndicator() {
        return derivFlexProductEligibilityIndicator;
    }

    @Override
    public void setDerivFlexProductEligibilityIndicator(Boolean derivFlexProductEligibilityIndicator) {
        this.derivFlexProductEligibilityIndicator = derivFlexProductEligibilityIndicator;
    }

    @XmlAttribute(name = "TmUnit")
    @Override
    public TimeUnit getDerivativeTimeUnit() {
        return derivativeTimeUnit;
    }

    @Override
    public void setDerivativeTimeUnit(TimeUnit derivativeTimeUnit) {
        this.derivativeTimeUnit = derivativeTimeUnit;
    }

    @XmlAttribute(name = "Exch")
    @Override
    public String getDerivativeSecurityExchange() {
        return derivativeSecurityExchange;
    }

    @Override
    public void setDerivativeSecurityExchange(String derivativeSecurityExchange) {
        this.derivativeSecurityExchange = derivativeSecurityExchange;
    }

    @XmlAttribute(name = "PosLmt")
    @Override
    public Integer getDerivativePositionLimit() {
        return derivativePositionLimit;
    }

    @Override
    public void setDerivativePositionLimit(Integer derivativePositionLimit) {
        this.derivativePositionLimit = derivativePositionLimit;
    }

    @XmlAttribute(name = "NTPosLmt")
    @Override
    public Integer getDerivativeNTPositionLimit() {
        return derivativeNTPositionLimit;
    }

    @Override
    public void setDerivativeNTPositionLimit(Integer derivativeNTPositionLimit) {
        this.derivativeNTPositionLimit = derivativeNTPositionLimit;
    }

    @XmlAttribute(name = "EncIssr")
    @Override
    public byte[] getDerivativeEncodedIssuer() {
        return derivativeEncodedIssuer;
    }

    @Override
    public void setDerivativeEncodedIssuer(byte[] derivativeEncodedIssuer) {
        this.derivativeEncodedIssuer = derivativeEncodedIssuer;
        if (derivativeEncodedIssuerLen == null) {
            derivativeEncodedIssuerLen = new Integer(derivativeEncodedIssuer.length);
        }
    }

    @XmlAttribute(name = "EncIssrLen")
    @Override
    public Integer getDerivativeEncodedIssuerLen() {
        return derivativeEncodedIssuerLen;
    }

    @Override
    public void setDerivativeEncodedIssuerLen(Integer derivativeEncodedIssuerLen) {
        this.derivativeEncodedIssuerLen = derivativeEncodedIssuerLen;
    }

    @XmlAttribute(name = "Desc")
    @Override
    public String getDerivativeSecurityDesc() {
        return derivativeSecurityDesc;
    }

    @Override
    public void setDerivativeSecurityDesc(String derivativeSecurityDesc) {
        this.derivativeSecurityDesc = derivativeSecurityDesc;
    }

    @XmlAttribute(name = "EncSecDesc")
    @Override
    public byte[] getDerivativeEncodedSecurityDesc() {
        return derivativeEncodedSecurityDesc;
    }

    @Override
    public void setDerivativeEncodedSecurityDesc(byte[] derivativeEncodedSecurityDesc) {
        this.derivativeEncodedSecurityDesc = derivativeEncodedSecurityDesc;
        if (derivativeEncodedSecurityDescLen == null) {
            derivativeEncodedSecurityDescLen = new Integer(derivativeEncodedSecurityDesc.length);
        }
    }

    @XmlAttribute(name = "EncSecDescLen")
    @Override
    public Integer getDerivativeEncodedSecurityDescLen() {
        return derivativeEncodedSecurityDescLen;
    }

    @Override
    public void setDerivativeEncodedSecurityDescLen(Integer derivativeEncodedSecurityDescLen) {
        this.derivativeEncodedSecurityDescLen = derivativeEncodedSecurityDescLen;
    }

    @XmlElementRef
    @Override
    public DerivativeSecurityXML getDerivativeSecurityXML() {
        return derivativeSecurityXML;
    }

    @Override
    public void setDerivativeSecurityXML() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        derivativeSecurityXML = new DerivativeSecurityXML50SP2(context);
    }

    public void setDerivativeSecurityXML(DerivativeSecurityXML derivativeSecurityXML) {
        this.derivativeSecurityXML = derivativeSecurityXML;
    }

    @Override
    public void clearDerivativeSecurityXML() {
        derivativeSecurityXML = null;
    }


    @XmlAttribute(name = "CSetMo")
    @Override
    public String getDerivativeContractSettlMonth() {
        return derivativeContractSettlMonth;
    }

    @Override
    public void setDerivativeContractSettlMonth(String derivativeContractSettlMonth) {
        this.derivativeContractSettlMonth = derivativeContractSettlMonth;
    }

    @Override
    public Integer getNoDerivativeEvents() {
        return noDerivativeEvents;
    }

    @Override
    public void setNoDerivativeEvents(Integer noDerivativeEvents) {
        this.noDerivativeEvents = noDerivativeEvents;
        if (noDerivativeEvents != null) {
            derivativeEvents = new DerivativeEventGroup[noDerivativeEvents.intValue()];
            for (int i = 0; i < derivativeEvents.length; i++) {
                derivativeEvents[i] = new DerivativeEventGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public DerivativeEventGroup[] getDerivativeEvents() {
        return derivativeEvents;
    }

    public void setDerivativeEvents(DerivativeEventGroup[] derivativeEvents) {
        this.derivativeEvents = derivativeEvents;
        if (derivativeEvents != null) {
            noDerivativeEvents = new Integer(derivativeEvents.length);
        }
    }

    @Override
    public DerivativeEventGroup addDerivativeEvent() {
        DerivativeEventGroup group = new DerivativeEventGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<DerivativeEventGroup> groups = new ArrayList<DerivativeEventGroup>();
        if (derivativeEvents != null && derivativeEvents.length > 0) {
            groups = new ArrayList<DerivativeEventGroup>(Arrays.asList(derivativeEvents));
        }
        groups.add(group);
        derivativeEvents = groups.toArray(new DerivativeEventGroup[groups.size()]);
        noDerivativeEvents = new Integer(derivativeEvents.length);

        return group;
    }

    @Override
    public DerivativeEventGroup deleteDerivativeEvent(int index) {
        DerivativeEventGroup result = null;

        if (derivativeEvents != null && derivativeEvents.length > 0 && derivativeEvents.length > index) {
            List<DerivativeEventGroup> groups = new ArrayList<DerivativeEventGroup>(Arrays.asList(derivativeEvents));
            result = groups.remove(index);
            derivativeEvents = groups.toArray(new DerivativeEventGroup[groups.size()]);
            if (derivativeEvents.length > 0) {
                noDerivativeEvents = new Integer(derivativeEvents.length);
            } else {
                derivativeEvents = null;
                noDerivativeEvents = null;
            }
        }

        return result;
    }

    @Override
    public int clearDerivativeEvents() {
        int result = 0;
        if (derivativeEvents != null && derivativeEvents.length > 0) {
            result = derivativeEvents.length;
            derivativeEvents = null;
            noDerivativeEvents = null;
        }

        return result;
    }

    @Override
    public DerivativeInstrumentParties getDerivativeInstrumentParties() {
        return derivativeInstrumentParties;
    }

    @Override
    public void setDerivativeInstrumentParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        derivativeInstrumentParties = new DerivativeInstrumentParties50SP2(context);
    }

    @Override
    public void clearDerivativeInstrumentParties() {
        derivativeInstrumentParties = null;
    }

    @XmlElementRef
    public DerivativeInstrumentPartyGroup[] getDerivativeInstrumentPartyGroups() {
        return derivativeInstrumentParties == null ? null : derivativeInstrumentParties.getDerivativeInstrumentPartyGroups();
    }

    public void setDerivativeInstrumentPartyGroups(DerivativeInstrumentPartyGroup[] instrumentPartyGroups) {
        if (instrumentPartyGroups != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            derivativeInstrumentParties = new DerivativeInstrumentParties50SP2(context);
            derivativeInstrumentParties.setNoDerivativeInstrumentParties(instrumentPartyGroups.length);
            ((DerivativeInstrumentParties50SP2) derivativeInstrumentParties).setDerivativeInstrumentPartyGroups(instrumentPartyGroups);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (noDerivativeSecurityAltID != null && noDerivativeSecurityAltID.intValue() > 0) {
            if (SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                derivativeSecurityAltIDGroups = new DerivativeSecurityAltIDGroup[noDerivativeSecurityAltID.intValue()];
                for (int i = 0; i < noDerivativeSecurityAltID.intValue(); i++) {
                    DerivativeSecurityAltIDGroup group = new DerivativeSecurityAltIDGroup50SP2(context);
                    group.decode(message);
                    derivativeSecurityAltIDGroups[i] = group;
                }
            }
        }
        if (SECURITY_XML_COMP_TAGS.contains(tag.tagNum)) {
            if (derivativeSecurityXML == null) {
                derivativeSecurityXML = new DerivativeSecurityXML50SP2(context);
            }
            derivativeSecurityXML.decode(tag, message);
        }
        if (noDerivativeEvents != null && noDerivativeEvents.intValue() > 0) {
            if (EVENT_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                derivativeEvents = new DerivativeEventGroup[noDerivativeEvents.intValue()];
                for (int i = 0; i < noDerivativeEvents.intValue(); i++) {
                    DerivativeEventGroup group = new DerivativeEventGroup50SP2(context);
                    group.decode(message);
                    derivativeEvents[i] = group;
                }
            }
        }
        if (INSTRUMENT_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (derivativeInstrumentParties == null) {
                derivativeInstrumentParties = new DerivativeInstrumentParties50SP2(context);
            }
            derivativeInstrumentParties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Instrument] component version [5.0SP1].";
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
