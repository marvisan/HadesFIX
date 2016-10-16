/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsg.java
 *
 * $Id: MassQuoteMsg.java,v 1.12 2011-04-28 10:07:46 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Mass Quote message can contain quotes for multiple securities to support applications that 
 * allow for the mass quoting of an option series. <br/>
 * Two levels of repeating groups have been provided to minimize the amount of data required to
 * submit a set of quotes for a class of options (e.g. all option series for IBM).<br/>
 * A QuoteSet specifies the first level of repeating fields for the Mass Quote message.
 * It represents a group of related quotes and can, for example, represent an option class.
 * Each QuoteSet contains an optional repeating group of QuoteEntries which can represent an option series.
 * It is possible the number of Quote Entries for a Quote Set (option class) could exceed one’s
 * physical or practical message size.
 * It may be necessary to fragment a message across multiple quote messages.
 * Message size limits must be mutually agreed to with one’s counterparties.
 * The grouping of quotes is as follows:<br/>
 * NoQuoteSets – specifies the number of sets of quotes contained in the message
 * QuoteSetID – Is a unique ID given to the quote set
 * Information regarding the security to which all of the quotes belong
 * TotQuoteEntries – defines the number of quotes for the quote set across all messages
 * NoQuoteEntries – defines the number of quotes contained within this message for this quote set
 * QuoteEntryID – Is a unique ID given to a specific quote entry
 * Information regarding the specific quote (bid/ask size and price)
 * If there are too many Quote Entries for a Quote Set to fit into one physical message, then
 * the quotes can be continued in another Mass Quote message by repeating all of the
 * QuoteSet information and then specifying the number of Quote Entries (related symbols)
 * in the continued message. The TotQuoteEntries is provided to optionally indicate to the
 * counterparty the total number of Quote Entries for a Quote Set in multiple quote messages.
 * This permits, but does not require, a receiving application to react in a stateful manner
 * where it can determine if it has received all quotes for a Quote Set before carrying out
 * some action. However, the overall approach to fragmentation is to permit each mass quote message
 * to be processed in a stateless manner as it is received. Each mass quote message should contain
 * enough information to have the Quote Entries applied to a market without requiring the next message
 * if fragmentation has occurred. Also, a continued message should not require any information from
 * the previous message.
 * Maximum message size for fragmentation purposes can be determined by using the optional
 * MaxMessageSize field in the Logon message or by mutual agreement between counterparties.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MassQuoteMsg extends FIXMsg  {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteReqID.getValue(),
        TagNum.QuoteID.getValue(),
        TagNum.QuoteType.getValue(),
        TagNum.QuoteResponseLevel.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.AccountType.getValue(),
        TagNum.DefBidSize.getValue(),
        TagNum.DefOfferSize.getValue(),
        TagNum.NoQuoteSets.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteID.getValue(),
        TagNum.NoQuoteSets.getValue()
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
     * TagNum = 537. Starting with 4.3 version.
     */
    protected QuoteType quoteType;

    /**
     * TagNum = 301. Starting with 4.2 version.
     */
    protected QuoteResponseLevel quoteResponseLevel;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

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
     * TagNum = 293. Starting with 4.2 version.
     */
    protected Double defBidSize;

    /**
     * TagNum = 294. Starting with 4.2 version.
     */
    protected Double defOfferSize;

    /**
     * TagNum = 296 REQUIRED. Starting with 4.2 version.
     */
    protected Integer noQuoteSets;

    /**
     * Starting with 4.2 version.
     */
    protected QuoteSetGroup[] quoteSetGroups;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MassQuoteMsg() {
        super();
    }
    
    public MassQuoteMsg(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }
    
    public MassQuoteMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.MassQuote.getValue(), beginString);
    }
    
    public MassQuoteMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.MassQuote.getValue(), beginString, applVerID);
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
    @TagNumRef(tagNum=TagNum.DefBidSize)
    public Double getDefBidSize() {
        return defBidSize;
    }

    /**
     * Message field setter.
     * @param defBidSize field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DefBidSize)
    public void setDefBidSize(Double defBidSize) {
        this.defBidSize = defBidSize;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DefOfferSize)
    public Double getDefOfferSize() {
        return defOfferSize;
    }

    /**
     * Message field setter.
     * @param defOfferSize field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.DefOfferSize)
    public void setDefOfferSize(Double defOfferSize) {
        this.defOfferSize = defOfferSize;
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
     * This method sets the number of {@link QuoteSetGroup} components. It will also create an array
     * of {@link QuoteSetGroup} objects and set the <code>quoteSetGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>quoteSetGroups</code> array they will be discarded.<br/>
     * @param noQuoteSets field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoQuoteSets, required=true)
    public void setNoQuoteSets(Integer noQuoteSets) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter for {@link QuoteSetGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    public QuoteSetGroup[] getQuoteSetGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link QuoteSetGroup} object to the existing array of <code>quoteSetGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noQuoteSets</code> field to the proper value.<br/>
     * Note: If the <code>setNoQuoteSets</code> method has been called there will already be a number of objects in the
     * <code>quoteSetGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2")
    public QuoteSetGroup addQuoteSetGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method deletes a {@link QuoteSetGroup} object from the existing array of <code>quoteSetGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noQuoteSets</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2")
    public QuoteSetGroup deleteQuoteSetGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Deletes all the {@link QuoteSetGroup} objects from the <code>quoteSetGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noQuoteSets</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2")
    public int clearQuoteSetGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteID == null || quoteID.trim().isEmpty()) {
            errorMsg.append(" [QuoteID]");
            hasMissingTag = true;
        }
        if (noQuoteSets == null) {
            errorMsg.append(" [NoQuoteSets]");
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
            if (quoteType != null) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            if (quoteResponseLevel != null) {
                TagEncoder.encode(bao, TagNum.QuoteResponseLevel, quoteResponseLevel.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (acctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            TagEncoder.encode(bao, TagNum.DefBidSize, defBidSize);
            TagEncoder.encode(bao, TagNum.DefOfferSize, defOfferSize);
            if (noQuoteSets != null) {
                TagEncoder.encode(bao, TagNum.NoQuoteSets, noQuoteSets);
                if (quoteSetGroups != null && quoteSetGroups.length == noQuoteSets.intValue()) {
                    for (int i = 0; i < noQuoteSets.intValue(); i++) {
                        if (quoteSetGroups[i] != null) {
                            bao.write(quoteSetGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "QuoteSetGroups field has been set but there is no data or the number of groups does not match.";
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

            case QuoteType:
                quoteType = QuoteType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
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

            case DefBidSize:
                defBidSize = new Double(new String(tag.value, sessionCharset));
                break;

            case DefOfferSize:
                defOfferSize = new Double(new String(tag.value, sessionCharset));
                break;

            case NoQuoteSets:
                noQuoteSets = new Integer(new String(tag.value, sessionCharset));
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
        StringBuilder b = new StringBuilder("{MassQuoteMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.QuoteReqID, quoteReqID);
        printTagValue(b, TagNum.QuoteID, quoteID);
        printTagValue(b, TagNum.QuoteType, quoteType);
        printTagValue(b, TagNum.QuoteResponseLevel, quoteResponseLevel);
        printTagValue(b, parties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.AccountType, accountType);
        printTagValue(b, TagNum.DefBidSize, defBidSize);
        printTagValue(b, TagNum.DefOfferSize, defOfferSize);
        printTagValue(b, TagNum.NoQuoteSets, noQuoteSets);
        printTagValue(b, quoteSetGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
}
