/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsg.java
 *
 * $Id: NewOrderCrossMsg.java,v 1.3 2011-05-17 09:28:24 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.SideCrossOrdModGroup;
import net.hades.fix.message.group.StrategyParametersGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;

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
import net.hades.fix.message.comp.DiscretionInstructions;
import net.hades.fix.message.comp.DisplayInstruction;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.PegInstructions;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.TriggeringInstruction;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CancellationRights;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.DiscretionInst;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.GTBookingInst;
import net.hades.fix.message.type.HandlInst;
import net.hades.fix.message.type.MoneyLaunderingStatus;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.PriceProtectionScope;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.ProcessCode;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.TimeInForce;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Used to submit a cross order into a market. The cross order contains two order sides (a buy and a sell). The
 * cross order is identified by its CrossID.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 10/05/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class NewOrderCrossMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CrossID.getValue(),
        TagNum.CrossType.getValue(),
        TagNum.CrossPrioritization.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.HandlInst.getValue(),
        TagNum.ExecInst.getValue(),
        TagNum.MinQty.getValue(),
        TagNum.MatchIncrement.getValue(),
        TagNum.MaxPriceLevels.getValue(),
        TagNum.MaxFloor.getValue(),
        TagNum.ExDestination.getValue(),
        TagNum.ExDestinationIDSource.getValue(),
        TagNum.NoTradingSessions.getValue(),
        TagNum.ProcessCode.getValue(),
        TagNum.PrevClosePx.getValue(),
        TagNum.LocateReqd.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.TransBkdTime.getValue(),
        TagNum.OrdType.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceProtectionScope.getValue(),
        TagNum.StopPx.getValue(),
        TagNum.Currency.getValue(),
        TagNum.ComplianceID.getValue(),
        TagNum.IOIID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.TimeInForce.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.GTBookingInst.getValue(),
        TagNum.MaxShow.getValue(),
        TagNum.TargetStrategy.getValue(),
        TagNum.NoStrategyParameters.getValue(),
        TagNum.TargetStrategyParameters.getValue(),
        TagNum.ParticipationRate.getValue(),
        TagNum.CancellationRights.getValue(),
        TagNum.MoneyLaunderingStatus.getValue(),
        TagNum.RegistID.getValue(),
        TagNum.Designation.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CrossID.getValue(),
        TagNum.CrossType.getValue(),
        TagNum.CrossPrioritization.getValue(),
        TagNum.NoSides.getValue(),
        TagNum.HandlInst.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.OrdType.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 548 REQUIRED. Starting with 4.3 version.
     */
    protected String crossID;

    /**
     * TagNum = 549 REQUIRED. Starting with 4.3 version.
     */
    protected CrossType crossType;

    /**
     * TagNum = 550 REQUIRED. Starting with 4.3 version.
     */
    protected CrossPrioritization crossPrioritization;
    
    /**
     * Starting with 5.0 version.
     */
    protected RootParties rootParties;

    /**
     * TagNum = 78 REQUIRED. Starting with 4.3 version.
     */
    protected Integer noSides;

    /**
     * Starting with 4.3 version.
     */
    protected SideCrossOrdModGroup[] sideCrossOrdModGroups;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;

    /**
     * TagNum = 63. Starting with 4.3 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.3 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 21. Starting with 4.3 version.
     */
    protected HandlInst handlInst;

    /**
     * TagNum = 18. Starting with 4.3 version.
     */
    protected String execInst;

    /**
     * TagNum = 110. Starting with 4.3 version.
     */
    protected Double minQty;

    /**
     * TagNum = 1089. Starting with 5.0 version.
     */
    protected Double matchIncrement;

    /**
     * TagNum = 1090. Starting with 5.0 version.
     */
    protected Integer maxPriceLevels;

    /**
     * Starting with 5.0 version.
     */
    protected DisplayInstruction displayInstruction;

    /**
     * TagNum = 111. Starting with 4.3 version.
     */
    protected Double maxFloor;

    /**
     * TagNum = 100. Starting with 4.3 version.
     */
    protected String exDestination;

    /**
     * TagNum = 1133. Starting with 5.0SP1 version.
     */
    protected ExDestinationIDSource exDestinationIDSource;

    /**
     * TagNum = 386. Starting with 4.3 version.
     */
    protected Integer noTradingSessions;

    /**
     * Starting with 4.3 version.
     */
    protected TradingSessionGroup[] tradingSessionGroups;

    /**
     * TagNum = 81. Starting with 4.3 version.
     */
    protected ProcessCode processCode;

    /**
     * TagNum = 140. Starting with 4.3 version.
     */
    protected Double prevClosePx;

    /**
     * TagNum = 114. Starting with 4.3 version.
     */
    protected Boolean locateReqd;

    /**
     * TagNum = 60 REQUIRED. Starting with 4.3 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 483. Starting with 5.0 version.
     */
    protected Date transBkdTime;

    /**
     * Starting with 4.3 version.
     */
    protected Stipulations stipulations;

    /**
     * TagNum = 40. Starting with 4.3 version.
     */
    protected OrdType ordType;

    /**
     * TagNum = 423. Starting with 4.3 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 44. Starting with 4.3 version.
     */
    protected Double price;

    /**
     * TagNum = 1092. Starting with 5.0 version.
     */
    protected PriceProtectionScope priceProtectionScope;

    /**
     * TagNum = 99. Starting with 4.3 version.
     */
    protected Double stopPx;

    /**
     * Starting with 5.0 version.
     */
    protected TriggeringInstruction triggeringInstruction;

    /**
     * Starting with 4.3 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;

    /**
     * Starting with 4.3 version.
     */
    protected YieldData yieldData;

    /**
     * TagNum = 15. Starting with 4.3 version.
     */
    protected Currency currency;

    /**
     * TagNum = 376. Starting with 4.3 version.
     */
    protected String complianceID;

    /**
     * TagNum = 23. Starting with 4.3 version.
     */
    protected String IOIID;

    /**
     * TagNum = 117. Starting with 4.3 version.
     */
    protected String quoteID;

    /**
     * TagNum = 59. Starting with 4.3 version.
     */
    protected TimeInForce timeInForce;

    /**
     * TagNum = 168. Starting with 4.3 version.
     */
    protected Date effectiveTime;

    /**
     * TagNum = 432. Starting with 4.3 version.
     */
    protected Date expireDate;

    /**
     * TagNum = 126. Starting with 4.3 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 427. Starting with 4.3 version.
     */
    protected GTBookingInst GTBookingInst;

    /**
     * TagNum = 210. Starting with 4.3 version.
     */
    protected Double maxShow;

    /**
     * Starting with 4.4 version.
     */
    protected PegInstructions pegInstructions;

    /**
     * Starting with 4.4 version.
     */
    protected DiscretionInstructions discretionInstructions;

    /**
     * TagNum = 847. Starting with 4.4 version.
     */
    protected Integer targetStrategy;

    /**
     * TagNum = 957. Starting with 5.0 version.
     */
    protected Integer noStrategyParameters;

    /**
     * Starting with 5.0 version.
     */
    protected StrategyParametersGroup[] strategyParametersGroups;

    /**
     * TagNum = 848. Starting with 4.4 version.
     */
    protected String targetStrategyParameters;

    /**
     * TagNum = 849. Starting with 4.4 version.
     */
    protected Double participationRate;

    /**
     * TagNum = 211. Starting with 4.3 version.
     */
    protected Double pegOffsetValue;

    /**
     * TagNum = 388. Starting with 4.3 version.
     */
    protected DiscretionInst discretionInst;

    /**
     * TagNum = 389. Starting with 4.3 version.
     */
    protected Double discretionOffsetValue;

    /**
     * TagNum = 480. Starting with 4.3 version.
     */
    protected CancellationRights cancellationRights;

    /**
     * TagNum = 481. Starting with 4.3 version.
     */
    protected MoneyLaunderingStatus moneyLaunderingStatus;

    /**
     * TagNum = 513. Starting with 4.3 version.
     */
    protected String registID;

    /**
     * TagNum = 494. Starting with 4.3 version.
     */
    protected String designation;

    /**
     * TagNum = 158. Starting with 4.3 version.
     */
    protected Double accruedInterestRate;

    /**
     * TagNum = 158. Starting with 4.3 version.
     */
    protected Double accruedInterestAmt;

    /**
     * TagNum = 118. Starting with 4.3 version.
     */
    protected Double netMoney;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewOrderCrossMsg() {
        super();
    }

    public NewOrderCrossMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public NewOrderCrossMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.NewOrderCross.getValue(), beginString);
    }

    public NewOrderCrossMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.NewOrderCross.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossID, required=true)
    public String getCrossID() {
        return crossID;
    }

    /**
     * Message field setter.
     * @param crossID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossID, required=true)
    public void setCrossID(String crossID) {
        this.crossID = crossID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossType, required=true)
    public CrossType getCrossType() {
        return crossType;
    }

    /**
     * Message field setter.
     * @param crossType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossType, required=true)
    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossPrioritization, required=true)
    public CrossPrioritization getCrossPrioritization() {
        return crossPrioritization;
    }

    /**
     * Message field setter.
     * @param crossPrioritization field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CrossPrioritization, required=true)
    public void setCrossPrioritization(CrossPrioritization crossPrioritization) {
        this.crossPrioritization = crossPrioritization;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public RootParties getRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to the proper implementation.
     */
    @FIXVersion(introduced="5.0")
    public void setRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the RootParties component class to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearRootParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSides)
    public Integer getNoSides() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SideCrossOrdModGroup} groups. It will also create an array
     * of {@link SideCrossOrdModGroup} objects and set the <code>sideCrossOrdModGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>sideCrossOrdModGroups</code> array they will be discarded.<br/>
     * @param noSides field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSides)
    public void setNoSides(Integer noSides) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SideCrossOrdModGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public SideCrossOrdModGroup[] getSideCrossOrdModGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link SideCrossOrdModGroup} object to the existing array of <code>sideCrossOrdModGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * Note: If the <code>setNoSides</code> method has been called there will already be a number of objects in the
     * <code>sideCrossOrdModGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public SideCrossOrdModGroup addSideCrossOrdModGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SideCrossOrdModGroup} object from the existing array of <code>sideCrossOrdModGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSides</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public SideCrossOrdModGroup deleteSideCrossOrdModGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SideCrossOrdModGroup} objects from the <code>sideCrossOrdModGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSides</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearSideCrossOrdModGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument attribute to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public Integer getNoUnderlyings() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingInstrument} components. It will also create an array
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public void setNoUnderlyings(Integer noUnderlyings) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UnderlyingInstrument} object to the existing array of <code>underlyingInstruments</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyings</code> method has been called there will already be a number of objects in the
     * <code>underlyingInstruments</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument addUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingInstrument} object from the existing array of <code>underlyingInstruments</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingInstrument} objects from the <code>underlyingInstruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyings</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrumentLeg} components. It will also create an array
     * of {@link InstrumentLeg} objects and set the <code>instrumentLegs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrumentLegs</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrumentLeg} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public InstrumentLeg[] getInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrumentLeg} object to the existing array of <code>instrumentLegs</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>instrumentLegs</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg addInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrumentLeg} object from the existing array of <code>instrumentLegs</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg deleteInstrumentLeg(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentLeg} objects from the <code>instrumentLegs</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public HandlInst getHandlInst() {
        return handlInst;
    }

    /**
     * Message field setter.
     * @param handlInst field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HandlInst)
    public void setHandlInst(HandlInst handlInst) {
        this.handlInst = handlInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public String getExecInst() {
        return execInst;
    }

    /**
     * Message field setter.
     * @param execInst field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExecInst)
    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MinQty)
    public Double getMinQty() {
        return minQty;
    }

    /**
     * Message field setter.
     * @param minQty field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MinQty)
    public void setMinQty(Double minQty) {
        this.minQty = minQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchIncrement)
    public Double getMatchIncrement() {
        return matchIncrement;
    }

    /**
     * Message field setter.
     * @param matchIncrement field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MatchIncrement)
    public void setMatchIncrement(Double matchIncrement) {
        this.matchIncrement = matchIncrement;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MaxPriceLevels)
    public Integer getMaxPriceLevels() {
        return maxPriceLevels;
    }

    /**
     * Message field setter.
     * @param maxPriceLevels field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MaxPriceLevels)
    public void setMaxPriceLevels(Integer maxPriceLevels) {
        this.maxPriceLevels = maxPriceLevels;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public DisplayInstruction getDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DisplayInstruction component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0")
    public void setDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the DisplayInstruction component to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearDisplayInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public Double getMaxFloor() {
        return maxFloor;
    }

    /**
     * Message field setter.
     * @param maxFloor field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxFloor)
    public void setMaxFloor(Double maxFloor) {
        this.maxFloor = maxFloor;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public String getExDestination() {
        return exDestination;
    }

    /**
     * Message field setter.
     * @param exDestination field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExDestination)
    public void setExDestination(String exDestination) {
        this.exDestination = exDestination;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ExDestinationIDSource)
    public ExDestinationIDSource getExDestinationIDSource() {
        return exDestinationIDSource;
    }

    /**
     * Message field setter.
     * @param exDestinationIDSource field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ExDestinationIDSource)
    public void setExDestinationIDSource(ExDestinationIDSource exDestinationIDSource) {
        this.exDestinationIDSource = exDestinationIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoTradingSessions)
    public Integer getNoTradingSessions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TradingSessionGroup} groups. It will also create an array
     * of {@link TradingSessionGroup} objects and set the <code>tradingSessionGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>tradingSessionGroups</code> array they will be discarded.<br/>
     * @param noAllocs field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoTradingSessions)
    public void setNoTradingSessions(Integer noAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TradingSessionGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public TradingSessionGroup[] getTradingSessionGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TradingSessionGroup} object to the existing array of <code>tradingSessionGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTradingSessions</code> field to the proper value.<br/>
     * Note: If the <code>setNoTradingSessions</code> method has been called there will already be a number of objects in the
     * <code>tradingSessionGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public TradingSessionGroup addTradingSessionGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TradingSessionGroup} object from the existing array of <code>tradingSessionGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTradingSessions</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.3")
    public TradingSessionGroup deleteTradingSessionGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TradingSessionGroup} objects from the <code>tradingSessionGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTradingSessions</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearTradingSessionGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public ProcessCode getProcessCode() {
        return processCode;
    }

     /**
     * Message field setter.
     * @param processCode field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ProcessCode)
    public void setProcessCode(ProcessCode processCode) {
        this.processCode = processCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public Double getPrevClosePx() {
        return prevClosePx;
    }

    /**
     * Message field setter.
     * @param prevClosePx field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PrevClosePx)
    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public Boolean getLocateReqd() {
        return locateReqd;
    }

    /**
     * Message field setter.
     * @param locateReqd field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.LocateReqd)
    public void setLocateReqd(Boolean locateReqd) {
        this.locateReqd = locateReqd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public Date getTransBkdTime() {
        return transBkdTime;
    }

    /**
     * Message field setter.
     * @param transBkdTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransBkdTime)
    public void setTransBkdTime(Date transBkdTime) {
        this.transBkdTime = transBkdTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public Stipulations getStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrdType)
    public OrdType getOrdType() {
        return ordType;
    }

    /**
     * Message field setter.
     * @param ordType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrdType)
    public void setOrdType(OrdType ordType) {
        this.ordType = ordType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Price)
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PriceProtectionScope)
    public PriceProtectionScope getPriceProtectionScope() {
        return priceProtectionScope;
    }

    /**
     * Message field setter.
     * @param priceProtectionScope field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PriceProtectionScope)
    public void setPriceProtectionScope(PriceProtectionScope priceProtectionScope) {
        this.priceProtectionScope = priceProtectionScope;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.StopPx)
    public Double getStopPx() {
        return stopPx;
    }

    /**
     * Message field setter.
     * @param stopPx field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.StopPx)
    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public TriggeringInstruction getTriggeringInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TriggeringInstruction component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0")
    public void setTriggeringInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TriggeringInstruction component to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearTriggeringInstruction() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public YieldData getYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the YieldData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the YieldData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearYieldData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ComplianceID)
    public String getComplianceID() {
        return complianceID;
    }

    /**
     * Message field setter.
     * @param complianceID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ComplianceID)
    public void setComplianceID(String complianceID) {
        this.complianceID = complianceID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.IOIID)
    public String getIOIID() {
        return IOIID;
    }

    /**
     * Message field setter.
     * @param IOIID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.IOIID)
    public void setIOIID(String IOIID) {
        this.IOIID = IOIID;
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public String getQuoteID() {
        return quoteID;
    }

    /**
     * Message field setter.
     * @param quoteID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    /**
     * Message field setter.
     * @param timeInForce field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TimeInForce)
    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Message field setter.
     * @param effectiveTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Message field setter.
     * @param expireDate field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.GTBookingInst)
    public GTBookingInst getGTBookingInst() {
        return GTBookingInst;
    }

    /**
     * Message field setter.
     * @param GTBookingInst field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.GTBookingInst)
    public void setGTBookingInst(GTBookingInst GTBookingInst) {
        this.GTBookingInst = GTBookingInst;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public Double getMaxShow() {
        return maxShow;
    }

    /**
     * Message field setter.
     * @param maxShow field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MaxShow)
    public void setMaxShow(Double maxShow) {
        this.maxShow = maxShow;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public PegInstructions getPegInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the PegInstructions component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setPegInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the PegInstructions component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearPegInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public DiscretionInstructions getDiscretionInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the PegInstructions component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setDiscretionInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the PegInstructions component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearDiscretionInstructions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategy)
    public Integer getTargetStrategy() {
        return targetStrategy;
    }

    /**
     * Message field setter.
     * @param targetStrategy field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategy)
    public void setTargetStrategy(Integer targetStrategy) {
        this.targetStrategy = targetStrategy;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoStrategyParameters)
    public Integer getNoStrategyParameters() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link StrategyParametersGroup} groups. It will also create an array
     * of {@link StrategyParametersGroup} objects and set the <code>strategyParametersGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>strategyParametersGroups</code> array they will be discarded.<br/>
     * @param noStrategyParameters field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoStrategyParameters)
    public void setNoStrategyParameters(Integer noStrategyParameters) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link StrategyParametersGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0")
    public StrategyParametersGroup[] getStrategyParametersGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link StrategyParametersGroup} object to the existing array of <code>strategyParametersGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noStrategyParameters</code> field to the proper value.<br/>
     * Note: If the <code>setNoStrategyParameters</code> method has been called there will already be a number of objects in the
     * <code>strategyParametersGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public StrategyParametersGroup addStrategyParametersGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link StrategyParametersGroup} object from the existing array of <code>strategyParametersGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noStrategyParameters</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public StrategyParametersGroup deleteStrategyParametersGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link StrategyParametersGroup} objects from the <code>strategyParametersGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noStrategyParameters</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearStrategyParametersGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategyParameters)
    public String getTargetStrategyParameters() {
        return targetStrategyParameters;
    }

    /**
     * Message field setter.
     * @param targetStrategyParameters field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TargetStrategyParameters)
    public void setTargetStrategyParameters(String targetStrategyParameters) {
        this.targetStrategyParameters = targetStrategyParameters;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ParticipationRate)
    public Double getParticipationRate() {
        return participationRate;
    }

    /**
     * Message field setter.
     * @param participationRate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ParticipationRate)
    public void setParticipationRate(Double participationRate) {
        this.participationRate = participationRate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public Double getPegOffsetValue() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param pegOffsetValue field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PegOffsetValue)
    public void setPegOffsetValue(Double pegOffsetValue) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionInst)
    public DiscretionInst getDiscretionInst() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param discretionInst field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionInst)
    public void setDiscretionInst(DiscretionInst discretionInst) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionOffsetValue)
    public Double getDiscretionOffsetValue() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param discretionOffsetValue field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.DiscretionOffsetValue)
    public void setDiscretionOffsetValue(Double discretionOffsetValue) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CancellationRights)
    public CancellationRights getCancellationRights() {
        return cancellationRights;
    }

    /**
     * Message field setter.
     * @param cancellationRights field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.CancellationRights)
    public void setCancellationRights(CancellationRights cancellationRights) {
        this.cancellationRights = cancellationRights;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MoneyLaunderingStatus)
    public MoneyLaunderingStatus getMoneyLaunderingStatus() {
        return moneyLaunderingStatus;
    }

    /**
     * Message field setter.
     * @param moneyLaunderingStatus field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MoneyLaunderingStatus)
    public void setMoneyLaunderingStatus(MoneyLaunderingStatus moneyLaunderingStatus) {
        this.moneyLaunderingStatus = moneyLaunderingStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistID)
    public String getRegistID() {
        return registID;
    }

    /**
     * Message field setter.
     * @param registID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.RegistID)
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Designation)
    public String getDesignation() {
        return designation;
    }

    /**
     * Message field setter.
     * @param designation field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Designation)
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public Double getAccruedInterestRate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param accruedInterestRate field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestRate)
    public void setAccruedInterestRate(Double accruedInterestRate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public Double getAccruedInterestAmt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param accruedInterestAmt field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public Double getNetMoney() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param netMoney field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.NetMoney)
    public void setNetMoney(Double netMoney) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (crossID == null || crossID.trim().isEmpty()) {
            errorMsg.append(" [CrossID]");
            hasMissingTag = true;
        }
        if (crossType == null) {
            errorMsg.append(" [CrossType]");
            hasMissingTag = true;
        }
        if (crossPrioritization == null) {
            errorMsg.append(" [CrossPrioritization]");
            hasMissingTag = true;
        }
        if (noSides == null || sideCrossOrdModGroups == null || noSides.intValue() == 0) {
            errorMsg.append(" [NoSides]");
            hasMissingTag = true;
        }
        if (instrument == null || instrument.getSymbol() == null) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
            hasMissingTag = true;
        }
        if (ordType == null) {
            errorMsg.append(" [OrdType]");
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
            TagEncoder.encode(bao, TagNum.CrossID, crossID);
            if (crossType != null) {
                TagEncoder.encode(bao, TagNum.CrossType, crossType.getValue());
            }
            if (crossPrioritization != null) {
                TagEncoder.encode(bao, TagNum.CrossPrioritization, crossPrioritization.getValue());
            }
            if (rootParties != null) {
                bao.write(rootParties.encode(MsgSecureType.ALL_FIELDS));
            }
            
            if (noSides != null) {
                TagEncoder.encode(bao, TagNum.NoSides, noSides);
                if (sideCrossOrdModGroups != null && sideCrossOrdModGroups.length == noSides.intValue()) {
                    for (int i = 0; i < noSides.intValue(); i++) {
                        if (sideCrossOrdModGroups[i] != null) {
                            bao.write(sideCrossOrdModGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SideCrossOrdModGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSides.getValue(), error);
                }
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noUnderlyings != null) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            if (handlInst != null) {
                TagEncoder.encode(bao, TagNum.HandlInst, handlInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.ExecInst, execInst);
            TagEncoder.encode(bao, TagNum.MinQty, minQty);
            TagEncoder.encode(bao, TagNum.MatchIncrement, matchIncrement);
            TagEncoder.encode(bao, TagNum.MaxPriceLevels, maxPriceLevels);
            if (displayInstruction != null) {
                bao.write(displayInstruction.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.MaxFloor, maxFloor);
            TagEncoder.encode(bao, TagNum.ExDestination, exDestination);
            if (exDestinationIDSource != null) {
                TagEncoder.encode(bao, TagNum.ExDestinationIDSource, exDestinationIDSource.getValue());
            }
            if (noTradingSessions != null && noTradingSessions.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoTradingSessions, noTradingSessions);
                if (tradingSessionGroups != null && tradingSessionGroups.length == noTradingSessions.intValue()) {
                    for (int i = 0; i < noTradingSessions.intValue(); i++) {
                        if (tradingSessionGroups[i] != null) {
                            bao.write(tradingSessionGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TradingSessionsGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTradingSessions.getValue(), error);
                }
            }
            if (processCode != null) {
                TagEncoder.encode(bao, TagNum.ProcessCode, processCode.getValue());
            }
            TagEncoder.encode(bao, TagNum.PrevClosePx, prevClosePx);
            TagEncoder.encode(bao, TagNum.LocateReqd, locateReqd);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransBkdTime, transBkdTime);
            if (stipulations != null) {
                bao.write(stipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            if (ordType != null) {
                TagEncoder.encode(bao, TagNum.OrdType, ordType.getValue());
            }
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.Price, price);
            if (priceProtectionScope != null) {
                TagEncoder.encode(bao, TagNum.PriceProtectionScope, priceProtectionScope.getValue());
            }
            TagEncoder.encode(bao, TagNum.StopPx, stopPx);
            if (triggeringInstruction != null) {
                bao.write(triggeringInstruction.encode(MsgSecureType.ALL_FIELDS));
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (yieldData != null) {
                bao.write(yieldData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.ComplianceID, complianceID);
            TagEncoder.encode(bao, TagNum.IOIID, IOIID);
            TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            if (timeInForce != null) {
                TagEncoder.encode(bao, TagNum.TimeInForce, timeInForce.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            if (GTBookingInst != null) {
                TagEncoder.encode(bao, TagNum.GTBookingInst, GTBookingInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.MaxShow, maxShow);
            if (pegInstructions != null) {
                bao.write(pegInstructions.encode(MsgSecureType.ALL_FIELDS));
            }
            if (discretionInstructions != null) {
                bao.write(discretionInstructions.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.TargetStrategy, targetStrategy);
            if (noStrategyParameters != null) {
                TagEncoder.encode(bao, TagNum.NoStrategyParameters, noStrategyParameters);
                if (strategyParametersGroups != null && strategyParametersGroups.length == noStrategyParameters.intValue()) {
                    for (int i = 0; i < noStrategyParameters.intValue(); i++) {
                        if (strategyParametersGroups[i] != null) {
                            bao.write(strategyParametersGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "StrategyParametersGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoStrategyParameters.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.TargetStrategyParameters, targetStrategyParameters);
            TagEncoder.encode(bao, TagNum.ParticipationRate, participationRate);
            TagEncoder.encode(bao, TagNum.PegOffsetValue, pegOffsetValue);
            if (discretionInst != null) {
                TagEncoder.encode(bao, TagNum.DiscretionInst, discretionInst.getValue());
            }
            TagEncoder.encode(bao, TagNum.DiscretionOffsetValue, discretionOffsetValue);
            if (cancellationRights != null) {
                TagEncoder.encode(bao, TagNum.CancellationRights, cancellationRights.getValue());
            }
            if (moneyLaunderingStatus != null) {
                TagEncoder.encode(bao, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.RegistID, registID);
            TagEncoder.encode(bao, TagNum.Designation, designation);
            TagEncoder.encode(bao, TagNum.AccruedInterestRate, accruedInterestRate);
            TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            TagEncoder.encode(bao, TagNum.NetMoney, netMoney);
 
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
            case CrossID:
                crossID = new String(tag.value, sessionCharset);
                break;

            case CrossType:
                crossType = CrossType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case CrossPrioritization:
                crossPrioritization = CrossPrioritization.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoSides:
                noSides = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case HandlInst:
                handlInst = HandlInst.valueFor(new String(tag.value, sessionCharset));
                break;

            case ExecInst:
                execInst = new String(tag.value, sessionCharset);
                break;

            case MinQty:
                minQty = new Double(new String(tag.value, sessionCharset));
                break;

            case MatchIncrement:
                matchIncrement = new Double(new String(tag.value, sessionCharset));
                break;

            case MaxPriceLevels:
                maxPriceLevels = new Integer(new String(tag.value, sessionCharset));
                break;

            case MaxFloor:
                maxFloor = new Double(new String(tag.value, sessionCharset));
                break;

            case ExDestination:
                exDestination = new String(tag.value, sessionCharset);
                break;

            case ExDestinationIDSource:
                exDestinationIDSource = ExDestinationIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case NoTradingSessions:
                noTradingSessions = new Integer(new String(tag.value, sessionCharset));
                break;

            case ProcessCode:
                processCode = ProcessCode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case PrevClosePx:
                prevClosePx = new Double(new String(tag.value, sessionCharset));
                break;

            case LocateReqd:
                locateReqd = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransBkdTime:
                transBkdTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OrdType:
                ordType = OrdType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceProtectionScope:
                priceProtectionScope = PriceProtectionScope.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case StopPx:
                stopPx = new Double(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case ComplianceID:
                complianceID = new String(tag.value, sessionCharset);
                break;

            case IOIID:
                IOIID = new String(tag.value, sessionCharset);
                break;

           case QuoteID:
                quoteID = new String(tag.value, sessionCharset);
                break;

            case TimeInForce:
                timeInForce = TimeInForce.valueFor(new String(tag.value, sessionCharset));
                break;

            case EffectiveTime:
                effectiveTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExpireDate:
                expireDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case GTBookingInst:
                GTBookingInst = GTBookingInst.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case MaxShow:
                maxShow = new Double(new String(tag.value, sessionCharset));
                break;

            case TargetStrategy:
                targetStrategy = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoStrategyParameters:
                noStrategyParameters = new Integer(new String(tag.value, sessionCharset));
                break;

            case TargetStrategyParameters:
                targetStrategyParameters = new String(tag.value, sessionCharset);
                break;

            case ParticipationRate:
                participationRate = new Double(new String(tag.value, sessionCharset));
                break;

            case PegOffsetValue:
                pegOffsetValue = new Double(new String(tag.value, sessionCharset));
                break;

            case DiscretionInst:
                discretionInst = DiscretionInst.valueFor(new String(tag.value, sessionCharset));
                break;

            case DiscretionOffsetValue:
                discretionOffsetValue = new Double(new String(tag.value, sessionCharset));
                break;
               
            case CancellationRights:
                cancellationRights = CancellationRights.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MoneyLaunderingStatus:
                moneyLaunderingStatus = MoneyLaunderingStatus.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case RegistID:
                registID = new String(tag.value, sessionCharset);
                break;

            case Designation:
                designation = new String(tag.value, sessionCharset);
                break;

            case AccruedInterestRate:
                accruedInterestRate = new Double(new String(tag.value, sessionCharset));
                break;

            case AccruedInterestAmt:
                accruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case NetMoney:
                netMoney = new Double(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [NewOrderCrossMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        StringBuilder b = new StringBuilder("{NewOrderCrossMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.CrossID, crossID);
        printTagValue(b, TagNum.CrossType, crossType);
        printTagValue(b, TagNum.CrossPrioritization, crossPrioritization);
        printTagValue(b, rootParties);
        printTagValue(b, TagNum.NoSides, noSides);
        printTagValue(b, sideCrossOrdModGroups);
        printTagValue(b, instrument);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.HandlInst, handlInst);
        printTagValue(b, TagNum.ExecInst, execInst);
        printTagValue(b, TagNum.MinQty, minQty);
        printTagValue(b, TagNum.MatchIncrement, matchIncrement);
        printTagValue(b, TagNum.MaxPriceLevels, maxPriceLevels);
        printTagValue(b, displayInstruction);
        printTagValue(b, TagNum.MaxFloor, maxFloor);
        printTagValue(b, TagNum.ExDestination, exDestination);
        printTagValue(b, TagNum.ExDestinationIDSource, exDestinationIDSource);
        printTagValue(b, TagNum.NoTradingSessions, noTradingSessions);
        printTagValue(b, tradingSessionGroups);
        printTagValue(b, TagNum.ProcessCode, processCode);
        printTagValue(b, TagNum.PrevClosePx, prevClosePx);
        printTagValue(b, TagNum.LocateReqd, locateReqd);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printUTCDateTimeTagValue(b, TagNum.TransBkdTime, transBkdTime);
        printTagValue(b, stipulations);
        printTagValue(b, TagNum.OrdType, ordType);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.PriceProtectionScope, priceProtectionScope);
        printTagValue(b, TagNum.StopPx, stopPx);
        printTagValue(b, triggeringInstruction);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, yieldData);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.ComplianceID, complianceID);
        printTagValue(b, TagNum.IOIID, IOIID);
        printTagValue(b, TagNum.QuoteID, quoteID);
        printTagValue(b, TagNum.TimeInForce, timeInForce);
        printUTCDateTimeTagValue(b, TagNum.EffectiveTime, effectiveTime);
        printDateTagValue(b, TagNum.ExpireDate, expireDate);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printTagValue(b, TagNum.GTBookingInst, GTBookingInst);
        printTagValue(b, TagNum.MaxShow, maxShow);
        printTagValue(b, pegInstructions);
        printTagValue(b, discretionInstructions);
        printTagValue(b, TagNum.TargetStrategy, targetStrategy);
        printTagValue(b, TagNum.NoStrategyParameters, noStrategyParameters);
        printTagValue(b, strategyParametersGroups);
        printTagValue(b, TagNum.TargetStrategyParameters, targetStrategyParameters);
        printTagValue(b, TagNum.ParticipationRate, participationRate);
        printTagValue(b, TagNum.PegOffsetValue, pegOffsetValue);
        printTagValue(b, TagNum.DiscretionInst, discretionInst);
        printTagValue(b, TagNum.DiscretionOffsetValue, discretionOffsetValue);
        printTagValue(b, TagNum.CancellationRights, cancellationRights);
        printTagValue(b, TagNum.MoneyLaunderingStatus, moneyLaunderingStatus);
        printTagValue(b, TagNum.RegistID, registID);
        printTagValue(b, TagNum.Designation, designation);
        printTagValue(b, TagNum.AccruedInterestRate, accruedInterestRate);
        printTagValue(b, TagNum.AccruedInterestAmt, accruedInterestAmt);
        printTagValue(b, TagNum.NetMoney, netMoney);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
