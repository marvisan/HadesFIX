/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg41.java
 *
 * $Id: IOIMsg41.java,v 1.12 2011-04-14 23:45:00 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
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

import net.hades.fix.message.Header;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.IOIQualifierGroup;
import net.hades.fix.message.group.impl.v40.IOIQualifierGroup40;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.1 IOIMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 09/02/2009, 7:06:44 PM
 */
public class IOIMsg41 extends IOIMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V41 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.IOIID.getValue(),
        TagNum.IOITransType.getValue(),
        TagNum.IOIRefID.getValue(),
        TagNum.Symbol.getValue(),
        TagNum.SymbolSfx.getValue(),
        TagNum.SecurityID.getValue(),
        TagNum.SecurityIDSource.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.MaturityMonthYear.getValue(),
        TagNum.MaturityDay.getValue(),
        TagNum.PutOrCall.getValue(),
        TagNum.StrikePrice.getValue(),
        TagNum.OptAttribute.getValue(),
        TagNum.ContractMultiplier.getValue(),
        TagNum.CouponRate.getValue(),
        TagNum.SecurityExchange.getValue(),
        TagNum.Issuer.getValue(),
        TagNum.SecurityDesc.getValue(),
        TagNum.Side.getValue(),
        TagNum.IOIQty.getValue(),
        TagNum.Currency.getValue(),
        TagNum.Price.getValue(),
        TagNum.ValidUntilTime.getValue(),
        TagNum.IOIQltyInd.getValue(),
        TagNum.IOIOthSvc.getValue(),
        TagNum.IOINaturalFlag.getValue(),
        TagNum.NoIOIQualifiers.getValue(),
        TagNum.Text.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.URLLink.getValue(),
        TagNum.NoRoutingIDs.getValue(),
        TagNum.Spread.getValue(),
        TagNum.Benchmark.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS_V41 = null;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> IOI_QUALIFIER_GROUP_TAGS = new IOIQualifierGroup40().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V41);
        ALL_TAGS.addAll(IOI_QUALIFIER_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(IOI_QUALIFIER_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public IOIMsg41() {
        super();
    }

    public IOIMsg41(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public IOIMsg41(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public IOIMsg41(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V41;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getIoiID() {
        return ioiID;
    }

    @Override
    public void setIoiID(String ioiID) {
        this.ioiID = ioiID;
    }

    @Override
    public IOITransType getIoiTransType() {
        return ioiTransType;
    }

    @Override
    public void setIoiTransType(IOITransType ioiTransType) {
        this.ioiTransType = ioiTransType;
    }

    @Override
    public String getIoiRefID() {
        return ioiRefID;
    }

    @Override
    public void setIoiRefID(String ioiRefID) {
        this.ioiRefID = ioiRefID;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbolSfx() {
        return symbolSfx;
    }

    @Override
    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
    }

    @Override
    public String getSecurityID() {
        return securityID;
    }

    @Override
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    @Override
    public String getSecurityIDSource() {
        return securityIDSource;
    }

    @Override
    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
    }

    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    @Override
    public String getMaturityMonthYear() {
        return maturityMonthYear;
    }

    @Override
    public void setMaturityMonthYear(String maturityMonthYear) {
        this.maturityMonthYear = maturityMonthYear;
    }

    @Override
    public Integer getMaturityDay() {
        return maturityDay;
    }

    @Override
    public void setMaturityDay(Integer maturityDay) {
        this.maturityDay = maturityDay;
    }

    @Override
    public PutOrCall getPutOrCall() {
        return putOrCall;
    }

    @Override
    public void setPutOrCall(PutOrCall putOrCall) {
        this.putOrCall = putOrCall;
    }

    @Override
    public Double getStrikePrice() {
        return strikePrice;
    }

    @Override
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    @Override
    public Character getOptAttribute() {
        return optAttribute;
    }

    @Override
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
    }

    @Override
    public Double getContractMultiplier() {
        return contractMultiplier;
    }

    @Override
    public void setContractMultiplier(Double contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    @Override
    public Double getCouponRate() {
        return couponRate;
    }

    @Override
    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    @Override
    public String getSecurityExchange() {
        return securityExchange;
    }

    @Override
    public void setSecurityExchange(String securityExchange) {
        this.securityExchange = securityExchange;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public Integer getEncodedIssuerLen() {
        return encodedIssuerLen;
    }

    @Override
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        this.encodedIssuerLen = encodedIssuerLen;
    }

    @Override
    public byte[] getEncodedIssuer() {
        return encodedIssuer;
    }

    @Override
    public void setEncodedIssuer(byte[] encodedIssuer) {
        this.encodedIssuer = encodedIssuer;
        if (encodedIssuerLen == null) {
            encodedIssuerLen = new Integer(encodedIssuer.length);
        }
    }

    @Override
    public String getSecurityDesc() {
        return securityDesc;
    }

    @Override
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    @Override
    public Integer getEncodedSecurityDescLen() {
        return encodedSecurityDescLen;
    }

    @Override
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        this.encodedSecurityDescLen = encodedSecurityDescLen;
    }

    @Override
    public byte[] getEncodedSecurityDesc() {
        return encodedSecurityDesc;
    }

    @Override
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        this.encodedSecurityDesc = encodedSecurityDesc;
        if (encodedSecurityDescLen == null) {
            encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
        }
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public IOIQty getIoiQty() {
        return ioiQty;
    }

    @Override
    public void setIoiQty(IOIQty ioiQty) {
        this.ioiQty = ioiQty;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Date getValidUntilTime() {
        return validUntilTime;
    }

    @Override
    public void setValidUntilTime(Date validUntilTime) {
        this.validUntilTime = validUntilTime;
    }

    @Override
    public IOIQltyInd getIoiQltyInd() {
        return ioiQltyInd;
    }

    @Override
    public void setIoiQltyInd(IOIQltyInd ioiQltyInd) {
        this.ioiQltyInd = ioiQltyInd;
    }

    @Override
    public Character getIoiOthSvc() {
        return ioiOthSvc;
    }

    @Override
    public void setIoiOthSvc(Character ioiOthSvc) {
        this.ioiOthSvc = ioiOthSvc;
    }

    @Override
    public Boolean getIoiNaturalFlag() {
        return ioiNaturalFlag;
    }

    @Override
    public void setIoiNaturalFlag(Boolean ioiNaturalFlag) {
        this.ioiNaturalFlag = ioiNaturalFlag;
    }

    @Override
    public Integer getNoIOIQualifiers() {
        return noIOIQualifiers;
    }

    @Override
    public void setNoIOIQualifiers(Integer noIOIQualifiers) {
        this.noIOIQualifiers = noIOIQualifiers;
        if (noIOIQualifiers != null) {
            ioiQualifiers = new IOIQualifierGroup[noIOIQualifiers.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < ioiQualifiers.length; i++) {
                ioiQualifiers[i] = new IOIQualifierGroup40(context);
            }
        }
    }

    @Override
    public IOIQualifierGroup[] getIoiQualifiers() {
        return ioiQualifiers;
    }

    @Override
    public IOIQualifierGroup addIoiQualifier() {

        IOIQualifierGroup group = new IOIQualifierGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<IOIQualifierGroup> groups = new ArrayList<IOIQualifierGroup>();
        if (ioiQualifiers != null && ioiQualifiers.length > 0) {
            groups = new ArrayList<IOIQualifierGroup>(Arrays.asList(ioiQualifiers));
        }
        groups.add(group);
        ioiQualifiers = groups.toArray(new IOIQualifierGroup[groups.size()]);
        noIOIQualifiers = new Integer(ioiQualifiers.length);

        return group;
    }

    @Override
    public IOIQualifierGroup deleteIoiQualifier(int index) {
        IOIQualifierGroup result = null;
        if (ioiQualifiers != null && ioiQualifiers.length > 0 && ioiQualifiers.length > index) {
            List<IOIQualifierGroup> groups = new ArrayList<IOIQualifierGroup>(Arrays.asList(ioiQualifiers));
            result = groups.remove(index);
            ioiQualifiers = groups.toArray(new IOIQualifierGroup[groups.size()]);
            if (ioiQualifiers.length > 0) {
                noIOIQualifiers = new Integer(ioiQualifiers.length);
            } else {
                ioiQualifiers = null;
                noIOIQualifiers = null;
            }
        }

        return result;
    }

    @Override
    public int clearIoiQualifiers() {
        int result = 0;
        if (ioiQualifiers != null && ioiQualifiers.length > 0) {
            result = ioiQualifiers.length;
            ioiQualifiers = null;
            noIOIQualifiers = null;
        }

        return result;
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
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public String getUrlLink() {
        return urlLink;
    }

    @Override
    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (ioiID == null || ioiID.trim().isEmpty()) {
            errorMsg.append(" [IOIID]");
            hasMissingTag = true;
        }
        if (ioiTransType == null) {
            errorMsg.append(" [IOITransType]");
            hasMissingTag = true;
        }
        if (symbol == null || symbol.trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (side == null) {
            errorMsg.append(" [Side]");
            hasMissingTag = true;
        }
        if (ioiQty == null) {
            errorMsg.append(" [IOIQty]");
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
            if (MsgUtil.isTagInList(TagNum.IOIID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIID, ioiID);
            }
            if (ioiTransType != null && MsgUtil.isTagInList(TagNum.IOITransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOITransType, ioiTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.IOIRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIRefID, ioiRefID);
            }
            if (MsgUtil.isTagInList(TagNum.Symbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Symbol, symbol);
            }
            if (MsgUtil.isTagInList(TagNum.SymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityID, securityID);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityDay, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityDay, maturityDay);
            }
            if (putOrCall != null && MsgUtil.isTagInList(TagNum.PutOrCall, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.StrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            }
            if (MsgUtil.isTagInList(TagNum.OptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.Issuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Issuer, issuer);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (ioiQty != null && MsgUtil.isTagInList(TagNum.IOIQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIQty, ioiQty.getValue());
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (MsgUtil.isTagInList(TagNum.ValidUntilTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ValidUntilTime, validUntilTime);
            }
            if (ioiQltyInd != null && MsgUtil.isTagInList(TagNum.IOIQltyInd, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.IOIQltyInd, ioiQltyInd.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.IOIOthSvc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIOthSvc, ioiOthSvc);
            }
            if (MsgUtil.isTagInList(TagNum.IOINaturalFlag, SECURED_TAGS, secured)) {
               TagEncoder.encode(bao, TagNum.IOINaturalFlag, ioiNaturalFlag);
            }
            if (noIOIQualifiers != null && MsgUtil.isTagInList(TagNum.NoIOIQualifiers, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoIOIQualifiers, noIOIQualifiers);
                if (ioiQualifiers != null && ioiQualifiers.length == noIOIQualifiers.intValue()) {
                    for (int i = 0; i < noIOIQualifiers.intValue(); i++) {
                        if (ioiQualifiers[i] != null) {
                            bao.write(ioiQualifiers[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "IOIQualifierGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoIOIQualifiers.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.URLLink, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.URLLink, urlLink);
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
        if (IOI_QUALIFIER_GROUP_TAGS.contains(tag.tagNum)) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            if (noIOIQualifiers != null && noIOIQualifiers.intValue() > 0) {
                message.reset();
                ioiQualifiers = new IOIQualifierGroup[noIOIQualifiers.intValue()];
                for (int i = 0; i < ioiQualifiers.length; i++) {
                    IOIQualifierGroup group = new IOIQualifierGroup40(context);
                    group.decode(message);
                    ioiQualifiers[i] = group;
                }
            }
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [IOIMsg] message version [4.1].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_V41;
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
