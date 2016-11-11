/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg.java
 *
 * $Id: InstrumentLeg.java,v 1.9 2011-01-03 07:28:48 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
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
import net.hades.fix.message.group.LegSecurityAltIDGroup;
import net.hades.fix.message.type.ContractMultiplierUnit;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseStyle;
import net.hades.fix.message.type.FlowScheduleType;
import net.hades.fix.message.type.PriceUnitOfMeasure;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * InstruemtLeg component data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 29/10/2008, 9:04:03 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class InstrumentLeg extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
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
        TagNum.LegMaturityTime.getValue(),
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
        TagNum.LegPutOrCall.getValue(),
        TagNum.LegOptionRatio.getValue(),
        TagNum.LegPrice.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedLegIssuerLen.getValue(),
        TagNum.EncodedLegSecurityDescLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegSymbol.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** 
     * TagNum = 600. Starting with 4.4 version.
     */
    protected String legSymbol;
    
    /** 
     * TagNum = 601. Starting with 4.4 version.
     */
    protected String legSymbolSfx;

    /**
     * TagNum = 602. Starting with 4.4 version.
     */
    protected String legSecurityID;

    /**
     * TagNum = 603. Starting with 4.4 version.
     */
    protected String legSecurityIDSource;

    /**
     * TagNum = 604. Starting with 4.4 version.
     */
    protected Integer noLegSecurityAltID;

    /**
     * TagNum = 604. Starting with 4.4 version.
     */
    protected LegSecurityAltIDGroup[] legSecurityAltIDGroups;

    /**
     * TagNum = 607. Starting with 4.4 version.
     */
    protected Product legProduct;

    /**
     * TagNum = 608. Starting with 4.4 version.
     */
    protected String legCFICode;
    
    /**
     * TagNum = 609. Starting with 4.4 version.
     */
    protected String legSecurityType;

    /**
     * TagNum = 764. Starting with 4.4 version.
     */
    protected String legSecuritySubType;

    /**
     * TagNum = 610. Starting with 4.4 version.
     */
    protected String legMaturityMonthYear;

    /**
     * TagNum = 611. Starting with 4.4 version.
     */
    protected Date legMaturityDate;

    /**
     * TagNum = 1212. Starting with 4.4 version.
     */
    protected Date legMaturityTime;

    /**
     * TagNum = 248. Starting with 4.4 version.
     */
    protected Date legCouponPaymentDate;

    /**
     * TagNum = 249. Starting with 4.4 version.
     */
    protected Date legIssueDate;

    /**
     * TagNum = 250. Starting with 4.4 version.
     */
    protected String legRepoCollateralSecurityType;

    /**
     * TagNum = 251. Starting with 4.4 version.
     */
    protected Integer legRepurchaseTerm;

    /**
     * TagNum = 252. Starting with 4.4 version.
     */
    protected Double legRepurchaseRate;

    /**
     * TagNum = 253. Starting with 4.4 version.
     */
    protected Double legFactor;

    /**
     * TagNum = 257. Starting with 4.4 version.
     */
    protected String legCreditRating;

    /**
     * TagNum = 599. Starting with 4.4 version.
     */
    protected String legInstrRegistry;

    /**
     * TagNum = 596. Starting with 4.4 version.
     */
    protected String legCountryOfIssue;

    /**
     * TagNum = 597. Starting with 4.4 version.
     */
    protected String legStateOrProvinceOfIssue;

    /**
     * TagNum = 598. Starting with 4.4 version.
     */
    protected String legLocaleOfIssue;

    /**
     * TagNum = 254. Starting with 4.4 version.
     */
    protected Date legRedemptionDate;

    /**
     * TagNum = 612. Starting with 4.4 version.
     */
    protected Double legStrikePrice;

    /**
     * TagNum = 942. Starting with 4.4 version.
     */
    protected Currency legStrikeCurrency;

    /**
     * TagNum = 613. Starting with 4.4 version.
     */
    protected Character legOptAttribute;

    /**
     * TagNum = 614. Starting with 4.4 version.
     */
    protected Double legContractMultiplier;

    /**
     * TagNum = 1436. Starting with 5.0SP2 version.
     */
    protected ContractMultiplierUnit legContractMultiplierUnit;

    /**
     * TagNum = 1440. Starting with 5.0SP2 version.
     */
    protected FlowScheduleType legFlowScheduleType;

    /**
     * TagNum = 999. Starting with 5.0 version.
     */
    protected UnitOfMeasure legUnitOfMeasure;

    /**
     * TagNum = 1224. Starting with 5.0SP1 version.
     */
    protected Double legUnitOfMeasureQty;

    /**
     * TagNum = 1421. Starting with 5.0SP1 version.
     */
    protected PriceUnitOfMeasure legPriceUnitOfMeasure;

    /**
     * TagNum = 1422. Starting with 5.0SP1 version.
     */
    protected Double legPriceUnitOfMeasureQty;

    /**
     * TagNum = 1001. Starting with 5.0 version.
     */
    protected TimeUnit legTimeUnit;

    /**
     * TagNum = 1420. Starting with 5.0SP1 version.
     */
    protected ExerciseStyle legExerciseStyle;

    /**
     * TagNum = 615. Starting with 4.4 version.
     */
    protected Double legCouponRate;

    /**
     * TagNum = 616. Starting with 4.4 version.
     */
    protected String legSecurityExchange;

    /**
     * TagNum = 617. Starting with 4.4 version.
     */
    protected String legIssuer;

    /**
     * TagNum = 618. Starting with 4.4 version.
     */
    protected Integer encodedLegIssuerLen;

    /**
     * TagNum = 619. Starting with 4.4 version.
     */
    protected byte[] encodedLegIssuer;

    /**
     * TagNum = 620. Starting with 4.4 version.
     */
    protected String legSecurityDesc;

    /**
     * TagNum = 621. Starting with 4.4 version.
     */
    protected Integer encodedLegSecurityDescLen;

    /**
     * TagNum = 622. Starting with 4.4 version.
     */
    protected byte[] encodedLegSecurityDesc;

    /**
     * TagNum = 623. Starting with 4.4 version.
     */
    protected Double legRatioQty;

    /**
     * TagNum = 624. Starting with 4.4 version.
     */
    protected Side legSide;

    /**
     * TagNum = 556. Starting with 4.4 version.
     */
    protected Currency legCurrency;

    /**
     * TagNum = 740. Starting with 4.4 version.
     */
    protected String legPool;

    /**
     * TagNum = 739. Starting with 4.4 version.
     */
    protected Date legDatedDate;

    /**
     * TagNum = 955. Starting with 4.4 version.
     */
    protected String legContractSettlMonth;

    /**
     * TagNum = 956. Starting with 4.4 version.
     */
    protected Date legInterestAccrualDate;

    /**
     * TagNum = 1358. Starting with 5.0SP1 version.
     */
    protected PutOrCall legPutOrCall;

    /**
     * TagNum = 1017. Starting with 5.0SP1 version.
     */
    protected Double legOptionRatio;

    /**
     * TagNum = 566. Starting with 5.0SP1 version.
     */
    protected Double legPrice;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public InstrumentLeg() {
    }
    
    public InstrumentLeg(FragmentContext context) {
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
    @TagNumRef(tagNum=TagNum.LegSymbol, required=true, condRequired=true)
    public String getLegSymbol() {
        return legSymbol;
    }

    /**
     * Message field setter.
     * @param legSymbol field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSymbol, required=true, condRequired=true)
    public void setLegSymbol(String legSymbol) {
        this.legSymbol = legSymbol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSymbolSfx)
    public String getLegSymbolSfx() {
        return legSymbolSfx;
    }

    /**
     * Message field setter.
     * @param legSymbolSfx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSymbolSfx)
    public void setLegSymbolSfx(String legSymbolSfx) {
        this.legSymbolSfx = legSymbolSfx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityID)
    public String getLegSecurityID() {
        return legSecurityID;
    }

    /**
     * Message field setter.
     * @param legSecurityID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityID)
    public void setLegSecurityID(String legSecurityID) {
        this.legSecurityID = legSecurityID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityIDSource)
    public String getLegSecurityIDSource() {
        return legSecurityIDSource;
    }

    /**
     * Message field setter.
     * @param legSecurityIDSource field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityIDSource)
    public void setLegSecurityIDSource(String legSecurityIDSource) {
        this.legSecurityIDSource = legSecurityIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegSecurityAltID)
    public Integer getNoLegSecurityAltID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of LegSecurityAltIDGroup groups. It will also create an array
     * of LegSecurityAltIDGroup objects and set the <code>legSecurityAltIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>legSecurityAltIDGroups</code> array they will be discarded.<br/>
     * @param noLegSecurityAltID number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegSecurityAltID)
    public void setNoLegSecurityAltID(Integer noLegSecurityAltID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for <code>LegSecurityAltIDGroup</code> array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public LegSecurityAltIDGroup[] getLegSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a  <code>LegSecurityAltIDGroup</code> object to the existing array of <code>legSecurityAltIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegSecurityAltID</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegSecurityAltID</code> method has been called there will already be a number of objects in the
     * <code>legSecurityAltIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public LegSecurityAltIDGroup addLegSecurityAltIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a  <code>LegSecurityAltIDGroup</code> object from the existing array of <code>legSecurityAltIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegSecurityAltID</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public LegSecurityAltIDGroup deleteLegSecurityAltIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the <code>LegSecurityAltIDGroup</code> objects from the <code>legSecurityAltIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegSecurityAltID</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearLegSecurityAltIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegProduct)
    public Product getLegProduct() {
        return legProduct;
    }

    /**
     * Message field setter.
     * @param legProduct field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegProduct)
    public void setLegProduct(Product legProduct) {
        this.legProduct = legProduct;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCFICode)
    public String getLegCFICode() {
        return legCFICode;
    }

    /**
     * Message field setter.
     * @param legCFICode field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCFICode)
    public void setLegCFICode(String legCFICode) {
        this.legCFICode = legCFICode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityType)
    public String getLegSecurityType() {
        return legSecurityType;
    }

    /**
     * Message field setter.
     * @param legSecurityType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityType)
    public void setLegSecurityType(String legSecurityType) {
        this.legSecurityType = legSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecuritySubType)
    public String getLegSecuritySubType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legSecuritySubType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecuritySubType)
    public void setLegSecuritySubType(String legSecuritySubType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegMaturityMonthYear)
    public String getLegMaturityMonthYear() {
        return legMaturityMonthYear;
    }

    /**
     * Message field setter.
     * @param legMaturityMonthYear field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegMaturityMonthYear)
    public void setLegMaturityMonthYear(String legMaturityMonthYear) {
        this.legMaturityMonthYear = legMaturityMonthYear;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegMaturityDate)
    public Date getLegMaturityDate() {
        return legMaturityDate;
    }

    /**
     * Message field setter.
     * @param legMaturityDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegMaturityDate)
    public void setLegMaturityDate(Date legMaturityDate) {
        this.legMaturityDate = legMaturityDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LegMaturityTime)
    public Date getLegMaturityTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legMaturityTime field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LegMaturityTime)
    public void setLegMaturityTime(Date legMaturityTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCouponPaymentDate)
    public Date getLegCouponPaymentDate() {
        return legCouponPaymentDate;
    }

    /**
     * Message field setter.
     * @param legCouponPaymentDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCouponPaymentDate)
    public void setLegCouponPaymentDate(Date legCouponPaymentDate) {
        this.legCouponPaymentDate = legCouponPaymentDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegIssueDate)
    public Date getLegIssueDate() {
        return legIssueDate;
    }

    /**
     * Message field setter.
     * @param legIssueDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegIssueDate)
    public void setLegIssueDate(Date legIssueDate) {
        this.legIssueDate = legIssueDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRepoCollateralSecurityType)
    public String getLegRepoCollateralSecurityType() {
        return legRepoCollateralSecurityType;
    }

    /**
     * Message field setter.
     * @param legRepoCollateralSecurityType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRepoCollateralSecurityType)
    public void setLegRepoCollateralSecurityType(String legRepoCollateralSecurityType) {
        this.legRepoCollateralSecurityType = legRepoCollateralSecurityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRepurchaseTerm)
    public Integer getLegRepurchaseTerm() {
        return legRepurchaseTerm;
    }

    /**
     * Message field setter.
     * @param legRepurchaseTerm field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRepurchaseTerm)
    public void setLegRepurchaseTerm(Integer legRepurchaseTerm) {
        this.legRepurchaseTerm = legRepurchaseTerm;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRepurchaseRate)
    public Double getLegRepurchaseRate() {
        return legRepurchaseRate;
    }

    /**
     * Message field setter.
     * @param legRepurchaseRate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRepurchaseRate)
    public void setLegRepurchaseRate(Double legRepurchaseRate) {
        this.legRepurchaseRate = legRepurchaseRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegFactor)
    public Double getLegFactor() {
        return legFactor;
    }

    /**
     * Message field setter.
     * @param legFactor field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegFactor)
    public void setLegFactor(Double legFactor) {
        this.legFactor = legFactor;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCreditRating)
    public String getLegCreditRating() {
        return legCreditRating;
    }

    /**
     * Message field setter.
     * @param legCreditRating field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCreditRating)
    public void setLegCreditRating(String legCreditRating) {
        this.legCreditRating = legCreditRating;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegInstrRegistry)
    public String getLegInstrRegistry() {
        return legInstrRegistry;
    }

    /**
     * Message field setter.
     * @param legInstrRegistry field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegInstrRegistry)
    public void setLegInstrRegistry(String legInstrRegistry) {
        this.legInstrRegistry = legInstrRegistry;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCountryOfIssue)
    public String getLegCountryOfIssue() {
        return legCountryOfIssue;
    }

    /**
     * Message field setter.
     * @param legCountryOfIssue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCountryOfIssue)
    public void setLegCountryOfIssue(String legCountryOfIssue) {
        this.legCountryOfIssue = legCountryOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegStateOrProvinceOfIssue)
    public String getLegStateOrProvinceOfIssue() {
        return legStateOrProvinceOfIssue;
    }

    /**
     * Message field setter.
     * @param legStateOrProvinceOfIssue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegStateOrProvinceOfIssue)
    public void setLegStateOrProvinceOfIssue(String legStateOrProvinceOfIssue) {
        this.legStateOrProvinceOfIssue = legStateOrProvinceOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegLocaleOfIssue)
    public String getLegLocaleOfIssue() {
        return legLocaleOfIssue;
    }

    /**
     * Message field setter.
     * @param legLocaleOfIssue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegLocaleOfIssue)
    public void setLegLocaleOfIssue(String legLocaleOfIssue) {
        this.legLocaleOfIssue = legLocaleOfIssue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRedemptionDate)
    public Date getLegRedemptionDate() {
        return legRedemptionDate;
    }

    /**
     * Message field setter.
     * @param legRedemptionDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRedemptionDate)
    public void setLegRedemptionDate(Date legRedemptionDate) {
        this.legRedemptionDate = legRedemptionDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegStrikePrice)
    public Double getLegStrikePrice() {
        return legStrikePrice;
    }

    /**
     * Message field setter.
     * @param legStrikePrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegStrikePrice)
    public void setLegStrikePrice(Double legStrikePrice) {
        this.legStrikePrice = legStrikePrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegStrikeCurrency)
    public Currency getLegStrikeCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legStrikeCurrency field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegStrikeCurrency)
    public void setLegStrikeCurrency(Currency legStrikeCurrency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegOptAttribute)
    public Character getLegOptAttribute() {
        return legOptAttribute;
    }

    /**
     * Message field setter.
     * @param legOptAttribute field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegOptAttribute)
    public void setLegOptAttribute(Character legOptAttribute) {
        this.legOptAttribute = legOptAttribute;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegContractMultiplier)
    public Double getLegContractMultiplier() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legContractMultiplier field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegContractMultiplier)
    public void setLegContractMultiplier(Double legContractMultiplier) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LegContractMultiplierUnit)
    public ContractMultiplierUnit getLegContractMultiplierUnit() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legContractMultiplierUnit field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LegContractMultiplierUnit)
    public void setLegContractMultiplierUnit(ContractMultiplierUnit legContractMultiplierUnit) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LegFlowScheduleType)
    public FlowScheduleType getLegFlowScheduleType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legFlowScheduleType field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.LegFlowScheduleType)
    public void setLegFlowScheduleType(FlowScheduleType legFlowScheduleType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LegUnitOfMeasure)
    public UnitOfMeasure getLegUnitOfMeasure() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legUnitOfMeasure field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LegUnitOfMeasure)
    public void setLegUnitOfMeasure(UnitOfMeasure legUnitOfMeasure) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegUnitOfMeasureQty)
    public Double getLegUnitOfMeasureQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legUnitOfMeasureQty field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegUnitOfMeasureQty)
    public void setLegUnitOfMeasureQty(Double legUnitOfMeasureQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPriceUnitOfMeasure)
    public PriceUnitOfMeasure getLegPriceUnitOfMeasure() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legPriceUnitOfMeasure field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPriceUnitOfMeasure)
    public void setLegPriceUnitOfMeasure(PriceUnitOfMeasure legPriceUnitOfMeasure) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPriceUnitOfMeasureQty)
    public Double getLegPriceUnitOfMeasureQty() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legPriceUnitOfMeasureQty field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPriceUnitOfMeasureQty)
    public void setLegPriceUnitOfMeasureQty(Double legPriceUnitOfMeasureQty) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LegTimeUnit)
    public TimeUnit getLegTimeUnit() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legTimeUnit field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.LegTimeUnit)
    public void setLegTimeUnit(TimeUnit legTimeUnit) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegExerciseStyle)
    public ExerciseStyle getLegExerciseStyle() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legExerciseStyle field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegExerciseStyle)
    public void setLegExerciseStyle(ExerciseStyle legExerciseStyle) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCouponRate)
    public Double getLegCouponRate() {
        return legCouponRate;
    }

    /**
     * Message field setter.
     * @param legCouponRate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCouponRate)
    public void setLegCouponRate(Double legCouponRate) {
        this.legCouponRate = legCouponRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityExchange)
    public String getLegSecurityExchange() {
        return legSecurityExchange;
    }

    /**
     * Message field setter.
     * @param legSecurityExchange field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityExchange)
    public void setLegSecurityExchange(String legSecurityExchange) {
        this.legSecurityExchange = legSecurityExchange;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegIssuer)
    public String getLegIssuer() {
        return legIssuer;
    }

    /**
     * Message field setter.
     * @param legIssuer field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegIssuer)
    public void setLegIssuer(String legIssuer) {
        this.legIssuer = legIssuer;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegIssuerLen)
    public Integer getEncodedLegIssuerLen() {
        return encodedLegIssuerLen;
    }

    /**
     * Message field setter.
     * @param encodedLegIssuerLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegIssuerLen)
    public void setEncodedLegIssuerLen(Integer encodedLegIssuerLen) {
        this.encodedLegIssuerLen = encodedLegIssuerLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegIssuer)
    public byte[] getEncodedLegIssuer() {
        return encodedLegIssuer;
    }

    /**
     * Message field setter.
     * @param encodedLegIssuer field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegIssuer)
    public void setEncodedLegIssuer(byte[] encodedLegIssuer) {
        this.encodedLegIssuer = encodedLegIssuer;
        if (encodedLegIssuerLen == null) {
            encodedLegIssuerLen = new Integer(encodedLegIssuer.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityDesc)
    public String getLegSecurityDesc() {
        return legSecurityDesc;
    }

    /**
     * Message field setter.
     * @param legSecurityDesc field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSecurityDesc)
    public void setLegSecurityDesc(String legSecurityDesc) {
        this.legSecurityDesc = legSecurityDesc;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegSecurityDesc)
    public byte[] getEncodedLegSecurityDesc() {
        return encodedLegSecurityDesc;
    }

    /**
     * Message field setter.
     * @param encodedLegSecurityDesc field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegSecurityDesc)
    public void setEncodedLegSecurityDesc(byte[] encodedLegSecurityDesc) {
        this.encodedLegSecurityDesc = encodedLegSecurityDesc;
         if (encodedLegSecurityDescLen == null) {
            encodedLegSecurityDescLen = new Integer(encodedLegSecurityDesc.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegSecurityDescLen)
    public Integer getEncodedLegSecurityDescLen() {
        return encodedLegSecurityDescLen;
    }

    /**
     * Message field setter.
     * @param encodedLegSecurityDescLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedLegSecurityDescLen)
    public void setEncodedLegSecurityDescLen(Integer encodedLegSecurityDescLen) {
        this.encodedLegSecurityDescLen = encodedLegSecurityDescLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRatioQty)
    public Double getLegRatioQty() {
        return legRatioQty;
    }

    /**
     * Message field setter.
     * @param legRatioQty field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegRatioQty)
    public void setLegRatioQty(Double legRatioQty) {
        this.legRatioQty = legRatioQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSide)
    public Side getLegSide() {
        return legSide;
    }

    /**
     * Message field setter.
     * @param legSide field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegSide)
    public void setLegSide(Side legSide) {
        this.legSide = legSide;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCurrency)
    public Currency getLegCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legCurrency field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegCurrency)
    public void setLegCurrency(Currency legCurrency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegPool)
    public String getLegPool() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legPool field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegPool)
    public void setLegPool(String legPool) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegDatedDate)
    public Date getLegDatedDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legDatedDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegDatedDate)
    public void setLegDatedDate(Date legDatedDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegContractSettlMonth)
    public String getLegContractSettlMonth() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legContractSettlMonth field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegContractSettlMonth)
    public void setLegContractSettlMonth(String legContractSettlMonth) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegInterestAccrualDate)
    public Date getLegInterestAccrualDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legInterestAccrualDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LegInterestAccrualDate)
    public void setLegInterestAccrualDate(Date legInterestAccrualDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPutOrCall)
    public PutOrCall getLegPutOrCall() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legPutOrCall field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPutOrCall)
    public void setLegPutOrCall(PutOrCall legPutOrCall) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegOptionRatio)
    public Double getLegOptionRatio() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legOptionRatio field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegOptionRatio)
    public void setLegOptionRatio(Double legOptionRatio) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPrice)
    public Double getLegPrice() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legPrice field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.LegPrice)
    public void setLegPrice(Double legPrice) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.LegSymbol.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (legSymbol == null) {
            errorMsg.append(" [LegSymbol]");
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
            TagEncoder.encode(bao, TagNum.LegSymbol, legSymbol);
            TagEncoder.encode(bao, TagNum.LegSymbolSfx, legSymbolSfx, sessionCharset);
            TagEncoder.encode(bao, TagNum.LegSecurityID, legSecurityID, sessionCharset);
            TagEncoder.encode(bao, TagNum.LegSecurityIDSource, legSecurityIDSource, sessionCharset);
            if (noLegSecurityAltID != null && noLegSecurityAltID.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoLegSecurityAltID, noLegSecurityAltID);
                if (legSecurityAltIDGroups != null && legSecurityAltIDGroups.length == noLegSecurityAltID.intValue()) {
                    for (int i = 0; i < noLegSecurityAltID.intValue(); i++) {
                        if (legSecurityAltIDGroups[i] != null) {
                            bao.write(legSecurityAltIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "LegSecurityAltIDGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegSecurityAltID.getValue(), error);
                }
            }
            if (legProduct != null) {
                TagEncoder.encode(bao, TagNum.LegProduct, legProduct.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegCFICode, legCFICode, sessionCharset);
            TagEncoder.encode(bao, TagNum.LegSecurityType, legSecurityType);
            TagEncoder.encode(bao, TagNum.LegSecuritySubType, legSecuritySubType);
            TagEncoder.encode(bao, TagNum.LegMaturityMonthYear, legMaturityMonthYear);
            TagEncoder.encodeDate(bao, TagNum.LegMaturityDate, legMaturityDate);
            TagEncoder.encodeTZTime(bao, TagNum.LegMaturityTime, legMaturityTime);
            TagEncoder.encodeDate(bao, TagNum.LegCouponPaymentDate, legCouponPaymentDate);
            TagEncoder.encodeDate(bao, TagNum.LegIssueDate, legIssueDate);
            TagEncoder.encode(bao, TagNum.LegRepoCollateralSecurityType, legRepoCollateralSecurityType);
            TagEncoder.encode(bao, TagNum.LegRepurchaseTerm, legRepurchaseTerm);
            TagEncoder.encode(bao, TagNum.LegRepurchaseRate, legRepurchaseRate);
            TagEncoder.encode(bao, TagNum.LegFactor, legFactor);
            TagEncoder.encode(bao, TagNum.LegCreditRating, legCreditRating);
            TagEncoder.encode(bao, TagNum.LegInstrRegistry, legInstrRegistry);
            TagEncoder.encode(bao, TagNum.LegCountryOfIssue, legCountryOfIssue);
            TagEncoder.encode(bao, TagNum.LegStateOrProvinceOfIssue, legStateOrProvinceOfIssue);
            TagEncoder.encode(bao, TagNum.LegLocaleOfIssue, legLocaleOfIssue);
            TagEncoder.encodeDate(bao, TagNum.LegRedemptionDate, legRedemptionDate);
            TagEncoder.encode(bao, TagNum.LegStrikePrice, legStrikePrice);
            if (legStrikeCurrency != null) {
                TagEncoder.encode(bao, TagNum.LegStrikeCurrency, legStrikeCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegOptAttribute, legOptAttribute);
            TagEncoder.encode(bao, TagNum.LegContractMultiplier, legContractMultiplier);
            if (legContractMultiplierUnit != null) {
                TagEncoder.encode(bao, TagNum.LegContractMultiplierUnit, legContractMultiplierUnit.getValue());
            }
            if (legFlowScheduleType != null) {
                TagEncoder.encode(bao, TagNum.LegFlowScheduleType, legFlowScheduleType.getValue());
            }
            if (legUnitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.LegUnitOfMeasure, legUnitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegUnitOfMeasureQty, legUnitOfMeasureQty);
            if (legPriceUnitOfMeasure != null) {
                TagEncoder.encode(bao, TagNum.LegPriceUnitOfMeasure, legPriceUnitOfMeasure.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegPriceUnitOfMeasureQty, legPriceUnitOfMeasureQty);
            if (legTimeUnit != null) {
                TagEncoder.encode(bao, TagNum.LegTimeUnit, legTimeUnit.getValue());
            }
            if (legExerciseStyle != null) {
                TagEncoder.encode(bao, TagNum.LegExerciseStyle, legExerciseStyle.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegCouponRate, legCouponRate);
            TagEncoder.encode(bao, TagNum.LegSecurityExchange, legSecurityExchange);
            TagEncoder.encode(bao, TagNum.LegIssuer, legIssuer, sessionCharset);
            if (encodedLegIssuerLen != null && encodedLegIssuerLen.intValue() > 0) {
                if (encodedLegIssuer != null && encodedLegIssuer.length > 0) {
                    encodedLegIssuerLen = new Integer(encodedLegIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedLegIssuerLen, encodedLegIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedLegIssuer, encodedLegIssuer);
                }
            }
            TagEncoder.encode(bao, TagNum.LegSecurityDesc, legSecurityDesc, sessionCharset);
            if (encodedLegSecurityDescLen != null && encodedLegSecurityDescLen.intValue() > 0) {
                if (encodedLegSecurityDesc != null && encodedLegSecurityDesc.length > 0) {
                    encodedLegSecurityDescLen = new Integer(encodedLegSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedLegSecurityDescLen, encodedLegSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedLegSecurityDesc, encodedLegSecurityDesc);
                }
            }
            TagEncoder.encode(bao, TagNum.LegRatioQty, legRatioQty);
            if (legSide != null) {
                TagEncoder.encode(bao, TagNum.LegSide, legSide.getValue());
            }
            if (legCurrency != null) {
                TagEncoder.encode(bao, TagNum.LegCurrency, legCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegPool, legPool);
            TagEncoder.encodeDate(bao, TagNum.LegDatedDate, legDatedDate);
            TagEncoder.encode(bao, TagNum.LegContractSettlMonth, legContractSettlMonth);
            TagEncoder.encodeDate(bao, TagNum.LegInterestAccrualDate, legInterestAccrualDate);
            if (legPutOrCall != null) {
                TagEncoder.encode(bao, TagNum.LegPutOrCall, legPutOrCall.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegOptionRatio, legOptionRatio);
            TagEncoder.encode(bao, TagNum.LegPrice, legPrice);
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
            case LegSymbol:
                legSymbol = new String(tag.value, sessionCharset);;
                break;

            case LegSymbolSfx:
                legSymbolSfx = new String(tag.value, sessionCharset);
                break;

            case LegSecurityID:
                legSecurityID = new String(tag.value, sessionCharset);
                break;

            case LegSecurityIDSource:
                legSecurityIDSource = new String(tag.value, sessionCharset);
                break;

            case NoLegSecurityAltID:
                noLegSecurityAltID = new Integer(new String(tag.value, sessionCharset));
                break;

            case LegProduct:
                legProduct = Product.valueFor(new Integer(new String(tag.value, sessionCharset)).intValue());
                break;

            case LegCFICode:
                legCFICode = new String(tag.value, sessionCharset);
                break;

            case LegSecurityType:
                legSecurityType = new String(tag.value, sessionCharset);
                break;

            case LegSecuritySubType:
                legSecuritySubType = new String(tag.value, sessionCharset);
                break;

            case LegMaturityMonthYear:
                legMaturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case LegMaturityDate:
                legMaturityDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegMaturityTime:
                legMaturityTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegCouponPaymentDate:
                legCouponPaymentDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegIssueDate:
                legIssueDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegRepoCollateralSecurityType:
                legRepoCollateralSecurityType = new String(tag.value, sessionCharset);
                break;

            case LegRepurchaseTerm:
                legRepurchaseTerm = new Integer(new String(tag.value, sessionCharset));
                break;

            case LegRepurchaseRate:
                legRepurchaseRate = new Double(new String(tag.value, sessionCharset));
                break;

            case LegFactor:
                legFactor = new Double(new String(tag.value, sessionCharset));
                break;

            case LegCreditRating:
                legCreditRating = new String(tag.value, sessionCharset);
                break;

            case LegInstrRegistry:
                legInstrRegistry = new String(tag.value, sessionCharset);
                break;

            case LegCountryOfIssue:
                legCountryOfIssue = new String(tag.value, sessionCharset);
                break;

            case LegStateOrProvinceOfIssue:
                legStateOrProvinceOfIssue = new String(tag.value, sessionCharset);
                break;

            case LegLocaleOfIssue:
                legLocaleOfIssue = new String(tag.value, sessionCharset);
                break;

            case LegRedemptionDate:
                legRedemptionDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegStrikePrice:
                legStrikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case LegStrikeCurrency:
                legStrikeCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegOptAttribute:
                legOptAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case LegContractMultiplier:
                legContractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case LegContractMultiplierUnit:
                legContractMultiplierUnit = ContractMultiplierUnit.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LegFlowScheduleType:
                legFlowScheduleType = FlowScheduleType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LegUnitOfMeasure:
                legUnitOfMeasure = UnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegUnitOfMeasureQty:
                legUnitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegPriceUnitOfMeasure:
                legPriceUnitOfMeasure = PriceUnitOfMeasure.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegPriceUnitOfMeasureQty:
                legPriceUnitOfMeasureQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegTimeUnit:
                legTimeUnit = TimeUnit.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegExerciseStyle:
                legExerciseStyle = ExerciseStyle.valueFor(new Integer(new String(tag.value, sessionCharset)).intValue());
                break;

            case LegCouponRate:
                legCouponRate = new Double(new String(tag.value, sessionCharset));
                break;

            case LegSecurityExchange:
                legSecurityExchange = new String(tag.value, sessionCharset);
                break;

            case LegIssuer:
                legIssuer = new String(tag.value, sessionCharset);
                break;

            case EncodedLegIssuerLen:
                encodedLegIssuerLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case LegSecurityDesc:
                legSecurityDesc = new String(tag.value, sessionCharset);
                break;

            case EncodedLegSecurityDescLen:
                encodedLegSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case LegRatioQty:
                legRatioQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegSide:
                legSide = Side.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case LegCurrency:
                legCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegPool:
                legPool = new String(tag.value, sessionCharset);
                break;

            case LegDatedDate:
                legDatedDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegContractSettlMonth:
                legContractSettlMonth = new String(tag.value, sessionCharset);
                break;

            case LegInterestAccrualDate:
                legInterestAccrualDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegPutOrCall:
                legPutOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LegOptionRatio:
                legOptionRatio = new Double(new String(tag.value, sessionCharset));
                break;

            case LegPrice:
                legPrice = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [InstrumentLeg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {

        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedLegIssuerLen.getValue()) {
            try {
                encodedLegIssuerLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedLegIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedLegIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedLegIssuerLen.intValue());
            encodedLegIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedLegSecurityDescLen.getValue()) {
            try {
                encodedLegSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedLegSecurityDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedLegSecurityDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedLegSecurityDescLen.intValue());
            encodedLegSecurityDesc = dataTag.value;
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
        b.append("{InstrumentLeg=");
        printTagValue(b, TagNum.LegSymbol, legSymbol);
        printTagValue(b, TagNum.LegSymbolSfx, legSymbolSfx);
        printTagValue(b, TagNum.LegSecurityID, legSecurityID);
        printTagValue(b, TagNum.LegSecurityIDSource, legSecurityIDSource);
        printTagValue(b, TagNum.NoLegSecurityAltID, noLegSecurityAltID);
        printTagValue(b, legSecurityAltIDGroups);
        printTagValue(b, TagNum.LegProduct, legProduct);
        printTagValue(b, TagNum.LegCFICode, legCFICode);
        printTagValue(b, TagNum.LegSecurityType, legSecurityType);
        printTagValue(b, TagNum.LegSecuritySubType, legSecuritySubType);
        printTagValue(b, TagNum.LegMaturityMonthYear, legMaturityMonthYear);
        printDateTagValue(b, TagNum.LegMaturityDate, legMaturityDate);
        printTimeTagValue(b, TagNum.LegMaturityTime, legMaturityTime);
        printDateTagValue(b, TagNum.LegCouponPaymentDate, legCouponPaymentDate);
        printDateTagValue(b, TagNum.LegIssueDate, legIssueDate);
        printTagValue(b, TagNum.LegRepoCollateralSecurityType, legRepoCollateralSecurityType);
        printTagValue(b, TagNum.LegRepurchaseTerm, legRepurchaseTerm);
        printTagValue(b, TagNum.LegRepurchaseRate, legRepurchaseRate);
        printTagValue(b, TagNum.LegFactor, legFactor);
        printTagValue(b, TagNum.LegCreditRating, legCreditRating);
        printTagValue(b, TagNum.LegInstrRegistry, legInstrRegistry);
        printTagValue(b, TagNum.LegCountryOfIssue, legCountryOfIssue);
        printTagValue(b, TagNum.LegStateOrProvinceOfIssue, legStateOrProvinceOfIssue);
        printTagValue(b, TagNum.LegLocaleOfIssue, legLocaleOfIssue);
        printDateTagValue(b, TagNum.LegRedemptionDate, legRedemptionDate);
        printTagValue(b, TagNum.LegStrikePrice, legStrikePrice);
        printTagValue(b, TagNum.LegStrikeCurrency, legStrikeCurrency);
        printTagValue(b, TagNum.LegOptAttribute, legOptAttribute);
        printTagValue(b, TagNum.LegContractMultiplier, legContractMultiplier);
        printTagValue(b, TagNum.LegUnitOfMeasure, legUnitOfMeasure);
        printTagValue(b, TagNum.LegUnitOfMeasureQty, legUnitOfMeasureQty);
        printTagValue(b, TagNum.LegPriceUnitOfMeasure, legPriceUnitOfMeasure);
        printTagValue(b, TagNum.LegPriceUnitOfMeasureQty, legPriceUnitOfMeasureQty);
        printTagValue(b, TagNum.LegTimeUnit, legTimeUnit);
        printTagValue(b, TagNum.LegExerciseStyle, legExerciseStyle);
        printTagValue(b, TagNum.LegCouponRate, legCouponRate);
        printTagValue(b, TagNum.LegSecurityExchange, legSecurityExchange);
        printTagValue(b, TagNum.LegIssuer, legIssuer);
        printTagValue(b, TagNum.EncodedLegIssuerLen, encodedLegIssuerLen);
        printTagValue(b, TagNum.XmlData, encodedLegIssuer);
        printTagValue(b, TagNum.LegSecurityDesc, legSecurityDesc);
        printTagValue(b, TagNum.EncodedLegSecurityDescLen, encodedLegSecurityDescLen);
        printTagValue(b, TagNum.XmlData, encodedLegSecurityDesc);
        printTagValue(b, TagNum.LegRatioQty, legRatioQty);
        printTagValue(b, TagNum.LegSide, legSide);
        printTagValue(b, TagNum.LegCurrency, legCurrency);
        printTagValue(b, TagNum.LegPool, legPool);
        printDateTagValue(b, TagNum.LegDatedDate, legDatedDate);
        printTagValue(b, TagNum.LegContractSettlMonth, legContractSettlMonth);
        printDateTagValue(b, TagNum.LegInterestAccrualDate, legInterestAccrualDate);
        printTagValue(b, TagNum.LegPutOrCall, legPutOrCall);
        printTagValue(b, TagNum.LegOptionRatio, legOptionRatio);
        printTagValue(b, TagNum.LegPrice, legPrice);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
