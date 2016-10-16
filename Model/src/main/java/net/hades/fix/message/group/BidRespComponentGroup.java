/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRespComponentGroup.java
 *
 * $Id: BidRespComponentGroup.java,v 1.1 2011-04-14 11:44:45 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.CommissionData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Country;
import net.hades.fix.message.type.NetGrossInd;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;

/**
 * Bid response component group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BidRespComponentGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.Country.getValue(),
        TagNum.Side.getValue(),
        TagNum.Price.getValue(),
        TagNum.PriceType.getValue(),
        TagNum.FairValue.getValue(),
        TagNum.NetGrossInd.getValue(),
        TagNum.SettlType.getValue(),
        TagNum.SettlDate.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 4.3 version.
     */
    protected CommissionData commissionData;

    /**
     * TagNum = 12. Starting with 4.2 version.
     */
    protected Double commission;

    /**
     * TagNum = 13. Starting with 4.2 version.
     */
    protected CommType commType;

    /**
     * TagNum = 66. Starting with 4.2 version.
     */
    protected String listID;

    /**
     * TagNum = 421. Starting with 4.2 version.
     */
    protected Country country;

    /**
     * TagNum = 54. Starting with 4.2 version.
     */
    protected Side side;

    /**
     * TagNum = 44. Starting with 4.0 version.
     */
    protected Double price;

    /**
     * TagNum = 423. Starting with 4.3 version.
     */
    protected PriceType priceType;

    /**
     * TagNum = 406. Starting with 4.2 version.
     */
    protected Double fairValue;

    /**
     * TagNum = 430. Starting with 4.2 version.
     */
    protected NetGrossInd netGrossInd;

    /**
     * TagNum = 63. Starting with 4.2 version.
     */
    protected String settlType;

    /**
     * TagNum = 64. Starting with 4.2 version.
     */
    protected Date settlDate;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.4 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 58. Starting with 4.2 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.2 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.2 version.
     */
    protected byte[] encodedText;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidRespComponentGroup() {
    }

    public BidRespComponentGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.Commission.getValue();
    }

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
    @FIXVersion(introduced="4.3")
    public CommissionData getCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the CommissionData component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3")
    public void setCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

     /**
     * Sets the CommissionData component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearCommissionData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public Double getCommission() {
        return getSafeCommissionData().getCommission();
    }

    /**
     * Message field setter.
     * @param commission field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.Commission)
    public void setCommission(Double commission) {
        getSafeCommissionData().setCommission(commission);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public CommType getCommType() {
        return getSafeCommissionData().getCommType();
    }

    /**
     * Message field setter.
     * @param commType field value
     */
    @FIXVersion(introduced="4.2", retired="4.3")
    @TagNumRef(tagNum=TagNum.CommType)
    public void setCommType(CommType commType) {
        getSafeCommissionData().setCommType(commType);
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.ListID)
    public String getListID() {
        return listID;
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.ListID)
    public void setListID(String listID) {
        this.listID = listID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.Country)
    public Country getCountry() {
        return country;
    }

    /**
     * Message field setter.
     * @param country field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.Country)
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Side)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.Side)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Price)
    public Double getPrice() {
        return price;
    }

    /**
     * Message field setter.
     * @param price field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.Price)
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.FairValue)
    public Double getFairValue() {
        return fairValue;
    }

    /**
     * Message field setter.
     * @param fairValue field value
     */
    @FIXVersion(introduced = "4.2")
    @TagNumRef(tagNum = TagNum.FairValue)
    public void setFairValue(Double fairValue) {
        this.fairValue = fairValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NetGrossInd)
    public NetGrossInd getNetGrossInd() {
        return netGrossInd;
    }

    /**
     * Message field setter.
     * @param netGrossInd field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NetGrossInd)
    public void setNetGrossInd(NetGrossInd netGrossInd) {
        this.netGrossInd = netGrossInd;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlType)
    public String getSettlType() {
        return settlType;
    }

    /**
     * Message field setter.
     * @param settlType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlType)
    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public Date getSettlDate() {
        return settlDate;
    }

    /**
     * Message field setter.
     * @param settlDate field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.SettlDate)
    public void setSettlDate(Date settlDate) {
        this.settlDate = settlDate;
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.4")
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
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        return encodedText;
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (listID == null || listID.trim().isEmpty()) {
            errorMsg.append(" [ListID]");
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
            if (commissionData != null) {
                bao.write(commissionData.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Commission, commission);
            if (commType != null) {
                TagEncoder.encode(bao, TagNum.CommType, commType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ListID, listID);
            if (country != null) {
                TagEncoder.encode(bao, TagNum.Country, country.getValue());
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            TagEncoder.encode(bao, TagNum.Price, price);
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            TagEncoder.encode(bao, TagNum.FairValue, fairValue);
            if (netGrossInd != null) {
                TagEncoder.encode(bao, TagNum.NetGrossInd, netGrossInd.getValue());
            }
            TagEncoder.encode(bao, TagNum.SettlType, settlType);
            TagEncoder.encodeDate(bao, TagNum.SettlDate, settlDate);
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
            case Commission:
                commission = new Double(new String(tag.value, sessionCharset));
                break;

            case CommType:
                commType = CommType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;

            case Country:
                country = Country.valueFor(new String(tag.value, sessionCharset));
                break;
                
            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case Price:
                price = new Double(new String(tag.value, sessionCharset));
                break;

            case PriceType:
                priceType = PriceType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case FairValue:
                fairValue = new Double(new String(tag.value, sessionCharset));
                break;

            case NetGrossInd:
                netGrossInd = NetGrossInd.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SettlType:
                settlType = new String(tag.value, sessionCharset);
                break;

            case SettlDate:
                settlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [BidRespComponentGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
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
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, TagNum.EncodedTextLen.getValue(), error);
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

    private CommissionData getSafeCommissionData() {
        if (getCommissionData() == null) {
            setCommissionData();
        }

        return getCommissionData();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{BidRespComponentGroup=");
        printTagValue(b, commissionData);
        printTagValue(b, TagNum.Commission, commission);
        printTagValue(b, TagNum.CommType, commType);
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, TagNum.Country, country);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.Price, price);
        printTagValue(b, TagNum.PriceType, priceType);
        printTagValue(b, TagNum.FairValue, fairValue);
        printTagValue(b, TagNum.NetGrossInd, netGrossInd);
        printTagValue(b, TagNum.SettlType, settlType);
        printDateTagValue(b, TagNum.SettlDate, settlDate);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
