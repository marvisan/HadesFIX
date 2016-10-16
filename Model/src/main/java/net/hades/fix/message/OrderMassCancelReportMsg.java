/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsg.java
 *
 * $Id: OrderMassCancelReportMsg.java,v 1.3 2011-05-07 07:05:31 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.NotAffectedOrdGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
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
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AffectedOrdGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MassCancelRejectReason;
import net.hades.fix.message.type.MassCancelRequestType;
import net.hades.fix.message.type.MassCancelResponse;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Order Mass Cancel Report is used to acknowledge an Order Mass Cancel Request. Note that each affected
 * order that is canceled is acknowledged with a separate Execution Report or Order Cancel Reject message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/05/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class OrderMassCancelReportMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ClOrdID.getValue(),
        TagNum.SecondaryClOrdID.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.MassActionReportID.getValue(),
        TagNum.SecondaryOrderID.getValue(),
        TagNum.MassCancelRequestType.getValue(),
        TagNum.MassCancelResponse.getValue(),
        TagNum.MassCancelRejectReason.getValue(),
        TagNum.TotalAffectedOrders.getValue(),
        TagNum.NoAffectedOrders.getValue(),
        TagNum.NoNotAffectedOrders.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.MarketSegmentID.getValue(),
        TagNum.Side.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrderID.getValue(),
        TagNum.MassCancelRequestType.getValue(),
        TagNum.MassCancelResponse.getValue(),
        TagNum.Side.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 11. Starting with 4.3 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 526. Starting with 4.3 version.
     */
    protected String secondaryClOrdID;

    /**
     * TagNum = 37 REQUIRED. Starting with 4.3 version.
     */
    protected String orderID;

    /**
     * TagNum = 1369. Starting with 5.0SP1 version.
     */
    protected String massActionReportID;

    /**
     * TagNum = 198. Starting with 4.3 version.
     */
    protected String secondaryOrderID;

    /**
     * TagNum = 530 REQUIRED. Starting with 4.3 version.
     */
    protected MassCancelRequestType massCancelRequestType;

    /**
     * TagNum = 531 REQUIRED. Starting with 4.3 version.
     */
    protected MassCancelResponse massCancelResponse;

    /**
     * TagNum = 532. Starting with 4.3 version.
     */
    protected MassCancelRejectReason massCancelRejectReason;

    /**
     * TagNum = 533. Starting with 4.3 version.
     */
    protected Integer totalAffectedOrders;
    
    /**
     * TagNum = 534. Starting with 4.3 version.
     */
    protected Integer noAffectedOrders;

    /**
     * Starting with 4.3 version.
     */
    protected AffectedOrdGroup[] affectedOrdGroups;
    
    /**
     * TagNum = 1370. Starting with 5.0SP1 version.
     */
    protected Integer noNotAffectedOrders;

    /**
     * Starting with 5.0SP1 version.
     */
    protected NotAffectedOrdGroup[] notAffectedOrdGroups;

    /**
     * TagNum = 336. Starting with 4.3 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * Starting with 5.0 version.
     */
    protected Parties parties;

    /**
     * Starting with 5.0SP2 version.
     */
    protected TargetParties targetParties;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.3 version.
     */
    protected UnderlyingInstrument underlyingInstrument;

    /**
     * TagNum = 1301. Starting with 5.0SP1 version.
     */
    protected String marketID;

    /**
     * TagNum = 1300. Starting with 5.0SP1 version.
     */
    protected String marketSegmentID;

    /**
     * TagNum = 54 REQUIRED. Starting with 4.3 version.
     */
    protected Side side;

    /**
     * TagNum = 60. Starting with 4.3 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 58. Starting with 4.3 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.3 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.3 version.
     */
    protected byte[] encodedText;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderMassCancelReportMsg() {
        super();
    }

    public OrderMassCancelReportMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public OrderMassCancelReportMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.OrderMassCancelReport.getValue(), beginString);
    }

    public OrderMassCancelReportMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.OrderMassCancelReport.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.ClOrdID, required = true)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID, required = true)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    /**
     * Message field setter.
     * @param secondaryClOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryClOrdID)
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderID, required = true)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrderID, required = true)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MassActionReportID)
    public String getMassActionReportID() {
        return massActionReportID;
    }

    /**
     * Message field setter.
     * @param massActionReportID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MassActionReportID)
    public void setMassActionReportID(String massActionReportID) {
        this.massActionReportID = massActionReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    /**
     * Message field setter.
     * @param secondaryOrderID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryOrderID)
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassCancelRequestType, required = true)
    public MassCancelRequestType getMassCancelRequestType() {
        return massCancelRequestType;
    }

    /**
     * Message field setter.
     * @param massCancelRequestType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassCancelRequestType, required = true)
    public void setMassCancelRequestType(MassCancelRequestType massCancelRequestType) {
        this.massCancelRequestType = massCancelRequestType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassCancelResponse, required = true)
    public MassCancelResponse getMassCancelResponse() {
        return massCancelResponse;
    }

    /**
     * Message field setter.
     * @param massCancelResponse field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassCancelResponse, required = true)
    public void setMassCancelResponse(MassCancelResponse massCancelResponse) {
        this.massCancelResponse = massCancelResponse;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassCancelRejectReason)
    public MassCancelRejectReason getMassCancelRejectReason() {
        return massCancelRejectReason;
    }

    /**
     * Message field setter.
     * @param massCancelRejectReason field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassCancelRejectReason)
    public void setMassCancelRejectReason(MassCancelRejectReason massCancelRejectReason) {
        this.massCancelRejectReason = massCancelRejectReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TotalAffectedOrders)
    public Integer getTotalAffectedOrders() {
        return totalAffectedOrders;
    }

    /**
     * Message field setter.
     * @param totalAffectedOrders field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TotalAffectedOrders)
    public void setTotalAffectedOrders(Integer totalAffectedOrders) {
        this.totalAffectedOrders = totalAffectedOrders;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoAffectedOrders)
    public Integer getNoAffectedOrders() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link AffectedOrdGroup} components. It will also create an array
     * of {@link AffectedOrdGroup} objects and set the <code>affectedOrdGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>affectedOrdGroups</code> array they will be discarded.<br/>
     * @param noAffectedOrders number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoAffectedOrders)
    public void setNoAffectedOrders(Integer noAffectedOrders) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link AffectedOrdGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.3")
    public AffectedOrdGroup[] getAffectedOrdGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link AffectedOrdGroup} object to the existing array of <code>affectedOrdGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noAffectedOrders</code> field to the proper value.<br/>
     * Note: If the <code>setNoAffectedOrders</code> method has been called there will already be a number of objects in the
     * <code>affectedOrdGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.3")
    public AffectedOrdGroup addAffectedOrdGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link AffectedOrdGroup} object from the existing array of <code>affectedOrdGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noAffectedOrders</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.3")
    public AffectedOrdGroup deleteAffectedOrdGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link AffectedOrdGroup} objects from the <code>affectedOrdGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noAffectedOrders</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3")
    public int clearAffectedOrdGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoNotAffectedOrders)
    public Integer getNoNotAffectedOrders() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link NotAffectedOrdGroup} components. It will also create an array
     * of {@link NotAffectedOrdGroup} objects and set the <code>notAffectedOrdGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>notAffectedOrdGroups</code> array they will be discarded.<br/>
     * @param noNotAffectedOrders number of MsgTypeGroup objects
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.NoNotAffectedOrders)
    public void setNoNotAffectedOrders(Integer noNotAffectedOrders) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link NotAffectedOrdGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public NotAffectedOrdGroup[] getNotAffectedOrdGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link NotAffectedOrdGroup} object to the existing array of <code>notAffectedOrdGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNotAffectedOrders</code> field to the proper value.<br/>
     * Note: If the <code>setNoNotAffectedOrders</code> method has been called there will already be a number of objects in the
     * <code>notAffectedOrdGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public NotAffectedOrdGroup addNotAffectedOrdGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link NotAffectedOrdGroup} object from the existing array of <code>notAffectedOrdGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNotAffectedOrders</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public NotAffectedOrdGroup deleteNotAffectedOrdGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link NotAffectedOrdGroup} objects from the <code>notAffectedOrdGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNotAffectedOrders</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearNotAffectedOrdGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper component implementation.
     */
    @FIXVersion(introduced="5.0")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced="5.0")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    public TargetParties getTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TargetParties component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced="5.0SP2")
    public void setTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TargetParties component to null.
     */
    @FIXVersion(introduced="5.0SP2")
    public void clearTargetParties() {
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
     * Sets the Instrument component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced="4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public UnderlyingInstrument getUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Sets the UnderlyingInstrument component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced = "4.3")
    public void setUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Sets the UnderlyingInstrument component to null.
     */
    @FIXVersion(introduced = "4.3")
    public void clearUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public String getMarketID() {
        return marketID;
    }

    /**
     * Message field setter.
     * @param marketID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketID)
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    /**
     * Message field setter.
     * @param marketSegmentID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.MarketSegmentID)
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Side, required = true)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Side, required = true)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime, required = true)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TransactTime, required = true)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.3")
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
        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
            hasMissingTag = true;
        }
        if (massActionReportID == null || massActionReportID.trim().isEmpty()) {
            errorMsg.append(" [MassActionReportID]");
            hasMissingTag = true;
        }
        if (massCancelRequestType == null) {
            errorMsg.append(" [MassCancelRequestType]");
            hasMissingTag = true;
        }
        if (massCancelResponse == null) {
            errorMsg.append(" [MassCancelResponse]");
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
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.MassActionReportID, massActionReportID);
            TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            if (massCancelRequestType != null) {
                TagEncoder.encode(bao, TagNum.MassCancelRequestType, massCancelRequestType.getValue());
            }
            if (massCancelResponse != null) {
                TagEncoder.encode(bao, TagNum.MassCancelResponse, massCancelResponse.getValue());
            }
            if (massCancelRejectReason != null) {
                TagEncoder.encode(bao, TagNum.MassCancelRejectReason, massCancelRejectReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.TotalAffectedOrders, totalAffectedOrders);
            if (noAffectedOrders != null) {
                TagEncoder.encode(bao, TagNum.NoAffectedOrders, noAffectedOrders);
                if (affectedOrdGroups != null && affectedOrdGroups.length == noAffectedOrders.intValue()) {
                    for (int i = 0; i < noAffectedOrders.intValue(); i++) {
                        if (affectedOrdGroups[i] != null) {
                            bao.write(affectedOrdGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "AffectedOrdGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAffectedOrders.getValue(), error);
                }
            }
            if (noNotAffectedOrders != null) {
                TagEncoder.encode(bao, TagNum.NoNotAffectedOrders, noNotAffectedOrders);
                if (notAffectedOrdGroups != null && notAffectedOrdGroups.length == noNotAffectedOrders.intValue()) {
                    for (int i = 0; i < noNotAffectedOrders.intValue(); i++) {
                        if (notAffectedOrdGroups[i] != null) {
                            bao.write(notAffectedOrdGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "NotAffectedOrdGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoNotAffectedOrders.getValue(), error);
                }
            }
            
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (targetParties != null) {
                bao.write(targetParties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
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
            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

           case SecondaryClOrdID:
                secondaryClOrdID = new String(tag.value, sessionCharset);
                break;
               
            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;
               
            case MassActionReportID:
                massActionReportID = new String(tag.value, sessionCharset);
                break;
               
            case SecondaryOrderID:
                secondaryOrderID = new String(tag.value, sessionCharset);
                break;

            case MassCancelRequestType:
                massCancelRequestType = MassCancelRequestType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MassCancelResponse:
                massCancelResponse = MassCancelResponse.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MassCancelRejectReason:
                massCancelRejectReason = MassCancelRejectReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TotalAffectedOrders:
                totalAffectedOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoAffectedOrders:
                noAffectedOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoNotAffectedOrders:
                noNotAffectedOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case MarketID:
                marketID = new String(tag.value, sessionCharset);
                break;

            case MarketSegmentID:
                marketSegmentID = new String(tag.value, sessionCharset);
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [OrderMassCancelReportMsg] fields.";
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
        StringBuilder b = new StringBuilder("{OrderMassCancelReportMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.SecondaryClOrdID, secondaryClOrdID);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.MassActionReportID, massActionReportID);
        printTagValue(b, TagNum.SecondaryOrderID, secondaryOrderID);
        printTagValue(b, TagNum.MassCancelRequestType, massCancelRequestType);
        printTagValue(b, TagNum.MassCancelResponse, massCancelResponse);
        printTagValue(b, TagNum.MassCancelRejectReason, massCancelRejectReason);
        printTagValue(b, TagNum.TotalAffectedOrders, totalAffectedOrders);
        printTagValue(b, TagNum.NoAffectedOrders, noAffectedOrders);
        printTagValue(b, affectedOrdGroups);
        printTagValue(b, TagNum.NoNotAffectedOrders, noNotAffectedOrders);
        printTagValue(b, notAffectedOrdGroups);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, parties);
        printTagValue(b, targetParties);
        printTagValue(b, instrument);
        printTagValue(b, underlyingInstrument);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, TagNum.Side, side);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
