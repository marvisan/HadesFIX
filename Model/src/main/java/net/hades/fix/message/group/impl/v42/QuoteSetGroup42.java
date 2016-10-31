/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteSetGroup42.java
 *
 * $Id: QuoteSetGroup42.java,v 1.9 2011-04-14 23:44:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX 4.2 implementation of QuoteSetGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class QuoteSetGroup42 extends QuoteSetGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6384242079257836353L;
    
    protected static final Set<Integer> TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.QuoteSetID.getValue(),
        TagNum.QuoteSetValidUntilTime.getValue(),
        TagNum.UnderlyingSymbol.getValue(),
        TagNum.UnderlyingSymbolSfx.getValue(),
        TagNum.UnderlyingSecurityID.getValue(),
        TagNum.UnderlyingSecurityIDSource.getValue(),
        TagNum.UnderlyingSecurityType.getValue(),
        TagNum.UnderlyingMaturityMonthYear.getValue(),
        TagNum.UnderlyingMaturityDay.getValue(),
        TagNum.UnderlyingPutOrCall.getValue(),
        TagNum.UnderlyingStrikePrice.getValue(),
        TagNum.UnderlyingOptAttribute.getValue(),
        TagNum.UnderlyingContractMultiplier.getValue(),
        TagNum.UnderlyingCouponRate.getValue(),
        TagNum.UnderlyingSecurityExchange.getValue(),
        TagNum.UnderlyingIssuer.getValue(),
        TagNum.UnderlyingSecurityDesc.getValue(),
        TagNum.QuoteSetValidUntilTime.getValue(),
        TagNum.TotNoQuoteEntries.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoQuoteEntries.getValue()
    }));
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> START_DATA_TAGS_V42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedUnderlyingIssuerLen.getValue(),
        TagNum.EncodedUnderlyingSecurityDescLen.getValue()
    }));
    
    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> QUOTE_ENTRY_GROUP_TAGS = new QuoteEntryGroup42().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V42);
        ALL_TAGS.addAll(START_DATA_TAGS_V42);
        ALL_TAGS.addAll(QUOTE_ENTRY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_ENTRY_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteSetGroup42() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteSetGroup42(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V42;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getQuoteSetID() {
        return quoteSetID;
    }

    @Override
    public void setQuoteSetID(String quoteSetID) {
        this.quoteSetID = quoteSetID;
    }

    @Override
    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    @Override
    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    @Override
    public String getUnderlyingSymbolSfx() {
        return underlyingSymbolSfx;
    }

    @Override
    public void setUnderlyingSymbolSfx(String underlyingSymbolSfx) {
        this.underlyingSymbolSfx = underlyingSymbolSfx;
    }

    @Override
    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    @Override
    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    @Override
    public String getUnderlyingSecurityIDSource() {
        return underlyingSecurityIDSource;
    }

    @Override
    public void setUnderlyingSecurityIDSource(String underlyingSecurityIDSource) {
        this.underlyingSecurityIDSource = underlyingSecurityIDSource;
    }

    @Override
    public String getUnderlyingSecurityType() {
        return underlyingSecurityType;
    }

    @Override
    public void setUnderlyingSecurityType(String underlyingSecurityType) {
        this.underlyingSecurityType = underlyingSecurityType;
    }

    @Override
    public String getUnderlyingMaturityMonthYear() {
        return underlyingMaturityMonthYear;
    }

    @Override
    public void setUnderlyingMaturityMonthYear(String underlyingMaturityMonthYear) {
        this.underlyingMaturityMonthYear = underlyingMaturityMonthYear;
    }

    @Override
    public Integer getUnderlyingMaturityDay() {
        return underlyingMaturityDay;
    }

    @Override
    public void setUnderlyingMaturityDay(Integer underlyingMaturityDay) {
        this.underlyingMaturityDay = underlyingMaturityDay;
    }

    @Override
    public PutOrCall getUnderlyingPutOrCall() {
        return underlyingPutOrCall;
    }

    @Override
    public void setUnderlyingPutOrCall(PutOrCall underlyingPutOrCall) {
        this.underlyingPutOrCall = underlyingPutOrCall;
    }

    @Override
    public Double getUnderlyingStrikePrice() {
        return underlyingStrikePrice;
    }

    @Override
    public void setUnderlyingStrikePrice(Double underlyingStrikePrice) {
        this.underlyingStrikePrice = underlyingStrikePrice;
    }

    @Override
    public Character getUnderlyingOptAttribute() {
        return underlyingOptAttribute;
    }

    @Override
    public void setUnderlyingOptAttribute(Character underlyingOptAttribute) {
        this.underlyingOptAttribute = underlyingOptAttribute;
    }

    @Override
    public Double getUnderlyingContractMultiplier() {
        return underlyingContractMultiplier;
    }

    @Override
    public void setUnderlyingContractMultiplier(Double underlyingContractMultiplier) {
        this.underlyingContractMultiplier = underlyingContractMultiplier;
    }

    @Override
    public Double getUnderlyingCouponRate() {
        return underlyingCouponRate;
    }

    @Override
    public void setUnderlyingCouponRate(Double underlyingCouponRate) {
        this.underlyingCouponRate = underlyingCouponRate;
    }

    @Override
    public String getUnderlyingSecurityExchange() {
        return underlyingSecurityExchange;
    }

    @Override
    public void setUnderlyingSecurityExchange(String underlyingSecurityExchange) {
        this.underlyingSecurityExchange = underlyingSecurityExchange;
    }

    @Override
    public String getUnderlyingIssuer() {
        return underlyingIssuer;
    }

    @Override
    public void setUnderlyingIssuer(String underlyingIssuer) {
        this.underlyingIssuer = underlyingIssuer;
    }

    @Override
    public Integer getEncodedUnderlyingIssuerLen() {
        return encodedUnderlyingIssuerLen;
    }

    @Override
    public void setEncodedUnderlyingIssuerLen(Integer encodedUnderlyingIssuerLen) {
        this.encodedUnderlyingIssuerLen = encodedUnderlyingIssuerLen;
    }

    @Override
    public byte[] getEncodedUnderlyingIssuer() {
        return encodedUnderlyingIssuer;
    }

    @Override
    public void setEncodedUnderlyingIssuer(byte[] encodedUnderlyingIssuer) {
        this.encodedUnderlyingIssuer = encodedUnderlyingIssuer;
        if (encodedUnderlyingIssuerLen == null) {
            encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
        }
    }

    @Override
    public String getUnderlyingSecurityDesc() {
        return underlyingSecurityDesc;
    }

    @Override
    public void setUnderlyingSecurityDesc(String underlyingSecurityDesc) {
        this.underlyingSecurityDesc = underlyingSecurityDesc;
    }

    @Override
    public Integer getEncodedUnderlyingSecurityDescLen() {
        return encodedUnderlyingSecurityDescLen;
    }

    @Override
    public void setEncodedUnderlyingSecurityDescLen(Integer encodedUnderlyingSecurityDescLen) {
        this.encodedUnderlyingSecurityDescLen = encodedUnderlyingSecurityDescLen;
    }

    @Override
    public byte[] getEncodedUnderlyingSecurityDesc() {
        return encodedUnderlyingSecurityDesc;
    }

    @Override
    public void setEncodedUnderlyingSecurityDesc(byte[] encodedUnderlyingSecurityDesc) {
        this.encodedUnderlyingSecurityDesc = encodedUnderlyingSecurityDesc;
    }

    @Override
    public Date getQuoteSetValidUntilTime() {
        return quoteSetValidUntilTime;
    }

    @Override
    public void setQuoteSetValidUntilTime(Date quoteSetValidUntilTime) {
        this.quoteSetValidUntilTime = quoteSetValidUntilTime;
    }

    @Override
    public Integer getTotNoQuoteEntries() {
        return totNoQuoteEntries;
    }

    @Override
    public void setTotNoQuoteEntries(Integer totNoQuoteEntries) {
        this.totNoQuoteEntries = totNoQuoteEntries;
    }

    @Override
    public Integer getNoQuoteEntries() {
        return noQuoteEntries;
    }

    @Override
    public void setNoQuoteEntries(Integer noQuoteEntries) {
        this.noQuoteEntries = noQuoteEntries;
        if (noQuoteEntries != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            quoteEntryGroups = new QuoteEntryGroup[noQuoteEntries.intValue()];
            for (int i = 0; i < quoteEntryGroups.length; i++) {
                quoteEntryGroups[i] = new QuoteEntryGroup42(context);
            }
        }
    }

    @Override
    public QuoteEntryGroup[] getQuoteEntryGroups() {
        return quoteEntryGroups;
    }

    @Override
    public QuoteEntryGroup addQuoteEntryGroup() {
        QuoteEntryGroup group = new QuoteEntryGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<QuoteEntryGroup> groups = new ArrayList<QuoteEntryGroup>();
        if (quoteEntryGroups != null && quoteEntryGroups.length > 0) {
            groups = new ArrayList<QuoteEntryGroup>(Arrays.asList(quoteEntryGroups));
        }
        groups.add(group);
        quoteEntryGroups = groups.toArray(new QuoteEntryGroup[groups.size()]);
        noQuoteEntries = new Integer(quoteEntryGroups.length);

        return group;
    }

    @Override
    public QuoteEntryGroup deleteQuoteEntryGroup(int index) {
        QuoteEntryGroup result = null;
        if (quoteEntryGroups != null && quoteEntryGroups.length > 0 && quoteEntryGroups.length > index) {
            List<QuoteEntryGroup> groups = new ArrayList<QuoteEntryGroup>(Arrays.asList(quoteEntryGroups));
            result = groups.remove(index);
            quoteEntryGroups = groups.toArray(new QuoteEntryGroup[groups.size()]);
            if (quoteEntryGroups.length > 0) {
                noQuoteEntries = new Integer(quoteEntryGroups.length);
            } else {
                quoteEntryGroups = null;
                noQuoteEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteEntryGroups() {
        int result = 0;
        if (quoteEntryGroups != null && quoteEntryGroups.length > 0) {
            result = quoteEntryGroups.length;
            quoteEntryGroups = null;
            noQuoteEntries = null;
        }

        return result;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteSetID == null || quoteSetID.trim().isEmpty()) {
            errorMsg.append(" [QuoteSetID]");
            hasMissingTag = true;
        }
        if (underlyingSymbol == null || underlyingSymbol.trim().isEmpty()) {
            errorMsg.append(" [UnderlyingSymbol]");
            hasMissingTag = true;
        }
        if (totNoQuoteEntries == null) {
            errorMsg.append(" [TotNoQuoteEntries]");
            hasMissingTag = true;
        }
        if (noQuoteEntries == null) {
            errorMsg.append(" [NoQuoteEntries]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.QuoteSetID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteSetID, quoteSetID);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSymbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSymbol, underlyingSymbol);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSymbolSfx, underlyingSymbolSfx);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityID, underlyingSecurityID);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityIDSource, underlyingSecurityIDSource);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityType, underlyingSecurityType);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingMaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingMaturityMonthYear, underlyingMaturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingMaturityDay, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingMaturityDay, underlyingMaturityDay);
            }
            if (underlyingPutOrCall != null && MsgUtil.isTagInList(TagNum.UnderlyingPutOrCall, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingPutOrCall, underlyingPutOrCall.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingStrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingStrikePrice, underlyingStrikePrice);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingOptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingOptAttribute, underlyingOptAttribute);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingContractMultiplier, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingContractMultiplier, underlyingContractMultiplier);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingCouponRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingCouponRate, underlyingCouponRate);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityExchange, underlyingSecurityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingIssuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingIssuer, underlyingIssuer, sessionCharset);
            }
            if (encodedUnderlyingIssuerLen != null && encodedUnderlyingIssuerLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedUnderlyingIssuerLen, SECURED_TAGS, secured)) {
                if (encodedUnderlyingIssuer != null && encodedUnderlyingIssuer.length > 0) {
                    encodedUnderlyingIssuerLen = new Integer(encodedUnderlyingIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuerLen, encodedUnderlyingIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingIssuer, encodedUnderlyingIssuer);
                }
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityDesc, underlyingSecurityDesc, sessionCharset);
            }
            if (encodedUnderlyingSecurityDescLen != null && encodedUnderlyingSecurityDescLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedUnderlyingSecurityDescLen, SECURED_TAGS, secured)) {
                if (encodedUnderlyingSecurityDesc != null && encodedUnderlyingSecurityDesc.length > 0) {
                    encodedUnderlyingSecurityDescLen = new Integer(encodedUnderlyingSecurityDesc.length);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDescLen, encodedUnderlyingSecurityDescLen);
                    TagEncoder.encode(bao, TagNum.EncodedUnderlyingSecurityDesc, encodedUnderlyingSecurityDesc);
                }
            }
            if (MsgUtil.isTagInList(TagNum.QuoteSetValidUntilTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.QuoteSetValidUntilTime, quoteSetValidUntilTime);
            }
            if (MsgUtil.isTagInList(TagNum.TotNoQuoteEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoQuoteEntries, totNoQuoteEntries);
            }
            if (noQuoteEntries != null && MsgUtil.isTagInList(TagNum.NoQuoteEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoQuoteEntries, noQuoteEntries);
                if (quoteEntryGroups != null && quoteEntryGroups.length == noQuoteEntries.intValue()) {
                    for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                        if (quoteEntryGroups[i] != null) {
                            bao.write(quoteEntryGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "QuoteEntryGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoQuoteEntries.getValue(), error);
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
        if (QUOTE_ENTRY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteEntries != null && noQuoteEntries.intValue() > 0) {
                message.reset();
                if (quoteEntryGroups == null) {
                    quoteEntryGroups = new QuoteEntryGroup[noQuoteEntries.intValue()];
                }
                for (int i = 0; i < quoteEntryGroups.length; i++) {
                    QuoteEntryGroup group = new QuoteEntryGroup42(context);
                    group.decode(message);
                    quoteEntryGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {

        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedUnderlyingIssuerLen.getValue()) {
            try {
                encodedUnderlyingIssuerLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedUnderlyingIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.severe(error + " Error was: " + ex.toString());
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedUnderlyingIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedUnderlyingIssuerLen.intValue());
            encodedUnderlyingIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedUnderlyingSecurityDescLen.getValue()) {
            try {
                encodedUnderlyingSecurityDescLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedUnderlyingSecurityDescLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.severe(error + " Error was: " + ex.toString());
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedUnderlyingSecurityDescLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedUnderlyingSecurityDescLen.intValue());
            encodedUnderlyingSecurityDesc = dataTag.value;
        }

        return result;
    }
    
    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }
    
    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_V42;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteSetGroup] group version [4.2].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
