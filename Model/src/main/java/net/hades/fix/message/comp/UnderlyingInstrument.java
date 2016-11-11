/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument.java
 *
 * $Id: UnderlyingInstrument.java,v 1.11 2011-04-16 07:38:27 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.DateConverter;
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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.UnderlyingSecurityAltIDGroup;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FXRateCalc;
import net.hades.fix.message.type.FlowScheduleType;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.RestructuringType;
import net.hades.fix.message.type.Seniority;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnderlyingSettlementType;
import net.hades.fix.message.type.UnitOfMeasure;
import net.hades.fix.message.util.TagEncoder;

/**
 * The UnderlyingInstrument component block, like the Instrument component block,
 * contains all the fields commonly used to describe a security or instrument.
 * In the case of the UnderlyingInstrument component block it describes an instrument
 * which underlies the primary instrument.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 16/12/2008, 7:24:31 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UnderlyingInstrument extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingSymbol.getValue(),
        TagNum.UnderlyingSymbolSfx.getValue(),
        TagNum.UnderlyingSecurityID.getValue(),
        TagNum.UnderlyingSecurityIDSource.getValue(),
        TagNum.NoUnderlyingSecurityAltID.getValue(),
        TagNum.UnderlyingProduct.getValue(),
        TagNum.UnderlyingCFICode.getValue(),
        TagNum.UnderlyingSecurityType.getValue(),
        TagNum.UnderlyingSecuritySubType.getValue(),
        TagNum.UnderlyingMaturityMonthYear.getValue(),
        TagNum.UnderlyingMaturityDate.getValue(),
        TagNum.UnderlyingMaturityTime.getValue(),
        TagNum.UnderlyingCouponPaymentDate.getValue(),
        TagNum.UnderlyingRestructuringType.getValue(),
        TagNum.UnderlyingSeniority.getValue(),
        TagNum.UnderlyingNotionalPercentageOutstanding.getValue(),
        TagNum.UnderlyingOriginalNotionalPercentageOutstanding.getValue(),
        TagNum.UnderlyingAttachmentPoint.getValue(),
        TagNum.UnderlyingDetachmentPoint.getValue(),
        TagNum.UnderlyingIssueDate.getValue(),
        TagNum.UnderlyingRepoCollateralSecurityType.getValue(),
        TagNum.UnderlyingRepurchaseTerm.getValue(),
        TagNum.UnderlyingRepurchaseRate.getValue(),
        TagNum.UnderlyingFactor.getValue(),
        TagNum.UnderlyingCreditRating.getValue(),
        TagNum.UnderlyingInstrRegistry.getValue(),
        TagNum.UnderlyingCountryOfIssue.getValue(),
        TagNum.UnderlyingStateOrProvinceOfIssue.getValue(),
        TagNum.UnderlyingLocaleOfIssue.getValue(),
        TagNum.UnderlyingRedemptionDate.getValue(),
        TagNum.UnderlyingPutOrCall.getValue(),
        TagNum.UnderlyingStrikePrice.getValue(),
        TagNum.UnderlyingStrikeCurrency.getValue(),
        TagNum.UnderlyingOptAttribute.getValue(),
        TagNum.UnderlyingContractMultiplier.getValue(),
        TagNum.UnderlyingContractMultiplierUnit.getValue(),
        TagNum.UnderlyingFlowScheduleType.getValue(),
        TagNum.UnderlyingUnitOfMeasure.getValue(),
        TagNum.UnderlyingUnitOfMeasureQty.getValue(),
        TagNum.UnderlyingPriceUnitOfMeasure.getValue(),
        TagNum.UnderlyingPriceUnitOfMeasureQty.getValue(),
        TagNum.UnderlyingTimeUnit.getValue(),
        TagNum.UnderlyingExerciseStyle.getValue(),
        TagNum.UnderlyingCouponRate.getValue(),
        TagNum.UnderlyingSecurityExchange.getValue(),
        TagNum.UnderlyingIssuer.getValue(),
        TagNum.UnderlyingSecurityDesc.getValue(),
        TagNum.UnderlyingCPProgram.getValue(),
        TagNum.UnderlyingCPRegType.getValue(),
        TagNum.UnderlyingAllocationPercent.getValue(),
        TagNum.UnderlyingCurrency.getValue(),
        TagNum.UnderlyingQty.getValue(),
        TagNum.UnderlyingSettlementType.getValue(),
        TagNum.UnderlyingCashAmount.getValue(),
        TagNum.UnderlyingCashType.getValue(),
        TagNum.UnderlyingPx.getValue(),
        TagNum.UnderlyingDirtyPrice.getValue(),
        TagNum.UnderlyingEndPrice.getValue(),
        TagNum.UnderlyingStartValue.getValue(),
        TagNum.UnderlyingCurrentValue.getValue(),
        TagNum.UnderlyingEndValue.getValue(),
        TagNum.UnderlyingAdjustedQuantity.getValue(),
        TagNum.UnderlyingFXRate.getValue(),
        TagNum.UnderlyingFXRateCalc.getValue(),
        TagNum.UnderlyingCapValue.getValue(),
        TagNum.UnderlyingSettlMethod.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedUnderlyingIssuerLen.getValue(),
        TagNum.EncodedUnderlyingSecurityDescLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingSymbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 311. Starting with 4.4 version.
     */
    protected String underlyingSymbol;

    /**
     * TagNum = 312. Starting with 4.4 version.
     */
    protected String underlyingSymbolSfx;

    /**
     * TagNum = 309. Starting with 4.4 version.
     */
    protected String underlyingSecurityID;

    /**
     * TagNum = 305. Starting with 4.4 version.
     */
    protected String underlyingSecurityIDSource;

    /**
     * TagNum = 457. Starting with 4.4 version.
     */
    protected Integer noUnderlyingSecurityAltID;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingSecurityAltIDGroup[] underlyingSecurityAltIDGroups;

    /**
     * TagNum = 462. Starting with 4.4 version.
     */
    protected Product underlyingProduct;

    /**
     * TagNum = 463. Starting with 4.4 version.
     */
    protected String underlyingCFICode;

    /**
     * TagNum = 310. Starting with 4.4 version.
     */
    protected String underlyingSecurityType;

    /**
     * TagNum = 763. Starting with 4.4 version.
     */
    protected String underlyingSecuritySubType;

    /**
     * TagNum = 313. Starting with 4.4 version.
     */
    protected String underlyingMaturityMonthYear;

    /**
     * TagNum = 542. Starting with 4.4 version.
     */
    protected Date underlyingMaturityDate;

    /**
     * TagNum = 1213. Starting with 5.0SP1 version.
     */
    protected Date underlyingMaturityTime;

    /**
     * TagNum = 241. Starting with 4.4 version.
     */
    protected Date underlyingCouponPaymentDate;

    /**
     * TagNum = 1453. Starting with 5.0SP2 version.
     */
    protected RestructuringType underlyingRestructuringType;

    /**
     * TagNum = 1454. Starting with 5.0SP2 version.
     */
    protected Seniority underlyingSeniority;

    /**
     * TagNum = 1455. Starting with 5.0SP2 version.
     */
    protected Double underlyingNotionalPercentageOutstanding;

    /**
     * TagNum = 1456. Starting with 5.0SP2 version.
     */
    protected Double underlyingOriginalNotionalPercentageOutstanding;

    /**
     * TagNum = 1459. Starting with 5.0SP2 version.
     */
    protected Double underlyingAttachmentPoint;

    /**
     * TagNum = 1460. Starting with 5.0SP2 version.
     */
    protected Double underlyingDetachmentPoint;

    /**
     * TagNum = 242. Starting with 4.4 version.
     */
    protected Date underlyingIssueDate;

    /**
     * TagNum = 243. Starting with 4.4 version.
     */
    protected String underlyingRepoCollateralSecurityType;

    /**
     * TagNum = 244. Starting with 4.4 version.
     */
    protected Integer underlyingRepurchaseTerm;

    /**
     * TagNum = 245. Starting with 4.4 version.
     */
    protected Double underlyingRepurchaseRate;

    /**
     * TagNum = 246. Starting with 4.4 version.
     */
    protected Double underlyingFactor;

    /**
     * TagNum = 256. Starting with 4.4 version.
     */
    protected String underlyingCreditRating;

    /**
     * TagNum = 595. Starting with 4.4 version.
     */
    protected String underlyingInstrRegistry;

    /**
     * TagNum = 592. Starting with 4.4 version.
     */
    protected String underlyingCountryOfIssue;

    /**
     * TagNum = 593. Starting with 4.4 version.
     */
    protected String underlyingStateOrProvinceOfIssue;

    /**
     * TagNum = 594. Starting with 4.4 version.
     */
    protected String underlyingLocaleOfIssue;

    /**
     * TagNum = 247. Starting with 4.4 version.
     */
    protected Date underlyingRedemptionDate;

    /**
     * TagNum = 316. Starting with 4.4 version.
     */
    protected Double underlyingStrikePrice;

    /**
     * TagNum = 941. Starting with 4.4 version.
     */
    protected Currency underlyingStrikeCurrency;

    /**
     * TagNum = 317. Starting with 4.4 version.
     */
    protected Character underlyingOptAttribute;

    /**
     * TagNum = 436. Starting with 4.4 version.
     */
    protected Double underlyingContractMultiplier;

    /**
     * TagNum = 1437. Starting with 5.0SP2 version.
     */
    protected ContractMultiplierUnit underlyingContractMultiplierUnit;

    /**
     * TagNum = 1441. Starting with 5.0SP2 version.
     */
    protected FlowScheduleType underlyingFlowScheduleType;

    /**
     * TagNum = 998. Starting with 5.0 version.
     */
    protected UnitOfMeasure underlyingUnitOfMeasure;

    /**
     * TagNum = 1423. Starting with 5.0SP1 version.
     */
    protected Double underlyingUnitOfMeasureQty;

    /**
     * TagNum = 1424. Starting with 5.0SP1 version.
     */
    protected PriceUnitOfMeasure underlyingPriceUnitOfMeasure;

    /**
     * TagNum = 1425. Starting with 5.0SP1 version.
     */
    protected Double underlyingPriceUnitOfMeasureQty;

    /**
     * TagNum = 1000. Starting with 5.0 version.
     */
    protected TimeUnit underlyingTimeUnit;

    /**
     * TagNum = 1419. Starting with 5.0SP1 version.
     */
    protected ExerciseStyle underlyingExerciseStyle;

    /**
     * TagNum = 435. Starting with 4.4 version.
     */
    protected Double underlyingCouponRate;

    /**
     * TagNum = 308. Starting with 4.4 version.
     */
    protected String underlyingSecurityExchange;

    /**
     * TagNum = 306. Starting with 4.4 version.
     */
    protected String underlyingIssuer;

    /**
     * TagNum = 362. Starting with 4.4 version.
     */
    protected Integer encodedUnderlyingIssuerLen;

    /**
     * TagNum = 363. Starting with 4.4 version.
     */
    protected byte[] encodedUnderlyingIssuer;

    /**
     * TagNum = 307. Starting with 4.4 version.
     */
    protected String underlyingSecurityDesc;

    /**
     * TagNum = 364. Starting with 4.4 version.
     */
    protected Integer encodedUnderlyingSecurityDescLen;

    /**
     * TagNum = 365. Starting with 4.4 version.
     */
    protected byte[] encodedUnderlyingSecurityDesc;

    /**
     * TagNum = 877. Starting with 4.4 version.
     */
    protected String underlyingCPProgram;

    /**
     * TagNum = 878. Starting with 4.4 version.
     */
    protected String underlyingCPRegType;

    /**
     * TagNum = 972. Starting with 5.0 version.
     */
    protected Double underlyingAllocationPercent;

    /**
     * TagNum = 318. Starting with 4.4 version.
     */
    protected Currency underlyingCurrency;

    /**
     * TagNum = 879. Starting with 4.4 version.
     */
    protected Double underlyingQty;

    /**
     * TagNum = 975. Starting with 5.0 version.
     */
    protected UnderlyingSettlementType underlyingSettlementType;

    /**
     * TagNum = 973. Starting with 5.0 version.
     */
    protected Double underlyingCashAmount;

    /**
     * TagNum = 974. Starting with 5.0 version.
     */
    protected String underlyingCashType;

    /**
     * TagNum = 810. Starting with 4.4 version.
     */
    protected Double underlyingPx;

    /**
     * TagNum = 882. Starting with 4.4 version.
     */
    protected Double underlyingDirtyPrice;

    /**
     * TagNum = 883. Starting with 4.4 version.
     */
    protected Double underlyingEndPrice;

    /**
     * TagNum = 884. Starting with 4.4 version.
     */
    protected Double underlyingStartValue;

    /**
     * TagNum = 885. Starting with 4.4 version.
     */
    protected Double underlyingCurrentValue;

    /**
     * TagNum = 886. Starting with 4.4 version.
     */
    protected Double underlyingEndValue;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingStipulations underlyingStipulations;

    /**
     * TagNum = 1044. Starting with 5.0 version.
     */
    protected Double underlyingAdjustedQuantity;

    /**
     * TagNum = 1045. Starting with 5.0 version.
     */
    protected Double underlyingFXRate;

    /**
     * TagNum = 1046. Starting with 5.0 version.
     */
    protected FXRateCalc underlyingFXRateCalc;

    /**
     * TagNum = 1038. Starting with 5.0 version.
     */
    protected Double underlyingCapValue;

    /**
     * Starting with 5.0 version.
     */
    protected UndlyInstrumentParties undlyInstrumentParties;

    /**
     * TagNum = 1039. Starting with 5.0 version.
     */
    protected String underlyingSettlMethod;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingInstrument() {
    }

    public UnderlyingInstrument(FragmentContext context) {
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbol, required=true, condRequired=true)
    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    /**
     * Message field setter.
     * @param underlyingSymbol field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbol, required=true, condRequired=true)
    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbolSfx)
    public String getUnderlyingSymbolSfx() {
        return underlyingSymbolSfx;
    }

    /**
     * Message field setter.
     * @param underlyingSymbolSfx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSymbolSfx)
    public void setUnderlyingSymbolSfx(String underlyingSymbolSfx) {
        this.underlyingSymbolSfx = underlyingSymbolSfx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityID)
    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityID)
    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityIDSource)
    public String getUnderlyingSecurityIDSource() {
        return underlyingSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityIDSource field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityIDSource)
    public void setUnderlyingSecurityIDSource(String underlyingSecurityIDSource) {
        this.underlyingSecurityIDSource = underlyingSecurityIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyingSecurityAltID)
    public Integer getNoUnderlyingSecurityAltID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingSecurityAltIDGroup} groups. It will also create an array
     * of {@link UnderlyingSecurityAltIDGroup} objects and set the <code>underlyingSecurityAltIDGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingSecurityAltIDGroups</code> array they will be discarded.<br/>
     * @param noUnderlyingSecurityAltID number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyingSecurityAltID)
    public void setNoUnderlyingSecurityAltID(Integer noUnderlyingSecurityAltID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for <code>UnderlyingSecurityAltIDGroup</code> array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public UnderlyingSecurityAltIDGroup[] getUnderlyingSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UnderlyingSecurityAltIDGroup} object to the existing array of
     * <code>underlyingSecurityAltIDGroups</code> and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyingSecurityAltID</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyingSecurityAltID</code> method has been called there will already be a
     * number of objects in the <code>underlyingSecurityAltIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public UnderlyingSecurityAltIDGroup addUnderlyingSecurityAltIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingSecurityAltIDGroup} object from the existing array
     * of <code>underlyingSecurityAltIDGroups</code> and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyingSecurityAltID</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public UnderlyingSecurityAltIDGroup deleteUnderlyingSecurityAltIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingSecurityAltIDGroup} objects from the <code>underlyingSecurityAltIDGroups</code>
     * array (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyingSecurityAltID</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearUnderlyingSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingProduct)
    public Product getUnderlyingProduct() {
        return underlyingProduct;
    }

    /**
     * Message field setter.
     * @param underlyingProduct field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingProduct)
    public void setUnderlyingProduct(Product underlyingProduct) {
        this.underlyingProduct = underlyingProduct;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCFICode)
    public String getUnderlyingCFICode() {
        return underlyingCFICode;
    }

    /**
     * Message field setter.
     * @param underlyingCFICode field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCFICode)
    public void setUnderlyingCFICode(String underlyingCFICode) {
        this.underlyingCFICode = underlyingCFICode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityType)
    public String getUnderlyingSecurityType() {
        return underlyingSecurityType;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityType)
    public void setUnderlyingSecurityType(String underlyingSecurityType) {
        this.underlyingSecurityType = underlyingSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecuritySubType)
    public String getUnderlyingSecuritySubType() {
        return underlyingSecuritySubType;
    }

    /**
     * Message field setter.
     * @param underlyingSecuritySubType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecuritySubType)
    public void setUnderlyingSecuritySubType(String underlyingSecuritySubType) {
        this.underlyingSecuritySubType = underlyingSecuritySubType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityMonthYear)
    public String getUnderlyingMaturityMonthYear() {
        return underlyingMaturityMonthYear;
    }

    /**
     * Message field setter.
     * @param underlyingMaturityMonthYear field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityMonthYear)
    public void setUnderlyingMaturityMonthYear(String underlyingMaturityMonthYear) {
        this.underlyingMaturityMonthYear = underlyingMaturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityDate)
    public Date getUnderlyingMaturityDate() {
        return underlyingMaturityDate;
    }

    /**
     * Message field setter.
     * @param underlyingMaturityDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityDate)
    public void setUnderlyingMaturityDate(Date underlyingMaturityDate) {
        this.underlyingMaturityDate = underlyingMaturityDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityTime)
    public Date getUnderlyingMaturityTime() {
        return underlyingMaturityTime;
    }

    /**
     * Message field setter.
     * @param underlyingMaturityTime field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingMaturityTime)
    public void setUnderlyingMaturityTime(Date underlyingMaturityTime) {
        this.underlyingMaturityTime = underlyingMaturityTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponPaymentDate)
    public Date getUnderlyingCouponPaymentDate() {
        return underlyingCouponPaymentDate;
    }

    /**
     * Message field setter.
     * @param underlyingCouponPaymentDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponPaymentDate)
    public void setUnderlyingCouponPaymentDate(Date underlyingCouponPaymentDate) {
        this.underlyingCouponPaymentDate = underlyingCouponPaymentDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingRestructuringType)
    public RestructuringType getUnderlyingRestructuringType() {
        return underlyingRestructuringType;
    }

    /**
     * Message field setter.
     * @param underlyingRestructuringType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingRestructuringType)
    public void setUnderlyingRestructuringType(RestructuringType underlyingRestructuringType) {
        this.underlyingRestructuringType = underlyingRestructuringType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingSeniority)
    public Seniority getUnderlyingSeniority() {
        return underlyingSeniority;
    }

    /**
     * Message field setter.
     * @param underlyingSeniority field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingSeniority)
    public void setUnderlyingSeniority(Seniority underlyingSeniority) {
        this.underlyingSeniority = underlyingSeniority;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingNotionalPercentageOutstanding)
    public Double getUnderlyingNotionalPercentageOutstanding() {
        return underlyingNotionalPercentageOutstanding;
    }

    /**
     * Message field setter.
     * @param underlyingNotionalPercentageOutstanding field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingNotionalPercentageOutstanding)
    public void setUnderlyingNotionalPercentageOutstanding(Double underlyingNotionalPercentageOutstanding) {
        this.underlyingNotionalPercentageOutstanding = underlyingNotionalPercentageOutstanding;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingOriginalNotionalPercentageOutstanding)
    public Double getUnderlyingOriginalNotionalPercentageOutstanding() {
        return underlyingOriginalNotionalPercentageOutstanding;
    }

    /**
     * Message field setter.
     * @param underlyingOriginalNotionalPercentageOutstanding field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingOriginalNotionalPercentageOutstanding)
    public void setUnderlyingOriginalNotionalPercentageOutstanding(Double underlyingOriginalNotionalPercentageOutstanding) {
        this.underlyingOriginalNotionalPercentageOutstanding = underlyingOriginalNotionalPercentageOutstanding;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingAttachmentPoint)
    public Double getUnderlyingAttachmentPoint() {
        return underlyingAttachmentPoint;
    }

    /**
     * Message field setter.
     * @param underlyingAttachmentPoint field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingAttachmentPoint)
    public void setUnderlyingAttachmentPoint(Double underlyingAttachmentPoint) {
        this.underlyingAttachmentPoint = underlyingAttachmentPoint;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingDetachmentPoint)
    public Double getUnderlyingDetachmentPoint() {
        return underlyingDetachmentPoint;
    }

    /**
     * Message field setter.
     * @param underlyingDetachmentPoint field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingDetachmentPoint)
    public void setUnderlyingDetachmentPoint(Double underlyingDetachmentPoint) {
        this.underlyingDetachmentPoint = underlyingDetachmentPoint;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingIssueDate)
    public Date getUnderlyingIssueDate() {
        return underlyingIssueDate;
    }

    /**
     * Message field setter.
     * @param underlyingIssueDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingIssueDate)
    public void setUnderlyingIssueDate(Date underlyingIssueDate) {
        this.underlyingIssueDate = underlyingIssueDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRepoCollateralSecurityType)
    public String getUnderlyingRepoCollateralSecurityType() {
        return underlyingRepoCollateralSecurityType;
    }

    /**
     * Message field setter.
     * @param underlyingRepoCollateralSecurityType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRepoCollateralSecurityType)
    public void setUnderlyingRepoCollateralSecurityType(String underlyingRepoCollateralSecurityType) {
        this.underlyingRepoCollateralSecurityType = underlyingRepoCollateralSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRepurchaseTerm)
    public Integer getUnderlyingRepurchaseTerm() {
        return underlyingRepurchaseTerm;
    }

    /**
     * Message field setter.
     * @param underlyingRepurchaseTerm field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRepurchaseTerm)
    public void setUnderlyingRepurchaseTerm(Integer underlyingRepurchaseTerm) {
        this.underlyingRepurchaseTerm = underlyingRepurchaseTerm;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRepurchaseRate)
    public Double getUnderlyingRepurchaseRate() {
        return underlyingRepurchaseRate;
    }

    /**
     * Message field setter.
     * @param underlyingRepurchaseRate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRepurchaseRate)
    public void setUnderlyingRepurchaseRate(Double underlyingRepurchaseRate) {
        this.underlyingRepurchaseRate = underlyingRepurchaseRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingFactor)
    public Double getUnderlyingFactor() {
        return underlyingFactor;
    }

    /**
     * Message field setter.
     * @param underlyingFactor field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingFactor)
    public void setUnderlyingFactor(Double underlyingFactor) {
        this.underlyingFactor = underlyingFactor;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCreditRating)
    public String getUnderlyingCreditRating() {
        return underlyingCreditRating;
    }

    /**
     * Message field setter.
     * @param underlyingCreditRating field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCreditRating)
    public void setUnderlyingCreditRating(String underlyingCreditRating) {
        this.underlyingCreditRating = underlyingCreditRating;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingInstrRegistry)
    public String getUnderlyingInstrRegistry() {
        return underlyingInstrRegistry;
    }

    /**
     * Message field setter.
     * @param underlyingInstrRegistry field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingInstrRegistry)
    public void setUnderlyingInstrRegistry(String underlyingInstrRegistry) {
        this.underlyingInstrRegistry = underlyingInstrRegistry;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCountryOfIssue)
    public String getUnderlyingCountryOfIssue() {
        return underlyingCountryOfIssue;
    }

    /**
     * Message field setter.
     * @param underlyingCountryOfIssue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCountryOfIssue)
    public void setUnderlyingCountryOfIssue(String underlyingCountryOfIssue) {
        this.underlyingCountryOfIssue = underlyingCountryOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStateOrProvinceOfIssue)
    public String getUnderlyingStateOrProvinceOfIssue() {
        return underlyingStateOrProvinceOfIssue;
    }

    /**
     * Message field setter.
     * @param underlyingStateOrProvinceOfIssue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStateOrProvinceOfIssue)
    public void setUnderlyingStateOrProvinceOfIssue(String underlyingStateOrProvinceOfIssue) {
        this.underlyingStateOrProvinceOfIssue = underlyingStateOrProvinceOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingLocaleOfIssue)
    public String getUnderlyingLocaleOfIssue() {
        return underlyingLocaleOfIssue;
    }

    /**
     * Message field setter.
     * @param underlyingLocaleOfIssue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingLocaleOfIssue)
    public void setUnderlyingLocaleOfIssue(String underlyingLocaleOfIssue) {
        this.underlyingLocaleOfIssue = underlyingLocaleOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRedemptionDate)
    public Date getUnderlyingRedemptionDate() {
        return underlyingRedemptionDate;
    }

    /**
     * Message field setter.
     * @param underlyingRedemptionDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingRedemptionDate)
    public void setUnderlyingRedemptionDate(Date underlyingRedemptionDate) {
        this.underlyingRedemptionDate = underlyingRedemptionDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikePrice)
    public Double getUnderlyingStrikePrice() {
        return underlyingStrikePrice;
    }

    /**
     * Message field setter.
     * @param underlyingStrikePrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikePrice)
    public void setUnderlyingStrikePrice(Double underlyingStrikePrice) {
        this.underlyingStrikePrice = underlyingStrikePrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikeCurrency)
    public Currency getUnderlyingStrikeCurrency() {
        return underlyingStrikeCurrency;
    }

    /**
     * Message field setter.
     * @param underlyingStrikeCurrency field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStrikeCurrency)
    public void setUnderlyingStrikeCurrency(Currency underlyingStrikeCurrency) {
        this.underlyingStrikeCurrency = underlyingStrikeCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingOptAttribute)
    public Character getUnderlyingOptAttribute() {
        return underlyingOptAttribute;
    }

    /**
     * Message field setter.
     * @param underlyingOptAttribute field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingOptAttribute)
    public void setUnderlyingOptAttribute(Character underlyingOptAttribute) {
        this.underlyingOptAttribute = underlyingOptAttribute;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplier)
    public Double getUnderlyingContractMultiplier() {
        return underlyingContractMultiplier;
    }

    /**
     * Message field setter.
     * @param underlyingContractMultiplier field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplier)
    public void setUnderlyingContractMultiplier(Double underlyingContractMultiplier) {
        this.underlyingContractMultiplier = underlyingContractMultiplier;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplierUnit)
    public ContractMultiplierUnit getUnderlyingContractMultiplierUnit() {
        return underlyingContractMultiplierUnit;
    }

    /**
     * Message field setter.
     * @param underlyingContractMultiplierUnit field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingContractMultiplierUnit)
    public void setUnderlyingContractMultiplierUnit(ContractMultiplierUnit underlyingContractMultiplierUnit) {
        this.underlyingContractMultiplierUnit = underlyingContractMultiplierUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingFlowScheduleType)
    public FlowScheduleType getUnderlyingFlowScheduleType() {
        return underlyingFlowScheduleType;
    }

    /**
     * Message field setter.
     * @param underlyingFlowScheduleType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.UnderlyingFlowScheduleType)
    public void setUnderlyingFlowScheduleType(FlowScheduleType underlyingFlowScheduleType) {
        this.underlyingFlowScheduleType = underlyingFlowScheduleType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingUnitOfMeasure)
    public UnitOfMeasure getUnderlyingUnitOfMeasure() {
        return underlyingUnitOfMeasure;
    }

    /**
     * Message field setter.
     * @param underlyingUnitOfMeasure field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingUnitOfMeasure)
    public void setUnderlyingUnitOfMeasure(UnitOfMeasure underlyingUnitOfMeasure) {
        this.underlyingUnitOfMeasure = underlyingUnitOfMeasure;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingUnitOfMeasureQty)
    public Double getUnderlyingUnitOfMeasureQty() {
        return underlyingUnitOfMeasureQty;
    }

    /**
     * Message field setter.
     * @param underlyingUnitOfMeasureQty field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingUnitOfMeasureQty)
    public void setUnderlyingUnitOfMeasureQty(Double underlyingUnitOfMeasureQty) {
        this.underlyingUnitOfMeasureQty = underlyingUnitOfMeasureQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingPriceUnitOfMeasure)
    public PriceUnitOfMeasure getUnderlyingPriceUnitOfMeasure() {
        return underlyingPriceUnitOfMeasure;
    }

    /**
     * Message field setter.
     * @param underlyingPriceUnitOfMeasure field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingPriceUnitOfMeasure)
    public void setUnderlyingPriceUnitOfMeasure(PriceUnitOfMeasure underlyingPriceUnitOfMeasure) {
        this.underlyingPriceUnitOfMeasure = underlyingPriceUnitOfMeasure;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingPriceUnitOfMeasureQty)
    public Double getUnderlyingPriceUnitOfMeasureQty() {
        return underlyingPriceUnitOfMeasureQty;
    }

    /**
     * Message field setter.
     * @param underlyingPriceUnitOfMeasureQty field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingPriceUnitOfMeasureQty)
    public void setUnderlyingPriceUnitOfMeasureQty(Double underlyingPriceUnitOfMeasureQty) {
        this.underlyingPriceUnitOfMeasureQty = underlyingPriceUnitOfMeasureQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingTimeUnit)
    public TimeUnit getUnderlyingTimeUnit() {
        return underlyingTimeUnit;
    }

    /**
     * Message field setter.
     * @param underlyingTimeUnit field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingTimeUnit)
    public void setUnderlyingTimeUnit(TimeUnit underlyingTimeUnit) {
        this.underlyingTimeUnit = underlyingTimeUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingExerciseStyle)
    public ExerciseStyle getUnderlyingExerciseStyle() {
        return underlyingExerciseStyle;
    }

    /**
     * Message field setter.
     * @param underlyingExerciseStyle field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.UnderlyingExerciseStyle)
    public void setUnderlyingExerciseStyle(ExerciseStyle underlyingExerciseStyle) {
        this.underlyingExerciseStyle = underlyingExerciseStyle;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponRate)
    public Double getUnderlyingCouponRate() {
        return underlyingCouponRate;
    }

    /**
     * Message field setter.
     * @param underlyingCouponRate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCouponRate)
    public void setUnderlyingCouponRate(Double underlyingCouponRate) {
        this.underlyingCouponRate = underlyingCouponRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityExchange)
    public String getUnderlyingSecurityExchange() {
        return underlyingSecurityExchange;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityExchange field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityExchange)
    public void setUnderlyingSecurityExchange(String underlyingSecurityExchange) {
        this.underlyingSecurityExchange = underlyingSecurityExchange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingIssuer)
    public String getUnderlyingIssuer() {
        return underlyingIssuer;
    }

    /**
     * Message field setter.
     * @param underlyingIssuer field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingIssuer)
    public void setUnderlyingIssuer(String underlyingIssuer) {
        this.underlyingIssuer = underlyingIssuer;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuerLen)
    public Integer getEncodedUnderlyingIssuerLen() {
        return encodedUnderlyingIssuerLen;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingIssuerLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuerLen)
    public void setEncodedUnderlyingIssuerLen(Integer encodedUnderlyingIssuerLen) {
        this.encodedUnderlyingIssuerLen = encodedUnderlyingIssuerLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuer)
    public byte[] getEncodedUnderlyingIssuer() {
        return encodedUnderlyingIssuer;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingIssuer field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingIssuer)
    public void setEncodedUnderlyingIssuer(byte[] encodedUnderlyingIssuer) {
        this.encodedUnderlyingIssuer = encodedUnderlyingIssuer;
        if (encodedUnderlyingIssuerLen == null) {
            encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityDesc)
    public String getUnderlyingSecurityDesc() {
        return underlyingSecurityDesc;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityDesc field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSecurityDesc)
    public void setUnderlyingSecurityDesc(String underlyingSecurityDesc) {
        this.underlyingSecurityDesc = underlyingSecurityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDescLen)
    public Integer getEncodedUnderlyingSecurityDescLen() {
        return encodedUnderlyingSecurityDescLen;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingSecurityDescLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDescLen)
    public void setEncodedUnderlyingSecurityDescLen(Integer encodedUnderlyingSecurityDescLen) {
        this.encodedUnderlyingSecurityDescLen = encodedUnderlyingSecurityDescLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDesc)
    public byte[] getEncodedUnderlyingSecurityDesc() {
        return encodedUnderlyingSecurityDesc;
    }

    /**
     * Message field setter.
     * @param encodedUnderlyingSecurityDesc field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedUnderlyingSecurityDesc)
    public void setEncodedUnderlyingSecurityDesc(byte[] encodedUnderlyingSecurityDesc) {
        this.encodedUnderlyingSecurityDesc = encodedUnderlyingSecurityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCPProgram)
    public String getUnderlyingCPProgram() {
        return underlyingCPProgram;
    }

    /**
     * Message field setter.
     * @param underlyingCPProgram field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCPProgram)
    public void setUnderlyingCPProgram(String underlyingCPProgram) {
        this.underlyingCPProgram = underlyingCPProgram;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCPRegType)
    public String getUnderlyingCPRegType() {
        return underlyingCPRegType;
    }

    /**
     * Message field setter.
     * @param underlyingCPRegType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCPRegType)
    public void setUnderlyingCPRegType(String underlyingCPRegType) {
        this.underlyingCPRegType = underlyingCPRegType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingAllocationPercent)
    public Double getUnderlyingAllocationPercent() {
        return underlyingAllocationPercent;
    }

    /**
     * Message field setter.
     * @param underlyingAllocationPercent field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingAllocationPercent)
    public void setUnderlyingAllocationPercent(Double underlyingAllocationPercent) {
        this.underlyingAllocationPercent = underlyingAllocationPercent;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCurrency)
    public Currency getUnderlyingCurrency() {
        return underlyingCurrency;
    }

    /**
     * Message field setter.
     * @param underlyingCurrency field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCurrency)
    public void setUnderlyingCurrency(Currency underlyingCurrency) {
        this.underlyingCurrency = underlyingCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingQty)
    public Double getUnderlyingQty() {
        return underlyingQty;
    }

    /**
     * Message field setter.
     * @param underlyingQty field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingQty)
    public void setUnderlyingQty(Double underlyingQty) {
        this.underlyingQty = underlyingQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlementType)
    public UnderlyingSettlementType getUnderlyingSettlementType() {
        return underlyingSettlementType;
    }

    /**
     * Message field setter.
     * @param underlyingSettlementType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlementType)
    public void setUnderlyingSettlementType(UnderlyingSettlementType underlyingSettlementType) {
        this.underlyingSettlementType = underlyingSettlementType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingCashAmount)
    public Double getUnderlyingCashAmount() {
        return underlyingCashAmount;
    }

    /**
     * Message field setter.
     * @param underlyingCashAmount field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingCashAmount)
    public void setUnderlyingCashAmount(Double underlyingCashAmount) {
        this.underlyingCashAmount = underlyingCashAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingCashType)
    public String getUnderlyingCashType() {
        return underlyingCashType;
    }

    /**
     * Message field setter.
     * @param underlyingCashType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingCashType)
    public void setUnderlyingCashType(String underlyingCashType) {
        this.underlyingCashType = underlyingCashType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingPx)
    public Double getUnderlyingPx() {
        return underlyingPx;
    }

    /**
     * Message field setter.
     * @param underlyingPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingPx)
    public void setUnderlyingPx(Double underlyingPx) {
        this.underlyingPx = underlyingPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingDirtyPrice)
    public Double getUnderlyingDirtyPrice() {
        return underlyingDirtyPrice;
    }

    /**
     * Message field setter.
     * @param underlyingDirtyPrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingDirtyPrice)
    public void setUnderlyingDirtyPrice(Double underlyingDirtyPrice) {
        this.underlyingDirtyPrice = underlyingDirtyPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingEndPrice)
    public Double getUnderlyingEndPrice() {
        return underlyingEndPrice;
    }

    /**
     * Message field setter.
     * @param underlyingEndPrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingEndPrice)
    public void setUnderlyingEndPrice(Double underlyingEndPrice) {
        this.underlyingEndPrice = underlyingEndPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStartValue)
    public Double getUnderlyingStartValue() {
        return underlyingStartValue;
    }

    /**
     * Message field setter.
     * @param underlyingStartValue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingStartValue)
    public void setUnderlyingStartValue(Double underlyingStartValue) {
        this.underlyingStartValue = underlyingStartValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCurrentValue)
    public Double getUnderlyingCurrentValue() {
        return underlyingCurrentValue;
    }

    /**
     * Message field setter.
     * @param underlyingCurrentValue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingCurrentValue)
    public void setUnderlyingCurrentValue(Double underlyingCurrentValue) {
        this.underlyingCurrentValue = underlyingCurrentValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingEndValue)
    public Double getUnderlyingEndValue() {
        return underlyingEndValue;
    }

    /**
     * Message field setter.
     * @param underlyingEndValue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingEndValue)
    public void setUnderlyingEndValue(Double underlyingEndValue) {
        this.underlyingEndValue = underlyingEndValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public UnderlyingStipulations getUnderlyingStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the UnderlyingStipulations component to the proper implementation class.
     */
    @FIXVersion(introduced="4.4")
    public void setUnderlyingStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the UnderlyingStipulations component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearUnderlyingStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingAdjustedQuantity)
    public Double getUnderlyingAdjustedQuantity() {
        return underlyingAdjustedQuantity;
    }

    /**
     * Message field setter.
     * @param underlyingAdjustedQuantity field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingAdjustedQuantity)
    public void setUnderlyingAdjustedQuantity(Double underlyingAdjustedQuantity) {
        this.underlyingAdjustedQuantity = underlyingAdjustedQuantity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingFXRate)
    public Double getUnderlyingFXRate() {
        return underlyingFXRate;
    }

    /**
     * Message field setter.
     * @param underlyingFXRate field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingFXRate)
    public void setUnderlyingFXRate(Double underlyingFXRate) {
        this.underlyingFXRate = underlyingFXRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingFXRateCalc)
    public FXRateCalc getUnderlyingFXRateCalc() {
        return underlyingFXRateCalc;
    }

    /**
     * Message field setter.
     * @param underlyingFXRateCalc field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingFXRateCalc)
    public void setUnderlyingFXRateCalc(FXRateCalc underlyingFXRateCalc) {
        this.underlyingFXRateCalc = underlyingFXRateCalc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingCapValue)
    public Double getUnderlyingCapValue() {
        return underlyingCapValue;
    }

    /**
     * Message field setter.
     * @param underlyingCapValue field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingCapValue)
    public void setUnderlyingCapValue(Double underlyingCapValue) {
        this.underlyingCapValue = underlyingCapValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public UndlyInstrumentParties getUndlyInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper implementation class.
     */
    @FIXVersion(introduced="5.0")
    public void setUndlyInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the UndlyInstrumentParties component to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearUndlyInstrumentParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlMethod)
    public String getUnderlyingSettlMethod() {
        return underlyingSettlMethod;
    }

    /**
     * Message field setter.
     * @param underlyingSettlMethod field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlMethod)
    public void setUnderlyingSettlMethod(String underlyingSettlMethod) {
        this.underlyingSettlMethod = underlyingSettlMethod;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UnderlyingSymbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (underlyingSymbol == null) {
            errorMsg.append(" [UnderlyingSymbol]");
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
            TagEncoder.encode(bao, TagNum.UnderlyingSymbol, underlyingSymbol);
            TagEncoder.encode(bao, TagNum.UnderlyingSymbolSfx, underlyingSymbolSfx);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityID, underlyingSecurityID);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityIDSource, underlyingSecurityIDSource);
            if (noUnderlyingSecurityAltID != null && noUnderlyingSecurityAltID.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoUnderlyingSecurityAltID, noUnderlyingSecurityAltID);
                if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length == noUnderlyingSecurityAltID.intValue()) {
                    for (int i = 0; i < noUnderlyingSecurityAltID.intValue(); i++) {
                        if (underlyingSecurityAltIDGroups[i] != null) {
                            bao.write(underlyingSecurityAltIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingSecurityAltIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups,
                        TagNum.NoUnderlyingSecurityAltID.getValue(), error);
                }
            }
            if (underlyingProduct != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingProduct, underlyingProduct.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingCFICode, underlyingCFICode);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityType, underlyingSecurityType);
            TagEncoder.encode(bao, TagNum.UnderlyingSecuritySubType, underlyingSecuritySubType);
            TagEncoder.encode(bao, TagNum.UnderlyingMaturityMonthYear, underlyingMaturityMonthYear);
            TagEncoder.encodeDate(bao, TagNum.UnderlyingMaturityDate, underlyingMaturityDate);
            TagEncoder.encodeTZTime(bao, TagNum.UnderlyingMaturityTime, underlyingMaturityTime);
            TagEncoder.encodeDate(bao, TagNum.UnderlyingCouponPaymentDate, underlyingCouponPaymentDate);
            if (underlyingRestructuringType != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingRestructuringType, underlyingRestructuringType.getValue());
            }
            if (underlyingSeniority != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingSeniority, underlyingSeniority.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingNotionalPercentageOutstanding, underlyingNotionalPercentageOutstanding);
            TagEncoder.encode(bao, TagNum.UnderlyingOriginalNotionalPercentageOutstanding, underlyingOriginalNotionalPercentageOutstanding);
            TagEncoder.encode(bao, TagNum.UnderlyingAttachmentPoint, underlyingAttachmentPoint);
            TagEncoder.encode(bao, TagNum.UnderlyingDetachmentPoint, underlyingDetachmentPoint);
            TagEncoder.encodeDate(bao, TagNum.UnderlyingIssueDate, underlyingIssueDate);
            TagEncoder.encode(bao, TagNum.UnderlyingRepoCollateralSecurityType, underlyingRepoCollateralSecurityType);
            TagEncoder.encode(bao, TagNum.UnderlyingRepurchaseTerm, underlyingRepurchaseTerm);
            TagEncoder.encode(bao, TagNum.UnderlyingRepurchaseRate, underlyingRepurchaseRate);
            TagEncoder.encode(bao, TagNum.UnderlyingFactor, underlyingFactor);
            TagEncoder.encode(bao, TagNum.UnderlyingCreditRating, underlyingCreditRating);
            TagEncoder.encode(bao, TagNum.UnderlyingInstrRegistry, underlyingInstrRegistry);
            TagEncoder.encode(bao, TagNum.UnderlyingCountryOfIssue, underlyingCountryOfIssue);
            TagEncoder.encode(bao, TagNum.UnderlyingStateOrProvinceOfIssue, underlyingStateOrProvinceOfIssue);
            TagEncoder.encode(bao, TagNum.UnderlyingLocaleOfIssue, underlyingLocaleOfIssue);
            TagEncoder.encodeDate(bao, TagNum.UnderlyingRedemptionDate, underlyingRedemptionDate);
            TagEncoder.encode(bao, TagNum.UnderlyingStrikePrice, underlyingStrikePrice);
            if (underlyingStrikeCurrency != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingStrikeCurrency, underlyingStrikeCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingOptAttribute, underlyingOptAttribute);
            TagEncoder.encode(bao, TagNum.UnderlyingContractMultiplier, underlyingContractMultiplier);
            if (underlyingContractMultiplierUnit != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingContractMultiplierUnit, underlyingContractMultiplierUnit.getValue());
            }
            if (underlyingFlowScheduleType != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingFlowScheduleType, underlyingFlowScheduleType.getValue());
            }
            if (underlyingUnitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingUnitOfMeasure, underlyingUnitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingUnitOfMeasureQty, underlyingUnitOfMeasureQty);
            if (underlyingPriceUnitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingPriceUnitOfMeasure, underlyingPriceUnitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingPriceUnitOfMeasureQty, underlyingPriceUnitOfMeasureQty);
            if (underlyingTimeUnit != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingTimeUnit, underlyingTimeUnit.getValue());
            }
            if (underlyingExerciseStyle != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingExerciseStyle, underlyingExerciseStyle.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingCouponRate, underlyingCouponRate);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityExchange, underlyingSecurityExchange);
            TagEncoder.encode(bao, TagNum.UnderlyingIssuer, underlyingIssuer, sessionCharset);
            if (encodedUnderlyingIssuerLen != null && encodedUnderlyingIssuerLen.intValue() > 0) {
                if (encodedUnderlyingIssuer != null && encodedUnderlyingIssuer.length > 0) {
                    encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuerLen, encodedUnderlyingIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuer, encodedUnderlyingIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityDesc, underlyingSecurityDesc, sessionCharset);
            if (encodedUnderlyingSecurityDescLen != null && encodedUnderlyingSecurityDescLen.intValue() > 0) {
                if (encodedUnderlyingSecurityDesc != null && encodedUnderlyingSecurityDesc.length > 0) {
                    encodedUnderlyingSecurityDescLen = new Integer(encodedUnderlyingSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDescLen, encodedUnderlyingSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDesc, encodedUnderlyingSecurityDesc);
                }
            }
            TagEncoder.encode(bao, TagNum.UnderlyingCPProgram, underlyingCPProgram);
            TagEncoder.encode(bao, TagNum.UnderlyingCPRegType, underlyingCPRegType);
            TagEncoder.encode(bao, TagNum.UnderlyingAllocationPercent, underlyingAllocationPercent);
            if (underlyingCurrency != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingCurrency, underlyingCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingQty, underlyingQty);
            if (underlyingSettlementType != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingSettlementType, underlyingSettlementType.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingCashAmount, underlyingCashAmount);
            TagEncoder.encode(bao, TagNum.UnderlyingCashType, underlyingCashType);
            TagEncoder.encode(bao, TagNum.UnderlyingPx, underlyingPx);
            TagEncoder.encode(bao, TagNum.UnderlyingDirtyPrice, underlyingDirtyPrice);
            TagEncoder.encode(bao, TagNum.UnderlyingEndPrice, underlyingEndPrice);
            TagEncoder.encode(bao, TagNum.UnderlyingStartValue, underlyingStartValue);
            TagEncoder.encode(bao, TagNum.UnderlyingCurrentValue, underlyingCurrentValue);
            TagEncoder.encode(bao, TagNum.UnderlyingEndValue, underlyingEndValue);
            if (underlyingStipulations != null) {
                bao.write(underlyingStipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.UnderlyingAdjustedQuantity, underlyingAdjustedQuantity);
            TagEncoder.encode(bao, TagNum.UnderlyingFXRate, underlyingFXRate);
            if (underlyingFXRateCalc != null) {
                TagEncoder.encode(bao, TagNum.UnderlyingFXRateCalc, underlyingFXRateCalc.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingCapValue, underlyingCapValue);
            if (undlyInstrumentParties != null) {
                bao.write(undlyInstrumentParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.UnderlyingSettlMethod, underlyingSettlMethod);
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
            case UnderlyingSymbol:
                underlyingSymbol = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSymbolSfx:
                underlyingSymbolSfx = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityID:
                underlyingSecurityID = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityIDSource:
                underlyingSecurityIDSource = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyingSecurityAltID:
                noUnderlyingSecurityAltID = new Integer(new String(tag.value, sessionCharset));
                break;

            case UnderlyingProduct:
                underlyingProduct = Product.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnderlyingCFICode:
                underlyingCFICode = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityType:
                underlyingSecurityType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecuritySubType:
                underlyingSecuritySubType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingMaturityMonthYear:
                underlyingMaturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case UnderlyingMaturityDate:
                underlyingMaturityDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingMaturityTime:
                underlyingMaturityTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCouponPaymentDate:
                underlyingCouponPaymentDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingRestructuringType:
                underlyingRestructuringType = RestructuringType.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSeniority:
                underlyingSeniority = Seniority.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingNotionalPercentageOutstanding:
                underlyingNotionalPercentageOutstanding = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingOriginalNotionalPercentageOutstanding:
                underlyingOriginalNotionalPercentageOutstanding = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingAttachmentPoint:
                underlyingAttachmentPoint = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingDetachmentPoint:
                underlyingDetachmentPoint = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingIssueDate:
                underlyingIssueDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingRepoCollateralSecurityType:
                underlyingRepoCollateralSecurityType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingRepurchaseTerm:
                underlyingRepurchaseTerm = new Integer(new String(tag.value, sessionCharset));
                break;

            case UnderlyingRepurchaseRate:
                underlyingRepurchaseRate = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingFactor:
                underlyingFactor = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCreditRating:
                underlyingCreditRating = new String(tag.value, sessionCharset);
                break;

            case UnderlyingInstrRegistry:
                underlyingInstrRegistry = new String(tag.value, sessionCharset);
                break;

            case UnderlyingCountryOfIssue:
                underlyingCountryOfIssue = new String(tag.value, sessionCharset);
                break;

            case UnderlyingStateOrProvinceOfIssue:
                underlyingStateOrProvinceOfIssue = new String(tag.value, sessionCharset);
                break;

            case UnderlyingLocaleOfIssue:
                underlyingLocaleOfIssue = new String(tag.value, sessionCharset);
                break;

            case UnderlyingRedemptionDate:
                underlyingRedemptionDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case UnderlyingStrikePrice:
                underlyingStrikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingStrikeCurrency:
                underlyingStrikeCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingOptAttribute:
                underlyingOptAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case UnderlyingContractMultiplier:
                underlyingContractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingContractMultiplierUnit:
                underlyingContractMultiplierUnit = ContractMultiplierUnit.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;
                
            case UnderlyingFlowScheduleType:
                underlyingFlowScheduleType = FlowScheduleType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnderlyingUnitOfMeasure:
                underlyingUnitOfMeasure = UnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingUnitOfMeasureQty:
                underlyingUnitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingPriceUnitOfMeasure:
                underlyingPriceUnitOfMeasure = PriceUnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingPriceUnitOfMeasureQty:
                underlyingPriceUnitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingTimeUnit:
                underlyingTimeUnit = TimeUnit.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingExerciseStyle:
                underlyingExerciseStyle = ExerciseStyle.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnderlyingCouponRate:
                underlyingCouponRate = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSecurityExchange:
                underlyingSecurityExchange = new String(tag.value, sessionCharset);
                break;

            case UnderlyingIssuer:
                underlyingIssuer = new String(tag.value, sessionCharset);
                break;

            case EncodedUnderlyingIssuerLen:
                encodedUnderlyingIssuerLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSecurityDesc:
                underlyingSecurityDesc = new String(tag.value, sessionCharset);
                break;

            case EncodedUnderlyingSecurityDescLen:
                encodedUnderlyingSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCPProgram:
                underlyingCPProgram = new String(tag.value, sessionCharset);
                break;

            case UnderlyingCPRegType:
                underlyingCPRegType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingAllocationPercent:
                underlyingAllocationPercent = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCurrency:
                underlyingCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingQty:
                underlyingQty = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSettlementType:
                underlyingSettlementType = UnderlyingSettlementType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case UnderlyingCashAmount:
                underlyingCashAmount = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCashType:
                underlyingCashType = new String(tag.value, sessionCharset);
                break;

            case UnderlyingPx:
                underlyingPx = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingDirtyPrice:
                underlyingDirtyPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingEndPrice:
                underlyingEndPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingStartValue:
                underlyingStartValue = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCurrentValue:
                underlyingCurrentValue = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingEndValue:
                underlyingEndValue = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingAdjustedQuantity:
                underlyingAdjustedQuantity = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingFXRate:
                underlyingFXRate = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingFXRateCalc:
                underlyingFXRateCalc = FXRateCalc.valueFor(new String(tag.value, sessionCharset));
                break;

            case UnderlyingCapValue:
                underlyingCapValue = new Double(new String(tag.value, sessionCharset));
                break;

            case UnderlyingSettlMethod:
                underlyingSettlMethod = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UnderlyingInstrument] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedUnderlyingIssuerLen.getValue()) {
            try {
                encodedUnderlyingIssuerLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedUnderlyingIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedUnderlyingIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedUnderlyingIssuerLen.intValue());
            encodedUnderlyingIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedUnderlyingSecurityDescLen.getValue()) {
            try {
                encodedUnderlyingSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedUnderlyingSecurityDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedUnderlyingSecurityDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedUnderlyingSecurityDescLen.intValue());
            encodedUnderlyingSecurityDesc = dataTag.value;
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
        b.append("{UnderlyingInstrument=");
        printTagValue(b, TagNum.UnderlyingSymbol, underlyingSymbol);
        printTagValue(b, TagNum.UnderlyingSymbolSfx, underlyingSymbolSfx);
        printTagValue(b, TagNum.UnderlyingSecurityID, underlyingSecurityID);
        printTagValue(b, TagNum.UnderlyingSecurityIDSource, underlyingSecurityIDSource);
        printTagValue(b, TagNum.NoUnderlyingSecurityAltID, noUnderlyingSecurityAltID);
        printTagValue(b, underlyingSecurityAltIDGroups);
        printTagValue(b, TagNum.UnderlyingProduct, underlyingProduct);
        printTagValue(b, TagNum.UnderlyingCFICode, underlyingCFICode);
        printTagValue(b, TagNum.UnderlyingSecurityType, underlyingSecurityType);
        printTagValue(b, TagNum.UnderlyingSecuritySubType, underlyingSecuritySubType);
        printTagValue(b, TagNum.UnderlyingMaturityMonthYear, underlyingMaturityMonthYear);
        printDateTagValue(b, TagNum.UnderlyingMaturityDate, underlyingMaturityDate);
        printUTCTimeTagValue(b, TagNum.UnderlyingMaturityTime, underlyingMaturityTime);
        printDateTagValue(b, TagNum.UnderlyingCouponPaymentDate, underlyingCouponPaymentDate);
        printTagValue(b, TagNum.UnderlyingRestructuringType, underlyingRestructuringType);
        printTagValue(b, TagNum.UnderlyingSeniority, underlyingSeniority);
        printTagValue(b, TagNum.UnderlyingNotionalPercentageOutstanding, underlyingNotionalPercentageOutstanding);
        printTagValue(b, TagNum.UnderlyingOriginalNotionalPercentageOutstanding, underlyingOriginalNotionalPercentageOutstanding);
        printTagValue(b, TagNum.UnderlyingAttachmentPoint, underlyingAttachmentPoint);
        printTagValue(b, TagNum.UnderlyingDetachmentPoint, underlyingDetachmentPoint);
        printDateTagValue(b, TagNum.UnderlyingIssueDate, underlyingIssueDate);
        printTagValue(b, TagNum.UnderlyingRepoCollateralSecurityType, underlyingRepoCollateralSecurityType);
        printTagValue(b, TagNum.UnderlyingRepurchaseTerm, underlyingRepurchaseTerm);
        printTagValue(b, TagNum.UnderlyingRepurchaseRate, underlyingRepurchaseRate);
        printTagValue(b, TagNum.UnderlyingFactor, underlyingFactor);
        printTagValue(b, TagNum.UnderlyingCreditRating, underlyingCreditRating);
        printTagValue(b, TagNum.UnderlyingInstrRegistry, underlyingInstrRegistry);
        printTagValue(b, TagNum.UnderlyingCountryOfIssue, underlyingCountryOfIssue);
        printTagValue(b, TagNum.UnderlyingStateOrProvinceOfIssue, underlyingStateOrProvinceOfIssue);
        printTagValue(b, TagNum.UnderlyingLocaleOfIssue, underlyingLocaleOfIssue);
        printDateTagValue(b, TagNum.UnderlyingRedemptionDate, underlyingRedemptionDate);
        printTagValue(b, TagNum.UnderlyingStrikePrice, underlyingStrikePrice);
        printTagValue(b, TagNum.UnderlyingStrikeCurrency, underlyingStrikeCurrency);
        printTagValue(b, TagNum.UnderlyingOptAttribute, underlyingOptAttribute);
        printTagValue(b, TagNum.UnderlyingContractMultiplier, underlyingContractMultiplier);
        printTagValue(b, TagNum.UnderlyingContractMultiplierUnit, underlyingContractMultiplierUnit);
        printTagValue(b, TagNum.UnderlyingFlowScheduleType, underlyingFlowScheduleType);
        printTagValue(b, TagNum.UnderlyingUnitOfMeasure, underlyingUnitOfMeasure);
        printTagValue(b, TagNum.UnderlyingUnitOfMeasureQty, underlyingUnitOfMeasureQty);
        printTagValue(b, TagNum.UnderlyingPriceUnitOfMeasure, underlyingPriceUnitOfMeasure);
        printTagValue(b, TagNum.UnderlyingPriceUnitOfMeasureQty, underlyingPriceUnitOfMeasureQty);
        printTagValue(b, TagNum.UnderlyingTimeUnit, underlyingTimeUnit);
        printTagValue(b, TagNum.UnderlyingExerciseStyle, underlyingExerciseStyle);
        printTagValue(b, TagNum.UnderlyingCouponRate, underlyingCouponRate);
        printTagValue(b, TagNum.UnderlyingSecurityExchange, underlyingSecurityExchange);
        printTagValue(b, TagNum.UnderlyingIssuer, underlyingIssuer);
        printTagValue(b, TagNum.EncodedUnderlyingIssuerLen, encodedUnderlyingIssuerLen);
        printTagValue(b, TagNum.EncodedUnderlyingIssuer, encodedUnderlyingIssuer);
        printTagValue(b, TagNum.UnderlyingSecurityDesc, underlyingSecurityDesc);
        printTagValue(b, TagNum.EncodedUnderlyingSecurityDescLen, encodedUnderlyingSecurityDescLen);
        printTagValue(b, TagNum.EncodedUnderlyingSecurityDesc, encodedUnderlyingSecurityDesc);
        printTagValue(b, TagNum.UnderlyingCPProgram, underlyingCPProgram);
        printTagValue(b, TagNum.UnderlyingCPRegType, underlyingCPRegType);
        printTagValue(b, TagNum.UnderlyingAllocationPercent, underlyingAllocationPercent);
        printTagValue(b, TagNum.UnderlyingCurrency, underlyingCurrency);
        printTagValue(b, TagNum.UnderlyingQty, underlyingQty);
        printTagValue(b, TagNum.UnderlyingSettlementType, underlyingSettlementType);
        printTagValue(b, TagNum.UnderlyingCashAmount, underlyingCashAmount);
        printTagValue(b, TagNum.UnderlyingCashType, underlyingCashType);
        printTagValue(b, TagNum.UnderlyingPx, underlyingPx);
        printTagValue(b, TagNum.UnderlyingDirtyPrice, underlyingDirtyPrice);
        printTagValue(b, TagNum.UnderlyingEndPrice, underlyingEndPrice);
        printTagValue(b, TagNum.UnderlyingStartValue, underlyingStartValue);
        printTagValue(b, TagNum.UnderlyingCurrentValue, underlyingCurrentValue);
        printTagValue(b, TagNum.UnderlyingEndValue, underlyingEndValue);
        printTagValue(b, underlyingStipulations);
        printTagValue(b, TagNum.UnderlyingAdjustedQuantity, underlyingAdjustedQuantity);
        printTagValue(b, TagNum.UnderlyingFXRate, underlyingFXRate);
        printTagValue(b, TagNum.UnderlyingFXRateCalc, underlyingFXRateCalc);
        printTagValue(b, TagNum.UnderlyingCapValue, underlyingCapValue);
        printTagValue(b, undlyInstrumentParties);
        printTagValue(b, TagNum.UnderlyingSettlMethod, underlyingSettlMethod);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
