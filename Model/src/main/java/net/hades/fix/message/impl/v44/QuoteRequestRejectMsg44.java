/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg44.java
 *
 * $Id: QuoteRequestRejectMsg44.java,v 1.13 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteRequestRejectGroup;
import net.hades.fix.message.group.impl.v44.QuoteRequestRejectGroup44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 QuoteRequestRejectMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name = "QuotReqRej")
@XmlType(propOrder = {"header", "quoteRequestRejectGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class QuoteRequestRejectMsg44 extends QuoteRequestRejectMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> QUOT_REQ_REJ_GROUP_TAGS = new QuoteRequestRejectGroup44().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(QUOT_REQ_REJ_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOT_REQ_REJ_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteRequestRejectMsg44() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteRequestRejectMsg44(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public QuoteRequestRejectMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteRequestRejectMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        QuoteRequestRejectMsg44 fixml = (QuoteRequestRejectMsg44) fragment;
        if (fixml.getQuoteReqID() != null) {
            quoteReqID = fixml.getQuoteReqID();
        }
        if (fixml.getRfqReqID() != null) {
            rfqReqID = fixml.getRfqReqID();
        }
        if (fixml.getQuoteRequestRejectReason() != null) {
            quoteRequestRejectReason = fixml.getQuoteRequestRejectReason();
        }
        if (fixml.getQuoteRequestRejectGroups() != null && fixml.getQuoteRequestRejectGroups().length > 0) {
           setQuoteRequestRejectGroups(fixml.getQuoteRequestRejectGroups());
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

    @XmlAttribute(name = "ReqRejRsn")
    @Override
    public Integer getQuoteRequestRejectReason() {
        return quoteRequestRejectReason;
    }

    @Override
    public void setQuoteRequestRejectReason(Integer quoteRequestRejectReason) {
        this.quoteRequestRejectReason = quoteRequestRejectReason;
    }

    @Override
    public Integer getNoRelatedSym() {
        return noRelatedSym;
    }

    @Override
    public void setNoRelatedSym(Integer noRelatedSym) {
        this.noRelatedSym = noRelatedSym;
        if (noRelatedSym != null) {
            quoteRequestRejectGroups = new QuoteRequestRejectGroup[noRelatedSym.intValue()];
            for (int i = 0; i < quoteRequestRejectGroups.length; i++) {
                quoteRequestRejectGroups[i] = new QuoteRequestRejectGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public QuoteRequestRejectGroup[] getQuoteRequestRejectGroups() {
        return quoteRequestRejectGroups;
    }

    public void setQuoteRequestRejectGroups(QuoteRequestRejectGroup[] quoteRequestRejectGroups) {
        this.quoteRequestRejectGroups = quoteRequestRejectGroups;
        if (quoteRequestRejectGroups != null) {
            noRelatedSym = new Integer(quoteRequestRejectGroups.length);
        }
    }

    @Override
    public QuoteRequestRejectGroup addQuoteRequestRejectGroup() {
        QuoteRequestRejectGroup group = new QuoteRequestRejectGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<QuoteRequestRejectGroup> groups = new ArrayList<QuoteRequestRejectGroup>();
        if (quoteRequestRejectGroups != null && quoteRequestRejectGroups.length > 0) {
            groups = new ArrayList<QuoteRequestRejectGroup>(Arrays.asList(quoteRequestRejectGroups));
        }
        groups.add(group);
        quoteRequestRejectGroups = groups.toArray(new QuoteRequestRejectGroup[groups.size()]);
        noRelatedSym = new Integer(quoteRequestRejectGroups.length);

        return group;
    }

    @Override
    public QuoteRequestRejectGroup deleteQuoteRequestRejectGroup(int index) {
        QuoteRequestRejectGroup result = null;
        if (quoteRequestRejectGroups != null && quoteRequestRejectGroups.length > 0 && quoteRequestRejectGroups.length > index) {
            List<QuoteRequestRejectGroup> groups = new ArrayList<QuoteRequestRejectGroup>(Arrays.asList(quoteRequestRejectGroups));
            result = groups.remove(index);
            quoteRequestRejectGroups = groups.toArray(new QuoteRequestRejectGroup[groups.size()]);
            if (quoteRequestRejectGroups.length > 0) {
                noRelatedSym = new Integer(quoteRequestRejectGroups.length);
            } else {
                quoteRequestRejectGroups = null;
                noRelatedSym = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteRequestRejectGroups() {
        int result = 0;
        if (quoteRequestRejectGroups != null && quoteRequestRejectGroups.length > 0) {
            result = quoteRequestRejectGroups.length;
            quoteRequestRejectGroups = null;
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.QuoteReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            }
            if (MsgUtil.isTagInList(TagNum.RFQReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RFQReqID, rfqReqID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteRequestRejectReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteRequestRejectReason, quoteRequestRejectReason);
            }
            if (noRelatedSym != null && MsgUtil.isTagInList(TagNum.NoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSym);
                if (quoteRequestRejectGroups != null && quoteRequestRejectGroups.length == noRelatedSym.intValue()) {
                    for (int i = 0; i < noRelatedSym.intValue(); i++) {
                        if (quoteRequestRejectGroups[i] != null) {
                            bao.write(quoteRequestRejectGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "QuoteRelatedSymbolGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRelatedSym.getValue(), error);
                }
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (QUOT_REQ_REJ_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSym != null && noRelatedSym.intValue() > 0) {
                message.reset();
                quoteRequestRejectGroups = new QuoteRequestRejectGroup[noRelatedSym.intValue()];
                for (int i = 0; i < quoteRequestRejectGroups.length; i++) {
                    QuoteRequestRejectGroup group = new QuoteRequestRejectGroup44(context);
                    group.decode(message);
                    quoteRequestRejectGroups[i] = group;
                }
            }
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteRequestRejectMsg] message version [4.4].";
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
