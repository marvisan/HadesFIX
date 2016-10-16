/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg.java
 *
 * $Id: QuoteCancelMsg.java,v 1.11 2011-04-28 10:07:46 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.MsgType;

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
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteCancelGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.QuoteCancelType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Quote Cancel message is used by an originator of quotes to cancel quotes.
 * The Quote Cancel message supports cancelation of:
 * <ul>
 *      <li> All quotes
 *      <li> Quotes for a specific symbol or security ID
 *      <li> All quotes for a security type
 *      <li> All quotes for an underlying
 * </ul>
 * Canceling a Quote is accomplished by indicating the type of cancelation in the QuoteCancelType field.
 * It is recommended that all Cancel messages be acknowledged using the Quote Status Report message.
 * The Quote Cancelation only applies to quotes made by the current FIX user.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class QuoteCancelMsg extends FIXMsg  {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5378973332956808201L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteReqID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.QuoteMsgID.getValue(),
        TagNum.QuoteCancelType.getValue(),
        TagNum.QuoteResponseLevel.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.NoQuoteEntries.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteID.getValue(),
        TagNum.QuoteCancelType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
        /**
     * TagNum = 131. Starting with 4.2 version.
     */
    protected String quoteReqID;

    /**
     * TagNum = 117 REQUIRED. Starting with 4.2 version.
     */
    protected String quoteID;

    /**
     * TagNum = 1166. Starting with 5.0SP1 version.
     */
    protected String quoteMsgID;

    /**
     * TagNum = 298. Starting with 4.2 version.
     */
    protected Integer quoteCancelType;

    /**
     * TagNum = 301. Starting with 4.2 version.
     */
    protected QuoteResponseLevel quoteResponseLevel;

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
     * TagNum = 625. Starting with 4.2 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 295. Starting with 4.2 version.
     */
    protected Integer noQuoteEntries;

    /**
     * Starting with 4.2 version.
     */
    protected QuoteCancelGroup[] quoteCancelEntries;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteCancelMsg() {
        super();
    }
    
    public QuoteCancelMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public QuoteCancelMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.QuoteCancel.getValue(), beginString);
    }
    
    public QuoteCancelMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.QuoteCancel.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.QuoteID, required=true)
    public String getQuoteID() {
        return quoteID;
    }

    /**
     * Message field setter.
     * @param quoteID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteID, required=true)
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteMsgID)
    public String getQuoteMsgID() {
        return quoteMsgID;
    }

    /**
     * Message field setter.
     * @param quoteMsgID field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.QuoteMsgID)
    public void setQuoteMsgID(String quoteMsgID) {
        this.quoteMsgID = quoteMsgID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteCancelType)
    public Integer getQuoteCancelType() {
        return quoteCancelType;
    }

    /**
     * Message field setter.
     * @param quoteCancelType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.QuoteCancelType)
    public void setQuoteCancelType(Integer quoteCancelType) {
        this.quoteCancelType = quoteCancelType;
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoQuoteEntries)
    public Integer getNoQuoteEntries() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * This method sets the number of {@link QuoteCancelGroup} groups. It will also create an array
     * of {@link QuoteCancelGroup} objects and set the <code>quoteCancelEntries</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>quoteCancelEntries</code> array they will be discarded.<br/>
     * @param noQuoteEntries field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoQuoteEntries)
    public void setNoQuoteEntries(Integer noQuoteEntries) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    public QuoteCancelGroup[] getQuoteCancelEntries() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link QuoteCancelGroup} object to the existing array of <code>quoteCancelEntries</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noQuoteEntries</code> field to the proper value.<br/>
     * Note: If the <code>setNoQuoteEntries</code> method has been called there will already be a number of objects in the
     * <code>quoteCancelEntries</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public QuoteCancelGroup addQuoteCancelEntry() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link QuoteCancelGroup} object from the existing array of <code>quoteCancelEntries</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noQuoteEntries</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public QuoteCancelGroup deleteQuoteCancelEntry(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link QuoteCancelGroup} objects from the <code>quoteCancelEntries</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noQuoteEntries</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearQuoteCancelEntries() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteCancelType != null && quoteCancelType.intValue() == QuoteCancelType.CancelSpecifiedQuote.getValue()) {
            if (quoteID == null) {
                errorMsg.append(" [QuoteID]");
                hasMissingTag = true;
            }
        }
        if (quoteCancelType == null) {
            errorMsg.append(" [QuoteCancelType]");
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
            TagEncoder.encode(bao, TagNum.QuoteMsgID, quoteMsgID);
            TagEncoder.encode(bao, TagNum.QuoteCancelType, quoteCancelType);
            if (quoteResponseLevel != null) {
                TagEncoder.encode(bao, TagNum.QuoteResponseLevel, quoteResponseLevel.getValue());
            }
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
            if (noQuoteEntries != null) {
                TagEncoder.encode(bao, TagNum.NoQuoteEntries, noQuoteEntries);
                if (quoteCancelEntries != null && quoteCancelEntries.length == noQuoteEntries.intValue()) {
                    for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                        if (quoteCancelEntries[i] != null) {
                            bao.write(quoteCancelEntries[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "QuoteCancelEntries field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoQuoteEntries.getValue(), error);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ex.toString()});
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

            case QuoteMsgID:
                quoteMsgID = new String(tag.value, sessionCharset);
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

            case NoQuoteEntries:
                noQuoteEntries = new Integer(new String(tag.value, sessionCharset));
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in QuoteCancelMsg.";
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
        StringBuilder b = new StringBuilder("{QuoteCancelMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.QuoteReqID, quoteReqID);
        printTagValue(b, TagNum.QuoteID, quoteID);
        printTagValue(b, TagNum.QuoteMsgID, quoteMsgID);
        printTagValue(b, TagNum.QuoteCancelType, quoteCancelType);
        printTagValue(b, TagNum.QuoteResponseLevel, quoteResponseLevel);
        printTagValue(b, parties);
        printTagValue(b, targetParties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.NoQuoteEntries, noQuoteEntries);
        printTagValue(b, quoteCancelEntries);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
}
