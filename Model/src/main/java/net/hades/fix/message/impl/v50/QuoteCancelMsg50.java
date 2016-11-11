/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg50.java
 *
 * $Id: QuoteCancelMsg50.java,v 1.10 2011-04-14 23:44:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteCancelMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.QuoteResponseLevel;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.QuoteCancelGroup;
import net.hades.fix.message.group.impl.v50.QuoteCancelGroup50;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BeginString;

/**
 * FIX version 5.0 QuoteCancelMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="QuotCxl")
@XmlType(propOrder = {"header", "partyIDGroups", "quoteCancelEntries"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteCancelMsg50 extends QuoteCancelMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -8566580222839189790L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> QUOTE_CANCEL_GROUP_TAGS = new QuoteCancelGroup50().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(QUOTE_CANCEL_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_CANCEL_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteCancelMsg50() {
        super();
    }
    
    public QuoteCancelMsg50(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public QuoteCancelMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }
    
    public QuoteCancelMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        QuoteCancelMsg50 fixml = (QuoteCancelMsg50) fragment;
        if (fixml.getQuoteReqID() != null) {
            quoteReqID = fixml.getQuoteReqID();
        }
        if (fixml.getQuoteID() != null) {
            quoteID = fixml.getQuoteID();
        }
        if (fixml.getQuoteCancelType() != null) {
            quoteCancelType = fixml.getQuoteCancelType();
        }
        if (fixml.getQuoteResponseLevel() != null) {
            quoteResponseLevel = fixml.getQuoteResponseLevel();
        }
        if (fixml.getParties() != null) {
            parties = fixml.getParties();
        }
        if (fixml.getAccount() != null) {
            account = fixml.getAccount();
        }
        if (fixml.getAcctIDSource() != null) {
            acctIDSource = fixml.getAcctIDSource();
        }
        if (fixml.getAccountType() != null) {
            accountType = fixml.getAccountType();
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getQuoteCancelEntries() != null && fixml.getQuoteCancelEntries().length > 0) {
            setQuoteCancelEntries(fixml.getQuoteCancelEntries());
        }
    }
    
    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }
    
    @Override
    public void setHeader(Header header) {
        this.header = header;
    }
    

    @XmlAttribute(name = "ReqID")
    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    @XmlAttribute(name = "QID")
    @Override
    public String getQuoteID() {
        return quoteID;
    }

    @Override
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    @XmlAttribute(name = "CxlTyp")
    @Override
    public Integer getQuoteCancelType() {
        return quoteCancelType;
    }

    @Override
    public void setQuoteCancelType(Integer quoteCancelType) {
        this.quoteCancelType = quoteCancelType;
    }

    @XmlAttribute(name = "RspLvl")
    @Override
    public QuoteResponseLevel getQuoteResponseLevel() {
        return quoteResponseLevel;
    }

    @Override
    public void setQuoteResponseLevel(QuoteResponseLevel quoteResponseLevel) {
        this.quoteResponseLevel = quoteResponseLevel;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        this.parties = new Parties50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties50) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlAttribute(name = "Acct")
    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @XmlAttribute(name = "AcctIDSrc")
    @Override
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    @Override
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    @XmlAttribute(name = "AcctTyp")
    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @Override
    public Integer getNoQuoteEntries() {
        return noQuoteEntries;
    }

    @Override
    public void setNoQuoteEntries(Integer noQuoteEntries) {
        this.noQuoteEntries = noQuoteEntries;
        if (noQuoteEntries != null) {
            quoteCancelEntries = new QuoteCancelGroup[noQuoteEntries.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < quoteCancelEntries.length; i++) {
                quoteCancelEntries[i] = new QuoteCancelGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteCancelGroup[] getQuoteCancelEntries() {
        return quoteCancelEntries;
    }

    public void setQuoteCancelEntries(QuoteCancelGroup[] quoteCancelEntries) {
        this.quoteCancelEntries = quoteCancelEntries;
        if (quoteCancelEntries != null) {
            noQuoteEntries = new Integer(quoteCancelEntries.length);
        }
    }

    @Override
    public QuoteCancelGroup addQuoteCancelEntry() {
        QuoteCancelGroup group = new QuoteCancelGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<QuoteCancelGroup> groups = new ArrayList<QuoteCancelGroup>();
        if (quoteCancelEntries != null && quoteCancelEntries.length > 0) {
            groups = new ArrayList<QuoteCancelGroup>(Arrays.asList(quoteCancelEntries));
        }
        groups.add(group);
        quoteCancelEntries = groups.toArray(new QuoteCancelGroup[groups.size()]);
        noQuoteEntries = new Integer(quoteCancelEntries.length);

        return group;
    }

    @Override
    public QuoteCancelGroup deleteQuoteCancelEntry(int index) {
        QuoteCancelGroup result = null;
        if (quoteCancelEntries != null && quoteCancelEntries.length > 0 && quoteCancelEntries.length > index) {
            List<QuoteCancelGroup> groups = new ArrayList<QuoteCancelGroup>(Arrays.asList(quoteCancelEntries));
            result = groups.remove(index);
            quoteCancelEntries = groups.toArray(new QuoteCancelGroup[groups.size()]);
            if (quoteCancelEntries.length > 0) {
                noQuoteEntries = new Integer(quoteCancelEntries.length);
            } else {
                quoteCancelEntries = null;
                noQuoteEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteCancelEntries() {
        int result = 0;
        if (quoteCancelEntries != null && quoteCancelEntries.length > 0) {
            result = quoteCancelEntries.length;
            quoteCancelEntries = null;
            noQuoteEntries = null;
        }

        return result;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (QUOTE_CANCEL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteEntries != null && noQuoteEntries.intValue() > 0) {
                message.reset();
                quoteCancelEntries = new QuoteCancelGroup[noQuoteEntries.intValue()];
                for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                    QuoteCancelGroup group = new QuoteCancelGroup50(context);
                    group.decode(message);
                    quoteCancelEntries[i] = group;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50(context);
            }
            parties.decode(tag, message);
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteCancelMsg] message version [5.0].";
    }
    
    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
