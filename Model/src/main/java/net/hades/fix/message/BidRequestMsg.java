/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRequestMsg.java
 *
 * $Id: BidRequestMsg.java,v 1.3 2011-04-28 10:07:42 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.BidRequestTransType;
import net.hades.fix.message.type.MsgType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.BidReqComponentGroup;
import net.hades.fix.message.group.BidReqDescriptorGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BasisPxType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BidTradeType;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IncTaxInd;
import net.hades.fix.message.type.LiquidityIndType;
import net.hades.fix.message.type.ProgRptReqs;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The BidRequest Message can be used in one of two ways depending on which market conventions are being
 * followed.
 * In the “Non disclosed” convention (e.g. US/European model) the BidRequest message can be used to request a
 * bid based on the sector, country, index and liquidity information contained within the message itself.<br/>
 * In the “Disclosed” convention (e.g. Japanese model) the BidRequest message can be used to request bids based
 * on the ListOrderDetail messages sent in advance of BidRequest message. In the “Disclosed” convention the list
 * repeating group is used to define which ListOrderDetail messages a bid is being sort for and the directions of
 * the required bids.<br/>
 * The pair of fields SideValue1 and SideValue2 are used to show the monetary total value in either direction (buy
 * or sell) of the transaction without revealing whether it is the buy-side institution’s intention to buy or sell.
 * The two repeating groups, NoEntries and NoBidComponents are mutually exclusive and a function of which
 * bidding model is being used. If the “Non Disclosure” method is being used the portfolio of stocks being traded
 * is described by a number of “bid descriptors” entries. If the “Disclosure” Method is being used the portfolio is
 * fully disclosed, except for side, by a number of “list” entries enumerating the lists that list the stocks to be
 * traded.<br/>
 * A BidRequest message with BidRequestTransType cancel may be used to indicate to sell side firms that they no
 * longer need to store details of the BidRequest as they have either lost the bid or the List has been canceled.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 16/01/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BidRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.BidID.getValue(),
        TagNum.ClientBidID.getValue(),
        TagNum.BidRequestTransType.getValue(),
        TagNum.ListName.getValue(),
        TagNum.TotNoRelatedSym.getValue(),
        TagNum.BidType.getValue(),
        TagNum.NumTickets.getValue(),
        TagNum.Currency.getValue(),
        TagNum.SideValue1.getValue(),
        TagNum.SideValue2.getValue(),
        TagNum.NoBidDescriptors.getValue(),
        TagNum.NoBidComponents.getValue(),
        TagNum.LiquidityIndType.getValue(),
        TagNum.WtAverageLiquidity.getValue(),
        TagNum.ExchangeForPhysical.getValue(),
        TagNum.OutMainCntryUIndex.getValue(),
        TagNum.CrossPercent.getValue(),
        TagNum.ProgRptReqs.getValue(),
        TagNum.ProgPeriodInterval.getValue(),
        TagNum.IncTaxInd.getValue(),
        TagNum.ForexReq.getValue(),
        TagNum.NumBidders.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.BidTradeType.getValue(),
        TagNum.BasisPxType.getValue(),
        TagNum.StrikeTime.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 390. Starting with 4.2 version.
     */
    protected String bidID;

    /**
     * TagNum = 391 REQUIRED. Starting with 4.2 version.
     */
    protected String clientBidID;

    /**
     * TagNum = 374 REQUIRED. Starting with 4.2 version.
     */
    protected BidRequestTransType bidRequestTransType;

    /**
     * TagNum = 392. Starting with 4.2 version.
     */
    protected String listName;

    /**
     * TagNum = 393 REQUIRED. Starting with 4.2 version.
     */
    protected Integer totNoRelatedSym;

    /**
     * TagNum = 394 REQUIRED. Starting with 4.2 version.
     */
    protected BidType bidType;

    /**
     * TagNum = 395. Starting with 4.2 version.
     */
    protected Integer numTickets;

    /**
     * TagNum = 15. Starting with 4.2 version.
     */
    protected Currency currency;

    /**
     * TagNum = 396. Starting with 4.2 version.
     */
    protected Double sideValue1;

    /**
     * TagNum = 397. Starting with 4.2 version.
     */
    protected Double sideValue2;

    /**
     * TagNum = 398. Starting with 4.2 version.
     */
    protected Integer noBidDescriptors;

    /**
     * Starting with 4.2 version.
     */
    protected BidReqDescriptorGroup[] bidDescriptorGroups;

    /**
     * TagNum = 420. Starting with 4.2 version.
     */
    protected Integer noBidComponents;

    /**
     * Starting with 4.2 version.
     */
    protected BidReqComponentGroup[] bidComponentGroups;

    /**
     * TagNum = 409. Starting with 4.2 version.
     */
    protected LiquidityIndType liquidityIndType;

    /**
     * TagNum = 410. Starting with 4.2 version.
     */
    protected Double wtAverageLiquidity;

    /**
     * TagNum = 411. Starting with 4.2 version.
     */
    protected Boolean exchangeForPhysical;

    /**
     * TagNum = 412. Starting with 4.2 version.
     */
    protected Double outMainCntryUIndex;

    /**
     * TagNum = 413. Starting with 4.2 version.
     */
    protected Double crossPercent;

    /**
     * TagNum = 414. Starting with 4.2 version.
     */
    protected ProgRptReqs progRptReqs;

    /**
     * TagNum = 415. Starting with 4.2 version.
     */
    protected Integer progPeriodInterval;

    /**
     * TagNum = 416. Starting with 4.2 version.
     */
    protected IncTaxInd incTaxInd;

    /**
     * TagNum = 121. Starting with 4.2 version.
     */
    protected Boolean forexReq;

    /**
     * TagNum = 417. Starting with 4.2 version.
     */
    protected Integer numBidders;

    /**
     * TagNum = 75. Starting with 4.2 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 418 REQUIRED. Starting with 4.2 version.
     */
    protected BidTradeType bidTradeType;

    /**
     * TagNum = 419 REQUIRED. Starting with 4.2 version.
     */
    protected BasisPxType basisPxType;

    /**
     * TagNum = 443. Starting with 4.2 version.
     */
    protected Date strikeTime;

    /**
     * TagNum = 58. Starting with 4.2 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.2 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.2 version.
     */
    protected byte[] encodedText;

    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidRequestMsg() {
        super();
    }

    public BidRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public BidRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.BidRequest.getValue(), beginString);
    }

    public BidRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.BidRequest.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidID)
    public String getBidID() {
        return bidID;
    }

    /**
     * Message field setter.
     * @param bidID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidID)
    public void setBidID(String bidID) {
        this.bidID = bidID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClientBidID, required=true)
    public String getClientBidID() {
        return clientBidID;
    }

    /**
     * Message field setter.
     * @param clientBidID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ClientBidID, required=true)
    public void setClientBidID(String clientBidID) {
        this.clientBidID = clientBidID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidRequestTransType, required=true)
    public BidRequestTransType getBidRequestTransType() {
        return bidRequestTransType;
    }

    /**
     * Message field setter.
     * @param bidRequestTransType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidRequestTransType, required=true)
    public void setBidRequestTransType(BidRequestTransType bidRequestTransType) {
        this.bidRequestTransType = bidRequestTransType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListName)
    public String getListName() {
        return listName;
    }

    /**
     * Message field setter.
     * @param listName field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListName)
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoRelatedSym, required=true)
    public Integer getTotNoRelatedSym() {
        return totNoRelatedSym;
    }

    /**
     * Message field setter.
     * @param totNoRelatedSym field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoRelatedSym, required=true)
    public void setTotNoRelatedSym(Integer totNoRelatedSym) {
        this.totNoRelatedSym = totNoRelatedSym;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidType, required=true)
    public BidType getBidType() {
        return bidType;
    }

    /**
     * Message field setter.
     * @param bidType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidType, required=true)
    public void setBidType(BidType bidType) {
        this.bidType = bidType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NumTickets)
    public Integer getNumTickets() {
        return numTickets;
    }

    /**
     * Message field setter.
     * @param numTickets field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NumTickets)
    public void setNumTickets(Integer numTickets) {
        this.numTickets = numTickets;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Message field setter.
     * @param currency field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Currency)
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SideValue1)
    public Double getSideValue1() {
        return sideValue1;
    }

    /**
     * Message field setter.
     * @param sideValue1 field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SideValue1)
    public void setSideValue1(Double sideValue1) {
        this.sideValue1 = sideValue1;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SideValue2)
    public Double getSideValue2() {
        return sideValue2;
    }

    /**
     * Message field setter.
     * @param sideValue2 field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SideValue2)
    public void setSideValue2(Double sideValue2) {
        this.sideValue2 = sideValue2;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoBidDescriptors)
    public Integer getNoBidDescriptors() {
        return noBidDescriptors;
    }

    /**
     * This method sets the number of {@link BidReqDescriptorGroup} components. It will also create an array
     * of {@link BidReqDescriptorGroup} objects and set the <code>bidDescriptorGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>bidDescriptorGroups</code> array they will be discarded.<br/>
     * @param noBidDescriptors number of objects
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoBidDescriptors)
    public void setNoBidDescriptors(Integer noBidDescriptors) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link BidReqDescriptorGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.2")
    public BidReqDescriptorGroup[] getBidDescriptorGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link BidReqDescriptorGroup} object to the existing array of <code>bidDescriptorGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noBidDescriptors</code> field to the proper value.<br/>
     * Note: If the <code>setNoBidDescriptors</code> method has been called there will already be a number of objects in the
     * <code>bidDescriptorGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2")
    public BidReqDescriptorGroup addBidDescriptorGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link BidReqDescriptorGroup} object from the existing array of <code>bidDescriptorGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noBidDescriptors</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2")
    public BidReqDescriptorGroup deleteBidDescriptorGroup(int index) {
        BidReqDescriptorGroup result = null;
        if (bidDescriptorGroups != null && bidDescriptorGroups.length > 0 && bidDescriptorGroups.length > index) {
            List<BidReqDescriptorGroup> groups = new ArrayList<BidReqDescriptorGroup>(Arrays.asList(bidDescriptorGroups));
            result = groups.remove(index);
            bidDescriptorGroups = groups.toArray(new BidReqDescriptorGroup[groups.size()]);
            if (bidDescriptorGroups.length > 0) {
                noBidDescriptors = new Integer(bidDescriptorGroups.length);
            } else {
                bidDescriptorGroups = null;
                noBidDescriptors = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link BidReqDescriptorGroup} objects from the <code>bidDescriptorGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noBidDescriptors</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2")
    public int clearBidDescriptorGroups() {
        int result = 0;
        if (bidDescriptorGroups != null && bidDescriptorGroups.length > 0) {
            result = bidDescriptorGroups.length;
            bidDescriptorGroups = null;
            noBidDescriptors = null;
        }

        return result;

    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoBidComponents)
    public Integer getNoBidComponents() {
        return noBidComponents;
    }

    /**
     * This method sets the number of {@link BidReqComponentGroup} components. It will also create an array
     * of {@link BidReqComponentGroup} objects and set the <code>bidComponentGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>bidComponentGroups</code> array they will be discarded.<br/>
     * @param noBidComponents number of objects
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoBidComponents)
    public void setNoBidComponents(Integer noBidComponents) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link BidReqComponentGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.2")
    public BidReqComponentGroup[] getBidComponentGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link BidReqComponentGroup} object to the existing array of <code>bidComponentGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noBidComponents</code> field to the proper value.<br/>
     * Note: If the <code>setNoBidComponents</code> method has been called there will already be a number of objects in the
     * <code>bidComponentGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2")
    public BidReqComponentGroup addBidComponentGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link BidReqComponentGroup} object from the existing array of <code>bidComponentGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noBidComponents</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2")
    public BidReqComponentGroup deleteBidComponentGroup(int index) {
        BidReqComponentGroup result = null;
        if (bidComponentGroups != null && bidComponentGroups.length > 0 && bidComponentGroups.length > index) {
            List<BidReqComponentGroup> groups = new ArrayList<BidReqComponentGroup>(Arrays.asList(bidComponentGroups));
            result = groups.remove(index);
            bidComponentGroups = groups.toArray(new BidReqComponentGroup[groups.size()]);
            if (bidComponentGroups.length > 0) {
                noBidComponents = new Integer(bidComponentGroups.length);
            } else {
                bidComponentGroups = null;
                noBidComponents = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link BidReqComponentGroup} objects from the <code>bidComponentGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noBidComponents</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2")
    public int clearBidComponentGroups() {
        int result = 0;
        if (bidComponentGroups != null && bidComponentGroups.length > 0) {
            result = bidComponentGroups.length;
            bidComponentGroups = null;
            noBidComponents = null;
        }

        return result;

    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LiquidityIndType)
    public LiquidityIndType getLiquidityIndType() {
        return liquidityIndType;
    }

    /**
     * Message field setter.
     * @param liquidityIndType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LiquidityIndType)
    public void setLiquidityIndType(LiquidityIndType liquidityIndType) {
        this.liquidityIndType = liquidityIndType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.WtAverageLiquidity)
    public Double getWtAverageLiquidity() {
        return wtAverageLiquidity;
    }

    /**
     * Message field setter.
     * @param wtAverageLiquidity field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.WtAverageLiquidity)
    public void setWtAverageLiquidity(Double wtAverageLiquidity) {
        this.wtAverageLiquidity = wtAverageLiquidity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExchangeForPhysical)
    public Boolean getExchangeForPhysical() {
        return exchangeForPhysical;
    }

    /**
     * Message field setter.
     * @param exchangeForPhysical field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ExchangeForPhysical)
    public void setExchangeForPhysical(Boolean exchangeForPhysical) {
        this.exchangeForPhysical = exchangeForPhysical;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OutMainCntryUIndex)
    public Double getOutMainCntryUIndex() {
        return outMainCntryUIndex;
    }

    /**
     * Message field setter.
     * @param outMainCntryUIndex field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.OutMainCntryUIndex)
    public void setOutMainCntryUIndex(Double outMainCntryUIndex) {
        this.outMainCntryUIndex = outMainCntryUIndex;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.CrossPercent)
    public Double getCrossPercent() {
        return crossPercent;
    }

    /**
     * Message field setter.
     * @param crossPercent field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.CrossPercent)
    public void setCrossPercent(Double crossPercent) {
        this.crossPercent = crossPercent;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgRptReqs)
    public ProgRptReqs getProgRptReqs() {
        return progRptReqs;
    }

    /**
     * Message field setter.
     * @param progRptReqs field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgRptReqs)
    public void setProgRptReqs(ProgRptReqs progRptReqs) {
        this.progRptReqs = progRptReqs;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgPeriodInterval)
    public Integer getProgPeriodInterval() {
        return progPeriodInterval;
    }

    /**
     * Message field setter.
     * @param progPeriodInterval field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ProgPeriodInterval)
    public void setProgPeriodInterval(Integer progPeriodInterval) {
        this.progPeriodInterval = progPeriodInterval;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.IncTaxInd)
    public IncTaxInd getIncTaxInd() {
        return incTaxInd;
    }

    /**
     * Message field setter.
     * @param incTaxInd field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.IncTaxInd)
    public void setIncTaxInd(IncTaxInd incTaxInd) {
        this.incTaxInd = incTaxInd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public Boolean getForexReq() {
        return forexReq;
    }

    /**
     * Message field setter.
     * @param forexReq field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ForexReq)
    public void setForexReq(Boolean forexReq) {
        this.forexReq = forexReq;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NumBidders)
    public Integer getNumBidders() {
        return numBidders;
    }

    /**
     * Message field setter.
     * @param numBidders field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NumBidders)
    public void setNumBidders(Integer numBidders) {
        this.numBidders = numBidders;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidTradeType, required=true)
    public BidTradeType getBidTradeType() {
        return bidTradeType;
    }

    /**
     * Message field setter.
     * @param bidTradeType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BidTradeType, required=true)
    public void setBidTradeType(BidTradeType bidTradeType) {
        this.bidTradeType = bidTradeType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BasisPxType, required=true)
    public BasisPxType getBasisPxType() {
        return basisPxType;
    }

    /**
     * Message field setter.
     * @param basisPxType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.BasisPxType, required=true)
    public void setBasisPxType(BasisPxType basisPxType) {
        this.basisPxType = basisPxType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.StrikeTime)
    public Date getStrikeTime() {
        return strikeTime;
    }

    /**
     * Message field setter.
     * @param strikeTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.StrikeTime)
    public void setStrikeTime(Date strikeTime) {
        this.strikeTime = strikeTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.2")
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

        if (clientBidID == null || clientBidID.trim().isEmpty()) {
            errorMsg.append(" [ClientBidID]");
            hasMissingTag = true;
        }
        if (bidRequestTransType == null) {
            errorMsg.append(" [BidRequestTransType]");
            hasMissingTag = true;
        }
        if (totNoRelatedSym == null) {
            errorMsg.append(" [TotNoRelatedSym]");
            hasMissingTag = true;
        }
        if (bidType == null) {
            errorMsg.append(" [BidType]");
            hasMissingTag = true;
        }
        if (bidTradeType == null) {
            errorMsg.append(" [BidTradeType]");
            hasMissingTag = true;
        }
        if (basisPxType == null) {
            errorMsg.append(" [BasisPxType]");
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
            TagEncoder.encode(bao, TagNum.BidID, bidID);
            TagEncoder.encode(bao, TagNum.ClientBidID, clientBidID);
            if (bidRequestTransType != null) {
                TagEncoder.encode(bao, TagNum.BidRequestTransType, bidRequestTransType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ListName, listName);
            TagEncoder.encode(bao, TagNum.TotNoRelatedSym, totNoRelatedSym);
            if (bidType != null) {
                TagEncoder.encode(bao, TagNum.BidType, bidType.getValue());
            }
            TagEncoder.encode(bao, TagNum.NumTickets, numTickets);
            if (currency != null) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            TagEncoder.encode(bao, TagNum.SideValue1, sideValue1);
            TagEncoder.encode(bao, TagNum.SideValue2, sideValue2);
            if (noBidDescriptors != null) {
                TagEncoder.encode(bao, TagNum.NoBidDescriptors, noBidDescriptors);
                if (bidDescriptorGroups != null && bidDescriptorGroups.length == noBidDescriptors.intValue()) {
                    for (int i = 0; i < noBidDescriptors.intValue(); i++) {
                        if (bidDescriptorGroups[i] != null) {
                            bao.write(bidDescriptorGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "BidReqDescriptorGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoBidDescriptors.getValue(), error);
                }
            }
            if (noBidComponents != null) {
                TagEncoder.encode(bao, TagNum.NoBidComponents, noBidComponents);
                if (bidComponentGroups != null && bidComponentGroups.length == noBidComponents.intValue()) {
                    for (int i = 0; i < noBidComponents.intValue(); i++) {
                        if (bidComponentGroups[i] != null) {
                            bao.write(bidComponentGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "BidReqComponentGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoBidComponents.getValue(), error);
                }
            }
            if (liquidityIndType != null) {
                TagEncoder.encode(bao, TagNum.LiquidityIndType, liquidityIndType.getValue());
            }
            TagEncoder.encode(bao, TagNum.WtAverageLiquidity, wtAverageLiquidity);
            TagEncoder.encode(bao, TagNum.ExchangeForPhysical, exchangeForPhysical);
            TagEncoder.encode(bao, TagNum.OutMainCntryUIndex, outMainCntryUIndex);
            TagEncoder.encode(bao, TagNum.CrossPercent, crossPercent);
            if (progRptReqs != null) {
                TagEncoder.encode(bao, TagNum.ProgRptReqs, progRptReqs.getValue());
            }
            TagEncoder.encode(bao, TagNum.ProgPeriodInterval, progPeriodInterval);
            if (incTaxInd != null) {
                TagEncoder.encode(bao, TagNum.IncTaxInd, incTaxInd.getValue());
            }
            TagEncoder.encode(bao, TagNum.ForexReq, forexReq);
            TagEncoder.encode(bao, TagNum.NumBidders, numBidders);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            if (bidTradeType != null) {
                TagEncoder.encode(bao, TagNum.BidTradeType, bidTradeType.getValue());
            }
            if (basisPxType != null) {
                TagEncoder.encode(bao, TagNum.BasisPxType, basisPxType.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.StrikeTime, strikeTime);
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
            case BidID:
                bidID = new String(tag.value, sessionCharset);
                break;

            case ClientBidID:
                clientBidID = new String(tag.value, sessionCharset);
                break;

            case BidRequestTransType:
                bidRequestTransType = BidRequestTransType.valueFor((new String(tag.value, sessionCharset)).charAt(0));
                break;

           case ListName:
                listName = new String(tag.value, sessionCharset);
                break;

            case TotNoRelatedSym:
                totNoRelatedSym = new Integer(new String(tag.value, sessionCharset));
                break;

            case BidType:
                bidType = BidType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NumTickets:
                numTickets = new Integer(new String(tag.value, sessionCharset));
                break;

            case Currency:
                currency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case SideValue1:
                sideValue1 = new Double(new String(tag.value, sessionCharset));
                break;

            case SideValue2:
                sideValue2 = new Double(new String(tag.value, sessionCharset));
                break;

            case NoBidDescriptors:
                noBidDescriptors = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoBidComponents:
                noBidComponents = new Integer(new String(tag.value, sessionCharset));
                break;

            case LiquidityIndType:
                liquidityIndType = LiquidityIndType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case WtAverageLiquidity:
                wtAverageLiquidity = new Double(new String(tag.value, sessionCharset));
                break;

            case ExchangeForPhysical:
                exchangeForPhysical = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case OutMainCntryUIndex:
                outMainCntryUIndex = new Double(new String(tag.value, sessionCharset));
                break;

            case CrossPercent:
                crossPercent = new Double(new String(tag.value, sessionCharset));
                break;

            case ProgRptReqs:
                progRptReqs = ProgRptReqs.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ProgPeriodInterval:
                progPeriodInterval = new Integer(new String(tag.value, sessionCharset));
                break;

            case IncTaxInd:
                incTaxInd = IncTaxInd.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ForexReq:
                forexReq = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NumBidders:
                numBidders = new Integer(new String(tag.value, sessionCharset));
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case BidTradeType:
                bidTradeType = BidTradeType.valueFor((new String(tag.value, sessionCharset)).charAt(0));
                break;

            case BasisPxType:
                basisPxType = BasisPxType.valueFor((new String(tag.value, sessionCharset)).charAt(0));
                break;

            case StrikeTime:
                strikeTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [BidRequestMsg] fields.";
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
        StringBuilder b = new StringBuilder("{BidRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.BidID, bidID);
        printTagValue(b, TagNum.ClientBidID, clientBidID);
        printTagValue(b, TagNum.BidRequestTransType, bidRequestTransType);
        printTagValue(b, TagNum.ListName, listName);
        printTagValue(b, TagNum.TotNoRelatedSym, totNoRelatedSym);
        printTagValue(b, TagNum.BidType, bidType);
        printTagValue(b, TagNum.NumTickets, numTickets);
        printTagValue(b, TagNum.Currency, currency);
        printTagValue(b, TagNum.SideValue1, sideValue1);
        printTagValue(b, TagNum.SideValue2, sideValue2);
        printTagValue(b, TagNum.NoBidDescriptors, noBidDescriptors);
        printTagValue(b, bidDescriptorGroups);
        printTagValue(b, TagNum.NoBidComponents, noBidComponents);
        printTagValue(b, bidComponentGroups);
        printTagValue(b, TagNum.LiquidityIndType, liquidityIndType);
        printTagValue(b, TagNum.WtAverageLiquidity, wtAverageLiquidity);
        printTagValue(b, TagNum.ExchangeForPhysical, exchangeForPhysical);
        printTagValue(b, TagNum.OutMainCntryUIndex, outMainCntryUIndex);
        printTagValue(b, TagNum.CrossPercent, crossPercent);
        printTagValue(b, TagNum.ProgRptReqs, progRptReqs);
        printTagValue(b, TagNum.ProgPeriodInterval, progPeriodInterval);
        printTagValue(b, TagNum.IncTaxInd, incTaxInd);
        printTagValue(b, TagNum.ForexReq, forexReq);
        printTagValue(b, TagNum.NumBidders, numBidders);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printTagValue(b, TagNum.BidTradeType, bidTradeType);
        printTagValue(b, TagNum.BasisPxType, basisPxType);
        printUTCDateTimeTagValue(b, TagNum.StrikeTime, strikeTime);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
