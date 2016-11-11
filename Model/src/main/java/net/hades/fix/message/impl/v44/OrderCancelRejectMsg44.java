/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRejectMsg44.java
 *
 * $Id: OrderCancelRejectMsg44.java,v 1.1 2011-01-23 10:02:05 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.OrderCancelRejectMsg;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CxlRejReason;
import net.hades.fix.message.type.CxlRejResponseTo;
import net.hades.fix.message.type.OrdStatus;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 OrderCancelRejectMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="OrdCxlRej")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class OrderCancelRejectMsg44 extends OrderCancelRejectMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderCancelRejectMsg44() {
        super();
    }

    public OrderCancelRejectMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public OrderCancelRejectMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public OrderCancelRejectMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        OrderCancelRejectMsg44 fixml = (OrderCancelRejectMsg44) fragment;
        if (fixml.getOrderID() != null) {
            orderID = fixml.getOrderID();
        }
        if (fixml.getSecondaryOrderID() != null) {
            secondaryOrderID = fixml.getSecondaryOrderID();
        }
        if (fixml.getSecondaryClOrdID() != null) {
            secondaryClOrdID = fixml.getSecondaryClOrdID();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getClOrdLinkID() != null) {
            clOrdLinkID = fixml.getClOrdLinkID();
        }
        if (fixml.getOrigClOrdID() != null) {
            origClOrdID = fixml.getOrigClOrdID();
        }
        if (fixml.getOrdStatus() != null) {
            ordStatus = fixml.getOrdStatus();
        }
        if (fixml.getWorkingIndicator() != null) {
            workingIndicator = fixml.getWorkingIndicator();
        }
        if (fixml.getOrigOrdModTime() != null) {
            origOrdModTime = fixml.getOrigOrdModTime();
        }
        if (fixml.getListID() != null) {
            listID = fixml.getListID();
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
        if (fixml.getTradeOriginationDate() != null) {
            tradeOriginationDate = fixml.getTradeOriginationDate();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getCxlRejResponseTo() != null) {
            cxlRejResponseTo = fixml.getCxlRejResponseTo();
        }
        if (fixml.getCxlRejReason() != null) {
            cxlRejReason = fixml.getCxlRejReason();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
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

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "OrdID2")
    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    @XmlAttribute(name = "ID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "LnkID")
    @Override
    public String getClOrdLinkID() {
        return clOrdLinkID;
    }

    @Override
    public void setClOrdLinkID(String clOrdLinkID) {
        this.clOrdLinkID = clOrdLinkID;
    }

    @XmlAttribute(name = "OrigID")
    @Override
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    @Override
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    @XmlAttribute(name = "Stat")
    @Override
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    @Override
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    @XmlAttribute(name = "WorkingInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getWorkingIndicator() {
        return workingIndicator;
    }

    @Override
    public void setWorkingIndicator(Boolean workingIndicator) {
        this.workingIndicator = workingIndicator;
    }

    @XmlAttribute(name = "OrigOrdModTm")
    @Override
    public Date getOrigOrdModTime() {
        return origOrdModTime;
    }

    @Override
    public void setOrigOrdModTime(Date origOrdModTime) {
        this.origOrdModTime = origOrdModTime;
    }

    @XmlAttribute(name = "ListID")
    @Override
    public String getListID() {
        return listID;
    }

    @Override
    public void setListID(String listID) {
        this.listID = listID;
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

    @XmlAttribute(name = "OrignDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeOriginationDate() {
        return tradeOriginationDate;
    }

    @Override
    public void setTradeOriginationDate(Date tradeOriginationDate) {
        this.tradeOriginationDate = tradeOriginationDate;
    }

    @XmlAttribute(name = "TrdDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
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

    @XmlAttribute(name = "CxlRejRspTo")
    @Override
    public CxlRejResponseTo getCxlRejResponseTo() {
        return cxlRejResponseTo;
    }

    @Override
    public void setCxlRejResponseTo(CxlRejResponseTo CxlRejResponseTo) {
        this.cxlRejResponseTo = CxlRejResponseTo;
    }

    @XmlAttribute(name = "CxlRejRsn")
    @Override
    public CxlRejReason getCxlRejReason() {
        return cxlRejReason;
    }

    @Override
    public void setCxlRejReason(CxlRejReason cxlRejReason) {
        this.cxlRejReason = cxlRejReason;
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.ClOrdLinkID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdLinkID, clOrdLinkID);
            }
            if (MsgUtil.isTagInList(TagNum.OrigClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrigClOrdID, origClOrdID);
            }
            if (ordStatus != null && MsgUtil.isTagInList(TagNum.OrdStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrdStatus, ordStatus.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ClientID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientID, clientID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecBroker, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            }
            if (MsgUtil.isTagInList(TagNum.WorkingIndicator, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.WorkingIndicator, workingIndicator);
            }
            if (MsgUtil.isTagInList(TagNum.OrigOrdModTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.OrigOrdModTime, origOrdModTime);
            }
            if (MsgUtil.isTagInList(TagNum.ListID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ListID, listID);
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
            if (MsgUtil.isTagInList(TagNum.TradeOriginationDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeOriginationDate, tradeOriginationDate);
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (cxlRejResponseTo != null && MsgUtil.isTagInList(TagNum.CxlRejResponseTo, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CxlRejResponseTo, cxlRejResponseTo.getValue());
            }
            if (cxlRejReason != null && MsgUtil.isTagInList(TagNum.CxlRejReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CxlRejReason, cxlRejReason.getValue());
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
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderCancelRejectMsg] message version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
