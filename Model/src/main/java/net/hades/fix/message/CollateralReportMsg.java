/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralReportMsg.java
 *
 * $Id: AllocationInstructionMsg.java,v 1.5 2011-10-21 10:31:03 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.TrdCollGroup;
import net.hades.fix.message.group.TrdRegTimestampsGroup;
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
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ExecCollGroup;
import net.hades.fix.message.group.MiscFeeGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CollApplType;
import net.hades.fix.message.type.CollStatus;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Used to report collateral status when responding to a Collateral Inquiry message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 19/12/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CollateralReportMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CollRptID.getValue(),
        TagNum.CollInquiryID.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.CollApplType.getValue(),
        TagNum.FinancialStatus.getValue(),
        TagNum.CollStatus.getValue(),
        TagNum.TotNumReports.getValue(),
        TagNum.LastRptRequested.getValue(),
        TagNum.Account.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.NoExecs.getValue(),
        TagNum.NoTrades.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.Quantity.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.Currency.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.MarginExcess.getValue(),
        TagNum.TotalNetValue.getValue(),
        TagNum.CashOutstanding.getValue(),
        TagNum.NoTrdRegTimestamps.getValue(),
        TagNum.Side.getValue(),
        TagNum.NoMiscFees.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.AccruedInterestAmt.getValue(),
        TagNum.EndAccruedInterestAmt.getValue(),
        TagNum.StartCash.getValue(),
        TagNum.EndCash.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.SettlSessID.getValue(),
        TagNum.SettlSessSubID.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CollRptID.getValue(),
        TagNum.CollStatus.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 908 REQUIRED. Starting with 4.4 version.
     */
    protected String collRptID;

    /**
     * TagNum = 909. Starting with 4.4 version.
     */
    protected String collInquiryID;

    /**
     * TagNum = 60. Starting with 5.0 version.
     */
    protected Date transactTime;
    
    /**
     * TagNum = 1043. Starting with 5.0 version.
     */
    protected CollApplType collApplType;
    
    /**
     * TagNum = 625. Starting with 5.0 version.
     */
    protected String financialStatus;
   
    /**
     * TagNum = 910 REQUIRED. Starting with 4.4 version.
     */
    protected CollStatus collStatus;
   
    /**
     * TagNum = 911. Starting with 4.4 version.
     */
    protected Integer totNumReports;
  
    /**
     * TagNum = 912. Starting with 4.4 version.
     */
    protected Boolean lastRptRequested;

    /**
     * Starting with 4.4 version.
     */
    protected Parties parties;
    
    /**
     * TagNum = 1. Starting with 4.4 version.
     */
    protected String account;

    /**
     * TagNum = 581. Starting with 4.4 version.
     */
    protected AccountType accountType;

    /**
     * TagNum = 11. Starting with 4.4 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 37 REQUIRED. Starting with 4.4 version.
     */
    protected String orderID;

    /**
     * TagNum = 198. Starting with 4.4 version.
     */
    protected String secondaryOrderID;

    /**
     * TagNum = 526. Starting with 4.4 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 124. Starting with 5.0SP2 version.
     */
    protected Integer noExecs;

    /**
     * Starting with 4.4 version.
     */
    protected ExecCollGroup[] execCollGroups;

    /**
     * TagNum = 897. Starting with 4.4 version.
     */
    protected Integer noTrades;

    /**
     * Starting with 4.4 version.
     */
    protected TrdCollGroup[] trdCollGroups;
  
    /**
     * Starting with 4.4 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.4 version.
     */
    protected FinancingDetails financingDetails;

    /**
     * TagNum = 64. Starting with 4.4 version.
     */
    protected Date settlDate;
    
    /** 
     * TagNum = 53. Starting with 4.4 version.
     */
    protected Double quantity;

    /**
     * TagNum = 854. Starting with 4.4 version.
     */
    protected QtyType qtyType;
 
    /**
     * TagNum = 15. Starting with 4.4 version.
     */
    protected Currency currency;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 899. Starting with 4.4 version.
     */
    protected Double marginExcess;

    /**
     * TagNum = 900. Starting with 4.4 version.
     */
    protected Double totalNetValue;

    /**
     * TagNum = 901. Starting with 4.4 version.
     */
    protected Double cashOutstanding;

    /**
     * TagNum = 768. Starting with 4.4 version.
     */
    protected Integer noTrdRegTimestamps;

    /**
     * Starting with 4.4 version.
     */
    protected TrdRegTimestampsGroup[] trdRegTimestampsGroups;

    /**
     * TagNum = 54. Starting with 4.4 version.
     */
    protected Side side;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noMiscFees;

    /**
     * Starting with 4.4 version.
     */
    protected MiscFeeGroup[] miscFeeGroups;

    /**
     * TagNum = 44. Starting with 4.4 version.
     */
    protected Double price;

    /**
     * TagNum = 423. Starting with 4.4 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 159. Starting with 4.4 version.
     */
    protected Double accruedInterestAmt;

    /**
     * TagNum = 920. Starting with 4.4 version.
     */
    protected Double endAccruedInterestAmt;

    /**
     * TagNum = 921. Starting with 4.4 version.
     */
    protected Double startCash;

    /**
     * TagNum = 922. Starting with 4.4 version.
     */
    protected Double endCash;

    /**
     * Starting with 4.4 version.
     */
    protected SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData;
  
    /**
     * Starting with 4.4 version.
     */
    protected Stipulations stipulations;

    /**
     * Starting with 4.4 version.
     */
    protected SettlInstructionsData settlInstructionsData;

    /**
     * TagNum = 336. Starting with 4.4 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.4 version.
     */
    protected String tradingSessionSubID;
  
    /**
     * TagNum = 716. Starting with 4.4 version.
     */
    protected String settlSessID;

    /**
     * TagNum = 716. Starting with 4.4 version.
     */
    protected String settlSessSubID;

    /**
     * TagNum = 715. Starting with 4.4 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 58. Starting with 4.4 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.4 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.4 version.
     */
    protected byte[] encodedText;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public CollateralReportMsg() {
        super();
    }

    public CollateralReportMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public CollateralReportMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.CollateralReport.getValue(), beginString);
    }

    public CollateralReportMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.CollateralReport.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.CollRptID, required=true)
    public String getCollRptID() {
        return collRptID;
    }

    /**
     * Message field setter.
     * @param collRptID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollRptID, required=true)
    public void setCollRptID(String collRptID) {
        this.collRptID = collRptID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollInquiryID)
    public String getCollInquiryID() {
        return collInquiryID;
    }

    /**
     * Message field setter.
     * @param collInquiryID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollInquiryID)
    public void setCollInquiryID(String collInquiryID) {
        this.collInquiryID = collInquiryID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CollApplType)
    public CollApplType getCollApplType() {
        return collApplType;
    }

    /**
     * Message field setter.
     * @param collApplType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.CollApplType)
    public void setCollApplType(CollApplType collApplType) {
        this.collApplType = collApplType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FinancialStatus)
    public String getFinancialStatus() {
        return financialStatus;
    }

    /**
     * Message field setter.
     * @param financialStatus field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FinancialStatus)
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollStatus, required=true)
    public CollStatus getCollStatus() {
        return collStatus;
    }

    /**
     * Message field setter.
     * @param collStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollStatus, required=true)
    public void setCollStatus(CollStatus collStatus) {
        this.collStatus = collStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumReports)
    public Integer getTotNumReports() {
        return totNumReports;
    }

    /**
     * Message field setter.
     * @param totNumReports field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumReports)
    public void setTotNumReports(Integer totNumReports) {
        this.totNumReports = totNumReports;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastRptRequested)
    public Boolean getLastRptRequested() {
        return lastRptRequested;
    }

    /**
     * Message field setter.
     * @param lastRptRequested field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastRptRequested)
    public void setLastRptRequested(Boolean lastRptRequested) {
        this.lastRptRequested = lastRptRequested;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Account)
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AccountType)
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Message field setter.
     * @param accountType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AccountType)
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    /**
     * Message field setter.
     * @param secondaryOrderID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    /**
     * Message field setter.
     * @param secondaryClOrdID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoExecs)
    public Integer getNoExecs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link ExecCollGroup} groups. It will also create an array
     * of {@link ExecCollGroup} objects and set the <code>execCollGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>execCollGroups</code> array they will be discarded.<br/>
     * @param noExecs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoExecs)
    public void setNoExecs(Integer noExecs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link ExecCollGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public ExecCollGroup[] getExecCollGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link ExecCollGroup} object to the existing array of <code>execCollGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noExecs</code> field to the proper value.<br/>
     * Note: If the <code>setNoExecs</code> method has been called there will already be a number of objects in the
     * <code>execCollGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public ExecCollGroup addExecCollGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link ExecCollGroup} object from the existing array of <code>execCollGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noExecs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public ExecCollGroup deleteExecCollGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link ExecCollGroup} objects from the <code>execCollGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noExecs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearExecCollGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoTrades)
    public Integer getNoTrades() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TrdCollGroup} groups. It will also create an array
     * of {@link TrdCollGroup} objects and set the <code>trdCollGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdCollGroups</code> array they will be discarded.<br/>
     * @param noTrades field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoTrades)
    public void setNoTrades(Integer noTrades) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdCollGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public TrdCollGroup[] getTrdCollGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdCollGroup} object to the existing array of <code>TrdCollGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTrades</code> field to the proper value.<br/>
     * Note: If the <code>setNoTrades</code> method has been called there will already be a number of objects in the
     * <code>TrdCollGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public TrdCollGroup addTrdCollGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdCollGroup} object from the existing array of <code>TrdCollGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTrades</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public TrdCollGroup deleteTrdCollGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdCollGroup} objects from the <code>TrdCollGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTrades</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearTrdCollGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     */
    @FIXVersion(introduced="4.4")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the instrument component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public FinancingDetails getFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the FinancingDetails component class to the proper implementation.
     */
    @FIXVersion(introduced = "4.4")
    public void setFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the FinancingDetails component to null.
     */
    @FIXVersion(introduced = "4.4")
    public void clearFinancingDetails() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Quantity)
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Message field setter.
     * @param quantity field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Quantity)
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QtyType)
    public QtyType getQtyType() {
        return qtyType;
    }

    /**
     * Message field setter.
     * @param qtyType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.QtyType)
    public void setQtyType(QtyType qtyType) {
        this.qtyType = qtyType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
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
     * This method sets the number of {@link InstrumentLeg} groups. It will also create an array
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
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
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
    @TagNumRef(tagNum=TagNum.MarginExcess)
    public Double getMarginExcess() {
        return marginExcess;
    }

    /**
     * Message field setter.
     * @param marginExcess field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MarginExcess)
    public void setMarginExcess(Double marginExcess) {
        this.marginExcess = marginExcess;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotalNetValue)
    public Double getTotalNetValue() {
        return totalNetValue;
    }

    /**
     * Message field setter.
     * @param totalNetValue field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotalNetValue)
    public void setTotalNetValue(Double totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CashOutstanding)
    public Double getCashOutstanding() {
        return cashOutstanding;
    }

    /**
     * Message field setter.
     * @param cashOutstanding field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CashOutstanding)
    public void setCashOutstanding(Double cashOutstanding) {
        this.cashOutstanding = cashOutstanding;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoTrdRegTimestamps)
    public Integer getNoTrdRegTimestamps() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TrdRegTimestampsGroup} components. It will also create an array
     * of {@link TrdRegTimestampsGroup} objects and set the <code>trdRegTimestampsGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>trdRegTimestampsGroups</code> array they will be discarded.<br/>
     * @param noTrdRegTimestamps number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoTrdRegTimestamps)
    public void setNoTrdRegTimestamps(Integer noTrdRegTimestamps) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TrdRegTimestampsGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public TrdRegTimestampsGroup[] getTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TrdRegTimestampsGroup} object to the existing array of <code>trdRegTimestampsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field to the proper value.<br/>
     * Note: If the <code>setNoTrdRegTimestamps</code> method has been called there will already be a number of objects in the
     * <code>trdRegTimestampsGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public TrdRegTimestampsGroup addTrdRegTimestampsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TrdRegTimestampsGroup} object from the existing array of <code>trdRegTimestampsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public TrdRegTimestampsGroup deleteTrdRegTimestampsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TrdRegTimestampsGroup} objects from the <code>trdRegTimestampsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTrdRegTimestamps</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearTrdRegTimestampsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoMiscFees)
    public Integer getNoMiscFees() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MiscFeeGroup} groups. It will also create an array
     * of {@link MiscFeeGroup} objects and set the <code>miscFeeGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>miscFeeGroups</code> array they will be discarded.<br/>
     * @param noMiscFees field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoMiscFees)
    public void setNoMiscFees(Integer noMiscFees) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MiscFeeGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public MiscFeeGroup[] getMiscFeeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MiscFeeGroup} object to the existing array of <code>miscFeeGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMiscFees</code> field to the proper value.<br/>
     * Note: If the <code>setNoMiscFees</code> method has been called there will already be a number of objects in the
     * <code>MiscFeeGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public MiscFeeGroup addMiscFeeGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MiscFeeGroup} object from the existing array of <code>miscFeeGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMiscFees</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public MiscFeeGroup deleteMiscFeeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MiscFeeGroup} objects from the <code>miscFeeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMiscFees</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearMiscFeeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Price)
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public Double getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param accruedInterestAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AccruedInterestAmt)
    public void setAccruedInterestAmt(Double accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndAccruedInterestAmt)
    public Double getEndAccruedInterestAmt() {
        return endAccruedInterestAmt;
    }

    /**
     * Message field setter.
     * @param endAccruedInterestAmt field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndAccruedInterestAmt)
    public void setEndAccruedInterestAmt(Double endAccruedInterestAmt) {
        this.endAccruedInterestAmt = endAccruedInterestAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StartCash)
    public Double getStartCash() {
        return startCash;
    }

    /**
     * Message field setter.
     * @param startCash field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StartCash)
    public void setStartCash(Double startCash) {
        this.startCash = startCash;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndCash)
    public Double getEndCash() {
        return endCash;
    }

    /**
     * Message field setter.
     * @param endCash field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EndCash)
    public void setEndCash(Double endCash) {
        this.endCash = endCash;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SpreadOrBenchmarkCurveData component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearSpreadOrBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Stipulations getStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Stipulations component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public SettlInstructionsData getSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SettlInstructionsData component class to the proper implementation.
     */
    @FIXVersion(introduced = "4.4")
    public void setSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SettlInstructionsData component to null.
     */
    @FIXVersion(introduced = "4.4")
    public void clearSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlSessID)
    public String getSettlSessID() {
        return settlSessID;
    }

    /**
     * Message field setter.
     * @param settlSessID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlSessID)
    public void setSettlSessID(String settlSessID) {
        this.settlSessID = settlSessID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlSessSubID)
    public String getSettlSessSubID() {
        return settlSessSubID;
    }

    /**
     * Message field setter.
     * @param settlSessSubID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlSessSubID)
    public void setSettlSessSubID(String settlSessSubID) {
        this.settlSessSubID = settlSessSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate, required=true)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate, required=true)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (collRptID == null || collRptID.trim().isEmpty()) {
            errorMsg.append(" [CollRptID]");
            hasMissingTag = true;
        }
        if (collStatus == null) {
            errorMsg.append(" [CollStatus]");
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
            TagEncoder.encode(bao, TagNum.CollRptID, collRptID);
            TagEncoder.encode(bao, TagNum.CollInquiryID, collInquiryID);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (collApplType != null) {
                TagEncoder.encode(bao, TagNum.CollApplType, collApplType.getValue());
            }
            TagEncoder.encode(bao, TagNum.FinancialStatus, financialStatus);
            if (collStatus != null) {
                TagEncoder.encode(bao, TagNum.CollStatus, collStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.TotNumReports, totNumReports);
            TagEncoder.encode(bao, TagNum.LastRptRequested, lastRptRequested);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            if (noExecs != null && noExecs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoExecs, noExecs);
                if (execCollGroups != null && execCollGroups.length == noExecs.intValue()) {
                    for (ExecCollGroup rateSource : execCollGroups) {
                        bao.write(rateSource.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "ExecCollGroup field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoExecs.getValue(), error);
                }
            }
            if (noTrades != null) {
                TagEncoder.encode(bao, TagNum.NoTrades, noTrades);
                if (trdCollGroups != null && trdCollGroups.length == noTrades.intValue()) {
                    for (int i = 0; i < noTrades.intValue(); i++) {
                        if (trdCollGroups[i] != null) {
                            bao.write(trdCollGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdCollGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTrades.getValue(), error);
                }
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (financingDetails != null) {
                bao.write(financingDetails.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
            TagEncoder.encode(bao, TagNum.Quantity, quantity);
            if (qtyType != null) {
                TagEncoder.encode(bao, TagNum.QtyType, qtyType.getValue());
            }
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (InstrumentLeg instrumentLeg : instrumentLegs) {
                        bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error = "InstrumentLegs field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                        TagNum.NoLegs.getValue(), error);
                }
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
            TagEncoder.encode(bao, TagNum.MarginExcess, marginExcess);
            TagEncoder.encode(bao, TagNum.TotalNetValue, totalNetValue);
            TagEncoder.encode(bao, TagNum.CashOutstanding, cashOutstanding);
            if (noTrdRegTimestamps != null) {
                TagEncoder.encode(bao, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
                if (trdRegTimestampsGroups != null && trdRegTimestampsGroups.length == noTrdRegTimestamps.intValue()) {
                    for (int i = 0; i < noTrdRegTimestamps.intValue(); i++) {
                        if (trdRegTimestampsGroups[i] != null) {
                            bao.write(trdRegTimestampsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TrdRegTimestampsGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoTrdRegTimestamps.getValue(), error);
                }
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (noMiscFees != null) {
                TagEncoder.encode(bao, TagNum.NoMiscFees, noMiscFees);
                if (miscFeeGroups != null && miscFeeGroups.length == noMiscFees.intValue()) {
                    for (int i = 0; i < noMiscFees.intValue(); i++) {
                        if (miscFeeGroups[i] != null) {
                            bao.write(miscFeeGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MiscFeeGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMiscFees.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Price, price);
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.AccruedInterestAmt, accruedInterestAmt);
            TagEncoder.encode(bao, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
            TagEncoder.encode(bao, TagNum.StartCash, startCash);
            TagEncoder.encode(bao, TagNum.EndCash, endCash);
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (stipulations != null) {
                bao.write(stipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            if (settlInstructionsData != null) {
                bao.write(settlInstructionsData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encode(bao, TagNum.SettlSessID, settlSessID);
            TagEncoder.encode(bao, TagNum.SettlSessSubID, settlSessSubID);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
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
            case CollRptID:
                collRptID = new String(tag.value, sessionCharset);
                break;

            case CollInquiryID:
                collInquiryID = new String(tag.value, sessionCharset);
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case CollApplType:
                collApplType = CollApplType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case FinancialStatus:
                financialStatus = new String(tag.value, sessionCharset);
                break;

            case CollStatus:
                collStatus = CollStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TotNumReports:
                totNumReports = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastRptRequested:
                lastRptRequested = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case AccountType:
                accountType = AccountType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

           case SecondaryOrderID:
                secondaryOrderID = new String(tag.value, sessionCharset);
                break;

           case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;

            case NoExecs:
                noExecs = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoTrades:
                noTrades = new Integer(new String(tag.value, sessionCharset));
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Quantity:
                quantity = new Double(new String(tag.value, sessionCharset));
                break;

            case QtyType:
                qtyType = QtyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case MarginExcess:
                marginExcess = new Double(new String(tag.value, sessionCharset));
                break;

            case TotalNetValue:
                totalNetValue = new Double(new String(tag.value, sessionCharset));
                break;

            case CashOutstanding:
                cashOutstanding = new Double(new String(tag.value, sessionCharset));
                break;

            case NoTrdRegTimestamps:
                noTrdRegTimestamps = new Integer(new String(tag.value, sessionCharset));
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case NoMiscFees:
                noMiscFees = new Integer(new String(tag.value, sessionCharset));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AccruedInterestAmt:
                accruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case EndAccruedInterestAmt:
                endAccruedInterestAmt = new Double(new String(tag.value, sessionCharset));
                break;

            case StartCash:
                startCash = new Double(new String(tag.value, sessionCharset));
                break;

            case EndCash:
                endCash = new Double(new String(tag.value, sessionCharset));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

           case SettlSessID:
                settlSessID = new String(tag.value, sessionCharset);
                break;

           case SettlSessSubID:
                settlSessSubID = new String(tag.value, sessionCharset);
                break;
                                     
            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [CollateralReportMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
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
        StringBuilder b = new StringBuilder("{CollateralReportMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.CollRptID, collRptID);
        printTagValue(b, TagNum.CollInquiryID, collInquiryID);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.CollApplType, collApplType);
        printTagValue(b, TagNum.FinancialStatus, financialStatus);
        printTagValue(b, TagNum.CollStatus, collStatus);
        printTagValue(b, TagNum.TotNumReports, totNumReports);
        printTagValue(b, TagNum.LastRptRequested, lastRptRequested);
        printTagValue(b, parties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.SecondaryOrderID, secondaryOrderID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.NoExecs, noExecs);
        printTagValue(b, execCollGroups);
        printTagValue(b, TagNum.NoTrades, noTrades);
        printTagValue(b, trdCollGroups);
        printTagValue(b, instrument);
        printTagValue(b, financingDetails);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.Quantity, quantity);
        printTagValue(b, TagNum.QtyType, qtyType);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.MarginExcess, marginExcess);
        printTagValue(b, TagNum.TotalNetValue, totalNetValue);
        printTagValue(b, TagNum.CashOutstanding, cashOutstanding);
        printTagValue(b, TagNum.NoTrdRegTimestamps, noTrdRegTimestamps);
        printTagValue(b, trdRegTimestampsGroups);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.NoMiscFees, noMiscFees);
        printTagValue(b, miscFeeGroups);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.AccruedInterestAmt, accruedInterestAmt);
        printTagValue(b, TagNum.EndAccruedInterestAmt, endAccruedInterestAmt);
        printTagValue(b, TagNum.StartCash, startCash);
        printTagValue(b, TagNum.EndCash, endCash);
        printTagValue(b, spreadOrBenchmarkCurveData);
        printTagValue(b, stipulations);
        printTagValue(b, settlInstructionsData);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.SettlSessID, settlSessID);
        printTagValue(b, TagNum.SettlSessSubID, settlSessSubID);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
