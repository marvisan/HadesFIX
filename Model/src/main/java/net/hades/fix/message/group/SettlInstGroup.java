/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstGroup.java
 *
 * $Id: SettlInstGroup.java,v 1.1 2011-03-25 04:50:53 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;

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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PaymentMethod;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlInstTransType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;
import java.util.Date;

/**
 * Settlement instructions group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SettlInstGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlInstID.getValue(),
        TagNum.SettlInstTransType.getValue(),
        TagNum.SettlInstRefID.getValue(),
        TagNum.Side.getValue(),
        TagNum.Product.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.CFICode.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.LastUpdateTime.getValue(),
        TagNum.PaymentMethod.getValue(),
        TagNum.PaymentRef.getValue(),
        TagNum.CardHolderName.getValue(),
        TagNum.CardNumber.getValue(),
        TagNum.CardStartDate.getValue(),
        TagNum.CardExpDate.getValue(),
        TagNum.CardIssNum.getValue(),
        TagNum.PaymentDate.getValue(),
        TagNum.PaymentRemitterID.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 162 REQUIRED. Starting with 4.4 version.
     */
    protected String settlInstID;

    /**
     * TagNum = 163. Starting with 4.4 version.
     */
    protected SettlInstTransType settlInstTransType;

    /**
     * TagNum = 214 REQUIRED. Starting with 4.4 version.
     */
    protected String settlInstRefID;

    /**
     * Starting with 4.4 version.
     */
    protected Parties parties;

    /**
     * TagNum = 54. Starting with 4.4 version.
     */
    protected Side side;

    /**
     * TagNum = 460. Starting with 4.4 version.
     */
    protected Product product;

    /**
     * TagNum = 167. Starting with 4.4 version.
     */
    protected String securityType;

    /**
     * TagNum = 461. Starting with 4.4 version.
     */
    protected String CFICode;

    /**
     * TagNum = 120. Starting with 5.0 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 168. Starting with 4.4 version.
     */
    protected Date effectiveTime;

    /**
     * TagNum = 126. Starting with 4.4 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 779. Starting with 4.4 version.
     */
    protected Date lastUpdateTime;

    /**
     * Starting with 4.4 version.
     */
    protected SettlInstructionsData settlInstructionsData;

    /**
     * TagNum = 492. Starting with 4.4 version.
     */
    protected PaymentMethod paymentMethod;

    /**
     * TagNum = 476. Starting with 4.4 version.
     */
    protected String paymentRef;

    /**
     * TagNum = 488. Starting with 4.4 version.
     */
    protected String cardHolderName;

    /**
     * TagNum = 489. Starting with 4.4 version.
     */
    protected String cardNumber;

    /**
     * TagNum = 503. Starting with 4.4 version.
     */
    protected Date cardStartDate;

    /**
     * TagNum = 490. Starting with 4.4 version.
     */
    protected Date cardExpDate;

    /**
     * TagNum = 491. Starting with 4.4 version.
     */
    protected String cardIssNum;

    /**
     * TagNum = 504. Starting with 4.4 version.
     */
    protected Date paymentDate;

    /**
     * TagNum = 505. Starting with 4.4 version.
     */
    protected String paymentRemitterID;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public SettlInstGroup() {
    }
    
    public SettlInstGroup(FragmentContext context) {
        super(context);
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstID, required=true)
    public String getSettlInstID() {
        return settlInstID;
    }

    /**
     * Message field setter.
     * @param settlInstID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstID, required=true)
    public void setSettlInstID(String settlInstID) {
        this.settlInstID = settlInstID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstTransType, required=true)
    public SettlInstTransType getSettlInstTransType() {
        return settlInstTransType;
    }

    /**
     * Message field setter.
     * @param settlInstTransType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstTransType, required=true)
    public void setSettlInstTransType(SettlInstTransType settlInstTransType) {
        this.settlInstTransType = settlInstTransType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstRefID, required=true)
    public String getSettlInstRefID() {
        return settlInstRefID;
    }

    /**
     * Message field setter.
     * @param settlInstRefID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstRefID, required=true)
    public void setSettlInstRefID(String settlInstRefID) {
        this.settlInstRefID = settlInstRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Product)
    public Product getProduct() {
        return product;
    }

    /**
     * Message field setter.
     * @param product field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Product)
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return securityType;
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum = TagNum.CFICode)
    public String getCFICode() {
        return CFICode;
    }

    /**
     * Message field setter.
     * @param CFICode field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum = TagNum.CFICode)
    public void setCFICode(String CFICode) {
        this.CFICode = CFICode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Message field setter.
     * @param effectiveTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

     /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastUpdateTime)
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Message field setter.
     * @param lastUpdateTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastUpdateTime)
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public SettlInstructionsData getSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SettlInstructionsData component class to the proper implementation.
     */
    @FIXVersion(introduced = "4.4")
    public void setSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the SettlInstructionsData component to null.
     */
    @FIXVersion(introduced = "4.4")
    public void clearSettlInstructionsData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentMethod)
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Message field setter.
     * @param paymentMethod field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentMethod)
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentRef)
    public String getPaymentRef() {
        return paymentRef;
    }

    /**
     * Message field setter.
     * @param paymentRef field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentRef)
    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardHolderName)
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Message field setter.
     * @param cardHolderName field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardHolderName)
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardNumber)
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Message field setter.
     * @param cardNumber field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardNumber)
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardStartDate)
    public Date getCardStartDate() {
        return cardStartDate;
    }

    /**
     * Message field setter.
     * @param cardStartDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardStartDate)
    public void setCardStartDate(Date cardStartDate) {
        this.cardStartDate = cardStartDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardExpDate)
    public Date getCardExpDate() {
        return cardExpDate;
    }

    /**
     * Message field setter.
     * @param cardExpDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardExpDate)
    public void setCardExpDate(Date cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardIssNum)
    public String getCardIssNum() {
        return cardIssNum;
    }

    /**
     * Message field setter.
     * @param cardIssNum field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.CardIssNum)
    public void setCardIssNum(String cardIssNum) {
        this.cardIssNum = cardIssNum;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentDate)
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Message field setter.
     * @param paymentDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentDate)
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentRemitterID)
    public String getPaymentRemitterID() {
        return paymentRemitterID;
    }

    /**
     * Message field setter.
     * @param paymentRemitterID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum=TagNum.PaymentRemitterID)
    public void setPaymentRemitterID(String paymentRemitterID) {
        this.paymentRemitterID = paymentRemitterID;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.SettlInstID.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (settlInstID == null || settlInstID.trim().isEmpty()) {
            errorMsg.append(" [SettlInstID]");
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
            TagEncoder.encode(bao, TagNum.SettlInstID, settlInstID);
            if (settlInstTransType != null) {
                TagEncoder.encode(bao, TagNum.SettlInstTransType, settlInstTransType.getValue());
            }
            TagEncoder.encode(bao, TagNum.SettlInstRefID, settlInstRefID);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (product != null) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.CFICode, CFICode);
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.LastUpdateTime, lastUpdateTime);
            if (settlInstructionsData != null) {
                bao.write(settlInstructionsData.encode(MsgSecureType.ALL_FIELDS));
            }
            if (paymentMethod != null) {
                TagEncoder.encode(bao, TagNum.PaymentMethod, paymentMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.PaymentRef, paymentRef);
            TagEncoder.encode(bao, TagNum.CardHolderName, cardHolderName);
            TagEncoder.encode(bao, TagNum.CardNumber, cardNumber);
            TagEncoder.encodeDate(bao, TagNum.CardStartDate, cardStartDate);
            TagEncoder.encodeDate(bao, TagNum.CardExpDate, cardExpDate);
            TagEncoder.encode(bao, TagNum.CardIssNum, cardIssNum);
            TagEncoder.encodeDate(bao, TagNum.PaymentDate, paymentDate);
            TagEncoder.encode(bao, TagNum.PaymentRemitterID, paymentRemitterID);
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
            case SettlInstID:
                settlInstID = new String(tag.value, sessionCharset);
                break;

            case SettlInstTransType:
                settlInstTransType = SettlInstTransType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SettlInstRefID:
                settlInstRefID = new String(tag.value, sessionCharset);
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case Product:
                product = Product.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case CFICode:
                CFICode = new String(tag.value, sessionCharset);
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case EffectiveTime:
                effectiveTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LastUpdateTime:
                lastUpdateTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case PaymentMethod:
                paymentMethod = PaymentMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PaymentRef:
                paymentRef = new String(tag.value, sessionCharset);
                break;

            case CardHolderName:
                cardHolderName = new String(tag.value, sessionCharset);
                break;

            case CardNumber:
                cardNumber = new String(tag.value, sessionCharset);
                break;

            case CardStartDate:
                cardStartDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case CardExpDate:
                cardExpDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case CardIssNum:
                cardIssNum = new String(tag.value, sessionCharset);
                break;

            case PaymentDate:
                paymentDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case PaymentRemitterID:
                paymentRemitterID = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SettlInstGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    /// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{SettlInstGroup=");
        printTagValue(b, TagNum.SettlInstID, settlInstID);
        printTagValue(b, TagNum.SettlInstTransType, settlInstTransType);
        printTagValue(b, TagNum.SettlInstRefID, settlInstRefID);
        printTagValue(b, parties);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.Product, product);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.CFICode, CFICode);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printUTCDateTimeTagValue(b, TagNum.EffectiveTime, effectiveTime);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printUTCDateTimeTagValue(b, TagNum.LastUpdateTime, lastUpdateTime);
        printTagValue(b, settlInstructionsData);
        printTagValue(b, TagNum.PaymentMethod, paymentMethod);
        printTagValue(b, TagNum.PaymentRef, paymentRef);
        printTagValue(b, TagNum.CardHolderName, cardHolderName);
        printTagValue(b, TagNum.CardNumber, cardNumber);
        printDateTagValue(b, TagNum.CardStartDate, cardStartDate);
        printDateTagValue(b, TagNum.CardExpDate, cardExpDate);
        printTagValue(b, TagNum.CardIssNum, cardIssNum);
        printDateTagValue(b, TagNum.PaymentDate, paymentDate);
        printTagValue(b, TagNum.PaymentRemitterID, paymentRemitterID);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
