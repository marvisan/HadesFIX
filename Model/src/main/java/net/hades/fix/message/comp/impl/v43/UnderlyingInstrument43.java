/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument43.java
 *
 * $Id: UnderlyingInstrument43.java,v 1.10 2011-04-16 07:38:25 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v43.UnderlyingSecurityAltIDGroup43;
import net.hades.fix.message.struct.Tag;
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
import java.util.logging.Level;

import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.UnderlyingSecurityAltIDGroup;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.util.TagEncoder;

/**
 *  The UnderlyingInstrument component block for FIX version 4.3.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 16/12/2008, 7:28:06 PM
 */
public class UnderlyingInstrument43 extends UnderlyingInstrument {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> UNDLY_SEC_ALT_ID_GROUP_TAGS = new UnderlyingSecurityAltIDGroup43().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(UNDLY_SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDLY_SEC_ALT_ID_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingInstrument43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public UnderlyingInstrument43(FragmentContext context) {
        super(context);
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
    public Integer getNoUnderlyingSecurityAltID() {
        return noUnderlyingSecurityAltID;
    }

    @Override
    public void setNoUnderlyingSecurityAltID(Integer noUnderlyingSecurityAltID) {
        this.noUnderlyingSecurityAltID = noUnderlyingSecurityAltID;
        if (noUnderlyingSecurityAltID != null) {
            underlyingSecurityAltIDGroups = new UnderlyingSecurityAltIDGroup[noUnderlyingSecurityAltID.intValue()];
            for (int i = 0; i < underlyingSecurityAltIDGroups.length; i++) {
                underlyingSecurityAltIDGroups[i] = new UnderlyingSecurityAltIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public UnderlyingSecurityAltIDGroup[] getUnderlyingSecurityAltIDGroups() {
        return underlyingSecurityAltIDGroups;
    }

    public void setUnderlyingSecurityAltIDGroups(UnderlyingSecurityAltIDGroup[] underlyingSecurityAltIDGroups) {
        this.underlyingSecurityAltIDGroups = underlyingSecurityAltIDGroups;
        if (underlyingSecurityAltIDGroups != null) {
            noUnderlyingSecurityAltID = new Integer(underlyingSecurityAltIDGroups.length);
        }
    }

    @Override
    public UnderlyingSecurityAltIDGroup addUnderlyingSecurityAltIDGroup() {

        UnderlyingSecurityAltIDGroup group = new UnderlyingSecurityAltIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<UnderlyingSecurityAltIDGroup> groups = new ArrayList<UnderlyingSecurityAltIDGroup>();
        if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length > 0) {
            groups = new ArrayList<UnderlyingSecurityAltIDGroup>(Arrays.asList(underlyingSecurityAltIDGroups));
        }
        groups.add(group);
        underlyingSecurityAltIDGroups = groups.toArray(new UnderlyingSecurityAltIDGroup[groups.size()]);
        noUnderlyingSecurityAltID = new Integer(underlyingSecurityAltIDGroups.length);

        return group;
    }

    @Override
    public UnderlyingSecurityAltIDGroup deleteUnderlyingSecurityAltIDGroup(int index) {

        UnderlyingSecurityAltIDGroup result = null;

        if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length > 0 && underlyingSecurityAltIDGroups.length > index) {
            List<UnderlyingSecurityAltIDGroup> groups = new ArrayList<UnderlyingSecurityAltIDGroup>(Arrays.asList(underlyingSecurityAltIDGroups));
            result = groups.remove(index);
            underlyingSecurityAltIDGroups = groups.toArray(new UnderlyingSecurityAltIDGroup[groups.size()]);
            if (underlyingSecurityAltIDGroups.length > 0) {
                noUnderlyingSecurityAltID = new Integer(underlyingSecurityAltIDGroups.length);
            } else {
                underlyingSecurityAltIDGroups = null;
                noUnderlyingSecurityAltID = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingSecurityAltIDGroups() {

        int result = 0;
        if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length > 0) {
            result = underlyingSecurityAltIDGroups.length;
            underlyingSecurityAltIDGroups = null;
            noUnderlyingSecurityAltID = null;
        }

        return result;
    }

    @Override
    public Product getUnderlyingProduct() {
        return underlyingProduct;
    }

    @Override
    public void setUnderlyingProduct(Product underlyingProduct) {
        this.underlyingProduct = underlyingProduct;
    }

    @Override
    public String getUnderlyingCFICode() {
        return underlyingCFICode;
    }

    @Override
    public void setUnderlyingCFICode(String underlyingCFICode) {
        this.underlyingCFICode = underlyingCFICode;
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
    public Date getUnderlyingMaturityDate() {
        return underlyingMaturityDate;
    }

    @Override
    public void setUnderlyingMaturityDate(Date underlyingMaturityDate) {
        this.underlyingMaturityDate = underlyingMaturityDate;
    }

    @Override
    public Date getUnderlyingCouponPaymentDate() {
        return underlyingCouponPaymentDate;
    }

    @Override
    public void setUnderlyingCouponPaymentDate(Date underlyingCouponPaymentDate) {
        this.underlyingCouponPaymentDate = underlyingCouponPaymentDate;
    }

    @Override
    public Date getUnderlyingIssueDate() {
        return underlyingIssueDate;
    }

    @Override
    public void setUnderlyingIssueDate(Date underlyingIssueDate) {
        this.underlyingIssueDate = underlyingIssueDate;
    }

    @Override
    public String getUnderlyingRepoCollateralSecurityType() {
        return underlyingRepoCollateralSecurityType;
    }

    @Override
    public void setUnderlyingRepoCollateralSecurityType(String underlyingRepoCollateralSecurityType) {
        this.underlyingRepoCollateralSecurityType = underlyingRepoCollateralSecurityType;
    }

    @Override
    public Integer getUnderlyingRepurchaseTerm() {
        return underlyingRepurchaseTerm;
    }

    @Override
    public void setUnderlyingRepurchaseTerm(Integer underlyingRepurchaseTerm) {
        this.underlyingRepurchaseTerm = underlyingRepurchaseTerm;
    }

    @Override
    public Double getUnderlyingRepurchaseRate() {
        return underlyingRepurchaseRate;
    }

    @Override
    public void setUnderlyingRepurchaseRate(Double underlyingRepurchaseRate) {
        this.underlyingRepurchaseRate = underlyingRepurchaseRate;
    }

    @Override
    public Double getUnderlyingFactor() {
        return underlyingFactor;
    }

    @Override
    public void setUnderlyingFactor(Double underlyingFactor) {
        this.underlyingFactor = underlyingFactor;
    }

    @Override
    public String getUnderlyingCreditRating() {
        return underlyingCreditRating;
    }

    @Override
    public void setUnderlyingCreditRating(String underlyingCreditRating) {
        this.underlyingCreditRating = underlyingCreditRating;
    }

    @Override
    public String getUnderlyingInstrRegistry() {
        return underlyingInstrRegistry;
    }

    @Override
    public void setUnderlyingInstrRegistry(String underlyingInstrRegistry) {
        this.underlyingInstrRegistry = underlyingInstrRegistry;
    }

    @Override
    public String getUnderlyingCountryOfIssue() {
        return underlyingCountryOfIssue;
    }

    @Override
    public void setUnderlyingCountryOfIssue(String underlyingCountryOfIssue) {
        this.underlyingCountryOfIssue = underlyingCountryOfIssue;
    }

    @Override
    public String getUnderlyingStateOrProvinceOfIssue() {
        return underlyingStateOrProvinceOfIssue;
    }

    @Override
    public void setUnderlyingStateOrProvinceOfIssue(String underlyingStateOrProvinceOfIssue) {
        this.underlyingStateOrProvinceOfIssue = underlyingStateOrProvinceOfIssue;
    }

    @Override
    public String getUnderlyingLocaleOfIssue() {
        return underlyingLocaleOfIssue;
    }

    @Override
    public void setUnderlyingLocaleOfIssue(String underlyingLocaleOfIssue) {
        this.underlyingLocaleOfIssue = underlyingLocaleOfIssue;
    }

    @Override
    public Date getUnderlyingRedemptionDate() {
        return underlyingRedemptionDate;
    }

    @Override
    public void setUnderlyingRedemptionDate(Date underlyingRedemptionDate) {
        this.underlyingRedemptionDate = underlyingRedemptionDate;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
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
            if (noUnderlyingSecurityAltID != null && noUnderlyingSecurityAltID.intValue() > 0 && MsgUtil.isTagInList(TagNum.NoUnderlyingSecurityAltID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoUnderlyingSecurityAltID, noUnderlyingSecurityAltID);
                if (underlyingSecurityAltIDGroups != null && underlyingSecurityAltIDGroups.length == noUnderlyingSecurityAltID.intValue()) {
                    for (int i = 0; i < noUnderlyingSecurityAltID.intValue(); i++) {
                        if (underlyingSecurityAltIDGroups[i] != null) {
                            bao.write(underlyingSecurityAltIDGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "UnderlyingSecurityAltIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups,
                        TagNum.NoUnderlyingSecurityAltID.getValue(), error);
                }
            }
            if (underlyingProduct != null && MsgUtil.isTagInList(TagNum.UnderlyingProduct, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingProduct, underlyingProduct.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingCFICode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingCFICode, underlyingCFICode);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingSecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingSecurityType, underlyingSecurityType);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingMaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingMaturityMonthYear, underlyingMaturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingMaturityDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.UnderlyingMaturityDate, underlyingMaturityDate);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingCouponPaymentDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.UnderlyingCouponPaymentDate, underlyingCouponPaymentDate);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingIssueDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.UnderlyingIssueDate, underlyingIssueDate);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingRepoCollateralSecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingRepoCollateralSecurityType, underlyingRepoCollateralSecurityType);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingRepurchaseTerm, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingRepurchaseTerm, underlyingRepurchaseTerm);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingRepurchaseRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingRepurchaseRate, underlyingRepurchaseRate);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingFactor, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingFactor, underlyingFactor);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingCreditRating, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingCreditRating, underlyingCreditRating);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingInstrRegistry, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingInstrRegistry, underlyingInstrRegistry);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingCountryOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingCountryOfIssue, underlyingCountryOfIssue);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingStateOrProvinceOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingStateOrProvinceOfIssue, underlyingStateOrProvinceOfIssue);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingLocaleOfIssue, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.UnderlyingLocaleOfIssue, underlyingLocaleOfIssue);
            }
            if (MsgUtil.isTagInList(TagNum.UnderlyingRedemptionDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.UnderlyingRedemptionDate, underlyingRedemptionDate);
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
        if (UNDLY_SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyingSecurityAltID != null && noUnderlyingSecurityAltID.intValue() > 0) {
                message.reset();
                underlyingSecurityAltIDGroups = new UnderlyingSecurityAltIDGroup[noUnderlyingSecurityAltID.intValue()];
                for (int i = 0; i < noUnderlyingSecurityAltID.intValue(); i++) {
                    UnderlyingSecurityAltIDGroup group = new UnderlyingSecurityAltIDGroup43(context);
                    group.decode(message);
                    underlyingSecurityAltIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [UnderlyingInstrument] component version [4.3].";
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
