/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrMsg.java
 *
 * $Id: MarketDataIncrRefreshMsg.java,v 1.10 2011-04-28 10:07:45 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.MDIncGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.struct.Tag;
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
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplQueueAction;
import net.hades.fix.message.type.ApplQueueResolution;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The second Market Data message format is used for incremental updates. Market Data Entries may
 * have an MDEntryID unique among all currently active Market Data Entries so they can be referenced
 * for the purposes of deleting and changing them later. When changing a Market Data Entry, it may
 * keep the same MDEntryID, in which case only MDEntryID would be populated, or the MDEntryID may
 * change, in which case MDEntryID will contain the new ID, and MDEntryRefID will contain the ID
 * of the Market Data Entry being changed. An MDEntryID can be reused within a day only if it has
 * first been deleted.
 * Alternately, in the case of displaying the best quotes of Market Makers or Exchanges, and not
 * orders in an order book, MDEntryID can be omitted for simplification. In this case, a New Market
 * Data Entry will replace the previous best quote for that side and symbol for the specified Market
 * Maker or Exchange. Deletion of a Market Data Entry would not specify an MDEntryID or MDRefID,
 * and would remove the most recent Market Data Entry for the specified symbol, side, and Market Maker
 * or Exchange. A Change of a Market Data Entry would not specify an MDEntryID or MDRefID, and
 * would replace the most recent Market Data Entry for the specified symbol, side, and Market Maker or Exchange.
 * The Market Data message for incremental updates may contain any combination of new, changed, or
 * deleted Market Data Entries, for any combination of instruments, with any combination of trades,
 * imbalances, quotes, index values, open, close, settlement, high, low, and VWAP prices, trade
 * volume and open interest so long as the maximum FIX message size is not exceeded. All of these
 * types of Market Data Entries can be changed and deleted.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MarketDataIncrRefreshMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
            TagNum.MDBookType.getValue(),
            TagNum.MDFeedType.getValue(),
            TagNum.TradeDate.getValue(),
            TagNum.MDReqID.getValue(),
            TagNum.NoMDEntries.getValue(),
            TagNum.ApplQueueDepth.getValue(),
            TagNum.ApplQueueResolution.getValue(),
            TagNum.ApplQueueAction.getValue(),
            TagNum.ApplQueueMax.getValue(),
            TagNum.NoRoutingIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>();

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
     * TagNum = 1021. Starting with 5.0 version.
     */
    protected MDBookType mdBookType;

    /**
     * TagNum = 1022. Starting with 5.0 version.
     */
    protected String mdFeedType;

    /**
     * TagNum = 75. Starting with 5.0 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 262. Starting with 4.2 version.
     */
    protected String mdReqID;

    /**
     * TagNum = 268. Starting with 4.2 version.
     */
    protected Integer noMDEntries;

    /**
     * Starting with 4.2 version.
     */
    protected MDIncGroup[] mdIncGroups;

    /**
     * TagNum = 813. Starting with 5.0 version.
     */
    protected Integer applQueueDepth;

    /**
     * TagNum = 814. Starting with 5.0 version.
     */
    protected ApplQueueResolution applQueueResolution;

    /**
     * TagNum = 815. Starting with 4.4 version.
     */
    protected ApplQueueAction applQueueAction;

    /**
     * TagNum = 812. Starting with 4.4 version.
     */
    protected Integer applQueueMax;

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
    public MarketDataIncrRefreshMsg() {
        super();
    }

    public MarketDataIncrRefreshMsg(Header header, ByteBuffer rawMsg)
            throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataIncrRefreshMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.MarketDataIncrRefresh.getValue(), beginString);
    }

    public MarketDataIncrRefreshMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.MarketDataIncrRefresh.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDFeedType)
    public String getMdFeedType() {
        return mdFeedType;
    }

    /**
     * Message field setter.
     * @param mdFeedType field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.MDFeedType)
    public void setMdFeedType(String mdFeedType) {
        this.mdFeedType = mdFeedType;
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
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.NoMDEntries)
    public Integer getNoMDEntries() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link MDIncGroup} groups. It will also create an array
     * of {@link MDIncGroup} objects and set the <code>mdIncGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>mdIncGroups</code> array they will be discarded.<br/>
     * @param noMDEntries field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.NoMDEntries)
    public void setNoMDEntries(Integer noMDEntries) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link MDIncGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.2")
    public MDIncGroup[] getMDIncGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link MDIncGroup} object to the existing array of <code>mdIncGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noMDEntries</code> field to the proper value.<br/>
     * Note: If the <code>setNoMDEntries</code> method has been called there will already be a number of objects in the
     * <code>mdIncGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2")
    public MDIncGroup addMDIncGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link MDIncGroup} object from the existing array of <code>mdIncGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noMDEntries</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2")
    public MDIncGroup deleteMDIncGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link MDIncGroup} objects from the <code>mdIncGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noMDEntries</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2")
    public int clearMDIncGroups() {
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
    @FIXVersion(introduced = "4.4", retired="5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueAction)
    public ApplQueueAction getApplQueueAction() {
        return applQueueAction;
    }

    /**
     * Message field setter.
     * @param applQueueAction field value
     */
    @FIXVersion(introduced = "4.4", retired="5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueAction)
    public void setApplQueueAction(ApplQueueAction applQueueAction) {
        this.applQueueAction = applQueueAction;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4", retired="5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueMax)
    public Integer getApplQueueMax() {
        return applQueueMax;
    }

    /**
     * Message field setter.
     * @param applQueueMax field value
     */
    @FIXVersion(introduced = "4.4", retired="5.0")
    @TagNumRef(tagNum = TagNum.ApplQueueMax)
    public void setApplQueueMax(Integer applQueueMax) {
        this.applQueueMax = applQueueMax;
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
            if (mdBookType != null) {
                TagEncoder.encode(bao, TagNum.MDBookType, mdBookType.getValue());
            }
            TagEncoder.encode(bao, TagNum.MDFeedType, mdFeedType);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encode(bao, TagNum.MDReqID, mdReqID);
            if (noMDEntries != null) {
                TagEncoder.encode(bao, TagNum.NoMDEntries, noMDEntries);
                if (mdIncGroups != null && mdIncGroups.length == noMDEntries.intValue()) {
                    for (int i = 0; i < noMDEntries.intValue(); i++) {
                        if (mdIncGroups[i] != null) {
                            bao.write(mdIncGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "MDIncGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMDEntries.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.ApplQueueDepth, applQueueDepth);
            if (applQueueResolution != null) {
                TagEncoder.encode(bao, TagNum.ApplQueueResolution, applQueueResolution.getValue());
            }
            if (applQueueAction != null) {
                TagEncoder.encode(bao, TagNum.ApplQueueAction, applQueueAction.getValue());
            }
            TagEncoder.encode(bao, TagNum.ApplQueueMax, applQueueMax);
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
            case MDBookType:
                mdBookType = MDBookType.valueFor(Integer.parseInt(new String(tag.value, sessionCharset)));
                break;

            case MDFeedType:
                mdFeedType = new String(tag.value, sessionCharset);
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MDReqID:
                mdReqID = new String(tag.value, sessionCharset);
                break;

            case NoMDEntries:
                noMDEntries = new Integer(new String(tag.value, sessionCharset));
                break;

            case ApplQueueDepth:
                applQueueDepth = new Integer(new String(tag.value, sessionCharset));
                break;

            case ApplQueueResolution:
                applQueueResolution = ApplQueueResolution.valueFor(Integer.parseInt(new String(tag.value)));
                break;

            case ApplQueueAction:
                applQueueAction = ApplQueueAction.valueFor(Integer.parseInt(new String(tag.value)));
                break;

            case ApplQueueMax:
                applQueueMax = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoRoutingIDs:
                noRoutingIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in MarketDataIncrRefresh.";
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{MarketDataIncrRefreshMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.MDBookType, mdBookType);
        printTagValue(b, TagNum.MDFeedType, mdFeedType);
         printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printTagValue(b, TagNum.MDReqID, mdReqID);
        printTagValue(b, TagNum.NoMDEntries, noMDEntries);
        printTagValue(b, mdIncGroups);
        printTagValue(b, TagNum.ApplQueueDepth, applQueueDepth);
        printTagValue(b, TagNum.ApplQueueResolution, applQueueResolution);
        printTagValue(b, TagNum.ApplQueueAction, applQueueAction);
        printTagValue(b, TagNum.ApplQueueMax, applQueueMax);
        printTagValue(b, TagNum.NoRoutingIDs, noRoutingIDs);
        printTagValue(b, routingIDGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
