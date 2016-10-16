/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument.java
 *
 * $Id: Instrument.java,v 1.13 2011-09-19 08:15:44 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.UnderlyingPriceDeterminationMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.EventGroup;
import net.hades.fix.message.group.SecurityAltIDGroup;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FuturesValuationMethod;
import net.hades.fix.message.type.InstrmtAssignmentMethod;
import net.hades.fix.message.type.ListMethod;
import net.hades.fix.message.type.OptPayoutType;
import net.hades.fix.message.type.PriceQuoteMethod;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.RestructuringType;
import net.hades.fix.message.type.SecurityStatus;
import net.hades.fix.message.type.Seniority;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlMethod;
import net.hades.fix.message.type.StrikePriceBoundaryMethod;
import net.hades.fix.message.type.StrikePriceDeterminationMethod;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;

/**
 * Instrument component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 * @created 26/10/2008, 11:46:24
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Instrument extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Symbol.getValue(),
        TagNum.SymbolSfx.getValue(),
        TagNum.SecurityID.getValue(),
        TagNum.SecurityIDSource.getValue(),
        TagNum.NoSecurityAltID.getValue(),
        TagNum.Product.getValue(),
        TagNum.ProductComplex.getValue(),
        TagNum.SecurityGroup.getValue(),
        TagNum.CFICode.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.SecuritySubType.getValue(),
        TagNum.MaturityMonthYear.getValue(),
        TagNum.MaturityDate.getValue(),
        TagNum.MaturityTime.getValue(),
        TagNum.SettleOnOpenFlag.getValue(),
        TagNum.InstrmtAssignmentMethod.getValue(),
        TagNum.SecurityStatus.getValue(),
        TagNum.CouponPaymentDate.getValue(),
        TagNum.RestructuringType.getValue(),
        TagNum.Seniority.getValue(),
        TagNum.NotionalPercentageOutstanding.getValue(),
        TagNum.OriginalNotionalPercentageOutstanding.getValue(),
        TagNum.AttachmentPoint.getValue(),
        TagNum.DetachmentPoint.getValue(),
        TagNum.IssueDate.getValue(),
        TagNum.RepoCollateralSecurityType.getValue(),
        TagNum.RepurchaseTerm.getValue(),
        TagNum.RepurchaseRate.getValue(),
        TagNum.Factor.getValue(),
        TagNum.CreditRating.getValue(),
        TagNum.InstrRegistry.getValue(),
        TagNum.CountryOfIssue.getValue(),
        TagNum.StateOrProvinceOfIssue.getValue(),
        TagNum.LocaleOfIssue.getValue(),
        TagNum.RedemptionDate.getValue(),
        TagNum.StrikePrice.getValue(),
        TagNum.StrikeCurrency.getValue(),
        TagNum.StrikeMultiplier.getValue(),
        TagNum.StrikeValue.getValue(),
        TagNum.StrikePriceDeterminationMethod.getValue(),
        TagNum.StrikePriceBoundaryMethod.getValue(),
        TagNum.StrikePriceBoundaryPrecision.getValue(),
        TagNum.UnderlyingPriceDeterminationMethod.getValue(),
        TagNum.OptAttribute.getValue(),
        TagNum.ContractMultiplier.getValue(),
        TagNum.ContractMultiplierUnit.getValue(),
        TagNum.FlowScheduleType.getValue(),
        TagNum.MinPriceIncrement.getValue(),
        TagNum.MinPriceIncrementAmount.getValue(),
        TagNum.UnitOfMeasure.getValue(),
        TagNum.UnitOfMeasureQty.getValue(),
        TagNum.PriceUnitOfMeasure.getValue(),
        TagNum.PriceUnitOfMeasureQty.getValue(),
        TagNum.SettlMethod.getValue(),
        TagNum.ExerciseStyle.getValue(),
        TagNum.OptPayoutType.getValue(),
        TagNum.OptPayAmount.getValue(),
        TagNum.PriceQuoteMethod.getValue(),
        TagNum.ValuationMethod.getValue(),
        TagNum.ListMethod.getValue(),
        TagNum.CapPrice.getValue(),
        TagNum.FloorPrice.getValue(),
        TagNum.PutOrCall.getValue(),
        TagNum.FlexibleIndicator.getValue(),
        TagNum.FlexProductEligibilityIndicator.getValue(),
        TagNum.TimeUnit.getValue(),
        TagNum.CouponRate.getValue(),
        TagNum.SecurityExchange.getValue(),
        TagNum.PositionLimit.getValue(),
        TagNum.NTPositionLimit.getValue(),
        TagNum.Issuer.getValue(),
        TagNum.SecurityDesc.getValue(),
        TagNum.Pool.getValue(),
        TagNum.ContractSettlMonth.getValue(),
        TagNum.CPProgram.getValue(),
        TagNum.CPRegType.getValue(),
        TagNum.NoEvents.getValue(),
        TagNum.DatedDate.getValue(),
        TagNum.InterestAccrualDate.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedIssuerLen.getValue(),
        TagNum.EncodedSecurityDescLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Symbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** 
     * TagNum = 55. Starting with 4.3 version.
     */
    protected String symbol;

    /** 
     * TagNum = 65. Starting with 4.3 version.
     */
    protected String symbolSfx;

    /** 
     * TagNum = 48. Starting with 4.3 version.
     */
    protected String securityID;

    /** 
     * TagNum = 22. Starting with 4.3 version.
     */
    protected String securityIDSource;

    /** 
     * TagNum = 454. Starting with 4.3 version.
     */
    protected Integer noSecurityAltID;

    /** 
     * TagNum = 455,456. Starting with 4.3 version.
     */
    protected SecurityAltIDGroup[] securityAltIDGroups;

    /** 
     * TagNum = 460. Starting with 4.3 version.
     */
    protected Product product;

    /**
     * TagNum = 1227. Starting with 5.0SP1 version.
     */
    protected String productComplex;

     /**
     * TagNum = 1151. Starting with 5.0SP1 version.
     */
    protected String securityGroup;

    /** 
     * TagNum = 461. Starting with 4.3 version.
     */
    protected String cfiCode;

    /** 
     * TagNum = 167. Starting with 4.3 version.
     */
    protected String securityType;

    /**
     * TagNum = 762. Starting with 4.4 version.
     */
    protected String securitySubType;

    /** 
     * TagNum = 200. Starting with 4.3 version.
     */
    protected String maturityMonthYear;

    /** 
     * TagNum = 541. Starting with 4.3 version.
     */
    protected Date maturityDate;

    /**
     * TagNum = 1079. Starting with 5.0 version.
     */
    protected Date maturityTime;

    /**
     * TagNum = 966. Starting with 5.0 version.
     */
    protected String settleOnOpenFlag;

    /**
     * TagNum = 1049. Starting with 5.0 version.
     */
    protected InstrmtAssignmentMethod instrmtAssignmentMethod;

    /**
     * TagNum = 965. Starting with 5.0 version.
     */
    protected SecurityStatus securityStatus;

    /** 
     * TagNum = 224. Starting with 4.3 version.
     */
    protected Date couponPaymentDate;

    /**
     * TagNum = 1449. Starting with 5.0SP2 version.
     */
    protected RestructuringType restructuringType;

    /**
     * TagNum = 1450. Starting with 5.0SP2 version.
     */
    protected Seniority seniority;

    /**
     * TagNum = 1451. Starting with 5.0SP2 version.
     */
    protected Double notionalPercentageOutstanding;

    /**
     * TagNum = 1452. Starting with 5.0SP2 version.
     */
    protected Double originalNotionalPercentageOutstanding;

    /**
     * TagNum = 1457. Starting with 5.0SP2 version.
     */
    protected Double attachmentPoint;

    /**
     * TagNum = 1458. Starting with 5.0SP2 version.
     */
    protected Double detachmentPoint;
    /** 
     * TagNum = 225. Starting with 4.3 version.
     */
    protected Date issueDate;

    /** 
     * TagNum = 239. Starting with 4.3 version.
     */
    protected String repoCollateralSecurityType;

    /** 
     * TagNum = 226. Starting with 4.3 version.
     */
    protected Integer repurchaseTerm;

    /** 
     * TagNum = 227. Starting with 4.3 version.
     */
    protected Double repurchaseRate;

    /** 
     * TagNum = 228. Starting with 4.3 version.
     */
    protected Double factor;

    /** 
     * TagNum = 255. Starting with 4.3 version.
     */
    protected String creditRating;

    /** 
     * TagNum = 543. Starting with 4.3 version.
     */
    protected String instrRegistry;

    /** 
     * TagNum = 470. Starting with 4.3 version.
     */
    protected String countryOfIssue;

    /** 
     * TagNum = 471. Starting with 4.3 version.
     */
    protected String stateOrProvinceOfIssue;

    /** 
     * TagNum = 472. Starting with 4.3 version.
     */
    protected String localeOfIssue;

    /** 
     * TagNum = 240. Starting with 4.3 version.
     */
    protected Date redemptionDate;

    /** 
     * TagNum = 202. Starting with 4.1 version.
     */
    protected Double strikePrice;

    /**
     * TagNum = 947. Starting with 4.4 version.
     */
    protected Currency strikeCurrency;

    /**
     * TagNum = 967. Starting with 5.0 version.
     */
    protected Double strikeMultiplier;

    /**
     * TagNum = 968. Starting with 5.0 version.
     */
    protected Double strikeValue;

    /**
     * TagNum = 1478. Starting with 5.0SP2 version.
     */
    protected StrikePriceDeterminationMethod strikePriceDeterminationMethod;

    /**
     * TagNum = 1479. Starting with 5.0SP2 version.
     */
    protected StrikePriceBoundaryMethod strikePriceBoundaryMethod;

    /**
     * TagNum = 1480. Starting with 5.0SP2 version.
     */
    protected Double strikePriceBoundaryPrecision;

    /**
     * TagNum = 1481. Starting with 5.0SP2 version.
     */
    protected UnderlyingPriceDeterminationMethod underlyingPriceDeterminationMethod;

    /** 
     * TagNum = 206. Starting with 4.1 version.
     */
    protected Character optAttribute;

    /** 
     * TagNum = 231. Starting with 4.2 version.
     */
    protected Double contractMultiplier;

    /**
     * TagNum = 1435. Starting with 5.0SP2 version.
     */
    protected ContractMultiplierUnit contractMultiplierUnit;

    /**
     * TagNum = 1439. Starting with 5.0SP2 version.
     */
    protected Integer flowScheduleType;

    /**
     * TagNum = 969. Starting with 5.0 version.
     */
    protected Double minPriceIncrement;

    /**
     * TagNum = 1146. Starting with 5.0SP1 version.
     */
    protected Double minPriceIncrementAmount;

    /**
     * TagNum = 996. Starting with 5.0 version.
     */
    protected UnitOfMeasure unitOfMeasure;

    /**
     * TagNum = 1147. Starting with 5.0SP1 version.
     */
    protected Double unitOfMeasureQty;

    /**
     * TagNum = 1191. Starting with 5.0SP1 version.
     */
    protected PriceUnitOfMeasure priceUnitOfMeasure;

    /**
     * TagNum = 1192. Starting with 5.0SP1 version.
     */
    protected Double priceUnitOfMeasureQty;

    /**
     * TagNum = 1193. Starting with 5.0SP1 version.
     */
    protected SettlMethod settlMethod;

    /**
     * TagNum = 1194. Starting with 5.0SP1 version.
     */
    protected ExerciseStyle exerciseStyle;

    /**
     * TagNum = 1482. Starting with 5.0SP2 version.
     */
    protected OptPayoutType optPayoutType;

    /**
     * TagNum = 1195. Starting with 5.0SP1 version.
     */
    protected Double optPayAmount;

    /**
     * TagNum = 1196. Starting with 5.0SP1 version.
     */
    protected PriceQuoteMethod priceQuoteMethod;

    /**
     * TagNum = 1197. Starting with 5.0SP1 version.
     */
    protected FuturesValuationMethod futuresValuationMethod;

    /**
     * TagNum = 1198. Starting with 5.0SP1 version.
     */
    protected ListMethod listMethod;

    /**
     * TagNum = 1199. Starting with 5.0SP1 version.
     */
    protected Double capPrice;

    /**
     * TagNum = 1200. Starting with 5.0SP1 version.
     */
    protected Double floorPrice;

    /**
     * TagNum = 201. Starting with 5.0SP1 version.
     */
    protected PutOrCall putOrCall;

    /**
     * TagNum = 1244. Starting with 5.0SP1 version.
     */
    protected Boolean flexibleIndicator;

    /**
     * TagNum = 1242. Starting with 5.0SP1 version.
     */
    protected Boolean flexProductEligibilityIndicator;

    /**
     * TagNum = 997. Starting with 5.0 version.
     */
    protected TimeUnit timeUnit;

    /** 
     * TagNum = 223. Starting with 4.2 version.
     */
    protected Double couponRate;

    /** 
     * TagNum = 207. Starting with 4.1 version.
     */
    protected String securityExchange;

    /**
     * TagNum = 970. Starting with 5.0 version.
     */
    protected Integer positionLimit;

    /**
     * TagNum = 971. Starting with 5.0 version.
     */
    protected Integer ntPositionLimit;

    /** 
     * TagNum = 106. Starting with 4.0 version.
     */
    protected String issuer;

    /** 
     * TagNum = 348. Starting with 4.2 version.
     */
    protected Integer encodedIssuerLen;

    /** 
     * TagNum = 349. Starting with 4.2 version.
     */
    protected byte[] encodedIssuer;

    /** 
     * TagNum = 107. Starting with 4.0 version.
     */
    protected String securityDesc;

    /** 
     * TagNum = 350. Starting with 4.2 version.
     */
    protected Integer encodedSecurityDescLen;

    /** 
     * TagNum = 351. Starting with 4.2 version.
     */
    protected byte[] encodedSecurityDesc;

    /**
     * Starting with 5.0SP1 version.
     */
    protected SecurityXML securityXML;

    /**
     * TagNum = 691. Starting with 4.4 version.
     */
    protected String pool;

    /**
     * TagNum = 667. Starting with 4.4 version.
     */
    protected String contractSettlMonth;

    /**
     * TagNum = 875. Starting with 4.4 version.
     */
    protected Integer cpProgram;

    /**
     * TagNum = 876. Starting with 4.4 version.
     */
    protected String cpRegType;

    /**
     * TagNum = 864. Starting with 4.4 version.
     */
    protected Integer noEvents;

    /**
     * Starting with 4.4 version.
     */
    protected EventGroup[] events;

    /**
     * TagNum = 873. Starting with 4.4 version.
     */
    protected Date datedDate;

    /**
     * TagNum = 874. Starting with 4.4 version.
     */
    protected Date interestAccrualDate;

    /**
     * Starting with 5.0 version.
     */
    protected InstrumentParties instrumentParties;

    /**
     * Starting with 5.0SP2 version.
     */
    protected ComplexEvents complexEvents;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Instrument() {
    }

    public Instrument(FragmentContext context) {
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
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CFICode)
    public String getCfiCode() {
        return cfiCode;
    }

    /**
     * Message field setter.
     * @param cfiCode field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CFICode)
    public void setCfiCode(String cfiCode) {
        this.cfiCode = cfiCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public Double getContractMultiplier() {
        return contractMultiplier;
    }

    /**
     * Message field setter.
     * @param contractMultiplier field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public void setContractMultiplier(Double contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ContractMultiplierUnit)
    public ContractMultiplierUnit getContractMultiplierUnit() {
        return contractMultiplierUnit;
    }

    /**
     * Message field setter.
     * @param contractMultiplierUnit field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.ContractMultiplierUnit)
    public void setContractMultiplierUnit(ContractMultiplierUnit contractMultiplierUnit) {
        this.contractMultiplierUnit = contractMultiplierUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.FlowScheduleType)
    public Integer getFlowScheduleType() {
        return flowScheduleType;
    }

    /**
     * Message field setter.
     * @param flowScheduleType field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.FlowScheduleType)
    public void setFlowScheduleType(Integer flowScheduleType) {
        this.flowScheduleType = flowScheduleType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CountryOfIssue)
    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    /**
     * Message field setter.
     * @param countryOfIssue field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CountryOfIssue)
    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CouponPaymentDate)
    public Date getCouponPaymentDate() {
        return couponPaymentDate;
    }

    /**
     * Message field setter.
     * @param couponPaymentDate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CouponPaymentDate)
    public void setCouponPaymentDate(Date couponPaymentDate) {
        this.couponPaymentDate = couponPaymentDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.RestructuringType)
    public RestructuringType getRestructuringType() {
        return restructuringType;
    }

    /**
     * Message field setter.
     * @param restructuringType field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.RestructuringType)
    public void setRestructuringType(RestructuringType restructuringType) {
        this.restructuringType = restructuringType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.Seniority)
    public Seniority getSeniority() {
        return seniority;
    }

    /**
     * Message field setter.
     * @param seniority field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.Seniority)
    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NotionalPercentageOutstanding)
    public Double getNotionalPercentageOutstanding() {
        return notionalPercentageOutstanding;
    }

    /**
     * Message field setter.
     * @param notionalPercentageOutstanding field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NotionalPercentageOutstanding)
    public void setNotionalPercentageOutstanding(Double notionalPercentageOutstanding) {
        this.notionalPercentageOutstanding = notionalPercentageOutstanding;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.OriginalNotionalPercentageOutstanding)
    public Double getOriginalNotionalPercentageOutstanding() {
        return originalNotionalPercentageOutstanding;
    }

    /**
     * Message field setter.
     * @param originalNotionalPercentageOutstanding field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.OriginalNotionalPercentageOutstanding)
    public void setOriginalNotionalPercentageOutstanding(Double originalNotionalPercentageOutstanding) {
        this.originalNotionalPercentageOutstanding = originalNotionalPercentageOutstanding;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.AttachmentPoint)
    public Double getAttachmentPoint() {
        return attachmentPoint;
    }

    /**
     * Message field setter.
     * @param attachmentPoint field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.AttachmentPoint)
    public void setAttachmentPoint(Double attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.DetachmentPoint)
    public Double getDetachmentPoint() {
        return detachmentPoint;
    }

    /**
     * Message field setter.
     * @param detachmentPoint field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.DetachmentPoint)
    public void setDetachmentPoint(Double detachmentPoint) {
        this.detachmentPoint = detachmentPoint;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public Double getCouponRate() {
        return couponRate;
    }

    /**
     * Message field setter.
     * @param couponRate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CreditRating)
    public String getCreditRating() {
        return creditRating;
    }

    /**
     * Message field setter.
     * @param creditRating field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.CreditRating)
    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public byte[] getEncodedIssuer() {
        return encodedIssuer;
    }

    /**
     * Message field setter.
     * @param encodedIssuer field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public void setEncodedIssuer(byte[] encodedIssuer) {
        this.encodedIssuer = encodedIssuer;
        if (encodedIssuerLen == null) {
            encodedIssuerLen = new Integer(encodedIssuer.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public Integer getEncodedIssuerLen() {
        return encodedIssuerLen;
    }

    /**
     * Message field setter.
     * @param encodedIssuerLen field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        this.encodedIssuerLen = encodedIssuerLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public byte[] getEncodedSecurityDesc() {
        return encodedSecurityDesc;
    }

    /**
     * Message field setter.
     * @param encodedSecurityDesc field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        this.encodedSecurityDesc = encodedSecurityDesc;
        if (encodedSecurityDescLen == null) {
            encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public Integer getEncodedSecurityDescLen() {
        return encodedSecurityDescLen;
    }

    /**
     * Message field setter.
     * @param encodedSecurityDescLen field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        this.encodedSecurityDescLen = encodedSecurityDescLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Factor)
    public Double getFactor() {
        return factor;
    }

    /**
     * Message field setter.
     * @param factor field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Factor)
    public void setFactor(Double factor) {
        this.factor = factor;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.InstrRegistry)
    public String getInstrRegistry() {
        return instrRegistry;
    }

    /**
     * Message field setter.
     * @param instrRegistry field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.InstrRegistry)
    public void setInstrRegistry(String instrRegistry) {
        this.instrRegistry = instrRegistry;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.IssueDate)
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * Message field setter.
     * @param issueDate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.IssueDate)
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public String getIssuer() {
        return issuer;
    }

    /**
     * Message field setter.
     * @param issuer field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LocaleOfIssue)
    public String getLocaleOfIssue() {
        return localeOfIssue;
    }

    /**
     * Message field setter.
     * @param localeOfIssue field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LocaleOfIssue)
    public void setLocaleOfIssue(String localeOfIssue) {
        this.localeOfIssue = localeOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDate)
    public Date getMaturityDate() {
        return maturityDate;
    }

    /**
     * Message field setter.
     * @param maturityDate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDate)
    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MaturityTime)
    public Date getMaturityTime() {
        return maturityTime;
    }

    /**
     * Message field setter.
     * @param maturityTime field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MaturityTime)
    public void setMaturityTime(Date maturityTime) {
        this.maturityTime = maturityTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SettleOnOpenFlag)
    public String getSettleOnOpenFlag() {
        return settleOnOpenFlag;
    }

    /**
     * Message field setter.
     * @param settleOnOpenFlag field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SettleOnOpenFlag)
    public void setSettleOnOpenFlag(String settleOnOpenFlag) {
        this.settleOnOpenFlag = settleOnOpenFlag;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrmtAssignmentMethod)
    public InstrmtAssignmentMethod getInstrmtAssignmentMethod() {
        return instrmtAssignmentMethod;
    }

    /**
     * Message field setter.
     * @param instrmtAssignmentMethod field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrmtAssignmentMethod)
    public void setInstrmtAssignmentMethod(InstrmtAssignmentMethod instrmtAssignmentMethod) {
        this.instrmtAssignmentMethod = instrmtAssignmentMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SecurityStatus)
    public SecurityStatus getSecurityStatus() {
        return securityStatus;
    }

    /**
     * Message field setter.
     * @param securityStatus field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.SecurityStatus)
    public void setSecurityStatus(SecurityStatus securityStatus) {
        this.securityStatus = securityStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public String getMaturityMonthYear() {
        return maturityMonthYear;
    }

    /**
     * Message field setter.
     * @param maturityMonthYear field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public void setMaturityMonthYear(String maturityMonthYear) {
        this.maturityMonthYear = maturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public Character getOptAttribute() {
        return optAttribute;
    }

    /**
     * Message field setter.
     * @param optAttribute field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Product)
    public Product getProduct() {
        return product;
    }

    /**
     * Message field setter.
     * @param product field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Product)
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ProductComplex)
    public String getProductComplex() {
        return productComplex;
    }

    /**
     * Message field setter.
     * @param productComplex field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ProductComplex)
    public void setProductComplex(String productComplex) {
        this.productComplex = productComplex;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityGroup)
    public String getSecurityGroup() {
        return securityGroup;
    }

    /**
     * Message field setter.
     * @param securityGroup field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SecurityGroup)
    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RedemptionDate)
    public Date getRedemptionDate() {
        return redemptionDate;
    }

    /**
     * Message field setter.
     * @param redemptionDate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RedemptionDate)
    public void setRedemptionDate(Date redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RepoCollateralSecurityType)
    public String getRepoCollateralSecurityType() {
        return repoCollateralSecurityType;
    }

    /**
     * Message field setter.
     * @param repoCollateralSecurityType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RepoCollateralSecurityType)
    public void setRepoCollateralSecurityType(String repoCollateralSecurityType) {
        this.repoCollateralSecurityType = repoCollateralSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RepurchaseRate)
    public Double getRepurchaseRate() {
        return repurchaseRate;
    }

    /**
     * Message field setter.
     * @param repurchaseRate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RepurchaseRate)
    public void setRepurchaseRate(Double repurchaseRate) {
        this.repurchaseRate = repurchaseRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RepurchaseTerm)
    public Integer getRepurchaseTerm() {
        return repurchaseTerm;
    }

    /**
     * Message field setter.
     * @param repurchaseTerm field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.RepurchaseTerm)
    public void setRepurchaseTerm(Integer repurchaseTerm) {
        this.repurchaseTerm = repurchaseTerm;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NoSecurityAltID)
    public Integer getNoSecurityAltID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of SecurityAltIDGroup groups. It will also create an array
     * of SecurityAltIDGroup objects and set the <code>securityAltIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>securityAltIDGroups</code> array they will be discarded.<br/>
     * @param noSecurityAltID number of MsgTypeGroup objects
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NoSecurityAltID)
    public void setNoSecurityAltID(Integer noSecurityAltID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for <code>SecurityAltIDGroup</code> array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.3")
    public SecurityAltIDGroup[] getSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a  <code>SecurityAltIDGroup</code> object to the existing array of <code>securityAltIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSecurityAltID</code> field to the proper value.<br/>
     * Note: If the <code>setNoSecurityAltID</code> method has been called there will already be a number of objects in the
     * <code>securityAltIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.3")
    public SecurityAltIDGroup addSecurityAltIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a  <code>SecurityAltIDGroup</code> object from the existing array of <code>securityAltIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSecurityAltID</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.3")
    public SecurityAltIDGroup deleteSecurityAltIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the <code>SecurityAltIDGroup</code> objects from the <code>securityAltIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSecurityAltID</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3")
    public int clearSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public String getSecurityDesc() {
        return securityDesc;
    }

    /**
     * Message field setter.
     * @param securityDesc field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public String getSecurityExchange() {
        return securityExchange;
    }

    /**
     * Message field setter.
     * @param securityExchange field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public void setSecurityExchange(String securityExchange) {
        this.securityExchange = securityExchange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public String getSecurityID() {
        return securityID;
    }

    /**
     * Message field setter.
     * @param securityID field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public String getSecurityIDSource() {
        return securityIDSource;
    }

    /**
     * Message field setter.
     * @param securityIDSource field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return securityType;
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SecuritySubType)
    public String getSecuritySubType() {
        return securitySubType;
    }

    /**
     * Message field setter.
     * @param securitySubType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SecuritySubType)
    public void setSecuritySubType(String securitySubType) {
        this.securitySubType = securitySubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.StateOrProvinceOfIssue)
    public String getStateOrProvinceOfIssue() {
        return stateOrProvinceOfIssue;
    }

    /**
     * Message field setter.
     * @param stateOrProvinceOfIssue field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.StateOrProvinceOfIssue)
    public void setStateOrProvinceOfIssue(String stateOrProvinceOfIssue) {
        this.stateOrProvinceOfIssue = stateOrProvinceOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public Double getStrikePrice() {
        return strikePrice;
    }

    /**
     * Message field setter.
     * @param strikePrice field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StrikeCurrency)
    public Currency getStrikeCurrency() {
        return strikeCurrency;
    }

    /**
     * Message field setter.
     * @param strikeCurrency field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StrikeCurrency)
    public void setStrikeCurrency(Currency strikeCurrency) {
        this.strikeCurrency = strikeCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrikeMultiplier)
    public Double getStrikeMultiplier() {
        return strikeMultiplier;
    }

    /**
     * Message field setter.
     * @param strikeMultiplier field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrikeMultiplier)
    public void setStrikeMultiplier(Double strikeMultiplier) {
        this.strikeMultiplier = strikeMultiplier;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrikeValue)
    public Double getStrikeValue() {
        return strikeValue;
    }

    /**
     * Message field setter.
     * @param strikeValue field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.StrikeValue)
    public void setStrikeValue(Double strikeValue) {
        this.strikeValue = strikeValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.StrikePriceDeterminationMethod)
    public StrikePriceDeterminationMethod getStrikePriceDeterminationMethod() {
        return strikePriceDeterminationMethod;
    }

    /**
     * Message field setter.
     * @param strikePriceDeterminationMethod field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.StrikePriceDeterminationMethod)
    public void setStrikePriceDeterminationMethod(StrikePriceDeterminationMethod strikePriceDeterminationMethod) {
        this.strikePriceDeterminationMethod = strikePriceDeterminationMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.StrikePriceBoundaryMethod)
    public StrikePriceBoundaryMethod getStrikePriceBoundaryMethod() {
        return strikePriceBoundaryMethod;
    }

    /**
     * Message field setter.
     * @param strikePriceBoundaryMethod field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.StrikePriceBoundaryMethod)
    public void setStrikePriceBoundaryMethod(StrikePriceBoundaryMethod strikePriceBoundaryMethod) {
        this.strikePriceBoundaryMethod = strikePriceBoundaryMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.StrikePriceBoundaryPrecision)
    public Double getStrikePriceBoundaryPrecision() {
        return strikePriceBoundaryPrecision;
    }

    /**
     * Message field setter.
     * @param strikePriceBoundaryPrecision field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.StrikePriceBoundaryPrecision)
    public void setStrikePriceBoundaryPrecision(Double strikePriceBoundaryPrecision) {
        this.strikePriceBoundaryPrecision = strikePriceBoundaryPrecision;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.UnderlyingPriceDeterminationMethod)
    public UnderlyingPriceDeterminationMethod getUnderlyingPriceDeterminationMethod() {
        return underlyingPriceDeterminationMethod;
    }

    /**
     * Message field setter.
     * @param underlyingPriceDeterminationMethod field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.UnderlyingPriceDeterminationMethod)
    public void setUnderlyingPriceDeterminationMethod(UnderlyingPriceDeterminationMethod underlyingPriceDeterminationMethod) {
        this.underlyingPriceDeterminationMethod = underlyingPriceDeterminationMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MinPriceIncrement)
    public Double getMinPriceIncrement() {
        return minPriceIncrement;
    }

    /**
     * Message field setter.
     * @param minPriceIncrement field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MinPriceIncrement)
    public void setMinPriceIncrement(Double minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MinPriceIncrementAmount)
    public Double getMinPriceIncrementAmount() {
        return minPriceIncrementAmount;
    }

    /**
     * Message field setter.
     * @param minPriceIncrementAmount field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MinPriceIncrementAmount)
    public void setMinPriceIncrementAmount(Double minPriceIncrementAmount) {
        this.minPriceIncrementAmount = minPriceIncrementAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnitOfMeasure)
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Message field setter.
     * @param unitOfMeasure field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.UnitOfMeasure)
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.UnitOfMeasureQty)
    public Double getUnitOfMeasureQty() {
        return unitOfMeasureQty;
    }

    /**
     * Message field setter.
     * @param unitOfMeasureQty field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.UnitOfMeasureQty)
    public void setUnitOfMeasureQty(Double unitOfMeasureQty) {
        this.unitOfMeasureQty = unitOfMeasureQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceUnitOfMeasure)
    public PriceUnitOfMeasure getPriceUnitOfMeasure() {
        return priceUnitOfMeasure;
    }

    /**
     * Message field setter.
     * @param priceUnitOfMeasure field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceUnitOfMeasure)
    public void setPriceUnitOfMeasure(PriceUnitOfMeasure priceUnitOfMeasure) {
        this.priceUnitOfMeasure = priceUnitOfMeasure;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceUnitOfMeasureQty)
    public Double getPriceUnitOfMeasureQty() {
        return priceUnitOfMeasureQty;
    }

    /**
     * Message field setter.
     * @param priceUnitOfMeasureQty field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceUnitOfMeasureQty)
    public void setPriceUnitOfMeasureQty(Double priceUnitOfMeasureQty) {
        this.priceUnitOfMeasureQty = priceUnitOfMeasureQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SettlMethod)
    public SettlMethod getSettlMethod() {
        return settlMethod;
    }

    /**
     * Message field setter.
     * @param settlMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.SettlMethod)
    public void setSettlMethod(SettlMethod settlMethod) {
        this.settlMethod = settlMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ExerciseStyle)
    public ExerciseStyle getExerciseStyle() {
        return exerciseStyle;
    }

    /**
     * Message field setter.
     * @param exerciseStyle field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ExerciseStyle)
    public void setExerciseStyle(ExerciseStyle exerciseStyle) {
        this.exerciseStyle = exerciseStyle;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.OptPayoutType)
    public OptPayoutType getOptPayoutType() {
        return optPayoutType;
    }

    /**
     * Message field setter.
     * @param optPayoutType field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.OptPayoutType)
    public void setOptPayoutType(OptPayoutType optPayoutType) {
        this.optPayoutType = optPayoutType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.OptPayAmount)
    public Double getOptPayAmount() {
        return optPayAmount;
    }

    /**
     * Message field setter.
     * @param optPayAmount field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.OptPayAmount)
    public void setOptPayAmount(Double optPayAmount) {
        this.optPayAmount = optPayAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceQuoteMethod)
    public PriceQuoteMethod getPriceQuoteMethod() {
        return priceQuoteMethod;
    }

    /**
     * Message field setter.
     * @param priceQuoteMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceQuoteMethod)
    public void setPriceQuoteMethod(PriceQuoteMethod priceQuoteMethod) {
        this.priceQuoteMethod = priceQuoteMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ValuationMethod)
    public FuturesValuationMethod getFuturesValuationMethod() {
        return futuresValuationMethod;
    }

    /**
     * Message field setter.
     * @param futuresValuationMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ValuationMethod)
    public void setFuturesValuationMethod(FuturesValuationMethod futuresValuationMethod) {
        this.futuresValuationMethod = futuresValuationMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ListMethod)
    public ListMethod getListMethod() {
        return listMethod;
    }

    /**
     * Message field setter.
     * @param listMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ListMethod)
    public void setListMethod(ListMethod listMethod) {
        this.listMethod = listMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.CapPrice)
    public Double getCapPrice() {
        return capPrice;
    }

    /**
     * Message field setter.
     * @param capPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.CapPrice)
    public void setCapPrice(Double capPrice) {
        this.capPrice = capPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FloorPrice)
    public Double getFloorPrice() {
        return floorPrice;
    }

    /**
     * Message field setter.
     * @param floorPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FloorPrice)
    public void setFloorPrice(Double floorPrice) {
        this.floorPrice = floorPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public PutOrCall getPutOrCall() {
        return putOrCall;
    }

    /**
     * Message field setter.
     * @param putOrCall field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public void setPutOrCall(PutOrCall putOrCall) {
        this.putOrCall = putOrCall;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FlexibleIndicator)
    public Boolean getFlexibleIndicator() {
        return flexibleIndicator;
    }

    /**
     * Message field setter.
     * @param flexibleIndicator field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FlexibleIndicator)
    public void setFlexibleIndicator(Boolean flexibleIndicator) {
        this.flexibleIndicator = flexibleIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FlexProductEligibilityIndicator)
    public Boolean getFlexProductEligibilityIndicator() {
        return flexProductEligibilityIndicator;
    }

    /**
     * Message field setter.
     * @param flexProductEligibilityIndicator field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.FlexProductEligibilityIndicator)
    public void setFlexProductEligibilityIndicator(Boolean flexProductEligibilityIndicator) {
        this.flexProductEligibilityIndicator = flexProductEligibilityIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TimeUnit)
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Message field setter.
     * @param timeUnit field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TimeUnit)
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PositionLimit)
    public Integer getPositionLimit() {
        return positionLimit;
    }

    /**
     * Message field setter.
     * @param positionLimit field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.PositionLimit)
    public void setPositionLimit(Integer positionLimit) {
        this.positionLimit = positionLimit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NTPositionLimit)
    public Integer getNtPositionLimit() {
        return ntPositionLimit;
    }

    /**
     * Message field setter.
     * @param ntPositionLimit field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NTPositionLimit)
    public void setNtPositionLimit(Integer ntPositionLimit) {
        this.ntPositionLimit = ntPositionLimit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
    public String getSymbol() {
        return symbol;
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public String getSymbolSfx() {
        return symbolSfx;
    }

    /**
     * Message field setter.
     * @param symbolSfx field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public SecurityXML getSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SecurityXML component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SecurityXML component to null.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Pool)
    public String getPool() {
        return pool;
    }

    /**
     * Message field setter.
     * @param pool field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Pool)
    public void setPool(String pool) {
        this.pool = pool;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.ContractSettlMonth)
    public String getContractSettlMonth() {
        return contractSettlMonth;
    }

    /**
     * Message field setter.
     * @param contractSettlMonth field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.ContractSettlMonth)
    public void setContractSettlMonth(String contractSettlMonth) {
        this.contractSettlMonth = contractSettlMonth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CPProgram)
    public Integer getCpProgram() {
        return cpProgram;
    }

    /**
     * Message field setter.
     * @param cpProgram field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CPProgram)
    public void setCpProgram(Integer cpProgram) {
        this.cpProgram = cpProgram;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CPRegType)
    public String getCpRegType() {
        return cpRegType;
    }

    /**
     * Message field setter.
     * @param cpRegType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CPRegType)
    public void setCpRegType(String cpRegType) {
        this.cpRegType = cpRegType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoEvents)
    public Integer getNoEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param noEvents field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoEvents)
    public void setNoEvents(Integer noEvents) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link EventGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public EventGroup[] getEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link EventGroup} object to the existing array of <code>events</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noEvents</code> field to the proper value.<br/>
     * Note: If the <code>setNoEvents</code> method has been called there will already be a number of objects in the
     * <code>events</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public EventGroup addEvent() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link EventGroup} object from the existing array of <code>events</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noEvents</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public EventGroup deleteEvent(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link EventGroup} objects from the <code>events</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noEvents</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DatedDate)
    public Date getDatedDate() {
        return datedDate;
    }

    /**
     * Message field setter.
     * @param datedDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DatedDate)
    public void setDatedDate(Date datedDate) {
        this.datedDate = datedDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.InterestAccrualDate)
    public Date getInterestAccrualDate() {
        return interestAccrualDate;
    }

    /**
     * Message field setter.
     * @param interestAccrualDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.InterestAccrualDate)
    public void setInterestAccrualDate(Date interestAccrualDate) {
        this.interestAccrualDate = interestAccrualDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    public InstrumentParties getInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the InstrumentParties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced = "5.0")
    public void setInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the InstrumentParties component to null.
     */
    @FIXVersion(introduced = "5.0")
    public void clearInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    public ComplexEvents getComplexEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ComplexEvents component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void setComplexEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ComplexEvents component to null.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void clearComplexEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.Symbol.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (symbol == null) {
            errorMsg.append(" [Symbol]");
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
            TagEncoder.encode(bao, TagNum.Symbol, symbol, sessionCharset);
            TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx, sessionCharset);
            TagEncoder.encode(bao, TagNum.SecurityID, securityID, sessionCharset);
            TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource, sessionCharset);
            if (noSecurityAltID != null && noSecurityAltID.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoSecurityAltID, noSecurityAltID);
                if (securityAltIDGroups != null && securityAltIDGroups.length == noSecurityAltID.intValue()) {
                    for (int i = 0; i < noSecurityAltID.intValue(); i++) {
                        if (securityAltIDGroups[i] != null) {
                            bao.write(securityAltIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SecurityAltIDGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoSecurityAltID.getValue(), error);
                }
            }
            if (product != null) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            TagEncoder.encode(bao, TagNum.ProductComplex, productComplex, sessionCharset);
            TagEncoder.encode(bao, TagNum.SecurityGroup, securityGroup, sessionCharset);
            TagEncoder.encode(bao, TagNum.CFICode, cfiCode, sessionCharset);
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.SecuritySubType, securitySubType, sessionCharset);
            TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear, sessionCharset);
            TagEncoder.encodeDate(bao, TagNum.MaturityDate, maturityDate);
            TagEncoder.encodeTZTime(bao, TagNum.MaturityTime, maturityTime);
            TagEncoder.encode(bao, TagNum.SettleOnOpenFlag, settleOnOpenFlag, sessionCharset);
            if (instrmtAssignmentMethod != null) {
                TagEncoder.encode(bao, TagNum.InstrmtAssignmentMethod, instrmtAssignmentMethod.getValue());
            }
            if (securityStatus != null) {
                TagEncoder.encode(bao, TagNum.SecurityStatus, securityStatus.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.CouponPaymentDate, couponPaymentDate);
            if (restructuringType != null) {
                TagEncoder.encode(bao, TagNum.RestructuringType, restructuringType.getValue());
            }
            if (seniority != null) {
                TagEncoder.encode(bao, TagNum.Seniority, seniority.getValue());
            }
            TagEncoder.encode(bao, TagNum.NotionalPercentageOutstanding, notionalPercentageOutstanding);
            TagEncoder.encode(bao, TagNum.OriginalNotionalPercentageOutstanding, originalNotionalPercentageOutstanding);
            TagEncoder.encode(bao, TagNum.AttachmentPoint, attachmentPoint);
            TagEncoder.encode(bao, TagNum.DetachmentPoint, detachmentPoint);
            TagEncoder.encodeDate(bao, TagNum.IssueDate, issueDate);
            TagEncoder.encode(bao, TagNum.RepoCollateralSecurityType, repoCollateralSecurityType);
            TagEncoder.encode(bao, TagNum.RepurchaseTerm, repurchaseTerm);
            TagEncoder.encode(bao, TagNum.RepurchaseRate, repurchaseRate);
            TagEncoder.encode(bao, TagNum.Factor, factor);
            TagEncoder.encode(bao, TagNum.CreditRating, creditRating, sessionCharset);
            TagEncoder.encode(bao, TagNum.InstrRegistry, instrRegistry, sessionCharset);
            TagEncoder.encode(bao, TagNum.CountryOfIssue, countryOfIssue, sessionCharset);
            TagEncoder.encode(bao, TagNum.StateOrProvinceOfIssue, stateOrProvinceOfIssue, sessionCharset);
            TagEncoder.encode(bao, TagNum.LocaleOfIssue, localeOfIssue, sessionCharset);
            TagEncoder.encodeDate(bao, TagNum.RedemptionDate, redemptionDate);
            TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            if (strikeCurrency != null) {
                TagEncoder.encode(bao, TagNum.StrikeCurrency, strikeCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.StrikeMultiplier, strikeMultiplier);
            TagEncoder.encode(bao, TagNum.StrikeValue, strikeValue);
            if (strikePriceDeterminationMethod != null) {
                TagEncoder.encode(bao, TagNum.StrikePriceDeterminationMethod, strikePriceDeterminationMethod.getValue());
            }
            if (strikePriceBoundaryMethod != null) {
                TagEncoder.encode(bao, TagNum.StrikePriceBoundaryMethod, strikePriceBoundaryMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.StrikePriceBoundaryPrecision, strikePriceBoundaryPrecision);
            if (underlyingPriceDeterminationMethod != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingPriceDeterminationMethod, underlyingPriceDeterminationMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            if (contractMultiplierUnit != null) {
                TagEncoder.encode(bao, TagNum.ContractMultiplierUnit, contractMultiplierUnit.getValue());
            }
            TagEncoder.encode(bao, TagNum.FlowScheduleType, flowScheduleType);
            TagEncoder.encode(bao, TagNum.MinPriceIncrement, minPriceIncrement);
            TagEncoder.encode(bao, TagNum.MinPriceIncrementAmount, minPriceIncrementAmount);
            if (unitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.UnitOfMeasure, unitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnitOfMeasureQty, unitOfMeasureQty);
            if (priceUnitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.PriceUnitOfMeasure, priceUnitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.PriceUnitOfMeasureQty, priceUnitOfMeasureQty);
            if (settlMethod != null) {
                TagEncoder.encode(bao, TagNum.SettlMethod, settlMethod.getValue());
            }
            if (exerciseStyle != null) {
                TagEncoder.encode(bao, TagNum.ExerciseStyle, exerciseStyle.getValue());
            }
            if (optPayoutType != null) {
                TagEncoder.encode(bao, TagNum.OptPayoutType, optPayoutType.getValue());
            }
            TagEncoder.encode(bao, TagNum.OptPayAmount, optPayAmount);
            if (priceQuoteMethod != null) {
                TagEncoder.encode(bao, TagNum.PriceQuoteMethod, priceQuoteMethod.getValue());
            }
            if (futuresValuationMethod != null) {
                TagEncoder.encode(bao, TagNum.ValuationMethod, futuresValuationMethod.getValue());
            }
            if (listMethod != null) {
                TagEncoder.encode(bao, TagNum.ListMethod, listMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.CapPrice, capPrice);
            TagEncoder.encode(bao, TagNum.FloorPrice, floorPrice);
            if (putOrCall != null) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            TagEncoder.encode(bao, TagNum.FlexibleIndicator, flexibleIndicator);
            TagEncoder.encode(bao, TagNum.FlexProductEligibilityIndicator, flexProductEligibilityIndicator);
            if (timeUnit != null) {
                TagEncoder.encode(bao, TagNum.TimeUnit, timeUnit.getValue());
            }
            TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange, sessionCharset);
            TagEncoder.encode(bao, TagNum.PositionLimit, positionLimit);
            TagEncoder.encode(bao, TagNum.NTPositionLimit, ntPositionLimit);
            TagEncoder.encode(bao, TagNum.Issuer, issuer, sessionCharset);
            if (encodedIssuerLen != null && encodedIssuerLen.intValue() > 0) {
                if (encodedIssuer != null && encodedIssuer.length > 0) {
                    encodedIssuerLen = new Integer(encodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedIssuerLen, encodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedIssuer, encodedIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            if (encodedSecurityDescLen != null && encodedSecurityDescLen.intValue() > 0) {
                if (encodedSecurityDesc != null && encodedSecurityDesc.length > 0) {
                    encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
                }
            }
            if (securityXML != null) {
                bao.write(securityXML.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Pool, pool, sessionCharset);
            TagEncoder.encode(bao, TagNum.ContractSettlMonth, contractSettlMonth, sessionCharset);
            TagEncoder.encode(bao, TagNum.CPProgram, cpProgram);
            TagEncoder.encode(bao, TagNum.CPRegType, cpRegType, sessionCharset);
            if (noEvents != null && noEvents.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoEvents, noEvents);
                if (events != null && events.length == noEvents.intValue()) {
                    for (int i = 0; i < noEvents.intValue(); i++) {
                        if (events[i] != null) {
                            bao.write(events[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "EventGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoEvents.getValue(), error);
                }
            }
            TagEncoder.encodeDate(bao, TagNum.DatedDate, datedDate);
            TagEncoder.encodeDate(bao, TagNum.InterestAccrualDate, interestAccrualDate);
            if (instrumentParties != null) {
                bao.write(instrumentParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (complexEvents != null) {
                bao.write(complexEvents.encode(MsgSecureType.ALL_FIELDS));
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
            case Symbol:
                symbol = new String(tag.value, sessionCharset);;
                break;

            case SymbolSfx:
                symbolSfx = new String(tag.value, sessionCharset);
                break;

            case SecurityID:
                securityID = new String(tag.value, sessionCharset);
                break;

            case SecurityIDSource:
                securityIDSource = new String(tag.value, sessionCharset);
                break;

            case NoSecurityAltID:
                noSecurityAltID = new Integer(new String(tag.value, sessionCharset));
                break;

            case Product:
                product = Product.valueFor(new Integer(new String(tag.value, sessionCharset)).intValue());
                break;

            case ProductComplex:
                productComplex = new String(tag.value, sessionCharset);
                break;

            case SecurityGroup:
                securityGroup = new String(tag.value, sessionCharset);
                break;

            case CFICode:
                cfiCode = new String(tag.value, sessionCharset);
                break;

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case SecuritySubType:
                securitySubType = new String(tag.value, sessionCharset);
                break;

            case MaturityMonthYear:
                maturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case MaturityDate:
                maturityDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MaturityTime:
                maturityTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case SettleOnOpenFlag:
                settleOnOpenFlag = new String(tag.value, sessionCharset);
                break;

            case InstrmtAssignmentMethod:
                instrmtAssignmentMethod = InstrmtAssignmentMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SecurityStatus:
                securityStatus = SecurityStatus.valueFor(new String(tag.value, sessionCharset));
                break;

            case CouponPaymentDate:
                couponPaymentDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case RestructuringType:
                restructuringType = RestructuringType.valueFor(new String(tag.value, sessionCharset));
                break;

            case Seniority:
                seniority = Seniority.valueFor(new String(tag.value, sessionCharset));
                break;

            case NotionalPercentageOutstanding:
                notionalPercentageOutstanding = new Double(new String(tag.value, sessionCharset));
                break;

            case OriginalNotionalPercentageOutstanding:
                originalNotionalPercentageOutstanding = new Double(new String(tag.value, sessionCharset));
                break;

            case AttachmentPoint:
                attachmentPoint = new Double(new String(tag.value, sessionCharset));
                break;

            case DetachmentPoint:
                detachmentPoint = new Double(new String(tag.value, sessionCharset));
                break;

            case IssueDate:
                issueDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case RepoCollateralSecurityType:
                repoCollateralSecurityType = new String(tag.value, sessionCharset);
                break;

            case RepurchaseTerm:
                repurchaseTerm = new Integer(new String(tag.value, sessionCharset));
                break;

            case RepurchaseRate:
                repurchaseRate = new Double(new String(tag.value, sessionCharset));
                break;

            case Factor:
                factor = new Double(new String(tag.value, sessionCharset));
                break;

            case CreditRating:
                creditRating = new String(tag.value, sessionCharset);
                break;

            case InstrRegistry:
                instrRegistry = new String(tag.value, sessionCharset);
                break;

            case CountryOfIssue:
                countryOfIssue = new String(tag.value, sessionCharset);
                break;

            case StateOrProvinceOfIssue:
                stateOrProvinceOfIssue = new String(tag.value, sessionCharset);
                break;

            case LocaleOfIssue:
                localeOfIssue = new String(tag.value, sessionCharset);
                break;

            case RedemptionDate:
                redemptionDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case StrikePrice:
                strikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case StrikeCurrency:
                strikeCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case StrikeMultiplier:
                strikeMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case StrikeValue:
                strikeValue = new Double(new String(tag.value, sessionCharset));
                break;

            case StrikePriceDeterminationMethod:
                strikePriceDeterminationMethod = StrikePriceDeterminationMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StrikePriceBoundaryMethod:
                strikePriceBoundaryMethod = StrikePriceBoundaryMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StrikePriceBoundaryPrecision:
                strikePriceBoundaryPrecision = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingPriceDeterminationMethod:
                underlyingPriceDeterminationMethod = UnderlyingPriceDeterminationMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case OptAttribute:
                optAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ContractMultiplier:
                contractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case ContractMultiplierUnit:
                contractMultiplierUnit = ContractMultiplierUnit.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case FlowScheduleType:
                flowScheduleType = new Integer(new String(tag.value, sessionCharset));
                break;

            case MinPriceIncrement:
                minPriceIncrement = new Double(new String(tag.value, sessionCharset));
                break;

            case MinPriceIncrementAmount:
                minPriceIncrementAmount = new Double(new String(tag.value, sessionCharset));
                break;

            case UnitOfMeasure:
                unitOfMeasure = UnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnitOfMeasureQty:
                unitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceUnitOfMeasure:
                priceUnitOfMeasure = PriceUnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case PriceUnitOfMeasureQty:
                priceUnitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlMethod:
                settlMethod = SettlMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ExerciseStyle:
                exerciseStyle = ExerciseStyle.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case OptPayoutType:
                optPayoutType = OptPayoutType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case OptPayAmount:
                optPayAmount = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceQuoteMethod:
                priceQuoteMethod = PriceQuoteMethod.valueFor(new String(tag.value, sessionCharset));
                break;

            case ValuationMethod:
                futuresValuationMethod = FuturesValuationMethod.valueFor(new String(tag.value, sessionCharset));
                break;

            case ListMethod:
                listMethod = ListMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case CapPrice:
                capPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case FloorPrice:
                floorPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case PutOrCall:
                putOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case FlexibleIndicator:
                flexibleIndicator = BooleanConverter.parse(new String(tag.value, getSessionCharset()));
                break;

            case FlexProductEligibilityIndicator:
                flexProductEligibilityIndicator = BooleanConverter.parse(new String(tag.value, getSessionCharset()));
                break;

            case TimeUnit:
                timeUnit = TimeUnit.valueFor(new String(tag.value, sessionCharset));
                break;

            case CouponRate:
                couponRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SecurityExchange:
                securityExchange = new String(tag.value, sessionCharset);
                break;

            case PositionLimit:
                positionLimit = new Integer(new String(tag.value, sessionCharset));
                break;

            case NTPositionLimit:
                ntPositionLimit = new Integer(new String(tag.value, sessionCharset));
                break;

            case Issuer:
                issuer = new String(tag.value, sessionCharset);
                break;

            case EncodedIssuerLen:
                encodedIssuerLen = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case SecurityDesc:
                securityDesc = new String(tag.value, sessionCharset);
                break;

            case EncodedSecurityDescLen:
                encodedSecurityDescLen = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case Pool:
                pool = new String(tag.value, sessionCharset);
                break;

            case ContractSettlMonth:
                contractSettlMonth = new String(tag.value, sessionCharset);
                break;

            case CPProgram:
                cpProgram = new Integer(new String(tag.value, sessionCharset));
                break;

            case CPRegType:
                cpRegType = new String(tag.value, sessionCharset);
                break;

            case NoEvents:
                noEvents = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case DatedDate:
                datedDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case InterestAccrualDate:
                interestAccrualDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [Instrument] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {

        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedIssuerLen.getValue()) {
            try {
                encodedIssuerLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat,
                        TagNum.EncodedIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedIssuerLen.intValue());
            encodedIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedSecurityDescLen.getValue()) {
            try {
                encodedSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedSecurityDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat,
                        TagNum.EncodedSecurityDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedSecurityDescLen.intValue());
            encodedSecurityDesc = dataTag.value;
        }

        return result;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{Instrument=");
        printTagValue(b, TagNum.Symbol, symbol);
        printTagValue(b, TagNum.SymbolSfx, symbolSfx);
        printTagValue(b, TagNum.SecurityID, securityID);
        printTagValue(b, TagNum.SecurityIDSource, securityIDSource);
        printTagValue(b, TagNum.NoSecurityAltID, noSecurityAltID);
        printTagValue(b, securityAltIDGroups);
        printTagValue(b, TagNum.Product, product);
        printTagValue(b, TagNum.ProductComplex, productComplex);
        printTagValue(b, TagNum.SecurityGroup, securityGroup);
        printTagValue(b, TagNum.CFICode, cfiCode);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.SecuritySubType, securitySubType);
        printTagValue(b, TagNum.MaturityMonthYear, maturityMonthYear);
        printDateTagValue(b, TagNum.MaturityDate, maturityDate);
        printTimeTagValue(b, TagNum.MaturityTime, maturityTime);
        printTagValue(b, TagNum.SettleOnOpenFlag, settleOnOpenFlag);
        printTagValue(b, TagNum.InstrmtAssignmentMethod, instrmtAssignmentMethod);
        printTagValue(b, TagNum.SecurityStatus, securityStatus);
        printDateTagValue(b, TagNum.CouponPaymentDate, couponPaymentDate);
        printTagValue(b, TagNum.RestructuringType, restructuringType);
        printTagValue(b, TagNum.Seniority, seniority);
        printTagValue(b, TagNum.NotionalPercentageOutstanding, notionalPercentageOutstanding);
        printTagValue(b, TagNum.OriginalNotionalPercentageOutstanding, originalNotionalPercentageOutstanding);
        printTagValue(b, TagNum.AttachmentPoint, attachmentPoint);
        printTagValue(b, TagNum.DetachmentPoint, detachmentPoint);
        printDateTagValue(b, TagNum.IssueDate, issueDate);
        printTagValue(b, TagNum.RepoCollateralSecurityType, repoCollateralSecurityType);
        printTagValue(b, TagNum.RepurchaseTerm, repurchaseTerm);
        printTagValue(b, TagNum.RepurchaseRate, repurchaseRate);
        printTagValue(b, TagNum.Factor, factor);
        printTagValue(b, TagNum.CreditRating, creditRating);
        printTagValue(b, TagNum.InstrRegistry, instrRegistry);
        printTagValue(b, TagNum.CountryOfIssue, countryOfIssue);
        printTagValue(b, TagNum.StateOrProvinceOfIssue, stateOrProvinceOfIssue);
        printTagValue(b, TagNum.LocaleOfIssue, localeOfIssue);
        printDateTagValue(b, TagNum.RedemptionDate, redemptionDate);
        printTagValue(b, TagNum.StrikePrice, strikePrice);
        printTagValue(b, TagNum.StrikeCurrency, strikeCurrency);
        printTagValue(b, TagNum.StrikeMultiplier, strikeMultiplier);
        printTagValue(b, TagNum.StrikeValue, strikeValue);
        printTagValue(b, TagNum.StrikePriceDeterminationMethod, strikePriceDeterminationMethod);
        printTagValue(b, TagNum.StrikePriceBoundaryMethod, strikePriceBoundaryMethod);
        printTagValue(b, TagNum.StrikePriceBoundaryPrecision, strikePriceBoundaryPrecision);
        printTagValue(b, TagNum.UnderlyingPriceDeterminationMethod, underlyingPriceDeterminationMethod);
        printTagValue(b, TagNum.OptAttribute, optAttribute);
        printTagValue(b, TagNum.ContractMultiplier, contractMultiplier);
        printTagValue(b, TagNum.ContractMultiplierUnit, contractMultiplierUnit);
        printTagValue(b, TagNum.FlowScheduleType, flowScheduleType);
        printTagValue(b, TagNum.MinPriceIncrement, minPriceIncrement);
        printTagValue(b, TagNum.MinPriceIncrementAmount, minPriceIncrementAmount);
        printTagValue(b, TagNum.UnitOfMeasure, unitOfMeasure);
        printTagValue(b, TagNum.UnitOfMeasureQty, unitOfMeasureQty);
        printTagValue(b, TagNum.PriceUnitOfMeasure, priceUnitOfMeasure);
        printTagValue(b, TagNum.PriceUnitOfMeasureQty, priceUnitOfMeasureQty);
        printTagValue(b, TagNum.SettlMethod, settlMethod);
        printTagValue(b, TagNum.ExerciseStyle, exerciseStyle);
        printTagValue(b, TagNum.OptPayoutType, optPayoutType);
        printTagValue(b, TagNum.OptPayAmount, optPayAmount);
        printTagValue(b, TagNum.PriceQuoteMethod, priceQuoteMethod);
        printTagValue(b, TagNum.ValuationMethod, futuresValuationMethod);
        printTagValue(b, TagNum.ListMethod, listMethod);
        printTagValue(b, TagNum.CapPrice, capPrice);
        printTagValue(b, TagNum.FloorPrice, floorPrice);
        printTagValue(b, TagNum.PutOrCall, putOrCall);
        printTagValue(b, TagNum.FlexibleIndicator, flexibleIndicator);
        printTagValue(b, TagNum.FlexProductEligibilityIndicator, flexProductEligibilityIndicator);
        printTagValue(b, TagNum.TimeUnit, timeUnit);
        printTagValue(b, TagNum.CouponRate, couponRate);
        printTagValue(b, TagNum.SecurityExchange, securityExchange);
        printTagValue(b, TagNum.PositionLimit, positionLimit);
        printTagValue(b, TagNum.NTPositionLimit, ntPositionLimit);
        printTagValue(b, TagNum.Issuer, issuer);
        printTagValue(b, TagNum.EncodedIssuerLen, encodedIssuerLen);
        printTagValue(b, TagNum.EncodedIssuer, encodedIssuer);
        printTagValue(b, TagNum.SecurityDesc, securityDesc);
        printTagValue(b, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
        printTagValue(b, securityXML);
        printTagValue(b, TagNum.Pool, pool);
        printTagValue(b, TagNum.ContractSettlMonth, contractSettlMonth);
        printTagValue(b, TagNum.CPProgram, cpProgram);
        printTagValue(b, TagNum.CPRegType, cpRegType);
        printTagValue(b, TagNum.NoEvents, noEvents);
        printTagValue(b, events);
        printDateTagValue(b, TagNum.DatedDate, datedDate);
        printDateTagValue(b, TagNum.InterestAccrualDate, interestAccrualDate);
        printTagValue(b, instrumentParties);
        printTagValue(b, complexEvents);
        b.append("}");

        return b.toString();
    }

     // </editor-fold>
}
