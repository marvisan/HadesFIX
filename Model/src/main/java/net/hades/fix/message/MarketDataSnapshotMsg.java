/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataSnapshotMsg.java
 *
 * $Id: MarketDataSnapshotMsg.java,v 1.15 2011-04-28 10:07:46 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RoutingIDGroup;
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

import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.group.MDFullGroup;
import net.hades.fix.message.type.ApplQueueResolution;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Market Data messages are used as the response to a Market Data Request message. In all cases, one
 * Market Data message refers only to one Market Data Request. It can be used to transmit a 2-sided book of
 * orders or list of quotes, a list of trades, index values, opening, closing, settlement, high, low, or VWAP prices,
 * the trade volume or open interest for a security, or any combination of these.
 * Market Data messages sent as the result of a Market Data Request message will specify the appropriate
 * MDReqID. Unsolicited Market Data messages can be sent; in such cases, MDReqID will not be present.
 * Market Data messages include many fields, and not all are required to be used. A firm may, at its option,
 * choose to send the minimum fields required, or may choose to send more information, such as tick direction,
 * tagging of best quotes, etc.<br/>
 * Market Data messages can take two forms. The first Market Data message format used for a Snapshot, or a
 * Snapshot + Updates where MDUpdateType = Full Refresh (0) is as follows:
 * <ul>
 *      <li>For Market Data Requests where a Bid or Offer is added, changed, or deleted, every update to a Market
 * Data Entry results in a new Market Data message that contains the entirety of the data requested for that
 * instrument, not just the changed Market Data Entry. In other words, both sides of the market, or just one
 * side in the case of a request of only bids or offers, for the depth requested, must be sent in one FIX Market
 * Data message.</li>
 *      <li>A Market Data message may contain several trades, imbalances, an index value, opening, closing,
 * settlement, high, low, and/or VWAP price for one instrument, as well as the traded volume and open
 * interest, but only one instrument per message.</li>
 *      <li>Messages containing bids and/or offers cannot contain trades, imbalances, index value, opening, closing,
 * settlement, high, low, and/or VWAP prices, trade volume, or open interest as separate entires.</li>
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.15 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MarketDataSnapshotMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
            TagNum.TotNumReports.getValue(),
            TagNum.MDReqID.getValue(),
            TagNum.MDReportID.getValue(),
            TagNum.ClearingBusinessDate.getValue(),
            TagNum.MDBookType.getValue(),
            TagNum.MDSubBookType.getValue(),
            TagNum.MarketDepth.getValue(),
            TagNum.MDFeedType.getValue(),
            TagNum.RefreshIndicator.getValue(),
            TagNum.TradeDate.getValue(),
            TagNum.MDStreamID.getValue(),
            TagNum.NoUnderlyings.getValue(),
            TagNum.NoLegs.getValue(),
            TagNum.FinancialStatus.getValue(),
            TagNum.CorporateAction.getValue(),
            TagNum.TotalVolumeTraded.getValue(),
            TagNum.TotalVolumeTradedDate.getValue(),
            TagNum.TotalVolumeTradedTime.getValue(),
            TagNum.NetChgPrevDay.getValue(),
            TagNum.NoMDEntries.getValue(),
            TagNum.ApplQueueDepth.getValue(),
            TagNum.ApplQueueResolution.getValue(),
            TagNum.NoRoutingIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new  HashSet<Integer>();

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
            TagNum.Symbol.getValue()
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
     * TagNum = 911. Starting with 5.0SP1 version.
     */
    protected Integer totNumReports;

    /**
     * TagNum = 262. Starting with 4.2 version.
     */
    protected String mdReqID;

    /**
     * TagNum = 963. Starting with 5.0 version.
     */
    protected Integer mdReportID;

    /**
     * TagNum = 715. Starting with 5.0 version.
     */
    protected Date clearingBusinessDate;

    /**
     * TagNum = 1021. Starting with 5.0 version.
     */
    protected MDBookType mdBookType;

    /**
     * TagNum = 1173. Starting with 5.0SP1 version.
     */
    protected Integer mdSubBookType;

    /**
     * TagNum = 264. Starting with 5.0SP1 version.
     */
    protected Integer marketDepth;

    /**
     * TagNum = 1022. Starting with 5.0SP1 version.
     */
    protected String mdFeedType;

    /**
     * TagNum = 1187. Starting with 5.0SP1 version.
     */
    protected Boolean refreshIndicator;

    /**
     * TagNum = 75. Starting with 4.3 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 1500. Starting with 5.0SP2 version.
     */
    protected String mdStreamID;

    /**
     * Starting with 5.0 version.
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
     * TagNum = 55 REQUIRED. Starting with 4.2 version.
     */
    protected String symbol;

    /**
     * TagNum = 65. Starting with 4.2 version.
     */
    protected String symbolSfx;

    /**
     * TagNum = 48. Starting with 4.2 version.
     */
    protected String securityID;

    /**
     * TagNum = 22. Starting with 4.2 version.
     */
    protected String securityIDSource;

    /**
     * TagNum = 167. Starting with 4.2 version.
     */
    protected String securityType;

    /**
     * TagNum = 200. Starting with 4.2 version.
     */
    protected String maturityMonthYear;

    /**
     * TagNum = 205. Starting with 4.2 version.
     */
    protected Integer maturityDay;

    /**
     * TagNum = 201. Starting with 4.2 version.
     */
    protected PutOrCall putOrCall;

    /**
     * TagNum = 202. Starting with 4.2 version.
     */
    protected Double strikePrice;

    /**
     * TagNum = 206. Starting with 4.2 version.
     */
    protected Character optAttribute;

    /**
     * TagNum = 231. Starting with 4.2 version.
     */
    protected Double contractMultiplier;

    /**
     * TagNum = 223. Starting with 4.2 version.
     */
    protected Double couponRate;

    /**
     * TagNum = 207. Starting with 4.2 version.
     */
    protected String securityExchange;

    /**
     * TagNum = 106. Starting with 4.2 version.
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
     * TagNum = 107. Starting with 4.2 version.
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
     * TagNum = 291. Starting with 4.2 version.
     */
    protected String financialStatus;

    /**
     * TagNum = 292. Starting with 4.2 version.
     */
    protected String corporateAction;

    /**
     * TagNum = 387. Starting with 4.2 version.
     */
    protected Double totalVolumeTraded;

    /**
     * TagNum = 449. Starting with 4.3 version.
     */
    protected Date totalVolumeTradedDate;

    /**
     * TagNum = 450. Starting with 4.3 version.
     */
    protected Date totalVolumeTradedTime;

    /**
     * TagNum = 451. Starting with 4.3 version.
     */
    protected Double netChgPrevDay;

    /**
     * TagNum = 268. Starting with 4.2 version.
     */
    protected Integer noMDEntries;

    /**
     * Starting with 4.2 version.
     */
    protected MDFullGroup[] mdFullGroups;

    /**
     * TagNum = 813. Starting with 5.0 version.
     */
    protected Integer applQueueDepth;

    /**
     * TagNum = 814. Starting with 5.0 version.
     */
    protected ApplQueueResolution applQueueResolution;

    /**
     * TagNum = 215. Starting with 5.0 version.
     */
    protected Integer noRoutingIDs;

    /**
     * Starting with 5.0 version.
     */
    protected RoutingIDGroup[] routingIDGroups;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public MarketDataSnapshotMsg() {
        super();
    }

    public MarketDataSnapshotMsg(Header header, ByteBuffer rawMsg)
            throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataSnapshotMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.MarketDataSnapshot.getValue(), beginString);
    }

    public MarketDataSnapshotMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.MarketDataSnapshot.getValue(), beginString, applVerID);
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
    public ApplicationSequenceControl getApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ApplicationSequenceControl component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void setApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the ApplicationSequenceControl component to null.
     */
    @FIXVersion(introduced = "5.0SP1")
    public void clearApplicationSequenceControl() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.TotNumReports)
    public Integer getTotNumReports() {
        return totNumReports;
    }

    /**
     * Message field setter.
     * @param totNumReports field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.TotNumReports)
    public void setTotNumReports(Integer totNumReports) {
        this.totNumReports = totNumReports;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.MDReqID)
    public String getMdReqID() {
        return mdReqID;
    }

    /**
     * Message field setter.
     * @param mdReqID field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.MDReqID)
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDReportID)
    public Integer getMdReportID() {
        return mdReportID;
    }

    /**
     * Message field setter.
     * @param mdReportID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDReportID)
    public void setMdReportID(Integer mdReportID) {
        this.mdReportID = mdReportID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.ClearingBusinessDate)
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    /**
     * Message field setter.
     * @param clearingBusinessDate field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.ClearingBusinessDate)
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDBookType)
    public MDBookType getMdBookType() {
        return mdBookType;
    }

    /**
     * Message field setter.
     * @param mdBookType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDBookType)
    public void setMdBookType(MDBookType mdBookType) {
        this.mdBookType = mdBookType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDSubBookType)
    public Integer getMdSubBookType() {
        return mdSubBookType;
    }

    /**
     * Message field setter.
     * @param mdSubBookType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDSubBookType)
    public void setMdSubBookType(Integer mdSubBookType) {
        this.mdSubBookType = mdSubBookType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MarketDepth)
    public Integer getMarketDepth() {
        return marketDepth;
    }

    /**
     * Message field setter.
     * @param marketDepth field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MarketDepth)
    public void setMarketDepth(Integer marketDepth) {
        this.marketDepth = marketDepth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDFeedType)
    public String getMdFeedType() {
        return mdFeedType;
    }

    /**
     * Message field setter.
     * @param mdFeedType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MDFeedType)
    public void setMdFeedType(String mdFeedType) {
        this.mdFeedType = mdFeedType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.RefreshIndicator)
    public Boolean getRefreshIndicator() {
        return refreshIndicator;
    }

    /**
     * Message field setter.
     * @param refreshIndicator field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.RefreshIndicator)
    public void setRefreshIndicator(Boolean refreshIndicator) {
        this.refreshIndicator = refreshIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.MDStreamID)
    public String getMdStreamID() {
        return mdStreamID;
    }

    /**
     * Message field setter.
     * @param mdStreamID field value
     */
    @FIXVersion(introduced = "5.0SP2")
    @TagNumRef(tagNum = TagNum.MDStreamID)
    public void setMdStreamID(String mdStreamID) {
        this.mdStreamID = mdStreamID;
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
     * Sets the field to the proper implementation instance.
     */
    @FIXVersion(introduced = "4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoUnderlyings)
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoUnderlyings)
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoLegs)
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoLegs)
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
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
    public String getSymbol() {
        return getSafeInstrument().getSymbol();
    }

    /**
     * Message field setter.
     * @param symbol field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Symbol, required = true)
    public void setSymbol(String symbol) {
       getSafeInstrument().setSymbol(symbol);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public String getSymbolSfx() {
        return getSafeInstrument().getSymbolSfx();
    }

    /**
     * Message field setter.
     * @param symbolSfx field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SymbolSfx)
    public void setSymbolSfx(String symbolSfx) {
        getSafeInstrument().setSymbolSfx(symbolSfx);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public String getSecurityID() {
        return getSafeInstrument().getSecurityID();
    }

    /**
     * Message field setter.
     * @param securityID field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityID)
    public void setSecurityID(String securityID) {
        getSafeInstrument().setSecurityID(securityID);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public String getSecurityIDSource() {
        return getSafeInstrument().getSecurityIDSource();
    }

    /**
     * Message field setter.
     * @param securityIDSource field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityIDSource)
    public void setSecurityIDSource(String securityIDSource) {
        getSafeInstrument().setSecurityIDSource(securityIDSource);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return getSafeInstrument().getSecurityType();
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        getSafeInstrument().setSecurityType(securityType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public String getMaturityMonthYear() {
        return getSafeInstrument().getMaturityMonthYear();
    }

    /**
     * Message field setter.
     * @param maturityMonthYear field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityMonthYear)
    public void setMaturityMonthYear(String maturityMonthYear) {
        getSafeInstrument().setMaturityMonthYear(maturityMonthYear);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public Integer getMaturityDay() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param maturityDay field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.MaturityDay)
    public void setMaturityDay(Integer maturityDay) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public PutOrCall getPutOrCall() {
        return getSafeInstrument().getPutOrCall();
    }

    /**
     * Message field setter.
     * @param putOrCall field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.PutOrCall)
    public void setPutOrCall(PutOrCall putOrCall) {
        getSafeInstrument().setPutOrCall(putOrCall);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public Double getStrikePrice() {
        return getSafeInstrument().getStrikePrice();
    }

    /**
     * Message field setter.
     * @param strikePrice field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.StrikePrice)
    public void setStrikePrice(Double strikePrice) {
        getSafeInstrument().setStrikePrice(strikePrice);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public Character getOptAttribute() {
        return getSafeInstrument().getOptAttribute();
    }

    /**
     * Message field setter.
     * @param optAttribute field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.OptAttribute)
    public void setOptAttribute(Character optAttribute) {
        getSafeInstrument().setOptAttribute(optAttribute);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public Double getContractMultiplier() {
        return getSafeInstrument().getContractMultiplier();
    }

    /**
     * Message field setter.
     * @param contractMultiplier field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.ContractMultiplier)
    public void setContractMultiplier(Double contractMultiplier) {
        getSafeInstrument().setContractMultiplier(contractMultiplier);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public Double getCouponRate() {
        return getSafeInstrument().getCouponRate();
    }

    /**
     * Message field setter.
     * @param couponRate field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.CouponRate)
    public void setCouponRate(Double couponRate) {
        getSafeInstrument().setCouponRate(couponRate);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public String getSecurityExchange() {
        return getSafeInstrument().getSecurityExchange();
    }

    /**
     * Message field setter.
     * @param securityExchange field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityExchange)
    public void setSecurityExchange(String securityExchange) {
        getSafeInstrument().setSecurityExchange(securityExchange);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public String getIssuer() {
        return getSafeInstrument().getIssuer();
    }

    /**
     * Message field setter.
     * @param issuer field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.Issuer)
    public void setIssuer(String issuer) {
        getSafeInstrument().setIssuer(issuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public Integer getEncodedIssuerLen() {
        return getSafeInstrument().getEncodedIssuerLen();
    }

    /**
     * Message field setter.
     * @param encodedIssuerLen field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuerLen)
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        getSafeInstrument().setEncodedIssuerLen(encodedIssuerLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public byte[] getEncodedIssuer() {
        return getSafeInstrument().getEncodedIssuer();
    }

    /**
     * Message field setter.
     * @param encodedIssuer field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedIssuer)
    public void setEncodedIssuer(byte[] encodedIssuer) {
        getSafeInstrument().setEncodedIssuer(encodedIssuer);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public String getSecurityDesc() {
        return getSafeInstrument().getSecurityDesc();
    }

    /**
     * Message field setter.
     * @param securityDesc field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.SecurityDesc)
    public void setSecurityDesc(String securityDesc) {
        getSafeInstrument().setSecurityDesc(securityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public Integer getEncodedSecurityDescLen() {
        return getSafeInstrument().getEncodedSecurityDescLen();
    }

    /**
     * Message field setter.
     * @param encodedSecurityDescLen field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDescLen)
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        getSafeInstrument().setEncodedSecurityDescLen(encodedSecurityDescLen);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public byte[] getEncodedSecurityDesc() {
        return getSafeInstrument().getEncodedSecurityDesc();
    }

    /**
     * Message field setter.
     * @param encodedSecurityDesc field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.3")
    @TagNumRef(tagNum = TagNum.EncodedSecurityDesc)
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        getSafeInstrument().setEncodedSecurityDesc(encodedSecurityDesc);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.FinancialStatus)
    public String getFinancialStatus() {
        return financialStatus;
    }

    /**
     * Message field setter.
     * @param financialStatus field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.FinancialStatus)
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.CorporateAction)
    public String getCorporateAction() {
        return corporateAction;
    }

    /**
     * Message field setter.
     * @param corporateAction field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.CorporateAction)
    public void setCorporateAction(String corporateAction) {
        this.corporateAction = corporateAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.4")
    @TagNumRef(tagNum = TagNum.TotalVolumeTraded)
    public Double getTotalVolumeTraded() {
        return totalVolumeTraded;
    }

    /**
     * Message field setter.
     * @param totalVolumeTraded field value
     */
    @FIXVersion(introduced = "4.2", retired = "4.4")
    @TagNumRef(tagNum = TagNum.TotalVolumeTraded)
    public void setTotalVolumeTraded(Double totalVolumeTraded) {
        this.totalVolumeTraded = totalVolumeTraded;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.TotalVolumeTradedDate)
    public Date getTotalVolumeTradedDate() {
        return totalVolumeTradedDate;
    }

    /**
     * Message field setter.
     * @param totalVolumeTradedDate field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.TotalVolumeTradedDate)
    public void setTotalVolumeTradedDate(Date totalVolumeTradedDate) {
        this.totalVolumeTradedDate = totalVolumeTradedDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.TotalVolumeTradedTime)
    public Date getTotalVolumeTradedTime() {
        return totalVolumeTradedTime;
    }

    /**
     * Message field setter.
     * @param totalVolumeTradedTime field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.TotalVolumeTradedTime)
    public void setTotalVolumeTradedTime(Date totalVolumeTradedTime) {
        this.totalVolumeTradedTime = totalVolumeTradedTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NetChgPrevDay)
    public Double getNetChgPrevDay() {
        return netChgPrevDay;
    }

    /**
     * Message field setter.
     * @param netChgPrevDay field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NetChgPrevDay)
    public void setNetChgPrevDay(Double netChgPrevDay) {
        this.netChgPrevDay = netChgPrevDay;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.NoMDEntries)
    public Integer getNoMDEntries() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MDFullGroup} groups. It will also create an array
     * of {@link MDFullGroup} objects and set the <code>mdFullGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>mdFullGroups</code> array they will be discarded.<br/>
     * @param noMDEntries field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.NoMDEntries)
    public void setNoMDEntries(Integer noMDEntries) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MDFullGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.2")
    public MDFullGroup[] getMdFullGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MDFullGroup} object to the existing array of <code>mdFullGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMDEntries</code> field to the proper value.<br/>
     * Note: If the <code>setNoMDEntries</code> method has been called there will already be a number of objects in the
     * <code>mdFullGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2")
    public MDFullGroup addMdFullGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MDFullGroup} object from the existing array of <code>mdFullGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMDEntries</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2")
    public MDFullGroup deleteMdFullGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MDFullGroup} objects from the <code>mdFullGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMDEntries</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2")
    public int clearMdFullGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueDepth)
    public Integer getApplQueueDepth() {
        return applQueueDepth;
    }

    /**
     * Message field setter.
     * @param applQueueDepth field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueDepth)
    public void setApplQueueDepth(Integer applQueueDepth) {
        this.applQueueDepth = applQueueDepth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueResolution)
    public ApplQueueResolution getApplQueueResolution() {
        return applQueueResolution;
    }

    /**
     * Message field setter.
     * @param applQueueResolution field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueResolution)
    public void setApplQueueResolution(ApplQueueResolution applQueueResolution) {
        this.applQueueResolution = applQueueResolution;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum=TagNum.NoRoutingIDs)
    public Integer getNoRoutingIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link RoutingIDGroup} groups. It will also create an array
     * of {@link RoutingIDGroup} objects and set the <code>routingIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>routingIDGroups</code> array they will be discarded.<br/>
     * @param noRoutingIDs field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum=TagNum.NoRoutingIDs)
    public void setNoRoutingIDs(Integer noRoutingIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RoutingIDGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    public RoutingIDGroup[] getRoutingIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link RoutingIDGroup} object to the existing array of <code>routingIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRoutingIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoRoutingIDs</code> method has been called there will already be a number of objects in the
     * <code>routingIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "5.0")
    public RoutingIDGroup addRoutingIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RoutingIDGroup} object from the existing array of <code>routingIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRoutingIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "5.0")
    public RoutingIDGroup deleteRoutingIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link RoutingIDGroup} objects from the <code>routingIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRoutingIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "5.0")
    public int clearRoutingIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (instrument == null || instrument.getSymbol() == null || instrument.getSymbol().trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (noMDEntries == null) {
            errorMsg.append(" [NoMDEntries]");
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
            TagEncoder.encode(bao, TagNum.TotNumReports, totNumReports);
            TagEncoder.encode(bao, TagNum.MDReqID, mdReqID);
            TagEncoder.encode(bao, TagNum.MDReportID, mdReportID);
            TagEncoder.encodeDate(bao, TagNum.ClearingBusinessDate, clearingBusinessDate);
            if (mdBookType != null) {
                TagEncoder.encode(bao, TagNum.MDBookType, mdBookType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MDSubBookType, mdSubBookType);
            TagEncoder.encode(bao, TagNum.MarketDepth, marketDepth);
            TagEncoder.encode(bao, TagNum.MDFeedType, mdFeedType);
            TagEncoder.encode(bao, TagNum.RefreshIndicator, refreshIndicator);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encode(bao, TagNum.MDStreamID, mdStreamID);
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noLegs.intValue()) {
                    for (UnderlyingInstrument underlyingInstrument : underlyingInstruments) {
                        bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error =
                            "UnderlyingInstruments field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null && noLegs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (InstrumentLeg instrumentLeg : instrumentLegs) {
                        bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
                    }
                } else {
                    String error =
                            "InstrumentLegs field has been set but there is no data or the number of components does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Symbol, symbol);
            TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx);
            TagEncoder.encode(bao, TagNum.SecurityID, securityID);
            TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource);
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear);
            TagEncoder.encode(bao, TagNum.MaturityDay, maturityDay);
            if (putOrCall != null) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            TagEncoder.encode(bao, TagNum.Issuer, issuer);
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
            TagEncoder.encode(bao, TagNum.FinancialStatus, financialStatus);
            TagEncoder.encode(bao, TagNum.CorporateAction, corporateAction);
            TagEncoder.encode(bao, TagNum.TotalVolumeTraded, totalVolumeTraded);
            TagEncoder.encodeUtcDate(bao, TagNum.TotalVolumeTradedDate, totalVolumeTradedDate);
            TagEncoder.encodeUTCTime(bao, TagNum.TotalVolumeTradedTime, totalVolumeTradedTime);
            TagEncoder.encode(bao, TagNum.NetChgPrevDay, netChgPrevDay);
            if (noMDEntries != null) {
                TagEncoder.encode(bao, TagNum.NoMDEntries, noMDEntries);
                if (mdFullGroups != null && mdFullGroups.length == noMDEntries.intValue()) {
                    for (int i = 0; i < noMDEntries.intValue(); i++) {
                        if (mdFullGroups[i] != null) {
                            bao.write(mdFullGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MDFullGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMDEntries.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.ApplQueueDepth, applQueueDepth);
            if (applQueueResolution != null) {
                TagEncoder.encode(bao, TagNum.ApplQueueResolution, applQueueResolution.getValue());
            }
            if (noRoutingIDs != null) {
                TagEncoder.encode(bao, TagNum.NoRoutingIDs, noRoutingIDs);
                if (routingIDGroups != null && routingIDGroups.length == noRoutingIDs.intValue()) {
                    for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                        if (routingIDGroups[i] != null) {
                            bao.write(routingIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "RoutingIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRoutingIDs.getValue(), error);
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
            case TotNumReports:
                totNumReports = new Integer(new String(tag.value, sessionCharset));
                break;

            case MDReqID:
                mdReqID = new String(tag.value, sessionCharset);
                break;

            case MDReportID:
                mdReportID = new Integer(new String(tag.value, sessionCharset));
                break;

            case ClearingBusinessDate:
                clearingBusinessDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MDBookType:
                mdBookType = MDBookType.valueFor(Integer.parseInt(new String(tag.value, sessionCharset)));
                break;

            case MDSubBookType:
                mdSubBookType = new Integer(new String(tag.value, sessionCharset));
                break;

            case MarketDepth:
                marketDepth = new Integer(new String(tag.value, sessionCharset));
                break;

            case MDFeedType:
                mdFeedType = new String(tag.value, sessionCharset);
                break;

            case RefreshIndicator:
                refreshIndicator = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MDStreamID:
                mdStreamID = new String(tag.value, sessionCharset);
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, sessionCharset));
                break;

            case Symbol:
                symbol = new String(tag.value, sessionCharset);
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

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case MaturityMonthYear:
                maturityMonthYear = new String(tag.value, sessionCharset);
                break;

            case MaturityDay:
                maturityDay = new Integer(new String(tag.value, sessionCharset));
                break;

            case PutOrCall:
                putOrCall = PutOrCall.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StrikePrice:
                strikePrice = new Double(new String(tag.value, sessionCharset));
                break;

            case OptAttribute:
                optAttribute = new Character(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ContractMultiplier:
                contractMultiplier = new Double(new String(tag.value, sessionCharset));
                break;

            case CouponRate:
                couponRate = new Double(new String(tag.value, sessionCharset));
                break;

            case SecurityExchange:
                securityExchange = new String(tag.value, sessionCharset);
                break;

            case Issuer:
                issuer = new String(tag.value, sessionCharset);
                break;

            case SecurityDesc:
                securityDesc = new String(tag.value, sessionCharset);
                break;

            case FinancialStatus:
                financialStatus = new String(tag.value, sessionCharset);
                break;

            case CorporateAction:
                corporateAction = new String(tag.value, sessionCharset);
                break;

            case TotalVolumeTraded:
                totalVolumeTraded = new Double(new String(tag.value, sessionCharset));
                break;

            case TotalVolumeTradedDate:
                totalVolumeTradedDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TotalVolumeTradedTime:
                totalVolumeTradedTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case NetChgPrevDay:
                netChgPrevDay = new Double(new String(tag.value, sessionCharset));
                break;

            case NoMDEntries:
                noMDEntries = new Integer(new String(tag.value, sessionCharset));
                break;

            case ApplQueueDepth:
                applQueueDepth = new Integer(new String(tag.value, sessionCharset));
                break;

            case ApplQueueResolution:
                applQueueResolution = ApplQueueResolution.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoRoutingIDs:
                noRoutingIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in MarketDataSnapshot.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private Instrument getSafeInstrument() {
        if (getInstrument() == null) {
            setInstrument();
        }

        return getInstrument();
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{MarketDataSnapshotMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.TotNumReports, totNumReports);
        printTagValue(b, TagNum.MDReqID, mdReqID);
        printTagValue(b, TagNum.MDReportID, mdReportID);
        printDateTagValue(b, TagNum.ClearingBusinessDate, clearingBusinessDate);
        printTagValue(b, TagNum.MDBookType, mdBookType);
        printTagValue(b, TagNum.MDSubBookType, mdSubBookType);
        printTagValue(b, TagNum.MarketDepth, marketDepth);
        printTagValue(b, TagNum.MDFeedType, mdFeedType);
        printTagValue(b, TagNum.RefreshIndicator, refreshIndicator);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printTagValue(b, TagNum.MDStreamID, mdStreamID);
        printTagValue(b, instrument);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.Symbol, symbol);
        printTagValue(b, TagNum.SymbolSfx, symbolSfx);
        printTagValue(b, TagNum.SecurityID, securityID);
        printTagValue(b, TagNum.SecurityIDSource, securityIDSource);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.MaturityMonthYear, maturityMonthYear);
        printTagValue(b, TagNum.MaturityDay, maturityDay);
        printTagValue(b, TagNum.PutOrCall, putOrCall);
        printTagValue(b, TagNum.StrikePrice, strikePrice);
        printTagValue(b, TagNum.OptAttribute, optAttribute);
        printTagValue(b, TagNum.ContractMultiplier, contractMultiplier);
        printTagValue(b, TagNum.CouponRate, couponRate);
        printTagValue(b, TagNum.SecurityExchange, securityExchange);
        printTagValue(b, TagNum.Issuer, issuer);
        printTagValue(b, TagNum.EncodedIssuerLen, encodedIssuerLen);
        printTagValue(b, TagNum.EncodedIssuer, encodedIssuer);
        printTagValue(b, TagNum.EncodedSecurityDescLen, encodedSecurityDescLen);
        printTagValue(b, TagNum.EncodedSecurityDesc, encodedSecurityDesc);
        printTagValue(b, TagNum.FinancialStatus, financialStatus);
        printTagValue(b, TagNum.CorporateAction, corporateAction);
        printTagValue(b, TagNum.TotalVolumeTraded, totalVolumeTraded);
        printDateTagValue(b, TagNum.TotalVolumeTradedDate, totalVolumeTradedDate);
        printUTCTimeTagValue(b, TagNum.TotalVolumeTradedTime, totalVolumeTradedTime);
        printTagValue(b, TagNum.NetChgPrevDay, netChgPrevDay);
        printTagValue(b, TagNum.NoMDEntries, noMDEntries);
        printTagValue(b, mdFullGroups);
        printTagValue(b, TagNum.ApplQueueDepth, applQueueDepth);
        printTagValue(b, TagNum.ApplQueueResolution, applQueueResolution);
        printTagValue(b, TagNum.NoRoutingIDs, noRoutingIDs);
        printTagValue(b, routingIDGroups);

        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
