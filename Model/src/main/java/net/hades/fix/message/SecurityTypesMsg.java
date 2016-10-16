/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypesMsg.java
 *
 * $Id: SecurityTypesMsg.java,v 1.2 2011-04-28 10:07:44 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SecTypesGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SecurityResponseType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Security Type message is used to return a list of security types available from a counterparty or market.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SecurityTypesMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityReqID.getValue(),
        TagNum.SecurityResponseID.getValue(),
        TagNum.SecurityResponseType.getValue(),
        TagNum.TotNoSecurityTypes.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoSecurityTypes.getValue(),
        TagNum.Text.getValue(),
        TagNum.MarketID.getValue(),
        TagNum.MarketSegmentID.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.SubscriptionRequestType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecurityReqID.getValue(),
        TagNum.SecurityResponseID.getValue(),
        TagNum.SecurityResponseType.getValue()
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
     * TagNum = 330 REQUIRED. Starting with 4.3 version.
     */
    protected String securityReqID;

    /**
     * TagNum = 321 REQUIRED. Starting with 4.3 version.
     */
    protected String securityResponseID;

    /**
     * TagNum = 321 REQUIRED. Starting with 4.3 version.
     */
    protected SecurityResponseType securityResponseType;

    /**
     * TagNum = 557. Starting with 4.3 version.
     */
    protected Integer totNoSecurityTypes;

    /**
     * TagNum = 893. Starting with 4.4 version.
     */
    protected Boolean lastFragment;

    /**
     * TagNum = 558. Starting with 4.3 version.
     */
    protected Integer noSecurityTypes;

    /**
     * Starting with 4.3 version.
     */
    protected SecTypesGroup[] secTypesGroups;

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

    /**
     * TagNum = 1301. Starting with 5.0SP1 version.
     */
    protected String marketID;

    /**
     * TagNum = 1300. Starting with 5.0SP1 version.
     */
    protected String marketSegmentID;

    /**
     * TagNum = 336. Starting with 4.3 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 263. Starting with 4.3 version.
     */
    protected SubscriptionRequestType subscriptionRequestType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityTypesMsg() {
        super();
    }

    public SecurityTypesMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SecurityTypesMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.SecurityTypes.getValue(), beginString);
    }

    public SecurityTypesMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.SecurityTypes.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityReqID, required=true)
    public String getSecurityReqID() {
        return securityReqID;
    }

    /**
     * Message field setter.
     * @param securityReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityReqID, required=true)
    public void setSecurityReqID(String securityReqID) {
        this.securityReqID = securityReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityResponseID, required=true)
    public String getSecurityResponseID() {
        return securityResponseID;
    }

    /**
     * Message field setter.
     * @param securityResponseID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityResponseID, required=true)
    public void setSecurityResponseID(String securityResponseID) {
        this.securityResponseID = securityResponseID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityResponseType, required=true)
    public SecurityResponseType getSecurityResponseType() {
        return securityResponseType;
    }

    /**
     * Message field setter.
     * @param securityResponseType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecurityResponseType, required=true)
    public void setSecurityResponseType(SecurityResponseType securityResponseType) {
        this.securityResponseType = securityResponseType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TotNoSecurityTypes)
    public Integer getTotNoSecurityTypes() {
        return totNoSecurityTypes;
    }

    /**
     * Message field setter.
     * @param totNoSecurityTypes field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TotNoSecurityTypes)
    public void setTotNoSecurityTypes(Integer totNoSecurityTypes) {
        this.totNoSecurityTypes = totNoSecurityTypes;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public Boolean getLastFragment() {
        return lastFragment;
    }

    /**
     * Message field setter.
     * @param lastFragment field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSecurityTypes)
    public Integer getNoSecurityTypes() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SecTypesGroup} components. It will also create an array
     * of {@link SecTypesGroup} objects and set the <code>secTypesGroups</code>
     * field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>secTypesGroups</code> array they will be discarded.<br/>
     * @param noSecurityTypes number of objects
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoSecurityTypes)
    public void setNoSecurityTypes(Integer noSecurityTypes) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SecTypesGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.3")
    public SecTypesGroup[] getSecTypesGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link SecTypesGroup} object to the existing array of <code>secTypesGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSecurityTypes</code> field to the proper value.<br/>
     * Note: If the <code>setNoSecurityTypes</code> method has been called there will already be a number of objects in the
     * <code>secTypesGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.3")
    public SecTypesGroup addSecTypesGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SecTypesGroup} object from the existing array of <code>secTypesGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSecurityTypes</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.3")
    public SecTypesGroup deleteSecTypesGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SecTypesGroup} objects from the <code>secTypesGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSecurityTypes</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3")
    public int clearSecTypesGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType)
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    /**
     * Message field setter.
     * @param subscriptionRequestType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SubscriptionRequestType)
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (securityReqID == null || securityReqID.trim().isEmpty()) {
            errorMsg.append(" [SecurityReqID]");
            hasMissingTag = true;
        }
        if (securityResponseID == null) {
            errorMsg.append(" [SecurityResponseID]");
            hasMissingTag = true;
        }
        if (securityResponseType == null) {
            errorMsg.append(" [SecurityResponseType]");
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
            TagEncoder.encode(bao, TagNum.SecurityReqID, securityReqID);
            TagEncoder.encode(bao, TagNum.SecurityResponseID, securityResponseID);
            if (securityResponseType != null) {
                TagEncoder.encode(bao, TagNum.SecurityResponseType, securityResponseType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TotNoSecurityTypes, totNoSecurityTypes);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noSecurityTypes != null) {
                TagEncoder.encode(bao, TagNum.NoSecurityTypes, noSecurityTypes);
                if (secTypesGroups != null && secTypesGroups.length == noSecurityTypes.intValue()) {
                    for (int i = 0; i < noSecurityTypes.intValue(); i++) {
                        if (secTypesGroups[i] != null) {
                            bao.write(secTypesGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SecTypesGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSecurityTypes.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            TagEncoder.encode(bao, TagNum.MarketID, marketID);
            TagEncoder.encode(bao, TagNum.MarketSegmentID, marketSegmentID);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (subscriptionRequestType != null) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
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
            case SecurityReqID:
                securityReqID = new String(tag.value, sessionCharset);
                break;

            case SecurityResponseID:
                securityResponseID = new String(tag.value, sessionCharset);
                break;

            case SecurityResponseType:
                securityResponseType = SecurityResponseType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TotNoSecurityTypes:
                totNoSecurityTypes = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoSecurityTypes:
                noSecurityTypes = new Integer(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case MarketID:
                marketID = new String(tag.value, sessionCharset);
                break;

            case MarketSegmentID:
                marketSegmentID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case SubscriptionRequestType:
                subscriptionRequestType = SubscriptionRequestType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SecurityTypesMsg] fields.";
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
        StringBuilder b = new StringBuilder("{SecurityTypesMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, applicationSequenceControl);
        printTagValue(b, TagNum.SecurityReqID, securityReqID);
        printTagValue(b, TagNum.SecurityResponseID, securityResponseID);
        printTagValue(b, TagNum.SecurityResponseType, securityResponseType);
        printTagValue(b, TagNum.TotNoSecurityTypes, totNoSecurityTypes);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoSecurityTypes, noSecurityTypes);
        printTagValue(b, secTypesGroups);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.MarketID, marketID);
        printTagValue(b, TagNum.MarketSegmentID, marketSegmentID);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.SubscriptionRequestType, subscriptionRequestType);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
