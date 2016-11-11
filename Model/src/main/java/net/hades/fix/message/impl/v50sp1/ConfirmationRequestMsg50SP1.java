/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConfirmationRequestMsg50SP1.java
 *
 * $Id: AllocationInstructionMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.ConfirmationRequestMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderAllocGroup;
import net.hades.fix.message.group.impl.v50sp1.OrderAllocGroup50SP1;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.AllocAccountType;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ConfirmType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP1 ConfirmationRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="CnfmReq")
@XmlType(propOrder = {"header", "orderAllocGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class ConfirmationRequestMsg50SP1 extends ConfirmationRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ORDER_ALLOC_GROUP_TAGS = new OrderAllocGroup50SP1().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(ORDER_ALLOC_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(ORDER_ALLOC_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ConfirmationRequestMsg50SP1() {
        super();
    }

    public ConfirmationRequestMsg50SP1(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ConfirmationRequestMsg50SP1(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public ConfirmationRequestMsg50SP1(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        ConfirmationRequestMsg50SP1 fixml = (ConfirmationRequestMsg50SP1) fragment;
        if (fixml.getConfirmReqID() != null) {
            confirmReqID = fixml.getConfirmReqID();
        }
        if (fixml.getConfirmType() != null) {
            confirmType = fixml.getConfirmType();
        }
        if (fixml.getOrderAllocGroups() != null) {
            setOrderAllocGroups(fixml.getOrderAllocGroups());
        }
        if (fixml.getAllocID() != null) {
            allocID = fixml.getAllocID();
        }
        if (fixml.getSecondaryAllocID() != null) {
            secondaryAllocID = fixml.getSecondaryAllocID();
        }
        if (fixml.getIndividualAllocID() != null) {
            individualAllocID = fixml.getIndividualAllocID();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getAllocAccount() != null) {
            allocAccount = fixml.getAllocAccount();
        }
        if (fixml.getAllocAcctIDSource() != null) {
            allocAcctIDSource = fixml.getAllocAcctIDSource();
        }
        if (fixml.getAllocAccountType() != null) {
            allocAccountType = fixml.getAllocAccountType();
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

    @XmlAttribute(name = "CnfmReqID")
    @Override
    public String getConfirmReqID() {
        return confirmReqID;
    }

    @Override
    public void setConfirmReqID(String confirmReqID) {
        this.confirmReqID = confirmReqID;
    }

    @XmlAttribute(name = "CnfmTyp")
    @Override
    public ConfirmType getConfirmType() {
        return confirmType;
    }

    @Override
    public void setConfirmType(ConfirmType confirmType) {
        this.confirmType = confirmType;
    }

    @Override
    public Integer getNoOrders() {
        return noOrders;
    }

    @Override
    public void setNoOrders(Integer noOrders) {
        this.noOrders = noOrders;
        if (noOrders != null) {
            orderAllocGroups = new OrderAllocGroup[noOrders.intValue()];
            for (int i = 0; i < orderAllocGroups.length; i++) {
                orderAllocGroups[i] = new OrderAllocGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public OrderAllocGroup[] getOrderAllocGroups() {
        return orderAllocGroups;
    }
    
    public void setOrderAllocGroups(OrderAllocGroup[] orderAllocGroups) {
        this.orderAllocGroups = orderAllocGroups;
        if (orderAllocGroups != null) {
            noOrders = new Integer(orderAllocGroups.length);
        }
    }

    @Override
    public OrderAllocGroup addOrderAllocGroup() {
        OrderAllocGroup group = new OrderAllocGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<OrderAllocGroup> groups = new ArrayList<OrderAllocGroup>();
        if (orderAllocGroups != null && orderAllocGroups.length > 0) {
            groups = new ArrayList<OrderAllocGroup>(Arrays.asList(orderAllocGroups));
        }
        groups.add(group);
        orderAllocGroups = groups.toArray(new OrderAllocGroup[groups.size()]);
        noOrders = new Integer(orderAllocGroups.length);

        return group;
    }

    @Override
    public OrderAllocGroup deleteOrderAllocGroup(int index) {
        OrderAllocGroup result = null;
        if (orderAllocGroups != null && orderAllocGroups.length > 0 && orderAllocGroups.length > index) {
            List<OrderAllocGroup> groups = new ArrayList<OrderAllocGroup>(Arrays.asList(orderAllocGroups));
            result = groups.remove(index);
            orderAllocGroups = groups.toArray(new OrderAllocGroup[groups.size()]);
            if (orderAllocGroups.length > 0) {
                noOrders = new Integer(orderAllocGroups.length);
            } else {
                orderAllocGroups = null;
                noOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearOrderAllocGroups() {
        int result = 0;
        if (orderAllocGroups != null && orderAllocGroups.length > 0) {
            result = orderAllocGroups.length;
            orderAllocGroups = null;
            noOrders = null;
        }

        return result;
    }

    @XmlAttribute(name = "AllocID")
    @Override
    public String getAllocID() {
        return allocID;
    }

    @Override
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    @XmlAttribute(name = "AllocID2")
    @Override
    public String getSecondaryAllocID() {
        return secondaryAllocID;
    }

    @Override
    public void setSecondaryAllocID(String secondaryAllocID) {
        this.secondaryAllocID = secondaryAllocID;
    }

    @XmlAttribute(name = "IndAllocID")
    @Override
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    @Override
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
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

    @XmlAttribute(name = "AcctTyp")
    @Override
    public AllocAccountType getAllocAccountType() {
        return allocAccountType;
    }

    @Override
    public void setAllocAccountType(AllocAccountType allocAccountType) {
        this.allocAccountType = allocAccountType;
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (ORDER_ALLOC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noOrders != null && noOrders.intValue() > 0) {
                message.reset();
                orderAllocGroups = new OrderAllocGroup[noOrders.intValue()];
                for (int i = 0; i < noOrders.intValue(); i++) {
                    OrderAllocGroup group = new OrderAllocGroup50SP1(context);
                    group.decode(message);
                    orderAllocGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [ConfirmationRequestMsg] message version [5.0SP1].";
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
