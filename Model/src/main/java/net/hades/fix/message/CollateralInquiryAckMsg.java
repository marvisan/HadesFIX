/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralInquiryAckMsg.java
 *
 * $Id: AllocationInstructionMsg.java,v 1.5 2011-10-21 10:31:03 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.CollInquiryStatus;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.ResponseTransportType;

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
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.ExecCollGroup;
import net.hades.fix.message.group.CollInqQualGroup;
import net.hades.fix.message.group.TrdCollGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.util.MsgUtil;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Used to respond to a Collateral Inquiry in the following situations:
 * <ul>
 *      <li>When the CollateralInquiry will result in an out of band response (such as a file transfer).</li>
 *      <li>When the inquiry is otherwise valid but no collateral is found to match the criteria specified on the Collateral Inquiry message.</li>
 *      <li>When the Collateral Inquiry is invalid based upon the business rules of the counterparty.</li>
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 19/12/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CollateralInquiryAckMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CollInquiryID.getValue(),
        TagNum.CollInquiryStatus.getValue(),
        TagNum.CollInquiryResult.getValue(),
        TagNum.NoCollInquiryQualifier.getValue(),
        TagNum.TotNumReports.getValue(),
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
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.SettlSessID.getValue(),
        TagNum.SettlSessSubID.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.ResponseTransportType.getValue(),
        TagNum.ResponseDestination.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.CollInquiryID.getValue(),
        TagNum.CollInquiryStatus.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 909 REQUIRED. Starting with 4.4 version.
     */
    protected String collInquiryID;

    /**
     * TagNum = 945 REQUIRED. Starting with 4.4 version.
     */
    protected CollInquiryStatus collInquiryStatus;

    /**
     * TagNum = 946. Starting with 4.4 version.
     */
    protected Integer collInquiryResult;

    /**
     * TagNum = 938. Starting with 4.4 version.
     */
    protected Integer noCollInquiryQualifier;

    /**
     * Starting with 4.4 version.
     */
    protected CollInqQualGroup[] collInqQualGroups;

    /**
     * TagNum = 911. Starting with 4.4 version.
     */
    protected Integer totNumReports;
 
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
     * TagNum = 725. Starting with 4.4 version.
     */
    protected ResponseTransportType responseTransportType;
           
    /**
     * TagNum = 726. Starting with 4.4 version.
     */
    protected String responseDestination;

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

    public CollateralInquiryAckMsg() {
        super();
    }

    public CollateralInquiryAckMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public CollateralInquiryAckMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.CollateralInquiryAck.getValue(), beginString);
    }

    public CollateralInquiryAckMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.CollateralInquiryAck.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.CollInquiryID, required=true)
    public String getCollInquiryID() {
        return collInquiryID;
    }

    /**
     * Message field setter.
     * @param collInquiryID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollInquiryID, required=true)
    public void setCollInquiryID(String collInquiryID) {
        this.collInquiryID = collInquiryID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollInquiryStatus, required=true)
    public CollInquiryStatus getCollInquiryStatus() {
        return collInquiryStatus;
    }

    /**
     * Message field setter.
     * @param collInquiryStatus field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollInquiryStatus, required=true)
    public void setCollInquiryStatus(CollInquiryStatus collInquiryStatus) {
        this.collInquiryStatus = collInquiryStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollInquiryResult)
    public Integer getCollInquiryResult() {
        return collInquiryResult;
    }

    /**
     * Message field setter.
     * @param collInquiryResult field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.CollInquiryResult)
    public void setCollInquiryResult(Integer collInquiryResult) {
        this.collInquiryResult = collInquiryResult;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoCollInquiryQualifier)
    public Integer getNoCollInquiryQualifier() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link CollInqQualGroup} groups. It will also create an array
     * of {@link CollInqQualGroup} objects and set the <code>collInqQualGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>collInqQualGroups</code> array they will be discarded.<br/>
     * @param noCollInquiryQualifier field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoCollInquiryQualifier)
    public void setNoCollInquiryQualifier(Integer noCollInquiryQualifier) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link CollInqQualGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public CollInqQualGroup[] getCollInqQualGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link CollInqQualGroup} object to the existing array of <code>collInqQualGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noCollInquiryQualifier</code> field to the proper value.<br/>
     * Note: If the <code>setNoCollInquiryQualifier</code> method has been called there will already be a number of objects in the
     * <code>collInqQualGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public CollInqQualGroup addCollInqQualGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link CollInqQualGroup} object from the existing array of <code>collInqQualGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noCollInquiryQualifier</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public CollInqQualGroup deleteCollInqQualGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link CollInqQualGroup} objects from the <code>collInqQualGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noCollInquiryQualifier</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearCollInqQualGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ClearingBusinessDate)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseTransportType)
    public ResponseTransportType getResponseTransportType() {
        return responseTransportType;
    }

    /**
     * Message field setter.
     * @param responseTransportType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseTransportType)
    public void setResponseTransportType(ResponseTransportType responseTransportType) {
        this.responseTransportType = responseTransportType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseDestination)
    public String getResponseDestination() {
        return responseDestination;
    }

    /**
     * Message field setter.
     * @param responseDestination field value
     */ 
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ResponseDestination)
    public void setResponseDestination(String responseDestination) {
        this.responseDestination = responseDestination;
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
        if (collInquiryID == null || collInquiryID.trim().isEmpty()) {
            errorMsg.append(" [CollInquiryID]");
            hasMissingTag = true;
        }
        if (collInquiryStatus == null) {
            errorMsg.append(" [CollInquiryStatus]");
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
            TagEncoder.encode(bao, TagNum.CollInquiryID, collInquiryID);
            if (collInquiryStatus != null) {
                TagEncoder.encode(bao, TagNum.CollInquiryStatus, collInquiryStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.CollInquiryResult, collInquiryResult);
            if (noCollInquiryQualifier != null) {
                TagEncoder.encode(bao, TagNum.NoCollInquiryQualifier, noCollInquiryQualifier);
                if (collInqQualGroups != null && collInqQualGroups.length == noCollInquiryQualifier.intValue()) {
                    for (int i = 0; i < noCollInquiryQualifier.intValue(); i++) {
                        if (collInqQualGroups[i] != null) {
                            bao.write(collInqQualGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "CollInqQualGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoCollInquiryQualifier.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.TotNumReports, totNumReports);
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
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encode(bao, TagNum.SettlSessID, settlSessID);
            TagEncoder.encode(bao, TagNum.SettlSessSubID, settlSessSubID);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            if (responseTransportType != null) {
                TagEncoder.encode(bao, TagNum.ResponseTransportType, responseTransportType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ResponseDestination, responseDestination);
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
            case CollInquiryID:
                collInquiryID = new String(tag.value, sessionCharset);
                break;
                
            case CollInquiryStatus:
                collInquiryStatus = CollInquiryStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case CollInquiryResult:
                collInquiryResult = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoCollInquiryQualifier:
                noCollInquiryQualifier = new Integer(new String(tag.value, sessionCharset));
                break;

            case TotNumReports:
                totNumReports = new Integer(new String(tag.value, sessionCharset));
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

            case ResponseTransportType:
                responseTransportType = ResponseTransportType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;
                        
            case ResponseDestination:
                responseDestination = new String(tag.value, sessionCharset);
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [CollateralInquiryAckMsg] fields.";
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
        StringBuilder b = new StringBuilder("{CollateralInquiryAckMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.CollInquiryID, collInquiryID);
        printTagValue(b, TagNum.CollInquiryStatus, collInquiryStatus);
        printTagValue(b, TagNum.CollInquiryResult, collInquiryResult);
        printTagValue(b, TagNum.NoCollInquiryQualifier, noCollInquiryQualifier);
        printTagValue(b, collInqQualGroups);
        printTagValue(b, TagNum.TotNumReports, totNumReports);
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
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.SettlSessID, settlSessID);
        printTagValue(b, TagNum.SettlSessSubID, settlSessSubID);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.ResponseTransportType, responseTransportType);
        printTagValue(b, TagNum.ResponseDestination, responseDestination);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
