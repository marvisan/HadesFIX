/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AssignmentReportMsg.java
 *
 * $Id: AllocationInstructionMsg.java,v 1.5 2011-10-21 10:31:03 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
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
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PosAmtGroup;
import net.hades.fix.message.group.PositionQtyGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.AssignmentMethod;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExerciseMethod;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlPriceType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Assignment Reports are sent from a clearing house to counterparties, such as a clearing firm as a result of the
 * assignment process. Communication Scenarios are:
 * <ul>
 *      <li>Assignment Report can be sent unsolicited from the clearing house to a clearing firm.</li>
 *      <li>Assignment Report can be returned in response to a Request for Positions message with a PosReqType(tag 724) set to 3 (Assignment).</li>
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 14/12/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AssignmentReportMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AsgnRptID.getValue(),
        TagNum.PosReqID.getValue(),
        TagNum.TotNumAssignmentReports.getValue(),
        TagNum.LastRptRequested.getValue(),
        TagNum.Account.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.Currency.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.NoPositions.getValue(),
        TagNum.NoPosAmt.getValue(),
        TagNum.ThresholdAmount.getValue(),
        TagNum.SettlPrice.getValue(),
        TagNum.SettlPriceType.getValue(),
        TagNum.UnderlyingSettlPrice.getValue(),
        TagNum.PriorSettlPrice.getValue(),
        TagNum.ExpireDate.getValue(),
        TagNum.AssignmentMethod.getValue(),
        TagNum.AssignmentUnit.getValue(),
        TagNum.OpenInterest.getValue(),
        TagNum.ExerciseMethod.getValue(),
        TagNum.SettlSessID.getValue(),
        TagNum.SettlSessSubID.getValue(),
        TagNum.ClearingBusinessDate.getValue(),
        TagNum.Text.getValue(),
        
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AsgnRptID.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.NoPositions.getValue(),
        TagNum.SettlPrice.getValue(),
        TagNum.SettlPriceType.getValue(),
        TagNum.UnderlyingSettlPrice.getValue(),
        TagNum.OpenInterest.getValue(),
        TagNum.ExerciseMethod.getValue(),
        TagNum.SettlSessID.getValue(),
        TagNum.SettlSessSubID.getValue(),
        TagNum.ClearingBusinessDate.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 5.0SP1 version.
     */
    protected ApplicationSequenceControl applicationSequenceControl;

    /**
     * TagNum = 833 REQUIRED. Starting with 4.4 version.
     */
    protected String asgnRptID;

    /**
     * TagNum = 710. Starting with 5.0SP2 version.
     */
    protected String posReqID;
          
    /**
     * TagNum = 832. Starting with 4.4 version.
     */
    protected Integer totNumAssignmentReports;
         
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
     * TagNum = 714. Starting with 5.0 version.
     */
    protected String posMaintRptRefID;
    
    /**
     * Starting with 4.4 version.
     */
    protected Instrument instrument;

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
     * TagNum = 702. Starting with 4.4 version.
     */
    protected Integer noPositions;

    /**
     * Starting with 4.4 version.
     */
    protected PositionQtyGroup[] positionQtyGroups;

    /**
     * TagNum = 753. Starting with 4.4 version.
     */
    protected Integer noPosAmt;

    /**
     * Starting with 4.4 version.
     */
    protected PosAmtGroup[] posAmtGroups;

    /**
     * TagNum = 834. Starting with 4.4 version.
     */
    protected Double thresholdAmount;

    /**
     * TagNum = 730. Starting with 4.4 version.
     */
    protected Double settlPrice;

    /**
     * TagNum = 731. Starting with 4.4 version.
     */
    protected SettlPriceType settlPriceType;

    /**
     * TagNum = 734. Starting with 4.4 version.
     */
    protected Double underlyingSettlPrice;

    /**
     * TagNum = 734. Starting with 5.0 version.
     */
    protected Double priorSettlPrice;

    /**
     * TagNum = 432. Starting with 4.4 version.
     */
    protected Date expireDate;

    /**
     * TagNum = 744. Starting with 4.4 version.
     */
    protected AssignmentMethod assignmentMethod;

    /**
     * TagNum = 745. Starting with 4.4 version.
     */
    protected Double assignmentUnit;
    
    /**
     * TagNum = 746. Starting with 4.4 version.
     */
    protected Double openInterest;
   
    /**
     * TagNum = 747. Starting with 4.4 version.
     */
    protected ExerciseMethod exerciseMethod;

    /**
     * TagNum = 716. Starting with 4.4 version.
     */
    protected String settlSessID;

    /**
     * TagNum = 716. Starting with 4.4 version.
     */
    protected String settlSessSubID;
   
    /**
     * TagNum = 715 REQUIRED. Starting with 4.4 version.
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

    public AssignmentReportMsg() {
        super();
    }

    public AssignmentReportMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public AssignmentReportMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.AssignmentReport.getValue(), beginString);
    }

    public AssignmentReportMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.AssignmentReport.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="5.0SP1")
    public ApplicationSequenceControl getApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ApplicationSequenceControl component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Clear the ApplicationSequenceControl component.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AsgnRptID, required=true)
    public String getAsgnRptID() {
        return asgnRptID;
    }

    /**
     * Message field setter.
     * @param asgnRptID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AsgnRptID, required=true)
    public void setAsgnRptID(String asgnRptID) {
        this.asgnRptID = asgnRptID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.PosReqID)
    public String getPosReqID() {
        return posReqID;
    }

    /**
     * Message field setter.
     * @param posReqID field value
     */
    @FIXVersion(introduced="5.0SP2")
    @TagNumRef(tagNum=TagNum.PosReqID)
    public void setPosReqID(String posReqID) {
        this.posReqID = posReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumAssignmentReports)
    public Integer getTotNumAssignmentReports() {
        return totNumAssignmentReports;
    }

    /**
     * Message field setter.
     * @param totNumAssignmentReports field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TotNumAssignmentReports)
    public void setTotNumAssignmentReports(Integer totNumAssignmentReports) {
        this.totNumAssignmentReports = totNumAssignmentReports;
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
    @TagNumRef(tagNum=TagNum.NoPositions, required=true)
    public Integer getNoPositions() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link PositionQtyGroup} components. It will also create an array
     * of {@link PositionQtyGroup} objects and set the <code>positionQtyGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>positionQtyGroups</code> array they will be discarded.<br/>
     * @param noPositions number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoPositions, required=true)
    public void setNoPositions(Integer noPositions) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PositionQtyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public PositionQtyGroup[] getPositionQtyGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link PositionQtyGroup} object to the existing array of <code>positionQtyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noPositions</code> field to the proper value.<br/>
     * Note: If the <code>setNoTrdRegTimestamps</code> method has been called there will already be a number of objects in the
     * <code>positionQtyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public PositionQtyGroup addPositionQtyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link PositionQtyGroup} object from the existing array of <code>positionQtyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noPositions</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public PositionQtyGroup deletePositionQtyGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PositionQtyGroup} objects from the <code>positionQtyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noPositions</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearPositionQtyGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoPosAmt)
    public Integer getNoPosAmt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link PosAmtGroup} groups. It will also create an array
     * of {@link PosAmtGroup} objects and set the <code>posAmtGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>posAmtGroups</code> array they will be discarded.<br/>
     * @param noPosAmt field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoPosAmt)
    public void setNoPosAmt(Integer noPosAmt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PosAmtGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0")
    public PosAmtGroup[] getPosAmtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link PosAmtGroup} object to the existing array of <code>posAmtGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noPosAmt</code> field to the proper value.<br/>
     * Note: If the <code>setNoPosAmt</code> method has been called there will already be a number of objects in the
     * <code>posAmtGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public PosAmtGroup addPosAmtGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link PosAmtGroup} object from the existing array of <code>posAmtGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noPosAmt</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public PosAmtGroup deletePosAmtGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PosAmtGroup} objects from the <code>posAmtGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noPosAmt</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearPosAmtGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ThresholdAmount)
    public Double getThresholdAmount() {
        return thresholdAmount;
    }

    /**
     * Message field setter.
     * @param thresholdAmount field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ThresholdAmount)
    public void setThresholdAmount(Double thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlPrice)
    public Double getSettlPrice() {
        return settlPrice;
    }

    /**
     * Message field setter.
     * @param settlPrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlPrice)
    public void setSettlPrice(Double settlPrice) {
        this.settlPrice = settlPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlPriceType)
    public SettlPriceType getSettlPriceType() {
        return settlPriceType;
    }

    /**
     * Message field setter.
     * @param settlPriceType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlPrice)
    public void setSettlPriceType(SettlPriceType settlPriceType) {
        this.settlPriceType = settlPriceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlPrice)
    public Double getUnderlyingSettlPrice() {
        return underlyingSettlPrice;
    }

    /**
     * Message field setter.
     * @param underlyingSettlPrice field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.UnderlyingSettlPrice)
    public void setUnderlyingSettlPrice(Double underlyingSettlPrice) {
        this.underlyingSettlPrice = underlyingSettlPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PriorSettlPrice)
    public Double getPriorSettlPrice() {
        return priorSettlPrice;
    }

    /**
     * Message field setter.
     * @param priorSettlPrice field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlPrice)
    public void setPriorSettlPrice(Double priorSettlPrice) {
        this.priorSettlPrice = priorSettlPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Message field setter.
     * @param expireDate field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExpireDate)
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AssignmentMethod)
    public AssignmentMethod getAssignmentMethod() {
        return assignmentMethod;
    }

    /**
     * Message field setter.
     * @param asssignmentMethod field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AssignmentMethod)
    public void setAssignmentMethod(AssignmentMethod asssignmentMethod) {
        this.assignmentMethod = asssignmentMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AssignmentUnit)
    public Double getAssignmentUnit() {
        return assignmentUnit;
    }

    /**
     * Message field setter.
     * @param assignmentUnit field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AssignmentUnit)
    public void setAssignmentUnit(Double assignmentUnit) {
        this.assignmentUnit = assignmentUnit;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OpenInterest)
    public Double getOpenInterest() {
        return openInterest;
    }

    /**
     * Message field setter.
     * @param openInterest field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.OpenInterest)
    public void setOpenInterest(Double openInterest) {
        this.openInterest = openInterest;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExerciseMethod)
    public ExerciseMethod getExerciseMethod() {
        return exerciseMethod;
    }

    /**
     * Message field setter.
     * @param exerciseMethod field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExerciseMethod)
    public void setExerciseMethod(ExerciseMethod exerciseMethod) {
        this.exerciseMethod = exerciseMethod;
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
        if (asgnRptID == null || asgnRptID.trim().isEmpty()) {
            errorMsg.append(" [AsgnRptID]");
            hasMissingTag = true;
        }
        if (parties == null || parties.getNoPartyIDs() == null) {
            errorMsg.append(" [Parties]");
            hasMissingTag = true;
        }
        if (clearingBusinessDate == null) {
            errorMsg.append(" [ClearingBusinessDate]");
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
            if (applicationSequenceControl != null) {
                bao.write(applicationSequenceControl.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.AsgnRptID, asgnRptID);
            TagEncoder.encode(bao, TagNum.PosReqID, posReqID);
            TagEncoder.encode(bao, TagNum.TotNumAssignmentReports, totNumAssignmentReports);
            TagEncoder.encode(bao, TagNum.LastRptRequested, lastRptRequested);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
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
            if (noPositions != null) {
                TagEncoder.encode(bao, TagNum.NoPositions, noPositions);
                if (positionQtyGroups != null && positionQtyGroups.length == noPositions.intValue()) {
                    for (int i = 0; i < noPositions.intValue(); i++) {
                        if (positionQtyGroups[i] != null) {
                            bao.write(positionQtyGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "PositionQtyGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoPositions.getValue(), error);
                }
            }
            if (noPosAmt != null) {
                TagEncoder.encode(bao, TagNum.NoPosAmt, noPosAmt);
                if (posAmtGroups != null && posAmtGroups.length == noPosAmt.intValue()) {
                    for (int i = 0; i < noPosAmt.intValue(); i++) {
                        if (posAmtGroups[i] != null) {
                            bao.write(posAmtGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "PosAmtGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoPosAmt.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.ThresholdAmount, thresholdAmount);
            TagEncoder.encode(bao, TagNum.SettlPrice, settlPrice);
            if (settlPriceType != null) {
                TagEncoder.encode(bao, TagNum.SettlPriceType, settlPriceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.UnderlyingSettlPrice, underlyingSettlPrice);
            TagEncoder.encode(bao, TagNum.PriorSettlPrice, priorSettlPrice);
            TagEncoder.encodeDate(bao, TagNum.ExpireDate, expireDate);
            if (assignmentMethod != null) {
                TagEncoder.encode(bao, TagNum.AssignmentMethod, assignmentMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.AssignmentUnit, assignmentUnit);
            TagEncoder.encode(bao, TagNum.OpenInterest, openInterest);
            if (exerciseMethod != null) {
                TagEncoder.encode(bao, TagNum.ExerciseMethod, exerciseMethod.getValue());
            }
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
            case AsgnRptID:
                asgnRptID = new String(tag.value, sessionCharset);
                break;

            case PosReqID:
                posReqID = new String(tag.value, sessionCharset);
                break;

            case TotNumAssignmentReports:
                totNumAssignmentReports = new Integer(new String(tag.value, sessionCharset));
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

            case PosMaintRptRefID:
                posMaintRptRefID = new String(tag.value, sessionCharset);
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

            case NoPositions:
                noPositions = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoPosAmt:
                noPosAmt = new Integer(new String(tag.value, sessionCharset));
                break;

            case ThresholdAmount:
                thresholdAmount = new Double(new String(tag.value, sessionCharset));
                break;

            case SettlPrice:
                settlPrice = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case SettlPriceType:
                settlPriceType = SettlPriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case UnderlyingSettlPrice:
                underlyingSettlPrice = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case PriorSettlPrice:
                priorSettlPrice = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case ExpireDate:
                expireDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AssignmentMethod:
                assignmentMethod = AssignmentMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case AssignmentUnit:
                assignmentUnit = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case OpenInterest:
                openInterest = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case ExerciseMethod:
                exerciseMethod = ExerciseMethod.valueFor(new String(tag.value, sessionCharset).charAt(0));
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
                String error = "Tag value [" + tag.tagNum + "] not present in [AssignmentReportMsg] fields.";
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
        StringBuilder b = new StringBuilder("{AssignmentReportMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.AsgnRptID, asgnRptID);
        printTagValue(b, TagNum.PosReqID, posReqID);
        printTagValue(b, TagNum.TotNumAssignmentReports, totNumAssignmentReports);
        printTagValue(b, TagNum.LastRptRequested, lastRptRequested);
        printTagValue(b, parties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, instrument);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.NoPositions, noPositions);
        printTagValue(b, positionQtyGroups);
        printTagValue(b, TagNum.NoPosAmt, noPosAmt);
        printTagValue(b, posAmtGroups);
        printTagValue(b, TagNum.ThresholdAmount, thresholdAmount);
        printTagValue(b, TagNum.SettlPrice, settlPrice);
        printTagValue(b, TagNum.SettlPriceType, settlPriceType);
        printTagValue(b, TagNum.UnderlyingSettlPrice, underlyingSettlPrice);
        printTagValue(b, TagNum.PriorSettlPrice, priorSettlPrice);
        printDateTagValue(b, TagNum.ExpireDate, expireDate);
        printTagValue(b, TagNum.AssignmentMethod, assignmentMethod);
        printTagValue(b, TagNum.AssignmentUnit, assignmentUnit);
        printTagValue(b, TagNum.OpenInterest, openInterest);
        printTagValue(b, TagNum.ExerciseMethod, exerciseMethod);
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
