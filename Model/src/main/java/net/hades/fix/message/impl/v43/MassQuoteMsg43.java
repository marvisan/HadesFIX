/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteMsg43.java
 *
 * $Id: MassQuoteMsg43.java,v 1.10 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.MassQuoteMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.group.impl.v43.QuoteSetGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.3 MassQuoteMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class MassQuoteMsg43 extends MassQuoteMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -8076997175946946589L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> QUOTE_SET_GROUP_TAGS = new QuoteSetGroup43().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(QUOTE_SET_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_SET_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MassQuoteMsg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MassQuoteMsg43(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MassQuoteMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MassQuoteMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Header getHeader() {
        return header;
    }
    
    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    @Override
    public String getQuoteID() {
        return quoteID;
    }

    @Override
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    @Override
    public QuoteType getQuoteType() {
        return quoteType;
    }

    @Override
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties43(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public Double getDefBidSize() {
        return defBidSize;
    }

    @Override
    public void setDefBidSize(Double defBidSize) {
        this.defBidSize = defBidSize;
    }

    @Override
    public Double getDefOfferSize() {
        return defOfferSize;
    }

    @Override
    public void setDefOfferSize(Double defOfferSize) {
        this.defOfferSize = defOfferSize;
    }

    @Override
    public Integer getNoQuoteSets() {
        return noQuoteSets;
    }
    
    @Override
    public void setNoQuoteSets(Integer noQuoteSets) {
        this.noQuoteSets = noQuoteSets;
        if (noQuoteSets != null) {
            quoteSetGroups = new QuoteSetGroup[noQuoteSets.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < quoteSetGroups.length; i++) {
                quoteSetGroups[i] = new QuoteSetGroup43(context);
            }
        }
    }

    @Override
    public QuoteSetGroup[] getQuoteSetGroups() {
        return quoteSetGroups;
    }

    public void setQuoteSetGroups(QuoteSetGroup[] quoteSetGroups) {
        this.quoteSetGroups = quoteSetGroups;
        if (quoteSetGroups != null) {
            noQuoteSets = new Integer(quoteSetGroups.length);
        }
    }

    @Override
    public QuoteSetGroup addQuoteSetGroup() {
        QuoteSetGroup group = new QuoteSetGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<QuoteSetGroup> groups = new ArrayList<QuoteSetGroup>();
        if (quoteSetGroups != null && quoteSetGroups.length > 0) {
            groups = new ArrayList<QuoteSetGroup>(Arrays.asList(quoteSetGroups));
        }
        groups.add(group);
        quoteSetGroups = groups.toArray(new QuoteSetGroup[groups.size()]);
        noQuoteSets = new Integer(quoteSetGroups.length);

        return group;
    }

    @Override
    public QuoteSetGroup deleteQuoteSetGroup(int index) {
        QuoteSetGroup result = null;
        if (quoteSetGroups != null && quoteSetGroups.length > 0 && quoteSetGroups.length > index) {
            List<QuoteSetGroup> groups = new ArrayList<QuoteSetGroup>(Arrays.asList(quoteSetGroups));
            result = groups.remove(index);
            quoteSetGroups = groups.toArray(new QuoteSetGroup[groups.size()]);
            if (quoteSetGroups.length > 0) {
                noQuoteSets = new Integer(quoteSetGroups.length);
            } else {
                quoteSetGroups = null;
                noQuoteSets = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteSetGroups() {
        int result = 0;
        if (quoteSetGroups != null && quoteSetGroups.length > 0) {
            result = quoteSetGroups.length;
            quoteSetGroups = null;
            noQuoteSets = null;
        }

        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.QuoteID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            }
            if (quoteType != null && MsgUtil.isTagInList(TagNum.QuoteType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
            }
            if (quoteResponseLevel != null && MsgUtil.isTagInList(TagNum.QuoteResponseLevel, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteResponseLevel, quoteResponseLevel.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.Account, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Account, account);
            }
            if (accountType != null && MsgUtil.isTagInList(TagNum.AccountType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DefBidSize, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DefBidSize, defBidSize);
            }
            if (MsgUtil.isTagInList(TagNum.DefOfferSize, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DefOfferSize, defOfferSize);
            }
            if (noQuoteSets != null) {
                if (MsgUtil.isTagInList(TagNum.NoQuoteSets, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoQuoteSets, noQuoteSets);
                    if (quoteSetGroups != null && quoteSetGroups.length == noQuoteSets.intValue()) {
                        for (int i = 0; i < noQuoteSets.intValue(); i++) {
                            if (quoteSetGroups[i] != null) {
                                bao.write(quoteSetGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "QuoteSetGroups field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                                TagNum.NoQuoteSets.getValue(), error);
                    }
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (QUOTE_SET_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteSets != null && noQuoteSets.intValue() > 0) {
                message.reset();
                quoteSetGroups = new QuoteSetGroup[noQuoteSets.intValue()];
                for (int i = 0; i < noQuoteSets.intValue(); i++) {
                    QuoteSetGroup component = new QuoteSetGroup43(context);
                    component.decode(message);
                    quoteSetGroups[i] = component;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties43(context);
            }
            parties.decode(tag, message);
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MassQuoteMsg] message version [4.3].";
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
