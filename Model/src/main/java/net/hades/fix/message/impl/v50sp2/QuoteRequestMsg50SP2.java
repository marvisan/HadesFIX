/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg50SP2.java
 *
 * $Id: QuoteRequestMsg50SP2.java,v 1.10 2011-04-14 23:44:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.comp.RootParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.group.impl.v50sp2.QuoteRequestGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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

import net.hades.fix.message.comp.impl.v50sp2.RootParties50SP2;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BookingType;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.RespondentType;

/**
 * FIX version 5.0SP2 QuoteRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 15/06/2009, 10:17:46 AM
 */
@XmlRootElement(name="QuotReq")
@XmlType(propOrder = {"header", "rootPartyIDGroups", "quoteRelatedSymbols"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteRequestMsg50SP2 extends QuoteRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -1522789661506632208L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> ROOT_PARTIES_COMP_TAGS = new RootParties50SP2().getFragmentAllTags();
    protected static final Set<Integer> RELATED_SYM_GROUP_TAGS = new QuoteRequestGroup50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(RELATED_SYM_GROUP_TAGS);
        ALL_TAGS.addAll(ROOT_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(RELATED_SYM_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROOT_PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteRequestMsg50SP2() {
        super();
    }

    public QuoteRequestMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public QuoteRequestMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public QuoteRequestMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        QuoteRequestMsg50SP2 fixml = (QuoteRequestMsg50SP2) fragment;
        if (fixml.getQuoteReqID() != null) {
            quoteReqID = fixml.getQuoteReqID();
        }
        if (fixml.getRfqReqID() != null) {
            rfqReqID = fixml.getRfqReqID();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getBookingType() != null) {
            bookingType = fixml.getBookingType();
        }
        if (fixml.getOrderCapacity() != null) {
            orderCapacity = fixml.getOrderCapacity();
        }
        if (fixml.getOrderRestrictions() != null) {
            orderRestrictions = fixml.getOrderRestrictions();
        }
        if (fixml.getPrivateQuote() != null) {
            privateQuote = fixml.getPrivateQuote();
        }
        if (fixml.getRespondentType() != null) {
            respondentType = fixml.getRespondentType();
        }
        if (fixml.getPreTradeAnonymity() != null) {
            preTradeAnonymity = fixml.getPreTradeAnonymity();
        }
        if (fixml.getRootParties() != null) {
            rootParties = fixml.getRootParties();
        }
        if (fixml.getQuoteRelatedSymbols() != null && fixml.getQuoteRelatedSymbols().length > 0) {
           setQuoteRelatedSymbols(fixml.getQuoteRelatedSymbols());
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

    @XmlAttribute(name = "ReqID")
    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    @XmlAttribute(name = "RFQReqID")
    @Override
    public String getRfqReqID() {
        return rfqReqID;
    }

    @Override
    public void setRfqReqID(String rfqReqID) {
        this.rfqReqID = rfqReqID;
    }

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "BkngTyp")
    @Override
    public BookingType getBookingType() {
        return bookingType;
    }

    @Override
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    @XmlAttribute(name = "Cpcty")
    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    @XmlAttribute(name = "Rstctions")
    @Override
    public String getOrderRestrictions() {
        return orderRestrictions;
    }

    @Override
    public void setOrderRestrictions(String orderRestrictions) {
        this.orderRestrictions = orderRestrictions;
    }

    @XmlAttribute(name = "PrvtQt")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPrivateQuote() {
        return privateQuote;
    }

    @Override
    public void setPrivateQuote(Boolean privateQuote) {
        this.privateQuote = privateQuote;
    }

    @XmlAttribute(name = "RspdntTyp")
    @Override
    public RespondentType getRespondentType() {
        return respondentType;
    }

    @Override
    public void setRespondentType(RespondentType respondentType) {
        this.respondentType = respondentType;
    }

    @XmlAttribute(name = "PrTrdAnon")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPreTradeAnonymity() {
        return preTradeAnonymity;
    }

    @Override
    public void setPreTradeAnonymity(Boolean preTradeAnonymity) {
        this.preTradeAnonymity = preTradeAnonymity;
    }

    @Override
    public RootParties getRootParties() {
        return rootParties;
    }

    @Override
    public void setRootParties() {
        this.rootParties = new RootParties50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearRootParties() {
        this.rootParties = null;
    }

    @XmlElementRef
    public RootPartyGroup[] getRootPartyIDGroups() {
        return rootParties == null ? null : rootParties.getRootPartyIDGroups();
    }

    public void setRootPartyIDGroups(RootPartyGroup[] rootPartyIDGroups) {
        if (rootPartyIDGroups != null) {
            if (rootParties == null) {
                setRootParties();
            }
            ((RootParties50SP2) rootParties).setRootPartyIDGroups(rootPartyIDGroups);
        }
    }

    @Override
    public Integer getNoRelatedSym() {
        return noRelatedSym;
    }

    @Override
    public void setNoRelatedSym(Integer noRelatedSym) {
        this.noRelatedSym = noRelatedSym;
        if (noRelatedSym != null) {
            quoteRelatedSymbols = new QuoteRequestGroup[noRelatedSym.intValue()];
            for (int i = 0; i < quoteRelatedSymbols.length; i++) {
                quoteRelatedSymbols[i] = new QuoteRequestGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteRequestGroup[] getQuoteRelatedSymbols() {
        return quoteRelatedSymbols;
    }

    public void setQuoteRelatedSymbols(QuoteRequestGroup[] quoteRelatedSymbols) {
        this.quoteRelatedSymbols = quoteRelatedSymbols;
        if (quoteRelatedSymbols != null) {
            noRelatedSym = new Integer(quoteRelatedSymbols.length);
        }
    }

    @Override
    public QuoteRequestGroup addQuoteRelatedSymbolGroup() {
        QuoteRequestGroup group = new QuoteRequestGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<QuoteRequestGroup> groups = new ArrayList<QuoteRequestGroup>();
        if (quoteRelatedSymbols != null && quoteRelatedSymbols.length > 0) {
            groups = new ArrayList<QuoteRequestGroup>(Arrays.asList(quoteRelatedSymbols));
        }
        groups.add(group);
        quoteRelatedSymbols = groups.toArray(new QuoteRequestGroup[groups.size()]);
        noRelatedSym = new Integer(quoteRelatedSymbols.length);

        return group;
    }

    @Override
    public QuoteRequestGroup deleteQuoteRelatedSymbolGroup(int index) {
        QuoteRequestGroup result = null;
        if (quoteRelatedSymbols != null && quoteRelatedSymbols.length > 0 && quoteRelatedSymbols.length > index) {
            List<QuoteRequestGroup> groups = new ArrayList<QuoteRequestGroup>(Arrays.asList(quoteRelatedSymbols));
            result = groups.remove(index);
            quoteRelatedSymbols = groups.toArray(new QuoteRequestGroup[groups.size()]);
            if (quoteRelatedSymbols.length > 0) {
                noRelatedSym = new Integer(quoteRelatedSymbols.length);
            } else {
                quoteRelatedSymbols = null;
                noRelatedSym = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteRelatedSymbolGroups() {
        int result = 0;
        if (quoteRelatedSymbols != null && quoteRelatedSymbols.length > 0) {
            result = quoteRelatedSymbols.length;
            quoteRelatedSymbols = null;
            noRelatedSym = null;
        }

        return result;
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
        if (RELATED_SYM_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSym != null && noRelatedSym.intValue() > 0) {
                message.reset();
                quoteRelatedSymbols = new QuoteRequestGroup[noRelatedSym.intValue()];
                for (int i = 0; i < quoteRelatedSymbols.length; i++) {
                    QuoteRequestGroup group = new QuoteRequestGroup50SP2(context);
                    group.decode(message);
                    quoteRelatedSymbols[i] = group;
                }
            }
        }
        if (ROOT_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (rootParties == null) {
                rootParties = new RootParties50SP2(context);
            }
            rootParties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteRequestMsg] message version [5.0SP2].";
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
