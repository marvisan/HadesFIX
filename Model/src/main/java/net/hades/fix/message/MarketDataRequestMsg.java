/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg.java
 *
 * $Id: MarketDataRequestMsg.java,v 1.11 2011-04-28 10:07:46 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDEntryTypeGroup;
import net.hades.fix.message.group.MDReqGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.type.ApplQueueAction;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDQuoteType;
import net.hades.fix.message.type.MDUpdateType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TagNum;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Some systems allow the transmission of real-time quote, order, trade, trade volume,
 * open interest, and/or other price information on a subscription basis.
 * A Market Data Request is a general request for market data on specific securities or forex quotes.<br/>
 * A successful Market Data Request returns one or more Market Data messages containing one or
 * more Market Data Entries. Each Market Data Entry is a Bid, an Offer, a Trade associated
 * with a security, the opening, closing, or settlement price of a security, the buyer or
 * seller imbalance for a security, the value of an index, the trading session high price,
 * low price, or VWAP, or the trade volume or open interest in a security.
 * Market Data Entries usually have a price and a quantity associated with them. For example,
 * in an order book environment, requesting just the top of book will result in only two active
 * Market Data Entries at a time – one for the best Bid and one for the best Offer.
 * For a full book, the Bid and Offer side may each have several Market Data Entries.
 * Each Market Data Entry might represent an aggregate for each price tier, and only one
 * Market Data Entry per side per price would be active at a time.
 * This is referred to as an Aggregated book. When several Market Data Entries at one price
 * tier could each represent a broker, Market Maker, ECN or Exchange’s quote in a
 * security, or individual orders in a book, this is a Non-Aggregated book. Alternately,
 * a Market Data Entry could represent a completed trade in a security, the value of an index,
 * the opening, closing, or settlement price of an instrument, the trading session high price,
 * low price, or VWAP, or the volume traded or open interest in a security.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MarketDataRequestMsg extends FIXMsg  {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3820108386460314532L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDReqID.getValue(),
        TagNum.SubscriptionRequestType.getValue(),
        TagNum.MarketDepth.getValue(),
        TagNum.MDUpdateType.getValue(),
        TagNum.AggregatedBook.getValue(),
        TagNum.OpenCloseSettlFlag.getValue(),
        TagNum.Scope.getValue(),
        TagNum.MDImplicitDelete.getValue(),
        TagNum.NoMDEntryTypes.getValue(),
        TagNum.NoRelatedSym.getValue(),
        TagNum.NoTradingSessions.getValue(),
        TagNum.ApplQueueAction.getValue(),
        TagNum.ApplQueueMax.getValue(),
        TagNum.MDQuoteType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MDReqID.getValue(),
        TagNum.SubscriptionRequestType.getValue(),
        TagNum.MarketDepth.getValue(),
        TagNum.NoMDEntryTypes.getValue(),
        TagNum.NoRelatedSym.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 262 REQUIRED. Starting with 4.2 version.
     */
    protected String mdReqID;

    /**
     * TagNum = 263 REQUIRED. Starting with 4.2 version.
     */
    protected SubscriptionRequestType subscriptionRequestType;

    /**
     * Starting with 5.0SP1 version.
     */
    protected Parties parties;

    /**
     * TagNum = 264 REQUIRED. Starting with 4.2 version.
     */
    protected Integer marketDepth;

    /**
     * TagNum = 265. Starting with 4.2 version.
     */
    protected MDUpdateType mdUpdateType;

    /**
     * TagNum = 266. Starting with 4.2 version.
     */
    protected Boolean aggregatedBook;

    /**
     * TagNum = 286. Starting with 4.3 version.
     */
    protected String openCloseSettlFlag;

    /**
     * TagNum = 546. Starting with 4.3 version.
     */
    protected String scope;

    /**
     * TagNum = 547. Starting with 4.3 version.
     */
    protected Boolean mdImplicitDelete;

    /**
     * TagNum = 267 REQUIRED. Starting with 4.2 version.
     */
    protected Integer noMDEntryTypes;

    /**
     * Starting with 4.2 version.
     */
    protected MDEntryTypeGroup[] mdEntryTypeGroups;

    /**
     * TagNum = 146. Starting with 4.2 version.
     */
    protected Integer noRelatedSym;

    /**
     * Starting with 4.2 version.
     */
    protected MDReqGroup[] mdReqGroups;

    /**
     * TagNum = 386. Starting with 4.3 version.
     */
    protected Integer noTradingSessions;

    /**
     * Starting with 4.3 version.
     */
    protected TradingSessionGroup[] tradingSessionGroups;

    /**
     * TagNum = 815. Starting with 4.4 version.
     */
    protected ApplQueueAction applQueueAction;

    /**
     * TagNum = 812. Starting with 4.4 version.
     */
    protected Integer applQueueMax;

    /**
     * TagNum = 1070. Starting with 5.0 version.
     */
    protected MDQuoteType mdQuoteType;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataRequestMsg() {
        super();
    }
    
    public MarketDataRequestMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.MarketDataRequest.getValue(), beginString);
    }
    
    public MarketDataRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.MarketDataRequest.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.MDReqID, required=true)
    public String getMdReqID() {
        return mdReqID;
    }

    /**
     * Message field setter.
     * @param mdReqID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDReqID, required=true)
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType, required=true)
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    /**
     * Message field setter.
     * @param subscriptionRequestType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType, required=true)
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MarketDepth, required=true)
    public Integer getMarketDepth() {
        return marketDepth;
    }

    /**
     * Message field setter.
     * @param marketDepth field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MarketDepth, required=true)
    public void setMarketDepth(Integer marketDepth) {
        this.marketDepth = marketDepth;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDUpdateType)
    public MDUpdateType getMdUpdateType() {
        return mdUpdateType;
    }

    /**
     * Message field setter.
     * @param mdUpdateType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MDUpdateType)
    public void setMdUpdateType(MDUpdateType mdUpdateType) {
        this.mdUpdateType = mdUpdateType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.AggregatedBook)
    public Boolean getAggregatedBook() {
        return aggregatedBook;
    }

    /**
     * Message field setter.
     * @param aggregatedBook field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.AggregatedBook)
    public void setAggregatedBook(Boolean aggregatedBook) {
        this.aggregatedBook = aggregatedBook;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OpenCloseSettlFlag)
    public String getOpenCloseSettlFlag() {
        return openCloseSettlFlag;
    }

    /**
     * Message field setter.
     * @param openCloseSettlFlag field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OpenCloseSettlFlag)
    public void setOpenCloseSettlFlag(String openCloseSettlFlag) {
        this.openCloseSettlFlag = openCloseSettlFlag;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Scope)
    public String getScope() {
        return scope;
    }

    /**
     * Message field setter.
     * @param scope field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Scope)
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MDImplicitDelete)
    public Boolean getMdImplicitDelete() {
        return mdImplicitDelete;
    }

    /**
     * Message field setter.
     * @param mdImplicitDelete field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MDImplicitDelete)
    public void setMdImplicitDelete(Boolean mdImplicitDelete) {
        this.mdImplicitDelete = mdImplicitDelete;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoMDEntryTypes, required=true)
    public Integer getNoMDEntryTypes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MDEntryTypeGroup} components. It will also create an array
     * of {@link net.hades.fix.message.comp.UnderlyingInstrument} objects and set the <code>mdEntryTypeGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>mdEntryTypeGroups</code> array they will be discarded.<br/>
     * @param noMDEntryTypes number of objects
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoMDEntryTypes, required=true)
    public void setNoMDEntryTypes(Integer noMDEntryTypes) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MDEntryTypeGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public MDEntryTypeGroup[] getMdEntryTypeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MDEntryTypeGroup} object to the existing array of <code>mdEntryTypeGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMDEntryTypes</code> field to the proper value.<br/>
     * Note: If the <code>setNoMDEntryTypes</code> method has been called there will already be a number of objects in the
     * <code>mdEntryTypeGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public MDEntryTypeGroup addMdEntryTypeGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MDEntryTypeGroup} object from the existing array of <code>mdEntryTypeGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMDEntryTypes</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public MDEntryTypeGroup deleteMdEntryTypeGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MDEntryTypeGroup} objects from the <code>mdEntryTypeGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMDEntryTypes</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearMdEntryTypeGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoRelatedSym, required=true)
    public Integer getNoRelatedSym() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MDReqGroup} components. It will also create an array
     * of {@link MDReqGroup} objects and set the <code>mdReqGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>mdReqGroups</code> array they will be discarded.<br/>
     * @param noRelatedSym number of objects
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoRelatedSym, required=true)
    public void setNoRelatedSym(Integer noRelatedSym) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MDReqGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public MDReqGroup[] getMdReqGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MDReqGroup} object to the existing array of <code>mdReqGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRelatedSym</code> field to the proper value.<br/>
     * Note: If the <code>setNoRelatedSym</code> method has been called there will already be a number of objects in the
     * <code>mdReqGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public MDReqGroup addMdReqGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MDReqGroup} object from the existing array of <code>mdReqGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRelatedSym</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public MDReqGroup deleteMdReqGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MDReqGroup} objects from the <code>mdReqGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRelatedSym</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearMdReqGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
     * This method sets the number of {@link TradingSessionGroup} components. It will also create an array
     * of {@link TradingSessionGroup} objects and set the <code>tradingSessionGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>tradingSessionGroups</code> array they will be discarded.<br/>
     * @param noTradingSessions number of objects
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoTradingSessions)
    public void setNoTradingSessions(Integer noTradingSessions) {
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ApplQueueAction)
    public ApplQueueAction getApplQueueAction() {
        return applQueueAction;
    }

    /**
     * Message field setter.
     * @param applQueueAction field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ApplQueueAction)
    public void setApplQueueAction(ApplQueueAction applQueueAction) {
        this.applQueueAction = applQueueAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ApplQueueMax)
    public Integer getApplQueueMax() {
        return applQueueMax;
    }

    /**
     * Message field setter.
     * @param applQueueMax field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ApplQueueMax)
    public void setApplQueueMax(Integer applQueueMax) {
        this.applQueueMax = applQueueMax;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDQuoteType)
    public MDQuoteType getMdQuoteType() {
        return mdQuoteType;
    }

    /**
     * Message field setter.
     * @param mdQuoteType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.MDQuoteType)
    public void setMdQuoteType(MDQuoteType mdQuoteType) {
        this.mdQuoteType = mdQuoteType;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (mdReqID == null || mdReqID.trim().isEmpty()) {
            errorMsg.append(" [MDReqID]");
            hasMissingTag = true;
        }
        if (subscriptionRequestType == null) {
            errorMsg.append(" [SubscriptionRequestType]");
            hasMissingTag = true;
        }
        if (marketDepth == null) {
            errorMsg.append(" [MarketDepth]");
            hasMissingTag = true;
        }
        if (noMDEntryTypes == null) {
            errorMsg.append(" [NoMDEntryTypes]");
            hasMissingTag = true;
        }
        if (noRelatedSym == null) {
            errorMsg.append(" [NoRelatedSym]");
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
            TagEncoder.encode(bao, TagNum.MDReqID, mdReqID);
            if (subscriptionRequestType != null) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.MarketDepth, marketDepth);
            if (mdUpdateType != null) {
                TagEncoder.encode(bao, TagNum.MDUpdateType, mdUpdateType.getValue());
            }
            TagEncoder.encode(bao, TagNum.AggregatedBook, aggregatedBook);
            TagEncoder.encode(bao, TagNum.OpenCloseSettlFlag, openCloseSettlFlag);
            TagEncoder.encode(bao, TagNum.Scope, scope);
            TagEncoder.encode(bao, TagNum.MDImplicitDelete, mdImplicitDelete);
            if (noMDEntryTypes != null) {
                TagEncoder.encode(bao, TagNum.NoMDEntryTypes, noMDEntryTypes);
                if (mdEntryTypeGroups != null && mdEntryTypeGroups.length == noMDEntryTypes.intValue()) {
                    for (int i = 0; i < noMDEntryTypes.intValue(); i++) {
                        if (mdEntryTypeGroups[i] != null) {
                            bao.write(mdEntryTypeGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MDEntryTypeGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMDEntryTypes.getValue(), error);
                }
            }
            if (noRelatedSym != null) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSym);
                if (mdReqGroups != null && mdReqGroups.length == noRelatedSym.intValue()) {
                    for (int i = 0; i < noRelatedSym.intValue(); i++) {
                        if (mdReqGroups[i] != null) {
                            bao.write(mdReqGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MDReqGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoRelatedSym.getValue(), error);
                }
            }
            if (noTradingSessions != null) {
                TagEncoder.encode(bao, TagNum.NoTradingSessions, noTradingSessions);
                if (tradingSessionGroups != null && tradingSessionGroups.length == noTradingSessions.intValue()) {
                    for (int i = 0; i < noTradingSessions.intValue(); i++) {
                        if (tradingSessionGroups[i] != null) {
                            bao.write(tradingSessionGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "TradingSessionGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoTradingSessions.getValue(), error);
                }
            }
            if (applQueueAction != null) {
                TagEncoder.encode(bao, TagNum.ApplQueueAction, applQueueAction.getValue());
            }
            TagEncoder.encode(bao, TagNum.ApplQueueMax, applQueueMax);
            if (mdQuoteType != null) {
                TagEncoder.encode(bao, TagNum.MDQuoteType, mdQuoteType.getValue());
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
            case MDReqID:
                mdReqID = new String(tag.value, sessionCharset);
                break;

            case SubscriptionRequestType:
                subscriptionRequestType = SubscriptionRequestType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case MarketDepth:
                marketDepth = new Integer(new String(tag.value, sessionCharset));
                break;

            case MDUpdateType:
                mdUpdateType = MDUpdateType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AggregatedBook:
                aggregatedBook = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case OpenCloseSettlFlag:
                openCloseSettlFlag = new String(tag.value, sessionCharset);
                break;

            case Scope:
                scope = new String(tag.value, sessionCharset);
                break;

            case MDImplicitDelete:
                mdImplicitDelete = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoMDEntryTypes:
                noMDEntryTypes = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoRelatedSym:
                noRelatedSym = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoTradingSessions:
                noTradingSessions = new Integer(new String(tag.value, sessionCharset));
                break;

            case ApplQueueAction:
                applQueueAction = ApplQueueAction.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ApplQueueMax:
                applQueueMax = new Integer(new String(tag.value, sessionCharset));
                break;
                
            case MDQuoteType:
                mdQuoteType = MDQuoteType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in MarketDataRequest.";
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
        StringBuilder b = new StringBuilder("{MarketDataRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.MDReqID, mdReqID);
        printTagValue(b, TagNum.SubscriptionRequestType, subscriptionRequestType);
        printTagValue(b, parties);
        printTagValue(b, TagNum.MarketDepth, marketDepth);
        printTagValue(b, TagNum.MDUpdateType, mdUpdateType);
        printTagValue(b, TagNum.AggregatedBook, aggregatedBook);
        printTagValue(b, TagNum.OpenCloseSettlFlag, openCloseSettlFlag);
        printTagValue(b, TagNum.Scope, scope);
        printTagValue(b, TagNum.MDImplicitDelete, mdImplicitDelete);
        printTagValue(b, TagNum.NoMDEntryTypes, noMDEntryTypes);
        printTagValue(b, mdEntryTypeGroups);
        printTagValue(b, TagNum.NoRelatedSym, noRelatedSym);
        printTagValue(b, mdReqGroups);
        printTagValue(b, TagNum.NoTradingSessions, noTradingSessions);
        printTagValue(b, tradingSessionGroups);
        printTagValue(b, TagNum.ApplQueueAction, applQueueAction);
        printTagValue(b, TagNum.ApplQueueMax, applQueueMax);
        printTagValue(b, TagNum.MDQuoteType, mdQuoteType);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
}
