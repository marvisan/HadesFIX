/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstGroup44.java
 *
 * $Id: SettlInstGroup44.java,v 1.3 2011-04-14 23:44:43 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.comp.impl.v44.Parties44;
import net.hades.fix.message.comp.impl.v44.SettlInstructionsData44;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.SettlInstGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.type.PaymentMethod;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SettlInstTransType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX 4.4 implementation of SettlInstGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="SetInst")
@XmlType(propOrder = {"partyIDGroups", "settlInstructionsData"})
@XmlAccessorType(XmlAccessType.NONE)
public class SettlInstGroup44 extends SettlInstGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties44().getFragmentAllTags();
    protected static final Set<Integer> SETTL_INST_COMP_TAGS = new SettlInstructionsData44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(SETTL_INST_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(SETTL_INST_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public SettlInstGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public SettlInstGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "SettlInstID")
    @Override
    public String getSettlInstID() {
        return settlInstID;
    }

    @Override
    public void setSettlInstID(String settlInstID) {
        this.settlInstID = settlInstID;
    }

    @XmlAttribute(name = "SettlInstTransTyp")
    @Override
    public SettlInstTransType getSettlInstTransType() {
        return settlInstTransType;
    }

    @Override
    public void setSettlInstTransType(SettlInstTransType settlInstTransType) {
        this.settlInstTransType = settlInstTransType;
    }

    @XmlAttribute(name = "SettlInstRefID")
    @Override
    public String getSettlInstRefID() {
        return settlInstRefID;
    }

    @Override
    public void setSettlInstRefID(String settlInstRefID) {
        this.settlInstRefID = settlInstRefID;
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

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @XmlAttribute(name = "Prod")
    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @XmlAttribute(name = "SecTyp")
    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    @XmlAttribute(name = "CFI")
    @Override
    public String getCFICode() {
        return CFICode;
    }

    @Override
    public void setCFICode(String CFICode) {
        this.CFICode = CFICode;
    }

    @XmlAttribute(name = "EfctvTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    @Override
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    @XmlAttribute(name = "ExpireTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @XmlAttribute(name = "LastUpdateTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @XmlElementRef
    @Override
    public SettlInstructionsData getSettlInstructionsData() {
        return settlInstructionsData;
    }

    @Override
    public void setSettlInstructionsData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.settlInstructionsData = new SettlInstructionsData44(context);
    }

    public void setSettlInstructionsData(SettlInstructionsData settlInstructionsData) {
        this.settlInstructionsData = settlInstructionsData;
    }

    @Override
    public void clearSettlInstructionsData() {
        this.settlInstructionsData = null;
    }

    @XmlAttribute(name = "PmtMethod")
    @Override
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @XmlAttribute(name = "PmtRef")
    @Override
    public String getPaymentRef() {
        return paymentRef;
    }

    @Override
    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    @XmlAttribute(name = "CardHolderName")
    @Override
    public String getCardHolderName() {
        return cardHolderName;
    }

    @Override
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @XmlAttribute(name = "CardNum")
    @Override
    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @XmlAttribute(name = "CardStartDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getCardStartDate() {
        return cardStartDate;
    }

    @Override
    public void setCardStartDate(Date cardStartDate) {
        this.cardStartDate = cardStartDate;
    }

    @XmlAttribute(name = "CardExpDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getCardExpDate() {
        return cardExpDate;
    }

    @Override
    public void setCardExpDate(Date cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    @XmlAttribute(name = "CardIssNum")
    @Override
    public String getCardIssNum() {
        return cardIssNum;
    }

    @Override
    public void setCardIssNum(String cardIssNum) {
        this.cardIssNum = cardIssNum;
    }

    @XmlAttribute(name = "PmtDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @XmlAttribute(name = "PmtRemtrID")
    @Override
    public String getPaymentRemitterID() {
        return paymentRemitterID;
    }

    @Override
    public void setPaymentRemitterID(String paymentRemitterID) {
        this.paymentRemitterID = paymentRemitterID;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.SettlInstID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstID, settlInstID);
            }
            if (settlInstTransType != null && MsgUtil.isTagInList(TagNum.SettlInstTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstTransType, settlInstTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SettlInstRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstRefID, settlInstRefID);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (product != null && MsgUtil.isTagInList(TagNum.Product, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.CFICode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CFICode, CFICode);
            }
            if (MsgUtil.isTagInList(TagNum.EffectiveTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            }
            if (MsgUtil.isTagInList(TagNum.ExpireTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            }
            if (MsgUtil.isTagInList(TagNum.LastUpdateTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.LastUpdateTime, lastUpdateTime);
            }
            if (settlInstructionsData != null) {
                bao.write(settlInstructionsData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (paymentMethod != null && MsgUtil.isTagInList(TagNum.PaymentMethod, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PaymentMethod, paymentMethod.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PaymentRef, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PaymentRef, paymentRef);
            }
            if (MsgUtil.isTagInList(TagNum.CardHolderName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CardHolderName, cardHolderName);
            }
            if (MsgUtil.isTagInList(TagNum.CardNumber, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CardNumber, cardNumber);
            }
            if (MsgUtil.isTagInList(TagNum.CardStartDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.CardStartDate, cardStartDate);
            }
            if (MsgUtil.isTagInList(TagNum.CardExpDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.CardExpDate, cardExpDate);
            }
            if (MsgUtil.isTagInList(TagNum.CardIssNum, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CardIssNum, cardIssNum);
            }
            if (MsgUtil.isTagInList(TagNum.PaymentDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.PaymentDate, paymentDate);
            }
            if (MsgUtil.isTagInList(TagNum.PaymentRemitterID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PaymentRemitterID, paymentRemitterID);
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
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties44(context);
            }
            parties.decode(tag, message);
        }
        if (SETTL_INST_COMP_TAGS.contains(tag.tagNum)) {
            if (settlInstructionsData == null) {
                settlInstructionsData = new SettlInstructionsData44(context);
            }
            settlInstructionsData.decode(tag, message);
        }
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
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SettlInstGroup] group version [4.4].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
