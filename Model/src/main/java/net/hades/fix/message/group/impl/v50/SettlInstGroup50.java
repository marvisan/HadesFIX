/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstGroup50.java
 *
 * $Id: SettlInstGroup50.java,v 1.3 2011-04-14 23:44:35 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.comp.impl.v50.SettlInstructionsData50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.SettlInstGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PaymentMethod;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SettlInstTransType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX 5.0 implementation of SettlInstGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="SetInst")
@XmlType(propOrder = {"partyIDGroups", "settlInstructionsData"})
@XmlAccessorType(XmlAccessType.NONE)
public class SettlInstGroup50 extends SettlInstGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();
    protected static final Set<Integer> SETTL_INST_COMP_TAGS = new SettlInstructionsData50().getFragmentAllTags();

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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public SettlInstGroup50() {
    }
    
    public SettlInstGroup50(FragmentContext context) {
        super(context);
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50(context);
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

    @XmlAttribute(name = "SettlCcy")
    @Override
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    @Override
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.settlInstructionsData = new SettlInstructionsData50(context);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50(context);
            }
            parties.decode(tag, message);
        }
        if (SETTL_INST_COMP_TAGS.contains(tag.tagNum)) {
            if (settlInstructionsData == null) {
                settlInstructionsData = new SettlInstructionsData50(context);
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
        return "This tag is not supported in [SettlInstGroup] group version [5.0].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
