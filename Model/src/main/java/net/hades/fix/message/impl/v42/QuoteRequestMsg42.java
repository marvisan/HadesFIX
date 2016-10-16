/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestMsg42.java
 *
 * $Id: QuoteRequestMsg42.java,v 1.10 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.QuoteRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.group.impl.v42.QuoteRequestGroup42;

/**
 * FIX version 4.2 QuoteRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 05/04/2009, 10:17:46 AM
 */
public class QuoteRequestMsg42 extends QuoteRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> RELATED_SYM_GROUP_TAGS = new QuoteRequestGroup42().getFragmentTags();

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

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public QuoteRequestMsg42() {
        super();
    }

    public QuoteRequestMsg42(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public QuoteRequestMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public QuoteRequestMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public Integer getNoRelatedSym() {
        return noRelatedSym;
    }

    @Override
    public void setNoRelatedSym(Integer noRelatedSym) {
        this.noRelatedSym = noRelatedSym;
        if (noRelatedSym != null) {
            quoteRelatedSymbols = new QuoteRequestGroup[noRelatedSym.intValue()];
            for (int i = 0; i < quoteRelatedSymbols.length; i++) {
                quoteRelatedSymbols[i] = new QuoteRequestGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public QuoteRequestGroup[] getQuoteRelatedSymbols() {
        return quoteRelatedSymbols;
    }

    @Override
    public QuoteRequestGroup addQuoteRelatedSymbolGroup() {
        QuoteRequestGroup group = new QuoteRequestGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            if (noRelatedSym != null && MsgUtil.isTagInList(TagNum.NoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSym);
                if (quoteRelatedSymbols != null && quoteRelatedSymbols.length == noRelatedSym.intValue()) {
                    for (int i = 0; i < noRelatedSym.intValue(); i++) {
                        if (quoteRelatedSymbols[i] != null) {
                            bao.write(quoteRelatedSymbols[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "QuoteRelatedSymbolGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRelatedSym.getValue(), error);
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
        if (noRelatedSym != null && noRelatedSym.intValue() > 0) {
            if (RELATED_SYM_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                quoteRelatedSymbols = new QuoteRequestGroup[noRelatedSym.intValue()];
                for (int i = 0; i < quoteRelatedSymbols.length; i++) {
                    QuoteRequestGroup group = new QuoteRequestGroup42(context);
                    group.decode(message);
                    quoteRelatedSymbols[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteRequestMsg] message version [4.2].";
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
