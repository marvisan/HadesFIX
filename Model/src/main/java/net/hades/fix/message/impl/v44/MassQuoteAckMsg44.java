/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MassQuoteAckMsg44.java
 *
 * $Id: MassQuoteAckMsg44.java,v 1.11 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.QuoteSetAckGroup;
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
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.comp.impl.v44.Parties44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.impl.v44.QuoteSetAckGroup44;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteStatus;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 MassQuoteAckMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="MassQuotAck")
@XmlType(propOrder = {"header", "partyIDGroups", "quoteSetAckGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MassQuoteAckMsg44 extends MassQuoteAckMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7905731477179773468L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> QUOTE_SET_ACK_GROUP_TAGS = new QuoteSetAckGroup44().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties44().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
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
    
    public MassQuoteAckMsg44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MassQuoteAckMsg44(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MassQuoteAckMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MassQuoteAckMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        MassQuoteAckMsg44 fixml = (MassQuoteAckMsg44) fragment;
        if (fixml.getQuoteReqID() != null) {
            quoteReqID = fixml.getQuoteReqID();
        }
        if (fixml.getQuoteID() != null) {
            quoteID = fixml.getQuoteID();
        }
        if (fixml.getQuoteStatus() != null) {
            quoteStatus = fixml.getQuoteStatus();
        }
        if (fixml.getQuoteRejectReason() != null) {
            quoteRejectReason = fixml.getQuoteRejectReason();
        }
        if (fixml.getQuoteType() != null) {
            quoteType = fixml.getQuoteType();
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
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getQuoteSetAckGroups() != null && fixml.getQuoteSetAckGroups().length > 0) {
            setQuoteSetAckGroups(fixml.getQuoteSetAckGroups());
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

    @XmlAttribute(name = "Stat")
    @Override
    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    @Override
    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    @XmlAttribute(name = "RejRsn")
    @Override
    public Integer getQuoteRejectReason() {
        return quoteRejectReason;
    }

    @Override
    public void setQuoteRejectReason(Integer quoteRejectReason) {
        this.quoteRejectReason = quoteRejectReason;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public QuoteType getQuoteType() {
        return quoteType;
    }

    @Override
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties44(context);
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
            ((Parties44) parties).setPartyIDGroups(partyIDGroups);
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

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
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
                quoteSetAckGroups[i] = new QuoteSetAckGroup44(context);
            }
        }
    }

    @XmlElementRef
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
        QuoteSetAckGroup group = new QuoteSetAckGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            if (acctIDSource != null && MsgUtil.isTagInList(TagNum.AcctIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            if (accountType != null && MsgUtil.isTagInList(TagNum.AccountType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AccountType, accountType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
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
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
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
                    QuoteSetAckGroup component = new QuoteSetAckGroup44(context);
                    component.decode(message);
                    quoteSetAckGroups[i] = component;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties44(context);
            }
            parties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MassQuoteAckMsg] message version [4.4].";
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
