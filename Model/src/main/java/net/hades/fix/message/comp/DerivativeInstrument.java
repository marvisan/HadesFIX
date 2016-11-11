/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrument.java
 *
 * $Id: DerivativeInstrument.java,v 1.2 2011-09-22 08:54:31 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;

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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivativeEventGroup;
import net.hades.fix.message.group.DerivativeSecurityAltIDGroup;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FuturesValuationMethod;
import net.hades.fix.message.type.InstrmtAssignmentMethod;
import net.hades.fix.message.type.ListMethod;
import net.hades.fix.message.type.PriceQuoteMethod;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityStatus;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlMethod;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Derivative instrument component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 26/10/2008, 11:46:24
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeInstrument extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeSymbol.getValue(),
        TagNum.DerivativeSymbolSfx.getValue(),
        TagNum.DerivativeSecurityID.getValue(),
        TagNum.DerivativeSecurityIDSource.getValue(),
        TagNum.NoDerivativeSecurityAltID.getValue(),
        TagNum.DerivativeProduct.getValue(),
        TagNum.DerivativeProductComplex.getValue(),
        TagNum.DerivFlexProductEligibilityIndicator.getValue(),
        TagNum.DerivativeSecurityGroup.getValue(),
        TagNum.DerivativeCFICode.getValue(),
        TagNum.DerivativeSecurityType.getValue(),
        TagNum.DerivativeSecuritySubType.getValue(),
        TagNum.DerivativeMaturityMonthYear.getValue(),
        TagNum.DerivativeMaturityDate.getValue(),
        TagNum.DerivativeMaturityTime.getValue(),
        TagNum.DerivativeSettleOnOpenFlag.getValue(),
        TagNum.DerivativeInstrmtAssignmentMethod.getValue(),
        TagNum.DerivativeSecurityStatus.getValue(),
        TagNum.DerivativeIssueDate.getValue(),
        TagNum.DerivativeInstrRegistry.getValue(),
        TagNum.DerivativeCountryOfIssue.getValue(),
        TagNum.DerivativeStateOrProvinceOfIssue.getValue(),
        TagNum.DerivativeLocaleOfIssue.getValue(),
        TagNum.DerivativeStrikePrice.getValue(),
        TagNum.DerivativeStrikeCurrency.getValue(),
        TagNum.DerivativeStrikeMultiplier.getValue(),
        TagNum.DerivativeOptAttribute.getValue(),
        TagNum.DerivativeContractMultiplier.getValue(),
        TagNum.DerivativeContractMultiplierUnit.getValue(),
        TagNum.DerivativeFlowScheduleType.getValue(),
        TagNum.DerivativeMinPriceIncrement.getValue(),
        TagNum.DerivativeMinPriceIncrementAmount.getValue(),
        TagNum.DerivativeUnitOfMeasure.getValue(),
        TagNum.DerivativeUnitOfMeasureQty.getValue(),
        TagNum.DerivativePriceUnitOfMeasure.getValue(),
        TagNum.DerivativePriceUnitOfMeasureQty.getValue(),
        TagNum.DerivativeSettlMethod.getValue(),
        TagNum.DerivativePriceQuoteMethod.getValue(),
        TagNum.DerivativeValuationMethod.getValue(),
        TagNum.DerivativeListMethod.getValue(),
        TagNum.DerivativeCapPrice.getValue(),
        TagNum.DerivativeFloorPrice.getValue(),
        TagNum.DerivativePutOrCall.getValue(),
        TagNum.DerivativeExerciseStyle.getValue(),
        TagNum.DerivativeOptPayAmount.getValue(),
        TagNum.DerivativeTimeUnit.getValue(),
        TagNum.DerivativeSecurityExchange.getValue(),
        TagNum.DerivativePositionLimit.getValue(),
        TagNum.DerivativeNTPositionLimit.getValue(),
        TagNum.DerivativeIssuer.getValue(),
        TagNum.DerivativeSecurityDesc.getValue(),
        TagNum.DerivativeContractSettlMonth.getValue(),
        TagNum.NoDerivativeEvents.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeEncodedIssuerLen.getValue(),
        TagNum.DerivativeEncodedSecurityDescLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeSymbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** 
     * TagNum = 1214. Starting with 5.0SP1 version.
     */
    protected String derivativeSymbol;

    /** 
     * TagNum = 1215. Starting with 5.0SP1 version.
     */
    protected String derivativeSymbolSfx;

    /** 
     * TagNum = 1216. Starting with 5.0SP1 version.
     */
    protected String derivativeSecurityID;

    /** 
     * TagNum = 1217. Starting with 5.0SP1 version.
     */
    protected String derivativeSecurityIDSource;

    /** 
     * TagNum = 1218. Starting with 5.0SP1 version.
     */
    protected Integer noDerivativeSecurityAltID;

    /** 
     * Starting with 5.0SP1 version.
     */
    protected DerivativeSecurityAltIDGroup[] derivativeSecurityAltIDGroups;

    /** 
     * TagNum = 1246. Starting with 5.0SP1 version.
     */
    protected Product derivativeProduct;

    /**
     * TagNum = 1228. Starting with 5.0SP1 version.
     */
    protected String derivativeProductComplex;
    
    /**
     * TagNum = 1243. Starting with 5.0SP1 version.
     */
    protected Boolean derivFlexProductEligibilityIndicator;

     /**
     * TagNum = 1247. Starting with 5.0SP1 version.
     */
    protected String derivativeSecurityGroup;

    /** 
     * TagNum = 1248. Starting with 5.0SP1 version.
     */
    protected String derivativeCFICode;

    /** 
     * TagNum = 1249. Starting with 5.0SP1 version.
     */
    protected String derivativeSecurityType;

    /**
     * TagNum = 1250. Starting with 5.0SP1 version.
     */
    protected String derivativeSecuritySubType;

    /** 
     * TagNum = 1251. Starting with 5.0SP1 version.
     */
    protected String derivativeMaturityMonthYear;

    /** 
     * TagNum = 1252. Starting with 5.0SP1 version.
     */
    protected Date derivativeMaturityDate;

    /**
     * TagNum = 1253. Starting with 5.0SP1 version.
     */
    protected Date derivativeMaturityTime;

    /**
     * TagNum = 1254. Starting with 5.0SP1 version.
     */
    protected String derivativeSettleOnOpenFlag;

    /**
     * TagNum = 1255. Starting with 5.0SP1 version.
     */
    protected InstrmtAssignmentMethod derivativeInstrmtAssignmentMethod;

    /**
     * TagNum = 1256. Starting with 5.0SP1 version.
     */
    protected SecurityStatus derivativeSecurityStatus;

    /** 
     * TagNum = 1276. Starting with 5.0SP1 version.
     */
    protected Date derivativeIssueDate;

    /** 
     * TagNum = 1257. Starting with 5.0SP1 version.
     */
    protected String derivativeInstrRegistry;

    /** 
     * TagNum = 1258. Starting with 5.0SP1 version.
     */
    protected String derivativeCountryOfIssue;

    /** 
     * TagNum = 1259. Starting with 5.0SP1 version.
     */
    protected String derivativeStateOrProvinceOfIssue;

    /** 
     * TagNum = 1260. Starting with 5.0SP1 version.
     */
    protected String derivativeLocaleOfIssue;

    /** 
     * TagNum = 1261. Starting with 5.0SP1 version.
     */
    protected Double derivativeStrikePrice;

    /**
     * TagNum = 1262. Starting with 5.0SP1 version.
     */
    protected Currency derivativeStrikeCurrency;

    /**
     * TagNum = 1263. Starting with 5.0SP1 version.
     */
    protected Double derivativeStrikeMultiplier;

    /** 
     * TagNum = 1265. Starting with 5.0SP1 version.
     */
    protected Character derivativeOptAttribute;

    /** 
     * TagNum = 1256. Starting with 5.0SP1 version.
     */
    protected Double derivativeContractMultiplier;

    /**
     * TagNum = 1438. Starting with 5.0SP2 version.
     */
    protected ContractMultiplierUnit derivativeContractMultiplierUnit;

    /**
     * TagNum = 1442. Starting with 5.0SP2 version.
     */
    protected Integer derivativeFlowScheduleType;

    /**
     * TagNum = 1267. Starting with 5.0SP1 version.
     */
    protected Double derivativeMinPriceIncrement;

    /**
     * TagNum = 1268. Starting with 5.0SP1 version.
     */
    protected Double derivativeMinPriceIncrementAmount;

    /**
     * TagNum = 1269. Starting with 5.0SP1 version.
     */
    protected UnitOfMeasure derivativeUnitOfMeasure;

    /**
     * TagNum = 1270. Starting with 5.0SP1 version.
     */
    protected Double derivativeUnitOfMeasureQty;

    /**
     * TagNum = 1315. Starting with 5.0SP1 version.
     */
    protected PriceUnitOfMeasure derivativePriceUnitOfMeasure;

    /**
     * TagNum = 1316. Starting with 5.0SP1 version.
     */
    protected Double derivativePriceUnitOfMeasureQty;

    /**
     * TagNum = 1317. Starting with 5.0SP1 version.
     */
    protected SettlMethod derivativeSettlMethod;

    /**
     * TagNum = 1318. Starting with 5.0SP1 version.
     */
    protected PriceQuoteMethod derivativePriceQuoteMethod;

    /**
     * TagNum = 1319. Starting with 5.0SP1 version.
     */
    protected FuturesValuationMethod derivativeValuationMethod;

    /**
     * TagNum = 1320. Starting with 5.0SP1 version.
     */
    protected ListMethod derivativeListMethod;

    /**
     * TagNum = 1321. Starting with 5.0SP1 version.
     */
    protected Double derivativeCapPrice;

    /**
     * TagNum = 1322. Starting with 5.0SP1 version.
     */
    protected Double derivativeFloorPrice;

    /**
     * TagNum = 1323. Starting with 5.0SP1 version.
     */
    protected PutOrCall derivativePutOrCall;
    
    /**
     * TagNum = 1299. Starting with 5.0SP1 version.
     */
    protected ExerciseStyle derivativeExerciseStyle;

    /** 
     * TagNum = 1225. Starting with 5.0SP1 version.
     */
    protected Double derivativeOptPayAmount;

    /**
     * TagNum = 1271. Starting with 5.0SP1 version.
     */
    protected TimeUnit derivativeTimeUnit;

    /** 
     * TagNum = 1272. Starting with 5.0SP1 version.
     */
    protected String derivativeSecurityExchange;

    /**
     * TagNum = 1273. Starting with 5.0SP1 version.
     */
    protected Integer derivativePositionLimit;

    /**
     * TagNum = 1274. Starting with 5.0SP1 version.
     */
    protected Integer derivativeNTPositionLimit;

    /** 
     * TagNum = 1275. Starting with 5.0SP1 version.
     */
    protected String derivativeIssuer;

    /** 
     * TagNum = 1277. Starting with 5.0SP1 version.
     */
    protected Integer derivativeEncodedIssuerLen;

    /** 
     * TagNum = 1278. Starting with 5.0SP1 version.
     */
    protected byte[] derivativeEncodedIssuer;

    /** 
     * TagNum = 1279. Starting with 5.0SP1 version.
     */
    protected String derivativeSecurityDesc;

    /** 
     * TagNum = 1280. Starting with 5.0SP1 version.
     */
    protected Integer derivativeEncodedSecurityDescLen;

    /** 
     * TagNum = 1281. Starting with 5.0SP1 version.
     */
    protected byte[] derivativeEncodedSecurityDesc;

    /**
     * Starting with 5.0SP1 version.
     */
    protected DerivativeSecurityXML derivativeSecurityXML;

    /**
     * TagNum = 1285. Starting with 5.0SP1 version.
     */
    protected String derivativeContractSettlMonth;

    /**
     * TagNum = 1286. Starting with 5.0SP1 version.
     */
    protected Integer noDerivativeEvents;

    /**
     * Starting with 4.4 version.
     */
    protected DerivativeEventGroup[] derivativeEvents;

    /**
     * Starting with 5.0 version.
     */
    protected DerivativeInstrumentParties derivativeInstrumentParties;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeInstrument() {
    }

    public DerivativeInstrument(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeCFICode)
    public String getDerivativeCFICode() {
        return derivativeCFICode;
    }

    /**
     * Message field setter.
     * @param derivativeCFICode field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeCFICode)
    public void setDerivativeCFICode(String derivativeCFICode) {
        this.derivativeCFICode = derivativeCFICode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeContractMultiplier)
    public Double getDerivativeContractMultiplier() {
        return derivativeContractMultiplier;
    }

    /**
     * Message field setter.
     * @param derivativeContractMultiplier field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeContractMultiplier)
    public void setDerivativeContractMultiplier(Double derivativeContractMultiplier) {
        this.derivativeContractMultiplier = derivativeContractMultiplier;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.DerivativeContractMultiplierUnit)
    public ContractMultiplierUnit getDerivativeContractMultiplierUnit() {
        return derivativeContractMultiplierUnit;
    }

    /**
     * Message field setter.
     * @param derivativeContractMultiplierUnit field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.DerivativeContractMultiplierUnit)
    public void setDerivativeContractMultiplierUnit(ContractMultiplierUnit derivativeContractMultiplierUnit) {
        this.derivativeContractMultiplierUnit = derivativeContractMultiplierUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.DerivativeFlowScheduleType)
    public Integer getDerivativeFlowScheduleType() {
        return derivativeFlowScheduleType;
    }

    /**
     * Message field setter.
     * @param derivativeFlowScheduleType field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.DerivativeFlowScheduleType)
    public void setDerivativeFlowScheduleType(Integer derivativeFlowScheduleType) {
        this.derivativeFlowScheduleType = derivativeFlowScheduleType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeCountryOfIssue)
    public String getDerivativeCountryOfIssue() {
        return derivativeCountryOfIssue;
    }

    /**
     * Message field setter.
     * @param derivativeCountryOfIssue field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeCountryOfIssue)
    public void setDerivativeCountryOfIssue(String derivativeCountryOfIssue) {
        this.derivativeCountryOfIssue = derivativeCountryOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedIssuer)
    public byte[] getDerivativeEncodedIssuer() {
        return derivativeEncodedIssuer;
    }

    /**
     * Message field setter.
     * @param derivativeEncodedIssuer field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedIssuer)
    public void setDerivativeEncodedIssuer(byte[] derivativeEncodedIssuer) {
        this.derivativeEncodedIssuer = derivativeEncodedIssuer;
        if (derivativeEncodedIssuerLen == null) {
            derivativeEncodedIssuerLen = new Integer(derivativeEncodedIssuer.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedIssuerLen)
    public Integer getDerivativeEncodedIssuerLen() {
        return derivativeEncodedIssuerLen;
    }

    /**
     * Message field setter.
     * @param derivativeEncodedIssuerLen field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedIssuerLen)
    public void setDerivativeEncodedIssuerLen(Integer derivativeEncodedIssuerLen) {
        this.derivativeEncodedIssuerLen = derivativeEncodedIssuerLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedSecurityDesc)
    public byte[] getDerivativeEncodedSecurityDesc() {
        return derivativeEncodedSecurityDesc;
    }

    /**
     * Message field setter.
     * @param derivativeEncodedSecurityDesc field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedSecurityDesc)
    public void setDerivativeEncodedSecurityDesc(byte[] derivativeEncodedSecurityDesc) {
        this.derivativeEncodedSecurityDesc = derivativeEncodedSecurityDesc;
        if (derivativeEncodedSecurityDescLen == null) {
            derivativeEncodedSecurityDescLen = new Integer(derivativeEncodedSecurityDesc.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedSecurityDescLen)
    public Integer getDerivativeEncodedSecurityDescLen() {
        return derivativeEncodedSecurityDescLen;
    }

    /**
     * Message field setter.
     * @param derivativeEncodedSecurityDescLen field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeEncodedSecurityDescLen)
    public void setDerivativeEncodedSecurityDescLen(Integer derivativeEncodedSecurityDescLen) {
        this.derivativeEncodedSecurityDescLen = derivativeEncodedSecurityDescLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeInstrRegistry)
    public String getDerivativeInstrRegistry() {
        return derivativeInstrRegistry;
    }

    /**
     * Message field setter.
     * @param derivativeInstrRegistry field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeInstrRegistry)
    public void setDerivativeInstrRegistry(String derivativeInstrRegistry) {
        this.derivativeInstrRegistry = derivativeInstrRegistry;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeIssueDate)
    public Date getDerivativeIssueDate() {
        return derivativeIssueDate;
    }

    /**
     * Message field setter.
     * @param derivativeIssueDate field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeIssueDate)
    public void setDerivativeIssueDate(Date derivativeIssueDate) {
        this.derivativeIssueDate = derivativeIssueDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeIssuer)
    public String getDerivativeIssuer() {
        return derivativeIssuer;
    }

    /**
     * Message field setter.
     * @param derivativeIssuer field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeIssuer)
    public void setDerivativeIssuer(String derivativeIssuer) {
        this.derivativeIssuer = derivativeIssuer;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeLocaleOfIssue)
    public String getDerivativeLocaleOfIssue() {
        return derivativeLocaleOfIssue;
    }

    /**
     * Message field setter.
     * @param derivativeLocaleOfIssue field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeLocaleOfIssue)
    public void setDerivativeLocaleOfIssue(String derivativeLocaleOfIssue) {
        this.derivativeLocaleOfIssue = derivativeLocaleOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMaturityDate)
    public Date getDerivativeMaturityDate() {
        return derivativeMaturityDate;
    }

    /**
     * Message field setter.
     * @param derivativeMaturityDate field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMaturityDate)
    public void setDerivativeMaturityDate(Date derivativeMaturityDate) {
        this.derivativeMaturityDate = derivativeMaturityDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMaturityTime)
    public Date getDerivativeMaturityTime() {
        return derivativeMaturityTime;
    }

    /**
     * Message field setter.
     * @param derivativeMaturityTime field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMaturityTime)
    public void setDerivativeMaturityTime(Date derivativeMaturityTime) {
        this.derivativeMaturityTime = derivativeMaturityTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSettleOnOpenFlag)
    public String getDerivativeSettleOnOpenFlag() {
        return derivativeSettleOnOpenFlag;
    }

    /**
     * Message field setter.
     * @param derivativeSettleOnOpenFlag field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSettleOnOpenFlag)
    public void setDerivativeSettleOnOpenFlag(String derivativeSettleOnOpenFlag) {
        this.derivativeSettleOnOpenFlag = derivativeSettleOnOpenFlag;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeInstrmtAssignmentMethod)
    public InstrmtAssignmentMethod getDerivativeInstrmtAssignmentMethod() {
        return derivativeInstrmtAssignmentMethod;
    }

    /**
     * Message field setter.
     * @param derivativeInstrmtAssignmentMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeInstrmtAssignmentMethod)
    public void setDerivativeInstrmtAssignmentMethod(InstrmtAssignmentMethod derivativeInstrmtAssignmentMethod) {
        this.derivativeInstrmtAssignmentMethod = derivativeInstrmtAssignmentMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityStatus)
    public SecurityStatus getDerivativeSecurityStatus() {
        return derivativeSecurityStatus;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityStatus field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityStatus)
    public void setDerivativeSecurityStatus(SecurityStatus derivativeSecurityStatus) {
        this.derivativeSecurityStatus = derivativeSecurityStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMaturityMonthYear)
    public String getDerivativeMaturityMonthYear() {
        return derivativeMaturityMonthYear;
    }

    /**
     * Message field setter.
     * @param derivativeMaturityMonthYear field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMaturityMonthYear)
    public void setDerivativeMaturityMonthYear(String derivativeMaturityMonthYear) {
        this.derivativeMaturityMonthYear = derivativeMaturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeOptAttribute)
    public Character getDerivativeOptAttribute() {
        return derivativeOptAttribute;
    }

    /**
     * Message field setter.
     * @param derivativeOptAttribute field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeOptAttribute)
    public void setDerivativeOptAttribute(Character derivativeOptAttribute) {
        this.derivativeOptAttribute = derivativeOptAttribute;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeProduct)
    public Product getDerivativeProduct() {
        return derivativeProduct;
    }

    /**
     * Message field setter.
     * @param derivativeProduct field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeProduct)
    public void setDerivativeProduct(Product derivativeProduct) {
        this.derivativeProduct = derivativeProduct;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeProductComplex)
    public String getDerivativeProductComplex() {
        return derivativeProductComplex;
    }

    /**
     * Message field setter.
     * @param derivativeProductComplex field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeProductComplex)
    public void setDerivativeProductComplex(String derivativeProductComplex) {
        this.derivativeProductComplex = derivativeProductComplex;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityGroup)
    public String getDerivativeSecurityGroup() {
        return derivativeSecurityGroup;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityGroup field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityGroup)
    public void setDerivativeSecurityGroup(String derivativeSecurityGroup) {
        this.derivativeSecurityGroup = derivativeSecurityGroup;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoDerivativeSecurityAltID)
    public Integer getNoDerivativeSecurityAltID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of DerivativeSecurityAltIDGroup groups. It will also create an array
     * of DerivativeSecurityAltIDGroup objects and set the <code>derivativeSecurityAltIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>derivativeSecurityAltIDGroups</code> array they will be discarded.<br/>
     * @param noDerivativeSecurityAltID number of DerivativeSecurityAltIDGroup objects
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoDerivativeSecurityAltID)
    public void setNoDerivativeSecurityAltID(Integer noDerivativeSecurityAltID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for <code>DerivativeSecurityAltIDGroup</code> array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeSecurityAltIDGroup[] getDerivativeSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a  <code>DerivativeSecurityAltIDGroup</code> object to the existing array of <code>derivativeSecurityAltIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noDerivativeSecurityAltID</code> field to the proper value.<br/>
     * Note: If the <code>setNoDerivativeSecurityAltID</code> method has been called there will already be a number of objects in the
     * <code>derivativeSecurityAltIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeSecurityAltIDGroup addDerivativeSecurityAltIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a  <code>DerivativeSecurityAltIDGroup</code> object from the existing array of <code>derivativeSecurityAltIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDerivativeSecurityAltID</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeSecurityAltIDGroup deleteDerivativeSecurityAltIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the <code>DerivativeSecurityAltIDGroup</code> objects from the <code>derivativeSecurityAltIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDerivativeSecurityAltID</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0SP1")
    public int clearDerivativeSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityDesc)
    public String getDerivativeSecurityDesc() {
        return derivativeSecurityDesc;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityDesc field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityDesc)
    public void setDerivativeSecurityDesc(String derivativeSecurityDesc) {
        this.derivativeSecurityDesc = derivativeSecurityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityExchange)
    public String getDerivativeSecurityExchange() {
        return derivativeSecurityExchange;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityExchange field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityExchange)
    public void setDerivativeSecurityExchange(String derivativeSecurityExchange) {
        this.derivativeSecurityExchange = derivativeSecurityExchange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityID)
    public String getDerivativeSecurityID() {
        return derivativeSecurityID;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityID field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityID)
    public void setDerivativeSecurityID(String derivativeSecurityID) {
        this.derivativeSecurityID = derivativeSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityIDSource)
    public String getDerivativeSecurityIDSource() {
        return derivativeSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityIDSource field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityIDSource)
    public void setDerivativeSecurityIDSource(String derivativeSecurityIDSource) {
        this.derivativeSecurityIDSource = derivativeSecurityIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityType)
    public String getDerivativeSecurityType() {
        return derivativeSecurityType;
    }

    /**
     * Message field setter.
     * @param derivativeSecurityType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecurityType)
    public void setDerivativeSecurityType(String derivativeSecurityType) {
        this.derivativeSecurityType = derivativeSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecuritySubType)
    public String getDerivativeSecuritySubType() {
        return derivativeSecuritySubType;
    }

    /**
     * Message field setter.
     * @param derivativeSecuritySubType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSecuritySubType)
    public void setDerivativeSecuritySubType(String derivativeSecuritySubType) {
        this.derivativeSecuritySubType = derivativeSecuritySubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStateOrProvinceOfIssue)
    public String getDerivativeStateOrProvinceOfIssue() {
        return derivativeStateOrProvinceOfIssue;
    }

    /**
     * Message field setter.
     * @param derivativeStateOrProvinceOfIssue field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStateOrProvinceOfIssue)
    public void setDerivativeStateOrProvinceOfIssue(String derivativeStateOrProvinceOfIssue) {
        this.derivativeStateOrProvinceOfIssue = derivativeStateOrProvinceOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStrikePrice)
    public Double getDerivativeStrikePrice() {
        return derivativeStrikePrice;
    }

    /**
     * Message field setter.
     * @param derivativeStrikePrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStrikePrice)
    public void setDerivativeStrikePrice(Double derivativeStrikePrice) {
        this.derivativeStrikePrice = derivativeStrikePrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStrikeCurrency)
    public Currency getDerivativeStrikeCurrency() {
        return derivativeStrikeCurrency;
    }

    /**
     * Message field setter.
     * @param derivativeStrikeCurrency field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStrikeCurrency)
    public void setDerivativeStrikeCurrency(Currency derivativeStrikeCurrency) {
        this.derivativeStrikeCurrency = derivativeStrikeCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStrikeMultiplier)
    public Double getDerivativeStrikeMultiplier() {
        return derivativeStrikeMultiplier;
    }

    /**
     * Message field setter.
     * @param derivativeStrikeMultiplier field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeStrikeMultiplier)
    public void setDerivativeStrikeMultiplier(Double derivativeStrikeMultiplier) {
        this.derivativeStrikeMultiplier = derivativeStrikeMultiplier;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMinPriceIncrement)
    public Double getDerivativeMinPriceIncrement() {
        return derivativeMinPriceIncrement;
    }

    /**
     * Message field setter.
     * @param derivativeMinPriceIncrement field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMinPriceIncrement)
    public void setDerivativeMinPriceIncrement(Double derivativeMinPriceIncrement) {
        this.derivativeMinPriceIncrement = derivativeMinPriceIncrement;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMinPriceIncrementAmount)
    public Double getDerivativeMinPriceIncrementAmount() {
        return derivativeMinPriceIncrementAmount;
    }

    /**
     * Message field setter.
     * @param derivativeMinPriceIncrementAmount field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeMinPriceIncrementAmount)
    public void setDerivativeMinPriceIncrementAmount(Double derivativeMinPriceIncrementAmount) {
        this.derivativeMinPriceIncrementAmount = derivativeMinPriceIncrementAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeUnitOfMeasure)
    public UnitOfMeasure getDerivativeUnitOfMeasure() {
        return derivativeUnitOfMeasure;
    }

    /**
     * Message field setter.
     * @param derivativeUnitOfMeasure field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeUnitOfMeasure)
    public void setDerivativeUnitOfMeasure(UnitOfMeasure derivativeUnitOfMeasure) {
        this.derivativeUnitOfMeasure = derivativeUnitOfMeasure;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeUnitOfMeasureQty)
    public Double getDerivativeUnitOfMeasureQty() {
        return derivativeUnitOfMeasureQty;
    }

    /**
     * Message field setter.
     * @param derivativeUnitOfMeasureQty field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeUnitOfMeasureQty)
    public void setDerivativeUnitOfMeasureQty(Double derivativeUnitOfMeasureQty) {
        this.derivativeUnitOfMeasureQty = derivativeUnitOfMeasureQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePriceUnitOfMeasure)
    public PriceUnitOfMeasure getDerivativePriceUnitOfMeasure() {
        return derivativePriceUnitOfMeasure;
    }

    /**
     * Message field setter.
     * @param derivativePriceUnitOfMeasure field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePriceUnitOfMeasure)
    public void setDerivativePriceUnitOfMeasure(PriceUnitOfMeasure derivativePriceUnitOfMeasure) {
        this.derivativePriceUnitOfMeasure = derivativePriceUnitOfMeasure;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePriceUnitOfMeasureQty)
    public Double getDerivativePriceUnitOfMeasureQty() {
        return derivativePriceUnitOfMeasureQty;
    }

    /**
     * Message field setter.
     * @param derivativePriceUnitOfMeasureQty field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePriceUnitOfMeasureQty)
    public void setDerivativePriceUnitOfMeasureQty(Double derivativePriceUnitOfMeasureQty) {
        this.derivativePriceUnitOfMeasureQty = derivativePriceUnitOfMeasureQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSettlMethod)
    public SettlMethod getDerivativeSettlMethod() {
        return derivativeSettlMethod;
    }

    /**
     * Message field setter.
     * @param derivativeSettlMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSettlMethod)
    public void setDerivativeSettlMethod(SettlMethod derivativeSettlMethod) {
        this.derivativeSettlMethod = derivativeSettlMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeExerciseStyle)
    public ExerciseStyle getDerivativeExerciseStyle() {
        return derivativeExerciseStyle;
    }

    /**
     * Message field setter.
     * @param derivativeExerciseStyle field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeExerciseStyle)
    public void setDerivativeExerciseStyle(ExerciseStyle derivativeExerciseStyle) {
        this.derivativeExerciseStyle = derivativeExerciseStyle;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeOptPayAmount)
    public Double getDerivativeOptPayAmount() {
        return derivativeOptPayAmount;
    }

    /**
     * Message field setter.
     * @param derivativeOptPayAmount field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeOptPayAmount)
    public void setDerivativeOptPayAmount(Double derivativeOptPayAmount) {
        this.derivativeOptPayAmount = derivativeOptPayAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePriceQuoteMethod)
    public PriceQuoteMethod getDerivativePriceQuoteMethod() {
        return derivativePriceQuoteMethod;
    }

    /**
     * Message field setter.
     * @param derivativePriceQuoteMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePriceQuoteMethod)
    public void setDerivativePriceQuoteMethod(PriceQuoteMethod derivativePriceQuoteMethod) {
        this.derivativePriceQuoteMethod = derivativePriceQuoteMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeValuationMethod)
    public FuturesValuationMethod getDerivativeValuationMethod() {
        return derivativeValuationMethod;
    }

    /**
     * Message field setter.
     * @param derivativeValuationMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeValuationMethod)
    public void setDerivativeValuationMethod(FuturesValuationMethod derivativeValuationMethod) {
        this.derivativeValuationMethod = derivativeValuationMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeListMethod)
    public ListMethod getDerivativeListMethod() {
        return derivativeListMethod;
    }

    /**
     * Message field setter.
     * @param derivativeListMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeListMethod)
    public void setDerivativeListMethod(ListMethod derivativeListMethod) {
        this.derivativeListMethod = derivativeListMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeCapPrice)
    public Double getDerivativeCapPrice() {
        return derivativeCapPrice;
    }

    /**
     * Message field setter.
     * @param derivativeCapPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeCapPrice)
    public void setDerivativeCapPrice(Double derivativeCapPrice) {
        this.derivativeCapPrice = derivativeCapPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeFloorPrice)
    public Double getDerivativeFloorPrice() {
        return derivativeFloorPrice;
    }

    /**
     * Message field setter.
     * @param derivativeFloorPrice field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeFloorPrice)
    public void setDerivativeFloorPrice(Double derivativeFloorPrice) {
        this.derivativeFloorPrice = derivativeFloorPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePutOrCall)
    public PutOrCall getDerivativePutOrCall() {
        return derivativePutOrCall;
    }

    /**
     * Message field setter.
     * @param derivativePutOrCall field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePutOrCall)
    public void setDerivativePutOrCall(PutOrCall derivativePutOrCall) {
        this.derivativePutOrCall = derivativePutOrCall;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivFlexProductEligibilityIndicator)
    public Boolean getDerivFlexProductEligibilityIndicator() {
        return derivFlexProductEligibilityIndicator;
    }

    /**
     * Message field setter.
     * @param derivFlexProductEligibilityIndicator field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivFlexProductEligibilityIndicator)
    public void setDerivFlexProductEligibilityIndicator(Boolean derivFlexProductEligibilityIndicator) {
        this.derivFlexProductEligibilityIndicator = derivFlexProductEligibilityIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeTimeUnit)
    public TimeUnit getDerivativeTimeUnit() {
        return derivativeTimeUnit;
    }

    /**
     * Message field setter.
     * @param derivativeTimeUnit field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeTimeUnit)
    public void setDerivativeTimeUnit(TimeUnit derivativeTimeUnit) {
        this.derivativeTimeUnit = derivativeTimeUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePositionLimit)
    public Integer getDerivativePositionLimit() {
        return derivativePositionLimit;
    }

    /**
     * Message field setter.
     * @param derivativePositionLimit field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativePositionLimit)
    public void setDerivativePositionLimit(Integer derivativePositionLimit) {
        this.derivativePositionLimit = derivativePositionLimit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeNTPositionLimit)
    public Integer getDerivativeNTPositionLimit() {
        return derivativeNTPositionLimit;
    }

    /**
     * Message field setter.
     * @param derivativeNTPositionLimit field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeNTPositionLimit)
    public void setDerivativeNTPositionLimit(Integer derivativeNTPositionLimit) {
        this.derivativeNTPositionLimit = derivativeNTPositionLimit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSymbol, required = true)
    public String getDerivativeSymbol() {
        return derivativeSymbol;
    }

    /**
     * Message field setter.
     * @param derivativeSymbol field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSymbol, required = true)
    public void setDerivativeSymbol(String derivativeSymbol) {
        this.derivativeSymbol = derivativeSymbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSymbolSfx)
    public String getDerivativeSymbolSfx() {
        return derivativeSymbolSfx;
    }

    /**
     * Message field setter.
     * @param derivativeSymbolSfx field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeSymbolSfx)
    public void setDerivativeSymbolSfx(String derivativeSymbolSfx) {
        this.derivativeSymbolSfx = derivativeSymbolSfx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeSecurityXML getDerivativeSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SecurityXML component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setDerivativeSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SecurityXML component to null.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearDerivativeSecurityXML() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeContractSettlMonth)
    public String getDerivativeContractSettlMonth() {
        return derivativeContractSettlMonth;
    }

    /**
     * Message field setter.
     * @param derivativeContractSettlMonth field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.DerivativeContractSettlMonth)
    public void setDerivativeContractSettlMonth(String derivativeContractSettlMonth) {
        this.derivativeContractSettlMonth = derivativeContractSettlMonth;
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoDerivativeEvents)
    public Integer getNoDerivativeEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param noDerivativeEvents field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoDerivativeEvents)
    public void setNoDerivativeEvents(Integer noDerivativeEvents) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link DerivativeEventGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeEventGroup[] getDerivativeEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link DerivativeEventGroup} object to the existing array of <code>derivativeEvents</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noDerivativeEvents</code> field to the proper value.<br/>
     * Note: If the <code>setNoDerivativeEvents</code> method has been called there will already be a number of objects in the
     * <code>derivativeEvents</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeEventGroup addDerivativeEvent() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link DerivativeEventGroup} object from the existing array of <code>derivativeEvents</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDerivativeEvents</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeEventGroup deleteDerivativeEvent(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link DerivativeEventGroup} objects from the <code>derivativeEvents</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDerivativeEvents</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0SP1")
    public int clearDerivativeEvents() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    public DerivativeInstrumentParties getDerivativeInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DerivativeInstrumentParties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setDerivativeInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DerivativeInstrumentParties component to null.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearDerivativeInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.DerivativeSymbol.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (derivativeSymbol == null) {
            errorMsg.append(" [DerivativeSymbol]");
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
            TagEncoder.encode(bao, TagNum.DerivativeSymbol, derivativeSymbol, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeSymbolSfx, derivativeSymbolSfx, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeSecurityID, derivativeSecurityID, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeSecurityIDSource, derivativeSecurityIDSource, sessionCharset);
            if (noDerivativeSecurityAltID != null && noDerivativeSecurityAltID.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoDerivativeSecurityAltID, noDerivativeSecurityAltID);
                if (derivativeSecurityAltIDGroups != null && derivativeSecurityAltIDGroups.length == noDerivativeSecurityAltID.intValue()) {
                    for (int i = 0; i < noDerivativeSecurityAltID.intValue(); i++) {
                        if (derivativeSecurityAltIDGroups[i] != null) {
                            bao.write(derivativeSecurityAltIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "DerivativeSecurityAltIDGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoSecurityAltID.getValue(), error);
                }
            }
            if (derivativeProduct != null) {
                TagEncoder.encode(bao, TagNum.DerivativeProduct, derivativeProduct.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativeProductComplex, derivativeProductComplex, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivFlexProductEligibilityIndicator, derivFlexProductEligibilityIndicator);
            TagEncoder.encode(bao, TagNum.DerivativeSecurityGroup, derivativeSecurityGroup, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeCFICode, derivativeCFICode, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeSecurityType, derivativeSecurityType);
            TagEncoder.encode(bao, TagNum.DerivativeSecuritySubType, derivativeSecuritySubType, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeMaturityMonthYear, derivativeMaturityMonthYear, sessionCharset);
            TagEncoder.encodeDate(bao, TagNum.DerivativeMaturityDate, derivativeMaturityDate);
            TagEncoder.encodeTZTime(bao, TagNum.DerivativeMaturityTime, derivativeMaturityTime);
            TagEncoder.encode(bao, TagNum.DerivativeSettleOnOpenFlag, derivativeSettleOnOpenFlag, sessionCharset);
            if (derivativeInstrmtAssignmentMethod != null) {
                TagEncoder.encode(bao, TagNum.DerivativeInstrmtAssignmentMethod, derivativeInstrmtAssignmentMethod.getValue());
            }
            if (derivativeSecurityStatus != null) {
                TagEncoder.encode(bao, TagNum.DerivativeSecurityStatus, derivativeSecurityStatus.getValue());
            }
            TagEncoder.encodeDate(bao, TagNum.DerivativeIssueDate, derivativeIssueDate);
            TagEncoder.encode(bao, TagNum.DerivativeInstrRegistry, derivativeInstrRegistry, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeCountryOfIssue, derivativeCountryOfIssue, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeStateOrProvinceOfIssue, derivativeStateOrProvinceOfIssue, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeLocaleOfIssue, derivativeLocaleOfIssue, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativeStrikePrice, derivativeStrikePrice);
            if (derivativeStrikeCurrency != null) {
                TagEncoder.encode(bao, TagNum.DerivativeStrikeCurrency, derivativeStrikeCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativeStrikeMultiplier, derivativeStrikeMultiplier);
            TagEncoder.encode(bao, TagNum.DerivativeOptAttribute, derivativeOptAttribute);
            TagEncoder.encode(bao, TagNum.DerivativeContractMultiplier, derivativeContractMultiplier);
            if (derivativeContractMultiplierUnit != null) {
                TagEncoder.encode(bao, TagNum.DerivativeContractMultiplierUnit, derivativeContractMultiplierUnit.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativeFlowScheduleType, derivativeFlowScheduleType);
            TagEncoder.encode(bao, TagNum.DerivativeMinPriceIncrement, derivativeMinPriceIncrement);
            TagEncoder.encode(bao, TagNum.DerivativeMinPriceIncrementAmount, derivativeMinPriceIncrementAmount);
            if (derivativeUnitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.DerivativeUnitOfMeasure, derivativeUnitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativeUnitOfMeasureQty, derivativeUnitOfMeasureQty);
            if (derivativePriceUnitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.DerivativePriceUnitOfMeasure, derivativePriceUnitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativePriceUnitOfMeasureQty, derivativePriceUnitOfMeasureQty);
            if (derivativeSettlMethod != null) {
                TagEncoder.encode(bao, TagNum.DerivativeSettlMethod, derivativeSettlMethod.getValue());
            }
            if (derivativeExerciseStyle != null) {
                TagEncoder.encode(bao, TagNum.DerivativeExerciseStyle, derivativeExerciseStyle.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativeOptPayAmount, derivativeOptPayAmount);
            if (derivativePriceQuoteMethod != null) {
                TagEncoder.encode(bao, TagNum.DerivativePriceQuoteMethod, derivativePriceQuoteMethod.getValue());
            }
            if (derivativeValuationMethod != null) {
                TagEncoder.encode(bao, TagNum.DerivativeValuationMethod, derivativeValuationMethod.getValue());
            }
            if (derivativeListMethod != null) {
                TagEncoder.encode(bao, TagNum.DerivativeListMethod, derivativeListMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativeCapPrice, derivativeCapPrice);
            TagEncoder.encode(bao, TagNum.DerivativeFloorPrice, derivativeFloorPrice);
            if (derivativePutOrCall != null) {
                TagEncoder.encode(bao, TagNum.DerivativePutOrCall, derivativePutOrCall.getValue());
            }
            if (derivativeTimeUnit != null) {
                TagEncoder.encode(bao, TagNum.DerivativeTimeUnit, derivativeTimeUnit.getValue());
            }
            TagEncoder.encode(bao, TagNum.DerivativeSecurityExchange, derivativeSecurityExchange, sessionCharset);
            TagEncoder.encode(bao, TagNum.DerivativePositionLimit, derivativePositionLimit);
            TagEncoder.encode(bao, TagNum.DerivativeNTPositionLimit, derivativeNTPositionLimit);
            TagEncoder.encode(bao, TagNum.DerivativeIssuer, derivativeIssuer, sessionCharset);
            if (derivativeEncodedIssuerLen != null && derivativeEncodedIssuerLen.intValue() > 0) {
                if (derivativeEncodedIssuer != null && derivativeEncodedIssuer.length > 0) {
                    derivativeEncodedIssuerLen = new Integer(derivativeEncodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.DerivativeEncodedIssuerLen, derivativeEncodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.DerivativeEncodedIssuer, derivativeEncodedIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.DerivativeSecurityDesc, derivativeSecurityDesc, sessionCharset);
            if (derivativeEncodedSecurityDescLen != null && derivativeEncodedSecurityDescLen.intValue() > 0) {
                if (derivativeEncodedSecurityDesc != null && derivativeEncodedSecurityDesc.length > 0) {
                    derivativeEncodedSecurityDescLen = new Integer(derivativeEncodedSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.DerivativeEncodedSecurityDescLen, derivativeEncodedSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.DerivativeEncodedSecurityDesc, derivativeEncodedSecurityDesc);
                }
            }
            if (derivativeSecurityXML != null) {
                bao.write(derivativeSecurityXML.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.DerivativeContractSettlMonth, derivativeContractSettlMonth, sessionCharset);
            if (noDerivativeEvents != null && noDerivativeEvents.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoDerivativeEvents, noDerivativeEvents);
                if (derivativeEvents != null && derivativeEvents.length == noDerivativeEvents.intValue()) {
                    for (int i = 0; i < noDerivativeEvents.intValue(); i++) {
                        if (derivativeEvents[i] != null) {
                            bao.write(derivativeEvents[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "DerivativeEventGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoEvents.getValue(), error);
                }
            }
            if (derivativeInstrumentParties != null) {
                bao.write(derivativeInstrumentParties.encode(MsgSecureType.ALL_FIELDS));
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
            case DerivativeSymbol:
                derivativeSymbol = new String(tag.value, sessionCharset);;
                break;

            case DerivativeSymbolSfx:
                derivativeSymbolSfx = new String(tag.value, sessionCharset);
                break;

            case DerivativeSecurityID:
                derivativeSecurityID = new String(tag.value, sessionCharset);
                break;

            case DerivativeSecurityIDSource:
                derivativeSecurityIDSource = new String(tag.value, sessionCharset);
                break;

            case NoDerivativeSecurityAltID:
                noDerivativeSecurityAltID = new Integer(new String(tag.value, sessionCharset));
                break;

            case DerivativeProduct:
                derivativeProduct = Product.valueFor(new Integer(new String(tag.value, sessionCharset)).intValue());
                break;

            case DerivativeProductComplex:
                derivativeProductComplex = new String(tag.value, sessionCharset);
                break;
                
            case DerivFlexProductEligibilityIndicator:
                derivFlexProductEligibilityIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case DerivativeSecurityGroup:
                derivativeSecurityGroup = new String(tag.value, sessionCharset);
                break;

            case DerivativeCFICode:
                derivativeCFICode = new String(tag.value, sessionCharset);
                break;

            case DerivativeSecurityType:
                derivativeSecurityType = new String(tag.value, sessionCharset);
                break;

            case DerivativeSecuritySubType:
                derivativeSecuritySubType = new String(tag.value, sessionCharset);
                break;

            case DerivativeMaturityMonthYear:
                derivativeMaturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case DerivativeMaturityDate:
                derivativeMaturityDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case DerivativeMaturityTime:
                derivativeMaturityTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case DerivativeSettleOnOpenFlag:
                derivativeSettleOnOpenFlag = new String(tag.value, sessionCharset);
                break;

            case DerivativeInstrmtAssignmentMethod:
                derivativeInstrmtAssignmentMethod = InstrmtAssignmentMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DerivativeSecurityStatus:
                derivativeSecurityStatus = SecurityStatus.valueFor(new String(tag.value, sessionCharset));
                break;

            case DerivativeIssueDate:
                derivativeIssueDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case DerivativeInstrRegistry:
                derivativeInstrRegistry = new String(tag.value, sessionCharset);
                break;

            case DerivativeCountryOfIssue:
                derivativeCountryOfIssue = new String(tag.value, sessionCharset);
                break;

            case DerivativeStateOrProvinceOfIssue:
                derivativeStateOrProvinceOfIssue = new String(tag.value, sessionCharset);
                break;

            case DerivativeLocaleOfIssue:
                derivativeLocaleOfIssue = new String(tag.value, sessionCharset);
                break;

            case DerivativeStrikePrice:
                derivativeStrikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeStrikeCurrency:
                derivativeStrikeCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case DerivativeStrikeMultiplier:
                derivativeStrikeMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeOptAttribute:
                derivativeOptAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DerivativeContractMultiplier:
                derivativeContractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeContractMultiplierUnit:
                derivativeContractMultiplierUnit = ContractMultiplierUnit.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DerivativeFlowScheduleType:
                derivativeFlowScheduleType = new Integer(new String(tag.value, sessionCharset));
                break;

            case DerivativeMinPriceIncrement:
                derivativeMinPriceIncrement = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeMinPriceIncrementAmount:
                derivativeMinPriceIncrementAmount = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeUnitOfMeasure:
                derivativeUnitOfMeasure = UnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case DerivativeUnitOfMeasureQty:
                derivativeUnitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativePriceUnitOfMeasure:
                derivativePriceUnitOfMeasure = PriceUnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case DerivativePriceUnitOfMeasureQty:
                derivativePriceUnitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeSettlMethod:
                derivativeSettlMethod = SettlMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DerivativeExerciseStyle:
                derivativeExerciseStyle = ExerciseStyle.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case DerivativeOptPayAmount:
                derivativeOptPayAmount = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativePriceQuoteMethod:
                derivativePriceQuoteMethod = PriceQuoteMethod.valueFor(new String(tag.value, sessionCharset));
                break;

            case DerivativeValuationMethod:
                derivativeValuationMethod = FuturesValuationMethod.valueFor(new String(tag.value, sessionCharset));
                break;

            case DerivativeListMethod:
                derivativeListMethod = ListMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DerivativeCapPrice:
                derivativeCapPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativeFloorPrice:
                derivativeFloorPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case DerivativePutOrCall:
                derivativePutOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case DerivativeTimeUnit:
                derivativeTimeUnit = TimeUnit.valueFor(new String(tag.value, sessionCharset));
                break;

            case DerivativeSecurityExchange:
                derivativeSecurityExchange = new String(tag.value, sessionCharset);
                break;

            case DerivativePositionLimit:
                derivativePositionLimit = new Integer(new String(tag.value, sessionCharset));
                break;

            case DerivativeNTPositionLimit:
                derivativeNTPositionLimit = new Integer(new String(tag.value, sessionCharset));
                break;

            case DerivativeIssuer:
                derivativeIssuer = new String(tag.value, sessionCharset);
                break;

            case DerivativeSecurityDesc:
                derivativeSecurityDesc = new String(tag.value, sessionCharset);
                break;

            case DerivativeContractSettlMonth:
                derivativeContractSettlMonth = new String(tag.value, sessionCharset);
                break;

            case NoDerivativeEvents:
                noDerivativeEvents = new Integer(new String(tag.value, getSessionCharset()));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeInstrument] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {

        ByteBuffer result = message;
        if (tag.tagNum == TagNum.DerivativeEncodedIssuerLen.getValue()) {
            try {
                derivativeEncodedIssuerLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat,
                        TagNum.EncodedIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, derivativeEncodedIssuerLen.intValue());
            derivativeEncodedIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.DerivativeEncodedSecurityDescLen.getValue()) {
            try {
                derivativeEncodedSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedSecurityDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat,
                        TagNum.EncodedSecurityDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, derivativeEncodedSecurityDescLen.intValue());
            derivativeEncodedSecurityDesc = dataTag.value;
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
        printTagValue(b, TagNum.DerivativeSymbol, derivativeSymbol);
        printTagValue(b, TagNum.DerivativeSymbolSfx, derivativeSymbolSfx);
        printTagValue(b, TagNum.DerivativeSecurityID, derivativeSecurityID);
        printTagValue(b, TagNum.DerivativeSecurityIDSource, derivativeSecurityIDSource);
        printTagValue(b, TagNum.NoDerivativeSecurityAltID, noDerivativeSecurityAltID);
        printTagValue(b, derivativeSecurityAltIDGroups);
        printTagValue(b, TagNum.DerivativeProduct, derivativeProduct);
        printTagValue(b, TagNum.DerivativeProductComplex, derivativeProductComplex);
        printTagValue(b, TagNum.DerivativeSecurityGroup, derivativeSecurityGroup);
        printTagValue(b, TagNum.DerivativeCFICode, derivativeCFICode);
        printTagValue(b, TagNum.DerivativeSecurityType, derivativeSecurityType);
        printTagValue(b, TagNum.DerivativeSecuritySubType, derivativeSecuritySubType);
        printTagValue(b, TagNum.DerivativeMaturityMonthYear, derivativeMaturityMonthYear);
        printDateTagValue(b, TagNum.DerivativeMaturityDate, derivativeMaturityDate);
        printTimeTagValue(b, TagNum.DerivativeMaturityTime, derivativeMaturityTime);
        printTagValue(b, TagNum.DerivativeSettleOnOpenFlag, derivativeSettleOnOpenFlag);
        printTagValue(b, TagNum.DerivativeInstrmtAssignmentMethod, derivativeInstrmtAssignmentMethod);
        printTagValue(b, TagNum.DerivativeSecurityStatus, derivativeSecurityStatus);
        printTagValue(b, TagNum.DerivativeInstrRegistry, derivativeInstrRegistry);
        printTagValue(b, TagNum.DerivativeCountryOfIssue, derivativeCountryOfIssue);
        printTagValue(b, TagNum.DerivativeStateOrProvinceOfIssue, derivativeStateOrProvinceOfIssue);
        printTagValue(b, TagNum.DerivativeLocaleOfIssue, derivativeLocaleOfIssue);
        printTagValue(b, TagNum.DerivativeStrikePrice, derivativeStrikePrice);
        printTagValue(b, TagNum.DerivativeStrikeCurrency, derivativeStrikeCurrency);
        printTagValue(b, TagNum.DerivativeStrikeMultiplier, derivativeStrikeMultiplier);
        printTagValue(b, TagNum.DerivativeOptAttribute, derivativeOptAttribute);
        printTagValue(b, TagNum.DerivativeContractMultiplier, derivativeContractMultiplier);
        printTagValue(b, TagNum.DerivativeContractMultiplierUnit, derivativeContractMultiplierUnit);
        printTagValue(b, TagNum.DerivativeFlowScheduleType, derivativeFlowScheduleType);
        printTagValue(b, TagNum.DerivativeMinPriceIncrement, derivativeMinPriceIncrement);
        printTagValue(b, TagNum.DerivativeMinPriceIncrementAmount, derivativeMinPriceIncrementAmount);
        printTagValue(b, TagNum.DerivativeUnitOfMeasure, derivativeUnitOfMeasure);
        printTagValue(b, TagNum.DerivativeUnitOfMeasureQty, derivativeUnitOfMeasureQty);
        printTagValue(b, TagNum.DerivativePriceUnitOfMeasure, derivativePriceUnitOfMeasure);
        printTagValue(b, TagNum.DerivativePriceUnitOfMeasureQty, derivativePriceUnitOfMeasureQty);
        printTagValue(b, TagNum.DerivativeSettlMethod, derivativeSettlMethod);
        printTagValue(b, TagNum.DerivativeExerciseStyle, derivativeExerciseStyle);
        printTagValue(b, TagNum.DerivativePriceQuoteMethod, derivativePriceQuoteMethod);
        printTagValue(b, TagNum.DerivativeValuationMethod, derivativeValuationMethod);
        printTagValue(b, TagNum.DerivativeListMethod, derivativeListMethod);
        printTagValue(b, TagNum.DerivativeCapPrice, derivativeCapPrice);
        printTagValue(b, TagNum.DerivativeFloorPrice, derivativeFloorPrice);
        printTagValue(b, TagNum.DerivativePutOrCall, derivativePutOrCall);
        printTagValue(b, TagNum.DerivFlexProductEligibilityIndicator, derivFlexProductEligibilityIndicator);
        printTagValue(b, TagNum.DerivativeTimeUnit, derivativeTimeUnit);
        printTagValue(b, TagNum.DerivativeSecurityExchange, derivativeSecurityExchange);
        printTagValue(b, TagNum.DerivativePositionLimit, derivativePositionLimit);
        printTagValue(b, TagNum.DerivativeNTPositionLimit, derivativeNTPositionLimit);
        printTagValue(b, TagNum.DerivativeIssuer, derivativeIssuer);
        printTagValue(b, TagNum.DerivativeEncodedIssuerLen, derivativeEncodedIssuerLen);
        printTagValue(b, TagNum.DerivativeEncodedIssuer, derivativeEncodedIssuer);
        printTagValue(b, TagNum.DerivativeSecurityDesc, derivativeSecurityDesc);
        printTagValue(b, TagNum.DerivativeEncodedSecurityDescLen, derivativeEncodedSecurityDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, derivativeEncodedSecurityDesc);
        printTagValue(b, derivativeSecurityXML);
        printTagValue(b, TagNum.DerivativeContractSettlMonth, derivativeContractSettlMonth);
        printTagValue(b, TagNum.NoDerivativeEvents, noDerivativeEvents);
        printTagValue(b, derivativeEvents);
        printTagValue(b, derivativeInstrumentParties);
        b.append("}");

        return b.toString();
    }

     // </editor-fold>
}
