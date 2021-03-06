/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg50.java
 *
 * $Id: QuoteRequestMsg50.java,v 1.11 2011-04-14 23:44:40 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.struct.Tag;

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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.group.impl.v50.QuoteRequestGroup50;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.OrderCapacity;

/**
 * FIX version 5.0 QuoteRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 05/04/2009, 10:17:46 AM
 */
@XmlRootElement(name="QuotReq")
@XmlType(propOrder = {"header", "quoteRelatedSymbols"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteRequestMsg50 extends QuoteRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    protected static final Set<Integer> RELATED_SYM_GROUP_TAGS = new QuoteRequestGroup50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(RELATED_SYM_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(RELATED_SYM_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteRequestMsg50() {
        super();
    }

    public QuoteRequestMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public QuoteRequestMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public QuoteRequestMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        QuoteRequestMsg50 fixml = (QuoteRequestMsg50) fragment;
        if (fixml.getQuoteReqID() != null) {
            quoteReqID = fixml.getQuoteReqID();
        }
        if (fixml.getRfqReqID() != null) {
            rfqReqID = fixml.getRfqReqID();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getOrderCapacity() != null) {
            orderCapacity = fixml.getOrderCapacity();
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

    @XmlAttribute(name = "Cpcty")
    @Override
    public OrderCapacity getOrderCapacity() {
        return orderCapacity;
    }

    @Override
    public void setOrderCapacity(OrderCapacity orderCapacity) {
        this.orderCapacity = orderCapacity;
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
                quoteRelatedSymbols[i] = new QuoteRequestGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        QuoteRequestGroup group = new QuoteRequestGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
                    QuoteRequestGroup group = new QuoteRequestGroup50(context);
                    group.decode(message);
                    quoteRelatedSymbols[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteRequestMsg] message version [5.0].";
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
