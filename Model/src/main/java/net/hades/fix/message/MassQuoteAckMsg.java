/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg.java
 *
 * $Id: MassQuoteAckMsg.java,v 1.10 2011-04-28 10:07:45 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteSetAckGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;
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

import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * Mass Quote Acknowledgement is used as the application level response to a Mass Quote message.
 * The Mass Quote Acknowledgement contains a field for reporting the reason in the event that the
 * entire quote is rejected ({@link net.hades.fix.message.type.TagNum#QuoteRejectReason} [300]).<br/>
 * The Mass Quote Acknowledgement also contains a field for each quote that is used in the event
 * that the quote entry is rejected ({@link net.hades.fix.message.type.TagNum#QuoteEntryRejectReason} [368]).
 * The ability to reject an individual quote entry is important so that the majority of quotes
 * can be successfully applied to the market instead of having to reject the entire Mass Quote
 * for a minority of rejected quotes.<br/>
 * Derivative markets are characterized by high bandwidth consumption – due to a change in
 * an underlying security price causing multiple (often in the hundreds) of quotes to be
 * recalculated and retransmitted to the market. For that reason the ability for market participants
 * (and the market ) to be able to set the level of response requested to a Mass Quote message
 * is specified using the QuoteResponseLevel[301] field.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MassQuoteAckMsg extends FIXMsg  {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -855127613136067810L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteReqID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.QuoteStatus.getValue(),
        TagNum.QuoteRejectReason.getValue(),
        TagNum.QuoteType.getValue(),
        TagNum.QuoteResponseLevel.getValue(),
        TagNum.QuoteCancelType.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.Text.getValue(),
        TagNum.NoQuoteSets.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteID.getValue(),
        TagNum.NoQuoteSets.getValue()
    }));

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 131. Starting with 4.2 version.
     */
    protected String quoteReqID;

    /**
     * TagNum = 117. Starting with 4.2 version.
     */
    protected String quoteID;

    /**
     * TagNum = 297 REQUIRED. Starting with 4.2 version.
     */
    protected QuoteStatus quoteStatus;

    /**
     * TagNum = 300. Starting with 4.2 version.
     */
    protected Integer quoteRejectReason;

    /**
     * TagNum = 301. Starting with 4.2 version.
     */
    protected QuoteResponseLevel quoteResponseLevel;

    /**
     * TagNum = 537. Starting with 4.3 version.
     */
    protected QuoteType quoteType;

    /**
     * TagNum = 298. Starting with 5.0SP1 version.
     */
    protected Integer quoteCancelType;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * Starting with 5.0SP2 version.
     */
    protected TargetParties targetParties;

    /**
     * TagNum = 1. Starting with 4.3 version.
     */
    protected String account;

    /**
     * TagNum = 660. Starting with 4.4 version.
     */
    protected AcctIDSource acctIDSource;

    /**
     * TagNum = 581. Starting with 4.3 version.
     */
    protected AccountType accountType;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 58. Starting with 4.2 version.
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

    /**
     * TagNum = 296. Starting with 4.2 version.
     */
    protected Integer noQuoteSets;

    /**
     * Starting with 4.2 version.
     */
    protected QuoteSetAckGroup[] quoteSetAckGroups;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MassQuoteAckMsg() {
        super();
    }
    
    public MassQuoteAckMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MassQuoteAckMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.MassQuoteAck.getValue(), beginString);
    }
    
    public MassQuoteAckMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.MassQuoteAck.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.QuoteReqID)
    public String getQuoteReqID() {
        return quoteReqID;
    }

    /**
     * Message field setter.
     * @param quoteReqID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteReqID)
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public String getQuoteID() {
        return quoteID;
    }

    /**
     * Message field setter.
     * @param quoteID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteID)
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteStatus, required=true)
    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    /**
     * Message field setter.
     * @param quoteStatus field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteStatus, required=true)
    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteRejectReason)
    public Integer getQuoteRejectReason() {
        return quoteRejectReason;
    }

    /**
     * Message field setter.
     * @param quoteRejectReason field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteRejectReason)
    public void setQuoteRejectReason(Integer quoteRejectReason) {
        this.quoteRejectReason = quoteRejectReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.QuoteResponseLevel)
    public QuoteResponseLevel getQuoteResponseLevel() {
        return quoteResponseLevel;
    }

    /**
     * Message field setter.
     * @param quoteResponseLevel field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.QuoteResponseLevel)
    public void setQuoteResponseLevel(QuoteResponseLevel quoteResponseLevel) {
        this.quoteResponseLevel = quoteResponseLevel;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteType)
    public QuoteType getQuoteType() {
        return quoteType;
    }

    /**
     * Message field setter.
     * @param quoteType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.QuoteType)
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteCancelType)
    public Integer getQuoteCancelType() {
        return quoteCancelType;
    }

    /**
     * Message field setter.
     * @param quoteCancelType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteCancelType)
    public void setQuoteCancelType(Integer quoteCancelType) {
        this.quoteCancelType = quoteCancelType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.3")
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
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Account)
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    /**
     * Message field setter.
     * @param acctIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.AccountType)
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Message field setter.
     * @param accountType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.AccountType)
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.2")
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

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoQuoteSets, required=true)
    public Integer getNoQuoteSets() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method sets the number of {@link QuoteSetAckGroup} components. It will also create an array
     * of {@link QuoteSetAckGroup} objects and set the <code>quoteSetAckGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>quoteSetAckGroups</code> array they will be discarded.<br/>
     * @param noQuoteSets field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoQuoteSets, required=true)
    public void setNoQuoteSets(Integer noQuoteSets) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter for {@link QuoteSetAckGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    public QuoteSetAckGroup[] getQuoteSetAckGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link QuoteSetAckGroup} object to the existing array of <code>quoteSetAckGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noQuoteSets</code> field to the proper value.<br/>
     * Note: If the <code>setNoQuoteSets</code> method has been called there will already be a number of objects in the
     * <code>quoteSetAckGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2")
    public QuoteSetAckGroup addQuoteSetAckGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method deletes a {@link QuoteSetAckGroup} object from the existing array of <code>quoteSetAckGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noQuoteSets</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2")
    public QuoteSetAckGroup deleteQuoteSetAckGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Deletes all the {@link QuoteSetAckGroup} objects from the <code>quoteSetAckGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noQuoteSets</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2")
    public int clearQuoteSetAckGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteStatus == null) {
            errorMsg.append(" [QuoteStatus]");
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
            TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            if (quoteStatus != null) {
                TagEncoder.encode(bao, TagNum.QuoteStatus, quoteStatus.getValue());
            }
            TagEncoder.encode(bao, TagNum.QuoteRejectReason, quoteRejectReason);
            if (quoteResponseLevel != null) {
                TagEncoder.encode(bao, TagNum.QuoteResponseLevel, quoteResponseLevel.getValue());
            }
            if (quoteType != null) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            TagEncoder.encode(bao, TagNum.QuoteCancelType, quoteCancelType);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (targetParties != null) {
                bao.write(targetParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (acctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (noQuoteSets != null) {
                TagEncoder.encode(bao, TagNum.NoQuoteSets, noQuoteSets);
                if (quoteSetAckGroups != null && quoteSetAckGroups.length == noQuoteSets.intValue()) {
                    for (int i = 0; i < noQuoteSets.intValue(); i++) {
                        if (quoteSetAckGroups[i] != null) {
                            bao.write(quoteSetAckGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "QuoteSetAckGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(), TagNum.NoLinesOfText.getValue(), error);
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
            case QuoteID:
                quoteID = new String(tag.value, sessionCharset);
                break;

            case QuoteReqID:
                quoteReqID = new String(tag.value, sessionCharset);
                break;

            case QuoteStatus:
                quoteStatus = QuoteStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QuoteRejectReason:
                quoteRejectReason = new Integer(new String(tag.value, sessionCharset));
                break;

            case QuoteType:
                quoteType = QuoteType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case QuoteCancelType:
                quoteCancelType = new Integer(new String(tag.value, sessionCharset));
                break;

            case QuoteResponseLevel:
                quoteResponseLevel = QuoteResponseLevel.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case AcctIDSource:
                acctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case AccountType:
                accountType = AccountType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case NoQuoteSets:
                noQuoteSets = new Integer(new String(tag.value, sessionCharset));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in MassQuoteMsg.";
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncodedTextLen.getValue(), error);
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
        StringBuilder b = new StringBuilder("{MassQuoteAckMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.QuoteReqID, quoteReqID);
        printTagValue(b, TagNum.QuoteID, quoteID);
        printTagValue(b, TagNum.QuoteStatus, quoteStatus);
        printTagValue(b, TagNum.QuoteRejectReason, quoteRejectReason);
        printTagValue(b, TagNum.QuoteResponseLevel, quoteResponseLevel);
        printTagValue(b, TagNum.QuoteType, quoteType);
        printTagValue(b, TagNum.QuoteCancelType, quoteCancelType);
        printTagValue(b, parties);
        printTagValue(b, targetParties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.NoQuoteSets, noQuoteSets);
        printTagValue(b, quoteSetAckGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
}
