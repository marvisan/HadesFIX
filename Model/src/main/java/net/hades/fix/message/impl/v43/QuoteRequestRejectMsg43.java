/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestRejectMsg43.java
 *
 * $Id: QuoteRequestRejectMsg43.java,v 1.9 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.QuoteRequestRejectMsg;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteRequestRejectGroup;
import net.hades.fix.message.group.impl.v43.QuoteRequestRejectGroup43;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 QuoteRequestRejectMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class QuoteRequestRejectMsg43 extends QuoteRequestRejectMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3926704580953687256L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> QUOT_REQ_REJ_GROUP_TAGS = new QuoteRequestRejectGroup43().getFragmentAllTags();
    
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
    
    public QuoteRequestRejectMsg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteRequestRejectMsg43(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public QuoteRequestRejectMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteRequestRejectMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    @Override
    public String getRfqReqID() {
        return rfqReqID;
    }

    @Override
    public void setRfqReqID(String rfqReqID) {
        this.rfqReqID = rfqReqID;
    }

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
                quoteRequestRejectGroups[i] = new QuoteRequestRejectGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

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
        QuoteRequestRejectGroup group = new QuoteRequestRejectGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

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
            LOGGER.severe(error + " Error was : " + ex.toString());
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
                    QuoteRequestRejectGroup group = new QuoteRequestRejectGroup43(context);
                    group.decode(message);
                    quoteRequestRejectGroups[i] = group;
                }
            }
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteRequestRejectMsg] message version [4.3].";
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
