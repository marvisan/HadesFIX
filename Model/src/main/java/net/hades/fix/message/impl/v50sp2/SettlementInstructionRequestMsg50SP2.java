/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionRequestMsg50SP2.java
 *
 * $Id: SettlementInstructionsMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.SettlementInstructionRequestMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;

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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.StandInstDbType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP2 SettlementInstructionRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="SettlInstrctnReq")
@XmlType(propOrder = {"header", "partyIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SettlementInstructionRequestMsg50SP2 extends SettlementInstructionRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlementInstructionRequestMsg50SP2() {
        super();
    }

    public SettlementInstructionRequestMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SettlementInstructionRequestMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public SettlementInstructionRequestMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        SettlementInstructionRequestMsg50SP2 fixml = (SettlementInstructionRequestMsg50SP2) fragment;
        if (fixml.getSettlInstReqID() != null) {
            settlInstReqID = fixml.getSettlInstReqID();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
        }
        if (fixml.getAllocAccount() != null) {
            allocAccount = fixml.getAllocAccount();
        }
        if (fixml.getAllocAcctIDSource() != null) {
            allocAcctIDSource = fixml.getAllocAcctIDSource();
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getProduct() != null) {
            product = fixml.getProduct();
        }
        if (fixml.getSecurityType() != null) {
            securityType = fixml.getSecurityType();
        }
        if (fixml.getCfiCode() != null) {
            cfiCode = fixml.getCfiCode();
        }
        if (fixml.getSettlCurrency() != null) {
            settlCurrency = fixml.getSettlCurrency();
        }
        if (fixml.getEffectiveTime() != null) {
            effectiveTime = fixml.getEffectiveTime();
        }
        if (fixml.getExpireTime() != null) {
            expireTime = fixml.getExpireTime();
        }
        if (fixml.getLastUpdateTime() != null) {
            lastUpdateTime = fixml.getLastUpdateTime();
        }
        if (fixml.getStandInstDbType() != null) {
            standInstDbType = fixml.getStandInstDbType();
        }
        if (fixml.getStandInstDbName() != null) {
            standInstDbName = fixml.getStandInstDbName();
        }
        if (fixml.getStandInstDbID() != null) {
            standInstDbID = fixml.getStandInstDbID();
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

    @XmlAttribute(name = "SettlInstReqID")
    @Override
    public String getSettlInstReqID() {
        return settlInstReqID;
    }

    @Override
    public void setSettlInstReqID(String settlInstReqID) {
        this.settlInstReqID = settlInstReqID;
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50SP2(context);
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
            ((Parties50SP2) parties).setPartyIDGroups(partyIDGroups);
        }
    }
    
    @XmlAttribute(name = "Acct")
    @Override
    public String getAllocAccount() {
        return allocAccount;
    }

    @Override
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    @XmlAttribute(name = "ActIDSrc")
    @Override
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    @Override
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
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
    public String getCfiCode() {
        return cfiCode;
    }

    @Override
    public void setCfiCode(String cfiCode) {
        this.cfiCode = cfiCode;
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

    @XmlAttribute(name = "StandInstDbTyp")
    @Override
    public StandInstDbType getStandInstDbType() {
        return standInstDbType;
    }

    @Override
    public void setStandInstDbType(StandInstDbType standInstDbType) {
        this.standInstDbType = standInstDbType;
    }

    @XmlAttribute(name = "StandInstDbName")
    @Override
    public String getStandInstDbName() {
        return standInstDbName;
    }

    @Override
    public void setStandInstDbName(String standInstDbName) {
        this.standInstDbName = standInstDbName;
    }

    @XmlAttribute(name = "StandInstDbID")
    @Override
    public String getStandInstDbID() {
        return standInstDbID;
    }

    @Override
    public void setStandInstDbID(String standInstDbID) {
        this.standInstDbID = standInstDbID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP2(context);
            }
            parties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SettlementInstructionRequestMsg] message version [5.0SP2].";
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
