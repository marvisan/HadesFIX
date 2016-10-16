/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg43.java
 *
 * $Id: MassQuoteAckMsg43.java,v 1.9 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteSetAckGroup;
import net.hades.fix.message.group.impl.v43.QuoteSetAckGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

/**
 * FIX version 4.3 MassQuoteAckMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class MassQuoteAckMsg43 extends MassQuoteAckMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -780619675950101873L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> QUOTE_SET_ACK_GROUP_TAGS = new QuoteSetAckGroup43().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(QUOTE_SET_ACK_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_SET_ACK_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MassQuoteAckMsg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MassQuoteAckMsg43(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MassQuoteAckMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MassQuoteAckMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    @Override
    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    @Override
    public Integer getQuoteRejectReason() {
        return quoteRejectReason;
    }

    @Override
    public void setQuoteRejectReason(Integer quoteRejectReason) {
        this.quoteRejectReason = quoteRejectReason;
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
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getNoQuoteSets() {
        return noQuoteSets;
    }
    
    @Override
    public void setNoQuoteSets(Integer noQuoteSets) {
        this.noQuoteSets = noQuoteSets;
        if (noQuoteSets != null) {
            quoteSetAckGroups = new QuoteSetAckGroup[noQuoteSets.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < quoteSetAckGroups.length; i++) {
                quoteSetAckGroups[i] = new QuoteSetAckGroup43(context);
            }
        }
    }

    @Override
    public QuoteSetAckGroup[] getQuoteSetAckGroups() {
        return quoteSetAckGroups;
    }

    public void setQuoteSetAckGroups(QuoteSetAckGroup[] quoteSetAckGroups) {
        this.quoteSetAckGroups = quoteSetAckGroups;
        if (quoteSetAckGroups != null) {
            noQuoteSets = new Integer(quoteSetAckGroups.length);
        }
    }

    @Override
    public QuoteSetAckGroup addQuoteSetAckGroup() {
        QuoteSetAckGroup group = new QuoteSetAckGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<QuoteSetAckGroup> groups = new ArrayList<QuoteSetAckGroup>();
        if (quoteSetAckGroups != null && quoteSetAckGroups.length > 0) {
            groups = new ArrayList<QuoteSetAckGroup>(Arrays.asList(quoteSetAckGroups));
        }
        groups.add(group);
        quoteSetAckGroups = groups.toArray(new QuoteSetAckGroup[groups.size()]);
        noQuoteSets = new Integer(quoteSetAckGroups.length);

        return group;
    }

    @Override
    public QuoteSetAckGroup deleteQuoteSetAckGroup(int index) {
        QuoteSetAckGroup result = null;
        if (quoteSetAckGroups != null && quoteSetAckGroups.length > 0 && quoteSetAckGroups.length > index) {
            List<QuoteSetAckGroup> groups = new ArrayList<QuoteSetAckGroup>(Arrays.asList(quoteSetAckGroups));
            result = groups.remove(index);
            quoteSetAckGroups = groups.toArray(new QuoteSetAckGroup[groups.size()]);
            if (quoteSetAckGroups.length > 0) {
                noQuoteSets = new Integer(quoteSetAckGroups.length);
            } else {
                quoteSetAckGroups = null;
                noQuoteSets = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteSetAckGroups() {
        int result = 0;
        if (quoteSetAckGroups != null && quoteSetAckGroups.length > 0) {
            result = quoteSetAckGroups.length;
            quoteSetAckGroups = null;
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
            if (quoteStatus != null && MsgUtil.isTagInList(TagNum.QuoteStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteStatus, quoteStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.QuoteRejectReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteRejectReason, quoteRejectReason);
            }
            if (quoteResponseLevel != null && MsgUtil.isTagInList(TagNum.QuoteResponseLevel, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteResponseLevel, quoteResponseLevel.getValue());
            }
            if (quoteType != null && MsgUtil.isTagInList(TagNum.QuoteType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteType, quoteType.getValue());
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
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (noQuoteSets != null) {
                if (MsgUtil.isTagInList(TagNum.NoQuoteSets, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoQuoteSets, noQuoteSets);
                    if (quoteSetAckGroups != null && quoteSetAckGroups.length == noQuoteSets.intValue()) {
                        for (int i = 0; i < noQuoteSets.intValue(); i++) {
                            if (quoteSetAckGroups[i] != null) {
                                bao.write(quoteSetAckGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "QuoteSetAckGroups field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                                TagNum.NoQuoteSets.getValue(), error);
                    }
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.severe(error + " Error was : " + ex.toString());
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (QUOTE_SET_ACK_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteSets != null && noQuoteSets.intValue() > 0) {
                message.reset();
                quoteSetAckGroups = new QuoteSetAckGroup[noQuoteSets.intValue()];
                for (int i = 0; i < noQuoteSets.intValue(); i++) {
                    QuoteSetAckGroup component = new QuoteSetAckGroup43(context);
                    component.decode(message);
                    quoteSetAckGroups[i] = component;
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
        return "This tag is not supported in [MassQuoteAckMsg] message version [4.3].";
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
